package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constant.FilePaths;
import softuni.exam.constant.Messages;
import softuni.exam.domain.dtos.PlayerImportDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.enums.Position;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String NORTH_HUB_TEAM = "North Hub";

    private static final BigDecimal LOWER_BOUND_SALARY = BigDecimal.valueOf(100000);


    private final PlayerRepository playerRepository;

    private final TeamRepository teamRepository;

    private final PictureRepository pictureRepository;

    private final Gson gson;

    private final FileUtil fileUtil;

    private final ModelMapper modelMapper;

    private final ValidatorUtil validatorUtil;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository,
                             TeamRepository teamRepository,
                             PictureRepository pictureRepository,
                             Gson gson,
                             FileUtil fileUtil,
                             ModelMapper modelMapper,
                             ValidatorUtil validatorUtil) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String importPlayers() {
        StringBuilder importResult = new StringBuilder();

        Arrays.stream(this.gson.fromJson(this.readPlayersJsonFile(), PlayerImportDto[].class))
                .forEach(playerImportDto -> {
                    List<String> allPositions = Arrays.stream(Position.values())
                            .map(Enum::toString)
                            .collect(Collectors.toList());

                    Position playerPosition = null;
                    if (allPositions.contains(playerImportDto.getPosition())) {
                        playerPosition = Position.valueOf(playerImportDto.getPosition());
                    }
                    Picture playerPicture = this.pictureRepository.findByUrl(playerImportDto.getPicture().getUrl());
                    Team playerTeam = this.teamRepository.findByName(playerImportDto.getTeam().getName());

                    Player player = this.modelMapper.map(playerImportDto, Player.class);
                    player.setPosition(playerPosition);
                    player.setPicture(playerPicture);
                    player.setTeam(playerTeam);

                    if (!this.validatorUtil.isValid(player)) {
                        importResult.append(String.format(Messages.INCORRECT_DATA, player.getClass().getSimpleName().toLowerCase()))
                                .append(System.lineSeparator());
                        return;
                    }

                    this.playerRepository.saveAndFlush(player);

                    importResult.append(
                            String.format(Messages.SUCCESSFULLY_IMPORTED, player.getClass().getSimpleName().toLowerCase(),
                                    String.format("%s %s", player.getFirstName(), player.getLastName()))
                    ).append(System.lineSeparator());
                });

        return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() != 0;
    }

    @Override
    public String readPlayersJsonFile() {
        return this.fileUtil.readFile(FilePaths.PLAYERS_FILE_PATH);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder exportResult = new StringBuilder();

        this.playerRepository.findAllBySalaryGreaterThanOrderBySalaryDesc(LOWER_BOUND_SALARY)
                .forEach(player ->
                        exportResult.append(String.format("Player name: %s %s", player.getFirstName(), player.getLastName()))
                        .append(System.lineSeparator())
                        .append("\tNumber: ").append(player.getNumber())
                        .append(System.lineSeparator())
                        .append("\tSalary: ").append(player.getSalary())
                        .append(System.lineSeparator())
                        .append("\tTeam: ").append(player.getTeam().getName())
                        .append(System.lineSeparator())
                );

        return exportResult.toString().trim();
    }

    @Override
    public String exportPlayersInATeam() {
        StringBuilder exportResult = new StringBuilder();
        exportResult.append("Team: ").append(NORTH_HUB_TEAM)
                .append(System.lineSeparator());

        this.playerRepository.findAllByTeamNameOrderByIdAsc(NORTH_HUB_TEAM)
                .forEach(player ->
                        exportResult.append(
                                String.format("\tPlayer name: %s %s - %s", player.getFirstName(), player.getLastName(), player.getPosition())
                        )
                        .append(System.lineSeparator())
                        .append("\tNumber: ").append(player.getNumber())
                        .append(System.lineSeparator())
                );

        return exportResult.toString().trim();
    }
}
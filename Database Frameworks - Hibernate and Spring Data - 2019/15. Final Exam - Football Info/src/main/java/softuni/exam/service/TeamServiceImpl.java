package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constant.FilePaths;
import softuni.exam.constant.Messages;
import softuni.exam.domain.dtos.TeamImportRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    private final PictureRepository pictureRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    private final ValidatorUtil validatorUtil;

    public TeamServiceImpl(TeamRepository teamRepository,
                           PictureRepository pictureRepository,
                           FileUtil fileUtil,
                           XmlParser xmlParser,
                           ModelMapper modelMapper,
                           ValidatorUtil validatorUtil) {
        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String importTeams() {
        StringBuilder importResult = new StringBuilder();

        this.xmlParser.convertXmlToEntity(TeamImportRootDto.class, FilePaths.TEAMS_FILE_PATH)
                .getTeamImportXmlDtos()
                .forEach(teamImportDto -> {
                    Picture picture = this.pictureRepository.findByUrl(teamImportDto.getPicture().getUrl());
                    Team team = this.modelMapper.map(teamImportDto, Team.class);
                    team.setPicture(picture);

                    if (!this.validatorUtil.isValid(team)) {
                        importResult.append(String.format(Messages.INCORRECT_DATA, team.getClass().getSimpleName().toLowerCase()))
                                .append(System.lineSeparator());
                        return;
                    }

                    this.teamRepository.saveAndFlush(team);
                    importResult.append(
                            String.format(Messages.SUCCESSFULLY_IMPORTED, team.getClass().getSimpleName().toLowerCase(), team.getName())
                    ).append(System.lineSeparator());
                });

        return importResult.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() != 0;
    }

    @Override
    public String readTeamsXmlFile() {
        return this.fileUtil.readFile(FilePaths.TEAMS_FILE_PATH);
    }
}
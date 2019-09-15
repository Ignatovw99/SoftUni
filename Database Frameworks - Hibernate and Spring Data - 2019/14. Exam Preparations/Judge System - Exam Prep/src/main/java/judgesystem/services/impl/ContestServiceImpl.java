package judgesystem.services.impl;

import com.google.gson.Gson;
import judgesystem.domain.dtos.ContestImportDto;
import judgesystem.domain.dtos.StrategyImportDto;
import judgesystem.domain.entities.Category;
import judgesystem.domain.entities.Contest;
import judgesystem.domain.entities.Strategy;
import judgesystem.repositories.CategoryRepository;
import judgesystem.repositories.ContestRepository;
import judgesystem.repositories.StrategyRepository;
import judgesystem.services.api.ContestService;
import judgesystem.utils.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContestServiceImpl implements ContestService {

    private final ContestRepository contestRepository;

    private final CategoryRepository categoryRepository;

    private final StrategyRepository strategyRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    @Autowired
    public ContestServiceImpl(ContestRepository contestRepository, CategoryRepository categoryRepository, StrategyRepository strategyRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper) {
        this.contestRepository = contestRepository;
        this.categoryRepository = categoryRepository;
        this.strategyRepository = strategyRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void importContests(String contestsFilePath) {
        String contestsFileContent = this.fileUtil.readFile(contestsFilePath);
        Arrays.stream(this.gson.fromJson(contestsFileContent, ContestImportDto[].class))
                .forEach(contestImportDto -> {
                    Contest contest = this.modelMapper.map(contestImportDto, Contest.class);
                    Category category = this.categoryRepository.findById(contest.getCategory().getId())
                            .orElse(null);
                    contest.setCategory(category);

                    this.contestRepository.saveAndFlush(contest);

                    contestImportDto.getAllowedStrategies()
                            .stream()
                            .map(strategyImportDto -> this.strategyRepository.findByName(strategyImportDto.getName()))
                            .filter(Objects::nonNull)
                            .forEach(strategy -> {
                                strategy.getContests().add(contest);
                                this.strategyRepository.saveAndFlush(strategy);
                            });
                });
    }
}
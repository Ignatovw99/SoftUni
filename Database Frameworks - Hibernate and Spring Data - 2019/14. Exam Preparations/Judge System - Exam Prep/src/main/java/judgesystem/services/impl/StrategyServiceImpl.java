package judgesystem.services.impl;

import com.google.gson.Gson;
import judgesystem.domain.dtos.StrategyImportDto;
import judgesystem.domain.entities.Strategy;
import judgesystem.repositories.StrategyRepository;
import judgesystem.services.api.StrategyService;
import judgesystem.utils.FileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StrategyServiceImpl implements StrategyService {

    private final StrategyRepository strategyRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final ModelMapper modelMapper;

    @Autowired
    public StrategyServiceImpl(StrategyRepository strategyRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper) {
        this.strategyRepository = strategyRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importStrategies(String strategiesFilePath) {
        String strategiesFileContent = this.fileUtil.readFile(strategiesFilePath);
        Arrays.stream(this.gson.fromJson(strategiesFileContent, StrategyImportDto[].class))
                .map(strategyImportDto -> this.modelMapper.map(strategyImportDto, Strategy.class))
                .forEach(this.strategyRepository::saveAndFlush);
    }
}

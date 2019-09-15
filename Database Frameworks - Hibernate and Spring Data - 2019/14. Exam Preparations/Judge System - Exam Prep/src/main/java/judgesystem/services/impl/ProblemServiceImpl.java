package judgesystem.services.impl;

import judgesystem.domain.dtos.ProblemImportRootDto;
import judgesystem.domain.entities.Contest;
import judgesystem.domain.entities.Problem;
import judgesystem.repositories.ContestRepository;
import judgesystem.repositories.ProblemRepository;
import judgesystem.services.api.ProblemService;
import judgesystem.utils.FileUtil;
import judgesystem.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    private final ContestRepository contestRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository, ContestRepository contestRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.problemRepository = problemRepository;
        this.contestRepository = contestRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importProblems(String problemsFilePath) {
        String problemsFileContent = this.fileUtil.readFile(problemsFilePath);
        ProblemImportRootDto problemImportRootDto = this.xmlParser.convertFromXml(ProblemImportRootDto.class, problemsFileContent);
        problemImportRootDto.getProblemImportDtos()
                .forEach(problemImportDto -> {
                    Problem problem = this.modelMapper.map(problemImportDto, Problem.class);
                    Contest contest = this.contestRepository.findById(problemImportDto.getContest().getId())
                            .orElse(null);
                    problem.setContest(contest);
                    this.problemRepository.saveAndFlush(problem);
                });
    }
}

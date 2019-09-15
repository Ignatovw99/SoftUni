package judgesystem.services.impl;

import com.google.gson.Gson;
import judgesystem.domain.dtos.SubmissionImportDto;
import judgesystem.domain.dtos.SubmissionImportRootDto;
import judgesystem.domain.dtos.UserImportDto;
import judgesystem.domain.dtos.UserParticipationRootImportDto;
import judgesystem.domain.entities.*;
import judgesystem.repositories.*;
import judgesystem.services.api.UserService;
import judgesystem.utils.FileUtil;
import judgesystem.utils.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ContestRepository contestRepository;

    private final ProblemRepository problemRepository;

    private final StrategyRepository strategyRepository;

    private final SubmissionRepository submissionRepository;

    private final FileUtil fileUtil;

    private final Gson gson;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ContestRepository contestRepository,
                           ProblemRepository problemRepository,
                           StrategyRepository strategyRepository,
                           SubmissionRepository submissionRepository,
                           FileUtil fileUtil,
                           Gson gson,
                           XmlParser xmlParser,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
        this.problemRepository = problemRepository;
        this.strategyRepository = strategyRepository;
        this.submissionRepository = submissionRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importUsers(String usersFilePath) {
        String usersFileContent =  this.fileUtil.readFile(usersFilePath);
        Arrays.stream(this.gson.fromJson(usersFileContent, UserImportDto[].class))
                .map(userImportDto -> this.modelMapper.map(userImportDto, User.class))
                .forEach(this.userRepository::saveAndFlush);
    }

    @Transactional
    @Override
    public String enrollInContests(String userParticipationFilePath) {
        String userParticipationFileContent = this.fileUtil.readFile(userParticipationFilePath);
        UserParticipationRootImportDto userParticipationRootImportDto =
                this.xmlParser.convertFromXml(UserParticipationRootImportDto.class, userParticipationFileContent);

        StringBuilder sb = new StringBuilder();

        userParticipationRootImportDto.getUserParticipations()
                .forEach(userParticipation -> {
                    Long contestId = userParticipation.getContestIdDto().getId();
                    Long userId = userParticipation.getUserIdDto().getId();

                    Contest contest = this.contestRepository.findById(contestId)
                            .orElse(null);

                    if (contest == null) {
                        sb.append(String.format("A contest with id %d does not exist in the database.", contestId))
                                .append(System.lineSeparator());
                    } else {
                        User user = this.userRepository.findById(userId)
                                .orElse(null);

                        if (user == null) {
                            sb.append(String.format("A user with id %d does not exist in the database.", userId))
                                    .append(System.lineSeparator());
                        } else {
                            if (user.getContests().contains(contest)) {
                                sb.append(String.format("A user with username %s already has enrolled to %s.", user.getUsername(), contest.getName()))
                                        .append(System.lineSeparator());
                            } else {
                                user.getContests().add(contest);
                                contest.getContestants().add(user);
                                sb.append(String.format("The user %s enrolls to contest %s.", user.getUsername(), contest.getName()))
                                        .append(System.lineSeparator());
                            }
                        }
                    }
                });
        return sb.toString().trim();
    }

    @Transactional
    @Override
    public String submitProblems(String submissionFilePath) {
        String submissionsFileContent = this.fileUtil.readFile(submissionFilePath);
        SubmissionImportRootDto submissionImportRootDto = this.xmlParser.convertFromXml(SubmissionImportRootDto.class, submissionsFileContent);

        StringBuilder sb = new StringBuilder();
        List<SubmissionImportDto> submissionImportDtos = submissionImportRootDto.getSubmissionImportDtos();
        for (SubmissionImportDto submissionImportDto : submissionImportDtos) {
            Submission submission = this.modelMapper.map(submissionImportDto, Submission.class);

            Long userId = submissionImportDto.getUser().getId();
            Long problemId = submissionImportDto.getProblem().getId();
            Long strategyId = submissionImportDto.getStrategy().getId();

            User user = this.userRepository.findById(userId)
                    .orElse(null);
            Problem problem = this.problemRepository.findById(problemId)
                    .get();
            Contest contest = problem.getContest();
            Strategy strategy = this.strategyRepository.findById(strategyId)
                    .orElse(null);

            submission.setStrategy(strategy);

            if (!contest.getContestants().contains(user)) {
                sb.append("User is not contestant in contest!")
                        .append(System.lineSeparator());
                continue;
            }

            sb.append(this.addNewSubmissionToContest(user, contest, problem, submission))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    //TODO - refactor this method
    private String addNewSubmissionToContest(User user, Contest contest, Problem problem, Submission submission) {
        submission.setProblem(problem);
        submission.setUser(user);

        Submission persistedSubmission = this.submissionRepository.saveAndFlush(submission);
        user.getSubmissions().add(persistedSubmission);
        boolean hasUserAlreadyParticipatedInContest = user.getMaxResultsForContests()
                .stream()
                .anyMatch(maxResultForContest -> maxResultForContest.getContest().getId().equals(contest.getId()));

        if (!hasUserAlreadyParticipatedInContest) {
            MaxResultForContest maxResultForContest = new MaxResultForContest();
            maxResultForContest.setContest(contest);
            maxResultForContest.setUser(user);
            maxResultForContest.setAveragePerformance(0.0);
            user.getMaxResultsForContests().add(maxResultForContest);
        }

        boolean hasUserAlreadySolvedProblem = user.getMaxResultsForProblems()
                .stream()
                .anyMatch(maxResultForProblem -> maxResultForProblem.getProblem().getId().equals(problem.getId()));

        boolean hasBetterSolutionForProblem;

        if (!hasUserAlreadySolvedProblem) {
            MaxResultForProblem maxResultForProblem = new MaxResultForProblem();
            maxResultForProblem.setUser(user);
            maxResultForProblem.setProblem(problem);
            user.getMaxResultsForProblems().add(maxResultForProblem);
            hasBetterSolutionForProblem = true;
        } else {
            hasBetterSolutionForProblem = user.getMaxResultsForProblems()
                    .stream()
                    .filter(maxResultForProblem -> maxResultForProblem.getProblem().getId().equals(problem.getId()))
                    .anyMatch(maxResultForProblem ->
                        maxResultForProblem.getBestSubmission().getPoints() < persistedSubmission.getPoints() ||
                                Double.compare(maxResultForProblem.getBestSubmission().getPoints(), persistedSubmission.getPoints()) == 0
                    );
        }

        if (hasBetterSolutionForProblem) {
            MaxResultForProblem maxResultForProblem = user.getMaxResultsForProblems()
                    .stream()
                    .filter(maxResult -> maxResult.getProblem().getId().equals(problem.getId()))
                    .collect(Collectors.toList())
                    .get(0);

            double bestPointsForProblem;
            double bestSubmissionPerformance;
            if (maxResultForProblem.getBestSubmission() == null) {
                bestPointsForProblem = 0.0;
                bestSubmissionPerformance = 0.0;
            } else {
                bestPointsForProblem = maxResultForProblem.getBestSubmission().getPoints();
                bestSubmissionPerformance = maxResultForProblem.getBestSubmission().getPerformance();
            }

            double additionalPoints = persistedSubmission.getPoints() - bestPointsForProblem;

            MaxResultForContest maxResultForContest = user.getMaxResultsForContests()
                    .stream()
                    .filter(maxResult -> maxResult.getContest().getId().equals(contest.getId()))
                    .collect(Collectors.toList())
                    .get(0);

            maxResultForContest.setAveragePerformance((maxResultForContest.getAveragePerformance() + bestSubmissionPerformance) / 2.0);
            maxResultForContest.setTotalPoints(maxResultForContest.getTotalPoints() + additionalPoints);

            maxResultForProblem.setBestSubmission(persistedSubmission);
        }

        this.userRepository.saveAndFlush(user);
        return null;
    }
}
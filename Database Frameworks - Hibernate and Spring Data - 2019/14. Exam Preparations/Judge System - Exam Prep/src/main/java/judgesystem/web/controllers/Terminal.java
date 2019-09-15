package judgesystem.web.controllers;

import judgesystem.services.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import static judgesystem.constants.FilesPaths.*;

@Controller
public class Terminal implements CommandLineRunner {

    private final CategoryService categoryService;

    private final StrategyService strategyService;

    private final ContestService contestService;

    private final ProblemService problemService;

    private final UserService userService;

    @Autowired
    public Terminal(CategoryService categoryService,
                    StrategyService strategyService,
                    ContestService contestService,
                    ProblemService problemService,
                    UserService userService) {
        this.categoryService = categoryService;
        this.strategyService = strategyService;
        this.contestService = contestService;
        this.problemService = problemService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.importCategories();
//        this.importStrategies();
//        this.importContests();
//        this.importProblems();
//        this.importUsers();
//        this.enrollInContestForUsers();
        this.submittingSomeProblems();
    }

    private void importCategories() {
        this.categoryService.importCategories(CATEGORIES_JSON_FILE_PATH);
    }

    private void importStrategies() {
        this.strategyService.importStrategies(STRATEGIES_JSON_FILE_PATH);
    }

    private void importContests() {
        this.contestService.importContests(CONTESTS_JSON_FILE_PATH);
    }

    private void importProblems() {
        this.problemService.importProblems(PROBLEMS_XML_FILE_PATH);
    }

    private void importUsers() {
        this.userService.importUsers(USERS_JSON_FILE_PATH);
    }

    private void enrollInContestForUsers() {
        System.out.println(this.userService.enrollInContests(USER_PARTICIPATION_XML_FILE_PATH));
    }

    private void submittingSomeProblems() {
        System.out.println(this.userService.submitProblems(SUBMISSION_XML_FILE_PATH));
    }
}
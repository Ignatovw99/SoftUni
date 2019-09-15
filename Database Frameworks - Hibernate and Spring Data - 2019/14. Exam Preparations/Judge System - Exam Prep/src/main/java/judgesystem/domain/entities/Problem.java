package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Problem extends BaseEntity {

    private String name;

    private Set<Submission> submissions;

    private Set<User> contestants;

    private Contest contest;

    private Set<MaxResultForProblem> maxResultsForProblem;

    private Set<Test> tests;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Submission.class, mappedBy = "problem")
    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    @ManyToMany
    @JoinTable(
            name = "users_problems",
            joinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    @ManyToOne
    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @OneToMany(targetEntity = MaxResultForProblem.class, mappedBy = "problem")
    public Set<MaxResultForProblem> getMaxResultsForProblem() {
        return maxResultsForProblem;
    }

    public void setMaxResultsForProblem(Set<MaxResultForProblem> maxResultsForProblem) {
        this.maxResultsForProblem = maxResultsForProblem;
    }

    @OneToMany(targetEntity = Test.class, mappedBy = "problem")
    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
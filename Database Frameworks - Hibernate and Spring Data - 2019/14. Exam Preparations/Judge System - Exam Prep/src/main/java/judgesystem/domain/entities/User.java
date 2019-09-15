package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User extends BaseEntity {

    private String username;

    private Set<Submission> submissions;

    private Set<MaxResultForContest> maxResultsForContests;

    private Set<MaxResultForProblem> maxResultsForProblems;

    private Set<Contest> contests;

    private Set<Problem> problems;

    public User() {
    }

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Set<Submission> submissions) {
        this.submissions = submissions;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<MaxResultForContest> getMaxResultsForContests() {
        return maxResultsForContests;
    }

    public void setMaxResultsForContests(Set<MaxResultForContest> maxResultsForContests) {
        this.maxResultsForContests = maxResultsForContests;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<MaxResultForProblem> getMaxResultsForProblems() {
        return maxResultsForProblems;
    }

    public void setMaxResultsForProblems(Set<MaxResultForProblem> maxResultsForProblems) {
        this.maxResultsForProblems = maxResultsForProblems;
    }

    @ManyToMany
    @JoinTable(
            name = "users_contests",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contest_id", referencedColumnName = "id")
    )
    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }

    @ManyToMany
    @JoinTable(
            name = "users_problems",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id")
    )
    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
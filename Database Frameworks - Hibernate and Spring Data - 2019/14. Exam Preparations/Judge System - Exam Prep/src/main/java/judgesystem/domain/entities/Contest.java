package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Contest extends BaseEntity {

    private String name;

    private Category category;

    private Set<Problem> problems;

    private Set<User> contestants;

    private Set<MaxResultForContest> maxResultsForContest;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(targetEntity = Problem.class, mappedBy = "contest", cascade = CascadeType.ALL)
    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    @ManyToMany(targetEntity = User.class, mappedBy = "contests")
    public Set<User> getContestants() {
        return contestants;
    }

    public void setContestants(Set<User> contestants) {
        this.contestants = contestants;
    }

    @OneToMany(targetEntity = MaxResultForContest.class, mappedBy = "contest", cascade = CascadeType.ALL)
    public Set<MaxResultForContest> getMaxResultsForContest() {
        return maxResultsForContest;
    }

    public void setMaxResultsForContest(Set<MaxResultForContest> maxResultsForContest) {
        this.maxResultsForContest = maxResultsForContest;
    }
}
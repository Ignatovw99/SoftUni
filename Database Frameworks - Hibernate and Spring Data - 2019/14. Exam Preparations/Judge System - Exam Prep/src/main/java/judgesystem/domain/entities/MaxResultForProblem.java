package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;
import judgesystem.domain.entities.base.BaseEntityAutoIncrement;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "max_results_for_problems")
public class MaxResultForProblem extends BaseEntityAutoIncrement {

    private User user;

    private Problem problem;

    private Submission bestSubmission;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    @ManyToOne
    public Submission getBestSubmission() {
        return bestSubmission;
    }

    public void setBestSubmission(Submission bestSubmission) {
        this.bestSubmission = bestSubmission;
    }
}
package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Submission extends BaseEntity {

    private User user;

    private Strategy strategy;

    private Double performance;

    private double points;

    private Problem problem;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Column
    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    @Column
    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    @ManyToOne
    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
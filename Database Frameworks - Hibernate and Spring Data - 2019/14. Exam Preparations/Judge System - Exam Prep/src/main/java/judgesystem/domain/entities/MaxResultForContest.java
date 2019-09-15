package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;
import judgesystem.domain.entities.base.BaseEntityAutoIncrement;

import javax.persistence.*;

@Entity
@Table(name = "max_results_for_contests")
public class MaxResultForContest extends BaseEntityAutoIncrement {

    private User user;

    private Contest contest;

    private Double averagePerformance;

    private double totalPoints;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    @Column(name = "average_performance")
    public Double getAveragePerformance() {
        return averagePerformance;
    }

    public void setAveragePerformance(Double averagePerformance) {
        this.averagePerformance = averagePerformance;
    }

    @Column(name = "total_points")
    public double getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(double totalPoints) {
        this.totalPoints = totalPoints;
    }
}

package judgesystem.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "submission")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubmissionImportDto implements Serializable {

    @XmlElement
    private Long id;

    @XmlElement
    private Double performance;

    @XmlElement
    private UserIdDto user;

    @XmlElement
    private ProblemIdDto problem;

    @XmlElement
    private Double points;

    @XmlElement
    private StrategyImportDto strategy;

    public SubmissionImportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPerformance() {
        return performance;
    }

    public void setPerformance(Double performance) {
        this.performance = performance;
    }

    public UserIdDto getUser() {
        return user;
    }

    public void setUser(UserIdDto user) {
        this.user = user;
    }

    public ProblemIdDto getProblem() {
        return problem;
    }

    public void setProblem(ProblemIdDto problem) {
        this.problem = problem;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public StrategyImportDto getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyImportDto strategy) {
        this.strategy = strategy;
    }
}
package judgesystem.domain.entities;

import judgesystem.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "strategies")
public class Strategy extends BaseEntity {

    private String name;

    private Set<Contest> contests;

    public Strategy() {
        this.contests = new HashSet<>();
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "strategies_contests",
            joinColumns = @JoinColumn(name = "strategy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "contest_id", referencedColumnName = "id")
    )
    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }
}

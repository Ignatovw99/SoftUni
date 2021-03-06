package mostwanted.domain.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race extends BaseEntity{

    private Integer laps;

    private District district;

    private Set<RaceEntry> raceEntries;

    public Race() {
    }

    @Column(name = "laps", nullable = false)
    @ColumnDefault(value = "0")
    @NotNull
    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(targetEntity = RaceEntry.class, mappedBy = "race", cascade = CascadeType.ALL)
    public Set<RaceEntry> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(Set<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }
}

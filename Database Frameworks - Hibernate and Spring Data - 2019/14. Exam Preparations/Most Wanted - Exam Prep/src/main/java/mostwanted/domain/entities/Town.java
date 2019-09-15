package mostwanted.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity{

    private String name;

    private Set<District> districts;

    private Set<Racer> racers;

    public Town() {
    }

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = District.class, mappedBy = "town", cascade = CascadeType.ALL)
    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    @OneToMany(targetEntity = Racer.class, mappedBy = "homeTown")
    public Set<Racer> getRacers() {
        return racers;
    }

    public void setRacers(Set<Racer> racers) {
        this.racers = racers;
    }
}

package residentevil.domain.entities;

import residentevil.domain.entities.enums.Magnitude;
import residentevil.domain.entities.enums.Mutation;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "viruses")
public class Virus extends BaseEntity {

    private String name;

    private String description;

    private String sideEffects;

    private String creator;

    private Boolean isDeadly;

    private Boolean isCurable;

    private Mutation mutation;

    private Integer turnoverRate;

    private Integer hoursUntilTurn;

    private Magnitude magnitude;

    private LocalDate releaseOn;

    private Set<Capital> capitals;

    public Virus() {
    }

    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT", length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "side_effects", length = 50)
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @Column(name = "creator", nullable = false)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Column(name = "is_deadly")
    public Boolean getIsDeadly() {
        return isDeadly;
    }

    public void setIsDeadly(Boolean isDeadly) {
        this.isDeadly = isDeadly;
    }

    @Column(name = "is_curable")
    public Boolean getIsCurable() {
        return isCurable;
    }

    public void setIsCurable(Boolean isCurable) {
        this.isCurable = isCurable;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "mutation", nullable = false)
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @Column(name = "turnover_rate", nullable = false)
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @Column(name = "hours_until_turn", nullable = false)
    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "magnitude", nullable = false)
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @Column(name = "release_on", nullable = false)
    public LocalDate getReleaseOn() {
        return releaseOn;
    }

    public void setReleaseOn(LocalDate releaseOn) {
        this.releaseOn = releaseOn;
    }

    @ManyToMany(targetEntity = Capital.class)
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = @JoinColumn(name = "virus_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "capital_id", referencedColumnName = "id")
    )
    public Set<Capital> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<Capital> capitals) {
        this.capitals = capitals;
    }
}

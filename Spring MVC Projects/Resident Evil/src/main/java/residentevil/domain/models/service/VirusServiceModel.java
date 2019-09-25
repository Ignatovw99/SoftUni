package residentevil.domain.models.service;

import residentevil.domain.entities.enums.Magnitude;
import residentevil.domain.entities.enums.Mutation;

import java.time.LocalDate;
import java.util.Set;

public class VirusServiceModel {

    private String id;

    private String name;

    private String description;

    private String sideEffects;

    private String creator;

    private boolean isDeadly;

    private boolean isCurable;

    private Mutation mutation;

    private Integer turnoverRate;

    private Integer hoursUntilTurn;

    private Magnitude magnitude;

    private LocalDate releaseOn;

    private Set<CapitalServiceModel> capitals;

    private Set<String> capitalsIds;

    public VirusServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean getIsDeadly() {
        return isDeadly;
    }

    public void setIsDeadly(boolean isDeadly) {
        this.isDeadly = isDeadly;
    }

    public Boolean getIsCurable() {
        return isCurable;
    }

    public void setIsCurable(Boolean isCurable) {
        this.isCurable = isCurable;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public LocalDate getReleaseOn() {
        return releaseOn;
    }

    public void setReleaseOn(LocalDate releaseOn) {
        this.releaseOn = releaseOn;
    }

    public Set<CapitalServiceModel> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<CapitalServiceModel> capitals) {
        this.capitals = capitals;
    }

    public Set<String> getCapitalsIds() {
        return capitalsIds;
    }

    public void setCapitalsIds(Set<String> capitalsIds) {
        this.capitalsIds = capitalsIds;
    }
}

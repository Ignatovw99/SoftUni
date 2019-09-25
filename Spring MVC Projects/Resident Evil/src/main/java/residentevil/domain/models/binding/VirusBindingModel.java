package residentevil.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import residentevil.common.annotations.IsDateBeforeToday;
import residentevil.domain.entities.Capital;
import residentevil.domain.entities.enums.Magnitude;
import residentevil.domain.entities.enums.Mutation;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

public class VirusBindingModel {

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

    private Set<String> capitalsIds;

    public VirusBindingModel() {
    }

    @NotNull(message = "The virus name should not be null")
    @NotEmpty(message = "The virus name should not be empty")
    @Length(min = 3, max = 10, message = "The virus name should be between 3 and 10 symbols")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "The virus description should not be null")
    @NotEmpty(message = "The virus description should not be empty")
    @Length(min = 5, max = 100, message = "The virus description should be between 5 and 100 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "The virus side effects should not be null")
    @NotEmpty(message = "The virus side effects should not be empty")
    @Length(max = 50, message = "The virus side effects should have a maximum of 50 symbols")
    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    @NotNull(message = "The virus creator should not be null")
    @NotEmpty(message = "The virus creator should not be empty")
    @Pattern(regexp = "^[Cc]orp$", message = "Invalid creator name")
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

    @NotNull(message = "The mutation should not be null")
    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    @NotNull(message = "The turnover rate should not be null")
    @Min(value = 0, message = "The minimum value is 0")
    @Max(value = 100, message = "The maximum value is 100")
    public Integer getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    @NotNull(message = "The hours until turn to mutation should not be null")
    @Min(value = 1, message = "The minimum value is 1")
    @Max(value = 12, message = "The maximum value is 12")
    public Integer getHoursUntilTurn() {
        return hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    @NotNull(message = "The magnitude should not be null")
    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "The release date should not be null")
    @IsDateBeforeToday(message = "The release date should be before today")
    public LocalDate getReleaseOn() {
        return releaseOn;
    }

    public void setReleaseOn(LocalDate releaseOn) {
        this.releaseOn = releaseOn;
    }

    @NotNull(message = "The affected capitals can not be null")
    @Size(min = 1, message = "You must select at least one capital")
    public Set<String> getCapitalsIds() {
        return capitalsIds;
    }

    public void setCapitalsIds(Set<String> capitalsIds) {
        this.capitalsIds = capitalsIds;
    }
}

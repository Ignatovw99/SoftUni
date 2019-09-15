package mostwanted.domain.dtos.raceentries;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportDto implements Serializable {

    @XmlAttribute(name = "has-finished")
    private Boolean hasFinished;

    @XmlAttribute(name = "finish-time")
    private Double finishTime;

    @XmlAttribute(name = "car-id")
    private Integer carIdNumber;

    @XmlElement(name = "racer")
    private String racerName;

    public RaceEntryImportDto() {
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCarIdNumber() {
        return carIdNumber;
    }

    public void setCarIdNumber(Integer carIdNumber) {
        this.carIdNumber = carIdNumber;
    }

    public String getRacerName() {
        return racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}

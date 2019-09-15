package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigDecimal;

public class RacerImportDto implements Serializable {

    @Expose
    private String name;

    @Expose
    private Integer age;

    @Expose
    private BigDecimal bounty;

    @Expose
    @SerializedName("homeTown")
    private String homeTownName;

    public RacerImportDto(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public String getHomeTownName() {
        return homeTownName;
    }

    public void setHomeTownName(String homeTownName) {
        this.homeTownName = homeTownName;
    }
}

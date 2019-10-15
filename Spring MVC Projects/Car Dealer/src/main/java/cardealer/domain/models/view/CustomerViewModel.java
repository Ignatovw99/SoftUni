package cardealer.domain.models.view;

import java.time.LocalDateTime;

public class CustomerViewModel {

    private String name;

    private LocalDateTime birthDate;

    private Boolean isYoungDriver;

    public CustomerViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsYoungDriver() {
        return isYoungDriver;
    }

    public void setIsYoungDriver(Boolean isYoungDriver) {
        this.isYoungDriver = isYoungDriver;
    }
}

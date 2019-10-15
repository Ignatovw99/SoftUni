package cardealer.domain.models.service.customers;

import java.time.LocalDateTime;

public class CustomerServiceModel {

    private Long id;

    private String name;

    private LocalDateTime birthDate;

    private Boolean isYoungDriver;

    public CustomerServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

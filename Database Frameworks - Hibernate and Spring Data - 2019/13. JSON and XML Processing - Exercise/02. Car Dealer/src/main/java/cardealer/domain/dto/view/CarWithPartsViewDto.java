package cardealer.domain.dto.view;

import cardealer.domain.dto.binding.CarDto;
import cardealer.domain.dto.binding.PartDto;

import java.io.Serializable;
import java.util.Set;

public class CarWithPartsViewDto implements Serializable {

    private CarDto car;

    private Set<PartDto> parts;

    public CarWithPartsViewDto() {
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public Set<PartDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartDto> parts) {
        this.parts = parts;
    }
}

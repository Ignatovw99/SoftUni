package xmlprocessing.domain.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXmlDto implements Serializable {

    @XmlElement
    private String firstName;

    @XmlElement
    private String lastName;

    @XmlElement
    private Integer age;

    @XmlElementWrapper(name = "cars")
    @XmlElement(name = "car")
    private Set<CarDto> cars;

    public UserXmlDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<CarDto> getCarDtos() {
        return cars;
    }

    public void setCarDtos(Set<CarDto> carDtos) {
        this.cars = carDtos;
    }

    @Override
    public String toString() {
        return "UserXmlDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}

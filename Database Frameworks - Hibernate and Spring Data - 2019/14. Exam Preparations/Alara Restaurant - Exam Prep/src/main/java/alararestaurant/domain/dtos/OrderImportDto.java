package alararestaurant.domain.dtos;

import alararestaurant.domain.entities.OrderType;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportDto implements Serializable {

    @XmlElement(name = "customer")
    private String customer;

    @XmlElement(name = "employee")
    private String employeeName;

    @XmlElement(name = "date-time")
    private String dateTime;

    @XmlElement(name = "type")
    private OrderType type;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OrderItemImportDto> orderItems;

    public OrderImportDto() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public List<OrderItemImportDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemImportDto> orderItems) {
        this.orderItems = orderItems;
    }
}

package alararestaurant.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrdersImportRootDto implements Serializable {

    @XmlElement(name = "order")
    private List<OrderImportDto> orders;

    public OrdersImportRootDto() {
    }

    public List<OrderImportDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderImportDto> orders) {
        this.orders = orders;
    }
}

package alararestaurant.service;

import alararestaurant.domain.dtos.OrdersImportRootDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Item;
import alararestaurant.domain.entities.Order;
import alararestaurant.domain.entities.OrderItem;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.OrderRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import alararestaurant.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static alararestaurant.constant.FilesPaths.ORDERS_IMPORT_FILE;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final EmployeeRepository employeeRepository;

    private final ItemRepository itemRepository;

    private final FileUtil fileUtil;

    private final XmlParser xmlParser;

    private final ModelMapper modelMapper;

    private final ValidationUtil validationUtil;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository, ItemRepository itemRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.orderRepository = orderRepository;
        this.employeeRepository = employeeRepository;
        this.itemRepository = itemRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean ordersAreImported() {
        return this.orderRepository.count() > 0;
    }

    @Override
    public String readOrdersXmlFile() {
        return this.fileUtil.readFile(ORDERS_IMPORT_FILE);
    }

    @Override
    public String importOrders() {
        StringBuilder stringBuilder = new StringBuilder();
        OrdersImportRootDto orders = this.xmlParser.convertXmlToEntity(ORDERS_IMPORT_FILE, OrdersImportRootDto.class);
        orders.getOrders()
                .forEach(orderImportDto -> {
                    Order order = this.modelMapper.map(orderImportDto, Order.class);
                    Employee employee = this.employeeRepository.findByName(orderImportDto.getEmployeeName());
                    if (!this.validationUtil.isValid(order) || employee == null) {
                        stringBuilder.append("Invalid date format.")
                                .append(System.lineSeparator());
                    } else {
                        order.setEmployee(employee);
                        List<OrderItem> orderItems = new ArrayList<>();
                        order.getOrderItems()
                                .forEach(currentOrderItem -> {
                                    OrderItem orderItem = new OrderItem();
                                    Item item = this.itemRepository.findByName(currentOrderItem.getItem().getName());
                                    if (item != null) {
                                        orderItem.setOrder(order);
                                        orderItem.setItem(item);
                                        orderItem.setQuantity(currentOrderItem.getQuantity());
                                        orderItems.add(orderItem);
                                    }
                                });
                        if (orderItems.size() != order.getOrderItems().size()) {
                            stringBuilder.append("Invalid data format.")
                                    .append(System.lineSeparator());
                        } else {
                            order.setOrderItems(orderItems);
                            this.orderRepository.saveAndFlush(order);
                            stringBuilder.append(String.format("Order for %s on %s added", order.getCustomer(), order.getDateTime()))
                                    .append(System.lineSeparator());
                        }
                    }
                });
        return stringBuilder.toString().trim();
    }

    @Override
    public String exportOrdersFinishedByTheBurgerFlippers() {
        Map<Employee, List<Order>> ordersByEmployee = new LinkedHashMap<>();
        this.orderRepository.finishedOrdersByBurgerFlipper()
                .forEach(order -> {
                    if (!ordersByEmployee.containsKey(order.getEmployee())) {
                        ordersByEmployee.put(order.getEmployee(), new ArrayList<>());
                    }
                    ordersByEmployee.get(order.getEmployee())
                            .add(order);
                });

        StringBuilder stringBuilder = new StringBuilder();

        ordersByEmployee.forEach((employee, orders) -> {
            stringBuilder.append(String.format("Name: %s", employee.getName())).append(System.lineSeparator())
                    .append("Orders:").append(System.lineSeparator());
                    orders.forEach(order -> {
                        stringBuilder.append(String.format("  Customer: %s", order.getCustomer())).append(System.lineSeparator())
                                .append("  Items:").append(System.lineSeparator());
                        order.getOrderItems()
                                .forEach(orderItem ->
                                    stringBuilder.append(String.format("\tName: %s", orderItem.getItem().getName())).append(System.lineSeparator())
                                            .append(String.format("\tPrice: %.2f", orderItem.getItem().getPrice())).append(System.lineSeparator())
                                            .append(String.format("\tQuantity: %d", orderItem.getQuantity())).append(System.lineSeparator())
                                            .append(System.lineSeparator())
                                );
                    });
        });

        return stringBuilder.toString().trim();
    }
}

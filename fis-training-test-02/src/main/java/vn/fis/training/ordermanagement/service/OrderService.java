package vn.fis.training.ordermanagement.service;

import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.domain.OrderCart;
import vn.fis.training.ordermanagement.domain.OrderItem;
import vn.fis.training.ordermanagement.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {


    OrderCart createOrder(OrderCart order);

    List<OrderCart> findAllOrder();

    OrderCart updateOrder(OrderCart order);

    List<OrderCart> findOrdersBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime);

    List<OrderCart> findWaitingApprovalOrders();

    List<OrderCart> findOrdersByOrderStatus(OrderStatus orderStatus);

    List<OrderCart> findOrdersByCustomer(Customer customer);


    OrderItem addOrderItem(OrderItem orderItem);

    OrderItem updateOrderItem(OrderItem orderItem);

    boolean removeOrderItem(Long idOrderItem);

}

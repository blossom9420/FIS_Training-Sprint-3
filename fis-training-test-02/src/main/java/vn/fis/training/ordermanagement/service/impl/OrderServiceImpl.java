package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.repository.OrderItemRepository;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderCart findByIdOrder(Long idOrder) {
        Optional<OrderCart> order = orderRepository.findById(idOrder);
        if (order.isPresent()) {
            return order.get();
        }
        return null;
    }

    @Override
    public OrderCart createOrder(OrderCart order) {
        if (findByIdOrder(order.getId()) == null) {
            orderRepository.save(order);
            return order;
        } else {
            return null;
        }
    }

    public List<OrderCart> findAllOrder(){
        return orderRepository.findAll();
    }

    @Override
    public OrderCart updateOrder(OrderCart order) {
        if (findByIdOrder(order.getId()) == null) {
            return null;
        } else {
            orderRepository.save(order);
            return order;
        }
    }

    @Override
    public List<OrderCart> findOrdersBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        List<OrderCart> list = orderRepository.findAll()
                .stream()
                .filter(order -> {
                            return ((order.getOrderDateTime()).isAfter(fromDateTime)
                                    && (order.getOrderDateTime()).isBefore(toDateTime));
                        }
                )
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OrderCart> findWaitingApprovalOrders() {
        List<OrderCart> list = orderRepository.findAll()
                .stream()
                .filter(order -> OrderStatus.WAITING_APPROVAL.equals(order.getStatus()) )
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<OrderCart> findOrdersByOrderStatus(OrderStatus orderStatus) {
        List<OrderCart> list = orderRepository.findAll()
                .stream()
                .filter(order -> orderStatus.equals(order.getStatus()) )
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    @Override
    public List<OrderCart> findOrdersByCustomer(Customer customer) {
        List<OrderCart> list = orderRepository.findAll()
                .stream()
                .filter(order -> customer.equals(order.getCustomer()) )
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }


    /***********************
     ***    OrderItem    ***
     ***********************/

    public OrderItem findByIdOrderItem(Long idOrderItem) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(idOrderItem);
        if (orderItem.isPresent()) {
            return orderItem.get();
        }
        return null;
    }

    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        if (findByIdOrderItem(orderItem.getId()) == null) {
            orderItemRepository.save(orderItem);
            return orderItem;
        } else {
            return null;
        }
    }


    public OrderItem updateOrderItem(OrderItem orderItem) {
        if (findByIdOrderItem(orderItem.getId()) != null) {
            orderItemRepository.save(orderItem);
            return orderItem;
        } else {
            return null;
        }
    }

    public boolean removeOrderItem(Long idOrderItem) {
        if (findByIdOrderItem(idOrderItem) == null) {
            return false;
        } else {
            orderItemRepository.deleteById(idOrderItem);
            return true;
        }
    }
}

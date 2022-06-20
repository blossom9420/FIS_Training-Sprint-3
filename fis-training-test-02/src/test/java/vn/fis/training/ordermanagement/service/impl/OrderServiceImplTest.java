package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    private LocalDateTime time;

    @Test
    void createOrder() {
        Customer customer = new Customer();
        customer.setId(1L);
        OrderCart order = new OrderCart();
        order.setId(1L);
        time = LocalDateTime.now();
        order.setOrderDateTime(time);
        order.setCustomer(customer);
        orderService.createOrder(order);
        assertEquals(1, orderService.findAllOrder().size());
    }

    @Test
    void findByIdOrder() {
        Customer customer = new Customer();
        customer.setId(1L);
        OrderCart expect = new OrderCart(3L, time, customer);

        OrderCart orderCart = orderService.findByIdOrder(3L);

        assertEquals(expect.getCustomer().getId(), orderCart.getCustomer().getId());
    }

    @Test
    void updateOrder_updateCus() {
        Customer updateCustomer = new Customer(); // name : Number 3, address: HCM CITY
        updateCustomer.setId(3L);
        OrderCart updateOrder = new OrderCart(3L, LocalDateTime.now(), updateCustomer);
        orderService.updateOrder(updateOrder);
        OrderCart result = orderService.findByIdOrder(3L);
        assertEquals(updateCustomer.getId(), result.getCustomer().getId());
    }

    @Test
    void updateOrder_updateStatus() {
        Customer customer = new Customer(); // name : Number 3, address: HCM CITY
        customer.setId(3L);
        OrderCart updateOrder = new OrderCart(2L, LocalDateTime.now(), customer);
        updateOrder.setStatus(OrderStatus.WAITING_APPROVAL);

        orderService.updateOrder(updateOrder);

        OrderCart result = orderService.findByIdOrder(3L);
        assertEquals(OrderStatus.WAITING_APPROVAL, result.getStatus());
    }

    @Test
    void findOrdersBetween() {
        LocalTime localTime = LocalTime.of(0,0);
        LocalDateTime fromDateTime = LocalDateTime.of(LocalDate.of(2022,06,14),localTime);
        LocalDateTime toDateTime =  LocalDateTime.of(LocalDate.of(2022,07,16),localTime);

        List<OrderCart> list = orderService.findOrdersBetween(fromDateTime, toDateTime);

        assertEquals(2,list.size());
    }

    @Test
    void findWaitingApprovalOrders() {

        List<OrderCart> list = orderService.findWaitingApprovalOrders();
        assertEquals(2,list.size());
    }

    @Test
    void findOrdersByOrderStatus() {
        List<OrderCart> list = orderService.findOrdersByOrderStatus(OrderStatus.CREATED);
        assertEquals(1,list.size());
    }

    @Test
    void findOrdersByCustomer() {
        Customer customer = new Customer("Number 3","3333","HCM CITY");
        customer.setId(3L);
        List<OrderCart> list = orderService.findOrdersByCustomer(customer);
        assertEquals(2,list.size());
    }


    //** OrderItem **//

    @Test
    void addOrderItem() {
        OrderCart orderCart = new OrderCart();
        orderCart.setId(2L);
        Product product = new Product();
        product.setId(1L);
        product.setPrice(2.55);
        OrderItem orderItem = new OrderItem(3L,orderCart,product,5);

        orderService.addOrderItem(orderItem);
        assertEquals(product.getPrice(), orderService.findByIdOrderItem(3L).getProduct().getPrice());
    }

    @Test
    void findByIdOrderItem() {
        assertNotNull( orderService.findByIdOrderItem(3L));
    }



    @Test
    void updateOrderItem_Quantity() {
        OrderCart orderCart = new OrderCart();
        orderCart.setId(2L);
        Product product = new Product();
        product.setId(1L);
        product.setPrice(2.55);
        OrderItem orderItem = new OrderItem(3L,orderCart,product,55);

        orderService.updateOrderItem(orderItem);
        assertEquals(55, orderService.findByIdOrderItem(3L).getQuantity());
    }

    @Test
    void removeOrderItem() {
        orderService.removeOrderItem(3L);
        assertNull(orderService.findByIdOrderItem(3L));
    }
}
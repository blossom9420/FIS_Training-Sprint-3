package vn.fis.training.ordermanagement.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_order")
public class OrderCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_datetime")
    private LocalDateTime orderDateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems  = new ArrayList<>();

    @Column(name = "total_amount")
    private Double totalAmount;

    /**
     * Bao gom cac trang thai duoc dinh nghia trong OrderStatus Class
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }


    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public OrderCart(Long id, LocalDateTime orderDateTime, Customer customer) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.customer = customer;
        this.status = OrderStatus.CREATED;
    }

    public OrderCart() {
        this.status = OrderStatus.CREATED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCart orderCart = (OrderCart) o;
        return Objects.equals(getId(), orderCart.getId()) && Objects.equals(getOrderDateTime(), orderCart.getOrderDateTime()) && Objects.equals(getCustomer(), orderCart.getCustomer()) && Objects.equals(getOrderItems(), orderCart.getOrderItems()) && Objects.equals(getTotalAmount(), orderCart.getTotalAmount()) && getStatus() == orderCart.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrderDateTime(), getCustomer(), getOrderItems(), getTotalAmount(), getStatus());
    }

    @Override
    public String toString() {
        return "OrderCart{" +
                "id=" + id +
                ", orderDateTime=" + orderDateTime +
                ", customer=" + customer +
//                ", orderItems=" + orderItems +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }
}

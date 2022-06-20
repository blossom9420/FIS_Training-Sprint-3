package vn.fis.training.ordermanagement.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tbl_order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private OrderCart order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    // số lượng
    @Column(name="quantity")
    private Integer quantity;

    // tổng cộng
    @Column(name="amount")
    private Double amount;


    public OrderItem(Long id, OrderCart order, Product product, Integer quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.amount = quantity*product.getPrice();
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderCart getOrder() {
        return order;
    }

    public void setOrder(OrderCart order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getId(), orderItem.getId()) && Objects.equals(getOrder(), orderItem.getOrder()) && Objects.equals(getProduct(), orderItem.getProduct()) && Objects.equals(getQuantity(), orderItem.getQuantity()) && Objects.equals(getAmount(), orderItem.getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrder(), getProduct(), getQuantity(), getAmount());
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", product=" + product +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}' +"\n";
    }
}

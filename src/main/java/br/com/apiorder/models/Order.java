package br.com.apiorder.models;

import br.com.apiorder.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="Orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @NotNull(message = "Enter the user id")
    private Long userId;

    private Status status;

    private BigDecimal discount;

    private BigDecimal total;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<Item> products;


    public Order() {
        this.createdAt = LocalDate.now();
        products = new ArrayList<>();
        total = BigDecimal.ZERO;
        discount = BigDecimal.ZERO;
        status = Status.AWAITING_PAYMENT;
    }

    public Order(Long userId, List<Item> products) {
        this.userId = userId;
        this.status = Status.AWAITING_PAYMENT;
        this.total = BigDecimal.ZERO;
        discount = BigDecimal.ZERO;
        this.createdAt = LocalDate.now();
        this.products = products == null ? new ArrayList<>() : products;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total
                .divide(BigDecimal.valueOf(1),2, RoundingMode.HALF_UP);
    }

    public List<Item> getProducts() {
        return products;
    }

    public void setProducts(List<Item> products) {
        this.products = products;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount
                .divide(BigDecimal.valueOf(1),2, RoundingMode.HALF_UP);;
    }

}

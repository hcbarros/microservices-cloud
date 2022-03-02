package br.com.apiproduct.templates;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDTO implements Serializable {

    private Long id;

    private Long productId;

    private Integer amount;

    private LocalDate createdAt;

    public OrderDTO() {
        super();
    }

    public OrderDTO(Long id, Long productId, Integer amount, LocalDate createdAt) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}

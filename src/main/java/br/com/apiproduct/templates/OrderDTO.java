package br.com.apiproduct.templates;

import br.com.apiproduct.enums.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class OrderDTO implements Serializable {

    private Long orderId;

    private Long userId;

    private List<OrderProductDTO> products;

    @NotNull(message = "Order status cannot be null!")
    private Status status;

    private BigDecimal total;

    private BigDecimal discount;


    public OrderDTO() {
        super();
    }

    public OrderDTO(List<OrderProductDTO> products, Status status) {
        this.products = products;
        this.total = BigDecimal.ZERO;
    }


    public List<OrderProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDTO> products) {
        this.products = products;
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
        this.total = total;
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void addValue(String price, String amount) {
        BigDecimal result = new BigDecimal(price).multiply(new BigDecimal(amount));
        total = total.add(result);
    }

}

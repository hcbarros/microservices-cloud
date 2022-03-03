package br.com.apiproduct.templates;

import br.com.apiproduct.enums.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class OrderDTO implements Serializable {

    private List<OrderProductDTO> productDTOS;

    @NotNull(message = "Order status cannot be null!")
    private Status status;

    private BigDecimal total;

    public OrderDTO() {
        super();
    }

    public OrderDTO(List<OrderProductDTO> productDTOS, Status status) {
        this.productDTOS = productDTOS;
        this.total = BigDecimal.ZERO;
    }


    public List<OrderProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public void setProductDTOS(List<OrderProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
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

    public void addValue(String value) {
        total = total.add(new BigDecimal(value));
    }

}

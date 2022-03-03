package br.com.apiproduct.templates;


public class OrderProductDTO {

    private Long idProduct;

    private Integer amount;

    public OrderProductDTO() { }

    public OrderProductDTO(Long idProduct, Integer amount) {
        this.idProduct = idProduct;
        this.amount = amount;
    }


    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

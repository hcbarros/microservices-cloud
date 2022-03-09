package br.com.apiproduct.templates;


import java.io.Serializable;

public class OrderProductDTO implements Serializable {

    private Long idProduct;

    private String name;

    private String price;

    private Integer amount;

    public OrderProductDTO() { }

    public OrderProductDTO(Integer amount, String name, String price) {
        this.amount = amount;
        this.name = name;
        this.price = price;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

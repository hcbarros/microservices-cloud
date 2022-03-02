package br.com.apiproduct.templates;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


public class ProductDTO {

    private Long id;

    @NotEmpty(message = "Enter the product name")
    private String name;

    @Pattern(regexp = "\\d{1,10}(\\.\\d{1,2})?$",
            message = "Limit balance must have a maximum of 8 integers and a maximum decimal fraction of 2 digits.")
    private String price;

    @Pattern(regexp = "\\d{1,10}", message = "Stock must be a positive integer!")
    private String stock;


    public ProductDTO() { }

    public ProductDTO(Long id, String name, String price, String stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}

package br.com.apiproduct.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(message = "Limit balance must have a maximum of 8 integers and a maximum decimal fraction of 2 digits.",
            integer = 8, fraction=2)
    private BigDecimal price;

    @Min(0)
    @Max(1000000000)
    private Integer stock;


    public Product() { }

    public Product(String name, BigDecimal price, Integer stock) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void updateStock(Integer amount) {
        this.stock -= amount;
    }

}

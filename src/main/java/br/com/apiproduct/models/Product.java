package br.com.apiproduct.models;

import br.com.apiproduct.enums.Status;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Enter the product name")
    private String name;

    @Pattern(regexp = "\\d{1,10}(\\.\\d{1,2})?$",
            message = "Limit balance must have a maximum of 8 integers and a maximum decimal fraction of 2 digits.")
    private String price;

    @Pattern(regexp = "\\d{1,10}", message = "Stock must be a positive integer!")
    private String stock;


    public Product() { }

    public Product(String name, String price, String stock) {
        this.name = name;
        this.price = price;
        setStock(stock);
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

    public int subtractStock(int amount) {
        int temp = Integer.parseInt(stock) - amount;
        stock = temp < 0 ? "0" : String.valueOf(temp);
        return temp;
    }

    public void addStock(Integer amount) {
        stock = String.valueOf(Integer.parseInt(stock) + amount);
    }

}

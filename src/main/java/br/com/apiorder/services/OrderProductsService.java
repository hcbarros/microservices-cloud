package br.com.apiorder.services;

import br.com.apiorder.models.Order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url= "http://localhost:8080/api/v1/products" , name = "api-product-cloud")
public interface OrderProductsService {

    @PutMapping("/reserveProducts")
    Order reserveProducts(Order order);

    @PutMapping("/returnProductsStock")
    Order returnProductsStock(Order order);
}

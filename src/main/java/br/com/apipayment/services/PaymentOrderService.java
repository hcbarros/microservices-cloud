package br.com.apipayment.services;

import br.com.apipayment.models.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(url= "http://localhost:8080/api/v1/orders" , name = "api-order-cloud")
public interface PaymentOrderService {

    @GetMapping("/{id}")
    Order findById(@PathVariable("id") Long id);

    @PutMapping("/updateStatus")
    Order updateStatus(Order order);
}

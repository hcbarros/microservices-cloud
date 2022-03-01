package br.com.apiproduct.controllers;

import br.com.apiproduct.models.Product;
import br.com.apiproduct.services.ProductService;
import br.com.apiproduct.templates.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;


    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid  @RequestBody Product product) {
        Product p = service.save(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/reserveOne")
    public ResponseEntity<Product> reserveProduct(@RequestBody OrderDTO orderDTO) {

        Product product = service.reserveProduct(orderDTO);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/reserveMany")
    public ResponseEntity<List<Product>> reserveProducts(@RequestBody List<OrderDTO> orders) {

        List<Product> products = service.reserveProducts(orders);
        return ResponseEntity.ok(products);
    }


}

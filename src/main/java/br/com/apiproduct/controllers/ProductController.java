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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST products")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @ApiOperation(value="Return product by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value="Return all products")
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok(products);
    }

    @ApiOperation(value="Return all products by id")
    @GetMapping("/allbyid")
    public ResponseEntity<List<Product>> findAllById(@RequestBody List<Long> ids) {
        List<Product> products = service.findAllById(ids);
        return ResponseEntity.ok(products);
    }

    @ApiOperation(value="returns products per page, starting with page 1")
    @GetMapping("/paginated/{pageNum}/{amount}")
    public ResponseEntity<List<Product>> findAllPaginated(
            @PathVariable int pageNum,
            @PathVariable int amount) {

        List<Product> products = service.findAllPaginated(pageNum, amount);
        return ResponseEntity.ok(products);
    }

    @ApiOperation(value="Save product")
    @PostMapping
    public ResponseEntity<Product> save(@Valid  @RequestBody Product product) {
        Product p = service.save(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location).body(p);
    }

    @ApiOperation(value="Update product")
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> update(@RequestBody Product p,
                                          @PathVariable Long id) {

        Product product = service.update(id, p);
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value="Reserve products after an order")
    @PutMapping("/reserveProducts")
    public ResponseEntity<OrderDTO> reserveProducts(@Valid @RequestBody OrderDTO order) {

        OrderDTO o = service.reserveProducts(order);
        return ResponseEntity.ok(o);
    }

    @ApiOperation(value="Returns products to stock after an order")
    @PutMapping("/returnProductsStock")
    public ResponseEntity<OrderDTO> returnProductsStock(@Valid @RequestBody OrderDTO order) {

        OrderDTO o = service.returnProducts(order);
        return ResponseEntity.ok(o);
    }

}

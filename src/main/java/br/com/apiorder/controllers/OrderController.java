package br.com.apiorder.controllers;

import br.com.apiorder.dto.UserDTO;
import br.com.apiorder.models.Order;
import br.com.apiorder.services.OrderProductsService;
import br.com.apiorder.services.OrderService;
import br.com.apiorder.services.OrderUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="API REST orders")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductsService productsService;

    @Autowired
    private OrderUserService userService;


    @ApiOperation(value="Returns order by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @ApiOperation(value="Returns all orders")
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @ApiOperation(value="Returns all orders from a user")
    @GetMapping("/allbyuserid/{userId}")
    public ResponseEntity<List<Order>> findAllByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findAllByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @ApiOperation(value="returns orders per page, starting with page 1")
    @GetMapping("/paginated/{pageNum}/{amount}")
    public ResponseEntity<List<Order>> findAllPaginated(
            @PathVariable int pageNum,
            @PathVariable int amount) {

        List<Order> orders = orderService.findAllPaginated(pageNum, amount);
        return ResponseEntity.ok(orders);
    }

    @ApiOperation(value="Update an order")
    @PutMapping("/updateStatus")
    public ResponseEntity<Order> updateStatus(@RequestBody Order order) {
        Order o = orderService.updateStatus(order);
        return ResponseEntity.ok(o);
    }

    @ApiOperation(value="save an order")
    @PostMapping
    public ResponseEntity<Order> save(@Valid @RequestBody Order order) {

        UserDTO user = userService.findById(order.getUserId());
        if(user == null) {
            throw new RuntimeException("User not found!");
        }
        Order o = productsService.reserveProducts(order);
        o = orderService.save(o, user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{orderId}")
                .buildAndExpand(order.getOrderId())
                .toUri();
        return ResponseEntity.created(location).body(o);
    }

    @ApiOperation(value="Delete an order by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

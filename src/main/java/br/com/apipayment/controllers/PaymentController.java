package br.com.apipayment.controllers;

import br.com.apipayment.models.Order;
import br.com.apipayment.models.Payment;
import br.com.apipayment.services.PaymentOrderService;
import br.com.apipayment.services.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(value="API REST payments")
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentOrderService orderService;


    @ApiOperation(value="Returns payment by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Payment> findById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        return ResponseEntity.ok(payment);
    }

    @ApiOperation(value="Returns all payments")
    @GetMapping
    public ResponseEntity<List<Payment>> findAll() {
        List<Payment> payments = paymentService.findAll();
        return ResponseEntity.ok(payments);
    }

    @ApiOperation(value="Returns all payments from a user")
    @GetMapping("/allbyuserid/{userId}")
    public ResponseEntity<List<Payment>> findAllByUserId(@PathVariable Long userId) {
        List<Payment> payments = paymentService.findAllByUserId(userId);
        return ResponseEntity.ok(payments);
    }

    @ApiOperation(value="returns payments per page, starting with page 1")
    @GetMapping("/paginated/{pageNum}/{amount}")
    public ResponseEntity<List<Payment>> findAllPaginated(
            @PathVariable int pageNum,
            @PathVariable int amount) {

        List<Payment> payments = paymentService.findAllPaginated(pageNum, amount);
        return ResponseEntity.ok(payments);
    }

    @ApiOperation(value="save an order")
    @PostMapping
    public ResponseEntity<Payment> save(@Valid @RequestBody Payment p) {

            Order order = orderService.findById(p.getOrder().getOrderId());
            if(order == null) {
                throw new RuntimeException("Order not found!");
            }
            Payment payment = paymentService.save(p, order);
            orderService.updateStatus(order);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(payment.getId())
                    .toUri();
            return ResponseEntity.created(location).body(payment);
    }

    @ApiOperation(value="Delete a payment by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

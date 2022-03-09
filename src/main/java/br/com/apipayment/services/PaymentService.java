package br.com.apipayment.services;

import br.com.apipayment.enums.Status;
import br.com.apipayment.models.Order;
import br.com.apipayment.models.Payment;
import br.com.apipayment.repositories.PaymentRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;


    public Payment save(Payment payment, Order order) {

        if(repo.existsByOrder_OrderId(order.getOrderId())) {
            throw new RuntimeException("This order has already been paid!");
        }
        if(payment.getAmountReceived().compareTo(order.getTotal()) >= 0) {
            order.setStatus(Status.PAID_OFF);
            payment.setOrder(order);
            return repo.save(payment);
        }
        throw new RuntimeException("Insufficient amount");
    }

    public Payment findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found!"));
    }

    public List<Payment> findAll() {
        return repo.findAll();
    }

    public List<Payment> findAllByUserId(Long userId) {
        return repo.findAllByOrder_UserId(userId);
    }

    public List<Payment> findAllPaginated(int pageNum, int amount) {
        Pageable paging = PageRequest.of(pageNum - 1, amount);
        Page<Payment> pagedResult = repo.findAll(paging);
        return pagedResult.toList();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }


}

package br.com.apipayment.repositories;

import br.com.apipayment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    boolean existsByOrder_OrderId(Long orderId);

    List<Payment> findAllByOrder_UserId(Long userId);
}

package br.com.apiorder.repositories;

import br.com.apiorder.enums.Status;
import br.com.apiorder.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long userId);

    List<Order> findAllByStatus(Status status);
}

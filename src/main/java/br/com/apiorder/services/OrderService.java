package br.com.apiorder.services;

import br.com.apiorder.dto.UserDTO;
import br.com.apiorder.enums.Status;
import br.com.apiorder.models.Order;
import br.com.apiorder.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private OrderProductsService productsService;


    public Order save(Order order, UserDTO user) {
        LocalDate regiterDate = LocalDate.parse(user.getRegisterDate(), DateTimeFormatter
                .ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
        if(Period.between(regiterDate, LocalDate.now()).getYears() > 10) {
            BigDecimal total = order.getTotal().multiply(BigDecimal.valueOf(0.75));
            order.setDiscount(order.getTotal().subtract(total));
            order.setTotal(total);
        }
        return repo.save(order);
    }

    public Order updateStatus(Order order) {
        Order o = findById(order.getOrderId());
        if(o == null) {
            throw new RuntimeException("Order not found!");
        }
        o.setStatus(order.getStatus());
        return repo.save(o);
    }

    public Order findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Order> findAll() {
        return repo.findAll();
    }

    public List<Order> findAllByUserId(Long userId) {
        return repo.findAllByUserId(userId);
    }

    public List<Order> findAllPaginated(int pageNum, int amount) {
        Pageable paging = PageRequest.of(pageNum - 1, amount);
        Page<Order> pagedResult = repo.findAll(paging);
        return pagedResult.toList();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleOrderCancellation(){
        List<Order> orders = repo.findAllByStatus(Status.AWAITING_PAYMENT);
        orders.forEach(o -> {
            if(Period.between(o.getCreatedAt(), LocalDate.now()).getDays() >= 3) {
                Order order = productsService.returnProductsStock(o);
                order.setStatus(Status.CANCELED);
                repo.save(order);
            }
        });
    }


}

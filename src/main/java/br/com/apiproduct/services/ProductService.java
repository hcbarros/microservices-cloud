package br.com.apiproduct.services;

import br.com.apiproduct.enums.Status;
import br.com.apiproduct.templates.OrderDTO;
import br.com.apiproduct.models.Product;
import br.com.apiproduct.repositories.ProductRepository;
import br.com.apiproduct.templates.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;


    public Product save(Product product) {
        if(repo.existsByName(product.getName())) {
            throw new RuntimeException("There is already a product registered with that name!");
        }
        return repo.save(product);
    }

    public Product update(Long id, Product product) {
        Product p = findById(id);
        Product compare = repo.findByName(product.getName());
        if(compare.getId() != p.getId()) {
            throw new RuntimeException("There is already a product with the name "+product.getName());
        }
        p.setStock(product.getStock());
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        return repo.save(p);
    }

    public Product findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public List<Product> findAllById(List<Long> ids) {
        return repo.findAllById(ids);
    }

    public List<Product> findAllPaginated(int pageNum, int amount) {
        Pageable paging = PageRequest.of(pageNum - 1, amount);
        Page<Product> pagedResult = repo.findAll(paging);
        return pagedResult.toList();
    }

    public OrderDTO reserveProducts(OrderDTO order) {
        List<OrderProductDTO> list = new ArrayList<>();
        List<OrderProductDTO> products = order.getProducts();
        products.forEach(p -> {
            Product product = findById(p.getIdProduct());
            if(product != null && order.getStatus() == Status.AWAITING_PAYMENT) {
                int resp = product.subtractStock(p.getAmount());
                int amount = resp >= 0 ? p.getAmount() : 0;
                repo.save(product);
                order.addValue(product.getPrice(), String.valueOf(amount));;
                list.add(new OrderProductDTO(amount, product.getName(), product.getPrice()));
            }
        });
        order.setProducts(list);
        return order;
    }

    public OrderDTO returnProducts(OrderDTO order) {
        List<OrderProductDTO> products = order.getProducts();
        products.forEach(p -> {
            Product product = findById(p.getIdProduct());
            product.addStock(p.getAmount());
            repo.save(product);
        });
        return order;
    }

}

package br.com.apiproduct.services;

import br.com.apiproduct.enums.Status;
import br.com.apiproduct.templates.OrderDTO;
import br.com.apiproduct.models.Product;
import br.com.apiproduct.repositories.ProductRepository;
import br.com.apiproduct.templates.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    public OrderDTO reserveProducts(OrderDTO order) {
        List<OrderProductDTO> list = new ArrayList<>();
        List<OrderProductDTO> products = order.getProductDTOS();
        products.forEach(p -> {
            Product product = findById(p.getIdProduct());
            if(product != null && order.getStatus() != Status.PAID_OFF) {
                int resp = product.updateStock(p.getAmount(), order.getStatus());
                if(resp >= 0) {
                    repo.save(product);
                    order.addValue(product.getPrice());;
                }
                list.add(p);
            }
        });
        order.setProductDTOS(list);
        return order;
    }


}

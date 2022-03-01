package br.com.apiproduct.services;

import br.com.apiproduct.templates.OrderDTO;
import br.com.apiproduct.models.Product;
import br.com.apiproduct.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Product findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found!"));
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product reserveProduct(OrderDTO order) {
        Product product = findById(order.getProductId());
        if(product.getStock() < order.getAmount()) {
            throw new RuntimeException("There is no quantity of this product to fulfill the order.");
        }
        product.updateStock(order.getAmount());
        return repo.save(product);
    }

    public List<Product> reserveProducts(List<OrderDTO> orders) {
        List<Product> list = new ArrayList<>();
        orders.forEach(order -> {
            Product product = findById(order.getProductId());
            product.updateStock(order.getAmount());
            product = repo.save(product);
            if(!list.contains(product)) list.add(product);
        });
        return list;
    }


}

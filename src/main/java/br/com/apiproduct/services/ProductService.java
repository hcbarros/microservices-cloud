package br.com.apiproduct.services;

import br.com.apiproduct.templates.OrderDTO;
import br.com.apiproduct.models.Product;
import br.com.apiproduct.models.repositories.ProductRepository;
import br.com.apiproduct.templates.ProductDTO;
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

    public Product update(ProductDTO pDTO) {
        Product p = findById(pDTO.getId());
        p.setStock(pDTO.getStock());
        p.setName(pDTO.getName());
        p.setPrice(pDTO.getPrice());
        return repo.save(p);
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
        int stock = product.updateStock(order.getAmount());
        if(stock < 0) {
            throw new RuntimeException("There is no quantity of this product to fulfill the order.");
        }
        return repo.save(product);
    }

    public List<Product> reserveProducts(List<OrderDTO> orders) {
        List<Product> list = new ArrayList<>();
        orders.forEach(order -> {
            Product product = findById(order.getProductId());
            int stock = product.updateStock(order.getAmount());
            if(stock < 0) {
                throw new RuntimeException("There is no quantity of this product to fulfill the order.");
            }
            product = repo.save(product);
            if(!list.contains(product)) list.add(product);
        });
        return list;
    }


}

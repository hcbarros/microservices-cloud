package br.com.apiproduct.config;

import br.com.apiproduct.models.Product;
import br.com.apiproduct.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner baseLoad(ProductRepository repo) {

        return args -> {

            Product p1 = new Product("Beans", "2", "50");
            Product p2 = new Product("Cheese", "3", "45");
            Product p3 = new Product("Coffee", "1.5", "60");
            Product p4 = new Product("Eggs", "2.3", "50");
            Product p5 = new Product("Ham", "3.5", "35");
            Product p6 = new Product("Juice", "1.3", "70");
            Product p7 = new Product("Oats", "1", "62");
            Product p8 = new Product("Onions", "0.8", "100");
            Product p9 = new Product("Rice", "1.8", "80");
            Product p10 = new Product("Sausage", "2", "90");

            for(Product p: Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10)) {
                if(!repo.existsByName(p.getName())) {
                    repo.save(p);
                }
            }
        };

    }

}
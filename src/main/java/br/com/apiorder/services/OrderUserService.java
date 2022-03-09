package br.com.apiorder.services;

import br.com.apiorder.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url= "http://localhost:8080/api/v1/users" , name = "api-user-cloud")
public interface OrderUserService {

    @GetMapping("/{id}")
    UserDTO findById(@PathVariable("id") Long id);
}

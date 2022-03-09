package br.com.apiorder.dto;


import br.com.apiorder.annotations.DateValidation;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String phone;

    @DateValidation(message = "Enter a valid date in the format: 'dd/MM/AAAA'")
    private String registerDate;

    public UserDTO() { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }
}

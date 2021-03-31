package com.senacbooks.senacbooks.users;

import java.io.Serializable;

import com.senacbooks.senacbooks.address.AddressDTO;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String cpf;
    private String login;
    private String password;
    private Boolean status;

    private AddressDTO address;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String cpf, String login, String password, Boolean status) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
        this.status = entity.getStatus();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

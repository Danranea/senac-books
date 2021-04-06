package com.senacbooks.senacbooks.users;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.senacbooks.senacbooks.address.AddressDTO;
import com.senacbooks.senacbooks.roles.RoleDTO;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 5, message = "Mínimo de 5 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    private String login;

    @NotBlank(message = "Campo obrigatório")
    private String password;

    private Boolean status;

    private RoleDTO role;

    // private AddressDTO address;

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
        this.role = new RoleDTO(entity.getRole());
        // this.address = new AddressDTO(entity.getAddress());
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

    public RoleDTO getRole() {
        return role;
    }

    // public AddressDTO getAddress() {
    //     return address;
    // }

}

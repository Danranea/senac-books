package com.senacbooks.senacbooks.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.senacbooks.senacbooks.roles.RoleDTO;

public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(min = 5, message = "Mínimo de 5 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String name;

    // TODO - nao pode estar visivel na resquisicao get
    @NotBlank(message = "Campo obrigatório")
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    private String login;

    // TODO - nao pode estar visivel na resquisicao get
    @Size(min = 3, message = "Mínimo de 5 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String password;

    private Boolean status;

    @NotBlank(message = "Campo obrigatório")
    private String zipCode;

    @NotBlank(message = "Campo obrigatório")
    private String address;

    @Positive(message = "Campo obrigatório")
    private Integer number;

    private String addressComplement;

    @NotBlank(message = "Campo obrigatório")
    private String city;

    @NotBlank(message = "Campo obrigatório")
    private String state;

    @NotBlank(message = "Campo obrigatório")
    private String country;

    Set<RoleDTO> roles = new HashSet<>();

    // private AddressDTO address;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String cpf, String login, String password, Boolean status, String zipCode,
    String address, Integer number, String addressComplement, String city, String state, String country) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.status = status;
        this.zipCode = zipCode;
        this.address = address;
        this.number = number;
        this.addressComplement = addressComplement;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.cpf = entity.getCpf();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
        this.status = entity.getStatus();
        this.zipCode = entity.getZipCode();
        this.address = entity.getAddress();
        this.number = entity.getNumber();
        this.addressComplement = entity.getAddressComplement();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.country = entity.getCountry();
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddressComplement() {
        return addressComplement;
    }

    public void setAddressComplement(String addressComplement) {
        this.addressComplement = addressComplement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}

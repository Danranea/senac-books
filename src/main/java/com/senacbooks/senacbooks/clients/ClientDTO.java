package com.senacbooks.senacbooks.clients;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.senacbooks.senacbooks.address.AddressDTO;
import com.senacbooks.senacbooks.address.AddressEntity;

public class ClientDTO {

    private Long id;

    @Size(min = 3, message = "Mínimo de 3 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String firstName;

    @Size(min = 3, message = "Mínimo de 3 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String lastName;

    @NotBlank(message = "Campo obrigatório")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "Campo obrigatório")
    @Column(unique = true)
    private String login;

    @Size(min = 3, message = "Mínimo de 3 caracteres")
    @NotBlank(message = "Campo obrigatório")
    private String password;
    private Boolean status;

    private List<AddressDTO> addresses = new ArrayList<>();

    
    public ClientDTO() {
    }

    public ClientDTO(Long id, String firstName, String lastName, String cpf, String login, String password,
            Boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public ClientDTO(ClientEntity entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.login = entity.getLogin();
        this.password = entity.getPassword();
        this.status = entity.getStatus();

        for (AddressEntity addressEntity : entity.getAddresses()) {
            this.addresses.add(new AddressDTO(addressEntity));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

}

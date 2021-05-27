package com.senacbooks.senacbooks.clients;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.senacbooks.senacbooks.address.AddressDTO;
import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.roles.RoleDTO;

public class ClientOrderDTO {

  private Long id;

  @Size(min = 3, message = "Mínimo de 3 caracteres")
  @NotBlank(message = "Campo obrigatório")
  private String firstName;

  @Size(min = 3, message = "Mínimo de 3 caracteres")
  @NotBlank(message = "Campo obrigatório")
  private String lastName;

  @NotBlank(message = "Campo obrigatório")
  @Column(unique = true)
  private String login;

  private Boolean status;

  public ClientOrderDTO() {
  }

  public ClientOrderDTO(Long id, String firstName, String lastName, String login, Boolean status) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = login;
    this.status = status;
  }

  public ClientOrderDTO(ClientEntity entity) {
    this.id = entity.getId();
    this.firstName = entity.getFirstName();
    this.lastName = entity.getLastName();
    this.login = entity.getLogin();
    this.status = entity.getStatus();
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

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}

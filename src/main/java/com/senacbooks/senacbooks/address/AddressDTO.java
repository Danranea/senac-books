package com.senacbooks.senacbooks.address;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.senacbooks.senacbooks.users.UserDTO;

public class AddressDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    private String zipCode;

    @NotBlank(message = "Campo obrigatório")
    private String address;

    @NotBlank(message = "Campo obrigatório")
    private Integer number;

    private String addressComplement;

    @NotBlank(message = "Campo obrigatório")
    private String city;

    @NotBlank(message = "Campo obrigatório")
    private String state;

    @NotBlank(message = "Campo obrigatório")
    private String country;

    // private UserDTO user;

    // private Client obj;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String zipCode, String address, Integer number, String addressComplement, String city,
            String state, String country) {
        this.id = id;
        this.zipCode = zipCode;
        this.address = address;
        this.number = number;
        this.addressComplement = addressComplement;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public AddressDTO(AddressEntity entity) {
        this.id = entity.getId();
        this.zipCode = entity.getZipCode();
        this.address = entity.getAddress();
        this.number = entity.getNumber();
        this.addressComplement = entity.getAddressComplement();
        this.city = entity.getCity();
        this.state = entity.getState();
        this.country = entity.getCountry();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    // public UserDTO getUserDTO() {
    //     return user;
    // }

    // public void setUserDTO(UserDTO user) {
    //     this.user = user;
    // }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AddressDTO other = (AddressDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

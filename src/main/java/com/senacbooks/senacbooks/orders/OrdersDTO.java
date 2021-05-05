package com.senacbooks.senacbooks.orders;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.senacbooks.senacbooks.address.AddressDTO;
import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.clients.ClientDTO;
import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.products.ProductDTO;
import com.senacbooks.senacbooks.products.ProductEntity;

public class OrdersDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    private ClientDTO client;

    @NotBlank(message = "Campo obrigatório")
    private List<ProductDTO> products;

    @NotBlank(message = "Campo obrigatório")
    private String payment;

    @NotBlank(message = "Campo obrigatório")
    private AddressDTO address;

    @NotBlank(message = "Campo obrigatório")
    private Double value;

    @NotBlank(message = "Campo obrigatório")
    private Double shipping;

    @NotBlank(message = "Campo obrigatório")
    private Double totalValue;

    @NotBlank(message = "Campo obrigatório")
    private Boolean status;
    
    public OrdersDTO() {
    }

    public OrdersDTO(Long id, ClientDTO client, List<ProductDTO> products, String payment, AddressDTO address,
            Double value, Double shipping, Double totalValue, Boolean status) {
        this.id = id;
        this.client = client;
        this.products = products;
        this.payment = payment;
        this.address = address;
        this.value = value;
        this.shipping = shipping;
        this.totalValue = totalValue;
        this.status = status;
    }

    public OrdersDTO(OrdersEntity entity) {
        this.id = entity.getId();
        this.client = new ClientDTO(entity.getClient());
        entity.getProducts().forEach(product -> this.products.add(new ProductDTO(product)));
        this.payment = entity.getPayment();
        this.address = new AddressDTO(entity.getAddress());
        this.value = entity.getValue();
        this.shipping = entity.getShipping();
        this.totalValue = entity.getTotalValue();
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

    public ClientDTO getCliente() {
        return client;
    }

    public void setCliente(ClientDTO cliente) {
        this.client = cliente;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getShipping() {
        return shipping;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
       
}

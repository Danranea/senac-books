package com.senacbooks.senacbooks.orders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.senacbooks.senacbooks.address.AddressDTO;
import com.senacbooks.senacbooks.clients.ClientDTO;
import com.senacbooks.senacbooks.payment.PaymentDTO;
import com.senacbooks.senacbooks.products.ProductDTO;

public class OrdersDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;

    private ClientDTO client;

    private List<ProductDTO> products = new ArrayList<>();

    private PaymentDTO payment;

    private AddressDTO address;

    @Positive(message = "field must be greater than zero")
    private Double value;

    @Positive(message = "field must be greater than zero")
    private Double shipping;

    @Positive(message = "field must be greater than zero")
    private Double totalValue;

    private Boolean status;
    
    public OrdersDTO() {
    }

    public OrdersDTO(Long id, ClientDTO client, List<ProductDTO> products, PaymentDTO payment, AddressDTO address,
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
        if (entity.getPayment() != null) {            
            this.payment = new PaymentDTO(entity.getPayment());
        }
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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO cliente) {
        this.client = cliente;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
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

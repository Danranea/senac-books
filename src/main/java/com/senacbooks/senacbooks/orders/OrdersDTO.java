package com.senacbooks.senacbooks.orders;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.products.ProductEntity;

public class OrdersDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    private ClientEntity cliente;

    /* @NotBlank(message = "Campo obrigatório")
    private List<ProductEntity> products;
 */
    @NotBlank(message = "Campo obrigatório")
    private String payment;

    @NotBlank(message = "Campo obrigatório")
    private AddressEntity address;

    @NotBlank(message = "Campo obrigatório")
    private Double value;

    @NotBlank(message = "Campo obrigatório")
    private Double shipping;

    @NotBlank(message = "Campo obrigatório")
    private Double total_value;

    @NotBlank(message = "Campo obrigatório")
    private Boolean status;
    
    public OrdersDTO() {
    }

    public OrdersDTO(Long id, ClientEntity cliente, List<ProductEntity> products, String payment, AddressEntity address,
            Double value, Double shipping, Double total_value, Boolean status) {
        this.id = id;
        this.cliente = cliente;
        /* this.products = products; */
        this.payment = payment;
        this.address = address;
        this.value = value;
        this.shipping = shipping;
        this.total_value = total_value;
        this.status = status;
    }

    public OrdersDTO(OrdersEntity entity) {
        this.id = entity.getId();
        this.cliente = entity.getCliente();
        /* this.products = entity.getProducts(); */
        this.payment = entity.getPayment();
        this.address = entity.getAddress();
        this.value = entity.getTotal_value();
        this.shipping = entity.getShipping();
        this.total_value = entity.getTotal_value();
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

    public ClientEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClientEntity cliente) {
        this.cliente = cliente;
    }

    /* public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    } */

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
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

    public Double getTotal_value() {
        return total_value;
    }

    public void setTotal_value(Double total_value) {
        this.total_value = total_value;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

       
}

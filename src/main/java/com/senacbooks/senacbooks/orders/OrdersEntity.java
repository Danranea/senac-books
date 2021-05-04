package com.senacbooks.senacbooks.orders;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.products.ProductEntity;

@Entity
@Table(name = "tb_orders")
public class OrdersEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private ClientEntity cliente;
    private List<ProductEntity> products;
    private String payment;
    private AddressEntity address;
    private Double value;
    private Double shipping;
    private Double total_value;
    private String status;
    
    public OrdersEntity() {
    }

    public OrdersEntity(Long id, ClientEntity cliente, List<ProductEntity> products, String payment,
            AddressEntity address, Double value, Double shipping, Double total_value, String status) {
        this.id = id;
        this.cliente = cliente;
        this.products = products;
        this.payment = payment;
        this.address = address;
        this.value = value;
        this.shipping = shipping;
        this.total_value = total_value;
        this.status = status;
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
    
    public List<ProductEntity> getProducts() {
        return products;
    }
    
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
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
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

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
        OrdersEntity other = (OrdersEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    



}

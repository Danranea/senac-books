package com.senacbooks.senacbooks.orders;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.payment.PaymentEntity;
import com.senacbooks.senacbooks.products.ProductEntity;

@Entity
@Table(name = "tb_orders")
public class OrdersEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private ClientEntity client;
    
    @ManyToMany
    @JoinTable(
            name="tb_order_product",
            joinColumns = @JoinColumn(name = "order_id"), // chave estrangeira relacionada a classe onde estamos, ou seja, será o produto.(A própria classe)
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> products = new ArrayList<>();
    
    @OneToOne
    @JoinColumn(name="payment_id")
    private PaymentEntity payment;

    @OneToOne
    @JoinColumn(name="address_id")
    private AddressEntity address;
    private Double value;
    private Double shipping;
    private Double totalValue;
    private Boolean status;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;
    
    public OrdersEntity() {
    }

    public OrdersEntity(Long id, ClientEntity client, List<ProductEntity> products, PaymentEntity payment,
            AddressEntity address, Double value, Double shipping, Double totalValue, Boolean status, Instant updatedAt, Instant createdAt) {
        this.id = id;
        this.client = client;
        this.products = products;
        this.payment = payment;
        this.address = address;
        this.value = value;
        this.shipping = shipping;
        this.totalValue = totalValue;
        this.status = status;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
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
    
    public ClientEntity getClient() {
        return client;
    }
    
    public void setClient(ClientEntity client) {
        this.client = client;
    }
    
    public List<ProductEntity> getProducts() {
        return products;
    }
    
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
    public PaymentEntity getPayment() {
        return payment;
    }
    
    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
    
    public AddressEntity getAddress() {
        return address;
    }
    
    public void setAddress(AddressEntity address) {
        this.address = address;
    }
    
    public Double getValue() {
        double sum = 0;
        for (ProductEntity productEntity : this.products) {
            sum += productEntity.getPrice();
        }
        return sum;
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
    
    public void setStatus(boolean b) {
        this.status = b;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setStatus(Boolean status) {
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

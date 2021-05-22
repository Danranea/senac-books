package com.senacbooks.senacbooks.orders.details;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.orders.OrdersEntity;
import com.senacbooks.senacbooks.products.ProductEntity;

@Entity
@Table(name = "tb_order_detail")
public class OrderDetailsEntity implements Serializable{
    
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private OrdersEntity order;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private ProductEntity product;

  @ManyToOne
  @JoinColumn(name = "client_id")
  private ClientEntity client;

  private Integer quantity;

  public OrderDetailsEntity() {
  }

  public OrderDetailsEntity(Long id, OrdersEntity order, ProductEntity product, ClientEntity client, Integer quantity) {
    this.id = id;
    this.order = order;
    this.product = product;
    this.client = client;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrdersEntity getOrder() {
    return order;
  }

  public void setOrder(OrdersEntity order) {
    this.order = order;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

  public ClientEntity getClient() {
    return client;
  }

  public void setClient(ClientEntity client) {
    this.client = client;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  
  
}

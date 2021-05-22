package com.senacbooks.senacbooks.orders.details;

import com.senacbooks.senacbooks.clients.ClientDTO;
import com.senacbooks.senacbooks.orders.OrdersDTO;
import com.senacbooks.senacbooks.products.ProductDTO;

public class OrderDetailsDTO {
  
  private Long id;
  private OrdersDTO order;
  private ProductDTO product;
  private ClientDTO client;
  private Integer quantity;
  
  public OrderDetailsDTO() {
  }

  public OrderDetailsDTO(Long id, OrdersDTO order, ProductDTO product, ClientDTO client, Integer quantity) {
    this.id = id;
    this.order = order;
    this.product = product;
    this.client = client;
    this.quantity = quantity;
  }

  public OrderDetailsDTO(OrderDetailsEntity entity){
    this.id = entity.getId();
    this.order = new OrdersDTO(entity.getOrder());
    this.product = new ProductDTO(entity.getProduct());
    this.client = new ClientDTO(entity.getClient());
    this.quantity = entity.getQuantity();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrdersDTO getOrder() {
    return order;
  }

  public void setOrder(OrdersDTO order) {
    this.order = order;
  }

  public ProductDTO getProduct() {
    return product;
  }

  public void setProduct(ProductDTO product) {
    this.product = product;
  }

  public ClientDTO getClient() {
    return client;
  }

  public void setClient(ClientDTO client) {
    this.client = client;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  
  
}

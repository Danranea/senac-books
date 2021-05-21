package com.senacbooks.senacbooks.orders.details;

import com.senacbooks.senacbooks.orders.OrdersDTO;
import com.senacbooks.senacbooks.products.ProductDTO;

public class OrderDetailsDTO {
  
  private Long id;
  private OrdersDTO order;
  private ProductDTO product;
  private Integer quantity;
  
  public OrderDetailsDTO() {
  }

  public OrderDetailsDTO(Long id, OrdersDTO order, ProductDTO product, Integer quantity) {
    this.id = id;
    this.order = order;
    this.product = product;
    this.quantity = quantity;
  }

  public OrderDetailsDTO(OrderDetailsEntity entity){
    this.id = entity.getId();
    this.order = new OrdersDTO(entity.getOrder());
    this.product = new ProductDTO(entity.getProduct());
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

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  
  
}

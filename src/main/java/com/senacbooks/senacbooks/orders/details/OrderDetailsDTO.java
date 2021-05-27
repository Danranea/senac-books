package com.senacbooks.senacbooks.orders.details;

import com.senacbooks.senacbooks.orders.OrdersDetaisInDTO;
import com.senacbooks.senacbooks.products.ProductDTO;

public class OrderDetailsDTO {
  
  private Long id;
  private OrdersDetaisInDTO order;
  private ProductDTO product;
  private Integer quantity;
  
  public OrderDetailsDTO() {
  }

  public OrderDetailsDTO(Long id, OrdersDetaisInDTO order, ProductDTO product, Integer quantity) {
    this.id = id;
    this.order = order;
    this.product = product;
    this.quantity = quantity;
  }

  public OrderDetailsDTO(OrderDetailsEntity entity){
    this.id = entity.getId();
    this.order = new OrdersDetaisInDTO(entity.getOrder());
    this.product = new ProductDTO(entity.getProduct());
    this.quantity = entity.getQuantity();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OrdersDetaisInDTO getOrder() {
    return order;
  }

  public void setOrder(OrdersDetaisInDTO order) {
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

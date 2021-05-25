package com.senacbooks.senacbooks.orders.details;

import com.senacbooks.senacbooks.orders.OrdersDTO;
import com.senacbooks.senacbooks.products.ProductDTO;

public class OrderDetailsDTOOut {
  
  private Long id;
  private ProductDTO product;
  private Integer quantity;
  
  public OrderDetailsDTOOut() {
  }

  public OrderDetailsDTOOut(Long id, ProductDTO product, Integer quantity) {
    this.id = id;
    this.product = product;
    this.quantity = quantity;
  }

  public OrderDetailsDTOOut(OrderDetailsEntity entity){
    this.id = entity.getId();
    this.product = new ProductDTO(entity.getProduct());
    this.quantity = entity.getQuantity();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

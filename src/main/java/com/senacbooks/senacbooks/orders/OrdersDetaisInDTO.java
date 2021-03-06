package com.senacbooks.senacbooks.orders;

import java.io.Serializable;
import java.time.Instant;

import javax.validation.constraints.Positive;

import com.senacbooks.senacbooks.address.AddressDTO;
import com.senacbooks.senacbooks.clients.ClientOrderDTO;
import com.senacbooks.senacbooks.payment.PaymentOrderDTO;

public class OrdersDetaisInDTO implements Serializable {
    
  private static final long serialVersionUID = 1L;

  private Long id;

  private ClientOrderDTO client;

  private PaymentOrderDTO payment;

  private AddressDTO address;

  @Positive(message = "field must be greater than zero")
  private Double value;

  @Positive(message = "field must be greater than zero")
  private Double shipping;

  @Positive(message = "field must be greater than zero")
  private Double totalValue;

  private Boolean status;

  private Instant createdAt;

  private Instant updatedAt;
  
  public OrdersDetaisInDTO() {
  }

  public OrdersDetaisInDTO(
      Long id, 
      ClientOrderDTO client,
      PaymentOrderDTO payment, 
      AddressDTO address,
      Double value, 
      Double shipping, 
      Double totalValue, 
      Boolean status, 
      Instant updatedAt, 
      Instant createdAt
      ) {
      this.id = id;
      this.client = client;
      this.payment = payment;
      this.address = address;
      this.value = value;
      this.shipping = shipping;
      this.totalValue = totalValue;
      this.status = status;
      this.updatedAt = updatedAt;
      this.createdAt = createdAt;
  }

  public OrdersDetaisInDTO(OrdersEntity entity) {
      this.id = entity.getId();
      this.client = new ClientOrderDTO(entity.getClient());
      if (entity.getPayment() != null) {            
          this.payment = new PaymentOrderDTO(entity.getPayment());
      }
      this.address = new AddressDTO(entity.getAddress());
      this.value = entity.getValue();
      this.shipping = entity.getShipping();
      this.totalValue = entity.getTotalValue();
      this.status = entity.getStatus();
      this.updatedAt = entity.getUpdatedAt();
      this.createdAt = entity.getCreatedAt();
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

  public ClientOrderDTO getClient() {
      return client;
  }

  public void setClient(ClientOrderDTO cliente) {
      this.client = cliente;
  }

  public PaymentOrderDTO getPayment() {
      return payment;
  }

  public void setPayment(PaymentOrderDTO payment) {
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
  
}

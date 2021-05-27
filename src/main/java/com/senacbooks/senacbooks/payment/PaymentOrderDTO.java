package com.senacbooks.senacbooks.payment;

import java.io.Serializable;

import com.senacbooks.senacbooks.clients.ClientDTO;

public class PaymentOrderDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String numberCard;

  private String validThru;
  private Integer cvv;

  private Boolean status;

  public PaymentOrderDTO() {
  }

  public PaymentOrderDTO(Long id, String numberCard, String validThru, Integer cvv, Boolean status) {
    this.id = id;
    this.numberCard = numberCard;
    this.validThru = validThru;
    this.cvv = cvv;
    this.status = status;
  }

  public PaymentOrderDTO(PaymentEntity entity) {
    this.id = entity.getId();
    this.numberCard = entity.getNumberCard();
    this.validThru = entity.getValidThru();
    this.cvv = entity.getCvv();
    this.status = entity.getStatus();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumberCard() {
    return numberCard;
  }

  public void setNumberCard(String numberCard) {
    this.numberCard = numberCard;
  }

  public String getValidThru() {
    return validThru;
  }

  public void setValidThru(String validThru) {
    this.validThru = validThru;
  }

  public Integer getCvv() {
    return cvv;
  }

  public void setCvv(Integer cvv) {
    this.cvv = cvv;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

}

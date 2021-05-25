package com.senacbooks.senacbooks.payment;

import java.io.Serializable;

import com.senacbooks.senacbooks.clients.ClientDTO;

public class PaymentDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String numberCard;

    private String validThru;
    private Integer cvv;

    private Boolean status;

    private ClientDTO client;
    
    public PaymentDTO() {
    }

    public PaymentDTO(Long id, String numberCard, String validThru, Integer cvv, Boolean status) {
        this.id = id;
        this.numberCard = numberCard;
        this.validThru = validThru;
        this.cvv = cvv;
        this.status = status;
    }

    public PaymentDTO(PaymentEntity entity) {
        this.id = entity.getId();
        this.numberCard = entity.getNumberCard();
        this.validThru = entity.getValidThru();
        this.cvv = entity.getCvv();
        this.status = entity.getStatus();
        this.client = new ClientDTO(entity.getClient());
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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
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
        PaymentDTO other = (PaymentDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}

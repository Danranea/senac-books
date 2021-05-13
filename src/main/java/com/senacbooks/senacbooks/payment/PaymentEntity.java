package com.senacbooks.senacbooks.payment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.senacbooks.senacbooks.clients.ClientEntity;

@Entity
@Table(name = "tb_payment")
public class PaymentEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String payment;
    private String numberCard;
    private Integer validThru;
    private Integer cvv;
    private Integer plots;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;
    
    public PaymentEntity() {
    }

    public PaymentEntity(Long id, String payment, String numberCard, Integer validThru, Integer cvv, Integer plots) {
        this.id = id;
        this.payment = payment;
        this.numberCard = numberCard;
        this.validThru = validThru;
        this.cvv = cvv;
        this.plots = plots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public Integer getValidThru() {
        return validThru;
    }

    public void setValidThru(Integer validThru) {
        this.validThru = validThru;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getPlots() {
        return plots;
    }

    public void setPlots(Integer plots) {
        this.plots = plots;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ClientEntity getClient(){
        return client;
    }

    public void setClient(ClientEntity client) {
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
        PaymentEntity other = (PaymentEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}

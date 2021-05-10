package com.senacbooks.senacbooks.payment;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class PaymentDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String payment;

    @NotBlank(message = "Campo obrigatório")
    private Integer numberCard;

    @NotBlank(message = "Campo obrigatório")
    private Integer validity;

    @NotBlank(message = "Campo obrigatório")
    private Integer cvv;
    
    private Integer plots;
    
    public PaymentDTO() {
    }

    public PaymentDTO(Long id, String payment, Integer numberCard, Integer validity, Integer cvv, Integer plots) {
        this.id = id;
        this.payment = payment;
        this.numberCard = numberCard;
        this.validity = validity;
        this.cvv = cvv;
        this.plots = plots;
    }

    public PaymentDTO(PaymentEntity entity) {
        this.id = entity.getId();
        this.payment = entity.getPayment();
        this.numberCard = entity.getNumberCard();
        this.validity = entity.getValidity();
        this.cvv = entity.getCvv();
        this.plots = entity.getPlots();
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

    public Integer getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(Integer numberCard) {
        this.numberCard = numberCard;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
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

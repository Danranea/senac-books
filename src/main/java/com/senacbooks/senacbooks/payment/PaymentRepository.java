package com.senacbooks.senacbooks.payment;

import java.util.List;
import java.util.Optional;

import com.senacbooks.senacbooks.payment.PaymentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long>{
    
    public Optional<PaymentEntity> findById(Long id);

    public List<PaymentEntity> findByClientId(Long clientId);

}

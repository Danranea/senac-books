package com.senacbooks.senacbooks.payment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.clients.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientService clientService;

    @Transactional(readOnly = true)
    public List<PaymentDTO> findAll() {
        List<PaymentEntity> list = paymentRepository.findAll();

        return list.stream().map(x -> new PaymentDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PaymentDTO findById(Long id) {
        Optional<PaymentEntity> obj = paymentRepository.findById(id);
        PaymentEntity entity = obj.orElseThrow();

        return new PaymentDTO(entity);
    }

    @Transactional
    public PaymentDTO insert(PaymentDTO dto) {
        PaymentEntity entity = new PaymentEntity();
        copyDTOToEntity(dto, entity);
        entity = paymentRepository.save(entity);

        return new PaymentDTO(entity);
    }

    @Transactional
    public PaymentDTO update(Long id, PaymentDTO dto) {
        PaymentEntity entity = paymentRepository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = paymentRepository.save(entity);

        return new PaymentDTO(entity);
    }

    @Transactional
    public String delete(Long id) {
        Optional<PaymentEntity> obj = paymentRepository.findById(id);
        PaymentEntity entity = obj.orElseThrow();
        String retorno;
        if (entity.getStatus()) {
            entity.setStatus(false);
            entity = paymentRepository.save(entity);
            retorno = "Forma de pagamento " + entity.getPayment() + " inativada com sucesso.";
        }else{
            entity.setStatus(true);
            entity = paymentRepository.save(entity);
            retorno = "Forma de pagamento " + entity.getPayment() + " reativada com sucesso.";
        }
        return retorno;
    }

    public void copyDTOToEntity(PaymentDTO dto, PaymentEntity entity) {
        entity.setPayment(dto.getPayment());
        entity.setNumberCard(dto.getNumberCard());
        entity.setValidity(dto.getValidity());
        entity.setCvv(dto.getCvv());
        entity.setPlots(dto.getPlots());
        
    }
}

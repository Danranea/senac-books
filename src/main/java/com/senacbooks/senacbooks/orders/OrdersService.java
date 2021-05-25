package com.senacbooks.senacbooks.orders;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.address.AddressRepository;
import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.clients.ClientRepository;
import com.senacbooks.senacbooks.payment.PaymentEntity;
import com.senacbooks.senacbooks.payment.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    
    @Transactional(readOnly = true) 
    public Page<OrdersDTO> findAllPaged(PageRequest pageRequest) {
        Page<OrdersEntity> list = ordersRepository.find(pageRequest);
        return list.map(x -> new OrdersDTO(x));
    }

    @Transactional(readOnly = true)
    public OrdersDTO findById(Long id) {
        Optional<OrdersEntity> obj = ordersRepository.findById(id);
        OrdersEntity entity = obj.orElseThrow();
        return new OrdersDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<OrdersDTO> findByClientId(PageRequest pageRequest, Long id) {
        Page<OrdersEntity> obj = ordersRepository.findByClientId(pageRequest, id);
        return obj.map(x -> new OrdersDTO(x));
    }

    @Transactional
    public OrdersDTO insert(OrdersDTO dto) {
        OrdersEntity entity = new OrdersEntity();
        copyDTOToEntity(dto, entity);
        entity.setStatus(true);
        entity = ordersRepository.save(entity);
        return new OrdersDTO(entity);
    }

    @Transactional
    public OrdersDTO update(Long id, OrdersDTO dto) {
        OrdersEntity entity = ordersRepository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = ordersRepository.save(entity);
        return new OrdersDTO(entity);
    }

    @Transactional
    public String delete(Long id) {
        Optional<OrdersEntity> obj = ordersRepository.findById(id);
        OrdersEntity entity = obj.orElseThrow();
        String message;
        if (entity.getStatus()) {
            entity.setStatus(false);
            entity = ordersRepository.save(entity);
            message = "Pedido " + entity.getId() + " inativado com sucesso";
        } else {
            entity.setStatus(true);
            entity = ordersRepository.save(entity);
            message = "Pedido " + entity.getId() + " Reativado com sucesso";
        }
        return message;
    }

    private void copyDTOToEntity(OrdersDTO dto, OrdersEntity entity) {
        entity.setShipping(dto.getShipping());
        entity.setTotalValue(dto.getTotalValue());
        entity.setValue(dto.getValue());
        entity.setCreatedAt(dto.getCreatedAt());

        AddressEntity addressEntity = addressRepository.getOne(dto.getAddress().getId());
        entity.setAddress(addressEntity);

        ClientEntity clientEntity = clientRepository.getOne(dto.getClient().getId());
        entity.setClient(clientEntity);

        if (dto.getPayment() != null){
            PaymentEntity paymentEntity = paymentRepository.getOne(dto.getPayment().getId());
            entity.setPayment(paymentEntity);
        }else{
            entity.setPayment(null);
        }

        // for (OrderDetailsDTO orderDetailsDTO : dto.getOrderDetails()) {
        //     OrderDetailsEntity orderDetailsEntity = orderDetailsRepository.getOne(orderDetailsDTO.getId());
        //     entity.getOrderDetails().add(orderDetailsEntity);
        // }

    }
}

package com.senacbooks.senacbooks.orders;

import java.util.Optional;

import com.senacbooks.senacbooks.users.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    
    @Transactional(readOnly = true) 
    public Page<OrdersDTO> findAllPaged(PageRequest pageRequest) {
        Page<OrdersEntity> list = OrdersRepository.findAllPaged(pageRequest);
        return list.map(x -> new OrdersDTO(x));
    }

    @Transactional(readOnly = true)
    public OrdersDTO findById(Long id) {
        Optional<OrdersEntity> obj = ordersRepository.findById(id);
        OrdersEntity entity = obj.orElseThrow();
        return new OrdersDTO(entity);
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
    }
}

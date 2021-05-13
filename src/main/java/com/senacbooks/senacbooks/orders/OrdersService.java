package com.senacbooks.senacbooks.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.address.AddressRepository;
import com.senacbooks.senacbooks.categories.CategoryDTO;
import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.clients.ClientRepository;
import com.senacbooks.senacbooks.payment.PaymentEntity;
import com.senacbooks.senacbooks.payment.PaymentRepository;
import com.senacbooks.senacbooks.products.ProductDTO;
import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductRepository productRepository;
    
    @Transactional(readOnly = true) 
    public List<OrdersDTO> findAllPaged(PageRequest pageRequest) {
        List<OrdersEntity> list = ordersRepository.findAll();
        List<OrdersDTO> listDto = new ArrayList<>();
        for (OrdersEntity ordersEntity : list) {
            OrdersDTO dto = new OrdersDTO(ordersEntity);
            listDto.add(dto);
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public OrdersDTO findById(Long id) {
        Optional<OrdersEntity> obj = ordersRepository.findById(id);
        OrdersEntity entity = obj.orElseThrow();
        return new OrdersDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<OrdersDTO> findByClientId(Long id) {
        List<OrdersEntity> obj = ordersRepository.findByClientId(id);
        return obj.stream().map(x -> new OrdersDTO(x)).collect(Collectors.toList());
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

        for (ProductDTO productDTO : dto.getProducts()) {
            ProductEntity productEntity = productRepository.getOne(productDTO.getId());
            entity.getProducts().add(productEntity);
        }

    }
}

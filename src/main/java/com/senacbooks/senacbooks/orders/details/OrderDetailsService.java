package com.senacbooks.senacbooks.orders.details;

import java.util.List;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.clients.ClientRepository;
import com.senacbooks.senacbooks.orders.OrdersRepository;
import com.senacbooks.senacbooks.products.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailsService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  @Autowired
  private OrdersRepository ordersRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ProductRepository productRepository;

  @Transactional
  public Page<OrderDetailsDTO> findAllPaged(PageRequest pageRequest) {
    Page<OrderDetailsEntity> list = orderDetailsRepository.find(pageRequest);

    return list.map(x -> new OrderDetailsDTO(x));
  }

  @Transactional(readOnly = true)
  public List<OrderDetailsDTO> findByClientId(Long id) {
    List<OrderDetailsEntity> obj = orderDetailsRepository.findByClientId(id);
    return obj.stream().map(x -> new OrderDetailsDTO(x)).collect(Collectors.toList());
  }

  @Transactional
  public OrderDetailsDTO insert(OrderDetailsDTO dto) {
    OrderDetailsEntity entity = new OrderDetailsEntity();
    copyDTOToEntity(dto, entity);
    entity = orderDetailsRepository.save(entity);
    return new OrderDetailsDTO(entity);
  }

  private void copyDTOToEntity(OrderDetailsDTO dto, OrderDetailsEntity entity) {
    entity.setQuantity(dto.getQuantity());

    entity.setOrder(ordersRepository.getOne(dto.getOrder().getId()));

    entity.setClient(clientRepository.getOne(dto.getClient().getId()));

    entity.setProduct(productRepository.getOne(dto.getProduct().getId()));

  }

}

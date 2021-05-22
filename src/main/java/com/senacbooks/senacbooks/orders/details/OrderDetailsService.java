package com.senacbooks.senacbooks.orders.details;

import java.util.List;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.orders.OrdersDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderDetailsService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

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

}

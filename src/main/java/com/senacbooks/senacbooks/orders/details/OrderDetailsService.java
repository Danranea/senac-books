package com.senacbooks.senacbooks.orders.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  public Page<OrderDetailsDTO> findAllPaged(PageRequest pageRequest) {
    Page<OrderDetailsEntity> list = orderDetailsRepository.find(pageRequest);
    
    return list.map(x -> new OrderDetailsDTO(x));
  }

}

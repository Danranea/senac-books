package com.senacbooks.senacbooks.orders.details;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  public List<OrderDetailsDTO> findAllPaged(PageRequest pageRequest) {
    List<OrderDetailsEntity> list = orderDetailsRepository.findAll();
    List<OrderDetailsDTO> listDto = new ArrayList<>();
    for (OrderDetailsEntity orderDetailsEntity : list) {
      OrderDetailsDTO dto = new OrderDetailsDTO(orderDetailsEntity);
      listDto.add(dto);
    }
    return listDto;
  }

}

package com.senacbooks.senacbooks.orders.details;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/orders/details")
public class OrderDetailsResource {

  @Autowired
  private OrderDetailsService service;

  @GetMapping
  public ResponseEntity<Page<OrderDetailsDTOOut>> findAllPaged(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
      @RequestParam(value = "direction", defaultValue = "DESC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    Page<OrderDetailsDTOOut> list = service.findAllPaged(pageRequest);
    return ResponseEntity.ok().body(list);
  }

  @PostMapping
  public ResponseEntity<OrderDetailsDTO> insert(@RequestBody OrderDetailsDTO dto) {
    OrderDetailsDTO orderDetailsDTO = service.insert(dto);

    URI uri = ServletUriComponentsBuilder
    .fromCurrentRequest()
    .path("/{id}")
    .buildAndExpand(orderDetailsDTO.getId()).toUri();
    
    return ResponseEntity.created(uri).body(orderDetailsDTO);
  }
}

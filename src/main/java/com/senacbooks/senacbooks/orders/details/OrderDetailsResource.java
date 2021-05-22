package com.senacbooks.senacbooks.orders.details;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders/details")
public class OrderDetailsResource {

  @Autowired
  private OrderDetailsService service;

  @GetMapping
  public ResponseEntity<Page<OrderDetailsDTO>> findAllPaged(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    Page<OrderDetailsDTO> list = service.findAllPaged(pageRequest);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/client/{id}")
    public ResponseEntity<List<OrderDetailsDTO>> findByClientId(@PathVariable Long id) {
        List<OrderDetailsDTO> dto = service.findByClientId(id);
        return ResponseEntity.ok().body(dto);
    }
}

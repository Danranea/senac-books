package com.senacbooks.senacbooks.orders;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/orders")
public class OrdersResource {
    
    @Autowired
    private OrdersService service;

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> findAllPaged(
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "40") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<OrdersDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrdersDTO> findById(@PathVariable Long id) {
        OrdersDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<OrdersDTO> insert(@Valid @RequestBody OrdersDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrdersDTO> update(@PathVariable Long id, @Valid @RequestBody OrdersDTO body) {
        OrdersDTO dto = service.update(id, body);

        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id) {
        String retorno = service.delete(id);
        return retorno;
    }
}

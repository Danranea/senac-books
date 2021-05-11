package com.senacbooks.senacbooks.payment;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/payment")
public class PaymentResource {
    
    @Autowired
    private PaymentService service;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> findAll(){
        List<PaymentDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable Long id) {
        PaymentDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> insert(@Valid @RequestBody PaymentDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/client/{clientId}")
    public ResponseEntity<PaymentDTO> insert(@Valid @RequestBody PaymentDTO dto, @PathVariable Long clientId) {
        dto = service.insertByClientId(dto, clientId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable Long id, @Valid @RequestBody PaymentDTO body) {
        PaymentDTO dto = service.update(id, body);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/client/{clientId}")
    public ResponseEntity<List<PaymentDTO>> updateByClientId(@PathVariable Long clientId, @Valid @RequestBody PaymentDTO paymentDto) {
        List<PaymentDTO> dto = service.updateByClientId(clientId, paymentDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public String delte(@PathVariable Long id) {
        String retorno = service.delete(id);
        return retorno;
    }
}

package com.senacbooks.senacbooks.address;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/addresses")
public class AddressResource {
    
    @Autowired
    private AddressService service;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> findAll(){
        List<AddressDTO> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        AddressDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AddressDTO> insert(@Valid @RequestBody AddressDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/client/{clientId}")
    public ResponseEntity<AddressDTO> insert(@Valid @RequestBody AddressDTO dto, @PathVariable Long clientId) {
        dto = service.insertByClientId(dto, clientId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<AddressDTO> update(@PathVariable Long id, @Valid @RequestBody AddressDTO body) {
        AddressDTO dto = service.update(id, body);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/client/{clientId}")
    public ResponseEntity<List<AddressDTO>> updateByClientId(@PathVariable Long clientId, @Valid @RequestBody AddressDTO addressDto) {
        List<AddressDTO> dto = service.updateByClientId(clientId, addressDto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public String delte(@PathVariable Long id) {
        String retorno = service.delete(id);
        return retorno;
    }
}

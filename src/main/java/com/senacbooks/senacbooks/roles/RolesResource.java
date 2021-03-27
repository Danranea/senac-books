package com.senacbooks.senacbooks.roles;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class RolesResource {
    
    @Autowired
    private RolesService service;

    // @GetMapping
    // public ResponseEntity<Page<RolesDTO>> findAll(
    // TODO
    // )

    @GetMapping(value = "/{id}")
    public ResponseEntity<RolesDTO> findById(@PathVariable Long id) {
        RolesDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<RolesDTO> insert(@RequestBody RolesDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RolesDTO> update(@PathVariable Long id, @RequestBody RolesDTO body) {
        RolesDTO dto = service.update(id, body);

        return ResponseEntity.ok().body(dto);
    }

    // @DeleteMapping(value = "/{id}")
    // public String delete(@PathVariable Long id) {
    //     String retorno = service.delete(id);
    //     return retorno;
    //     TODO
    // }
}

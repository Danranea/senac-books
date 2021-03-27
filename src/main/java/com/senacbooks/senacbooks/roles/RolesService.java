package com.senacbooks.senacbooks.roles;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolesService {
    
    @Autowired
    private RolesRepository repository;

    @Transactional(readOnly = true)
    public RolesDTO findById(Long id){
        Optional<RolesEntity> obj = repository.findById(id);
        RolesEntity entity = obj.orElseThrow();

        return new RolesDTO(entity);
    }

    @Transactional
    public RolesDTO insert(RolesDTO dto) {
        RolesEntity entity = new RolesEntity();
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);

        return new RolesDTO(entity);
    }

    @Transactional
    public RolesDTO update(Long id, RolesDTO dto) {
        RolesEntity entity = repository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);

        return new RolesDTO(entity);
    }

    // @Transactional
    // public String delete(Long id) {
        // TODO
    // }

    private void copyDTOToEntity(RolesDTO dto, RolesEntity entity) {
        entity.setId(dto.getId());
        entity.setAuthority(dto.getAuthority());
    }
}

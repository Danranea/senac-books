package com.senacbooks.senacbooks.roles;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<RoleEntity> list = repository.findAll();

        return list.stream().map(x -> new RoleDTO(x)).collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public RoleDTO findById(Long id){
        Optional<RoleEntity> obj = repository.findById(id);
        RoleEntity entity = obj.orElseThrow();

        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO insert(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);

        return new RoleDTO(entity);
    }

    @Transactional
    public RoleDTO update(Long id, RoleDTO dto) {
        RoleEntity entity = repository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);

        return new RoleDTO(entity);
    }

    // @Transactional
    // public String delete(Long id) {
        // TODO
    // }

    private void copyDTOToEntity(RoleDTO dto, RoleEntity entity) {
        entity.setId(dto.getId());
        entity.setAuthority(dto.getAuthority());
    }
}

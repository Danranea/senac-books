package com.senacbooks.senacbooks.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true) 
    public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
        Page<UserEntity> list = repository.findAll(pageRequest);
        return list.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<UserEntity> obj = repository.findById(id);
        UserEntity entity = obj.orElseThrow();
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        UserEntity entity = new UserEntity();
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        UserEntity entity = repository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public String delete(Long id) {
        Optional<UserEntity> obj = repository.findById(id);
        UserEntity entity = obj.orElseThrow();
        String message;
        if (entity.getStatus()) {
            entity.setStatus(false);
            entity = repository.save(entity);
            message = "Usuário " + entity.getName() + " inativado com sucesso";
        } else {
            entity.setStatus(true);
            entity = repository.save(entity);
            message = "Usuário " + entity.getName() + " Ativado com sucesso";
        }
        return message;
    }

    private void copyDTOToEntity(UserDTO dto, UserEntity entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
    }
}

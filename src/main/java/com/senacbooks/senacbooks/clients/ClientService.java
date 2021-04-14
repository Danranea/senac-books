package com.senacbooks.senacbooks.clients;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<ClientEntity> list = clientRepository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<ClientEntity> obj = clientRepository.findById(id);
        ClientEntity entity = obj.orElseThrow();
        return new ClientDTO(entity);
    }

    @Transactional 
    public ClientDTO insert(ClientDTO dto) {
        ClientEntity entity = new ClientEntity();
        copyDTOToEntity(dto, entity);
        entity = clientRepository.save(entity);
        return new ClientDTO();
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
        ClientEntity entity = clientRepository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public String delete(Long id) {
        Optional<ClientEntity> obj = clientRepository.findById(id);
        ClientEntity entity = obj.orElseThrow();
        String message;
        if (entity.getStatus()) {
            entity.setStatus(false);
            entity = clientRepository.save(entity);
            message = "Cliente " + entity.getFirstName() + " " + entity.getLastName() + " excluído com sucesso";
        } else {
            entity.setStatus(false);
            entity = clientRepository.save(entity);
            message = "Cliente " + entity.getFirstName() + " " + entity.getLastName() + " ativado com sucesso";
        }
        return message;
    }
    
    public void copyDTOToEntity(ClientDTO dto, ClientEntity entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setCpf(dto.getCpf());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
    } 
}

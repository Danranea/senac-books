package com.senacbooks.senacbooks.clients;

import java.util.Optional;

import com.senacbooks.senacbooks.address.AddressDTO;
import com.senacbooks.senacbooks.address.AddressEntity;
import com.senacbooks.senacbooks.address.AddressRepository;
import com.senacbooks.senacbooks.address.AddressService;
import com.senacbooks.senacbooks.roles.RoleDTO;
import com.senacbooks.senacbooks.roles.RoleEntity;
import com.senacbooks.senacbooks.roles.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService{
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        entity.setStatus(true);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = clientRepository.save(entity);
        
        for (AddressEntity addressEntity: entity.getAddresses()) {
            addClientToAddress(entity.getId(), addressEntity.getId());
        }

        return new ClientDTO(entity);
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

        for (RoleDTO roleDTO : dto.getRoles()) {
            RoleEntity role = roleRepository.getOne(roleDTO.getId());
            entity.getRoles().add(role);
        }

        entity.getAddresses().clear();
        for (AddressDTO addressDTO : dto.getAddresses()) {
            AddressEntity addressEntity = new AddressEntity();
            addressService.copyDTOToEntity(addressDTO, addressEntity);
            addressEntity = addressRepository.save(addressEntity);
            entity.getAddresses().add(addressEntity);
        }
    }

    public ClientEntity getClientEntity(Long id){
        return clientRepository.findById(id).orElseThrow();
    }

    private void addClientToAddress(Long clientId, Long addressId) {
        ClientEntity clientEntity = getClientEntity(clientId);
        AddressEntity addressEntity = addressService.getAddress(addressId);

        addressEntity.setClient(clientEntity);
    }
}

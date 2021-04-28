package com.senacbooks.senacbooks.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.clients.ClientService;
import com.senacbooks.senacbooks.users.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private ClientService clientService;

    @Transactional(readOnly = true)
    public List<AddressDTO> findAll() {
        List<AddressEntity> list = addressRepository.findAll();

        return list.stream().map(x -> new AddressDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AddressDTO findById(Long id) {
        Optional<AddressEntity> obj = addressRepository.findById(id);
        AddressEntity entity = obj.orElseThrow();

        return new AddressDTO(entity);
    }

    @Transactional
    public AddressDTO insert(AddressDTO dto) {
        AddressEntity entity = new AddressEntity();
        copyDTOToEntity(dto, entity);
        entity = addressRepository.save(entity);

        return new AddressDTO(entity);
    }

    @Transactional
    public AddressDTO update(Long id, AddressDTO dto) {
        AddressEntity entity = addressRepository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = addressRepository.save(entity);

        return new AddressDTO(entity);
    }

    @Transactional
    public String delete(Long id) {
        Optional<AddressEntity> obj = addressRepository.findById(id);
        AddressEntity entity = obj.orElseThrow();
        String retorno;
        if (entity.getStatus()) {
            entity.setStatus(false);
            entity = addressRepository.save(entity);
            retorno = "Endereço " + entity.getAddress() + " inativado com sucesso.";
        }else{
            entity.setStatus(true);
            entity = addressRepository.save(entity);
            retorno = "Endereço " + entity.getAddress() + " reativado com sucesso.";
        }
        return retorno;
    }

    public void copyDTOToEntity(AddressDTO dto, AddressEntity entity) {
        entity.setAddress(dto.getAddress());
        entity.setAddressComplement(dto.getAddressComplement());
        entity.setCity(dto.getCity());
        entity.setNeighborhood(dto.getNeighborhood());
        entity.setNumber(dto.getNumber());
        entity.setZipCode(dto.getZipCode());
        entity.setPayment(dto.getPayment());
        entity.setState(dto.getState());
        entity.setStatus(dto.getStatus());
    }

    public AddressEntity getAddress(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow();
    }

    public List<AddressDTO> updateByClientId(Long clientId, AddressDTO dto) {
        List<AddressEntity> addressesDTOs = addressRepository.findByClientId(clientId);
        List<AddressDTO> addressDTOs= new ArrayList<>();

        for (AddressEntity address : addressesDTOs) {
            if (address.getId().equals(dto.getId())){
                address.setPayment(dto.getPayment());
                address = addressRepository.save(address);
                AddressDTO addressDTO = new AddressDTO(address);
                addressDTOs.add(addressDTO);
            }else{
                address.setPayment(false);
                address = addressRepository.save(address);
                AddressDTO addressDTO = new AddressDTO(address);
                addressDTOs.add(addressDTO);
            }
        }
        return addressDTOs;
    }

    public AddressDTO insertByClientId(AddressDTO dto, Long clientId) {
        AddressEntity entity = new AddressEntity();
        ClientEntity client = clientService.getClientEntity(clientId);
        copyDTOToEntity(dto, entity);
        entity.setClient(client);
        entity = addressRepository.save(entity);

        return new AddressDTO(entity);
    }
}

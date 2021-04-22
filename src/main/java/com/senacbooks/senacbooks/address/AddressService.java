package com.senacbooks.senacbooks.address;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.senacbooks.senacbooks.users.UserDTO;
import com.senacbooks.senacbooks.users.UserEntity;
import com.senacbooks.senacbooks.users.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private UserRepository userRepository;

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
    public void delete(Long id) {
        addressRepository.deleteById(id);
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
    }

    public AddressEntity getAddress(Long addressId) {
        return addressRepository.findById(addressId).orElseThrow();
    }
}

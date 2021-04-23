package com.senacbooks.senacbooks.users;

import java.util.Optional;

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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true) 
    public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
        Page<UserEntity> list = userRepository.findAll(pageRequest);
        return list.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<UserEntity> obj = userRepository.findById(id);
        UserEntity entity = obj.orElseThrow();
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        UserEntity entity = new UserEntity();
        copyDTOToEntity(dto, entity);
        entity.setStatus(true);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = userRepository.save(entity);

        // addressService.insert(dto.getAddress());
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        UserEntity entity = userRepository.getOne(id);
        copyDTOToEntity(dto, entity);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public String delete(Long id) {
        Optional<UserEntity> obj = userRepository.findById(id);
        UserEntity entity = obj.orElseThrow();
        String message;
        if (entity.getStatus()) {
            entity.setStatus(false);
            entity = userRepository.save(entity);
            message = "Usuário " + entity.getName() + " inativado com sucesso";
        } else {
            entity.setStatus(true);
            entity = userRepository.save(entity);
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
        entity.setZipCode(dto.getZipCode());
        entity.setAddress(dto.getAddress());
        entity.setNumber(dto.getNumber());
        entity.setAddressComplement(dto.getAddressComplement());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()) {
            RoleEntity role = roleRepository.getOne(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }

}

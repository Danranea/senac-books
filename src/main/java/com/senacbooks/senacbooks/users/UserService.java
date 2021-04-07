package com.senacbooks.senacbooks.users;

import java.util.Optional;

import com.senacbooks.senacbooks.address.AddressResource;
import com.senacbooks.senacbooks.address.AddressService;
import com.senacbooks.senacbooks.roles.RoleDTO;
import com.senacbooks.senacbooks.roles.RoleEntity;
import com.senacbooks.senacbooks.roles.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired 
    private AddressService addressService;

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
        entity.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()) {
            RoleEntity role = roleRepository.getOne(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByLogin(login);
        if (user == null){
            logger.error("User not found: " + login);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + login);
        return user;
    }
}

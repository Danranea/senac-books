package com.senacbooks.senacbooks.users;

import java.util.Optional;

import com.senacbooks.senacbooks.address.AddressResource;
import com.senacbooks.senacbooks.address.AddressService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository repository;

    // @Autowired 
    // private AddressService addressService;

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

        // addressService.insert(dto.getAddress());
        
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

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = repository.findByLogin(login);
        if (user == null){
            logger.error("User not found: " + login);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("User found: " + login);
        return user;
    }
}

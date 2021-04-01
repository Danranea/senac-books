package com.senacbooks.senacbooks.users;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.senacbooks.senacbooks.categories.CategoryEntity;
import com.senacbooks.senacbooks.exception.CustomException;
import com.senacbooks.senacbooks.products.ProductDTO;
import com.senacbooks.senacbooks.products.ProductEntity;
import com.senacbooks.senacbooks.roles.RoleEntity;
import com.senacbooks.senacbooks.roles.RoleRepository;
import com.senacbooks.senacbooks.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Long roleId, String title, PageRequest pageRequest){
        List<RoleEntity> roles = (roleId == 0)? null : Arrays.asList(roleRepository.getOne(roleId));
        Page<UserEntity> page = repository.find(roles,title, pageRequest);
        return page.map(x -> new UserDTO(x));
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
            message = "O usuário " + entity.getName() + " já está inativo";
        }

        return message;
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserEntity user = repository.findByUserName(username);
            user = repository.findById(user.getId()).orElseThrow();
            UserDTO userDto = new UserDTO(user);
            return jwtTokenProvider.createToken(username, user.getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void copyDTOToEntity(UserDTO dto, UserEntity entity) {
        entity.setName(dto.getName());
        entity.setUserName(dto.getUserName());
        entity.setStatus(dto.getStatus());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = repository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}

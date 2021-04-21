package com.senacbooks.senacbooks.config;

import com.senacbooks.senacbooks.clients.ClientEntity;
import com.senacbooks.senacbooks.clients.ClientRepository;
import com.senacbooks.senacbooks.users.UserEntity;
import com.senacbooks.senacbooks.users.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

  private static Logger logger = LoggerFactory.getLogger(LoginService.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByLogin(login);
    ClientEntity client = clientRepository.findByLogin(login);
    if (user == null && client == null) {
      logger.error("User not found: " + login);
      throw new UsernameNotFoundException("Email not found");
    }
    if (user != null) {
      logger.info("User found: " + login);
      return user;
    } else {
      logger.info("User found: " + login);
      return client;
    }

  }

}

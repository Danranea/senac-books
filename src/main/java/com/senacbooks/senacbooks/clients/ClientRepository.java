package com.senacbooks.senacbooks.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

  // método para acessar o banco buscando por e-mail
  ClientEntity findByLogin(String login);
}

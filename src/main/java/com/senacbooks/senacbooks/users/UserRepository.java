package com.senacbooks.senacbooks.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //método para acessar o banco buscando por e-mail
    UserEntity findByLogin(String login);

}

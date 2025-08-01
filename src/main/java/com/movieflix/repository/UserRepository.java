package com.movieflix.repository;

import com.movieflix.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //Método para buscar um usuário pelo email
    // O JPA vai gerar a consulta automaticamente com base no nome do método
    //derived queries
    Optional<UserDetails> findUserByEmail(String email);
}

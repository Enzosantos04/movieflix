package com.movieflix.service;


import com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//metodo que vai buscar o usuário no banco de dados a partir do email
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email) //necessario criar esse metodo no UserRepository (derived queries do JPA)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}

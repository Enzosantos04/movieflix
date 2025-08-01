package com.movieflix.service;

import com.movieflix.dto.UserDTO;
import com.movieflix.entity.User;
import com.movieflix.mapper.UserMapper;
import com.movieflix.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private  final PasswordEncoder passwordEncoder;
    private final  UserRepository usersRepository;
    private final UserMapper userMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository usersRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    public UserDTO register(UserDTO userDTO) {
        User user = userMapper.map(userDTO);
        //  pega a senha do usuário e codifica ela
        String password = user.getPassword();
        // setando a senha codificada no usuário
        user.setPassword(passwordEncoder.encode(password));
        User savedUser = usersRepository.save(user);
        return userMapper.map(savedUser);
    }
}

package com.movieflix.service;

import com.movieflix.dto.UserDTO;
import com.movieflix.entity.User;
import com.movieflix.mapper.UserMapper;
import com.movieflix.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final  UserRepository usersRepository;
    private final UserMapper userMapper;

public UserService(UserRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }
public UserDTO register(UserDTO userDTO) {
        User user = userMapper.map(userDTO);
        User savedUser = usersRepository.save(user);
        return userMapper.map(savedUser);
    }
}

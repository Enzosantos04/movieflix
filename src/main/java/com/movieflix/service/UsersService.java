package com.movieflix.service;

import com.movieflix.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UserRepository usersRepository;
}

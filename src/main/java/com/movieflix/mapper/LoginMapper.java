package com.movieflix.mapper;

import com.movieflix.dto.LoginDTO;
import com.movieflix.entity.User;



public class LoginMapper {
    public LoginDTO map(LoginDTO loginDTO) {
        User user = new User();
        user.setEmail(loginDTO.getEmail());
        user.setPassword(loginDTO.getPassword());
        return loginDTO;
    }

    public LoginDTO map(User user) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(user.getEmail());
        loginDTO.setPassword(user.getPassword());
        return loginDTO;
    }
}

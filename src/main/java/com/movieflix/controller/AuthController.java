package com.movieflix.controller;


import ch.qos.logback.core.subst.Token;
import com.movieflix.config.TokenService;
import com.movieflix.dto.LoginDTO;
import com.movieflix.dto.UserDTO;
import com.movieflix.entity.User;
import com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class    AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;



    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody  UserDTO userDTO){
        UserDTO savedUser = userService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login) {
        //Esse token é geralmente passado para o AuthenticationManager do Spring Security para validar as credenciais e autenticar o usuário.
        UsernamePasswordAuthenticationToken userAndPassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        Authentication authenticate = authenticationManager.authenticate(userAndPassword);
        User user = (User) authenticate.getPrincipal();
        // Aqui você pode gerar um token JWT ou qualquer outro tipo de token de autenticação
        //retorna o token JWT para o usuário autenticado
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(token);

    }


}

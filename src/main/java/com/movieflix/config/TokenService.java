package com.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieflix.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TokenService {
    // chave secreta para assinar o token JWT, porem vou fazer como enviroment variable no meu aplication.properties
    @Value("${flix.security.secret}") // anotacao do spring para pegar o valor da variavel de ambiente e passar para a variavel secret
    private String secret;
    //metodo para gerar o token JWT
    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret); // HMAC256 'e um algoritmo de encriptacao feito baseado na secret
        return JWT.create()
                .withSubject(user.getEmail()) //dono do token, nesse caso o email
                .withClaim("userId", user.getId()) //claim é uma informação adicional que eu quero passar no token, nesse caso o id do usuário
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400)) //tempo de expiração do token, nesse caso 24 horas (86400 segundos)
                .withIssuedAt(Instant.now()) //data de emissão do token, nesse caso o momento atual
                .withIssuer("MovieFlix") //quem emitiu o token, nesse caso o MovieFlix
                .sign(algorithm);//sign e um algoritmo de encriptação do meu token
    }


    // metodo para validar o token JWT
    // esse metodo vai receber o token JWT e verificar se ele é válido, se for, retorna um objeto JWTUserData(record) com as informações do usuário
    public Optional<JWTUserData> verifyToken(String token) {

        try{ //tenta verificar se o token é válido
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token); //verifica se o token é válido, se não for, lança uma exceção

            //se o token for válido, retorna um objeto JWTUserData com as informações do usuário
           return Optional.of(JWTUserData
                    .builder()
                    .id(jwt.getClaim("userId").asLong())//pega o id do usuário do token
                    .name(jwt.getClaim("name").asString())//pega o nome do usuário do token
                    .email(jwt.getSubject()) //pega o email do usuário do token
                    .build()); //constrói o objeto JWTUserData com as informações do usuário
        }catch (JWTVerificationException exception){
            return Optional.empty(); //se o token não for válido, retorna um Optional vazio

        }


    }


}

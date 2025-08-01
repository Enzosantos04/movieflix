package com.movieflix.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Verifica se o cabeçalho Authorization(postman) está presente e começa com "Bearer"
        String authorizationHeader = request.getHeader("Authorization");


// Se o cabeçalho Authorization estiver presente e começar com "Bearer", extraímos o token
        if(Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer")){
            // Extrai o token do cabeçalho Authorization
            String token = authorizationHeader.substring("Bearer ".length());

            // Verifica se o token é válido
            Optional<JWTUserData> optionalJWTUserData = tokenService.verifyToken(token);
            // Se o token for válido, adiciona as informações do usuário ao request
            if(optionalJWTUserData.isPresent()){
                JWTUserData userData = optionalJWTUserData.get();
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }



            filterChain.doFilter(request, response); // Continua o filtro na cadeia de filtros
        }else {
            filterChain.doFilter(request, response);
        }
    }


}

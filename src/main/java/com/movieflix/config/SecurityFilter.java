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
    // Método que é chamado para filtrar as requisições HTTP
    // Esse método é chamado uma vez por requisição, garantindo que o filtro seja aplicado corretamente
    // O método doFilterInternal é responsável por interceptar as requisições HTTP e verificar
    // se o token JWT está presente e é válido. Se for, ele adiciona as informações do usuário ao contexto de segurança.
    // Se o token não estiver presente ou não for válido, a requisição continua sem autenticação.
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
                // Cria um objeto UsernamePasswordAuthenticationToken com as informações do usuário,
                // sem senha e sem autoridades (roles)
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userData, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }



            filterChain.doFilter(request, response); // Continua o filtro na cadeia de filtros
        }else {
            filterChain.doFilter(request, response);
        }
    }


}

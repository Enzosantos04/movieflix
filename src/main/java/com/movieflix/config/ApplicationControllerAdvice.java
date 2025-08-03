package com.movieflix.config;
import com.movieflix.exception.UsernameOrPasswordInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;


// metodos q captam exceptions baseados nessa exceptions
// e retornam uma resposta personalizada para o usuário
// restControllerAdvice é uma anotação que indica que essa classe é um controlador de exceções
// e que irá interceptar exceções lançadas por controladores REST
@RestControllerAdvice
public class ApplicationControllerAdvice {

    //@ExceptionHandler é uma anotação que indica que o método irá tratar uma exceção específica
    // Nesse caso, o método irá tratar a exceção UsernameOrPasswordInvalidException
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    // @ResponseStatus é uma anotação que indica o status HTTP que será retornado quando a exceção for lançada
    //se ocorrer a exceção UsernameOrPasswordInvalidException, o método irá retornar uma resposta com o status HTTP 400 Bad Request
    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public String handleNotFoundException(UsernameOrPasswordInvalidException ex){
        return ex.getMessage(); //retorna a mensagem de erro da exceção
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //map 'e tipo uma lista de chave-valor, onde a chave é do tipo String e o valor também é do tipo String
    //Esse método irá tratar exceções do tipo MethodArgumentNotValidException, que ocorrem quando há erros de validação nos argumentos de um método
    //e retorna um mapa contendo os erros de validação
    public Map<String, String> handleArgumentNotValidException(MethodArgumentNotValidException ex){
        //cria um novo mapa para armazenar os erros de validação
        Map<String, String> errors = new HashMap<>();
        //ex.getBindingResult().getAllErrors() retorna uma lista de erros de validação
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            //adiciona cada erro de validação ao mapa, onde a chave é o nome do campo e o valor é a mensagem de erro
        errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        return errors; //retorna o mapa de erros de validação
    }
}

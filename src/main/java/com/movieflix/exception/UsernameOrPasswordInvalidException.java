package com.movieflix.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException {
    //essa exceção é lançada quando o usuário ou senha estão incorretos
    //cosntrutor que recebe uma mensagem de erro
    public UsernameOrPasswordInvalidException(String message) {
        //chama o construtor da classe pai (RuntimeException) passando a mensagem de erro
        super(message);

    }
}

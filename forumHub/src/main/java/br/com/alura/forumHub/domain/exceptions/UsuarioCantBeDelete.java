package br.com.alura.forumHub.domain.exceptions;

public class UsuarioCantBeDelete extends RuntimeException{
    public UsuarioCantBeDelete(String message) {
        super(message);
    }
}

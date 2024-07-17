package br.com.alura.forumHub.domain.exceptions;

public class CursoNotFoundException extends RuntimeException{
    public CursoNotFoundException(String message) {
        super(message);
    }
}

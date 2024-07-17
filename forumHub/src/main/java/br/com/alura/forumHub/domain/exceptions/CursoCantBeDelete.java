package br.com.alura.forumHub.domain.exceptions;

public class CursoCantBeDelete extends RuntimeException{
    public CursoCantBeDelete(String message) {
        super(message);
    }
}

package br.com.alura.forumHub.domain.exceptions;

public class TopicoCantBeDelete extends RuntimeException{
    public TopicoCantBeDelete(String message) {
        super(message);
    }
}

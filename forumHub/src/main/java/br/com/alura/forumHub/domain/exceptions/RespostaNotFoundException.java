package br.com.alura.forumHub.domain.exceptions;

public class RespostaNotFoundException extends RuntimeException{
  public RespostaNotFoundException(String message) {
    super(message);
  }
}

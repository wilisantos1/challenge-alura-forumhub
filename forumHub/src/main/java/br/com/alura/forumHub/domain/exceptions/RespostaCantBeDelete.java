package br.com.alura.forumHub.domain.exceptions;

public class RespostaCantBeDelete extends RuntimeException {
  public RespostaCantBeDelete(String message) {
    super(message);
  }
}

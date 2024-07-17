package br.com.alura.forumHub.domain.DTOs.resposta;

import br.com.alura.forumHub.application.entities.Resposta;
import br.com.alura.forumHub.application.entities.Topico;
import br.com.alura.forumHub.application.entities.Usuario;

import java.time.LocalDateTime;

public record RespostaDTO(
    Long id,
    String mensagem,
    Topico topico,
    LocalDateTime dataCriacao,
    Usuario autor,
    String solucao) {
  public RespostaDTO(Resposta resposta) {
    this(
        resposta.getId(),
        resposta.getMensagem(),
        resposta.getTopico(),
        resposta.getDataCriacao(),
        resposta.getAutor(),
        resposta.getSolucao());
  }
}

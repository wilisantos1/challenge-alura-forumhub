package br.com.alura.forumHub.domain.DTOs.resposta;

import br.com.alura.forumHub.application.entities.Topico;
import br.com.alura.forumHub.application.entities.Usuario;

import java.time.LocalDateTime;

public record RespostaCadastroDTO(
    String mensagem,
    Topico topico,
    LocalDateTime dataCriacao,
    Usuario autor,
    String solucao) {
}

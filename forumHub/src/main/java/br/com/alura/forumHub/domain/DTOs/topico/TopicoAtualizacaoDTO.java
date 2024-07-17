package br.com.alura.forumHub.domain.DTOs.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoAtualizacaoDTO(
        @NotNull
        Long id,

        @NotBlank
        String mensagem,

        // Necessário definir uma data a frente do momento atual.
        @NotNull
        @Future
        LocalDateTime data,

        @NotBlank
        String status
) {

}

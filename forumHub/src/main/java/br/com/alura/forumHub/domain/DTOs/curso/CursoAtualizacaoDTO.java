package br.com.alura.forumHub.domain.DTOs.curso;

import jakarta.validation.constraints.NotNull;

public record CursoAtualizacaoDTO(
        String nome,
        String categoria,
        @NotNull
        Long id
) {
}

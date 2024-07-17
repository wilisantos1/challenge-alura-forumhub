package br.com.alura.forumHub.domain.DTOs.usuario;

import jakarta.validation.constraints.NotNull;

public record UsuarioAtualizacaoDTO(
        String nome,

        String senha,

        @NotNull
        Long id
) {
}

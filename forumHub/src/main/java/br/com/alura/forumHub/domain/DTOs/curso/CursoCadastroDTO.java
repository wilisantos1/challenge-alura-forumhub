package br.com.alura.forumHub.domain.DTOs.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoCadastroDTO(
        @NotBlank
        String nome,
        String categoria
) {
}

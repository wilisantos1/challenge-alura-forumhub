package br.com.alura.forumHub.domain.DTOs.usuario;

import br.com.alura.forumHub.application.entities.Usuario;

public record UsuarioDTO(
        Long id,
        String email
) {
    public UsuarioDTO(Usuario autor) {
        this(autor.getId(),autor.getEmail());
    }
}

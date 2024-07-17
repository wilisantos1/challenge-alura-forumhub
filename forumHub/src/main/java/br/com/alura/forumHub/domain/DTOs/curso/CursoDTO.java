package br.com.alura.forumHub.domain.DTOs.curso;


import br.com.alura.forumHub.application.entities.Curso;

public record CursoDTO(
        String nome,
        Long id,
        String categoria
) {
    public CursoDTO(Curso curso) {
        this(curso.getNome(),
            curso.getId(),
            curso.getCategoria());
    }
}

package br.com.alura.forumHub.domain.DTOs.topico;

import br.com.alura.forumHub.application.entities.Topico;
import br.com.alura.forumHub.domain.DTOs.curso.CursoDTO;
import br.com.alura.forumHub.domain.DTOs.usuario.UsuarioDTO;

import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensagem,
        UsuarioDTO usuario,
        LocalDateTime data,
        String status,
        CursoDTO curso
) {
    public TopicoDTO(Topico topico) {
        this(topico.getId(),
            topico.getTitulo(),
            topico.getMensagem(),
            new UsuarioDTO(topico.getUsuario()),
            topico.getDataCriacao(),
            topico.getStatus(),
            new CursoDTO(topico.getCurso()));
    }
}

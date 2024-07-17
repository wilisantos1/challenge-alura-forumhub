package br.com.alura.forumHub.services;


import br.com.alura.forumHub.application.entities.Curso;
import br.com.alura.forumHub.domain.DTOs.curso.CursoAtualizacaoDTO;
import br.com.alura.forumHub.domain.DTOs.curso.CursoCadastroDTO;
import br.com.alura.forumHub.domain.DTOs.curso.CursoDTO;
import br.com.alura.forumHub.domain.exceptions.CursoCantBeDelete;
import br.com.alura.forumHub.domain.exceptions.CursoNotFoundException;
import br.com.alura.forumHub.infrastructure.DB.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoUseCases {

  private final CursoRepository cursoRepository;

  @Autowired
  public CursoUseCases(CursoRepository cursoRepository) {
    this.cursoRepository = cursoRepository;
  }

  @Transactional
  public CursoDTO cadastrarCurso(CursoCadastroDTO cadastrarCursoDTO) {
    Curso curso = new Curso(cadastrarCursoDTO);
    cursoRepository.save(curso);
    return new CursoDTO(curso);
  }

  public List<CursoDTO> listarCursos() {
    return cursoRepository.findAll()
        .stream()
        .map(CursoDTO::new)
        .collect(Collectors.toList());
  }

  public CursoDTO buscarCurso(Long id) {
    return new CursoDTO(cursoRepository
        .findById(id)
        .orElseThrow(
            () -> new CursoNotFoundException("Curso não encontrado!")));
  }

  @Transactional
  public CursoDTO atualizarCurso(CursoAtualizacaoDTO cursoAtualizacaoDTO) {
    Curso curso = cursoRepository.findById(cursoAtualizacaoDTO.id())
        .orElseThrow(
            () -> new CursoNotFoundException("Curso não encontrado!"));

    if (cursoAtualizacaoDTO.nome() != null) {
      curso.setNome(cursoAtualizacaoDTO.nome());
    }
    if (cursoAtualizacaoDTO.categoria() != null) {
      curso.setCategoria(cursoAtualizacaoDTO.categoria());
    }
    cursoRepository.save(curso);
    return new CursoDTO(curso);
  }

  @Transactional
  public void deletarCurso(Long id) {
    try {
      cursoRepository.delete(
          cursoRepository.findById(id)
              .orElseThrow(
                  () -> new CursoNotFoundException("Curso não encontrado!")));
    } catch (Exception e) {
      throw new CursoCantBeDelete("O curso não pode ser deletado!");
    }
  }
}

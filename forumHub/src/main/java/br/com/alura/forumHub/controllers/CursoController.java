package br.com.alura.forumHub.controllers;


import br.com.alura.forumHub.domain.DTOs.curso.CursoAtualizacaoDTO;
import br.com.alura.forumHub.domain.DTOs.curso.CursoCadastroDTO;
import br.com.alura.forumHub.domain.DTOs.curso.CursoDTO;
import br.com.alura.forumHub.services.CursoUseCases;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

  private final CursoUseCases cursoUseCases;

  @Autowired
  public CursoController(CursoUseCases cursoUseCases) {
    this.cursoUseCases = cursoUseCases;
  }

  @GetMapping
  public ResponseEntity<List<CursoDTO>> listarCursos() {
    return ResponseEntity.ok(cursoUseCases.listarCursos());
  }

  @PostMapping
  @Transactional
  public ResponseEntity cadastrarCurso(@RequestBody @Valid CursoCadastroDTO cursoCadastroDTO) {
    return ResponseEntity.created(
        ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(
                cursoUseCases
                    .cadastrarCurso(cursoCadastroDTO)
                    .id())
            .toUri()
    ).body(cursoUseCases.cadastrarCurso(cursoCadastroDTO));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deletarCurso(@PathVariable Long id) {
    cursoUseCases.deletarCurso(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity buscarCurso(@PathVariable Long id) {
    return ResponseEntity.ok(cursoUseCases.buscarCurso(id));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity atualizarCurso(@RequestBody @Valid CursoAtualizacaoDTO cursoAtualizacaoDTO) {
    return ResponseEntity.ok(cursoUseCases.atualizarCurso(cursoAtualizacaoDTO));
  }
}

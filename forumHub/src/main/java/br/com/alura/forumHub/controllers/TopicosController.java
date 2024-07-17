package br.com.alura.forumHub.controllers;

import br.com.alura.forumHub.domain.DTOs.topico.TopicoAtualizacaoDTO;
import br.com.alura.forumHub.domain.DTOs.topico.TopicoCadastroDTO;
import br.com.alura.forumHub.domain.DTOs.topico.TopicoDTO;
import br.com.alura.forumHub.services.TopicosUseCases;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {

  private final TopicosUseCases topicosService;

  @Autowired
  public TopicosController(TopicosUseCases topicosService) {
    this.topicosService = topicosService;
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deletarTopico(@PathVariable Long id) {
    topicosService.deletarTopico(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity atualizarTopico(@RequestBody @Valid TopicoAtualizacaoDTO topicoAtualizacaoDTO) {
    return ResponseEntity.ok(topicosService.atualizarTopico(topicoAtualizacaoDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity buscarTopico(@PathVariable Long id) {
    return ResponseEntity.ok(topicosService.buscarTopico(id));
  }

  @GetMapping
  public ResponseEntity<List<TopicoDTO>> listarTopicos() {
    return ResponseEntity.ok(topicosService.listarTopicos());
  }

  @PostMapping
  @Transactional
  public ResponseEntity criarTopico(@RequestBody @Valid TopicoCadastroDTO topicoCadastroDTO) {
    return ResponseEntity.created(
        ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(topicosService
                .criarTopico(topicoCadastroDTO)
                .id())
            .toUri()
    ).body(topicosService.criarTopico(topicoCadastroDTO));
  }
}

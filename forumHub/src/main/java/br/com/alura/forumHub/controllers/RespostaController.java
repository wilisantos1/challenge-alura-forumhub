package br.com.alura.forumHub.controllers;


import br.com.alura.forumHub.domain.DTOs.resposta.RespostaAtualizacaoDTO;
import br.com.alura.forumHub.domain.DTOs.resposta.RespostaCadastroDTO;
import br.com.alura.forumHub.domain.DTOs.resposta.RespostaDTO;
import br.com.alura.forumHub.services.RespostaUseCases;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/resposta")
@SecurityRequirement(name = "bearer-key")
public class RespostaController {

  private final RespostaUseCases respostaUseCases;

  @Autowired
  public RespostaController(RespostaUseCases respostaUseCases) {
    this.respostaUseCases = respostaUseCases;
  }

  @GetMapping
  public ResponseEntity<List<RespostaDTO>> listarRespostas() {
    return ResponseEntity.ok(respostaUseCases.listarRespostas());
  }

  @PostMapping
  @Transactional
  public ResponseEntity cadastrarResposta(@RequestBody @Valid RespostaCadastroDTO respostaCadastroDTO) {
    return ResponseEntity.created(
        ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(
                respostaUseCases
                    .cadastrarResposta(respostaCadastroDTO)
                    .id())
            .toUri()
    ).body(respostaUseCases.cadastrarResposta(respostaCadastroDTO));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deletarResposta(@PathVariable Long id) {
    respostaUseCases.deletarResposta(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity buscarResposta(@PathVariable Long id) {
    return ResponseEntity.ok(respostaUseCases.buscarResposta(id));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity atualizarResposta(@RequestBody @Valid RespostaAtualizacaoDTO respostaAtualizacaoDTO) {
    return ResponseEntity.ok(respostaUseCases.atualizarResposta(respostaAtualizacaoDTO));
  }
}

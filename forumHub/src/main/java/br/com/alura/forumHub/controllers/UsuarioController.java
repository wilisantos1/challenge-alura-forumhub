package br.com.alura.forumHub.controllers;

import br.com.alura.forumHub.application.entities.Usuario;
import br.com.alura.forumHub.domain.DTOs.usuario.*;
import br.com.alura.forumHub.infrastructure.security.TokenService;
import br.com.alura.forumHub.services.UsuarioUseCases;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;
  private final UsuarioUseCases usuarioService;

  @Autowired
  public UsuarioController(AuthenticationManager authenticationManager, TokenService tokenService, UsuarioUseCases usuarioService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
    this.usuarioService = usuarioService;
  }

  @PostMapping("/login")
  public ResponseEntity loginUsuario(@RequestBody @Valid UsuarioAutenticacaoDTO usuarioAutenticacaoDTO) {
    return ResponseEntity.ok(new TokenUsuarioDTO(
        tokenService.gerarToken((Usuario) authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(usuarioAutenticacaoDTO.email(), usuarioAutenticacaoDTO.senha())).getPrincipal())));
  }

  @PostMapping
  @Transactional
  public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO usuarioCadastroDTO) {
    var usuarioDTO = this.usuarioService.cadastrarUsuario(usuarioCadastroDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.id()).toUri();
    return ResponseEntity.created(uri).body(usuarioDTO);
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity atualizarUsuario(@RequestBody @Valid UsuarioAtualizacaoDTO usuarioAtualizacaoDTO) {
    return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioAtualizacaoDTO));
  }

  @GetMapping
  public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
    return ResponseEntity.ok(usuarioService.listarUsuarios());
  }

  @GetMapping("/{id}")
  public ResponseEntity buscarUsuario(@PathVariable Long id) {
    return ResponseEntity.ok(usuarioService.buscarUsuario(id));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity deletarUsuario(@PathVariable Long id) {
    usuarioService.deletarUsuario(id);
    return ResponseEntity.noContent().build();
  }
}

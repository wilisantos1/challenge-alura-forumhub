package br.com.alura.forumHub.services;

import br.com.alura.forumHub.application.entities.Usuario;
import br.com.alura.forumHub.domain.DTOs.usuario.UsuarioAtualizacaoDTO;
import br.com.alura.forumHub.domain.DTOs.usuario.UsuarioCadastroDTO;
import br.com.alura.forumHub.domain.DTOs.usuario.UsuarioDTO;
import br.com.alura.forumHub.domain.exceptions.UsuarioCantBeDelete;
import br.com.alura.forumHub.domain.exceptions.UsuarioNotFoundException;
import br.com.alura.forumHub.infrastructure.DB.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioUseCases implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UsuarioUseCases(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.usuarioRepository.findByEmail(email);
  }

  @Transactional
  public UsuarioDTO cadastrarUsuario(UsuarioCadastroDTO usuarioCadastrarDTO) {
    Usuario usuario = new Usuario(usuarioCadastrarDTO);
    usuario.setSenha(passwordEncoder.encode(usuarioCadastrarDTO.senha()));
    usuarioRepository.save(usuario);
    return new UsuarioDTO(usuario);
  }

  public List<UsuarioDTO> listarUsuarios() {
    var lista = this.usuarioRepository.findAll();
    return lista.stream().map(UsuarioDTO::new).collect(Collectors.toList());
  }

  public UsuarioDTO buscarUsuario(Long id) {
    Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException("O usúario buscado não foi encontrado!"));
    return new UsuarioDTO(usuario);
  }

  @Transactional
  public UsuarioDTO atualizarUsuario(UsuarioAtualizacaoDTO dto) {
    Usuario usuario = this.usuarioRepository.findById(dto.id()).orElseThrow(() -> new UsuarioNotFoundException("O usúario não foi encontrado!"));
    if (dto.senha() != null) {
      usuario.setSenha(passwordEncoder.encode(dto.senha()));
    }
    if (dto.nome() != null) {
      usuario.setNome(dto.nome());
    }
    this.usuarioRepository.save(usuario);
    return new UsuarioDTO(usuario);
  }


  @Transactional
  public void deletarUsuario(Long id) {
    buscarUsuario(id);
    try {
      this.usuarioRepository.deleteById(id);
    } catch (Exception e) {
      throw new UsuarioCantBeDelete("O usuário não pode ser deletado!");
    }
  }
}


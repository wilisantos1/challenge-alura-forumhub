package br.com.alura.forumHub.services;

import br.com.alura.forumHub.application.entities.Topico;
import br.com.alura.forumHub.domain.DTOs.topico.TopicoAtualizacaoDTO;
import br.com.alura.forumHub.domain.DTOs.topico.TopicoCadastroDTO;
import br.com.alura.forumHub.domain.DTOs.topico.TopicoDTO;
import br.com.alura.forumHub.domain.exceptions.TopicoCantBeDelete;
import br.com.alura.forumHub.domain.exceptions.TopicoNotFoundException;
import br.com.alura.forumHub.infrastructure.DB.repositories.CursoRepository;
import br.com.alura.forumHub.infrastructure.DB.repositories.TopicoRepository;
import br.com.alura.forumHub.infrastructure.DB.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicosUseCases {

  private final TopicoRepository topicoRepository;
  private final UsuarioRepository usuarioRepository;
  private final CursoRepository cursoRepository;

  @Autowired
  public TopicosUseCases(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository) {
    this.topicoRepository = topicoRepository;
    this.usuarioRepository = usuarioRepository;
    this.cursoRepository = cursoRepository;
  }

  public List<TopicoDTO> listarTopicos() {
    return this.topicoRepository.findAll().stream()
        .map(TopicoDTO::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public TopicoDTO criarTopico(TopicoCadastroDTO topicoCadastroDTO) {
    Topico topico = new Topico(topicoCadastroDTO);
    topico.setUsuario(usuarioRepository.getReferenceById(topicoCadastroDTO.autor().id()));
    topico.setCurso(cursoRepository.getReferenceById(topicoCadastroDTO.curso().id()));
    topicoRepository.save(topico);
    return new TopicoDTO(topico);
  }

  @Transactional
  public void deletarTopico(Long id) {
    buscarTopico(id);
    try {
      topicoRepository.deleteById(id);
    } catch (Exception e) {
      throw new TopicoCantBeDelete("O tópico não pode ser removido!");
    }
  }

  public TopicoDTO buscarTopico(Long id) {
    return new TopicoDTO(topicoRepository.findById(id).orElseThrow(() -> new TopicoNotFoundException("Tópico não encontrado!")));
  }

  @Transactional
  public TopicoDTO atualizarTopico(TopicoAtualizacaoDTO topicoAtualizacaoDTO) {
    Topico topico = topicoRepository.findById(topicoAtualizacaoDTO.id())
        .orElseThrow(
            () -> new TopicoNotFoundException("O tópico não foi encontrado, por isso não é possível realizar as alterações!"));

    if (topicoAtualizacaoDTO.status() != null) {
      topico.setStatus(topicoAtualizacaoDTO.status());
    }
    if (topicoAtualizacaoDTO.mensagem() != null) {
      topico.setMensagem(topicoAtualizacaoDTO.mensagem());
    }
    topico.setDataAtualizacao(topicoAtualizacaoDTO.data());

    topicoRepository.save(topico);

    return new TopicoDTO(topico);
  }
}

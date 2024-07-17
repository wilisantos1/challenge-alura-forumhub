package br.com.alura.forumHub.services;


import br.com.alura.forumHub.application.entities.Resposta;
import br.com.alura.forumHub.domain.DTOs.resposta.RespostaAtualizacaoDTO;
import br.com.alura.forumHub.domain.DTOs.resposta.RespostaCadastroDTO;
import br.com.alura.forumHub.domain.DTOs.resposta.RespostaDTO;
import br.com.alura.forumHub.domain.exceptions.RespostaCantBeDelete;
import br.com.alura.forumHub.domain.exceptions.RespostaNotFoundException;
import br.com.alura.forumHub.infrastructure.DB.repositories.RespostaRepository;
import br.com.alura.forumHub.infrastructure.DB.repositories.TopicoRepository;
import br.com.alura.forumHub.infrastructure.DB.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RespostaUseCases {

  private final RespostaRepository respostaRepository;
  private final UsuarioRepository usuarioRepository;
  private final TopicoRepository topicoRepository;

  @Autowired
  public RespostaUseCases(RespostaRepository respostaRepository, UsuarioRepository usuarioRepository, TopicoRepository topicoRepository) {
    this.respostaRepository = respostaRepository;
    this.usuarioRepository = usuarioRepository;
    this.topicoRepository = topicoRepository;
  }

  @Transactional
  public RespostaDTO cadastrarResposta(RespostaCadastroDTO cadastrarRespostaDTO) {
    Resposta resposta = new Resposta(cadastrarRespostaDTO);
    resposta.setAutor(usuarioRepository.getReferenceById(cadastrarRespostaDTO.autor().getId()));
    resposta.setTopico(topicoRepository.getReferenceById(cadastrarRespostaDTO.topico().getId()));
    respostaRepository.save(resposta);
    return new RespostaDTO(resposta);
  }

  public List<RespostaDTO> listarRespostas() {
    return respostaRepository.findAll()
        .stream()
        .map(RespostaDTO::new)
        .collect(Collectors.toList());
  }

  public RespostaDTO buscarResposta(Long id) {
    return new RespostaDTO(respostaRepository
        .findById(id)
        .orElseThrow(
            () -> new RespostaNotFoundException("Resposta não encontrado!")));
  }

  @Transactional
  public RespostaDTO atualizarResposta(RespostaAtualizacaoDTO respostaAtualizacaoDTO) {
    Resposta resposta = respostaRepository.findById(respostaAtualizacaoDTO.id())
        .orElseThrow(
            () -> new RespostaNotFoundException("Resposta não encontrado!"));

    if (respostaAtualizacaoDTO.autor() != null) {
      resposta.setAutor(respostaAtualizacaoDTO.autor());
    }
    if (respostaAtualizacaoDTO.dataCriacao() != null) {
      resposta.setDataCriacao(respostaAtualizacaoDTO.dataCriacao());
    }
    if (respostaAtualizacaoDTO.topico() != null) {
      resposta.setTopico(respostaAtualizacaoDTO.topico());
    }
    if (respostaAtualizacaoDTO.mensagem() != null) {
      resposta.setMensagem(respostaAtualizacaoDTO.mensagem());
    }
    if (respostaAtualizacaoDTO.solucao() != null) {
      resposta.setSolucao(respostaAtualizacaoDTO.solucao());
    }
    if (respostaAtualizacaoDTO.id() != null) {
      resposta.setId(respostaAtualizacaoDTO.id());
    }

    respostaRepository.save(resposta);
    return new RespostaDTO(resposta);
  }

  @Transactional
  public void deletarResposta(Long id) {
    try {
      respostaRepository.delete(
          respostaRepository.findById(id)
              .orElseThrow(
                  () -> new RespostaNotFoundException("Resposta não encontrado!")));
    } catch (Exception e) {
      throw new RespostaCantBeDelete("O curso não pode ser deletado!");
    }
  }
}

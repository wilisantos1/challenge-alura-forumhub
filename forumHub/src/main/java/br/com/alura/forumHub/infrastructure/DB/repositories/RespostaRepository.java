package br.com.alura.forumHub.infrastructure.DB.repositories;

import br.com.alura.forumHub.application.entities.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta,Long> {
}

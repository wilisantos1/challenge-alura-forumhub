package br.com.alura.forumHub.infrastructure.DB.repositories;

import br.com.alura.forumHub.application.entities.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico,Long> {
}

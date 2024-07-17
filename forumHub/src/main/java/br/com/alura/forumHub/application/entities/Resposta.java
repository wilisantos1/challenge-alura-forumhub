package br.com.alura.forumHub.application.entities;

import br.com.alura.forumHub.domain.DTOs.resposta.RespostaCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "respostas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String mensagem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    private String solucao;

    public Resposta(RespostaCadastroDTO cadastrarRespostaDTO) {
        this.mensagem = cadastrarRespostaDTO.mensagem();
        this.dataCriacao = cadastrarRespostaDTO.dataCriacao();
        this.solucao = cadastrarRespostaDTO.solucao();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Resposta resposta = (Resposta) o;
        return getId() != null && Objects.equals(getId(), resposta.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Resposta{" +
               "id=" + id +
               ", mensagem='" + mensagem + '\'' +
               ", topico=" + topico +
               ", dataCriacao=" + dataCriacao +
               ", autor=" + autor +
               ", solucao='" + solucao + '\'' +
               '}';
    }
}

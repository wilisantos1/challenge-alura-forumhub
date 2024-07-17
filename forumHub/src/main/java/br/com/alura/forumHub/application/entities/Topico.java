package br.com.alura.forumHub.application.entities;

import br.com.alura.forumHub.domain.DTOs.topico.TopicoCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    @Column(unique = true)
    private String titulo;

    @NotBlank
    @Column(unique = true)
    private String mensagem;

    private LocalDateTime dataCriacao;

    private String status;

    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resposta> respostas = new ArrayList<>();

    public Topico(TopicoCadastroDTO topicoCadastroDTO) {
        this.titulo = topicoCadastroDTO.titulo();
        this.mensagem = topicoCadastroDTO.mensagem();
        this.dataCriacao = topicoCadastroDTO.data();
        this.status = topicoCadastroDTO.status();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Topico topico = (Topico) o;
        return getId() != null && Objects.equals(getId(), topico.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Topico{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", mensagem='" + mensagem + '\'' +
               ", dataCriacao=" + dataCriacao +
               ", status='" + status + '\'' +
               ", dataAtualizacao=" + dataAtualizacao +
               ", usuario=" + usuario +
               '}';
    }
}

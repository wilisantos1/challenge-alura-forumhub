create table respostas(
    id bigint auto_increment primary key,
    mensagem text not null,
    topico_id bigint not null,
    data_criacao datetime not null,
    usuario_id bigint not null,
    solucao varchar(255),
    CONSTRAINT fk_respostas_topicos FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respostas_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
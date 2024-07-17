create table topicos(
    id bigint auto_increment primary key,
    titulo varchar(255) not null,
    mensagem text not null,
    data_criacao datetime not null,
    status varchar(255),
    usuario_id bigint not null,
    curso_id bigint not null,
    respostas bigint,
    CONSTRAINT fk_topicos_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_cursos FOREIGN KEY (curso_id) REFERENCES cursos(id)
);
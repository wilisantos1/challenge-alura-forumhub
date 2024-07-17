ALTER TABLE topicos
    ADD CONSTRAINT fk_topicos_respostas FOREIGN KEY (respostas) REFERENCES respostas(id);
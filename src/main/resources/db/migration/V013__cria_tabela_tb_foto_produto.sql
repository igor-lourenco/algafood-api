CREATE TABLE tb_foto_produto (
	produto_id bigint not null,
    nome_arquivo varchar(150) not null,
    descricao varchar(150) not null,
    content_type varchar(80) not null,
    tamanho int not null,

    PRIMARY KEY (produto_id),
    CONSTRAINT fk_foto_produto_tb_produto FOREIGN KEY (produto_id) REFERENCES tb_produto (id)
) ENGINE=INNODB DEFAULT charset=utf8;
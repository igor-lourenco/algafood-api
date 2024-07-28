CREATE TABLE tb_cidade (
	id bigint not null AUTO_INCREMENT,
    nome_cidade varchar(80) not null,
    nome_estado varchar(80) not null,

    PRIMARY KEY (id)
) ENGINE=INNODB charset=utf8;
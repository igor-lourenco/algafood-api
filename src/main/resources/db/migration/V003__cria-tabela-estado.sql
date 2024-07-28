
CREATE TABLE tb_estado (
	id bigint not null AUTO_INCREMENT,
   nome varchar(80) not null,
    PRIMARY KEY (id)
) ENGINE=INNODB charset=utf8;


insert into tb_cidade (nome_cidade, nome_estado) values ('Uberlândia', 'Minas Gerais');
insert into tb_cidade (nome_cidade, nome_estado) values ('Belo Horizonte', 'Minas Gerais');
insert into tb_cidade (nome_cidade, nome_estado) values ('São Paulo', 'São Paulo');
insert into tb_cidade (nome_cidade, nome_estado) values ('Campinas', 'São Paulo');
insert into tb_cidade (nome_cidade, nome_estado) values ('Fortaleza', 'Ceará');

-- Adiciona os valores que estao na coluna 'nome_estado' da tabela tb_cidade na tabela tb_estado na coluna 'nome'
INSERT INTO tb_estado (nome) SELECT DISTINCT nome_estado from tb_cidade;

-- Cria uma coluna na tabela tb_cidade
ALTER TABLE tb_cidade ADD COLUMN estado_id bigint not null;

-- Adiciona o id na coluna 'estado_id' for igual ('id' da tabela tb_estado onde o campo da coluna 'nome' for igual ao campo da coluna 'nome_estado' da tabela tb_cidade)
UPDATE tb_cidade c SET c.estado_id = (SELECT e.id from tb_estado e WHERE e.nome = c.nome_estado);

-- Adiciona uma CONSTRAINT com nome 'fk_cidade_estado' e cria uma chave estrangeira referenciando o 'estado_id' da tabela tb_cidade ao 'id' da tabela tb_estado
ALTER TABLE tb_cidade ADD CONSTRAINT fk_cidade_estado FOREIGN KEY (estado_id) REFERENCES tb_estado (id);

-- Apaga a coluna 'nome_estado'
ALTER TABLE tb_cidade DROP COLUMN nome_estado;

-- Atualiza a coluna 'nome_cidade' para 'nome'  na tabela tb_cidade
ALTER TABLE tb_cidade CHANGE nome_cidade nome varchar(80) not null;




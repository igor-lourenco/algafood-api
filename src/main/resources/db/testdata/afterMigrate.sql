
--  Desabilita a checagem de chaves estrangeiras (cada banco de dados tem sua forma)
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM tb_cidade;
DELETE FROM tb_cozinha;
DELETE FROM tb_estado;
DELETE FROM tb_forma_pagamento;
DELETE FROM tb_grupo;
DELETE FROM tb_grupo_permissao; 
DELETE FROM tb_permissao;
DELETE FROM tb_produto; 
DELETE FROM tb_restaurante;
DELETE FROM tb_restaurante_forma_pagamento;
DELETE FROM tb_usuario;
DELETE FROM tb_usuario_grupo;
DELETE FROM tb_restaurante_usuario;
DELETE FROM tb_item_pedido;
DELETE FROM tb_pedido;

-- Habilita a checagem de chaves estrangeiras novamente
SET FOREIGN_KEY_CHECKS = 1;


-- Altera as tabelas pra comecarem o auto_imcrement do 1 novamente
ALTER TABLE tb_cidade AUTO_INCREMENT = 1;
ALTER TABLE tb_cozinha AUTO_INCREMENT = 1;
ALTER TABLE tb_estado AUTO_INCREMENT = 1;
ALTER TABLE tb_forma_pagamento AUTO_INCREMENT = 1;
ALTER TABLE tb_grupo AUTO_INCREMENT = 1;
ALTER TABLE tb_permissao AUTO_INCREMENT = 1;
ALTER TABLE tb_produto AUTO_INCREMENT = 1;
ALTER TABLE tb_restaurante AUTO_INCREMENT = 1;
ALTER TABLE tb_usuario AUTO_INCREMENT = 1;
ALTER TABLE tb_usuario_grupo AUTO_INCREMENT = 1;
ALTER TABLE tb_restaurante_usuario AUTO_INCREMENT = 1;
ALTER TABLE tb_item_pedido AUTO_INCREMENT = 1;
ALTER TABLE tb_pedido AUTO_INCREMENT = 1;


-- Insere os dados na tabela
insert into tb_estado (id, nome) values (1, 'Minas Gerais');
insert into tb_estado (id, nome) values (2, 'São Paulo');
insert into tb_estado (id, nome) values (3, 'Ceará');

insert into tb_cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into tb_cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into tb_cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into tb_cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into tb_cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into tb_cozinha (nome) VALUES ('Tailandesa');
insert into tb_cozinha (nome) VALUES ('Indiana');
insert into tb_cozinha (nome) VALUES ('Etiope');
insert into tb_cozinha (nome) VALUES ('Marroquina');
insert into tb_cozinha (nome) VALUES ('Sul-Africana');
insert into tb_cozinha (nome) values ('Argentina');
insert into tb_cozinha (nome) values ('Brasileira');

insert into tb_forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into tb_forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into tb_forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo, aberto) values (1, 'Thai Gourmet', 10, 1, '12345-678', 'Rua das flores', '999', 'Rua colorida', 'Bairro Jardim', 1, utc_timestamp, utc_timestamp, true, true);
insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'The Test Kitchen', 15, 5, utc_timestamp, utc_timestamp, true, true);
insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'La Maison Arabe', 15, 4,utc_timestamp, utc_timestamp, true, true);
insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Enqutatash ', 15, 3, utc_timestamp, utc_timestamp, true, true);

insert into tb_produto (ativo, descricao, nome, preco, restaurante_id) values (0, 'Produto descrição', 'Nome do Produto', 9.9, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into tb_produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into tb_restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into tb_usuario (id, nome, email, senha, data_cadastro) values (1, 'Usuário 1', 'usuario1@email.com', '123', utc_timestamp);
insert into tb_usuario (id, nome, email, senha, data_cadastro) values (2, 'Usuário 2', 'usuario3@email.com', '123', utc_timestamp);
insert into tb_usuario (id, nome, email, senha, data_cadastro) values (3, 'Usuário 3', 'usuario3@email.com', '123', utc_timestamp);

insert into tb_permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into tb_permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into tb_grupo (id, nome) values (1, 'Grupo 1');
insert into tb_grupo (id, nome) values (2, 'Grupo 2');
insert into tb_grupo (id, nome) values (3, 'Grupo 3');
insert into tb_grupo (id, nome) values (4, 'Grupo 4');

insert into tb_grupo_permissao (grupo_id, permissao_id) values (1, 1), (2, 1), (2, 2), (3, 1);

insert into tb_usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into tb_restaurante_usuario (restaurante_id, usuario_id) values (1, 1);
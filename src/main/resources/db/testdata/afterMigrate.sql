
--  Desabilita a checagem de chaves estrangeiras (cada banco de dados tem sua forma)
SET FOREIGN_KEY_CHECKS = 0;

-- Comando usado para controlar o acesso concorrente a tabelas. Quando uma tabela e bloqueada, impede-se que outras transacoes realizem operacoes conflitantes nela.
lock tables
 tb_cidade write,
 tb_cozinha write,
 tb_estado write,
 tb_forma_pagamento write,
 tb_grupo write,
 tb_grupo_permissao write,
 tb_permissao write,
 tb_produto write,
 tb_restaurante write,
 tb_restaurante_forma_pagamento write,
 tb_usuario write,
 tb_usuario_grupo write,
 tb_restaurante_usuario write,
 tb_item_pedido write,
 tb_pedido write,
 tb_foto_produto write,
 oauth_client_details write,
 oauth2_registered_client write;

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
DELETE FROM tb_foto_produto;
DELETE FROM oauth_client_details; -- tabela usada pelo antigo Spring Security
DELETE FROM oauth2_registered_client; -- tabela usada pelo novo Spring Security


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

insert into tb_forma_pagamento (id, descricao, data_atualizacao) values (1, 'Cartão de crédito', utc_timestamp);
insert into tb_forma_pagamento (id, descricao, data_atualizacao) values (2, 'Cartão de débito', utc_timestamp);
insert into tb_forma_pagamento (id, descricao, data_atualizacao) values (3, 'Dinheiro', utc_timestamp);
insert into tb_forma_pagamento (id, descricao, data_atualizacao) values (4, 'Pix', utc_timestamp);

insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo, aberto)
values (1, 'Thai Gourmet', 10, 1, '12345-678', 'Rua das flores', '999', 'Rua colorida', 'Bairro Jardim', 1, utc_timestamp, utc_timestamp, true, true);

-- insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
-- insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
-- insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'The Test Kitchen', 15, 5, utc_timestamp, utc_timestamp, true, true);
-- insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'La Maison Arabe', 15, 4,utc_timestamp, utc_timestamp, true, true);
-- insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Enqutatash ', 15, 3, utc_timestamp, utc_timestamp, true, true);


insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo, aberto)
values (2, 'Thai Delivery', 9.50, 1, '12345-678', 'Rua das flores', '999', 'Rua colorida', 'Bairro Jardim', 1, utc_timestamp, utc_timestamp, true, true);

insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo, aberto)
values (3, 'Tuk Tuk Comida Indiana', 15, 2, '12345-678', 'Rua das flores', '999', 'Rua colorida', 'Bairro Jardim', 1, utc_timestamp, utc_timestamp, true, true);

insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo, aberto)
values (4, 'The Test Kitchen', 15, 5, '12345-678', 'Rua das flores', '999', 'Rua colorida', 'Bairro Jardim', 1, utc_timestamp, utc_timestamp, true, true);

insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo, aberto)
values (5, 'La Maison Arabe', 15, 4, '12345-678', 'Rua das flores', '999', 'Rua colorida', 'Bairro Jardim', 1, utc_timestamp, utc_timestamp, true, true);

insert into tb_restaurante (id, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, data_cadastro, data_atualizacao, ativo, aberto)
values (6, 'Enqutatash', 15, 3, '12345-678', 'Rua das flores', '999', 'Rua colorida', 'Bairro Jardim', 1, utc_timestamp, utc_timestamp, true, true);



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

insert into tb_usuario (id, nome, email, senha, data_cadastro) values (1, 'Diana R', 'diana@email.com', '$2a$12$t3bjL2pvIG7/kmNnjRxJLOktxn9Y7jrJ1ENHimRBzcZqpepZyv/5S', utc_timestamp);
insert into tb_usuario (id, nome, email, senha, data_cadastro) values (2, 'Lexi L', 'lexi@email.com', '$2a$12$t3bjL2pvIG7/kmNnjRxJLOktxn9Y7jrJ1ENHimRBzcZqpepZyv/5S', utc_timestamp);
insert into tb_usuario (id, nome, email, senha, data_cadastro) values (3, 'Eva E', 'eva@email.com', '$2a$12$t3bjL2pvIG7/kmNnjRxJLOktxn9Y7jrJ1ENHimRBzcZqpepZyv/5S', utc_timestamp);
insert into tb_usuario (id, nome, email, senha, data_cadastro) values (4, 'Riley R', 'riley@email.com', '$2a$12$t3bjL2pvIG7/kmNnjRxJLOktxn9Y7jrJ1ENHimRBzcZqpepZyv/5S', utc_timestamp);
insert into tb_usuario (id, nome, email, senha, data_cadastro) values (5, 'Angela W', 'angela@email.com', '$2a$12$t3bjL2pvIG7/kmNnjRxJLOktxn9Y7jrJ1ENHimRBzcZqpepZyv/5S', utc_timestamp);
insert into tb_usuario (id, nome, email, senha, data_cadastro) values (6, 'Lana R', 'lana@email.com', '$2a$12$t3bjL2pvIG7/kmNnjRxJLOktxn9Y7jrJ1ENHimRBzcZqpepZyv/5S', utc_timestamp);

insert into tb_permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', '(NÃO VAI SER USADO) Permite consultar cozinhas');
insert into tb_permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar ou criar cozinhas');
insert into tb_permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMAS_PAGAMENTO', '(NÃO VAI SER USADO) Permite consultar formas de pagamento');
insert into tb_permissao (id, nome, descricao) values (4, 'EDITAR_FORMAS_PAGAMENTO', 'Permite editar ou criar formas de pagamento');
insert into tb_permissao (id, nome, descricao) values (5, 'CONSULTAR_CIDADES', '(NÃO VAI SER USADO) Permite consultar cidades');
insert into tb_permissao (id, nome, descricao) values (6, 'EDITAR_CIDADES', 'Permite editar ou criar cidades');
insert into tb_permissao (id, nome, descricao) values (7, 'CONSULTAR_ESTADOS', '(NÃO VAI SER USADO) Permite consultar estados');
insert into tb_permissao (id, nome, descricao) values (8, 'EDITAR_ESTADOS', 'Permite editar ou criar estados');
insert into tb_permissao (id, nome, descricao) values (9, 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões');
insert into tb_permissao (id, nome, descricao) values (10, 'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite editar ou criar usuários, grupos ou permissões');
insert into tb_permissao (id, nome, descricao) values (11, 'CONSULTAR_RESTAURANTES', '(NÃO VAI SER USADO) Permite consultar restaurantes');
insert into tb_permissao (id, nome, descricao) values (12, 'EDITAR_RESTAURANTES', 'Permite editar, criar ou gerenciar restaurantes');
insert into tb_permissao (id, nome, descricao) values (13, 'CONSULTAR_PRODUTOS', '(NÃO VAI SER USADO) Permite consultar produtos');
insert into tb_permissao (id, nome, descricao) values (14, 'EDITAR_PRODUTOS', '(NÃO VAI SER USADO) Permite editar ou criar produtos');
insert into tb_permissao (id, nome, descricao) values (15, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into tb_permissao (id, nome, descricao) values (16, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into tb_permissao (id, nome, descricao) values (17, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into tb_grupo (id, nome) values (1, 'Gerente');
insert into tb_grupo (id, nome) values (2, 'Vendedor');
insert into tb_grupo (id, nome) values (3, 'Secretária');
insert into tb_grupo (id, nome) values (4, 'Cadastrador');


-- Adiciona todas as permissoes no grupo do gerente
insert into tb_grupo_permissao (grupo_id, permissao_id) select 1, id
 from tb_permissao;

-- Adiciona permissoes no grupo do vendedor de consultar qualquer coisa e editar produto
insert into tb_grupo_permissao (grupo_id, permissao_id) select 2, id
 from tb_permissao where nome like 'CONSULTAR_%';

insert into tb_grupo_permissao (grupo_id, permissao_id) values (2, 14);

-- Adiciona permissoes no grupo do auxiliar para apenas consultar
insert into tb_grupo_permissao (grupo_id, permissao_id) select 3, id
 from tb_permissao
 where nome like 'CONSULTAR_%';

-- Adiciona permissoes no grupo do cadastrador para fazer coisa relacionado a restaurantes e produtos
insert into tb_grupo_permissao (grupo_id, permissao_id) select 4, id
 from tb_permissao
 where nome like '%_RESTAURANTES' or nome like '%_PRODUTOS';


insert into tb_usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2), (3, 3), (4, 4);

insert into tb_restaurante_usuario (restaurante_id, usuario_id) values (1, 5), (3, 5);

-- Pedido 1
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, 'ee13f455-c207-4be6-8eab-6c610567a9ef', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'ENTREGUE', '2024-09-05 14:04:11', 298.90, 10, 308.90);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

-- Pedido 2
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (2, '6e85fcab-a426-4436-9837-f15af54c7737', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'ENTREGUE', '2024-09-04 14:04:11', 79, 0, 79);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

-- Pedido 3
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (3, 'abc123de-567f-890a-bc12-345def6789gh', 2, 2, 3, 2, '38400-222', 'Rua Goiás', '1500', 'Casa 3', 'Jardim', 'CONFIRMADO', '2024-09-03 14:04:11', 150, 15, 165);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 3, 3, 1, 150, 150, 'Sem cebola');

-- Pedido 4
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (4, 'ijkl456m-789n-012o-pq34-567rst890uvw', 3, 3, 1, 3, '38400-333', 'Avenida Brasil', '250', 'Apto 502', 'Centro', 'CRIADO', '2024-09-17 14:04:11', 200, 20, 220);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 4, 2, 100, 200, 'Extra queijo');

-- Pedido 5
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (5, 'mnop789q-123r-456s-tuvw-890xyz012abc', 5, 4, 4, 2, '38400-444', 'Rua São Paulo', '400', 'Casa 10', 'Bela Vista', 'CONFIRMADO', '2024-09-16 14:04:11', 99.9, 5, 104.9);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (6, 5, 5, 1, 99.9, 99.9, 'Com molho extra');


-- Pedido 6
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (6, 'e2c8b504-18d2-45eb-b120-5561202c4583', 1, 5, 1, 3, '38400-555', 'Rua Amazonas', '600', 'Apto 101', 'Centro', 'ENTREGUE', '2024-09-18 10:30:00', 120, 10, 130);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (7, 6, 1, 2, 60, 120, 'Sem salada');


-- Pedido 7
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (7, '6b6bc467-eaf6-4707-be97-2be4229bcdde', 2, 3, 2, 2, '38400-666', 'Rua Bahia', '700', 'Casa 5', 'Jardim', 'ENTREGUE', '2024-09-19 12:45:00', 250, 15, 265);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (8, 7, 2, 1, 100, 100, 'Com molho extra');
insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (9, 7, 3, 1, 150, 150, 'Sem cebola');


-- Pedido 8
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (8, 'c9b784fe-8c2b-4b8b-85c1-d2358dab5926', 3, 2, 3, 1, '38400-777', 'Rua Ceará', '800', 'Casa 8', 'Centro', 'CRIADO', '2024-09-03 14:00:00', 180, 20, 200);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (10, 8, 4, 2, 90, 180, 'Extra queijo');


-- Pedido 9
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (9, 'c2cb1ccc-9275-4aa5-834d-3b885cb1dfa1', 4, 4, 4, 2, '38400-888', 'Rua Espírito Santo', '900', 'Apto 202', 'Bela Vista', 'ENTREGUE', '2024-09-02 16:15:00', 99.9, 5, 104.9);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (11, 9, 5, 1, 99.9, 99.9, 'Com molho extra');


-- Pedido 10
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (10, '4b98c720-768e-4696-8a82-58599ced7ef2', 5, 1, 1, 3, '38400-999', 'Rua Pernambuco', '1000', 'Casa 12', 'Centro', 'ENTREGUE', '2024-09-01 18:30:00', 220, 10, 230);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (12, 10, 6, 2, 110, 220, 'Menos picante, por favor');

-- >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

-- Pedido 11 -> PEDIDO PARA TRABALHAR COM TRATAMENTO DE TIMEZONE OFFSET NA AGREGACAO DE VENDAS DIASRIAS POR DATA
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (11, 'd0b0a12b-a8e5-4890-889b-f704a54dedc0', 1, 2, 2, 1, '38400-111', 'Rua Pará', '1100', 'Apto 303', 'Centro', 'CONFIRMADO', '2024-09-05 02:00:00', 150, 10, 160);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (13, 11, 1, 1, 50, 50, 'Sem cebola');
insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (14, 11, 2, 1, 100, 100, 'Extra molho');

-- Pedido 12
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (12, 'aa24cfe3-a6f6-43b2-93f5-c38db3c811aa', 2, 3, 3, 2, '38400-222', 'Rua Maranhão', '1200', 'Casa 15', 'Jardim', 'CRIADO', '2024-09-04 11:15:00', 200, 15, 215);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (15, 12, 3, 2, 100, 200, 'Sem pimenta');

-- Pedido 13
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (13, '626176be-ea00-4698-86ea-e8ed26ee6486', 3, 4, 4, 3, '38400-333', 'Rua Pernambuco', '1300', 'Apto 404', 'Centro', 'CONFIRMADO', '2024-09-03 13:30:00', 180, 20, 200);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (16, 13, 4, 1, 90, 90, 'Extra queijo');
insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (17, 13, 5, 1, 90, 90, 'Com molho extra');

-- Pedido 14
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (14, '1ebb7c9c-f5b7-4107-ac4d-5d7e3ead2caf', 4, 5, 1, 1, '38400-444', 'Rua Rio de Janeiro', '1400', 'Casa 20', 'Bela Vista', 'CONFIRMADO', '2024-09-02 15:45:00', 220, 10, 230);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (18, 14, 6, 2, 110, 220, 'Menos picante, por favor');

-- Pedido 15
insert into tb_pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (15, '73c02f6b-b4f5-4f05-baad-04892e568c56', 5, 1, 2, 2, '38400-555', 'Rua São Paulo', '1500', 'Casa 25', 'Centro', 'CRIADO', '2024-09-01 18:00:00', 99.9, 5, 104.9);

insert into tb_item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (19, 15, 5, 1, 99.9, 99.9, 'Com molho extra');



-- INSERCOES DOS CLIENTES OAuth2
--  client_id -> O identificador exclusivo do cliente.
--  resources_ids -> Os recursos que o cliente pode acessar (esse projeto nao tem suporte)
--  client_secret -> a senha criptografada com Bcrypt do cliente
--  scope -> O escopo de acesso concedido ao cliente, definindo os recursos e operações permitidos( por exemlo: READ WIRTE)
--  authorized_grant_types > Tipos de concessão autorizados para o cliente, como "authorization_code", "password", "client_credentials", etc.
--  web_server_redirect_uri -> O URI de redirecionamento do servidor web para o cliente após a autenticação(usado no "authorization_code")
--  authorities -> As autoridades (permissões) concedidas ao cliente.
--  access_token_validity > A validade do token de acesso, em segundos.
--  refresh_token_validity -> A validade do token de atualização (refresh token), em segundos.
--  autoapprove -> Indica se a solicitação de autorização do cliente será aprovada automaticamente (true) ou não (false). (usado no "authorization_code")

insert into oauth_client_details (
    client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri,
    authorities, access_token_validity, refresh_token_validity, autoapprove
)

values (
    'algafood-web', null, '$2a$12$zeKzJh2G/Q5qD5PnCj.7L.nqpBLDmFsTndaNWk2d3Aa.XbSyNZpvK', 'READ,WRITE', 'password', null,
    null, 60 * 60 * 4, 60 * 60 * 24, null
);

insert into oauth_client_details (
    client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri,
    authorities, access_token_validity, refresh_token_validity, autoapprove
)

values (
    'foodanalytics', null, '$2a$12$zeKzJh2G/Q5qD5PnCj.7L.nqpBLDmFsTndaNWk2d3Aa.XbSyNZpvK', 'READ,WRITE', 'authorization_code', 'http://www.foodanalytics.local:8082',
    null, 60 * 60 * 4, 60 * 60 * 24, null
);

insert into oauth_client_details (
    client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri,
    authorities, access_token_validity, refresh_token_validity, autoapprove
)

values (
    'faturamento', null, '$2a$12$zeKzJh2G/Q5qD5PnCj.7L.nqpBLDmFsTndaNWk2d3Aa.XbSyNZpvK', 'READ,WRITE', 'client_credentials', null,
   'CONSULTAR_PEDIDOS, GERAR_RELATORIOS', 60 * 60 * 4, 60 * 60 * 24, null
);


-- INSERCOES DOS CLIENTES OAuth2 para o novo Spring Security
--INSERT INTO oauth2_registered_client
--(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
--VALUES('1', 'algafood-web-client-credentials-token-opaco', '2025-04-28 14:16:47', '$2a$10$gKcOBFyaoaP/iQlvgPvAj./uTV1QgoIcxQmSdcH0mCN/LjU3J6wh.', NULL, 'algafood web client credentials token opaco', 'client_secret_basic', 'client_credentials', '', 'READ', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"reference"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}');
--
--INSERT INTO oauth2_registered_client
--(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
--VALUES('2', 'algafood-web-client-credentials-token-jwt', '2025-04-28 14:16:47', '$2a$10$soRCbW9qh4nakdNIfAbI6.kbdewdBvSrKQs0xkFabZ93i2outdLoW', NULL, 'algafood web client credentials token jwt', 'client_secret_basic', 'client_credentials', '', 'READ', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000]}');
--
--INSERT INTO oauth2_registered_client
--(id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, scopes, client_settings, token_settings)
--VALUES('3', 'algafood-web-authorization-code-token-jwt', '2025-04-28 14:16:47', '$2a$10$vFoUIgaNih7FEq6LzKMxwu5uZEMUNu2qCTJ.PIvdM8nRGczYfPh4G', NULL, 'algafood web authorization code token jwt', 'client_secret_basic', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html,http://127.0.0.1:8080/authorizated', 'READ,WRITE', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":false,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.core.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",86400.000000000]}');



-- Comando utilizado para liberar os bloqueios aplicados anteriormente as tabelas em uma sessao do banco de dados.
unlock tables;




















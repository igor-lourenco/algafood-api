
create table tb_forma_pagamento (
    id bigint not null auto_increment,
    descricao varchar(255),
    primary key (id)
) engine=InnoDB charset=utf8;

create table tb_grupo (
    id bigint not null auto_increment,
    nome varchar(255),
    usuario_id bigint,
    primary key (id)
) engine=InnoDB charset=utf8;

create table tb_grupo_permissao (
    grupo_id bigint not null,
    permissao_id bigint not null
) engine=InnoDB charset=utf8;

create table tb_permissao (
    id bigint not null auto_increment,
    descricao varchar(255),
    nome varchar(255),
    primary key (id)
) engine=InnoDB charset=utf8;

create table tb_produto (
    id bigint not null auto_increment,
    ativo bit not null,
    descricao varchar(255) not null,
    nome varchar(255) not null,
    preco decimal(19,2) not null,
    restaurante_id bigint not null,
    primary key (id)
) engine=InnoDB charset=utf8;

create table tb_restaurante (
    id bigint not null auto_increment,
    data_atualizacao datetime not null,
    data_cadastro datetime not null,
    endereco_bairro varchar(255),
    endereco_cep varchar(255),
    endereco_complemento varchar(255),
    endereco_logradouro varchar(255),
    endereco_numero varchar(255),
    nome varchar(255),
    taxa_frete decimal(19,2),
    cozinha_id bigint,
    endereco_cidade_id bigint,
    primary key (id)
) engine=InnoDB charset=utf8;

create table tb_restaurante_forma_pagamento (
    restaurante_id bigint not null,
    forma_pagamento_id bigint not null
) engine=InnoDB charset=utf8;

create table tb_usuario (
    id bigint not null auto_increment,
    data_cadastro datetime not null,
    email varchar(255),
    nome varchar(255),
    senha varchar(255),
    primary key (id)
) engine=InnoDB charset=utf8;


create table tb_restaurante_usuario (
    restaurante_id bigint not null,
    usuario_id bigint not null
) engine=InnoDB charset=utf8;


ALTER TABLE tb_restaurante_usuario add constraint
fk_restaurante_usuario foreign key (restaurante_id) references tb_restaurante (id);

ALTER TABLE tb_restaurante_usuario add constraint
fk_usuario_restaurante foreign key (usuario_id) references tb_usuario (id);
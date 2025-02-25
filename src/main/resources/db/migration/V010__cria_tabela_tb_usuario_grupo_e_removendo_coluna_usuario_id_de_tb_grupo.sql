
create table tb_usuario_grupo (
    usuario_id bigint not null,
    grupo_id bigint not null
) engine=InnoDB charset=utf8;


ALTER TABLE tb_usuario_grupo add constraint fk_grupo_usuario_id foreign key (usuario_id) references tb_usuario (id);

ALTER TABLE tb_usuario_grupo add constraint fk_usuario_grupo_id foreign key (grupo_id) references tb_grupo (id);

ALTER TABLE tb_grupo DROP FOREIGN KEY fk_grupo_usuario;
ALTER TABLE tb_grupo DROP COLUMN usuario_id;
alter table tb_grupo add constraint fk_grupo_usuario foreign key (usuario_id) references tb_usuario (id);
alter table tb_grupo_permissao add constraint fk_grupo_permissao foreign key (permissao_id) references tb_permissao (id);
alter table tb_grupo_permissao add constraint fk_permissao_grupo foreign key (grupo_id) references tb_grupo (id);
alter table tb_produto add constraint fk_produto_restaurante foreign key (restaurante_id) references tb_restaurante (id);
alter table tb_restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references tb_cozinha (id);
alter table tb_restaurante add constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references tb_cidade (id);
alter table tb_restaurante_forma_pagamento add constraint fk_restaurante_forma_pagamento foreign key (forma_pagamento_id) references tb_forma_pagamento (id);
alter table tb_restaurante_forma_pagamento add constraint fk_forma_pagamento_restaurante foreign key (restaurante_id) references tb_restaurante (id);
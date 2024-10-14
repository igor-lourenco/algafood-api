ALTER TABLE tb_forma_pagamento ADD data_atualizacao datetime null;
UPDATE tb_forma_pagamento SET data_atualizacao = utc_timestamp;
ALTER TABLE tb_forma_pagamento MODIFY data_atualizacao datetime NOT NULL;
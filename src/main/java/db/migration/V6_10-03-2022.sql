-- CRIADO PARA GERAR A CHAVE ESTRAGEIRA DA EMPRESA 
-- PARA EXECUÇÃO IMPORTANTE RODAR QUERY POR QUERY DE FORMA INDIVIDUAL
-- FOI NECESSARIO REALIZAR DELETE NAS TABELAS ABAIXO PARA REMOVER DADOS QUE IMPEDIA A CRIAÇÃO DO NOVO CAMPO EMPRESA_ID

-- delete from tb_usuario
-- delete from tb_pessoa_fisica
-- delete from tb_role_acesso

-- alter  table tb_avaliacao_produto add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_categoria_produto add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_conta_pagar add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_conta_receber add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_cupom_desconto add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_endereco add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_forma_pagamento add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_imagem_produto add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_item_venda_loja add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_marca_produto add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_nota_fiscal_compra add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_nota_fiscal_venda add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_nota_item_produto add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_pessoa_fisica add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_pessoa_juridica add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_produto add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_status_rastreio add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_usuario add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)
-- alter  table tb_venda_compra_loja add constraint empresa_fk FOREIGN Key (empresa_id) references tb_pessoa_juridica (psa_id)

-- CRIACAO DA CHAVE UNIQUE NO LOGIN
--alter table tb_usuario add constraint login_unique unique(usu_login)

--CRIADA PARA QUE O CAMPO EMPRESA_ID SEJA NULO PARA PESSOA_JURIDICA
--ALTER TABLE public.tb_pessoa_juridica ADD COLUMN empresa_id bigint NULL;
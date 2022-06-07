--Tabela destinada para criação e verificaçao de acessos aos endpoint da API
CREATE TABLE public.tb_acesso_endpoint
(
    nome_endpoint character varying COLLATE pg_catalog."default",
    qtd_acesso_endpoint integer
);

INSERT INTO public.tb_acesso_endpoint(
	nome_endpoint, qtd_acesso_endpoint)
VALUES ('END-POINT-NOME-PESSOA-FISICA', 0);

alter table tb_acesso_endpoint add constraint nome_endpoint_unique UNIQUE (nome_endpoint);
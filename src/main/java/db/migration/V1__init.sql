--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.25
-- Dumped by pg_dump version 12.5

-- Started on 2022-02-01 18:28:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2345 (class 1262 OID 16393)
-- Name: db_loja_virtual; Type: DATABASE; Schema: -; Owner: postgres
--

--CREATE DATABASE db_loja_virtual WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';


ALTER DATABASE db_loja_virtual OWNER TO postgres;

--\connect db_loja_virtual

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 222 (class 1255 OID 16957)
-- Name: validachavepessoa(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE EXISTE INTEGER;

BEGIN
		existe = (SELECT COUNT(1) FROM tb_pessoa_fisica WHERE psa_id = NEW.pessoa_id);
	if(existe <= 0) then
		existe = (SELECT COUNT(1) FROM tb_pessoa_juridica WHERE psa_id = NEW.pessoa_id);
	if(existe <= 0) then
		raise exception 'Não foi encontrado o ID ou PK da pessoa para realizar associação';
	end if;
	end if;
RETURN NEW;
END;
$$;


ALTER FUNCTION public.validachavepessoa() OWNER TO postgres;

--
-- TOC entry 221 (class 1255 OID 16958)
-- Name: validachavepessoafornecedor(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoafornecedor() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE EXISTE INTEGER;

BEGIN
		existe = (SELECT COUNT(1) FROM tb_pessoa_fisica WHERE psa_id = NEW.pessoa_fornecedor_id);
	if(existe <= 0) then
		existe = (SELECT COUNT(1) FROM tb_pessoa_juridica WHERE psa_id = NEW.pessoa_fornecedor_id);
	if(existe <= 0) then
		raise exception 'Não foi encontrado o ID ou PK da pessoa fornecedor para realizar associação';
	end if;
	end if;
RETURN NEW;
END;
$$;


ALTER FUNCTION public.validachavepessoafornecedor() OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16685)
-- Name: seq_avaliacao_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_avaliacao_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_avaliacao_produto OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 16534)
-- Name: seq_conta_pagar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_pagar OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 16517)
-- Name: seq_conta_receber; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_receber
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_receber OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 16541)
-- Name: seq_cupom_desconto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cupom_desconto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cupom_desconto OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 16524)
-- Name: seq_forma_pagamento; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_forma_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_forma_pagamento OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 16561)
-- Name: seq_imagem_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_imagem_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_imagem_produto OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16668)
-- Name: seq_item_venda_loja; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_item_venda_loja
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_item_venda_loja OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 16577)
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16619)
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16589)
-- Name: seq_nota_item_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_item_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_item_produto OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 16462)
-- Name: seq_pessoa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pessoa OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 16551)
-- Name: seq_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_produto OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 16433)
-- Name: seq_role_acesso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_role_acesso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_role_acesso OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16609)
-- Name: seq_status_rastreio; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_status_rastreio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_status_rastreio OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16426)
-- Name: seq_tb_categoria_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_tb_categoria_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_tb_categoria_produto OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16473)
-- Name: seq_tb_endereco; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_tb_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_tb_endereco OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 16411)
-- Name: seq_tb_marca_prod; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_tb_marca_prod
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_tb_marca_prod OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16495)
-- Name: seq_tb_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_tb_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_tb_usuario OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16626)
-- Name: seq_venda_compra_loja; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_venda_compra_loja
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_venda_compra_loja OWNER TO postgres;

SET default_tablespace = '';

--
-- TOC entry 203 (class 1259 OID 16680)
-- Name: tb_avaliacao_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_avaliacao_produto (
    avp_id bigint NOT NULL,
    pessoa_id bigint NOT NULL,
    produto_id bigint NOT NULL,
    avp_nota integer NOT NULL,
    avp_desc character varying(255) NOT NULL
);


ALTER TABLE public.tb_avaliacao_produto OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16692)
-- Name: tb_categoria_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_categoria_produto (
    ctp_id bigint NOT NULL,
    ctp_desc character varying(255) NOT NULL
);


ALTER TABLE public.tb_categoria_produto OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16697)
-- Name: tb_conta_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_conta_pagar (
    ctp_id bigint NOT NULL,
    ctp_desc character varying(255) NOT NULL,
    ctp_data_pagamento date,
    ctp_data_vencimento date NOT NULL,
    ctr_status character varying(255),
    ctp_vlr_desconto numeric(19,2),
    ctp_vlr_total numeric(19,2) NOT NULL,
    pessoa_id bigint NOT NULL,
    pessoa_fornecedor_id bigint NOT NULL
);


ALTER TABLE public.tb_conta_pagar OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16710)
-- Name: tb_conta_receber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_conta_receber (
    ctr_id bigint NOT NULL,
    ctr_desc character varying(255) NOT NULL,
    ctr_data_pagamento date,
    ctr_data_vencimento date NOT NULL,
    ctr_status character varying(255) NOT NULL,
    ctr_vlr_desconto numeric(19,2),
    ctr_vlr_total numeric(19,2) NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.tb_conta_receber OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16719)
-- Name: tb_cupom_desconto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_cupom_desconto (
    cpd_id bigint NOT NULL,
    cpd_codigo_desc character varying(255) NOT NULL,
    cpd_data_valid_cupom date NOT NULL,
    cpd_vlr_porcent_desconto numeric(19,2),
    cpd_vlr_real_desconto numeric(19,2)
);


ALTER TABLE public.tb_cupom_desconto OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16729)
-- Name: tb_endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_endereco (
    end_id bigint NOT NULL,
    end_bairro character varying(255) NOT NULL,
    end_cep character varying(255) NOT NULL,
    end_cidade character varying(255) NOT NULL,
    end_complemento character varying(255),
    end_logradouro character varying(255),
    end_numero character varying(255) NOT NULL,
    end_rua character varying(255) NOT NULL,
    end_tipo_enderco character varying(255) NOT NULL,
    end_uf character varying(255) NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.tb_endereco OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16747)
-- Name: tb_forma_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_forma_pagamento (
    fpg_id bigint NOT NULL,
    fpg_desc character varying(255) NOT NULL
);


ALTER TABLE public.tb_forma_pagamento OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16757)
-- Name: tb_imagem_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_imagem_produto (
    imp_id bigint NOT NULL,
    imp_img_miniatura text NOT NULL,
    imp_img_original text NOT NULL,
    produto_id bigint NOT NULL
);


ALTER TABLE public.tb_imagem_produto OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16770)
-- Name: tb_item_venda_loja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_item_venda_loja (
    ivl_id bigint NOT NULL,
    ivl_quantidade double precision NOT NULL,
    produto_id bigint NOT NULL,
    venda_compra_loja_id bigint NOT NULL
);


ALTER TABLE public.tb_item_venda_loja OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16785)
-- Name: tb_marca_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_marca_produto (
    mrp_id bigint NOT NULL,
    mrp_desc character varying(255) NOT NULL
);


ALTER TABLE public.tb_marca_produto OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16790)
-- Name: tb_nota_fiscal_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_nota_fiscal_compra (
    nfc_id bigint NOT NULL,
    nfc_data_compra date NOT NULL,
    nfc_desc_observ character varying(255),
    nfc_numero_nota character varying(255) NOT NULL,
    nfc_serie_nota character varying(255) NOT NULL,
    nfc_vlr_desconto numeric(19,2),
    nfc_vlr_icms numeric(19,2) NOT NULL,
    nfc_vlr_total numeric(19,2) NOT NULL,
    conta_pagar_id bigint NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.tb_nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16808)
-- Name: tb_nota_fiscal_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_nota_fiscal_venda (
    nfv_id bigint NOT NULL,
    nfv_numero character varying(255) NOT NULL,
    nfv_pdf text NOT NULL,
    nfv_serie character varying(255) NOT NULL,
    nfv_tipo character varying(255) NOT NULL,
    nfv_xml text NOT NULL,
    venda_compra_loja_id bigint NOT NULL
);


ALTER TABLE public.tb_nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16584)
-- Name: tb_nota_item_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_nota_item_produto (
    nip_id bigint NOT NULL,
    nip_quantidade double precision NOT NULL,
    nota_fiscal_compra_id bigint NOT NULL,
    produto_id bigint NOT NULL
);


ALTER TABLE public.tb_nota_item_produto OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16840)
-- Name: tb_pessoa_fisica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_pessoa_fisica (
    psa_id bigint NOT NULL,
    psa_email character varying(255) NOT NULL,
    psa_nome character varying(255) NOT NULL,
    psa_telefone character varying(255) NOT NULL,
    psf_cpf character varying(255) NOT NULL,
    psf_data_nascimento date
);


ALTER TABLE public.tb_pessoa_fisica OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16856)
-- Name: tb_pessoa_juridica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_pessoa_juridica (
    psa_id bigint NOT NULL,
    psa_email character varying(255) NOT NULL,
    psa_nome character varying(255) NOT NULL,
    psa_telefone character varying(255) NOT NULL,
    psj_categoria character varying(255),
    psj_cnpj character varying(255) NOT NULL,
    psj_insc_estadual character varying(255) NOT NULL,
    psj_insc_municipal character varying(255),
    psj_nome_fantasia character varying(255) NOT NULL,
    psj_razao_social character varying(255) NOT NULL
);


ALTER TABLE public.tb_pessoa_juridica OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16864)
-- Name: tb_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_produto (
    prod_id bigint NOT NULL,
    prod_alert_qtd_estoque boolean,
    prod_altura double precision NOT NULL,
    prod_ativo boolean NOT NULL,
    prod_desc text NOT NULL,
    prod_largura double precision NOT NULL,
    prod_link_video character varying(255),
    prod_nome character varying(255) NOT NULL,
    prod_peso double precision NOT NULL,
    prod_profundidad double precision NOT NULL,
    prod_qtd_alert_estoque integer,
    prod_qtd_clique integer,
    prod_qtd_estoque integer NOT NULL,
    prod_tipo_unidade character varying(255) NOT NULL,
    prod_vlr_venda numeric(19,2) NOT NULL
);


ALTER TABLE public.tb_produto OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 16475)
-- Name: tb_role_acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_role_acesso (
    rla_id bigint NOT NULL,
    rla_desc character varying(255) NOT NULL
);


ALTER TABLE public.tb_role_acesso OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16601)
-- Name: tb_status_rastreio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_status_rastreio (
    str_id bigint NOT NULL,
    str_centro_distrib character varying(255),
    str_cidade character varying(255),
    str_estado character varying(255),
    str_status character varying(255),
    venda_compra_loja_id bigint NOT NULL
);


ALTER TABLE public.tb_status_rastreio OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16892)
-- Name: tb_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_usuario (
    usu_id bigint NOT NULL,
    usu_data_atual_password date NOT NULL,
    usu_login character varying(255) NOT NULL,
    usu_password character varying(255) NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.tb_usuario OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 16488)
-- Name: tb_usuario_role_acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_usuario_role_acesso (
    usuario_id bigint NOT NULL,
    role_acesso_id bigint NOT NULL
);


ALTER TABLE public.tb_usuario_role_acesso OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16900)
-- Name: tb_venda_compra_loja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tb_venda_compra_loja (
    vcl_id bigint NOT NULL,
    vcl_data_entrega date NOT NULL,
    vcl_data_venda date NOT NULL,
    vcl_dia_entrega integer NOT NULL,
    vcl_vlr_desc numeric(19,2),
    vcl_vlr_frete numeric(19,2) NOT NULL,
    vcl_vlr_total numeric(19,2) NOT NULL,
    cupom_desconto_id bigint,
    endereco_cobranca_id bigint NOT NULL,
    endereco_entrega_id bigint NOT NULL,
    forma_pagamento_id bigint NOT NULL,
    nota_fiscal_venda_id bigint NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.tb_venda_compra_loja OWNER TO postgres;

--
-- TOC entry 2322 (class 0 OID 16680)
-- Dependencies: 203
-- Data for Name: tb_avaliacao_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2324 (class 0 OID 16692)
-- Dependencies: 205
-- Data for Name: tb_categoria_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2325 (class 0 OID 16697)
-- Dependencies: 206
-- Data for Name: tb_conta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2326 (class 0 OID 16710)
-- Dependencies: 207
-- Data for Name: tb_conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2327 (class 0 OID 16719)
-- Dependencies: 208
-- Data for Name: tb_cupom_desconto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2328 (class 0 OID 16729)
-- Dependencies: 209
-- Data for Name: tb_endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2329 (class 0 OID 16747)
-- Dependencies: 210
-- Data for Name: tb_forma_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2330 (class 0 OID 16757)
-- Dependencies: 211
-- Data for Name: tb_imagem_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2331 (class 0 OID 16770)
-- Dependencies: 212
-- Data for Name: tb_item_venda_loja; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2332 (class 0 OID 16785)
-- Dependencies: 213
-- Data for Name: tb_marca_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2333 (class 0 OID 16790)
-- Dependencies: 214
-- Data for Name: tb_nota_fiscal_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2334 (class 0 OID 16808)
-- Dependencies: 215
-- Data for Name: tb_nota_fiscal_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2315 (class 0 OID 16584)
-- Dependencies: 196
-- Data for Name: tb_nota_item_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2335 (class 0 OID 16840)
-- Dependencies: 216
-- Data for Name: tb_pessoa_fisica; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tb_pessoa_fisica VALUES (1, 'pessoaFisica@teste', 'pessoa fisica', '95852658', '4568898', '2021-10-01');


--
-- TOC entry 2336 (class 0 OID 16856)
-- Dependencies: 217
-- Data for Name: tb_pessoa_juridica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2337 (class 0 OID 16864)
-- Dependencies: 218
-- Data for Name: tb_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2305 (class 0 OID 16475)
-- Dependencies: 186
-- Data for Name: tb_role_acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2317 (class 0 OID 16601)
-- Dependencies: 198
-- Data for Name: tb_status_rastreio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2338 (class 0 OID 16892)
-- Dependencies: 219
-- Data for Name: tb_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2306 (class 0 OID 16488)
-- Dependencies: 187
-- Data for Name: tb_usuario_role_acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2339 (class 0 OID 16900)
-- Dependencies: 220
-- Data for Name: tb_venda_compra_loja; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2347 (class 0 OID 0)
-- Dependencies: 204
-- Name: seq_avaliacao_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_avaliacao_produto', 1, false);


--
-- TOC entry 2348 (class 0 OID 0)
-- Dependencies: 191
-- Name: seq_conta_pagar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_pagar', 1, false);


--
-- TOC entry 2349 (class 0 OID 0)
-- Dependencies: 189
-- Name: seq_conta_receber; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_receber', 1, false);


--
-- TOC entry 2350 (class 0 OID 0)
-- Dependencies: 192
-- Name: seq_cupom_desconto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cupom_desconto', 1, false);


--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 190
-- Name: seq_forma_pagamento; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_forma_pagamento', 1, false);


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 194
-- Name: seq_imagem_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_imagem_produto', 1, false);


--
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 202
-- Name: seq_item_venda_loja; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_item_venda_loja', 1, false);


--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 195
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_compra', 1, false);


--
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 200
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_venda', 1, false);


--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 197
-- Name: seq_nota_item_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_item_produto', 1, false);


--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 184
-- Name: seq_pessoa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pessoa', 1, false);


--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 193
-- Name: seq_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_produto', 1, false);


--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 183
-- Name: seq_role_acesso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_role_acesso', 1, false);


--
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 199
-- Name: seq_status_rastreio; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_status_rastreio', 1, false);


--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 182
-- Name: seq_tb_categoria_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_tb_categoria_produto', 1, false);


--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 185
-- Name: seq_tb_endereco; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_tb_endereco', 1, false);


--
-- TOC entry 2363 (class 0 OID 0)
-- Dependencies: 181
-- Name: seq_tb_marca_prod; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_tb_marca_prod', 1, false);


--
-- TOC entry 2364 (class 0 OID 0)
-- Dependencies: 188
-- Name: seq_tb_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_tb_usuario', 1, false);


--
-- TOC entry 2365 (class 0 OID 0)
-- Dependencies: 201
-- Name: seq_venda_compra_loja; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_venda_compra_loja', 1, false);


--
-- TOC entry 2121 (class 2606 OID 16684)
-- Name: tb_avaliacao_produto tb_avaliacao_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_avaliacao_produto
    ADD CONSTRAINT tb_avaliacao_produto_pkey PRIMARY KEY (avp_id);


--
-- TOC entry 2123 (class 2606 OID 16696)
-- Name: tb_categoria_produto tb_categoria_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_categoria_produto
    ADD CONSTRAINT tb_categoria_produto_pkey PRIMARY KEY (ctp_id);


--
-- TOC entry 2125 (class 2606 OID 16704)
-- Name: tb_conta_pagar tb_conta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_conta_pagar
    ADD CONSTRAINT tb_conta_pagar_pkey PRIMARY KEY (ctp_id);


--
-- TOC entry 2127 (class 2606 OID 16717)
-- Name: tb_conta_receber tb_conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_conta_receber
    ADD CONSTRAINT tb_conta_receber_pkey PRIMARY KEY (ctr_id);


--
-- TOC entry 2129 (class 2606 OID 16723)
-- Name: tb_cupom_desconto tb_cupom_desconto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_cupom_desconto
    ADD CONSTRAINT tb_cupom_desconto_pkey PRIMARY KEY (cpd_id);


--
-- TOC entry 2131 (class 2606 OID 16736)
-- Name: tb_endereco tb_endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_endereco
    ADD CONSTRAINT tb_endereco_pkey PRIMARY KEY (end_id);


--
-- TOC entry 2133 (class 2606 OID 16751)
-- Name: tb_forma_pagamento tb_forma_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_forma_pagamento
    ADD CONSTRAINT tb_forma_pagamento_pkey PRIMARY KEY (fpg_id);


--
-- TOC entry 2135 (class 2606 OID 16764)
-- Name: tb_imagem_produto tb_imagem_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_imagem_produto
    ADD CONSTRAINT tb_imagem_produto_pkey PRIMARY KEY (imp_id);


--
-- TOC entry 2137 (class 2606 OID 16774)
-- Name: tb_item_venda_loja tb_item_venda_loja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_item_venda_loja
    ADD CONSTRAINT tb_item_venda_loja_pkey PRIMARY KEY (ivl_id);


--
-- TOC entry 2139 (class 2606 OID 16789)
-- Name: tb_marca_produto tb_marca_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_marca_produto
    ADD CONSTRAINT tb_marca_produto_pkey PRIMARY KEY (mrp_id);


--
-- TOC entry 2141 (class 2606 OID 16797)
-- Name: tb_nota_fiscal_compra tb_nota_fiscal_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_nota_fiscal_compra
    ADD CONSTRAINT tb_nota_fiscal_compra_pkey PRIMARY KEY (nfc_id);


--
-- TOC entry 2143 (class 2606 OID 16815)
-- Name: tb_nota_fiscal_venda tb_nota_fiscal_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_nota_fiscal_venda
    ADD CONSTRAINT tb_nota_fiscal_venda_pkey PRIMARY KEY (nfv_id);


--
-- TOC entry 2117 (class 2606 OID 16588)
-- Name: tb_nota_item_produto tb_nota_item_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_nota_item_produto
    ADD CONSTRAINT tb_nota_item_produto_pkey PRIMARY KEY (nip_id);


--
-- TOC entry 2145 (class 2606 OID 16847)
-- Name: tb_pessoa_fisica tb_pessoa_fisica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_pessoa_fisica
    ADD CONSTRAINT tb_pessoa_fisica_pkey PRIMARY KEY (psa_id);


--
-- TOC entry 2147 (class 2606 OID 16863)
-- Name: tb_pessoa_juridica tb_pessoa_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_pessoa_juridica
    ADD CONSTRAINT tb_pessoa_juridica_pkey PRIMARY KEY (psa_id);


--
-- TOC entry 2149 (class 2606 OID 16871)
-- Name: tb_produto tb_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_produto
    ADD CONSTRAINT tb_produto_pkey PRIMARY KEY (prod_id);


--
-- TOC entry 2111 (class 2606 OID 16479)
-- Name: tb_role_acesso tb_role_acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_role_acesso
    ADD CONSTRAINT tb_role_acesso_pkey PRIMARY KEY (rla_id);


--
-- TOC entry 2119 (class 2606 OID 16608)
-- Name: tb_status_rastreio tb_status_rastreio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_status_rastreio
    ADD CONSTRAINT tb_status_rastreio_pkey PRIMARY KEY (str_id);


--
-- TOC entry 2151 (class 2606 OID 16899)
-- Name: tb_usuario tb_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_usuario
    ADD CONSTRAINT tb_usuario_pkey PRIMARY KEY (usu_id);


--
-- TOC entry 2153 (class 2606 OID 16904)
-- Name: tb_venda_compra_loja tb_venda_compra_loja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_venda_compra_loja
    ADD CONSTRAINT tb_venda_compra_loja_pkey PRIMARY KEY (vcl_id);


--
-- TOC entry 2113 (class 2606 OID 16516)
-- Name: tb_usuario_role_acesso uk_7ce0jqk2sh64vdemu5erj0tai; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_usuario_role_acesso
    ADD CONSTRAINT uk_7ce0jqk2sh64vdemu5erj0tai UNIQUE (role_acesso_id);


--
-- TOC entry 2115 (class 2606 OID 16494)
-- Name: tb_usuario_role_acesso unique_acesso_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_usuario_role_acesso
    ADD CONSTRAINT unique_acesso_usuario UNIQUE (usuario_id, role_acesso_id);


--
-- TOC entry 2171 (class 2620 OID 16964)
-- Name: tb_avaliacao_produto validachavepessoa_avaliacao_produto_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_avaliacao_produto_insert BEFORE INSERT ON public.tb_avaliacao_produto FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2170 (class 2620 OID 16963)
-- Name: tb_avaliacao_produto validachavepessoa_avaliacao_produto_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_avaliacao_produto_update BEFORE UPDATE ON public.tb_avaliacao_produto FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2173 (class 2620 OID 16962)
-- Name: tb_conta_pagar validachavepessoa_conta_pagar_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_conta_pagar_insert BEFORE INSERT ON public.tb_conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2172 (class 2620 OID 16961)
-- Name: tb_conta_pagar validachavepessoa_conta_pagar_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_conta_pagar_update BEFORE UPDATE ON public.tb_conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2177 (class 2620 OID 16966)
-- Name: tb_conta_receber validachavepessoa_conta_receber_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_conta_receber_insert BEFORE INSERT ON public.tb_conta_receber FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2176 (class 2620 OID 16965)
-- Name: tb_conta_receber validachavepessoa_conta_receber_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_conta_receber_update BEFORE UPDATE ON public.tb_conta_receber FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2178 (class 2620 OID 16967)
-- Name: tb_endereco validachavepessoa_endereco_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_endereco_insert BEFORE INSERT ON public.tb_endereco FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2179 (class 2620 OID 16968)
-- Name: tb_endereco validachavepessoa_endereco_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_endereco_update BEFORE UPDATE ON public.tb_endereco FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2175 (class 2620 OID 16960)
-- Name: tb_conta_pagar validachavepessoa_fornecedor_conta_pagar_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_fornecedor_conta_pagar_insert BEFORE INSERT ON public.tb_conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoafornecedor();


--
-- TOC entry 2174 (class 2620 OID 16959)
-- Name: tb_conta_pagar validachavepessoa_fornecedor_conta_pagar_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_fornecedor_conta_pagar_update BEFORE UPDATE ON public.tb_conta_pagar FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoafornecedor();


--
-- TOC entry 2181 (class 2620 OID 16970)
-- Name: tb_nota_fiscal_compra validachavepessoa_nota_fiscal_compra_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_nota_fiscal_compra_insert BEFORE INSERT ON public.tb_nota_fiscal_compra FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2180 (class 2620 OID 16969)
-- Name: tb_nota_fiscal_compra validachavepessoa_nota_fiscal_compra_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_nota_fiscal_compra_update BEFORE UPDATE ON public.tb_nota_fiscal_compra FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2183 (class 2620 OID 16972)
-- Name: tb_usuario validachavepessoa_usuario_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_usuario_insert BEFORE INSERT ON public.tb_usuario FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2182 (class 2620 OID 16971)
-- Name: tb_usuario validachavepessoa_usuario_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_usuario_update BEFORE UPDATE ON public.tb_usuario FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2185 (class 2620 OID 16974)
-- Name: tb_venda_compra_loja validachavepessoa_venda_compra_loja_insert; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_venda_compra_loja_insert BEFORE INSERT ON public.tb_venda_compra_loja FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2184 (class 2620 OID 16973)
-- Name: tb_venda_compra_loja validachavepessoa_venda_compra_loja_update; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa_venda_compra_loja_update BEFORE UPDATE ON public.tb_venda_compra_loja FOR EACH ROW EXECUTE PROCEDURE public.validachavepessoa();


--
-- TOC entry 2163 (class 2606 OID 16798)
-- Name: tb_nota_fiscal_compra conta_pagar_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_nota_fiscal_compra
    ADD CONSTRAINT conta_pagar_fk FOREIGN KEY (conta_pagar_id) REFERENCES public.tb_conta_pagar(ctp_id);


--
-- TOC entry 2165 (class 2606 OID 16925)
-- Name: tb_venda_compra_loja cupom_desconto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_venda_compra_loja
    ADD CONSTRAINT cupom_desconto_fk FOREIGN KEY (cupom_desconto_id) REFERENCES public.tb_cupom_desconto(cpd_id);


--
-- TOC entry 2166 (class 2606 OID 16930)
-- Name: tb_venda_compra_loja endereco_cobranca_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_venda_compra_loja
    ADD CONSTRAINT endereco_cobranca_fk FOREIGN KEY (endereco_cobranca_id) REFERENCES public.tb_endereco(end_id);


--
-- TOC entry 2167 (class 2606 OID 16935)
-- Name: tb_venda_compra_loja endereco_entrega_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_venda_compra_loja
    ADD CONSTRAINT endereco_entrega_fk FOREIGN KEY (endereco_entrega_id) REFERENCES public.tb_endereco(end_id);


--
-- TOC entry 2168 (class 2606 OID 16940)
-- Name: tb_venda_compra_loja forma_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_venda_compra_loja
    ADD CONSTRAINT forma_pagamento_fk FOREIGN KEY (forma_pagamento_id) REFERENCES public.tb_forma_pagamento(fpg_id);


--
-- TOC entry 2156 (class 2606 OID 16803)
-- Name: tb_nota_item_produto nota_fiscal_compra_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_nota_item_produto
    ADD CONSTRAINT nota_fiscal_compra_fk FOREIGN KEY (nota_fiscal_compra_id) REFERENCES public.tb_nota_fiscal_compra(nfc_id);


--
-- TOC entry 2169 (class 2606 OID 16945)
-- Name: tb_venda_compra_loja nota_fiscal_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_venda_compra_loja
    ADD CONSTRAINT nota_fiscal_venda_fk FOREIGN KEY (nota_fiscal_venda_id) REFERENCES public.tb_nota_fiscal_venda(nfv_id);


--
-- TOC entry 2159 (class 2606 OID 16872)
-- Name: tb_avaliacao_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_avaliacao_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.tb_produto(prod_id);


--
-- TOC entry 2160 (class 2606 OID 16877)
-- Name: tb_imagem_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_imagem_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.tb_produto(prod_id);


--
-- TOC entry 2161 (class 2606 OID 16882)
-- Name: tb_item_venda_loja produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_item_venda_loja
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.tb_produto(prod_id);


--
-- TOC entry 2157 (class 2606 OID 16887)
-- Name: tb_nota_item_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_nota_item_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.tb_produto(prod_id);


--
-- TOC entry 2154 (class 2606 OID 16497)
-- Name: tb_usuario_role_acesso role_acesso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_usuario_role_acesso
    ADD CONSTRAINT role_acesso_fk FOREIGN KEY (role_acesso_id) REFERENCES public.tb_role_acesso(rla_id);


--
-- TOC entry 2155 (class 2606 OID 16920)
-- Name: tb_usuario_role_acesso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_usuario_role_acesso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.tb_usuario(usu_id);


--
-- TOC entry 2162 (class 2606 OID 16905)
-- Name: tb_item_venda_loja venda_compra_loja_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_item_venda_loja
    ADD CONSTRAINT venda_compra_loja_fk FOREIGN KEY (venda_compra_loja_id) REFERENCES public.tb_venda_compra_loja(vcl_id);


--
-- TOC entry 2164 (class 2606 OID 16910)
-- Name: tb_nota_fiscal_venda venda_compra_loja_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_nota_fiscal_venda
    ADD CONSTRAINT venda_compra_loja_fk FOREIGN KEY (venda_compra_loja_id) REFERENCES public.tb_venda_compra_loja(vcl_id);


--
-- TOC entry 2158 (class 2606 OID 16915)
-- Name: tb_status_rastreio venda_compra_loja_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tb_status_rastreio
    ADD CONSTRAINT venda_compra_loja_fk FOREIGN KEY (venda_compra_loja_id) REFERENCES public.tb_venda_compra_loja(vcl_id);


--
-- TOC entry 2346 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2022-02-01 18:28:51

--
-- PostgreSQL database dump complete
--


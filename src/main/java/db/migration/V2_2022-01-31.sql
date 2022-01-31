-- TRIGGER COLUMN PESSOA_ID
CREATE OR REPLACE FUNCTION validaChavePessoa()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
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
$$

CREATE TRIGGER validaChavePessoa_venda_compra_loja_update
BEFORE UPDATE
ON tb_venda_compra_loja
FOR EACH ROW
EXECUTE PROCEDURE validaChavePessoa();

CREATE TRIGGER validaChavePessoa_venda_compra_loja_insert
BEFORE INSERT
ON tb_venda_compra_loja
FOR EACH ROW
EXECUTE PROCEDURE validaChavePessoa();

-- TRIGGER PARA COLUMN PESSOA_FORNECEDOR_ID
CREATE OR REPLACE FUNCTION validaChavePessoaFornecedor()
	RETURNS TRIGGER
	LANGUAGE PLPGSQL
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
$$

CREATE TRIGGER validaChavePessoa_fornecedor_conta_pagar_update
BEFORE UPDATE
ON tb_conta_pagar
FOR EACH ROW
EXECUTE PROCEDURE validaChavePessoaFornecedor();

CREATE TRIGGER validaChavePessoa_fornecedor_conta_pagar_insert
BEFORE INSERT
ON tb_conta_pagar
FOR EACH ROW
EXECUTE PROCEDURE validaChavePessoaFornecedor();
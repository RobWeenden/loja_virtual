package br.com.rws.lojavirtual.loja_virtual_rws.enums;

public enum TipoPessoaEnum {
	
	JURIDICA("Jurídica"),
	JURIDICA_FORNECEDOR("fornecedor"),
	FISICA("Física");
	
	private String descricao;
	
	private TipoPessoaEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

}

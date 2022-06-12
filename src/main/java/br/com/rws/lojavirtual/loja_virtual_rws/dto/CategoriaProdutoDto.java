package br.com.rws.lojavirtual.loja_virtual_rws.dto;

import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaJuridicaModel;

public class CategoriaProdutoDto {

	private Long id;
	private String descricao;
	private PessoaJuridicaModel empresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PessoaJuridicaModel getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PessoaJuridicaModel empresa) {
		this.empresa = empresa;
	}

}

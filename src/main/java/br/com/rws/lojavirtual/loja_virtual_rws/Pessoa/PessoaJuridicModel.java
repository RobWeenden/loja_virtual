package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa_juridica")
@PrimaryKeyJoinColumn(name = "psa_id")
public class PessoaJuridicModel extends PessoaAbstract {

    @Column(name = "psj_cnpj")
    private String cnpj;

    @Column(name = "psj_insc_estadual")
    private String inscEstadual;

    @Column(name = "psj_insc_municipal")
    private String inscMunicipal;

    @Column(name = "psj_nome_fantasia")
    private String nomeFantasia;

    @Column(name = "psj_razao_social")
    private String razaoSocial;

    @Column(name = "psj_categoria")
    private String categoria;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public String getInscMunicipal() {
        return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
        this.inscMunicipal = inscMunicipal;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}

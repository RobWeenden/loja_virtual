package br.com.rws.lojavirtual.loja_virtual_rws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "tb_pessoa_fisica")
@PrimaryKeyJoinColumn(name = "psa_id")
public class PessoaFisicaModel extends PessoaAbstract {
	
	@CPF(message = "Cpf está inválido")
    @Column(name = "psf_cpf", nullable = false)
    private String cpf;

    @Column(name = "psf_data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

}

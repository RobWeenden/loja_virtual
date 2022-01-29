package br.com.rws.lojavirtual.loja_virtual_rws.ContaPagar;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaAbstract;

@Entity
@Table(name = "tb_conta_pagar")
@SequenceGenerator(name = "seq_conta_pagar", sequenceName = "seq_conta_pagar", allocationSize = 1, initialValue = 1)
public class ContaPagarModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
    @Column(name = "ctp_id")
    private Long id;

    @Column(name = "ctp_desc", nullable = false)
    private String descricao;

    @Column(name = "ctp_vlr_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "ctp_vlr_desconto")
    private BigDecimal valorDesconto;

    @Enumerated(EnumType.STRING)
    @Column(name = "ctr_status")
    private StatusContaPagarEnum statusContaPagar;

    @Temporal(TemporalType.DATE)
    @Column(name = "ctp_data_vencimento", nullable = false)
    private Date dtVencimento;

    @Temporal(TemporalType.DATE)
    @Column(name = "ctp_data_pagamento")
    private Date dtPagamento;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private PessoaAbstract pessoa;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "pessoa_fornecedor_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fornecedor_fk"))
    private PessoaAbstract pessoaFornecedor;

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

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public StatusContaPagarEnum getStatusContaPagar() {
        return statusContaPagar;
    }

    public void setStatusContaPagar(StatusContaPagarEnum statusContaPagar) {
        this.statusContaPagar = statusContaPagar;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public Date getDtPagamento() {
        return dtPagamento;
    }

    public void setDtPagamento(Date dtPagamento) {
        this.dtPagamento = dtPagamento;
    }

    public PessoaAbstract getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaAbstract pessoa) {
        this.pessoa = pessoa;
    }

    public PessoaAbstract getPessoaFornecedor() {
        return pessoaFornecedor;
    }

    public void setPessoaFornecedor(PessoaAbstract pessoaFornecedor) {
        this.pessoaFornecedor = pessoaFornecedor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContaPagarModel other = (ContaPagarModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

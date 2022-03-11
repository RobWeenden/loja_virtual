package br.com.rws.lojavirtual.loja_virtual_rws.VendaCompraLoja;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rws.lojavirtual.loja_virtual_rws.CupomDesconto.CupomDescontoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Endereco.EnderecoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.FormaPagamento.FormaPagamentoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.NotaFiscalVenda.NotaFiscalVendaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaAbstract;

@Entity
@Table(name = "tb_venda_compra_loja")
@SequenceGenerator(name = "seq_venda_compra_loja", sequenceName = "seq_venda_compra_loja", allocationSize = 1, initialValue = 1)
public class VendaCompraLojaModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venda_compra_loja")
    @Column(name = "vcl_id")
    private Long id;

    @Column(name = "vcl_vlr_total", nullable = false)
    private BigDecimal valorTotal;
    
    @Column(name = "vcl_vlr_desc")
    private BigDecimal valorDesconto;

    @Column(name = "vcl_vlr_frete", nullable = false)
    private BigDecimal valorFrete;

    @Column(name = "vcl_dia_entrega", nullable = false)
    private Integer diaEntrega;

    @Temporal(TemporalType.DATE)
    @Column(name = "vcl_data_venda", nullable = false)
    private Date dataVenda;

    @Temporal(TemporalType.DATE)
    @Column(name = "vcl_data_entrega", nullable = false)
    private Date dataEntrega;

    @ManyToOne
    @JoinColumn(name = "cupom_desconto_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "cupom_desconto_fk"))
    private CupomDescontoModel cupomDesconto;

    @OneToOne
    @JoinColumn(name = "nota_fiscal_venda_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "nota_fiscal_venda_fk"))
    private NotaFiscalVendaModel notaFiscalVenda;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "forma_pagamento_fk"))
    private FormaPagamentoModel formaPagamento;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private PessoaAbstract pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_entrega_fk"))
    private EnderecoModel enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "endereco_cobranca_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "endereco_cobranca_fk"))
    private EnderecoModel enderecoCobranca;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private PessoaAbstract empresa;

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
        VendaCompraLojaModel other = (VendaCompraLojaModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Integer getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(Integer diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public CupomDescontoModel getCupomDesconto() {
        return cupomDesconto;
    }

    public void setCupomDesconto(CupomDescontoModel cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
    }

    public NotaFiscalVendaModel getNotaFiscalVenda() {
        return notaFiscalVenda;
    }

    public void setNotaFiscalVenda(NotaFiscalVendaModel notaFiscalVenda) {
        this.notaFiscalVenda = notaFiscalVenda;
    }

    public FormaPagamentoModel getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoModel formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public PessoaAbstract getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaAbstract pessoa) {
        this.pessoa = pessoa;
    }

    public EnderecoModel getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoModel enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public EnderecoModel getEnderecoCobranca() {
        return enderecoCobranca;
    }

    public void setEnderecoCobranca(EnderecoModel enderecoCobranca) {
        this.enderecoCobranca = enderecoCobranca;
    }

    public PessoaAbstract getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaAbstract empresa) {
        this.empresa = empresa;
    }
    
}

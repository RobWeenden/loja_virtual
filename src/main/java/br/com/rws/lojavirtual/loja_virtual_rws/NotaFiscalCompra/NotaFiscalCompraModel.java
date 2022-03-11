package br.com.rws.lojavirtual.loja_virtual_rws.NotaFiscalCompra;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.rws.lojavirtual.loja_virtual_rws.ContaPagar.ContaPagarModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaAbstract;

@Entity
@Table(name = "tb_nota_fiscal_compra")
@SequenceGenerator(name = "seq_nota_fiscal_compra", sequenceName = "seq_nota_fiscal_compra", allocationSize = 1, initialValue = 1)
public class NotaFiscalCompraModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_compra")
    @Column(name = "nfc_id")
    private Long id;

    @Column(name = "nfc_numero_nota", nullable = false)
    private String numeroNota;

    @Column(name = "nfc_serie_nota", nullable = false)
    private String serieNota;

    @Column(name = "nfc_desc_observ")
    private String descObservacao;

    @Column(name = "nfc_vlr_total", nullable = false)
    private BigDecimal vlrTotal;

    @Column(name = "nfc_vlr_desconto")
    private BigDecimal vlrDesconto;

    @Column(name = "nfc_vlr_icms", nullable = false)
    private BigDecimal vlrIcms;

    @Column(name = "nfc_data_compra", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCompra;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private PessoaAbstract pessoa;

    @ManyToOne
    @JoinColumn(name = "conta_pagar_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "conta_pagar_fk"))
    private ContaPagarModel contaPagar;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private PessoaAbstract empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public String getSerieNota() {
        return serieNota;
    }

    public void setSerieNota(String serieNota) {
        this.serieNota = serieNota;
    }

    public String getDescObservacao() {
        return descObservacao;
    }

    public void setDescObservacao(String descObservacao) {
        this.descObservacao = descObservacao;
    }

    public BigDecimal getVlrTotal() {
        return vlrTotal;
    }

    public void setVlrTotal(BigDecimal vlrTotal) {
        this.vlrTotal = vlrTotal;
    }

    public BigDecimal getVlrDesconto() {
        return vlrDesconto;
    }

    public void setVlrDesconto(BigDecimal vlrDesconto) {
        this.vlrDesconto = vlrDesconto;
    }

    public BigDecimal getVlrIcms() {
        return vlrIcms;
    }

    public void setVlrIcms(BigDecimal vlrIcms) {
        this.vlrIcms = vlrIcms;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public PessoaAbstract getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaAbstract pessoa) {
        this.pessoa = pessoa;
    }

    public ContaPagarModel getContaPagar() {
        return contaPagar;
    }

    public void setContaPagar(ContaPagarModel contaPagar) {
        this.contaPagar = contaPagar;
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
        NotaFiscalCompraModel other = (NotaFiscalCompraModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public PessoaAbstract getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaAbstract empresa) {
        this.empresa = empresa;
    }
}

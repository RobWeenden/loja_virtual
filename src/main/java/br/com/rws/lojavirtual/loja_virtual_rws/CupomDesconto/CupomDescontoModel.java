package br.com.rws.lojavirtual.loja_virtual_rws.CupomDesconto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_cupom_desconto")
@SequenceGenerator(name = "seq_cupom_desconto", sequenceName = "seq_cupom_desconto", allocationSize = 1, initialValue = 1)
public class CupomDescontoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
    @Column(name = "cpd_id")
    private Long id;

    @Column(name = "cpd_codigo_desc")
    private String codigoDescricao;

    @Column(name = "cpd_vlr_real_desconto")
    private BigDecimal vlrRealDesconto;

    @Column(name = "cpd_vlr_porcent_desconto")
    private BigDecimal vlrPorcentDesconto;

    @Temporal(TemporalType.DATE)
    @Column(name = "cpd_data_valid_cupom")
    private Date dataValidCupom;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoDescricao() {
        return codigoDescricao;
    }

    public void setCodigoDescricao(String codigoDescricao) {
        this.codigoDescricao = codigoDescricao;
    }

    public BigDecimal getVlrRealDesconto() {
        return vlrRealDesconto;
    }

    public void setVlrRealDesconto(BigDecimal vlrRealDesconto) {
        this.vlrRealDesconto = vlrRealDesconto;
    }

    public BigDecimal getVlrPorcentDesconto() {
        return vlrPorcentDesconto;
    }

    public void setVlrPorcentDesconto(BigDecimal vlrPorcentDesconto) {
        this.vlrPorcentDesconto = vlrPorcentDesconto;
    }

    public Date getDataValidCupom() {
        return dataValidCupom;
    }

    public void setDataValidCupom(Date dataValidCupom) {
        this.dataValidCupom = dataValidCupom;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CupomDescontoModel other = (CupomDescontoModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

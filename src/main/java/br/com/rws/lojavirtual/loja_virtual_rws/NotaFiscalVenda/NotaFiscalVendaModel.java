package br.com.rws.lojavirtual.loja_virtual_rws.NotaFiscalVenda;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.rws.lojavirtual.loja_virtual_rws.VendaCompraLoja.VendaCompraLojaModel;

@Entity
@Table(name = "tb_nota_fiscal_venda")
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", allocationSize = 1, initialValue = 1)
public class NotaFiscalVendaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
    @Column(name = "nfv_id")
    private Long id;

    @Column(name = "nfv_numero", nullable = false)
    private String numero;

    @Column(name = "nfv_serie", nullable = false)
    private String serie;

    @Column(name = "nfv_tipo", nullable = false)
    private String tipo;

    @Column(name = "nfv_xml", columnDefinition = "text", nullable = false)
    private String xml;

    @Column(name = "nfv_pdf", columnDefinition = "text", nullable = false)
    private String pdf;

    @OneToOne
    @JoinColumn(name = "venda_compra_loja_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "venda_compra_loja_fk"))
    private VendaCompraLojaModel vendaCompraLoja;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
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
        NotaFiscalVendaModel other = (NotaFiscalVendaModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public VendaCompraLojaModel getVendaCompraLoja() {
        return vendaCompraLoja;
    }

    public void setVendaCompraLoja(VendaCompraLojaModel vendaCompraLoja) {
        this.vendaCompraLoja = vendaCompraLoja;
    }
}

package br.com.rws.lojavirtual.loja_virtual_rws.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

@Entity
@Table(name = "tb_produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class ProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    @Column(name = "prod_id")
    private Long id;

    @Column(name = "prod_tipo_unidade", nullable = false)
    private String tipoUnidade;

    @Column(name = "prod_nome", nullable = false)
    private String nome;

    @Column(name = "prod_desc", columnDefinition = "text", length = 2000, nullable = false)
    private String descricao;

    // Nota Item Produto

    @Column(name = "prod_peso", nullable = false)
    private Double peso;

    @Column(name = "prod_largura", nullable = false)
    private Double largura;

    @Column(name = "prod_altura", nullable = false)
    private Double altura;

    @Column(name = "prod_profundidad", nullable = false)
    private Double profundidade;

    @Column(name = "prod_vlr_venda", nullable = false)
    private BigDecimal vlrVenda = BigDecimal.ZERO;

    @Column(name = "prod_qtd_estoque", nullable = false)
    private Integer qtdEstoque = 0;

    @Column(name = "prod_qtd_alert_estoque")
    private Integer qtdAlertEstoque = 0;

    @Column(name = "prod_alert_qtd_estoque")
    private Boolean alertQtdEstoque = Boolean.FALSE;

    @Column(name = "prod_link_video")
    private String linkVideo;

    @Column(name = "prod_qtd_clique")
    private Integer qtdClique = 0;

    @Column(name = "prod_ativo", nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private PessoaAbstract empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(String tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }

    public BigDecimal getVlrVenda() {
        return vlrVenda;
    }

    public void setVlrVenda(BigDecimal vlrVenda) {
        this.vlrVenda = vlrVenda;
    }

    public Integer getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdAlertEstoque() {
        return qtdAlertEstoque;
    }

    public void setQtdAlertEstoque(Integer qtdAlertEstoque) {
        this.qtdAlertEstoque = qtdAlertEstoque;
    }

    public Boolean getAlertQtdEstoque() {
        return alertQtdEstoque;
    }

    public void setAlertQtdEstoque(Boolean alertQtdEstoque) {
        this.alertQtdEstoque = alertQtdEstoque;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public Integer getQtdClique() {
        return qtdClique;
    }

    public void setQtdClique(Integer qtdClique) {
        this.qtdClique = qtdClique;
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
        ProdutoModel other = (ProdutoModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public PessoaAbstract getEmpresa() {
        return empresa;
    }

    public void setEmpresa(PessoaAbstract empresa) {
        this.empresa = empresa;
    }

}
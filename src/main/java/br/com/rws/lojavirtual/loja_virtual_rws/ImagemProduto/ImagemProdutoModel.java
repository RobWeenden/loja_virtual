package br.com.rws.lojavirtual.loja_virtual_rws.ImagemProduto;

import java.io.Serializable;

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

import br.com.rws.lojavirtual.loja_virtual_rws.Produto.ProdutoModel;

@Entity
@Table(name = "tb_imagem_produto")
@SequenceGenerator(name = "seq_imagem_produto", sequenceName = "seq_imagem_produto", allocationSize = 1, initialValue = 1)
public class ImagemProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imagem_produto")
    @Column(name = "imp_id")
    private Long id;

    @Column(name = "imp_img_original", columnDefinition = "text", nullable = false)
    private String imgOriginal;

    @Column(name = "imp_img_miniatura", columnDefinition = "text", nullable = false)
    private String imgMiniatura;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
    private ProdutoModel produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgOriginal() {
        return imgOriginal;
    }

    public void setImgOriginal(String imgOriginal) {
        this.imgOriginal = imgOriginal;
    }

    public String getImgMiniatura() {
        return imgMiniatura;
    }

    public void setImgMiniatura(String imgMiniatura) {
        this.imgMiniatura = imgMiniatura;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
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
        ImagemProdutoModel other = (ImagemProdutoModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

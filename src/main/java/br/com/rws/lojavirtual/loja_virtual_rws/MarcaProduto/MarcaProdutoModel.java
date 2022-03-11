package br.com.rws.lojavirtual.loja_virtual_rws.MarcaProduto;

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

import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaAbstract;

@Entity
@Table(name = "tb_marca_produto")
@SequenceGenerator(name = "seq_tb_marca_produto", sequenceName = "seq_tb_marca_prod", allocationSize = 1, initialValue = 1)
public class MarcaProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_marca_produto")
    @Column(name = "mrp_id")
    private Long id;

    @Column(name = "mrp_desc", nullable = false)
    private String descricao;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_fk"))
    private PessoaAbstract empresa;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        MarcaProdutoModel other = (MarcaProdutoModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

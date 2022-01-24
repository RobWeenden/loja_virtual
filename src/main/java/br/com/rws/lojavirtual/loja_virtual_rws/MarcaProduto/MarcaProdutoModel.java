package br.com.rws.lojavirtual.loja_virtual_rws.MarcaProduto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_marca_produto")
@SequenceGenerator(name="seq_tb_marca_produto", sequenceName = "seq_tb_marca_prod", allocationSize =1, initialValue = 1)
public class MarcaProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_marca_produto")
    @Column(name = "mp_id")
    private Long id;
    
    @Column(name ="mp_desc")
    private String nomeDesc;

    public String getNomeDesc() {
        return nomeDesc;
    }

    public void setNomeDesc(String nomeDesc) {
        this.nomeDesc = nomeDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package br.com.rws.lojavirtual.loja_virtual_rws.Endereco;

import java.io.Serializable;

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

import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaAbstract;

@Entity
@Table(name = "tb_endereco")
@SequenceGenerator(name = "seq_tb_endereco", sequenceName = "seq_tb_endereco", allocationSize = 1, initialValue = 1)
public class EnderecoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_endereco")
    @Column(name = "end_id")
    private Long id;

    @Column(name = "end_rua", nullable = false)
    private String rua;

    @Column(name = "end_logradouro")
    private String logadouro;

    @Column(name = "end_bairro", nullable = false)
    private String bairro;

    @Column(name = "end_cidade", nullable = false)
    private String cidade;

    @Column(name = "end_complemento")
    private String complemento;

    @Column(name = "end_cep", nullable = false)
    private String cep;

    @Column(name = "end_numero", nullable = false)
    private String numero;

    @Column(name = "end_uf", nullable = false)
    private String uf;

    @ManyToOne(targetEntity = PessoaAbstract.class)
    @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
    private PessoaAbstract pessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "end_tipo_enderco", nullable = false)
    private TipoEnderecoEnum tipoEndereco;

    public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public TipoEnderecoEnum getTipoEndereco() {
        return tipoEndereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getLogadouro() {
        return logadouro;
    }

    public void setLogadouro(String logadouro) {
        this.logadouro = logadouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public PessoaAbstract getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaAbstract pessoa) {
        this.pessoa = pessoa;
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
        EnderecoModel other = (EnderecoModel) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

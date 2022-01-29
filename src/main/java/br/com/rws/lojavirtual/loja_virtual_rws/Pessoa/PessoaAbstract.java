package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.rws.lojavirtual.loja_virtual_rws.Endereco.EnderecoModel;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa", allocationSize = 1, initialValue = 1)
public abstract class PessoaAbstract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @Column(name = "psa_id")
    private Long id;

    @Column(name = "psa_nome", nullable = false)
    private String nome;

    @Column(name = "psa_email", nullable = false)
    private String email;

    @Column(name = "psa_telefone", nullable = false)
    private String telefone;

    @OneToMany(mappedBy ="pessoa", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnderecoModel> enderecos;

    public List<EnderecoModel> getEndereco() {
        return enderecos;
    }

    public void setEndereco(List<EnderecoModel> enderecos) {
        if (enderecos == null) {
            enderecos = new ArrayList<EnderecoModel>();
        }
        this.enderecos = enderecos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
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
        PessoaAbstract other = (PessoaAbstract) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}

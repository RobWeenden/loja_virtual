package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridicaModel, Long>{
    
    @Query(value = "SELECT pj FROM PessoaJuridicaModel pj WHERE pj.cnpj = ?1")
    public PessoaJuridicaModel existeCnpjCadastrado(String cnpj);

    @Query(value = "SELECT pj FROM PessoaJuridicaModel pj WHERE pj.inscEstadual = ?1")
    public PessoaJuridicaModel existeInscEstadual(String inscEstadual);
}

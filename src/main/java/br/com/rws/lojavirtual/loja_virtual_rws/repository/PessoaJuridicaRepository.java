package br.com.rws.lojavirtual.loja_virtual_rws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaJuridicaModel;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridicaModel, Long>{
    
    @Query(value = "SELECT pj FROM PessoaJuridicaModel pj WHERE pj.cnpj = ?1")
    public PessoaJuridicaModel existeCnpjCadastrado(String cnpj);

    @Query(value = "SELECT pj FROM PessoaJuridicaModel pj WHERE pj.cnpj = ?1")
    public List<PessoaJuridicaModel> pesquisarCNPJ(String cnpj);
    
    @Query(value = "SELECT pj FROM PessoaJuridicaModel pj WHERE pj.inscEstadual = ?1")
    public PessoaJuridicaModel existeInscEstadual(String inscEstadual);
    
    @Query(value = "SELECT pj FROM PessoaJuridicaModel pj WHERE pj.inscEstadual = ?1")
    public List<PessoaJuridicaModel> existeInscEstadualFromList(String inscEstadual);
    
    @Query(value = "SELECT pj FROM PessoaJuridicaModel pj WHERE upper(trim(pj.nome)) like %?1%")
    public List<PessoaJuridicaModel> pesquisaPorNomePJ(String nome);
}

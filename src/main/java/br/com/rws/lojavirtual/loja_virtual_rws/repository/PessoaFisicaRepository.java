package br.com.rws.lojavirtual.loja_virtual_rws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaFisicaModel;

public interface PessoaFisicaRepository extends CrudRepository<PessoaFisicaModel, Long> {

    @Query(value = "SELECT pf FROM PessoaFisicaModel pf WHERE pf.cpf = ?1")
    public PessoaFisicaModel existeCpfCadastrado(String cpf);
    
    @Query(value = "SELECT pf FROM PessoaFisicaModel pf WHERE pf.cpf = ?1")
    public List<PessoaFisicaModel> pesquisarCpfFromList(String cpf);
    
    @Query(value = "SELECT pf FROM PessoaFisicaModel pf WHERE upper(trim(pf.nome)) like %?1%")
    public List<PessoaFisicaModel> pesquisaPorNomePF(String nome);
    
    @Query(value = "SELECT pf FROM PessoaFisicaModel pf WHERE pf.cpf = ?1")
    public PessoaFisicaModel pesquisarPorCPF(String cpf);
    
    
    
    

}

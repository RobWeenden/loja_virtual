package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PessoaFisicaRepository extends CrudRepository<PessoaFisicaModel, Long> {

    @Query(value = "SELECT pf FROM PessoaFisicaModel pf WHERE pf.cpf = ?1")
    public PessoaFisicaModel existeCpfCadastrado(String cpf);

}

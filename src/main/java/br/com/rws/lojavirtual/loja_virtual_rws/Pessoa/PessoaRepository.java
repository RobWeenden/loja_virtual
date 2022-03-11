package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaJuridicModel, Long>{
    
}

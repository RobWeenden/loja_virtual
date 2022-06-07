package br.com.rws.lojavirtual.loja_virtual_rws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rws.lojavirtual.loja_virtual_rws.model.EnderecoModel;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long>{

	
}

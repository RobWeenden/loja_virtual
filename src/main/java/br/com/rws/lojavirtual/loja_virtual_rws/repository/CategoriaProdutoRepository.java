package br.com.rws.lojavirtual.loja_virtual_rws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.rws.lojavirtual.loja_virtual_rws.model.CategoriaProdutoModel;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProdutoModel, Long> {
	
	@Query(nativeQuery = true, value ="SELECT count(1) > 0 FROM tb_categoria_produto WHERE upper(trim(ctp_desc)) = ?1")
	public boolean existeDescricaoCategoria(String descricao);

}

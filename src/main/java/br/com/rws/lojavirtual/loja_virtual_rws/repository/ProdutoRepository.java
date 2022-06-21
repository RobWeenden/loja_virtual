package br.com.rws.lojavirtual.loja_virtual_rws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.rws.lojavirtual.loja_virtual_rws.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
	
	@Query(nativeQuery = true, value ="SELECT count(1) > 0 FROM tb_produto WHERE upper(trim(prod_nome)) = ?1")
	public boolean existeDescricaoProduto(String descricao);
	
	@Query(value="SELECT pd FROM ProdutoModel pd WHERE upper(trim(pd.nome)) like %?1%")
	public List<ProdutoModel> pesquisarProdutoByNome(String nome);

	@Query(value="SELECT pd FROM ProdutoModel pd WHERE upper(trim(pd.nome)) like %?1% AND pd.empresa.id = ?2")
	public List<ProdutoModel> pesquisarProdutoByNome(String nome, Long idEmpresa);
}

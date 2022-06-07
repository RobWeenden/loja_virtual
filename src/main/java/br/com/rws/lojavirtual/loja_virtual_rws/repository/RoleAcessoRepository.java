package br.com.rws.lojavirtual.loja_virtual_rws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rws.lojavirtual.loja_virtual_rws.model.RoleAcessoModel;

@Repository
@Transactional
public interface RoleAcessoRepository extends JpaRepository<RoleAcessoModel, Long> {

    @Query("select a from RoleAcessoModel a where upper(trim(a.descricao)) like %?1%")
    List<RoleAcessoModel> buscarRoleDescricao(String desc);
}

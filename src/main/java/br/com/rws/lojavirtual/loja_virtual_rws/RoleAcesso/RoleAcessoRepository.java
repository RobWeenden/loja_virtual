package br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleAcessoRepository extends JpaRepository<RoleAcessoModel, Long> {

    @Query("select a from RoleAcessoModel a where upper(trim(a.descricao)) like %?1%")
    List<RoleAcessoModel> buscarRoleDescricao(String desc);
}

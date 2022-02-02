package br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoleAcessoRepository extends JpaRepository<RoleAcessoModel, Long>{
    
}

package br.com.rws.lojavirtual.loja_virtual_rws.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    
    @Query(value = "SELECT us FROM UsuarioModel us WHERE us.login = ?1")
    UsuarioModel findUserByLogin(String login);

    @Query(value = "SELECT us FROM UsuarioModel us WHERE us.pessoa.psa_id = ?1 or us.usu_login =?2")
    UsuarioModel findByUsuarioByPessoa(Long id, String email);
}

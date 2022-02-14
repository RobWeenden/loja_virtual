package br.com.rws.lojavirtual.loja_virtual_rws.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    
    @Query(value = "SELECT us FROM UsuarioModel us WHERE us.login = ?1")
    UsuarioModel findUserByLogin(String login);
}

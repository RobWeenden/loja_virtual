package br.com.rws.lojavirtual.loja_virtual_rws.Usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    
    @Query(value = "SELECT us FROM UsuarioModel us WHERE us.login = ?1")
    UsuarioModel findUserByLogin(String login);

    @Query(value = "SELECT us FROM UsuarioModel us WHERE us.pessoa.id = ?1 or us.login =?2")
    UsuarioModel findByUsuarioByPessoa(Long id, String email);

    @Query(value = "select constraint_name from information_schema.constraint_column_usage " +
            "where table_name  = 'tb_usuario_role_acesso' and column_name = 'role_acesso_id' " +
            "and constraint_name <> 'unique_acesso_usuario' ", nativeQuery = true)
    String consultaConstraintRoleAcesso();

    @Transactional
    @Modifying
    @Query(value="INSERT INTO tb_usuario_role_acesso(usuario_id, role_acesso_id) "+
    " VALUES (?1, (SELECT rla_id FROM tb_role_acesso WHERE rla_desc = 'ROLE_USER') )", nativeQuery = true)
    void inserirRoleAcessoPj(Long id);

    @Query(value = "SELECT u FROM UsuarioModel u WHERE u.dataAtualPassword <= CURRENT_DATE -90")
    List<UsuarioModel> usuarioDataPasswordVencida();
}

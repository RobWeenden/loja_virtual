package br.com.rws.lojavirtual.loja_virtual_rws.Usuario;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.rws.lojavirtual.loja_virtual_rws.RoleAcesso.RoleAcessoModel;

@Entity
@Table(name = "tb_usuario")
@SequenceGenerator(name = "seq_tb_usuario", sequenceName = "seq_tb_usuario", allocationSize = 1, initialValue = 1)
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_usuario")
    @Column(name = "usu_id")
    private Long id;

    @Column(name = "usu_login")
    private String login;

    @Column(name = "usu_password")
    private String password;

    @Temporal(TemporalType.DATE)
    @Column(name = "usu_data_atual_password")
    private Date dataAtualPassword;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_usuario_role_acesso", uniqueConstraints = @UniqueConstraint(columnNames = { "usuario_id",
            "role_acesso_id" }, name = "unique_acesso_usuario"), 
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "usu_id", table = "tb_usuario", unique = false, foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "role_acesso_id", unique = false, referencedColumnName = "rla_id", table = "tb_role_acesso", foreignKey = @ForeignKey(name = "role_acesso_fk", value = ConstraintMode.CONSTRAINT)))
    private List<RoleAcessoModel> rolesAcessos;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.rolesAcessos;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

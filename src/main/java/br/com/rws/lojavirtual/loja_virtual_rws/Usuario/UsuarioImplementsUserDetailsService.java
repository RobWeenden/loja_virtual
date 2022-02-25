package br.com.rws.lojavirtual.loja_virtual_rws.Usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioModel usuario = userRepository.findUserByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado!");
        }
        
        return new User(usuario.getLogin(), usuario.getPassword(), usuario.getAuthorities());
    }

}

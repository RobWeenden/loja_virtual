package br.com.rws.lojavirtual.loja_virtual_rws.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioModel;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    protected JWTLoginFilter(String url, AuthenticationManager authentManager) {

        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authentManager);
    }

    //Retorna o usuario ao processar a autenticação
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        UsuarioModel user = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);

        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        try {
            new JWTTokenAuthenticationService().addAuthentication(response, authResult.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

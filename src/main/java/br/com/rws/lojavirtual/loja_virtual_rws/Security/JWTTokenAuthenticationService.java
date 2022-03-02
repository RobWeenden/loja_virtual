package br.com.rws.lojavirtual.loja_virtual_rws.Security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.Context.ApplicationContextLoad;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

//Metodo para gerar o Token de autenticação gerar ele e recuperar

@Service
@Component
public class JWTTokenAuthenticationService {

    private static final long EXPIRATION_TIME = 1728000000;
    private static final String SECRET = "!Uwur4[OzqeXmBThewn*%kI-]@#EGrU($(dWmUkD#&aP(uxNIC";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) throws Exception {

        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        String token = TOKEN_PREFIX + " " + JWT;

        response.addHeader(HEADER_STRING, token);
        liberacaoCors(response);
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");//USANDO PARA O POSTMAN - TESTE

    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String token = request.getHeader(HEADER_STRING);
        try {

            if (token != null) {
                String tokenClean = token.replace(TOKEN_PREFIX, "").trim();
                String tokenUser = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(tokenClean)
                        .getBody().getSubject();
                if (tokenUser != null) {
                    UsuarioModel usuario = ApplicationContextLoad
                            .getApplicationContext().getBean(UsuarioRepository.class)
                            .findUserByLogin(tokenUser);
                    if (usuario != null) {
                        return new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getPassword(),
                                usuario.getAuthorities());
                    }
                }
            }
        } catch (SignatureException signatureExcp) {
            response.getWriter().write("Token está invalido!!");
        } catch (ExpiredJwtException expiredJwt) {
            response.getWriter().write("Token está expirado, efetue o login novamente.");

        } finally {

            liberacaoCors(response);
        }

        return null;
    }

    private void liberacaoCors(HttpServletResponse resp) {
        if (resp.getHeader("Access-Control-Allow-Origin") == null) {
            resp.addHeader("Access-Control-Allow-Origin", "*");
        }
        if (resp.getHeader("Access-Control-Allow-Headers") == null) {
            resp.addHeader("Access-Control-Allow-Headers", "*");
        }
        if (resp.getHeader("Access-Control-Request-Headers") == null) {
            resp.addHeader("Access-Control-Request-Headers", "*");
        }
        if (resp.getHeader("Access-Control-Allow-Methods") == null) {
            resp.addHeader("Access-Control-Allow-Methods", "*");
        }

    }
}

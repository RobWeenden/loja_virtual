package br.com.rws.lojavirtual.loja_virtual_rws.Security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.Config.ApiConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAuthenticationService {

    @Autowired
    private static ApiConfig configApi;

    private static final long EXPIRATION_TIME = configApi.getExpirationTime();
    private static final String SECRET = configApi.getSecretKey();
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public void addAuthentication(HttpServletResponse response, String username) throws Exception {

        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        
        String token = TOKEN_PREFIX + " " + JWT;

        response.addHeader(HEADER_STRING, token);
        liberacaoCors(response);
        response.getWriter().write("{\"Authorization\": \""+ token + "\"}");//USANDO PARA O POSTMAN - TESTE
        
    }

    public Authentication gAuthentication(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String token = request.getHeader(HEADER_STRING);
        return null;
    }

    private void liberacaoCors(HttpServletResponse resp){
        if(resp.getHeader("Access-Control-Allow-Origin") == null){
            resp.addHeader("Access-Control-Allow-Origin", "*");
        }
        if(resp.getHeader("Access-Control-Allow-Headers") == null){
            resp.addHeader("Access-Control-Allow-Headers", "*");
        }
        if(resp.getHeader("Access-Control-Request-Headers") == null){
            resp.addHeader("Access-Control-Request-Headers", "*");
        }
        if(resp.getHeader("Access-Control-Allow-Methods") == null){
            resp.addHeader("Access-Control-Allow-Methods", "*");
        }

    }
}

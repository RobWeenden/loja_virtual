package br.com.rws.lojavirtual.loja_virtual_rws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EndpointContService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public void atualizarAcessoEndpoint() {
		jdbcTemplate.execute("begin; update tb_acesso_endpoint set qtd_acesso_endpoint = qtd_acesso_endpoint + 1 where nome_endpoint = 'END-POINT-NOME-PESSOA-FISICA'; commit");

    }
}

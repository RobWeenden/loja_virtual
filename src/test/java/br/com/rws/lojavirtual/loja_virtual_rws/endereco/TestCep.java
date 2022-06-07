package br.com.rws.lojavirtual.loja_virtual_rws.endereco;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.rws.lojavirtual.loja_virtual_rws.LojaVirtualRwsApplication;
import br.com.rws.lojavirtual.loja_virtual_rws.controller.PessoaController;
import br.com.rws.lojavirtual.loja_virtual_rws.dto.CepDTO;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualRwsApplication.class)
public class TestCep extends TestCase{
	
    @Autowired
    PessoaController pessoaController;
    
    @Test
    void testConsultaCep() {
    	String cep = "71691143";
    	CepDTO cepDto = new CepDTO();
    	
    	cepDto = pessoaController.consultaCep(cep).getBody();
    	//continue 16:43
    }
}

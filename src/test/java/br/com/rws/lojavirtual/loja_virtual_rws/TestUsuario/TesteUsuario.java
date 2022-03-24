package br.com.rws.lojavirtual.loja_virtual_rws.TestUsuario;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.rws.lojavirtual.loja_virtual_rws.LojaVirtualRwsApplication;
import br.com.rws.lojavirtual.loja_virtual_rws.Exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaController;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaJuridicaModel;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualRwsApplication.class)
public class TesteUsuario extends TestCase {

    @Autowired
    PessoaController pessoaController;

    @Test
    public void testCadastroUsuario() throws CustomExceptions {

        PessoaJuridicaModel pessoaJuridica = new PessoaJuridicaModel();
        pessoaJuridica.setCnpj("_Teste_PJ"+gerarCaracterAleatorio());
        pessoaJuridica.setNome("_nome_"+gerarCaracterAleatorio());
        pessoaJuridica.setEmail("_email_pj@"+gerarCaracterAleatorio()+".com");
        pessoaJuridica.setTelefone("8585548555");
        pessoaJuridica.setInscEstadual("6655855825");
        pessoaJuridica.setInscMunicipal("132158d5d58");
        pessoaJuridica.setNomeFantasia("_fantasia_"+gerarCaracterAleatorio());
        pessoaJuridica.setRazaoSocial("_razao_social_"+gerarCaracterAleatorio());

        pessoaController.savePessoaJuridica(pessoaJuridica);
        //CONTINUE 
    }

    @Test
    public String gerarCaracterAleatorio() {
        String randomString = "AabcDeFfgGhHsSdDJjKkLlçÇQWwEeRrTtYyUuIiOoPpZzXxCcVvBbNnMn)=m!#@#$%¨&*(_+_=-0987654321[{]}";
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            int index = (int) (randomString.length() * Math.random());
            sb.append(randomString.charAt(index));
        }
        return sb.toString();
    }

}

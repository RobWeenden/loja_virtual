package br.com.rws.lojavirtual.loja_virtual_rws.TestUsuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.rws.lojavirtual.loja_virtual_rws.LojaVirtualRwsApplication;
import br.com.rws.lojavirtual.loja_virtual_rws.Exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaController;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaJuridicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioService;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualRwsApplication.class)
public class TesteUsuario extends TestCase {

    @Autowired
    PessoaController pessoaController;

    @Test
    public void testCadastroUsuario() throws CustomExceptions {

        PessoaJuridicaModel pessoaJuridica = new PessoaJuridicaModel();
        pessoaJuridica.setCnpj("1231505225858558");
        pessoaJuridica.setNome("_nome_*cym(Fu=WUgHVo6Un=_G");
        pessoaJuridica.setEmail("nome@Fu=WUg.com");
        pessoaJuridica.setTelefone("8585548555");
        pessoaJuridica.setInscEstadual("6655855825");
        pessoaJuridica.setInscMunicipal("132158d5d58");
        pessoaJuridica.setNomeFantasia("_fantasia_V&7HE$3EaOPiPuy1&t2F");
        pessoaJuridica.setRazaoSocial("_razao_social_V&7HE$3EaOPiPuy1&t2F");

        pessoaController.savePessoaJuridica(pessoaJuridica);
        // PessoaFisicaModel pessoaFisica = new PessoaFisicaModel();
        // pessoaFisica.setCpf("123158558");
        // pessoaFisica.setNome("_nome_asdkfjkeadsfjjk");
        // pessoaFisica.setEmail("nome@teste.com");
        // pessoaFisica.setTelefone("8585548555");

        // pessoaFisica.setEmpresa(pessoaFisica);

    }

    @Test
    public void gerarCaracterAleatorio() {
        String randomString = "AabcDeFfgGhHsSdDJjKkLlçÇQWwEeRrTtYyUuIiOoPpZzXxCcVvBbNnMn)=m!#@#$%¨&*(_+_=-0987654321[{]}";
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            int index = (int) (randomString.length() * Math.random());
            sb.append(randomString.charAt(index));
        }
        System.out.println("_nome_"+sb.toString());
        System.out.println("_fantasia_"+sb.toString());
        System.out.println("_razao_social_"+sb.toString());
    }

}

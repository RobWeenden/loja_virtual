package br.com.rws.lojavirtual.loja_virtual_rws.TestUsuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.rws.lojavirtual.loja_virtual_rws.LojaVirtualRwsApplication;
import br.com.rws.lojavirtual.loja_virtual_rws.Endereco.EnderecoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Endereco.TipoEnderecoEnum;
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
        pessoaJuridica.setNome("_nome_" + gerarCaracterAleatorio());
        pessoaJuridica.setEmail("_email_pj@" + gerarCaracterAleatorio() + ".com");
        pessoaJuridica.setTelefone("8585548555");
        pessoaJuridica.setTipoPessoa("_tipo_pessoa" + gerarCaracterAleatorio());
        pessoaJuridica.setCnpj("_Teste_PJ" + gerarCaracterAleatorio());
        pessoaJuridica.setInscEstadual("6655855825");
        pessoaJuridica.setInscMunicipal("132158d5d58");
        pessoaJuridica.setNomeFantasia("_fantasia_" + gerarCaracterAleatorio());
        pessoaJuridica.setRazaoSocial("_razao_social_" + gerarCaracterAleatorio());
        pessoaJuridica.setCategoria("-categoria_" + gerarCaracterAleatorio());

        EnderecoModel endereco1 = new EnderecoModel();
        endereco1.setBairro("_bairro_1" + gerarCaracterAleatorio());
        endereco1.setCep("_cep_1" + gerarCaracterAleatorio());
        endereco1.setComplemento("_complemento_1" + gerarCaracterAleatorio());
        endereco1.setEmpresa(pessoaJuridica);
        endereco1.setNumero("_numero_1" + gerarCaracterAleatorio());
        endereco1.setPessoa(pessoaJuridica);
        endereco1.setRua("_rua_1" + gerarCaracterAleatorio());
        endereco1.setLogradouro("_logra_1" + gerarCaracterAleatorio());
        endereco1.setTipoEndereco(TipoEnderecoEnum.ENTREGA);
        endereco1.setUf("_uf_1" + gerarCaracterAleatorio());
        endereco1.setCidade("_cidade_1" + gerarCaracterAleatorio());

        EnderecoModel endereco2 = new EnderecoModel();
        endereco2.setRua("_rua_2" + gerarCaracterAleatorio());
        endereco2.setLogradouro("_logra_2" + gerarCaracterAleatorio());
        endereco2.setBairro("_bairro_2" + gerarCaracterAleatorio());
        endereco2.setComplemento("_complemento_2" + gerarCaracterAleatorio());
        endereco2.setCep("_cep_2" + gerarCaracterAleatorio());
        endereco2.setNumero("_numero_2" + gerarCaracterAleatorio());
        endereco2.setUf("_uf_2" + gerarCaracterAleatorio());
        endereco2.setTipoEndereco(TipoEnderecoEnum.COBRANCA);
        endereco2.setEmpresa(pessoaJuridica);
        endereco2.setPessoa(pessoaJuridica);
        endereco2.setCidade("_cidade_1" + gerarCaracterAleatorio());

        pessoaJuridica.getEnderecos().add(endereco2);
        pessoaJuridica.getEnderecos().add(endereco1);

        pessoaJuridica =  pessoaController.savePessoaJuridica(pessoaJuridica).getBody();
        assertEquals(true, pessoaJuridica.getId() > 0);

        for(EnderecoModel endereco : pessoaJuridica.getEnderecos()){
            assertEquals(true, endereco.getId() > 0);
        }

        assertEquals(2, pessoaJuridica.getEnderecos().size());
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

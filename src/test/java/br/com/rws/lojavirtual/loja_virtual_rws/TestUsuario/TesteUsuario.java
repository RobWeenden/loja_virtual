package br.com.rws.lojavirtual.loja_virtual_rws.TestUsuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.rws.lojavirtual.loja_virtual_rws.LojaVirtualRwsApplication;
import br.com.rws.lojavirtual.loja_virtual_rws.controller.PessoaController;
import br.com.rws.lojavirtual.loja_virtual_rws.enums.TipoEnderecoEnum;
import br.com.rws.lojavirtual.loja_virtual_rws.exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.model.EnderecoModel;
import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaFisicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaJuridicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.PessoaJuridicaRepository;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualRwsApplication.class)
class TesteUsuario extends TestCase {

    @Autowired
    PessoaController pessoaController;
    
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    
    @Test
    void testCadastroUsuarioPJ() throws CustomExceptions {

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

        pessoaController.savePessoaJuridica(pessoaJuridica);
        // assertEquals(true, pessoaJuridica.getId() > 0);

        // for(EnderecoModel endereco : pessoaJuridica.getEnderecos()){
        //     assertEquals(true, endereco.getId() > 0);
        // }

       // assertEquals(2, pessoaJuridica.getEnderecos().size());
        //CONTINUE  53:57
    }

    @Test
    String gerarCaracterAleatorio() {
        String randomString = "AabcDeFfgGhHsSdDJjKkLlçÇQWwEeRrTtYyUuIiOoPpZzXxCcVvBbNnMn)=m!#@#$%¨&*(_+_=-0987654321[{]}";
        StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            int index = (int) (randomString.length() * Math.random());
            sb.append(randomString.charAt(index));
        }
        return sb.toString();
    }

    @Test
    void testCadastroUsuarioPF() throws Exception {
    	
    	PessoaJuridicaModel pessoaJuridica = pessoaJuridicaRepository.existeCnpjCadastrado("AKDJ454837122936F");

        PessoaFisicaModel pessoaFisica = new PessoaFisicaModel();
        pessoaFisica.setNome("_nome_" + gerarCaracterAleatorio());
        pessoaFisica.setEmail("robson.weenden@yahoo.com.br");
        pessoaFisica.setTelefone("8585548555");
        pessoaFisica.setTipoPessoa("_tipo_pessoa" + gerarCaracterAleatorio());
        pessoaFisica.setCpf("896.582.880-57");
        pessoaFisica.setEmpresa(pessoaJuridica);

        EnderecoModel endereco1 = new EnderecoModel();
        endereco1.setBairro("_bairro_1" + gerarCaracterAleatorio());
        endereco1.setCep("_cep_1" + gerarCaracterAleatorio());
        endereco1.setComplemento("_complemento_1" + gerarCaracterAleatorio());
        endereco1.setEmpresa(pessoaFisica);
        endereco1.setNumero("_numero_1" + gerarCaracterAleatorio());
        endereco1.setPessoa(pessoaFisica);
        endereco1.setRua("_rua_1" + gerarCaracterAleatorio());
        endereco1.setLogradouro("_logra_1" + gerarCaracterAleatorio());
        endereco1.setTipoEndereco(TipoEnderecoEnum.ENTREGA);
        endereco1.setUf("_uf_1" + gerarCaracterAleatorio());
        endereco1.setCidade("_cidade_1" + gerarCaracterAleatorio());
        endereco1.setEmpresa(pessoaJuridica);

        EnderecoModel endereco2 = new EnderecoModel();
        endereco2.setRua("_rua_2" + gerarCaracterAleatorio());
        endereco2.setLogradouro("_logra_2" + gerarCaracterAleatorio());
        endereco2.setBairro("_bairro_2" + gerarCaracterAleatorio());
        endereco2.setComplemento("_complemento_2" + gerarCaracterAleatorio());
        endereco2.setCep("_cep_2" + gerarCaracterAleatorio());
        endereco2.setNumero("_numero_2" + gerarCaracterAleatorio());
        endereco2.setUf("_uf_2" + gerarCaracterAleatorio());
        endereco2.setTipoEndereco(TipoEnderecoEnum.COBRANCA);
        endereco2.setEmpresa(pessoaFisica);
        endereco2.setPessoa(pessoaFisica);
        endereco2.setCidade("_cidade_1" + gerarCaracterAleatorio());
        endereco2.setEmpresa(pessoaJuridica);

        pessoaFisica.getEnderecos().add(endereco2);
        pessoaFisica.getEnderecos().add(endereco1);

        pessoaController.savePessoaFisica(pessoaFisica);
        // assertEquals(true, pessoaJuridica.getId() > 0);

        // for(EnderecoModel endereco : pessoaJuridica.getEnderecos()){
        //     assertEquals(true, endereco.getId() > 0);
        // }

       // assertEquals(2, pessoaJuridica.getEnderecos().size());
        //CONTINUE  53:57
    }

}

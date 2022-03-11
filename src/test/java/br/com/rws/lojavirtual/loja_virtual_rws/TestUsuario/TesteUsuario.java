package br.com.rws.lojavirtual.loja_virtual_rws.TestUsuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.rws.lojavirtual.loja_virtual_rws.LojaVirtualRwsApplication;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaFisicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaJuridicModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Pessoa.PessoaRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioService;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualRwsApplication.class)
public class TesteUsuario extends TestCase{

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    PessoaRepository pessoaRepository;

    @Test
    public void testCadastroUsuario(){

        PessoaJuridicModel pessoaJuridica = new PessoaJuridicModel();
        pessoaJuridica.setCnpj("1231505225858558");
        pessoaJuridica.setNome("_nome_aASLFDKJDKIA==AKDKEI");
        pessoaJuridica.setEmail("nome@teste.com");
        pessoaJuridica.setTelefone("8585548555");
        pessoaJuridica.setInscEstadual("6655855825");
        pessoaJuridica.setInscMunicipal("132158d5d58");
        pessoaJuridica.setNomeFantasia("_fantsia_aakdlkf90rLKJDF9-93");
        pessoaJuridica.setRazaoSocial("-razcao_laksdj9309FT093IASLF0");

        pessoaRepository.save(pessoaJuridica);
        // PessoaFisicaModel pessoaFisica = new PessoaFisicaModel();
        // pessoaFisica.setCpf("123158558");
        // pessoaFisica.setNome("_nome_asdkfjkeadsfjjk");
        // pessoaFisica.setEmail("nome@teste.com");
        // pessoaFisica.setTelefone("8585548555");

        // pessoaFisica.setEmpresa(pessoaFisica);

    }
    
}

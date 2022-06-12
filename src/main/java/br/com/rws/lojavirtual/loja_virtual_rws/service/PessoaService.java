package br.com.rws.lojavirtual.loja_virtual_rws.service;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.rws.lojavirtual.loja_virtual_rws.dto.CepDTO;
import br.com.rws.lojavirtual.loja_virtual_rws.dto.ConsultaCnpjDTO;
import br.com.rws.lojavirtual.loja_virtual_rws.email.SendEmailService;
import br.com.rws.lojavirtual.loja_virtual_rws.exceptions.CustomExceptions;
import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaFisicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.model.PessoaJuridicaModel;
import br.com.rws.lojavirtual.loja_virtual_rws.model.UsuarioModel;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.PessoaFisicaRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.PessoaJuridicaRepository;
import br.com.rws.lojavirtual.loja_virtual_rws.repository.UsuarioRepository;

@Service
public class PessoaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    private SendEmailService sendEmail;

    @Autowired
    private JdbcTemplate jdbc;

    public PessoaJuridicaModel savePessoaJuridica(PessoaJuridicaModel pessoaJuridica) throws CustomExceptions {

        for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
            pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
            pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
        }

        pessoaJuridica = pessoaJuridicaRepository.save(pessoaJuridica);
        UsuarioModel usuarioPj = usuarioRepository.findByUsuarioByPessoa(pessoaJuridica.getId(),
                pessoaJuridica.getEmail());

        if (usuarioPj == null) {
            //REMOVER RESTRIÇÃO NO DB
            String constraint = usuarioRepository.consultaConstraintRoleAcesso();
            if (constraint != null) {
                //EXECUÇÃO DE UMA TRANSAÇÃO PARA REMOÇÃO DA RESTRIÇÃO
                jdbc.execute("begin; alter table tb_usuario_role_acesso drop constraint " + constraint + "; commit;");
            }

            usuarioPj = new UsuarioModel();
            usuarioPj.setDataAtualPassword(Calendar.getInstance().getTime());
            usuarioPj.setEmpresa(pessoaJuridica);
            usuarioPj.setPessoa(pessoaJuridica);
            usuarioPj.setLogin(pessoaJuridica.getEmail());

            String passwordEnvioEmail = "" + Calendar.getInstance().getTimeInMillis();
            String passwordCrypt = new BCryptPasswordEncoder().encode(passwordEnvioEmail);

            usuarioPj.setPassword(passwordCrypt);
            usuarioPj = usuarioRepository.save(usuarioPj);
            usuarioRepository.inserirAcessoUser(usuarioPj.getId());
            usuarioRepository.inserirAcessoPJ(usuarioPj.getId(), "ROLE_ADMIN");

            StringBuilder msgHtmlEnvio = new StringBuilder();
            msgHtmlEnvio.append("<b><h3>Segue abaixo seus dados de acesso para sua Loja Virtual</h3></b><br>");
            msgHtmlEnvio.append("<b>Login:</b>"+pessoaJuridica.getEmail()+ "<br>");
            msgHtmlEnvio.append("<b>Senha:</b>"+passwordEnvioEmail+ "<br>");
            msgHtmlEnvio.append("<h3>Agradecemo a Preferência.</h3>");

            try {

                sendEmail.enviarEmailHtml("Acesso gerado para Loja Virtual", msgHtmlEnvio.toString(),
                        pessoaJuridica.getEmail());

            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomExceptions(e.getMessage());
            }
        }
        return pessoaJuridica;
    }


    public PessoaFisicaModel savePessoaFisica(PessoaFisicaModel pessoaFisica) {

        for (int i = 0; i < pessoaFisica.getEnderecos().size(); i++) {
            pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
        }

        pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
        UsuarioModel usuarioPf = usuarioRepository.findByUsuarioByPessoa(pessoaFisica.getId(), pessoaFisica.getEmail());

        if (usuarioPf == null) {
            String constraint = usuarioRepository.consultaConstraintRoleAcesso();
            if (constraint != null) {
                jdbc.execute("begin; alter table tb_usuario_role_acesso drop constraint " + constraint + "; commit;");
            }

            usuarioPf = new UsuarioModel();
            usuarioPf.setDataAtualPassword(Calendar.getInstance().getTime());
            usuarioPf.setEmpresa(pessoaFisica.getEmpresa());
            usuarioPf.setPessoa(pessoaFisica);
            usuarioPf.setLogin(pessoaFisica.getEmail());

            String passwordEnvioEmail = "" + Calendar.getInstance().getTimeInMillis();
            String passwordCrypt = new BCryptPasswordEncoder().encode(passwordEnvioEmail);

            usuarioPf.setPassword(passwordCrypt);
            usuarioPf = usuarioRepository.save(usuarioPf);
            usuarioRepository.inserirAcessoUser(usuarioPf.getId());

            StringBuilder msgHtmlEnvio = new StringBuilder();
            msgHtmlEnvio.append("<b><h3>Segue abaixo seus dados de acesso para sua Loja Virtual</h3></b><br>");
            msgHtmlEnvio.append("<b>Login:</b>"+pessoaFisica.getEmail()+ "<br>");
            msgHtmlEnvio.append("<b>Senha:</b>"+passwordEnvioEmail+ "<br>");
            msgHtmlEnvio.append("<h3>Agradecemo a Preferência.</h3>");

            try {
                sendEmail.enviarEmailHtml("Acesso gerado para Loja Virtual", msgHtmlEnvio.toString(), pessoaFisica.getEmail());

            } catch (Exception e) {
            	e.printStackTrace();
            }
          
        }
        return pessoaFisica;
    }
    
    public CepDTO consultaCep(String cep) {
    	//API VIA CEP
    	return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDTO.class).getBody();
    }


	public ConsultaCnpjDTO consultaCnpjReceitaWS(String cnpj) {
		// API RECEITA WS
    	return new RestTemplate().getForEntity("https://receitaws.com.br/v1/cnpj/" + cnpj, ConsultaCnpjDTO.class).getBody();
	}
}

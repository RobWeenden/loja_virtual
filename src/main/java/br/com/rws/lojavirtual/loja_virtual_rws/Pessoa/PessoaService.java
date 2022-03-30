package br.com.rws.lojavirtual.loja_virtual_rws.Pessoa;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.Email.SendEmailService;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioRepository;

@Service
public class PessoaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private SendEmailService sendEmail;

    @Autowired
    private JdbcTemplate jdbc;

    public PessoaJuridicaModel savePessoaJuridica(PessoaJuridicaModel pessoaJuridica) {

        for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
            pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
            pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
        }

        pessoaJuridica = pessoaRepository.save(pessoaJuridica);
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
            usuarioRepository.inserirRoleAcessoPj(usuarioPj.getId());

            StringBuilder msgHtmlEnvio = new StringBuilder();
            msgHtmlEnvio.append("<b>Segue abaixo seus dados de acesso para sua Loja Virtual</b>");
            msgHtmlEnvio.append("<b>Login: <b>"+pessoaJuridica.getEmail()+"<br>");
            msgHtmlEnvio.append("<b>Senha: <br>"+passwordEnvioEmail+"<br>");
            msgHtmlEnvio.append("Agradecemo a Preferência.");


            try {

                sendEmail.enviarEmailHtml("Acesso gerado para Loja Virtual", msgHtmlEnvio.toString(),
                        pessoaJuridica.getEmail());

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }
        return pessoaJuridica;
    }
}

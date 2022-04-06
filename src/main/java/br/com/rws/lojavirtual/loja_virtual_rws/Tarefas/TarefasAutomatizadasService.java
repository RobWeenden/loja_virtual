package br.com.rws.lojavirtual.loja_virtual_rws.Tarefas;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.rws.lojavirtual.loja_virtual_rws.Email.SendEmailService;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioModel;
import br.com.rws.lojavirtual.loja_virtual_rws.Usuario.UsuarioRepository;

@Component
@Service
public class TarefasAutomatizadasService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SendEmailService sendEmail;

    @Scheduled(initialDelay = 2000, fixedDelay = 86400000)
    //@Scheduled(cron = "0 0 11 * * *", zone="America/Sao_Paulo")
    public void notificarTrocaSenhaUsuario()
            throws UnsupportedEncodingException, MessagingException, InterruptedException {

        List<UsuarioModel> usuarios = usuarioRepository.usuarioDataPasswordVencida();
        for (UsuarioModel usuario : usuarios) {
            StringBuilder msg = new StringBuilder();
            msg.append("Olá ").append(usuario.getPessoa().getNome()).append("<br>");
            msg.append("Sua senha já passou os 90 dias de validade, está na hora de trocar por uma nova senha")
                    .append("<br>");
            msg.append("Troque sua senha da loja virtual RWS Logistica");

            sendEmail.enviarEmailHtml("Troca de Senha", msg.toString(), usuario.getLogin());
            Thread.sleep(3000);

        }
    }
}

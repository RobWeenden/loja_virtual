package br.com.rws.lojavirtual.loja_virtual_rws.Email;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private ConfigEmail configEmail;

    @Bean
    public ConfigEmail configEmail(){
        return new ConfigEmail();
    }


    // private String username = configEmail.getEmail();
    // private String password = configEmail.getPassword();

    /**
     * Realiza a requisição do envio de email
     * 
     * @param assunto
     * @param mensagem
     * @param emailDestino
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @Async
    public void enviarEmailHtml(String assunto, String mensagem, String emailDestino) throws UnsupportedEncodingException, MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "false");
		properties.put("mail.smtp.host", configEmail.getSmtp());
		properties.put("mail.smtp.port", configEmail.getPort());
		properties.put("mail.smtp.socketFactory.port", configEmail.getPort());
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new Authenticator() {
           
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(configEmail.getEmail(), configEmail.getPassword());
			}
        });
        
        session.setDebug(true);
        
        Address[]  addUserForEnvio = InternetAddress.parse(emailDestino);
        Message msgForEnvio = new MimeMessage(session);
        msgForEnvio.setFrom(new InternetAddress(configEmail.getEmail(), "RWS LTDA", "UTF-8"));
        msgForEnvio.addRecipients(Message.RecipientType.TO, addUserForEnvio);
        msgForEnvio.setSubject(assunto);
        msgForEnvio.setContent(msgForEnvio, "text/html; charset=utf-8");

        Transport.send(msgForEnvio);
    }

}

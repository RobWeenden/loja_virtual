package br.com.rws.lojavirtual.loja_virtual_rws.Email;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("configemail")
public class ConfigEmail {

    private String email;
    private String password;
    private String smtp;
    private String port;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

}

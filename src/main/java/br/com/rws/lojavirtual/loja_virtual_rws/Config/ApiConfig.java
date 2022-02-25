package br.com.rws.lojavirtual.loja_virtual_rws.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config_api")
public class ApiConfig {

    private long expirationTime;
    private String secretKey;

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}

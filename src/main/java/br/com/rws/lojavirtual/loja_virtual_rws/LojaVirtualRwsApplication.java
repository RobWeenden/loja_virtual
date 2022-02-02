package br.com.rws.lojavirtual.loja_virtual_rws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "br.com.rws.*")
@ComponentScan(basePackages = { "br.com.rws.*" })
@EnableJpaRepositories(basePackages = { "br.com.rws.*" })
@EnableTransactionManagement
public class LojaVirtualRwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualRwsApplication.class, args);
	}

}

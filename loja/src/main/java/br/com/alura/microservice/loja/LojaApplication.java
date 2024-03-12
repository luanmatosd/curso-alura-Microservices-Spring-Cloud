package br.com.alura.microservice.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class LojaApplication {

	// Ao chamar um serviço usando esse "RestTemplate", por ele ter a anotação de
	// "LoadBalanced"
	// O Spring irá interceptar e direcionar a solicitação para o "serviço de
	// descoberta"
	// Dessa forma não é preciso passar o IP e Porta na URL, e sim, somente o nome
	// do serviço que deseja encontrar no Eureka
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

}

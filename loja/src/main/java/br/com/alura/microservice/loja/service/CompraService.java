package br.com.alura.microservice.loja.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;

// Classe criada com o objetivo de conter as regras de negócio da compra de itens por um usuário

@Service
public class CompraService {

	// Integração entre a Loja e o Fornecedor
	// Loja está fazendo uma requisição para outro Micro serviço, o do Fornecedor
	// Loja está buscando informações de endereço do fornecedor
	public static void realizaCompra(CompraDTO compra) {

		// Criando uma instância do RestTemplate
		RestTemplate client = new RestTemplate();

		// Definindo a URL de destino para a requisição
		String url = "http://localhost:8081/info/" + compra.getEndereco().getEstado();

		// Fazendo uma requisição HTTP GET para a URL especificada
		ResponseEntity<InfoFornecedorDTO> exchange = client.exchange(url, // URL de destino
				HttpMethod.GET, // Método HTTP utilizado (GET neste caso)
				null, // Corpo da requisição (nulo, pois é uma requisição GET)
				InfoFornecedorDTO.class // Tipo esperado para a resposta (InfoFornecedorDTO)
		);

		System.out.println(exchange.getBody().getEndereco());
	}

}

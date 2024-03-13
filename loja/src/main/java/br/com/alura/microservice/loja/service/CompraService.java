package br.com.alura.microservice.loja.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;
import br.com.alura.microservice.loja.model.Compra;

// Interface criada com o objetivo de conter as regras de negócio da compra de itens por um usuário

@Service
public class CompraService {
	
	//Dessa forma, estamos colocando o LOG em cada instância de serviço
	//Ou seja, precisarei acessar instância por instância para poder enxergar os Logs
	//O ideal aqui seria agrupar essa Logs e colocar em um único arquivo
	//Iremos fazer agora, com que cada instância envie seu Log para um servidor central de Logs
	//Para isso iremos usar o Papertrail
	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
	@Autowired
	private FornecedorClient fornecedorClient;

	public Compra realizaCompra(CompraDTO compra) {
		
		final String estado = compra.getEndereco().getEstado();
		
		LOG.info("Buscando informações de fornecedor do {}", estado);
		// Buscando informações do endereço do fornecedor para realizar uma compra
		InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(estado);
		
		
		LOG.info("Realizando um pedido");
		// Fazendo pedido ao fornecedor passando os itens da compra
		// e buscando "id" e "tempo de preparo" do pedido
		InfoPedidoDTO infoPedidoDTO = fornecedorClient.realizaPedido(compra.getItens());
		
		System.out.println(infoFornecedorDTO.getEndereco());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(infoPedidoDTO.getId());
		compraSalva.setTempoDePreparo(infoPedidoDTO.getTempoDePreparo());
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());
		
		return compraSalva;
	}

}

package br.com.alura.microservice.loja.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;
import br.com.alura.microservice.loja.controller.dto.ItemDaCompraDTO;
import br.com.alura.microservice.loja.dto.InfoPedidoDTO;

//Interface criada para substituir o RestTemplate da "CompraService"
//Usando o Feign fica mais fácil de bater no outro micro serviço

@FeignClient("fornecedor")
public interface FornecedorClient {
	
	//Buscando o endereço do fornecedor para realizar uma compra
	@RequestMapping("/info/{estado}")
	InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

	//Buscando id e tempo de preparo do pedido no momento de realizar um pedido ao fornecedor
	@RequestMapping(method = RequestMethod.POST, value = "/pedido")
	InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);
}

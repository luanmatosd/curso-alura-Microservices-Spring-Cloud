package br.com.alura.microservice.loja.controller.dto;

import java.util.List;

//Classe criada para o usuário enviar suas informações de compra
//No ato de compra é precisa enviar o Endereço de entrega, como os itens a ser entregados
public class CompraDTO {

	private List<ItemDaCompraDTO> itens;

	private EnderecoDTO endereco;

	public List<ItemDaCompraDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemDaCompraDTO> itens) {
		this.itens = itens;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

}

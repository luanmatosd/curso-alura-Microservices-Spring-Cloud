package br.com.alura.microservice.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.alura.microservice.loja.client.FornecedorClient;
import br.com.alura.microservice.loja.controller.dto.CompraDTO;
import br.com.alura.microservice.loja.controller.dto.InfoFornecedorDTO;

// Interface criada com o objetivo de conter as regras de negócio da compra de itens por um usuário

@Service
public class CompraService {

	@Autowired
	private FornecedorClient fornecedorClient;

	public void realizaCompra(CompraDTO compra) {

		InfoFornecedorDTO infoFornecedorDTO = fornecedorClient.getInfoPorEstado(compra.getEndereco().getEstado());
		System.out.println(infoFornecedorDTO.getEndereco());
	}

}

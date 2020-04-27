package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.exceptionHandler.NegocioExeption;
import com.example.demo.domain.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	public Cliente salvar (Cliente cliente) {

		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

		if(clienteExistente != null  && clienteExistente.equals(cliente)) {

			throw new NegocioExeption("Já existe um cliente cadastrado com este email.");

		}


		return clienteRepository.save(cliente);

	}

	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

}

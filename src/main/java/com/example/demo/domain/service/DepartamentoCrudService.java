package com.example.demo.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.exceptionHandler.NegocioExeption;
import com.example.demo.domain.model.Departamento;
import com.example.demo.domain.model.StatusDepartamento;
import com.example.demo.repository.DepartamentoRepository;

@Service
public class DepartamentoCrudService {
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public Departamento salvar (Departamento departamento) {
		Departamento departamentoExists = departamentoRepository.findByDescricao(departamento.getDescricao());
		
		if(departamentoExists != null && departamentoExists.equals(departamento)) {
			throw new NegocioExeption("Já existe um departamento com este nome cadastrado");
			
		}
		
		departamento.setStatus(StatusDepartamento.ATIVO);
		departamento.setDataCadastro(OffsetDateTime.now());
		return departamentoRepository.save(departamento);
		
	}

	public void excluir(long departamentoId) {
		departamentoRepository.deleteById(departamentoId);
		
	}
	

}

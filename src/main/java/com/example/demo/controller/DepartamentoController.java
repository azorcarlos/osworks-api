package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Departamento;
import com.example.demo.domain.service.DepartamentoCrudService;
import com.example.demo.repository.DepartamentoRepository;

@RestController
@RequestMapping("/departamento")
public class  DepartamentoController {
	
	@Autowired
	DepartamentoCrudService departamentoCrud;
	
	@Autowired
	DepartamentoRepository departamentoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Departamento adicionar (@Valid @RequestBody Departamento departamento) {
				
		return departamentoCrud.salvar(departamento);
		
	}
	
	@PutMapping("/{departamentoId}")
	public ResponseEntity<Departamento>atualizar(@Valid @PathVariable long departamentoId,
			@RequestBody Departamento departamento){
		
		
		if(!departamentoRepository.existsById(departamentoId)) {
			return ResponseEntity.notFound().build();
		}
		
		departamento.setId(departamentoId);
		departamento = departamentoCrud.salvar(departamento);
		return ResponseEntity.ok(departamento) ;
		
	}
	
	@GetMapping("/{departamentoId}")
	public ResponseEntity<Departamento> buscar(@PathVariable Long departamentoId){
	
		return departamentoRepository.findById(departamentoId)
				.map(get -> ResponseEntity.ok().body(get))
				.orElse(ResponseEntity.notFound().build());
		
		
	}
	
	@GetMapping
	public List<Departamento> listar (){
		return departamentoRepository.findAll();
	}
	
	@DeleteMapping("/{departamentoId}")
	public ResponseEntity<Void> remover(@PathVariable long departamentoId){
		
		if(!departamentoRepository.existsById(departamentoId)) {
			return ResponseEntity.notFound().build();
		}
		
		departamentoCrud.excluir(departamentoId);
		return ResponseEntity.noContent().build();
	}



}

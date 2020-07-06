package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import com.example.demo.api.exceptionHandler.EntidadeNaoEncontradaException;
import com.example.demo.api.exceptionHandler.MyResourceNotFoundException;
import com.example.demo.api.model.VendedorModel;
import com.example.demo.domain.model.Vendedores;
import com.example.demo.repository.DepartamentoRepository;
import com.example.demo.repository.VendedoresRepository;

import javassist.bytecode.stackmap.MapMaker;



@RestController
@RequestMapping("/vendedores")
public class VendedoresController {
	@Autowired
	VendedoresRepository vendedoresRepository;
	@Autowired
	DepartamentoRepository departamentoRepository;
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public VendedorModel save (@Valid @RequestBody Vendedores vendedores) {
		departamentoRepository.findById(vendedores.getDepartamento().getId())
		.orElseThrow(() -> new EntidadeNaoEncontradaException("Departamento não encontrado"));
		return toModel(vendedoresRepository.save(vendedores));
		

	}
	
	@GetMapping
	public List<Vendedores> listar () {
		return vendedoresRepository.findAll();
		
	}
	
	@GetMapping("/{vendedorId}")
	public ResponseEntity<Vendedores> buscar(@PathVariable Long vendedorId){
		
		return vendedoresRepository.findById(vendedorId)
				.map(ret -> ResponseEntity.ok().body(ret))
				.orElse(ResponseEntity.notFound().build());		
	}
	
	
	
	@PutMapping("/{vendedorId}")
	public ResponseEntity<Vendedores> autalizar (@PathVariable Long vendedorId,
			@RequestBody Vendedores vendedores ){
		if(!vendedoresRepository.existsById(vendedorId)) {
			return ResponseEntity.notFound().build();
		}
		
		if(!departamentoRepository.existsById(vendedores.getDepartamento().getId())) {
			//throw new EntityExistsException("Deparetamento não encontrado!");
			throw new MyResourceNotFoundException("Teste");
		}
		
		vendedores.setId(vendedorId);
		vendedores = vendedoresRepository.save(vendedores);
		return ResponseEntity.ok(vendedores);
	}
	
	@DeleteMapping("/{vendedorId}")
	public ResponseEntity<Void> excluir(@PathVariable Long vendedorId ){
		if(!vendedoresRepository.existsById(vendedorId)) {
			return ResponseEntity.notFound().build();
		}
		vendedoresRepository.deleteById(vendedorId);
		return ResponseEntity.noContent().build();
		
		
	}
	
	private VendedorModel toModel(Vendedores vendedores) {
		return modelMapper.map(vendedores, VendedorModel.class);
		
	}
	
	private List<VendedorModel> toCollectionModel(List<Vendedores> vendedores){
		return vendedores.stream()
				.map(vendedor->toModel(vendedor))
				.collect(Collectors.toList());
		
	}
	


	
}

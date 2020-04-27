package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.exceptionHandler.EntidadeNaoEncontradaException;
import com.example.demo.api.model.ComentarioInput;
import com.example.demo.api.model.ComentarioModel;
import com.example.demo.domain.model.Comentario;
import com.example.demo.domain.model.OrdemServico;
import com.example.demo.domain.service.GestaoOrdemServicoService;
import com.example.demo.repository.OrdemServicoRepository;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
		
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(()->new EntidadeNaoEncontradaException("Ordem de serviço não encontrada - Comentários"));
		return toCollectionModel(ordemServico.getComentarios());
		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel acicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInput comentarioInput) {
				
		Comentario comentario = gestaoOrdemServico.criarComentario(ordemServicoId, comentarioInput.getDescricao());
		
		return toModel(comentario);
					
		
	}
	
	private ComentarioModel toModel (Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios){
		return comentarios.stream()
				.map(comentario->toModel(comentario))
				.collect(Collectors.toList());
	}

}

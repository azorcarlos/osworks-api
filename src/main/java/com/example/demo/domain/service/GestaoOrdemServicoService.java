package com.example.demo.domain.service;

import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.exceptionHandler.EntidadeNaoEncontradaException;
import com.example.demo.api.exceptionHandler.NegocioExeption;
import com.example.demo.domain.model.Cliente;
import com.example.demo.domain.model.Comentario;
import com.example.demo.domain.model.OrdemServico;
import com.example.demo.domain.model.StatusOrdemServico;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ComentarioRepository;
import com.example.demo.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRerpository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar (OrdemServico ordemServico) {
		

		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(()->new NegocioExeption("Cliente não encontrado"));
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		return ordemServicoRerpository.save(ordemServico);
	
	}
	
	public Comentario criarComentario (Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDateEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		return comentarioRepository.save(comentario);
		
		
	}
	
	public void finalizar (Long ordemServicoId) {
		
		OrdemServico ordemServico = buscar(ordemServicoId);
		ordemServico.finalizar();
		ordemServicoRerpository.save(ordemServico);
		
	 
		
	}

	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRerpository.findById(ordemServicoId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("Ordem de servico não encontrada"));
	}
	

}

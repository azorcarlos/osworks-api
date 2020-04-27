package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long > {
	List<Comentario> findByIdContaining(Long id);
	
}

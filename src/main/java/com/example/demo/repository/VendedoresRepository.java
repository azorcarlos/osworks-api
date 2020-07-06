package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Vendedores;

public interface VendedoresRepository  extends JpaRepository<Vendedores, Long>{

}

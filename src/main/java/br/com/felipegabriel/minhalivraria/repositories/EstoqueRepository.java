package br.com.felipegabriel.minhalivraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.felipegabriel.minhalivraria.models.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer>{

}

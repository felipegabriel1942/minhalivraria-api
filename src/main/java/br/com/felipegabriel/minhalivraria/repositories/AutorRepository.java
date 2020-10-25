package br.com.felipegabriel.minhalivraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.felipegabriel.minhalivraria.models.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer>{

}

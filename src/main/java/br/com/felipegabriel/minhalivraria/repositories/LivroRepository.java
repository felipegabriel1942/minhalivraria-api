package br.com.felipegabriel.minhalivraria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.felipegabriel.minhalivraria.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	
	@Query(value = "SELECT l FROM Livro l WHERE l.fkCategoria = ?1")
	public List<Livro> getLivrosByCategoria(Integer pkCategoria);
}

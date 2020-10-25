package br.com.felipegabriel.minhalivraria.service;

import java.util.List;
import java.util.Optional;

import br.com.felipegabriel.minhalivraria.models.Livro;

public interface LivroService {

	Livro save(Livro livro);
	
	Optional<Livro> getByPkLivro(Integer pkLivro);
	
	void delete(Integer pkLivro);
	
	Livro update(Livro livro);
	
	List<Livro> getAll();
	
	List<Livro> getByCategoria(Integer fkCategoria);
	
	
}

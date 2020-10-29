package br.com.felipegabriel.minhalivraria.service;

import java.util.List;
import java.util.Optional;

import br.com.felipegabriel.minhalivraria.models.Livro;
import br.com.felipegabriel.minhalivraria.projections.LivrosForLoja;

public interface LivroService {

	Livro save(Livro livro);
	
	Optional<Livro> getByPkLivro(Integer pkLivro);
	
	void delete(Livro livro);
	
	Livro update(Livro livro);
	
	List<Livro> getAll();
	
	List<Livro> getByCategoria(Integer fkCategoria);
	
	List<LivrosForLoja> getLivrosForLoja();
	
	
}

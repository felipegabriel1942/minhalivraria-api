package br.com.felipegabriel.minhalivraria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.felipegabriel.minhalivraria.models.Livro;
import br.com.felipegabriel.minhalivraria.projections.LivrosForLoja;
import br.com.felipegabriel.minhalivraria.repositories.LivroRepository;
import br.com.felipegabriel.minhalivraria.service.LivroService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LivroServiceImpl implements LivroService {
	
	private final LivroRepository repository;

	@Override
	public Livro save(Livro livro) {
		return repository.save(livro);
	}

	@Override
	public Optional<Livro> getByPkLivro(Integer pkLivro) {
		return repository.findById(pkLivro);
	}

	@Override
	public void delete(Livro livro) {
		repository.delete(livro);
	}

	@Override
	public Livro update(Livro livro) {
		
		if(livro == null || livro.getPkLivro() == null) {
			throw new IllegalArgumentException("Primary Key do livro não pode ser nula.");
		}
		
		return repository.save(livro);
	}

	@Override
	public List<Livro> getAll() {
		return repository.findAll();
	}

	@Override
	public List<Livro> getByCategoria(Integer fkCategoria) {
		return repository.getLivrosByCategoria(fkCategoria);
	}

	@Override
	public List<LivrosForLoja> getLivrosForLoja() {
		return repository.getLivrosForLoja();
	}

}

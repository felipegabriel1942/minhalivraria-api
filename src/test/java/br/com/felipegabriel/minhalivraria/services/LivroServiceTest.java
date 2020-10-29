package br.com.felipegabriel.minhalivraria.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.felipegabriel.minhalivraria.models.Livro;
import br.com.felipegabriel.minhalivraria.projections.LivrosForLoja;
import br.com.felipegabriel.minhalivraria.repositories.LivroRepository;
import br.com.felipegabriel.minhalivraria.repositories.LivroRepositoryTest;
import br.com.felipegabriel.minhalivraria.service.LivroService;
import br.com.felipegabriel.minhalivraria.service.impl.LivroServiceImpl;

@ExtendWith(SpringExtension.class)
public class LivroServiceTest {
	
	LivroService service;
	
	@MockBean
	LivroRepository repository;
	
	@BeforeEach
	public void setUp() {
		service = new LivroServiceImpl(repository);
	}
	
	@Test
	@DisplayName("Deve salvar um livro.")
	public void saveLivroTest() {
		
		Livro livro = LivroRepositoryTest.criarNovoLivro1();
		Livro livroSalvo = LivroRepositoryTest.criarNovoLivro1();
		livroSalvo.setPkLivro(1);
		
		Mockito.when(repository.save(livro)).thenReturn(livroSalvo);
		
		Livro savedLivro = service.save(livro);
		
		assertThat(savedLivro.getPkLivro()).isNotNull();
	}
	
	@Test
	@DisplayName("Deve buscar um livro por pk.")
	public void getByPkLivroTest() {
		
		Integer pkLivro = 1;
		Livro livro = LivroRepositoryTest.criarNovoLivro1();
		livro.setPkLivro(pkLivro);
		
		Mockito.when(repository.findById(pkLivro)).thenReturn(Optional.of(livro));
		
		Optional<Livro> foundLivro = service.getByPkLivro(pkLivro);
		
		assertThat(foundLivro.isPresent()).isTrue();
		
	}
	
	@Test
	@DisplayName("Deve deletar um livro")
	public void deleteLivroTest() {
		
		Livro livro = Livro.builder().pkLivro(1).build();
		
		assertDoesNotThrow(() -> service.delete(livro));
		
		Mockito.verify(repository, Mockito.times(1)).delete(livro);
	}
	
	@Test
	@DisplayName("Deve atualizar um livro")
	public void updateLivroTest() {
		
		Integer pkLivro = 1;
		
		Livro updatingLivro = Livro.builder().pkLivro(pkLivro).build();

		Livro updatedLivro = LivroRepositoryTest.criarNovoLivro1();
		updatedLivro.setPkLivro(pkLivro);
		
		Mockito.when(repository.save(updatingLivro)).thenReturn(updatedLivro);
		
		Livro livro = service.update(updatingLivro);
		
		assertThat(livro).isEqualTo(updatedLivro);

	}
	
	@Test
	@DisplayName("Deve buscar todos os livros cadastrados")
	public void getAllLivrosTest() {
		
		Livro livro1 = LivroRepositoryTest.criarNovoLivro1();
		Livro livro2 = LivroRepositoryTest.criarNovoLivro2();
		
		List<Livro> listaLivros = new ArrayList<>();
		listaLivros.add(livro1);
		listaLivros.add(livro2);
		
		when(repository.findAll()).thenReturn(listaLivros);
		
		List<Livro> result = service.getAll();
		
		assertThat(result).hasSize(2);
	}
	
	@Test
	@DisplayName("Deve buscar todos os livros cadastrados de uma determinada categoria")
	public void getByCategoriaTest() {
		Livro livro1 = LivroRepositoryTest.criarNovoLivro1();
		
		List<Livro> listaLivros = new ArrayList<>();
		listaLivros.add(livro1);

		when(repository.getLivrosByCategoria(1)).thenReturn(listaLivros);
		
		List<Livro> result = service.getByCategoria(1);
		
		assertThat(result).hasSize(1);
	}
		
	
	@Test
	@DisplayName("Deve buscar livros para exibição na loja.")
	public void getLivrosForLojaTest() {
		
		List<LivrosForLoja> livros = new ArrayList<LivrosForLoja>();
		
		LivrosForLoja livro = new LivrosForLoja() {
			
			@Override
			public String getTitulo() {
				return "Conan";
			}
			
			@Override
			public Integer getQuantidade() {
				return 2;
			}
			
			@Override
			public Double getPreco() {
				return 100.00;
			}
			
			@Override
			public Integer getPkLivro() {
				return 1;
			}
			
			@Override
			public String getImagem() {
				return "imagem";
			}
			
			@Override
			public String getCategoria() {
				return "Ação";
			}
			
			@Override
			public String getAutor() {
				return "Felipe";
			}
		};
		
		livros.add(livro);
		
		when(repository.getLivrosForLoja()).thenReturn(livros);
		
		List<LivrosForLoja> result = service.getLivrosForLoja();
		
		assertThat(result).hasSize(1);
	}
}

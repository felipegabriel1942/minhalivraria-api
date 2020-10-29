package br.com.felipegabriel.minhalivraria.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.felipegabriel.minhalivraria.models.Autor;
import br.com.felipegabriel.minhalivraria.models.Categoria;
import br.com.felipegabriel.minhalivraria.models.Estoque;
import br.com.felipegabriel.minhalivraria.models.Livro;
import br.com.felipegabriel.minhalivraria.projections.LivrosForLoja;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class LivroRepositoryTest {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	LivroRepository repository;
	
	@Test
	@DisplayName("Deve salvar um livro")
	public void saveLivroTest() {
		Livro livro = criarNovoLivro1();
		entityManager.persist(livro);
		
		boolean exists = repository.findById(livro.getPkLivro()).isPresent();
		
		assertThat(exists).isTrue();
	}
	
	@Test
	@DisplayName("Deve deletar um livro")
	public void deleteLivroTest() {
		Livro livro = criarNovoLivro1();
		Livro savedLivro = entityManager.persist(livro);
		
		repository.delete(savedLivro);
		Livro deletedLivro = entityManager.find(Livro.class, livro.getPkLivro());
		
		assertThat(deletedLivro).isNull();
		
	}
	
	@Test
	@DisplayName("Deve buscar livro por PK")
	public void findLivroByPkTest() {
		Livro livro = criarNovoLivro1();
		Livro savedLivro = entityManager.persist(livro);
		
		Livro foundLivro = repository.findById(savedLivro.getPkLivro()).orElse(null);
		
		assertThat(foundLivro).isNotNull();
	}
	
	@Test
	@DisplayName("Deve buscar todos os livros")
	public void getAllTest() {
		Livro livro1 = criarNovoLivro1();
		Livro livro2 = criarNovoLivro2();
		
		List<Livro> livros = new ArrayList<Livro>();
		livros.add(livro1);
		livros.add(livro2);
		
		livros.forEach(l -> entityManager.persist(l));
		
		List<Livro> savedLivros = repository.findAll();
		
		assertThat(savedLivros).hasSize(2);
		
	}
		
	@Test
	@DisplayName("Deve buscar todos os livros que possuam determinada categoria.")
	public void getLivrosByCategoriaTest() {
		Livro livro1 = criarNovoLivro1();
		Livro livro2 = criarNovoLivro2();
		
		List<Livro> livros = new ArrayList<Livro>();
		livros.add(livro1);
		livros.add(livro2);
		
		livros.forEach(l -> entityManager.persist(l));
		
		List<Livro> result = repository.getLivrosByCategoria(1);
		
		assertThat(result).hasSize(1);
		assertThat(result.get(0).getFkCategoria()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Deve trazer as informações necessarias para criar a tela de seleção de livros da loja.")
	public void getLivrosForLojaTest() {
		
		Livro livro = criarNovoLivro1();
		
		Autor autor = criarAutor1();
		
		Categoria categoria = criarCategoria1();
		
		Estoque estoque = criarEstoque1();
		
		entityManager.persist(livro);
		entityManager.persist(autor);
		entityManager.persist(categoria);
		entityManager.persist(estoque);
		
		List<LivrosForLoja> livros = repository.getLivrosForLoja();
		
		assertThat(livros).hasSize(1);
	}
	
	public static Livro criarNovoLivro1() {
		return Livro.builder()
				.titulo("Conan - o barbaro")
				.preco(79.99)
				.fkCategoria(1)
				.fkAutor(1)
				.dataCadastro(LocalDate.now())
				.imagem("imagem")
				.resumo("Um livro sobre um barbaro.").build();	
	}
	
	public static Autor criarAutor1() {
		return Autor.builder().nome("Felipe").build();
	}
	
	public static Categoria criarCategoria1() {
		return Categoria.builder().descricao("Aventura").build();
	}
	
	public static Estoque criarEstoque1() {
		return Estoque.builder().fkLivro(4).quantidade(2).build();
	}
	
	public static Livro criarNovoLivro2() {
		return Livro.builder()
				.titulo("1984")
				.preco(66.99)
				.fkCategoria(2)
				.fkAutor(2)
				.dataCadastro(LocalDate.now())
				.imagem("imagem")
				.resumo("Um livro sobre os males do totalitarismo.").build();	
	}
}

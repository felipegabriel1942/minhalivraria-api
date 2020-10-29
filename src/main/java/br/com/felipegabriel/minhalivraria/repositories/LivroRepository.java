package br.com.felipegabriel.minhalivraria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.felipegabriel.minhalivraria.models.Livro;
import br.com.felipegabriel.minhalivraria.projections.LivrosForLoja;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer>{
	
	@Query(value = "SELECT l FROM Livro l WHERE l.fkCategoria = ?1")
	public List<Livro> getLivrosByCategoria(Integer fkCategoria);
	
	@Query(value = "SELECT \r\n" + 
			"	livro.pklivro as pkLivro, \r\n" + 
			"	livro.imagem as imagem, \r\n" + 
			"	livro.titulo as titulo, \r\n" + 
			"	livro.preco as preco, \r\n" + 
			"	autor.nome as autor, \r\n" + 
			"	estoque.quantidade as quantidade, \r\n" + 
			"	categoria.descricao as categoria \r\n" + 
			"FROM livro \r\n" + 
			"INNER JOIN categoria ON livro.fkcategoria = categoria.pkcategoria \r\n" + 
			"INNER JOIN autor ON livro.fkautor = autor.pkautor \r\n" + 
			"LEFT JOIN estoque ON livro.pklivro = estoque.fklivro ", nativeQuery = true)
	public List<LivrosForLoja> getLivrosForLoja();
}

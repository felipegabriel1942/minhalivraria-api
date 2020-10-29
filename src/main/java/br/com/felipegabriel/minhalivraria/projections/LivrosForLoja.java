package br.com.felipegabriel.minhalivraria.projections;

public interface LivrosForLoja {
	
	Integer getPkLivro();
	
	String getImagem();
	
	String getTitulo();
	
	Double getPreco();
	
	String getAutor();
	
	Integer getQuantidade();
	
	String getCategoria();
	
}

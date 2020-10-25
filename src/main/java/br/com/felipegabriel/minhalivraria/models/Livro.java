package br.com.felipegabriel.minhalivraria.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "livro")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pklivro", nullable = false)
	private Integer pkLivro;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	private Double preco;
	
	@Column(name = "fkautor", nullable = false)
	private Integer fkAutor;
	
	@Column(name = "fkcategoria", nullable = false)
	private Integer fkCategoria;
	
	@Column(name = "datatcadastro", nullable = false)
	private LocalDate dataCadastro;
	
	@Column(nullable = false)
	private String imagem;
	
	@Column(nullable = false)
	private String resumo;
}

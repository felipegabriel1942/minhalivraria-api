package br.com.felipegabriel.minhalivraria.models;

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
@Table(name = "estoque")
public class Estoque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pkestoque", nullable = false)
	private Integer pkEstoque;
	
	@Column(name = "fklivro", nullable = false)
	private Integer fkLivro;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
}

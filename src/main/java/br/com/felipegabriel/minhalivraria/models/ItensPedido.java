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
@Table(name = "itens_pedido")
public class ItensPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pkitempedido", nullable = false)
	private Integer pkItemPedido;
	
	@Column(name = "fklivro", nullable = false)
	private Integer fkLivro;
	
	@Column(name = "fkpedido", nullable = false)
	private Integer fkPedido;
	
	@Column(nullable = false)
	private Integer quantidade;
	
}

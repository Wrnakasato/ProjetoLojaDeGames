package com.games.gameswel.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_produtos") 
public class ProdutosGames {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Id;
	
	
	@NotBlank (message = "nome")
	private String nomeJogo;
	

	@JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "Preço é obrigatório!")
    @Positive(message = "O preço deve ser maior do que zero!")
    private BigDecimal preco;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private CategoriaGames categoria;
    
	
	public CategoriaGames getCategoria() {
		return categoria;
	}


	public void setCategoria(CategoriaGames categoria) {
		this.categoria = categoria;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}

	public String getNomejogo() {
		return nomeJogo;
	}


	public void setNomejogo(String nomejogo) {
		nomeJogo = nomejogo;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	
	
}

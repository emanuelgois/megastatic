package com.megastatics.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.megastatics.domain.Categoria;

public class CategoriaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5720529143178280063L;
	
	private Integer codigo;
	@NotEmpty(message = "Campo obrigatório")
	@Length(min=5, max =80, message = "O Tamanho do campo deve ter entre 5 e 80 catacteres!" )
	private String nome;
	
	public CategoriaDTO() {
		// TODO Auto-generated constructor stub
	}
	

	public CategoriaDTO(Categoria categoria) {
		this.codigo = categoria.getCodigo();
		this.nome = categoria.getNome();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}

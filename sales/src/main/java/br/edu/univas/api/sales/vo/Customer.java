package br.edu.univas.api.sales.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

	private Integer id;
	private String cpf;
	private String nome;
	private String email;
	
}

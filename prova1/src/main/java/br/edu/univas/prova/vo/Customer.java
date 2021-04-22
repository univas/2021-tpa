package br.edu.univas.prova.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

	private long cpf;
	private String nome;
	private Date dataNascimento;
	private Long cpfTitular;
	
}

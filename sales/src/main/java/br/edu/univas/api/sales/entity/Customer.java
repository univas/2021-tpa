package br.edu.univas.api.sales.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Generated
public class Customer {

	@Id
	private Integer id;
	private String cpf;
	private String nome;
	private String email;
	
}

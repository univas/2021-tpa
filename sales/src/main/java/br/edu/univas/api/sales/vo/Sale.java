package br.edu.univas.api.sales.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sale {

	private Integer id;
	private Integer customerId;
	private Integer productId;
	private Date date;
	private int quantity;
	
}

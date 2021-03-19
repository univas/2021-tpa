package br.edu.univas.api.sales.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.edu.univas.api.sales.vo.Sale;

@Repository
public class SaleRepository {

	private Map<Integer, Sale> my_database = new HashMap<>();
	
	public void create(Sale sale) {
		my_database.put(sale.getId(), sale);
	}
	
	public Collection<Sale> list() {
		return my_database.values();
	}
	
	public Sale getById(Integer id) {
		return my_database.get(id);
	}
	
	public void update(Sale sale) {
		my_database.put(sale.getId(), sale);
	}
	
	public void delete(Integer id) {
		my_database.remove(id);
	}
}

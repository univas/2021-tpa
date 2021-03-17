package br.edu.univas.api.sales.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.edu.univas.api.sales.vo.Product;

@Repository
public class ProductRepository {

	private Map<Integer, Product> my_database = new HashMap<>();

	public void create(Product product) {
		my_database.put(product.getId(), product);
	}
	
	public Collection<Product> list() {
		return my_database.values();
	}
	
	public Product getById(Integer id) {
		return my_database.get(id);
	}
	
	public void update(Product product) {
		my_database.put(product.getId(), product);
	}
	
	public void delete(Integer id) {
		my_database.remove(id);
	}
}


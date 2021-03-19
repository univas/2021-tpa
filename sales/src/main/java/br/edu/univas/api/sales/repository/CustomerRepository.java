package br.edu.univas.api.sales.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.edu.univas.api.sales.vo.Customer;

@Repository
public class CustomerRepository {
	
	private Map<Integer, Customer> my_database = new HashMap<>();

	public void create(Customer customer) {
		my_database.put(customer.getId(), customer);
	}
	
	public Collection<Customer> list() {
		return my_database.values();
	}
	
	public Customer getById(Integer id) {
		return my_database.get(id);
	}
	
	public void update(Customer customer) {
		my_database.put(customer.getId(), customer);
	}
	
	public void delete(Integer id) {
		my_database.remove(id);
	}
}

package br.edu.univas.prova.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.edu.univas.prova.vo.Customer;

@Repository
public class CustomerRepository {

	private Map<Long, Customer> my_database = new HashMap<>();
		
	public void create(Customer customer) {
		my_database.put(customer.getCpf(), customer);
	}
	
	public List<Customer> listAll() {
		return my_database.values().stream().collect(Collectors.toList());
	}

	public Optional<Customer> getByCpf(Long cpf) {
		if (my_database.containsKey(cpf)) {
			return Optional.of(my_database.get(cpf));
		}
		
		return Optional.empty();
	}

	public void update(Customer customer) {
		my_database.put(customer.getCpf(), customer);		
	}
	
	public void delete(Long cpf) {
		List<Customer> customers = my_database.values().stream()
				.filter(customer -> cpf.equals(customer.getCpfTitular()))
				.collect(Collectors.toList());
		
		for (Customer customer : customers) {
			my_database.remove(customer.getCpf());
		}
		
		my_database.remove(cpf);
	}

	public List<Customer> listDependents(Long cpfTitular) {
		return my_database.values().stream()
				.filter(customer -> cpfTitular.equals(customer.getCpfTitular()))
				.collect(Collectors.toList());
	}
	
}

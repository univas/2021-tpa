package br.edu.univas.api.sales.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.api.sales.repository.CustomerRepository;
import br.edu.univas.api.sales.vo.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;
	
	@GetMapping
	public ResponseEntity<Collection<Customer>> listCustomers() {
		if (repository.list().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(repository.list());
	}
	
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		repository.create(customer);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Integer id, @RequestBody Customer customer) {
		Customer oldCustomer = repository.getById(id);
		if (oldCustomer != null) {
			customer.setId(id);
			repository.update(customer);
			return ResponseEntity.ok(customer);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> delete(@PathVariable Integer id) {
		Customer customer = repository.getById(id);
		if (customer != null) {
			repository.delete(id);
			return ResponseEntity.ok(customer);
		}
		
		return ResponseEntity.notFound().build();
	}
}

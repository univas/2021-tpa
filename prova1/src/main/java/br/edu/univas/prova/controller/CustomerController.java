package br.edu.univas.prova.controller;

import java.util.List;
import java.util.Optional;

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

import br.edu.univas.prova.repository.CustomerRepository;
import br.edu.univas.prova.vo.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		customerRepository.create(customer);
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> listAll() {
		List<Customer> customers = customerRepository.listAll();
		return ResponseEntity.ok(customers);
	}
	
	@PutMapping("/{cpf}")
	public ResponseEntity<Customer> update(@PathVariable Long cpf, @RequestBody Customer customer) {
		Optional<Customer> oldCustomer = customerRepository.getByCpf(cpf);
		if (oldCustomer.isPresent()) {
			customer.setCpf(cpf);
			customerRepository.update(customer);
			return ResponseEntity.ok(customer);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Customer> getByCpf(@PathVariable Long cpf) {
		Optional<Customer> customer = customerRepository.getByCpf(cpf);
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Customer> delete(@PathVariable Long cpf) {
		Optional<Customer> customer = customerRepository.getByCpf(cpf);
		if (customer.isPresent()) {
			customerRepository.delete(cpf);
			return ResponseEntity.ok(customer.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{cpf}/dependents")
	public ResponseEntity<List<Customer>> listDependentsByTitular(@PathVariable(name = "cpf") Long cpfTitular) {
		List<Customer> dependents = customerRepository.listDependents(cpfTitular);
		return ResponseEntity.ok(dependents);
	}
}

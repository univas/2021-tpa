package br.edu.univas.api.sales.controller;

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

import br.edu.univas.api.sales.entity.Customer;
import br.edu.univas.api.sales.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/test")
	public ResponseEntity<String> sayHello() {
		return ResponseEntity.ok("Hello World from Github Action!!!");
	}
	
	@GetMapping()
	public ResponseEntity<List<Customer>> listAll() {
		return ResponseEntity.ok(customerService.listAll());
	}
	
	@PostMapping
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
		customerService.save(customer);
		return ResponseEntity.ok(customer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@PathVariable Integer id, @RequestBody Customer customer) {
		Optional<Customer> oldCustomer = customerService.findById(id);
		if (oldCustomer.isPresent()) {
			customer.setId(id);
			customerService.save(customer);
			return ResponseEntity.ok(customer);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> delete(@PathVariable Integer id) {
		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent()) {
			customerService.delete(id);
			return ResponseEntity.ok(customer.get());
		}
		
		return ResponseEntity.notFound().build();
	}
}

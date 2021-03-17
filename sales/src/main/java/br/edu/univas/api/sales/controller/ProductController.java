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

import br.edu.univas.api.sales.repository.ProductRepository;
import br.edu.univas.api.sales.vo.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository repository;
	
	@GetMapping
	public ResponseEntity<Collection<Product>> listProducts() {		
		return ResponseEntity.ok(repository.list());
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		repository.create(product);
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Integer id, 
			@RequestBody Product product) {
		Product oldProduct = repository.getById(id);
		if (oldProduct != null) {
			product.setId(id);
			repository.update(product);
			return ResponseEntity.ok(product);
		}
		
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable Integer id) {
		Product product = repository.getById(id);
		if (product != null) {
			repository.delete(id);
			return ResponseEntity.ok(product);
		}
		
		return ResponseEntity.notFound().build();
	}
}

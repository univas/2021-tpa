package br.edu.univas.api.sales.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.univas.api.sales.vo.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

	@GetMapping
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			product.setDescription("Product " + i);
			product.setId(i);
			product.setPrice(i * 4.435f);
			products.add(product);
		}
		
		return ResponseEntity.ok(products);
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		System.out.println("Produto recebido:");
		System.out.println(product.getDescription());
		System.out.println(product.getPrice());
		System.out.println(product.getId());
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Integer id, 
			@RequestBody Product product) {
		System.out.println("ID do produto: " + id);
		System.out.println("Produto recebido para atualização:");
		System.out.println(product.getDescription());
		System.out.println(product.getPrice());
		System.out.println(product.getId());
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> delete(@PathVariable Integer id) {
		System.out.println("ID do produto para deletar: " + id);
		return ResponseEntity.ok(new Product());
	}
}

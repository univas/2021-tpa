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

import br.edu.univas.api.sales.repository.SaleRepository;
import br.edu.univas.api.sales.vo.Sale;

@RestController
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleRepository repository;
	
	@GetMapping
	public ResponseEntity<Collection<Sale>> listSales() {
		return ResponseEntity.ok(repository.list());
	}
	
	@PostMapping
	public ResponseEntity<Sale> create(@RequestBody Sale sale) {
		repository.create(sale);
		return ResponseEntity.ok(sale);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Sale> update(
			@PathVariable Integer id, @RequestBody Sale sale) {
		Sale oldSale = repository.getById(id);
		if (oldSale != null) {
			sale.setId(id);
			repository.update(sale);
			return ResponseEntity.ok(sale);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Sale> delete(@PathVariable Integer id) {
		Sale sale = repository.getById(id);
		if (sale != null) {
			repository.delete(id);
			return ResponseEntity.ok(sale);
		}
		
		return ResponseEntity.notFound().build();
	}
	
}

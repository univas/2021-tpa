package br.edu.univas.api.sales.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.univas.api.sales.entity.Customer;
import br.edu.univas.api.sales.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> listAll() {
		return StreamSupport.stream(
				customerRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}
	
}

package br.edu.univas.api.sales.repository;

import org.springframework.data.repository.CrudRepository;

import br.edu.univas.api.sales.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}

package br.edu.univas.api.sales.controller;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import br.edu.univas.api.sales.repository.CustomerRepository;
import br.edu.univas.api.sales.vo.Customer;

@SpringBootTest
public class CustomerControllerTest {

	@Mock
	private CustomerRepository repository;
	
	@InjectMocks
	private CustomerController controller;
	
	@Test
	public void listCustomers_whenDatabaseHasFiveCustomers_shouldReturnListWithFiveCustomers() {
		List<Customer> customerList = List.of(new Customer(), new Customer(),
				new Customer(), new Customer(),new Customer());
		
		Mockito.when(repository.list()).thenReturn(customerList);
		
		ResponseEntity<Collection<Customer>> responseEntity = controller.listCustomers();
		
		Assertions.assertEquals(5, responseEntity.getBody().size());
		
		Mockito.verify(repository, Mockito.times(1)).list();
	}
	
	@Test
	public void create_whenPassValidCustomer_shouldCallCreateFromRepository() {
		Customer customer = new Customer();
		customer.setId(123);
		customer.setCpf("123456");
		customer.setEmail("email@email.com");
		customer.setNome("Nome");

		ResponseEntity<Customer> responseEntity = controller.create(customer);
		
		Customer customerReturned = responseEntity.getBody();
		Assertions.assertNotNull(customerReturned);
		Assertions.assertEquals(customer.getId(), customerReturned.getId());
		Assertions.assertEquals(customer.getCpf(), customerReturned.getCpf());
		Assertions.assertEquals(customer.getEmail(), customerReturned.getEmail());
		Assertions.assertEquals(customer.getNome(), customerReturned.getNome());
		
		Mockito.verify(repository, Mockito.times(1)).create(customer);
	}
	
}

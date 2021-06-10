package br.edu.univas.api.sales.controller;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.univas.api.sales.repository.OldCustomerRepository;
import br.edu.univas.api.sales.vo.Customer;

@SpringBootTest
public class CustomerControllerTest {

	@Mock
	private OldCustomerRepository repository;
	
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
	
	@Test
	public void update_whenPassValidId_shouldUpdateCustomer() {
		Integer customerId = 123;

		Customer customer = new Customer();
		customer.setId(999);
		customer.setCpf("123456");
		customer.setEmail("email@email.com");
		customer.setNome("Nome");
		
		Mockito.when(repository.getById(customerId)).thenReturn(new Customer());
		
		ResponseEntity<Customer> responseEntity = controller.update(customerId, customer);
		
		Customer customerReturned = responseEntity.getBody();
		Assertions.assertNotNull(customerReturned);
		Assertions.assertEquals(customerId, customerReturned.getId());
		
		Mockito.verify(repository, Mockito.times(1)).getById(customerId);
		Mockito.verify(repository, Mockito.times(1)).update(customer);				
	}
	
	@Test
	public void update_whenPassInvalidId_shouldNotCallUpdateMethod() {
		Integer customerId = 123;
		
		Customer customer = new Customer();
		
		Mockito.when(repository.getById(customerId)).thenReturn(null);
		
		ResponseEntity<Customer> responseEntity = controller.update(customerId, customer);
		
		Assertions.assertNull(responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		
		Mockito.verify(repository, Mockito.times(1)).getById(customerId);
		Mockito.verify(repository, Mockito.never()).update(customer);
	}
	
	@Test
	public void delete_whenPassValidId_shouldDeleteCustomer() {
		Integer customerId = 123;
		
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setNome("Nome");
		customer.setEmail("Email@email.com");
		customer.setCpf("12345678999");
		
		Mockito.when(repository.getById(customerId)).thenReturn(customer);
		
		ResponseEntity<Customer> customerReturned = controller.delete(customerId);
		
		Assertions.assertEquals(customer, customerReturned.getBody());
		
		Mockito.verify(repository, Mockito.times(1)).getById(customerId);
		Mockito.verify(repository, Mockito.times(1)).delete(customerId);
	}
	
	@Test
	public void delete_whenPassInvalidId_shouldNotCallDeleteMethod() {
		Integer customerId = 123;
		
		Mockito.when(repository.getById(customerId)).thenReturn(null);
		
		ResponseEntity<Customer> customerReturned = controller.delete(customerId);
		
		Assertions.assertNull(customerReturned.getBody());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, customerReturned.getStatusCode());
		
		Mockito.verify(repository, Mockito.times(1)).getById(customerId);
		Mockito.verify(repository, Mockito.never()).delete(customerId);
	}
}

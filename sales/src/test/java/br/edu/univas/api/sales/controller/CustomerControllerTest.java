package br.edu.univas.api.sales.controller;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.univas.api.sales.entity.Customer;
import br.edu.univas.api.sales.service.CustomerService;

@SpringBootTest
public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController controller;
	
	@Test
	public void listCustomers_whenDatabaseHasFiveCustomers_shouldReturnListWithFiveCustomers() {
		List<Customer> customerList = List.of(new Customer(), new Customer(),
				new Customer(), new Customer(),new Customer());
		
		Mockito.when(customerService.listAll()).thenReturn(customerList);
		
		ResponseEntity<List<Customer>> responseEntity = controller.listAll();
		
		Assertions.assertEquals(5, responseEntity.getBody().size());
		
		Mockito.verify(customerService, Mockito.times(1)).listAll();
	}
	
	@Test
	public void create_whenPassValidCustomer_shouldCallCreateFromService() {
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
		
		Mockito.verify(customerService, Mockito.times(1)).save(customer);
	}
	
	@Test
	public void update_whenPassValidId_shouldUpdateCustomer() {
		Integer customerId = 123;

		Customer customer = new Customer();
		customer.setId(999);
		customer.setCpf("123456");
		customer.setEmail("email@email.com");
		customer.setNome("Nome");
		
		Optional<Customer> optCustomer = Optional.of(new Customer());
		
		Mockito.when(customerService.findById(customerId)).thenReturn(optCustomer);
		
		ResponseEntity<Customer> responseEntity = controller.update(customerId, customer);
		
		Customer customerReturned = responseEntity.getBody();
		Assertions.assertNotNull(customerReturned);
		Assertions.assertEquals(customerId, customerReturned.getId());
		
		Mockito.verify(customerService, Mockito.times(1)).findById(customerId);
		Mockito.verify(customerService, Mockito.times(1)).save(customer);				
	}
	
	@Test
	public void update_whenPassInvalidId_shouldNotCallUpdateMethod() {
		Integer customerId = 123;
		
		Customer customer = new Customer();
		
		Mockito.when(customerService.findById(customerId)).thenReturn(Optional.empty());
		
		ResponseEntity<Customer> responseEntity = controller.update(customerId, customer);
		
		Assertions.assertNull(responseEntity.getBody());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		
		Mockito.verify(customerService, Mockito.times(1)).findById(customerId);
		Mockito.verify(customerService, Mockito.never()).save(customer);
	}
	
	@Test
	public void delete_whenPassValidId_shouldDeleteCustomer() {
		Integer customerId = 123;
		
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setNome("Nome");
		customer.setEmail("Email@email.com");
		customer.setCpf("12345678999");
		
		Optional<Customer> optCustomer = Optional.of(customer);
		
		Mockito.when(customerService.findById(customerId)).thenReturn(optCustomer);
		
		ResponseEntity<Customer> customerReturned = controller.delete(customerId);
		
		Assertions.assertEquals(customer, customerReturned.getBody());
		
		Mockito.verify(customerService, Mockito.times(1)).findById(customerId);
		Mockito.verify(customerService, Mockito.times(1)).delete(customerId);
	}
	
	@Test
	public void delete_whenPassInvalidId_shouldNotCallDeleteMethod() {
		Integer customerId = 123;
		
		Mockito.when(customerService.findById(customerId)).thenReturn(Optional.empty());
		
		ResponseEntity<Customer> customerReturned = controller.delete(customerId);
		
		Assertions.assertNull(customerReturned.getBody());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, customerReturned.getStatusCode());
		
		Mockito.verify(customerService, Mockito.times(1)).findById(customerId);
		Mockito.verify(customerService, Mockito.never()).delete(customerId);
	}
}

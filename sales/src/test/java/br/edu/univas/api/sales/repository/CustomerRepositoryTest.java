package br.edu.univas.api.sales.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.univas.api.sales.vo.Customer;

@SpringBootTest
public class CustomerRepositoryTest {

	@InjectMocks
	private CustomerRepository repository;
	
	@Test	
	public void create_shouldSaveCustomer() {
		int sizeBefore = repository.list().size();
		
		Customer customer = new Customer();
		customer.setId(123);
		
		repository.create(customer);
		
		Customer customerReturned = repository.getById(123);
		int currentSize = repository.list().size();
		
		Assertions.assertEquals(sizeBefore + 1, currentSize);
		Assertions.assertEquals(customer, customerReturned);
	}
	
	@Test
	public void list_shouldReturnAllCustomersSaved() {
		Assertions.assertEquals(0, repository.list().size());

		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer();
			customer.setId(i);
			repository.create(customer);
		}
		
		Assertions.assertEquals(5, repository.list().size());
	}
	
	@Test
	public void getById_shouldReturnCustomer() {
		Integer customerId = 123;
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setNome("João");
		customer.setCpf("12345678966");
		customer.setEmail("joao@gmail.com");
		
		Customer customerReturned = repository.getById(customerId);
		Assertions.assertNull(customerReturned);
		
		repository.create(customer);
		
		customerReturned = repository.getById(customerId);
		Assertions.assertNotNull(customerReturned);
		Assertions.assertEquals(customer.getId(), customerReturned.getId());
		Assertions.assertEquals(customer.getCpf(), customerReturned.getCpf());
		Assertions.assertEquals(customer.getEmail(), customerReturned.getEmail());
		Assertions.assertEquals(customer.getNome(), customerReturned.getNome());
	}
	
	@Test
	public void update_shouldUpdateCustomer() {
		Integer customerId = 123;
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setNome("João");
		customer.setCpf("12345678966");
		customer.setEmail("joao@gmail.com");
		
		repository.create(customer);
		
		customer = new Customer();
		customer.setId(customerId);
		customer.setNome("João Updated");
		customer.setCpf("88888888877");
		customer.setEmail("joao_updated@gmail.com");
		
		repository.update(customer);
		
		Customer customerReturned = repository.getById(customerId);
		Assertions.assertNotNull(customerReturned);
		Assertions.assertEquals(customer.getId(), customerReturned.getId());
		Assertions.assertEquals(customer.getCpf(), customerReturned.getCpf());
		Assertions.assertEquals(customer.getEmail(), customerReturned.getEmail());
		Assertions.assertEquals(customer.getNome(), customerReturned.getNome());
	}
	
	@Test
	public void delete_shouldDeleteCustomer() {
		Integer customerId = 123;
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setNome("João");
		customer.setCpf("12345678966");
		customer.setEmail("joao@gmail.com");
		
		repository.create(customer);
		
		Customer customerReturned = repository.getById(customerId);
		Assertions.assertNotNull(customerReturned);
		
		
		repository.delete(customerId);
		customerReturned = repository.getById(customerId);
		Assertions.assertNull(customerReturned);
	}
	
}

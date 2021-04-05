package org.dsc.ese.bootsvc.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.dsc.ese.bootsvc.exception.CustomerNotFoundException;
import org.dsc.ese.bootsvc.model.Customer;
import org.dsc.ese.bootsvc.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService
{
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class); 
    private final CustomerRepository repository; 
    
	@Autowired
	public CustomerServiceImpl(final CustomerRepository repository)
	{
		this.repository = repository;
	}

	@Override
	@Transactional
	public Customer addCustomer(@NotNull @Valid Customer customer)
	{
		LOGGER.debug("Creating {}", customer);
		return repository.save(customer);
	}

	@Override
	public Customer updateCustomer(@NotNull @Valid Customer customer)
	{
		LOGGER.debug("Updating {}", customer);
		Optional<Customer> existing = repository.findById(customer.getId());
		if (!existing.isPresent())
		{
			throw new CustomerNotFoundException(customer.getId());
		}
		
		Customer oldcust = existing.get();
		oldcust.setEmail(customer.getEmail());
		oldcust.setFirstname(customer.getFirstname());
		oldcust.setLastname(customer.getLastname());
		return repository.save(oldcust);
	}

	@Override
	public List<Customer> getCustomerList()
	{
		return repository.findAll();
	}

	@Override
	public Customer getCustomer(Long customerId)
	{
		Optional<Customer> existing = repository.findById(customerId);
		return existing.orElseThrow(() -> new CustomerNotFoundException(customerId));
	}

	@Override
	public void deleteCustomer(Long customerId)
	{
		Optional<Customer> existing = repository.findById(customerId);
		
		if (!existing.isPresent())
		{
			throw new CustomerNotFoundException(customerId);
		}
		
		repository.deleteById(customerId);		
	}

	@Override
	public List<Customer> getCustomersByLastname(String lastname)
	{
		return repository.findByLastname(lastname);
	}

}

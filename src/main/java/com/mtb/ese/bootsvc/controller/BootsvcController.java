package com.mtb.ese.bootsvc.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtb.ese.bootsvc.model.Customer;
import com.mtb.ese.bootsvc.service.CustomerService;
import com.mtb.ese.bootsvc.exception.CustomerNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "customer", description = "The Customer API")
public class BootsvcController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(BootsvcController.class);
	private final CustomerService customerService;
	
	@Autowired
	public BootsvcController(final CustomerService customerService)
	{
		this.customerService = customerService;
	}
	
	@Operation(summary = "Add a new customer")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Customer added successfully", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = Customer.class)) }),
			  @ApiResponse(responseCode = "500", description = "Internal error", 
			    content = @Content) })
	@RequestMapping(value = "/customer", 
					method = RequestMethod.POST, 
					consumes = {"application/json" },
					produces = {"application/json" })
	public Customer addCustomer(@Valid @RequestBody final Customer customer)
	{
		LOGGER.debug("Received request to create customer {}", customer);
		return customerService.addCustomer(customer);
	}
	
	@Operation(summary = "Update a customer")
	@RequestMapping(value = "/customer", 
					method = RequestMethod.PUT, 
					consumes = {"application/json" },
					produces = {"application/json" })
	public Customer updateCustomer(@Valid @RequestBody final Customer customer)
	{
		LOGGER.debug("Received request to update customer {}", customer);
		return customerService.updateCustomer(customer);
	}
	
	@Operation(summary = "Get a list of all customers")
	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = {
			"application/json" })
	public List<Customer> listCustomers()
	{
		LOGGER.debug("Received request to list all customers");
		return customerService.getCustomerList();
	}

	@Operation(summary = "Get a list of customers by lastname")
	@RequestMapping(value = "/customer/search/lastname/{lastname}", method = RequestMethod.GET, produces = {
			"application/json" })
	public List<Customer> findCustomersByLastname(@PathVariable String lastname)
	{
		LOGGER.debug("Received request to find customers by lastname {}", lastname);
		return customerService.getCustomersByLastname(lastname);
	}

	@Operation(summary = "Get a single customer by id")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = {
			"application/json" })
	public Customer getCustomer(@PathVariable Long id)
	{
		LOGGER.debug("Received request to get customer {}", id);
		return customerService.getCustomer(id);
	}

	@Operation(summary = "Delete a single customer by id")
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable Long id)
	{
		LOGGER.debug("Received request to delete customer {}", id);
		customerService.deleteCustomer(id);
	}
	
}

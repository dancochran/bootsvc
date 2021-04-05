package org.dsc.ese.bootsvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.dsc.ese.bootsvc.model.Customer;
import org.dsc.ese.bootsvc.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import static org.dsc.ese.bootsvc.config.OpenApiConfig.BASIC_AUTH_SECURITY_SCHEME;

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
	
	@Operation(summary = "Add a new customer",
			security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
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
	
	@Operation(summary = "Update a customer",
			security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
	@RequestMapping(value = "/customer", 
					method = RequestMethod.PUT, 
					consumes = {"application/json" },
					produces = {"application/json" })
	public Customer updateCustomer(@Valid @RequestBody final Customer customer)
	{
		LOGGER.debug("Received request to update customer {}", customer);
		return customerService.updateCustomer(customer);
	}
	
	@Operation(summary = "Get a list of all customers",
			security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
	@RequestMapping(value = "/customer", method = RequestMethod.GET, produces = {
			"application/json" })
	public List<Customer> listCustomers()
	{
		LOGGER.debug("Received request to list all customers");
		return customerService.getCustomerList();
	}

	@Operation(summary = "Get a list of customers by lastname",
			security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
	@RequestMapping(value = "/customer/search/lastname/{lastname}", method = RequestMethod.GET, produces = {
			"application/json" })
	public List<Customer> findCustomersByLastname(@PathVariable String lastname)
	{
		LOGGER.debug("Received request to find customers by lastname {}", lastname);
		return customerService.getCustomersByLastname(lastname);
	}

	@Operation(summary = "Get a single customer by id",
			security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = {
			"application/json" })
	public Customer getCustomer(@PathVariable Long id)
	{
		LOGGER.debug("Received request to get customer {}", id);
		return customerService.getCustomer(id);
	}

	@Operation(summary = "Delete a single customer by id",
			security = {@SecurityRequirement(name = BASIC_AUTH_SECURITY_SCHEME)})
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable Long id)
	{
		LOGGER.debug("Received request to delete customer {}", id);
		customerService.deleteCustomer(id);
	}
	
}

package com.mtb.ese.bootsvc.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.mtb.ese.bootsvc.model.Customer;

public interface CustomerService
{
	Customer addCustomer(@NotNull @Valid final Customer customer); 
	Customer updateCustomer(@NotNull @Valid final Customer customer);
    List<Customer> getCustomerList(); 
    Customer getCustomer(Long customerId); 
    void deleteCustomer(final Long customerId);
    List<Customer> getCustomersByLastname(String lastname);

}

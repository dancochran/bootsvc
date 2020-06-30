package com.mtb.ese.bootsvc.repository;

import com.mtb.ese.bootsvc.model.Customer;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	List<Customer> findByLastname(String lastname);
}

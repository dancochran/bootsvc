package org.dsc.ese.bootsvc.repository;

import java.util.List;

import org.dsc.ese.bootsvc.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
	List<Customer> findByLastname(String lastname);
}

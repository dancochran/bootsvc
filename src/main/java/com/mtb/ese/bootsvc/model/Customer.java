package com.mtb.ese.bootsvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Customer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Version
	@Column(name = "version", nullable = false, updatable = true)
	private Long version;
	
	@NotNull
	@Size(min = 4, max = 64)
	@Column(name = "firstname", nullable = false)
	private String firstname;

	@NotNull
	@Size(max = 64)
	@Column(name = "lastname", nullable = false)
	private String lastname;

	@NotNull
	@Size(max = 64)
	@Column(name = "email", nullable = false)
	private String email;

	public Customer()
	{
	}

	public Customer(final Long id, final String firstname, final String lastname, final String email)
	{
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}

	public Long getId()
	{
		return id;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Override
	public String toString()
	{
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + "]";
	}


}

package org.dsc.ese.bootsvc.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = -683367169428916657L;

	public CustomerNotFoundException(Long id)
	{
		super("No customer found for id " + id);
	}
}

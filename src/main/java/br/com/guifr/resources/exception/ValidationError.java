package br.com.guifr.resources.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<FieldValidation> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timesStamp) {
		super(status, msg, timesStamp);
	}

	public List<FieldValidation> getErrors() {
		return errors;
	}

	public void addErrors(FieldValidation error) {
		this.errors.add(error);
	}

	

}

package insurance.management.system.exception;

import java.util.List;

public class ValidationResponse {
	
	private int statusCode;
    private List<ValidationError> errors;
    
	public ValidationResponse() {
		super();
		
	}

	public ValidationResponse(int statusCode, List<ValidationError> errors) {
		super();
		this.statusCode = statusCode;
		this.errors = errors;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}
    

    

    
}

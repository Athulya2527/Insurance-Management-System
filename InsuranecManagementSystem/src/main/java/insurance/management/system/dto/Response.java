package insurance.management.system.dto;

import org.springframework.http.HttpStatus;

public class Response {
    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
    
    
    
	public Response() {
		super();
	}

	public Response(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.statusCode = httpStatus.value();
		this.message = message;
		
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}

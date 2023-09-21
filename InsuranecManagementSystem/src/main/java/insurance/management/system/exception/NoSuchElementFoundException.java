package insurance.management.system.exception;

@SuppressWarnings("serial")
public class NoSuchElementFoundException extends RuntimeException {

	private String message;

	public NoSuchElementFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

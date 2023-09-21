package insurance.management.system.service;

public interface LogInservice {

	Object checkAccess(String email, String password, String role) throws Exception;

}

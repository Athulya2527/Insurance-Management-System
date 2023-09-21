package insurance.management.system.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import insurance.management.system.dto.Response;


@RestControllerAdvice
public class InsuranceManagementExceptionHandler  {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ValidationError> validationErrors = new ArrayList<>();
        
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            validationErrors.add(new ValidationError(field, message));
        }

        ValidationResponse errorMessage = new ValidationResponse(HttpStatus.BAD_REQUEST.value(), validationErrors);
        return ResponseEntity.badRequest().body(errorMessage);
    } 
    @ExceptionHandler(value = AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response handleAlreadyExistsException(AlreadyExistsException ex) {
        return new Response(HttpStatus.CONFLICT, ex.getMessage());
    }
    
    @ExceptionHandler(value = NoSuchElementFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response handleNoSuchElementException(NoSuchElementFoundException ex) {
    	return new Response(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}

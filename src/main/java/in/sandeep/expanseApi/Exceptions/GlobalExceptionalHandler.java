package in.sandeep.expanseApi.Exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in.sandeep.expanseApi.Entities.ErrorObject;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {

	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject>handleExpanseNotFoundException(ResourceNotFoundException ex,WebRequest req){
		ErrorObject errorObject= new ErrorObject();
		errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorObject.setMessage(ex.getMessage());
		errorObject.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentMismatchException(MethodArgumentTypeMismatchException ex,WebRequest req){
		ErrorObject error2Object= new ErrorObject();
		error2Object.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error2Object.setMessage(ex.getMessage());
		error2Object.setTimestamp(new Date());
		return new ResponseEntity<Object>(error2Object,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGeneralException(Exception ex,WebRequest req){
		ErrorObject error3Object= new ErrorObject();
		error3Object.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error3Object.setMessage(ex.getMessage());
		error3Object.setTimestamp(new Date());
		return new ResponseEntity<Object>(error3Object,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String,Object> body= new HashMap<String,Object>();
		body.put("status code", HttpStatus.BAD_REQUEST.value());
		body.put("timestamp", new Date());
		
		List<String>errors =ex.getBindingResult()
		.getFieldErrors()
		.stream()
		.map(x ->x.getDefaultMessage())
		.collect(Collectors.toList());
		body.put("message", errors);
		return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<ErrorObject>  handleItemExistException(ItemAlreadyExistsException ex,WebRequest req){
		ErrorObject error4Object= new ErrorObject();
		error4Object.setStatusCode(HttpStatus.CONFLICT.value());
		error4Object.setMessage(ex.getMessage());
		error4Object.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(error4Object,HttpStatus.CONFLICT);
		
	}
}

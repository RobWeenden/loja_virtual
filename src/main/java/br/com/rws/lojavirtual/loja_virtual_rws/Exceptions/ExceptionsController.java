package br.com.rws.lojavirtual.loja_virtual_rws.Exceptions;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionsController extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ Exception.class, RuntimeException.class, Throwable.class })
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ObjectErrorDTO errorDto = new ObjectErrorDTO();
		String msg = "";
		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> listError = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			for (ObjectError objectError : listError) {
				msg += objectError.getDefaultMessage() + " \n";
			}

		} else if (ex instanceof HttpMessageNotReadableException) {
			msg = "Não há dados sendo enviados no corpo da requisição";
		} else {
			msg = ex.getMessage();
		}
		errorDto.setError(msg);
		errorDto.setCodeError(status.value() + " ==> " + status.getReasonPhrase());
		ex.printStackTrace();
		return new ResponseEntity<Object>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class })
	protected ResponseEntity<Object> handlerExceptionDataInEntity(Exception ex) {

		ObjectErrorDTO errorDto = new ObjectErrorDTO();
		String msg = "";
		if (ex instanceof DataIntegrityViolationException) {
			msg = "Error de integridade no banco: "
					+ ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof SQLException) {
			msg = "Error de SQL do Banco: " + ((SQLException) ex).getCause().getCause().getMessage();

		} else if (ex instanceof ConstraintViolationException) {
			msg = "Eror de chave estrangiera: "
					+ ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		} else {
			msg = ex.getMessage();
		}
		errorDto.setError(msg);
		errorDto.setCodeError(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		ex.printStackTrace();
		return new ResponseEntity<Object>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(CustomExceptions.class)
	public ResponseEntity<Object> handleExceptionCustom(CustomExceptions customExpr) {
		ObjectErrorDTO errorDTO = new ObjectErrorDTO();
		errorDTO.setError(customExpr.getMessage());
		errorDTO.setCodeError(HttpStatus.OK.toString());

		return new ResponseEntity<Object>(errorDTO, HttpStatus.OK);
	}
}

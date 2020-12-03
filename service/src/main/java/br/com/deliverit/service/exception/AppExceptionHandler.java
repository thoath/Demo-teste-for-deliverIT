package br.com.deliverit.service.exception;

import br.com.deliverit.api.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * Generic exception handler, just for demo purpose
 * @author Lucas Koch
 */
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppException> handle(MethodArgumentNotValidException exception) {

        AppException appException = new AppException();
        appException.setMessage(
                exception.
                        getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage).collect(Collectors.joining()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppException> handle(Exception exception) {

        AppException appException = new AppException();
        appException.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(appException);
    }
}

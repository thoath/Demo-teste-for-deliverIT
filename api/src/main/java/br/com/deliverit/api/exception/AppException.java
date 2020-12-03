package br.com.deliverit.api.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * Generic object for the application exception messages
 * @author Lucas Koch
 */
@Getter
@Setter
public class AppException {

    private static final long serialVersionUID = 1L;

    private String message;
}

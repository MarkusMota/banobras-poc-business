package mx.gob.banobras.nombreApp.usuarios.business.infraestructure.exception;



import java.util.NoSuchElementException;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.client.ResourceAccessException;





@RestControllerAdvice
public class ExceptionHandlerUsuario {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {
        ProblemDetail errorDetail = null;
        

        if (ex instanceof HttpMessageNotReadableException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "100");
        }
        if (ex instanceof MissingRequestHeaderException) {
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "101");
        }
        if (ex instanceof NoSuchElementException) {
        	ex.printStackTrace();
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "102");
        }
        if (ex instanceof ResourceAccessException) {
        	ex.printStackTrace();
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage() + " - Capa Business");
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "103");
        }
        
        if (ex instanceof InternalServerError) {
        	ex.printStackTrace();
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "104");
        }
        
        if (ex instanceof HttpServerErrorException) {
        	ex.printStackTrace();
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "105");
        }
        
        if (ex instanceof NullPointerException) {
        	ex.printStackTrace();
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "106");
        }
        
       
        if (ex instanceof Exception) {
        	
        	String msgResp = "";
        	if(	ex.getMessage().toUpperCase().contains("EXPIRADO")){
        		msgResp= "Token Expirado";
        	}
        	ex.printStackTrace();
            errorDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatusCode.valueOf(500), msgResp);
            errorDetail.setProperty("message_error", ex.getMessage());
            errorDetail.setProperty("code_error", "107");
        }
         
        return errorDetail;
    }

}

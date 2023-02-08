package shop.mtcoding.blog2.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.blog2.ex.CustomException;
import shop.mtcoding.blog2.util.Script;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) {
        if (e.getStatus() == HttpStatus.UNAUTHORIZED) {
            return new ResponseEntity<>(Script.href(e.getMessage(), "/loginForm"), e.getStatus());
        }
        return new ResponseEntity<>(Script.back(e.getMessage()), e.getStatus());
    }
}

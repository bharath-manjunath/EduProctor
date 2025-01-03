package com.eduproctor.tut.error;

import com.eduproctor.tut.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice // WIll handle when an exception occurs in controller
@ResponseStatus //WIll send resposne status
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    //Creating a method to handle specific exception

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }
}

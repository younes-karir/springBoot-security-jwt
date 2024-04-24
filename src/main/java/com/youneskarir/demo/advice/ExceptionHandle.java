package com.youneskarir.demo.advice;


import com.youneskarir.demo.advice.custom.ElementExistException;
import com.youneskarir.demo.advice.custom.EmptyTokenFieldException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandle {
    
    
    
    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                item -> errors.put(item.getField(), item.getDefaultMessage())
            );
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(ExpiredJwtException.class)
    ProblemDetail handleExpiredJwtException(ExpiredJwtException exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"JWT expired");
    }

    @ExceptionHandler(MalformedJwtException.class)
    ProblemDetail handleExpiredJwtException(MalformedJwtException exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(400),"Invalid JWT token");
    }

    @ExceptionHandler(SignatureException.class)
    ProblemDetail handleExpiredJwtException(SignatureException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"Invalid JWT signature");
    }   
    
    @ExceptionHandler(UnsupportedJwtException.class)
    ProblemDetail handleExpiredJwtException(UnsupportedJwtException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"Unsupported JWT token");
    }   
    
    @ExceptionHandler(EmptyTokenFieldException.class)
    ProblemDetail handleExpiredJwtException(EmptyTokenFieldException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"JWT string is empty");
    }    
    
    @ExceptionHandler(AccessDeniedException.class)
    ProblemDetail handleExpiredJwtException(AccessDeniedException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(403),"not authorized to access this resource");
    }   
    
    @ExceptionHandler(BadCredentialsException.class)
    ProblemDetail handleExpiredJwtException(BadCredentialsException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"username or password is incorrect");
    }

}

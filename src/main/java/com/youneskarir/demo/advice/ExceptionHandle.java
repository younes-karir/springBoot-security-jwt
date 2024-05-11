package com.youneskarir.demo.advice;


import com.youneskarir.demo.advice.custom.ElementExistException;
import com.youneskarir.demo.advice.custom.EmptyTokenFieldException;
import com.youneskarir.demo.advice.custom.NotValidEnumValueException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandle {
    
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ElementExistException.class
    })
    public ResponseEntity<Object> handleValidation(Exception exception){
        Map<String,String> errors = new HashMap<>();
        if(exception instanceof MethodArgumentNotValidException ex)
            ex.getBindingResult().getFieldErrors().forEach(
                    item -> errors.put(item.getField(), item.getDefaultMessage())
            );

        if(exception instanceof ElementExistException ex)
            errors.put("email", ex.getMessage());

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(@NotNull HttpMessageNotReadableException ex) {
        if (ex.getMessage().contains("Required request body is missing")) {
            // Request body is missing
            return ProblemDetail
                    .forStatusAndDetail(HttpStatusCode
                            .valueOf(400),"Request body is missing");
        } else if (ex.getCause() instanceof com.fasterxml.jackson.core.JsonParseException) {
            // Malformed JSON
            return ProblemDetail
                    .forStatusAndDetail(HttpStatusCode
                            .valueOf(400),"Malformed JSON in request body");
        } else if (ex.getMessage().contains("Could not read document")) {
            // Unsupported media type or other read error
            return ProblemDetail
                    .forStatusAndDetail(HttpStatusCode
                            .valueOf(400),"Unsupported media type or other read error");
        } else if(ex.getMessage().contains("not one of the values accepted for Enum class"))
        {
            return ProblemDetail
                    .forStatusAndDetail(HttpStatusCode
                            .valueOf(400),"not one of the values accepted for Enum");
        }
        else {
            // Handle other HttpMessageNotReadableException cases
            System.out.println(ex.getMessage());
            return ProblemDetail
                    .forStatusAndDetail(HttpStatusCode
                            .valueOf(400),"Other HttpMessageNotReadableException occurred");
        }
    }
    
    @ExceptionHandler(ExpiredJwtException.class)
    // Handles exceptions thrown when a JWT has expired.
    ProblemDetail handleExpiredJwtException(ExpiredJwtException exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"The JWT has expired.");
    }

    @ExceptionHandler(MalformedJwtException.class)
    // Handles exceptions thrown when a JWT is malformed or not valid.
    ProblemDetail handleMalformedJwtException(MalformedJwtException exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(400),"The JWT token is not valid.");
    }

    @ExceptionHandler(SignatureException.class)
    // Handles exceptions thrown when a JWT signature is not valid.
    ProblemDetail handleSignatureException(SignatureException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"The JWT signature is not valid.");
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    // Handles exceptions thrown when a JWT token is not supported.
    ProblemDetail handleUnsupportedJwtException(UnsupportedJwtException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(400),"The JWT token is not supported.");
    }

    @ExceptionHandler(EmptyTokenFieldException.class)
    // Handles exceptions thrown when the JWT string is empty.
    ProblemDetail handleEmptyTokenFieldException(EmptyTokenFieldException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(400),"The JWT string is empty.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    // Handles exceptions thrown when access to a resource is denied.
    ProblemDetail handleAccessDeniedException(AccessDeniedException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(403),"Access to this resource is forbidden.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    // Handles exceptions thrown when username or password is incorrect.
    ProblemDetail handleBadCredentialsException(BadCredentialsException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(401),"The username or password is incorrect.");
    }


    @ExceptionHandler(AccountStatusException.class)
    // Handles exceptions thrown when the account status is not valid.
    ProblemDetail handleAccountStatusException(AccountStatusException  exception){
        return ProblemDetail
                .forStatusAndDetail(HttpStatusCode
                        .valueOf(403),"The account status is not valid.");
    }



}

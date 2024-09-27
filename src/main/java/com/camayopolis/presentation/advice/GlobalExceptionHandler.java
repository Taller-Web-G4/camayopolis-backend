package com.camayopolis.presentation.advice;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.camayopolis.presentation.DetalleErrorEnum;
import com.camayopolis.presentation.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.FUNCIONAL.getCodigo(),
                DetalleErrorEnum.FUNCIONAL.getDescripcion(),
                "Usuario no encontrado: " + ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.AUTENTICACION.getCodigo(),
                DetalleErrorEnum.AUTENTICACION.getDescripcion(),
                "Credenciales inválidas: " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String rootCauseMessage = (ex.getRootCause() != null) ? ex.getRootCause().getMessage() : "No se pudo determinar la causa raíz.";
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.BASE_DATOS.getCodigo(),
                DetalleErrorEnum.BASE_DATOS.getDescripcion(),
                "Error de integridad de datos: " + rootCauseMessage,
                HttpStatus.CONFLICT.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.VALIDACION.getCodigo(),
                DetalleErrorEnum.VALIDACION.getDescripcion(),
                "Error de validación: " + ex.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String fieldName = (fieldError != null) ? fieldError.getField() : "Campo desconocido";
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Error de validación desconocido";

        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.VALIDACION.getCodigo(),
                DetalleErrorEnum.VALIDACION.getDescripcion(),
                "Error en campo '" + fieldName + "': " + errorMessage,
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.FUNCIONAL.getCodigo(),
                DetalleErrorEnum.FUNCIONAL.getDescripcion(),
                ex.getReason(),
                ex.getStatusCode().value()
        );
        return new ResponseEntity<>(response, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.SISTEMA.getCodigo(),
                DetalleErrorEnum.SISTEMA.getDescripcion(),
                "Error interno del servidor: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.AUTORIZACION.getCodigo(),
                DetalleErrorEnum.AUTORIZACION.getDescripcion(),
                "No tiene los permisos necesarios para acceder a este recurso.",
                HttpStatus.FORBIDDEN.value()
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.AUTENTICACION.getCodigo(),
                DetalleErrorEnum.AUTENTICACION.getDescripcion(),
                "No se proporcionó la autenticación adecuada o es insuficiente.",
                HttpStatus.FORBIDDEN.value()
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ErrorResponse> handleJWTVerificationException(JWTVerificationException ex) {
        ErrorResponse response = ErrorResponse.buildErrorResponse(
                DetalleErrorEnum.AUTENTICACION.getCodigo(),
                DetalleErrorEnum.AUTENTICACION.getDescripcion(),
                "Token inválido o no autorizado: " + ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}

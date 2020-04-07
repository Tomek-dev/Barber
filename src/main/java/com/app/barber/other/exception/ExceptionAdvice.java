package com.app.barber.other.exception;

import com.app.barber.other.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BarberNotFoundException.class)
    public ResponseEntity<ApiResponse> handleBarberNotFoundException(BarberNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleServiceNotFoundException(ServiceNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StarNotFoundException.class)
    public ResponseEntity<ApiResponse> handleStarNotFoundException(StarNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ApiResponse> handleTokenNotFoundException(TokenNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleWorkerNotFoundException(WorkerNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GeocodeException.class)
    public ResponseEntity<ApiResponse> handleGeocodeException(GeocodeException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(VisitDateNotAvailableException.class)
    public ResponseEntity<ApiResponse> handleVisitDateNotAvailableException(VisitDateNotAvailableException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OpenNotFoundException.class)
    public ResponseEntity<ApiResponse> handleOpenNotFoundException(OpenNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectParamException.class)
    public ResponseEntity<ApiResponse> handleIncorrectParamException(IncorrectParamException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BelongException.class)
    public ResponseEntity<ApiResponse> handleBelongException(BelongException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(VisitNotFoundException.class)
    public ResponseEntity<ApiResponse> handleVisitNotFoundException(VisitNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ApiResponse> handleReviewNotFoundException(ReviewNotFoundException e){
        ApiResponse response = new ApiResponse(false, e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setDate(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}

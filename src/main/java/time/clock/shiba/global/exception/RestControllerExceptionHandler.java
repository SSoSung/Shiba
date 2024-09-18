package time.clock.shiba.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import time.clock.shiba.global.common.ShibaApiResponse;
import time.clock.shiba.global.common.ShibaStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ShibaApiResponse> handleApiRequestException(BadRequestException e){
        ShibaApiResponse shibaApiException = new ShibaApiResponse(
                ShibaStatus.BAD_REQUEST,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(shibaApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ShibaApiResponse> handleApiRequestException(RuntimeException e){
        ShibaApiResponse shibaApiException = new ShibaApiResponse(
                ShibaStatus.BAD_REQUEST,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(shibaApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ShibaApiResponse> handleApiRequestException(AccessDeniedException e){
        ShibaApiResponse shibaApiException = new ShibaApiResponse(
                ShibaStatus.FORBIDDEN,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(shibaApiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ShibaApiResponse> handleApiRequestException(MethodArgumentNotValidException e) {
        ShibaApiResponse shibaApiException = new ShibaApiResponse(
                ShibaStatus.SYSTEM_ERROR,
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(shibaApiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

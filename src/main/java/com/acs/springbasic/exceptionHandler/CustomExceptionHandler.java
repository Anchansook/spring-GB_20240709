package com.acs.springbasic.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//# @RestControllerAdvice (클래스)
// : RestController에서 발생하는 특정 상황(예외 상황)에 대해 처리하는 클래스로 지정 
@RestControllerAdvice
public class CustomExceptionHandler {
    
    //# @ExceptionHandler
    // : 지정한 예외에 대해 직접 컨트롤할 수 있도록 하는 어노테이션
    //& @ExceptionHandler(value={예외클래스명.class, ...})
    // 예외처리를 따로하면 콘솔에 에러문구가 안 나옴
    @ExceptionHandler(value={MethodArgumentNotValidException.class})
    public ResponseEntity<String> customException(
        MethodArgumentNotValidException exception
    ) {
        exception.printStackTrace(); // 에러 상황문구 띄워줌
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("잘못된 입력입니다.");
    };

    @ExceptionHandler(value={HttpMessageNotReadableException.class})
    public ResponseEntity<String> notReadableException(
        HttpMessageNotReadableException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청 데이터를 읽을 수 없습니다.");
    };

}

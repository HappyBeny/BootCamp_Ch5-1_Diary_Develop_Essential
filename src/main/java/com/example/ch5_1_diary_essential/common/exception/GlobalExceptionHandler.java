package com.example.ch5_1_diary_essential.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ID/일정 정보에 접근할 자격이 없을 경우에 대한 예외 레핑
     * 예외 발생 시 message에 id/schedule 담아서 그에 따라 판단하게끔 함.
     */
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<Map<String, String>> handleNotAuthorizedException(NotAuthorizedException e) {

        return createMessageResponseEntity(HttpStatus.UNAUTHORIZED, "You are not the owner of this " + e.getMessage());
    }

    /**
     * 삭제된 유저의 정보에 접근하는 경우에 대한 예외 래핑
     */
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMemberNotFoundException(MemberNotFoundException e) {
        if (e.getMessage().equals("deleted")) {
            return createMessageResponseEntity(HttpStatus.NOT_FOUND, "this user is deleted already");
        }
        return createMessageResponseEntity(HttpStatus.NOT_FOUND, "this user is not exist");
    }

    /**
     * 비밀번호가 틀렸을 경우에 대한 예외 래핑
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException e) {
        return createMessageResponseEntity(HttpStatus.BAD_REQUEST, "incorrect password");
    }

    /**
     * mvc 모델 상에서 나머지 예외들을 일괄적으로 처리
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        log.info(
                "exception: {}, message: {}, stacktrace: {}",
                e.getClass().getName(), e.getMessage(), e.getStackTrace()
        );
        return createMessageResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");
    }

    private ResponseEntity<Map<String, String>> createMessageResponseEntity(HttpStatus status, String message) {
        Map<String, String> body = new HashMap<>();
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}

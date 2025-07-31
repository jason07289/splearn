package tobyspring.splearn.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tobyspring.splearn.domain.member.DuplicateEmailException;
import tobyspring.splearn.domain.member.DuplicateProfileException;

import java.time.LocalDateTime;

@ControllerAdvice//Exception들은 도메인 패키지에 있지만 web 응답코드 생성은 api 어댑터에서 처리한다.
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception exception) {
        return getProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception);
    }

    @ExceptionHandler({DuplicateEmailException.class, DuplicateProfileException.class})
    public ProblemDetail emailExceptionHandler(DuplicateEmailException exception) {
        return getProblemDetail(HttpStatus.CONFLICT, exception);
    }

    private static ProblemDetail getProblemDetail(HttpStatus status, Exception exception) {
        //ProblemDetail? -> spring 6.0부터 지원, RFC9457표준에 맞춘응답을해줌
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, exception.getMessage());
        //properties 추가 가능
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("exception", exception.getClass().getSimpleName());

        //Body = {"type":"about:blank",
        // "title":"Conflict",
        // "status":409,
        // "detail":"이미 사용중인 이메일입니다: toby@splearn.app",
        // "instance":"/api/members",
        // "timestamp":"2025-07-31T13:12:48.661331",
        // "exception":"DuplicateEmailException"
        // }
        return problemDetail;
    }
}

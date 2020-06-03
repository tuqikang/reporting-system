package cn.tqktqk.work.problemreportingsystem.exceptions;

import cn.tqktqk.work.problemreportingsystem.model.enums.ResponseEnum;
import cn.tqktqk.work.problemreportingsystem.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Response allExceptionHandler(HttpServletRequest request, ServerException exception) {
        return Response.fail(exception.getResponseEnum(),exception.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Response exceptionHandler(HttpServletRequest request, Exception exception) {
        log.error(exception.getMessage());
        return Response.fail(ResponseEnum.FAIL);
    }


}

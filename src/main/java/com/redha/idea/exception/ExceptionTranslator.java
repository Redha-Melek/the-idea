package com.redha.idea.exception;

import com.redha.idea.exception.bean.ErrorResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.Status;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807).
 */
@ControllerAdvice
public class ExceptionTranslator {

    @ResponseBody
    @ExceptionHandler(BadRequestAlertException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseBean handleBadRequestAlertException(BadRequestAlertException ex) {
        ErrorResponseBean errorResponseBean = new ErrorResponseBean();
        errorResponseBean.setEntityName(ex.getEntityName());
        errorResponseBean.setStatus(Status.BAD_REQUEST.getStatusCode());
        errorResponseBean.setErrorKey(ex.getErrorKey());
        errorResponseBean.setMessage(ex.getMessage());
        return errorResponseBean;
    }

    @ResponseBody
    @ExceptionHandler(NotFoundAlertException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseBean handleEntityNotFoundException(NotFoundAlertException ex) {
        ErrorResponseBean errorResponseBean = new ErrorResponseBean();
        errorResponseBean.setEntityName(ex.getEntityName());
        errorResponseBean.setStatus(Status.NOT_FOUND.getStatusCode());
        errorResponseBean.setErrorKey(ex.getErrorKey());
        errorResponseBean.setMessage(ex.getMessage());
        return errorResponseBean;
    }

}

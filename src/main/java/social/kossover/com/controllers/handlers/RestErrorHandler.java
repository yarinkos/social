package social.kossover.com.controllers.handlers;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import social.kossover.com.controllers.handlers.exepctions.BaseException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@ResponseBody
@ControllerAdvice
public class RestErrorHandler { //extends ResponseEntityExceptionHandler {

    private static Logger LOG = Logger.getLogger(RestErrorHandler.class);

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) //todo : generalize it
    @ExceptionHandler(BaseException.class)
    public ErrorDetail myError(HttpServletRequest request, Exception exception) {
        ErrorDetail error = new ErrorDetail();
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(exception.getLocalizedMessage());
       // error.setUrl(request.getRequestURL().append("/error/111").toString());
        return error;
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BindException.class,
    })
    public List<ObjectError>  badRequest(HttpServletRequest req, BindException bindException) {
        return bindException.getAllErrors();
    }

}
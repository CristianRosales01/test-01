package cl.cristian.rosales.test.config;

import cl.cristian.rosales.test.dto.ResponseErrorDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseErrorDTO handleException(Exception ex) {
        ResponseErrorDTO response = new ResponseErrorDTO();
        response.setMessage(ex.getMessage());
        return response;
    }

}

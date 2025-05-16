package net.javaquides.springboot_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourveNotFoundException extends RuntimeException {
    public ResourveNotFoundException(String message){
        super(message);
    }
}

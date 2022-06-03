package io.github.viniciusboos.projetofinal.infra;

import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.exception.RecursoJaRegistradoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoJaRegistradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handler(RecursoJaRegistradoException exception) {

        return new ErrorDTO(400, exception.getMessage());
    }

    @ExceptionHandler(DadoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handler(DadoInvalidoException exception) {

        return new ErrorDTO(400, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> pessoaValidationExceptionHandler(MethodArgumentNotValidException exception){

        Map<String, String> erros = new LinkedHashMap<>();
        erros.put("status", "400");
        List<String> mensagens = new ArrayList<>();
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        for (ObjectError error:
                allErrors) {
            String mensagemErro = error.getDefaultMessage();
            erros.put("mensagem", mensagemErro);
        }

        return erros;
    }
}

package io.github.viniciusboos.projetofinal.infra;

import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.exception.RecursoJaRegistradoException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public ErrorDTO handler(DataAccessException exception) {

        return new ErrorDTO(500, "Error ao comunicar com o bando de dados");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDTO handler(HttpMessageNotReadableException exception) {

        return new ErrorDTO(400, "Erro ao converter dados de um campo," +
                " possivelmente foi colocado letra no lugar de um numero");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorDTO handler(MissingServletRequestParameterException exception) {

        return new ErrorDTO(400, "Parâmetros nos métodos Get não podem ser null");
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorDTO handler(MethodArgumentTypeMismatchException exception) {

        return new ErrorDTO(400, "Não foi possível consultar o município");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO pessoaValidationExceptionHandler(MethodArgumentNotValidException exception){

        String mensagemErro = null;
        for (ObjectError error:
                exception.getBindingResult().getAllErrors()) {
            mensagemErro = error.getDefaultMessage();
            break;
        }
        ErrorDTO errorDTO = new ErrorDTO(400, mensagemErro);
        return errorDTO;
    }
}

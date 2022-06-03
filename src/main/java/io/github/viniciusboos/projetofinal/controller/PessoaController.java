package io.github.viniciusboos.projetofinal.controller;

import io.github.viniciusboos.projetofinal.modelo.pessoa.PessoaDTO;
import io.github.viniciusboos.projetofinal.modelo.pessoa.Pessoa;
import io.github.viniciusboos.projetofinal.repository.PessoaRepository;
import io.github.viniciusboos.projetofinal.modelo.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @GetMapping
    public List<Pessoa> getAllPessoas() {

        List<Pessoa> pessoas = repository.findAll();
        return pessoas;
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody PessoaDTO pessoaDTO){

        Pessoa pessoa = PessoaService.criarPessoa(pessoaDTO);
        repository.save(pessoa);
        return ResponseEntity.status(200).body(pessoa);
    }


}

package io.github.viniciusboos.projetofinal.controller;

import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.exception.RecursoJaRegistradoException;
import io.github.viniciusboos.projetofinal.modelo.uf.UF;
import io.github.viniciusboos.projetofinal.modelo.uf.UFDTO;
import io.github.viniciusboos.projetofinal.modelo.uf.UFService;
import io.github.viniciusboos.projetofinal.repository.UFRepository;
import net.bytebuddy.description.type.TypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("uf")
public class UFController {

    @Autowired
    private UFRepository repository;

    @GetMapping
    public List<UF> getTodasUF() {
        return repository.findAll();
    }

    @GetMapping(params = "nome")
    public ResponseEntity getUFPorNome(@RequestParam String nome) {
        return consultaUF(null, nome, null, null);
    }

    @GetMapping(params = "codigoUF")
    public ResponseEntity getUFPorCodigoUF(@RequestParam Long codigoUF) {
        return consultaUF(null, null, codigoUF, null);
    }

    @GetMapping(params = "sigla")
    public ResponseEntity getUFPorSigla(@RequestParam String sigla) {
        return consultaUF(sigla, null, null, null);
    }

    @GetMapping(params = "status")
    public List<UF> getUFPorStatus(@RequestParam Integer status) {
        List<UF> possivelUFs = repository.findByStatus(status);
        return possivelUFs;
    }

    @GetMapping(params = {"status", "nome"})
    public ResponseEntity getUFPorStatusENome(@RequestParam Integer status,
                                             @RequestParam String nome) {
        return consultaUF(null, nome, null, status);
    }

    @GetMapping(params = {"status", "sigla"})
    public ResponseEntity getUFPorStatusESigla(@RequestParam Integer status,
                                              @RequestParam String sigla) {
        return consultaUF(sigla, null, null, status);
    }

    @GetMapping(params = {"status", "codigoUF"})
    public ResponseEntity getUFPorStatusECodigoUF(@RequestParam Integer status,
                                              @RequestParam Long codigoUF) {
        return consultaUF(null, null, codigoUF, status);
    }

    @GetMapping(params = {"sigla", "nome"})
    public ResponseEntity getUFPorSiglaENome(@RequestParam String nome,
                                            @RequestParam String sigla) {
        return consultaUF(sigla, nome, null, null);
    }

    @GetMapping(params = {"codigoUF", "nome"})
    public ResponseEntity getUFPorNomeECodigoUF(@RequestParam String nome,
                                    @RequestParam Long codigoUF) {
        return consultaUF(null, nome, codigoUF, null);
    }

    @GetMapping(params = {"sigla", "codigoUF"})
    public ResponseEntity getUFPorSiglaECodigoUf(@RequestParam Long codigoUF,
                                     @RequestParam String sigla) {
        return consultaUF(sigla, null, codigoUF, null);
    }


    @GetMapping(params = {"sigla", "nome", "codigoUF"})
    public ResponseEntity getUFPorSiglaENomeECodigoUF(@RequestParam String nome,
                                          @RequestParam String sigla,
                                          @RequestParam Long codigoUF) {
        return consultaUF(sigla, nome, codigoUF, null);
    }

    @GetMapping(params = {"status", "codigoUF", "nome"})
    public ResponseEntity getUFPorStatusCodigoUFENome(@RequestParam Integer status,
                                                  @RequestParam Long codigoUF,
                                                    @RequestParam String nome) {
        return consultaUF(null, nome, codigoUF, status);
    }

    @GetMapping(params = {"status", "codigoUF", "sigla"})
    public ResponseEntity getUFPorStatusCodigoUFESigla(@RequestParam Integer status,
                                                  @RequestParam Long codigoUF,
                                                       @RequestParam String sigla) {
        return consultaUF(sigla, null, codigoUF, status);
    }
    @GetMapping(params = {"status", "nome", "sigla"})
    public ResponseEntity getUFPorStatusNomeESigla(@RequestParam Integer status,
                                                   @RequestParam String sigla,
                                                   @RequestParam String nome) {
        return consultaUF(sigla, nome, null, status);
    }
    @GetMapping(params = {"status", "codigoUF", "sigla", "nome"})
    public ResponseEntity getUFPorStatusCodigoUFSiglaENome(@RequestParam Integer status,
                                                  @RequestParam Long codigoUF,
                                                           @RequestParam String sigla,
                                                           @RequestParam String nome) {
        return consultaUF(sigla, nome, codigoUF, status);
    }

    private ResponseEntity consultaUF(String sigla,
                                      String nome,
                                      Long codigoUF,
                                      Integer status) {

        if (nome == null && codigoUF == null && status == null) {
            Optional<UF> possivelUF = repository.findBySigla(sigla);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (sigla == null && codigoUF == null && status == null) {
            Optional<UF> possivelUF = repository.findByNome(nome);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (nome == null && sigla == null && status == null) {
            Optional<UF> possivelUF = repository.findByCodigoUF(codigoUF);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (codigoUF == null && status == null) {
            Optional<UF> possivelUF = repository.findBySiglaAndNome(sigla, nome);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (codigoUF == null && nome == null) {
            Optional<UF> possivelUF = repository.findBySiglaAndStatus(sigla, status);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (sigla == null && nome == null) {
            Optional<UF> possivelUF = repository.findByCodigoUFAndStatus(codigoUF, status);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (sigla == null && codigoUF == null) {
            Optional<UF> possivelUF = repository.findByNomeAndStatus(nome, status);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (sigla == null && status == null) {
            Optional<UF> possivelUF = repository.findByNomeAndCodigoUF(nome, codigoUF);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (nome == null && status == null) {
            Optional<UF> possivelUF = repository.findBySiglaAndCodigoUF(sigla, codigoUF);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if(status == null) {
            Optional<UF> possivelUF = repository.findBySiglaAndNomeAndCodigoUF(sigla, nome, codigoUF);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if(codigoUF == null) {
            Optional<UF> possivelUF = repository.findBySiglaAndNomeAndStatus(sigla, nome, status);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if(sigla == null) {
            Optional<UF> possivelUF = repository.findByCodigoUFAndNomeAndStatus(codigoUF, nome, status);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if(nome == null) {
            Optional<UF> possivelUF = repository.findBySiglaAndCodigoUFAndStatus(sigla, codigoUF, status);
            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                return ResponseEntity.ok(uf);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }

        Optional<UF> possivelUF = repository.findBySiglaAndNomeAndCodigoUFAndStatus(sigla, nome, codigoUF, status);
        if (possivelUF.isPresent()) {
            UF uf = possivelUF.get();
            return ResponseEntity.ok(uf);
        }
        return ResponseEntity.ok().body(new ArrayList<>());
    }


    @PostMapping
    public ResponseEntity<List<UF>> criarUF(@Valid @RequestBody UFDTO ufdto) {

        for(char c:
            ufdto.getNome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }
        for(char c:
                ufdto.getSigla().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Sigla não pode ter números");
            }
        }

        List<UF> ufs = repository.findAll();
        for (UF uf :
                ufs) {
            if (uf.getSigla().equals(ufdto.getSigla())) {
                throw new RecursoJaRegistradoException("Sigla " + ufdto.getSigla() +
                        " ja registrada no banco de dados");
            }
            if (uf.getNome().equals(ufdto.getNome())) {
                throw new RecursoJaRegistradoException("Nome " + ufdto.getNome() +
                        " ja registrado no banco de dados");
            }
        }

        if (ufdto.getStatus() != 1 && ufdto.getStatus() != 2) {
            throw new DadoInvalidoException("Status " + ufdto.getStatus() +
                    " invalido, possíveis status 1 - ativado / 2 - desativado");
        }

        UF uf = UFService.criarUF(ufdto);

        repository.save(uf);

        ufs = repository.findAll();
        return ResponseEntity.ok(ufs);
    }

    @PutMapping
    public ResponseEntity<List<UF>> alterarUF(@Valid @RequestBody UFDTO ufdto) {

        for(char c:
                ufdto.getNome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }
        for(char c:
                ufdto.getSigla().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Sigla não pode ter números");
            }
        }

        if (ufdto.getCodigoUF() == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        Optional<UF> possivelUF = repository.findByCodigoUF(ufdto.getCodigoUF());

        if (possivelUF.isPresent()) {
            UF ufAntesDeSerAlterada = possivelUF.get();
            List<UF> ufs = repository.findAll();

            if (!ufAntesDeSerAlterada.getSigla().equals(ufdto.getSigla())) {
                for (UF uf :
                        ufs) {
                    if (uf.getSigla().equals(ufdto.getSigla())) {
                        throw new RecursoJaRegistradoException("Sigla " + ufdto.getSigla() +
                                " ja registrada no banco de dados");
                    }
                }
            }
            if (!ufAntesDeSerAlterada.getNome().equals(ufdto.getNome())) {
                for (UF uf :
                        ufs) {
                    if (uf.getNome().equals(ufdto.getNome())) {
                        throw new RecursoJaRegistradoException("Nome " + ufdto.getNome() +
                                " ja registrado no banco de dados");
                    }
                }
            }
            if (ufdto.getStatus() != 1 && ufdto.getStatus() != 2) {
                throw new DadoInvalidoException("Status " + ufdto.getStatus() +
                        " invalido, possíveis status 1 - ativado / 2 - desativado");
            }

            UF uf = UFService.alterarUF(ufAntesDeSerAlterada, ufdto);

            repository.save(uf);

            ufs = repository.findAll();
            return ResponseEntity.ok(ufs);
        }
        throw new DadoInvalidoException("UF do código " + ufdto.getCodigoUF() + " não existe");
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<List<UF>> deletarUF(@PathVariable Long codigo) {

        if (codigo == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        Optional<UF> possivelUF = repository.findByCodigoUF(codigo);

        if (possivelUF.isPresent()) {
            UF ufQueSeraDeletada = possivelUF.get();
            ufQueSeraDeletada.setStatus(2);
            repository.save(ufQueSeraDeletada);

            List<UF> ufs = repository.findAll();
            return ResponseEntity.ok(ufs);
        }
        throw new DadoInvalidoException("UF do código " + codigo + " não existe");
    }
}

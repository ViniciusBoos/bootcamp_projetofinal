package io.github.viniciusboos.projetofinal.controller;

import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.exception.RecursoJaRegistradoException;
import io.github.viniciusboos.projetofinal.modelo.uf.UF;
import io.github.viniciusboos.projetofinal.modelo.uf.UFDTO;
import io.github.viniciusboos.projetofinal.modelo.uf.UFService;
import io.github.viniciusboos.projetofinal.repository.UFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("uf")
public class UFController {

    @Autowired
    private UFRepository repository;

    @GetMapping
    public List<UF> getTodasUF() {

        List<UF> ufs = repository.findAll();
        System.out.println(ufs.get(1));
        if(ufs.isEmpty()) {
            return null;
        }
        return ufs;
    }

    @GetMapping(params = "sigla")
    public UF getUFPorSigla(@RequestParam String sigla) {

        if(sigla == null) {
            throw new DadoInvalidoException("Sigla não pode ser null");
        }

        Optional<UF> possivelUF = repository.findBySigla(sigla);
        if(possivelUF.isPresent()){
            return possivelUF.get();
        }
        throw new DadoInvalidoException("Sigla não existe no banco de dados");
    }

    @PostMapping
    public ResponseEntity<List<UF>> criarUF(@Valid @RequestBody UFDTO ufdto) {

        List<UF> ufs = repository.findAll();
        for (UF uf:
             ufs) {
            if(uf.getSigla().equals(ufdto.getSigla())) {
                throw new RecursoJaRegistradoException("Sigla " + ufdto.getSigla() +
                        " ja registrada no banco de dados");
            }
        }

        if(ufdto.getStatus() != 1 && ufdto.getStatus() != 2) {
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

        if(ufdto.getCodigo() == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        Optional<UF> possivelUF = repository.findByCodigo(ufdto.getCodigo());

        if(possivelUF.isPresent()) {
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
            if (ufdto.getStatus() != 1 && ufdto.getStatus() != 2) {
                throw new DadoInvalidoException("Status " + ufdto.getStatus() +
                        " invalido, possíveis status 1 - ativado / 2 - desativado");
            }


            UF uf = UFService.alterarUF(ufAntesDeSerAlterada, ufdto);

            repository.save(uf);

            ufs = repository.findAll();
            return ResponseEntity.ok(ufs);
        }
        throw new DadoInvalidoException("Código não existe no banco de dados");
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<List<UF>> deletarUF(@PathVariable Long codigo) {

        if(codigo == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        Optional<UF> possivelUF = repository.findByCodigo(codigo);

        if(possivelUF.isPresent()) {
            UF ufQueSeraDeletada = possivelUF.get();
            repository.delete(ufQueSeraDeletada);

            List<UF> ufs = repository.findAll();
            return ResponseEntity.ok().body(ufs);
        }
        throw new DadoInvalidoException("Código não existe no banco de dados");
    }
}

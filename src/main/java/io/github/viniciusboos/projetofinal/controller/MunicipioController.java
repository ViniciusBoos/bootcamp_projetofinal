package io.github.viniciusboos.projetofinal.controller;

import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.exception.RecursoJaRegistradoException;
import io.github.viniciusboos.projetofinal.modelo.municipio.Municipio;
import io.github.viniciusboos.projetofinal.modelo.municipio.MunicipioDTO;
import io.github.viniciusboos.projetofinal.modelo.municipio.MunicipioService;
import io.github.viniciusboos.projetofinal.modelo.uf.UF;
import io.github.viniciusboos.projetofinal.repository.MunicipioRepository;
import io.github.viniciusboos.projetofinal.repository.UFRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("municipio")
public class MunicipioController {

    @Autowired
    private MunicipioRepository repository;

    @Autowired
    private UFRepository ufRepository;

    @GetMapping
    public List<MunicipioDTO> getTodosMunicipios() {
        List<Municipio> municipios = repository.findAll();
        List<MunicipioDTO> municipioDTOs = new ArrayList<>();
        for (Municipio municipio :
                municipios) {
            MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
            municipioDTOs.add(municipioDTO);
        }
        return municipioDTOs;
    }

    @GetMapping(params = "codigoUF")
    public List<MunicipioDTO> getMunicipiosPorCodigoUF(@RequestParam Long codigoUF) {
        List<Municipio> municipios = repository.findByCodigoUF(codigoUF);
        List<MunicipioDTO> municipioDTOs = new ArrayList<>();
        for (Municipio municipio :
                municipios) {
            MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
            municipioDTOs.add(municipioDTO);
        }
        return municipioDTOs;
    }

    @GetMapping(params = "status")
    public List<MunicipioDTO> getMunicipioPorStatus(@RequestParam Integer status) {

        List<Municipio> municipios = repository.findByStatus(status);
        List<MunicipioDTO> municipioDTOs = new ArrayList<>();
        for (Municipio municipio :
                municipios) {
            MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
            municipioDTOs.add(municipioDTO);
        }
        return municipioDTOs;
    }

    @GetMapping(params = "nome")
    public List<MunicipioDTO> getMunicipioPorNome(@RequestParam String nome) {
        List<Municipio> municipios = repository.findByNome(nome);
        List<MunicipioDTO> municipioDTOs = new ArrayList<>();
        for (Municipio municipio :
                municipios) {
            MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
            municipioDTOs.add(municipioDTO);
        }
        return municipioDTOs;
    }

    @GetMapping(params = "codigoMunicipio")
    public ResponseEntity getMunicipioPorCodigoMunicipio(@RequestParam Long codigoMunicipio) {
        return consultaMunicipio(codigoMunicipio, null, null, null);
    }

    @GetMapping(params = {"codigoMunicipio", "nome"})
    public ResponseEntity getMunicipioPorCodigoMunicipioENome(@RequestParam String nome,
                                                              @RequestParam Long codigoMunicipio) {
        return consultaMunicipio(codigoMunicipio, nome, null, null);
    }

    @GetMapping(params = {"status", "nome"})
    public ResponseEntity getMunicipioPorStatusENome(@RequestParam String nome,
                                                     @RequestParam Integer status) {
        return consultaMunicipio(null, nome, null, status);
    }

    @GetMapping(params = {"status", "codigoUF"})
    public ResponseEntity getMunicipioPorStatusECodigoUF(@RequestParam Long codigoUF,
                                                         @RequestParam Integer status) {
        return consultaMunicipio(null, null, codigoUF, status);
    }

    @GetMapping(params = {"status", "codigoMunicipio"})
    public ResponseEntity getMunicipioPorStatusECodigoMunicipio(@RequestParam Long codigoMunicipio,
                                                                @RequestParam Integer status) {
        return consultaMunicipio(codigoMunicipio, null, null, status);
    }

    @GetMapping(params = {"codigoUF", "nome"})
    public ResponseEntity getMunicipioPorNomeECodigoUF(@RequestParam String nome,
                                                       @RequestParam Long codigoUF) {
        return consultaMunicipio(null, nome, codigoUF, null);
    }

    @GetMapping(params = {"codigoMunicipio", "codigoUF"})
    public ResponseEntity getMunicipioPorCodigoMunicipioECodigoUf(@RequestParam Long codigoUF,
                                                                  @RequestParam Long codigoMunicipio) {
        return consultaMunicipio(codigoMunicipio, null, codigoUF, null);
    }

    @GetMapping(params = {"codigoMunicipio", "nome", "codigoUF"})
    public ResponseEntity getMunicipioPorCodigoMunicipioNomeECodigoUF(@RequestParam String nome,
                                                                      @RequestParam Long codigoMunicipio,
                                                                      @RequestParam Long codigoUF) {
        return consultaMunicipio(codigoMunicipio, nome, codigoUF, null);
    }
    @GetMapping(params = {"status", "codigoMunicipio", "codigoUF"})
    public ResponseEntity getMunicipioPorStatusCodigoMunicipioECodigoUF(@RequestParam Long codigoMunicipio,
                                                                @RequestParam Long codigoUF,
                                                                @RequestParam Integer status) {
        return consultaMunicipio(codigoMunicipio, null, codigoUF, status);
    }
    @GetMapping(params = {"status", "codigoUF", "nome"})
    public ResponseEntity getMunicipioPorStatusCodigoUFENome(@RequestParam Long codigoUF,
                                                                        @RequestParam String nome,
                                                                        @RequestParam Integer status) {
        return consultaMunicipio(null, nome, codigoUF, status);
    }
    @GetMapping(params = {"status", "codigoMunicipio", "nome"})
    public ResponseEntity getMunicipioPorStatusCodigoMunicipioENome(@RequestParam Long codigoMunicipio,
                                                                    @RequestParam String nome,
                                                                        @RequestParam Integer status) {
        return consultaMunicipio(codigoMunicipio, nome, null, status);
    }
    @GetMapping(params = {"status", "codigoMunicipio", "nome", "codigoUF"})
    public ResponseEntity getMunicipioPorStatusCodigoMunicipioNomeECodigoUF(@RequestParam Long codigoMunicipio,
                                                                    @RequestParam String nome,
                                                                    @RequestParam Long codigoUF,
                                                                    @RequestParam Integer status) {
        return consultaMunicipio(codigoMunicipio, nome, codigoUF, status);
    }

    private ResponseEntity consultaMunicipio(Long codigoMunicipio,
                                             String nome,
                                             Long codigoUF,
                                             Integer status) {

        if (nome == null && codigoUF == null && status == null) {
            Optional<Municipio> possivelMunicipio = repository.findByCodigoMunicipio(codigoMunicipio);
            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
                return ResponseEntity.ok(municipioDTO);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }

        if (codigoUF == null && status == null) {
            Optional<Municipio> possivelMunicipio = repository.findByCodigoMunicipioAndNome(codigoMunicipio, nome);
            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
                return ResponseEntity.ok(municipioDTO);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if (codigoUF == null && codigoMunicipio == null) {
            List<Municipio> municipios = repository.findByStatusAndNome(status, nome);
            List<MunicipioDTO> municipioDTOS = new ArrayList<>();
            for (Municipio municipio :
                    municipios) {
                municipioDTOS.add(new MunicipioDTO(municipio));
            }
            return ResponseEntity.ok(municipioDTOS);
        }
        if (codigoMunicipio == null && nome == null) {
            List<Municipio> municipios = repository.findByStatusAndCodigoUF(status, codigoUF);
            List<MunicipioDTO> municipioDTOS = new ArrayList<>();
            for (Municipio municipio:
                 municipios) {
                municipioDTOS.add(new MunicipioDTO(municipio));
            }
            return ResponseEntity.ok(municipioDTOS);
        }
        if (codigoUF == null && nome == null) {
            Optional<Municipio> possivelMunicipio = repository.findByStatusAndCodigoMunicipio(status, codigoMunicipio);
            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
                return ResponseEntity.ok(municipioDTO);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }

        if (codigoMunicipio == null && status == null) {
            List<Municipio> municipios = repository.findByNomeAndCodigoUF(nome, codigoUF);
            List<MunicipioDTO> municipioDTOS = new ArrayList<>();
            for (Municipio municipio:
                    municipios) {
                municipioDTOS.add(new MunicipioDTO(municipio));
            }
            return ResponseEntity.ok(municipioDTOS);
        }

        if (nome == null && status == null) {
            Optional<Municipio> possivelMunicipio = repository.findByCodigoMunicipioAndCodigoUF(codigoMunicipio, codigoUF);
            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
                return ResponseEntity.ok(municipioDTO);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }

        if (status == null) {
            Optional<Municipio> possivelMunicipio = repository.findByCodigoMunicipioAndNomeAndCodigoUF(codigoMunicipio, nome, codigoUF);
            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
                return ResponseEntity.ok(municipioDTO);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if(codigoMunicipio == null) {
            List<Municipio> municipios = repository.findByStatusAndCodigoUFAndNome(status, codigoUF, nome);
            List<MunicipioDTO> municipioDTOS = new ArrayList<>();
            for (Municipio municipio:
                    municipios) {
                municipioDTOS.add(new MunicipioDTO(municipio));
            }
            return ResponseEntity.ok(municipioDTOS);
        }
        if(codigoUF == null) {
            Optional<Municipio> possivelMunicipio = repository.findByStatusAndCodigoMunicipioAndNome(status, codigoMunicipio, nome);
            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
                return ResponseEntity.ok(municipioDTO);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }
        if(nome == null) {
            Optional<Municipio> possivelMunicipio = repository.findByStatusAndCodigoMunicipioAndCodigoUF(status, codigoMunicipio, codigoUF);
            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
                return ResponseEntity.ok(municipioDTO);
            }
            return ResponseEntity.ok().body(new ArrayList<>());
        }

        Optional<Municipio> possivelMunicipio = repository.findByCodigoMunicipioAndNomeAndCodigoUFAndStatus(codigoMunicipio, nome, codigoUF, status);
        if (possivelMunicipio.isPresent()) {
            Municipio municipio = possivelMunicipio.get();
            MunicipioDTO municipioDTO = new MunicipioDTO(municipio);
            return ResponseEntity.ok(municipioDTO);
        }
        return ResponseEntity.ok().body(new ArrayList<>());
    }


    @PostMapping
    public ResponseEntity<List<MunicipioDTO>> criarMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO) {

        for (char c :
                municipioDTO.getNome().toCharArray()) {
            if (Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }

        List<Municipio> municipios = repository.findAll();
        for (Municipio municipio :
                municipios) {
            if (Objects.equals(municipio.getUf().getCodigoUF(), municipioDTO.getCodigoUF()) &&
                    municipio.getNome().equals(municipioDTO.getNome())) {
                throw new RecursoJaRegistradoException("Município com nome " + municipioDTO.getNome() +
                        " ja registrado em " + municipio.getUf().getNome() + " no banco de dados");
            }
        }

        if (municipioDTO.getStatus() != 1 && municipioDTO.getStatus() != 2) {
            throw new DadoInvalidoException("Status " + municipioDTO.getStatus() +
                    " invalido, possíveis status 1 - ativado / 2 - desativado");
        }

        Optional<UF> possivelUF = ufRepository.findByCodigoUF(municipioDTO.getCodigoUF());

        if (possivelUF.isPresent()) {
            Municipio municipio = MunicipioService.criarMunicipio(municipioDTO, possivelUF.get());

            repository.save(municipio);

            municipios = repository.findAll();
            List<MunicipioDTO> municipioDTOs = new ArrayList<>();
            for (Municipio municipioAserRegistrado :
                    municipios) {
                municipioDTOs.add(new MunicipioDTO(municipioAserRegistrado));
            }
            return ResponseEntity.ok(municipioDTOs);
        }
        throw new DadoInvalidoException("UF do código " + municipioDTO.getCodigoUF() + " não existe");
    }

    @PutMapping
    public ResponseEntity<List<MunicipioDTO>> alterarMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO) {

        if (municipioDTO.getCodigoUF() == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        for (char c :
                municipioDTO.getNome().toCharArray()) {
            if (Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }

        Optional<Municipio> possivelMunicipio = repository.findByCodigoMunicipio(municipioDTO.getCodigoMunicipio());

        if (possivelMunicipio.isPresent()) {
            Municipio municipioAntesDeSerAlterado = possivelMunicipio.get();

            List<Municipio> municipios = repository.findAll();

            if ((municipioAntesDeSerAlterado.getNome().equals(municipioDTO.getNome()) &&
                    !municipioAntesDeSerAlterado.getUf().getCodigoUF().equals(municipioDTO.getCodigoUF())) ||
                    (!municipioAntesDeSerAlterado.getNome().equals(municipioDTO.getNome()) &&
                    municipioAntesDeSerAlterado.getUf().getCodigoUF().equals(municipioDTO.getCodigoUF())) ||
                    (!municipioAntesDeSerAlterado.getNome().equals(municipioDTO.getNome()) &&
                    !municipioAntesDeSerAlterado.getUf().getCodigoUF().equals(municipioDTO.getCodigoUF()))  ) {
                for (Municipio municipio :
                        municipios) {
                    if (municipio.getUf().getCodigoUF().equals(municipioDTO.getCodigoUF()) &&
                            municipio.getNome().equals(municipioDTO.getNome())) {
                        throw new RecursoJaRegistradoException("Município com nome " + municipioDTO.getNome() +
                                " ja registrado em " + municipio.getUf().getNome() + " no banco de dados");
                    }
                }
            }
            Optional<UF> possivelUF = ufRepository.findByCodigoUF(municipioDTO.getCodigoUF());

            if (possivelUF.isPresent()) {
                UF uf = possivelUF.get();
                Municipio municipio = MunicipioService.alterarMunicipio(municipioAntesDeSerAlterado, municipioDTO, uf);
                repository.save(municipio);

                municipios = repository.findAll();

                List<MunicipioDTO> municipioDTOs = new ArrayList<>();
                for (Municipio municipioAserRegistrado :
                        municipios) {
                    municipioDTOs.add(new MunicipioDTO(municipioAserRegistrado));
                }
                return ResponseEntity.ok(municipioDTOs);
            }
            throw new DadoInvalidoException("UF do código " + municipioDTO.getCodigoUF() + " não existe");

        }
        throw new DadoInvalidoException("Município do código " + municipioDTO.getCodigoMunicipio() + " não existe");
    }

    @DeleteMapping("/{codigoMunicipio}")
    public ResponseEntity<List<MunicipioDTO>> deletarMunicipio(@PathVariable Long codigoMunicipio) {

        if (codigoMunicipio == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        Optional<Municipio> possivelMunicipio = repository.findByCodigoMunicipio(codigoMunicipio);

        if (possivelMunicipio.isPresent()) {
            Municipio municipioQueSeraDeletado = possivelMunicipio.get();
            municipioQueSeraDeletado.setStatus(2);
            repository.save(municipioQueSeraDeletado);

            List<Municipio> municipios = repository.findAll();
            List<MunicipioDTO> municipioDTOs = new ArrayList<>();
            for (Municipio municipioAserRegistrado :
                    municipios) {
                municipioDTOs.add(new MunicipioDTO(municipioAserRegistrado));
            }
            return ResponseEntity.ok(municipioDTOs);
        }
        throw new DadoInvalidoException("Município com código " + codigoMunicipio + " não existe no banco de dados");
    }
}

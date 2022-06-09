package io.github.viniciusboos.projetofinal.controller;

import io.github.viniciusboos.projetofinal.modelo.bairro.Bairro;
import io.github.viniciusboos.projetofinal.modelo.bairro.BairroDTO;
import io.github.viniciusboos.projetofinal.modelo.bairro.BairroService;
import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.exception.RecursoJaRegistradoException;
import io.github.viniciusboos.projetofinal.modelo.municipio.Municipio;
import io.github.viniciusboos.projetofinal.repository.BairroRepository;
import io.github.viniciusboos.projetofinal.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("bairro")
public class BairroController {

    @Autowired
    private BairroRepository repository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @GetMapping
    public List<BairroDTO> getTodosBairros() {

        List<Bairro> bairros = repository.findAll();
        List<BairroDTO> bairroDTOs = new ArrayList<>();
        for (Bairro bairro:
                bairros) {
            bairroDTOs.add(new BairroDTO(bairro));
        }
        return bairroDTOs;
    }

    @GetMapping(params = "codigoMunicipio")
    public List<BairroDTO> getBairroPorCodigoMunicipio(@RequestParam Long codigoMunicipio) {
        List<Bairro> bairros = repository.findByCodigoMunicipio(codigoMunicipio);
        List<BairroDTO> bairroDTOs = new ArrayList<>();
        for (Bairro bairro:
                bairros) {
            bairroDTOs.add(new BairroDTO(bairro));
        }
        return bairroDTOs;
    }

    @GetMapping(params = "status")
    public List<BairroDTO> getBairroPorStatus(@RequestParam Integer status) {

        if (status == 1 || status == 2) {
            List<Bairro> bairros = repository.findByStatus(status);
            List<BairroDTO> bairroDTOs = new ArrayList<>();
            for (Bairro bairro:
                    bairros) {
                bairroDTOs.add(new BairroDTO(bairro));
            }
            return bairroDTOs;
        }
        throw new DadoInvalidoException("Status não existe, " +
                "possíveis status 1 - ativado / 2 - desativado");
    }

    @GetMapping(params = "nome")
    public ResponseEntity getBairroPorNome(@RequestParam String nome) {
        return consultaBairro(null, nome, null, null);
    }

    @GetMapping(params = "codigoBairro")
    public ResponseEntity getBairroPorCodigoBairro(@RequestParam Long codigoBairro) {
        return consultaBairro(codigoBairro, null, null, null);
    }

    @GetMapping(params = {"codigoBairro", "nome"})
    public ResponseEntity getBairroPorCodigoBairroENome(@RequestParam String nome,
                                                @RequestParam Long codigoBairro) {
        return consultaBairro(codigoBairro, nome, null, null);
    }

    @GetMapping(params = {"status", "nome"})
    public ResponseEntity getBairroPorStatusENome(@RequestParam String nome,
                                                   @RequestParam Integer status) {
        return consultaBairro(null, nome, null, status);
    }
    @GetMapping(params = {"status", "codigoBairro"})
    public ResponseEntity getBairroPorStatusECodigoBairro(@RequestParam Integer status,
                                                   @RequestParam Long codigoBairro) {
        return consultaBairro(codigoBairro, null, null, status);
    }
    @GetMapping(params = {"status", "codigoMunicipio"})
    public ResponseEntity getBairroPorStatusECodigoMunicipio(@RequestParam Integer status,
                                                   @RequestParam Long codigoMunicipio) {
        return consultaBairro(null, null, codigoMunicipio, status);
    }

    @GetMapping(params = {"codigoMunicipio", "nome"})
    public ResponseEntity getBairroPorNomeECodigoMunicipio(@RequestParam String nome,
                                                   @RequestParam Long codigoMunicipio) {
        return consultaBairro(null, nome, codigoMunicipio, null);
    }

    @GetMapping(params = {"codigoMunicipio", "codigoBairro"})
    public ResponseEntity getBairroPorCodigoBairroECodigoMunicipio(@RequestParam Long codigoBairro,
                                                           @RequestParam Long codigoMunicipio) {
        return consultaBairro(codigoBairro, null, codigoMunicipio, null);
    }

    @GetMapping(params = {"status", "codigoMunicipio","nome"})
    public ResponseEntity getBairroPorStatusCodigoMunicipioENome(@RequestParam Integer status,
                                                             @RequestParam Long codigoMunicipio,
                                                                 @RequestParam String nome) {
        return consultaBairro(null, nome, codigoMunicipio, status);
    }

    @GetMapping(params = {"status", "codigoBairro","nome"})
    public ResponseEntity getBairroPorStatusCodigoBairroENome(@RequestParam Integer status,
                                                                 @RequestParam Long codigoBairro,
                                                              @RequestParam String nome) {
        return consultaBairro(codigoBairro, nome, null, status);
    }
    @GetMapping(params = {"status", "codigoBairro","codigoMunicipio"})
    public ResponseEntity getBairroPorStatusCodigoBairroECodigoMunicipio(@RequestParam Integer status,
                                                                         @RequestParam Long codigoBairro,
                                                              @RequestParam Long codigoMunicipio) {
        return consultaBairro(codigoBairro, null, codigoMunicipio, status);
    }

    @GetMapping(params = {"codigoMunicipio", "nome", "codigoBairro"})
    public ResponseEntity getBairroPorCodigoBairroNomeECodigoMunicipio(@RequestParam String nome,
                                                 @RequestParam Long codigoMunicipio,
                                                 @RequestParam Long codigoBairro) {
        return consultaBairro(codigoBairro, nome, codigoMunicipio, null);
    }

    @GetMapping(params = {"status", "codigoBairro","codigoMunicipio", "nome"})
    public ResponseEntity getBairroPorStatusCodigoBairroCodigoMunicipioENome(@RequestParam Integer status,
                                                                         @RequestParam Long codigoBairro,
                                                                             @RequestParam String nome,
                                                                         @RequestParam Long codigoMunicipio) {
        return consultaBairro(codigoBairro, nome, codigoMunicipio, status);
    }

    private ResponseEntity consultaBairro(Long codigoBairro,
                                  String nome,
                                  Long codigoMunicipio,
                                          Integer status) {

        if (nome == null && codigoMunicipio == null && status == null) {
            Optional<Bairro> possivelBairro = repository.findByCodigoBairro(codigoBairro);
            if (possivelBairro.isPresent()) {
                return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }

        if (codigoBairro == null && codigoMunicipio == null && status == null) {
            List<Bairro> bairros = repository.findByNome(nome);
            List<BairroDTO> bairroDTOS = new ArrayList<>();

            for (Bairro bairro:
                    bairros) {
                bairroDTOS.add(new BairroDTO(bairro));
            }
            return ResponseEntity.ok(bairroDTOS);
        }

        if (codigoMunicipio == null && status == null) {
            Optional<Bairro> possivelBairro = repository.findByCodigoBairroAndNome(codigoBairro, nome);
            if (possivelBairro.isPresent()) {
                return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }

        if (codigoMunicipio == null && codigoBairro == null) {
            List<Bairro> bairros = repository.findByStatusAndNome(status, nome);
            List<BairroDTO> bairroDTOS = new ArrayList<>();

            for (Bairro bairro:
                 bairros) {
                bairroDTOS.add(new BairroDTO(bairro));
            }
            return ResponseEntity.ok(bairroDTOS);
        }

        if (nome == null && codigoBairro == null) {
            List<Bairro> bairros = repository.findByStatusAndCodigoMunicipio(status, codigoMunicipio);
            List<BairroDTO> bairroDTOS = new ArrayList<>();

            for (Bairro bairro:
                    bairros) {
                bairroDTOS.add(new BairroDTO(bairro));
            }
            return ResponseEntity.ok(bairroDTOS);
        }

        if (nome == null && codigoMunicipio == null) {
            Optional<Bairro> possivelBairro = repository.findByStatusAndCodigoBairro(status, codigoBairro);
            if (possivelBairro.isPresent()) {
                return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }

        if (codigoBairro == null && status == null) {
            List<Bairro> bairros = repository.findByNomeAndCodigoMunicipio(nome, codigoMunicipio);
            List<BairroDTO> bairroDTOS = new ArrayList<>();

            for (Bairro bairro:
                    bairros) {
                bairroDTOS.add(new BairroDTO(bairro));
            }
            return ResponseEntity.ok(bairroDTOS);
        }

        if (nome == null && status == null) {
            Optional<Bairro> possivelBairro = repository.findByCodigoBairroAndCodigoMunicipio(codigoBairro, codigoMunicipio);
            if (possivelBairro.isPresent()) {
                return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }

        if(nome == null) {
            Optional<Bairro> possivelBairro = repository.findByCodigoBairroAndCodigoMunicipioAndStatus(codigoBairro, codigoMunicipio, status);
            if (possivelBairro.isPresent()) {
                return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoBairro == null) {
            List<Bairro> bairros = repository.findByCodigoMunicipioAndNomeAndStatus(codigoMunicipio, nome, status);
            List<BairroDTO> bairroDTOS = new ArrayList<>();

            for (Bairro bairro:
                    bairros) {
                bairroDTOS.add(new BairroDTO(bairro));
            }
            return ResponseEntity.ok(bairroDTOS);
        }
        if(codigoMunicipio == null) {
            Optional<Bairro> possivelBairro = repository.findByCodigoBairroAndNomeAndStatus(codigoBairro, nome, status);
            if (possivelBairro.isPresent()) {
                return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }

        if(status == null){

            Optional<Bairro> possivelBairro = repository.findByCodigoBairroAndNomeAndCodigoMunicipio(codigoBairro, nome, codigoMunicipio);
            if (possivelBairro.isPresent()) {
                return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }

        Optional<Bairro> possivelBairro = repository.findByCodigoBairroAndNomeAndCodigoMunicipioAndStatus(codigoBairro, nome, codigoMunicipio, status);
        if (possivelBairro.isPresent()) {
            return ResponseEntity.ok(new BairroDTO(possivelBairro.get()));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }


    @PostMapping
    public ResponseEntity<List<BairroDTO>> criarBairro(@Valid @RequestBody BairroDTO bairroDTO) {

        for(char c:
                bairroDTO.getNome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }

        List<Bairro> bairros = repository.findAll();
        for (Bairro bairro :
                bairros) {
            if (bairro.getMunicipio().getCodigoMunicipio().equals(bairroDTO.getCodigoMunicipio()) &&
                    bairro.getNome().equals(bairroDTO.getNome())) {
                throw new RecursoJaRegistradoException("Bairro com nome " + bairroDTO.getNome() +
                        " ja registrado em " + bairro.getMunicipio().getNome() + " no banco de dados");
            }
        }

        if (bairroDTO.getStatus() != 1 && bairroDTO.getStatus() != 2) {
            throw new DadoInvalidoException("Status " + bairroDTO.getStatus() +
                    " invalido, possíveis status 1 - ativado / 2 - desativado");
        }

        Optional<Municipio> possivelMunicipio = municipioRepository.findByCodigoMunicipio(bairroDTO.getCodigoMunicipio());

        if (possivelMunicipio.isPresent()) {
            Bairro bairro = BairroService.criarBairro(bairroDTO, possivelMunicipio.get());

            repository.save(bairro);

            bairros = repository.findAll();

            List<BairroDTO> bairroDTOs = new ArrayList<>();
            for (Bairro bairroASerAlterado:
                    bairros) {
                bairroDTOs.add(new BairroDTO(bairroASerAlterado));
            }
            return ResponseEntity.ok(bairroDTOs);
        }
        throw new DadoInvalidoException("Município do código " + bairroDTO.getCodigoMunicipio() + " não existe");
    }

    @PutMapping
    public ResponseEntity<List<BairroDTO>> alterarBairro(@Valid @RequestBody BairroDTO bairroDTO) {

        if (bairroDTO.getCodigoBairro() == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        for(char c:
                bairroDTO.getNome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }

        Optional<Bairro> possivelBairro = repository.findByCodigoBairro(bairroDTO.getCodigoBairro());

        if (possivelBairro.isPresent()) {
            Bairro bairroAntesDeSerAlterado = possivelBairro.get();

            List<Bairro> bairros = repository.findAll();
            if ((bairroAntesDeSerAlterado.getNome().equals(bairroDTO.getNome()) &&
                    !bairroAntesDeSerAlterado.getMunicipio().getCodigoMunicipio().equals(bairroDTO.getCodigoMunicipio())) ||
                    (!bairroAntesDeSerAlterado.getNome().equals(bairroDTO.getNome()) &&
                            bairroAntesDeSerAlterado.getMunicipio().getCodigoMunicipio().equals(bairroDTO.getCodigoMunicipio())) ||
                    (!bairroAntesDeSerAlterado.getNome().equals(bairroDTO.getNome()) &&
                    !bairroAntesDeSerAlterado.getMunicipio().getCodigoMunicipio().equals(bairroDTO.getCodigoMunicipio())) ) {
                for (Bairro bairro :
                        bairros) {
                    if (bairro.getMunicipio().getCodigoMunicipio().equals(bairroDTO.getCodigoMunicipio()) &&
                            bairro.getNome().equals(bairroDTO.getNome())) {
                        throw new RecursoJaRegistradoException("Bairro com nome " + bairroDTO.getNome() +
                                " ja registrado em " + bairro.getMunicipio().getNome() + " no banco de dados");
                    }
                }

                if (bairroDTO.getStatus() != 1 && bairroDTO.getStatus() != 2) {
                    throw new DadoInvalidoException("Status " + bairroDTO.getStatus() +
                            " invalido, possíveis status 1 - ativado / 2 - desativado");
                }

            }
            Optional<Municipio> possivelMunicipio = municipioRepository.findByCodigoMunicipio(bairroDTO.getCodigoMunicipio());

            if (possivelMunicipio.isPresent()) {
                Municipio municipio = possivelMunicipio.get();
                Bairro bairro = BairroService.alterarBairro(bairroDTO, bairroAntesDeSerAlterado, municipio);
                repository.save(bairro);

                bairros = repository.findAll();
                List<BairroDTO> bairroDTOs = new ArrayList<>();
                for (Bairro bairroASerAlterado:
                        bairros) {
                    bairroDTOs.add(new BairroDTO(bairroASerAlterado));
                }
                return ResponseEntity.ok(bairroDTOs);
            }
            throw new DadoInvalidoException("Município do código " + bairroDTO.getCodigoMunicipio() + " não existe");
        }
        throw new DadoInvalidoException("Bairro do código " + bairroDTO.getCodigoBairro() + " não existe");
    }

    @DeleteMapping("/{codigoBairro}")
    public ResponseEntity<List<BairroDTO>> deletarBairro(@PathVariable Long codigoBairro) {

        if (codigoBairro == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        Optional<Bairro> possivelBairro = repository.findByCodigoBairro(codigoBairro);

        if (possivelBairro.isPresent()) {
            Bairro bairroQueSeraDeletado = possivelBairro.get();
            bairroQueSeraDeletado.setStatus(2);
            repository.save(bairroQueSeraDeletado);

            List<Bairro> bairros = repository.findAll();
            List<BairroDTO> bairroDTOs = new ArrayList<>();
            for (Bairro bairroASerAlterado:
                    bairros) {
                bairroDTOs.add(new BairroDTO(bairroASerAlterado));
            }
            return ResponseEntity.ok(bairroDTOs);
        }
        throw new DadoInvalidoException("Bairro com código " + codigoBairro + " não existe no banco de dados");
    }
}

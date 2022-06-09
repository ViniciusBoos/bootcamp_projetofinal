package io.github.viniciusboos.projetofinal.controller;

import io.github.viniciusboos.projetofinal.modelo.bairro.BairroView;
import io.github.viniciusboos.projetofinal.modelo.endereco.Endereco;
import io.github.viniciusboos.projetofinal.modelo.endereco.EnderecoDTO;
import io.github.viniciusboos.projetofinal.modelo.endereco.EnderecoService;
import io.github.viniciusboos.projetofinal.modelo.endereco.EnderecoView;
import io.github.viniciusboos.projetofinal.modelo.exception.DadoInvalidoException;
import io.github.viniciusboos.projetofinal.modelo.municipio.MunicipioView;
import io.github.viniciusboos.projetofinal.modelo.pessoa.Pessoa;
import io.github.viniciusboos.projetofinal.modelo.pessoa.PessoaDTO;
import io.github.viniciusboos.projetofinal.modelo.pessoa.PessoaService;
import io.github.viniciusboos.projetofinal.modelo.pessoa.PessoaView;
import io.github.viniciusboos.projetofinal.repository.BairroRepository;
import io.github.viniciusboos.projetofinal.repository.EnderecoRepository;
import io.github.viniciusboos.projetofinal.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public List<Pessoa> getAllPessoas() {
        return repository.findAll();
    }
    @GetMapping(params = "idade")
    public List<Pessoa> getPessoasPorIdade(@RequestParam Integer idade) {
        return repository.findByIdade(idade);
    }
    @GetMapping(params = "nome")
    public List<Pessoa> getPessoasPorNome(@RequestParam String nome) {
        return repository.findByNome(nome);
    }
    @GetMapping(params = "sobrenome")
    public List<Pessoa> getPessoasPorSobrenome(@RequestParam String sobrenome) {
        return repository.findBySobrenome(sobrenome);
    }
    @GetMapping(params = "status")
    public List<Pessoa> getPessoaPorStatus(@RequestParam Integer status) {
        return repository.findByStatus(status);
    }

    @GetMapping(params = "codigoPessoa")
    public ResponseEntity getPessoaPorCodigoPessoa(@RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, null, null, null, null, null);
    }
    @GetMapping(params = "login")
    public ResponseEntity getPessoaPorLogin(@RequestParam String login) {
        return consultaPessoa(null, null, null, login, null, null);
    }
    @GetMapping(params = {"nome", "sobrenome"})
    public ResponseEntity getPessoaPorNomeESobrenome(@RequestParam String nome, @RequestParam String sobrenome) {
        return consultaPessoa(null, nome, sobrenome, null, null, null);
    }
    @GetMapping(params = {"codigoPessoa", "nome"})
    public ResponseEntity getPessoaPorCodigoPessoaENome(@RequestParam Long codigoPessoa, @RequestParam String nome) {
        return consultaPessoa(codigoPessoa, nome, null, null, null, null);
    }
    @GetMapping(params = {"codigoPessoa", "sobrenome"})
    public ResponseEntity getPessoaPorCodigoPessoaESobrenome(@RequestParam Long codigoPessoa, @RequestParam String sobrenome) {
        return consultaPessoa(codigoPessoa, null, sobrenome, null, null, null);
    }
    @GetMapping(params = {"codigoPessoa", "login"})
    public ResponseEntity getPessoaPorCodigoPessoaELogin(@RequestParam Long codigoPessoa, @RequestParam String login) {
        return consultaPessoa(codigoPessoa, null, null, login, null, null);
    }
    @GetMapping(params = {"nome", "login"})
    public ResponseEntity getPessoaPorNomeELogin(@RequestParam String nome, @RequestParam String login) {
        return consultaPessoa(null, nome, null, login, null, null);
    }
    @GetMapping(params = {"login", "sobrenome"})
    public ResponseEntity getPessoaPorLoginESobrenome(@RequestParam String login, @RequestParam String sobrenome) {
        return consultaPessoa(null, null, sobrenome, login, null, null);
    }
    @GetMapping(params = {"nome", "idade"})
    public ResponseEntity getPessoasPorNomeEIdade(@RequestParam String nome, @RequestParam Integer idade ) {
        return consultaPessoa(null, nome, null, null, idade, null);
    }
    @GetMapping(params = {"sobrenome", "idade"})
    public ResponseEntity getPessoasPorSobrenomeEIdade(@RequestParam String sobrenome, @RequestParam Integer idade ) {
        return consultaPessoa(null, null, sobrenome, null, idade, null);
    }
    @GetMapping(params = {"status", "idade"})
    public ResponseEntity getPessoaPorStatusEIdade(@RequestParam Integer status, @RequestParam Integer idade ) {
        return consultaPessoa(null, null, null, null, idade, status);
    }

    @GetMapping(params = {"codigoPessoa", "idade"})
    public ResponseEntity getPessoaPorCodigoPessoaEIdade(@RequestParam Long codigoPessoa, @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, null, null, null, idade, null);
    }
    @GetMapping(params = {"login", "idade"})
    public ResponseEntity getPessoaPorLoginEIdade(@RequestParam String login, @RequestParam Integer idade) {
        return consultaPessoa(null, null, null, login, idade, null);
    }
    @GetMapping(params = {"nome", "status"})
    public ResponseEntity getPessoasPorNomeEStatus(@RequestParam String nome, @RequestParam Integer status ) {
        return consultaPessoa(null, nome, null, null, null, status);
    }
    @GetMapping(params = {"sobrenome", "status"})
    public ResponseEntity getPessoasPorSobrenomeEStatus(@RequestParam String sobrenome, @RequestParam Integer status ) {
        return consultaPessoa(null, null, sobrenome, null, null, status);
    }
    @GetMapping(params = {"codigoPessoa", "status"})
    public ResponseEntity getPessoaPorCodigoPessoaEStatus(@RequestParam Long codigoPessoa, @RequestParam Integer status) {
        return consultaPessoa(codigoPessoa, null, null, null, null, status);
    }
    @GetMapping(params = {"login", "status"})
    public ResponseEntity getPessoaPorLoginEStatus(@RequestParam String login, @RequestParam Integer status) {
        return consultaPessoa(null, null, null, login, null, status);
    }
    @GetMapping(params = {"codigoPessoa", "nome", "sobrenome"})
    public ResponseEntity getPessoaPorCodigoPessoaNomeESobrenome(@RequestParam String nome,
                                                             @RequestParam String sobrenome,
                                                             @RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, null, null, null);
    }
    @GetMapping(params = {"codigoPessoa", "idade", "sobrenome"})
    public ResponseEntity getPessoaPorCodigoPessoaIdadeESobrenome(@RequestParam Integer idade,
                                                                 @RequestParam String sobrenome,
                                                                 @RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, null, sobrenome, null, idade, null);
    }

    @GetMapping(params = {"login", "nome", "sobrenome"})
    public ResponseEntity getPessoaPorLoginNomeESobrenome(@RequestParam String nome,
                                                             @RequestParam String sobrenome,
                                                             @RequestParam String login) {
        return consultaPessoa(null, nome, sobrenome, login, null, null);
    }
    @GetMapping(params = {"codigoPessoa", "login", "sobrenome"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginESobrenome(@RequestParam String login,
                                                             @RequestParam String sobrenome,
                                                             @RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, null, sobrenome, login, null, null);
    }
    @GetMapping(params = {"codigoPessoa", "nome", "login"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginENome(@RequestParam String login,
                                                              @RequestParam String nome,
                                                              @RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, nome, null, login, null, null);
    }

    @GetMapping(params = {"nome", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorNomeSobrenomeEIdade(@RequestParam String nome,
                                                          @RequestParam String sobrenome,
                                                          @RequestParam Integer idade) {
        return consultaPessoa(null, nome, sobrenome, null, idade, null);
    }
    @GetMapping(params = {"codigoPessoa", "nome", "idade"})
    public ResponseEntity getPessoaPorCodigoPessoaNomeEIdade(@RequestParam Long codigoPessoa,
                                                             @RequestParam String nome,
                                                             @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, nome, null, null, idade, null);
    }
    @GetMapping(params = {"status", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorStatusSobrenomeEIdade(@RequestParam Integer status,
                                                                  @RequestParam String sobrenome,
                                                                  @RequestParam Integer idade) {
        return consultaPessoa(null, null, sobrenome, null, idade, status);
    }
    @GetMapping(params = {"status", "login", "idade"})
    public ResponseEntity getPessoaPorStatusLoginEIdade(@RequestParam Integer status,
                                                            @RequestParam String login,
                                                            @RequestParam Integer idade) {
        return consultaPessoa(null, null, null, login, idade, status);
    }
    @GetMapping(params = {"status", "nome", "idade"})
    public ResponseEntity getPessoaPorStatusNomeEIdade(@RequestParam Integer status,
                                                        @RequestParam String nome,
                                                        @RequestParam Integer idade) {
        return consultaPessoa(null, nome, null, null, idade, status);
    }
    @GetMapping(params = {"status", "codigoPessoa", "idade"})
    public ResponseEntity getPessoaPorStatusCodigoPessoaEIdade(@RequestParam Integer status,
                                                       @RequestParam Long codigoPessoa,
                                                       @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, null, null, null, idade, status);
    }
    @GetMapping(params = {"codigoPessoa", "login", "idade"})
    public ResponseEntity getPessoaPorCodigoPessoaEIdade(@RequestParam Long codigoPessoa,
                                                         @RequestParam String login,
                                                         @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, null, null, login, idade, null);
    }
    @GetMapping(params = {"nome", "login", "idade"})
    public ResponseEntity getPessoaPorNomeLoginEIdade(@RequestParam String nome,
                                                      @RequestParam String login,
                                                      @RequestParam Integer idade) {
        return consultaPessoa(null, nome, null, login, idade, null);
    }
    @GetMapping(params = {"login", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorLoginSobrenomeEIdade(@RequestParam String login,
                                                           @RequestParam String sobrenome,
                                                           @RequestParam Integer idade) {
        return consultaPessoa(null, null, sobrenome, login, idade, null);
    }
    @GetMapping(params = {"login", "sobrenome", "status"})
    public ResponseEntity getPessoaPorLoginSobrenomeEStatus(@RequestParam String login,
                                                           @RequestParam String sobrenome,
                                                           @RequestParam Integer status) {
        return consultaPessoa(null, null, sobrenome, login, null, status);
    }
    @GetMapping(params = {"login", "nome", "status"})
    public ResponseEntity getPessoaPorLoginNomeEStatus(@RequestParam String login,
                                                            @RequestParam String nome,
                                                            @RequestParam Integer status) {
        return consultaPessoa(null, nome, null, login, null, status);
    }
    @GetMapping(params = {"sobrenome", "nome", "status"})
    public ResponseEntity getPessoaPorSobrenomeNomeEStatus(@RequestParam String sobrenome,
                                                       @RequestParam String nome,
                                                       @RequestParam Integer status) {
        return consultaPessoa(null, nome, sobrenome, null, null, status);
    }
    @GetMapping(params = {"sobrenome", "codigoPessoa", "status"})
    public ResponseEntity getPessoaPorSobrenomeNomeEStatus(@RequestParam String sobrenome,
                                                           @RequestParam Long codigoPessoa,
                                                           @RequestParam Integer status) {
        return consultaPessoa(codigoPessoa, null, sobrenome, null, null, status);
    }
    @GetMapping(params = {"login", "codigoPessoa", "status"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginEStatus(@RequestParam String login,
                                                           @RequestParam Long codigoPessoa,
                                                           @RequestParam Integer status) {
        return consultaPessoa(codigoPessoa, null, null, login, null, status);
    }
    @GetMapping(params = {"nome", "codigoPessoa", "status"})
    public ResponseEntity getPessoaPorCodigoPessoaNomeEStatus(@RequestParam String nome,
                                                               @RequestParam Long codigoPessoa,
                                                               @RequestParam Integer status) {
        return consultaPessoa(codigoPessoa, nome, null, null, null, status);
    }
    @GetMapping(params = {"codigoPessoa", "nome", "login", "sobrenome"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginNomeESobrenome(@RequestParam String login,
                                                                      @RequestParam String nome,
                                                                      @RequestParam Long codigoPessoa,
                                                                      @RequestParam String sobrenome) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, login, null, null);
    }
    @GetMapping(params = {"codigoPessoa", "nome", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorCodigoPessoaNomeSobrenomeEIdade(@RequestParam String nome,
                                                                      @RequestParam String sobrenome,
                                                                      @RequestParam Long codigoPessoa,
                                                                      @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, null, idade, null);
    }

    @GetMapping(params = {"login", "nome", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorLoginNomeSobrenomeEIdade(@RequestParam String nome,
                                                               @RequestParam String sobrenome,
                                                               @RequestParam String login,
                                                               @RequestParam Integer idade) {
        return consultaPessoa(null, nome, sobrenome, login, idade, null);
    }
    @GetMapping(params = {"codigoPessoa", "login", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginSobrenomeEIdade(@RequestParam String login,
                                                                       @RequestParam String sobrenome,
                                                                       @RequestParam Long codigoPessoa,
                                                                       @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, null, sobrenome, login, idade, null);
    }
    @GetMapping(params = {"codigoPessoa", "nome", "login", "idade"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginNomeEIdade(@RequestParam String login,
                                                                  @RequestParam String nome,
                                                                  @RequestParam Long codigoPessoa,
                                                                  @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, nome, null, login, idade, null);
    }
    @GetMapping(params = {"sobrenome", "status", "login", "idade"})
    public ResponseEntity getPessoaPorSobrenomeStatusLoginEIdade(@RequestParam String login,
                                                                  @RequestParam Integer status,
                                                                  @RequestParam String sobrenome,
                                                                  @RequestParam Integer idade) {
        return consultaPessoa(null, null, sobrenome, login, idade, status);
    }
    @GetMapping(params = {"sobrenome", "status", "nome", "idade"})
    public ResponseEntity getPessoaPorSobrenomeStatusNomeEIdade(@RequestParam String nome,
                                                                  @RequestParam Integer status,
                                                                  @RequestParam String sobrenome,
                                                                  @RequestParam Integer idade) {
        return consultaPessoa(null, nome, sobrenome, null, idade, status);
    }

    @GetMapping(params = {"sobrenome", "status", "codigoPessoa", "idade"})
    public ResponseEntity getPessoaPorSobrenomeStatusCodigoPessoaEIdade(@RequestParam Long codigoPessoa,
                                                                @RequestParam Integer status,
                                                                @RequestParam String sobrenome,
                                                                @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, null, sobrenome, null, idade, status);
    }
    @GetMapping(params = {"nome", "status", "login", "idade"})
    public ResponseEntity getPessoaPorLoginStatusNomeEIdade(@RequestParam String nome,
                                                                @RequestParam Integer status,
                                                                @RequestParam String login,
                                                                @RequestParam Integer idade) {
        return consultaPessoa(null, nome, null, login, idade, status);
    }
    @GetMapping(params = {"codigoPessoa", "status", "login", "idade"})
    public ResponseEntity getPessoaPorLoginStatusCodigoPessoaEIdade(@RequestParam Long codigoPessoa,
                                                            @RequestParam Integer status,
                                                            @RequestParam String login,
                                                            @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, null, null, login, idade, status);
    }
    @GetMapping(params = {"codigoPessoa", "status", "nome", "idade"})
    public ResponseEntity getPessoaPorNomeStatusCodigoPessoaEIdade(@RequestParam Long codigoPessoa,
                                                                    @RequestParam Integer status,
                                                                    @RequestParam String nome,
                                                                    @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, nome, null, null, idade, status);
    }
    @GetMapping(params = {"status", "sobrenome", "login", "nome"})
    public ResponseEntity getPessoaPorStatusSobrenomeLoginENome(@RequestParam Integer status,
                                                                   @RequestParam String sobrenome,
                                                                   @RequestParam String login,
                                                                   @RequestParam String nome) {
        return consultaPessoa(null, nome, sobrenome, login, null, status);
    }
    @GetMapping(params = {"status", "sobrenome", "login", "codigoPessoa"})
    public ResponseEntity getPessoaPorStatusSobrenomeLoginECodigoPessoa(@RequestParam Integer status,
                                                                   @RequestParam String sobrenome,
                                                                   @RequestParam String login,
                                                                   @RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, null, sobrenome, login, null, status);
    }
    @GetMapping(params = {"status", "sobrenome", "nome", "codigoPessoa"})
    public ResponseEntity getPessoaPorStatusSobrenomeNomeECodigoPessoa(@RequestParam Integer status,
                                                                        @RequestParam String sobrenome,
                                                                        @RequestParam String nome,
                                                                        @RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, null, null, status);
    }

    @GetMapping(params = {"codigoPessoa", "nome", "login", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginNomeSobrenomeEIdade(@RequestParam String login,
                                                                           @RequestParam String nome,
                                                                           @RequestParam Long codigoPessoa,
                                                                           @RequestParam String sobrenome,
                                                                           @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, login, idade, null);
    }
    @GetMapping(params = {"status", "nome", "login", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorStatusLoginNomeSobrenomeEIdade(@RequestParam String login,
                                                                           @RequestParam String nome,
                                                                           @RequestParam Integer status,
                                                                           @RequestParam String sobrenome,
                                                                           @RequestParam Integer idade) {
        return consultaPessoa(null, nome, sobrenome, login, idade, status);
    }
    @GetMapping(params = {"status", "codigoPessoa", "login", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorStatusCodigoPessoaLoginSobrenomeEIdade(@RequestParam String login,
                                                                     @RequestParam Long codigoPessoa,
                                                                     @RequestParam Integer status,
                                                                     @RequestParam String sobrenome,
                                                                     @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, null, sobrenome, login, idade, status);
    }
    @GetMapping(params = {"status", "nome", "codigoPessoa", "sobrenome", "idade"})
    public ResponseEntity getPessoaPorStatusNomeCodigoPessoaSobrenomeEIdade(@RequestParam Long codigoPessoa,
                                                                     @RequestParam String nome,
                                                                     @RequestParam Integer status,
                                                                     @RequestParam String sobrenome,
                                                                     @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, null, idade, status);
    }
    @GetMapping(params = {"status", "nome", "login", "codigoPessoa", "idade"})
    public ResponseEntity getPessoaPorStatusNomeLoginCodigoPessoaEIdade(@RequestParam String login,
                                                                     @RequestParam String nome,
                                                                     @RequestParam Integer status,
                                                                     @RequestParam Long codigoPessoa,
                                                                     @RequestParam Integer idade) {
        return consultaPessoa(codigoPessoa, nome, null, login, idade, status);
    }
    @GetMapping(params = {"status", "nome", "login", "sobrenome", "codigoPessoa"})
    public ResponseEntity getPessoaPorStatusNomeLoginSobrenomeECodigoPessoa(@RequestParam String login,
                                                                     @RequestParam String nome,
                                                                     @RequestParam Integer status,
                                                                     @RequestParam String sobrenome,
                                                                     @RequestParam Long codigoPessoa) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, login, null, status);
    }
    @GetMapping(params = {"codigoPessoa", "nome", "login", "sobrenome", "idade", "status"})
    public ResponseEntity getPessoaPorCodigoPessoaLoginNomeSobrenomeIdadeEStatus(@RequestParam String login,
                                                                      @RequestParam String nome,
                                                                      @RequestParam Long codigoPessoa,
                                                                      @RequestParam String sobrenome,
                                                                      @RequestParam Integer idade,
                                                                      @RequestParam Integer status) {
        return consultaPessoa(codigoPessoa, nome, sobrenome, login, idade, status);
    }

    public ResponseEntity consultaPessoa(Long codigoPessoa, String nome, String sobrenome, String login, Integer idade, Integer status) {

        if(nome == null && sobrenome == null && login == null && idade == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoa(codigoPessoa);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && nome == null && sobrenome == null && idade == null && status == null) {
            List<Pessoa> pessoas = repository.findByLogin(login);
            return ResponseEntity.ok(pessoas);
        }
        if(codigoPessoa == null && nome == null && sobrenome == null && status == null) {
            List<Pessoa> pessoas = repository.findByLoginAndIdade(login, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(nome == null && sobrenome == null && login == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndIdade(codigoPessoa, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(nome == null && sobrenome == null && login == null && codigoPessoa == null) {
            List<Pessoa> pessoas = repository.findByStatusAndIdade(status, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(codigoPessoa == null && sobrenome == null && login == null && status == null) {
            List<Pessoa> pessoas = repository.findByNomeAndIdade(nome, idade);
            return ResponseEntity.ok(pessoas);
        }

        if(codigoPessoa == null && nome == null && login == null && status == null) {
            List<Pessoa> pessoas = repository.findBySobrenomeAndIdade(sobrenome, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(codigoPessoa == null && nome == null && sobrenome == null && idade == null) {
            List<Pessoa> pessoas = repository.findByLoginAndStatus(login, status);
            return ResponseEntity.ok(pessoas);
        }
        if(nome == null && sobrenome == null && login == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndStatus(codigoPessoa, status);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && sobrenome == null && login == null && idade == null) {
            List<Pessoa> pessoas = repository.findByNomeAndStatus(nome, status);
            return ResponseEntity.ok(pessoas);
        }

        if(codigoPessoa == null && nome == null && login == null && idade == null) {
            List<Pessoa> pessoas = repository.findBySobrenomeAndStatus(sobrenome, status);
            return ResponseEntity.ok(pessoas);
        }
        if(sobrenome == null && login == null && idade == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndNome(codigoPessoa, nome);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(nome == null && login == null && idade == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndSobrenome(codigoPessoa, sobrenome);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(nome == null && sobrenome == null && idade == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndLogin(codigoPessoa, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && login == null && idade == null && status == null) {
            List<Pessoa> pessoas = repository.findByNomeAndSobrenome(nome, sobrenome);
            return ResponseEntity.ok(pessoas);
        }
        if(codigoPessoa == null && sobrenome == null && idade == null && status == null) {
            List<Pessoa> pessoas = repository.findByNomeAndLogin(nome, login);
            return ResponseEntity.ok(pessoas);
        }
        if(codigoPessoa == null && nome == null && idade == null && status == null) {
            List<Pessoa> pessoas = repository.findBySobrenomeAndLogin(sobrenome, login);
            return ResponseEntity.ok(pessoas);
        }
        if(codigoPessoa == null && idade == null && status == null) {
            List<Pessoa> pessoas = repository.findByNomeAndSobrenomeAndLogin(nome, sobrenome, login);
            return ResponseEntity.ok(pessoas);
        }
        if(nome == null && idade == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndSobrenomeAndLogin(codigoPessoa, sobrenome, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && sobrenome == null && status == null) {
            List<Pessoa> pessoas = repository.findByNomeAndLoginAndIdade(nome, login, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(sobrenome == null && idade == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndNomeAndLogin(codigoPessoa, nome, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(login == null && nome == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndIdadeAndSobrenome(codigoPessoa, idade, sobrenome);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && nome == null && status == null) {
            List<Pessoa> pessoas = repository.findBySobrenomeAndIdadeAndLogin(sobrenome, idade, login);
            return ResponseEntity.ok(pessoas);
        }
        if(nome == null && sobrenome == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndIdadeAndLogin(codigoPessoa, idade, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(sobrenome == null && login == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndNomeAndIdade(codigoPessoa, nome, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(login == null && codigoPessoa == null && status == null) {
            List<Pessoa> pessoas = repository.findByIdadeAndNomeAndSobrenome(idade, nome, sobrenome);
            return ResponseEntity.ok(pessoas);
        }
        if(login == null && nome == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndStatusAndSobrenome(codigoPessoa, status, sobrenome);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && nome == null && idade == null) {
            List<Pessoa> pessoas = repository.findBySobrenomeAndStatusAndLogin(sobrenome, status, login);
            return ResponseEntity.ok(pessoas);
        }
        if(nome == null && sobrenome == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndStatusAndLogin(codigoPessoa, status, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(sobrenome == null && login == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndNomeAndStatus(codigoPessoa, nome, status);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(login == null && codigoPessoa == null && idade == null) {
            List<Pessoa> pessoas = repository.findByStatusAndNomeAndSobrenome(status, nome, sobrenome);
            return ResponseEntity.ok(pessoas);
        }
        if(login == null && codigoPessoa == null && nome == null) {
            List<Pessoa> pessoas = repository.findByStatusAndIdadeAndSobrenome(status, idade, sobrenome);
            return ResponseEntity.ok(pessoas);
        }
        if(sobrenome == null && codigoPessoa == null && nome == null) {
            List<Pessoa> pessoas = repository.findByStatusAndIdadeAndLogin(status, idade, login);
            return ResponseEntity.ok(pessoas);
        }
        if(login == null && status == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoaAndNomeAndSobrenome(codigoPessoa, nome, sobrenome);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && idade == null && sobrenome == null) {
            List<Pessoa> pessoas = repository.findByLoginAndNomeAndStatus(login, nome, status);
            return ResponseEntity.ok(pessoas);
        }
        if(sobrenome == null && login == null && nome == null) {
            Optional<Pessoa> possivelPessoa = repository.findByStatusAndIdadeAndCodigoPessoa(status, idade, codigoPessoa);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(sobrenome == null && login == null && codigoPessoa == null) {
            List<Pessoa> pessoas = repository.findByStatusAndIdadeAndNome(status, idade, nome);
            return ResponseEntity.ok(pessoas);
        }

        if(idade == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByCodigoPessoaAndNomeAndSobrenomeAndLogin(codigoPessoa, nome, sobrenome, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(nome == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByCodigoPessoaAndIdadeAndSobrenomeAndLogin(codigoPessoa, idade, sobrenome, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(sobrenome == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByCodigoPessoaAndIdadeAndNomeAndLogin(codigoPessoa, idade, nome, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && status == null) {
            List<Pessoa> pessoas = repository
                    .findBySobrenomeAndIdadeAndNomeAndLogin(sobrenome, idade, nome, login);
            return ResponseEntity.ok(pessoas);
        }
        if(login == null && status == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findBySobrenomeAndIdadeAndNomeAndCodigoPessoa(sobrenome, idade, nome, codigoPessoa);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(nome == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByCodigoPessoaAndStatusAndSobrenomeAndLogin(codigoPessoa, status, sobrenome, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(sobrenome == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByCodigoPessoaAndStatusAndNomeAndLogin(codigoPessoa, status, nome, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null && idade == null) {
            List<Pessoa> pessoas = repository
                    .findBySobrenomeAndStatusAndNomeAndLogin(sobrenome, status, nome, login);
            return ResponseEntity.ok(pessoas);
        }
        if(login == null && idade == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findBySobrenomeAndStatusAndNomeAndCodigoPessoa(sobrenome, status, nome, codigoPessoa);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(login == null && codigoPessoa == null) {
            List<Pessoa> pessoas = repository
                    .findBySobrenomeAndStatusAndNomeAndIdade(sobrenome, status, nome, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(nome == null && codigoPessoa == null) {
            List<Pessoa> pessoas = repository
                    .findBySobrenomeAndStatusAndLoginAndIdade(sobrenome, status, login, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(sobrenome == null && codigoPessoa == null) {
            List<Pessoa> pessoas = repository
                    .findByNomeAndStatusAndLoginAndIdade(nome, status, login, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(sobrenome == null && nome == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByCodigoPessoaAndStatusAndLoginAndIdade(codigoPessoa, status, login, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(nome == null && login == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findBySobrenomeAndStatusAndCodigoPessoaAndIdade(sobrenome, status, codigoPessoa, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(login == null && sobrenome == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByNomeAndStatusAndCodigoPessoaAndIdade(nome, status, codigoPessoa, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(codigoPessoa == null) {
            List<Pessoa> pessoas = repository
                    .findByNomeAndSobrenomeAndStatusAndLoginAndIdade(nome, sobrenome, status, login, idade);
            return ResponseEntity.ok(pessoas);
        }
        if(nome == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByCodigoPessoaAndSobrenomeAndStatusAndLoginAndIdade(codigoPessoa, sobrenome, status, login, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(sobrenome == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByNomeAndCodigoPessoaAndStatusAndLoginAndIdade(nome, codigoPessoa, status, login, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(status == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByNomeAndSobrenomeAndCodigoPessoaAndLoginAndIdade(nome, sobrenome, codigoPessoa, login, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(login == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByNomeAndSobrenomeAndStatusAndCodigoPessoaAndIdade(nome, sobrenome, status, codigoPessoa, idade);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }
        if(idade == null) {
            Optional<Pessoa> possivelPessoa = repository
                    .findByNomeAndSobrenomeAndStatusAndCodigoPessoaAndLogin(nome, sobrenome, status, codigoPessoa, login);
            if(possivelPessoa.isPresent()) {
                Pessoa pessoa = possivelPessoa.get();
                return ResponseEntity.ok(criarPessoaView(pessoa));
            }
            return ResponseEntity.ok(new ArrayList<>());
        }

        Optional<Pessoa> possivelPessoa = repository
                .findByCodigoPessoaAndNomeAndSobrenomeAndLoginAndIdadeAndStatus(codigoPessoa, nome, sobrenome, login, idade, status);
        if(possivelPessoa.isPresent()) {
            Pessoa pessoa = possivelPessoa.get();
            return ResponseEntity.ok(criarPessoaView(pessoa));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    public PessoaView criarPessoaView(Pessoa pessoa) {
        PessoaView pessoaView = new PessoaView(pessoa);

        List<Endereco> enderecos = enderecoRepository.findByCodigoPessoa(pessoa.getCodigoPessoa());
        List<EnderecoView> enderecoViews = new ArrayList<>();

        if(!enderecos.isEmpty()) {
            for (Endereco endereco :
                    enderecos) {
                MunicipioView municipioView = new MunicipioView(endereco.getBairro().getMunicipio());
                municipioView.setUf(endereco.getBairro().getMunicipio().getUf());

                BairroView bairroView = new BairroView(endereco.getBairro());
                bairroView.setMunicipioView(municipioView);

                EnderecoView enderecoView = new EnderecoView(endereco);
                enderecoView.setBairroView(bairroView);
                enderecoViews.add(enderecoView);
            }
            pessoaView.setEnderecoViews(enderecoViews);
            return pessoaView;
        }
        throw new DadoInvalidoException("Essa pessoa não tem endereços!");
    }

    @PostMapping
    public ResponseEntity<List<Pessoa>> criarPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {

        for(char c:
                pessoaDTO.getNome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }
        for(char c:
                pessoaDTO.getSobrenome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Sobrenome não pode ter números");
            }
        }

        if (pessoaDTO.getEnderecos() == null) {
            throw new DadoInvalidoException("Endereços são obrigatórios");
        }

        if (pessoaDTO.getEnderecos().isEmpty()) {
            throw new DadoInvalidoException("Endereços não pode ser vazio");
        }

        List<Pessoa> pessoas = repository.findAll();

        for (Pessoa pessoa :
                pessoas) {
            if (pessoa.getLogin().equals(pessoaDTO.getLogin())) {
                throw new DadoInvalidoException("Login ja registrado no banco de dados");
            }
        }

        Pessoa pessoa = PessoaService.criarPessoa(pessoaDTO);

        List<Endereco> enderecos = new ArrayList<>();

        for (EnderecoDTO enderecoDTO :
                pessoaDTO.getEnderecos()) {
            Endereco endereco = EnderecoService.criarEnderecos(enderecoDTO, pessoa, bairroRepository);
            enderecos.add(endereco);
        }

        repository.save(pessoa);

        for (Endereco endereco :
                enderecos) {
            enderecoRepository.save(endereco);
        }

        pessoas = repository.findAll();
        return ResponseEntity.status(200).body(pessoas);
    }

    @PutMapping
    public ResponseEntity<List<Pessoa>> alterarPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {

        if (pessoaDTO.getCodigoPessoa() == null) {
            throw new DadoInvalidoException("CodigoPessoa é obrigatório");
        }
        if (pessoaDTO.getEnderecos() == null) {
            throw new DadoInvalidoException("Endereços são obrigatórios");
        }
        if (pessoaDTO.getEnderecos().isEmpty()) {
            throw new DadoInvalidoException("Endereços não pode ser vazio");
        }

        for(char c:
                pessoaDTO.getNome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Nome não pode ter números");
            }
        }
        for(char c:
                pessoaDTO.getSobrenome().toCharArray()) {
            if(Character.isDigit(c)) {
                throw new DadoInvalidoException("Sobrenome não pode ter números");
            }
        }

        Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoa(pessoaDTO.getCodigoPessoa());
        List<Endereco> enderecos = new ArrayList<>();
        List<Endereco> enderecosASerDeletado = enderecoRepository.findByCodigoPessoa(pessoaDTO.getCodigoPessoa());

        if (possivelPessoa.isPresent()) {

            Pessoa pessoaASerAlterada = possivelPessoa.get();

            for (EnderecoDTO enderecoDTO:
                    pessoaDTO.getEnderecos()) {
                if (enderecoDTO.getCodigoPessoa() == null) {
                    throw new DadoInvalidoException("CodigoPessoa é obrigatório no endereços");
                }
                if (!enderecoDTO.getCodigoPessoa().equals(pessoaASerAlterada.getCodigoPessoa())) {
                    throw new DadoInvalidoException("CodigoPessoa do endereço " + enderecoDTO.getNomeRua()
                            + " não pode ser diferente do codigoPessoa da pessoa sendo alterada");
                }

                System.out.println(enderecoDTO.getCodigoEndereco());
                if (enderecoDTO.getCodigoEndereco() == null || enderecoDTO.getCodigoEndereco() == 0) {
                    Endereco endereco = EnderecoService
                            .criarEnderecos(enderecoDTO, pessoaASerAlterada, bairroRepository);
                    enderecos.add(endereco);
                }

                if (enderecoDTO.getCodigoEndereco() != null) {
                    Endereco endereco = EnderecoService
                            .alterarEndereco(enderecoDTO, pessoaASerAlterada, bairroRepository);
                    enderecos.add(endereco);
                    Optional<Endereco> removerEndereco = enderecoRepository.findByCodigoEndereco(endereco.getCodigoEndereco());
                    enderecosASerDeletado.remove(removerEndereco.get());
                }
            }

            List<Pessoa> pessoas = repository.findAll();

            if (pessoaDTO.getLogin().equals(pessoaASerAlterada.getLogin()) &&
                    !pessoaDTO.getCodigoPessoa().equals(pessoaASerAlterada.getCodigoPessoa())) {
                for (Pessoa pessoa :
                        pessoas) {
                    if (pessoa.getLogin().equals(pessoaDTO.getLogin())) {
                        throw new DadoInvalidoException("Login ja registrado no banco de dados");
                    }
                }
            }

            Pessoa pessoa = PessoaService.alterarPessoa(pessoaDTO, pessoaASerAlterada);

            repository.save(pessoa);

            for (Endereco endereco :
                    enderecos) {
                enderecoRepository.save(endereco);
            }

            if(!enderecosASerDeletado.isEmpty()) {
                for (Endereco endereco:
                        enderecosASerDeletado) {
                    enderecoRepository.deleteById(endereco.getCodigoEndereco());
                }
            }

            pessoas = repository.findAll();
            return ResponseEntity.status(200).body(pessoas);
        }
        throw new DadoInvalidoException("CodigoPessoa " + pessoaDTO.getCodigoPessoa() + " não existe no banco de dados");
    }

    @DeleteMapping("/{codigoPessoa}")
    public ResponseEntity<List<Pessoa>> deletarMunicipio(@PathVariable Long codigoPessoa) {

        if (codigoPessoa == null) {
            throw new DadoInvalidoException("Código é necessário");
        }

        Optional<Pessoa> possivelPessoa = repository.findByCodigoPessoa(codigoPessoa);

        if (possivelPessoa.isPresent()) {
            Pessoa PessoaQueSeraDeletada = possivelPessoa.get();
            PessoaQueSeraDeletada.setStatus(2);
            repository.save(PessoaQueSeraDeletada);

            List<Pessoa> pessoas = repository.findAll();
            return ResponseEntity.ok(pessoas);
        }
        throw new DadoInvalidoException("Pessoa com código " + codigoPessoa + " não existe no banco de dados");
    }
}

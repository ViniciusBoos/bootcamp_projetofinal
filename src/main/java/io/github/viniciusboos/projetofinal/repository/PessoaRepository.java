package io.github.viniciusboos.projetofinal.repository;

import io.github.viniciusboos.projetofinal.modelo.pessoa.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    List<Pessoa> findByIdade(Integer idade);

    List<Pessoa> findByNome(String nome);

    List<Pessoa> findBySobrenome(String sobrenome);

    List<Pessoa> findByStatus(Integer status);

    Optional<Pessoa> findByCodigoPessoa(Long codigoPessoa);

    Optional<Pessoa> findByCodigoPessoaAndNomeAndSobrenomeAndLogin(Long codigoPessoa, String nome, String sobrenome, String login);

    List<Pessoa> findByNomeAndSobrenome(String nome, String sobrenome);

    List<Pessoa> findByLogin(String login);

    Optional<Pessoa> findByCodigoPessoaAndNome(Long codigoPessoa, String nome);

    Optional<Pessoa> findByCodigoPessoaAndSobrenome(Long codigoPessoa, String sobrenome);

    Optional<Pessoa> findByCodigoPessoaAndLogin(Long codigoPessoa, String login);

    List<Pessoa> findByNomeAndLogin(String nome, String login);

    List<Pessoa> findBySobrenomeAndLogin(String sobrenome, String login);

    List<Pessoa> findByNomeAndSobrenomeAndLogin(String nome, String sobrenome, String login);

    Optional<Pessoa> findByCodigoPessoaAndSobrenomeAndLogin(Long codigoPessoa, String sobrenome, String login);

    Optional<Pessoa> findByCodigoPessoaAndNomeAndLogin(Long codigoPessoa, String nome, String login);

    Optional<Pessoa> findByCodigoPessoaAndNomeAndSobrenome(Long codigoPessoa, String nome, String sobrenome);

    Optional<Pessoa> findByCodigoPessoaAndNomeAndSobrenomeAndLoginAndIdadeAndStatus(Long codigoPessoa, String nome, String sobrenome, String login, Integer idade, Integer status);

    List<Pessoa> findBySobrenomeAndStatus(String sobrenome, Integer status);

    List<Pessoa> findByNomeAndStatus(String nome, Integer status);

    Optional<Pessoa> findByCodigoPessoaAndStatus(Long codigoPessoa, Integer status);

    List<Pessoa> findByLoginAndStatus(String login, Integer status);

    List<Pessoa> findBySobrenomeAndIdade(String sobrenome, Integer idade);

    List<Pessoa> findByNomeAndIdade(String nome, Integer idade);

    List<Pessoa> findByStatusAndIdade(Integer status, Integer idade);

    List<Pessoa> findByLoginAndIdade(String login, Integer idade);

    Optional<Pessoa> findByCodigoPessoaAndIdade(Long codigoPessoa, Integer idade);

    Optional<Pessoa> findByCodigoPessoaAndIdadeAndSobrenome(Long codigoPessoa, Integer idade, String sobrenome);

    List<Pessoa>findBySobrenomeAndIdadeAndLogin(String sobrenome, Integer idade, String login);

    Optional<Pessoa> findByCodigoPessoaAndIdadeAndLogin(Long codigoPessoa, Integer idade, String login);

    Optional<Pessoa> findByCodigoPessoaAndNomeAndIdade(Long codigoPessoa, String nome, Integer idade);

    List<Pessoa> findByIdadeAndNomeAndSobrenome(Integer idade, String nome, String sobrenome);

    Optional<Pessoa> findByCodigoPessoaAndStatusAndSobrenome(Long codigoPessoa, Integer status, String sobrenome);

    List<Pessoa> findBySobrenomeAndStatusAndLogin(String sobrenome, Integer status, String login);

    Optional<Pessoa> findByCodigoPessoaAndStatusAndLogin(Long codigoPessoa, Integer status, String login);

    Optional<Pessoa> findByCodigoPessoaAndNomeAndStatus(Long codigoPessoa, String nome, Integer status);

    List<Pessoa> findByStatusAndNomeAndSobrenome(Integer status, String nome, String sobrenome);

    List<Pessoa> findByStatusAndIdadeAndSobrenome(Integer status, Integer idade, String sobrenome);

    List<Pessoa> findByStatusAndIdadeAndLogin(Integer status, Integer idade, String login);

    Optional<Pessoa> findByStatusAndIdadeAndCodigoPessoa(Integer status, Integer idade, Long codigoPessoa);

    List<Pessoa> findByStatusAndIdadeAndNome(Integer status, Integer idade, String nome);

    Optional<Pessoa> findByCodigoPessoaAndIdadeAndSobrenomeAndLogin(Long codigoPessoa, Integer idade, String sobrenome, String login);

    Optional<Pessoa> findByCodigoPessoaAndIdadeAndNomeAndLogin(Long codigoPessoa, Integer idade, String nome, String login);

    List<Pessoa> findBySobrenomeAndIdadeAndNomeAndLogin(String sobrenome, Integer idade, String nome, String login);

    Optional<Pessoa> findBySobrenomeAndIdadeAndNomeAndCodigoPessoa(String sobrenome, Integer idade, String nome, Long codigoPessoa);

    Optional<Pessoa> findByCodigoPessoaAndStatusAndSobrenomeAndLogin(Long codigoPessoa, Integer status, String sobrenome, String login);

    Optional<Pessoa> findByCodigoPessoaAndStatusAndNomeAndLogin(Long codigoPessoa, Integer status, String nome, String login);

    List<Pessoa> findBySobrenomeAndStatusAndNomeAndLogin(String sobrenome, Integer status, String nome, String login);

    Optional<Pessoa> findBySobrenomeAndStatusAndNomeAndCodigoPessoa(String sobrenome, Integer status, String nome, Long codigoPessoa);

    List<Pessoa> findBySobrenomeAndStatusAndNomeAndIdade(String sobrenome, Integer status, String nome, Integer idade);

    Optional<Pessoa> findBySobrenomeAndStatusAndCodigoPessoaAndIdade(String sobrenome, Integer status, Long codigoPessoa, Integer idade);

    Optional<Pessoa> findByNomeAndStatusAndCodigoPessoaAndIdade(String nome, Integer status, Long codigoPessoa, Integer idade);

    Optional<Pessoa> findByCodigoPessoaAndStatusAndLoginAndIdade(Long codigoPessoa, Integer status, String login, Integer idade);

    List<Pessoa> findByLoginAndNomeAndStatus(String login, String nome, Integer status);

    List<Pessoa>findBySobrenomeAndStatusAndLoginAndIdade(String sobrenome, Integer status, String login, Integer idade);

    List<Pessoa> findByNomeAndStatusAndLoginAndIdade(String nome, Integer status, String login, Integer idade);

    List<Pessoa> findByNomeAndSobrenomeAndStatusAndLoginAndIdade(String nome, String sobrenome, Integer status, String login, Integer idade);

    Optional<Pessoa> findByCodigoPessoaAndSobrenomeAndStatusAndLoginAndIdade(Long codigoPessoa, String sobrenome, Integer status, String login, Integer idade);

    Optional<Pessoa> findByNomeAndCodigoPessoaAndStatusAndLoginAndIdade(String nome, Long codigoPessoa, Integer status, String login, Integer idade);

    Optional<Pessoa> findByNomeAndSobrenomeAndCodigoPessoaAndLoginAndIdade(String nome, String sobrenome, Long codigoPessoa, String login, Integer idade);

    Optional<Pessoa> findByNomeAndSobrenomeAndStatusAndCodigoPessoaAndIdade(String nome, String sobrenome, Integer status, Long codigoPessoa, Integer idade);

    Optional<Pessoa> findByNomeAndSobrenomeAndStatusAndCodigoPessoaAndLogin(String nome, String sobrenome, Integer status, Long codigoPessoa, String login);

    List<Pessoa> findByNomeAndLoginAndIdade(String nome, String login, Integer idade);
}

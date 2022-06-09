package io.github.viniciusboos.projetofinal.repository;

import io.github.viniciusboos.projetofinal.modelo.uf.UF;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Long> {

    Optional<UF> findBySigla(String sigla);
    Optional<UF> findByCodigoUF(Long codigo);

    Optional<UF> findByNome(String nome);

    List<UF> findByStatus(Integer status);

    Optional<UF> findBySiglaAndNome(String sigla, String nome);

    Optional<UF> findBySiglaAndNomeAndCodigoUF(String sigla, String nome, Long codigoUF);

    Optional<UF> findByNomeAndCodigoUF(String nome, Long codigoUF);

    Optional<UF> findBySiglaAndCodigoUF(String sigla, Long codigoUF);

    Optional<UF> findBySiglaAndStatus(String sigla, Integer status);

    Optional<UF> findByCodigoUFAndStatus(Long codigoUF, Integer status);

    Optional<UF> findByNomeAndStatus(String nome, Integer status);

    Optional<UF> findBySiglaAndNomeAndStatus(String sigla, String nome, Integer status);

    Optional<UF> findByCodigoUFAndNomeAndStatus(Long codigoUF, String nome, Integer status);

    Optional<UF> findBySiglaAndCodigoUFAndStatus(String sigla, Long codigoUF, Integer status);

    Optional<UF> findBySiglaAndNomeAndCodigoUFAndStatus(String sigla, String nome, Long codigoUF, Integer status);

}

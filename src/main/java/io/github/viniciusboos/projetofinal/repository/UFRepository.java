package io.github.viniciusboos.projetofinal.repository;

import io.github.viniciusboos.projetofinal.modelo.uf.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UFRepository extends JpaRepository<UF, Integer> {

    Optional<UF> findBySigla(String sigla);
    Optional<UF> findByCodigo(Long codigo);
}

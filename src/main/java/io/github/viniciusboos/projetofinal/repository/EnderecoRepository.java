package io.github.viniciusboos.projetofinal.repository;

import io.github.viniciusboos.projetofinal.modelo.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.pessoa.codigoPessoa= :codigoPessoa")
    List<Endereco> findByCodigoPessoa(@Param("codigoPessoa") Long codigoPessoa);

    Optional<Endereco> findByCodigoEndereco(Long codigoEndereco);
}

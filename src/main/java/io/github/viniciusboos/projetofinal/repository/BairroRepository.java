package io.github.viniciusboos.projetofinal.repository;

import io.github.viniciusboos.projetofinal.modelo.bairro.Bairro;
import io.github.viniciusboos.projetofinal.modelo.uf.UF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio")
    List<Bairro> findByCodigoMunicipio(@Param("codigoMunicipio") Long codigoMunicipio);

    List<Bairro> findByStatus(Integer status);

    Optional<Bairro> findByCodigoBairro(Long codigoBairro);

    List<Bairro> findByNome(String nome);

    Optional<Bairro> findByCodigoBairroAndNome(Long codigoBairro, String nome);

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio AND b.nome= :nome")
    List<Bairro> findByNomeAndCodigoMunicipio(@Param("nome") String nome,
                                                  @Param("codigoMunicipio") Long codigoMunicipio);

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio AND b.codigoBairro= :codigoBairro")
    Optional<Bairro> findByCodigoBairroAndCodigoMunicipio(@Param("codigoBairro") Long codigoBairro,
                                                          @Param("codigoMunicipio") Long codigoMunicipio);

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio AND b.nome= :nome" +
            " AND b.codigoBairro= :codigoBairro")
    Optional<Bairro> findByCodigoBairroAndNomeAndCodigoMunicipio(@Param("codigoBairro") Long codigoBairro,
                                                                 @Param("nome") String nome,
                                                                 @Param("codigoMunicipio") Long codigoMunicipio);

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio AND b.nome= :nome" +
            " AND b.codigoBairro= :codigoBairro AND b.status= :status")
    Optional<Bairro> findByCodigoBairroAndNomeAndCodigoMunicipioAndStatus(@Param("codigoBairro") Long codigoBairro,
                                                                          @Param("nome") String nome,
                                                                          @Param("codigoMunicipio") Long codigoMunicipio,
                                                                          @Param("status") Integer status);

    List<Bairro> findByStatusAndNome(Integer status, String nome);

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio AND b.status= :status")
    List<Bairro> findByStatusAndCodigoMunicipio(@Param("status") Integer status, @Param("codigoMunicipio") Long codigoMunicipio);

    Optional<Bairro> findByStatusAndCodigoBairro(Integer status, Long codigoBairro);

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio AND b.status= :status AND b.codigoBairro= :codigoBairro")
    Optional<Bairro> findByCodigoBairroAndCodigoMunicipioAndStatus(@Param("codigoBairro") Long codigoBairro,
                                                                   @Param("codigoMunicipio") Long codigoMunicipio,
                                                                   @Param("status") Integer status);

    Optional<Bairro> findByCodigoBairroAndNomeAndStatus(Long codigoBairro, String nome, Integer status);

    @Query(value = "SELECT b FROM Bairro b WHERE b.municipio.codigoMunicipio= :codigoMunicipio AND b.status= :status AND b.nome= :nome")
    List<Bairro> findByCodigoMunicipioAndNomeAndStatus(@Param("codigoMunicipio") Long codigoMunicipio,
                                                           @Param("nome") String nome,
                                                           @Param("status") Integer status);
}

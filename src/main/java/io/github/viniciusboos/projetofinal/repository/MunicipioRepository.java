package io.github.viniciusboos.projetofinal.repository;

import io.github.viniciusboos.projetofinal.modelo.municipio.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF")
    List<Municipio> findByCodigoUF(@Param("codigoUF") Long codigoUF);

    Optional<Municipio> findByCodigoMunicipio(Long codigo);

    List<Municipio> findByNome(String nome);

    List<Municipio> findByStatus(Integer status);

    Optional<Municipio> findByCodigoMunicipioAndNome(Long codigoMunicipio, String nome);

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF and m.nome= :nome")
    List<Municipio> findByNomeAndCodigoUF(@Param("nome") String nome, @Param("codigoUF") Long codigoUF);

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF and m.codigoMunicipio= :codigoMunicipio")
    Optional<Municipio> findByCodigoMunicipioAndCodigoUF(@Param("codigoMunicipio") Long codigoMunicipio,
                                                         @Param("codigoUF") Long codigoUF);

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF and m.nome= :nome and m.codigoMunicipio= :codigoMunicipio")
    Optional<Municipio> findByCodigoMunicipioAndNomeAndCodigoUF(@Param("codigoMunicipio") Long codigoMunicipio,
                                                                @Param("nome")String nome, @Param("codigoUF") Long codigoUF);

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF and m.nome= :nome and m.codigoMunicipio= :codigoMunicipio and m.status= :status")
    Optional<Municipio> findByCodigoMunicipioAndNomeAndCodigoUFAndStatus(@Param("codigoMunicipio") Long codigoMunicipio,
                                                                         @Param("nome")String nome,
                                                                         @Param("codigoUF") Long codigoUF, @Param("status") Integer status);

    List<Municipio> findByStatusAndNome(Integer status, String nome);

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF AND m.status= :status")
    List<Municipio> findByStatusAndCodigoUF(@Param("status") Integer status, @Param("codigoUF") Long codigoUF);

    Optional<Municipio> findByStatusAndCodigoMunicipio(Integer status, Long codigoMunicipio);

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF AND m.status= :status AND m.nome= :nome")
    List<Municipio> findByStatusAndCodigoUFAndNome(@Param("status") Integer status,
                                                       @Param("codigoUF") Long codigoUF, @Param("nome") String nome);

    Optional<Municipio> findByStatusAndCodigoMunicipioAndNome(Integer status, Long codigoMunicipio, String nome);

    @Query(value = "SELECT m FROM Municipio m WHERE m.uf.codigoUF= :codigoUF AND m.status= :status AND m.codigoMunicipio= :codigoMunicipio")
    Optional<Municipio> findByStatusAndCodigoMunicipioAndCodigoUF(@Param("status") Integer status, @Param("codigoMunicipio") Long codigoMunicipio, @Param("codigoUF") Long codigoUF);
}

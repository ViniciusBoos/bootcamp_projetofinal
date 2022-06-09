package io.github.viniciusboos.projetofinal.modelo.municipio;


import com.fasterxml.jackson.annotation.*;
import io.github.viniciusboos.projetofinal.modelo.uf.UF;

import javax.persistence.*;

@Entity
@Table(name = "TB_MUNICIPIO")
public class Municipio {

    @Id
    @Column(name = "CODIGO_MUNICIPIO", length = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigoMunicipioSequenceGenerator")
    @SequenceGenerator(name = "codigoMunicipioSequenceGenerator", sequenceName = "SEQUENCE_MUNICIPIO",
            allocationSize = 1, initialValue = 1)
    private Long codigoMunicipio;

    @JsonProperty("uf")
    @JoinColumn(name = "CODIGO_UF")
    @ManyToOne
    private UF uf;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}

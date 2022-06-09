package io.github.viniciusboos.projetofinal.modelo.municipio;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.viniciusboos.projetofinal.modelo.uf.UF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MunicipioView {

    private Long codigoMunicipio;
    private Long codigoUF;
    private String nome;
    @JsonProperty("uf")
    private UF uf;
    private Integer status;

    public MunicipioView(){super();}

    public MunicipioView(Municipio municipio) {
        this.codigoMunicipio = municipio.getCodigoMunicipio();
        this.codigoUF = municipio.getUf().getCodigoUF();
        this.nome = municipio.getNome();
        this.status = municipio.getStatus();
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
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

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }
}

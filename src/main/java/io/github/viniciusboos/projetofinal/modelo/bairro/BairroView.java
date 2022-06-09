package io.github.viniciusboos.projetofinal.modelo.bairro;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.viniciusboos.projetofinal.modelo.municipio.MunicipioView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BairroView {

    private Long codigoBairro;
    private Long codigoMunicipio;
    private String nome;
    @JsonProperty("municipio")
    private MunicipioView municipioView;
    private Integer status;

    public BairroView(){super();}

    public BairroView(Bairro bairro) {
        this.codigoBairro = bairro.getCodigoBairro();
        this.codigoMunicipio = bairro.getMunicipio().getCodigoMunicipio();
        this.nome = bairro.getNome();
        this.status = bairro.getStatus();
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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

    public MunicipioView getMunicipioView() {
        return municipioView;
    }

    public void setMunicipioView(MunicipioView municipioView) {
        this.municipioView = municipioView;
    }
}

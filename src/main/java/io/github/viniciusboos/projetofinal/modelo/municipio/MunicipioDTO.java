package io.github.viniciusboos.projetofinal.modelo.municipio;

import io.github.viniciusboos.projetofinal.modelo.uf.UF;

import javax.validation.constraints.*;

public class MunicipioDTO {

    @Max(value = 999999999, message = "CodigoMunicipio não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoMunicipio não pode ser menor que 0")
    private Long codigoMunicipio;

    @NotNull(message = "CodigoUF é necessário")
    @Max(value = 999999999, message = "CodigoUF não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoUF não pode ser menor que 0")
    private Long codigoUF;

    @NotNull(message = "Nome é necessário")
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 256, min = 3, message = "Nome não pode ser maior que 256 dígitos e menor que 3")
    private String nome;

    @NotNull(message = "Status é necessário")
    @Max(value = 2, message = "Status so pode ser 1 Ativo out 2 Desativado")
    @Min(value = 1, message = "Status so pode ser 1 Ativo out 2 Desativado")
    private Integer status;

    public MunicipioDTO(){super();}

    public MunicipioDTO(Municipio municipio) {
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
}

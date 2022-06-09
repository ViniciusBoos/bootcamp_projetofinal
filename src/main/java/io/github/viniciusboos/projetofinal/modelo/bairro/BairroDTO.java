package io.github.viniciusboos.projetofinal.modelo.bairro;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class BairroDTO {

    @Max(value = 999999999, message = "CodigoBairro não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoBairro não pode ser menor que 0")
    private Long codigoBairro;

    @NotNull(message = "CodigoMunicipio é obrigatório")
    @Max(value = 999999999, message = "CodigoMunicipio não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoMunicipio não pode ser menor que 0")
    private Long codigoMunicipio;

    @NotNull(message = "Nome é obrigatório")
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(max = 256, min = 3, message = "Nome não pode ter mais que 256 dígitos e menos que 3 dígitos")
    private String nome;

    @NotNull(message = "Status é obrigatório")
    @Max(value = 2, message = "Status so pode ser 1 Ativo out 2 Desativado")
    @Min(value = 1, message = "Status so pode ser 1 Ativo out 2 Desativado")
    private Integer status;

    public BairroDTO(){super();}

    public BairroDTO(Bairro bairro) {
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
}

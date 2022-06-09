package io.github.viniciusboos.projetofinal.modelo.uf;

import javax.validation.constraints.*;

public class UFDTO {

    private Long codigoUF;

    @NotBlank(message = "Nome é necessário")
    @NotNull(message = "Nome é necessário")
    @Size(max = 60, min = 3, message = "Nome não pode ter mais que 60 dígitos e menos que 3 dígitos")
    private String nome;

    @NotBlank(message = "Sigla é necessário")
    @NotNull(message = "Sigla é necessária")
    @Size(max = 2, min = 2, message = "Sigla deve ter 2 dígitos")
    private String sigla;

    @NotNull(message = "Status é necessário")
    @Max(value = 2, message = "Status so pode ser 1 Ativo out 2 Desativado")
    @Min(value = 1, message = "Status so pode ser 1 Ativo out 2 Desativado")
    private Integer status;

    public UFDTO(String sigla, String nome, Integer status) {
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

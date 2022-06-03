package io.github.viniciusboos.projetofinal.modelo.uf;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UFDTO {

    private Long codigo;

    @NotBlank(message = "Nome é necessário")
    @NotNull(message = "Nome é necessário")
    private String nome;

    @NotBlank(message = "Sigla é necessário")
    @NotNull(message = "Sigla é necessária")
    private String sigla;

    @NotNull(message = "Status é necessário")
    private Integer status;

    public UFDTO(String sigla, String nome, Integer status) {
        this.sigla = sigla;
        this.nome = nome;
        this.status = status;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

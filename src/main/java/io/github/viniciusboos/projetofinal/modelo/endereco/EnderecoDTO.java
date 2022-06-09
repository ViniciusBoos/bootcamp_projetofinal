package io.github.viniciusboos.projetofinal.modelo.endereco;

import javax.validation.constraints.*;

public class EnderecoDTO {

    @Max(value = 999999999, message = "CodigoEndereco não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoEndereco não pode ser menor que 0")
    private Long codigoEndereco;

    @NotNull(message = "CodigoBairro é necessario")
    @Max(value = 999999999, message = "CodigoBairro não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoBairro não pode ser menor que 0")
    private Long codigoBairro;

    @Max(value = 999999999, message = "CodigoPessoa não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoPessoa não pode ser menor que 0")
    private Long codigoPessoa;

    @NotNull(message = "NomeRua é necessário")
    @NotBlank(message = "NomeRua não pode ser vazio")
    @Size(max = 256, min = 0, message = "NomeRua não pode ter mais que 256 dígitos e menos que 0 dígitos")
    private String nomeRua;

    @NotNull(message = "Numero é necessário")
    @NotBlank(message = "Numero não pode ser vazio")
    @Size(max = 10, min = 0, message = "Numero não pode ter mais que 10 dígitos")
    private String numero;

    @NotNull(message = "Complemento é necessário")
    @NotBlank(message = "Complemento não pode ser vazio")
    @Size(max = 20, min = 0, message = "Complemento não pode ter mais que 20 dígitos")
    private String complemento;

    @NotNull(message = "CEP é necessário")
    @NotBlank(message = "CEP não pode ser vazia")
    @Size(max = 10, min = 10, message = "CEP não pode ter mais nem menos que 10 dígitos")
    private String cep;

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Long codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
    }

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}

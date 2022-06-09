package io.github.viniciusboos.projetofinal.modelo.endereco;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.viniciusboos.projetofinal.modelo.bairro.Bairro;
import io.github.viniciusboos.projetofinal.modelo.bairro.BairroView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoView {

    private Long codigoEndereco;
    private Long codigoBairro;
    private Long codigoPessoa;
    private String nomeRua;
    private String numero;
    private String complemento;
    private String cep;
    @JsonProperty("bairro")
    private BairroView bairroView;

    public EnderecoView(Endereco endereco) {
        this.codigoEndereco = endereco.getCodigoEndereco();
        this.codigoBairro = endereco.getBairro().getCodigoBairro();
        this.codigoPessoa = endereco.getPessoa().getCodigoPessoa();
        this.nomeRua = endereco.getNomeRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.cep = endereco.getCep();
    }

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

    public BairroView getBairroView() {
        return bairroView;
    }

    public void setBairroView(BairroView bairroView) {
        this.bairroView = bairroView;
    }
}

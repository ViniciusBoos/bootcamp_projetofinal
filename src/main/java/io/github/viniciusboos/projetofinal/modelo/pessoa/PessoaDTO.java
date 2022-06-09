package io.github.viniciusboos.projetofinal.modelo.pessoa;

import io.github.viniciusboos.projetofinal.modelo.endereco.EnderecoDTO;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;
import java.util.List;

public class PessoaDTO {

    @Max(value = 999999999, message = "CodigoPessoa não pode ter mais que 9 dígitos")
    @Min(value = 0, message = "CodigoPessoa não pode ser menor que 0")
    @NumberFormat()
    private Long codigoPessoa;

    @NotNull(message = "Nome é necessário")
    @NotBlank(message = "Nome é necessário")
    @Size(max = 256, min = 3, message = "Nome não pode ter mais que 256 dígitos e menos que 3 dígitos")
    private String nome;

    @NotNull(message = "Sobrenome é necessário")
    @NotBlank(message = "Sobrenome é necessário")
    @Size(max = 256, min = 3, message = "Sobrenome não pode ter mais que 256 dígitos e menos que 3 dígitos")
    private String sobrenome;

    @NotNull(message = "Idade é necessário")
    @Max(value = 999, message = "Idade não pode ter mais que 3 dígitos")
    @Min(value = 0, message = "Idade não pode ser menor que 0")
    private Integer idade;

    @NotNull(message = "Login é necessário")
    @NotBlank(message = "Login é necessário")
    @Size(max = 50, min = 3, message = "Login não pode ter mais que 50 dígitos e menos que 3 dígitos")
    private String login;

    @Size(max = 20, min = 0, message = "Complemento não pode ter mais que 20 dígitos")
    private String complemento;

    @NotNull(message = "Senha é necessário")
    @NotBlank(message = "Senha é necessário")
    @Size(max = 50, min = 3, message = "Senha não pode ter mais que 50 dígitos e menos que 3 dígitos")
    private String senha;

    @NotNull(message = "status é necessário")
    @Max(value = 2, message = "Status so pode ser 1 Ativo out 2 Desativado")
    @Min(value = 1, message = "Status so pode ser 1 Ativo out 2 Desativado")
    private Integer status;

    private List<EnderecoDTO> enderecos;

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }
}

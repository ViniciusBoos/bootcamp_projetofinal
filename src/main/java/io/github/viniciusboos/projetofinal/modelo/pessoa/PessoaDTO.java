package io.github.viniciusboos.projetofinal.modelo.pessoa;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PessoaDTO {

    @NotNull(message = "Nome é necessário")
    @NotBlank(message = "Nome é necessário")
    private String nome;
    @NotNull(message = "sobrenome é necessário")
    @NotBlank(message = "sobrenome é necessário")
    private String sobreNome;
    @NotNull(message = "idade é necessário")
    @NotBlank(message = "idade é necessário")
    private String idade;
    @NotNull(message = "login é necessário")
    @NotBlank(message = "login é necessário")
    private String login;
    @NotNull(message = "senha é necessário")
    @NotBlank(message = "senha é necessário")
    private String senha;
    @NotNull(message = "status é necessário")
    @NotBlank(message = "status é necessário")
    private String status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

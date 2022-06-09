package io.github.viniciusboos.projetofinal.modelo.pessoa;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.viniciusboos.projetofinal.modelo.endereco.Endereco;
import io.github.viniciusboos.projetofinal.modelo.endereco.EnderecoView;

import java.util.List;

public class PessoaView {

    private Long codigoPessoa;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private String login;
    private String senha;
    private Integer status;
    @JsonProperty("enderecos")
    private List<EnderecoView> enderecoViews;

    public PessoaView(){ super();}
    public PessoaView(Pessoa pessoa) {
        this.codigoPessoa = pessoa.getCodigoPessoa();
        this.nome = pessoa.getNome();
        this.sobrenome = pessoa.getSobrenome();
        this.idade = pessoa.getIdade();
        this.login = pessoa.getLogin();
        this.senha = pessoa.getSenha();
        this.status = pessoa.getStatus();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public List<EnderecoView> getEnderecoViews() {
        return enderecoViews;
    }

    public void setEnderecoViews(List<EnderecoView> enderecoViews) {
        this.enderecoViews = enderecoViews;
    }
}

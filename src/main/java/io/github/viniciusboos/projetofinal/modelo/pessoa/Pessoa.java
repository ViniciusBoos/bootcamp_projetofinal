package io.github.viniciusboos.projetofinal.modelo.pessoa;

import javax.persistence.*;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa {

    @Id
    @Column(name = "CODIGO_PESSOA", length = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigoPessoaSequenceGenerator")
    @SequenceGenerator(name = "codigoPessoaSequenceGenerator", sequenceName = "SEQUENCE_PESSOA",
            allocationSize = 1, initialValue = 1)
    private Long codigoPessoa;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SOBRENOME")
    private String sobrenome;

    @Column(name = "IDADE", length = 3)
    private Integer idade;

    @Column(name = "LOGIN", length = 50)
    private String login;

    @Column(name = "SENHA", length = 50)
    private String senha;

    @Column(name = "STATUS", length = 3)
    private Integer status;

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

}

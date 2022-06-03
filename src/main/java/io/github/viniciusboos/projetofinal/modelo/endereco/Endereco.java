package io.github.viniciusboos.projetofinal.modelo.endereco;

import io.github.viniciusboos.projetofinal.modelo.bairro.Bairro;
import io.github.viniciusboos.projetofinal.modelo.pessoa.Pessoa;

import javax.persistence.*;

@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @Column(name = "CODIGO_ENDERECO", length = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigoEnderecoSequenceGenerator")
    @SequenceGenerator(name = "codigoEnderecoSequenceGenerator", sequenceName = "SEQUENCE_ENDERECO",
            allocationSize = 1, initialValue = 1)
    private Long codigo;

    @JoinColumn(name = "CODIGO_PESSOA")
    @ManyToOne
    private Pessoa pessoa;

    @JoinColumn(name = "CODIGO_BAIRRO")
    @ManyToOne
    private Bairro bairro;

    @Column(name = "NOME_RUA")
    private String rua;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP")
    private String cep;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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

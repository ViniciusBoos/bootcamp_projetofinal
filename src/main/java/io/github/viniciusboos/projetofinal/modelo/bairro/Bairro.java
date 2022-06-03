package io.github.viniciusboos.projetofinal.modelo.bairro;

import io.github.viniciusboos.projetofinal.modelo.endereco.Endereco;
import io.github.viniciusboos.projetofinal.modelo.municipio.Municipio;
import io.github.viniciusboos.projetofinal.modelo.pessoa.Pessoa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_BAIRRO")
public class Bairro {

    @Id
    @Column(name = "CODIGO_BAIRRO", length = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigoBairroSequenceGenerator")
    @SequenceGenerator(name = "codigoBairroSequenceGenerator", sequenceName = "SEQUENCE_BAIRRO",
            allocationSize = 1, initialValue = 1)
    private Long codigo;

    @JoinColumn(name = "CODIGO_MUNICIPIO")
    @ManyToOne
    private Municipio municipio;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
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

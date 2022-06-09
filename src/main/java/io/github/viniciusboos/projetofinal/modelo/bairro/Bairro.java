package io.github.viniciusboos.projetofinal.modelo.bairro;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.github.viniciusboos.projetofinal.modelo.municipio.Municipio;

import javax.persistence.*;

@Entity
@Table(name = "TB_BAIRRO")
public class Bairro {

    @Id
    @Column(name = "CODIGO_BAIRRO", length = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigoBairroSequenceGenerator")
    @SequenceGenerator(name = "codigoBairroSequenceGenerator", sequenceName = "SEQUENCE_BAIRRO",
            allocationSize = 1, initialValue = 1)
    private Long codigoBairro;

    @JsonProperty("municipio")
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    @ManyToOne
    private Municipio municipio;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "STATUS")
    private Integer status;

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
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

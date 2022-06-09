package io.github.viniciusboos.projetofinal.modelo.uf;

import javax.persistence.*;

@Entity
@Table(name = "TB_UF")
public class UF {

    @Id
    @Column(name = "CODIGO_UF", length = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codigoUFSequenceGenerator")
    @SequenceGenerator(name = "codigoUFSequenceGenerator", sequenceName = "SEQUENCE_UF",
            allocationSize = 1, initialValue = 1)
    private Long codigoUF;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SIGLA")
    private String sigla;

    @Column(name = "STATUS")
    private Integer status;

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
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

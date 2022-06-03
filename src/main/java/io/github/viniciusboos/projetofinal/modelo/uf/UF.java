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
    private Long codigo;

    @Column(name = "SIGLA")
    private String sigla;

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

    @Override
    public String toString() {
        return "UF{" +
                "codigo=" + codigo +
                ", sigla='" + sigla + '\'' +
                ", nome='" + nome + '\'' +
                ", status=" + status +
                '}';
    }
}

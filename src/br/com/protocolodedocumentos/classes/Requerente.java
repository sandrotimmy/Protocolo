package br.com.protocolodedocumentos.classes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Requerente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "s_requerente")
    @GenericGenerator(name = "s_requerente", strategy = "increment")
//    @SequenceGenerator(name = "s_requerente", sequenceName = "seq_requerente",allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_requerente")
    private Long id;
    @Column(length = 11)
    private long CPF;
    @Column(length = 50)
    private String nome;
    @Column(length = 20)
    private String profissao;

    public Requerente() {
    }

    public Requerente(long CPF, String nome, String profissao) {
        this.CPF = CPF;
        this.nome = nome;
        this.profissao = profissao;
    }

    public Long getId() {
        return id;
    }

    public long getCPF() {
        return CPF;
    }

    public String getNome() {
        return nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Requerente other = (Requerente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.CPF != other.CPF) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.profissao, other.profissao);
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}

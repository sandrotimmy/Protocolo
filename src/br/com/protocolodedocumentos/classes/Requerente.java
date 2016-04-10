package br.com.protocolodedocumentos.classes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class Requerente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "s_requerente")
    @GenericGenerator(name = "s_requerente", strategy = "increment")
    @XmlElement(name="id")
    private Long id;
    @Column(length = 19)
    @XmlElement(name="CPF")
    private long CPF;
    @Column(length = 50)
    @XmlElement(name="nome")
    private String nome;
    @Column(length = 20)
    @XmlElement(name="profissao")
    private String profissao;
    @OneToOne(mappedBy = "requerente")
    Protocolo protocolo;

    public Requerente() {
    }

    public Requerente(long CPF, String nome, String profissao, Protocolo protocolo) {
        this.CPF = CPF;
        this.nome = nome;
        this.profissao = profissao;
        this.protocolo = protocolo;
    }

    public Requerente(Long id, long CPF, String nome, String profissao) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
        this.profissao = profissao;
    }

    public Protocolo getProtocolo() {
        return protocolo;
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

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
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

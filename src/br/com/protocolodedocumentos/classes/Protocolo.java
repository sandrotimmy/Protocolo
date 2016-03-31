package br.com.protocolodedocumentos.classes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Protocolo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_protocolo", sequenceName = "s_protocolo")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_protocolo")
    private Long id;
    @Column(length = 50, nullable = false)
    private String nome;
    @OneToOne
    private Protocolo protocolo;
    @OneToOne
    private Requerente requerente;
    @OneToMany(mappedBy = "protocolo")
    private List<Documentos> documentos;

    public Protocolo() {
    }

    public Protocolo(String nome, List<Documentos> documentos) {
        this.nome = nome;
        this.documentos = documentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Protocolo other = (Protocolo) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + this.id + " nome: " + this.nome;
    }
}

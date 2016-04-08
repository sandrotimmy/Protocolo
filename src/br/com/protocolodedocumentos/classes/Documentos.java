package br.com.protocolodedocumentos.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import org.hibernate.annotations.GenericGenerator;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
public class Documentos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "s_documentos")
    @GenericGenerator(name = "s_documentos", strategy = "increment")
    @XmlElement(name = "id")
    private Long id;
    @Column(length = 50, nullable = false)
    @XmlElement(name = "tipo")
    private String tipo;
    @Column(length = 100, nullable = false)
    @XmlElement(name = "documento")
    private String documento;
    @Column(length = 100, nullable = false)
    @XmlElement(name = "complemento")
    private String complemento;
    @ManyToOne(optional = false)
    @JoinColumn(name = "protocolo", foreignKey = @ForeignKey(name = "fk_documentos_protocolo"))
    private Protocolo protocolo;

    public Documentos() {
    }

    public Documentos(Protocolo protocolo, String tipo, String documento, String complemento) {
        this.protocolo = protocolo;
        this.tipo = tipo;
        this.documento = documento;
        this.complemento = complemento;
    }

    public Documentos(Long id, String tipo, String documento, String complemento, Protocolo protocolo) {
        this.id = id;
        this.tipo = tipo;
        this.documento = documento;
        this.complemento = complemento;
        this.protocolo = protocolo;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public String getDocumento() {
        return documento;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
        Documentos other = (Documentos) obj;
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
        return "\nCODIGO: " + id + "\nTIPO: " + tipo + "\nDOCUMENTO: " + documento + "\nCOMPLEMENTO: " + complemento; //To change body of generated methods, choose Tools | Templates.
    }

}

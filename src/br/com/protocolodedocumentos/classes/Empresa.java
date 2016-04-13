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
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "s_empresa")
    @GenericGenerator(name = "s_empresa", strategy = "increment")
    @XmlElement(name = "id")
    private Long id;
    @Column(nullable = false)
    @XmlElement(name = "CNPJ")
    private long CNPJ;
    @Column(length = 50, nullable = false)
    @XmlElement(name = "razaoSocial")
    private String razaoSocial;
    @Column(length = 20)
    @XmlElement(name = "segmento")
    private String segmento;
    @OneToOne(mappedBy = "empresa")
    Protocolo protocolo;

    public Empresa() {
    }

    public Empresa(long CNPJ, String RazaoSocial, String segmento) {
        this.CNPJ = CNPJ;
        this.razaoSocial = RazaoSocial;
        this.segmento = segmento;
    }

    public Empresa(long CNPJ, String RazaoSocial, String segmento, Protocolo protocolo) {
        this.CNPJ = CNPJ;
        this.razaoSocial = RazaoSocial;
        this.segmento = segmento;
        this.protocolo = protocolo;
    }

    public Long getId() {
        return id;
    }

    public long getCNPJ() {
        return CNPJ;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getSegmento() {
        return segmento;
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCNPJ(long CNPJ) {
        this.CNPJ = CNPJ;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.razaoSocial = RazaoSocial;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.CNPJ != other.CNPJ) {
            return false;
        }
        if (!Objects.equals(this.razaoSocial, other.razaoSocial)) {
            return false;
        }
        return Objects.equals(this.segmento, other.segmento);
    }

    @Override
    public String toString() {
        return "\nEMPRESA:\nID: " + id + "\nCNPJ: " + CNPJ + "\nRazão Social: " + razaoSocial + "\nSegmento: " + segmento;
    }
}

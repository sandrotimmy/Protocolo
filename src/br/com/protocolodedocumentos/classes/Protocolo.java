package br.com.protocolodedocumentos.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Protocolo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "s_protocolo")
    @GenericGenerator(name = "s_protocolo", strategy = "increment")
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dataProtocolo;
    @Column(length = 3, nullable = false)
    String retorno;
    @Column(length = 150)
    private String observacoes;
    @OneToOne
    @JoinColumn(name = "requerente", foreignKey = @ForeignKey(name = "fk_requerente_protocolo"))
    private Requerente requerente;
    @OneToOne
    @JoinColumn(name = "empresa", foreignKey = @ForeignKey(name = "fk_empresa_protocolo"))
    private Empresa empresa;
    @OneToMany(mappedBy = "protocolo")
    private List<Documentos> documentos;

    public Protocolo() {
    }

    public Protocolo(Date dataProtocolo, String retorno, String observacoes) {
        this.dataProtocolo = dataProtocolo;
        this.retorno = retorno;
        this.observacoes = observacoes;
    }

    public Protocolo(Long id, Date dataProtocolo, String retorno, String observacoes, Requerente requerente, Empresa empresa, List<Documentos> documentos) {
        this.id = id;
        this.dataProtocolo = dataProtocolo;
        this.retorno = retorno;
        this.observacoes = observacoes;
        this.requerente = requerente;
        this.empresa = empresa;
        this.documentos = documentos;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setRequerente(Requerente requerente) {
        this.requerente = requerente;
    }

    public void setDocumentos(List<Documentos> documentos) {
        this.documentos = documentos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setDataProtocolo(Date dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }

    public String getRetorno() {
        return retorno;
    }

    public Requerente getRequerente() {
        return requerente;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public List<Documentos> getDocumentos() {
        return documentos;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Date getDataProtocolo() {
        return dataProtocolo;
    }

    public Long getId() {
        return id;
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
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}

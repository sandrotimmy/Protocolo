
package br.com.protocolodedocumentos.classes;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Empresa implements Serializable {
        private static final long serialVersionUID = 1L;
    	@Id
            @GeneratedValue(generator = "s_empresa")
    @GenericGenerator(name = "s_empresa", strategy = "increment")
//	@GeneratedValue(strategy = SEQUENCE, generator = "s_empresa")
//	@SequenceGenerator(name="s_empresa", sequenceName="s_empresa")
	private Long id;
        @Column(length=14)
        private long CNPJ;
        @Column(length=50)
        private String RazaoSocial;
        @Column(length=20)
        private String segmento;

    public Empresa() {
    }

    public Empresa(long CNPJ, String RazaoSocial, String segmento) {
        this.CNPJ = CNPJ;
        this.RazaoSocial = RazaoSocial;
        this.segmento = segmento;
    }

    public Long getId() {
        return id;
    }

    public long getCNPJ() {
        return CNPJ;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCNPJ(long CNPJ) {
        this.CNPJ = CNPJ;
    }

    public void setRazaoSocial(String RazaoSocial) {
        this.RazaoSocial = RazaoSocial;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
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
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.CNPJ != other.CNPJ) {
            return false;
        }
        if (!Objects.equals(this.RazaoSocial, other.RazaoSocial)) {
            return false;
        }
        return Objects.equals(this.segmento, other.segmento);
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "PGH_CONTADOR_VISITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghContadorVisita.findAll", query = "SELECT p FROM PghContadorVisita p"),
    @NamedQuery(name = "PghContadorVisita.findByIdCntVisita", query = "SELECT p FROM PghContadorVisita p WHERE p.idCntVisita = :idCntVisita"),
    @NamedQuery(name = "PghContadorVisita.findByCntVisitas", query = "SELECT p FROM PghContadorVisita p WHERE p.cntVisitas = :cntVisitas"),
    @NamedQuery(name = "PghContadorVisita.findByTipoConsulta", query = "SELECT p FROM PghContadorVisita p WHERE p.tipoConsulta = :tipoConsulta"),
    @NamedQuery(name = "PghContadorVisita.findByUsuarioCreacion", query = "SELECT p FROM PghContadorVisita p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghContadorVisita.findByUsuarioActualizacion", query = "SELECT p FROM PghContadorVisita p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghContadorVisita.findByFechaCreacion", query = "SELECT p FROM PghContadorVisita p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghContadorVisita.findByFechaActualizacion", query = "SELECT p FROM PghContadorVisita p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghContadorVisita.findByTerminalCreacion", query = "SELECT p FROM PghContadorVisita p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghContadorVisita.findByTerminalActualizacion", query = "SELECT p FROM PghContadorVisita p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghContadorVisita.findByEstado", query = "SELECT p FROM PghContadorVisita p WHERE p.estado = :estado")})
public class PghContadorVisita extends Auditoria {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "PGH_CONTADOR_VISITA")
    @SequenceGenerator(name = "PGH_CONTADOR_VISITA", sequenceName = "PGH_CONTADOR_VISITA_SEQ", allocationSize = 1)
    @Column(name = "ID_CNT_VISITA")
    private Long idCntVisita;
    @Column(name = "CNT_VISITAS")
    private Long cntVisitas;
    @Column(name = "TIPO_CONSULTA")
    private Long tipoConsulta;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private String estado;

    public PghContadorVisita() {
    }

    public PghContadorVisita(Long idCntVisita) {
        this.idCntVisita = idCntVisita;
    }

    public PghContadorVisita(Long idCntVisita, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String estado) {
        this.idCntVisita = idCntVisita;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdCntVisita() {
        return idCntVisita;
    }

    public void setIdCntVisita(Long idCntVisita) {
        this.idCntVisita = idCntVisita;
    }

    public Long getCntVisitas() {
        return cntVisitas;
    }

    public void setCntVisitas(Long cntVisitas) {
        this.cntVisitas = cntVisitas;
    }

    public Long getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(Long tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCntVisita != null ? idCntVisita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghContadorVisita)) {
            return false;
        }
        PghContadorVisita other = (PghContadorVisita) object;
        if ((this.idCntVisita == null && other.idCntVisita != null) || (this.idCntVisita != null && !this.idCntVisita.equals(other.idCntVisita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghContadorVisita[ idCntVisita=" + idCntVisita + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_REGISTRO_SUSPEN_HABILI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiRegistroSuspenHabili.findAll", query = "SELECT m FROM MdiRegistroSuspenHabili m"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByIdRegistroSuspenHabil", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.idRegistroSuspenHabil = :idRegistroSuspenHabil"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByFechaInicioSuspension", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.fechaInicioSuspension = :fechaInicioSuspension"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByFechaFinSuspension", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.fechaFinSuspension = :fechaFinSuspension"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByObservacion", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.observacion = :observacion"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByProceso", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.proceso = :proceso"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByUsuarioActualizacion", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByUsuarioCreacion", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByFechaCreacion", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByFechaActualizacion", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByTerminalActualizacion", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByTerminalCreacion", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiRegistroSuspenHabili.findByEstado", query = "SELECT m FROM MdiRegistroSuspenHabili m WHERE m.estado = :estado")})
public class MdiRegistroSuspenHabili extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO_SUSPEN_HABIL")
    private Long idRegistroSuspenHabil;
    @Column(name = "FECHA_INICIO_SUSPENSION")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioSuspension;
    @Column(name = "FECHA_FIN_SUSPENSION")
    @Temporal(TemporalType.DATE)
    private Date fechaFinSuspension;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "PROCESO")
    private String proceso;    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @JoinColumn(name = "ID_REGISTRO_HIDROCARBURO", referencedColumnName = "ID_REGISTRO_HIDROCARBURO")
    @ManyToOne
    private MdiRegistroHidrocarburo idRegistroHidrocarburo;

    public MdiRegistroSuspenHabili() {
    }

    public MdiRegistroSuspenHabili(Long idRegistroSuspenHabil) {
        this.idRegistroSuspenHabil = idRegistroSuspenHabil;
    }

    public MdiRegistroSuspenHabili(Long idRegistroSuspenHabil, String proceso, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, char estado) {
        this.idRegistroSuspenHabil = idRegistroSuspenHabil;
        this.proceso = proceso;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdRegistroSuspenHabil() {
        return idRegistroSuspenHabil;
    }

    public void setIdRegistroSuspenHabil(Long idRegistroSuspenHabil) {
        this.idRegistroSuspenHabil = idRegistroSuspenHabil;
    }

    public Date getFechaInicioSuspension() {
        return fechaInicioSuspension;
    }

    public void setFechaInicioSuspension(Date fechaInicioSuspension) {
        this.fechaInicioSuspension = fechaInicioSuspension;
    }

    public Date getFechaFinSuspension() {
        return fechaFinSuspension;
    }

    public void setFechaFinSuspension(Date fechaFinSuspension) {
        this.fechaFinSuspension = fechaFinSuspension;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public MdiRegistroHidrocarburo getIdRegistroHidrocarburo() {
        return idRegistroHidrocarburo;
    }

    public void setIdRegistroHidrocarburo(MdiRegistroHidrocarburo idRegistroHidrocarburo) {
        this.idRegistroHidrocarburo = idRegistroHidrocarburo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroSuspenHabil != null ? idRegistroSuspenHabil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiRegistroSuspenHabili)) {
            return false;
        }
        MdiRegistroSuspenHabili other = (MdiRegistroSuspenHabili) object;
        if ((this.idRegistroSuspenHabil == null && other.idRegistroSuspenHabil != null) || (this.idRegistroSuspenHabil != null && !this.idRegistroSuspenHabil.equals(other.idRegistroSuspenHabil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiRegistroSuspenHabili[ idRegistroSuspenHabil=" + idRegistroSuspenHabil + " ]";
    }
    
}

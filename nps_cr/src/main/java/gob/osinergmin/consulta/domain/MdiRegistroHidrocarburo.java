/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_REGISTRO_HIDROCARBURO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiRegistroHidrocarburo.findAll", query = "SELECT m FROM MdiRegistroHidrocarburo m"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByIdRegistroHidrocarburo", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.idRegistroHidrocarburo = :idRegistroHidrocarburo"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByNumeroRegistroHidrocarburo", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.numeroRegistroHidrocarburo = :numeroRegistroHidrocarburo"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByFechaEmision", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByUsuarioActualizacion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByUsuarioCreacion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByFechaCreacion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByFechaActualizacion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByFechaAprobacion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByTerminalActualizacion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByTerminalCreacion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByFechaInicioSuspencion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.fechaInicioSuspencion = :fechaInicioSuspencion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByFechaFinSuspencion", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.fechaFinSuspencion = :fechaFinSuspencion"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByEstadoProceso", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "MdiRegistroHidrocarburo.findByEstado", query = "SELECT m FROM MdiRegistroHidrocarburo m WHERE m.estado = :estado")})
public class MdiRegistroHidrocarburo extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_REGISTRO_HIDROCARBURO")
    private Long idRegistroHidrocarburo;
    @Basic(optional = false)
    @Column(name = "NUMERO_REGISTRO_HIDROCARBURO")
    private String numeroRegistroHidrocarburo;
    @Column(name = "FECHA_EMISION")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;    
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;    
    @Column(name = "FECHA_INICIO_SUSPENCION")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioSuspencion;
    @Column(name = "FECHA_FIN_SUSPENCION")
    @Temporal(TemporalType.DATE)
    private Date fechaFinSuspencion;
    @Column(name = "ESTADO_PROCESO")
    private Long estadoProceso;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(mappedBy = "idRegistroHidrocarburo")
    private List<MdiRegistroSuspenHabili> mdiRegistroSuspenHabiliList;
    @JoinColumn(name = "ID_UNIDAD_SUPERVISADA", referencedColumnName = "ID_UNIDAD_SUPERVISADA")
    @ManyToOne(optional = false)
    private MdiUnidadSupervisada idUnidadSupervisada;

    public MdiRegistroHidrocarburo() {
    }

    public MdiRegistroHidrocarburo(Long idRegistroHidrocarburo) {
        this.idRegistroHidrocarburo = idRegistroHidrocarburo;
    }

    public MdiRegistroHidrocarburo(Long idRegistroHidrocarburo, String numeroRegistroHidrocarburo, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, char estado) {
        this.idRegistroHidrocarburo = idRegistroHidrocarburo;
        this.numeroRegistroHidrocarburo = numeroRegistroHidrocarburo;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdRegistroHidrocarburo() {
        return idRegistroHidrocarburo;
    }

    public void setIdRegistroHidrocarburo(Long idRegistroHidrocarburo) {
        this.idRegistroHidrocarburo = idRegistroHidrocarburo;
    }

    public String getNumeroRegistroHidrocarburo() {
        return numeroRegistroHidrocarburo;
    }

    public void setNumeroRegistroHidrocarburo(String numeroRegistroHidrocarburo) {
        this.numeroRegistroHidrocarburo = numeroRegistroHidrocarburo;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaInicioSuspencion() {
        return fechaInicioSuspencion;
    }

    public void setFechaInicioSuspencion(Date fechaInicioSuspencion) {
        this.fechaInicioSuspencion = fechaInicioSuspencion;
    }

    public Date getFechaFinSuspencion() {
        return fechaFinSuspencion;
    }

    public void setFechaFinSuspencion(Date fechaFinSuspencion) {
        this.fechaFinSuspencion = fechaFinSuspencion;
    }

    public Long getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(Long estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiRegistroSuspenHabili> getMdiRegistroSuspenHabiliList() {
        return mdiRegistroSuspenHabiliList;
    }

    public void setMdiRegistroSuspenHabiliList(List<MdiRegistroSuspenHabili> mdiRegistroSuspenHabiliList) {
        this.mdiRegistroSuspenHabiliList = mdiRegistroSuspenHabiliList;
    }

    public MdiUnidadSupervisada getIdUnidadSupervisada() {
        return idUnidadSupervisada;
    }

    public void setIdUnidadSupervisada(MdiUnidadSupervisada idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroHidrocarburo != null ? idRegistroHidrocarburo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiRegistroHidrocarburo)) {
            return false;
        }
        MdiRegistroHidrocarburo other = (MdiRegistroHidrocarburo) object;
        if ((this.idRegistroHidrocarburo == null && other.idRegistroHidrocarburo != null) || (this.idRegistroHidrocarburo != null && !this.idRegistroHidrocarburo.equals(other.idRegistroHidrocarburo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiRegistroHidrocarburo[ idRegistroHidrocarburo=" + idRegistroHidrocarburo + " ]";
    }
    
}

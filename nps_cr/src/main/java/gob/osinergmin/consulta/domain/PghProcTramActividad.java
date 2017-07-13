/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_PROC_TRAM_ACTIVIDAD")
@NamedQueries({
    @NamedQuery(name = "PghProcTramActividad.findAll", query = "SELECT p FROM PghProcTramActividad p")
})
public class PghProcTramActividad extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROC_TRAM_ACTI")
    private Long idProcTramActi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "FECHA_ELIMINACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;
    @JoinColumn(name = "ID_PROCEDIMIENTO_TRAMITE", referencedColumnName = "ID_PROCEDIMIENTO_TRAMITE")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghProcedimientoTramite idProcedimientoTramite;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ManyToOne(fetch = FetchType.LAZY)
    private MdiActividad idActividad;

    public PghProcTramActividad() {
    }

    public PghProcTramActividad(Long idProcTramActi) {
        this.idProcTramActi = idProcTramActi;
    }

    public PghProcTramActividad(Long idProcTramActi, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idProcTramActi = idProcTramActi;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdProcTramActi() {
        return idProcTramActi;
    }

    public void setIdProcTramActi(Long idProcTramActi) {
        this.idProcTramActi = idProcTramActi;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public PghProcedimientoTramite getIdProcedimientoTramite() {
        return idProcedimientoTramite;
    }

    public void setIdProcedimientoTramite(PghProcedimientoTramite idProcedimientoTramite) {
        this.idProcedimientoTramite = idProcedimientoTramite;
    }

    public MdiActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(MdiActividad idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcTramActi != null ? idProcTramActi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghProcTramActividad)) {
            return false;
        }
        PghProcTramActividad other = (PghProcTramActividad) object;
        if ((this.idProcTramActi == null && other.idProcTramActi != null) || (this.idProcTramActi != null && !this.idProcTramActi.equals(other.idProcTramActi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghProcTramActividad[ idProcTramActi=" + idProcTramActi + " ]";
    }
}

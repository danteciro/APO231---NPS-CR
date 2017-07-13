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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_PROCEDIMIENTO_TRAMITE")
@NamedQueries({
    @NamedQuery(name = "PghProcedimientoTramite.findAll", query = "SELECT p FROM PghProcedimientoTramite p")
})
public class PghProcedimientoTramite extends Auditoria{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROCEDIMIENTO_TRAMITE")
    private Long idProcedimientoTramite;
    @Column(name = "FECHA_ELIMINACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminacion;
    
    @JoinColumn(name = "ID_TRAMITE", referencedColumnName = "ID_TRAMITE")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PghTramite idTramite;
    @JoinColumn(name = "ID_PROCEDIMIENTO", referencedColumnName = "ID_PROCEDIMIENTO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PghProcedimiento idProcedimiento;
    @OneToMany(mappedBy = "idProcedimientoTramite", fetch = FetchType.LAZY)
    private List<PghProcTramActividad> pghProcTramActividadList;

    public PghProcedimientoTramite() {
    }

    public PghProcedimientoTramite(Long idProcedimientoTramite) {
        this.idProcedimientoTramite = idProcedimientoTramite;
    }

    public PghProcedimientoTramite(Long idProcedimientoTramite, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String estado) {
        this.idProcedimientoTramite = idProcedimientoTramite;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdProcedimientoTramite() {
        return idProcedimientoTramite;
    }

    public void setIdProcedimientoTramite(Long idProcedimientoTramite) {
        this.idProcedimientoTramite = idProcedimientoTramite;
    }

    public Date getFechaEliminacion() {
        return fechaEliminacion;
    }

    public void setFechaEliminacion(Date fechaEliminacion) {
        this.fechaEliminacion = fechaEliminacion;
    }

    public PghTramite getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(PghTramite idTramite) {
        this.idTramite = idTramite;
    }

    public PghProcedimiento getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(PghProcedimiento idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public List<PghProcTramActividad> getPghProcTramActividadList() {
        return pghProcTramActividadList;
    }

    public void setPghProcTramActividadList(List<PghProcTramActividad> pghProcTramActividadList) {
        this.pghProcTramActividadList = pghProcTramActividadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimientoTramite != null ? idProcedimientoTramite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghProcedimientoTramite)) {
            return false;
        }
        PghProcedimientoTramite other = (PghProcedimientoTramite) object;
        if ((this.idProcedimientoTramite == null && other.idProcedimientoTramite != null) || (this.idProcedimientoTramite != null && !this.idProcedimientoTramite.equals(other.idProcedimientoTramite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghProcedimientoTramite[ idProcedimientoTramite=" + idProcedimientoTramite + " ]";
    }   
}

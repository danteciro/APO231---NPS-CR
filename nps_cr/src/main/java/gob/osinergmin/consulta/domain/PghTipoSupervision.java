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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_TIPO_SUPERVISION")
@NamedQueries({
    @NamedQuery(name = "PghTipoSupervision.findAll", query = "SELECT p FROM PghTipoSupervision p"),
    @NamedQuery(name = "PghTipoSupervision.findByIdTipoSupervision", query = "SELECT p FROM PghTipoSupervision p WHERE p.idTipoSupervision = :idTipoSupervision"),
    @NamedQuery(name = "PghTipoSupervision.findByEstado", query = "SELECT p FROM PghTipoSupervision p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghTipoSupervision.findByDescripcion", query = "SELECT p FROM PghTipoSupervision p WHERE p.descripcion = :descripcion")})
public class PghTipoSupervision extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TIPO_SUPERVISION")
    private Long idTipoSupervision;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghProceso idProceso;

    public PghTipoSupervision() {
    }

    public PghTipoSupervision(Long idTipoSupervision) {
        this.idTipoSupervision = idTipoSupervision;
    }

    public PghTipoSupervision(Long idTipoSupervision, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idTipoSupervision = idTipoSupervision;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdTipoSupervision() {
        return idTipoSupervision;
    }

    public void setIdTipoSupervision(Long idTipoSupervision) {
        this.idTipoSupervision = idTipoSupervision;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PghProceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(PghProceso idProceso) {
        this.idProceso = idProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoSupervision != null ? idTipoSupervision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghTipoSupervision)) {
            return false;
        }
        PghTipoSupervision other = (PghTipoSupervision) object;
        if ((this.idTipoSupervision == null && other.idTipoSupervision != null) || (this.idTipoSupervision != null && !this.idTipoSupervision.equals(other.idTipoSupervision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghTipoSupervision[ idTipoSupervision=" + idTipoSupervision + " ]";
    }    
}

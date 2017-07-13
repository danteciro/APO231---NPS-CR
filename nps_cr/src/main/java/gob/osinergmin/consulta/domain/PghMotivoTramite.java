/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_MOTIVO_TRAMITE")
@NamedQueries({
    @NamedQuery(name = "PghMotivoTramite.findAll", query = "SELECT p FROM PghMotivoTramite p"),
    @NamedQuery(name = "PghMotivoTramite.findByIdMotivoTramite", query = "SELECT p FROM PghMotivoTramite p WHERE p.idMotivoTramite = :idMotivoTramite"),
    @NamedQuery(name = "PghMotivoTramite.findByIdTramite", query = "SELECT p FROM PghMotivoTramite p WHERE p.estado = 1 and p.idTramite.idTramite = :idTramite"),
    @NamedQuery(name = "PghMotivoTramite.findByDescripcion", query = "SELECT p FROM PghMotivoTramite p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghMotivoTramite.findByEstado", query = "SELECT p FROM PghMotivoTramite p WHERE p.estado = :estado")})
public class PghMotivoTramite extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MOTIVO_TRAMITE")
    private Long idMotivoTramite;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    
    @JoinColumn(name = "ID_TRAMITE", referencedColumnName = "ID_TRAMITE")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghTramite idTramite;
    @OneToMany(mappedBy = "idMotivoTramite", fetch = FetchType.LAZY)
    private List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList;

    public PghMotivoTramite() {
    }

    public PghMotivoTramite(Long idMotivoTramite) {
        this.idMotivoTramite = idMotivoTramite;
    }

    public Long getIdMotivoTramite() {
        return idMotivoTramite;
    }

    public void setIdMotivoTramite(Long idMotivoTramite) {
        this.idMotivoTramite = idMotivoTramite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PghTramite getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(PghTramite idTramite) {
        this.idTramite = idTramite;
    }

    public List<PghCnfRequProcedimiento> getPghCnfRequProcedimientoList() {
        return pghCnfRequProcedimientoList;
    }

    public void setPghCnfRequProcedimientoList(List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList) {
        this.pghCnfRequProcedimientoList = pghCnfRequProcedimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotivoTramite != null ? idMotivoTramite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghMotivoTramite)) {
            return false;
        }
        PghMotivoTramite other = (PghMotivoTramite) object;
        if ((this.idMotivoTramite == null && other.idMotivoTramite != null) || (this.idMotivoTramite != null && !this.idMotivoTramite.equals(other.idMotivoTramite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghMotivoTramite[ idMotivoTramite=" + idMotivoTramite + " ]";
    }
}

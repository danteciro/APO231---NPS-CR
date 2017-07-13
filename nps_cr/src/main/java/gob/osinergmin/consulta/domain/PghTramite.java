/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_TRAMITE")
@NamedQueries({
    @NamedQuery(name = "PghTramite.findAll", query = "SELECT p FROM PghTramite p where p.estado=:estado"),
    @NamedQuery(name = "PghTramite.findByIdEtapa", query = "SELECT p FROM PghTramite p where p.estado=:estado and p.idEtapa.idEtapa=:idEtapa")
})
public class PghTramite extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRAMITE")
    private Long idTramite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "idTramite")
    private List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList;
    @OneToMany(mappedBy = "idTramite")
    private List<PghMotivoTramite> pghMotivoTramiteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTramite")
    private List<PghProcedimientoTramite> pghProcedimientoTramiteList;
    @OneToMany(mappedBy = "idTramite")
    private List<PghCnfTramiteActividad> pghCnfTramiteActividadList;
    @JoinColumn(name = "ID_ETAPA", referencedColumnName = "ID_ETAPA")
    @ManyToOne
    private PghEtapa idEtapa;

    public PghTramite() {
    }

    public PghTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public PghTramite(Long idTramite, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String estado) {
        this.idTramite = idTramite;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
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
    
    @XmlTransient
    public List<PghCnfRequProcedimiento> getPghCnfRequProcedimientoList() {
        return pghCnfRequProcedimientoList;
    }

    public void setPghCnfRequProcedimientoList(List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList) {
        this.pghCnfRequProcedimientoList = pghCnfRequProcedimientoList;
    }

    @XmlTransient
    public List<PghMotivoTramite> getPghMotivoTramiteList() {
        return pghMotivoTramiteList;
    }

    public void setPghMotivoTramiteList(List<PghMotivoTramite> pghMotivoTramiteList) {
        this.pghMotivoTramiteList = pghMotivoTramiteList;
    }

    @XmlTransient
    public List<PghProcedimientoTramite> getPghProcedimientoTramiteList() {
        return pghProcedimientoTramiteList;
    }

    public void setPghProcedimientoTramiteList(List<PghProcedimientoTramite> pghProcedimientoTramiteList) {
        this.pghProcedimientoTramiteList = pghProcedimientoTramiteList;
    }

    @XmlTransient
    public List<PghCnfTramiteActividad> getPghCnfTramiteActividadList() {
        return pghCnfTramiteActividadList;
    }

    public void setPghCnfTramiteActividadList(List<PghCnfTramiteActividad> pghCnfTramiteActividadList) {
        this.pghCnfTramiteActividadList = pghCnfTramiteActividadList;
    }

    public PghEtapa getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(PghEtapa idEtapa) {
        this.idEtapa = idEtapa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTramite != null ? idTramite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghTramite)) {
            return false;
        }
        PghTramite other = (PghTramite) object;
        if ((this.idTramite == null && other.idTramite != null) || (this.idTramite != null && !this.idTramite.equals(other.idTramite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghTramite[ idTramite=" + idTramite + " ]";
    }  
}

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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_ZONIFICACION_DETALLE")
@NamedQueries({
    @NamedQuery(name = "PghZonificacionDetalle.findAll", query = "SELECT p FROM PghZonificacion p"),
    @NamedQuery(name = "PghZonificacionDetalle.findByIdZonificacion", query = "SELECT p FROM PghZonificacion p WHERE p.idZonificacion = :idZonificacion"),
    @NamedQuery(name = "PghZonificacion.findByEstado", query = "SELECT p FROM PghZonificacion p WHERE p.estado = :estado")})
public class PghZonificacionDetalle extends Auditoria {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ZONIFICACION_DETALLE")
    private Long idZonificacionDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @NotNull
    @Column(name = "ID_MACRO_REGION")
    private Long idMacroRegion;
    @NotNull
    @Column(name = "ID_REGION")
    private Long idRegion;
    @JoinColumn(name = "ID_ZONIFICACION", referencedColumnName = "ID_ZONIFICACION")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PghZonificacion idZonificacion;
    @JoinColumns({
        @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO"),
        @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA"),
        @JoinColumn(name = "ID_DISTRITO", referencedColumnName = "ID_DISTRITO")})
    @ManyToOne(fetch = FetchType.LAZY)
    private MdiUbigeo mdiUbigeo;
//    @OneToMany(mappedBy = "idZonificacionDetalle", fetch = FetchType.LAZY)
//    private List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList;

    public PghZonificacionDetalle() {
    }

    public PghZonificacionDetalle(Long idZonificacionDetalle) {
        this.idZonificacionDetalle = idZonificacionDetalle;
    }

    public PghZonificacionDetalle(Long idZonificacionDetalle, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String estado) {
        this.idZonificacionDetalle = idZonificacionDetalle;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdZonificacionDetalle() {
        return idZonificacionDetalle;
    }

    public void setIdZonificacionDetalle(Long idZonificacionDetalle) {
        this.idZonificacionDetalle = idZonificacionDetalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public MdiUbigeo getMdiUbigeo() {
        return mdiUbigeo;
    }

    public void setMdiUbigeo(MdiUbigeo mdiUbigeo) {
        this.mdiUbigeo = mdiUbigeo;
    }

//    public List<PghCnfRequProcedimiento> getPghCnfRequProcedimientoList() {
//        return pghCnfRequProcedimientoList;
//    }
//
//    public void setPghCnfRequProcedimientoList(List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList) {
//        this.pghCnfRequProcedimientoList = pghCnfRequProcedimientoList;
//    }

    public Long getIdMacroRegion() {
        return idMacroRegion;
    }

    public void setIdMacroRegion(Long idMacroRegion) {
        this.idMacroRegion = idMacroRegion;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public PghZonificacion getIdZonificacion() {
        return idZonificacion;
    }

    public void setIdZonificacion(PghZonificacion idZonificacion) {
        this.idZonificacion = idZonificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZonificacionDetalle != null ? idZonificacionDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghZonificacionDetalle)) {
            return false;
        }
        PghZonificacionDetalle other = (PghZonificacionDetalle) object;
        if ((this.idZonificacionDetalle == null && other.idZonificacionDetalle != null) || (this.idZonificacionDetalle != null && !this.idZonificacionDetalle.equals(other.idZonificacionDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghZonificacionDetalle[ idZonificacionDetalle=" + idZonificacionDetalle + " ]";
    }
}

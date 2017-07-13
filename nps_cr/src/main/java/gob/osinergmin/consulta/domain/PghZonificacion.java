/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "PGH_ZONIFICACION")
@NamedQueries({
    @NamedQuery(name = "PghZonificacion.findAll", query = "SELECT p FROM PghZonificacion p"),
    @NamedQuery(name = "PghZonificacion.findByIdZonificacion", query = "SELECT p FROM PghZonificacion p where p.idZonificacion=:idZonificacion")
})
public class PghZonificacion extends Auditoria{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ZONIFICACION")
    private Long idZonificacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 38)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idZonificacion", fetch = FetchType.LAZY)
    private List<PghZonificacionDetalle> pghZonificacionDetalleList;    
    
    @OneToMany(mappedBy = "idZonificacion", fetch = FetchType.LAZY)
    private List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList;

    public Long getIdZonificacion() {
        return idZonificacion;
    }

    public void setIdZonificacion(Long idZonificacion) {
        this.idZonificacion = idZonificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PghZonificacionDetalle> getPghZonificacionDetalleList() {
        return pghZonificacionDetalleList;
    }

    public void setPghZonificacionDetalleList(List<PghZonificacionDetalle> pghZonificacionDetalleList) {
        this.pghZonificacionDetalleList = pghZonificacionDetalleList;
    }

    public List<PghCnfRequProcedimiento> getPghCnfRequProcedimientoList() {
        return pghCnfRequProcedimientoList;
    }

    public void setPghCnfRequProcedimientoList(List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList) {
        this.pghCnfRequProcedimientoList = pghCnfRequProcedimientoList;
    }
    
    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghZonificacion[ idZonificacion=" + idZonificacion + " ]";
    }
    
}

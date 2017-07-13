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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "PGH_TIPIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghTipificacion.findAll", query = "SELECT p FROM PghTipificacion p"),
    @NamedQuery(name = "PghTipificacion.findByIdTipificacion", query = "SELECT p FROM PghTipificacion p WHERE p.idTipificacion = :idTipificacion"),
    @NamedQuery(name = "PghTipificacion.findByDescripcion", query = "SELECT p FROM PghTipificacion p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghTipificacion.findByIdTipoMoneda", query = "SELECT p FROM PghTipificacion p WHERE p.idTipoMoneda = :idTipoMoneda"),
    @NamedQuery(name = "PghTipificacion.findBySancionMonetaria", query = "SELECT p FROM PghTipificacion p WHERE p.sancionMonetaria = :sancionMonetaria"),
    @NamedQuery(name = "PghTipificacion.findByEstado", query = "SELECT p FROM PghTipificacion p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghTipificacion.findByIdTipificacionPadre", query = "SELECT p FROM PghTipificacion p WHERE p.idTipificacionPadre = :idTipificacionPadre"),
    @NamedQuery(name = "PghTipificacion.findByUsuarioCreacion", query = "SELECT p FROM PghTipificacion p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghTipificacion.findByTerminalCreacion", query = "SELECT p FROM PghTipificacion p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghTipificacion.findByUsuarioActualizacion", query = "SELECT p FROM PghTipificacion p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghTipificacion.findByTerminalActualizacion", query = "SELECT p FROM PghTipificacion p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghTipificacion.findByBasesLegales", query = "SELECT p FROM PghTipificacion p WHERE p.basesLegales = :basesLegales"),
    @NamedQuery(name = "PghTipificacion.findByFechaCreacion", query = "SELECT p FROM PghTipificacion p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghTipificacion.findByFechaActualizacion", query = "SELECT p FROM PghTipificacion p WHERE p.fechaActualizacion = :fechaActualizacion")})
public class PghTipificacion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPIFICACION")
    private Long idTipificacion;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ID_TIPO_MONEDA")
    private Long idTipoMoneda;
    @Column(name = "SANCION_MONETARIA")
    private String sancionMonetaria;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "ID_TIPIFICACION_PADRE")
    private Long idTipificacionPadre;    
    @Column(name = "BASES_LEGALES")
    private String basesLegales;    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pghTipificacion1")
    private PghTipificacion pghTipificacion;
    @JoinColumn(name = "ID_TIPIFICACION", referencedColumnName = "ID_TIPIFICACION", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PghTipificacion pghTipificacion1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipificacion")
    private List<PghCriterio> pghCriterioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pghTipificacion")
    private List<PghObligacionTipificacion> pghObligacionTipificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pghTipificacion")
    private List<PghTipificacionSancion> pghTipificacionSancionList;

    public PghTipificacion() {
    }

    public PghTipificacion(Long idTipificacion) {
        this.idTipificacion = idTipificacion;
    }

    public Long getIdTipificacion() {
        return idTipificacion;
    }

    public void setIdTipificacion(Long idTipificacion) {
        this.idTipificacion = idTipificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(Long idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public String getSancionMonetaria() {
        return sancionMonetaria;
    }

    public void setSancionMonetaria(String sancionMonetaria) {
        this.sancionMonetaria = sancionMonetaria;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Long getIdTipificacionPadre() {
        return idTipificacionPadre;
    }

    public void setIdTipificacionPadre(Long idTipificacionPadre) {
        this.idTipificacionPadre = idTipificacionPadre;
    }

    public String getBasesLegales() {
        return basesLegales;
    }

    public void setBasesLegales(String basesLegales) {
        this.basesLegales = basesLegales;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public PghTipificacion getPghTipificacion() {
        return pghTipificacion;
    }

    public void setPghTipificacion(PghTipificacion pghTipificacion) {
        this.pghTipificacion = pghTipificacion;
    }

    public PghTipificacion getPghTipificacion1() {
        return pghTipificacion1;
    }

    public void setPghTipificacion1(PghTipificacion pghTipificacion1) {
        this.pghTipificacion1 = pghTipificacion1;
    }

    @XmlTransient
    public List<PghCriterio> getPghCriterioList() {
        return pghCriterioList;
    }

    public void setPghCriterioList(List<PghCriterio> pghCriterioList) {
        this.pghCriterioList = pghCriterioList;
    }

    @XmlTransient
    public List<PghObligacionTipificacion> getPghObligacionTipificacionList() {
        return pghObligacionTipificacionList;
    }

    public void setPghObligacionTipificacionList(List<PghObligacionTipificacion> pghObligacionTipificacionList) {
        this.pghObligacionTipificacionList = pghObligacionTipificacionList;
    }

    @XmlTransient
    public List<PghTipificacionSancion> getPghTipificacionSancionList() {
        return pghTipificacionSancionList;
    }

    public void setPghTipificacionSancionList(List<PghTipificacionSancion> pghTipificacionSancionList) {
        this.pghTipificacionSancionList = pghTipificacionSancionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipificacion != null ? idTipificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghTipificacion)) {
            return false;
        }
        PghTipificacion other = (PghTipificacion) object;
        if ((this.idTipificacion == null && other.idTipificacion != null) || (this.idTipificacion != null && !this.idTipificacion.equals(other.idTipificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghTipificacion[ idTipificacion=" + idTipificacion + " ]";
    }
    
}

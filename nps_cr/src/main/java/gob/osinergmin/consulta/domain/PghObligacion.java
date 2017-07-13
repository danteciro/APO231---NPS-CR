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
@Table(name = "PGH_OBLIGACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghObligacion.findAll", query = "SELECT p FROM PghObligacion p"),
    @NamedQuery(name = "PghObligacion.findByIdObligacion", query = "SELECT p FROM PghObligacion p WHERE p.idObligacion = :idObligacion"),
    @NamedQuery(name = "PghObligacion.findByCodigoObligacion", query = "SELECT p FROM PghObligacion p WHERE p.codigoObligacion = :codigoObligacion"),
    @NamedQuery(name = "PghObligacion.findByFechaEntradaVigencia", query = "SELECT p FROM PghObligacion p WHERE p.fechaEntradaVigencia = :fechaEntradaVigencia"),
    @NamedQuery(name = "PghObligacion.findByFechaTerminoVigencia", query = "SELECT p FROM PghObligacion p WHERE p.fechaTerminoVigencia = :fechaTerminoVigencia"),
    @NamedQuery(name = "PghObligacion.findByIdTipoCreacion", query = "SELECT p FROM PghObligacion p WHERE p.idTipoCreacion = :idTipoCreacion"),
    @NamedQuery(name = "PghObligacion.findByDescripcion", query = "SELECT p FROM PghObligacion p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghObligacion.findByIdCriticidad", query = "SELECT p FROM PghObligacion p WHERE p.idCriticidad = :idCriticidad"),
    @NamedQuery(name = "PghObligacion.findByEstado", query = "SELECT p FROM PghObligacion p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghObligacion.findByUsuarioCreacion", query = "SELECT p FROM PghObligacion p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghObligacion.findByFechaCreacion", query = "SELECT p FROM PghObligacion p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghObligacion.findByTerminalCreacion", query = "SELECT p FROM PghObligacion p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghObligacion.findByUsuarioActualizacion", query = "SELECT p FROM PghObligacion p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghObligacion.findByFechaActualizacion", query = "SELECT p FROM PghObligacion p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghObligacion.findByTerminalActualizacion", query = "SELECT p FROM PghObligacion p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghObligacion.findByCodigoAntecesor", query = "SELECT p FROM PghObligacion p WHERE p.codigoAntecesor = :codigoAntecesor"),
    @NamedQuery(name = "PghObligacion.findByRuta", query = "SELECT p FROM PghObligacion p WHERE p.ruta = :ruta"),
    @NamedQuery(name = "PghObligacion.findByIdObligacionRef", query = "SELECT p FROM PghObligacion p WHERE p.idObligacionRef = :idObligacionRef")})
public class PghObligacion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OBLIGACION")
    private Long idObligacion;
    @Column(name = "CODIGO_OBLIGACION")
    private String codigoObligacion;
    @Column(name = "FECHA_ENTRADA_VIGENCIA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntradaVigencia;
    @Column(name = "FECHA_TERMINO_VIGENCIA")
    @Temporal(TemporalType.DATE)
    private Date fechaTerminoVigencia;
    @Column(name = "ID_TIPO_CREACION")
    private Long idTipoCreacion;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ID_CRITICIDAD")
    private Long idCriticidad;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "CODIGO_ANTECESOR")
    private String codigoAntecesor;
    @Column(name = "RUTA")
    private String ruta;
    @Column(name = "ID_OBLIGACION_REF")
    private Long idObligacionRef;
    @OneToMany(mappedBy = "idObligacion")
    private List<PghDetalleObligacion> pghDetalleObligacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idObligacion")
    private List<PghConfObligacion> pghConfObligacionList;
    @OneToMany(mappedBy = "idObligacion")
    private List<PghCriterio> pghCriterioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pghObligacion")
    private List<PghObligacionTipificacion> pghObligacionTipificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pghObligacion")
    private List<PghTemaObligacionMaestro> pghTemaObligacionMaestroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pghObligacion")
    private List<PghObligacionBaseLegal> pghObligacionBaseLegalList;

    public PghObligacion() {
    }

    public PghObligacion(Long idObligacion) {
        this.idObligacion = idObligacion;
    }

    public Long getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(Long idObligacion) {
        this.idObligacion = idObligacion;
    }

    public String getCodigoObligacion() {
        return codigoObligacion;
    }

    public void setCodigoObligacion(String codigoObligacion) {
        this.codigoObligacion = codigoObligacion;
    }

    public Date getFechaEntradaVigencia() {
        return fechaEntradaVigencia;
    }

    public void setFechaEntradaVigencia(Date fechaEntradaVigencia) {
        this.fechaEntradaVigencia = fechaEntradaVigencia;
    }

    public Date getFechaTerminoVigencia() {
        return fechaTerminoVigencia;
    }

    public void setFechaTerminoVigencia(Date fechaTerminoVigencia) {
        this.fechaTerminoVigencia = fechaTerminoVigencia;
    }

    public Long getIdTipoCreacion() {
        return idTipoCreacion;
    }

    public void setIdTipoCreacion(Long idTipoCreacion) {
        this.idTipoCreacion = idTipoCreacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdCriticidad() {
        return idCriticidad;
    }

    public void setIdCriticidad(Long idCriticidad) {
        this.idCriticidad = idCriticidad;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getCodigoAntecesor() {
        return codigoAntecesor;
    }

    public void setCodigoAntecesor(String codigoAntecesor) {
        this.codigoAntecesor = codigoAntecesor;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Long getIdObligacionRef() {
        return idObligacionRef;
    }

    public void setIdObligacionRef(Long idObligacionRef) {
        this.idObligacionRef = idObligacionRef;
    }

    @XmlTransient
    public List<PghDetalleObligacion> getPghDetalleObligacionList() {
        return pghDetalleObligacionList;
    }

    public void setPghDetalleObligacionList(List<PghDetalleObligacion> pghDetalleObligacionList) {
        this.pghDetalleObligacionList = pghDetalleObligacionList;
    }

    @XmlTransient
    public List<PghConfObligacion> getPghConfObligacionList() {
        return pghConfObligacionList;
    }

    public void setPghConfObligacionList(List<PghConfObligacion> pghConfObligacionList) {
        this.pghConfObligacionList = pghConfObligacionList;
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
    public List<PghTemaObligacionMaestro> getPghTemaObligacionMaestroList() {
        return pghTemaObligacionMaestroList;
    }

    public void setPghTemaObligacionMaestroList(List<PghTemaObligacionMaestro> pghTemaObligacionMaestroList) {
        this.pghTemaObligacionMaestroList = pghTemaObligacionMaestroList;
    }

    @XmlTransient
    public List<PghObligacionBaseLegal> getPghObligacionBaseLegalList() {
        return pghObligacionBaseLegalList;
    }

    public void setPghObligacionBaseLegalList(List<PghObligacionBaseLegal> pghObligacionBaseLegalList) {
        this.pghObligacionBaseLegalList = pghObligacionBaseLegalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObligacion != null ? idObligacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghObligacion)) {
            return false;
        }
        PghObligacion other = (PghObligacion) object;
        if ((this.idObligacion == null && other.idObligacion != null) || (this.idObligacion != null && !this.idObligacion.equals(other.idObligacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinerg.consulta.domain.PghObligacion[ idObligacion=" + idObligacion + " ]";
    }
    
}

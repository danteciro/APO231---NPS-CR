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
@Table(name = "PGH_BASE_LEGAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PghBaseLegal.findAll", query = "SELECT p FROM PghBaseLegal p"),
    @NamedQuery(name = "PghBaseLegal.findByIdBaseLegal", query = "SELECT p FROM PghBaseLegal p WHERE p.idBaseLegal = :idBaseLegal"),
    @NamedQuery(name = "PghBaseLegal.findByCodigoBaseLegal", query = "SELECT p FROM PghBaseLegal p WHERE p.codigoBaseLegal = :codigoBaseLegal"),
    @NamedQuery(name = "PghBaseLegal.findByIdTipoNormaLegal", query = "SELECT p FROM PghBaseLegal p WHERE p.idTipoNormaLegal = :idTipoNormaLegal"),
    @NamedQuery(name = "PghBaseLegal.findByNumero", query = "SELECT p FROM PghBaseLegal p WHERE p.numero = :numero"),
    @NamedQuery(name = "PghBaseLegal.findByAnio", query = "SELECT p FROM PghBaseLegal p WHERE p.anio = :anio"),
    @NamedQuery(name = "PghBaseLegal.findByIdSigla", query = "SELECT p FROM PghBaseLegal p WHERE p.idSigla = :idSigla"),
    @NamedQuery(name = "PghBaseLegal.findByFechaEntradaVigencia", query = "SELECT p FROM PghBaseLegal p WHERE p.fechaEntradaVigencia = :fechaEntradaVigencia"),
    @NamedQuery(name = "PghBaseLegal.findByFechaPublicacion", query = "SELECT p FROM PghBaseLegal p WHERE p.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "PghBaseLegal.findByTitulo", query = "SELECT p FROM PghBaseLegal p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "PghBaseLegal.findByDescripcion", query = "SELECT p FROM PghBaseLegal p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghBaseLegal.findByEstado", query = "SELECT p FROM PghBaseLegal p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghBaseLegal.findByUsuarioCreacion", query = "SELECT p FROM PghBaseLegal p WHERE p.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "PghBaseLegal.findByFechaCreacion", query = "SELECT p FROM PghBaseLegal p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PghBaseLegal.findByTerminalCreacion", query = "SELECT p FROM PghBaseLegal p WHERE p.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "PghBaseLegal.findByUsuarioActualizacion", query = "SELECT p FROM PghBaseLegal p WHERE p.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "PghBaseLegal.findByFechaActualizacion", query = "SELECT p FROM PghBaseLegal p WHERE p.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "PghBaseLegal.findByTerminalActualizacion", query = "SELECT p FROM PghBaseLegal p WHERE p.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "PghBaseLegal.findByCodigoAntecesor", query = "SELECT p FROM PghBaseLegal p WHERE p.codigoAntecesor = :codigoAntecesor"),
    @NamedQuery(name = "PghBaseLegal.findByIdBaseLegalRef", query = "SELECT p FROM PghBaseLegal p WHERE p.idBaseLegalRef = :idBaseLegalRef")})
public class PghBaseLegal extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_BASE_LEGAL")
    private Long idBaseLegal;
    @Column(name = "CODIGO_BASE_LEGAL")
    private String codigoBaseLegal;
    @Column(name = "ID_TIPO_NORMA_LEGAL")
    private Long idTipoNormaLegal;
    @Column(name = "NUMERO")
    private Long numero;
    @Column(name = "ANIO")
    private String anio;
    @Column(name = "ID_SIGLA")
    private Long idSigla;
    @Column(name = "FECHA_ENTRADA_VIGENCIA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntradaVigencia;
    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ESTADO")
    private Character estado;    
    @Column(name = "CODIGO_ANTECESOR")
    private String codigoAntecesor;
    @Column(name = "ID_BASE_LEGAL_REF")
    private Long idBaseLegalRef;
    @OneToMany(mappedBy = "idBaseLegal")
    private List<PghDetalleBaseLegal> pghDetalleBaseLegalList;
    @OneToMany(mappedBy = "idBaseLegal")
    private List<PghListadoBaseLegal> pghListadoBaseLegalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pghBaseLegal")
    private List<PghObligacionBaseLegal> pghObligacionBaseLegalList;

    public PghBaseLegal() {
    }

    public PghBaseLegal(Long idBaseLegal) {
        this.idBaseLegal = idBaseLegal;
    }

    public Long getIdBaseLegal() {
        return idBaseLegal;
    }

    public void setIdBaseLegal(Long idBaseLegal) {
        this.idBaseLegal = idBaseLegal;
    }

    public String getCodigoBaseLegal() {
        return codigoBaseLegal;
    }

    public void setCodigoBaseLegal(String codigoBaseLegal) {
        this.codigoBaseLegal = codigoBaseLegal;
    }

    public Long getIdTipoNormaLegal() {
        return idTipoNormaLegal;
    }

    public void setIdTipoNormaLegal(Long idTipoNormaLegal) {
        this.idTipoNormaLegal = idTipoNormaLegal;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Long getIdSigla() {
        return idSigla;
    }

    public void setIdSigla(Long idSigla) {
        this.idSigla = idSigla;
    }

    public Date getFechaEntradaVigencia() {
        return fechaEntradaVigencia;
    }

    public void setFechaEntradaVigencia(Date fechaEntradaVigencia) {
        this.fechaEntradaVigencia = fechaEntradaVigencia;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Long getIdBaseLegalRef() {
        return idBaseLegalRef;
    }

    public void setIdBaseLegalRef(Long idBaseLegalRef) {
        this.idBaseLegalRef = idBaseLegalRef;
    }

    @XmlTransient
    public List<PghDetalleBaseLegal> getPghDetalleBaseLegalList() {
        return pghDetalleBaseLegalList;
    }

    public void setPghDetalleBaseLegalList(List<PghDetalleBaseLegal> pghDetalleBaseLegalList) {
        this.pghDetalleBaseLegalList = pghDetalleBaseLegalList;
    }

    @XmlTransient
    public List<PghListadoBaseLegal> getPghListadoBaseLegalList() {
        return pghListadoBaseLegalList;
    }

    public void setPghListadoBaseLegalList(List<PghListadoBaseLegal> pghListadoBaseLegalList) {
        this.pghListadoBaseLegalList = pghListadoBaseLegalList;
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
        hash += (idBaseLegal != null ? idBaseLegal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghBaseLegal)) {
            return false;
        }
        PghBaseLegal other = (PghBaseLegal) object;
        if ((this.idBaseLegal == null && other.idBaseLegal != null) || (this.idBaseLegal != null && !this.idBaseLegal.equals(other.idBaseLegal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PghBaseLegal[ idBaseLegal=" + idBaseLegal + " ]";
    }
    
}

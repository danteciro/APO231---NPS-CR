/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_UNIDAD_ORGANICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiUnidadOrganica.findAll", query = "SELECT m FROM MdiUnidadOrganica m"),
    @NamedQuery(name = "MdiUnidadOrganica.findByIdUnidadOrganica", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.idUnidadOrganica = :idUnidadOrganica"),
    @NamedQuery(name = "MdiUnidadOrganica.findByIdUnidadOrganicaSuperior", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.idUnidadOrganicaSuperior = :idUnidadOrganicaSuperior"),
    @NamedQuery(name = "MdiUnidadOrganica.findBySede", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.sede = :sede"),
    @NamedQuery(name = "MdiUnidadOrganica.findByDescripcion", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MdiUnidadOrganica.findByDetalle", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.detalle = :detalle"),
    @NamedQuery(name = "MdiUnidadOrganica.findByCodDepSiga", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.codDepSiga = :codDepSiga"),
    @NamedQuery(name = "MdiUnidadOrganica.findByIdTipoUnidadOrganica", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.idTipoUnidadOrganica = :idTipoUnidadOrganica"),
    @NamedQuery(name = "MdiUnidadOrganica.findBySigla", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.sigla = :sigla"),
    @NamedQuery(name = "MdiUnidadOrganica.findByEstado", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiUnidadOrganica.findByUsuarioCreacion", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiUnidadOrganica.findByUsuarioActualizacion", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiUnidadOrganica.findByTerminalCreacion", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiUnidadOrganica.findByTerminalActualizacion", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiUnidadOrganica.findByFechaCreacion", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiUnidadOrganica.findByFechaActualizacion", query = "SELECT m FROM MdiUnidadOrganica m WHERE m.fechaActualizacion = :fechaActualizacion")})
public class MdiUnidadOrganica extends Auditoria {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UNIDAD_ORGANICA")
    private BigDecimal idUnidadOrganica;
    @Column(name = "ID_UNIDAD_ORGANICA_SUPERIOR")
    private BigInteger idUnidadOrganicaSuperior;
    @Column(name = "SEDE")
    private BigInteger sede;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "DETALLE")
    private String detalle;
    @Basic(optional = false)
    @Column(name = "COD_DEP_SIGA")
    private String codDepSiga;
    @Column(name = "ID_TIPO_UNIDAD_ORGANICA")
    private BigInteger idTipoUnidadOrganica;
    @Basic(optional = false)
    @Column(name = "SIGLA")
    private String sigla;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiUnidadOrganica")
    private List<MdiUnidadSupXUnidadOrg> mdiUnidadSupXUnidadOrgList;
    @OneToMany(mappedBy = "idUnidadOrganica")
    private List<MdiEmpresaLocaXUnidadOrg> mdiEmpresaLocaXUnidadOrgList;

    public MdiUnidadOrganica() {
    }

    public MdiUnidadOrganica(BigDecimal idUnidadOrganica) {
        this.idUnidadOrganica = idUnidadOrganica;
    }

    public MdiUnidadOrganica(BigDecimal idUnidadOrganica, String descripcion, String detalle, String codDepSiga, String sigla, char estado, String usuarioCreacion, String terminalCreacion, Date fechaCreacion) {
        this.idUnidadOrganica = idUnidadOrganica;
        this.descripcion = descripcion;
        this.detalle = detalle;
        this.codDepSiga = codDepSiga;
        this.sigla = sigla;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public BigDecimal getIdUnidadOrganica() {
        return idUnidadOrganica;
    }

    public void setIdUnidadOrganica(BigDecimal idUnidadOrganica) {
        this.idUnidadOrganica = idUnidadOrganica;
    }

    public BigInteger getIdUnidadOrganicaSuperior() {
        return idUnidadOrganicaSuperior;
    }

    public void setIdUnidadOrganicaSuperior(BigInteger idUnidadOrganicaSuperior) {
        this.idUnidadOrganicaSuperior = idUnidadOrganicaSuperior;
    }

    public BigInteger getSede() {
        return sede;
    }

    public void setSede(BigInteger sede) {
        this.sede = sede;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getCodDepSiga() {
        return codDepSiga;
    }

    public void setCodDepSiga(String codDepSiga) {
        this.codDepSiga = codDepSiga;
    }

    public BigInteger getIdTipoUnidadOrganica() {
        return idTipoUnidadOrganica;
    }

    public void setIdTipoUnidadOrganica(BigInteger idTipoUnidadOrganica) {
        this.idTipoUnidadOrganica = idTipoUnidadOrganica;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiUnidadSupXUnidadOrg> getMdiUnidadSupXUnidadOrgList() {
        return mdiUnidadSupXUnidadOrgList;
    }

    public void setMdiUnidadSupXUnidadOrgList(List<MdiUnidadSupXUnidadOrg> mdiUnidadSupXUnidadOrgList) {
        this.mdiUnidadSupXUnidadOrgList = mdiUnidadSupXUnidadOrgList;
    }

    @XmlTransient
    public List<MdiEmpresaLocaXUnidadOrg> getMdiEmpresaLocaXUnidadOrgList() {
        return mdiEmpresaLocaXUnidadOrgList;
    }

    public void setMdiEmpresaLocaXUnidadOrgList(List<MdiEmpresaLocaXUnidadOrg> mdiEmpresaLocaXUnidadOrgList) {
        this.mdiEmpresaLocaXUnidadOrgList = mdiEmpresaLocaXUnidadOrgList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadOrganica != null ? idUnidadOrganica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiUnidadOrganica)) {
            return false;
        }
        MdiUnidadOrganica other = (MdiUnidadOrganica) object;
        if ((this.idUnidadOrganica == null && other.idUnidadOrganica != null) || (this.idUnidadOrganica != null && !this.idUnidadOrganica.equals(other.idUnidadOrganica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiUnidadOrganica[ idUnidadOrganica=" + idUnidadOrganica + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_UNIDAD_SUPERVISADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiUnidadSupervisada.findAll", query = "SELECT m FROM MdiUnidadSupervisada m"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByCodigoOsinergmin", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.codigoOsinergmin = :codigoOsinergmin"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByNombreUnidad", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.nombreUnidad = :nombreUnidad"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByFechaActualizacion", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByFechaCreacion", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByIdUnidadSupervisada", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.idUnidadSupervisada = :idUnidadSupervisada"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByUsuarioActualizacion", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByUsuarioCreacion", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByTerminalActualizacion", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByTerminalCreacion", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByTipoUnidad", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.tipoUnidad = :tipoUnidad"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByEstado", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByDireccion", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.direccion = :direccion"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByZona", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.zona = :zona"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByUtm", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.utm = :utm"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByResolucionDirectoral", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.resolucionDirectoral = :resolucionDirectoral"),
    @NamedQuery(name = "MdiUnidadSupervisada.findByIdTipoActividad", query = "SELECT m FROM MdiUnidadSupervisada m WHERE m.idTipoActividad = :idTipoActividad")})
public class MdiUnidadSupervisada extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "CODIGO_OSINERGMIN")
    private String codigoOsinergmin;
    @Basic(optional = false)
    @Column(name = "NOMBRE_UNIDAD")
    private String nombreUnidad;    
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UNIDAD_SUPERVISADA")
    private Long idUnidadSupervisada;
    @Column(name = "TIPO_UNIDAD")
    private Long tipoUnidad;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "ZONA")
    private Character zona;
    @Column(name = "UTM")
    private String utm;
    @Column(name = "RESOLUCION_DIRECTORAL")
    private String resolucionDirectoral;
    @Column(name = "ID_TIPO_ACTIVIDAD")
    private Short idTipoActividad;
    @OneToMany(mappedBy = "idUnidadSupervisada")
    private List<MdiEmpresaContacto> mdiEmpresaContactoList;
    @OneToMany(mappedBy = "idUnidadSupervisada")
    private List<MdiDocumentoAdjunto> mdiDocumentoAdjuntoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiUnidadSupervisada")
    private List<MdiUnidadSupXUnidadOrg> mdiUnidadSupXUnidadOrgList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadSupervisada")
    private List<MdiDirccionUnidadSuprvisada> mdiDirccionUnidadSuprvisadaList;
    @OneToMany(mappedBy = "idUnidadSupervisada")
    private List<MdiPoliza> mdiPolizaList;
    @JoinColumn(name = "ID_ETAPA", referencedColumnName = "ID_ETAPA")
    @ManyToOne
    private MdiEtapa idEtapa;
    @JoinColumn(name = "ID_EMPRESA_SUPERVISADA", referencedColumnName = "ID_EMPRESA_SUPERVISADA")
    @ManyToOne(optional = false)
    private MdiEmpresaSupervisada idEmpresaSupervisada;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ManyToOne
    private MdiActividad idActividad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiUnidadSupervisada")
    private List<MdiEmpresaUnidadSupervisada> mdiEmpresaUnidadSupervisadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUnidadSupervisada")
    private List<MdiRegistroHidrocarburo> mdiRegistroHidrocarburoList;

    public MdiUnidadSupervisada() {
    }

    public MdiUnidadSupervisada(Long idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    public MdiUnidadSupervisada(Long idUnidadSupervisada, String codigoOsinergmin, String nombreUnidad, Date fechaCreacion, String usuarioCreacion, String terminalCreacion, char estado) {
        this.idUnidadSupervisada = idUnidadSupervisada;
        this.codigoOsinergmin = codigoOsinergmin;
        this.nombreUnidad = nombreUnidad;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public String getCodigoOsinergmin() {
        return codigoOsinergmin;
    }

    public void setCodigoOsinergmin(String codigoOsinergmin) {
        this.codigoOsinergmin = codigoOsinergmin;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public Long getIdUnidadSupervisada() {
        return idUnidadSupervisada;
    }

    public void setIdUnidadSupervisada(Long idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    public Long getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(Long tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Character getZona() {
        return zona;
    }

    public void setZona(Character zona) {
        this.zona = zona;
    }

    public String getUtm() {
        return utm;
    }

    public void setUtm(String utm) {
        this.utm = utm;
    }

    public String getResolucionDirectoral() {
        return resolucionDirectoral;
    }

    public void setResolucionDirectoral(String resolucionDirectoral) {
        this.resolucionDirectoral = resolucionDirectoral;
    }

    public Short getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Short idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    @XmlTransient
    public List<MdiEmpresaContacto> getMdiEmpresaContactoList() {
        return mdiEmpresaContactoList;
    }

    public void setMdiEmpresaContactoList(List<MdiEmpresaContacto> mdiEmpresaContactoList) {
        this.mdiEmpresaContactoList = mdiEmpresaContactoList;
    }

    @XmlTransient
    public List<MdiDocumentoAdjunto> getMdiDocumentoAdjuntoList() {
        return mdiDocumentoAdjuntoList;
    }

    public void setMdiDocumentoAdjuntoList(List<MdiDocumentoAdjunto> mdiDocumentoAdjuntoList) {
        this.mdiDocumentoAdjuntoList = mdiDocumentoAdjuntoList;
    }

    @XmlTransient
    public List<MdiUnidadSupXUnidadOrg> getMdiUnidadSupXUnidadOrgList() {
        return mdiUnidadSupXUnidadOrgList;
    }

    public void setMdiUnidadSupXUnidadOrgList(List<MdiUnidadSupXUnidadOrg> mdiUnidadSupXUnidadOrgList) {
        this.mdiUnidadSupXUnidadOrgList = mdiUnidadSupXUnidadOrgList;
    }

    @XmlTransient
    public List<MdiDirccionUnidadSuprvisada> getMdiDirccionUnidadSuprvisadaList() {
        return mdiDirccionUnidadSuprvisadaList;
    }

    public void setMdiDirccionUnidadSuprvisadaList(List<MdiDirccionUnidadSuprvisada> mdiDirccionUnidadSuprvisadaList) {
        this.mdiDirccionUnidadSuprvisadaList = mdiDirccionUnidadSuprvisadaList;
    }

    @XmlTransient
    public List<MdiPoliza> getMdiPolizaList() {
        return mdiPolizaList;
    }

    public void setMdiPolizaList(List<MdiPoliza> mdiPolizaList) {
        this.mdiPolizaList = mdiPolizaList;
    }

    public MdiEtapa getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(MdiEtapa idEtapa) {
        this.idEtapa = idEtapa;
    }

    public MdiEmpresaSupervisada getIdEmpresaSupervisada() {
        return idEmpresaSupervisada;
    }

    public void setIdEmpresaSupervisada(MdiEmpresaSupervisada idEmpresaSupervisada) {
        this.idEmpresaSupervisada = idEmpresaSupervisada;
    }

    public MdiActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(MdiActividad idActividad) {
        this.idActividad = idActividad;
    }

    @XmlTransient
    public List<MdiEmpresaUnidadSupervisada> getMdiEmpresaUnidadSupervisadaList() {
        return mdiEmpresaUnidadSupervisadaList;
    }

    public void setMdiEmpresaUnidadSupervisadaList(List<MdiEmpresaUnidadSupervisada> mdiEmpresaUnidadSupervisadaList) {
        this.mdiEmpresaUnidadSupervisadaList = mdiEmpresaUnidadSupervisadaList;
    }

    @XmlTransient
    public List<MdiRegistroHidrocarburo> getMdiRegistroHidrocarburoList() {
        return mdiRegistroHidrocarburoList;
    }

    public void setMdiRegistroHidrocarburoList(List<MdiRegistroHidrocarburo> mdiRegistroHidrocarburoList) {
        this.mdiRegistroHidrocarburoList = mdiRegistroHidrocarburoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadSupervisada != null ? idUnidadSupervisada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiUnidadSupervisada)) {
            return false;
        }
        MdiUnidadSupervisada other = (MdiUnidadSupervisada) object;
        if ((this.idUnidadSupervisada == null && other.idUnidadSupervisada != null) || (this.idUnidadSupervisada != null && !this.idUnidadSupervisada.equals(other.idUnidadSupervisada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiUnidadSupervisada[ idUnidadSupervisada=" + idUnidadSupervisada + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_EMPRESA_SUPERVISADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiEmpresaSupervisada.findAll", query = "SELECT m FROM MdiEmpresaSupervisada m"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByIdEmpresaSupervisada", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.idEmpresaSupervisada = :idEmpresaSupervisada"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByRazonSocial", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.razonSocial = :razonSocial"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByNumeroIdentificacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByNombreComercial", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.nombreComercial = :nombreComercial"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByApellidoPaterno", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByApellidoMaterno", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByTelefono", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByUsuarioCreacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByUsuarioActualizacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByFechaCreacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByFechaActualizacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByNombre", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByTerminalActualizacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByTerminalCreacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByCiiuPrincipal", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.ciiuPrincipal = :ciiuPrincipal"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByFlagBandera", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.flagBandera = :flagBandera"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByTipoDocumentoIdentidad", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.tipoDocumentoIdentidad = :tipoDocumentoIdentidad"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByNaturaleza", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.naturaleza = :naturaleza"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByOrigenInformacion", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.origenInformacion = :origenInformacion"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByCodigoEmpresa", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.codigoEmpresa = :codigoEmpresa"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByRuc", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.ruc = :ruc"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByUtm", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.utm = :utm"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByNombreCorto", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.nombreCorto = :nombreCorto"),
    @NamedQuery(name = "MdiEmpresaSupervisada.findByEstado", query = "SELECT m FROM MdiEmpresaSupervisada m WHERE m.estado = :estado")})
public class MdiEmpresaSupervisada extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMPRESA_SUPERVISADA")
    private Long idEmpresaSupervisada;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "NUMERO_IDENTIFICACION")
    private String numeroIdentificacion;
    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Column(name = "TELEFONO")
    private String telefono;    
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "CIIU_PRINCIPAL")
    private String ciiuPrincipal;
    @Column(name = "FLAG_BANDERA")
    private String flagBandera;
    @Column(name = "TIPO_DOCUMENTO_IDENTIDAD")
    private Long tipoDocumentoIdentidad;
    @Column(name = "NATURALEZA")
    private Character naturaleza;
    @Basic(optional = false)
    @Column(name = "ORIGEN_INFORMACION")
    private String origenInformacion;
    @Column(name = "CODIGO_EMPRESA")
    private Long codigoEmpresa;
    @Column(name = "RUC")
    private String ruc;
    @Column(name = "UTM")
    private String utm;
    @Column(name = "NOMBRE_CORTO")
    private String nombreCorto;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(mappedBy = "idEmpresaSupervisada")
    private List<MdiEmpresaContacto> mdiEmpresaContactoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresaSupervisada")
    private List<MdiDireccionEmpSupervisada> mdiDireccionEmpSupervisadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresaSupervisada")
    private List<MdiUnidadSupervisada> mdiUnidadSupervisadaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiEmpresaSupervisada")
    private List<MdiEmpresaUnidadSupervisada> mdiEmpresaUnidadSupervisadaList;

    public MdiEmpresaSupervisada() {
    }

    public MdiEmpresaSupervisada(Long idEmpresaSupervisada) {
        this.idEmpresaSupervisada = idEmpresaSupervisada;
    }

    public MdiEmpresaSupervisada(Long idEmpresaSupervisada, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String origenInformacion, char estado) {
        this.idEmpresaSupervisada = idEmpresaSupervisada;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.origenInformacion = origenInformacion;
        this.estado = estado;
    }

    public Long getIdEmpresaSupervisada() {
        return idEmpresaSupervisada;
    }

    public void setIdEmpresaSupervisada(Long idEmpresaSupervisada) {
        this.idEmpresaSupervisada = idEmpresaSupervisada;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiiuPrincipal() {
        return ciiuPrincipal;
    }

    public void setCiiuPrincipal(String ciiuPrincipal) {
        this.ciiuPrincipal = ciiuPrincipal;
    }

    public String getFlagBandera() {
        return flagBandera;
    }

    public void setFlagBandera(String flagBandera) {
        this.flagBandera = flagBandera;
    }

    public Long getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(Long tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    public Character getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(Character naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getOrigenInformacion() {
        return origenInformacion;
    }

    public void setOrigenInformacion(String origenInformacion) {
        this.origenInformacion = origenInformacion;
    }

    public Long getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Long codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getUtm() {
        return utm;
    }

    public void setUtm(String utm) {
        this.utm = utm;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiEmpresaContacto> getMdiEmpresaContactoList() {
        return mdiEmpresaContactoList;
    }

    public void setMdiEmpresaContactoList(List<MdiEmpresaContacto> mdiEmpresaContactoList) {
        this.mdiEmpresaContactoList = mdiEmpresaContactoList;
    }

    @XmlTransient
    public List<MdiDireccionEmpSupervisada> getMdiDireccionEmpSupervisadaList() {
        return mdiDireccionEmpSupervisadaList;
    }

    public void setMdiDireccionEmpSupervisadaList(List<MdiDireccionEmpSupervisada> mdiDireccionEmpSupervisadaList) {
        this.mdiDireccionEmpSupervisadaList = mdiDireccionEmpSupervisadaList;
    }

    @XmlTransient
    public List<MdiUnidadSupervisada> getMdiUnidadSupervisadaList() {
        return mdiUnidadSupervisadaList;
    }

    public void setMdiUnidadSupervisadaList(List<MdiUnidadSupervisada> mdiUnidadSupervisadaList) {
        this.mdiUnidadSupervisadaList = mdiUnidadSupervisadaList;
    }

    @XmlTransient
    public List<MdiEmpresaUnidadSupervisada> getMdiEmpresaUnidadSupervisadaList() {
        return mdiEmpresaUnidadSupervisadaList;
    }

    public void setMdiEmpresaUnidadSupervisadaList(List<MdiEmpresaUnidadSupervisada> mdiEmpresaUnidadSupervisadaList) {
        this.mdiEmpresaUnidadSupervisadaList = mdiEmpresaUnidadSupervisadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresaSupervisada != null ? idEmpresaSupervisada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiEmpresaSupervisada)) {
            return false;
        }
        MdiEmpresaSupervisada other = (MdiEmpresaSupervisada) object;
        if ((this.idEmpresaSupervisada == null && other.idEmpresaSupervisada != null) || (this.idEmpresaSupervisada != null && !this.idEmpresaSupervisada.equals(other.idEmpresaSupervisada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiEmpresaSupervisada[ idEmpresaSupervisada=" + idEmpresaSupervisada + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_EMPRESA_CONTACTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiEmpresaContacto.findAll", query = "SELECT m FROM MdiEmpresaContacto m"),
    @NamedQuery(name = "MdiEmpresaContacto.findByIdEmpresaContacto", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.idEmpresaContacto = :idEmpresaContacto"),
    @NamedQuery(name = "MdiEmpresaContacto.findByIdTipoDocumentoIdentidad", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.idTipoDocumentoIdentidad = :idTipoDocumentoIdentidad"),
    @NamedQuery(name = "MdiEmpresaContacto.findByIdTipoContacto", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.idTipoContacto = :idTipoContacto"),
    @NamedQuery(name = "MdiEmpresaContacto.findByNumeroDocIdentidad", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.numeroDocIdentidad = :numeroDocIdentidad"),
    @NamedQuery(name = "MdiEmpresaContacto.findByNombre", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiEmpresaContacto.findByApellidoPaterno", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTelefonoPersonal", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.telefonoPersonal = :telefonoPersonal"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTelefonoContacto", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.telefonoContacto = :telefonoContacto"),
    @NamedQuery(name = "MdiEmpresaContacto.findByNacionalidadContacto", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.nacionalidadContacto = :nacionalidadContacto"),
    @NamedQuery(name = "MdiEmpresaContacto.findByApellidoMaterno", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "MdiEmpresaContacto.findByCorreoElectronico", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "MdiEmpresaContacto.findByUsuarioCreacion", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiEmpresaContacto.findByFechaCreacion", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTerminalCreacion", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiEmpresaContacto.findByUsuarioActualizacion", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiEmpresaContacto.findByFechaActualizacion", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTerminalActualizacion", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiEmpresaContacto.findByCargo", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.cargo = :cargo"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTelefonoVivienda", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.telefonoVivienda = :telefonoVivienda"),
    @NamedQuery(name = "MdiEmpresaContacto.findByIdTipoDireccionEmpresa", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.idTipoDireccionEmpresa = :idTipoDireccionEmpresa"),
    @NamedQuery(name = "MdiEmpresaContacto.findByPrincipal", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.principal = :principal"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTelefono", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "MdiEmpresaContacto.findByCelular", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.celular = :celular"),
    @NamedQuery(name = "MdiEmpresaContacto.findByNacionalidad", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "MdiEmpresaContacto.findByEstado", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTipoDocumentoIdentidad", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.tipoDocumentoIdentidad = :tipoDocumentoIdentidad"),
    @NamedQuery(name = "MdiEmpresaContacto.findByTipoContacto", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.tipoContacto = :tipoContacto"),
    @NamedQuery(name = "MdiEmpresaContacto.findByIdEmpresaSupervisora", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.idEmpresaSupervisora = :idEmpresaSupervisora"),
    @NamedQuery(name = "MdiEmpresaContacto.findByOrigenInformacion", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.origenInformacion = :origenInformacion"),
    @NamedQuery(name = "MdiEmpresaContacto.findByNombreCompleto", query = "SELECT m FROM MdiEmpresaContacto m WHERE m.nombreCompleto = :nombreCompleto")})
public class MdiEmpresaContacto extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMPRESA_CONTACTO")
    private Long idEmpresaContacto;
    @Column(name = "ID_TIPO_DOCUMENTO_IDENTIDAD")
    private Long idTipoDocumentoIdentidad;
    @Column(name = "ID_TIPO_CONTACTO")
    private Long idTipoContacto;
    @Basic(optional = false)
    @Column(name = "NUMERO_DOC_IDENTIDAD")
    private String numeroDocIdentidad;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @Column(name = "TELEFONO_PERSONAL")
    private String telefonoPersonal;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;
    @Column(name = "NACIONALIDAD_CONTACTO")
    private Long nacionalidadContacto;
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;    
    @Column(name = "CARGO")
    private String cargo;
    @Column(name = "TELEFONO_VIVIENDA")
    private String telefonoVivienda;
    @Column(name = "ID_TIPO_DIRECCION_EMPRESA")
    private Long idTipoDireccionEmpresa;
    @Basic(optional = false)
    @Column(name = "PRINCIPAL")
    private char principal;
    @Column(name = "TELEFONO")
    private Long telefono;
    @Column(name = "CELULAR")
    private String celular;
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Column(name = "TIPO_DOCUMENTO_IDENTIDAD")
    private Long tipoDocumentoIdentidad;
    @Column(name = "TIPO_CONTACTO")
    private Long tipoContacto;
    @Column(name = "ID_EMPRESA_SUPERVISORA")
    private Long idEmpresaSupervisora;
    @Column(name = "ORIGEN_INFORMACION")
    private String origenInformacion;
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @JoinColumn(name = "ID_UNIDAD_SUPERVISADA", referencedColumnName = "ID_UNIDAD_SUPERVISADA")
    @ManyToOne
    private MdiUnidadSupervisada idUnidadSupervisada;
    @JoinColumn(name = "ID_SUPERVISORA_EMPRESA", referencedColumnName = "ID_SUPERVISORA_EMPRESA")
    @ManyToOne
    private MdiSupervisoraEmpresa idSupervisoraEmpresa;
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR")
    @ManyToOne
    private MdiLocador idLocador;
    @JoinColumn(name = "ID_EMPRESA_SUPERVISADA", referencedColumnName = "ID_EMPRESA_SUPERVISADA")
    @ManyToOne
    private MdiEmpresaSupervisada idEmpresaSupervisada;

    public MdiEmpresaContacto() {
    }

    public MdiEmpresaContacto(Long idEmpresaContacto) {
        this.idEmpresaContacto = idEmpresaContacto;
    }

    public MdiEmpresaContacto(Long idEmpresaContacto, String numeroDocIdentidad, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, char principal, char estado) {
        this.idEmpresaContacto = idEmpresaContacto;
        this.numeroDocIdentidad = numeroDocIdentidad;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.principal = principal;
        this.estado = estado;
    }

    public Long getIdEmpresaContacto() {
        return idEmpresaContacto;
    }

    public void setIdEmpresaContacto(Long idEmpresaContacto) {
        this.idEmpresaContacto = idEmpresaContacto;
    }

    public Long getIdTipoDocumentoIdentidad() {
        return idTipoDocumentoIdentidad;
    }

    public void setIdTipoDocumentoIdentidad(Long idTipoDocumentoIdentidad) {
        this.idTipoDocumentoIdentidad = idTipoDocumentoIdentidad;
    }

    public Long getIdTipoContacto() {
        return idTipoContacto;
    }

    public void setIdTipoContacto(Long idTipoContacto) {
        this.idTipoContacto = idTipoContacto;
    }

    public String getNumeroDocIdentidad() {
        return numeroDocIdentidad;
    }

    public void setNumeroDocIdentidad(String numeroDocIdentidad) {
        this.numeroDocIdentidad = numeroDocIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Long getNacionalidadContacto() {
        return nacionalidadContacto;
    }

    public void setNacionalidadContacto(Long nacionalidadContacto) {
        this.nacionalidadContacto = nacionalidadContacto;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefonoVivienda() {
        return telefonoVivienda;
    }

    public void setTelefonoVivienda(String telefonoVivienda) {
        this.telefonoVivienda = telefonoVivienda;
    }

    public Long getIdTipoDireccionEmpresa() {
        return idTipoDireccionEmpresa;
    }

    public void setIdTipoDireccionEmpresa(Long idTipoDireccionEmpresa) {
        this.idTipoDireccionEmpresa = idTipoDireccionEmpresa;
    }

    public char getPrincipal() {
        return principal;
    }

    public void setPrincipal(char principal) {
        this.principal = principal;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Long getTipoDocumentoIdentidad() {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(Long tipoDocumentoIdentidad) {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }

    public Long getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(Long tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public Long getIdEmpresaSupervisora() {
        return idEmpresaSupervisora;
    }

    public void setIdEmpresaSupervisora(Long idEmpresaSupervisora) {
        this.idEmpresaSupervisora = idEmpresaSupervisora;
    }

    public String getOrigenInformacion() {
        return origenInformacion;
    }

    public void setOrigenInformacion(String origenInformacion) {
        this.origenInformacion = origenInformacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public MdiUnidadSupervisada getIdUnidadSupervisada() {
        return idUnidadSupervisada;
    }

    public void setIdUnidadSupervisada(MdiUnidadSupervisada idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    public MdiSupervisoraEmpresa getIdSupervisoraEmpresa() {
        return idSupervisoraEmpresa;
    }

    public void setIdSupervisoraEmpresa(MdiSupervisoraEmpresa idSupervisoraEmpresa) {
        this.idSupervisoraEmpresa = idSupervisoraEmpresa;
    }

    public MdiLocador getIdLocador() {
        return idLocador;
    }

    public void setIdLocador(MdiLocador idLocador) {
        this.idLocador = idLocador;
    }

    public MdiEmpresaSupervisada getIdEmpresaSupervisada() {
        return idEmpresaSupervisada;
    }

    public void setIdEmpresaSupervisada(MdiEmpresaSupervisada idEmpresaSupervisada) {
        this.idEmpresaSupervisada = idEmpresaSupervisada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresaContacto != null ? idEmpresaContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiEmpresaContacto)) {
            return false;
        }
        MdiEmpresaContacto other = (MdiEmpresaContacto) object;
        if ((this.idEmpresaContacto == null && other.idEmpresaContacto != null) || (this.idEmpresaContacto != null && !this.idEmpresaContacto.equals(other.idEmpresaContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiEmpresaContacto[ idEmpresaContacto=" + idEmpresaContacto + " ]";
    }
    
}

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
@Table(name = "MDI_LOCADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiLocador.findAll", query = "SELECT m FROM MdiLocador m"),
    @NamedQuery(name = "MdiLocador.findByIdLocador", query = "SELECT m FROM MdiLocador m WHERE m.idLocador = :idLocador"),
    @NamedQuery(name = "MdiLocador.findByIdTipoDocumentoIdentidad", query = "SELECT m FROM MdiLocador m WHERE m.idTipoDocumentoIdentidad = :idTipoDocumentoIdentidad"),
    @NamedQuery(name = "MdiLocador.findByNumeroDocumento", query = "SELECT m FROM MdiLocador m WHERE m.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "MdiLocador.findByRuc", query = "SELECT m FROM MdiLocador m WHERE m.ruc = :ruc"),
    @NamedQuery(name = "MdiLocador.findByPrimerNombre", query = "SELECT m FROM MdiLocador m WHERE m.primerNombre = :primerNombre"),
    @NamedQuery(name = "MdiLocador.findBySegundoNombre", query = "SELECT m FROM MdiLocador m WHERE m.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "MdiLocador.findByApellidoPaterno", query = "SELECT m FROM MdiLocador m WHERE m.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "MdiLocador.findByApellidoMaterno", query = "SELECT m FROM MdiLocador m WHERE m.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "MdiLocador.findBySexo", query = "SELECT m FROM MdiLocador m WHERE m.sexo = :sexo"),
    @NamedQuery(name = "MdiLocador.findByFechaNacimiento", query = "SELECT m FROM MdiLocador m WHERE m.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "MdiLocador.findByTipoSangre", query = "SELECT m FROM MdiLocador m WHERE m.tipoSangre = :tipoSangre"),
    @NamedQuery(name = "MdiLocador.findByEstadoCivil", query = "SELECT m FROM MdiLocador m WHERE m.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "MdiLocador.findByHijos", query = "SELECT m FROM MdiLocador m WHERE m.hijos = :hijos"),
    @NamedQuery(name = "MdiLocador.findByNumeroHijos", query = "SELECT m FROM MdiLocador m WHERE m.numeroHijos = :numeroHijos"),
    @NamedQuery(name = "MdiLocador.findByIdNacionalidad", query = "SELECT m FROM MdiLocador m WHERE m.idNacionalidad = :idNacionalidad"),
    @NamedQuery(name = "MdiLocador.findByTelefonoContacto", query = "SELECT m FROM MdiLocador m WHERE m.telefonoContacto = :telefonoContacto"),
    @NamedQuery(name = "MdiLocador.findByIdColegioProfesional", query = "SELECT m FROM MdiLocador m WHERE m.idColegioProfesional = :idColegioProfesional"),
    @NamedQuery(name = "MdiLocador.findByNumeroColegiatura", query = "SELECT m FROM MdiLocador m WHERE m.numeroColegiatura = :numeroColegiatura"),
    @NamedQuery(name = "MdiLocador.findByTelefonoPersonal", query = "SELECT m FROM MdiLocador m WHERE m.telefonoPersonal = :telefonoPersonal"),
    @NamedQuery(name = "MdiLocador.findByFechaFinVigencia", query = "SELECT m FROM MdiLocador m WHERE m.fechaFinVigencia = :fechaFinVigencia"),
    @NamedQuery(name = "MdiLocador.findByAlergiaLocador", query = "SELECT m FROM MdiLocador m WHERE m.alergiaLocador = :alergiaLocador"),
    @NamedQuery(name = "MdiLocador.findByFechaInicioVigencia", query = "SELECT m FROM MdiLocador m WHERE m.fechaInicioVigencia = :fechaInicioVigencia"),
    @NamedQuery(name = "MdiLocador.findByRestriccionMedica", query = "SELECT m FROM MdiLocador m WHERE m.restriccionMedica = :restriccionMedica"),
    @NamedQuery(name = "MdiLocador.findByCorreoElectronicoPersonal", query = "SELECT m FROM MdiLocador m WHERE m.correoElectronicoPersonal = :correoElectronicoPersonal"),
    @NamedQuery(name = "MdiLocador.findByCorreoElectronicoLaboral", query = "SELECT m FROM MdiLocador m WHERE m.correoElectronicoLaboral = :correoElectronicoLaboral"),
    @NamedQuery(name = "MdiLocador.findByIdProfesion", query = "SELECT m FROM MdiLocador m WHERE m.idProfesion = :idProfesion"),
    @NamedQuery(name = "MdiLocador.findByObservaciones", query = "SELECT m FROM MdiLocador m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MdiLocador.findByEstado", query = "SELECT m FROM MdiLocador m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiLocador.findByUsuarioCreacion", query = "SELECT m FROM MdiLocador m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiLocador.findByFechaCreacion", query = "SELECT m FROM MdiLocador m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiLocador.findByTerminalCreacion", query = "SELECT m FROM MdiLocador m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiLocador.findByUsuarioActualizacion", query = "SELECT m FROM MdiLocador m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiLocador.findByFechaActualizacion", query = "SELECT m FROM MdiLocador m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiLocador.findByTerminalActualizacion", query = "SELECT m FROM MdiLocador m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiLocador.findByOrigenInformacion", query = "SELECT m FROM MdiLocador m WHERE m.origenInformacion = :origenInformacion"),
    @NamedQuery(name = "MdiLocador.findByNombreCompleto", query = "SELECT m FROM MdiLocador m WHERE m.nombreCompleto = :nombreCompleto")})
public class MdiLocador extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LOCADOR")
    private Long idLocador;
    @Basic(optional = false)
    @Column(name = "ID_TIPO_DOCUMENTO_IDENTIDAD")
    private long idTipoDocumentoIdentidad;
    @Basic(optional = false)
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    @Column(name = "RUC")
    private String ruc;
    @Basic(optional = false)
    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;
    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;
    @Basic(optional = false)
    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;
    @Column(name = "SEXO")
    private Long sexo;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "TIPO_SANGRE")
    private Long tipoSangre;
    @Column(name = "ESTADO_CIVIL")
    private Long estadoCivil;
    @Column(name = "HIJOS")
    private String hijos;
    @Column(name = "NUMERO_HIJOS")
    private Short numeroHijos;
    @Column(name = "ID_NACIONALIDAD")
    private Long idNacionalidad;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;
    @Column(name = "ID_COLEGIO_PROFESIONAL")
    private Long idColegioProfesional;
    @Column(name = "NUMERO_COLEGIATURA")
    private String numeroColegiatura;
    @Column(name = "TELEFONO_PERSONAL")
    private String telefonoPersonal;
    @Column(name = "FECHA_FIN_VIGENCIA")
    @Temporal(TemporalType.DATE)
    private Date fechaFinVigencia;
    @Column(name = "ALERGIA_LOCADOR")
    private String alergiaLocador;
    @Column(name = "FECHA_INICIO_VIGENCIA")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioVigencia;
    @Column(name = "RESTRICCION_MEDICA")
    private String restriccionMedica;
    @Column(name = "CORREO_ELECTRONICO_PERSONAL")
    private String correoElectronicoPersonal;
    @Column(name = "CORREO_ELECTRONICO_LABORAL")
    private String correoElectronicoLaboral;
    @Column(name = "ID_PROFESION")
    private Long idProfesion;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @Column(name = "ORIGEN_INFORMACION")
    private String origenInformacion;
    @Column(name = "NOMBRE_COMPLETO")
    private String nombreCompleto;
    @OneToMany(mappedBy = "idLocador")
    private List<MdiEmpresaContacto> mdiEmpresaContactoList;
    @OneToMany(mappedBy = "idLocador")
    private List<MdiSedeEmpresaLocador> mdiSedeEmpresaLocadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLocador")
    private List<MdiInstAcadLocador> mdiInstAcadLocadorList;
    @OneToMany(mappedBy = "idLocador")
    private List<MdiDocumentoAdjunto> mdiDocumentoAdjuntoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiLocador")
    private List<MdiLocadorXEmpresa> mdiLocadorXEmpresaList;
    @OneToMany(mappedBy = "idLocador")
    private List<MdiPoliza> mdiPolizaList;
    @OneToMany(mappedBy = "idLocador")
    private List<MdiContratoEmpresaLocador> mdiContratoEmpresaLocadorList;
    @OneToMany(mappedBy = "idLocador")
    private List<MdiEmpresaLocaXUnidadOrg> mdiEmpresaLocaXUnidadOrgList;

    public MdiLocador() {
    }

    public MdiLocador(Long idLocador) {
        this.idLocador = idLocador;
    }

    public MdiLocador(Long idLocador, long idTipoDocumentoIdentidad, String numeroDocumento, String primerNombre, String apellidoPaterno, String apellidoMaterno, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idLocador = idLocador;
        this.idTipoDocumentoIdentidad = idTipoDocumentoIdentidad;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdLocador() {
        return idLocador;
    }

    public void setIdLocador(Long idLocador) {
        this.idLocador = idLocador;
    }

    public long getIdTipoDocumentoIdentidad() {
        return idTipoDocumentoIdentidad;
    }

    public void setIdTipoDocumentoIdentidad(long idTipoDocumentoIdentidad) {
        this.idTipoDocumentoIdentidad = idTipoDocumentoIdentidad;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
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

    public Long getSexo() {
        return sexo;
    }

    public void setSexo(Long sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(Long tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public Long getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Long estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getHijos() {
        return hijos;
    }

    public void setHijos(String hijos) {
        this.hijos = hijos;
    }

    public Short getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(Short numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    public Long getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Long idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Long getIdColegioProfesional() {
        return idColegioProfesional;
    }

    public void setIdColegioProfesional(Long idColegioProfesional) {
        this.idColegioProfesional = idColegioProfesional;
    }

    public String getNumeroColegiatura() {
        return numeroColegiatura;
    }

    public void setNumeroColegiatura(String numeroColegiatura) {
        this.numeroColegiatura = numeroColegiatura;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public Date getFechaFinVigencia() {
        return fechaFinVigencia;
    }

    public void setFechaFinVigencia(Date fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public String getAlergiaLocador() {
        return alergiaLocador;
    }

    public void setAlergiaLocador(String alergiaLocador) {
        this.alergiaLocador = alergiaLocador;
    }

    public Date getFechaInicioVigencia() {
        return fechaInicioVigencia;
    }

    public void setFechaInicioVigencia(Date fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    public String getRestriccionMedica() {
        return restriccionMedica;
    }

    public void setRestriccionMedica(String restriccionMedica) {
        this.restriccionMedica = restriccionMedica;
    }

    public String getCorreoElectronicoPersonal() {
        return correoElectronicoPersonal;
    }

    public void setCorreoElectronicoPersonal(String correoElectronicoPersonal) {
        this.correoElectronicoPersonal = correoElectronicoPersonal;
    }

    public String getCorreoElectronicoLaboral() {
        return correoElectronicoLaboral;
    }

    public void setCorreoElectronicoLaboral(String correoElectronicoLaboral) {
        this.correoElectronicoLaboral = correoElectronicoLaboral;
    }

    public Long getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(Long idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
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

    @XmlTransient
    public List<MdiEmpresaContacto> getMdiEmpresaContactoList() {
        return mdiEmpresaContactoList;
    }

    public void setMdiEmpresaContactoList(List<MdiEmpresaContacto> mdiEmpresaContactoList) {
        this.mdiEmpresaContactoList = mdiEmpresaContactoList;
    }

    @XmlTransient
    public List<MdiSedeEmpresaLocador> getMdiSedeEmpresaLocadorList() {
        return mdiSedeEmpresaLocadorList;
    }

    public void setMdiSedeEmpresaLocadorList(List<MdiSedeEmpresaLocador> mdiSedeEmpresaLocadorList) {
        this.mdiSedeEmpresaLocadorList = mdiSedeEmpresaLocadorList;
    }

    @XmlTransient
    public List<MdiInstAcadLocador> getMdiInstAcadLocadorList() {
        return mdiInstAcadLocadorList;
    }

    public void setMdiInstAcadLocadorList(List<MdiInstAcadLocador> mdiInstAcadLocadorList) {
        this.mdiInstAcadLocadorList = mdiInstAcadLocadorList;
    }

    @XmlTransient
    public List<MdiDocumentoAdjunto> getMdiDocumentoAdjuntoList() {
        return mdiDocumentoAdjuntoList;
    }

    public void setMdiDocumentoAdjuntoList(List<MdiDocumentoAdjunto> mdiDocumentoAdjuntoList) {
        this.mdiDocumentoAdjuntoList = mdiDocumentoAdjuntoList;
    }

    @XmlTransient
    public List<MdiLocadorXEmpresa> getMdiLocadorXEmpresaList() {
        return mdiLocadorXEmpresaList;
    }

    public void setMdiLocadorXEmpresaList(List<MdiLocadorXEmpresa> mdiLocadorXEmpresaList) {
        this.mdiLocadorXEmpresaList = mdiLocadorXEmpresaList;
    }

    @XmlTransient
    public List<MdiPoliza> getMdiPolizaList() {
        return mdiPolizaList;
    }

    public void setMdiPolizaList(List<MdiPoliza> mdiPolizaList) {
        this.mdiPolizaList = mdiPolizaList;
    }

    @XmlTransient
    public List<MdiContratoEmpresaLocador> getMdiContratoEmpresaLocadorList() {
        return mdiContratoEmpresaLocadorList;
    }

    public void setMdiContratoEmpresaLocadorList(List<MdiContratoEmpresaLocador> mdiContratoEmpresaLocadorList) {
        this.mdiContratoEmpresaLocadorList = mdiContratoEmpresaLocadorList;
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
        hash += (idLocador != null ? idLocador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiLocador)) {
            return false;
        }
        MdiLocador other = (MdiLocador) object;
        if ((this.idLocador == null && other.idLocador != null) || (this.idLocador != null && !this.idLocador.equals(other.idLocador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiLocador[ idLocador=" + idLocador + " ]";
    }
    
}

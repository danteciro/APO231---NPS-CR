/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_POLIZA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiPoliza.findAll", query = "SELECT m FROM MdiPoliza m"),
    @NamedQuery(name = "MdiPoliza.findByIdPoliza", query = "SELECT m FROM MdiPoliza m WHERE m.idPoliza = :idPoliza"),
    @NamedQuery(name = "MdiPoliza.findByMoneda", query = "SELECT m FROM MdiPoliza m WHERE m.moneda = :moneda"),
    @NamedQuery(name = "MdiPoliza.findByIdCompaniaSeguro", query = "SELECT m FROM MdiPoliza m WHERE m.idCompaniaSeguro = :idCompaniaSeguro"),
    @NamedQuery(name = "MdiPoliza.findBySegundoNombreBroker", query = "SELECT m FROM MdiPoliza m WHERE m.segundoNombreBroker = :segundoNombreBroker"),
    @NamedQuery(name = "MdiPoliza.findByTipoPoliza", query = "SELECT m FROM MdiPoliza m WHERE m.tipoPoliza = :tipoPoliza"),
    @NamedQuery(name = "MdiPoliza.findByApellidoPaternoBroker", query = "SELECT m FROM MdiPoliza m WHERE m.apellidoPaternoBroker = :apellidoPaternoBroker"),
    @NamedQuery(name = "MdiPoliza.findByNumeroPoliza", query = "SELECT m FROM MdiPoliza m WHERE m.numeroPoliza = :numeroPoliza"),
    @NamedQuery(name = "MdiPoliza.findByApellidoMaternoBroker", query = "SELECT m FROM MdiPoliza m WHERE m.apellidoMaternoBroker = :apellidoMaternoBroker"),
    @NamedQuery(name = "MdiPoliza.findByMontoCobertura", query = "SELECT m FROM MdiPoliza m WHERE m.montoCobertura = :montoCobertura"),
    @NamedQuery(name = "MdiPoliza.findByPrimerNombreBroker", query = "SELECT m FROM MdiPoliza m WHERE m.primerNombreBroker = :primerNombreBroker"),
    @NamedQuery(name = "MdiPoliza.findByFechaInicio", query = "SELECT m FROM MdiPoliza m WHERE m.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "MdiPoliza.findByFechaFin", query = "SELECT m FROM MdiPoliza m WHERE m.fechaFin = :fechaFin"),
    @NamedQuery(name = "MdiPoliza.findByTelefonoContacto", query = "SELECT m FROM MdiPoliza m WHERE m.telefonoContacto = :telefonoContacto"),
    @NamedQuery(name = "MdiPoliza.findByTelefonoPersonal", query = "SELECT m FROM MdiPoliza m WHERE m.telefonoPersonal = :telefonoPersonal"),
    @NamedQuery(name = "MdiPoliza.findByTelefonoEmergencia", query = "SELECT m FROM MdiPoliza m WHERE m.telefonoEmergencia = :telefonoEmergencia"),
    @NamedQuery(name = "MdiPoliza.findByCorreoElectronico", query = "SELECT m FROM MdiPoliza m WHERE m.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "MdiPoliza.findByUsuarioCreacion", query = "SELECT m FROM MdiPoliza m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiPoliza.findByFechaCreacion", query = "SELECT m FROM MdiPoliza m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiPoliza.findByTerminalCreacion", query = "SELECT m FROM MdiPoliza m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiPoliza.findByUsuarioActualizacion", query = "SELECT m FROM MdiPoliza m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiPoliza.findByFechaActualizacion", query = "SELECT m FROM MdiPoliza m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiPoliza.findByTerminalActualizacion", query = "SELECT m FROM MdiPoliza m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiPoliza.findByValorUit", query = "SELECT m FROM MdiPoliza m WHERE m.valorUit = :valorUit"),
    @NamedQuery(name = "MdiPoliza.findByObservaciones", query = "SELECT m FROM MdiPoliza m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MdiPoliza.findByEstado", query = "SELECT m FROM MdiPoliza m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiPoliza.findByEstadoProceso", query = "SELECT m FROM MdiPoliza m WHERE m.estadoProceso = :estadoProceso")})
public class MdiPoliza extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_POLIZA")
    private Long idPoliza;
    @Column(name = "MONEDA")
    private Long moneda;
    @Column(name = "ID_COMPANIA_SEGURO")
    private Long idCompaniaSeguro;
    @Column(name = "SEGUNDO_NOMBRE_BROKER")
    private String segundoNombreBroker;
    @Basic(optional = false)
    @Column(name = "TIPO_POLIZA")
    private long tipoPoliza;
    @Column(name = "APELLIDO_PATERNO_BROKER")
    private String apellidoPaternoBroker;
    @Column(name = "NUMERO_POLIZA")
    private String numeroPoliza;
    @Column(name = "APELLIDO_MATERNO_BROKER")
    private String apellidoMaternoBroker;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_COBERTURA")
    private BigDecimal montoCobertura;
    @Column(name = "PRIMER_NOMBRE_BROKER")
    private String primerNombreBroker;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "TELEFONO_CONTACTO")
    private String telefonoContacto;
    @Column(name = "TELEFONO_PERSONAL")
    private String telefonoPersonal;
    @Column(name = "TELEFONO_EMERGENCIA")
    private String telefonoEmergencia;
    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;    
    @Column(name = "VALOR_UIT")
    private Long valorUit;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Column(name = "ESTADO_PROCESO")
    private Long estadoProceso;
    @JoinColumn(name = "ID_UNIDAD_SUPERVISADA", referencedColumnName = "ID_UNIDAD_SUPERVISADA")
    @ManyToOne
    private MdiUnidadSupervisada idUnidadSupervisada;
    @JoinColumn(name = "ID_SUPERVISORA_EMPRESA", referencedColumnName = "ID_SUPERVISORA_EMPRESA")
    @ManyToOne
    private MdiSupervisoraEmpresa idSupervisoraEmpresa;
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR")
    @ManyToOne
    private MdiLocador idLocador;

    public MdiPoliza() {
    }

    public MdiPoliza(Long idPoliza) {
        this.idPoliza = idPoliza;
    }

    public MdiPoliza(Long idPoliza, long tipoPoliza, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, char estado) {
        this.idPoliza = idPoliza;
        this.tipoPoliza = tipoPoliza;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdPoliza() {
        return idPoliza;
    }

    public void setIdPoliza(Long idPoliza) {
        this.idPoliza = idPoliza;
    }

    public Long getMoneda() {
        return moneda;
    }

    public void setMoneda(Long moneda) {
        this.moneda = moneda;
    }

    public Long getIdCompaniaSeguro() {
        return idCompaniaSeguro;
    }

    public void setIdCompaniaSeguro(Long idCompaniaSeguro) {
        this.idCompaniaSeguro = idCompaniaSeguro;
    }

    public String getSegundoNombreBroker() {
        return segundoNombreBroker;
    }

    public void setSegundoNombreBroker(String segundoNombreBroker) {
        this.segundoNombreBroker = segundoNombreBroker;
    }

    public long getTipoPoliza() {
        return tipoPoliza;
    }

    public void setTipoPoliza(long tipoPoliza) {
        this.tipoPoliza = tipoPoliza;
    }

    public String getApellidoPaternoBroker() {
        return apellidoPaternoBroker;
    }

    public void setApellidoPaternoBroker(String apellidoPaternoBroker) {
        this.apellidoPaternoBroker = apellidoPaternoBroker;
    }

    public String getNumeroPoliza() {
        return numeroPoliza;
    }

    public void setNumeroPoliza(String numeroPoliza) {
        this.numeroPoliza = numeroPoliza;
    }

    public String getApellidoMaternoBroker() {
        return apellidoMaternoBroker;
    }

    public void setApellidoMaternoBroker(String apellidoMaternoBroker) {
        this.apellidoMaternoBroker = apellidoMaternoBroker;
    }

    public BigDecimal getMontoCobertura() {
        return montoCobertura;
    }

    public void setMontoCobertura(BigDecimal montoCobertura) {
        this.montoCobertura = montoCobertura;
    }

    public String getPrimerNombreBroker() {
        return primerNombreBroker;
    }

    public void setPrimerNombreBroker(String primerNombreBroker) {
        this.primerNombreBroker = primerNombreBroker;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getTelefonoPersonal() {
        return telefonoPersonal;
    }

    public void setTelefonoPersonal(String telefonoPersonal) {
        this.telefonoPersonal = telefonoPersonal;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Long getValorUit() {
        return valorUit;
    }

    public void setValorUit(Long valorUit) {
        this.valorUit = valorUit;
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

    public Long getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(Long estadoProceso) {
        this.estadoProceso = estadoProceso;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoliza != null ? idPoliza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiPoliza)) {
            return false;
        }
        MdiPoliza other = (MdiPoliza) object;
        if ((this.idPoliza == null && other.idPoliza != null) || (this.idPoliza != null && !this.idPoliza.equals(other.idPoliza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiPoliza[ idPoliza=" + idPoliza + " ]";
    }
    
}

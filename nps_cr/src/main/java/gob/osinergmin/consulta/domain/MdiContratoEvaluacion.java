/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "MDI_CONTRATO_EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiContratoEvaluacion.findAll", query = "SELECT m FROM MdiContratoEvaluacion m"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByIdContratoEvaluacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.idContratoEvaluacion = :idContratoEvaluacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByFechaEvaluacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.fechaEvaluacion = :fechaEvaluacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByMotivoEvaluacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.motivoEvaluacion = :motivoEvaluacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByNombreEvaluador", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.nombreEvaluador = :nombreEvaluador"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByEntidadEvaluador", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.entidadEvaluador = :entidadEvaluador"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByIdDivisionEvaluador", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.idDivisionEvaluador = :idDivisionEvaluador"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByPuntajeTotal", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.puntajeTotal = :puntajeTotal"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByIdUnidadEvaluador", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.idUnidadEvaluador = :idUnidadEvaluador"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByEstado", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByUsuarioCreacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByFechaCreacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByTerminalCreacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByUsuarioActualizacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByFechaActualizacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByTerminalActualizacion", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByCargoEvaluador", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.cargoEvaluador = :cargoEvaluador"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByPeriodoInicio", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.periodoInicio = :periodoInicio"),
    @NamedQuery(name = "MdiContratoEvaluacion.findByPeriodoFin", query = "SELECT m FROM MdiContratoEvaluacion m WHERE m.periodoFin = :periodoFin")})
public class MdiContratoEvaluacion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONTRATO_EVALUACION")
    private Long idContratoEvaluacion;
    @Column(name = "FECHA_EVALUACION")
    @Temporal(TemporalType.DATE)
    private Date fechaEvaluacion;
    @Column(name = "MOTIVO_EVALUACION")
    private Long motivoEvaluacion;
    @Column(name = "NOMBRE_EVALUADOR")
    private String nombreEvaluador;
    @Column(name = "ENTIDAD_EVALUADOR")
    private Long entidadEvaluador;
    @Column(name = "ID_DIVISION_EVALUADOR")
    private Long idDivisionEvaluador;
    @Column(name = "PUNTAJE_TOTAL")
    private BigInteger puntajeTotal;
    @Column(name = "ID_UNIDAD_EVALUADOR")
    private Long idUnidadEvaluador;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @Basic(optional = false)
    @Column(name = "CARGO_EVALUADOR")
    private String cargoEvaluador;
    @Basic(optional = false)
    @Column(name = "PERIODO_INICIO")
    private String periodoInicio;
    @Basic(optional = false)
    @Column(name = "PERIODO_FIN")
    private String periodoFin;
    @JoinColumns({
        @JoinColumn(name = "ID_MACRO_REGION", referencedColumnName = "ID_MACRO_REGION"),
        @JoinColumn(name = "ID_REGION", referencedColumnName = "ID_REGION")})
    @ManyToOne
    private MdiMacroRegionXRegion mdiMacroRegionXRegion;
    @JoinColumn(name = "ID_CONTRATO_EMPRESA_LOCADOR", referencedColumnName = "ID_CONTRATO_EMPRESA_LOCADOR")
    @ManyToOne(optional = false)
    private MdiContratoEmpresaLocador idContratoEmpresaLocador;
    @OneToMany(mappedBy = "idContratoEvaluacion")
    private List<MdiContratoDocumentoAdjunto> mdiContratoDocumentoAdjuntoList;

    public MdiContratoEvaluacion() {
    }

    public MdiContratoEvaluacion(Long idContratoEvaluacion) {
        this.idContratoEvaluacion = idContratoEvaluacion;
    }

    public MdiContratoEvaluacion(Long idContratoEvaluacion, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String cargoEvaluador, String periodoInicio, String periodoFin) {
        this.idContratoEvaluacion = idContratoEvaluacion;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.cargoEvaluador = cargoEvaluador;
        this.periodoInicio = periodoInicio;
        this.periodoFin = periodoFin;
    }

    public Long getIdContratoEvaluacion() {
        return idContratoEvaluacion;
    }

    public void setIdContratoEvaluacion(Long idContratoEvaluacion) {
        this.idContratoEvaluacion = idContratoEvaluacion;
    }

    public Date getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(Date fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Long getMotivoEvaluacion() {
        return motivoEvaluacion;
    }

    public void setMotivoEvaluacion(Long motivoEvaluacion) {
        this.motivoEvaluacion = motivoEvaluacion;
    }

    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }

    public Long getEntidadEvaluador() {
        return entidadEvaluador;
    }

    public void setEntidadEvaluador(Long entidadEvaluador) {
        this.entidadEvaluador = entidadEvaluador;
    }

    public Long getIdDivisionEvaluador() {
        return idDivisionEvaluador;
    }

    public void setIdDivisionEvaluador(Long idDivisionEvaluador) {
        this.idDivisionEvaluador = idDivisionEvaluador;
    }

    public BigInteger getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(BigInteger puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }

    public Long getIdUnidadEvaluador() {
        return idUnidadEvaluador;
    }

    public void setIdUnidadEvaluador(Long idUnidadEvaluador) {
        this.idUnidadEvaluador = idUnidadEvaluador;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getCargoEvaluador() {
        return cargoEvaluador;
    }

    public void setCargoEvaluador(String cargoEvaluador) {
        this.cargoEvaluador = cargoEvaluador;
    }

    public String getPeriodoInicio() {
        return periodoInicio;
    }

    public void setPeriodoInicio(String periodoInicio) {
        this.periodoInicio = periodoInicio;
    }

    public String getPeriodoFin() {
        return periodoFin;
    }

    public void setPeriodoFin(String periodoFin) {
        this.periodoFin = periodoFin;
    }

    public MdiMacroRegionXRegion getMdiMacroRegionXRegion() {
        return mdiMacroRegionXRegion;
    }

    public void setMdiMacroRegionXRegion(MdiMacroRegionXRegion mdiMacroRegionXRegion) {
        this.mdiMacroRegionXRegion = mdiMacroRegionXRegion;
    }

    public MdiContratoEmpresaLocador getIdContratoEmpresaLocador() {
        return idContratoEmpresaLocador;
    }

    public void setIdContratoEmpresaLocador(MdiContratoEmpresaLocador idContratoEmpresaLocador) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
    }

    @XmlTransient
    public List<MdiContratoDocumentoAdjunto> getMdiContratoDocumentoAdjuntoList() {
        return mdiContratoDocumentoAdjuntoList;
    }

    public void setMdiContratoDocumentoAdjuntoList(List<MdiContratoDocumentoAdjunto> mdiContratoDocumentoAdjuntoList) {
        this.mdiContratoDocumentoAdjuntoList = mdiContratoDocumentoAdjuntoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoEvaluacion != null ? idContratoEvaluacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiContratoEvaluacion)) {
            return false;
        }
        MdiContratoEvaluacion other = (MdiContratoEvaluacion) object;
        if ((this.idContratoEvaluacion == null && other.idContratoEvaluacion != null) || (this.idContratoEvaluacion != null && !this.idContratoEvaluacion.equals(other.idContratoEvaluacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiContratoEvaluacion[ idContratoEvaluacion=" + idContratoEvaluacion + " ]";
    }
    
}

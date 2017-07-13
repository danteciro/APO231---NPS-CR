/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_CONTRATO_EMPRESA_LOCADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiContratoEmpresaLocador.findAll", query = "SELECT m FROM MdiContratoEmpresaLocador m"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByIdContratoEmpresaLocador", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.idContratoEmpresaLocador = :idContratoEmpresaLocador"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByNumeroContrato", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.numeroContrato = :numeroContrato"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByFechaInicioContrato", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.fechaInicioContrato = :fechaInicioContrato"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByFechaFinContrato", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.fechaFinContrato = :fechaFinContrato"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByHonorarioTotal", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.honorarioTotal = :honorarioTotal"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByMotivoConclusion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.motivoConclusion = :motivoConclusion"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByNumeroCartaNotarial", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.numeroCartaNotarial = :numeroCartaNotarial"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByFechaNotificacion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.fechaNotificacion = :fechaNotificacion"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByObservaciones", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByFechaEfectivo", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.fechaEfectivo = :fechaEfectivo"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByEstadoProceso", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByEstado", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByUsuarioCreacion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByFechaCreacion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByTerminalCreacion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByUsuarioActualizacion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByFechaActualizacion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByCategoriaLocador", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.categoriaLocador = :categoriaLocador"),
    @NamedQuery(name = "MdiContratoEmpresaLocador.findByTerminalActualizacion", query = "SELECT m FROM MdiContratoEmpresaLocador m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiContratoEmpresaLocador extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONTRATO_EMPRESA_LOCADOR")
    private Long idContratoEmpresaLocador;
    @Basic(optional = false)
    @Column(name = "NUMERO_CONTRATO")
    private String numeroContrato;
    @Column(name = "FECHA_INICIO_CONTRATO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioContrato;
    @Column(name = "FECHA_FIN_CONTRATO")
    @Temporal(TemporalType.DATE)
    private Date fechaFinContrato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HONORARIO_TOTAL")
    private BigDecimal honorarioTotal;
    @Column(name = "MOTIVO_CONCLUSION")
    private Long motivoConclusion;
    @Column(name = "NUMERO_CARTA_NOTARIAL")
    private String numeroCartaNotarial;
    @Column(name = "FECHA_NOTIFICACION")
    @Temporal(TemporalType.DATE)
    private Date fechaNotificacion;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "FECHA_EFECTIVO")
    @Temporal(TemporalType.DATE)
    private Date fechaEfectivo;
    @Basic(optional = false)
    @Column(name = "ESTADO_PROCESO")
    private long estadoProceso;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @Column(name = "CATEGORIA_LOCADOR")
    private Long categoriaLocador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratoEmpresaLocador")
    private List<MdiContratoEvaluacion> mdiContratoEvaluacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratoEmpresaLocador")
    private List<MdiLocadorDestaque> mdiLocadorDestaqueList;
    @OneToMany(mappedBy = "idContratoEmpresaLocador")
    private List<MdiContratoDocumentoAdjunto> mdiContratoDocumentoAdjuntoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiContratoEmpresaLocador")
    private List<MdiLocadorXEmpresa> mdiLocadorXEmpresaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratoEmpresaLocador")
    private List<MdiAdendaEmpresaLocador> mdiAdendaEmpresaLocadorList;
    @JoinColumn(name = "ID_SUPERVISORA_EMPRESA", referencedColumnName = "ID_SUPERVISORA_EMPRESA")
    @ManyToOne
    private MdiSupervisoraEmpresa idSupervisoraEmpresa;
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR")
    @ManyToOne
    private MdiLocador idLocador;
    @JoinColumn(name = "ID_CONCURSO", referencedColumnName = "ID_CONCURSO")
    @ManyToOne
    private MdiConcurso idConcurso;

    public MdiContratoEmpresaLocador() {
    }

    public MdiContratoEmpresaLocador(Long idContratoEmpresaLocador) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
    }

    public MdiContratoEmpresaLocador(Long idContratoEmpresaLocador, String numeroContrato, long estadoProceso, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
        this.numeroContrato = numeroContrato;
        this.estadoProceso = estadoProceso;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdContratoEmpresaLocador() {
        return idContratoEmpresaLocador;
    }

    public void setIdContratoEmpresaLocador(Long idContratoEmpresaLocador) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public BigDecimal getHonorarioTotal() {
        return honorarioTotal;
    }

    public void setHonorarioTotal(BigDecimal honorarioTotal) {
        this.honorarioTotal = honorarioTotal;
    }

    public Long getMotivoConclusion() {
        return motivoConclusion;
    }

    public void setMotivoConclusion(Long motivoConclusion) {
        this.motivoConclusion = motivoConclusion;
    }

    public String getNumeroCartaNotarial() {
        return numeroCartaNotarial;
    }

    public void setNumeroCartaNotarial(String numeroCartaNotarial) {
        this.numeroCartaNotarial = numeroCartaNotarial;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFechaEfectivo() {
        return fechaEfectivo;
    }

    public void setFechaEfectivo(Date fechaEfectivo) {
        this.fechaEfectivo = fechaEfectivo;
    }

    public long getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(long estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Long getCategoriaLocador() {
        return categoriaLocador;
    }

    public void setCategoriaLocador(Long categoriaLocador) {
        this.categoriaLocador = categoriaLocador;
    }

    @XmlTransient
    public List<MdiContratoEvaluacion> getMdiContratoEvaluacionList() {
        return mdiContratoEvaluacionList;
    }

    public void setMdiContratoEvaluacionList(List<MdiContratoEvaluacion> mdiContratoEvaluacionList) {
        this.mdiContratoEvaluacionList = mdiContratoEvaluacionList;
    }

    @XmlTransient
    public List<MdiLocadorDestaque> getMdiLocadorDestaqueList() {
        return mdiLocadorDestaqueList;
    }

    public void setMdiLocadorDestaqueList(List<MdiLocadorDestaque> mdiLocadorDestaqueList) {
        this.mdiLocadorDestaqueList = mdiLocadorDestaqueList;
    }

    @XmlTransient
    public List<MdiContratoDocumentoAdjunto> getMdiContratoDocumentoAdjuntoList() {
        return mdiContratoDocumentoAdjuntoList;
    }

    public void setMdiContratoDocumentoAdjuntoList(List<MdiContratoDocumentoAdjunto> mdiContratoDocumentoAdjuntoList) {
        this.mdiContratoDocumentoAdjuntoList = mdiContratoDocumentoAdjuntoList;
    }

    @XmlTransient
    public List<MdiLocadorXEmpresa> getMdiLocadorXEmpresaList() {
        return mdiLocadorXEmpresaList;
    }

    public void setMdiLocadorXEmpresaList(List<MdiLocadorXEmpresa> mdiLocadorXEmpresaList) {
        this.mdiLocadorXEmpresaList = mdiLocadorXEmpresaList;
    }

    @XmlTransient
    public List<MdiAdendaEmpresaLocador> getMdiAdendaEmpresaLocadorList() {
        return mdiAdendaEmpresaLocadorList;
    }

    public void setMdiAdendaEmpresaLocadorList(List<MdiAdendaEmpresaLocador> mdiAdendaEmpresaLocadorList) {
        this.mdiAdendaEmpresaLocadorList = mdiAdendaEmpresaLocadorList;
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

    public MdiConcurso getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(MdiConcurso idConcurso) {
        this.idConcurso = idConcurso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoEmpresaLocador != null ? idContratoEmpresaLocador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiContratoEmpresaLocador)) {
            return false;
        }
        MdiContratoEmpresaLocador other = (MdiContratoEmpresaLocador) object;
        if ((this.idContratoEmpresaLocador == null && other.idContratoEmpresaLocador != null) || (this.idContratoEmpresaLocador != null && !this.idContratoEmpresaLocador.equals(other.idContratoEmpresaLocador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiContratoEmpresaLocador[ idContratoEmpresaLocador=" + idContratoEmpresaLocador + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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
@Table(name = "MDI_ADENDA_EMPRESA_LOCADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findAll", query = "SELECT m FROM MdiAdendaEmpresaLocador m"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByIdAdendaEmpresaLocador", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.idAdendaEmpresaLocador = :idAdendaEmpresaLocador"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByTipoAdenda", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.tipoAdenda = :tipoAdenda"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByFechaFirmaAdenda", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.fechaFirmaAdenda = :fechaFirmaAdenda"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByFechaInicioAdenda", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.fechaInicioAdenda = :fechaInicioAdenda"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByFechaFinAdenda", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.fechaFinAdenda = :fechaFinAdenda"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByHonorarioTotal", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.honorarioTotal = :honorarioTotal"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByObservaciones", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByEstado", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByUsuarioCreacion", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByFechaCreacion", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByTerminalCreacion", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByUsuarioActualizacion", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByFechaActualizacion", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiAdendaEmpresaLocador.findByTerminalActualizacion", query = "SELECT m FROM MdiAdendaEmpresaLocador m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiAdendaEmpresaLocador extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ADENDA_EMPRESA_LOCADOR")
    private Long idAdendaEmpresaLocador;
    @Column(name = "TIPO_ADENDA")
    private Long tipoAdenda;
    @Column(name = "FECHA_FIRMA_ADENDA")
    @Temporal(TemporalType.DATE)
    private Date fechaFirmaAdenda;
    @Column(name = "FECHA_INICIO_ADENDA")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioAdenda;
    @Column(name = "FECHA_FIN_ADENDA")
    @Temporal(TemporalType.DATE)
    private Date fechaFinAdenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HONORARIO_TOTAL")
    private BigDecimal honorarioTotal;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;   
    @OneToMany(mappedBy = "idAdendaEmpresaLocador")
    private List<MdiContratoDocumentoAdjunto> mdiContratoDocumentoAdjuntoList;
    @JoinColumn(name = "ID_CONTRATO_EMPRESA_LOCADOR", referencedColumnName = "ID_CONTRATO_EMPRESA_LOCADOR")
    @ManyToOne(optional = false)
    private MdiContratoEmpresaLocador idContratoEmpresaLocador;

    public MdiAdendaEmpresaLocador() {
    }

    public MdiAdendaEmpresaLocador(Long idAdendaEmpresaLocador) {
        this.idAdendaEmpresaLocador = idAdendaEmpresaLocador;
    }

    public MdiAdendaEmpresaLocador(Long idAdendaEmpresaLocador, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idAdendaEmpresaLocador = idAdendaEmpresaLocador;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdAdendaEmpresaLocador() {
        return idAdendaEmpresaLocador;
    }

    public void setIdAdendaEmpresaLocador(Long idAdendaEmpresaLocador) {
        this.idAdendaEmpresaLocador = idAdendaEmpresaLocador;
    }

    public Long getTipoAdenda() {
        return tipoAdenda;
    }

    public void setTipoAdenda(Long tipoAdenda) {
        this.tipoAdenda = tipoAdenda;
    }

    public Date getFechaFirmaAdenda() {
        return fechaFirmaAdenda;
    }

    public void setFechaFirmaAdenda(Date fechaFirmaAdenda) {
        this.fechaFirmaAdenda = fechaFirmaAdenda;
    }

    public Date getFechaInicioAdenda() {
        return fechaInicioAdenda;
    }

    public void setFechaInicioAdenda(Date fechaInicioAdenda) {
        this.fechaInicioAdenda = fechaInicioAdenda;
    }

    public Date getFechaFinAdenda() {
        return fechaFinAdenda;
    }

    public void setFechaFinAdenda(Date fechaFinAdenda) {
        this.fechaFinAdenda = fechaFinAdenda;
    }

    public BigDecimal getHonorarioTotal() {
        return honorarioTotal;
    }

    public void setHonorarioTotal(BigDecimal honorarioTotal) {
        this.honorarioTotal = honorarioTotal;
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

    @XmlTransient
    public List<MdiContratoDocumentoAdjunto> getMdiContratoDocumentoAdjuntoList() {
        return mdiContratoDocumentoAdjuntoList;
    }

    public void setMdiContratoDocumentoAdjuntoList(List<MdiContratoDocumentoAdjunto> mdiContratoDocumentoAdjuntoList) {
        this.mdiContratoDocumentoAdjuntoList = mdiContratoDocumentoAdjuntoList;
    }

    public MdiContratoEmpresaLocador getIdContratoEmpresaLocador() {
        return idContratoEmpresaLocador;
    }

    public void setIdContratoEmpresaLocador(MdiContratoEmpresaLocador idContratoEmpresaLocador) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdendaEmpresaLocador != null ? idAdendaEmpresaLocador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiAdendaEmpresaLocador)) {
            return false;
        }
        MdiAdendaEmpresaLocador other = (MdiAdendaEmpresaLocador) object;
        if ((this.idAdendaEmpresaLocador == null && other.idAdendaEmpresaLocador != null) || (this.idAdendaEmpresaLocador != null && !this.idAdendaEmpresaLocador.equals(other.idAdendaEmpresaLocador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiAdendaEmpresaLocador[ idAdendaEmpresaLocador=" + idAdendaEmpresaLocador + " ]";
    }
    
}

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
@Table(name = "MDI_LOCADOR_DESTAQUE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiLocadorDestaque.findAll", query = "SELECT m FROM MdiLocadorDestaque m"),
    @NamedQuery(name = "MdiLocadorDestaque.findByEntidadDestaque", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.entidadDestaque = :entidadDestaque"),
    @NamedQuery(name = "MdiLocadorDestaque.findByIdLocadorDestaque", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.idLocadorDestaque = :idLocadorDestaque"),
    @NamedQuery(name = "MdiLocadorDestaque.findByIdDivisionDestaque", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.idDivisionDestaque = :idDivisionDestaque"),
    @NamedQuery(name = "MdiLocadorDestaque.findByIdUnidadDestaque", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.idUnidadDestaque = :idUnidadDestaque"),
    @NamedQuery(name = "MdiLocadorDestaque.findByFechaInicioDestaque", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.fechaInicioDestaque = :fechaInicioDestaque"),
    @NamedQuery(name = "MdiLocadorDestaque.findByFechaFinDestaque", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.fechaFinDestaque = :fechaFinDestaque"),
    @NamedQuery(name = "MdiLocadorDestaque.findByMotivoDestaque", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.motivoDestaque = :motivoDestaque"),
    @NamedQuery(name = "MdiLocadorDestaque.findByEstado", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiLocadorDestaque.findByUsuarioCreacion", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiLocadorDestaque.findByFechaCreacion", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiLocadorDestaque.findByTerminalCreacion", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiLocadorDestaque.findByUsuarioActualizacion", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiLocadorDestaque.findByFechaActualizacion", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiLocadorDestaque.findByTerminalActualizacion", query = "SELECT m FROM MdiLocadorDestaque m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiLocadorDestaque extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Column(name = "ENTIDAD_DESTAQUE")
    private Long entidadDestaque;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_LOCADOR_DESTAQUE")
    private Long idLocadorDestaque;
    @Column(name = "ID_DIVISION_DESTAQUE")
    private Long idDivisionDestaque;
    @Column(name = "ID_UNIDAD_DESTAQUE")
    private Long idUnidadDestaque;
    @Column(name = "FECHA_INICIO_DESTAQUE")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioDestaque;
    @Column(name = "FECHA_FIN_DESTAQUE")
    @Temporal(TemporalType.DATE)
    private Date fechaFinDestaque;
    @Column(name = "MOTIVO_DESTAQUE")
    private Long motivoDestaque;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiLocadorDestaque")
    private List<MdiLocadorCompetencia> mdiLocadorCompetenciaList;
    @JoinColumns({
        @JoinColumn(name = "ID_MACRO_REGION", referencedColumnName = "ID_MACRO_REGION"),
        @JoinColumn(name = "ID_REGION", referencedColumnName = "ID_REGION")})
    @ManyToOne
    private MdiMacroRegionXRegion mdiMacroRegionXRegion;
    @JoinColumn(name = "ID_CONTRATO_EMPRESA_LOCADOR", referencedColumnName = "ID_CONTRATO_EMPRESA_LOCADOR")
    @ManyToOne(optional = false)
    private MdiContratoEmpresaLocador idContratoEmpresaLocador;

    public MdiLocadorDestaque() {
    }

    public MdiLocadorDestaque(Long idLocadorDestaque) {
        this.idLocadorDestaque = idLocadorDestaque;
    }

    public MdiLocadorDestaque(Long idLocadorDestaque, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idLocadorDestaque = idLocadorDestaque;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getEntidadDestaque() {
        return entidadDestaque;
    }

    public void setEntidadDestaque(Long entidadDestaque) {
        this.entidadDestaque = entidadDestaque;
    }

    public Long getIdLocadorDestaque() {
        return idLocadorDestaque;
    }

    public void setIdLocadorDestaque(Long idLocadorDestaque) {
        this.idLocadorDestaque = idLocadorDestaque;
    }

    public Long getIdDivisionDestaque() {
        return idDivisionDestaque;
    }

    public void setIdDivisionDestaque(Long idDivisionDestaque) {
        this.idDivisionDestaque = idDivisionDestaque;
    }

    public Long getIdUnidadDestaque() {
        return idUnidadDestaque;
    }

    public void setIdUnidadDestaque(Long idUnidadDestaque) {
        this.idUnidadDestaque = idUnidadDestaque;
    }

    public Date getFechaInicioDestaque() {
        return fechaInicioDestaque;
    }

    public void setFechaInicioDestaque(Date fechaInicioDestaque) {
        this.fechaInicioDestaque = fechaInicioDestaque;
    }

    public Date getFechaFinDestaque() {
        return fechaFinDestaque;
    }

    public void setFechaFinDestaque(Date fechaFinDestaque) {
        this.fechaFinDestaque = fechaFinDestaque;
    }

    public Long getMotivoDestaque() {
        return motivoDestaque;
    }

    public void setMotivoDestaque(Long motivoDestaque) {
        this.motivoDestaque = motivoDestaque;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiLocadorCompetencia> getMdiLocadorCompetenciaList() {
        return mdiLocadorCompetenciaList;
    }

    public void setMdiLocadorCompetenciaList(List<MdiLocadorCompetencia> mdiLocadorCompetenciaList) {
        this.mdiLocadorCompetenciaList = mdiLocadorCompetenciaList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocadorDestaque != null ? idLocadorDestaque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiLocadorDestaque)) {
            return false;
        }
        MdiLocadorDestaque other = (MdiLocadorDestaque) object;
        if ((this.idLocadorDestaque == null && other.idLocadorDestaque != null) || (this.idLocadorDestaque != null && !this.idLocadorDestaque.equals(other.idLocadorDestaque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiLocadorDestaque[ idLocadorDestaque=" + idLocadorDestaque + " ]";
    }
    
}

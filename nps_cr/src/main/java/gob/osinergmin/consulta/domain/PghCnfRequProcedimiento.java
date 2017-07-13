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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_CNF_REQU_PROCEDIMIENTO")
@NamedQueries({
    @NamedQuery(name = "PghCnfRequProcedimiento.findAll", query = "SELECT p FROM PghCnfRequProcedimiento p"),
    @NamedQuery(name = "PghCnfRequProcedimiento.findByComentario", query = "SELECT p FROM PghCnfRequProcedimiento p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "PghCnfRequProcedimiento.findByEstado", query = "SELECT p FROM PghCnfRequProcedimiento p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghCnfRequProcedimiento.findByIdRequisitoProcedimiento", query = "SELECT p FROM PghCnfRequProcedimiento p WHERE p.idRequisitoProcedimiento = :idRequisitoProcedimiento"),
    @NamedQuery(name = "PghCnfRequProcedimiento.findByIdRequisitoProcedimientoPad", query = "SELECT p FROM PghCnfRequProcedimiento p WHERE p.idRequisitoProcedimientoPad = :idRequisitoProcedimientoPad")})
public class PghCnfRequProcedimiento extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQUISITO_PROCEDIMIENTO")
    private Long idRequisitoProcedimiento;
    @Size(max = 1500)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "ID_REQUISITO_PROCEDIMIENTO_PAD")
    private Long idRequisitoProcedimientoPad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FLG_GENERAL")
    private String flgGeneral;
    @Column(name = "NRO_ORDEN")
    private Long nroOrden;
    
    @JoinColumn(name = "ID_ZONIFICACION", referencedColumnName = "ID_ZONIFICACION")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghZonificacion idZonificacion;
    @JoinColumn(name = "ID_TRAMITE", referencedColumnName = "ID_TRAMITE")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghTramite idTramite;
    
    @JoinColumn(name = "ID_REQUISITO", referencedColumnName = "ID_REQUISITO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PghRequisito idRequisito;
    
    @JoinColumn(name = "ID_PROCEDIMIENTO", referencedColumnName = "ID_PROCEDIMIENTO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PghProcedimiento idProcedimiento;

    @JoinColumn(name = "ID_MOTIVO_TRAMITE", referencedColumnName = "ID_MOTIVO_TRAMITE")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghMotivoTramite idMotivoTramite;
    
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ManyToOne(fetch = FetchType.LAZY)
    private MdiActividad idActividad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRequisitoProcedimiento", fetch = FetchType.LAZY)
    private List<PghRequProcParaDina> pghRequProcParaDinaList;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_REQUISITO_PROCEDIMIENTO_PAD", insertable = false, updatable = false)
    private PghCnfRequProcedimiento pghCnfRequProcedimientoPad;
    
    @OneToMany(mappedBy="pghCnfRequProcedimientoPad")
    private List<PghCnfRequProcedimiento> pghRequProcParaProcedimientoList;

    public PghCnfRequProcedimiento() {
    }

    public List<PghRequProcParaDina> getPghRequProcParaDinaList() {
        return pghRequProcParaDinaList;
    }

    public void setPghRequProcParaDinaList(List<PghRequProcParaDina> pghRequProcParaDinaList) {
        this.pghRequProcParaDinaList = pghRequProcParaDinaList;
    }
    
    public PghCnfRequProcedimiento(Long idRequisitoProcedimiento) {
        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
    }

    public PghCnfRequProcedimiento(Long idRequisitoProcedimiento, String comentario) {
        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
        this.comentario = comentario;
    }

    public PghCnfRequProcedimiento(Long idRequisitoProcedimiento, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdRequisitoProcedimiento() {
        return idRequisitoProcedimiento;
    }

    public void setIdRequisitoProcedimiento(Long idRequisitoProcedimiento) {
        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
    }

    public Long getIdRequisitoProcedimientoPad() {
        return idRequisitoProcedimientoPad;
    }

    public void setIdRequisitoProcedimientoPad(Long idRequisitoProcedimientoPad) {
        this.idRequisitoProcedimientoPad = idRequisitoProcedimientoPad;
    }

    public PghZonificacion getIdZonificacion() {
        return idZonificacion;
    }

    public void setIdZonificacion(PghZonificacion idZonificacion) {
        this.idZonificacion = idZonificacion;
    }

    public PghTramite getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(PghTramite idTramite) {
        this.idTramite = idTramite;
    }

    public PghRequisito getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(PghRequisito idRequisito) {
        this.idRequisito = idRequisito;
    }

    public PghProcedimiento getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(PghProcedimiento idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public PghMotivoTramite getIdMotivoTramite() {
        return idMotivoTramite;
    }

    public void setIdMotivoTramite(PghMotivoTramite idMotivoTramite) {
        this.idMotivoTramite = idMotivoTramite;
    }

    public MdiActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(MdiActividad idActividad) {
        this.idActividad = idActividad;
    }

    public String getFlgGeneral() {
        return flgGeneral;
    }

    public void setFlgGeneral(String flgGeneral) {
        this.flgGeneral = flgGeneral;
    }

    public Long getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(Long nroOrden) {
        this.nroOrden = nroOrden;
    }
    
    public PghCnfRequProcedimiento getPghCnfRequProcedimientoPad() {
        return pghCnfRequProcedimientoPad;
    }

    public void setPghCnfRequProcedimientoPad(PghCnfRequProcedimiento pghCnfRequProcedimientoPad) {
        this.pghCnfRequProcedimientoPad = pghCnfRequProcedimientoPad;
    }

    public List<PghCnfRequProcedimiento> getPghRequProcParaProcedimientoList() {
        return pghRequProcParaProcedimientoList;
    }

    public void setPghRequProcParaProcedimientoList(List<PghCnfRequProcedimiento> pghRequProcParaProcedimientoList) {
        this.pghRequProcParaProcedimientoList = pghRequProcParaProcedimientoList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequisitoProcedimiento != null ? idRequisitoProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghCnfRequProcedimiento)) {
            return false;
        }
        PghCnfRequProcedimiento other = (PghCnfRequProcedimiento) object;
        if ((this.idRequisitoProcedimiento == null && other.idRequisitoProcedimiento != null) || (this.idRequisitoProcedimiento != null && !this.idRequisitoProcedimiento.equals(other.idRequisitoProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghCnfRequProcedimiento[ idRequisitoProcedimiento=" + idRequisitoProcedimiento + " ]";
    }   
}

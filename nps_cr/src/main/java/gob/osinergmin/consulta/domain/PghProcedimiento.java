/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_PROCEDIMIENTO")
@NamedQueries({
    @NamedQuery(name="PghProcedimiento.findAll", query = "SELECT p FROM PghProcedimiento p"),
    @NamedQuery(name="PghProcedimiento.countByFilter",query="Select count(d.idProcedimiento) from PghProcedimiento d where d.estado=:estado "
        + "and upper(d.item) like :item and upper(d.denominacion) like :denominacion and upper(d.baseLegal) like :baseLegal"),
    @NamedQuery(name="PghProcedimiento.findByFilter",query="Select d from PghProcedimiento d where d.estado=:estado "
        + "and upper(d.item) like :item and upper(d.denominacion) like :denominacion and upper(d.baseLegal) like :baseLegal")
})
public class PghProcedimiento extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROCEDIMIENTO")
    @SequenceGenerator(name = "SEQ_GENERATOR", sequenceName = "PGH_PROCEDIMIENTO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
    private Long idProcedimiento;
    @Size(max = 10)
    @Column(name = "ITEM")
    private String item;
    @Size(max = 500)
    @Column(name = "DENOMINACION")
    private String denominacion;
    @Size(max = 500)
    @Column(name = "BASE_LEGAL")
    private String baseLegal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DERECHO_TRAMITACION")
    private BigDecimal derechoTramitacion;
    @Column(name = "ID_CALIFICACION")
    private Long idCalificacion;
    @Column(name = "ID_SILENCIO_ADMINISTRATIVO")
    private Long idSilencioAdministrativo;
    @Column(name = "PLAZO_RESOLVER")
    private BigDecimal plazoResolver;
    @Column(name = "ID_INICIO_PROCEDIMIENTO")
    private Long idInicioProcedimiento;
    @Column(name = "ID_AUTORIDAD_COMPETENTE")
    private Long idAutoridadCompetente;
    @Column(name = "ID_APELACION")
    private Long idApelacion;
    @Column(name = "ID_RECONSIDERACION")
    private Long idReconsideracion;
    @Column(name = "ID_ANEXO_RRH")
    private Long idAnexoRrh;
    @Column(name = "ID_VALOR_UIT")
    private Long idValorUit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 4000)
    @Column(name = "NOTA_PROCEDIMIENTO")
    private String notaProcedimiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimiento", fetch = FetchType.LAZY)
    private List<PghProcedimientoTramite> pghProcedimientoTramiteList;
    @OneToMany(mappedBy = "idProcedimiento", fetch = FetchType.LAZY)
    private List<PghHstProcedimiento> pghHstProcedimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProcedimiento", fetch = FetchType.LAZY)
    private List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList;

    //JPIRO
    @Transient
    private String proceso;
    
    public PghProcedimiento() {
    }

    public PghProcedimiento(Long idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public PghProcedimiento(Long idProcedimiento, String item,String denominacion,String proceso) {
        this.idProcedimiento = idProcedimiento;
        this.item=item;
        this.denominacion=denominacion;
        this.proceso=proceso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Long getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Long idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getBaseLegal() {
        return baseLegal;
    }

    public void setBaseLegal(String baseLegal) {
        this.baseLegal = baseLegal;
    }

    public BigDecimal getDerechoTramitacion() {
        return derechoTramitacion;
    }

    public void setDerechoTramitacion(BigDecimal derechoTramitacion) {
        this.derechoTramitacion = derechoTramitacion;
    }

    public Long getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Long idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Long getIdSilencioAdministrativo() {
        return idSilencioAdministrativo;
    }

    public void setIdSilencioAdministrativo(Long idSilencioAdministrativo) {
        this.idSilencioAdministrativo = idSilencioAdministrativo;
    }

    public BigDecimal getPlazoResolver() {
        return plazoResolver;
    }

    public void setPlazoResolver(BigDecimal plazoResolver) {
        this.plazoResolver = plazoResolver;
    }

    public Long getIdInicioProcedimiento() {
        return idInicioProcedimiento;
    }

    public void setIdInicioProcedimiento(Long idInicioProcedimiento) {
        this.idInicioProcedimiento = idInicioProcedimiento;
    }

    public Long getIdAutoridadCompetente() {
        return idAutoridadCompetente;
    }

    public void setIdAutoridadCompetente(Long idAutoridadCompetente) {
        this.idAutoridadCompetente = idAutoridadCompetente;
    }

    public Long getIdApelacion() {
        return idApelacion;
    }

    public void setIdApelacion(Long idApelacion) {
        this.idApelacion = idApelacion;
    }

    public Long getIdReconsideracion() {
        return idReconsideracion;
    }

    public void setIdReconsideracion(Long idReconsideracion) {
        this.idReconsideracion = idReconsideracion;
    }

    public Long getIdAnexoRrh() {
        return idAnexoRrh;
    }

    public void setIdAnexoRrh(Long idAnexoRrh) {
        this.idAnexoRrh = idAnexoRrh;
    }

    public Long getIdValorUit() {
        return idValorUit;
    }

    public void setIdValorUit(Long idValorUit) {
        this.idValorUit = idValorUit;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotaProcedimiento() {
        return notaProcedimiento;
    }

    public void setNotaProcedimiento(String notaProcedimiento) {
        this.notaProcedimiento = notaProcedimiento;
    }

    public List<PghProcedimientoTramite> getPghProcedimientoTramiteList() {
        return pghProcedimientoTramiteList;
    }

    public void setPghProcedimientoTramiteList(List<PghProcedimientoTramite> pghProcedimientoTramiteList) {
        this.pghProcedimientoTramiteList = pghProcedimientoTramiteList;
    }

    public List<PghHstProcedimiento> getPghHstProcedimientoList() {
        return pghHstProcedimientoList;
    }

    public void setPghHstProcedimientoList(List<PghHstProcedimiento> pghHstProcedimientoList) {
        this.pghHstProcedimientoList = pghHstProcedimientoList;
    }

    public List<PghCnfRequProcedimiento> getPghCnfRequProcedimientoList() {
        return pghCnfRequProcedimientoList;
    }

    public void setPghCnfRequProcedimientoList(List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList) {
        this.pghCnfRequProcedimientoList = pghCnfRequProcedimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcedimiento != null ? idProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghProcedimiento)) {
            return false;
        }
        PghProcedimiento other = (PghProcedimiento) object;
        if ((this.idProcedimiento == null && other.idProcedimiento != null) || (this.idProcedimiento != null && !this.idProcedimiento.equals(other.idProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghProcedimiento[ idProcedimiento=" + idProcedimiento + " ]";
    }  
}

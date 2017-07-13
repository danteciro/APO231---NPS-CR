/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_REQU_PROC_PARA_DINA")
@NamedQueries({
    @NamedQuery(name = "PghRequProcParaDina.findAll", query = "SELECT p FROM PghRequProcParaDina p"),
    @NamedQuery(name = "PghRequProcParaDina.findByEstado", query = "SELECT p FROM PghRequProcParaDina p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghRequProcParaDina.findByIdRequisitoProcedimiento", query = "SELECT p FROM PghRequProcParaDina p WHERE p.idRequisitoProcedimiento = :idRequisitoProcedimiento"),
    @NamedQuery(name = "PghRequProcParaDina.findByIdRequProcParaDina", query = "SELECT p FROM PghRequProcParaDina p WHERE p.idRequProcParaDina = :idRequProcParaDina")})
public class PghRequProcParaDina extends Auditoria {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REQU_PROC_PARA_DINA")
    private Long idRequProcParaDina;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "ID_REQUISITO_PROCEDIMIENTO")
//    private Long idRequisitoProcedimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    
    @JoinColumn(name = "ID_REQUISITO_PROCEDIMIENTO", referencedColumnName = "ID_REQUISITO_PROCEDIMIENTO")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghCnfRequProcedimiento idRequisitoProcedimiento;
    
    @JoinColumn(name = "ID_VALOR_PARAMETRO", referencedColumnName = "ID_VALOR_PARAMETRO")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PghValorParametro idValorParametro;

    public PghRequProcParaDina() {
    }

    public PghRequProcParaDina(Long idRequProcParaDina) {
        this.idRequProcParaDina = idRequProcParaDina;
    }

//    public PghRequProcParaDina(Long idRequProcParaDina, String estado, long idRequisitoProcedimiento, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
//        this.idRequProcParaDina = idRequProcParaDina;
//        this.estado = estado;
//        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
//        this.usuarioCreacion = usuarioCreacion;
//        this.fechaCreacion = fechaCreacion;
//        this.terminalCreacion = terminalCreacion;
//    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public long getIdRequisitoProcedimiento() {
//        return idRequisitoProcedimiento;
//    }
//
//    public void setIdRequisitoProcedimiento(Long idRequisitoProcedimiento) {
//        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
//    }

    public Long getIdRequProcParaDina() {
        return idRequProcParaDina;
    }

    public void setIdRequProcParaDina(Long idRequProcParaDina) {
        this.idRequProcParaDina = idRequProcParaDina;
    }

    public PghValorParametro getIdValorParametro() {
        return idValorParametro;
    }

    public void setIdValorParametro(PghValorParametro idValorParametro) {
        this.idValorParametro = idValorParametro;
    }

    public PghCnfRequProcedimiento getIdRequisitoProcedimiento() {
        return idRequisitoProcedimiento;
    }

    public void setIdRequisitoProcedimiento(PghCnfRequProcedimiento idRequisitoProcedimiento) {
        this.idRequisitoProcedimiento = idRequisitoProcedimiento;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequProcParaDina != null ? idRequProcParaDina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghRequProcParaDina)) {
            return false;
        }
        PghRequProcParaDina other = (PghRequProcParaDina) object;
        if ((this.idRequProcParaDina == null && other.idRequProcParaDina != null) || (this.idRequProcParaDina != null && !this.idRequProcParaDina.equals(other.idRequProcParaDina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghRequProcParaDina[ idRequProcParaDina=" + idRequProcParaDina + " ]";
    }
}

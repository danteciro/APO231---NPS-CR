/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "PGH_PROCESO")
@NamedQueries({
    @NamedQuery(name = "PghProceso.findAll", query = "SELECT p FROM PghProceso p"),
    @NamedQuery(name = "PghProceso.findByIdProceso", query = "SELECT p FROM PghProceso p WHERE p.idProceso = :idProceso"),
    @NamedQuery(name = "PghProceso.findByEstado", query = "SELECT p FROM PghProceso p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghProceso.findByDescripcion", query = "SELECT p FROM PghProceso p WHERE p.descripcion = :descripcion")})
public class PghProceso extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROCESO")
    private Long idProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @OneToMany(mappedBy = "idProceso", fetch = FetchType.LAZY)
    private List<PghEtapa> pghEtapaList;
    @OneToMany(mappedBy = "idProceso", fetch = FetchType.LAZY)
    private List<PghTipoSupervision> pghTipoSupervisionList;

    public PghProceso() {
    }

    public PghProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public PghProceso(Long idProceso, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String estado) {
        this.idProceso = idProceso;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Long idProceso) {
        this.idProceso = idProceso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PghEtapa> getPghEtapaList() {
        return pghEtapaList;
    }

    public void setPghEtapaList(List<PghEtapa> pghEtapaList) {
        this.pghEtapaList = pghEtapaList;
    }

    public List<PghTipoSupervision> getPghTipoSupervisionList() {
        return pghTipoSupervisionList;
    }

    public void setPghTipoSupervisionList(List<PghTipoSupervision> pghTipoSupervisionList) {
        this.pghTipoSupervisionList = pghTipoSupervisionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProceso != null ? idProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghProceso)) {
            return false;
        }
        PghProceso other = (PghProceso) object;
        if ((this.idProceso == null && other.idProceso != null) || (this.idProceso != null && !this.idProceso.equals(other.idProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghProceso[ idProceso=" + idProceso + " ]";
    }
}

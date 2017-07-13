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
@Table(name = "PGH_ETAPA")
@NamedQueries({
    @NamedQuery(name = "PghEtapa.findAll", query = "SELECT p FROM PghEtapa p where p.estado=:estado"),
    @NamedQuery(name = "PghEtapa.findByDescProceso", 
        query = "SELECT p FROM PghEtapa p left join p.idProceso pro where p.estado=:estado and pro.descripcion=:descProceso")
})
public class PghEtapa extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ETAPA")
    private Long idEtapa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ORDEN_ETAPA")
    private Long ordenEtapa;    
    @JoinColumn(name = "ID_PROCESO", referencedColumnName = "ID_PROCESO")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghProceso idProceso;
    @OneToMany(mappedBy = "idEtapa", fetch = FetchType.LAZY)
    private List<PghTramite> pghTramiteList;

    public PghEtapa() {
    }

    public PghEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
    }

    public PghEtapa(Long idEtapa, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String estado) {
        this.idEtapa = idEtapa;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Long idEtapa) {
        this.idEtapa = idEtapa;
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

    public PghProceso getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(PghProceso idProceso) {
        this.idProceso = idProceso;
    }

    public List<PghTramite> getPghTramiteList() {
        return pghTramiteList;
    }

    public void setPghTramiteList(List<PghTramite> pghTramiteList) {
        this.pghTramiteList = pghTramiteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtapa != null ? idEtapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghEtapa)) {
            return false;
        }
        PghEtapa other = (PghEtapa) object;
        if ((this.idEtapa == null && other.idEtapa != null) || (this.idEtapa != null && !this.idEtapa.equals(other.idEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.domain.PghEtapa[ idEtapa=" + idEtapa + " ]";
    }
}

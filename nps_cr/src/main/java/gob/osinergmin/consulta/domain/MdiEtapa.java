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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_ETAPA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiEtapa.findAll", query = "SELECT m FROM MdiEtapa m"),
    @NamedQuery(name = "MdiEtapa.findByIdEtapa", query = "SELECT m FROM MdiEtapa m WHERE m.idEtapa = :idEtapa"),
    @NamedQuery(name = "MdiEtapa.findByDescripcion", query = "SELECT m FROM MdiEtapa m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MdiEtapa.findByFechaCreacion", query = "SELECT m FROM MdiEtapa m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiEtapa.findByFechaActualizacion", query = "SELECT m FROM MdiEtapa m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiEtapa.findByUsuarioCreacion", query = "SELECT m FROM MdiEtapa m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiEtapa.findByUsuarioActualizacion", query = "SELECT m FROM MdiEtapa m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiEtapa.findByTerminalCreacion", query = "SELECT m FROM MdiEtapa m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiEtapa.findByTerminalActualizacion", query = "SELECT m FROM MdiEtapa m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiEtapa extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ETAPA")
    private Short idEtapa;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;    
    @OneToMany(mappedBy = "idEtapa")
    private List<MdiUnidadSupervisada> mdiUnidadSupervisadaList;

    public MdiEtapa() {
    }

    public MdiEtapa(Short idEtapa) {
        this.idEtapa = idEtapa;
    }

    public MdiEtapa(Short idEtapa, String descripcion, Date fechaCreacion, String usuarioCreacion, String terminalCreacion) {
        this.idEtapa = idEtapa;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Short getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(Short idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<MdiUnidadSupervisada> getMdiUnidadSupervisadaList() {
        return mdiUnidadSupervisadaList;
    }

    public void setMdiUnidadSupervisadaList(List<MdiUnidadSupervisada> mdiUnidadSupervisadaList) {
        this.mdiUnidadSupervisadaList = mdiUnidadSupervisadaList;
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
        if (!(object instanceof MdiEtapa)) {
            return false;
        }
        MdiEtapa other = (MdiEtapa) object;
        if ((this.idEtapa == null && other.idEtapa != null) || (this.idEtapa != null && !this.idEtapa.equals(other.idEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiEtapa[ idEtapa=" + idEtapa + " ]";
    }
    
}

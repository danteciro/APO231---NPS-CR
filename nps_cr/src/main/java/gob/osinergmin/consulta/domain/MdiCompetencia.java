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
@Table(name = "MDI_COMPETENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiCompetencia.findAll", query = "SELECT m FROM MdiCompetencia m"),
    @NamedQuery(name = "MdiCompetencia.findByIdCompetencia", query = "SELECT m FROM MdiCompetencia m WHERE m.idCompetencia = :idCompetencia"),
    @NamedQuery(name = "MdiCompetencia.findByTipoCompetencia", query = "SELECT m FROM MdiCompetencia m WHERE m.tipoCompetencia = :tipoCompetencia"),
    @NamedQuery(name = "MdiCompetencia.findByNombreCompetencia", query = "SELECT m FROM MdiCompetencia m WHERE m.nombreCompetencia = :nombreCompetencia"),
    @NamedQuery(name = "MdiCompetencia.findByDescripcionCompetencia", query = "SELECT m FROM MdiCompetencia m WHERE m.descripcionCompetencia = :descripcionCompetencia"),
    @NamedQuery(name = "MdiCompetencia.findByEstado", query = "SELECT m FROM MdiCompetencia m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiCompetencia.findByUsuarioCreacion", query = "SELECT m FROM MdiCompetencia m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiCompetencia.findByFechaCreacion", query = "SELECT m FROM MdiCompetencia m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiCompetencia.findByTerminalCreacion", query = "SELECT m FROM MdiCompetencia m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiCompetencia.findByUsuarioActualizacion", query = "SELECT m FROM MdiCompetencia m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiCompetencia.findByFechaActualizacion", query = "SELECT m FROM MdiCompetencia m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiCompetencia.findByTerminalActualizacion", query = "SELECT m FROM MdiCompetencia m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiCompetencia extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COMPETENCIA")
    private Long idCompetencia;
    @Basic(optional = false)
    @Column(name = "TIPO_COMPETENCIA")
    private long tipoCompetencia;
    @Basic(optional = false)
    @Column(name = "NOMBRE_COMPETENCIA")
    private String nombreCompetencia;
    @Column(name = "DESCRIPCION_COMPETENCIA")
    private String descripcionCompetencia;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiCompetencia")
    private List<MdiLocadorCompetencia> mdiLocadorCompetenciaList;

    public MdiCompetencia() {
    }

    public MdiCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public MdiCompetencia(Long idCompetencia, long tipoCompetencia, String nombreCompetencia, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idCompetencia = idCompetencia;
        this.tipoCompetencia = tipoCompetencia;
        this.nombreCompetencia = nombreCompetencia;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public long getTipoCompetencia() {
        return tipoCompetencia;
    }

    public void setTipoCompetencia(long tipoCompetencia) {
        this.tipoCompetencia = tipoCompetencia;
    }

    public String getNombreCompetencia() {
        return nombreCompetencia;
    }

    public void setNombreCompetencia(String nombreCompetencia) {
        this.nombreCompetencia = nombreCompetencia;
    }

    public String getDescripcionCompetencia() {
        return descripcionCompetencia;
    }

    public void setDescripcionCompetencia(String descripcionCompetencia) {
        this.descripcionCompetencia = descripcionCompetencia;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompetencia != null ? idCompetencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiCompetencia)) {
            return false;
        }
        MdiCompetencia other = (MdiCompetencia) object;
        if ((this.idCompetencia == null && other.idCompetencia != null) || (this.idCompetencia != null && !this.idCompetencia.equals(other.idCompetencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiCompetencia[ idCompetencia=" + idCompetencia + " ]";
    }
    
}

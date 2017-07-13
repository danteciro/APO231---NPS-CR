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
@Table(name = "MDI_INSTITUCION_ACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiInstitucionAcademica.findAll", query = "SELECT m FROM MdiInstitucionAcademica m"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByIdInstitucionAcademica", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.idInstitucionAcademica = :idInstitucionAcademica"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByTipoInstitucion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.tipoInstitucion = :tipoInstitucion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByNombreInstitucion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.nombreInstitucion = :nombreInstitucion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByDescripcionInstitucion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.descripcionInstitucion = :descripcionInstitucion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByAmbitoInstitucion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.ambitoInstitucion = :ambitoInstitucion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByEstado", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByUsuarioCreacion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByFechaCreacion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByTerminalCreacion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByUsuarioActualizacion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByFechaActualizacion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiInstitucionAcademica.findByTerminalActualizacion", query = "SELECT m FROM MdiInstitucionAcademica m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiInstitucionAcademica extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_INSTITUCION_ACADEMICA")
    private Long idInstitucionAcademica;
    @Basic(optional = false)
    @Column(name = "TIPO_INSTITUCION")
    private long tipoInstitucion;
    @Basic(optional = false)
    @Column(name = "NOMBRE_INSTITUCION")
    private String nombreInstitucion;
    @Column(name = "DESCRIPCION_INSTITUCION")
    private String descripcionInstitucion;
    @Column(name = "AMBITO_INSTITUCION")
    private Long ambitoInstitucion;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInstitucionAcademica")
    private List<MdiInstAcadLocador> mdiInstAcadLocadorList;

    public MdiInstitucionAcademica() {
    }

    public MdiInstitucionAcademica(Long idInstitucionAcademica) {
        this.idInstitucionAcademica = idInstitucionAcademica;
    }

    public MdiInstitucionAcademica(Long idInstitucionAcademica, long tipoInstitucion, String nombreInstitucion, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idInstitucionAcademica = idInstitucionAcademica;
        this.tipoInstitucion = tipoInstitucion;
        this.nombreInstitucion = nombreInstitucion;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdInstitucionAcademica() {
        return idInstitucionAcademica;
    }

    public void setIdInstitucionAcademica(Long idInstitucionAcademica) {
        this.idInstitucionAcademica = idInstitucionAcademica;
    }

    public long getTipoInstitucion() {
        return tipoInstitucion;
    }

    public void setTipoInstitucion(long tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getDescripcionInstitucion() {
        return descripcionInstitucion;
    }

    public void setDescripcionInstitucion(String descripcionInstitucion) {
        this.descripcionInstitucion = descripcionInstitucion;
    }

    public Long getAmbitoInstitucion() {
        return ambitoInstitucion;
    }

    public void setAmbitoInstitucion(Long ambitoInstitucion) {
        this.ambitoInstitucion = ambitoInstitucion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiInstAcadLocador> getMdiInstAcadLocadorList() {
        return mdiInstAcadLocadorList;
    }

    public void setMdiInstAcadLocadorList(List<MdiInstAcadLocador> mdiInstAcadLocadorList) {
        this.mdiInstAcadLocadorList = mdiInstAcadLocadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstitucionAcademica != null ? idInstitucionAcademica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiInstitucionAcademica)) {
            return false;
        }
        MdiInstitucionAcademica other = (MdiInstitucionAcademica) object;
        if ((this.idInstitucionAcademica == null && other.idInstitucionAcademica != null) || (this.idInstitucionAcademica != null && !this.idInstitucionAcademica.equals(other.idInstitucionAcademica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiInstitucionAcademica[ idInstitucionAcademica=" + idInstitucionAcademica + " ]";
    }
    
}

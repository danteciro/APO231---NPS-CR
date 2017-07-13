/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_LOCADOR_COMPETENCIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiLocadorCompetencia.findAll", query = "SELECT m FROM MdiLocadorCompetencia m"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByIdCompetencia", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.mdiLocadorCompetenciaPK.idCompetencia = :idCompetencia"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByObservaciones", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByEstado", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByUsuarioCreacion", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByFechaCreacion", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByTerminalCreacion", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByUsuarioActualizacion", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByFechaActualizacion", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByIdLocadorDestaque", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.mdiLocadorCompetenciaPK.idLocadorDestaque = :idLocadorDestaque"),
    @NamedQuery(name = "MdiLocadorCompetencia.findByTerminalActualizacion", query = "SELECT m FROM MdiLocadorCompetencia m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiLocadorCompetencia extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiLocadorCompetenciaPK mdiLocadorCompetenciaPK;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @JoinColumn(name = "ID_LOCADOR_DESTAQUE", referencedColumnName = "ID_LOCADOR_DESTAQUE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiLocadorDestaque mdiLocadorDestaque;
    @JoinColumn(name = "ID_COMPETENCIA", referencedColumnName = "ID_COMPETENCIA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiCompetencia mdiCompetencia;

    public MdiLocadorCompetencia() {
    }

    public MdiLocadorCompetencia(MdiLocadorCompetenciaPK mdiLocadorCompetenciaPK) {
        this.mdiLocadorCompetenciaPK = mdiLocadorCompetenciaPK;
    }

    public MdiLocadorCompetencia(MdiLocadorCompetenciaPK mdiLocadorCompetenciaPK, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.mdiLocadorCompetenciaPK = mdiLocadorCompetenciaPK;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public MdiLocadorCompetencia(long idCompetencia, long idLocadorDestaque) {
        this.mdiLocadorCompetenciaPK = new MdiLocadorCompetenciaPK(idCompetencia, idLocadorDestaque);
    }

    public MdiLocadorCompetenciaPK getMdiLocadorCompetenciaPK() {
        return mdiLocadorCompetenciaPK;
    }

    public void setMdiLocadorCompetenciaPK(MdiLocadorCompetenciaPK mdiLocadorCompetenciaPK) {
        this.mdiLocadorCompetenciaPK = mdiLocadorCompetenciaPK;
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

    public MdiLocadorDestaque getMdiLocadorDestaque() {
        return mdiLocadorDestaque;
    }

    public void setMdiLocadorDestaque(MdiLocadorDestaque mdiLocadorDestaque) {
        this.mdiLocadorDestaque = mdiLocadorDestaque;
    }

    public MdiCompetencia getMdiCompetencia() {
        return mdiCompetencia;
    }

    public void setMdiCompetencia(MdiCompetencia mdiCompetencia) {
        this.mdiCompetencia = mdiCompetencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiLocadorCompetenciaPK != null ? mdiLocadorCompetenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiLocadorCompetencia)) {
            return false;
        }
        MdiLocadorCompetencia other = (MdiLocadorCompetencia) object;
        if ((this.mdiLocadorCompetenciaPK == null && other.mdiLocadorCompetenciaPK != null) || (this.mdiLocadorCompetenciaPK != null && !this.mdiLocadorCompetenciaPK.equals(other.mdiLocadorCompetenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiLocadorCompetencia[ mdiLocadorCompetenciaPK=" + mdiLocadorCompetenciaPK + " ]";
    }
    
}

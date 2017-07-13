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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_EMPRESA_UNIDAD_SUPERVISADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findAll", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByIdUnidadSupervisada", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.mdiEmpresaUnidadSupervisadaPK.idUnidadSupervisada = :idUnidadSupervisada"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByIdEmpresaSupervisada", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.mdiEmpresaUnidadSupervisadaPK.idEmpresaSupervisada = :idEmpresaSupervisada"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByFechaDesde", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByFechaHasta", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByEstado", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByUsuarioCreacion", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByUsuarioActualizacion", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByTerminalCreacion", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByTerminalActualizacion", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByFechaCreacion", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiEmpresaUnidadSupervisada.findByFechaActualizacion", query = "SELECT m FROM MdiEmpresaUnidadSupervisada m WHERE m.fechaActualizacion = :fechaActualizacion")})
public class MdiEmpresaUnidadSupervisada extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiEmpresaUnidadSupervisadaPK mdiEmpresaUnidadSupervisadaPK;
    @Column(name = "FECHA_DESDE")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @JoinColumn(name = "ID_UNIDAD_SUPERVISADA", referencedColumnName = "ID_UNIDAD_SUPERVISADA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiUnidadSupervisada mdiUnidadSupervisada;
    @JoinColumn(name = "ID_EMPRESA_SUPERVISADA", referencedColumnName = "ID_EMPRESA_SUPERVISADA", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiEmpresaSupervisada mdiEmpresaSupervisada;

    public MdiEmpresaUnidadSupervisada() {
    }

    public MdiEmpresaUnidadSupervisada(MdiEmpresaUnidadSupervisadaPK mdiEmpresaUnidadSupervisadaPK) {
        this.mdiEmpresaUnidadSupervisadaPK = mdiEmpresaUnidadSupervisadaPK;
    }

    public MdiEmpresaUnidadSupervisada(MdiEmpresaUnidadSupervisadaPK mdiEmpresaUnidadSupervisadaPK, char estado, String usuarioCreacion, String terminalCreacion, Date fechaCreacion) {
        this.mdiEmpresaUnidadSupervisadaPK = mdiEmpresaUnidadSupervisadaPK;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.fechaCreacion = fechaCreacion;
    }

    public MdiEmpresaUnidadSupervisada(long idUnidadSupervisada, long idEmpresaSupervisada) {
        this.mdiEmpresaUnidadSupervisadaPK = new MdiEmpresaUnidadSupervisadaPK(idUnidadSupervisada, idEmpresaSupervisada);
    }

    public MdiEmpresaUnidadSupervisadaPK getMdiEmpresaUnidadSupervisadaPK() {
        return mdiEmpresaUnidadSupervisadaPK;
    }

    public void setMdiEmpresaUnidadSupervisadaPK(MdiEmpresaUnidadSupervisadaPK mdiEmpresaUnidadSupervisadaPK) {
        this.mdiEmpresaUnidadSupervisadaPK = mdiEmpresaUnidadSupervisadaPK;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public MdiUnidadSupervisada getMdiUnidadSupervisada() {
        return mdiUnidadSupervisada;
    }

    public void setMdiUnidadSupervisada(MdiUnidadSupervisada mdiUnidadSupervisada) {
        this.mdiUnidadSupervisada = mdiUnidadSupervisada;
    }

    public MdiEmpresaSupervisada getMdiEmpresaSupervisada() {
        return mdiEmpresaSupervisada;
    }

    public void setMdiEmpresaSupervisada(MdiEmpresaSupervisada mdiEmpresaSupervisada) {
        this.mdiEmpresaSupervisada = mdiEmpresaSupervisada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiEmpresaUnidadSupervisadaPK != null ? mdiEmpresaUnidadSupervisadaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiEmpresaUnidadSupervisada)) {
            return false;
        }
        MdiEmpresaUnidadSupervisada other = (MdiEmpresaUnidadSupervisada) object;
        if ((this.mdiEmpresaUnidadSupervisadaPK == null && other.mdiEmpresaUnidadSupervisadaPK != null) || (this.mdiEmpresaUnidadSupervisadaPK != null && !this.mdiEmpresaUnidadSupervisadaPK.equals(other.mdiEmpresaUnidadSupervisadaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiEmpresaUnidadSupervisada[ mdiEmpresaUnidadSupervisadaPK=" + mdiEmpresaUnidadSupervisadaPK + " ]";
    }
    
}

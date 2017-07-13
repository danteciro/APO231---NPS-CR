/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MDI_PUNTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiPunto.findAll", query = "SELECT m FROM MdiPunto m"),
    @NamedQuery(name = "MdiPunto.findByIdPunto", query = "SELECT m FROM MdiPunto m WHERE m.idPunto = :idPunto"),
    @NamedQuery(name = "MdiPunto.findByCoordenadaX", query = "SELECT m FROM MdiPunto m WHERE m.coordenadaX = :coordenadaX"),
    @NamedQuery(name = "MdiPunto.findByCoordenadaY", query = "SELECT m FROM MdiPunto m WHERE m.coordenadaY = :coordenadaY"),
    @NamedQuery(name = "MdiPunto.findByFechaCreacion", query = "SELECT m FROM MdiPunto m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiPunto.findByFechaActualizacion", query = "SELECT m FROM MdiPunto m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiPunto.findByTerminalActualizacion", query = "SELECT m FROM MdiPunto m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiPunto.findByUsuarioActualizacion", query = "SELECT m FROM MdiPunto m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiPunto.findByUsuarioCreacion", query = "SELECT m FROM MdiPunto m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiPunto.findByTerminalCreacion", query = "SELECT m FROM MdiPunto m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiPunto.findByEstado", query = "SELECT m FROM MdiPunto m WHERE m.estado = :estado")})
public class MdiPunto extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PUNTO")
    private Long idPunto;
    @Column(name = "COORDENADA_X")
    private Long coordenadaX;
    @Column(name = "COORDENADA_Y")
    private Long coordenadaY;    
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @JoinColumn(name = "ID_COORDENADA", referencedColumnName = "ID_COORDENADA")
    @ManyToOne
    private MdiCoordenada idCoordenada;

    public MdiPunto() {
    }

    public MdiPunto(Long idPunto) {
        this.idPunto = idPunto;
    }

    public MdiPunto(Long idPunto, Date fechaCreacion, String usuarioCreacion, String terminalCreacion, char estado) {
        this.idPunto = idPunto;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdPunto() {
        return idPunto;
    }

    public void setIdPunto(Long idPunto) {
        this.idPunto = idPunto;
    }

    public Long getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Long coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Long getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Long coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public MdiCoordenada getIdCoordenada() {
        return idCoordenada;
    }

    public void setIdCoordenada(MdiCoordenada idCoordenada) {
        this.idCoordenada = idCoordenada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPunto != null ? idPunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiPunto)) {
            return false;
        }
        MdiPunto other = (MdiPunto) object;
        if ((this.idPunto == null && other.idPunto != null) || (this.idPunto != null && !this.idPunto.equals(other.idPunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiPunto[ idPunto=" + idPunto + " ]";
    }
    
}

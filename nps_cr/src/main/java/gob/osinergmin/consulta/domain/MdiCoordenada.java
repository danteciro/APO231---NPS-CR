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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MDI_COORDENADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiCoordenada.findAll", query = "SELECT m FROM MdiCoordenada m"),
    @NamedQuery(name = "MdiCoordenada.findByIdCoordenada", query = "SELECT m FROM MdiCoordenada m WHERE m.idCoordenada = :idCoordenada"),
    @NamedQuery(name = "MdiCoordenada.findByUsuarioActualizacion", query = "SELECT m FROM MdiCoordenada m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiCoordenada.findByUsuarioCreacion", query = "SELECT m FROM MdiCoordenada m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiCoordenada.findByFechaCreacion", query = "SELECT m FROM MdiCoordenada m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiCoordenada.findByFechaActualizacion", query = "SELECT m FROM MdiCoordenada m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiCoordenada.findByTerminalCreacion", query = "SELECT m FROM MdiCoordenada m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiCoordenada.findByTerminalActualizacion", query = "SELECT m FROM MdiCoordenada m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiCoordenada.findByBanda", query = "SELECT m FROM MdiCoordenada m WHERE m.banda = :banda"),
    @NamedQuery(name = "MdiCoordenada.findByTipoCoordenada", query = "SELECT m FROM MdiCoordenada m WHERE m.tipoCoordenada = :tipoCoordenada"),
    @NamedQuery(name = "MdiCoordenada.findByEstado", query = "SELECT m FROM MdiCoordenada m WHERE m.estado = :estado")})
public class MdiCoordenada extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_COORDENADA")
    private Long idCoordenada;    
    @Column(name = "BANDA")
    private Character banda;
    @Column(name = "TIPO_COORDENADA")
    private Long tipoCoordenada;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(mappedBy = "idCoordenada")
    private List<MdiPunto> mdiPuntoList;
    @JoinColumn(name = "ID_DIRECCION_REGION", referencedColumnName = "ID_DIRECCION_REGION")
    @ManyToOne
    private MdiDireccionRegion idDireccionRegion;
    @JoinColumn(name = "MDI_DIRECCION_EMP_SUPERVISADA", referencedColumnName = "ID_DIRECCION_EMP_SUPERVISADA")
    @ManyToOne
    private MdiDireccionEmpSupervisada mdiDireccionEmpSupervisada;
    @JoinColumn(name = "ID_DIRCCION_UNIDAD_SUPRVISADA", referencedColumnName = "ID_DIRCCION_UNIDAD_SUPRVISADA")
    @ManyToOne
    private MdiDirccionUnidadSuprvisada idDirccionUnidadSuprvisada;

    public MdiCoordenada() {
    }

    public MdiCoordenada(Long idCoordenada) {
        this.idCoordenada = idCoordenada;
    }

    public MdiCoordenada(Long idCoordenada, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, char estado) {
        this.idCoordenada = idCoordenada;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public Long getIdCoordenada() {
        return idCoordenada;
    }

    public void setIdCoordenada(Long idCoordenada) {
        this.idCoordenada = idCoordenada;
    }

    public Character getBanda() {
        return banda;
    }

    public void setBanda(Character banda) {
        this.banda = banda;
    }

    public Long getTipoCoordenada() {
        return tipoCoordenada;
    }

    public void setTipoCoordenada(Long tipoCoordenada) {
        this.tipoCoordenada = tipoCoordenada;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiPunto> getMdiPuntoList() {
        return mdiPuntoList;
    }

    public void setMdiPuntoList(List<MdiPunto> mdiPuntoList) {
        this.mdiPuntoList = mdiPuntoList;
    }

    public MdiDireccionRegion getIdDireccionRegion() {
        return idDireccionRegion;
    }

    public void setIdDireccionRegion(MdiDireccionRegion idDireccionRegion) {
        this.idDireccionRegion = idDireccionRegion;
    }

    public MdiDireccionEmpSupervisada getMdiDireccionEmpSupervisada() {
        return mdiDireccionEmpSupervisada;
    }

    public void setMdiDireccionEmpSupervisada(MdiDireccionEmpSupervisada mdiDireccionEmpSupervisada) {
        this.mdiDireccionEmpSupervisada = mdiDireccionEmpSupervisada;
    }

    public MdiDirccionUnidadSuprvisada getIdDirccionUnidadSuprvisada() {
        return idDirccionUnidadSuprvisada;
    }

    public void setIdDirccionUnidadSuprvisada(MdiDirccionUnidadSuprvisada idDirccionUnidadSuprvisada) {
        this.idDirccionUnidadSuprvisada = idDirccionUnidadSuprvisada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoordenada != null ? idCoordenada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiCoordenada)) {
            return false;
        }
        MdiCoordenada other = (MdiCoordenada) object;
        if ((this.idCoordenada == null && other.idCoordenada != null) || (this.idCoordenada != null && !this.idCoordenada.equals(other.idCoordenada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiCoordenada[ idCoordenada=" + idCoordenada + " ]";
    }
    
}

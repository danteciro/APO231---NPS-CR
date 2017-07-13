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
@Table(name = "MDI_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiProducto.findAll", query = "SELECT m FROM MdiProducto m"),
    @NamedQuery(name = "MdiProducto.findByDedicado", query = "SELECT m FROM MdiProducto m WHERE m.dedicado = :dedicado"),
    @NamedQuery(name = "MdiProducto.findByNombreLargo", query = "SELECT m FROM MdiProducto m WHERE m.nombreLargo = :nombreLargo"),
    @NamedQuery(name = "MdiProducto.findByNombreCorto", query = "SELECT m FROM MdiProducto m WHERE m.nombreCorto = :nombreCorto"),
    @NamedQuery(name = "MdiProducto.findByCodigo", query = "SELECT m FROM MdiProducto m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MdiProducto.findByIdProducto", query = "SELECT m FROM MdiProducto m WHERE m.idProducto = :idProducto"),
    @NamedQuery(name = "MdiProducto.findByFechaCreacion", query = "SELECT m FROM MdiProducto m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiProducto.findByFechaActualizacion", query = "SELECT m FROM MdiProducto m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiProducto.findByUsuarioCreacion", query = "SELECT m FROM MdiProducto m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiProducto.findByUsuarioActualizacion", query = "SELECT m FROM MdiProducto m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiProducto.findByTerminalActualizacion", query = "SELECT m FROM MdiProducto m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiProducto.findByTerminalCreacion", query = "SELECT m FROM MdiProducto m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiProducto.findByNivel", query = "SELECT m FROM MdiProducto m WHERE m.nivel = :nivel"),
    @NamedQuery(name = "MdiProducto.findByEstado", query = "SELECT m FROM MdiProducto m WHERE m.estado = :estado")})
public class MdiProducto extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "DEDICADO")
    private String dedicado;
    @Basic(optional = false)
    @Column(name = "NOMBRE_LARGO")
    private String nombreLargo;
    @Basic(optional = false)
    @Column(name = "NOMBRE_CORTO")
    private String nombreCorto;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PRODUCTO")
    private Long idProducto;    
    @Basic(optional = false)
    @Column(name = "NIVEL")
    private int nivel;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @OneToMany(mappedBy = "idProductoPadre")
    private List<MdiProducto> mdiProductoList;
    @JoinColumn(name = "ID_PRODUCTO_PADRE", referencedColumnName = "ID_PRODUCTO")
    @ManyToOne
    private MdiProducto idProductoPadre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiProducto")
    private List<MdiProductoNivel> mdiProductoNivelList;

    public MdiProducto() {
    }

    public MdiProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public MdiProducto(Long idProducto, String dedicado, String nombreLargo, String nombreCorto, String codigo, Date fechaCreacion, String usuarioCreacion, String terminalCreacion, int nivel, char estado) {
        this.idProducto = idProducto;
        this.dedicado = dedicado;
        this.nombreLargo = nombreLargo;
        this.nombreCorto = nombreCorto;
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.nivel = nivel;
        this.estado = estado;
    }

    public String getDedicado() {
        return dedicado;
    }

    public void setDedicado(String dedicado) {
        this.dedicado = dedicado;
    }

    public String getNombreLargo() {
        return nombreLargo;
    }

    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiProducto> getMdiProductoList() {
        return mdiProductoList;
    }

    public void setMdiProductoList(List<MdiProducto> mdiProductoList) {
        this.mdiProductoList = mdiProductoList;
    }

    public MdiProducto getIdProductoPadre() {
        return idProductoPadre;
    }

    public void setIdProductoPadre(MdiProducto idProductoPadre) {
        this.idProductoPadre = idProductoPadre;
    }

    @XmlTransient
    public List<MdiProductoNivel> getMdiProductoNivelList() {
        return mdiProductoNivelList;
    }

    public void setMdiProductoNivelList(List<MdiProductoNivel> mdiProductoNivelList) {
        this.mdiProductoNivelList = mdiProductoNivelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiProducto)) {
            return false;
        }
        MdiProducto other = (MdiProducto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiProducto[ idProducto=" + idProducto + " ]";
    }
    
}

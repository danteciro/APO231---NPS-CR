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
import javax.persistence.JoinColumns;
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
@Table(name = "MDI_DIRECCION_REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiDireccionRegion.findAll", query = "SELECT m FROM MdiDireccionRegion m"),
    @NamedQuery(name = "MdiDireccionRegion.findByIdDireccionRegion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.idDireccionRegion = :idDireccionRegion"),
    @NamedQuery(name = "MdiDireccionRegion.findByNumeroVia", query = "SELECT m FROM MdiDireccionRegion m WHERE m.numeroVia = :numeroVia"),
    @NamedQuery(name = "MdiDireccionRegion.findByNumeroDepartamento", query = "SELECT m FROM MdiDireccionRegion m WHERE m.numeroDepartamento = :numeroDepartamento"),
    @NamedQuery(name = "MdiDireccionRegion.findByManzana", query = "SELECT m FROM MdiDireccionRegion m WHERE m.manzana = :manzana"),
    @NamedQuery(name = "MdiDireccionRegion.findByLote", query = "SELECT m FROM MdiDireccionRegion m WHERE m.lote = :lote"),
    @NamedQuery(name = "MdiDireccionRegion.findByKilometro", query = "SELECT m FROM MdiDireccionRegion m WHERE m.kilometro = :kilometro"),
    @NamedQuery(name = "MdiDireccionRegion.findByInterior", query = "SELECT m FROM MdiDireccionRegion m WHERE m.interior = :interior"),
    @NamedQuery(name = "MdiDireccionRegion.findByEtapa", query = "SELECT m FROM MdiDireccionRegion m WHERE m.etapa = :etapa"),
    @NamedQuery(name = "MdiDireccionRegion.findByReferencia", query = "SELECT m FROM MdiDireccionRegion m WHERE m.referencia = :referencia"),
    @NamedQuery(name = "MdiDireccionRegion.findByUrbanizacion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.urbanizacion = :urbanizacion"),
    @NamedQuery(name = "MdiDireccionRegion.findByUsuarioActualizacion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiDireccionRegion.findByUsuarioCreacion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiDireccionRegion.findByFechaCreacion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiDireccionRegion.findByFechaActualizacion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiDireccionRegion.findByDescripcion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "MdiDireccionRegion.findByTerminalCreacion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiDireccionRegion.findByTerminalActualizacion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiDireccionRegion.findByNombreVia", query = "SELECT m FROM MdiDireccionRegion m WHERE m.nombreVia = :nombreVia"),
    @NamedQuery(name = "MdiDireccionRegion.findByBlockChalet", query = "SELECT m FROM MdiDireccionRegion m WHERE m.blockChalet = :blockChalet"),
    @NamedQuery(name = "MdiDireccionRegion.findByDireccionCompleta", query = "SELECT m FROM MdiDireccionRegion m WHERE m.direccionCompleta = :direccionCompleta"),
    @NamedQuery(name = "MdiDireccionRegion.findByIdTipoDireccion", query = "SELECT m FROM MdiDireccionRegion m WHERE m.idTipoDireccion = :idTipoDireccion"),
    @NamedQuery(name = "MdiDireccionRegion.findByIdTipoVia", query = "SELECT m FROM MdiDireccionRegion m WHERE m.idTipoVia = :idTipoVia"),
    @NamedQuery(name = "MdiDireccionRegion.findByEstado", query = "SELECT m FROM MdiDireccionRegion m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiDireccionRegion.findByDescripcionVia", query = "SELECT m FROM MdiDireccionRegion m WHERE m.descripcionVia = :descripcionVia")})
public class MdiDireccionRegion extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DIRECCION_REGION")
    private Long idDireccionRegion;
    @Basic(optional = false)
    @Column(name = "NUMERO_VIA")
    private String numeroVia;
    @Basic(optional = false)
    @Column(name = "NUMERO_DEPARTAMENTO")
    private String numeroDepartamento;
    @Basic(optional = false)
    @Column(name = "MANZANA")
    private String manzana;
    @Basic(optional = false)
    @Column(name = "LOTE")
    private String lote;
    @Basic(optional = false)
    @Column(name = "KILOMETRO")
    private String kilometro;
    @Basic(optional = false)
    @Column(name = "INTERIOR")
    private String interior;
    @Basic(optional = false)
    @Column(name = "ETAPA")
    private String etapa;
    @Basic(optional = false)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Basic(optional = false)
    @Column(name = "URBANIZACION")
    private String urbanizacion;    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "NOMBRE_VIA")
    private char nombreVia;
    @Basic(optional = false)
    @Column(name = "BLOCK_CHALET")
    private String blockChalet;
    @Basic(optional = false)
    @Column(name = "DIRECCION_COMPLETA")
    private String direccionCompleta;
    @Column(name = "ID_TIPO_DIRECCION")
    private Long idTipoDireccion;
    @Column(name = "ID_TIPO_VIA")
    private Long idTipoVia;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_VIA")
    private String descripcionVia;
    @JoinColumns({
        @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO"),
        @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA"),
        @JoinColumn(name = "ID_DISTRITO", referencedColumnName = "ID_DISTRITO")})
    @ManyToOne
    private MdiUbigeo mdiUbigeo;
    @JoinColumn(name = "ID_REGION", referencedColumnName = "ID_REGION")
    @ManyToOne
    private MdiRegion idRegion;
    @OneToMany(mappedBy = "idDireccionRegion")
    private List<MdiCoordenada> mdiCoordenadaList;

    public MdiDireccionRegion() {
    }

    public MdiDireccionRegion(Long idDireccionRegion) {
        this.idDireccionRegion = idDireccionRegion;
    }

    public MdiDireccionRegion(Long idDireccionRegion, String numeroVia, String numeroDepartamento, String manzana, String lote, String kilometro, String interior, String etapa, String referencia, String urbanizacion, String descripcion, String terminalCreacion, String terminalActualizacion, char nombreVia, String blockChalet, String direccionCompleta, char estado, String descripcionVia) {
        this.idDireccionRegion = idDireccionRegion;
        this.numeroVia = numeroVia;
        this.numeroDepartamento = numeroDepartamento;
        this.manzana = manzana;
        this.lote = lote;
        this.kilometro = kilometro;
        this.interior = interior;
        this.etapa = etapa;
        this.referencia = referencia;
        this.urbanizacion = urbanizacion;
        this.descripcion = descripcion;
        this.terminalCreacion = terminalCreacion;
        this.terminalActualizacion = terminalActualizacion;
        this.nombreVia = nombreVia;
        this.blockChalet = blockChalet;
        this.direccionCompleta = direccionCompleta;
        this.estado = estado;
        this.descripcionVia = descripcionVia;
    }

    public Long getIdDireccionRegion() {
        return idDireccionRegion;
    }

    public void setIdDireccionRegion(Long idDireccionRegion) {
        this.idDireccionRegion = idDireccionRegion;
    }

    public String getNumeroVia() {
        return numeroVia;
    }

    public void setNumeroVia(String numeroVia) {
        this.numeroVia = numeroVia;
    }

    public String getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(String numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getKilometro() {
        return kilometro;
    }

    public void setKilometro(String kilometro) {
        this.kilometro = kilometro;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }    

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public char getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(char nombreVia) {
        this.nombreVia = nombreVia;
    }

    public String getBlockChalet() {
        return blockChalet;
    }

    public void setBlockChalet(String blockChalet) {
        this.blockChalet = blockChalet;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public Long getIdTipoDireccion() {
        return idTipoDireccion;
    }

    public void setIdTipoDireccion(Long idTipoDireccion) {
        this.idTipoDireccion = idTipoDireccion;
    }

    public Long getIdTipoVia() {
        return idTipoVia;
    }

    public void setIdTipoVia(Long idTipoVia) {
        this.idTipoVia = idTipoVia;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getDescripcionVia() {
        return descripcionVia;
    }

    public void setDescripcionVia(String descripcionVia) {
        this.descripcionVia = descripcionVia;
    }

    public MdiUbigeo getMdiUbigeo() {
        return mdiUbigeo;
    }

    public void setMdiUbigeo(MdiUbigeo mdiUbigeo) {
        this.mdiUbigeo = mdiUbigeo;
    }

    public MdiRegion getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(MdiRegion idRegion) {
        this.idRegion = idRegion;
    }

    @XmlTransient
    public List<MdiCoordenada> getMdiCoordenadaList() {
        return mdiCoordenadaList;
    }

    public void setMdiCoordenadaList(List<MdiCoordenada> mdiCoordenadaList) {
        this.mdiCoordenadaList = mdiCoordenadaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccionRegion != null ? idDireccionRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiDireccionRegion)) {
            return false;
        }
        MdiDireccionRegion other = (MdiDireccionRegion) object;
        if ((this.idDireccionRegion == null && other.idDireccionRegion != null) || (this.idDireccionRegion != null && !this.idDireccionRegion.equals(other.idDireccionRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiDireccionRegion[ idDireccionRegion=" + idDireccionRegion + " ]";
    }
    
}

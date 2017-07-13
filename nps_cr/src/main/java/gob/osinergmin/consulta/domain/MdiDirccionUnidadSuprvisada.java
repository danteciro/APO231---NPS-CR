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
@Table(name = "MDI_DIRCCION_UNIDAD_SUPRVISADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findAll", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByIdDirccionUnidadSuprvisada", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.idDirccionUnidadSuprvisada = :idDirccionUnidadSuprvisada"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByEtapa", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.etapa = :etapa"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByReferencia", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.referencia = :referencia"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByUrbanizacion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.urbanizacion = :urbanizacion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByFechaActualizacion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByFechaCreacion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByUsuarioCreacion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByUsuarioActualizacion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByTerminalCreacion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByTerminalActualizacion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByDireccionCompleta", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.direccionCompleta = :direccionCompleta"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByIdTipoDireccion", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.idTipoDireccion = :idTipoDireccion"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByEstado", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByIdTipoVia", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.idTipoVia = :idTipoVia"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByNumeroVia", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.numeroVia = :numeroVia"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByNumeroDepartamento", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.numeroDepartamento = :numeroDepartamento"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByLote", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.lote = :lote"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByManzana", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.manzana = :manzana"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByInterior", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.interior = :interior"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByKilometro", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.kilometro = :kilometro"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByBlockChallet", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.blockChallet = :blockChallet"),
    @NamedQuery(name = "MdiDirccionUnidadSuprvisada.findByDescripcionVia", query = "SELECT m FROM MdiDirccionUnidadSuprvisada m WHERE m.descripcionVia = :descripcionVia")})
public class MdiDirccionUnidadSuprvisada extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DIRCCION_UNIDAD_SUPRVISADA")
    private Long idDirccionUnidadSuprvisada;
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
    @Column(name = "DIRECCION_COMPLETA")
    private String direccionCompleta;
    @Basic(optional = false)
    @Column(name = "ID_TIPO_DIRECCION")
    private long idTipoDireccion;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Column(name = "ID_TIPO_VIA")
    private Long idTipoVia;
    @Basic(optional = false)
    @Column(name = "NUMERO_VIA")
    private String numeroVia;
    @Basic(optional = false)
    @Column(name = "NUMERO_DEPARTAMENTO")
    private String numeroDepartamento;
    @Basic(optional = false)
    @Column(name = "LOTE")
    private String lote;
    @Basic(optional = false)
    @Column(name = "MANZANA")
    private String manzana;
    @Basic(optional = false)
    @Column(name = "INTERIOR")
    private String interior;
    @Basic(optional = false)
    @Column(name = "KILOMETRO")
    private String kilometro;
    @Basic(optional = false)
    @Column(name = "BLOCK_CHALLET")
    private String blockChallet;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_VIA")
    private String descripcionVia;
    @JoinColumn(name = "ID_UNIDAD_SUPERVISADA", referencedColumnName = "ID_UNIDAD_SUPERVISADA")
    @ManyToOne(optional = false)
    private MdiUnidadSupervisada idUnidadSupervisada;
    @JoinColumns({
        @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO"),
        @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA"),
        @JoinColumn(name = "ID_DISTRITO", referencedColumnName = "ID_DISTRITO")})
    @ManyToOne(optional = false)
    private MdiUbigeo mdiUbigeo;
    @OneToMany(mappedBy = "idDirccionUnidadSuprvisada")
    private List<MdiCoordenada> mdiCoordenadaList;

    public MdiDirccionUnidadSuprvisada() {
    }

    public MdiDirccionUnidadSuprvisada(Long idDirccionUnidadSuprvisada) {
        this.idDirccionUnidadSuprvisada = idDirccionUnidadSuprvisada;
    }

    public MdiDirccionUnidadSuprvisada(Long idDirccionUnidadSuprvisada, String etapa, String referencia, String urbanizacion, Date fechaCreacion, String usuarioCreacion, String terminalCreacion, String terminalActualizacion, String direccionCompleta, long idTipoDireccion, char estado, String numeroVia, String numeroDepartamento, String lote, String manzana, String interior, String kilometro, String blockChallet, String descripcionVia) {
        this.idDirccionUnidadSuprvisada = idDirccionUnidadSuprvisada;
        this.etapa = etapa;
        this.referencia = referencia;
        this.urbanizacion = urbanizacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.terminalActualizacion = terminalActualizacion;
        this.direccionCompleta = direccionCompleta;
        this.idTipoDireccion = idTipoDireccion;
        this.estado = estado;
        this.numeroVia = numeroVia;
        this.numeroDepartamento = numeroDepartamento;
        this.lote = lote;
        this.manzana = manzana;
        this.interior = interior;
        this.kilometro = kilometro;
        this.blockChallet = blockChallet;
        this.descripcionVia = descripcionVia;
    }

    public Long getIdDirccionUnidadSuprvisada() {
        return idDirccionUnidadSuprvisada;
    }

    public void setIdDirccionUnidadSuprvisada(Long idDirccionUnidadSuprvisada) {
        this.idDirccionUnidadSuprvisada = idDirccionUnidadSuprvisada;
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

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public long getIdTipoDireccion() {
        return idTipoDireccion;
    }

    public void setIdTipoDireccion(long idTipoDireccion) {
        this.idTipoDireccion = idTipoDireccion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Long getIdTipoVia() {
        return idTipoVia;
    }

    public void setIdTipoVia(Long idTipoVia) {
        this.idTipoVia = idTipoVia;
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

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getKilometro() {
        return kilometro;
    }

    public void setKilometro(String kilometro) {
        this.kilometro = kilometro;
    }

    public String getBlockChallet() {
        return blockChallet;
    }

    public void setBlockChallet(String blockChallet) {
        this.blockChallet = blockChallet;
    }

    public String getDescripcionVia() {
        return descripcionVia;
    }

    public void setDescripcionVia(String descripcionVia) {
        this.descripcionVia = descripcionVia;
    }

    public MdiUnidadSupervisada getIdUnidadSupervisada() {
        return idUnidadSupervisada;
    }

    public void setIdUnidadSupervisada(MdiUnidadSupervisada idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }

    public MdiUbigeo getMdiUbigeo() {
        return mdiUbigeo;
    }

    public void setMdiUbigeo(MdiUbigeo mdiUbigeo) {
        this.mdiUbigeo = mdiUbigeo;
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
        hash += (idDirccionUnidadSuprvisada != null ? idDirccionUnidadSuprvisada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiDirccionUnidadSuprvisada)) {
            return false;
        }
        MdiDirccionUnidadSuprvisada other = (MdiDirccionUnidadSuprvisada) object;
        if ((this.idDirccionUnidadSuprvisada == null && other.idDirccionUnidadSuprvisada != null) || (this.idDirccionUnidadSuprvisada != null && !this.idDirccionUnidadSuprvisada.equals(other.idDirccionUnidadSuprvisada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiDirccionUnidadSuprvisada[ idDirccionUnidadSuprvisada=" + idDirccionUnidadSuprvisada + " ]";
    }
    
}

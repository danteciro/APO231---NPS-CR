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
@Table(name = "MDI_DIRECCION_EMP_SUPERVISADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findAll", query = "SELECT m FROM MdiDireccionEmpSupervisada m"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByIdDireccionEmpSupervisada", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.idDireccionEmpSupervisada = :idDireccionEmpSupervisada"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByNumeroVia", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.numeroVia = :numeroVia"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByNumeroDepartamento", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.numeroDepartamento = :numeroDepartamento"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByManzana", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.manzana = :manzana"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByLote", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.lote = :lote"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByKilometro", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.kilometro = :kilometro"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByInterior", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.interior = :interior"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByEtapa", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.etapa = :etapa"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByReferencia", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.referencia = :referencia"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByUrbanizacion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.urbanizacion = :urbanizacion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByUsuarioActualizacion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByUsuarioCreacion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByFechaCreacion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByFechaActualizacion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByTerminalCreacion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByTerminalActualizacion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByBlockChalet", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.blockChalet = :blockChalet"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByDireccionCompleta", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.direccionCompleta = :direccionCompleta"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByIdTipoDireccion", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.idTipoDireccion = :idTipoDireccion"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByDescripcionVia", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.descripcionVia = :descripcionVia"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByEstado", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiDireccionEmpSupervisada.findByIdTipoVia", query = "SELECT m FROM MdiDireccionEmpSupervisada m WHERE m.idTipoVia = :idTipoVia")})
public class MdiDireccionEmpSupervisada extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_DIRECCION_EMP_SUPERVISADA")
    private Long idDireccionEmpSupervisada;
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
    @Column(name = "BLOCK_CHALET")
    private String blockChalet;
    @Basic(optional = false)
    @Column(name = "DIRECCION_COMPLETA")
    private String direccionCompleta;
    @Column(name = "ID_TIPO_DIRECCION")
    private Long idTipoDireccion;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION_VIA")
    private String descripcionVia;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Column(name = "ID_TIPO_VIA")
    private Long idTipoVia;
    @JoinColumns({
        @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO"),
        @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA"),
        @JoinColumn(name = "ID_DISTRITO", referencedColumnName = "ID_DISTRITO")})
    @ManyToOne
    private MdiUbigeo mdiUbigeo;
    @JoinColumn(name = "ID_EMPRESA_SUPERVISADA", referencedColumnName = "ID_EMPRESA_SUPERVISADA")
    @ManyToOne(optional = false)
    private MdiEmpresaSupervisada idEmpresaSupervisada;
    @OneToMany(mappedBy = "mdiDireccionEmpSupervisada")
    private List<MdiCoordenada> mdiCoordenadaList;

    public MdiDireccionEmpSupervisada() {
    }

    public MdiDireccionEmpSupervisada(Long idDireccionEmpSupervisada) {
        this.idDireccionEmpSupervisada = idDireccionEmpSupervisada;
    }

    public MdiDireccionEmpSupervisada(Long idDireccionEmpSupervisada, String numeroVia, String numeroDepartamento, String manzana, String lote, String kilometro, String interior, String etapa, String referencia, String urbanizacion, String usuarioCreacion, Date fechaCreacion, String terminalCreacion, String blockChalet, String direccionCompleta, String descripcionVia, char estado) {
        this.idDireccionEmpSupervisada = idDireccionEmpSupervisada;
        this.numeroVia = numeroVia;
        this.numeroDepartamento = numeroDepartamento;
        this.manzana = manzana;
        this.lote = lote;
        this.kilometro = kilometro;
        this.interior = interior;
        this.etapa = etapa;
        this.referencia = referencia;
        this.urbanizacion = urbanizacion;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
        this.blockChalet = blockChalet;
        this.direccionCompleta = direccionCompleta;
        this.descripcionVia = descripcionVia;
        this.estado = estado;
    }

    public Long getIdDireccionEmpSupervisada() {
        return idDireccionEmpSupervisada;
    }

    public void setIdDireccionEmpSupervisada(Long idDireccionEmpSupervisada) {
        this.idDireccionEmpSupervisada = idDireccionEmpSupervisada;
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

    public String getDescripcionVia() {
        return descripcionVia;
    }

    public void setDescripcionVia(String descripcionVia) {
        this.descripcionVia = descripcionVia;
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

    public MdiUbigeo getMdiUbigeo() {
        return mdiUbigeo;
    }

    public void setMdiUbigeo(MdiUbigeo mdiUbigeo) {
        this.mdiUbigeo = mdiUbigeo;
    }

    public MdiEmpresaSupervisada getIdEmpresaSupervisada() {
        return idEmpresaSupervisada;
    }

    public void setIdEmpresaSupervisada(MdiEmpresaSupervisada idEmpresaSupervisada) {
        this.idEmpresaSupervisada = idEmpresaSupervisada;
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
        hash += (idDireccionEmpSupervisada != null ? idDireccionEmpSupervisada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiDireccionEmpSupervisada)) {
            return false;
        }
        MdiDireccionEmpSupervisada other = (MdiDireccionEmpSupervisada) object;
        if ((this.idDireccionEmpSupervisada == null && other.idDireccionEmpSupervisada != null) || (this.idDireccionEmpSupervisada != null && !this.idDireccionEmpSupervisada.equals(other.idDireccionEmpSupervisada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiDireccionEmpSupervisada[ idDireccionEmpSupervisada=" + idDireccionEmpSupervisada + " ]";
    }
    
}

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
import javax.persistence.JoinColumns;
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
@Table(name = "MDI_SEDE_EMPRESA_LOCADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiSedeEmpresaLocador.findAll", query = "SELECT m FROM MdiSedeEmpresaLocador m"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByIdSedeEmpresaLocador", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.idSedeEmpresaLocador = :idSedeEmpresaLocador"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByIdTipoDireccion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.idTipoDireccion = :idTipoDireccion"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByNumeroVia", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.numeroVia = :numeroVia"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByIdTipoVia", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.idTipoVia = :idTipoVia"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByNumeroDepartamento", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.numeroDepartamento = :numeroDepartamento"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByManzana", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.manzana = :manzana"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByLote", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.lote = :lote"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByKilometro", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.kilometro = :kilometro"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByInterior", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.interior = :interior"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByBlockChalet", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.blockChalet = :blockChalet"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByEtapa", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.etapa = :etapa"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByReferencia", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.referencia = :referencia"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByUrbanizacion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.urbanizacion = :urbanizacion"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByDescripcionVia", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.descripcionVia = :descripcionVia"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByFax", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.fax = :fax"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByDireccionCompleta", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.direccionCompleta = :direccionCompleta"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByTelefono", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.telefono = :telefono"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByEstado", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByUsuarioCreacion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByFechaCreacion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByUsuarioActualizacion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByTerminalCreacion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByTerminalActualizacion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiSedeEmpresaLocador.findByFechaActualizacion", query = "SELECT m FROM MdiSedeEmpresaLocador m WHERE m.fechaActualizacion = :fechaActualizacion")})
public class MdiSedeEmpresaLocador extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SEDE_EMPRESA_LOCADOR")
    private Long idSedeEmpresaLocador;
    @Basic(optional = false)
    @Column(name = "ID_TIPO_DIRECCION")
    private long idTipoDireccion;
    @Column(name = "NUMERO_VIA")
    private String numeroVia;
    @Column(name = "ID_TIPO_VIA")
    private Long idTipoVia;
    @Column(name = "NUMERO_DEPARTAMENTO")
    private String numeroDepartamento;
    @Column(name = "MANZANA")
    private String manzana;
    @Column(name = "LOTE")
    private String lote;
    @Column(name = "KILOMETRO")
    private String kilometro;
    @Column(name = "INTERIOR")
    private String interior;
    @Column(name = "BLOCK_CHALET")
    private String blockChalet;
    @Column(name = "ETAPA")
    private String etapa;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "URBANIZACION")
    private String urbanizacion;
    @Column(name = "DESCRIPCION_VIA")
    private String descripcionVia;
    @Column(name = "FAX")
    private String fax;
    @Column(name = "DIRECCION_COMPLETA")
    private String direccionCompleta;
    @Column(name = "TELEFONO")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;   
    @JoinColumns({
        @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO"),
        @JoinColumn(name = "ID_PROVINCIA", referencedColumnName = "ID_PROVINCIA"),
        @JoinColumn(name = "ID_DISTRITO", referencedColumnName = "ID_DISTRITO")})
    @ManyToOne(optional = false)
    private MdiUbigeo mdiUbigeo;
    @JoinColumn(name = "ID_SUPERVISORA_EMPRESA", referencedColumnName = "ID_SUPERVISORA_EMPRESA")
    @ManyToOne
    private MdiSupervisoraEmpresa idSupervisoraEmpresa;
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR")
    @ManyToOne
    private MdiLocador idLocador;

    public MdiSedeEmpresaLocador() {
    }

    public MdiSedeEmpresaLocador(Long idSedeEmpresaLocador) {
        this.idSedeEmpresaLocador = idSedeEmpresaLocador;
    }

    public MdiSedeEmpresaLocador(Long idSedeEmpresaLocador, long idTipoDireccion, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idSedeEmpresaLocador = idSedeEmpresaLocador;
        this.idTipoDireccion = idTipoDireccion;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdSedeEmpresaLocador() {
        return idSedeEmpresaLocador;
    }

    public void setIdSedeEmpresaLocador(Long idSedeEmpresaLocador) {
        this.idSedeEmpresaLocador = idSedeEmpresaLocador;
    }

    public long getIdTipoDireccion() {
        return idTipoDireccion;
    }

    public void setIdTipoDireccion(long idTipoDireccion) {
        this.idTipoDireccion = idTipoDireccion;
    }

    public String getNumeroVia() {
        return numeroVia;
    }

    public void setNumeroVia(String numeroVia) {
        this.numeroVia = numeroVia;
    }

    public Long getIdTipoVia() {
        return idTipoVia;
    }

    public void setIdTipoVia(Long idTipoVia) {
        this.idTipoVia = idTipoVia;
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

    public String getBlockChalet() {
        return blockChalet;
    }

    public void setBlockChalet(String blockChalet) {
        this.blockChalet = blockChalet;
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

    public String getDescripcionVia() {
        return descripcionVia;
    }

    public void setDescripcionVia(String descripcionVia) {
        this.descripcionVia = descripcionVia;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public MdiUbigeo getMdiUbigeo() {
        return mdiUbigeo;
    }

    public void setMdiUbigeo(MdiUbigeo mdiUbigeo) {
        this.mdiUbigeo = mdiUbigeo;
    }

    public MdiSupervisoraEmpresa getIdSupervisoraEmpresa() {
        return idSupervisoraEmpresa;
    }

    public void setIdSupervisoraEmpresa(MdiSupervisoraEmpresa idSupervisoraEmpresa) {
        this.idSupervisoraEmpresa = idSupervisoraEmpresa;
    }

    public MdiLocador getIdLocador() {
        return idLocador;
    }

    public void setIdLocador(MdiLocador idLocador) {
        this.idLocador = idLocador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSedeEmpresaLocador != null ? idSedeEmpresaLocador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiSedeEmpresaLocador)) {
            return false;
        }
        MdiSedeEmpresaLocador other = (MdiSedeEmpresaLocador) object;
        if ((this.idSedeEmpresaLocador == null && other.idSedeEmpresaLocador != null) || (this.idSedeEmpresaLocador != null && !this.idSedeEmpresaLocador.equals(other.idSedeEmpresaLocador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiSedeEmpresaLocador[ idSedeEmpresaLocador=" + idSedeEmpresaLocador + " ]";
    }
    
}

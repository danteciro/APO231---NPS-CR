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
@Table(name = "MDI_SUPERVISORA_EMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiSupervisoraEmpresa.findAll", query = "SELECT m FROM MdiSupervisoraEmpresa m"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByIdSupervisoraEmpresa", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.idSupervisoraEmpresa = :idSupervisoraEmpresa"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByTipoEmpresaConstitucion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.tipoEmpresaConstitucion = :tipoEmpresaConstitucion"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByRazonSocial", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.razonSocial = :razonSocial"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByRuc", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.ruc = :ruc"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByCiiuPrincipal", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.ciiuPrincipal = :ciiuPrincipal"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByPaginaWeb", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.paginaWeb = :paginaWeb"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByEstado", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByUsuarioCreacion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByFechaCreacion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByTerminalCreacion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByUsuarioActualizacion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByFechaActualizacion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByTerminalActualizacion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiSupervisoraEmpresa.findByOrigenInformacion", query = "SELECT m FROM MdiSupervisoraEmpresa m WHERE m.origenInformacion = :origenInformacion")})
public class MdiSupervisoraEmpresa extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SUPERVISORA_EMPRESA")
    private Long idSupervisoraEmpresa;
    @Basic(optional = false)
    @Column(name = "TIPO_EMPRESA_CONSTITUCION")
    private long tipoEmpresaConstitucion;
    @Basic(optional = false)
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Basic(optional = false)
    @Column(name = "RUC")
    private String ruc;
    @Column(name = "CIIU_PRINCIPAL")
    private String ciiuPrincipal;
    @Column(name = "PAGINA_WEB")
    private String paginaWeb;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @Column(name = "ORIGEN_INFORMACION")
    private String origenInformacion;
    @OneToMany(mappedBy = "idSupervisoraEmpresa")
    private List<MdiEmpresaContacto> mdiEmpresaContactoList;
    @OneToMany(mappedBy = "idSupervisoraEmpresa")
    private List<MdiSedeEmpresaLocador> mdiSedeEmpresaLocadorList;
    @OneToMany(mappedBy = "idSupervisoraEmpresa")
    private List<MdiPoliza> mdiPolizaList;
    @OneToMany(mappedBy = "idSupervisoraEmpresa")
    private List<MdiContratoEmpresaLocador> mdiContratoEmpresaLocadorList;
    @OneToMany(mappedBy = "idSupervisoraEmpresa")
    private List<MdiEmpresaLocaXUnidadOrg> mdiEmpresaLocaXUnidadOrgList;

    public MdiSupervisoraEmpresa() {
    }

    public MdiSupervisoraEmpresa(Long idSupervisoraEmpresa) {
        this.idSupervisoraEmpresa = idSupervisoraEmpresa;
    }

    public MdiSupervisoraEmpresa(Long idSupervisoraEmpresa, long tipoEmpresaConstitucion, String razonSocial, String ruc, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idSupervisoraEmpresa = idSupervisoraEmpresa;
        this.tipoEmpresaConstitucion = tipoEmpresaConstitucion;
        this.razonSocial = razonSocial;
        this.ruc = ruc;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdSupervisoraEmpresa() {
        return idSupervisoraEmpresa;
    }

    public void setIdSupervisoraEmpresa(Long idSupervisoraEmpresa) {
        this.idSupervisoraEmpresa = idSupervisoraEmpresa;
    }

    public long getTipoEmpresaConstitucion() {
        return tipoEmpresaConstitucion;
    }

    public void setTipoEmpresaConstitucion(long tipoEmpresaConstitucion) {
        this.tipoEmpresaConstitucion = tipoEmpresaConstitucion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCiiuPrincipal() {
        return ciiuPrincipal;
    }

    public void setCiiuPrincipal(String ciiuPrincipal) {
        this.ciiuPrincipal = ciiuPrincipal;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getOrigenInformacion() {
        return origenInformacion;
    }

    public void setOrigenInformacion(String origenInformacion) {
        this.origenInformacion = origenInformacion;
    }

    @XmlTransient
    public List<MdiEmpresaContacto> getMdiEmpresaContactoList() {
        return mdiEmpresaContactoList;
    }

    public void setMdiEmpresaContactoList(List<MdiEmpresaContacto> mdiEmpresaContactoList) {
        this.mdiEmpresaContactoList = mdiEmpresaContactoList;
    }

    @XmlTransient
    public List<MdiSedeEmpresaLocador> getMdiSedeEmpresaLocadorList() {
        return mdiSedeEmpresaLocadorList;
    }

    public void setMdiSedeEmpresaLocadorList(List<MdiSedeEmpresaLocador> mdiSedeEmpresaLocadorList) {
        this.mdiSedeEmpresaLocadorList = mdiSedeEmpresaLocadorList;
    }

    @XmlTransient
    public List<MdiPoliza> getMdiPolizaList() {
        return mdiPolizaList;
    }

    public void setMdiPolizaList(List<MdiPoliza> mdiPolizaList) {
        this.mdiPolizaList = mdiPolizaList;
    }

    @XmlTransient
    public List<MdiContratoEmpresaLocador> getMdiContratoEmpresaLocadorList() {
        return mdiContratoEmpresaLocadorList;
    }

    public void setMdiContratoEmpresaLocadorList(List<MdiContratoEmpresaLocador> mdiContratoEmpresaLocadorList) {
        this.mdiContratoEmpresaLocadorList = mdiContratoEmpresaLocadorList;
    }

    @XmlTransient
    public List<MdiEmpresaLocaXUnidadOrg> getMdiEmpresaLocaXUnidadOrgList() {
        return mdiEmpresaLocaXUnidadOrgList;
    }

    public void setMdiEmpresaLocaXUnidadOrgList(List<MdiEmpresaLocaXUnidadOrg> mdiEmpresaLocaXUnidadOrgList) {
        this.mdiEmpresaLocaXUnidadOrgList = mdiEmpresaLocaXUnidadOrgList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSupervisoraEmpresa != null ? idSupervisoraEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiSupervisoraEmpresa)) {
            return false;
        }
        MdiSupervisoraEmpresa other = (MdiSupervisoraEmpresa) object;
        if ((this.idSupervisoraEmpresa == null && other.idSupervisoraEmpresa != null) || (this.idSupervisoraEmpresa != null && !this.idSupervisoraEmpresa.equals(other.idSupervisoraEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiSupervisoraEmpresa[ idSupervisoraEmpresa=" + idSupervisoraEmpresa + " ]";
    }
    
}

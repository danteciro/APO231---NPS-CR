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
@Table(name = "MDI_EMPRESA_LOCA_X_UNIDAD_ORG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findAll", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByIdEmpresaLocaXUnidadOrg", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.idEmpresaLocaXUnidadOrg = :idEmpresaLocaXUnidadOrg"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByEstado", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByUsuarioCreacion", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByFechaCreacion", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByTerminalCreacion", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByUsuarioActualizacion", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByFechaActualizacion", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiEmpresaLocaXUnidadOrg.findByTerminalActualizacion", query = "SELECT m FROM MdiEmpresaLocaXUnidadOrg m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiEmpresaLocaXUnidadOrg extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_EMPRESA_LOCA_X_UNIDAD_ORG")
    private Long idEmpresaLocaXUnidadOrg;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;   
    @JoinColumn(name = "ID_UNIDAD_ORGANICA", referencedColumnName = "ID_UNIDAD_ORGANICA")
    @ManyToOne
    private MdiUnidadOrganica idUnidadOrganica;
    @JoinColumn(name = "ID_SUPERVISORA_EMPRESA", referencedColumnName = "ID_SUPERVISORA_EMPRESA")
    @ManyToOne
    private MdiSupervisoraEmpresa idSupervisoraEmpresa;
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR")
    @ManyToOne
    private MdiLocador idLocador;

    public MdiEmpresaLocaXUnidadOrg() {
    }

    public MdiEmpresaLocaXUnidadOrg(Long idEmpresaLocaXUnidadOrg) {
        this.idEmpresaLocaXUnidadOrg = idEmpresaLocaXUnidadOrg;
    }

    public MdiEmpresaLocaXUnidadOrg(Long idEmpresaLocaXUnidadOrg, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idEmpresaLocaXUnidadOrg = idEmpresaLocaXUnidadOrg;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdEmpresaLocaXUnidadOrg() {
        return idEmpresaLocaXUnidadOrg;
    }

    public void setIdEmpresaLocaXUnidadOrg(Long idEmpresaLocaXUnidadOrg) {
        this.idEmpresaLocaXUnidadOrg = idEmpresaLocaXUnidadOrg;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public MdiUnidadOrganica getIdUnidadOrganica() {
        return idUnidadOrganica;
    }

    public void setIdUnidadOrganica(MdiUnidadOrganica idUnidadOrganica) {
        this.idUnidadOrganica = idUnidadOrganica;
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
        hash += (idEmpresaLocaXUnidadOrg != null ? idEmpresaLocaXUnidadOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiEmpresaLocaXUnidadOrg)) {
            return false;
        }
        MdiEmpresaLocaXUnidadOrg other = (MdiEmpresaLocaXUnidadOrg) object;
        if ((this.idEmpresaLocaXUnidadOrg == null && other.idEmpresaLocaXUnidadOrg != null) || (this.idEmpresaLocaXUnidadOrg != null && !this.idEmpresaLocaXUnidadOrg.equals(other.idEmpresaLocaXUnidadOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiEmpresaLocaXUnidadOrg[ idEmpresaLocaXUnidadOrg=" + idEmpresaLocaXUnidadOrg + " ]";
    }
    
}

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_LOCADOR_X_EMPRESA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiLocadorXEmpresa.findAll", query = "SELECT m FROM MdiLocadorXEmpresa m"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByCategoriaLocador", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.categoriaLocador = :categoriaLocador"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByMotivoCambio", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.motivoCambio = :motivoCambio"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByObservaciones", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByEstado", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByUsuarioCreacion", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByFechaCreacion", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByTerminalCreacion", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByUsuarioActualizacion", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByIdContratoEmpresaLocador", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.mdiLocadorXEmpresaPK.idContratoEmpresaLocador = :idContratoEmpresaLocador"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByIdLocador", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.mdiLocadorXEmpresaPK.idLocador = :idLocador"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByFechaActualizacion", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiLocadorXEmpresa.findByTerminalActualizacion", query = "SELECT m FROM MdiLocadorXEmpresa m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiLocadorXEmpresa extends Auditoria {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MdiLocadorXEmpresaPK mdiLocadorXEmpresaPK;
    @Basic(optional = false)
    @Column(name = "CATEGORIA_LOCADOR")
    private long categoriaLocador;
    @Column(name = "MOTIVO_CAMBIO")
    private Long motivoCambio;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiLocador mdiLocador;
    @JoinColumn(name = "ID_CONTRATO_EMPRESA_LOCADOR", referencedColumnName = "ID_CONTRATO_EMPRESA_LOCADOR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MdiContratoEmpresaLocador mdiContratoEmpresaLocador;

    public MdiLocadorXEmpresa() {
    }

    public MdiLocadorXEmpresa(MdiLocadorXEmpresaPK mdiLocadorXEmpresaPK) {
        this.mdiLocadorXEmpresaPK = mdiLocadorXEmpresaPK;
    }

    public MdiLocadorXEmpresa(MdiLocadorXEmpresaPK mdiLocadorXEmpresaPK, long categoriaLocador, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.mdiLocadorXEmpresaPK = mdiLocadorXEmpresaPK;
        this.categoriaLocador = categoriaLocador;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public MdiLocadorXEmpresa(long idContratoEmpresaLocador, long idLocador) {
        this.mdiLocadorXEmpresaPK = new MdiLocadorXEmpresaPK(idContratoEmpresaLocador, idLocador);
    }

    public MdiLocadorXEmpresaPK getMdiLocadorXEmpresaPK() {
        return mdiLocadorXEmpresaPK;
    }

    public void setMdiLocadorXEmpresaPK(MdiLocadorXEmpresaPK mdiLocadorXEmpresaPK) {
        this.mdiLocadorXEmpresaPK = mdiLocadorXEmpresaPK;
    }

    public long getCategoriaLocador() {
        return categoriaLocador;
    }

    public void setCategoriaLocador(long categoriaLocador) {
        this.categoriaLocador = categoriaLocador;
    }

    public Long getMotivoCambio() {
        return motivoCambio;
    }

    public void setMotivoCambio(Long motivoCambio) {
        this.motivoCambio = motivoCambio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public MdiLocador getMdiLocador() {
        return mdiLocador;
    }

    public void setMdiLocador(MdiLocador mdiLocador) {
        this.mdiLocador = mdiLocador;
    }

    public MdiContratoEmpresaLocador getMdiContratoEmpresaLocador() {
        return mdiContratoEmpresaLocador;
    }

    public void setMdiContratoEmpresaLocador(MdiContratoEmpresaLocador mdiContratoEmpresaLocador) {
        this.mdiContratoEmpresaLocador = mdiContratoEmpresaLocador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mdiLocadorXEmpresaPK != null ? mdiLocadorXEmpresaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiLocadorXEmpresa)) {
            return false;
        }
        MdiLocadorXEmpresa other = (MdiLocadorXEmpresa) object;
        if ((this.mdiLocadorXEmpresaPK == null && other.mdiLocadorXEmpresaPK != null) || (this.mdiLocadorXEmpresaPK != null && !this.mdiLocadorXEmpresaPK.equals(other.mdiLocadorXEmpresaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiLocadorXEmpresa[ mdiLocadorXEmpresaPK=" + mdiLocadorXEmpresaPK + " ]";
    }
    
}

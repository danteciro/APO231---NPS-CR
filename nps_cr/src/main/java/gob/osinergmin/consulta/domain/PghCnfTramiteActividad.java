/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_CNF_TRAMITE_ACTIVIDAD")
@NamedQueries({
    @NamedQuery(name = "PghCnfTramiteActividad.findAll", query = "SELECT p FROM PghCnfTramiteActividad p"),
    @NamedQuery(name = "PghCnfTramiteActividad.findByIdTramiteActivdad", query = "SELECT p FROM PghCnfTramiteActividad p WHERE p.idTramiteActivdad = :idTramiteActivdad"),
    @NamedQuery(name = "PghCnfTramiteActividad.findByEstado", query = "SELECT p FROM PghCnfTramiteActividad p WHERE p.estado = :estado")})
public class PghCnfTramiteActividad extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TRAMITE_ACTIVDAD")
    private Long idTramiteActivdad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    
    @JoinColumn(name = "ID_TRAMITE", referencedColumnName = "ID_TRAMITE")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghTramite idTramite;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD")
    @ManyToOne(fetch = FetchType.LAZY)
    private MdiActividad idActividad;

    public PghCnfTramiteActividad() {
    }

    public PghCnfTramiteActividad(Long idTramiteActivdad) {
        this.idTramiteActivdad = idTramiteActivdad;
    }

    public PghCnfTramiteActividad(Long idTramiteActivdad, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idTramiteActivdad = idTramiteActivdad;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdTramiteActivdad() {
        return idTramiteActivdad;
    }

    public void setIdTramiteActivdad(Long idTramiteActivdad) {
        this.idTramiteActivdad = idTramiteActivdad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PghTramite getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(PghTramite idTramite) {
        this.idTramite = idTramite;
    }

    public MdiActividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(MdiActividad idActividad) {
        this.idActividad = idActividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTramiteActivdad != null ? idTramiteActivdad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghCnfTramiteActividad)) {
            return false;
        }
        PghCnfTramiteActividad other = (PghCnfTramiteActividad) object;
        if ((this.idTramiteActivdad == null && other.idTramiteActivdad != null) || (this.idTramiteActivdad != null && !this.idTramiteActivdad.equals(other.idTramiteActivdad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghCnfTramiteActividad[ idTramiteActivdad=" + idTramiteActivdad + " ]";
    }
}

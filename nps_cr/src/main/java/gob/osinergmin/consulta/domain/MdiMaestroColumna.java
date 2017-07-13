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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lbarboza
 *
 */
@Entity
@Table(name = "MDI_MAESTRO_COLUMNA")
@NamedQueries({
    @NamedQuery(name = "MdiMaestroColumna.findAll", query = "SELECT m FROM MdiMaestroColumna m"),
    @NamedQuery(name = "MdiMaestroColumna.findByCodigoDominio", query = "SELECT mc FROM MdiMaestroColumna mc "
            + "left join mc.mdiMaestroTabla mt  "
            + "WHERE "
            + "mc.mdiMaestroTabla.mdiMaestroTablaPK.dominio = :dominio and "
            + "mc.mdiMaestroTabla.mdiMaestroTablaPK.aplicacion = :aplicacion and "
            + "mc.codigo = :codigo"),
    @NamedQuery(name = "MdiMaestroColumna.findByDominio", query = "SELECT mc FROM MdiMaestroColumna mc "
            + "left join mc.mdiMaestroTabla mt  "
            + "WHERE "
            + "mc.mdiMaestroTabla.mdiMaestroTablaPK.dominio = :dominio and "
            + "mc.mdiMaestroTabla.mdiMaestroTablaPK.aplicacion = :aplicacion and "
            + "mc.estado = :estado "),
    @NamedQuery(name = "MdiMaestroColumna.findIdMaestroColumnaByFiltro", query = "SELECT mc FROM MdiMaestroColumna mc "
            + "left join mc.mdiMaestroTabla mt  "
            + "WHERE "
            + "mc.mdiMaestroTabla.mdiMaestroTablaPK.dominio = :dominio and "
            + "mc.mdiMaestroTabla.mdiMaestroTablaPK.aplicacion = :aplicacion and "
            + "mc.codigo = :codigo and mc.descripcion = :descripcion"),
    @NamedQuery(name = "MdiMaestroColumna.findByDescripHijos", query = "SELECT mc from MdiMaestroColumna mc "
            + " WHERE mc.mdiMaestroTabla.mdiMaestroTablaPK.aplicacion =:aplicacion and "
            + //" mc.estado=:estado and "+
            " mc.mdiMaestroTabla.mdiMaestroTablaPK.dominio = ( "
            + "SELECT mt.mdiMaestroTablaPK.dominio from MdiMaestroTabla mt WHERE mt.mdiMaestroTablaPK.aplicacion=:aplicacion "
            + "AND mt.descripcion=(select mco.descripcion from MdiMaestroColumna mco where mco.idMaestroColumna=:idMaestroColumna) )")
})
public class MdiMaestroColumna extends Auditoria {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CODIGO", nullable = false, length = 5)
    private String codigo;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "SEQ_GENERATOR", sequenceName = "MDI_MAESTRO_COLUMNA_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
    @Column(name = "ID_MAESTRO_COLUMNA", nullable = false)
    private Long idMaestroColumna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO", nullable = false)
    private String estado;
    @JoinColumns({
        @JoinColumn(name = "DOMINIO", referencedColumnName = "DOMINIO"),
        @JoinColumn(name = "APLICACION", referencedColumnName = "APLICACION", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MdiMaestroTabla mdiMaestroTabla;

    public MdiMaestroColumna() {
    }

    public MdiMaestroColumna(Long idMaestroColumna) {
        this.idMaestroColumna = idMaestroColumna;
    }

    public MdiMaestroColumna(Long idMaestroColumna, String descripcion) {
        this.idMaestroColumna = idMaestroColumna;
        this.descripcion = descripcion;
    }

    public MdiMaestroColumna(Long idMaestroColumna, String descripcion, Date fechaCreacion, String usuarioCreacion, String terminalCreacion, String estado) {
        this.idMaestroColumna = idMaestroColumna;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioCreacion = usuarioCreacion;
        this.terminalCreacion = terminalCreacion;
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getIdMaestroColumna() {
        return idMaestroColumna;
    }

    public void setIdMaestroColumna(Long idMaestroColumna) {
        this.idMaestroColumna = idMaestroColumna;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public MdiMaestroTabla getMdiMaestroTabla() {
        return mdiMaestroTabla;
    }

    public void setMdiMaestroTabla(MdiMaestroTabla mdiMaestroTabla) {
        this.mdiMaestroTabla = mdiMaestroTabla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaestroColumna != null ? idMaestroColumna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiMaestroColumna)) {
            return false;
        }
        MdiMaestroColumna other = (MdiMaestroColumna) object;
        if ((this.idMaestroColumna == null && other.idMaestroColumna != null) || (this.idMaestroColumna != null && !this.idMaestroColumna.equals(other.idMaestroColumna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.MdiMaestroColumna[ idMaestroColumna=" + idMaestroColumna + " ]";
    }
}

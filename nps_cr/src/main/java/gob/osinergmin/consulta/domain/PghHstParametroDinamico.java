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
import javax.validation.constraints.Size;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_HST_PARAMETRO_DINAMICO")
@NamedQueries({
    @NamedQuery(name = "PghHstParametroDinamico.findAll", query = "SELECT p FROM PghHstParametroDinamico p"),
    @NamedQuery(name = "PghHstParametroDinamico.findByIdHstParamaetroDinamico", query = "SELECT p FROM PghHstParametroDinamico p WHERE p.idHstParamaetroDinamico = :idHstParamaetroDinamico"),
    @NamedQuery(name = "PghHstParametroDinamico.findByNombre", query = "SELECT p FROM PghHstParametroDinamico p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PghHstParametroDinamico.findByIdAmbitoParametrico", query = "SELECT p FROM PghHstParametroDinamico p WHERE p.idAmbitoParametrico = :idAmbitoParametrico"),
    @NamedQuery(name = "PghHstParametroDinamico.findByDescripcion", query = "SELECT p FROM PghHstParametroDinamico p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghHstParametroDinamico.findByComentario", query = "SELECT p FROM PghHstParametroDinamico p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "PghHstParametroDinamico.findByEstado", query = "SELECT p FROM PghHstParametroDinamico p WHERE p.estado = :estado")})
public class PghHstParametroDinamico extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HST_PARAMAETRO_DINAMICO")
    private Long idHstParamaetroDinamico;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ID_AMBITO_PARAMETRICO")
    private Long idAmbitoParametrico;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;

    @JoinColumn(name = "ID_PARAMETRO_DINAMICO", referencedColumnName = "ID_PARAMETRO_DINAMICO")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghParametroDinamico idParametroDinamico;

    public PghHstParametroDinamico() {
    }

    public PghHstParametroDinamico(Long idHstParamaetroDinamico) {
        this.idHstParamaetroDinamico = idHstParamaetroDinamico;
    }

    public PghHstParametroDinamico(Long idHstParamaetroDinamico, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idHstParamaetroDinamico = idHstParamaetroDinamico;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdHstParamaetroDinamico() {
        return idHstParamaetroDinamico;
    }

    public void setIdHstParamaetroDinamico(Long idHstParamaetroDinamico) {
        this.idHstParamaetroDinamico = idHstParamaetroDinamico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdAmbitoParametrico() {
        return idAmbitoParametrico;
    }

    public void setIdAmbitoParametrico(Long idAmbitoParametrico) {
        this.idAmbitoParametrico = idAmbitoParametrico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PghParametroDinamico getIdParametroDinamico() {
        return idParametroDinamico;
    }

    public void setIdParametroDinamico(PghParametroDinamico idParametroDinamico) {
        this.idParametroDinamico = idParametroDinamico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHstParamaetroDinamico != null ? idHstParamaetroDinamico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghHstParametroDinamico)) {
            return false;
        }
        PghHstParametroDinamico other = (PghHstParametroDinamico) object;
        if ((this.idHstParamaetroDinamico == null && other.idHstParamaetroDinamico != null) || (this.idHstParamaetroDinamico != null && !this.idHstParamaetroDinamico.equals(other.idHstParamaetroDinamico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghHstParametroDinamico[ idHstParamaetroDinamico=" + idHstParamaetroDinamico + " ]";
    }
    
}

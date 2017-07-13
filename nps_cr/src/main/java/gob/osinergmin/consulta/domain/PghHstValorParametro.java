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
@Table(name = "PGH_HST_VALOR_PARAMETRO")
@NamedQueries({
    @NamedQuery(name = "PghHstValorParametro.findAll", query = "SELECT p FROM PghHstValorParametro p"),
    @NamedQuery(name = "PghHstValorParametro.findByIdHstValorParametro", query = "SELECT p FROM PghHstValorParametro p WHERE p.idHstValorParametro = :idHstValorParametro"),
    @NamedQuery(name = "PghHstValorParametro.findByIdParametroDinamico", query = "SELECT p FROM PghHstValorParametro p WHERE p.idParametroDinamico = :idParametroDinamico"),
    @NamedQuery(name = "PghHstValorParametro.findByValor", query = "SELECT p FROM PghHstValorParametro p WHERE p.valor = :valor"),
    @NamedQuery(name = "PghHstValorParametro.findByDescripcion", query = "SELECT p FROM PghHstValorParametro p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghHstValorParametro.findByComentario", query = "SELECT p FROM PghHstValorParametro p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "PghHstValorParametro.findByValorDefecto", query = "SELECT p FROM PghHstValorParametro p WHERE p.valorDefecto = :valorDefecto"),
    @NamedQuery(name = "PghHstValorParametro.findByEstado", query = "SELECT p FROM PghHstValorParametro p WHERE p.estado = :estado")})
public class PghHstValorParametro extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HST_VALOR_PARAMETRO")
    private Long idHstValorParametro;
    @Column(name = "ID_PARAMETRO_DINAMICO")
    private Long idParametroDinamico;
    @Size(max = 100)
    @Column(name = "VALOR")
    private String valor;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "VALOR_DEFECTO")
    private Character valorDefecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private char estado;
    
    @JoinColumn(name = "ID_VALOR_PARAMETRO", referencedColumnName = "ID_VALOR_PARAMETRO")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghValorParametro idValorParametro;

    public PghHstValorParametro() {
    }

    public PghHstValorParametro(Long idHstValorParametro) {
        this.idHstValorParametro = idHstValorParametro;
    }

    public PghHstValorParametro(Long idHstValorParametro, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idHstValorParametro = idHstValorParametro;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdHstValorParametro() {
        return idHstValorParametro;
    }

    public void setIdHstValorParametro(Long idHstValorParametro) {
        this.idHstValorParametro = idHstValorParametro;
    }

    public Long getIdParametroDinamico() {
        return idParametroDinamico;
    }

    public void setIdParametroDinamico(Long idParametroDinamico) {
        this.idParametroDinamico = idParametroDinamico;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public Character getValorDefecto() {
        return valorDefecto;
    }

    public void setValorDefecto(Character valorDefecto) {
        this.valorDefecto = valorDefecto;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public PghValorParametro getIdValorParametro() {
        return idValorParametro;
    }

    public void setIdValorParametro(PghValorParametro idValorParametro) {
        this.idValorParametro = idValorParametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHstValorParametro != null ? idHstValorParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghHstValorParametro)) {
            return false;
        }
        PghHstValorParametro other = (PghHstValorParametro) object;
        if ((this.idHstValorParametro == null && other.idHstValorParametro != null) || (this.idHstValorParametro != null && !this.idHstValorParametro.equals(other.idHstValorParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghHstValorParametro[ idHstValorParametro=" + idHstValorParametro + " ]";
    }
    
}

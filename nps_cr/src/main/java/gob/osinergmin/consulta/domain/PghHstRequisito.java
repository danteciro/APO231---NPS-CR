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
@Table(name = "PGH_HST_REQUISITO")
@NamedQueries({
    @NamedQuery(name = "PghHstRequisito.findAll", query = "SELECT p FROM PghHstRequisito p"),
    @NamedQuery(name = "PghHstRequisito.findByIdHstRequisito", query = "SELECT p FROM PghHstRequisito p WHERE p.idHstRequisito = :idHstRequisito"),
    @NamedQuery(name = "PghHstRequisito.findByDescripcion", query = "SELECT p FROM PghHstRequisito p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghHstRequisito.findByComentarioPredeterminado", query = "SELECT p FROM PghHstRequisito p WHERE p.comentarioPredeterminado = :comentarioPredeterminado"),
    @NamedQuery(name = "PghHstRequisito.findByIdDocumentoAdjunto", query = "SELECT p FROM PghHstRequisito p WHERE p.idDocumentoAdjunto = :idDocumentoAdjunto"),
    @NamedQuery(name = "PghHstRequisito.findByEstado", query = "SELECT p FROM PghHstRequisito p WHERE p.estado = :estado")})
public class PghHstRequisito extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_HST_REQUISITO")
    private Long idHstRequisito;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 1500)
    @Column(name = "COMENTARIO_PREDETERMINADO")
    private String comentarioPredeterminado;
    @Column(name = "ID_DOCUMENTO_ADJUNTO")
    private Long idDocumentoAdjunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    
    @JoinColumn(name = "ID_REQUISITO", referencedColumnName = "ID_REQUISITO")
    @ManyToOne(fetch = FetchType.LAZY)
    private PghRequisito idRequisito;

    public PghHstRequisito() {
    }

    public PghHstRequisito(Long idHstRequisito) {
        this.idHstRequisito = idHstRequisito;
    }

    public PghHstRequisito(Long idHstRequisito, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idHstRequisito = idHstRequisito;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdHstRequisito() {
        return idHstRequisito;
    }

    public void setIdHstRequisito(Long idHstRequisito) {
        this.idHstRequisito = idHstRequisito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComentarioPredeterminado() {
        return comentarioPredeterminado;
    }

    public void setComentarioPredeterminado(String comentarioPredeterminado) {
        this.comentarioPredeterminado = comentarioPredeterminado;
    }

    public Long getIdDocumentoAdjunto() {
        return idDocumentoAdjunto;
    }

    public void setIdDocumentoAdjunto(Long idDocumentoAdjunto) {
        this.idDocumentoAdjunto = idDocumentoAdjunto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PghRequisito getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(PghRequisito idRequisito) {
        this.idRequisito = idRequisito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHstRequisito != null ? idHstRequisito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghHstRequisito)) {
            return false;
        }
        PghHstRequisito other = (PghHstRequisito) object;
        if ((this.idHstRequisito == null && other.idHstRequisito != null) || (this.idHstRequisito != null && !this.idHstRequisito.equals(other.idHstRequisito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghHstRequisito[ idHstRequisito=" + idHstRequisito + " ]";
    }  
}

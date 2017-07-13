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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "PGH_PARAMETRO_DINAMICO")
@NamedQueries({
    @NamedQuery(name = "PghParametroDinamico.findAll", query = "SELECT p FROM PghParametroDinamico p"),
    @NamedQuery(name = "PghParametroDinamico.findByIdParametroDinamico", query = "SELECT p FROM PghParametroDinamico p WHERE p.idParametroDinamico = :idParametroDinamico"),
    @NamedQuery(name = "PghParametroDinamico.findByNombre", query = "SELECT p FROM PghParametroDinamico p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PghParametroDinamico.findByIdAmbitoParametrico", query = "SELECT p FROM PghParametroDinamico p WHERE p.idAmbitoParametrico = :idAmbitoParametrico"),
    @NamedQuery(name = "PghParametroDinamico.findByDescripcion", query = "SELECT p FROM PghParametroDinamico p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PghParametroDinamico.findByComentario", query = "SELECT p FROM PghParametroDinamico p WHERE p.comentario = :comentario"),
    @NamedQuery(name = "PghParametroDinamico.findByEstado", query = "SELECT p FROM PghParametroDinamico p WHERE p.estado = :estado"),
    @NamedQuery(name = "PghParametroDinamico.countByFilter", query = "Select count(d.idParametroDinamico) from PghParametroDinamico d where d.estado='1' "),
    @NamedQuery(name = "PghParametroDinamico.findByFilterAmbito", query = "Select new PghParametroDinamico(d.idParametroDinamico,d.nombre,d.descripcion,d.comentario ,d.idAmbitoParametrico.idMaestroColumna ,d.idAmbitoParametrico.descripcion) from PghParametroDinamico d where d.estado='1' and d.idAmbitoParametrico.idMaestroColumna=:idAmbitoParametrico"),
    @NamedQuery(name = "PghParametroDinamico.findByFilterNombre", query = "Select new PghParametroDinamico(d.idParametroDinamico,d.nombre,d.descripcion,d.comentario ,d.idAmbitoParametrico.idMaestroColumna ,d.idAmbitoParametrico.descripcion) from PghParametroDinamico d where d.estado='1' and upper(d.nombre) like :nombre "),
    @NamedQuery(name = "PghParametroDinamico.findByIdParametro", query = "Select d from PghParametroDinamico d where d.estado='1' and d.idParametroDinamico=:idParametroDinamico ")
})
public class PghParametroDinamico extends Auditoria {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PARAMETRO_DINAMICO")
    @SequenceGenerator(name = "SEQ_GENERATOR", sequenceName = "PGH_PARAMETRO_DINAMICO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENERATOR")
    private Long idParametroDinamico;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "ID_AMBITO_PARAMETRICO")
    private MdiMaestroColumna idAmbitoParametrico;
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
    @Size(max = 500)
    @Column(name = "PREGUNTA")
    private String pregunta;
    @ManyToOne
    @JoinColumn(name = "TIPO_CONSULTA")
    private MdiMaestroColumna tipoConsulta;
    @OneToMany(mappedBy = "idParametroDinamico", fetch = FetchType.LAZY)
    private List<PghHstParametroDinamico> pghHstParametroDinamicoList;
    @OneToMany(mappedBy = "idParametroDinamico", fetch = FetchType.LAZY)
    private List<PghValorParametro> pghValorParametroList;

    public PghParametroDinamico() {
    }

    public PghParametroDinamico(Long idParametroDinamico) {
        this.idParametroDinamico = idParametroDinamico;
    }

    public PghParametroDinamico(Long idParametroDinamico, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idParametroDinamico = idParametroDinamico;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public PghParametroDinamico(Long idParametroDinamico, String nombre, String descripcion, String comentario, Long idAmbitoParametrico, String descAmbitoParametrico) {
        this.idParametroDinamico = idParametroDinamico;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.comentario = comentario;
        this.idAmbitoParametrico = new MdiMaestroColumna(idAmbitoParametrico, descAmbitoParametrico);
    }

    public Long getIdParametroDinamico() {
        return idParametroDinamico;
    }

    public void setIdParametroDinamico(Long idParametroDinamico) {
        this.idParametroDinamico = idParametroDinamico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MdiMaestroColumna getIdAmbitoParametrico() {
        return idAmbitoParametrico;
    }

    public void setIdAmbitoParametrico(MdiMaestroColumna idAmbitoParametrico) {
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

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public MdiMaestroColumna getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(MdiMaestroColumna tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public List<PghHstParametroDinamico> getPghHstParametroDinamicoList() {
        return pghHstParametroDinamicoList;
    }

    public void setPghHstParametroDinamicoList(List<PghHstParametroDinamico> pghHstParametroDinamicoList) {
        this.pghHstParametroDinamicoList = pghHstParametroDinamicoList;
    }

    public List<PghValorParametro> getPghValorParametroList() {
        return pghValorParametroList;
    }

    public void setPghValorParametroList(List<PghValorParametro> pghValorParametroList) {
        this.pghValorParametroList = pghValorParametroList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroDinamico != null ? idParametroDinamico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PghParametroDinamico)) {
            return false;
        }
        PghParametroDinamico other = (PghParametroDinamico) object;
        if ((this.idParametroDinamico == null && other.idParametroDinamico != null) || (this.idParametroDinamico != null && !this.idParametroDinamico.equals(other.idParametroDinamico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.PghParametroDinamico[ idParametroDinamico=" + idParametroDinamico + " ]";
    }
}
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_INST_ACAD_LOCADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiInstAcadLocador.findAll", query = "SELECT m FROM MdiInstAcadLocador m"),
    @NamedQuery(name = "MdiInstAcadLocador.findByIdInstAcadLocador", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.idInstAcadLocador = :idInstAcadLocador"),
    @NamedQuery(name = "MdiInstAcadLocador.findByIdTipoEstudio", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.idTipoEstudio = :idTipoEstudio"),
    @NamedQuery(name = "MdiInstAcadLocador.findByIdNivelEstudio", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.idNivelEstudio = :idNivelEstudio"),
    @NamedQuery(name = "MdiInstAcadLocador.findByTituloEstudio", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.tituloEstudio = :tituloEstudio"),
    @NamedQuery(name = "MdiInstAcadLocador.findByFechaInicio", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "MdiInstAcadLocador.findByIdAreaEstudio", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.idAreaEstudio = :idAreaEstudio"),
    @NamedQuery(name = "MdiInstAcadLocador.findByFechaFin", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.fechaFin = :fechaFin"),
    @NamedQuery(name = "MdiInstAcadLocador.findByMencionEstudio", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.mencionEstudio = :mencionEstudio"),
    @NamedQuery(name = "MdiInstAcadLocador.findByEstado", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiInstAcadLocador.findByEstadoProceso", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.estadoProceso = :estadoProceso"),
    @NamedQuery(name = "MdiInstAcadLocador.findByUsuarioCreacion", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiInstAcadLocador.findByFechaCreacion", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiInstAcadLocador.findByTerminalCreacion", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiInstAcadLocador.findByUsuarioActualizacion", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiInstAcadLocador.findByFechaActualizacion", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiInstAcadLocador.findByTerminalActualizacion", query = "SELECT m FROM MdiInstAcadLocador m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiInstAcadLocador extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_INST_ACAD_LOCADOR")
    private Long idInstAcadLocador;
    @Column(name = "ID_TIPO_ESTUDIO")
    private Long idTipoEstudio;
    @Column(name = "ID_NIVEL_ESTUDIO")
    private Long idNivelEstudio;
    @Column(name = "TITULO_ESTUDIO")
    private String tituloEstudio;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "ID_AREA_ESTUDIO")
    private Long idAreaEstudio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "MENCION_ESTUDIO")
    private String mencionEstudio;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @Column(name = "ESTADO_PROCESO")
    private long estadoProceso;    
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR")
    @ManyToOne(optional = false)
    private MdiLocador idLocador;
    @JoinColumn(name = "ID_INSTITUCION_ACADEMICA", referencedColumnName = "ID_INSTITUCION_ACADEMICA")
    @ManyToOne(optional = false)
    private MdiInstitucionAcademica idInstitucionAcademica;
    @OneToMany(mappedBy = "idInstAcadLocador")
    private List<MdiDocumentoAdjunto> mdiDocumentoAdjuntoList;

    public MdiInstAcadLocador() {
    }

    public MdiInstAcadLocador(Long idInstAcadLocador) {
        this.idInstAcadLocador = idInstAcadLocador;
    }

    public MdiInstAcadLocador(Long idInstAcadLocador, char estado, long estadoProceso, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idInstAcadLocador = idInstAcadLocador;
        this.estado = estado;
        this.estadoProceso = estadoProceso;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdInstAcadLocador() {
        return idInstAcadLocador;
    }

    public void setIdInstAcadLocador(Long idInstAcadLocador) {
        this.idInstAcadLocador = idInstAcadLocador;
    }

    public Long getIdTipoEstudio() {
        return idTipoEstudio;
    }

    public void setIdTipoEstudio(Long idTipoEstudio) {
        this.idTipoEstudio = idTipoEstudio;
    }

    public Long getIdNivelEstudio() {
        return idNivelEstudio;
    }

    public void setIdNivelEstudio(Long idNivelEstudio) {
        this.idNivelEstudio = idNivelEstudio;
    }

    public String getTituloEstudio() {
        return tituloEstudio;
    }

    public void setTituloEstudio(String tituloEstudio) {
        this.tituloEstudio = tituloEstudio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getIdAreaEstudio() {
        return idAreaEstudio;
    }

    public void setIdAreaEstudio(Long idAreaEstudio) {
        this.idAreaEstudio = idAreaEstudio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getMencionEstudio() {
        return mencionEstudio;
    }

    public void setMencionEstudio(String mencionEstudio) {
        this.mencionEstudio = mencionEstudio;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public long getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(long estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public MdiLocador getIdLocador() {
        return idLocador;
    }

    public void setIdLocador(MdiLocador idLocador) {
        this.idLocador = idLocador;
    }

    public MdiInstitucionAcademica getIdInstitucionAcademica() {
        return idInstitucionAcademica;
    }

    public void setIdInstitucionAcademica(MdiInstitucionAcademica idInstitucionAcademica) {
        this.idInstitucionAcademica = idInstitucionAcademica;
    }

    @XmlTransient
    public List<MdiDocumentoAdjunto> getMdiDocumentoAdjuntoList() {
        return mdiDocumentoAdjuntoList;
    }

    public void setMdiDocumentoAdjuntoList(List<MdiDocumentoAdjunto> mdiDocumentoAdjuntoList) {
        this.mdiDocumentoAdjuntoList = mdiDocumentoAdjuntoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstAcadLocador != null ? idInstAcadLocador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiInstAcadLocador)) {
            return false;
        }
        MdiInstAcadLocador other = (MdiInstAcadLocador) object;
        if ((this.idInstAcadLocador == null && other.idInstAcadLocador != null) || (this.idInstAcadLocador != null && !this.idInstAcadLocador.equals(other.idInstAcadLocador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiInstAcadLocador[ idInstAcadLocador=" + idInstAcadLocador + " ]";
    }
    
}

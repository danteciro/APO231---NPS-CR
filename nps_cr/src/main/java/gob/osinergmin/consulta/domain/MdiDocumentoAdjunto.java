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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author lchancayauri
 */
@Entity
@Table(name = "MDI_DOCUMENTO_ADJUNTO")
@NamedQueries({
    @NamedQuery(name = "MdiDocumentoAdjunto.findAll", query = "SELECT m FROM MdiDocumentoAdjunto m"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByIdDocumentoAdjunto", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.idDocumentoAdjunto = :idDocumentoAdjunto"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByRutaAlfresco", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.rutaAlfresco = :rutaAlfresco"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByNombre", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByComentario", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.comentario = :comentario"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByIdTipoDocumentoCarga", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.idTipoDocumentoCarga = :idTipoDocumentoCarga"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByTitulo", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.titulo = :titulo"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByFechaCarga", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.fechaCarga = :fechaCarga"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByFechaDocumento", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.fechaDocumento = :fechaDocumento"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByFechaCaptura", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.fechaCaptura = :fechaCaptura"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByEstado", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByUsuarioCreacion", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByFechaCreacion", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByTerminalCreacion", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByUsuarioActualizacion", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByFechaActualizacion", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiDocumentoAdjunto.findByTerminalActualizacion", query = "SELECT m FROM MdiDocumentoAdjunto m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiDocumentoAdjunto extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DOCUMENTO_ADJUNTO")
    private Long idDocumentoAdjunto;
    @Size(max = 200)
    @Column(name = "RUTA_ALFRESCO")
    private String rutaAlfresco;
    @Size(max = 500)
//    @Column(name = "NOMBRE")
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombre;
    @Size(max = 4000)
    @Column(name = "COMENTARIO")
    private String comentario;
//    @Column(name = "ID_TIPO_DOCUMENTO_CARGA")
    @Column(name = "TIPO_DOCUMENTO_CARGA")
    private Long idTipoDocumentoCarga;
    @Size(max = 250)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "FECHA_CARGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCarga;
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDocumento;
    @Column(name = "FECHA_CAPTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaptura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private String estado;
    
    @JoinColumn(name = "ID_UNIDAD_SUPERVISADA", referencedColumnName = "ID_UNIDAD_SUPERVISADA")
    @ManyToOne(fetch = FetchType.LAZY)
    private MdiUnidadSupervisada idUnidadSupervisada;
    @JoinColumn(name = "ID_LOCADOR", referencedColumnName = "ID_LOCADOR")
    @ManyToOne(fetch = FetchType.LAZY)
    private MdiLocador idLocador;
    @JoinColumn(name = "ID_INST_ACAD_LOCADOR", referencedColumnName = "ID_INST_ACAD_LOCADOR")
    @ManyToOne(fetch = FetchType.LAZY)
    private MdiInstAcadLocador idInstAcadLocador;
    
    @OneToMany(mappedBy = "idDocumentoAdjunto", fetch = FetchType.LAZY)
    private List<PghRequisito> pghRequisitoList;

    public MdiDocumentoAdjunto() {
    }

    public MdiDocumentoAdjunto(Long idDocumentoAdjunto) {
        this.idDocumentoAdjunto = idDocumentoAdjunto;
    }

    public MdiDocumentoAdjunto(Long idDocumentoAdjunto, String estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idDocumentoAdjunto = idDocumentoAdjunto;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdDocumentoAdjunto() {
        return idDocumentoAdjunto;
    }

    public void setIdDocumentoAdjunto(Long idDocumentoAdjunto) {
        this.idDocumentoAdjunto = idDocumentoAdjunto;
    }

    public String getRutaAlfresco() {
        return rutaAlfresco;
    }

    public void setRutaAlfresco(String rutaAlfresco) {
        this.rutaAlfresco = rutaAlfresco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getIdTipoDocumentoCarga() {
        return idTipoDocumentoCarga;
    }

    public void setIdTipoDocumentoCarga(Long idTipoDocumentoCarga) {
        this.idTipoDocumentoCarga = idTipoDocumentoCarga;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<PghRequisito> getPghRequisitoList() {
        return pghRequisitoList;
    }

    public void setPghRequisitoList(List<PghRequisito> pghRequisitoList) {
        this.pghRequisitoList = pghRequisitoList;
    }
    
    public MdiUnidadSupervisada getIdUnidadSupervisada() {
        return idUnidadSupervisada;
    }

    public void setIdUnidadSupervisada(MdiUnidadSupervisada idUnidadSupervisada) {
        this.idUnidadSupervisada = idUnidadSupervisada;
    }
    
    public MdiLocador getIdLocador() {
        return idLocador;
    }

    public void setIdLocador(MdiLocador idLocador) {
        this.idLocador = idLocador;
    }
    
    public MdiInstAcadLocador getIdInstAcadLocador() {
        return idInstAcadLocador;
    }

    public void setIdInstAcadLocador(MdiInstAcadLocador idInstAcadLocador) {
        this.idInstAcadLocador = idInstAcadLocador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocumentoAdjunto != null ? idDocumentoAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiDocumentoAdjunto)) {
            return false;
        }
        MdiDocumentoAdjunto other = (MdiDocumentoAdjunto) object;
        if ((this.idDocumentoAdjunto == null && other.idDocumentoAdjunto != null) || (this.idDocumentoAdjunto != null && !this.idDocumentoAdjunto.equals(other.idDocumentoAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinergmin.consulta.domain.MdiDocumentoAdjunto[ idDocumentoAdjunto=" + idDocumentoAdjunto + " ]";
    } 
}

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_CONTRATO_DOCUMENTO_ADJUNTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findAll", query = "SELECT m FROM MdiContratoDocumentoAdjunto m"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByIdContratoDocumentoAdjunto", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.idContratoDocumentoAdjunto = :idContratoDocumentoAdjunto"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByTipoDocumentoCarga", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.tipoDocumentoCarga = :tipoDocumentoCarga"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByNombreArchivo", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByTitulo", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.titulo = :titulo"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByFechaDocumento", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.fechaDocumento = :fechaDocumento"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByComentario", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.comentario = :comentario"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByFechaCarga", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.fechaCarga = :fechaCarga"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByEstado", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByUsuarioCreacion", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByFechaCreacion", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByTerminalCreacion", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByUsuarioActualizacion", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByFechaActualizacion", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByTerminalActualizacion", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.terminalActualizacion = :terminalActualizacion"),
    @NamedQuery(name = "MdiContratoDocumentoAdjunto.findByRutaAlfresco", query = "SELECT m FROM MdiContratoDocumentoAdjunto m WHERE m.rutaAlfresco = :rutaAlfresco")})
public class MdiContratoDocumentoAdjunto extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONTRATO_DOCUMENTO_ADJUNTO")
    private Long idContratoDocumentoAdjunto;
    @Basic(optional = false)
    @Column(name = "TIPO_DOCUMENTO_CARGA")
    private long tipoDocumentoCarga;
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaDocumento;
    @Column(name = "COMENTARIO")
    private String comentario;
    @Column(name = "FECHA_CARGA")
    @Temporal(TemporalType.DATE)
    private Date fechaCarga;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @Column(name = "RUTA_ALFRESCO")
    private String rutaAlfresco;
    @JoinColumn(name = "ID_CONTRATO_EVALUACION", referencedColumnName = "ID_CONTRATO_EVALUACION")
    @ManyToOne
    private MdiContratoEvaluacion idContratoEvaluacion;
    @JoinColumn(name = "ID_CONTRATO_EMPRESA_LOCADOR", referencedColumnName = "ID_CONTRATO_EMPRESA_LOCADOR")
    @ManyToOne
    private MdiContratoEmpresaLocador idContratoEmpresaLocador;
    @JoinColumn(name = "ID_ADENDA_EMPRESA_LOCADOR", referencedColumnName = "ID_ADENDA_EMPRESA_LOCADOR")
    @ManyToOne
    private MdiAdendaEmpresaLocador idAdendaEmpresaLocador;

    public MdiContratoDocumentoAdjunto() {
    }

    public MdiContratoDocumentoAdjunto(Long idContratoDocumentoAdjunto) {
        this.idContratoDocumentoAdjunto = idContratoDocumentoAdjunto;
    }

    public MdiContratoDocumentoAdjunto(Long idContratoDocumentoAdjunto, long tipoDocumentoCarga, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idContratoDocumentoAdjunto = idContratoDocumentoAdjunto;
        this.tipoDocumentoCarga = tipoDocumentoCarga;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdContratoDocumentoAdjunto() {
        return idContratoDocumentoAdjunto;
    }

    public void setIdContratoDocumentoAdjunto(Long idContratoDocumentoAdjunto) {
        this.idContratoDocumentoAdjunto = idContratoDocumentoAdjunto;
    }

    public long getTipoDocumentoCarga() {
        return tipoDocumentoCarga;
    }

    public void setTipoDocumentoCarga(long tipoDocumentoCarga) {
        this.tipoDocumentoCarga = tipoDocumentoCarga;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getRutaAlfresco() {
        return rutaAlfresco;
    }

    public void setRutaAlfresco(String rutaAlfresco) {
        this.rutaAlfresco = rutaAlfresco;
    }

    public MdiContratoEvaluacion getIdContratoEvaluacion() {
        return idContratoEvaluacion;
    }

    public void setIdContratoEvaluacion(MdiContratoEvaluacion idContratoEvaluacion) {
        this.idContratoEvaluacion = idContratoEvaluacion;
    }

    public MdiContratoEmpresaLocador getIdContratoEmpresaLocador() {
        return idContratoEmpresaLocador;
    }

    public void setIdContratoEmpresaLocador(MdiContratoEmpresaLocador idContratoEmpresaLocador) {
        this.idContratoEmpresaLocador = idContratoEmpresaLocador;
    }

    public MdiAdendaEmpresaLocador getIdAdendaEmpresaLocador() {
        return idAdendaEmpresaLocador;
    }

    public void setIdAdendaEmpresaLocador(MdiAdendaEmpresaLocador idAdendaEmpresaLocador) {
        this.idAdendaEmpresaLocador = idAdendaEmpresaLocador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoDocumentoAdjunto != null ? idContratoDocumentoAdjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiContratoDocumentoAdjunto)) {
            return false;
        }
        MdiContratoDocumentoAdjunto other = (MdiContratoDocumentoAdjunto) object;
        if ((this.idContratoDocumentoAdjunto == null && other.idContratoDocumentoAdjunto != null) || (this.idContratoDocumentoAdjunto != null && !this.idContratoDocumentoAdjunto.equals(other.idContratoDocumentoAdjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiContratoDocumentoAdjunto[ idContratoDocumentoAdjunto=" + idContratoDocumentoAdjunto + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "MDI_CONCURSO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiConcurso.findAll", query = "SELECT m FROM MdiConcurso m"),
    @NamedQuery(name = "MdiConcurso.findByIdConcurso", query = "SELECT m FROM MdiConcurso m WHERE m.idConcurso = :idConcurso"),
    @NamedQuery(name = "MdiConcurso.findByNumeroConcurso", query = "SELECT m FROM MdiConcurso m WHERE m.numeroConcurso = :numeroConcurso"),
    @NamedQuery(name = "MdiConcurso.findByNombreConcurso", query = "SELECT m FROM MdiConcurso m WHERE m.nombreConcurso = :nombreConcurso"),
    @NamedQuery(name = "MdiConcurso.findByDescripcionConcurso", query = "SELECT m FROM MdiConcurso m WHERE m.descripcionConcurso = :descripcionConcurso"),
    @NamedQuery(name = "MdiConcurso.findByNumeroPlazas", query = "SELECT m FROM MdiConcurso m WHERE m.numeroPlazas = :numeroPlazas"),
    @NamedQuery(name = "MdiConcurso.findByFechaConvocatoria", query = "SELECT m FROM MdiConcurso m WHERE m.fechaConvocatoria = :fechaConvocatoria"),
    @NamedQuery(name = "MdiConcurso.findByFechaPropuestaTecnica", query = "SELECT m FROM MdiConcurso m WHERE m.fechaPropuestaTecnica = :fechaPropuestaTecnica"),
    @NamedQuery(name = "MdiConcurso.findByFechaPropuestaEconomica", query = "SELECT m FROM MdiConcurso m WHERE m.fechaPropuestaEconomica = :fechaPropuestaEconomica"),
    @NamedQuery(name = "MdiConcurso.findByFechaEmisionResultado", query = "SELECT m FROM MdiConcurso m WHERE m.fechaEmisionResultado = :fechaEmisionResultado"),
    @NamedQuery(name = "MdiConcurso.findByEstado", query = "SELECT m FROM MdiConcurso m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiConcurso.findByUsuarioCreacion", query = "SELECT m FROM MdiConcurso m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiConcurso.findByFechaCreacion", query = "SELECT m FROM MdiConcurso m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiConcurso.findByTerminalCreacion", query = "SELECT m FROM MdiConcurso m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiConcurso.findByUsuarioActualizacion", query = "SELECT m FROM MdiConcurso m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiConcurso.findByFechaActualizacion", query = "SELECT m FROM MdiConcurso m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiConcurso.findByTerminalActualizacion", query = "SELECT m FROM MdiConcurso m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiConcurso extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CONCURSO")
    private Long idConcurso;
    @Basic(optional = false)
    @Column(name = "NUMERO_CONCURSO")
    private String numeroConcurso;
    @Basic(optional = false)
    @Column(name = "NOMBRE_CONCURSO")
    private String nombreConcurso;
    @Column(name = "DESCRIPCION_CONCURSO")
    private String descripcionConcurso;
    @Column(name = "NUMERO_PLAZAS")
    private BigInteger numeroPlazas;
    @Column(name = "FECHA_CONVOCATORIA")
    @Temporal(TemporalType.DATE)
    private Date fechaConvocatoria;
    @Column(name = "FECHA_PROPUESTA_TECNICA")
    @Temporal(TemporalType.DATE)
    private Date fechaPropuestaTecnica;
    @Column(name = "FECHA_PROPUESTA_ECONOMICA")
    @Temporal(TemporalType.DATE)
    private Date fechaPropuestaEconomica;
    @Column(name = "FECHA_EMISION_RESULTADO")
    @Temporal(TemporalType.DATE)
    private Date fechaEmisionResultado;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;    
    @OneToMany(mappedBy = "idConcurso")
    private List<MdiContratoEmpresaLocador> mdiContratoEmpresaLocadorList;

    public MdiConcurso() {
    }

    public MdiConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public MdiConcurso(Long idConcurso, String numeroConcurso, String nombreConcurso, char estado, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idConcurso = idConcurso;
        this.numeroConcurso = numeroConcurso;
        this.nombreConcurso = nombreConcurso;
        this.estado = estado;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public String getNumeroConcurso() {
        return numeroConcurso;
    }

    public void setNumeroConcurso(String numeroConcurso) {
        this.numeroConcurso = numeroConcurso;
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public void setNombreConcurso(String nombreConcurso) {
        this.nombreConcurso = nombreConcurso;
    }

    public String getDescripcionConcurso() {
        return descripcionConcurso;
    }

    public void setDescripcionConcurso(String descripcionConcurso) {
        this.descripcionConcurso = descripcionConcurso;
    }

    public BigInteger getNumeroPlazas() {
        return numeroPlazas;
    }

    public void setNumeroPlazas(BigInteger numeroPlazas) {
        this.numeroPlazas = numeroPlazas;
    }

    public Date getFechaConvocatoria() {
        return fechaConvocatoria;
    }

    public void setFechaConvocatoria(Date fechaConvocatoria) {
        this.fechaConvocatoria = fechaConvocatoria;
    }

    public Date getFechaPropuestaTecnica() {
        return fechaPropuestaTecnica;
    }

    public void setFechaPropuestaTecnica(Date fechaPropuestaTecnica) {
        this.fechaPropuestaTecnica = fechaPropuestaTecnica;
    }

    public Date getFechaPropuestaEconomica() {
        return fechaPropuestaEconomica;
    }

    public void setFechaPropuestaEconomica(Date fechaPropuestaEconomica) {
        this.fechaPropuestaEconomica = fechaPropuestaEconomica;
    }

    public Date getFechaEmisionResultado() {
        return fechaEmisionResultado;
    }

    public void setFechaEmisionResultado(Date fechaEmisionResultado) {
        this.fechaEmisionResultado = fechaEmisionResultado;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MdiContratoEmpresaLocador> getMdiContratoEmpresaLocadorList() {
        return mdiContratoEmpresaLocadorList;
    }

    public void setMdiContratoEmpresaLocadorList(List<MdiContratoEmpresaLocador> mdiContratoEmpresaLocadorList) {
        this.mdiContratoEmpresaLocadorList = mdiContratoEmpresaLocadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcurso != null ? idConcurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiConcurso)) {
            return false;
        }
        MdiConcurso other = (MdiConcurso) object;
        if ((this.idConcurso == null && other.idConcurso != null) || (this.idConcurso != null && !this.idConcurso.equals(other.idConcurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.MdiConcurso[ idConcurso=" + idConcurso + " ]";
    }
    
}

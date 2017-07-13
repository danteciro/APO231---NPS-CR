/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cflorian
 */
@Entity
@Table(name = "MDI_ACTIVIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MdiActividad.findAll", query = "SELECT m FROM MdiActividad m"),
    @NamedQuery(name = "MdiActividad.findByIdActividad", query = "SELECT m FROM MdiActividad m WHERE m.idActividad = :idActividad"),
    @NamedQuery(name = "MdiActividad.findByIdActividadPadre", query = "SELECT m FROM MdiActividad m WHERE m.idActividadPadre = :idActividadPadre"),
    @NamedQuery(name = "MdiActividad.findByEstado", query = "SELECT m FROM MdiActividad m WHERE m.estado = :estado"),
    @NamedQuery(name = "MdiActividad.findByCodigo", query = "SELECT m FROM MdiActividad m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "MdiActividad.findByNombre", query = "SELECT m FROM MdiActividad m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MdiActividad.findByNivel", query = "SELECT m FROM MdiActividad m WHERE m.nivel = :nivel"),
    @NamedQuery(name = "MdiActividad.findByEsMayor", query = "SELECT m FROM MdiActividad m WHERE m.esMayor = :esMayor"),
    @NamedQuery(name = "MdiActividad.findByEtapa", query = "SELECT m FROM MdiActividad m WHERE m.etapa = :etapa"),
    @NamedQuery(name = "MdiActividad.findByUsuarioCreacion", query = "SELECT m FROM MdiActividad m WHERE m.usuarioCreacion = :usuarioCreacion"),
    @NamedQuery(name = "MdiActividad.findByFechaCreacion", query = "SELECT m FROM MdiActividad m WHERE m.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "MdiActividad.findByTerminalCreacion", query = "SELECT m FROM MdiActividad m WHERE m.terminalCreacion = :terminalCreacion"),
    @NamedQuery(name = "MdiActividad.findByUsuarioActualizacion", query = "SELECT m FROM MdiActividad m WHERE m.usuarioActualizacion = :usuarioActualizacion"),
    @NamedQuery(name = "MdiActividad.findByFechaActualizacion", query = "SELECT m FROM MdiActividad m WHERE m.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "MdiActividad.findByTerminalActualizacion", query = "SELECT m FROM MdiActividad m WHERE m.terminalActualizacion = :terminalActualizacion")})
public class MdiActividad extends Auditoria {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_ACTIVIDAD")
    private Long idActividad;
    @Column(name = "ID_ACTIVIDAD_PADRE")
    private Long idActividadPadre;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private char estado;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NIVEL")
    private Integer nivel;
    @Column(name = "ES_MAYOR")
    private String esMayor;
    @Column(name = "ETAPA")
    private Long etapa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiActividad")
    private List<PghProcesoObligacionTipo> pghProcesoObligacionTipoList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mdiActividad1")
    private MdiActividad mdiActividad;
    @JoinColumn(name = "ID_ACTIVIDAD", referencedColumnName = "ID_ACTIVIDAD", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MdiActividad mdiActividad1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mdiActividad")
    private List<MdiActividadNivel> mdiActividadNivelList;
    @OneToMany(mappedBy = "idActividad")
    private List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList;
    @OneToMany(mappedBy = "idActividad")
    private List<MdiUnidadSupervisada> mdiUnidadSupervisadaList;
    @OneToMany(mappedBy = "idActividad")
    private List<PghProcTramActividad> pghProcTramActividadList;
    @OneToMany(mappedBy = "idActividad")
    private List<PghCnfTramiteActividad> pghCnfTramiteActividadList;
    /* OSINE_SFS-600 - REQF-0009 - Inicio */
    @Column(name = "ORDEN_NPS")
    private Integer ordenNps;
    /* OSINE_SFS-600 - REQF-0009 - Fin */
    public MdiActividad() {
    }

    public MdiActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public MdiActividad(Long idActividad, char estado, String codigo, String usuarioCreacion, Date fechaCreacion, String terminalCreacion) {
        this.idActividad = idActividad;
        this.estado = estado;
        this.codigo = codigo;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.terminalCreacion = terminalCreacion;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Long getIdActividadPadre() {
        return idActividadPadre;
    }

    public void setIdActividadPadre(Long idActividadPadre) {
        this.idActividadPadre = idActividadPadre;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public String getEsMayor() {
        return esMayor;
    }

    public void setEsMayor(String esMayor) {
        this.esMayor = esMayor;
    }

    public Long getEtapa() {
        return etapa;
    }

    public void setEtapa(Long etapa) {
        this.etapa = etapa;
    }

    @XmlTransient
    public List<PghProcesoObligacionTipo> getPghProcesoObligacionTipoList() {
        return pghProcesoObligacionTipoList;
    }

    public void setPghProcesoObligacionTipoList(List<PghProcesoObligacionTipo> pghProcesoObligacionTipoList) {
        this.pghProcesoObligacionTipoList = pghProcesoObligacionTipoList;
    }

    public MdiActividad getMdiActividad() {
        return mdiActividad;
    }

    public void setMdiActividad(MdiActividad mdiActividad) {
        this.mdiActividad = mdiActividad;
    }

    public MdiActividad getMdiActividad1() {
        return mdiActividad1;
    }

    public void setMdiActividad1(MdiActividad mdiActividad1) {
        this.mdiActividad1 = mdiActividad1;
    }

    @XmlTransient
    public List<MdiActividadNivel> getMdiActividadNivelList() {
        return mdiActividadNivelList;
    }

    public void setMdiActividadNivelList(List<MdiActividadNivel> mdiActividadNivelList) {
        this.mdiActividadNivelList = mdiActividadNivelList;
    }

    @XmlTransient
    public List<PghCnfRequProcedimiento> getPghCnfRequProcedimientoList() {
        return pghCnfRequProcedimientoList;
    }

    public void setPghCnfRequProcedimientoList(List<PghCnfRequProcedimiento> pghCnfRequProcedimientoList) {
        this.pghCnfRequProcedimientoList = pghCnfRequProcedimientoList;
    }

    @XmlTransient
    public List<MdiUnidadSupervisada> getMdiUnidadSupervisadaList() {
        return mdiUnidadSupervisadaList;
    }

    public void setMdiUnidadSupervisadaList(List<MdiUnidadSupervisada> mdiUnidadSupervisadaList) {
        this.mdiUnidadSupervisadaList = mdiUnidadSupervisadaList;
    }

    @XmlTransient
    public List<PghProcTramActividad> getPghProcTramActividadList() {
        return pghProcTramActividadList;
    }

    public void setPghProcTramActividadList(List<PghProcTramActividad> pghProcTramActividadList) {
        this.pghProcTramActividadList = pghProcTramActividadList;
    }

    @XmlTransient
    public List<PghCnfTramiteActividad> getPghCnfTramiteActividadList() {
        return pghCnfTramiteActividadList;
    }

    public void setPghCnfTramiteActividadList(List<PghCnfTramiteActividad> pghCnfTramiteActividadList) {
        this.pghCnfTramiteActividadList = pghCnfTramiteActividadList;
    }
              
    public Integer getOrdenNps() {
		return ordenNps;
	}

	public void setOrdenNps(Integer ordenNps) {
		this.ordenNps = ordenNps;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idActividad != null ? idActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MdiActividad)) {
            return false;
        }
        MdiActividad other = (MdiActividad) object;
        if ((this.idActividad == null && other.idActividad != null) || (this.idActividad != null && !this.idActividad.equals(other.idActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gob.osinerg.consulta.domain.MdiActividad[ idActividad=" + idActividad + " ]";
    }
    
}

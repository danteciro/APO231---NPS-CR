/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import sun.misc.BASE64Encoder;

/**
 *
 * @author lchancayauri
 */
public class ObligacionesDTO implements Serializable{
	
    private Long idObligacion;
    private String descripcion;
    private Long idBaseLegal;
    private String descripcionBaseLegal;
    private String tipificacion;
    private String monto;
    private Long idActividad;
    private Long idGrupoActividad;
    private Long idSupervision;
    private String descripcionOligacionTipo;  
    private Long idClasificacion;
    private String baseLegal;
    private String nombreDocAdjunto;
    private Long idDocAdjunto;
    private byte[] archivo;
    private String archivo64;
    
    public Long getIdObligacion() {
        return idObligacion;
    }

    public void setIdObligacion(Long idObligacion) {
        this.idObligacion = idObligacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdBaseLegal() {
        return idBaseLegal;
    }

    public void setIdBaseLegal(Long idBaseLegal) {
        this.idBaseLegal = idBaseLegal;
    }

    public String getDescripcionBaseLegal() {
        return descripcionBaseLegal;
    }

    public void setDescripcionBaseLegal(String descripcionBaseLegal) {
        this.descripcionBaseLegal = descripcionBaseLegal;
    }

    public String getTipificacion() {
        return tipificacion;
    }

    public void setTipificacion(String tipificacion) {
        this.tipificacion = tipificacion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }

    public Long getIdGrupoActividad() {
        return idGrupoActividad;
    }

    public void setIdGrupoActividad(Long idGrupoActividad) {
        this.idGrupoActividad = idGrupoActividad;
    }

    public Long getIdSupervision() {
        return idSupervision;
    }

    public void setIdSupervision(Long idSupervision) {
        this.idSupervision = idSupervision;
    }

	public Long getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(Long idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public String getDescripcionOligacionTipo() {
		return descripcionOligacionTipo;
	}

	public void setDescripcionOligacionTipo(String descripcionOligacionTipo) {
		this.descripcionOligacionTipo = descripcionOligacionTipo;
	}

	public String getBaseLegal() {
		return baseLegal;
	}

	public void setBaseLegal(String baseLegal) {
		this.baseLegal = baseLegal;
	}

	public String getNombreDocAdjunto() {
		return nombreDocAdjunto;
	}

	public void setNombreDocAdjunto(String nombreDocAdjunto) {
		this.nombreDocAdjunto = nombreDocAdjunto;
	}

	public Long getIdDocAdjunto() {
		return idDocAdjunto;
	}

	public void setIdDocAdjunto(Long idDocAdjunto) {
		this.idDocAdjunto = idDocAdjunto;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public String getArchivo64() {
		return archivo64;
	}

	public void setArchivo64(String archivo64) {
		this.archivo64 = archivo64;
	}
}


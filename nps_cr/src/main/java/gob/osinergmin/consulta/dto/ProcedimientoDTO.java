/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author lchancayauri
 */
public class ProcedimientoDTO implements Serializable{
    
    private Long idProcedimiento;
    private String item;
    private String denominacion;
    private String baseLegal;
    private BigDecimal derechoTramitacion;
    private Long idCalificacion;
    private Long idSilencioAdministrativo;
    private BigDecimal plazoResolver;
    private Long idInicioProcedimiento;
    private Long idAutoridadCompetente;
    private Long idApelacion;
    private Long idReconsideracion;
    private Long idAnexoRrh;
    private Long idValorUit;
    private String estado;
    private String notaProcedimiento;
    
    private String silencioAdministrativo;
    private Double costoTramite;
    private String silencioAdministrativoMensaje;
    private String indicacionLegible;
    private String indicacionPlazo;
    
    public Long getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(Long idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getBaseLegal() {
        return baseLegal;
    }

    public void setBaseLegal(String baseLegal) {
        this.baseLegal = baseLegal;
    }

    public BigDecimal getDerechoTramitacion() {
        return derechoTramitacion;
    }

    public void setDerechoTramitacion(BigDecimal derechoTramitacion) {
        this.derechoTramitacion = derechoTramitacion;
    }

    public Long getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Long idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Long getIdSilencioAdministrativo() {
        return idSilencioAdministrativo;
    }

    public void setIdSilencioAdministrativo(Long idSilencioAdministrativo) {
        this.idSilencioAdministrativo = idSilencioAdministrativo;
    }

    public BigDecimal getPlazoResolver() {
        return plazoResolver;
    }

    public void setPlazoResolver(BigDecimal plazoResolver) {
        this.plazoResolver = plazoResolver;
    }

    public Long getIdInicioProcedimiento() {
        return idInicioProcedimiento;
    }

    public void setIdInicioProcedimiento(Long idInicioProcedimiento) {
        this.idInicioProcedimiento = idInicioProcedimiento;
    }

    public Long getIdAutoridadCompetente() {
        return idAutoridadCompetente;
    }

    public void setIdAutoridadCompetente(Long idAutoridadCompetente) {
        this.idAutoridadCompetente = idAutoridadCompetente;
    }

    public Long getIdApelacion() {
        return idApelacion;
    }

    public void setIdApelacion(Long idApelacion) {
        this.idApelacion = idApelacion;
    }

    public Long getIdReconsideracion() {
        return idReconsideracion;
    }

    public void setIdReconsideracion(Long idReconsideracion) {
        this.idReconsideracion = idReconsideracion;
    }

    public Long getIdAnexoRrh() {
        return idAnexoRrh;
    }

    public void setIdAnexoRrh(Long idAnexoRrh) {
        this.idAnexoRrh = idAnexoRrh;
    }

    public Long getIdValorUit() {
        return idValorUit;
    }

    public void setIdValorUit(Long idValorUit) {
        this.idValorUit = idValorUit;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotaProcedimiento() {
        return notaProcedimiento;
    }

    public void setNotaProcedimiento(String notaProcedimiento) {
        this.notaProcedimiento = notaProcedimiento;
    }

    public String getSilencioAdministrativo() {
        return silencioAdministrativo;
    }

    public void setSilencioAdministrativo(String silencioAdministrativo) {
        this.silencioAdministrativo = silencioAdministrativo;
    }

    public Double getCostoTramite() {
        return costoTramite;
    }

    public void setCostoTramite(Double costoTramite) {
        this.costoTramite = costoTramite;
    }

    public String getSilencioAdministrativoMensaje() {
        return silencioAdministrativoMensaje;
    }

    public void setSilencioAdministrativoMensaje(String silencioAdministrativoMensaje) {
        this.silencioAdministrativoMensaje = silencioAdministrativoMensaje;
    }

    public String getIndicacionLegible() {
        return indicacionLegible;
    }

    public void setIndicacionLegible(String indicacionLegible) {
        this.indicacionLegible = indicacionLegible;
    }

    public String getIndicacionPlazo() {
        return indicacionPlazo;
    }

    public void setIndicacionPlazo(String indicacionPlazo) {
        this.indicacionPlazo = indicacionPlazo;
    }
    
    @Override
    public String toString() {
        return "ProcedimientoDTO{" + "idProcedimiento=" + idProcedimiento + ", item=" + item + ", denominacion=" + denominacion + ", baseLegal=" + baseLegal + ", derechoTramitacion=" + derechoTramitacion + ", idCalificacion=" + idCalificacion + ", idSilencioAdministrativo=" + idSilencioAdministrativo + ", plazoResolver=" + plazoResolver + ", idInicioProcedimiento=" + idInicioProcedimiento + ", idAutoridadCompetente=" + idAutoridadCompetente + ", idApelacion=" + idApelacion + ", idReconsideracion=" + idReconsideracion + ", idAnexoRrh=" + idAnexoRrh + ", idValorUit=" + idValorUit + ", estado=" + estado + ", notaProcedimiento=" + notaProcedimiento + '}';
    }
    
    
}

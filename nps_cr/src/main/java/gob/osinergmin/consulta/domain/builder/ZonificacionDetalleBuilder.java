/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.osinergmin.consulta.domain.builder;

import gob.osinergmin.consulta.domain.PghZonificacionDetalle;
import gob.osinergmin.consulta.dto.ZonificacionDetalleDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 *
 * @author lchancayauri
 */
public class ZonificacionDetalleBuilder {

    public static List<ZonificacionDetalleDTO> getListaZonificacionDetalle(List<PghZonificacionDetalle> listaZonificacionDetalle) {
        List<ZonificacionDetalleDTO> listaZonificacionDetalleDTO = new ArrayList<ZonificacionDetalleDTO>();
        if (!CollectionUtils.isEmpty(listaZonificacionDetalle)) {
            ZonificacionDetalleDTO zonificacionDetalleDTO = new ZonificacionDetalleDTO();
            for (PghZonificacionDetalle zonificacionDetalle : listaZonificacionDetalle) {
                zonificacionDetalleDTO = getZonificacionDetalle(zonificacionDetalle);
                listaZonificacionDetalleDTO.add(zonificacionDetalleDTO);
            }
        }
        return listaZonificacionDetalleDTO;
    }

    public static ZonificacionDetalleDTO getZonificacionDetalle(PghZonificacionDetalle zonificacionDetalle) {
        ZonificacionDetalleDTO zonificacionDetalleDTO = new ZonificacionDetalleDTO();
        if (zonificacionDetalle != null) {
            zonificacionDetalleDTO.setIdZonificacionDetalle(zonificacionDetalle.getIdZonificacionDetalle());
            zonificacionDetalleDTO.setEstado(zonificacionDetalle.getEstado());
//            zonificacionDetalleDTO.setIdDepartamento(zonificacionDetalle.getMdiUbigeo().getMdiUbigeoPK().getIdDepartamento());
//            zonificacionDetalleDTO.setIdProvincia(zonificacionDetalle.getMdiUbigeo().getMdiUbigeoPK().getIdProvincia());
//            zonificacionDetalleDTO.setIdDistrito(zonificacionDetalle.getMdiUbigeo().getMdiUbigeoPK().getIdDistrito());
            zonificacionDetalleDTO.setIdMacroRegion(zonificacionDetalle.getIdMacroRegion());
            zonificacionDetalleDTO.setIdRegion(zonificacionDetalle.getIdRegion());
            zonificacionDetalleDTO.setIdZonificacion(zonificacionDetalle.getIdZonificacion().getIdZonificacion());
        }
        return zonificacionDetalleDTO;
    }
}

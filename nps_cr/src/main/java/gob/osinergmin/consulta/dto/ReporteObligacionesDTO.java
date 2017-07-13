package gob.osinergmin.consulta.dto;

public class ReporteObligacionesDTO {

	private String item;
	private String mostrarItem;
	private String rowSpanItem;
	private String obligacionNormativa;
	private String mostrarObligacionNormativa;
	private String rowSpanObligacionNormativa;	
	private String baseLegal;
	private String mostrarBaseLegal;
	private String rowSpanBaseLegal;
	private String tipificacion;
	private String mostrarTipificacion;
	private String rowSpanTipificacion;
	private String sancion;		
	private String mostrarSancion;
	private String rowSpanSancion;	
	private String incumplimiento;
	private String mostrarIncumplimiento;
	private String rowSpanIncumplimiento;	
	private String sancionEspecifica;
	private String mostrarsancionEspecifica;
	private String rowSpanSancionEspecifica;
	private String baseLegalCriterioEspecifico;
	private String mostrarBaseLegalCriterioEspecifico;
	private String rowSpanBaseLegalCriterioEspecifico;
	private String criticidad;
	private String mostrarCriticidad;
	private String rowSpanCriticidad;
	/* OSINE_SFS-600 - REQF-0015 - Inicio */
	private String adjunto;
	private String mostrarAdjunto;
	private String rowSpanAdjunto;
	/* OSINE_SFS-600 - REQF-0015 - Fin */
	
	
	
	public ReporteObligacionesDTO(){}
	public ReporteObligacionesDTO(String item, String mostrarItem,
			String rowSpanItem, String obligacionNormativa,
			String mostrarObligacionNormativa,
			String rowSpanObligacionNormativa, String baseLegal,
			String mostrarBaseLegal, String rowSpanBaseLegal,
			String tipificacion, String mostrarTipificacion,
			String rowSpanTipificacion, String sancion, String mostrarSancion,
			String rowSpanSancion, String incumplimiento,
			String mostrarIncumplimiento, String rowSpanIncumplimiento,
			String sancionEspecifica, String mostrarsancionEspecifica,
			String rowSpanSancionEspecifica,
			String baseLegalCriterioEspecifico,
			String mostrarBaseLegalCriterioEspecifico,
			String rowSpanBaseLegalCriterioEspecifico, String criticidad,
			String mostrarCriticidad, String rowSpanCriticidad) {
		super();
		this.item = item;
		this.mostrarItem = mostrarItem;
		this.rowSpanItem = rowSpanItem;
		this.obligacionNormativa = obligacionNormativa;
		this.mostrarObligacionNormativa = mostrarObligacionNormativa;
		this.rowSpanObligacionNormativa = rowSpanObligacionNormativa;
		this.baseLegal = baseLegal;
		this.mostrarBaseLegal = mostrarBaseLegal;
		this.rowSpanBaseLegal = rowSpanBaseLegal;
		this.tipificacion = tipificacion;
		this.mostrarTipificacion = mostrarTipificacion;
		this.rowSpanTipificacion = rowSpanTipificacion;
		this.sancion = sancion;
		this.mostrarSancion = mostrarSancion;
		this.rowSpanSancion = rowSpanSancion;
		this.incumplimiento = incumplimiento;
		this.mostrarIncumplimiento = mostrarIncumplimiento;
		this.rowSpanIncumplimiento = rowSpanIncumplimiento;
		this.sancionEspecifica = sancionEspecifica;
		this.mostrarsancionEspecifica = mostrarsancionEspecifica;
		this.rowSpanSancionEspecifica = rowSpanSancionEspecifica;
		this.baseLegalCriterioEspecifico = baseLegalCriterioEspecifico;
		this.mostrarBaseLegalCriterioEspecifico = mostrarBaseLegalCriterioEspecifico;
		this.rowSpanBaseLegalCriterioEspecifico = rowSpanBaseLegalCriterioEspecifico;
		this.criticidad = criticidad;
		this.mostrarCriticidad = mostrarCriticidad;
		this.rowSpanCriticidad = rowSpanCriticidad;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getMostrarItem() {
		return mostrarItem;
	}
	public void setMostrarItem(String mostrarItem) {
		this.mostrarItem = mostrarItem;
	}
	public String getRowSpanItem() {
		return rowSpanItem;
	}
	public void setRowSpanItem(String rowSpanItem) {
		this.rowSpanItem = rowSpanItem;
	}
	public String getObligacionNormativa() {
		return obligacionNormativa;
	}
	public void setObligacionNormativa(String obligacionNormativa) {
		this.obligacionNormativa = obligacionNormativa;
	}
	public String getMostrarObligacionNormativa() {
		return mostrarObligacionNormativa;
	}
	public void setMostrarObligacionNormativa(String mostrarObligacionNormativa) {
		this.mostrarObligacionNormativa = mostrarObligacionNormativa;
	}
	public String getRowSpanObligacionNormativa() {
		return rowSpanObligacionNormativa;
	}
	public void setRowSpanObligacionNormativa(String rowSpanObligacionNormativa) {
		this.rowSpanObligacionNormativa = rowSpanObligacionNormativa;
	}
	public String getBaseLegal() {
		return baseLegal;
	}
	public void setBaseLegal(String baseLegal) {
		this.baseLegal = baseLegal;
	}
	public String getMostrarBaseLegal() {
		return mostrarBaseLegal;
	}
	public void setMostrarBaseLegal(String mostrarBaseLegal) {
		this.mostrarBaseLegal = mostrarBaseLegal;
	}
	public String getRowSpanBaseLegal() {
		return rowSpanBaseLegal;
	}
	public void setRowSpanBaseLegal(String rowSpanBaseLegal) {
		this.rowSpanBaseLegal = rowSpanBaseLegal;
	}
	public String getTipificacion() {
		return tipificacion;
	}
	public void setTipificacion(String tipificacion) {
		this.tipificacion = tipificacion;
	}
	public String getMostrarTipificacion() {
		return mostrarTipificacion;
	}
	public void setMostrarTipificacion(String mostrarTipificacion) {
		this.mostrarTipificacion = mostrarTipificacion;
	}
	public String getRowSpanTipificacion() {
		return rowSpanTipificacion;
	}
	public void setRowSpanTipificacion(String rowSpanTipificacion) {
		this.rowSpanTipificacion = rowSpanTipificacion;
	}
	public String getSancion() {
		return sancion;
	}
	public void setSancion(String sancion) {
		this.sancion = sancion;
	}
	public String getMostrarSancion() {
		return mostrarSancion;
	}
	public void setMostrarSancion(String mostrarSancion) {
		this.mostrarSancion = mostrarSancion;
	}
	public String getRowSpanSancion() {
		return rowSpanSancion;
	}
	public void setRowSpanSancion(String rowSpanSancion) {
		this.rowSpanSancion = rowSpanSancion;
	}
	public String getIncumplimiento() {
		return incumplimiento;
	}
	public void setIncumplimiento(String incumplimiento) {
		this.incumplimiento = incumplimiento;
	}
	public String getMostrarIncumplimiento() {
		return mostrarIncumplimiento;
	}
	public void setMostrarIncumplimiento(String mostrarIncumplimiento) {
		this.mostrarIncumplimiento = mostrarIncumplimiento;
	}
	public String getRowSpanIncumplimiento() {
		return rowSpanIncumplimiento;
	}
	public void setRowSpanIncumplimiento(String rowSpanIncumplimiento) {
		this.rowSpanIncumplimiento = rowSpanIncumplimiento;
	}
	public String getSancionEspecifica() {
		return sancionEspecifica;
	}
	public void setSancionEspecifica(String sancionEspecifica) {
		this.sancionEspecifica = sancionEspecifica;
	}
	public String getMostrarsancionEspecifica() {
		return mostrarsancionEspecifica;
	}
	public void setMostrarsancionEspecifica(String mostrarsancionEspecifica) {
		this.mostrarsancionEspecifica = mostrarsancionEspecifica;
	}
	public String getRowSpanSancionEspecifica() {
		return rowSpanSancionEspecifica;
	}
	public void setRowSpanSancionEspecifica(String rowSpanSancionEspecifica) {
		this.rowSpanSancionEspecifica = rowSpanSancionEspecifica;
	}
	public String getBaseLegalCriterioEspecifico() {
		return baseLegalCriterioEspecifico;
	}
	public void setBaseLegalCriterioEspecifico(String baseLegalCriterioEspecifico) {
		this.baseLegalCriterioEspecifico = baseLegalCriterioEspecifico;
	}
	public String getMostrarBaseLegalCriterioEspecifico() {
		return mostrarBaseLegalCriterioEspecifico;
	}
	public void setMostrarBaseLegalCriterioEspecifico(
			String mostrarBaseLegalCriterioEspecifico) {
		this.mostrarBaseLegalCriterioEspecifico = mostrarBaseLegalCriterioEspecifico;
	}
	public String getRowSpanBaseLegalCriterioEspecifico() {
		return rowSpanBaseLegalCriterioEspecifico;
	}
	public void setRowSpanBaseLegalCriterioEspecifico(
			String rowSpanBaseLegalCriterioEspecifico) {
		this.rowSpanBaseLegalCriterioEspecifico = rowSpanBaseLegalCriterioEspecifico;
	}
	public String getCriticidad() {
		return criticidad;
	}
	public void setCriticidad(String criticidad) {
		this.criticidad = criticidad;
	}
	public String getMostrarCriticidad() {
		return mostrarCriticidad;
	}
	public void setMostrarCriticidad(String mostrarCriticidad) {
		this.mostrarCriticidad = mostrarCriticidad;
	}
	public String getRowSpanCriticidad() {
		return rowSpanCriticidad;
	}
	public void setRowSpanCriticidad(String rowSpanCriticidad) {
		this.rowSpanCriticidad = rowSpanCriticidad;
	}
	/* OSINE_SFS-600 - REQF-0015 - Inicio */
	public String getAdjunto() {
		return adjunto;
	}
	public void setAdjunto(String adjunto) {
		this.adjunto = adjunto;
	}
	public String getMostrarAdjunto() {
		return mostrarAdjunto;
	}
	public void setMostrarAdjunto(String mostrarAdjunto) {
		this.mostrarAdjunto = mostrarAdjunto;
	}
	public String getRowSpanAdjunto() {
		return rowSpanAdjunto;
	}
	public void setRowSpanAdjunto(String rowSpanAdjunto) {
		this.rowSpanAdjunto = rowSpanAdjunto;
	}			
	/* OSINE_SFS-600 - REQF-0015 - Fin */
	

}

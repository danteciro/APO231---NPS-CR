package gob.osinergmin.consulta.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase utilitaria que permite realizar la exportacion al ireport
 * 
 * @author cflorian
 * @version 1.0
 * @see
 */
public class JasperUtil {

	private static final Logger log = LoggerFactory.getLogger(JasperUtil.class);

	/**
	 * Permite exportar a excel
	 * 
	 * @param reportStream
	 * @param parameters
	 * @param ds
	 * @param response
	 * @param strNombre
	 */
	public static void exportarExcel(InputStream reportStream,
			HashMap<String, Object> parameters, JRDataSource ds,
			HttpServletResponse response, String strNombre) {

		OutputStream outputStreamResponse = null;

		response.resetBuffer();
		response.setContentType(Constantes.CONTENT_TYPE_EXPORT_REPORTE_EXCEL);
		response.setHeader(Constantes.HEADER_UNO_REPORTE_EXCEL,
				Constantes.VALOR_HEADER_UNO_REPORTE_EXCEL + strNombre
						+ Constantes.EXTENSION_ARCHIVO_EXCEL);
		response.setHeader(Constantes.HEADER_DOS_REPORTE_EXCEL,
				Constantes.VALOR_DOS_REPORTE_EXCEL);
		response.setHeader(Constantes.HEADER_TRES_REPORTE_EXCEL,
				Constantes.VALOR_TRES_REPORTE_EXCEL);
		response.setDateHeader(Constantes.HEADER_CUATRO_REPORTE_EXCEL,
				Constantes.VALOR_CUATRO_REPORTE_EXCEL);

		JasperPrint jasperPrint = null;

		try {
			if (ds == null) {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters);
			} else {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters, ds);
			}
		}

		catch (JRException e1) {
			log.error(e1.getMessage(), e1);
		}

		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		try {
			outputStreamResponse = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					outputStreamResponse);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					new String[] { strNombre });
			exporter.setParameter(
					JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
					Boolean.TRUE);
			exporter.exportReport();
		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		} catch (JRException e1) {
			log.error(e1.getMessage(), e1);
		} finally {
			try {
				outputStreamResponse.flush();
				outputStreamResponse.close();
				response.flushBuffer();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * Permite exportar a excel
	 * 
	 * @param lista
	 * @param response
	 * @param strNombre
	 */
	public static void exportarExcel_v2(List<JasperPrint> lista,
			HttpServletResponse response, String strNombre) {

		OutputStream outputStreamResponse = null;

		response.resetBuffer();
		response.setContentType(Constantes.CONTENT_TYPE_EXPORT_REPORTE_EXCEL);
		response.setHeader(Constantes.HEADER_UNO_REPORTE_EXCEL,
				Constantes.VALOR_HEADER_UNO_REPORTE_EXCEL + strNombre
						+ Constantes.EXTENSION_ARCHIVO_EXCEL);
		response.setHeader(Constantes.HEADER_DOS_REPORTE_EXCEL,
				Constantes.VALOR_DOS_REPORTE_EXCEL);
		response.setHeader(Constantes.HEADER_TRES_REPORTE_EXCEL,
				Constantes.VALOR_TRES_REPORTE_EXCEL);
		response.setDateHeader(Constantes.HEADER_CUATRO_REPORTE_EXCEL,
				Constantes.VALOR_CUATRO_REPORTE_EXCEL);

		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, lista);
		try {
			outputStreamResponse = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					outputStreamResponse);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					new String[] { strNombre, "reporteBySocio",
							"reporteSemaforoByMes" });
			exporter.exportReport();

		} catch (IOException e1) {
			log.error(e1.getMessage(), e1);
		} catch (JRException e1) {
			log.error(e1.getMessage(), e1);
		} finally {
			try {
				outputStreamResponse.flush();
				outputStreamResponse.close();
				response.flushBuffer();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * Permite exportar a PDF
	 * 
	 * @param reportStream
	 * @param parameters
	 * @param ds
	 * @param response
	 * @param strNombre
	 */
	public static void exportarPDF(InputStream reportStream,
			HashMap<String, Object> parameters, JRDataSource ds,
			HttpServletResponse response, String strNombre) {
		OutputStream outputStreamResponse = null;

		response.resetBuffer();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ strNombre + "." + "pdf" + "\";");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "private");
		response.setDateHeader("Expires", 1);
		response.setContentType("application/pdf");

		JasperPrint jasperPrint = null;
		try {
			outputStreamResponse = response.getOutputStream();
			if (ds == null) {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters);
			} else {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters, ds);
			}

			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					outputStreamResponse);
			exporter.exportReport();

		} catch (JRException e1) {
			log.error("<<< Error generar Jasper>>> " + e1.getMessage());
			log.error(e1.getMessage(), e1);
		} catch (IOException e) {
			log.error("<<< Error generar Jasper>>> " + e.getMessage());
			log.error(e.getMessage(), e);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				outputStreamResponse.flush();
				outputStreamResponse.close();
				response.flushBuffer();

			} catch (IOException e) {
				log.error("<<< Error generar >>> " + e.getMessage());
				log.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * Permite exportar a PDF
	 * 
	 * @param reportStream
	 * @param parameters
	 * @param ds
	 * @param response
	 * @param strNombre
	 */
	public static void exportarPDF_v2(InputStream reportStream,
			HashMap<String, Object> parameters, JRDataSource ds,
			HttpServletResponse response, String strNombre) {
		OutputStream outputStreamResponse = null;
		response.resetBuffer();
		response.setHeader("Content-Disposition", "inline; filename=\""
				+ strNombre + "." + "pdf" + "\";");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "private");
		response.setDateHeader("Expires", 1);
		response.setContentType("application/pdf");

		JasperPrint jasperPrint = null;
		try {
			outputStreamResponse = response.getOutputStream();
			if (ds == null) {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters);
			} else {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters, ds);
			}

			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					outputStreamResponse);
			exporter.exportReport();

		} catch (JRException e1) {
			log.error("<<< Error generar Jasper>>> " + e1.getMessage());
			log.error(e1.getMessage(), e1);
		} catch (IOException e) {
			log.error("<<< Error generar Jasper>>> " + e.getMessage());
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error("<<< Error generar Jasper>>> " + e.getMessage());
			log.error(e.getMessage(), e);
		} finally {
			try {
				outputStreamResponse.flush();
				outputStreamResponse.close();
				response.flushBuffer();

			} catch (IOException e) {
				log.error("<<< Error generar >>> " + e.getMessage());
				log.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * Permite exportar a HTML
	 * 
	 * @param reportStream
	 * @param parameters
	 * @param ds
	 * @param response
	 * @param strNombre
	 * @throws IOException
	 */
	public static void exportHtml(InputStream reportStream,
			HashMap<String, Object> parameters, JRDataSource ds,
			HttpServletResponse response, String strNombre,
			HttpServletRequest request) throws IOException {
		OutputStream outputStreamResponse = null;
		response.resetBuffer();
		response.setContentType("text/html");
		response.setHeader("Content-Disposition", "inline; filename=\""
				+ strNombre + "." + "html" + "\";");
		response.setHeader("Location", "display.html");
		response.setDateHeader("Expires", 1);

		JasperPrint jasperPrint = null;

		try {
			outputStreamResponse = response.getOutputStream();

			if (ds == null) {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters);
			} else {
				jasperPrint = JasperFillManager.fillReport(reportStream,
						parameters, ds);
			}
			request.getSession().setAttribute(
					ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
					jasperPrint);
			JRExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					outputStreamResponse);
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
					request.getContextPath() + "/image?image=");
			exporter.setParameter(
					JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
					Boolean.FALSE);
			exporter.setParameter(
					JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.FALSE);
			exporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT, "px");
			exporter.setParameter(
					JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);

			if (exporter != null) {
				exporter.exportReport();
			}
		} catch (JRException e1) {
			log.error("<<< Error generar Jasper>>> " + e1.getMessage());
			log.error(e1.getMessage(), e1);
		} catch (IOException e) {
			log.error("<<< Error generar Jasper>>> " + e.getMessage());
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error("<<< Error generar Jasper>>> " + e.getMessage());
			log.error(e.getMessage(), e);
		} finally {
			try {
				response.flushBuffer();
			} catch (IOException e) {
				log.error("<<< Error generar >>> " + e.getMessage());
				log.error(e.getMessage(), e);
			}
		}

	}

}

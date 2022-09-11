/*
*****************************************************************************************
This file is part of Zelot Gestion ERP.

    Zelot Gestion ERP is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zelot Gestion ERP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zelot Gestion ERP.  If not, see <http://www.gnu.org/licenses/>.

*****************************************************************************************
Este archivo forma parte de Zelot Gestión ERP.

     Zelot Gestion ERP es un software libre: usted puede redistribuirlo y / o modificar
     bajo los términos de la GNU Lesser General Public License publicada por
     la Free Software Foundation, bien de la versión 3 de la Licencia, o
     (a su elección) cualquier versión posterior.

     Zelot Gestión ERP se distribuye con la esperanza de que sea útil,
     pero SIN NINGUNA GARANTÍA, incluso sin la garantía implícita de
     COMERCIALIZACIÓN o IDONEIDAD PARA UN PROPÓSITO PARTICULAR. Consulte la
     GNU Lesser General Public License para más detalles.

     Debería haber recibido una copia de la GNU Lesser General Public License
     junto con Zelot Gestión ERP. Si no, vea <http://www.gnu.org/licenses/>.
******************************************************************************************/
package logica;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import persistencia.CobranzaDao;
import dominio.Caja;
import dominio.Cliente;
import dominio.Cobranza;
import dominio.ComprobanteCtaCte;
import dominio.DetallePago;
import dominio.DocumentoImputado;
import dominio.Factura;
import dominio.MovimientoCaja;
import dominio.NroCorrelativo;
import dominio.TipoMovimientoCaja;

public class CobranzaLogica {

	private static CobranzaLogica cobranzaLogica = null;
	private CobranzaDao cobranzaDao = CobranzaDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	DecimalFormat decimalFormat = new DecimalFormat("00000000");
	DecimalFormat decimalFormat2 = new DecimalFormat("##.00");
	private MovimientoCajaLogica movimientoCajaLogica = MovimientoCajaLogica.getInstance();
	private DetallePagoLogica detallePagoLogica = DetallePagoLogica.getInstance();
	private NroCorrelativoLogica nroCorrelativoLogica = NroCorrelativoLogica.getInstance();


	public static CobranzaLogica getInstance(){
		if(cobranzaLogica == null){
			cobranzaLogica = new CobranzaLogica();
		}
		return cobranzaLogica;
	}

	public CobranzaLogica() {

	}

	/*
	 * METODOS
	 */

	/*public boolean addCobranzaCabecera(Factura factura, Vector<DetallePago> detallesPago, Caja caja){
		boolean resultado = true;
		NroCorrelativo nroCorrelativo = new NroCorrelativo("FAC", factura.getLetraFactura(), factura.getNroPtoVenta(), factura.getNroFactura());
		int rowAffected = cobranzaDao.crateFactura(factura, simpleDateFormat.format(new Date()));
		addMovimientoCaja(factura, caja);
		if (rowAffected != 0 ){
			if(detallesPago != null){
			resultado = detallePagoLogica.addDetallesPagos(detallesPago);
			}
			if(!resultado){
				cobranzaDao.deleteCabecera(factura);
				movimientoCajaLogica.deleteMovimientoCaja(movimientoCajaLogica.getMovimientoCaja(movimientoCajaLogica.getUltimoCodigo()).getCodigoMovimiento());
			}else{
				nroCorrelativoLogica.updateNroCorrelativo(nroCorrelativo);
				Iterator<ItemFactura> itItemFac = factura.getItemsFactura().iterator();
				while(itItemFac.hasNext()){
					ItemFactura itemFac = (ItemFactura)itItemFac.next();
					if(!addCobranzaItem(factura, itemFac)){
						resultado = false;
						cobranzaDao.deleteCabecera(factura);
						if(detallesPago != null)detallePagoLogica.deleteDetallesPagos(detallesPago);
						try {
							int corrActual = decimalFormat.parse(nroCorrelativo.getNroCobranza()).intValue();
							int corrAnterior = corrActual - 1;
							nroCorrelativo.setNroCobranza(decimalFormat.format(corrAnterior));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						nroCorrelativoLogica.undoNroCorrelativo(nroCorrelativo);
						break;
					}
				}
			}
		}else{
			resultado = false;
		}
		return resultado;
	}*/

	/*public boolean addCobranzaItem(Factura factura, ItemFactura itemFactura){
		int rowAffected = cobranzaDao.crateItemFactura(factura, itemFactura, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			articuloLogica.updateStockByArticulo(itemFactura.getArticulo(), (itemFactura.getArticulo().getStockDefault() - itemFactura.getCantidad()));
			return true;
		}else{
			return false;
		}
	}*/

	/*public boolean addMovimientoCaja(Factura factura, Caja caja){
		MovimientoCaja movimientoCaja = new MovimientoCaja();
		movimientoCaja.setCaja(caja);
		movimientoCaja.setCodigoSujetoEntidad(factura.getCliente().getCodigo());
		movimientoCaja.setConcepto("FAC "+factura.getLetraFactura() + " NRº "+factura.getNroPtoVenta()+"-"+factura.getNroFactura());
		movimientoCaja.setFechaMovimiento(simpleDateFormat.format(new Date()));
		movimientoCaja.setImporteMovimiento(Float.valueOf(decimalFormat2.format(factura.getTotalFinal().floatValue())));
		TipoMovimientoCaja tmc = new TipoMovimientoCaja();
		tmc.setCodigoTipoMovimiento(5);
		movimientoCaja.setTipoMovimiento(tmc);
		return  movimientoCajaLogica.addMovimientoCaja(movimientoCaja);
	}*/

	/******************************* COMPROBANTES CTA CTE *****************************************/

	public ArrayList<ComprobanteCtaCte> getAllComprobanteCtaCte(){
		return cobranzaDao.selectAllComprobantesCtaCte();
	}

	public ArrayList<ComprobanteCtaCte> getAllComprobanteCtaCteByCliente(Cliente cliente){
		return cobranzaDao.selectAllComprobantesCtaCteByCliente(cliente);
	}

	public ArrayList<ComprobanteCtaCte> getAllComprobanteCtaCteByClientePendientes(Cliente cliente){
		return cobranzaDao.selectAllComprobantesCtaCteByClientePendientes(cliente);
	}

	public ComprobanteCtaCte getComprobanteCtaCte(String tipo, String letra, String nroPtVta, String nroComp, int nroCta){
		return cobranzaDao.select(nroComp, nroPtVta, tipo, letra, nroCta);
	}

	public boolean addComprobanteCtaCte(Factura factura){
		boolean resultado = true;
		if(factura != null){
			if(factura.getCondicionVenta().getDiasVencimiento() != 0){
				int cuotas = factura.getCantCtas()+1;
				int diasVen = factura.getCondicionVenta().getDiasVencimiento();
				int cuotasVta = factura.getCondicionVenta().getCantidadCuotasDefault();
				for(int i=1; i<cuotas; i++){
					int dv = (diasVen/cuotasVta)*i;
					ComprobanteCtaCte comprobanteCtaCte = new ComprobanteCtaCte();
					comprobanteCtaCte.setCantidadCtas(i);
					comprobanteCtaCte.setCliente(factura.getCliente());
					comprobanteCtaCte.setConcepto("FAC "+factura.getLetraFactura() + " NRº "+factura.getNroPtoVenta()+"-"+factura.getNroFactura());
					comprobanteCtaCte.setFechaEmision(factura.getFechaFacturacion());
					comprobanteCtaCte.setFechaVencimiento(getFechaVencimiento(factura.getFechaFacturacion(), dv, cuotasVta));
					comprobanteCtaCte.setNroComprobanteCtaCte(factura.getNroFactura());
					comprobanteCtaCte.setNroPtoVtaComprobanteCtaCte(factura.getNroPtoVenta());
					comprobanteCtaCte.setTipoComprobanteCtaCte("FAC");
					comprobanteCtaCte.setLetraComprobanteCtaCte(factura.getLetraFactura());
					//comprobanteCtaCte.setMontoOriginal(factura.getTotalFinal());
					comprobanteCtaCte.setMontoOriginal(factura.getTotalFinal() / factura.getCantCtas());
					comprobanteCtaCte.setSaldo(factura.getTotalFinal() / factura.getCantCtas());

					int rowAffected = cobranzaDao.crateComprobanteCtaCte(comprobanteCtaCte, simpleDateFormat.format(new Date()));
					if(rowAffected == 0){
						resultado = false;
					}
				}


			}
		}
		return resultado;
	}

	public boolean addComprobanteNCCtaCte(Factura factura){
		boolean resultado = true;
		if(factura != null){
			if(factura.getCondicionVenta().getDiasVencimiento() != 0){
				//int cuotas = factura.getCantCtas()+1;
				//int diasVen = factura.getCondicionVenta().getDiasVencimiento();
				//int cuotasVta = factura.getCondicionVenta().getCantidadCuotasDefault();
				//for(int i=1; i<cuotas; i++){
					//int dv = (diasVen/cuotasVta)*i;
					ComprobanteCtaCte comprobanteCtaCte = new ComprobanteCtaCte();
					comprobanteCtaCte.setCantidadCtas(0);
					comprobanteCtaCte.setCliente(factura.getCliente());
					comprobanteCtaCte.setConcepto(factura.getTipoComprobante()+" "+factura.getLetraFactura() + " NRº "+factura.getNroPtoVenta()+"-"+factura.getNroFactura());
					comprobanteCtaCte.setFechaEmision(factura.getFechaFacturacion());
					comprobanteCtaCte.setFechaVencimiento(factura.getFechaFacturacion());
					comprobanteCtaCte.setNroComprobanteCtaCte(factura.getNroFactura());
					comprobanteCtaCte.setNroPtoVtaComprobanteCtaCte(factura.getNroPtoVenta());
					comprobanteCtaCte.setTipoComprobanteCtaCte(factura.getTipoComprobante());
					comprobanteCtaCte.setLetraComprobanteCtaCte(factura.getLetraFactura());
					//comprobanteCtaCte.setMontoOriginal(factura.getTotalFinal());
					comprobanteCtaCte.setMontoOriginal(factura.getTotalFinal());
					comprobanteCtaCte.setSaldo(factura.getTotalFinal());

					int rowAffected = cobranzaDao.crateComprobanteCtaCte(comprobanteCtaCte, simpleDateFormat.format(new Date()));
					if(rowAffected == 0){
						resultado = false;
					}
				//}


			}
		}
		return resultado;
	}

	public String getFechaVencimiento(String fechaEmision, int dv, int ctas){
		String fechaResultado = null;
		try{
			Calendar calendar = Calendar.getInstance();
			Date fechaE = null;
			Integer diasVen = dv;
			if(!fechaEmision.isEmpty() && diasVen != null){
				fechaE = simpleDateFormat.parse(fechaEmision);
				calendar.setTime(fechaE);
				calendar.add(Calendar.DAY_OF_MONTH, diasVen);
				Date fecheV = calendar.getTime();
				fechaResultado = simpleDateFormat.format(fecheV);
			}
		}catch(ParseException pe){
			pe.printStackTrace();
		}
		return fechaResultado;
	}


	/******************************************* COBRANZAS - RECIBOS **************************************/

	public boolean addCobranza(Cobranza cobranza, Vector<DetallePago> detallesPago, Caja caja){
	boolean resultado = true;
	NroCorrelativo nroCorrelativo = new NroCorrelativo("REC", cobranza.getLetraComprobanteRecibo(), cobranza.getNroPtoVtaComprobanteRecibo(), cobranza.getNroComprobanteRecibo());
	int rowAffected = cobranzaDao.crateCobranza(cobranza, simpleDateFormat.format(new Date()));
	addMovimientoCaja(cobranza, caja);
	if (rowAffected != 0 ){
		if(detallesPago != null){
			resultado = detallePagoLogica.addDetallesPagos(detallesPago);
		}
		if(!resultado){
			cobranzaDao.deleteCobranza(cobranza);
			movimientoCajaLogica.deleteMovimientoCaja(movimientoCajaLogica.getMovimientoCaja(movimientoCajaLogica.getUltimoCodigo()).getCodigoMovimiento());
		}else{
			nroCorrelativoLogica.updateNroCorrelativo(nroCorrelativo);
			Iterator<DocumentoImputado> itDocImp = cobranza.getDocumentosImputados().iterator();
			while(itDocImp.hasNext()){
				DocumentoImputado documentoImputado = (DocumentoImputado)itDocImp.next();
				boolean b = addDocumentoImputado(cobranza, documentoImputado);
				if(!b){
					resultado = false;
					cobranzaDao.deleteCobranza(cobranza);
					if(detallesPago != null)detallePagoLogica.deleteDetallesPagos(detallesPago);
					try {
						int corrActual = decimalFormat.parse(nroCorrelativo.getNroComprobante()).intValue();
						int corrAnterior = corrActual - 1;
						nroCorrelativo.setNroComprobante(decimalFormat.format(corrAnterior));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					nroCorrelativoLogica.undoNroCorrelativo(nroCorrelativo);
					break;
				}
			}
		}
	}else{
		resultado = false;
	}
	return resultado;
	}


	public boolean addDocumentoImputado(Cobranza cobranza, DocumentoImputado documentoImputado){
		int rowAffected = cobranzaDao.crateDocumentoImputado(cobranza, documentoImputado, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			if(updateSaldoComprobanteCtaCte(documentoImputado, simpleDateFormat.format(new Date()))){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean updateSaldoComprobanteCtaCte(DocumentoImputado documentoImputado, String fechaHoraUpdate){
		int rowAffected = cobranzaDao.updateSaldoComprobanteCtaCte(documentoImputado, fechaHoraUpdate);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updateAnulado(Factura factura, String fechaHoraUpdate){
		int rowAffected = cobranzaDao.updateComprobanteAnulado(factura, fechaHoraUpdate);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean addMovimientoCaja(Cobranza cobranza, Caja caja){
		MovimientoCaja movimientoCaja = new MovimientoCaja();
		movimientoCaja.setCaja(caja);
		movimientoCaja.setCodigoSujetoEntidad(cobranza.getCliente().getCodigo());
		movimientoCaja.setConcepto("REC "+cobranza.getLetraComprobanteRecibo() + " NRº "+cobranza.getNroPtoVtaComprobanteRecibo()+"-"+cobranza.getNroComprobanteRecibo());
		movimientoCaja.setFechaMovimiento(simpleDateFormat.format(new Date()));
		movimientoCaja.setImporteMovimiento(Float.valueOf(decimalFormat2.format(cobranza.getMontoTotalImputado().floatValue())));
		TipoMovimientoCaja tmc = new TipoMovimientoCaja();
		tmc.setCodigoTipoMovimiento(4);
		movimientoCaja.setTipoMovimiento(tmc);
		return  movimientoCajaLogica.addMovimientoCaja(movimientoCaja);
	}

	public ArrayList<Cobranza> getAllCobranzasByCliente(Cliente cliente){
		return cobranzaDao.selectAllCobranzasByCliente(cliente);
	}

	public ArrayList<DocumentoImputado> getAllDocumentosImputadosByCobranza(Cobranza cobranza){
		return cobranzaDao.selectAllDocumentosImputadosByCobranza(cobranza);
	}

}

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
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import persistencia.ComprobanteDao;
import dominio.Caja;
import dominio.Cliente;
import dominio.DetallePago;
import dominio.Factura;
import dominio.ItemFactura;
import dominio.MovimientoCaja;
import dominio.NroCorrelativo;
import dominio.TipoMovimientoCaja;

public class ComprobanteLogica {

	private static ComprobanteLogica comprobanteLogica = null;
	private ComprobanteDao comprobanteDao = ComprobanteDao.getInstance();
	private NroCorrelativoLogica nroCorrelativoLogica = NroCorrelativoLogica.getInstance();
	private DetallePagoLogica detallePagoLogica = DetallePagoLogica.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
	DecimalFormat decimalFormat = new DecimalFormat("00000000");
	DecimalFormat decimalFormat2 = new DecimalFormat("##.00");
	private MovimientoCajaLogica movimientoCajaLogica = MovimientoCajaLogica.getInstance();
	private ArticuloLogica articuloLogica = ArticuloLogica.getInstance();
	private CobranzaLogica cobranzaLogica = CobranzaLogica.getInstance();

	public static ComprobanteLogica getInstance(){
		if(comprobanteLogica == null){
			comprobanteLogica = new ComprobanteLogica();
		}
		return comprobanteLogica;
	}

	public ComprobanteLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addComprobanteCabecera(Factura factura, Vector<DetallePago> detallesPago, Caja caja){
		boolean resultado = true;
		NroCorrelativo nroCorrelativo = new NroCorrelativo("FAC", factura.getLetraFactura(), factura.getNroPtoVenta(), factura.getNroFactura());
		int rowAffected = comprobanteDao.crateFactura(factura, simpleDateFormat.format(new Date()));
		addMovimientoCaja(factura, caja);
		cobranzaLogica.addComprobanteCtaCte(factura);
		if (rowAffected != 0 ){
			if(detallesPago != null){
			resultado = detallePagoLogica.addDetallesPagos(detallesPago);
			}
			if(!resultado){
				comprobanteDao.deleteCabecera(factura);
				movimientoCajaLogica.deleteMovimientoCaja(movimientoCajaLogica.getMovimientoCaja(movimientoCajaLogica.getUltimoCodigo()).getCodigoMovimiento());
			}else{
				nroCorrelativoLogica.updateNroCorrelativo(nroCorrelativo);
				Iterator<ItemFactura> itItemFac = factura.getItemsFactura().iterator();
				while(itItemFac.hasNext()){
					ItemFactura itemFac = (ItemFactura)itItemFac.next();
					if(!addComprobanteItem(factura, itemFac)){
						resultado = false;
						comprobanteDao.deleteCabecera(factura);
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

	public boolean addComprobanteItem(Factura factura, ItemFactura itemFactura){
		int rowAffected = comprobanteDao.crateItemFactura(factura, itemFactura, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			articuloLogica.updateStockByArticulo(itemFactura.getArticulo(), (itemFactura.getArticulo().getStockDefault() - itemFactura.getCantidad()));
			return true;
		}else{
			return false;
		}
	}

	public boolean updateAnulado(Factura factura, String fechaHoraUpdate){
		int rowAffected = comprobanteDao.updateFacturaAnulado(factura, fechaHoraUpdate);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean addComprobanteNCCabecera(Factura factura, Caja caja){
		boolean resultado = true;
		NroCorrelativo nroCorrelativo = new NroCorrelativo("NC", factura.getLetraFactura(), factura.getNroPtoVenta(), factura.getNroFactura());
		if(factura.getTotalBonifClient() != 0)factura.setTotalBonifClient(-factura.getTotalBonifClient());
		if(factura.getTotalBonifGlobal() != 0)factura.setTotalBonifGlobal(-factura.getTotalBonifGlobal());
		if(factura.getTotalFinal() != 0)factura.setTotalFinal(-factura.getTotalFinal());
		if(factura.getSubTotal() != 0)factura.setSubTotal(-factura.getSubTotal());
		if(factura.getTotalIva105() != 0)factura.setTotalIva105(-factura.getTotalIva105());
		if(factura.getTotalIva21() != 0)factura.setTotalIva21(-factura.getTotalIva21());
		if(factura.getTotalIva27() != 0)factura.setTotalIva27(-factura.getTotalIva27());
		if(factura.getTotalRecarClient() != 0)factura.setTotalRecarClient(-factura.getTotalRecarClient());
		if(factura.getTotalRecarGlobal() != 0)factura.setTotalRecarGlobal(-factura.getTotalRecarGlobal());
		int rowAffected = comprobanteDao.crateFactura(factura, simpleDateFormat.format(new Date()));
		addMovimientoCaja(factura, caja);
		updateAnulado(factura, simpleDateFormat.format(new Date()));
		cobranzaLogica.addComprobanteNCCtaCte(factura);
		cobranzaLogica.updateAnulado(factura, simpleDateFormat.format(new Date()));
		if (rowAffected != 0 ){
			/*if(detallesPago != null){
			resultado = detallePagoLogica.addDetallesPagos(detallesPago);
			}*/
			if(!resultado){
				comprobanteDao.deleteCabecera(factura);
				factura.setAnulado(false);
				updateAnulado(factura, simpleDateFormat.format(new Date()));
				cobranzaLogica.updateAnulado(factura, simpleDateFormat.format(new Date()));
				movimientoCajaLogica.deleteMovimientoCaja(movimientoCajaLogica.getMovimientoCaja(movimientoCajaLogica.getUltimoCodigo()).getCodigoMovimiento());
			}else{
				nroCorrelativoLogica.updateNroCorrelativo(nroCorrelativo);
				Iterator<ItemFactura> itItemFac = factura.getItemsFactura().iterator();
				while(itItemFac.hasNext()){
					ItemFactura itemFac = (ItemFactura)itItemFac.next();

					if(itemFac.getPrecioTotal() != 0)itemFac.setPrecioTotal(-itemFac.getPrecioTotal());
					if(itemFac.getPrecioUnitario() != 0)itemFac.setPrecioUnitario(-itemFac.getPrecioUnitario());
					if(itemFac.getValorIvaCalculado() != 0)itemFac.setValorIvaCalculado(-itemFac.getValorIvaCalculado());

					if(!addComprobanteNCItem(factura, itemFac)){
						resultado = false;
						comprobanteDao.deleteCabecera(factura);
						//if(detallesPago != null)detallePagoLogica.deleteDetallesPagos(detallesPago);
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

	public boolean addComprobanteNCItem(Factura factura, ItemFactura itemFactura){
		int rowAffected = comprobanteDao.crateItemFactura(factura, itemFactura, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			if(itemFactura.getArticulo().getCodigo() != 000000000){
				articuloLogica.updateStockByArticulo(itemFactura.getArticulo(), (itemFactura.getArticulo().getStockDefault() + itemFactura.getCantidad()));
			}
			return true;
		}else{
			return false;
		}
	}

	public boolean addMovimientoCaja(Factura factura, Caja caja){
		MovimientoCaja movimientoCaja = new MovimientoCaja();
		movimientoCaja.setCaja(caja);
		movimientoCaja.setCodigoSujetoEntidad(factura.getCliente().getCodigo());
		movimientoCaja.setConcepto(factura.getTipoComprobante()+" "+factura.getLetraFactura() + " NRº "+factura.getNroPtoVenta()+"-"+factura.getNroFactura());
		movimientoCaja.setFechaMovimiento(simpleDateFormat.format(new Date()));
		movimientoCaja.setImporteMovimiento(Float.valueOf(decimalFormat2.format(factura.getTotalFinal().floatValue())));
		TipoMovimientoCaja tmc = new TipoMovimientoCaja();
		if(factura.getTipoComprobante().equals("FAC")){
			if(factura.getCondicionVenta().getDiasVencimiento() == 0){
				tmc.setCodigoTipoMovimiento(7);
			}else{
				tmc.setCodigoTipoMovimiento(5);
			}
		}else if(factura.getTipoComprobante().equals("NC")){
			if(factura.getCondicionVenta().getDiasVencimiento() == 0){
				tmc.setCodigoTipoMovimiento(8);
			}else{
				tmc.setCodigoTipoMovimiento(9);
			}
		}
		movimientoCaja.setTipoMovimiento(tmc);
		return  movimientoCajaLogica.addMovimientoCaja(movimientoCaja);
	}

	public ArrayList<ItemFactura> getListAllItemsFacturaByFactura(Factura factura){
		return comprobanteDao.selectAllItemFacturaByFactura(factura);
	}

	public Factura getFactura(String tipoComp, String letraComp, String nroPtoVta, String nroComp){
		return comprobanteDao.selectFactura(tipoComp, letraComp, nroPtoVta, nroComp);
	}

	public ArrayList<Factura> getListFacturasByClienteAndFecha(Cliente cliente, String fechaDesde, String fechaHasta){
		return comprobanteDao.selectAllFacturaByClienteAndFecha(cliente, fechaDesde, fechaHasta);
	}

	public ArrayList<Factura> getListFacturasByFecha(String fechaDesde, String fechaHasta){
		return comprobanteDao.selectAllFacturaByFecha(fechaDesde, fechaHasta);
	}

}

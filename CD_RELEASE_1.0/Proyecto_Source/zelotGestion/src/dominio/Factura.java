/*<Copyright© 2011 Luciano Delfino>
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
package dominio;

import java.util.Vector;

public class Factura {

	private String nroFactura;
	private String nroPtoVenta;
	private String letraFactura;
	private String tipoComprobante;
	private Vector<ItemFactura> itemsFactura;
	private Cliente cliente;
	private ListaPrecio listaPrecio;
	private Float descGlobal;
	private Float recGlobal;
	private Deposito deposito;
	private String fechaFacturacion;
	private CondicionVenta condicionVenta;
	private Float subTotal;
	private Float totalBonifClient;
	private Float totalRecarClient;
	private Float totalBonifGlobal;
	private Float totalRecarGlobal;
	private Float totalIva21;
	private Float totalIva27;
	private Float totalIva105;
	private Float totalFinal;
	private int cantCtas;
	private String nroComprobanteRelac;
	private String tipoComprobanteRelac;
	private String letraComprobanteRelac;
	private String nroPtoVtaComprobanteRelac;
	private boolean anulado;





	public boolean isAnulado() {
		return anulado;
	}


	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}


	public String getNroComprobanteRelac() {
		return nroComprobanteRelac;
	}


	public void setNroComprobanteRelac(String nroComprobanteRelac) {
		this.nroComprobanteRelac = nroComprobanteRelac;
	}


	public String getTipoComprobanteRelac() {
		return tipoComprobanteRelac;
	}


	public void setTipoComprobanteRelac(String tipoComprobanteRelac) {
		this.tipoComprobanteRelac = tipoComprobanteRelac;
	}


	public String getLetraComprobanteRelac() {
		return letraComprobanteRelac;
	}


	public void setLetraComprobanteRelac(String letraComprobanteRelac) {
		this.letraComprobanteRelac = letraComprobanteRelac;
	}


	public String getNroPtoVtaComprobanteRelac() {
		return nroPtoVtaComprobanteRelac;
	}


	public void setNroPtoVtaComprobanteRelac(String nroPtoVtaComprobanteRelac) {
		this.nroPtoVtaComprobanteRelac = nroPtoVtaComprobanteRelac;
	}





	public String getTipoComprobante() {
		return tipoComprobante;
	}


	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}


	public String getNroPtoVenta() {
		return nroPtoVenta;
	}


	public void setNroPtoVenta(String nroPtoVenta) {
		this.nroPtoVenta = nroPtoVenta;
	}


	public int getCantCtas() {
		return cantCtas;
	}


	public void setCantCtas(int cantCtas) {
		this.cantCtas = cantCtas;
	}


	public Float getSubTotal() {
		return subTotal;
	}


	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}


	public Float getTotalBonifClient() {
		return totalBonifClient;
	}


	public void setTotalBonifClient(Float totalBonifClient) {
		this.totalBonifClient = totalBonifClient;
	}


	public Float getTotalRecarClient() {
		return totalRecarClient;
	}


	public void setTotalRecarClient(Float totalRecarClient) {
		this.totalRecarClient = totalRecarClient;
	}


	public Float getTotalBonifGlobal() {
		return totalBonifGlobal;
	}


	public void setTotalBonifGlobal(Float totalBonifGlobal) {
		this.totalBonifGlobal = totalBonifGlobal;
	}


	public Float getTotalRecarGlobal() {
		return totalRecarGlobal;
	}


	public void setTotalRecarGlobal(Float totalRecarGlobal) {
		this.totalRecarGlobal = totalRecarGlobal;
	}


	public Float getTotalIva21() {
		return totalIva21;
	}


	public void setTotalIva21(Float totalIva21) {
		this.totalIva21 = totalIva21;
	}


	public Float getTotalIva27() {
		return totalIva27;
	}


	public void setTotalIva27(Float totalIva27) {
		this.totalIva27 = totalIva27;
	}


	public Float getTotalIva105() {
		return totalIva105;
	}


	public void setTotalIva105(Float totalIva105) {
		this.totalIva105 = totalIva105;
	}


	public Float getTotalFinal() {
		return totalFinal;
	}


	public void setTotalFinal(Float totalFinal) {
		this.totalFinal = totalFinal;
	}


	public String getNroFactura() {
		return nroFactura;
	}


	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}


	public String getLetraFactura() {
		return letraFactura;
	}


	public void setLetraFactura(String letraFactura) {
		this.letraFactura = letraFactura;
	}


	public Vector<ItemFactura> getItemsFactura() {
		return itemsFactura;
	}


	public void setItemsFactura(Vector<ItemFactura> itemsFactura) {
		this.itemsFactura = itemsFactura;
	}


	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ListaPrecio getListaPrecio() {
		return listaPrecio;
	}
	public void setListaPrecio(ListaPrecio listaPrecio) {
		this.listaPrecio = listaPrecio;
	}
	public Float getDescGlobal() {
		return descGlobal;
	}
	public void setDescGlobal(Float descGlobal) {
		this.descGlobal = descGlobal;
	}
	public Float getRecGlobal() {
		return recGlobal;
	}
	public void setRecGlobal(Float recGlobal) {
		this.recGlobal = recGlobal;
	}
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	public CondicionVenta getCondicionVenta() {
		return condicionVenta;
	}
	public void setCondicionVenta(CondicionVenta condicionVenta) {
		this.condicionVenta = condicionVenta;
	}


	public Factura(String nroFactura, String nroPtoVenta, String letraFactura,
			String tipoComprobante, Vector<ItemFactura> itemsFactura,
			Cliente cliente, ListaPrecio listaPrecio, Float descGlobal,
			Float recGlobal, Deposito deposito, String fechaFacturacion,
			CondicionVenta condicionVenta, Float subTotal,
			Float totalBonifClient, Float totalRecarClient,
			Float totalBonifGlobal, Float totalRecarGlobal, Float totalIva21,
			Float totalIva27, Float totalIva105, Float totalFinal,
			int cantCtas, String nroComprobanteRelac,
			String tipoComprobanteRelac, String letraComprobanteRelac,
			String nroPtoVtaComprobanteRelac, boolean anulado) {
		super();
		this.nroFactura = nroFactura;
		this.nroPtoVenta = nroPtoVenta;
		this.letraFactura = letraFactura;
		this.tipoComprobante = tipoComprobante;
		this.itemsFactura = itemsFactura;
		this.cliente = cliente;
		this.listaPrecio = listaPrecio;
		this.descGlobal = descGlobal;
		this.recGlobal = recGlobal;
		this.deposito = deposito;
		this.fechaFacturacion = fechaFacturacion;
		this.condicionVenta = condicionVenta;
		this.subTotal = subTotal;
		this.totalBonifClient = totalBonifClient;
		this.totalRecarClient = totalRecarClient;
		this.totalBonifGlobal = totalBonifGlobal;
		this.totalRecarGlobal = totalRecarGlobal;
		this.totalIva21 = totalIva21;
		this.totalIva27 = totalIva27;
		this.totalIva105 = totalIva105;
		this.totalFinal = totalFinal;
		this.cantCtas = cantCtas;
		this.nroComprobanteRelac = nroComprobanteRelac;
		this.tipoComprobanteRelac = tipoComprobanteRelac;
		this.letraComprobanteRelac = letraComprobanteRelac;
		this.nroPtoVtaComprobanteRelac = nroPtoVtaComprobanteRelac;
		this.anulado = anulado;
	}


	public Factura() {

	}


}

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

import java.util.ArrayList;

public class Cobranza {

	private String nroComprobanteRecibo;
	private String nroPtoVtaComprobanteRecibo;
	private String tipoComprobanteRecibo;
	private String letraComprobanteRecibo;
	private Cliente cliente;
	private Vendedor vendedor;
	private ArrayList<DocumentoImputado> documentosImputados;
	private String fechaCobranza;
	private Float montoTotalImputado;

	public String getNroComprobanteRecibo() {
		return nroComprobanteRecibo;
	}
	public void setNroComprobanteRecibo(String nroComprobanteRecibo) {
		this.nroComprobanteRecibo = nroComprobanteRecibo;
	}
	public String getNroPtoVtaComprobanteRecibo() {
		return nroPtoVtaComprobanteRecibo;
	}
	public void setNroPtoVtaComprobanteRecibo(String nroPtoVtaComprobanteRecibo) {
		this.nroPtoVtaComprobanteRecibo = nroPtoVtaComprobanteRecibo;
	}
	public String getTipoComprobanteRecibo() {
		return tipoComprobanteRecibo;
	}
	public void setTipoComprobanteRecibo(String tipoComprobanteRecibo) {
		this.tipoComprobanteRecibo = tipoComprobanteRecibo;
	}
	public String getLetraComprobanteRecibo() {
		return letraComprobanteRecibo;
	}
	public void setLetraComprobanteRecibo(String letraComprobanteRecibo) {
		this.letraComprobanteRecibo = letraComprobanteRecibo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public ArrayList<DocumentoImputado> getDocumentosImputados() {
		return documentosImputados;
	}
	public void setDocumentosImputados(
			ArrayList<DocumentoImputado> documentosImputados) {
		this.documentosImputados = documentosImputados;
	}
	public String getFechaCobranza() {
		return fechaCobranza;
	}
	public void setFechaCobranza(String fechaCobranza) {
		this.fechaCobranza = fechaCobranza;
	}
	public Float getMontoTotalImputado() {
		return montoTotalImputado;
	}
	public void setMontoTotalImputado(Float montoTotalImputado) {
		this.montoTotalImputado = montoTotalImputado;
	}

	public Cobranza(String nroComprobanteRecibo,
			String nroPtoVtaComprobanteRecibo, String tipoComprobanteRecibo,
			String letraComprobanteRecibo, Cliente cliente, Vendedor vendedor,
			ArrayList<DocumentoImputado> documentosImputados,
			String fechaCobranza, Float montoTotalImputado) {
		super();
		this.nroComprobanteRecibo = nroComprobanteRecibo;
		this.nroPtoVtaComprobanteRecibo = nroPtoVtaComprobanteRecibo;
		this.tipoComprobanteRecibo = tipoComprobanteRecibo;
		this.letraComprobanteRecibo = letraComprobanteRecibo;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.documentosImputados = documentosImputados;
		this.fechaCobranza = fechaCobranza;
		this.montoTotalImputado = montoTotalImputado;
	}

	public Cobranza() {

	}


}

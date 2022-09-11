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


public class DetallePago {

	private Factura factura;
	private Cobranza cobranza;
	private MedioPago medioPago;
	private Banco banco;
	private Tarjeta tarjeta;
	private String sucursalBanco;
	private String nroCuenta;
	private String nroComprobante;
	private String nroSerie;
	private String titularNombre;
	private String titularNroDoc;
	private String fechaVencimiento;
	private String fechaEmicion;
	private String codLocalidad;
	private Float cotizacion;
	private String nroTarjeta;
	private int indice;
	private Float importe;




	public Cobranza getCobranza() {
		return cobranza;
	}
	public void setCobranza(Cobranza cobranza) {
		this.cobranza = cobranza;
	}
	public Float getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	public String getSucursalBanco() {
		return sucursalBanco;
	}
	public void setSucursalBanco(String sucursalBanco) {
		this.sucursalBanco = sucursalBanco;
	}
	public String getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	public String getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public String getNroSerie() {
		return nroSerie;
	}
	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}
	public String getTitularNombre() {
		return titularNombre;
	}
	public void setTitularNombre(String titularNombre) {
		this.titularNombre = titularNombre;
	}
	public String getTitularNroDoc() {
		return titularNroDoc;
	}
	public void setTitularNroDoc(String titularNroDoc) {
		this.titularNroDoc = titularNroDoc;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public String getFechaEmicion() {
		return fechaEmicion;
	}
	public void setFechaEmicion(String fechaEmicion) {
		this.fechaEmicion = fechaEmicion;
	}
	public String getCodLocalidad() {
		return codLocalidad;
	}
	public void setCodLocalidad(String codLocalidad) {
		this.codLocalidad = codLocalidad;
	}

	public Float getCotizacion() {
		return cotizacion;
	}
	public void setCotizacion(Float cotizacion) {
		this.cotizacion = cotizacion;
	}
	public String getNroTarjeta() {
		return nroTarjeta;
	}
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	public DetallePago(Factura factura, Cobranza cobranza, MedioPago medioPago,
			Banco banco, Tarjeta tarjeta, String sucursalBanco,
			String nroCuenta, String nroComprobante, String nroSerie,
			String titularNombre, String titularNroDoc,
			String fechaVencimiento, String fechaEmicion, String codLocalidad,
			Float cotizacion, String nroTarjeta, int indice, Float importe) {
		super();
		this.factura = factura;
		this.cobranza = cobranza;
		this.medioPago = medioPago;
		this.banco = banco;
		this.tarjeta = tarjeta;
		this.sucursalBanco = sucursalBanco;
		this.nroCuenta = nroCuenta;
		this.nroComprobante = nroComprobante;
		this.nroSerie = nroSerie;
		this.titularNombre = titularNombre;
		this.titularNroDoc = titularNroDoc;
		this.fechaVencimiento = fechaVencimiento;
		this.fechaEmicion = fechaEmicion;
		this.codLocalidad = codLocalidad;
		this.cotizacion = cotizacion;
		this.nroTarjeta = nroTarjeta;
		this.indice = indice;
		this.importe = importe;
	}
	public DetallePago() {

	}



}

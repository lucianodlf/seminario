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

public class MedioPago {

	private int codigo;
	private String descripcion;
	private boolean requiereBanco;
	private boolean requiereBancoSucursal;
	private boolean requiereNroCta;
	private boolean requiereNroComprobante;
	private boolean requiereNroSerie;
	private boolean requiereDatosTitular;
	private boolean requiereFechaVencimiento;
	private boolean requiereFechaEmicion;
	private boolean requiereCodLocalidad;
	private boolean requiereCotizacion;
	private boolean requiereTarjeta;
	private boolean requiereTarjetaNro;
	private boolean isDefault;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isRequiereBanco() {
		return requiereBanco;
	}
	public void setRequiereBanco(boolean requiereBanco) {
		this.requiereBanco = requiereBanco;
	}
	public boolean isRequiereBancoSucursal() {
		return requiereBancoSucursal;
	}
	public void setRequiereBancoSucursal(boolean requiereBancoSucursal) {
		this.requiereBancoSucursal = requiereBancoSucursal;
	}
	public boolean isRequiereNroCta() {
		return requiereNroCta;
	}
	public void setRequiereNroCta(boolean requiereNroCta) {
		this.requiereNroCta = requiereNroCta;
	}
	public boolean isRequiereNroComprobante() {
		return requiereNroComprobante;
	}
	public void setRequiereNroComprobante(boolean requiereNroComprobante) {
		this.requiereNroComprobante = requiereNroComprobante;
	}
	public boolean isRequiereNroSerie() {
		return requiereNroSerie;
	}
	public void setRequiereNroSerie(boolean requiereNroSerie) {
		this.requiereNroSerie = requiereNroSerie;
	}
	public boolean isRequiereDatosTitular() {
		return requiereDatosTitular;
	}
	public void setRequiereDatosTitular(boolean requiereDatosTitular) {
		this.requiereDatosTitular = requiereDatosTitular;
	}
	public boolean isRequiereFechaVencimiento() {
		return requiereFechaVencimiento;
	}
	public void setRequiereFechaVencimiento(boolean requiereFechaVencimiento) {
		this.requiereFechaVencimiento = requiereFechaVencimiento;
	}
	public boolean isRequiereFechaEmicion() {
		return requiereFechaEmicion;
	}
	public void setRequiereFechaEmicion(boolean requiereFechaEmicion) {
		this.requiereFechaEmicion = requiereFechaEmicion;
	}
	public boolean isRequiereCodLocalidad() {
		return requiereCodLocalidad;
	}
	public void setRequiereCodLocalidad(boolean requiereCodLocalidad) {
		this.requiereCodLocalidad = requiereCodLocalidad;
	}
	public boolean isRequiereCotizacion() {
		return requiereCotizacion;
	}
	public void setRequiereCotizacion(boolean requiereCotizacion) {
		this.requiereCotizacion = requiereCotizacion;
	}
	public boolean isRequiereTarjeta() {
		return requiereTarjeta;
	}
	public void setRequiereTarjeta(boolean requiereTarjeta) {
		this.requiereTarjeta = requiereTarjeta;
	}
	public boolean isRequiereTarjetaNro() {
		return requiereTarjetaNro;
	}
	public void setRequiereTarjetaNro(boolean requiereTarjetaNro) {
		this.requiereTarjetaNro = requiereTarjetaNro;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public MedioPago(int codigo, String descripcion, boolean requiereBanco,
			boolean requiereBancoSucursal, boolean requiereNroCta,
			boolean requiereNroComprobante, boolean requiereNroSerie,
			boolean requiereDatosTitular, boolean requiereFechaVencimiento,
			boolean requiereFechaEmicion, boolean requiereCodLocalidad,
			boolean requiereCotizacion, boolean requiereTarjeta,
			boolean requiereTarjetaNro, boolean isDefault) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.requiereBanco = requiereBanco;
		this.requiereBancoSucursal = requiereBancoSucursal;
		this.requiereNroCta = requiereNroCta;
		this.requiereNroComprobante = requiereNroComprobante;
		this.requiereNroSerie = requiereNroSerie;
		this.requiereDatosTitular = requiereDatosTitular;
		this.requiereFechaVencimiento = requiereFechaVencimiento;
		this.requiereFechaEmicion = requiereFechaEmicion;
		this.requiereCodLocalidad = requiereCodLocalidad;
		this.requiereCotizacion = requiereCotizacion;
		this.requiereTarjeta = requiereTarjeta;
		this.requiereTarjetaNro = requiereTarjetaNro;
		this.isDefault = isDefault;
	}
	public MedioPago() {

	}
	@Override
	public String toString() {
		return this.descripcion;
	}





}

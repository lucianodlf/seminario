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

public class ItemFactura {

	private int indice;
	private Articulo articulo;
	private Float descManual;
	private Float recManual;
	private Float iva;
	private Float precioUnitario;
	private Float precioTotal;
	private Float cantidad;
	private Float valorIvaCalculado;



	public Float getValorIvaCalculado() {
		return valorIvaCalculado;
	}
	public void setValorIvaCalculado(Float valorIvaCalculado) {
		this.valorIvaCalculado = valorIvaCalculado;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	public Float getDescManual() {
		return descManual;
	}
	public void setDescManual(Float descManual) {
		this.descManual = descManual;
	}
	public Float getRecManual() {
		return recManual;
	}
	public void setRecManual(Float recManual) {
		this.recManual = recManual;
	}
	public Float getIva() {
		return iva;
	}
	public void setIva(Float iva) {
		this.iva = iva;
	}
	public Float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(Float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public Float getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(Float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public ItemFactura(int indice, Articulo articulo, Float descManual,
			Float recManual, Float iva, Float precioUnitario,
			Float precioTotal, Float cantidad, Float valorIvaCalculado) {
		super();
		this.indice = indice;
		this.articulo = articulo;
		this.descManual = descManual;
		this.recManual = recManual;
		this.iva = iva;
		this.precioUnitario = precioUnitario;
		this.precioTotal = precioTotal;
		this.cantidad = cantidad;
		this.valorIvaCalculado = valorIvaCalculado;
	}
	public ItemFactura() {

	}


}

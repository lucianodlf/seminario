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
package reports;

public class ReportArticulo {

	private String codigo;
	private String descripcion;
	private String unidadVta;
	private String descuento;
	private String precio;



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getUnidadVta() {
		return unidadVta;
	}



	public void setUnidadVta(String unidadVta) {
		this.unidadVta = unidadVta;
	}



	public String getDescuento() {
		return descuento;
	}



	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}



	public String getPrecio() {
		return precio;
	}



	public void setPrecio(String precio) {
		this.precio = precio;
	}



	public ReportArticulo(String codigo, String descripcion,
			String unidadVta, String descuento, String precio) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.unidadVta = unidadVta;
		this.descuento = descuento;
		this.precio = precio;
	}



	public ReportArticulo() {

	}


}

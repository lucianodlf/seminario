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

public class TipoMovimientoCaja {

	private int codigoTipoMovimiento;
	private String descripcion;
	private boolean suma;
	private boolean resta;
	public int getCodigoTipoMovimiento() {
		return codigoTipoMovimiento;
	}
	public void setCodigoTipoMovimiento(int codigoTipoMovimiento) {
		this.codigoTipoMovimiento = codigoTipoMovimiento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isResta() {
		return resta;
	}
	public void setResta(boolean resta) {
		this.resta = resta;
	}
	public boolean isSuma() {
		return suma;
	}
	public void setSuma(boolean suma) {
		this.suma = suma;
	}
	public TipoMovimientoCaja(int codigoTipoMovimiento, String descripcion,
			boolean suma, boolean resta) {
		super();
		this.codigoTipoMovimiento = codigoTipoMovimiento;
		this.descripcion = descripcion;
		this.suma = suma;
		this.resta = resta;
	}
	public TipoMovimientoCaja() {

	}
	@Override
	public String toString() {
		return this.descripcion;
	}


}

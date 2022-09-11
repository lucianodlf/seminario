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

public class MovimientoCaja {

	private Caja caja;
	private int codigoMovimiento;
	private TipoMovimientoCaja tipoMovimiento;
	private String fechaMovimiento;
	private Integer codigoSujetoEntidad;
	private String concepto;
	private Float importeMovimiento;

	public Caja getCaja() {
		return caja;
	}
	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public int getCodigoMovimiento() {
		return codigoMovimiento;
	}
	public void setCodigoMovimiento(int codigoMovimiento) {
		this.codigoMovimiento = codigoMovimiento;
	}
	public TipoMovimientoCaja getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(TipoMovimientoCaja tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(String fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public Integer getCodigoSujetoEntidad() {
		return codigoSujetoEntidad;
	}
	public void setCodigoSujetoEntidad(Integer codigoSujetoEntidad) {
		this.codigoSujetoEntidad = codigoSujetoEntidad;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Float getImporteMovimiento() {
		return importeMovimiento;
	}
	public void setImporteMovimiento(Float importeMovimiento) {
		this.importeMovimiento = importeMovimiento;
	}

	public MovimientoCaja(Caja caja, int ciodigoMovimiento,
			TipoMovimientoCaja tipoMovimiento, String fechaMovimiento,
			int codigoSujetoEntidad, String concepto, Float importeMovimiento) {
		super();
		this.caja = caja;
		this.codigoMovimiento = ciodigoMovimiento;
		this.tipoMovimiento = tipoMovimiento;
		this.fechaMovimiento = fechaMovimiento;
		this.codigoSujetoEntidad = codigoSujetoEntidad;
		this.concepto = concepto;
		this.importeMovimiento = importeMovimiento;
	}

	public MovimientoCaja() {

	}





}

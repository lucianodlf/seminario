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

public class Caja {

	private int codigo;
	private String fechaInicio;
	private String fechaCierre;
	private Float montoInicio;
	private Float montoCierre;
	private ArrayList<MovimientoCaja> movimientosCasa;
	private boolean cerrada;



	public boolean isCerrada() {
		return cerrada;
	}
	public void setCerrada(boolean cerrada) {
		this.cerrada = cerrada;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public Float getMontoInicio() {
		return montoInicio;
	}
	public void setMontoInicio(Float montoInicio) {
		this.montoInicio = montoInicio;
	}
	public Float getMontoCierre() {
		return montoCierre;
	}
	public void setMontoCierre(Float montoCierre) {
		this.montoCierre = montoCierre;
	}
	public ArrayList<MovimientoCaja> getMovimientosCasa() {
		return movimientosCasa;
	}
	public void setMovimientosCasa(ArrayList<MovimientoCaja> movimientosCasa) {
		this.movimientosCasa = movimientosCasa;
	}


	public Caja(int codigo, String fechaInicio, String fechaCierre,
			Float montoInicio, Float montoCierre,
			ArrayList<MovimientoCaja> movimientosCasa, boolean cerrada) {
		super();
		this.codigo = codigo;
		this.fechaInicio = fechaInicio;
		this.fechaCierre = fechaCierre;
		this.montoInicio = montoInicio;
		this.montoCierre = montoCierre;
		this.movimientosCasa = movimientosCasa;
		this.cerrada = cerrada;
	}
	public Caja() {

	}





}

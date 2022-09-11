
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
******************************************************************************************/package dominio;

public class Impuesto {

	private int codigo;
	private String descripcion;
	private Float porcentaje;
	private Float montoFijo;
	private String grupoImpuesto;
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
	public Float getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Float porcentaje) {
		this.porcentaje = porcentaje;
	}
	public Float getMontoFijo() {
		return montoFijo;
	}
	public void setMontoFijo(Float montoFijo) {
		this.montoFijo = montoFijo;
	}

	public String getGrupoImpuesto() {
		return grupoImpuesto;
	}
	public void setGrupoImpuesto(String grupoImpuesto) {
		this.grupoImpuesto = grupoImpuesto;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}


	public Impuesto(int codigo, String descripcion, Float porcentaje,
			Float montoFijo, String grupoImpuesto, boolean isDefault) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.porcentaje = porcentaje;
		this.montoFijo = montoFijo;
		this.grupoImpuesto = grupoImpuesto;
		this.isDefault = isDefault;
	}
	public Impuesto() {

	}
	@Override
	public String toString() {
		return String.valueOf(this.codigo)+" | "+this.descripcion;
	}




}

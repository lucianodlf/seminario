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

public class ListaPrecio {

	private int codigoListaPrecios;
	private String descripcion;
	private Float utilidad;
	private Float recargoAdicional;
	private Float descuentoAdicional;
	private Impuesto alicuotaIva;
	private boolean Default;
	private boolean aplicaAlicuta;
	private boolean activo;




	public Impuesto getAlicuotaIva() {
		return alicuotaIva;
	}
	public void setAlicuotaIva(Impuesto alicuotaIva) {
		this.alicuotaIva = alicuotaIva;
	}
	public boolean isDefault() {
		return Default;
	}
	public void setDefault(boolean default1) {
		Default = default1;
	}
	public Float getUtilidad() {
		return utilidad;
	}
	public void setUtilidad(Float utilidad) {
		this.utilidad = utilidad;
	}
	public Float getRecargoAdicional() {
		return recargoAdicional;
	}
	public void setRecargoAdicional(Float recargoAdicional) {
		this.recargoAdicional = recargoAdicional;
	}
	public Float getDescuentoAdicional() {
		return descuentoAdicional;
	}
	public void setDescuentoAdicional(Float descuentoAdicional) {
		this.descuentoAdicional = descuentoAdicional;
	}
	public boolean isAplicaAlicuta() {
		return aplicaAlicuta;
	}
	public void setAplicaAlicuta(boolean aplicaAlicuta) {
		this.aplicaAlicuta = aplicaAlicuta;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public int getCodigoListaPrecios() {
		return codigoListaPrecios;
	}
	public void setCodigoListaPrecios(int codigoListaPrecios) {
		this.codigoListaPrecios = codigoListaPrecios;
	}
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public ListaPrecio(int codigoListaPrecios, String descripcion,
			Float utilidad, Float recargoAdicional, Float descuentoAdicional,
			Impuesto alicuotaIva, boolean default1, boolean aplicaAlicuta,
			boolean activo) {
		super();
		this.codigoListaPrecios = codigoListaPrecios;
		this.descripcion = descripcion;
		this.utilidad = utilidad;
		this.recargoAdicional = recargoAdicional;
		this.descuentoAdicional = descuentoAdicional;
		this.alicuotaIva = alicuotaIva;
		Default = default1;
		this.aplicaAlicuta = aplicaAlicuta;
		this.activo = activo;
	}
	public ListaPrecio() {
		// TODO Auto-generated constructor stub
	}



}

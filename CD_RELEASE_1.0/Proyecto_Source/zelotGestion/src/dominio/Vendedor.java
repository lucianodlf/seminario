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

public class Vendedor extends Persona {

	private String email;
	private String fechaAlta;
	private String password;
	private Vehiculo vehiculo;
	private Deposito deposito;
	private boolean	activo;




	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Deposito getDeposito() {
		return deposito;
	}
	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	public Vendedor() {
		// TODO Auto-generated constructor stub
	}
	public Vendedor(int codigo, String nombre, String apellido,
			DocumentoTipoPersona documentoTipoPersona,
			String documentoNroPersona, String fechaNacimiento,
			Provincia provincia, Localidad localidad, String telefonoFijo,
			String telefonoMovil, String direccionCalle, String direccionNro,
			int direccionPiso, String direccionDpto, String email,
			String fechaAlta, String password, boolean activo) {
		super(codigo, nombre, apellido, documentoTipoPersona,
				documentoNroPersona, fechaNacimiento, provincia, localidad,
				telefonoFijo, telefonoMovil, direccionCalle, direccionNro,
				direccionPiso, direccionDpto);
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.password = password;
		this.activo = activo;
	}
	@Override
	public String toString() {
		return this.getCodigo() + ") " + this.getApellido() + " " + this.getNombre();
	}


}

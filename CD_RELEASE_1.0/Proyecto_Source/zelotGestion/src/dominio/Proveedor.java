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
public class Proveedor extends Persona {

	private CondicionIva CondicionIVA;
	private String numeroCuit;
	private String email;
	private String fechaAlta;
	private String razonSocial;
	private String nombreFantasia;
	private Float limiteCredito;
	private boolean activo;


	public CondicionIva getCondicionIVA() {
		return CondicionIVA;
	}





	public void setCondicionIVA(CondicionIva condicionIVA) {
		CondicionIVA = condicionIVA;
	}





	public String getNumeroCuit() {
		return numeroCuit;
	}





	public void setNumeroCuit(String numeroCuit) {
		this.numeroCuit = numeroCuit;
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





	public String getRazonSocial() {
		return razonSocial;
	}





	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}





	public String getNombreFantasia() {
		return nombreFantasia;
	}





	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}





	public Float getLimiteCredito() {
		return limiteCredito;
	}





	public void setLimiteCredito(Float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}





	public boolean isActivo() {
		return activo;
	}





	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	public Proveedor(int codigo, String nombre, String apellido,
			DocumentoTipoPersona documentoTipoPersona,
			String documentoNroPersona, String fechaNacimiento,
			Provincia provincia, Localidad localidad, String telefonoFijo,
			String telefonoMovil, String direccionCalle, String direccionNro,
			int direccionPiso, String direccionDpto, CondicionIva condicionIVA,
			String numeroCuit, String email, String fechaAlta,
			String razonSocial, String nombreFantasia, Float limiteCredito,
			boolean activo) {
		super(codigo, nombre, apellido, documentoTipoPersona,
				documentoNroPersona, fechaNacimiento, provincia, localidad,
				telefonoFijo, telefonoMovil, direccionCalle, direccionNro,
				direccionPiso, direccionDpto);
		CondicionIVA = condicionIVA;
		this.numeroCuit = numeroCuit;
		this.email = email;
		this.fechaAlta = fechaAlta;
		this.razonSocial = razonSocial;
		this.nombreFantasia = nombreFantasia;
		this.limiteCredito = limiteCredito;
		this.activo = activo;
	}



	public Proveedor(int codigo, String nombre, String apellido,
			DocumentoTipoPersona documentoTipoPersona,
			String documentoNroPersona, String fechaNacimiento,
			Provincia provincia, Localidad localidad, String telefonoFijo,
			String telefonoMovil, String direccionCalle, String direccionNro,
			int direccionPiso, String direccionDpto) {
		super(codigo, nombre, apellido, documentoTipoPersona, documentoNroPersona,
				fechaNacimiento, provincia, localidad, telefonoFijo, telefonoMovil,
				direccionCalle, direccionNro, direccionPiso, direccionDpto);
		// TODO Auto-generated constructor stub
	}





	public Proveedor() {
		// TODO Auto-generated constructor stub
	}
}

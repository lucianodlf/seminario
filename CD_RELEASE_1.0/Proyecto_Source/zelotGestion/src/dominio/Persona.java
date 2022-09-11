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
public class Persona {

	private int codigo;
	private String nombre;
	private String apellido;
	private DocumentoTipoPersona documentoTipoPersona;
	private String documentoNroPersona;
	private String fechaNacimiento;
	private Provincia provincia;
	private Localidad localidad;
	private String telefonoFijo;
	private String telefonoMovil;
	private String direccionCalle;
	private String direccionNro;
	private int direccionPiso;
	private String direccionDpto;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public DocumentoTipoPersona getDocumentoTipoPersona() {
		return documentoTipoPersona;
	}
	public void setDocumentoTipoPersona(DocumentoTipoPersona documentoTipoPersona) {
		this.documentoTipoPersona = documentoTipoPersona;
	}
	public String getDocumentoNroPersona() {
		return documentoNroPersona;
	}
	public void setDocumentoNroPersona(String documentoNroPersona) {
		this.documentoNroPersona = documentoNroPersona;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getDireccionCalle() {
		return direccionCalle;
	}
	public void setDireccionCalle(String direccionCalle) {
		this.direccionCalle = direccionCalle;
	}
	public String getDireccionNro() {
		return direccionNro;
	}
	public void setDireccionNro(String direccionNro) {
		this.direccionNro = direccionNro;
	}
	public int getDireccionPiso() {
		return direccionPiso;
	}
	public void setDireccionPiso(int direccionPiso) {
		this.direccionPiso = direccionPiso;
	}
	public String getDireccionDpto() {
		return direccionDpto;
	}
	public void setDireccionDpto(String direccionDpto) {
		this.direccionDpto = direccionDpto;
	}

	public Persona(int codigo, String nombre, String apellido,
			DocumentoTipoPersona documentoTipoPersona,
			String documentoNroPersona, String fechaNacimiento,
			Provincia provincia, Localidad localidad, String telefonoFijo,
			String telefonoMovil, String direccionCalle, String direccionNro,
			int direccionPiso, String direccionDpto) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documentoTipoPersona = documentoTipoPersona;
		this.documentoNroPersona = documentoNroPersona;
		this.fechaNacimiento = fechaNacimiento;
		this.provincia = provincia;
		this.localidad = localidad;
		this.telefonoFijo = telefonoFijo;
		this.telefonoMovil = telefonoMovil;
		this.direccionCalle = direccionCalle;
		this.direccionNro = direccionNro;
		this.direccionPiso = direccionPiso;
		this.direccionDpto = direccionDpto;
	}

	public Persona() {

	}


}
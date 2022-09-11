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

public class Empresa {
	private String razonSocial;
	private String nombreFantasia;
	private String nroPtoVta;
	private String fechaInicioActividades;
	private String direccion;
	private Localidad localidad;
	private Provincia provincia;
	private String codPostal;
	private DocumentoTipoPersona documentoTipoPersona;
	private String documentoNro;
	private CondicionIva condicionIva;
	private String telefono1;
	private String telefono2;
	private String mail;
	private String web;

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
	public String getNroPtoVta() {
		return nroPtoVta;
	}
	public void setNroPtoVta(String nroPtoVta) {
		this.nroPtoVta = nroPtoVta;
	}
	public String getFechaInicioActividades() {
		return fechaInicioActividades;
	}
	public void setFechaInicioActividades(String fechaInicioActividades) {
		this.fechaInicioActividades = fechaInicioActividades;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public DocumentoTipoPersona getDocumentoTipoPersona() {
		return documentoTipoPersona;
	}
	public void setDocumentoTipoPersona(DocumentoTipoPersona documentoTipoPersona) {
		this.documentoTipoPersona = documentoTipoPersona;
	}
	public String getDocumentoNro() {
		return documentoNro;
	}
	public void setDocumentoNro(String documentoNro) {
		this.documentoNro = documentoNro;
	}
	public CondicionIva getCondicionIva() {
		return condicionIva;
	}
	public void setCondicionIva(CondicionIva condicionIva) {
		this.condicionIva = condicionIva;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public Empresa(String razonSocial, String nombreFantasia, String nroPtoVta,
			String fechaInicioActividades, String direccion,
			Localidad localidad, Provincia provincia, String codPostal,
			DocumentoTipoPersona documentoTipoPersona, String documentoNro,
			CondicionIva condicionIva, String telefono1, String telefono2,
			String mail, String web) {
		super();
		this.razonSocial = razonSocial;
		this.nombreFantasia = nombreFantasia;
		this.nroPtoVta = nroPtoVta;
		this.fechaInicioActividades = fechaInicioActividades;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.codPostal = codPostal;
		this.documentoTipoPersona = documentoTipoPersona;
		this.documentoNro = documentoNro;
		this.condicionIva = condicionIva;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.mail = mail;
		this.web = web;
	}
	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}


}

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


public class ComprobanteCtaCte {

	private String nroComprobanteCtaCte;
	private String nroPtoVtaComprobanteCtaCte;
	private String letraComprobanteCtaCte;
	private String tipoComprobanteCtaCte;
	private Cliente cliente;
	private Vendedor vendedor;
	private String concepto;
	private String fechaEmision;
	private String fechaVencimiento;
	private Float montoOriginal;
	private Float saldo;
	private int cantidadCtas;
	private boolean anulado;



	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public String getNroComprobanteCtaCte() {
		return nroComprobanteCtaCte;
	}
	public void setNroComprobanteCtaCte(String nroComprobanteCtaCte) {
		this.nroComprobanteCtaCte = nroComprobanteCtaCte;
	}
	public String getNroPtoVtaComprobanteCtaCte() {
		return nroPtoVtaComprobanteCtaCte;
	}
	public void setNroPtoVtaComprobanteCtaCte(String nroPtoVtaComprobanteCtaCte) {
		this.nroPtoVtaComprobanteCtaCte = nroPtoVtaComprobanteCtaCte;
	}
	public String getLetraComprobanteCtaCte() {
		return letraComprobanteCtaCte;
	}
	public void setLetraComprobanteCtaCte(String letraComprobanteCtaCte) {
		this.letraComprobanteCtaCte = letraComprobanteCtaCte;
	}
	public String getTipoComprobanteCtaCte() {
		return tipoComprobanteCtaCte;
	}
	public void setTipoComprobanteCtaCte(String tipoComprobanteCtaCte) {
		this.tipoComprobanteCtaCte = tipoComprobanteCtaCte;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Float getMontoOriginal() {
		return montoOriginal;
	}
	public void setMontoOriginal(Float montoOriginal) {
		this.montoOriginal = montoOriginal;
	}
	public Float getSaldo() {
		return saldo;
	}
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}
	public int getCantidadCtas() {
		return cantidadCtas;
	}
	public void setCantidadCtas(int cantidadCtas) {
		this.cantidadCtas = cantidadCtas;
	}


	public ComprobanteCtaCte(String nroComprobanteCtaCte,
			String nroPtoVtaComprobanteCtaCte, String letraComprobanteCtaCte,
			String tipoComprobanteCtaCte, Cliente cliente, Vendedor vendedor,
			String concepto, String fechaEmision, String fechaVencimiento,
			Float montoOriginal, Float saldo, int cantidadCtas, boolean anulado) {
		super();
		this.nroComprobanteCtaCte = nroComprobanteCtaCte;
		this.nroPtoVtaComprobanteCtaCte = nroPtoVtaComprobanteCtaCte;
		this.letraComprobanteCtaCte = letraComprobanteCtaCte;
		this.tipoComprobanteCtaCte = tipoComprobanteCtaCte;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.concepto = concepto;
		this.fechaEmision = fechaEmision;
		this.fechaVencimiento = fechaVencimiento;
		this.montoOriginal = montoOriginal;
		this.saldo = saldo;
		this.cantidadCtas = cantidadCtas;
		this.anulado = anulado;
	}
	public ComprobanteCtaCte() {

	}



}

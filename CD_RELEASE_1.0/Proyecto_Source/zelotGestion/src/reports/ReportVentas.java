/*
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
package reports;

public class ReportVentas {
	private String fechaVta;
	private int codCliente;
	private String razonSocial;
	private String nroFactura;
	private String codItem;
	private String descItem;
	private Float cantItem;
	private Float preFinalUnit;
	private Float preTotalFinal;
	private int totalItemsFact;
	private Float totalCantItemFac;
	private Float montoTotalFac;


	public int getTotalItemsFact() {
		return totalItemsFact;
	}
	public void setTotalItemsFact(int totalItemsFact) {
		this.totalItemsFact = totalItemsFact;
	}
	public String getFechaVta() {
		return fechaVta;
	}
	public void setFechaVta(String fechaVta) {
		this.fechaVta = fechaVta;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getNroFactura() {
		return nroFactura;
	}
	public void setNroFactura(String nroFactura) {
		this.nroFactura = nroFactura;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getDescItem() {
		return descItem;
	}
	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}
	public Float getCantItem() {
		return cantItem;
	}
	public void setCantItem(Float cantItem) {
		this.cantItem = cantItem;
	}
	public Float getPreFinalUnit() {
		return preFinalUnit;
	}
	public void setPreFinalUnit(Float preFinalUnit) {
		this.preFinalUnit = preFinalUnit;
	}
	public Float getPreTotalFinal() {
		return preTotalFinal;
	}
	public void setPreTotalFinal(Float preTotalFinal) {
		this.preTotalFinal = preTotalFinal;
	}

	public Float getTotalCantItemFac() {
		return totalCantItemFac;
	}
	public void setTotalCantItemFac(Float totalCantItemFac) {
		this.totalCantItemFac = totalCantItemFac;
	}
	public Float getMontoTotalFac() {
		return montoTotalFac;
	}
	public void setMontoTotalFac(Float montoTotalFac) {
		this.montoTotalFac = montoTotalFac;
	}

	public ReportVentas(String fechaVta, int codCliente, String razonSocial,
			String nroFactura, String codItem, String descItem, Float cantItem,
			Float preFinalUnit, Float preTotalFinal, int totalItemsFact,
			Float totalCantItemFac, Float montoTotalFac) {
		super();
		this.fechaVta = fechaVta;
		this.codCliente = codCliente;
		this.razonSocial = razonSocial;
		this.nroFactura = nroFactura;
		this.codItem = codItem;
		this.descItem = descItem;
		this.cantItem = cantItem;
		this.preFinalUnit = preFinalUnit;
		this.preTotalFinal = preTotalFinal;
		this.totalItemsFact = totalItemsFact;
		this.totalCantItemFac = totalCantItemFac;
		this.montoTotalFac = montoTotalFac;
	}
	public ReportVentas() {

	}




}

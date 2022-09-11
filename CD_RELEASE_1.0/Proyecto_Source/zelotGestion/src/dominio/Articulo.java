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
public class Articulo {

	private int codigo;
	private String descripcion;
	private String sinonimo;
	private Proveedor proveedor;
	private Marca marca;
	private Rubro rubro;
	private SubRubro subRubro;
	private Linea linea;
	private Division division;
	private String fechaAlta;
	private Float descuentoDefault;
	private Deposito deposito;
	private Float stockDefault;
	private Float marckupDefaul;
	private Float costoConImp;
	private Float costoSinImp;
	private Float impInterno;
	private String unidadVta;
	private Float subUnidadVta;
	private Float unidadMinVta;
	private Float stockMin;
	private Float stockMax;
	private Float descuentoMax;
	private Float pesoEstimado;
	private Impuesto impuesto;
	private Float precioListaBase;
	private boolean activo;

	public Articulo(int codigo, String descripcion, String sinonimo,
			Proveedor proveedor, Marca marca, Rubro rubro, SubRubro subRubro,
			Linea linea, Division division, String fechaAlta,
			Float descuentoDefault, Deposito deposito, Float stockDefault,
			Float marckupDefaul, Float costoConImp, Float costoSinImp,
			Float impInterno, String unidadVta, Float subUnidadVta,
			Float unidadMinVta, Float stockMin, Float stockMax,
			Float descuentoMax, Float pesoEstimado, boolean activo, Impuesto impuesto, Float precioListaBase) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.sinonimo = sinonimo;
		this.proveedor = proveedor;
		this.marca = marca;
		this.rubro = rubro;
		this.subRubro = subRubro;
		this.linea = linea;
		this.division = division;
		this.fechaAlta = fechaAlta;
		this.descuentoDefault = descuentoDefault;
		this.deposito = deposito;
		this.stockDefault = stockDefault;
		this.marckupDefaul = marckupDefaul;
		this.costoConImp = costoConImp;
		this.costoSinImp = costoSinImp;
		this.impInterno = impInterno;
		this.unidadVta = unidadVta;
		this.subUnidadVta = subUnidadVta;
		this.unidadMinVta = unidadMinVta;
		this.stockMin = stockMin;
		this.stockMax = stockMax;
		this.descuentoMax = descuentoMax;
		this.pesoEstimado = pesoEstimado;
		this.activo = activo;
		this.impuesto = impuesto;
		this.precioListaBase = precioListaBase;
	}




	public Float getPrecioListaBase() {
		return precioListaBase;
	}




	public void setPrecioListaBase(Float precioListaBase) {
		this.precioListaBase = precioListaBase;
	}




	public Impuesto getImpuesto() {
		return impuesto;
	}




	public void setImpuesto(Impuesto impuesto) {
		this.impuesto = impuesto;
	}




	public Marca getMarca() {
		return marca;
	}




	public void setMarca(Marca marca) {
		this.marca = marca;
	}




	public Linea getLinea() {
		return linea;
	}




	public void setLinea(Linea linea) {
		this.linea = linea;
	}




	public Division getDivision() {
		return division;
	}




	public void setDivision(Division division) {
		this.division = division;
	}




	public Deposito getDeposito() {
		return deposito;
	}




	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}




	public Float getUnidadMinVta() {
		return unidadMinVta;
	}




	public void setUnidadMinVta(Float unidadMinVta) {
		this.unidadMinVta = unidadMinVta;
	}




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




	public String getSinonimo() {
		return sinonimo;
	}




	public void setSinonimo(String sinonimo) {
		this.sinonimo = sinonimo;
	}




	public Proveedor getProveedor() {
		return proveedor;
	}




	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}




	public Rubro getRubro() {
		return rubro;
	}




	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}




	public SubRubro getSubRubro() {
		return subRubro;
	}




	public void setSubRubro(SubRubro subRubro) {
		this.subRubro = subRubro;
	}




	public String getFechaAlta() {
		return fechaAlta;
	}




	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}




	public Float getDescuentoDefault() {
		return descuentoDefault;
	}




	public void setDescuentoDefault(Float descuentoDefault) {
		this.descuentoDefault = descuentoDefault;
	}




	public Float getStockDefault() {
		return stockDefault;
	}




	public void setStockDefault(Float stockDefault) {
		this.stockDefault = stockDefault;
	}




	public Float getMarckupDefaul() {
		return marckupDefaul;
	}




	public void setMarckupDefaul(Float marckupDefaul) {
		this.marckupDefaul = marckupDefaul;
	}




	public Float getCostoConImp() {
		return costoConImp;
	}




	public void setCostoConImp(Float costoConImp) {
		this.costoConImp = costoConImp;
	}




	public Float getCostoSinImp() {
		return costoSinImp;
	}




	public void setCostoSinImp(Float costoSinImp) {
		this.costoSinImp = costoSinImp;
	}




	public Float getImpInterno() {
		return impInterno;
	}




	public void setImpInterno(Float impInterno) {
		this.impInterno = impInterno;
	}




	public String getUnidadVta() {
		return unidadVta;
	}




	public void setUnidadVta(String unidadVta) {
		this.unidadVta = unidadVta;
	}




	public Float getSubUnidadVta() {
		return subUnidadVta;
	}




	public void setSubUnidadVta(Float subUnidadVta) {
		this.subUnidadVta = subUnidadVta;
	}




	public Float getStockMin() {
		return stockMin;
	}




	public void setStockMin(Float stockMin) {
		this.stockMin = stockMin;
	}




	public Float getStockMax() {
		return stockMax;
	}




	public void setStockMax(Float stockMax) {
		this.stockMax = stockMax;
	}




	public Float getDescuentoMax() {
		return descuentoMax;
	}




	public void setDescuentoMax(Float descuentoMax) {
		this.descuentoMax = descuentoMax;
	}




	public Float getPesoEstimado() {
		return pesoEstimado;
	}




	public void setPesoEstimado(Float pesoEstimado) {
		this.pesoEstimado = pesoEstimado;
	}




	public boolean isActivo() {
		return activo;
	}




	public void setActivo(boolean activo) {
		this.activo = activo;
	}




	public Articulo() {
		// TODO Auto-generated constructor stub
	}









}

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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import logica.ArticuloLogica;


import complementos.CalculationEngine;

import dominio.Articulo;
import dominio.Cliente;
import dominio.CondicionIva;
import dominio.ItemFactura;
import dominio.ListaPrecio;

public class ReportListadoPrecioLogica {
	private static ReportListadoPrecioLogica reportListadoPrecioLogica = null;
	private ArticuloLogica articuloLogica = ArticuloLogica.getInstance();  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:
	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private NumberFormat nf = new DecimalFormat("00000");


	public static ReportListadoPrecioLogica getInstance(){
		if(reportListadoPrecioLogica == null){
			reportListadoPrecioLogica = new ReportListadoPrecioLogica();
		}
		return reportListadoPrecioLogica;
	}

	private ReportListadoPrecioLogica() {

	}

	//recalcula los precios del articulo


	public Collection<ReportArticulo> getDataReport(ListaPrecio listaPrecio){
		ArrayList<Articulo> articulos = articuloLogica.getListArticulo();
		Collection<ReportArticulo> dataReport = new ArrayList<ReportArticulo>();
		ReportArticulo rlp = null;
		Iterator<Articulo> it = articulos.iterator();
		while(it.hasNext()){
			ItemFactura dataItem = recalculaPrecios((Articulo)it.next(), listaPrecio);
			rlp = new ReportArticulo();
			rlp.setCodigo(nf.format(dataItem.getArticulo().getCodigo()));
			rlp.setDescripcion(dataItem.getArticulo().getDescripcion());
			if(dataItem.getArticulo().getDescuentoDefault().equals(Float.valueOf(0))){
				rlp.setDescuento(String.valueOf(Float.valueOf(0)+"%"));
			}else{
				rlp.setDescuento(getValueDecimalVisual(dataItem.getArticulo().getDescuentoDefault())+"%");
			}
			rlp.setPrecio("$"+getValueDecimalVisual(dataItem.getPrecioTotal()));
			rlp.setUnidadVta(dataItem.getArticulo().getUnidadVta());

			dataReport.add(rlp);
		}
		return dataReport;
	}


	private ItemFactura recalculaPrecios(Articulo articulo, ListaPrecio listaPrecio){
		ItemFactura itemFactura = new ItemFactura();
		Cliente cliente = new Cliente();
		CondicionIva condicionIva = new CondicionIva();

		condicionIva.setDiscriminaIVA(true);
		cliente.setCondicionIVA(condicionIva);
		cliente.setListaPrecios(listaPrecio);
		itemFactura.setArticulo(articulo);
		itemFactura.setCantidad(Float.valueOf(1));
		itemFactura.setDescManual(Float.valueOf(0));
		itemFactura.setIva(articulo.getImpuesto().getPorcentaje());
		itemFactura.setRecManual(Float.valueOf(0));

		ItemFactura itFacResul = CalculationEngine.calculaImportesItemFactura(itemFactura, cliente);

		return itFacResul;
	}

	//RETORNA EL VALOR FLOAT DE LOS TEXT FIELD DECIMALES CON EL FORMATO DETERMINADO
	private String getValueDecimalVisual(Float valor){
		decimalFormatSymbols.setDecimalSeparator('.');
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		if(valor != null){
			//System.out.println(decimalFormat.format(valor.doubleValue()));
			return decimalFormat.format(valor.doubleValue());
		}else{
			return null;
		}
	}
}

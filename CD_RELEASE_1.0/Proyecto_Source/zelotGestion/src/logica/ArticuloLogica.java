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
package logica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import persistencia.ArticuloDao;
import dominio.Articulo;

public class ArticuloLogica {

	private static ArticuloLogica articuloLogica = null;
	private ArticuloDao articuloDao = ArticuloDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");


	public static ArticuloLogica getInstance(){
		if(articuloLogica == null){
			articuloLogica = new ArticuloLogica();
		}
		return articuloLogica;
	}

	public ArticuloLogica() {

	}

	/*
	 * METODOS
	 */
	public boolean addArticulo(Articulo iva){
		int rowAffected = articuloDao.create(iva, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Articulo getArticulo(int codigo){
			Articulo articulo = articuloDao.select(codigo);
			return articulo;
	}

	public ArrayList<Articulo> getListArticulo(){
			ArrayList<Articulo> listaArticulos = articuloDao.selectAll();
			return listaArticulos;
	}

	public boolean updateArticulo(Articulo articulo){
		if(articulo.getCodigo() != 0){
			int rowAffected = articuloDao.update(articulo);
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean deleteArticulo(int codigo){
		int rowAffected = articuloDao.delete(codigo);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = articuloDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}


	public boolean updateStockByArticulo(Articulo articulo, Float cantidad){
		if(articulo.getCodigo() != 0){
			int rowAffected = articuloDao.updateStock(articulo, cantidad);
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	/***** MOTOR DE CALCULO **********/

	//calcula precio de costo con impuestos internos
	public Float getPrecioCostoConImpInt(Articulo articulo){
		Float resultado = articulo.getCostoSinImp();
		if(articulo.getImpInterno() != null){
			resultado += articulo.getImpInterno();
		}
		if(resultado == null)resultado = new Float(0);
		return resultado;
	}

	//calcula precio de lista base
	public Float getPrecioListaBase(Articulo articulo){
		Float resultado = getPrecioCostoConImpInt(articulo);
		if(articulo.getMarckupDefaul() != null){
			resultado +=(resultado * articulo.getMarckupDefaul());
		}
		if(resultado == null)resultado = new Float(0);
		return resultado;
	}

	//calcula precio venta sin iva. calcula recargos y descuentos sobre el precio de lista base
	public Float getPrecioVentaSinIva(Articulo articulo){
		Float resultado = getPrecioListaBase(articulo);
		if(articulo.getDescuentoDefault() != null){
			resultado -= (resultado * articulo.getDescuentoDefault());
		}
		if(resultado == null)resultado = new Float(0);
		return resultado;
	}

	//calucla precio de venta con iva
	public Float getPrecioVentaConIva(Articulo articulo){
		Float resultado = getPrecioVentaSinIva(articulo);
		if(articulo.getImpuesto() != null){
			resultado += (resultado * articulo.getImpuesto().getPorcentaje());
		}
		if(resultado == null)resultado = new Float(0);
		return resultado;
	}




}

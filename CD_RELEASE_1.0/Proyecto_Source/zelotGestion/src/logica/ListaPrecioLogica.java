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
Este archivo forma parte de Zelot Gesti�n ERP.

     Zelot Gestion ERP es un software libre: usted puede redistribuirlo y / o modificar
     bajo los t�rminos de la GNU Lesser General Public License publicada por
     la Free Software Foundation, bien de la versi�n 3 de la Licencia, o
     (a su elecci�n) cualquier versi�n posterior.

     Zelot Gesti�n ERP se distribuye con la esperanza de que sea �til,
     pero SIN NINGUNA GARANT�A, incluso sin la garant�a impl�cita de
     COMERCIALIZACI�N o IDONEIDAD PARA UN PROP�SITO PARTICULAR. Consulte la
     GNU Lesser General Public License para m�s detalles.

     Deber�a haber recibido una copia de la GNU Lesser General Public License
     junto con Zelot Gesti�n ERP. Si no, vea <http://www.gnu.org/licenses/>.
******************************************************************************************/
package logica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import persistencia.ListaPrecioDao;
import dominio.ListaPrecio;

public class ListaPrecioLogica {

	private static ListaPrecioLogica listaPrecioLogica = null;
	private ListaPrecioDao listaPrecioDao = ListaPrecioDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static ListaPrecioLogica getInstance(){
		if(listaPrecioLogica == null){
			listaPrecioLogica = new ListaPrecioLogica();
		}
		return listaPrecioLogica;
	}

	public ListaPrecioLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addListaPrecio(ListaPrecio listaPrecio){
		int rowAffected = listaPrecioDao.create(listaPrecio, simpleDateFormat.format(new Date()));
		if (rowAffected != 0 ){
			return true;
		}else{
			return false;
		}
	}

	public ListaPrecio getListaPrecio(int codigo){
			ListaPrecio listaPrecio = listaPrecioDao.select(codigo);
			return listaPrecio;
	}

	public ArrayList<ListaPrecio> getListListaPrecio(){
			ArrayList<ListaPrecio> tiposDeDocumentos = listaPrecioDao.selectAll();
			return tiposDeDocumentos;
	}

	public boolean updateListaPrecio(ListaPrecio listaPrecio){
		if(listaPrecio.getCodigoListaPrecios() != 0){
			int rowAffected = listaPrecioDao.update(listaPrecio, simpleDateFormat.format(new Date()));
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean deleteListaPrecio(int codigo){
		int rowAffected = listaPrecioDao.delete(codigo, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = listaPrecioDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}

}

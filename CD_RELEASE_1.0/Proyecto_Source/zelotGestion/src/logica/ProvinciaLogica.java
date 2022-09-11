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

import persistencia.ProvinciaDao;
import dominio.Provincia;

public class ProvinciaLogica {

	private static ProvinciaLogica provinciaLogica = null;
	private ProvinciaDao provinciaDao = ProvinciaDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static ProvinciaLogica getInstance(){
		if(provinciaLogica == null){
			provinciaLogica = new ProvinciaLogica();
		}
		return provinciaLogica;
	}

	public ProvinciaLogica() {

	}

	/*
	 * METODOS
	 */
	public boolean addProvincia (Provincia provincia){
		int rowAffected = provinciaDao.create(provincia,  simpleDateFormat.format(new Date()));
		if (rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Provincia getProvincia(int codigo){
			Provincia provincia = provinciaDao.select(codigo);
			return provincia;
	}

	public ArrayList<Provincia> getListProvincias(){
			ArrayList<Provincia> provincias = provinciaDao.selectAll();
			return provincias;
	}

	public boolean updateProvincia(Provincia provincia){
		if(provincia.getCodigo() != 0){
			int rowAffected = provinciaDao.update(provincia);
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean deleteProvincia(int codigo){
		int rowAffected = provinciaDao.delete(codigo);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = provinciaDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}
}

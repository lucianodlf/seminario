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

import java.util.ArrayList;

import persistencia.ImpuestoDao;
import dominio.Impuesto;

public class ImpuestoLogica {
	private static ImpuestoLogica impuestoLogica = null;
	private ImpuestoDao impuestoDao = ImpuestoDao.getInstance();



	public static ImpuestoLogica getInstance(){
		if(impuestoLogica == null){
			impuestoLogica = new ImpuestoLogica();
		}
		return impuestoLogica;
	}

	public ImpuestoLogica() {

	}

	/*
	 * METODOS
	 */
	/*public boolean addImpuesto(Impuesto impuesto){
		int rowAffected = impuestoDao.create(impuesto, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}*/

	public Impuesto getImpuesto(int codigo){
			Impuesto impuesto = impuestoDao.select(codigo);
			return impuesto;
	}

	public Impuesto getImpuestoByArticulo(int codigoArticulo){
		Impuesto impuesto = impuestoDao.select(codigoArticulo);
		return impuesto;
	}


	public ArrayList<Impuesto> getListImpuestos(){
		ArrayList<Impuesto> impuestos = impuestoDao.selectAll();
		return impuestos;

	}



	/*public boolean updateImpuesto(Impuesto impuesto){
		if(impuesto.getCodigo() != 0){
			int rowAffected = impuestoDao.update(impuesto);
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}*/

	/*public boolean deleteImpuesto(int codigo){
		int rowAffected = impuestoDao.delete(codigo);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}*/

	/*public Integer getNewCodigoItems(){
		Integer newCodigo = impuestoDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}*/
}

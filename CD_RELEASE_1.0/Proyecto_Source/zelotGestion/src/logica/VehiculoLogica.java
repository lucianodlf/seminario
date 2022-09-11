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

import dominio.Vehiculo;

public class VehiculoLogica {

	private static VehiculoLogica vehiculoLogica = null;
	//private VehiculoDao vehiculoDao = VehiculoDao.getInstance();

	public static VehiculoLogica getInstance(){
		if(vehiculoLogica == null){
			vehiculoLogica = new VehiculoLogica();
		}
		return vehiculoLogica;
	}

	public VehiculoLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addVehiculo(Vehiculo vehiculo){
		/*int rowAffected = vehiculoDao.create(vehiculo);
		if (rowAffected != 0 ){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

	public Vehiculo getVehiculo(int codigo){
			/*Vehiculo vehiculo = vehiculoDao.select(codigo);
			return vehiculo;*/
		return null;
	}

	public ArrayList<Vehiculo> getListVehiculo(){
			/*ArrayList<Vehiculo> tiposDeDocumentos = vehiculoDao.selectAll();
			return tiposDeDocumentos;*/
		return null;
	}

	public boolean updateVehiculo(Vehiculo vehiculo){
		/*if(vehiculo.getId() != 0){
			int rowAffected = vehiculoDao.update(vehiculo);
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}*/
		return true;
	}

	public boolean deleteVehiculo(int codigo){
		/*int rowAffected = clienteTipoDao.delete(codigo);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

}

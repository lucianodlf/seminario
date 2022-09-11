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

import persistencia.ProveedorDao;

import dominio.Proveedor;

public class ProveedorLogica {

	private static ProveedorLogica proveedorLogica = null;
	private ProveedorDao proveedorDao = ProveedorDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static ProveedorLogica getInstance(){
		if(proveedorLogica == null){
			proveedorLogica = new ProveedorLogica();
		}
		return proveedorLogica;
	}

	private ProveedorLogica(){

	}

	public boolean addProveedor(Proveedor proveedor){
		int rowsAffected = proveedorDao.create(proveedor, simpleDateFormat.format(new Date()));
		if(rowsAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Proveedor getProveedor(int codigo){
		Proveedor proveedor = proveedorDao.select(codigo);
		return proveedor;
	}

	public ArrayList<Proveedor> getListProveedors(){
		ArrayList<Proveedor> proveedores = proveedorDao.selectAll();
		return proveedores;
	}

	public boolean updateProveedor(Proveedor proveedor){
		if(proveedor.getCodigo() != 0){
			int rowAffected = proveedorDao.update(proveedor, simpleDateFormat.format(new Date()));
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean deleteProveedor(int codigo){
		int rowAffected = proveedorDao.delete(codigo, simpleDateFormat.format(new Date()) );
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = proveedorDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}

}

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
Este archivo forma parte de Zelot Gesti?n ERP.

     Zelot Gestion ERP es un software libre: usted puede redistribuirlo y / o modificar
     bajo los t?rminos de la GNU Lesser General Public License publicada por
     la Free Software Foundation, bien de la versi?n 3 de la Licencia, o
     (a su elecci?n) cualquier versi?n posterior.

     Zelot Gesti?n ERP se distribuye con la esperanza de que sea ?til,
     pero SIN NINGUNA GARANT?A, incluso sin la garant?a impl?cita de
     COMERCIALIZACI?N o IDONEIDAD PARA UN PROP?SITO PARTICULAR. Consulte la
     GNU Lesser General Public License para m?s detalles.

     Deber?a haber recibido una copia de la GNU Lesser General Public License
     junto con Zelot Gesti?n ERP. Si no, vea <http://www.gnu.org/licenses/>.
******************************************************************************************/
package logica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import persistencia.VendedorDao;
import dominio.Vendedor;

public class VendedorLogica {

	private static VendedorLogica vendedorLogica = null;
	private VendedorDao vendedorDao = VendedorDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static VendedorLogica getInstance(){
		if(vendedorLogica == null){
			vendedorLogica = new VendedorLogica();
		}
		return vendedorLogica;
	}

	public VendedorLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addVendedor(Vendedor vendedor){
		int rowAffected = vendedorDao.create(vendedor, simpleDateFormat.format(new Date()));
		if (rowAffected != 0 ){
			return true;
		}else{
			return false;
		}
	}

	public Vendedor getVendedor(int codigo){
			Vendedor vendedor = vendedorDao.select(codigo);
			return vendedor;
	}

	public ArrayList<Vendedor> getListVendedor(){
			ArrayList<Vendedor> vendedores = vendedorDao.selectAll();
			return vendedores;
	}

	public boolean updateVendedor(Vendedor vendedor){
		if(vendedor.getCodigo() != 0){
			int rowAffected = vendedorDao.update(vendedor, simpleDateFormat.format(new Date()));
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean deleteVendedor(int codigo){
		int rowAffected = vendedorDao.delete(codigo, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = vendedorDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}

}

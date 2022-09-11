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

import persistencia.DepositoDao;
import dominio.Deposito;

public class DepositoLogica {

	private static DepositoLogica depositoLogica = null;
	private DepositoDao depositoDao = DepositoDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static DepositoLogica getInstance(){
		if(depositoLogica == null){
			depositoLogica = new DepositoLogica();
		}
		return depositoLogica;
	}

	public DepositoLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addDeposito(Deposito deposito){
		int rowAffected = depositoDao.create(deposito, simpleDateFormat.format(new Date()));
		if (rowAffected != 0 ){
			return true;
		}else{
			return false;
		}
	}

	public Deposito getDeposito(int codigo){
			Deposito deposito = depositoDao.select(codigo);
			return deposito;
	}

	public ArrayList<Deposito> getListDeposito(){
			ArrayList<Deposito> tiposDeDocumentos = depositoDao.selectAll();
			return tiposDeDocumentos;
	}

	public boolean updateDeposito(Deposito deposito){
		if(deposito.getCodigo() != 0){
			int rowAffected = depositoDao.update(deposito, simpleDateFormat.format(new Date()));
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean deleteDeposito(int codigo){
		int rowAffected = depositoDao.delete(codigo, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = depositoDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}

}

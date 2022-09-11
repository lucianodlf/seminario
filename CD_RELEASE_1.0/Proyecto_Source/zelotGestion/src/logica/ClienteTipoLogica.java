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

import java.util.ArrayList;

import persistencia.ClienteTipoDao;
import dominio.ClienteTipo;

public class ClienteTipoLogica {

	private static ClienteTipoLogica clienteTipoLogica = null;
	private ClienteTipoDao clienteTipoDao = ClienteTipoDao.getInstance();

	public static ClienteTipoLogica getInstance(){
		if(clienteTipoLogica == null){
			clienteTipoLogica = new ClienteTipoLogica();
		}
		return clienteTipoLogica;
	}

	public ClienteTipoLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addClienteTipo(ClienteTipo clienteTipo){
		/*int rowAffected = clienteTipoDao.create(clienteTipo);
		if (rowAffected != 0 ){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

	public ClienteTipo getClienteTipo(int codigo){
			ClienteTipo clienteTipo = clienteTipoDao.select(codigo);
			return clienteTipo;
	}

	public ArrayList<ClienteTipo> getListClienteTipo(){
			ArrayList<ClienteTipo> tiposDeDocumentos = clienteTipoDao.selectAll();
			return tiposDeDocumentos;
	}

	public boolean updateClienteTipo(ClienteTipo clienteTipo){
		/*if(clienteTipo.getId() != 0){
			int rowAffected = clienteTipoDao.update(clienteTipo);
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

	public boolean deleteClienteTipo(int codigo){
		/*int rowAffected = clienteTipoDao.delete(codigo);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

}

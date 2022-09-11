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

import persistencia.ClienteEstadoDao;
import dominio.ClienteEstado;

public class ClienteEstadoLogica {

	private static ClienteEstadoLogica clienteEstadoLogica = null;
	private ClienteEstadoDao clienteEstadoDao = ClienteEstadoDao.getInstance();

	public static ClienteEstadoLogica getInstance(){
		if(clienteEstadoLogica == null){
			clienteEstadoLogica = new ClienteEstadoLogica();
		}
		return clienteEstadoLogica;
	}

	public ClienteEstadoLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addClienteEstado(ClienteEstado clienteEstado){
		/*int rowAffected = clienteEstadoDao.create(clienteEstado);
		if (rowAffected != 0 ){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

	public ClienteEstado getClienteEstado(int codigo){
			ClienteEstado clienteEstado = clienteEstadoDao.select(codigo);
			return clienteEstado;
	}

	public ArrayList<ClienteEstado> getListClienteEstado(){
			ArrayList<ClienteEstado> tiposDeDocumentos = clienteEstadoDao.selectAll();
			return tiposDeDocumentos;
	}

	public boolean updateClienteEstado(ClienteEstado clienteEstado){
		/*if(clienteEstado.getId() != 0){
			int rowAffected = clienteEstadoDao.update(clienteEstado);
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

	public boolean deleteClienteEstado(int codigo){
		/*int rowAffected = clienteTipoDao.delete(codigo);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

}

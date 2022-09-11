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
package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dominio.ClienteTipo;


public class ClienteTipoDao {

	private static ClienteTipoDao clienteTipoDao = null;

	public static ClienteTipoDao getInstance(){
		if(clienteTipoDao == null){
			clienteTipoDao = new ClienteTipoDao();
		}
		return clienteTipoDao;
	}

	public ClienteTipoDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	/*public int create(CondicionIVA clienteTipo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO GRAL_CONDICIONES_IVA (DESCRIPCION, DISCRIMINA_IVA, MUESTRA_IVA, LETRA_FACTURACION_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, clienteTipo.getDescripcion());
			preparedStatement.setString(2, String.valueOf(clienteTipo.isDiscriminaIVA()));
			preparedStatement.setString(3, String.valueOf(clienteTipo.isMuestraIVA()));
			preparedStatement.setString(4, clienteTipo.getLetraFacturacionDefault());
			preparedStatement.setString(5, "1");
			preparedStatement.setString(6, fechaHoraUpdate);

			rowsAffected = preparedStatement.executeUpdate();
			System.out.println("QUERY: " + preparedStatement);
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}*/

	/*
	 * UPDATE
	 */
	/*public int update(CondicionIVA iva){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE tipo_iva SET iva_nombre = ? WHERE id_iva = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, iva.getNombre());
			preparedStatement.setString(2, String.valueOf(iva.getCodigo()));

			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}*/

	/*
	 * DELETE
	 */
	/*public int delete(int codigo){
		try{
			int filasAfectadas = 0;
			String sql = "DELETE FROM tipo_iva WHERE id_iva = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(codigo));

			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}*/

	/*
	 * SELECT POR CODIGO
	 */
	public ClienteTipo select(int codigo){
		try{
			ClienteTipo clienteTipo = new ClienteTipo();
			String sql = "SELECT ID_CLIENTE_TIPO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM VTAS_CLIENTES_TIPO WHERE ACTIVO = ? AND ID_CLIENTE_TIPO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				clienteTipo.setCodigoClienteTipo(codigo);
				clienteTipo.setDescripcion(resultSet.getString("DESCRIPCION"));

				preparedStatement.close();
				return clienteTipo;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ClienteTipo> selectAll(){
		try{
			ClienteTipo clienteTipo = null;
			ArrayList<ClienteTipo> clientesTipo = new ArrayList<ClienteTipo>();

			String sql = "SELECT ID_CLIENTE_TIPO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM VTAS_CLIENTES_TIPO WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					clienteTipo = new ClienteTipo();
					clienteTipo.setCodigoClienteTipo(Integer.parseInt(resultSet.getString("ID_CLIENTE_TIPO")));
					clienteTipo.setDescripcion(resultSet.getString("DESCRIPCION"));
					clientesTipo.add(clienteTipo);
				}

				preparedStatement.close();
				return clientesTipo;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}

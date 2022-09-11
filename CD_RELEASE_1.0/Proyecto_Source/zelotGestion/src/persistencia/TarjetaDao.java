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

import dominio.Tarjeta;


public class TarjetaDao {

	private static TarjetaDao tarjetaDao = null;

	public static TarjetaDao getInstance(){
		if(tarjetaDao == null){
			tarjetaDao = new TarjetaDao();
		}
		return tarjetaDao;
	}

	private TarjetaDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
/*	public int create(Tarjeta tarjeta){
		try{
			int filasAfectadas = 0;
			String sql = "INSERT INTO tarjetas (tar_nombre)" +
			" VALUES(?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, tarjeta.getNombre());
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}*/

	/*
	 * UPDATE
	 */
/*	public int update(Tarjeta tarjeta){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE tarjetas SET tar_nombre = ? WHERE id_tarjeta = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, tarjeta.getNombre());
			preparedStatement.setString(2, String.valueOf(tarjeta.getCodigo()));
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
			String sql = "DELETE FROM tarjetas WHERE id_tarjeta = ?";
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
	public Tarjeta select(int codigo){
		try{
			Tarjeta tarjeta = new Tarjeta();
			String sql = "SELECT ID_TARJETA, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_TARJETAS WHERE ACTIVO = ? AND ID_TARJETA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				tarjeta.setCodigo(codigo);
				tarjeta.setNombre(resultSet.getString("DESCRIPCION"));

				preparedStatement.close();
				return tarjeta;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Tarjeta> selectAll(){
		try{
			Tarjeta tarjeta = null;
			ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();

			String sql = "SELECT ID_TARJETA, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_TARJETAS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					tarjeta = new Tarjeta();
					tarjeta.setCodigo(Integer.parseInt(resultSet.getString("ID_TARJETA")));
					tarjeta.setNombre(resultSet.getString("DESCRIPCION"));
					tarjetas.add(tarjeta);
				}

				preparedStatement.close();
				return tarjetas;
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

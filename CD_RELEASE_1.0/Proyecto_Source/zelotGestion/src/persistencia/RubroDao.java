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

import dominio.Rubro;


public class RubroDao {

	private static RubroDao rubroDao = null;

	public static RubroDao getInstance(){
		if(rubroDao == null){
			rubroDao = new RubroDao();
		}
		return rubroDao;
	}

	public RubroDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Rubro rubro, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO INVRIO_RUBROS (DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, rubro.getDescripcion());
			preparedStatement.setBoolean(2, true);
			preparedStatement.setString(3, fechaHoraUpdate);

			rowsAffected = preparedStatement.executeUpdate();
			//System.out.println("QUERY: " + preparedStatement);
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * UPDATE
	 */
	public int update(Rubro rubro, String fechaHoraUpdate ){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_RUBROS SET DESCRIPCION = ?, FECHA_HORA_UPDATE = ? WHERE ID_RUBRO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, rubro.getDescripcion());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, String.valueOf(rubro.getCodigo()));

			//System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * DELETE
	 */
	public int delete(int codigo, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_RUBROS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_RUBRO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, String.valueOf(codigo));

			//System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * SELECT POR CODIGO
	 */
	public Rubro select(int codigo){
		try{
			Rubro rubro = new Rubro();
			String sql = "SELECT ID_RUBRO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_RUBROS WHERE ACTIVO = ? AND ID_RUBRO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				rubro.setCodigo(codigo);
				rubro.setDescripcion(resultSet.getString("DESCRIPCION"));
				preparedStatement.close();
				return rubro;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Rubro> selectAll(){
		try{
			Rubro rubro = null;
			ArrayList<Rubro> rubros = new ArrayList<Rubro>();

			String sql = "SELECT ID_RUBRO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_RUBROS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					rubro = new Rubro();
					rubro.setCodigo(Integer.parseInt(resultSet.getString("ID_RUBRO")));
					rubro.setDescripcion(resultSet.getString("DESCRIPCION"));
					rubros.add(rubro);
				}

				preparedStatement.close();
				return rubros;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Integer selectLastCode(){
		try{
			Integer newCodigo = null;

			String sql = "SELECT MAX(ID_RUBRO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.INVRIO_RUBROS WHERE ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				newCodigo = resultSet.getInt("ULTIMOCODIGO");
				preparedStatement.close();
				return newCodigo;
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

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

import dominio.SubRubro;


public class SubRubroDao {

	private static SubRubroDao subRubroDao = null;

	public static SubRubroDao getInstance(){
		if(subRubroDao == null){
			subRubroDao = new SubRubroDao();
		}
		return subRubroDao;
	}

	public SubRubroDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(SubRubro subRubro, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO INVRIO_SUBRUBROS (DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, subRubro.getDescripcion());
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
	public int update(SubRubro subRubro, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_SUBRUBROS SET DESCRIPCION = ?, FECHA_HORA_UPDATE = ? WHERE ID_SUBRUBRO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, subRubro.getDescripcion());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, String.valueOf(subRubro.getCodigo()));

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
			String sql = "UPDATE INVRIO_SUBRUBROS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_SUBRUBRO = ?";

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
	public SubRubro select(int codigo){
		try{
			SubRubro subRubro = new SubRubro();
			String sql = "SELECT ID_SUBRUBRO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_SUBRUBROS WHERE ACTIVO = ? AND ID_SUBRUBRO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				subRubro.setCodigo(codigo);
				subRubro.setDescripcion(resultSet.getString("DESCRIPCION"));

				preparedStatement.close();
				return subRubro;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<SubRubro> selectAll(){
		try{
			SubRubro subRubro = null;
			ArrayList<SubRubro> subRubros = new ArrayList<SubRubro>();

			String sql = "SELECT ID_SUBRUBRO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_SUBRUBROS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					subRubro = new SubRubro();
					subRubro.setCodigo(Integer.parseInt(resultSet.getString("ID_SUBRUBRO")));
					subRubro.setDescripcion(resultSet.getString("DESCRIPCION"));
					subRubros.add(subRubro);
				}

				preparedStatement.close();
				return subRubros;
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

			String sql = "SELECT MAX(ID_SUBRUBRO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.INVRIO_SUBRUBROS WHERE ACTIVO = ?";

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

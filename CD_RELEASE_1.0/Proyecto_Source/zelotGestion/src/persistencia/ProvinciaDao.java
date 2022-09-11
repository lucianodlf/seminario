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

import dominio.Provincia;


public class ProvinciaDao {

	private static ProvinciaDao provinciaDao = null;

	public static ProvinciaDao getInstance(){
		if(provinciaDao == null){
			provinciaDao = new ProvinciaDao();
		}
		return provinciaDao;
	}

	public ProvinciaDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Provincia provincia, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO GRAL_PROVINCIAS (DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, provincia.getNombre());
			preparedStatement.setBoolean(2, true);
			preparedStatement.setString(3, fechaHoraUpdate);

			rowsAffected = preparedStatement.executeUpdate();
			System.out.println("QUERY: " + preparedStatement);
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
	public int update(Provincia provincia){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_PROVINCIAS SET DESCRIPCION = ? WHERE ID_PROVINCIA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, provincia.getNombre());
			preparedStatement.setInt(2, provincia.getCodigo());

			rowsAffected = preparedStatement.executeUpdate();
			System.out.println("QUERY: " + preparedStatement);
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * DELETE
	 */
	public int delete(int codigo){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_PROVINCIAS SET ACTIVO = ? WHERE ID_PROVINCIA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, codigo);

			rowsAffected = preparedStatement.executeUpdate();
			System.out.println("QUERY: " + preparedStatement);
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * SELECT POR CODIGO
	 */
	public Provincia select(int codigo){
		try{
			Provincia provincia = new Provincia();
			String sql = "SELECT ID_PROVINCIA, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_PROVINCIAS WHERE ACTIVO = ? AND ID_PROVINCIA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				provincia.setCodigo(codigo);
				provincia.setNombre(resultSet.getString("DESCRIPCION"));

				preparedStatement.close();
				return provincia;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Provincia> selectAll(){
		try{
			Provincia provincia = null;
			ArrayList<Provincia> provincias = new ArrayList<Provincia>();

			String sql = "SELECT ID_PROVINCIA, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_PROVINCIAS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					provincia = new Provincia();
					provincia.setCodigo(Integer.parseInt(resultSet.getString("ID_PROVINCIA")));
					provincia.setNombre(resultSet.getString("DESCRIPCION"));
					provincias.add(provincia);
				}

				preparedStatement.close();
				return provincias;
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

			String sql = "SELECT MAX(ID_PROVINCIA) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.GRAL_PROVINCIAS WHERE ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			System.out.println("QUERY: " + preparedStatement);
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

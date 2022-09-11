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

import logica.ProvinciaLogica;

import dominio.Localidad;


public class LocalidadDao {

	private static LocalidadDao localidadDao = null;
	private ProvinciaLogica provinciaLogica = ProvinciaLogica.getInstance();

	public static LocalidadDao getInstance(){
		if(localidadDao == null){
			localidadDao = new LocalidadDao();
		}
		return localidadDao;
	}

	public LocalidadDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Localidad localidad, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO GRAL_LOCALIDADES (ID_PROVINCIA, DESCRIPCION, CODIGO_POSTAL, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);


			preparedStatement.setInt(1, localidad.getProvincia().getCodigo());
			preparedStatement.setString(2, localidad.getNombre());
			preparedStatement.setString(3, localidad.getCodigoPostal());
			preparedStatement.setBoolean(4, true);
			preparedStatement.setString(5, fechaHoraUpdate);

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
	public int update(Localidad localidad){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_LOCALIDADES SET ID_PROVINCIA = ?, DESCRIPCION = ?, CODIGO_POSTAL = ? WHERE ID_LOCALIDAD = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, localidad.getProvincia().getCodigo());
			preparedStatement.setString(2, localidad.getNombre());
			preparedStatement.setString(3, localidad.getCodigoPostal());
			preparedStatement.setInt(4, localidad.getCodigo());

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
			String sql = "UPDATE GRAL_LOCALIDADES SET ACTIVO = ? WHERE ID_LOCALIDAD = ?";

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
	public Localidad select(int codigo){
		try{
			Localidad localidad = new Localidad();
			String sql = "SELECT ID_LOCALIDAD, ID_PROVINCIA, DESCRIPCION, CODIGO_POSTAL, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_LOCALIDADES WHERE ACTIVO = ? AND ID_LOCALIDAD = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				localidad.setCodigo(codigo);
				localidad.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
				localidad.setNombre(resultSet.getString("DESCRIPCION"));
				localidad.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));

				preparedStatement.close();
				return localidad;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Localidad> selectAll(){
		try{
			Localidad localidad = null;
			ArrayList<Localidad> localidades = new ArrayList<Localidad>();

			String sql = "SELECT ID_LOCALIDAD, ID_PROVINCIA, DESCRIPCION, CODIGO_POSTAL, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_LOCALIDADES WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					localidad = new Localidad();
					localidad.setCodigo(resultSet.getInt("ID_LOCALIDAD"));
					localidad.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
					localidad.setNombre(resultSet.getString("DESCRIPCION"));
					localidad.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
					localidades.add(localidad);
				}

				preparedStatement.close();
				return localidades;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Localidad> selectAllByProvincia(int codigoProvincia){
		try{
			Localidad localidad = null;
			ArrayList<Localidad> localidades = new ArrayList<Localidad>();

			String sql = "SELECT ID_LOCALIDAD, ID_PROVINCIA, DESCRIPCION, CODIGO_POSTAL, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_LOCALIDADES WHERE ACTIVO = ? AND ID_PROVINCIA = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigoProvincia);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					localidad = new Localidad();
					localidad.setCodigo(resultSet.getInt("ID_LOCALIDAD"));
					localidad.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
					localidad.setNombre(resultSet.getString("DESCRIPCION"));
					localidad.setCodigoPostal(resultSet.getString("CODIGO_POSTAL"));
					localidades.add(localidad);
				}

				preparedStatement.close();
				return localidades;
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

			String sql = "SELECT MAX(ID_LOCALIDAD) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.GRAL_LOCALIDADES WHERE ACTIVO = ?";

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

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

import dominio.Perfil;
import dominio.Permiso;

public class PerfilDao {
	private static PerfilDao perfilDao = null;
	/*private PermisoLogica periPermisoLogica = PermisoLogica.getInstance();*/

	public static PerfilDao getInstance(){
		if(perfilDao == null){
			perfilDao = new PerfilDao();
		}
		return perfilDao;
	}

	private PerfilDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Perfil perfil, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "INSERT INTO SGDAD_PERFILES (DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE)" +
			" VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, perfil.getNombre());
			preparedStatement.setBoolean(2, true);
			preparedStatement.setString(3, fechaHoraUpdate);

			System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}


	/*
	 * UPDATE
	 */
	public int update(Perfil perfil, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE SGDAD_PERFILES SET DESCRIPCION = ?, FECHA_HORA_UPDATE = ? WHERE ID_PERFIL = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, perfil.getNombre());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setInt(3, perfil.getCodigo());

			System.out.println("QUERY: " + preparedStatement);
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
			String sql = "UPDATE SGDAD_PERFILES SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_PERFIL = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setInt(3, codigo);

			System.out.println("QUERY: " + preparedStatement);
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
	public int rolbackPerfil(Perfil perfil){
		try{
			int filasAfectadas = 0;
			String sql = "DELETE FROM SGDAD_PERFILES WHERE ID_PERFIL = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, perfil.getCodigo());

			System.out.println("QUERY: " + preparedStatement);
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
	public Perfil select(int codigo){
		try{
			Perfil perfil = new Perfil();
			String sql = "SELECT ID_PERFIL, DESCRIPCION FROM SGDAD_PERFILES WHERE ID_PERFIL = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				perfil.setCodigo(resultSet.getInt("ID_PERFIL"));
				perfil.setNombre(resultSet.getString("DESCRIPCION"));

				preparedStatement.close();
				return perfil;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Perfil> selectAll(){
		try{
			ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
			Perfil perfil = null;
			String sql = "SELECT ID_PERFIL, DESCRIPCION FROM SGDAD_PERFILES WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					perfil = new Perfil();
					perfil.setCodigo(resultSet.getInt("ID_PERFIL"));
					perfil.setNombre(resultSet.getString("DESCRIPCION"));
					perfiles.add(perfil);
				}
				preparedStatement.close();
				return perfiles;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
/*
	public Perfil selectPerfilByUsuario(Usuario usuario){
		try{
			Perfil perfil = null;
			ArrayList<Permiso> permisos = new ArrayList<Permiso>();
			String sql = "SELECT * FROM perfiles_permisos WHERE perfil = ? AND usuario = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(usuario.getPerfil().getCodigo()));
			preparedStatement.setString(2, String.valueOf(usuario.getCodigo()));
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				perfil = select(Integer.valueOf(resultSet.getString("perfil")));
				resultSet.first();
			}
			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					permisos.add(periPermisoLogica.getPermisoByCodigo(Integer.valueOf(resultSet.getString("permiso"))));
				}
				perfil.setPermisos(permisos);
				preparedStatement.close();
				return perfil;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	*/

	public Integer selectLastCode(){
		try{
			Integer newCodigo = null;

			String sql = "SELECT MAX(ID_PERFIL) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.SGDAD_PERFILES WHERE ACTIVO = ?";

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


	/****************************** PERMISOS *************************************/
	public int createPermiso(Perfil perfil, Permiso permiso, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "INSERT INTO SGDAD_PERFILES_PERMISOS (ID_PERFIL, ID_PERMISO, ACTIVO, FECHA_HORA_UPDATE)" +
			" VALUES(?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, perfil.getCodigo());
			preparedStatement.setInt(2, permiso.getCodigo());
			preparedStatement.setBoolean(3, permiso.isActivo());
			preparedStatement.setString(4, fechaHoraUpdate);

			System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	public ArrayList<Permiso> selectPermisosByPerfil(Perfil perfil){
		try{
			ArrayList<Permiso> permisos = new ArrayList<Permiso>();
			Permiso permiso = null;
			String sql = "SELECT PER.ID_PERMISO AS COD_PERM, PER.DESCRIPCION AS DESC_PERM, PP.ACTIVO AS PP_ACTIVO FROM ZELOT_GESTION_DB.SGDAD_PERMISOS AS PER INNER JOIN " +
					"ZELOT_GESTION_DB.SGDAD_PERFILES_PERMISOS AS PP ON PER.ID_PERMISO = PP.ID_PERMISO WHERE PP.ID_PERFIL = ? AND PP.ACTIVO = ?;";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, perfil.getCodigo());
			preparedStatement.setBoolean(2, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					permiso = new Permiso();
					permiso.setCodigo(resultSet.getInt("COD_PERM"));
					permiso.setDescripcion(resultSet.getString("DESC_PERM"));
					permiso.setActivo(resultSet.getBoolean("PP_ACTIVO"));

					permisos.add(permiso);
				}
				preparedStatement.close();
				return permisos;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * UPDATE
	 */
	public int updatePermisosByPerfiles(Permiso permiso, Perfil perfil, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE SGDAD_PERFILES_PERMISOS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_PERFIL = ? AND ID_PERMISO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, permiso.isActivo());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setInt(3, perfil.getCodigo());
			preparedStatement.setInt(4, permiso.getCodigo());

			System.out.println("QUERY: " + preparedStatement);
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
	public int rolbackPermiso(Perfil perfil, Permiso permiso){
		try{
			int filasAfectadas = 0;
			String sql = "DELETE FROM SGDAD_PERFILES_PERMISOS WHERE ID_PERFIL = ? AND ID_PERMISO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, perfil.getCodigo());
			preparedStatement.setInt(2, permiso.getCodigo());

			System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

}

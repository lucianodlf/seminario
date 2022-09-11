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

import logica.PerfilLogica;
import dominio.Usuario;

public class UsuarioDao {

	/*
	 * IMPLEMENTACION PATRON SINGLETON
	 */
	private static UsuarioDao usuarioDao = null;
	private PerfilLogica perfilLogica = PerfilLogica.getInstance();

	public static UsuarioDao getInstance(){
		if(usuarioDao == null){
			usuarioDao = new UsuarioDao();
		}
		return usuarioDao;
	}

	private UsuarioDao(){

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Usuario usuario, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "INSERT INTO SGDAD_USUARIOS (ID_PERFIL, NOMBRE, APELLIDO, APODO, PASSWORD, EMAIL, FECHA_ALTA, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setInt(1, usuario.getPerfil().getCodigo());
			preparedStatement.setString(2, usuario.getNombre());
			preparedStatement.setString(3, usuario.getApellido());
			preparedStatement.setString(4, usuario.getApodo());
			preparedStatement.setString(5, usuario.getPassword());
			preparedStatement.setString(6, usuario.getEmail());
			preparedStatement.setString(7, usuario.getFechaAlta());
			preparedStatement.setBoolean(8, usuario.isActivo());
			preparedStatement.setString(9, fechaHoraUpdate);


			System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			/*filasAfectadas = setPermisosToPerfiles(usuario);*/
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
/*
	private int setPermisosToPerfiles(Usuario usuario){
		try{
			usuario.setCodigo((selectUsuarioByUserAndPass(usuario)).getCodigo());
			int filasAfectadas = 0;
			String sql = "INSERT INTO perfiles_permisos (perfil, permiso, usuario)" +
			" VALUES(?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			ArrayList<Permiso> permisos = usuario.getPerfil().getPermisos();
			Iterator<Permiso> it = permisos.iterator();
			while(it.hasNext()){
				Permiso permiso = (Permiso)it.next();
				preparedStatement.setString(1, String.valueOf(usuario.getPerfil().getCodigo()));
				preparedStatement.setString(2, String.valueOf(permiso.getCodigo()));
				preparedStatement.setString(3, String.valueOf(usuario.getCodigo()));
				filasAfectadas = preparedStatement.executeUpdate();
			}
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
*/
	/*
	 * UPDATE
	 */
	public int update(Usuario usuario, String fechaHoraUpdate){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE SGDAD_USUARIOS SET ID_PERFIL = ?, NOMBRE = ?, APELLIDO = ?, APODO = ?, " +
					"PASSWORD = ?, EMAIL = ?, ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_USUARIO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, usuario.getPerfil().getCodigo());
			preparedStatement.setString(2, usuario.getNombre());
			preparedStatement.setString(3, usuario.getApellido());
			preparedStatement.setString(4, usuario.getApodo());
			preparedStatement.setString(5, usuario.getPassword());
			preparedStatement.setString(6, usuario.getEmail());
			//preparedStatement.setString(5, usuario.getFechaAlta());
			preparedStatement.setBoolean(7, usuario.isActivo());
			preparedStatement.setString(8, fechaHoraUpdate);
			preparedStatement.setInt(9, usuario.getCodigo());

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
			String sql = "UPDATE SGDAD_USUARIOS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_USUARIO = ?";

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
	 * SELECT POR CODIGO
	 */
	public Usuario select(int codigo){
		try{
			Usuario usuario = new Usuario();

			String sql = "SELECT ID_USUARIO, ID_PERFIL, NOMBRE, APELLIDO, APODO, PASSWORD, EMAIL, " +
					"FECHA_ALTA, ACTIVO, FECHA_HORA_UPDATE FROM SGDAD_USUARIOS WHERE ID_USUARIO = ? AND ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, codigo);
			preparedStatement.setBoolean(2, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				usuario.setCodigo(resultSet.getInt("ID_USUARIO"));
				usuario.setPerfil(perfilLogica.getPerfilByCodigo(resultSet.getInt("ID_PERFIL")));
				usuario.setNombre(resultSet.getString("NOMBRE"));
				usuario.setApellido(resultSet.getString("APELLIDO"));
				usuario.setApodo(resultSet.getString("APODO"));
				usuario.setPassword(resultSet.getString("PASSWORD"));
				usuario.setEmail(resultSet.getString("EMAIL"));
				usuario.setFechaAlta(resultSet.getString("FECHA_ALTA"));
				usuario.setActivo(resultSet.getBoolean("ACTIVO"));

				return usuario;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Usuario> selectAll(){
		try{
			Usuario usuario = null;
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			String sql = "SELECT ID_USUARIO, ID_PERFIL, NOMBRE, APELLIDO, APODO, PASSWORD, EMAIL, " +
					"FECHA_ALTA, ACTIVO, FECHA_HORA_UPDATE FROM SGDAD_USUARIOS WHERE ACTIVO = ? ORDER BY NOMBRE ASC";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					usuario = new Usuario();
					usuario.setCodigo(resultSet.getInt("ID_USUARIO"));
					usuario.setPerfil(perfilLogica.getPerfilByCodigo(resultSet.getInt("ID_PERFIL")));
					usuario.setNombre(resultSet.getString("NOMBRE"));
					usuario.setApellido(resultSet.getString("APELLIDO"));
					usuario.setApodo(resultSet.getString("APODO"));
					usuario.setPassword(resultSet.getString("PASSWORD"));
					usuario.setEmail(resultSet.getString("EMAIL"));
					usuario.setFechaAlta(resultSet.getString("FECHA_ALTA"));
					usuario.setActivo(resultSet.getBoolean("ACTIVO"));
					usuarios.add(usuario);
				}
				preparedStatement.close();
				return usuarios;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Usuario selectUsuarioByUserAndPass(Usuario usuario){
		try{
			String sql = "SELECT ID_USUARIO, ID_PERFIL, NOMBRE, APELLIDO, APODO, PASSWORD, EMAIL, " +
					"FECHA_ALTA, ACTIVO, FECHA_HORA_UPDATE FROM SGDAD_USUARIOS WHERE APODO = ? AND PASSWORD = ? AND ACTIVO = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, usuario.getApodo());
			preparedStatement.setString(2, usuario.getPassword());
			preparedStatement.setBoolean(3, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				usuario.setCodigo(resultSet.getInt("ID_USUARIO"));
				usuario.setPerfil(perfilLogica.getPerfilByCodigo(resultSet.getInt("ID_PERFIL")));
				usuario.setNombre(resultSet.getString("NOMBRE"));
				usuario.setApellido(resultSet.getString("APELLIDO"));
				usuario.setApodo(resultSet.getString("APODO"));
				usuario.setPassword(resultSet.getString("PASSWORD"));
				usuario.setEmail(resultSet.getString("EMAIL"));
				usuario.setFechaAlta(resultSet.getString("FECHA_ALTA"));
				usuario.setActivo(resultSet.getBoolean("ACTIVO"));
				return usuario;
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
	public int selectCodigoUsuarioByUserAndPass(Usuario usuario){
		try{
			String sql = "SELECT id_usuario FROM usuarios WHERE us_user = ? AND us_pass = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, usuario.getNombreUsuario());
			preparedStatement.setString(2, usuario.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				return 	Integer.valueOf(resultSet.getString("id_usuario"));
			}else{
				preparedStatement.close();
				return 0;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
*/

	public Integer selectLastCode(){
		try{
			Integer newCodigo = null;

			String sql = "SELECT MAX(ID_PERFIL) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.SGDAD_USUARIOS WHERE ACTIVO = ?";

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

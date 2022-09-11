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

import dominio.Deposito;


public class DepositoDao {

	private static DepositoDao depositoDao = null;

	public static DepositoDao getInstance(){
		if(depositoDao == null){
			depositoDao = new DepositoDao();
		}
		return depositoDao;
	}

	public DepositoDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Deposito deposito, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO INVRIO_DEPOSITOS (DESCRIPCION, PRINCIPAL, FIJO, MOVIL, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, deposito.getDescripcion());
			preparedStatement.setBoolean(2, deposito.isPrincipal());
			preparedStatement.setBoolean(3, deposito.isFijo());
			preparedStatement.setBoolean(4, deposito.isMovil());
			preparedStatement.setBoolean(5, true);
			preparedStatement.setString(6, fechaHoraUpdate);

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
	public int update(Deposito deposito, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE INVRIO_DEPOSITOS SET DESCRIPCION = ?, PRINCIPAL = ?, FIJO = ?, MOVIL = ?, FECHA_HORA_UPDATE = ? " +
					"WHERE ID_DEPOSITO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, deposito.getDescripcion());
			preparedStatement.setBoolean(2, deposito.isPrincipal());
			preparedStatement.setBoolean(3, deposito.isFijo());
			preparedStatement.setBoolean(4, deposito.isMovil());
			preparedStatement.setString(5, fechaHoraUpdate);
			preparedStatement.setInt(6, deposito.getCodigo());

			//System.out.println("QUERY: " + preparedStatement);
			rowsAffected = preparedStatement.executeUpdate();
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
	public int delete(int codigo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE INVRIO_DEPOSITOS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_DEPOSITO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setInt(3, codigo);

			//System.out.println("QUERY: " + preparedStatement);
			rowsAffected = preparedStatement.executeUpdate();
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
	public Deposito select(int codigo){
		try{
			Deposito deposito = new Deposito();
			String sql = "SELECT ID_DEPOSITO, DESCRIPCION, PRINCIPAL, FIJO, MOVIL, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_DEPOSITOS WHERE ACTIVO = ? AND ID_DEPOSITO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				deposito.setCodigo(codigo);
				deposito.setDescripcion(resultSet.getString("DESCRIPCION"));
				deposito.setFijo(resultSet.getBoolean("FIJO"));
				deposito.setMovil(resultSet.getBoolean("MOVIL"));
				deposito.setPrincipal(resultSet.getBoolean("PRINCIPAL"));

				preparedStatement.close();
				return deposito;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Deposito> selectAll(){
		try{
			Deposito deposito = null;
			ArrayList<Deposito> depositos = new ArrayList<Deposito>();

			String sql = "SELECT ID_DEPOSITO, DESCRIPCION, PRINCIPAL, FIJO, MOVIL, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_DEPOSITOS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					deposito = new Deposito();
					deposito.setCodigo(resultSet.getInt("ID_DEPOSITO"));
					deposito.setDescripcion(resultSet.getString("DESCRIPCION"));
					deposito.setFijo(resultSet.getBoolean("FIJO"));
					deposito.setMovil(resultSet.getBoolean("MOVIL"));
					deposito.setPrincipal(resultSet.getBoolean("PRINCIPAL"));
					depositos.add(deposito);
				}

				preparedStatement.close();
				return depositos;
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

			String sql = "SELECT MAX(ID_DEPOSITO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.INVRIO_DEPOSITOS WHERE ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
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

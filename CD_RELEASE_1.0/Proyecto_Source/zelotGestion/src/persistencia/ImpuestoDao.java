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

import dominio.Impuesto;


public class ImpuestoDao {

	private static ImpuestoDao impuestoDao = null;

	public static ImpuestoDao getInstance(){
		if(impuestoDao == null){
			impuestoDao = new ImpuestoDao();
		}
		return impuestoDao;
	}

	public ImpuestoDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	/*public int create(Impuesto impuesto, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO VTAS_IMPUESTOS (DESCRIPCION, PORCENTAJE, MONTO_FIJO, " +
					"ID_GRUPO_IMPUESTO, DAFAUL, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, impuesto.getDescripcion());
			preparedStatement.setFloat(2, impuesto.getPorcentaje());
			preparedStatement.setFloat(3, impuesto.getMontoFijo());
			preparedStatement.setString(4, impuesto.getGrupoImpuesto());
			preparedStatement.setBoolean(5, false);
			preparedStatement.setBoolean(6, true);
			preparedStatement.setString(7, fechaHoraUpdate);

			rowsAffected = preparedStatement.executeUpdate();
			//System.out.println("QUERY: " + preparedStatement);
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
	/*public int update(Impuesto impuesto){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE VTAS_IMPUESTOS SET DESCRIPCION = ?, PORCENTAJE = ?, MONTO_FIJO = ?, " +
					"ID_GRUPO_IMPUESTO = ?, DAFAUL = ? WHERE ID_IMPUESTO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, impuesto.getDescripcion());
			preparedStatement.setFloat(2, impuesto.getPorcentaje());
			preparedStatement.setFloat(3, impuesto.getMontoFijo());
			preparedStatement.setString(4, impuesto.getGrupoImpuesto());
			preparedStatement.setBoolean(5, false);
			preparedStatement.setInt(6, impuesto.getCodigo());

			rowsAffected = preparedStatement.executeUpdate();
			//System.out.println("QUERY: " + preparedStatement);
			preparedStatement.close();
			return rowsAffected;
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
			int rowsAffected = 0;
			String sql = "UPDATE VTAS_IMPUESTOS SET ACTIVO = ? WHERE ID_IMPUESTO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, String.valueOf(codigo));

			//System.out.println("QUERY: " + preparedStatement);
			rowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}*/

	/*
	 * SELECT POR CODIGO
	 */
	public Impuesto select(int codigo){
		try{
			Impuesto impuesto = new Impuesto();
			String sql = "SELECT ID_IMPUESTO, DESCRIPCION, PORCENTAJE, MONTO_FIJO, ID_GRUPO_IMPUESTO, IS_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM VTAS_IMPUESTOS WHERE ACTIVO = ? AND ID_IMPUESTO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				impuesto.setCodigo(codigo);
				impuesto.setDescripcion(resultSet.getString("DESCRIPCION"));
				impuesto.setPorcentaje(resultSet.getFloat("PORCENTAJE"));
				impuesto.setMontoFijo(resultSet.getFloat("MONTO_FIJO"));
				impuesto.setGrupoImpuesto(resultSet.getString("ID_GRUPO_IMPUESTO"));
				impuesto.setDefault(resultSet.getBoolean("IS_DEFAULT"));

				preparedStatement.close();
				return impuesto;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Impuesto> selectAll(){
		try{
			Impuesto impuesto = null;
			ArrayList<Impuesto> impuestos = new ArrayList<Impuesto>();

			String sql = "SELECT ID_IMPUESTO, DESCRIPCION, PORCENTAJE, MONTO_FIJO, ID_GRUPO_IMPUESTO, IS_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM VTAS_IMPUESTOS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					impuesto = new Impuesto();
					impuesto.setCodigo(resultSet.getInt("ID_IMPUESTO"));
					impuesto.setDescripcion(resultSet.getString("DESCRIPCION"));
					impuesto.setPorcentaje(resultSet.getFloat("PORCENTAJE"));
					impuesto.setMontoFijo(resultSet.getFloat("MONTO_FIJO"));
					impuesto.setGrupoImpuesto(resultSet.getString("ID_GRUPO_IMPUESTO"));
					impuesto.setDefault(resultSet.getBoolean("IS_DEFAULT"));
					impuestos.add(impuesto);
				}

				preparedStatement.close();
				return impuestos;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}





	/*public Integer selectLastCode(){
		try{
			Integer newCodigo = null;

			String sql = "SELECT MAX(ID_IMPUESTO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.VTAS_IMPUESTOS WHERE ACTIVO = ?";

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
	}*/



}

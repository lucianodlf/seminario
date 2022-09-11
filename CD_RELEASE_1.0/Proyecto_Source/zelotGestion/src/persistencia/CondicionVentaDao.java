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

import dominio.CondicionVenta;


public class CondicionVentaDao {

	private static CondicionVentaDao condicionVentaDao = null;

	public static CondicionVentaDao getInstance(){
		if(condicionVentaDao == null){
			condicionVentaDao = new CondicionVentaDao();
		}
		return condicionVentaDao;
	}

	public CondicionVentaDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(CondicionVenta condicionVenta, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO VTAS_CONDICIONES_VENTA (DESCRIPCION, CANTIDAD_CUOTAS_DEFAULT, DIAS_VENCIMIENTO, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, condicionVenta.getDescripcion());
			preparedStatement.setInt(2, condicionVenta.getCantidadCuotasDefault());
			preparedStatement.setInt(3, condicionVenta.getDiasVencimiento());
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
	public int update(CondicionVenta condicionVenta, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE VTAS_CONDICIONES_VENTA SET DESCRIPCION = ?, CANTIDAD_CUOTAS_DEFAULT = ?, " +
					"DIAS_VENCIMIENTO = ?, ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_CONDICION_VENTA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, condicionVenta.getDescripcion());
			preparedStatement.setInt(2, condicionVenta.getCantidadCuotasDefault());
			preparedStatement.setInt(3, condicionVenta.getDiasVencimiento());
			preparedStatement.setBoolean(4, true);
			preparedStatement.setString(5, fechaHoraUpdate);
			preparedStatement.setInt(6, condicionVenta.getCodigoCondicionVenta());

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
			String sql = "UPDATE VTAS_CONDICIONES_VENTA SET ACTIVO = ? WHERE ID_CONDICION_VENTA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
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
	public CondicionVenta select(int codigo){
		try{
			CondicionVenta condicionVenta = new CondicionVenta();
			String sql = "SELECT ID_CONDICION_VENTA, DESCRIPCION, CANTIDAD_CUOTAS_DEFAULT, DIAS_VENCIMIENTO, ACTIVO, FECHA_HORA_UPDATE " +
					"FROM VTAS_CONDICIONES_VENTA WHERE ACTIVO = ? AND ID_CONDICION_VENTA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				condicionVenta.setCodigoCondicionVenta(codigo);
				condicionVenta.setDescripcion(resultSet.getString("DESCRIPCION"));
				condicionVenta.setCantidadCuotasDefault(resultSet.getInt("CANTIDAD_CUOTAS_DEFAULT"));
				condicionVenta.setDiasVencimiento(resultSet.getInt("DIAS_VENCIMIENTO"));


				preparedStatement.close();
				return condicionVenta;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<CondicionVenta> selectAll(){
		try{
			CondicionVenta condicionVenta = null;
			ArrayList<CondicionVenta> condicionesVenta = new ArrayList<CondicionVenta>();

			String sql = "SELECT ID_CONDICION_VENTA, DESCRIPCION, CANTIDAD_CUOTAS_DEFAULT, DIAS_VENCIMIENTO, ACTIVO, FECHA_HORA_UPDATE " +
					"FROM VTAS_CONDICIONES_VENTA WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					condicionVenta = new CondicionVenta();
					condicionVenta.setCodigoCondicionVenta(resultSet.getInt("ID_CONDICION_VENTA"));
					condicionVenta.setDescripcion(resultSet.getString("DESCRIPCION"));
					condicionVenta.setCantidadCuotasDefault(resultSet.getInt("CANTIDAD_CUOTAS_DEFAULT"));
					condicionVenta.setDiasVencimiento(resultSet.getInt("DIAS_VENCIMIENTO"));
					condicionesVenta.add(condicionVenta);
				}

				preparedStatement.close();
				return condicionesVenta;
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

			String sql = "SELECT MAX(ID_CONDICION_VENTA) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.VTAS_CONDICIONES_VENTA WHERE ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
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

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

import dominio.Caja;
import dominio.TipoMovimientoCaja;


public class CajaDao {

	private static CajaDao cajaDao = null;

	public static CajaDao getInstance(){
		if(cajaDao == null){
			cajaDao = new CajaDao();
		}
		return cajaDao;
	}

	public CajaDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Caja caja, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO CTBLE_CAJA (FECHA_HORA_INICIO, FECHA_HORA_CIERRE, MONTO_INICIO, MONTO_CIERRE, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, caja.getFechaInicio());
			preparedStatement.setString(2, caja.getFechaCierre());
			preparedStatement.setFloat(3, caja.getMontoInicio());
			preparedStatement.setFloat(4, caja.getMontoCierre());
			preparedStatement.setBoolean(5, true);
			preparedStatement.setString(6, fechaHoraUpdate);

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
	public int update(Caja caja, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE CTBLE_CAJA SET FECHA_HORA_CIERRE = ?, MONTO_CIERRE = ?, " +
					"FECHA_HORA_UPDATE = ?, CERRADA  = ? WHERE ID_CAJA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, caja.getFechaCierre());
			preparedStatement.setFloat(2, caja.getMontoCierre());
			preparedStatement.setString(3, fechaHoraUpdate);
			preparedStatement.setBoolean(4, caja.isCerrada());
			preparedStatement.setInt(5,caja.getCodigo());

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
/*	public int delete(int codigo){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_BANCOS SET ACTIVO = ? WHERE ID_BANCO = ?";

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
	}*/

	/*
	 * SELECT POR CODIGO
	 */
	public TipoMovimientoCaja selectTipoMovimiento(int codigo){
		try{
			TipoMovimientoCaja tipoMovimientoCaja = new TipoMovimientoCaja();
			String sql = "SELECT ID_TIPO_MOVIMIENTO, DESCRIPCION, OPERACION_SUMA, OPERACION_RESTA, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM CTABLE_TIPO_MOVIMIENTOS WHERE ACTIVO = ? AND ID_TIPO_MOVIMIENTO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				tipoMovimientoCaja.setCodigoTipoMovimiento(resultSet.getInt("ID_TIPO_MOVIMIENTO"));
				tipoMovimientoCaja.setDescripcion(resultSet.getString("DESCRIPCION"));
				tipoMovimientoCaja.setResta(resultSet.getBoolean("OPERACION_RESTA"));
				tipoMovimientoCaja.setSuma(resultSet.getBoolean("OPERACION_SUMA"));

				preparedStatement.close();
				return tipoMovimientoCaja;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Caja selectCaja(int codigo){
		try{
			Caja caja = new Caja();
			String sql = "SELECT FECHA_HORA_INICIO, FECHA_HORA_CIERRE, MONTO_INICIO, MONTO_CIERRE, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM CTBLE_CAJA WHERE ACTIVO = ? AND ID_CAJA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				caja.setCodigo(codigo);
				caja.setFechaInicio(resultSet.getString("FECHA_HORA_INICIO"));
				caja.setFechaCierre(resultSet.getString("FECHA_HORA_CIERRE"));
				caja.setMontoInicio(resultSet.getFloat("MONTO_INICIO"));
				caja.setMontoCierre(resultSet.getFloat("MONTO_CIERRE"));

				preparedStatement.close();
				return caja;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Caja selectCajaByFecha(String fecha){
		try{
			Caja caja = new Caja();
			String sql = "SELECT ID_CAJA, FECHA_HORA_INICIO, FECHA_HORA_CIERRE, MONTO_INICIO, MONTO_CIERRE, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM CTBLE_CAJA WHERE ACTIVO = ? AND DATE(FECHA_HORA_INICIO) = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, fecha);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				caja.setCodigo(resultSet.getInt("ID_CAJA"));
				caja.setFechaInicio(resultSet.getString("FECHA_HORA_INICIO"));
				caja.setFechaCierre(resultSet.getString("FECHA_HORA_CIERRE"));
				caja.setMontoInicio(resultSet.getFloat("MONTO_INICIO"));
				caja.setMontoCierre(resultSet.getFloat("MONTO_CIERRE"));

				preparedStatement.close();
				return caja;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Float selectMontoCierreAnterio(){
		try{
			Float montoCierre = Float.valueOf(0);
			String sql = "SELECT MONTO_CIERRE FROM CTBLE_CAJA WHERE ACTIVO = ? AND " +
					"FECHA_HORA_CIERRE =(SELECT MAX(FECHA_HORA_CIERRE) FROM CTBLE_CAJA WHERE ACTIVO = ?)";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setBoolean(2, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				montoCierre = resultSet.getFloat("MONTO_CIERRE");

				preparedStatement.close();
				return montoCierre;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	public ArrayList<TipoMovimientoCaja> selectAllTiposMovimientoCaja(){
		try{
			TipoMovimientoCaja tipoMovimientoCaja = null;
			ArrayList<TipoMovimientoCaja> tiposMovimientos = new ArrayList<TipoMovimientoCaja>();

			String sql = "SELECT ID_TIPO_MOVIMIENTO, DESCRIPCION, OPERACION_SUMA, OPERACION_RESTA, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM CTABLE_TIPO_MOVIMIENTOS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					tipoMovimientoCaja = new TipoMovimientoCaja();
					tipoMovimientoCaja.setCodigoTipoMovimiento(resultSet.getInt("ID_TIPO_MOVIMIENTO"));
					tipoMovimientoCaja.setDescripcion(resultSet.getString("DESCRIPCION"));
					tipoMovimientoCaja.setResta(resultSet.getBoolean("OPERACION_RESTA"));
					tipoMovimientoCaja.setSuma(resultSet.getBoolean("OPERACION_SUMA"));
					tiposMovimientos.add(tipoMovimientoCaja);
				}

				preparedStatement.close();
				return tiposMovimientos;
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

			String sql = "SELECT MAX(ID_BANCO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.CTBLE_CAJA WHERE ACTIVO = ?";

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

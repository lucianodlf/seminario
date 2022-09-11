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
import java.sql.Types;
import java.util.ArrayList;

import logica.CajaLogica;
import dominio.Caja;
import dominio.MovimientoCaja;


public class MovimientoCajaDao {

	private static MovimientoCajaDao movimientoCajaDao = null;
	private CajaLogica cajaLogica = CajaLogica.getInstance();

	public static MovimientoCajaDao getInstance(){
		if(movimientoCajaDao == null){
			movimientoCajaDao = new MovimientoCajaDao();
		}
		return movimientoCajaDao;
	}

	public MovimientoCajaDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(MovimientoCaja movimientoCaja, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO CTBLE_CAJA_MOVIMIENTOS (ID_CAJA, FECHA_HORA_MOVIMIENTO, TIPO_MOVIMIENTO, ID_SUJETO_ENTIDAD, " +
					"CONCEPTO, IMPORTE, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, movimientoCaja.getCaja().getCodigo());
			preparedStatement.setString(2, movimientoCaja.getFechaMovimiento());
			preparedStatement.setInt(3, movimientoCaja.getTipoMovimiento().getCodigoTipoMovimiento());
			if(movimientoCaja.getCodigoSujetoEntidad() != null){
				preparedStatement.setInt(4, movimientoCaja.getCodigoSujetoEntidad());
			}else{
				preparedStatement.setNull(4, Types.NULL);
			}
			preparedStatement.setString(5, movimientoCaja.getConcepto());
			preparedStatement.setFloat(6, movimientoCaja.getImporteMovimiento());
			preparedStatement.setBoolean(7, true);
			preparedStatement.setString(8, fechaHoraUpdate);

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
	public int update(MovimientoCaja movimientoCaja, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE CTBLE_CAJA_MOVIMIENTOS SET TIPO_MOVIMIENTO = ?, ID_SUJETO_ENTIDAD = ?, " +
					"CONCEPTO = ?, IMPORTE = ?, FECHA_HORA_UPDATE = ? WHERE ACTIVO = ? AND ID_CAJA_MOVIMIENTO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setInt(1, movimientoCaja.getTipoMovimiento().getCodigoTipoMovimiento());
			preparedStatement.setInt(2, movimientoCaja.getCodigoSujetoEntidad());
			preparedStatement.setString(3, movimientoCaja.getConcepto());
			preparedStatement.setFloat(4, movimientoCaja.getImporteMovimiento());
			preparedStatement.setString(5, fechaHoraUpdate);
			preparedStatement.setBoolean(6, true);
			preparedStatement.setInt(7, movimientoCaja.getCodigoMovimiento());

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
			String sql = "UPDATE CTBLE_CAJA_MOVIMIENTOS SET ACTIVO = ? WHERE ID_CAJA_MOVIMIENTO = ?";

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
	public MovimientoCaja select(int codigo){
		try{
			MovimientoCaja movimientoCaja = new MovimientoCaja();
			String sql = "SELECT ID_CAJA_MOVIMIENTO, ID_CAJA, FECHA_HORA_MOVIMIENTO, TIPO_MOVIMIENTO, ID_SUJETO_ENTIDAD, " +
					"CONCEPTO, IMPORTE, ACTIVO, FECHA_HORA_UPDATE FROM CTBLE_CAJA_MOVIMIENTOS " +
					"WHERE ACTIVO = ? AND ID_CAJA_MOVIMIENTO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				movimientoCaja.setCodigoMovimiento(codigo);
				movimientoCaja.setCaja(cajaLogica.getCaja(resultSet.getInt("ID_CAJA")));
				movimientoCaja.setFechaMovimiento(resultSet.getString("FECHA_HORA_MOVIMIENTO"));
				movimientoCaja.setTipoMovimiento(cajaLogica.getTipoMovimientoCaja(resultSet.getInt("TIPO_MOVIMIENTO")));
				movimientoCaja.setCodigoSujetoEntidad(resultSet.getInt("ID_SUJETO_ENTIDAD"));
				movimientoCaja.setConcepto(resultSet.getString("CONCEPTO"));
				movimientoCaja.setImporteMovimiento(resultSet.getFloat("IMPORTE"));

				preparedStatement.close();
				return movimientoCaja;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<MovimientoCaja> selectAll(){
		try{
			MovimientoCaja movimientoCaja = null;
			ArrayList<MovimientoCaja> movimientosCaja = new ArrayList<MovimientoCaja>();

			String sql = "SELECT ID_CAJA_MOVIMIENTO, ID_CAJA, FECHA_HORA_MOVIMIENTO, TIPO_MOVIMIENTO, ID_SUJETO_ENTIDAD, " +
					"CONCEPTO, IMPORTE, ACTIVO, FECHA_HORA_UPDATE FROM CTBLE_CAJA_MOVIMIENTOS " +
					"WHERE ACTIVO = ? ORDER BY FECHA_HORA_MOVIMIENTO ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					movimientoCaja = new MovimientoCaja();
					movimientoCaja.setCodigoMovimiento(resultSet.getInt("ID_CAJA_MOVIMIENTO"));
					movimientoCaja.setCaja(cajaLogica.getCaja(resultSet.getInt("ID_CAJA")));
					movimientoCaja.setFechaMovimiento(resultSet.getString("FECHA_HORA_MOVIMIENTO"));
					movimientoCaja.setTipoMovimiento(cajaLogica.getTipoMovimientoCaja(resultSet.getInt("TIPO_MOVIMIENTO")));
					movimientoCaja.setCodigoSujetoEntidad(resultSet.getInt("ID_SUJETO_ENTIDAD"));
					movimientoCaja.setConcepto(resultSet.getString("CONCEPTO"));
					movimientoCaja.setImporteMovimiento(resultSet.getFloat("IMPORTE"));
					movimientosCaja.add(movimientoCaja);
				}

				preparedStatement.close();
				return movimientosCaja;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<MovimientoCaja> selectAllByCaja(Caja caja){
		try{
			MovimientoCaja movimientoCaja = null;
			ArrayList<MovimientoCaja> movimientosCaja = new ArrayList<MovimientoCaja>();

			String sql = "SELECT ID_CAJA_MOVIMIENTO, ID_CAJA, FECHA_HORA_MOVIMIENTO, TIPO_MOVIMIENTO, ID_SUJETO_ENTIDAD, " +
					"CONCEPTO, IMPORTE, ACTIVO, FECHA_HORA_UPDATE FROM CTBLE_CAJA_MOVIMIENTOS " +
					"WHERE ACTIVO = ? AND ID_CAJA = ? ORDER BY FECHA_HORA_MOVIMIENTO ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, caja.getCodigo());

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					movimientoCaja = new MovimientoCaja();
					movimientoCaja.setCodigoMovimiento(resultSet.getInt("ID_CAJA_MOVIMIENTO"));
					movimientoCaja.setCaja(cajaLogica.getCaja(resultSet.getInt("ID_CAJA")));
					movimientoCaja.setFechaMovimiento(resultSet.getString("FECHA_HORA_MOVIMIENTO"));
					movimientoCaja.setTipoMovimiento(cajaLogica.getTipoMovimientoCaja(resultSet.getInt("TIPO_MOVIMIENTO")));
					movimientoCaja.setCodigoSujetoEntidad(resultSet.getInt("ID_SUJETO_ENTIDAD"));
					movimientoCaja.setConcepto(resultSet.getString("CONCEPTO"));
					movimientoCaja.setImporteMovimiento(resultSet.getFloat("IMPORTE"));
					movimientosCaja.add(movimientoCaja);
				}

				preparedStatement.close();
				return movimientosCaja;
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

			String sql = "SELECT MAX(ID_BANCO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.CTBLE_CAJA_MOVIMIENTOS WHERE ACTIVO = ?";

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

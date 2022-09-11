package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.NroCorrelativo;


public class NroCorrelativoDao {

	private static NroCorrelativoDao nroCorrelativoDao = null;


	public static NroCorrelativoDao getInstance(){
		if(nroCorrelativoDao == null){
			nroCorrelativoDao = new NroCorrelativoDao();
		}
		return nroCorrelativoDao;
	}

	public NroCorrelativoDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	/*public int create(NroCorrelativo nroCorrelativo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO GRAL_NROS_CORRELATIVOS_COMPROBANTES (DESCRIPCION, CANTIDAD_CUOTAS_DEFAULT, DIAS_VENCIMIENTO, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, nroCorrelativo.getDescripcion());
			preparedStatement.setInt(2, nroCorrelativo.getCantidadCuotasDefault());
			preparedStatement.setInt(3, nroCorrelativo.getDiasVencimiento());
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
	}*/

	/*
	 * UPDATE
	 */
	public int update(NroCorrelativo nroCorrelativo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_NROS_CORRELATIVOS_COMPROBANTES SET NRO_COMPROBANTE = ?, FECHA_HORA_UPDATE = ? " +
					"WHERE ID_TIPO_COMPROBANTE = ? AND LETRA_COMPROBANTE = ? AND NRO_PTO_VENTA = ? AND ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, nroCorrelativo.getNroComprobante());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, nroCorrelativo.getTipoComprobante());
			preparedStatement.setString(4, nroCorrelativo.getLetraComprobante());
			preparedStatement.setString(5, nroCorrelativo.getNroPuntoVenta());
			preparedStatement.setBoolean(6, true);

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
	public int undoCorrelativo(NroCorrelativo nroCorrelativo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_NROS_CORRELATIVOS_COMPROBANTES SET NRO_COMPROBANTE = ?, FECHA_HORA_UPDATE = ? " +
					"WHERE ID_TIPO_COMPROBANTE = ? AND LETRA_COMPROBANTE = ? AND NRO_PTO_VENTA = ? AND ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, nroCorrelativo.getNroComprobante());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, nroCorrelativo.getTipoComprobante());
			preparedStatement.setString(4, nroCorrelativo.getLetraComprobante());
			preparedStatement.setString(5, nroCorrelativo.getNroPuntoVenta());
			preparedStatement.setBoolean(6, true);

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
	 * SELECT
	 */
	public NroCorrelativo select(NroCorrelativo correlativo){
		try{
			NroCorrelativo nroCorrelativo = new NroCorrelativo();
			String sql = "SELECT ID_TIPO_COMPROBANTE, LETRA_COMPROBANTE, NRO_PTO_VENTA, NRO_COMPROBANTE, ACTIVO, FECHA_HORA_UPDATE " +
					"FROM GRAL_NROS_CORRELATIVOS_COMPROBANTES WHERE ACTIVO = ? AND ID_TIPO_COMPROBANTE = ? AND LETRA_COMPROBANTE = ? AND NRO_PTO_VENTA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, correlativo.getTipoComprobante());
			preparedStatement.setString(3, correlativo.getLetraComprobante());
			preparedStatement.setString(4, correlativo.getNroPuntoVenta());

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				nroCorrelativo.setTipoComprobante(resultSet.getString("ID_TIPO_COMPROBANTE"));
				nroCorrelativo.setLetraComprobante(resultSet.getString("LETRA_COMPROBANTE"));
				nroCorrelativo.setNroPuntoVenta(resultSet.getString("NRO_PTO_VENTA"));
				nroCorrelativo.setNroComprobante(resultSet.getString("NRO_COMPROBANTE"));

				preparedStatement.close();
				return nroCorrelativo;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/*public ArrayList<NroCorrelativo> selectAll(){
		try{
			NroCorrelativo nroCorrelativo = null;
			ArrayList<NroCorrelativo> condicionesVenta = new ArrayList<NroCorrelativo>();

			String sql = "SELECT ID_CONDICION_VENTA, DESCRIPCION, CANTIDAD_CUOTAS_DEFAULT, DIAS_VENCIMIENTO, ACTIVO, FECHA_HORA_UPDATE " +
					"FROM GRAL_NROS_CORRELATIVOS_COMPROBANTES WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					nroCorrelativo = new NroCorrelativo();
					nroCorrelativo.setCodigoNroCorrelativo(Integer.parseInt(resultSet.getString("ID_CONDICION_VENTA")));
					nroCorrelativo.setDescripcion(resultSet.getString("DESCRIPCION"));
					nroCorrelativo.setCantidadCuotasDefault(Integer.parseInt(resultSet.getString("CANTIDAD_CUOTAS_DEFAULT")));
					nroCorrelativo.setDiasVencimiento(Integer.parseInt(resultSet.getString("DIAS_VENCIMIENTO")));
					condicionesVenta.add(nroCorrelativo);
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
	}*/

	/*public Integer selectLastCode(){
		try{
			Integer newCodigo = null;

			String sql = "SELECT MAX(ID_CONDICION_VENTA) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.GRAL_NROS_CORRELATIVOS_COMPROBANTES WHERE ACTIVO = ?";

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
	}*/
}

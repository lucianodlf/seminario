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

import dominio.MedioPago;


public class MedioPagoDao {

	private static MedioPagoDao medioPagoDao = null;

	public static MedioPagoDao getInstance(){
		if(medioPagoDao == null){
			medioPagoDao = new MedioPagoDao();
		}
		return medioPagoDao;
	}

	public MedioPagoDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	/*public int create(MedioPago medioPago, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO INVRIO_ARTICULOS (SINONIMO_ARTICULO, ID_PROVEEDOR, ID_MARCA, ID_RUBRO, ID_SUBRUBRO, ID_LINEA, " +
					"ID_DIVISION, DESCRIPCION, FECHA_ALTA, PORCENTAJE_MARCKUP_DEFAULT, ID_DEPOSITO_DEFAULT, STOCK_DEFAULT, " +
					"PRECIO_COSTO_S_IMPUESTOS, PRECIO_COSTO_C_DESC_Y_REC, PRECIO_LISTA_BASE, MONTO_IMPUESTO_INTERNO, UNIDAD_VENTA, SUBUNIDADES_POR_UN_VENTA," +
					"UNIDAD_MINIMA_VENTA, STOCK_MINIMO, STOCK_MAXIMO, PORCENTAJE_MAXIMO_DESCUENTO, PORCENTAJE_DESCUENTO_DEFAULT," +
					"PESO_ESTIMADO, ID_ALICUOTA, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setString(1, medioPago.getSinonimo());
			preparedStatement.setInt(2, 1);
			preparedStatement.setNull(3, Types.NULL);
			preparedStatement.setInt(4, medioPago.getRubro().getCodigo());
			preparedStatement.setInt(5, medioPago.getSubRubro().getCodigo());
			preparedStatement.setNull(6, Types.NULL);
			preparedStatement.setInt(7, 1);
			preparedStatement.setString(8, medioPago.getDescripcion());
			preparedStatement.setString(9, simpleDateFormat.format(new Date()));
			preparedStatement.setFloat(10, medioPago.getMarckupDefaul());
			preparedStatement.setNull(11, Types.NULL);
			preparedStatement.setFloat(12, medioPago.getStockDefault());
			preparedStatement.setFloat(13, medioPago.getCostoSinImp());
			preparedStatement.setFloat(14, medioPago.getCostoConImp());
			preparedStatement.setFloat(15, medioPago.getPrecioListaBase());
			preparedStatement.setFloat(16, medioPago.getImpInterno());
			preparedStatement.setString(17, medioPago.getUnidadVta());
			preparedStatement.setFloat(18, medioPago.getSubUnidadVta());
			preparedStatement.setFloat(19, medioPago.getUnidadMinVta());
			preparedStatement.setFloat(20, medioPago.getStockMin());
			preparedStatement.setFloat(21, medioPago.getStockMax());
			preparedStatement.setFloat(22, medioPago.getDescuentoMax());
			preparedStatement.setFloat(23, medioPago.getDescuentoDefault());
			preparedStatement.setFloat(24, medioPago.getPesoEstimado());
			preparedStatement.setInt(25, medioPago.getImpuesto().getCodigo());
			preparedStatement.setBoolean(26, medioPago.isActivo());
			preparedStatement.setString(27, fechaHoraUpdate);

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
	/*public int update(MedioPago medioPago){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_ARTICULOS SET SINONIMO_ARTICULO = ?, ID_PROVEEDOR = ?, ID_MARCA = ?, ID_RUBRO = ?, ID_SUBRUBRO = ?," +
					"ID_LINEA = ?, ID_DIVISION = ?, DESCRIPCION = ?, FECHA_ALTA = ?, PORCENTAJE_MARCKUP_DEFAULT = ?," +
					"ID_DEPOSITO_DEFAULT = ?, STOCK_DEFAULT = ?, PRECIO_COSTO_S_IMPUESTOS = ?, PRECIO_COSTO_C_DESC_Y_REC = ?," +
					"PRECIO_LISTA_BASE = ?, MONTO_IMPUESTO_INTERNO = ?, UNIDAD_VENTA = ?, SUBUNIDADES_POR_UN_VENTA = ?, UNIDAD_MINIMA_VENTA = ?," +
					"STOCK_MINIMO = ?, STOCK_MAXIMO = ?, PORCENTAJE_MAXIMO_DESCUENTO = ?, PORCENTAJE_DESCUENTO_DEFAULT = ?," +
					"PESO_ESTIMADO = ?, ID_ALICUOTA = ?, ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_ARTICULO = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setString(1, medioPago.getSinonimo());
			preparedStatement.setInt(2, 1);
			preparedStatement.setNull(3, Types.NULL);
			preparedStatement.setInt(4, medioPago.getRubro().getCodigo());
			preparedStatement.setInt(5, medioPago.getSubRubro().getCodigo());
			preparedStatement.setNull(6, Types.NULL);
			preparedStatement.setInt(7, 1);
			preparedStatement.setString(8, medioPago.getDescripcion());
			preparedStatement.setString(9, medioPago.getFechaAlta());
			preparedStatement.setFloat(10, medioPago.getMarckupDefaul());
			preparedStatement.setNull(11, Types.NULL);
			preparedStatement.setFloat(12, medioPago.getStockDefault());
			preparedStatement.setFloat(13, medioPago.getCostoSinImp());
			preparedStatement.setFloat(14, medioPago.getCostoConImp());
			preparedStatement.setFloat(15, medioPago.getPrecioListaBase());
			preparedStatement.setFloat(16, medioPago.getImpInterno());
			preparedStatement.setString(17, medioPago.getUnidadVta());
			preparedStatement.setFloat(18, medioPago.getSubUnidadVta());
			preparedStatement.setFloat(19, medioPago.getUnidadMinVta());
			preparedStatement.setFloat(20, medioPago.getStockMin());
			preparedStatement.setFloat(21, medioPago.getStockMax());
			preparedStatement.setFloat(22, medioPago.getDescuentoMax());
			preparedStatement.setFloat(23, medioPago.getDescuentoDefault());
			preparedStatement.setFloat(24, medioPago.getPesoEstimado());
			preparedStatement.setInt(25, medioPago.getImpuesto().getCodigo());
			preparedStatement.setBoolean(26, medioPago.isActivo());
			preparedStatement.setString(27, simpleDateFormat.format(new Date()));

			preparedStatement.setString(28, String.valueOf(medioPago.getCodigo()));

			System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
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
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_ARTICULOS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_ARTICULO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, simpleDateFormat.format(new Date()));
			preparedStatement.setString(3, String.valueOf(codigo));

			System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}*/

	/*
	 * SELECT POR CODIGO
	 */
	public MedioPago select(int codigo){
		try{
			MedioPago medioPago = new MedioPago();
			String sql = "SELECT ID_MEDIO_PAGO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM CTA_CTE_MEDIOS_PAGOS WHERE ACTIVO = ? AND ID_MEDIO_PAGO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				medioPago.setCodigo(codigo);
				medioPago.setDescripcion(resultSet.getString("DESCRIPCION"));

				preparedStatement.close();
				return medioPago;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<MedioPago> selectAll(){
		try{
			MedioPago medioPago = null;
			ArrayList<MedioPago> medioPagos = new ArrayList<MedioPago>();

			String sql = "SELECT ID_MEDIO_PAGO, DESCRIPCION, ACTIVO, FECHA_HORA_UPDATE FROM CTA_CTE_MEDIOS_PAGOS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					medioPago = new MedioPago();
					medioPago.setCodigo(Integer.parseInt(resultSet.getString("ID_MEDIO_PAGO")));
					medioPago.setDescripcion(resultSet.getString("DESCRIPCION"));

					medioPagos.add(medioPago);
				}

				preparedStatement.close();
				return medioPagos;
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

			String sql = "SELECT MAX(ID_ARTICULO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.INVRIO_ARTICULOS WHERE ACTIVO = ?";

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
	}*/
}

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logica.ImpuestoLogica;
import logica.RubroLogica;
import logica.SubRubroLogica;

import dominio.Articulo;


public class ArticuloDao {

	private static ArticuloDao articuloDao = null;
	private RubroLogica rubroLogica = RubroLogica.getInstance();
	private SubRubroLogica subRubroLogica = SubRubroLogica.getInstance();
	private ImpuestoLogica impuestoLogica = ImpuestoLogica.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static ArticuloDao getInstance(){
		if(articuloDao == null){
			articuloDao = new ArticuloDao();
		}
		return articuloDao;
	}

	public ArticuloDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Articulo articulo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO INVRIO_ARTICULOS (SINONIMO_ARTICULO, ID_PROVEEDOR, ID_MARCA, ID_RUBRO, ID_SUBRUBRO, ID_LINEA, " +
					"ID_DIVISION, DESCRIPCION, FECHA_ALTA, PORCENTAJE_MARCKUP_DEFAULT, ID_DEPOSITO_DEFAULT, STOCK_DEFAULT, " +
					"PRECIO_COSTO_S_IMPUESTOS, PRECIO_COSTO_C_DESC_Y_REC, PRECIO_LISTA_BASE, MONTO_IMPUESTO_INTERNO, UNIDAD_VENTA, SUBUNIDADES_POR_UN_VENTA," +
					"UNIDAD_MINIMA_VENTA, STOCK_MINIMO, STOCK_MAXIMO, PORCENTAJE_MAXIMO_DESCUENTO, PORCENTAJE_DESCUENTO_DEFAULT," +
					"PESO_ESTIMADO, ID_ALICUOTA, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setString(1, articulo.getSinonimo());
			preparedStatement.setInt(2, 1);
			preparedStatement.setNull(3, Types.NULL);
			preparedStatement.setInt(4, articulo.getRubro().getCodigo());
			preparedStatement.setInt(5, articulo.getSubRubro().getCodigo());
			preparedStatement.setNull(6, Types.NULL);
			preparedStatement.setInt(7, 1);
			preparedStatement.setString(8, articulo.getDescripcion());
			preparedStatement.setString(9, simpleDateFormat.format(new Date()));
			preparedStatement.setFloat(10, articulo.getMarckupDefaul());
			preparedStatement.setNull(11, Types.NULL);
			preparedStatement.setFloat(12, articulo.getStockDefault());
			preparedStatement.setFloat(13, articulo.getCostoSinImp());
			preparedStatement.setFloat(14, articulo.getCostoConImp());
			preparedStatement.setFloat(15, articulo.getPrecioListaBase());
			preparedStatement.setFloat(16, articulo.getImpInterno());
			preparedStatement.setString(17, articulo.getUnidadVta());
			preparedStatement.setFloat(18, articulo.getSubUnidadVta());
			preparedStatement.setFloat(19, articulo.getUnidadMinVta());
			preparedStatement.setFloat(20, articulo.getStockMin());
			preparedStatement.setFloat(21, articulo.getStockMax());
			preparedStatement.setFloat(22, articulo.getDescuentoMax());
			preparedStatement.setFloat(23, articulo.getDescuentoDefault());
			preparedStatement.setFloat(24, articulo.getPesoEstimado());
			preparedStatement.setInt(25, articulo.getImpuesto().getCodigo());
			preparedStatement.setBoolean(26, articulo.isActivo());
			preparedStatement.setString(27, fechaHoraUpdate);

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
	public int update(Articulo articulo){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_ARTICULOS SET SINONIMO_ARTICULO = ?, ID_PROVEEDOR = ?, ID_MARCA = ?, ID_RUBRO = ?, ID_SUBRUBRO = ?," +
					"ID_LINEA = ?, ID_DIVISION = ?, DESCRIPCION = ?, FECHA_ALTA = ?, PORCENTAJE_MARCKUP_DEFAULT = ?," +
					"ID_DEPOSITO_DEFAULT = ?, STOCK_DEFAULT = ?, PRECIO_COSTO_S_IMPUESTOS = ?, PRECIO_COSTO_C_DESC_Y_REC = ?," +
					"PRECIO_LISTA_BASE = ?, MONTO_IMPUESTO_INTERNO = ?, UNIDAD_VENTA = ?, SUBUNIDADES_POR_UN_VENTA = ?, UNIDAD_MINIMA_VENTA = ?," +
					"STOCK_MINIMO = ?, STOCK_MAXIMO = ?, PORCENTAJE_MAXIMO_DESCUENTO = ?, PORCENTAJE_DESCUENTO_DEFAULT = ?," +
					"PESO_ESTIMADO = ?, ID_ALICUOTA = ?, ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_ARTICULO = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setString(1, articulo.getSinonimo());
			preparedStatement.setInt(2, 1);
			preparedStatement.setNull(3, Types.NULL);
			preparedStatement.setInt(4, articulo.getRubro().getCodigo());
			preparedStatement.setInt(5, articulo.getSubRubro().getCodigo());
			preparedStatement.setNull(6, Types.NULL);
			preparedStatement.setInt(7, 1);
			preparedStatement.setString(8, articulo.getDescripcion());
			preparedStatement.setString(9, articulo.getFechaAlta());
			preparedStatement.setFloat(10, articulo.getMarckupDefaul());
			preparedStatement.setNull(11, Types.NULL);
			preparedStatement.setFloat(12, articulo.getStockDefault());
			preparedStatement.setFloat(13, articulo.getCostoSinImp());
			preparedStatement.setFloat(14, articulo.getCostoConImp());
			preparedStatement.setFloat(15, articulo.getPrecioListaBase());
			preparedStatement.setFloat(16, articulo.getImpInterno());
			preparedStatement.setString(17, articulo.getUnidadVta());
			preparedStatement.setFloat(18, articulo.getSubUnidadVta());
			preparedStatement.setFloat(19, articulo.getUnidadMinVta());
			preparedStatement.setFloat(20, articulo.getStockMin());
			preparedStatement.setFloat(21, articulo.getStockMax());
			preparedStatement.setFloat(22, articulo.getDescuentoMax());
			preparedStatement.setFloat(23, articulo.getDescuentoDefault());
			preparedStatement.setFloat(24, articulo.getPesoEstimado());
			preparedStatement.setInt(25, articulo.getImpuesto().getCodigo());
			preparedStatement.setBoolean(26, articulo.isActivo());
			preparedStatement.setString(27, simpleDateFormat.format(new Date()));

			preparedStatement.setString(28, String.valueOf(articulo.getCodigo()));

			//System.out.println("QUERY: " + preparedStatement);
			filasAfectadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			return filasAfectadas;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	public int updateStock(Articulo articulo, Float cantidad){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_ARTICULOS SET STOCK_DEFAULT = ?, FECHA_HORA_UPDATE = ? WHERE ID_ARTICULO = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setFloat(1, cantidad);
			preparedStatement.setString(2, simpleDateFormat.format(new Date()));
			preparedStatement.setString(3, String.valueOf(articulo.getCodigo()));

			//System.out.println("QUERY: " + preparedStatement);
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
	public int delete(int codigo){
		try{
			int filasAfectadas = 0;
			String sql = "UPDATE INVRIO_ARTICULOS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_ARTICULO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, simpleDateFormat.format(new Date()));
			preparedStatement.setString(3, String.valueOf(codigo));

			//System.out.println("QUERY: " + preparedStatement);
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
	public Articulo select(int codigo){
		try{
			Articulo articulo = new Articulo();
			String sql = "SELECT ID_ARTICULO, SINONIMO_ARTICULO, ID_PROVEEDOR, ID_MARCA, ID_RUBRO, ID_SUBRUBRO, ID_LINEA, " +
					"ID_DIVISION, DESCRIPCION, FECHA_ALTA, PORCENTAJE_MARCKUP_DEFAULT, ID_DEPOSITO_DEFAULT, STOCK_DEFAULT, " +
					"PRECIO_COSTO_S_IMPUESTOS, PRECIO_COSTO_C_DESC_Y_REC, PRECIO_LISTA_BASE, MONTO_IMPUESTO_INTERNO, UNIDAD_VENTA, SUBUNIDADES_POR_UN_VENTA, " +
					"UNIDAD_MINIMA_VENTA, STOCK_MINIMO, STOCK_MAXIMO, PORCENTAJE_MAXIMO_DESCUENTO, PORCENTAJE_DESCUENTO_DEFAULT, " +
					"PESO_ESTIMADO, ID_ALICUOTA, ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_ARTICULOS WHERE ACTIVO = ? AND ID_ARTICULO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				articulo.setCodigo(codigo);
				articulo.setSinonimo(resultSet.getString("SINONIMO_ARTICULO"));
				articulo.setProveedor(null);
				articulo.setMarca(null);
				articulo.setRubro(rubroLogica.getRubro(resultSet.getInt("ID_RUBRO")));
				articulo.setSubRubro(subRubroLogica.getSubRubro(resultSet.getInt("ID_SUBRUBRO")));
				articulo.setLinea(null);
				articulo.setDivision(null);
				articulo.setDescripcion(resultSet.getString("DESCRIPCION"));
				articulo.setFechaAlta(simpleDateFormat.format(resultSet.getDate("FECHA_ALTA")));
				articulo.setMarckupDefaul(resultSet.getFloat("PORCENTAJE_MARCKUP_DEFAULT"));
				articulo.setDeposito(null);
				articulo.setStockDefault(resultSet.getFloat("STOCK_DEFAULT"));
				articulo.setCostoSinImp(resultSet.getFloat("PRECIO_COSTO_S_IMPUESTOS"));
				articulo.setCostoConImp(resultSet.getFloat("PRECIO_COSTO_C_DESC_Y_REC"));
				articulo.setPrecioListaBase(resultSet.getFloat("PRECIO_LISTA_BASE"));
				articulo.setImpInterno(resultSet.getFloat("MONTO_IMPUESTO_INTERNO"));
				articulo.setUnidadVta(resultSet.getString("UNIDAD_VENTA"));
				articulo.setSubUnidadVta(resultSet.getFloat("SUBUNIDADES_POR_UN_VENTA"));
				articulo.setUnidadMinVta(resultSet.getFloat("UNIDAD_MINIMA_VENTA"));
				articulo.setStockMin(resultSet.getFloat("STOCK_MINIMO"));
				articulo.setStockMax(resultSet.getFloat("STOCK_MAXIMO"));
				articulo.setDescuentoMax(resultSet.getFloat("PORCENTAJE_MAXIMO_DESCUENTO"));
				articulo.setDescuentoDefault(resultSet.getFloat("PORCENTAJE_DESCUENTO_DEFAULT"));
				articulo.setPesoEstimado(resultSet.getFloat("PESO_ESTIMADO"));
				articulo.setImpuesto(impuestoLogica.getImpuesto(resultSet.getInt("ID_ALICUOTA")));
				articulo.setActivo(resultSet.getBoolean("ACTIVO"));

				preparedStatement.close();
				return articulo;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Articulo> selectAll(){
		try{
			Articulo articulo = null;
			ArrayList<Articulo> articulos = new ArrayList<Articulo>();

			String sql = "SELECT ID_ARTICULO, SINONIMO_ARTICULO, ID_PROVEEDOR, ID_MARCA, ID_RUBRO, ID_SUBRUBRO, ID_LINEA, ID_DIVISION, " +
					"DESCRIPCION, FECHA_ALTA, PORCENTAJE_MARCKUP_DEFAULT, ID_DEPOSITO_DEFAULT, STOCK_DEFAULT, " +
					"PRECIO_COSTO_S_IMPUESTOS, PRECIO_COSTO_C_DESC_Y_REC, PRECIO_LISTA_BASE, MONTO_IMPUESTO_INTERNO, UNIDAD_VENTA, SUBUNIDADES_POR_UN_VENTA, " +
					"UNIDAD_MINIMA_VENTA, STOCK_MINIMO, STOCK_MAXIMO, PORCENTAJE_MAXIMO_DESCUENTO, PORCENTAJE_DESCUENTO_DEFAULT, " +
					"PESO_ESTIMADO, ID_ALICUOTA, ACTIVO, FECHA_HORA_UPDATE FROM INVRIO_ARTICULOS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC LIMIT 100";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					articulo = new Articulo();
					articulo.setCodigo(Integer.parseInt(resultSet.getString("ID_ARTICULO")));
					articulo.setSinonimo(resultSet.getString("SINONIMO_ARTICULO"));
					articulo.setProveedor(null);
					articulo.setMarca(null);
					articulo.setRubro(rubroLogica.getRubro(resultSet.getInt("ID_RUBRO")));
					articulo.setSubRubro(subRubroLogica.getSubRubro(resultSet.getInt("ID_SUBRUBRO")));
					articulo.setLinea(null);
					articulo.setDivision(null);
					articulo.setDescripcion(resultSet.getString("DESCRIPCION"));
					articulo.setFechaAlta(simpleDateFormat.format(resultSet.getDate("FECHA_ALTA")));
					articulo.setMarckupDefaul(resultSet.getFloat("PORCENTAJE_MARCKUP_DEFAULT"));
					articulo.setDeposito(null);
					articulo.setStockDefault(resultSet.getFloat("STOCK_DEFAULT"));
					articulo.setCostoSinImp(resultSet.getFloat("PRECIO_COSTO_S_IMPUESTOS"));
					articulo.setCostoConImp(resultSet.getFloat("PRECIO_COSTO_C_DESC_Y_REC"));
					articulo.setPrecioListaBase(resultSet.getFloat("PRECIO_LISTA_BASE"));
					articulo.setImpInterno(resultSet.getFloat("MONTO_IMPUESTO_INTERNO"));
					articulo.setUnidadVta(resultSet.getString("UNIDAD_VENTA"));
					articulo.setSubUnidadVta(resultSet.getFloat("SUBUNIDADES_POR_UN_VENTA"));
					articulo.setUnidadMinVta(resultSet.getFloat("UNIDAD_MINIMA_VENTA"));
					articulo.setStockMin(resultSet.getFloat("STOCK_MINIMO"));
					articulo.setStockMax(resultSet.getFloat("STOCK_MAXIMO"));
					articulo.setDescuentoMax(resultSet.getFloat("PORCENTAJE_MAXIMO_DESCUENTO"));
					articulo.setDescuentoDefault(resultSet.getFloat("PORCENTAJE_DESCUENTO_DEFAULT"));
					articulo.setPesoEstimado(resultSet.getFloat("PESO_ESTIMADO"));
					articulo.setImpuesto(impuestoLogica.getImpuesto(resultSet.getInt("ID_ALICUOTA")));
					articulo.setActivo(resultSet.getBoolean("ACTIVO"));
					articulos.add(articulo);
				}

				preparedStatement.close();
				return articulos;
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

			String sql = "SELECT MAX(ID_ARTICULO) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.INVRIO_ARTICULOS WHERE ACTIVO = ?";

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
	}
}

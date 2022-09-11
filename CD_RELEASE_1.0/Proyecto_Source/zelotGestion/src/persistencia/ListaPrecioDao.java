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

import dominio.ListaPrecio;


public class ListaPrecioDao {

	private static ListaPrecioDao listaPrecioDao = null;

	public static ListaPrecioDao getInstance(){
		if(listaPrecioDao == null){
			listaPrecioDao = new ListaPrecioDao();
		}
		return listaPrecioDao;
	}

	public ListaPrecioDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(ListaPrecio listaPrecio, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO VTAS_LISTAS_PRECIOS (DESCRIPCION, PORCENTAJE_UTILIDAD, PORCENTAJE_DESC_ADIC, " +
					"PORCENTAJE_RECAR_ADIC, APLICA_ALICUOTA_IVA, ID_ALICUOTA_IVA_APLICADA, IS_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, listaPrecio.getDescripcion());
			preparedStatement.setFloat(2, listaPrecio.getUtilidad());
			preparedStatement.setFloat(3, listaPrecio.getDescuentoAdicional());
			preparedStatement.setFloat(4, listaPrecio.getRecargoAdicional());
			preparedStatement.setBoolean(5, listaPrecio.isAplicaAlicuta());
			preparedStatement.setNull(6, Types.NULL);
			preparedStatement.setBoolean(7, listaPrecio.isDefault());
			preparedStatement.setBoolean(8, true);
			preparedStatement.setString(9, fechaHoraUpdate);

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
	public int update(ListaPrecio listaPrecio, String fechaHoraUpdate){
		try{
			int roewAffected = 0;
			String sql = "UPDATE VTAS_LISTAS_PRECIOS SET DESCRIPCION = ?, PORCENTAJE_UTILIDAD  = ?, PORCENTAJE_DESC_ADIC  = ?, " +
					"PORCENTAJE_RECAR_ADIC = ?, APLICA_ALICUOTA_IVA = ?, ID_ALICUOTA_IVA_APLICADA = ?, IS_DEFAULT = ?, " +
					"ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_LISTA_PRECIOS = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, listaPrecio.getDescripcion());
			preparedStatement.setFloat(2, listaPrecio.getUtilidad());
			preparedStatement.setFloat(3, listaPrecio.getDescuentoAdicional());
			preparedStatement.setFloat(4, listaPrecio.getRecargoAdicional());
			preparedStatement.setBoolean(5, listaPrecio.isAplicaAlicuta());
			preparedStatement.setNull(6, Types.NULL);
			preparedStatement.setBoolean(7, listaPrecio.isDefault());
			preparedStatement.setBoolean(8, true);
			preparedStatement.setString(9, fechaHoraUpdate);
			preparedStatement.setInt(10, listaPrecio.getCodigoListaPrecios());

			roewAffected = preparedStatement.executeUpdate();
			//System.out.println("QUERY: " + preparedStatement);
			preparedStatement.close();
			return roewAffected;
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
			int roewAffected = 0;
			String sql = "UPDATE VTAS_LISTAS_PRECIOS SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_LISTA_PRECIOS = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setInt(3, codigo);

			//System.out.println("QUERY: " + preparedStatement);
			roewAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			return roewAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * SELECT POR CODIGO
	 */
	public ListaPrecio select(int codigo){
		try{
			ListaPrecio listaPrecio = new ListaPrecio();
			String sql = "SELECT DESCRIPCION, PORCENTAJE_UTILIDAD, PORCENTAJE_DESC_ADIC, " +
					"PORCENTAJE_RECAR_ADIC, APLICA_ALICUOTA_IVA, ID_ALICUOTA_IVA_APLICADA, IS_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM VTAS_LISTAS_PRECIOS WHERE ACTIVO = ? AND ID_LISTA_PRECIOS = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				listaPrecio.setCodigoListaPrecios(codigo);
				listaPrecio.setDescripcion(resultSet.getString("DESCRIPCION"));
				listaPrecio.setUtilidad(resultSet.getFloat("PORCENTAJE_UTILIDAD"));
				listaPrecio.setDescuentoAdicional(resultSet.getFloat("PORCENTAJE_DESC_ADIC"));
				listaPrecio.setRecargoAdicional(resultSet.getFloat("PORCENTAJE_RECAR_ADIC"));
				listaPrecio.setAplicaAlicuta(resultSet.getBoolean("APLICA_ALICUOTA_IVA"));
				listaPrecio.setDefault(resultSet.getBoolean("IS_DEFAULT"));
				listaPrecio.setActivo(resultSet.getBoolean("ACTIVO"));

				preparedStatement.close();
				return listaPrecio;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ListaPrecio> selectAll(){
		try{
			ListaPrecio listaPrecio = null;
			ArrayList<ListaPrecio> clientesTipo = new ArrayList<ListaPrecio>();

			String sql = "SELECT ID_LISTA_PRECIOS, DESCRIPCION, PORCENTAJE_UTILIDAD, PORCENTAJE_DESC_ADIC, " +
					"PORCENTAJE_RECAR_ADIC, APLICA_ALICUOTA_IVA, ID_ALICUOTA_IVA_APLICADA, IS_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM VTAS_LISTAS_PRECIOS WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			//System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					listaPrecio = new ListaPrecio();
					listaPrecio.setCodigoListaPrecios(resultSet.getInt("ID_LISTA_PRECIOS"));
					listaPrecio.setDescripcion(resultSet.getString("DESCRIPCION"));
					listaPrecio.setUtilidad(resultSet.getFloat("PORCENTAJE_UTILIDAD"));
					listaPrecio.setDescuentoAdicional(resultSet.getFloat("PORCENTAJE_DESC_ADIC"));
					listaPrecio.setRecargoAdicional(resultSet.getFloat("PORCENTAJE_RECAR_ADIC"));
					listaPrecio.setAplicaAlicuta(resultSet.getBoolean("APLICA_ALICUOTA_IVA"));
					listaPrecio.setDefault(resultSet.getBoolean("IS_DEFAULT"));
					listaPrecio.setActivo(resultSet.getBoolean("ACTIVO"));

					clientesTipo.add(listaPrecio);
				}

				preparedStatement.close();
				return clientesTipo;
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

			String sql = "SELECT MAX(ID_LISTA_PRECIOS) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.VTAS_LISTAS_PRECIOS WHERE ACTIVO = ?";

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

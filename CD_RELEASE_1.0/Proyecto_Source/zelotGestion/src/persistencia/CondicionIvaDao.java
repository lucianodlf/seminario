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

import dominio.CondicionIva;


public class CondicionIvaDao {

	private static CondicionIvaDao condicionIvaDao = null;

	public static CondicionIvaDao getInstance(){
		if(condicionIvaDao == null){
			condicionIvaDao = new CondicionIvaDao();
		}
		return condicionIvaDao;
	}

	public CondicionIvaDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(CondicionIva condicionIva, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO GRAL_CONDICIONES_IVA (DESCRIPCION, DISCRIMINA_IVA, MUESTRA_IVA, LETRA_FACTURACION_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, condicionIva.getDescripcion());
			preparedStatement.setBoolean(2, condicionIva.isDiscriminaIVA());
			preparedStatement.setBoolean(3, condicionIva.isMuestraIVA());
			preparedStatement.setString(4, condicionIva.getLetraFacturacionDefault());
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
	public int update(CondicionIva condicionIva){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_CONDICIONES_IVA SET DESCRIPCION = ?, DISCRIMINA_IVA = ?, MUESTRA_IVA = ?, LETRA_FACTURACION_DEFAULT = ? WHERE ID_CONDICION_IVA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, condicionIva.getDescripcion());
			preparedStatement.setBoolean(2, condicionIva.isDiscriminaIVA());
			preparedStatement.setBoolean(3, condicionIva.isMuestraIVA());
			preparedStatement.setString(4, condicionIva.getLetraFacturacionDefault());
			preparedStatement.setString(5, String.valueOf(condicionIva.getCodigo()));

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
	 * DELETE
	 */
	public int delete(int codigo){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_CONDICIONES_IVA SET ACTIVO = ? WHERE ID_CONDICION_IVA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, String.valueOf(codigo));

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
	public CondicionIva select(int codigo){
		try{
			CondicionIva condicionIVA = new CondicionIva();
			String sql = "SELECT ID_CONDICION_IVA, DESCRIPCION, DISCRIMINA_IVA, MUESTRA_IVA, LETRA_FACTURACION_DEFAULT, " +
					"ACTIVO, FECHA_HORA_UPDATE FROM GRAL_CONDICIONES_IVA WHERE ACTIVO = ? AND ID_CONDICION_IVA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				condicionIVA.setCodigo(codigo);
				condicionIVA.setDescripcion(resultSet.getString("DESCRIPCION"));
				condicionIVA.setDiscriminaIVA(resultSet.getBoolean("DISCRIMINA_IVA"));
				condicionIVA.setMuestraIVA(resultSet.getBoolean("MUESTRA_IVA"));
				condicionIVA.setLetraFacturacionDefault(resultSet.getString("LETRA_FACTURACION_DEFAULT"));

				preparedStatement.close();
				return condicionIVA;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<CondicionIva> selectAll(){
		try{
			CondicionIva condicionIVA = null;
			ArrayList<CondicionIva> condicionesIVA = new ArrayList<CondicionIva>();

			String sql = "SELECT ID_CONDICION_IVA, DESCRIPCION, DISCRIMINA_IVA, MUESTRA_IVA, LETRA_FACTURACION_DEFAULT, " +
			"ACTIVO, FECHA_HORA_UPDATE FROM GRAL_CONDICIONES_IVA WHERE ACTIVO = ? ORDER BY DESCRIPCION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					condicionIVA = new CondicionIva();
					condicionIVA.setCodigo(resultSet.getInt("ID_CONDICION_IVA"));
					condicionIVA.setDescripcion(resultSet.getString("DESCRIPCION"));
					condicionIVA.setDiscriminaIVA(resultSet.getBoolean("DISCRIMINA_IVA"));
					condicionIVA.setMuestraIVA(resultSet.getBoolean("MUESTRA_IVA"));
					condicionIVA.setLetraFacturacionDefault(resultSet.getString("LETRA_FACTURACION_DEFAULT"));
					condicionesIVA.add(condicionIVA);
				}

				preparedStatement.close();
				return condicionesIVA;
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

			String sql = "SELECT MAX(ID_CONDICION_IVA) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.GRAL_CONDICIONES_IVA WHERE ACTIVO = ?";

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

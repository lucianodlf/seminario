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

import logica.DocumentoTipoPersonaLogica;
import dominio.Vendedor;


public class VendedorDao {

	private static VendedorDao vendedorDao = null;
	private DocumentoTipoPersonaLogica documentoTipoPersonaLogica = DocumentoTipoPersonaLogica.getInstance();
	//private DepositoLogica depositoLogica = DepositoLogica.getInstance();
	//private VehiculoLogica vehiculoLogica = VehiculoLogica.getInstance();
	//private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static VendedorDao getInstance(){
		if(vendedorDao == null){
			vendedorDao = new VendedorDao();
		}
		return vendedorDao;
	}

	public VendedorDao() {

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Vendedor vendedor, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "INSERT INTO VTAS_VENDEDORES (NOMBRE, APELLIDO, DOCUMENTO_TIPO, DOCUMENTO_NRO, " +
					"DIR_CALLE, DIR_NRO, DIR_PISO, DIR_DPTO, TEL_FIJO, TEL_MOVIL, EMAIL, FECHA_NACIMIENTO, FECHA_ALTA, " +
					"PASSWORD, ID_DEPOSITO, ID_VEHICULO, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, vendedor.getNombre());
			preparedStatement.setString(2, vendedor.getApellido());
			preparedStatement.setInt(3, vendedor.getDocumentoTipoPersona().getCodigo());
			preparedStatement.setString(4, vendedor.getDocumentoNroPersona());
			preparedStatement.setString(5, vendedor.getDireccionCalle());
			preparedStatement.setString(6, vendedor.getDireccionNro());
			preparedStatement.setInt(7, vendedor.getDireccionPiso());
			preparedStatement.setString(8, vendedor.getDireccionDpto());
			preparedStatement.setString(9, vendedor.getTelefonoFijo());
			preparedStatement.setString(10, vendedor.getTelefonoMovil());
			preparedStatement.setString(11, vendedor.getEmail());
			preparedStatement.setString(12, vendedor.getFechaNacimiento());
			preparedStatement.setString(13, fechaHoraUpdate);
			preparedStatement.setString(14,  vendedor.getPassword());
			preparedStatement.setInt(15, 1);
			preparedStatement.setInt(16, 1);
			preparedStatement.setBoolean(17, true);
			preparedStatement.setString(18, fechaHoraUpdate);

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
	public int update(Vendedor vendedor, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE VTAS_VENDEDORES SET NOMBRE = ?, APELLIDO = ?, DOCUMENTO_TIPO = ?, DOCUMENTO_NRO = ?, " +
					"DIR_CALLE = ?, DIR_NRO = ?, DIR_PISO = ?, DIR_DPTO = ?, TEL_FIJO = ?, TEL_MOVIL = ?, EMAIL = ?, " +
					"FECHA_NACIMIENTO = ?, FECHA_ALTA = ?, PASSWORD = ?, ID_DEPOSITO = ?, ID_VEHICULO = ?, " +
					"ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_VENDEDOR = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, vendedor.getNombre());
			preparedStatement.setString(2, vendedor.getApellido());
			preparedStatement.setInt(3, vendedor.getDocumentoTipoPersona().getCodigo());
			preparedStatement.setString(4, vendedor.getDocumentoNroPersona());
			preparedStatement.setString(5, vendedor.getDireccionCalle());
			preparedStatement.setString(6, vendedor.getDireccionNro());
			preparedStatement.setInt(7, vendedor.getDireccionPiso());
			preparedStatement.setString(8, vendedor.getDireccionDpto());
			preparedStatement.setString(9, vendedor.getTelefonoFijo());
			preparedStatement.setString(10, vendedor.getTelefonoMovil());
			preparedStatement.setString(11, vendedor.getEmail());
			preparedStatement.setString(12, vendedor.getFechaNacimiento());
			preparedStatement.setString(13, fechaHoraUpdate);
			preparedStatement.setString(14, vendedor.getPassword());
			preparedStatement.setInt(15, 1);
			preparedStatement.setInt(16, 1);
			preparedStatement.setBoolean(17, true);
			preparedStatement.setString(18, fechaHoraUpdate);
			preparedStatement.setInt(19, vendedor.getCodigo());

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
	public int delete(int codigo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE VTAS_VENDEDORES SET ACTIVO = ?, FECHA_HORA_UPDATE = ?  WHERE ID_VENDEDOR = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setInt(3, codigo);

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
	public Vendedor select(int codigo){
		try{
			Vendedor vendedor = new Vendedor();
			String sql = "SELECT ID_VENDEDOR, NOMBRE, APELLIDO, DOCUMENTO_TIPO, DOCUMENTO_NRO, DIR_CALLE, DIR_NRO, DIR_PISO, DIR_DPTO, " +
					"TEL_FIJO, TEL_MOVIL, EMAIL, FECHA_NACIMIENTO, FECHA_ALTA, PASSWORD, ID_DEPOSITO, ID_VEHICULO, ACTIVO, " +
					"FECHA_HORA_UPDATE FROM VTAS_VENDEDORES WHERE ACTIVO = ? AND ID_VENDEDOR = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				vendedor.setCodigo(codigo);
				vendedor.setNombre(resultSet.getString("NOMBRE"));
				vendedor.setApellido(resultSet.getString("APELLIDO"));
				vendedor.setDocumentoTipoPersona(documentoTipoPersonaLogica.getDocumentoTipoPersona(resultSet.getInt("DOCUMENTO_TIPO")));
				vendedor.setDocumentoNroPersona(resultSet.getString("DOCUMENTO_NRO"));
				vendedor.setDireccionCalle(resultSet.getString("DIR_CALLE"));
				vendedor.setDireccionNro(resultSet.getString("DIR_NRO"));
				vendedor.setDireccionPiso(resultSet.getInt("DIR_PISO"));
				vendedor.setDireccionDpto(resultSet.getString("DIR_DPTO"));
				vendedor.setTelefonoFijo(resultSet.getString("TEL_FIJO"));
				vendedor.setTelefonoMovil(resultSet.getString("TEL_MOVIL"));
				vendedor.setEmail(resultSet.getString("EMAIL"));
				vendedor.setFechaNacimiento(resultSet.getString("FECHA_NACIMIENTO"));
				vendedor.setFechaAlta(resultSet.getString("FECHA_ALTA"));
				vendedor.setPassword(resultSet.getString("PASSWORD"));
				//vendedor.setDeposito(depositoLogica.getDeposito(Integer.parseInt(resultSet.getString("ID_DEPOSITO"))));
				//vendedor.setVehiculo(vehiculoLogica.getVehiculo(Integer.parseInt(resultSet.getString("ID_VEHICULO"))));
				vendedor.setActivo(resultSet.getBoolean("ACTIVO"));

				preparedStatement.close();
				return vendedor;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Vendedor> selectAll(){
		try{
			Vendedor vendedor = null;
			ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();

			String sql = "SELECT ID_VENDEDOR, NOMBRE, APELLIDO, DOCUMENTO_TIPO, DOCUMENTO_NRO, DIR_CALLE, DIR_NRO, DIR_PISO, DIR_DPTO, " +
					"TEL_FIJO, TEL_MOVIL, EMAIL, FECHA_NACIMIENTO, FECHA_ALTA, PASSWORD, ID_DEPOSITO, ID_VEHICULO, ACTIVO, " +
					"FECHA_HORA_UPDATE FROM VTAS_VENDEDORES WHERE ACTIVO = ? ORDER BY APELLIDO ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					vendedor = new Vendedor();
					vendedor.setCodigo(resultSet.getInt("ID_VENDEDOR"));
					vendedor.setNombre(resultSet.getString("NOMBRE"));
					vendedor.setApellido(resultSet.getString("APELLIDO"));
					vendedor.setDocumentoTipoPersona(documentoTipoPersonaLogica.getDocumentoTipoPersona(resultSet.getInt("DOCUMENTO_TIPO")));
					vendedor.setDocumentoNroPersona(resultSet.getString("DOCUMENTO_NRO"));
					vendedor.setDireccionCalle(resultSet.getString("DIR_CALLE"));
					vendedor.setDireccionNro(resultSet.getString("DIR_NRO"));
					vendedor.setDireccionPiso(resultSet.getInt("DIR_PISO"));
					vendedor.setDireccionDpto(resultSet.getString("DIR_DPTO"));
					vendedor.setTelefonoFijo(resultSet.getString("TEL_FIJO"));
					vendedor.setTelefonoMovil(resultSet.getString("TEL_MOVIL"));
					vendedor.setEmail(resultSet.getString("EMAIL"));
					vendedor.setFechaNacimiento(resultSet.getString("FECHA_NACIMIENTO"));
					vendedor.setFechaAlta(resultSet.getString("FECHA_ALTA"));
					vendedor.setPassword(resultSet.getString("PASSWORD"));
					//vendedor.setDeposito(depositoLogica.getDeposito(Integer.parseInt(resultSet.getString("ID_DEPOSITO"))));
					//vendedor.setVehiculo(vehiculoLogica.getVehiculo(Integer.parseInt(resultSet.getString("ID_VEHICULO"))));
					vendedor.setActivo(resultSet.getBoolean("ACTIVO"));
					vendedores.add(vendedor);
				}

				preparedStatement.close();
				return vendedores;
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

			String sql = "SELECT MAX(ID_VENDEDOR) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.VTAS_VENDEDORES WHERE ACTIVO = ?";

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

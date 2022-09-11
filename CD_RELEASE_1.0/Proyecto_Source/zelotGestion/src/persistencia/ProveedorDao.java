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

import logica.CondicionIvaLogica;
import logica.DocumentoTipoPersonaLogica;
import logica.LocalidadLogica;
import logica.ProvinciaLogica;
import dominio.Proveedor;

public class ProveedorDao {

	/*
	 * IMPLEMENTACION PATRON SINGLETON
	 */
	private static ProveedorDao proveedorDao = null;
	private CondicionIvaLogica condicionIvaLogica = CondicionIvaLogica.getInstance();
	private DocumentoTipoPersonaLogica documentoTipoPersonaLogica = DocumentoTipoPersonaLogica.getInstance();
	private ProvinciaLogica provinciaLogica = ProvinciaLogica.getInstance();
	private LocalidadLogica localidadLogica = LocalidadLogica.getInstance();


	public static ProveedorDao getInstance(){
		if(proveedorDao == null){
			proveedorDao = new ProveedorDao();
		}
		return proveedorDao;
	}

	private ProveedorDao(){

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Proveedor proveedor, String fechaHoraUpdate){
				try{
			int rowsAffected = 0;
			String sql = "INSERT INTO CPRAS_PROVEEDORES (ID_CONDICION_IVA , NOMBRE, APELLIDO, " +
						"DOCUMENTO_TIPO, DOCUMENTO_NRO, CUIT, DIRECCION_CALLE, DIRECCION_NUMERO, DIRECCION_PISO, DIRECCION_DPTO, TEL_FIJO, " +
						"TEL_MOVIL, EMAIL, FECHA_ALTA, RAZON_SOCIAL, NOMBRE_FANTASIA, LIMITE_CREDITO, " +
						"ID_PROVINCIA, ID_LOCALIDAD, " +
						"ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setInt(1, proveedor.getCondicionIVA().getCodigo());
			preparedStatement.setString(2, proveedor.getNombre());
			preparedStatement.setString(3, proveedor.getApellido());
			preparedStatement.setInt(4, proveedor.getDocumentoTipoPersona().getCodigo());
			preparedStatement.setString(5, proveedor.getDocumentoNroPersona());
			preparedStatement.setString(6, proveedor.getNumeroCuit());
			preparedStatement.setString(7, proveedor.getDireccionCalle());
			preparedStatement.setString(8, proveedor.getDireccionNro());
			preparedStatement.setInt(9, proveedor.getDireccionPiso());
			preparedStatement.setString(10, proveedor.getDireccionDpto());
			preparedStatement.setString(11, proveedor.getTelefonoFijo());
			preparedStatement.setString(12, proveedor.getTelefonoMovil());
			preparedStatement.setString(13, proveedor.getEmail());
			preparedStatement.setString(14, proveedor.getFechaAlta());
			preparedStatement.setString(15, proveedor.getRazonSocial());
			preparedStatement.setString(16, proveedor.getNombreFantasia());
			preparedStatement.setFloat(17, proveedor.getLimiteCredito());
			preparedStatement.setInt(18, proveedor.getProvincia().getCodigo());
			preparedStatement.setInt(19, proveedor.getLocalidad().getCodigo());
			preparedStatement.setBoolean(20, proveedor.isActivo());
			preparedStatement.setString(21, fechaHoraUpdate);

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
	 * UPDATE
	 */
	public int update(Proveedor proveedor, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE CPRAS_PROVEEDORES SET ID_CONDICION_IVA = ?, NOMBRE = ?, APELLIDO = ?, " +
						"DOCUMENTO_TIPO = ?, DOCUMENTO_NRO = ?, CUIT = ?, DIRECCION_CALLE = ?, DIRECCION_NUMERO = ?, DIRECCION_PISO = ?, DIRECCION_DPTO = ?, TEL_FIJO = ?, " +
						"TEL_MOVIL = ?, EMAIL = ?, FECHA_ALTA = ?, RAZON_SOCIAL = ?, NOMBRE_FANTASIA = ?, LIMITE_CREDITO = ?, " +
						"ID_PROVINCIA = ?, ID_LOCALIDAD = ?, " +
						"ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_PROVEEDOR = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setInt(1, proveedor.getCondicionIVA().getCodigo());
			preparedStatement.setString(2, proveedor.getNombre());
			preparedStatement.setString(3, proveedor.getApellido());
			preparedStatement.setInt(4, proveedor.getDocumentoTipoPersona().getCodigo());
			preparedStatement.setString(5, proveedor.getDocumentoNroPersona());
			preparedStatement.setString(6, proveedor.getNumeroCuit());
			preparedStatement.setString(7, proveedor.getDireccionCalle());
			preparedStatement.setString(8, proveedor.getDireccionNro());
			preparedStatement.setInt(9, proveedor.getDireccionPiso());
			preparedStatement.setString(10, proveedor.getDireccionDpto());
			preparedStatement.setString(11, proveedor.getTelefonoFijo());
			preparedStatement.setString(12, proveedor.getTelefonoMovil());
			preparedStatement.setString(13, proveedor.getEmail());
			preparedStatement.setString(14, proveedor.getFechaAlta());
			preparedStatement.setString(15, proveedor.getRazonSocial());
			preparedStatement.setString(16, proveedor.getNombreFantasia());
			preparedStatement.setFloat(17, proveedor.getLimiteCredito());
			preparedStatement.setInt(18, proveedor.getProvincia().getCodigo());
			preparedStatement.setInt(19, proveedor.getLocalidad().getCodigo());
			preparedStatement.setBoolean(20, proveedor.isActivo());
			preparedStatement.setString(21, fechaHoraUpdate);
			preparedStatement.setInt(22, proveedor.getCodigo());


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
	public int delete(int codigo, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			//String sql = "DELETE FROM VTAS_CLIENTES WHERE ID_CLIENTE = ?";
			String sql = "UPDATE CPRAS_PROVEEDORES SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_PROVEEDOR = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, String.valueOf(codigo));

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
	public Proveedor select(int codigo){
		try{
			Proveedor proveedor = new Proveedor();

			String sql = "SELECT ID_PROVEEDOR, ID_CONDICION_IVA, NOMBRE, APELLIDO, " +
						"DOCUMENTO_TIPO, DOCUMENTO_NRO, CUIT, DIRECCION_CALLE, DIRECCION_NUMERO, DIRECCION_PISO, DIRECCION_DPTO, TEL_FIJO, " +
						"TEL_MOVIL, EMAIL, FECHA_ALTA, RAZON_SOCIAL, NOMBRE_FANTASIA, LIMITE_CREDITO, " +
						"ID_PROVINCIA, ID_LOCALIDAD, " +
						"ACTIVO FROM CPRAS_PROVEEDORES WHERE ACTIVO = ? AND ID_PROVEEDOR = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();


			if(resultSet.next()){
				proveedor.setCodigo(codigo);
				proveedor.setCondicionIVA(condicionIvaLogica.getCondicionIva(resultSet.getInt("ID_CONDICION_IVA")));
				proveedor.setNombre(resultSet.getString("NOMBRE"));
				proveedor.setApellido(resultSet.getString("APELLIDO"));
				proveedor.setDocumentoTipoPersona(documentoTipoPersonaLogica.getDocumentoTipoPersona(resultSet.getInt("DOCUMENTO_TIPO")));
				proveedor.setDocumentoNroPersona(resultSet.getString("DOCUMENTO_NRO"));
				proveedor.setNumeroCuit(resultSet.getString("CUIT"));
				proveedor.setDireccionCalle(resultSet.getString("DIRECCION_CALLE"));
				proveedor.setDireccionNro(resultSet.getString("DIRECCION_NUMERO"));
				proveedor.setDireccionPiso(resultSet.getInt("DIRECCION_PISO"));
				proveedor.setDireccionDpto(resultSet.getString("DIRECCION_DPTO"));
				proveedor.setTelefonoFijo(resultSet.getString("TEL_FIJO"));
				proveedor.setTelefonoMovil(resultSet.getString("TEL_MOVIL"));
				proveedor.setEmail(resultSet.getString("EMAIL"));
				proveedor.setFechaAlta(resultSet.getString("FECHA_ALTA"));
				proveedor.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
				proveedor.setNombreFantasia(resultSet.getString("NOMBRE_FANTASIA"));
				proveedor.setLimiteCredito(resultSet.getFloat("LIMITE_CREDITO"));
				proveedor.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
				proveedor.setLocalidad(localidadLogica.getLocalidad(resultSet.getInt("ID_LOCALIDAD")));
				proveedor.setActivo(resultSet.getBoolean("ACTIVO"));

				preparedStatement.close();
				return proveedor;

			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Proveedor> selectAll(){
		try{
			Proveedor proveedor = null;
			ArrayList<Proveedor> proveedors = new ArrayList<Proveedor>();
			String sql = "SELECT ID_PROVEEDOR, ID_CONDICION_IVA, NOMBRE, APELLIDO, " +
						"DOCUMENTO_TIPO, DOCUMENTO_NRO, CUIT, DIRECCION_CALLE, DIRECCION_NUMERO, DIRECCION_PISO, DIRECCION_DPTO, TEL_FIJO, " +
						"TEL_MOVIL, EMAIL, FECHA_ALTA, RAZON_SOCIAL, NOMBRE_FANTASIA, LIMITE_CREDITO, " +
						"ID_PROVINCIA, ID_LOCALIDAD, " +
						"ACTIVO FROM CPRAS_PROVEEDORES WHERE ACTIVO = ? ORDER BY APELLIDO ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					proveedor = new Proveedor();
					proveedor.setCodigo(resultSet.getInt("ID_PROVEEDOR"));
					proveedor.setCondicionIVA(condicionIvaLogica.getCondicionIva(resultSet.getInt("ID_CONDICION_IVA")));
					proveedor.setNombre(resultSet.getString("NOMBRE"));
					proveedor.setApellido(resultSet.getString("APELLIDO"));
					proveedor.setDocumentoTipoPersona(documentoTipoPersonaLogica.getDocumentoTipoPersona(resultSet.getInt("DOCUMENTO_TIPO")));
					proveedor.setDocumentoNroPersona(resultSet.getString("DOCUMENTO_NRO"));
					proveedor.setNumeroCuit(resultSet.getString("CUIT"));
					proveedor.setDireccionCalle(resultSet.getString("DIRECCION_CALLE"));
					proveedor.setDireccionNro(resultSet.getString("DIRECCION_NUMERO"));
					proveedor.setDireccionPiso(resultSet.getInt("DIRECCION_PISO"));
					proveedor.setDireccionDpto(resultSet.getString("DIRECCION_DPTO"));
					proveedor.setTelefonoFijo(resultSet.getString("TEL_FIJO"));
					proveedor.setTelefonoMovil(resultSet.getString("TEL_MOVIL"));
					proveedor.setEmail(resultSet.getString("EMAIL"));
					proveedor.setFechaAlta(resultSet.getString("FECHA_ALTA"));
					proveedor.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
					proveedor.setNombreFantasia(resultSet.getString("NOMBRE_FANTASIA"));
					proveedor.setLimiteCredito(resultSet.getFloat("LIMITE_CREDITO"));
					proveedor.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
					proveedor.setLocalidad(localidadLogica.getLocalidad(resultSet.getInt("ID_LOCALIDAD")));
					proveedor.setActivo(resultSet.getBoolean("ACTIVO"));
					proveedors.add(proveedor);
				}
				preparedStatement.close();
				return proveedors;
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

			String sql = "SELECT MAX(ID_PROVEEDOR) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.CPRAS_PROVEEDORES WHERE ACTIVO = ?";

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

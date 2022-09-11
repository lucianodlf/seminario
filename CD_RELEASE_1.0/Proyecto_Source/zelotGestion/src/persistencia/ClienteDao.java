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

import logica.ClienteCategoriaLogica;
import logica.ClienteEstadoLogica;
import logica.ClienteGrupoLogica;
import logica.ClienteTipoLogica;
import logica.CondicionIvaLogica;
import logica.CondicionVentaLogica;
import logica.DocumentoTipoPersonaLogica;
import logica.ListaPrecioLogica;
import logica.LocalidadLogica;
import logica.ProvinciaLogica;
import logica.VendedorLogica;
import dominio.Cliente;

public class ClienteDao {

	/*
	 * IMPLEMENTACION PATRON SINGLETON
	 */
	private static ClienteDao clienteDao = null;
	private CondicionIvaLogica condicionIvaLogica = CondicionIvaLogica.getInstance();
	private CondicionVentaLogica condicionVentaLogica = CondicionVentaLogica.getInstance();
	private DocumentoTipoPersonaLogica documentoTipoPersonaLogica = DocumentoTipoPersonaLogica.getInstance();
	private ClienteTipoLogica clienteTipoLogica = ClienteTipoLogica.getInstance();
	private ClienteEstadoLogica clienteEstadoLogica = ClienteEstadoLogica.getInstance();
	private ClienteCategoriaLogica clienteCategoriaLogica = ClienteCategoriaLogica.getInstance();
	private ClienteGrupoLogica clienteGrupoLogica = ClienteGrupoLogica.getInstance();
	private ListaPrecioLogica listaPreciosLogica = ListaPrecioLogica.getInstance();
	private VendedorLogica vendedorLogica = VendedorLogica.getInstance();
	private ProvinciaLogica provinciaLogica = ProvinciaLogica.getInstance();
	private LocalidadLogica localidadLogica = LocalidadLogica.getInstance();




	public static ClienteDao getInstance(){
		if(clienteDao == null){
			clienteDao = new ClienteDao();
		}
		return clienteDao;
	}

	private ClienteDao(){

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int create(Cliente cliente, String fechaHoraUpdate){
				try{
			int rowsAffected = 0;
			String sql = "INSERT INTO VTAS_CLIENTES (ID_CONDICION_IVA, ID_CONDICION_VTA_DEFAULT, NOMBRE, APELLIDO, " +
						"DOCUMENTO_TIPO, DOCUMENTO_NRO, CUIT, DIRECCION_CALLE, DIRECCION_NUMERO, DIRECCION_PISO, DIRECCION_DPTO, TEL_FIJO, " +
						"TEL_MOVIL, EMAIL, CTA_CTE_ACTIVA, FECHA_NACIMIENTO, FECHA_ALTA, RAZON_SOCIAL, NOMBRE_FANTASIA, LIMITE_CREDITO, ID_CLIENTE_TIPO," +
						"ID_CLIENTE_ESTADO, ID_CLIENTE_CATEGORIA, ID_CLIENTE_GURPO, ID_LISTA_PRECIOS, ID_VENDEDOR, ID_PROVINCIA, ID_LOCALIDAD, PORCENTAJE_DESC_DEFAULT," +
						"PORCENTAJE_DESC_MAX, ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setInt(1, cliente.getCondicionIVA().getCodigo());
			preparedStatement.setInt(2, cliente.getCondicionVentaDefault().getCodigoCondicionVenta());
			preparedStatement.setString(3, cliente.getNombre());
			preparedStatement.setString(4, cliente.getApellido());
			preparedStatement.setInt(5, cliente.getDocumentoTipoPersona().getCodigo());
			preparedStatement.setString(6, cliente.getDocumentoNroPersona());
			preparedStatement.setString(7, cliente.getNumeroCuit());
			preparedStatement.setString(8, cliente.getDireccionCalle());
			preparedStatement.setString(9, cliente.getDireccionNro());
			preparedStatement.setInt(10, cliente.getDireccionPiso());
			preparedStatement.setString(11, cliente.getDireccionDpto());
			preparedStatement.setString(12, cliente.getTelefonoFijo());
			preparedStatement.setString(13, cliente.getTelefonoMovil());
			preparedStatement.setString(14, cliente.getEmail());
			preparedStatement.setBoolean(15,cliente.isCtaCteActiva());
			preparedStatement.setString(16, cliente.getFechaNacimiento());
			preparedStatement.setString(17, cliente.getFechaAlta());
			preparedStatement.setString(18, cliente.getRazonSocial());
			preparedStatement.setString(19, cliente.getNombreFantasia());
			preparedStatement.setFloat(20, cliente.getLimiteCredito());
			preparedStatement.setInt(21, 1);
			preparedStatement.setInt(22, 1);
			preparedStatement.setInt(23, cliente.getClienteCategoria().getCodigoCategoriaCliente());
			preparedStatement.setInt(24, 1);
			preparedStatement.setString(25, String.valueOf(cliente.getListaPrecios().getCodigoListaPrecios()));
			preparedStatement.setString(26, String.valueOf(cliente.getVendedor().getCodigo()));
			preparedStatement.setString(27, String.valueOf(cliente.getProvincia().getCodigo()));
			preparedStatement.setString(28, String.valueOf(cliente.getLocalidad().getCodigo()));
			preparedStatement.setString(29, String.valueOf(cliente.getPorcentajeDescuentoDefault()));
			preparedStatement.setString(30, String.valueOf(cliente.getPorcentajeDescuentoMaximo()));
			preparedStatement.setBoolean(31, cliente.isActivo());
			preparedStatement.setString(32, fechaHoraUpdate);

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
	public int update(Cliente cliente, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE VTAS_CLIENTES SET ID_CONDICION_IVA = ?, ID_CONDICION_VTA_DEFAULT = ?, NOMBRE = ?, APELLIDO = ?, " +
						"DOCUMENTO_TIPO = ?, DOCUMENTO_NRO = ?, CUIT = ?, DIRECCION_CALLE = ?, DIRECCION_NUMERO = ?, DIRECCION_PISO = ?, DIRECCION_DPTO = ?, TEL_FIJO = ?, " +
						"TEL_MOVIL = ?, EMAIL = ?, CTA_CTE_ACTIVA = ?, FECHA_NACIMIENTO = ?, FECHA_ALTA = ?, RAZON_SOCIAL = ?, NOMBRE_FANTASIA = ?, LIMITE_CREDITO = ?, ID_CLIENTE_TIPO = ?, " +
						"ID_CLIENTE_ESTADO = ?, ID_CLIENTE_CATEGORIA = ?, ID_CLIENTE_GURPO = ?, ID_LISTA_PRECIOS = ?, ID_VENDEDOR = ?, ID_PROVINCIA = ?, ID_LOCALIDAD = ?, PORCENTAJE_DESC_DEFAULT = ?, " +
						"PORCENTAJE_DESC_MAX = ?, ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_CLIENTE = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setInt(1, cliente.getCondicionIVA().getCodigo());
			preparedStatement.setInt(2, cliente.getCondicionVentaDefault().getCodigoCondicionVenta());
			preparedStatement.setString(3, cliente.getNombre());
			preparedStatement.setString(4, cliente.getApellido());
			preparedStatement.setInt(5, cliente.getDocumentoTipoPersona().getCodigo());
			preparedStatement.setString(6, cliente.getDocumentoNroPersona());
			preparedStatement.setString(7, cliente.getNumeroCuit());
			preparedStatement.setString(8, cliente.getDireccionCalle());
			preparedStatement.setString(9, cliente.getDireccionNro());
			preparedStatement.setInt(10, cliente.getDireccionPiso());
			preparedStatement.setString(11, cliente.getDireccionDpto());
			preparedStatement.setString(12, cliente.getTelefonoFijo());
			preparedStatement.setString(13, cliente.getTelefonoMovil());
			preparedStatement.setString(14, cliente.getEmail());
			preparedStatement.setBoolean(15,cliente.isCtaCteActiva());
			preparedStatement.setString(16, cliente.getFechaNacimiento());
			preparedStatement.setString(17, cliente.getFechaAlta());
			preparedStatement.setString(18, cliente.getRazonSocial());
			preparedStatement.setString(19, cliente.getNombreFantasia());
			preparedStatement.setFloat(20, cliente.getLimiteCredito());
			preparedStatement.setInt(21, 1);
			preparedStatement.setInt(22, 1);
			preparedStatement.setInt(23, cliente.getClienteCategoria().getCodigoCategoriaCliente());
			preparedStatement.setInt(24, 1);
			preparedStatement.setString(25, String.valueOf(cliente.getListaPrecios().getCodigoListaPrecios()));
			preparedStatement.setString(26, String.valueOf(cliente.getVendedor().getCodigo()));
			preparedStatement.setString(27, String.valueOf(cliente.getProvincia().getCodigo()));
			preparedStatement.setString(28, String.valueOf(cliente.getLocalidad().getCodigo()));
			preparedStatement.setString(29, String.valueOf(cliente.getPorcentajeDescuentoDefault()));
			preparedStatement.setString(30, String.valueOf(cliente.getPorcentajeDescuentoMaximo()));
			preparedStatement.setBoolean(31, cliente.isActivo());
			preparedStatement.setString(32, fechaHoraUpdate);
			preparedStatement.setInt(33, cliente.getCodigo());


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
			String sql = "UPDATE VTAS_CLIENTES SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_CLIENTE = ?";

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
	public Cliente select(int codigo){
		try{
			Cliente cliente = new Cliente();

			String sql = "SELECT ID_CLIENTE, ID_CONDICION_IVA, ID_CONDICION_VTA_DEFAULT, NOMBRE, APELLIDO, " +
						"DOCUMENTO_TIPO, DOCUMENTO_NRO, CUIT, DIRECCION_CALLE, DIRECCION_NUMERO, DIRECCION_PISO, DIRECCION_DPTO, TEL_FIJO, " +
						"TEL_MOVIL, EMAIL, CTA_CTE_ACTIVA, FECHA_NACIMIENTO, FECHA_ALTA, RAZON_SOCIAL, NOMBRE_FANTASIA, LIMITE_CREDITO, ID_CLIENTE_TIPO," +
						"ID_CLIENTE_ESTADO, ID_CLIENTE_CATEGORIA, ID_CLIENTE_GURPO, ID_LISTA_PRECIOS, ID_VENDEDOR, ID_PROVINCIA, ID_LOCALIDAD, PORCENTAJE_DESC_DEFAULT," +
						"PORCENTAJE_DESC_MAX, ACTIVO FROM VTAS_CLIENTES WHERE ACTIVO = ? AND ID_CLIENTE = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			preparedStatement.setString(2, String.valueOf(codigo));
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();


			if(resultSet.next()){
				cliente.setCodigo(codigo);
				cliente.setCondicionIVA(condicionIvaLogica.getCondicionIva(resultSet.getInt("ID_CONDICION_IVA")));
				cliente.setCondicionVentaDefault(condicionVentaLogica.getCondicionVenta(resultSet.getInt("ID_CONDICION_VTA_DEFAULT")));
				cliente.setNombre(resultSet.getString("NOMBRE"));
				cliente.setApellido(resultSet.getString("APELLIDO"));
				cliente.setDocumentoTipoPersona(documentoTipoPersonaLogica.getDocumentoTipoPersona(resultSet.getInt("DOCUMENTO_TIPO")));
				cliente.setDocumentoNroPersona(resultSet.getString("DOCUMENTO_NRO"));
				cliente.setNumeroCuit(resultSet.getString("CUIT"));
				cliente.setDireccionCalle(resultSet.getString("DIRECCION_CALLE"));
				cliente.setDireccionNro(resultSet.getString("DIRECCION_NUMERO"));
				cliente.setDireccionPiso(resultSet.getInt("DIRECCION_PISO"));
				cliente.setDireccionDpto(resultSet.getString("DIRECCION_DPTO"));
				cliente.setTelefonoFijo(resultSet.getString("TEL_FIJO"));
				cliente.setTelefonoMovil(resultSet.getString("TEL_MOVIL"));
				cliente.setEmail(resultSet.getString("EMAIL"));
				cliente.setCtaCteActiva(resultSet.getBoolean("CTA_CTE_ACTIVA"));
				cliente.setFechaNacimiento(resultSet.getString("FECHA_NACIMIENTO"));
				cliente.setFechaAlta(resultSet.getString("FECHA_ALTA"));
				cliente.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
				cliente.setNombreFantasia(resultSet.getString("NOMBRE_FANTASIA"));
				cliente.setLimiteCredito(resultSet.getFloat("LIMITE_CREDITO"));
				cliente.setClienteTipo(clienteTipoLogica.getClienteTipo(resultSet.getInt("ID_CLIENTE_TIPO")));
				cliente.setClienteEstado(clienteEstadoLogica.getClienteEstado(resultSet.getInt("ID_CLIENTE_ESTADO")));
				cliente.setClienteCategoria(clienteCategoriaLogica.getClienteCategoria(resultSet.getInt("ID_CLIENTE_CATEGORIA")));
				cliente.setClienteGrupo(clienteGrupoLogica.getClienteGrupo(resultSet.getInt("ID_CLIENTE_GURPO")));
				cliente.setListaPrecios(listaPreciosLogica.getListaPrecio(resultSet.getInt("ID_LISTA_PRECIOS")));
				cliente.setVendedor(vendedorLogica.getVendedor(resultSet.getInt("ID_VENDEDOR")));
				cliente.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
				cliente.setLocalidad(localidadLogica.getLocalidad(resultSet.getInt("ID_LOCALIDAD")));
				cliente.setPorcentajeDescuentoDefault(resultSet.getFloat("PORCENTAJE_DESC_DEFAULT"));
				cliente.setPorcentajeDescuentoMaximo(resultSet.getFloat("PORCENTAJE_DESC_MAX"));
				cliente.setActivo(resultSet.getBoolean("ACTIVO"));

				preparedStatement.close();
				return cliente;

			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Cliente> selectAll(){
		try{
			Cliente cliente = null;
			ArrayList<Cliente> clientes = new ArrayList<Cliente>();
			String sql = "SELECT ID_CLIENTE, ID_CONDICION_IVA, ID_CONDICION_VTA_DEFAULT, NOMBRE, APELLIDO, " +
						"DOCUMENTO_TIPO, DOCUMENTO_NRO, CUIT, DIRECCION_CALLE, DIRECCION_NUMERO, DIRECCION_PISO, DIRECCION_DPTO, TEL_FIJO, " +
						"TEL_MOVIL, EMAIL, CTA_CTE_ACTIVA, FECHA_NACIMIENTO, FECHA_ALTA, RAZON_SOCIAL, NOMBRE_FANTASIA, LIMITE_CREDITO, ID_CLIENTE_TIPO," +
						"ID_CLIENTE_ESTADO, ID_CLIENTE_CATEGORIA, ID_CLIENTE_GURPO, ID_LISTA_PRECIOS, ID_VENDEDOR, ID_PROVINCIA, ID_LOCALIDAD, PORCENTAJE_DESC_DEFAULT," +
						"PORCENTAJE_DESC_MAX, ACTIVO FROM VTAS_CLIENTES WHERE ACTIVO = ? ORDER BY APELLIDO ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, String.valueOf(1));
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					cliente = new Cliente();
					cliente.setCodigo(resultSet.getInt("ID_CLIENTE"));
					cliente.setCondicionIVA(condicionIvaLogica.getCondicionIva(resultSet.getInt("ID_CONDICION_IVA")));
					cliente.setCondicionVentaDefault(condicionVentaLogica.getCondicionVenta(resultSet.getInt("ID_CONDICION_VTA_DEFAULT")));
					cliente.setNombre(resultSet.getString("NOMBRE"));
					cliente.setApellido(resultSet.getString("APELLIDO"));
					cliente.setDocumentoTipoPersona(documentoTipoPersonaLogica.getDocumentoTipoPersona(resultSet.getInt("DOCUMENTO_TIPO")));
					cliente.setDocumentoNroPersona(resultSet.getString("DOCUMENTO_NRO"));
					cliente.setNumeroCuit(resultSet.getString("CUIT"));
					cliente.setDireccionCalle(resultSet.getString("DIRECCION_CALLE"));
					cliente.setDireccionNro(resultSet.getString("DIRECCION_NUMERO"));
					cliente.setDireccionPiso(resultSet.getInt("DIRECCION_PISO"));
					cliente.setDireccionDpto(resultSet.getString("DIRECCION_DPTO"));
					cliente.setTelefonoFijo(resultSet.getString("TEL_FIJO"));
					cliente.setTelefonoMovil(resultSet.getString("TEL_MOVIL"));
					cliente.setEmail(resultSet.getString("EMAIL"));
					cliente.setCtaCteActiva(resultSet.getBoolean("CTA_CTE_ACTIVA"));
					cliente.setFechaNacimiento(resultSet.getString("FECHA_NACIMIENTO"));
					cliente.setFechaAlta(resultSet.getString("FECHA_ALTA"));
					cliente.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
					cliente.setNombreFantasia(resultSet.getString("NOMBRE_FANTASIA"));
					cliente.setLimiteCredito(resultSet.getFloat("LIMITE_CREDITO"));
					cliente.setClienteTipo(clienteTipoLogica.getClienteTipo(resultSet.getInt("ID_CLIENTE_TIPO")));
					cliente.setClienteEstado(clienteEstadoLogica.getClienteEstado(resultSet.getInt("ID_CLIENTE_ESTADO")));
					cliente.setClienteCategoria(clienteCategoriaLogica.getClienteCategoria(resultSet.getInt("ID_CLIENTE_CATEGORIA")));
					cliente.setClienteGrupo(clienteGrupoLogica.getClienteGrupo(resultSet.getInt("ID_CLIENTE_GURPO")));
					cliente.setListaPrecios(listaPreciosLogica.getListaPrecio(resultSet.getInt("ID_LISTA_PRECIOS")));
					cliente.setVendedor(vendedorLogica.getVendedor(resultSet.getInt("ID_VENDEDOR")));
					cliente.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
					cliente.setLocalidad(localidadLogica.getLocalidad(resultSet.getInt("ID_LOCALIDAD")));
					cliente.setPorcentajeDescuentoDefault(resultSet.getFloat("PORCENTAJE_DESC_DEFAULT"));
					cliente.setPorcentajeDescuentoMaximo(resultSet.getFloat("PORCENTAJE_DESC_MAX"));
					cliente.setActivo(resultSet.getBoolean("ACTIVO"));
					clientes.add(cliente);
				}
				preparedStatement.close();
				return clientes;
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

			String sql = "SELECT MAX(ID_CLIENTE) AS ULTIMOCODIGO FROM ZELOT_GESTION_DB.VTAS_CLIENTES WHERE ACTIVO = ?";

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
	}

}

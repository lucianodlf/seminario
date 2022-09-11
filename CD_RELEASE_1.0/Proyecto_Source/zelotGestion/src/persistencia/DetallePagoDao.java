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
import java.sql.SQLException;
import java.sql.Types;

import dominio.DetallePago;

public class DetallePagoDao {

	/*
	 * IMPLEMENTACION PATRON SINGLETON
	 */
	private static DetallePagoDao detallePagoDao = null;


	public static DetallePagoDao getInstance(){
		if(detallePagoDao == null){
			detallePagoDao = new DetallePagoDao();
		}
		return detallePagoDao;
	}

	private DetallePagoDao(){

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int crateDetallePago(DetallePago detallePago, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sqlCabecera = "INSERT INTO GRAL_DETALLE_PAGOS (NRO_COMPROBANTE, NRO_PTO_VENTA, LETRA_COMPROBANTE, TIPO_COMPROBANTE, " +
						"ID_MEDIO_DE_PAGO, MED_PAG_ID_BANCO, MED_PAG_ID_TARJETA, MED_PAG_BANCO_SUCURSAL, MED_PAG_NRO_CUENTA, MED_PAG_NRO_COMPROBANTE, " +
						"MED_PAG_NRO_SERIE, MED_PAG_TITULAR_NOMBRE, MED_PAG_TITULAR_NRO_DOC, MED_PAG_FECHA_EMICION, MED_PAG_FECHA_VENCIMIENTO, " +
						"MED_PAG_CODIGO_LOCALIDAD, MED_PAG_COTIZACION, MED_PAG_TARJETA_NRO, MED_PAG_IMPORTE, ACTIVO, FECHA_HORA_UPDATE) " +
						"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlCabecera);

			if(detallePago.getFactura() != null){
				preparedStatement.setString(1, detallePago.getFactura().getNroFactura());
				preparedStatement.setString(2, detallePago.getFactura().getNroPtoVenta());
				preparedStatement.setString(3, detallePago.getFactura().getLetraFactura());
				preparedStatement.setString(4, detallePago.getFactura().getTipoComprobante());
			}else if(detallePago.getCobranza() != null){
				preparedStatement.setString(1, detallePago.getCobranza().getNroComprobanteRecibo());
				preparedStatement.setString(2, detallePago.getCobranza().getNroPtoVtaComprobanteRecibo());
				preparedStatement.setString(3, detallePago.getCobranza().getLetraComprobanteRecibo());
				preparedStatement.setString(4, detallePago.getCobranza().getTipoComprobanteRecibo());
			}
			preparedStatement.setInt(5, detallePago.getMedioPago().getCodigo());

			if(detallePago.getBanco() != null){
				preparedStatement.setInt(6, detallePago.getBanco().getCodigo());
			}else{
				preparedStatement.setNull(6, Types.NULL);
			}

			if(detallePago.getTarjeta() != null){
				preparedStatement.setInt(7, detallePago.getTarjeta().getCodigo());
			}else{
				preparedStatement.setNull(7, Types.NULL);
			}


			preparedStatement.setString(8, detallePago.getSucursalBanco());
			preparedStatement.setString(9, detallePago.getNroCuenta());
			preparedStatement.setString(10, detallePago.getNroComprobante());
			preparedStatement.setString(11, detallePago.getNroSerie());
			preparedStatement.setString(12, detallePago.getTitularNombre());
			preparedStatement.setString(13, detallePago.getTitularNroDoc());

			if(!detallePago.getFechaEmicion().equals("") || !detallePago.getFechaEmicion().isEmpty()){
				preparedStatement.setString(14, detallePago.getFechaEmicion());
			}else{
				preparedStatement.setNull(14, Types.NULL);
			}

			if(!detallePago.getFechaVencimiento().equals("") || !detallePago.getFechaVencimiento().isEmpty()){
				preparedStatement.setString(15, detallePago.getFechaVencimiento());
			}else{
				preparedStatement.setNull(15, Types.NULL);
			}

			preparedStatement.setNull(16, Types.NULL);
			preparedStatement.setFloat(17, detallePago.getCotizacion());
			preparedStatement.setString(18, detallePago.getNroTarjeta());
			preparedStatement.setFloat(19, detallePago.getImporte());
			preparedStatement.setBoolean(20, true);
			preparedStatement.setString(21, fechaHoraUpdate);


			System.out.println("QUERY: " + preparedStatement);
			rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) preparedStatement.cancel();
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	/*public int crateItemDetallePago(DetallePago detallePago, ItemDetallePago itemDetallePago, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sqlDetalle = "INSERT INTO VTAS_COMPROBANTES_DETALLE(CAB_COMP_NRO_COMPROBANTE, CAB_COMP_NRO_PUNTO_VENTA," +
					" ID_ARTICULO, ID_COMBO, CANTIDAD, PRECIO_UNITARIO_VENTA, PRECIO_UNITARIO_LISTA, PORCENTAJE_DESCUENTO_MANUAL," +
					" PORCENTAJE_DESCUENTO_ARTICULO, PORCENTAJE_DESCUENTO_CLIENTE, MONTO_IMPUESTO_INT_APLICADO, PESO_ARTICULO, ES_BONIFICADO," +
					" ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlDetalle);

			preparedStatement.setString(1, detallePago.getNroDetallePago());
			preparedStatement.setString(2, "0001");
			preparedStatement.setInt(3, itemDetallePago.getArticulo().getCodigo());
			preparedStatement.setNull(4, Types.NULL);
			preparedStatement.setFloat(5, itemDetallePago.getCantidad());
			preparedStatement.setFloat(6, itemDetallePago.getPrecioUnitario());
			preparedStatement.setFloat(7, itemDetallePago.getPrecioUnitario());
			preparedStatement.setFloat(8, itemDetallePago.getDescManual());
			preparedStatement.setFloat(9, itemDetallePago.getArticulo().getDescuentoDefault());
			preparedStatement.setFloat(10, new Float(0));
			preparedStatement.setFloat(11, itemDetallePago.getArticulo().getImpInterno());
			preparedStatement.setFloat(12, itemDetallePago.getArticulo().getPesoEstimado());
			preparedStatement.setBoolean(13, false);
			preparedStatement.setBoolean(14, true);
			preparedStatement.setString(15, fechaHoraUpdate);



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
	 * UPDATE
	 */
	/*public int updateCorrelativo(DetallePago detallePago, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE GRAL_NROS_CORRELATIVOS_COMPROBANTES SET NRO_COMPROBANTE = ?, FECHA_HORA_UPDATE = ?" +
					" WHERE ID_TIPO_COMPROBANTE = ? AND LETRA_COMPROBANTE = ? AND NRO_PTO_VENTA = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setString(1, detallePago.);
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
	}*/
	/*
	 * DELETE
	 */
	public int deleteDetallePago(DetallePago detallePago){
		try{
			int rowsAffected = 0;
			String sql = "DELETE FROM GRAL_DETALE_PAGOS WHERE NRO_COMPROBANTE = ? AND NRO_PTO_VENTA = ? AND LETRA_COMPROBANTE = ? AND ID_TIPO_COMPROBANTE = ?";
			//String sql = "UPDATE VTAS_CLIENTES SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_CLIENTE = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			if(detallePago.getFactura() != null){
				preparedStatement.setString(1, detallePago.getFactura().getNroFactura());
				preparedStatement.setString(2, detallePago.getFactura().getNroPtoVenta());
				preparedStatement.setString(3, detallePago.getFactura().getLetraFactura());
				preparedStatement.setString(4, detallePago.getFactura().getTipoComprobante());
			}else if(detallePago.getCobranza() != null){
				preparedStatement.setString(1, detallePago.getCobranza().getNroComprobanteRecibo());
				preparedStatement.setString(2, detallePago.getCobranza().getNroPtoVtaComprobanteRecibo());
				preparedStatement.setString(3, detallePago.getCobranza().getLetraComprobanteRecibo());
				preparedStatement.setString(4, detallePago.getCobranza().getTipoComprobanteRecibo());
			}

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
	/*public Cliente select(int codigo){
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
	}*/

	/*public ArrayList<Cliente> selectAll(){
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
	}*/

	/*public Integer selectLastCode(){
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
	}*/

}

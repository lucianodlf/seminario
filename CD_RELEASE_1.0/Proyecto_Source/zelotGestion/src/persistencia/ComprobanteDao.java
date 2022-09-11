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
import java.util.Vector;

import logica.ArticuloLogica;
import logica.ClienteLogica;
import logica.CondicionVentaLogica;
import logica.ListaPrecioLogica;
import dominio.Articulo;
import dominio.Cliente;
import dominio.Factura;
import dominio.Impuesto;
import dominio.ItemFactura;

public class ComprobanteDao {

	/*
	 * IMPLEMENTACION PATRON SINGLETON
	 */
	private static ComprobanteDao comprobanteDao = null;

	private ArticuloLogica articuloLogica = ArticuloLogica.getInstance();
	private ClienteLogica clienteLogica = ClienteLogica.getInstance();
	private CondicionVentaLogica condicionVentaLogica = CondicionVentaLogica.getInstance();
	private ListaPrecioLogica listaPrecioLogica = ListaPrecioLogica.getInstance();





	public static ComprobanteDao getInstance(){
		if(comprobanteDao == null){
			comprobanteDao = new ComprobanteDao();
		}
		return comprobanteDao;
	}

	private ComprobanteDao(){

	}

	/*
	 * METODOS
	 */

	/*
	 * CREATE
	 */
	public int crateFactura(Factura factura, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sqlCabecera = "INSERT INTO VTAS_COMPROBANTES_CABECERA (NRO_COMPROBANTE, NRO_PTO_VENTA, ID_CLIENTE, ID_VENDEDOR, " +
						"ID_TIPO_COMPROBANTE, ID_CONDICION_VENTA, ID_EMPRESA, ID_LISTA_PRECIOS, ID_RUTA, FECHA_HORA_COMPROBANTE, " +
						"FECHA_ENTREGA, MONTO_TOTAL_CON_DESC_IMP, MONTO_TOTAL_SIN_DESC_IMP, CANTIDAD_TOTAL_ITEMS, " +
						"OBSERVACIONES, DESCUENTO_TOTAL_APLICADO, RECARGO_TOTAL_APLICADO, " +
						"LETRA_COMPROBANTE, CANTIDAD_CUOTAS, NRO_COMPROBANTE_CANCELADO, NRO_PTO_VENTA_CANCELADO, " +
						"ID_TIPO_COMPROBANTE_CANCELADO, LETRA_COMPROBANTE_CANCELADO, IMPORTE_IVA_21, IMPORTE_IVA_27, " +
						"IMPORTE_IVA_105, FECHA_HORA_UPDATE, ACTIVO) " +
						"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlCabecera);

			preparedStatement.setString(1, factura.getNroFactura());
			preparedStatement.setString(2, factura.getNroPtoVenta());
			preparedStatement.setInt(3, factura.getCliente().getCodigo());
			preparedStatement.setInt(4, 1);
			preparedStatement.setString(5, factura.getTipoComprobante());
			preparedStatement.setInt(6, factura.getCondicionVenta().getCodigoCondicionVenta());
			preparedStatement.setInt(7, 1);
			preparedStatement.setInt(8, factura.getListaPrecio().getCodigoListaPrecios());
			preparedStatement.setNull(9, Types.NULL);
			preparedStatement.setString(10, factura.getFechaFacturacion());
			preparedStatement.setString(11, "20111231");
			preparedStatement.setFloat(12, factura.getTotalFinal());
			preparedStatement.setFloat(13, factura.getSubTotal());
			preparedStatement.setFloat(14, factura.getItemsFactura().size());
			preparedStatement.setNull(15, Types.NULL);
			preparedStatement.setFloat(16, factura.getTotalBonifGlobal());
			preparedStatement.setFloat(17, factura.getTotalRecarGlobal());
			preparedStatement.setString(18, factura.getLetraFactura());
			preparedStatement.setInt(19, factura.getCantCtas());
			preparedStatement.setString(20, factura.getNroComprobanteRelac());
			preparedStatement.setString(21, factura.getNroPtoVtaComprobanteRelac());
			preparedStatement.setString(22, factura.getTipoComprobanteRelac());
			preparedStatement.setString(23, factura.getLetraComprobanteRelac());
			//preparedStatement.setBoolean(24, factura.isAnulado());
			preparedStatement.setFloat(24, factura.getTotalIva21());
			preparedStatement.setFloat(25, factura.getTotalIva27());
			preparedStatement.setFloat(26, factura.getTotalIva105());
			preparedStatement.setString(27, fechaHoraUpdate);
			preparedStatement.setBoolean(28, true);

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

	public int crateItemFactura(Factura factura, ItemFactura itemFactura, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sqlDetalle = "INSERT INTO VTAS_COMPROBANTES_DETALLE(CAB_COMP_NRO_COMPROBANTE, CAB_COMP_NRO_PUNTO_VENTA," +
					" CAB_COMP_TIPO, CAB_COMP_LETRA, ID_ARTICULO, ID_COMBO, CANTIDAD, PRECIO_UNITARIO_VENTA, PRECIO_UNITARIO_LISTA, PORCENTAJE_DESCUENTO_MANUAL," +
					" PORCENTAJE_DESCUENTO_ARTICULO, PORCENTAJE_DESCUENTO_CLIENTE, MONTO_IMPUESTO_INT_APLICADO, PESO_ARTICULO, ES_BONIFICADO, OBSERVACION_FACTURACION, " +
					" ACTIVO, FECHA_HORA_UPDATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlDetalle);

			preparedStatement.setString(1, factura.getNroFactura());
			preparedStatement.setString(2, "0001");
			preparedStatement.setString(3, factura.getTipoComprobante());
			preparedStatement.setString(4, factura.getLetraFactura());
			preparedStatement.setInt(5, itemFactura.getArticulo().getCodigo());
			preparedStatement.setNull(6, Types.NULL);
			preparedStatement.setFloat(7, itemFactura.getCantidad());
			preparedStatement.setFloat(8, itemFactura.getPrecioUnitario());
			preparedStatement.setFloat(9, itemFactura.getPrecioTotal());
			preparedStatement.setFloat(10, itemFactura.getDescManual());
			preparedStatement.setFloat(11, itemFactura.getArticulo().getDescuentoDefault());
			preparedStatement.setFloat(12, new Float(0));
			preparedStatement.setFloat(13, itemFactura.getArticulo().getImpInterno());
			preparedStatement.setFloat(14, itemFactura.getArticulo().getPesoEstimado());
			preparedStatement.setBoolean(15, false);
			if(itemFactura.getArticulo().getCodigo() == 0){
				preparedStatement.setString(16, itemFactura.getArticulo().getDescripcion());
			}else{
				preparedStatement.setNull(16, Types.NULL);
			}
			preparedStatement.setBoolean(17, true);
			preparedStatement.setString(18, fechaHoraUpdate);



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
	public int updateFacturaAnulado(Factura factura, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE VTAS_COMPROBANTES_CABECERA SET ANULADO = ?, FECHA_HORA_UPDATE = ?" +
					" WHERE NRO_COMPROBANTE = ? AND ID_TIPO_COMPROBANTE = ? AND LETRA_COMPROBANTE = ? AND NRO_PTO_VENTA = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setBoolean(1, factura.isAnulado());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, factura.getNroComprobanteRelac());
			preparedStatement.setString(4, factura.getTipoComprobanteRelac());
			preparedStatement.setString(5, factura.getLetraComprobanteRelac());
			preparedStatement.setString(6, factura.getNroPtoVtaComprobanteRelac());

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
	public int deleteCabecera(Factura factura){
		try{
			int rowsAffected = 0;
			String sql = "DELETE FROM VTAS_COMPROBANTES_CABECERA WHERE NRO_COMPROBANTE = ? AND NRO_PTO_VENTA = ? AND LETRA_COMPROBANTE = ? AND ID_TIPO_COMPROBANTE = ?";
			//String sql = "UPDATE VTAS_CLIENTES SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE ID_CLIENTE = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, factura.getNroFactura());
			preparedStatement.setString(2, factura.getNroPtoVenta());
			preparedStatement.setString(3, factura.getLetraFactura());
			preparedStatement.setString(4, "FAC");

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

	public Factura selectFactura(String tipoComp, String letraComp, String nroPtoVta, String nroComp){
		try{
			Factura factura = null;
			String sql = "SELECT NRO_COMPROBANTE, NRO_PTO_VENTA, ID_CLIENTE, ID_VENDEDOR, " +
					"ID_TIPO_COMPROBANTE, ID_CONDICION_VENTA, ID_EMPRESA, ID_LISTA_PRECIOS, " +
					"ID_RUTA, FECHA_HORA_COMPROBANTE, FECHA_ENTREGA, " +
					"MONTO_TOTAL_CON_DESC_IMP, MONTO_TOTAL_SIN_DESC_IMP, CANTIDAD_TOTAL_ITEMS, OBSERVACIONES, " +
					"DESCUENTO_TOTAL_APLICADO, RECARGO_TOTAL_APLICADO, LETRA_COMPROBANTE, CANTIDAD_CUOTAS, " +
					"NRO_COMPROBANTE_CANCELADO, NRO_PTO_VENTA_CANCELADO, ID_TIPO_COMPROBANTE_CANCELADO, " +
					"LETRA_COMPROBANTE_CANCELADO, IMPORTE_IVA_21, IMPORTE_IVA_27, IMPORTE_IVA_105, " +
					"FECHA_HORA_UPDATE, ANULADO, ACTIVO FROM VTAS_COMPROBANTES_CABECERA " +
					"WHERE NRO_COMPROBANTE = ? AND NRO_PTO_VENTA = ? AND ID_TIPO_COMPROBANTE = ? AND " +
					"LETRA_COMPROBANTE = ? AND ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, nroComp);
			preparedStatement.setString(2, nroPtoVta);
			preparedStatement.setString(3, tipoComp);
			preparedStatement.setString(4, letraComp);
			preparedStatement.setBoolean(5, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
					factura = new Factura();
					factura.setNroFactura(nroComp);
					factura.setNroPtoVenta(nroPtoVta);
					factura.setTipoComprobante(tipoComp);
					factura.setLetraFactura(letraComp);
					factura.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
					factura.setCondicionVenta(condicionVentaLogica.getCondicionVenta(resultSet.getInt("ID_CONDICION_VENTA")));
					factura.setListaPrecio(listaPrecioLogica.getListaPrecio(resultSet.getInt("ID_LISTA_PRECIOS")));
					factura.setFechaFacturacion(resultSet.getString("FECHA_HORA_COMPROBANTE"));
					factura.setTotalFinal(resultSet.getFloat("MONTO_TOTAL_CON_DESC_IMP"));
					factura.setSubTotal(resultSet.getFloat("MONTO_TOTAL_SIN_DESC_IMP"));
					factura.setTotalBonifGlobal(resultSet.getFloat("DESCUENTO_TOTAL_APLICADO"));
					factura.setTotalRecarGlobal(resultSet.getFloat("RECARGO_TOTAL_APLICADO"));
					factura.setCantCtas(resultSet.getInt("CANTIDAD_CUOTAS"));
					factura.setNroComprobanteRelac(resultSet.getString("NRO_COMPROBANTE_CANCELADO"));
					factura.setNroPtoVtaComprobanteRelac(resultSet.getString("NRO_PTO_VENTA_CANCELADO"));
					factura.setTipoComprobanteRelac(resultSet.getString("ID_TIPO_COMPROBANTE_CANCELADO"));
					factura.setLetraComprobanteRelac(resultSet.getString("LETRA_COMPROBANTE_CANCELADO"));
					factura.setTotalIva21(resultSet.getFloat("IMPORTE_IVA_21"));
					factura.setTotalIva27(resultSet.getFloat("IMPORTE_IVA_27"));
					factura.setTotalIva105(resultSet.getFloat("IMPORTE_IVA_105"));
					factura.setAnulado(resultSet.getBoolean("ANULADO"));
					factura.setDeposito(null);
					factura.setItemsFactura(new Vector<ItemFactura>(selectAllItemFacturaByFactura(factura)));
				preparedStatement.close();
				return factura;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Factura> selectAllFacturaByClienteAndFecha(Cliente cliente, String fechaDesde, String fechaHasta){
		try{
			ArrayList<Factura> facturas = new ArrayList<Factura>();
			Factura factura = null;
			String sql = "SELECT NRO_COMPROBANTE, NRO_PTO_VENTA, ID_CLIENTE, ID_VENDEDOR, " +
					"ID_TIPO_COMPROBANTE, ID_CONDICION_VENTA, ID_EMPRESA, ID_LISTA_PRECIOS, " +
					"ID_RUTA, FECHA_HORA_COMPROBANTE, FECHA_ENTREGA, " +
					"MONTO_TOTAL_CON_DESC_IMP, MONTO_TOTAL_SIN_DESC_IMP, CANTIDAD_TOTAL_ITEMS, OBSERVACIONES, " +
					"DESCUENTO_TOTAL_APLICADO, RECARGO_TOTAL_APLICADO, LETRA_COMPROBANTE, CANTIDAD_CUOTAS, " +
					"NRO_COMPROBANTE_CANCELADO, NRO_PTO_VENTA_CANCELADO, ID_TIPO_COMPROBANTE_CANCELADO, " +
					"LETRA_COMPROBANTE_CANCELADO, IMPORTE_IVA_21, IMPORTE_IVA_27, IMPORTE_IVA_105, " +
					"FECHA_HORA_UPDATE, ACTIVO FROM VTAS_COMPROBANTES_CABECERA " +
					"WHERE ID_CLIENTE = ? AND ACTIVO = ? AND FECHA_HORA_COMPROBANTE BETWEEN ? AND ? ORDER BY FECHA_HORA_COMPROBANTE DESC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, cliente.getCodigo());
			preparedStatement.setBoolean(2, true);
			preparedStatement.setString(3, fechaDesde);
			preparedStatement.setString(4, fechaHasta);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					factura = new Factura();
					factura.setNroFactura(resultSet.getString("NRO_COMPROBANTE"));
					factura.setNroPtoVenta(resultSet.getString("NRO_PTO_VENTA"));
					factura.setTipoComprobante(resultSet.getString("ID_TIPO_COMPROBANTE"));
					factura.setLetraFactura(resultSet.getString("LETRA_COMPROBANTE"));
					factura.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
					factura.setCondicionVenta(condicionVentaLogica.getCondicionVenta(resultSet.getInt("ID_CONDICION_VENTA")));
					factura.setListaPrecio(listaPrecioLogica.getListaPrecio(resultSet.getInt("ID_LISTA_PRECIOS")));
					factura.setFechaFacturacion(resultSet.getString("FECHA_HORA_COMPROBANTE"));
					factura.setTotalFinal(resultSet.getFloat("MONTO_TOTAL_CON_DESC_IMP"));
					factura.setSubTotal(resultSet.getFloat("MONTO_TOTAL_SIN_DESC_IMP"));
					factura.setTotalBonifGlobal(resultSet.getFloat("DESCUENTO_TOTAL_APLICADO"));
					factura.setTotalRecarGlobal(resultSet.getFloat("RECARGO_TOTAL_APLICADO"));
					factura.setCantCtas(resultSet.getInt("CANTIDAD_CUOTAS"));
					factura.setNroComprobanteRelac(resultSet.getString("NRO_COMPROBANTE_CANCELADO"));
					factura.setNroPtoVtaComprobanteRelac(resultSet.getString("NRO_PTO_VENTA_CANCELADO"));
					factura.setTipoComprobanteRelac(resultSet.getString("ID_TIPO_COMPROBANTE_CANCELADO"));
					factura.setLetraComprobanteRelac(resultSet.getString("LETRA_COMPROBANTE_CANCELADO"));
					factura.setTotalIva21(resultSet.getFloat("IMPORTE_IVA_21"));
					factura.setTotalIva27(resultSet.getFloat("IMPORTE_IVA_27"));
					factura.setTotalIva105(resultSet.getFloat("IMPORTE_IVA_105"));
					factura.setDeposito(null);
					factura.setItemsFactura(new Vector<ItemFactura>(selectAllItemFacturaByFactura(factura)));
					facturas.add(factura);
				}
				preparedStatement.close();
				return facturas;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Factura> selectAllFacturaByFecha(String fechaDesde, String fechaHasta){
		try{
			ArrayList<Factura> facturas = new ArrayList<Factura>();
			Factura factura = null;
			String sql = "SELECT NRO_COMPROBANTE, NRO_PTO_VENTA, ID_CLIENTE, ID_VENDEDOR, " +
					"ID_TIPO_COMPROBANTE, ID_CONDICION_VENTA, ID_EMPRESA, ID_LISTA_PRECIOS, " +
					"ID_RUTA, FECHA_HORA_COMPROBANTE, FECHA_ENTREGA, " +
					"MONTO_TOTAL_CON_DESC_IMP, MONTO_TOTAL_SIN_DESC_IMP, CANTIDAD_TOTAL_ITEMS, OBSERVACIONES, " +
					"DESCUENTO_TOTAL_APLICADO, RECARGO_TOTAL_APLICADO, LETRA_COMPROBANTE, CANTIDAD_CUOTAS, " +
					"NRO_COMPROBANTE_CANCELADO, NRO_PTO_VENTA_CANCELADO, ID_TIPO_COMPROBANTE_CANCELADO, " +
					"LETRA_COMPROBANTE_CANCELADO, IMPORTE_IVA_21, IMPORTE_IVA_27, IMPORTE_IVA_105, " +
					"FECHA_HORA_UPDATE, ACTIVO FROM VTAS_COMPROBANTES_CABECERA " +
					"WHERE ACTIVO = ? AND FECHA_HORA_COMPROBANTE BETWEEN ? AND ? ORDER BY FECHA_HORA_COMPROBANTE DESC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, fechaDesde);
			preparedStatement.setString(3, fechaHasta);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					factura = new Factura();
					factura.setNroFactura(resultSet.getString("NRO_COMPROBANTE"));
					factura.setNroPtoVenta(resultSet.getString("NRO_PTO_VENTA"));
					factura.setTipoComprobante(resultSet.getString("ID_TIPO_COMPROBANTE"));
					factura.setLetraFactura(resultSet.getString("LETRA_COMPROBANTE"));
					factura.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
					factura.setCondicionVenta(condicionVentaLogica.getCondicionVenta(resultSet.getInt("ID_CONDICION_VENTA")));
					factura.setListaPrecio(listaPrecioLogica.getListaPrecio(resultSet.getInt("ID_LISTA_PRECIOS")));
					factura.setFechaFacturacion(resultSet.getString("FECHA_HORA_COMPROBANTE"));
					factura.setTotalFinal(resultSet.getFloat("MONTO_TOTAL_CON_DESC_IMP"));
					factura.setSubTotal(resultSet.getFloat("MONTO_TOTAL_SIN_DESC_IMP"));
					factura.setTotalBonifGlobal(resultSet.getFloat("DESCUENTO_TOTAL_APLICADO"));
					factura.setTotalRecarGlobal(resultSet.getFloat("RECARGO_TOTAL_APLICADO"));
					factura.setCantCtas(resultSet.getInt("CANTIDAD_CUOTAS"));
					factura.setNroComprobanteRelac(resultSet.getString("NRO_COMPROBANTE_CANCELADO"));
					factura.setNroPtoVtaComprobanteRelac(resultSet.getString("NRO_PTO_VENTA_CANCELADO"));
					factura.setTipoComprobanteRelac(resultSet.getString("ID_TIPO_COMPROBANTE_CANCELADO"));
					factura.setLetraComprobanteRelac(resultSet.getString("LETRA_COMPROBANTE_CANCELADO"));
					factura.setTotalIva21(resultSet.getFloat("IMPORTE_IVA_21"));
					factura.setTotalIva27(resultSet.getFloat("IMPORTE_IVA_27"));
					factura.setTotalIva105(resultSet.getFloat("IMPORTE_IVA_105"));
					factura.setDeposito(null);
					factura.setItemsFactura(new Vector<ItemFactura>(selectAllItemFacturaByFactura(factura)));
					facturas.add(factura);
				}
				preparedStatement.close();
				return facturas;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ItemFactura> selectAllItemFacturaByFactura(Factura factura){
		try{
			ItemFactura itemFactura = null;
			ArrayList<ItemFactura> itemsFactura = new ArrayList<ItemFactura>();
			String sql = "SELECT CAB_COMP_NRO_COMPROBANTE, CAB_COMP_NRO_PUNTO_VENTA, ID_ARTICULO, ID_COMBO, " +
					"CANTIDAD, PRECIO_UNITARIO_VENTA, PRECIO_UNITARIO_LISTA, PORCENTAJE_DESCUENTO_MANUAL, " +
					"PORCENTAJE_DESCUENTO_ARTICULO, PORCENTAJE_DESCUENTO_CLIENTE, MONTO_IMPUESTO_INT_APLICADO, " +
					"PESO_ARTICULO, ES_BONIFICADO, OBSERVACION_FACTURACION, ACTIVO, FECHA_HORA_UPDATE FROM VTAS_COMPROBANTES_DETALLE " +
					"WHERE CAB_COMP_NRO_COMPROBANTE = ? AND CAB_COMP_NRO_PUNTO_VENTA = ? AND CAB_COMP_TIPO = ? AND " +
					"CAB_COMP_LETRA = ? AND ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, factura.getNroFactura());
			preparedStatement.setString(2, factura.getNroPtoVenta());
			preparedStatement.setString(3, factura.getTipoComprobante());
			preparedStatement.setString(4, factura.getLetraFactura());
			preparedStatement.setBoolean(5, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					itemFactura = new ItemFactura();
					if(resultSet.getInt("ID_ARTICULO") != 0){
						itemFactura.setArticulo(articuloLogica.getArticulo(resultSet.getInt("ID_ARTICULO")));
					}else{
						Articulo art = new Articulo();
						art.setCodigo(resultSet.getInt("ID_ARTICULO"));
						art.setActivo(true);
						art.setCostoConImp(Float.valueOf(0));
						art.setCostoSinImp(Float.valueOf(0));
						art.setDeposito(null);
						art.setDescuentoDefault(Float.valueOf(0));
						art.setDescuentoMax(Float.valueOf(0));
						art.setDivision(null);
						art.setFechaAlta(null);
						art.setImpInterno(Float.valueOf(0));
						art.setLinea(null);
						art.setImpuesto(new Impuesto(1, null, Float.valueOf(1), Float.valueOf(0), null, false));
						art.setMarca(null);
						art.setMarckupDefaul(Float.valueOf(0));
						art.setPesoEstimado(Float.valueOf(0));
						art.setPrecioListaBase(Float.valueOf(0));
						art.setProveedor(null);
						art.setRubro(null);
						art.setSinonimo(null);
						art.setStockDefault(Float.valueOf(0));
						art.setStockMax(Float.valueOf(0));
						art.setStockMin(Float.valueOf(0));
						art.setSubRubro(null);
						art.setSubUnidadVta(Float.valueOf(0));
						art.setUnidadMinVta(Float.valueOf(0));
						art.setUnidadVta(null);
						art.setDescripcion(resultSet.getString("OBSERVACION_FACTURACION"));
						itemFactura.setArticulo(art);
					}
					itemFactura.setCantidad(resultSet.getFloat("CANTIDAD"));
					itemFactura.setPrecioUnitario(resultSet.getFloat("PRECIO_UNITARIO_VENTA"));
					itemFactura.setDescManual(resultSet.getFloat("PORCENTAJE_DESCUENTO_MANUAL"));
					//itemFactura.setPrecioUnitario(resultSet.getFloat("PRECIO_UNITARIO_VENTA"));
					//itemFactura.setPrecioTotal(resultSet.getFloat("PRECIO_UNITARIO_VENTA") * resultSet.getFloat("CANTIDAD"));
					itemFactura.setPrecioTotal(resultSet.getFloat("PRECIO_UNITARIO_LISTA"));
					itemsFactura.add(itemFactura);
				}
				preparedStatement.close();
				return itemsFactura;
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

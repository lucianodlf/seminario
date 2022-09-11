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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import logica.ClienteLogica;
import logica.VendedorLogica;
import dominio.Cliente;
import dominio.Cobranza;
import dominio.ComprobanteCtaCte;
import dominio.DocumentoImputado;
import dominio.Factura;

public class CobranzaDao {

	/*
	 * IMPLEMENTACION PATRON SINGLETON
	 */
	private static CobranzaDao cobranzaDao = null;


	private VendedorLogica vendedorLogica = VendedorLogica.getInstance();
	private ClienteLogica clienteLogica = ClienteLogica.getInstance();





	public static CobranzaDao getInstance(){
		if(cobranzaDao == null){
			cobranzaDao = new CobranzaDao();
		}
		return cobranzaDao;
	}

	private CobranzaDao(){

	}

	/*
	 * METODOS
	 */


	/******************************************* COMPROBANTES DE CUENTA CORRIENTE **********************************/
	/*
	 * CREATE
	 */
	public int crateComprobanteCtaCte(ComprobanteCtaCte comprobanteCtaCte, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sqlCabecera = "INSERT INTO CTA_CTE_COMPROBANTES (NRO_COMPROBANTE_CTA_CTE, NRO_PTO_VENTA_CTA_CTE, " +
					"TIPO_COMPROBANTE_CTA_CTE, LETRA_COMPROBANTE_CTA_CTE, ID_CLIENTE, ID_VENDEDOR, " +
					"CONCEPTO, FECHA_EMISION, FECHA_VENCIMIENTO, MONTO_ORIGINAL, SALDO, NRO_CTA, " +
						 "ACTIVO, FECHA_HORA_UPDATE) " +
						"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlCabecera);

			preparedStatement.setString(1, comprobanteCtaCte.getNroComprobanteCtaCte());
			preparedStatement.setString(2, comprobanteCtaCte.getNroPtoVtaComprobanteCtaCte());
			preparedStatement.setString(3, comprobanteCtaCte.getTipoComprobanteCtaCte());
			preparedStatement.setString(4, comprobanteCtaCte.getLetraComprobanteCtaCte());
			preparedStatement.setInt(5, comprobanteCtaCte.getCliente().getCodigo());
			if(comprobanteCtaCte.getVendedor() != null){
				preparedStatement.setInt(6, comprobanteCtaCte.getVendedor().getCodigo());
			}else{
				preparedStatement.setNull(6, Types.NULL);
			}
			preparedStatement.setString(7, comprobanteCtaCte.getConcepto());
			preparedStatement.setString(8, comprobanteCtaCte.getFechaEmision());
			preparedStatement.setString(9, comprobanteCtaCte.getFechaVencimiento());
			preparedStatement.setFloat(10, comprobanteCtaCte.getMontoOriginal());
			preparedStatement.setFloat(11, comprobanteCtaCte.getSaldo());
			preparedStatement.setInt(12, comprobanteCtaCte.getCantidadCtas());
			preparedStatement.setBoolean(13, true);
			preparedStatement.setString(14, fechaHoraUpdate);


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



	/*
	 * UPDATE
	 */
	/*public int updateSaldoComprobanteCtaCte(ComprobanteCtaCte comprobanteCtaCte, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE CTA_CTE_COMPROBANTES SET SALDO = ?, FECHA_HORA_UPDATE = ?" +
					" WHERE NRO_COMPROBANTE_CTA_CTE = ? AND NRO_PTO_VENTA_CTA_CTE = ? AND TIPO_COMPROBANTE_CTA_CTE = ? " +
					"AND LETRA_COMPROBANTE_CTA_CTE = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setFloat(1, comprobanteCtaCte.getSaldo());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, comprobanteCtaCte.getNroComprobanteCtaCte());
			preparedStatement.setString(4, comprobanteCtaCte.getNroPtoVtaComprobanteCtaCte());
			preparedStatement.setString(5, comprobanteCtaCte.getTipoComprobanteCtaCte());
			preparedStatement.setString(6, comprobanteCtaCte.getLetraComprobanteCtaCte());

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
	public int deleteComprobanteCtaCte(ComprobanteCtaCte comprobanteCtaCte, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE CTA_CTE_COMPROBANTES SET ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE " +
					"NRO_COMPROBANTE_CTA_CTE = ? AND NRO_PTO_VENTA_CTA_CTE = ? " +
					"AND TIPO_COMPROBANTE_CTA_CTE = ? AND LETRA_COMPROBANTE_CTA_CTE = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, comprobanteCtaCte.getNroComprobanteCtaCte());
			preparedStatement.setString(4, comprobanteCtaCte.getNroPtoVtaComprobanteCtaCte());
			preparedStatement.setString(5, comprobanteCtaCte.getTipoComprobanteCtaCte());
			preparedStatement.setString(6, comprobanteCtaCte.getLetraComprobanteCtaCte());

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
	public ComprobanteCtaCte select(String nroComprobanteCtaCte, String nroPtoVtaComprobanteCtaCte, String tipoComprobanteCtaCte, String letraComprobanteCtaCte, int nroCta){
		try{
			ComprobanteCtaCte comprobanteCtaCte = new ComprobanteCtaCte();

			String sql = "SELECT NRO_COMPROBANTE_CTA_CTE, NRO_PTO_VENTA_CTA_CTE, " +
					"TIPO_COMPROBANTE_CTA_CTE, LETRA_COMPROBANTE_CTA_CTE, ID_CLIENTE, ID_VENDEDOR, " +
					"CONCEPTO, FECHA_EMISION, FECHA_VENCIMIENTO, MONTO_ORIGINAL, SALDO, NRO_CTA, " +
						 "ACTIVO, FECHA_HORA_UPDATE FROM CTA_CTE_COMPROBANTES WHERE NRO_COMPROBANTE_CTA_CTE = ? AND " +
						 "NRO_PTO_VENTA_CTA_CTE = ? AND TIPO_COMPROBANTE_CTA_CTE = ? AND LETRA_COMPROBANTE_CTA_CTE = ? AND " +
						 "NRO_CTA = ? AND ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, nroComprobanteCtaCte);
			preparedStatement.setString(2, nroPtoVtaComprobanteCtaCte);
			preparedStatement.setString(3, tipoComprobanteCtaCte);
			preparedStatement.setString(4, letraComprobanteCtaCte);
			preparedStatement.setInt(5, nroCta);
			preparedStatement.setBoolean(6, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				comprobanteCtaCte.setNroComprobanteCtaCte(nroComprobanteCtaCte);
				comprobanteCtaCte.setNroPtoVtaComprobanteCtaCte(nroPtoVtaComprobanteCtaCte);
				comprobanteCtaCte.setTipoComprobanteCtaCte(tipoComprobanteCtaCte);
				comprobanteCtaCte.setLetraComprobanteCtaCte(letraComprobanteCtaCte);
				comprobanteCtaCte.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
				comprobanteCtaCte.setVendedor(vendedorLogica.getVendedor(resultSet.getInt("ID_VENDEDOR")));
				comprobanteCtaCte.setConcepto(resultSet.getString("CONCEPTO"));
				comprobanteCtaCte.setFechaEmision(resultSet.getString("FECHA_EMISION"));
				comprobanteCtaCte.setFechaVencimiento(resultSet.getString("FECHA_VENCIMIENTO"));
				comprobanteCtaCte.setMontoOriginal(resultSet.getFloat("MONTO_ORIGINAL"));
				comprobanteCtaCte.setSaldo(resultSet.getFloat("SALDO"));
				comprobanteCtaCte.setCantidadCtas(resultSet.getInt("NRO_CTA"));

				preparedStatement.close();
				return comprobanteCtaCte;

			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	public int updateSaldoComprobanteCtaCte(DocumentoImputado documentoImputado, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			DecimalFormat decimalFormat = new DecimalFormat("####.00");
			String sql = "UPDATE  CTA_CTE_COMPROBANTES SET SALDO = ?, " +
						 "ACTIVO = ?, FECHA_HORA_UPDATE = ? WHERE NRO_COMPROBANTE_CTA_CTE = ? AND " +
						 "NRO_PTO_VENTA_CTA_CTE = ? AND TIPO_COMPROBANTE_CTA_CTE = ? AND LETRA_COMPROBANTE_CTA_CTE = ? AND " +
						 "NRO_CTA = ? AND ACTIVO = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			try {
				preparedStatement.setFloat(1, decimalFormat.parse(decimalFormat.format((documentoImputado.getComprobanteCtaCte().getSaldo() - documentoImputado.getMontoImputado()))).floatValue());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			preparedStatement.setBoolean(2, true);
			preparedStatement.setString(3, fechaHoraUpdate);
			preparedStatement.setString(4, documentoImputado.getComprobanteCtaCte().getNroComprobanteCtaCte());
			preparedStatement.setString(5, documentoImputado.getComprobanteCtaCte().getNroPtoVtaComprobanteCtaCte());
			preparedStatement.setString(6, documentoImputado.getComprobanteCtaCte().getTipoComprobanteCtaCte());
			preparedStatement.setString(7, documentoImputado.getComprobanteCtaCte().getLetraComprobanteCtaCte());
			preparedStatement.setInt(8, documentoImputado.getComprobanteCtaCte().getCantidadCtas());
			preparedStatement.setBoolean(9, true);

			System.out.println("QUERY: " + preparedStatement);
			rowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	public int updateComprobanteAnulado(Factura factura, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sql = "UPDATE CTA_CTE_COMPROBANTES SET ANULADO = ?, FECHA_HORA_UPDATE = ?" +
					" WHERE NRO_COMPROBANTE_CTA_CTE = ? AND NRO_PTO_VENTA_CTA_CTE = ? AND " +
					"TIPO_COMPROBANTE_CTA_CTE = ? AND LETRA_COMPROBANTE_CTA_CTE = ? AND ACTIVO = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			preparedStatement.setBoolean(1, factura.isAnulado());
			preparedStatement.setString(2, fechaHoraUpdate);
			preparedStatement.setString(3, factura.getNroComprobanteRelac());
			preparedStatement.setString(4, factura.getNroPtoVtaComprobanteRelac());
			preparedStatement.setString(5, factura.getTipoComprobanteRelac());
			preparedStatement.setString(6, factura.getLetraComprobanteRelac());
			preparedStatement.setBoolean(7, true);

			System.out.println("QUERY: " + preparedStatement);
			rowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			return rowsAffected;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}


	public ArrayList<ComprobanteCtaCte> selectAllComprobantesCtaCte(){
		try{
			ComprobanteCtaCte comprobanteCtaCte = null;
			ArrayList<ComprobanteCtaCte> comprobantesCtaCte = new ArrayList<ComprobanteCtaCte>();
			String sql = "SELECT NRO_COMPROBANTE_CTA_CTE, NRO_PTO_VENTA_CTA_CTE, " +
					"TIPO_COMPROBANTE_CTA_CTE, LETRA_COMPROBANTE_CTA_CTE, ID_CLIENTE, ID_VENDEDOR, " +
					"CONCEPTO, FECHA_EMISION, FECHA_VENCIMIENTO, MONTO_ORIGINAL, SALDO, NRO_CTA, " +
						 "ANULADO, ACTIVO, FECHA_HORA_UPDATE FROM CTA_CTE_COMPROBANTES WHERE ACTIVO = ? ORDER BY FECHA_EMISION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					comprobanteCtaCte = new ComprobanteCtaCte();
					comprobanteCtaCte.setNroComprobanteCtaCte(resultSet.getString("NRO_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setNroPtoVtaComprobanteCtaCte(resultSet.getString("NRO_PTO_VENTA_CTA_CTE"));
					comprobanteCtaCte.setTipoComprobanteCtaCte(resultSet.getString("TIPO_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setLetraComprobanteCtaCte(resultSet.getString("LETRA_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
					comprobanteCtaCte.setVendedor(vendedorLogica.getVendedor(resultSet.getInt("ID_VENDEDOR")));
					comprobanteCtaCte.setConcepto(resultSet.getString("CONCEPTO"));
					comprobanteCtaCte.setFechaEmision(resultSet.getString("FECHA_EMISION"));
					comprobanteCtaCte.setFechaVencimiento(resultSet.getString("FECHA_VENCIMIENTO"));
					comprobanteCtaCte.setMontoOriginal(resultSet.getFloat("MONTO_ORIGINAL"));
					comprobanteCtaCte.setSaldo(resultSet.getFloat("SALDO"));
					comprobanteCtaCte.setCantidadCtas(resultSet.getInt("NRO_CTA"));
					comprobanteCtaCte.setAnulado(resultSet.getBoolean("ANULADO"));
					comprobantesCtaCte.add(comprobanteCtaCte);
				}
				preparedStatement.close();
				return comprobantesCtaCte;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ComprobanteCtaCte> selectAllComprobantesCtaCteByCliente(Cliente cliente){
		try{
			ComprobanteCtaCte comprobanteCtaCte = null;
			ArrayList<ComprobanteCtaCte> comprobantesCtaCte = new ArrayList<ComprobanteCtaCte>();
			String sql = "SELECT NRO_COMPROBANTE_CTA_CTE, NRO_PTO_VENTA_CTA_CTE, " +
					"TIPO_COMPROBANTE_CTA_CTE, LETRA_COMPROBANTE_CTA_CTE, ID_CLIENTE, ID_VENDEDOR, " +
					"CONCEPTO, FECHA_EMISION, FECHA_VENCIMIENTO, MONTO_ORIGINAL, SALDO, NRO_CTA, " +
						 "ANULADO, ACTIVO, FECHA_HORA_UPDATE FROM CTA_CTE_COMPROBANTES WHERE ACTIVO = ? AND ID_CLIENTE  = ? ORDER BY FECHA_EMISION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, cliente.getCodigo());
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					comprobanteCtaCte = new ComprobanteCtaCte();
					comprobanteCtaCte.setNroComprobanteCtaCte(resultSet.getString("NRO_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setNroPtoVtaComprobanteCtaCte(resultSet.getString("NRO_PTO_VENTA_CTA_CTE"));
					comprobanteCtaCte.setTipoComprobanteCtaCte(resultSet.getString("TIPO_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setLetraComprobanteCtaCte(resultSet.getString("LETRA_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
					comprobanteCtaCte.setVendedor(vendedorLogica.getVendedor(resultSet.getInt("ID_VENDEDOR")));
					comprobanteCtaCte.setConcepto(resultSet.getString("CONCEPTO"));
					comprobanteCtaCte.setFechaEmision(resultSet.getString("FECHA_EMISION"));
					comprobanteCtaCte.setFechaVencimiento(resultSet.getString("FECHA_VENCIMIENTO"));
					comprobanteCtaCte.setMontoOriginal(resultSet.getFloat("MONTO_ORIGINAL"));
					comprobanteCtaCte.setSaldo(resultSet.getFloat("SALDO"));
					comprobanteCtaCte.setCantidadCtas(resultSet.getInt("NRO_CTA"));
					comprobanteCtaCte.setAnulado(resultSet.getBoolean("ANULADO"));
					comprobantesCtaCte.add(comprobanteCtaCte);
				}
				preparedStatement.close();
				return comprobantesCtaCte;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ComprobanteCtaCte> selectAllComprobantesCtaCteByClientePendientes(Cliente cliente){
		try{
			ComprobanteCtaCte comprobanteCtaCte = null;
			ArrayList<ComprobanteCtaCte> comprobantesCtaCte = new ArrayList<ComprobanteCtaCte>();
			String sql = "SELECT NRO_COMPROBANTE_CTA_CTE, NRO_PTO_VENTA_CTA_CTE, " +
					"TIPO_COMPROBANTE_CTA_CTE, LETRA_COMPROBANTE_CTA_CTE, ID_CLIENTE, ID_VENDEDOR, " +
					"CONCEPTO, FECHA_EMISION, FECHA_VENCIMIENTO, MONTO_ORIGINAL, SALDO, NRO_CTA, " +
						 "ANULADO, ACTIVO, FECHA_HORA_UPDATE, ANULADO FROM CTA_CTE_COMPROBANTES WHERE ACTIVO = ? AND ID_CLIENTE  = ? " +
						 "AND SALDO <> 0 ORDER BY FECHA_EMISION ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, cliente.getCodigo());
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					comprobanteCtaCte = new ComprobanteCtaCte();
					comprobanteCtaCte.setNroComprobanteCtaCte(resultSet.getString("NRO_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setNroPtoVtaComprobanteCtaCte(resultSet.getString("NRO_PTO_VENTA_CTA_CTE"));
					comprobanteCtaCte.setTipoComprobanteCtaCte(resultSet.getString("TIPO_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setLetraComprobanteCtaCte(resultSet.getString("LETRA_COMPROBANTE_CTA_CTE"));
					comprobanteCtaCte.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
					comprobanteCtaCte.setVendedor(vendedorLogica.getVendedor(resultSet.getInt("ID_VENDEDOR")));
					comprobanteCtaCte.setConcepto(resultSet.getString("CONCEPTO"));
					comprobanteCtaCte.setFechaEmision(resultSet.getString("FECHA_EMISION"));
					comprobanteCtaCte.setFechaVencimiento(resultSet.getString("FECHA_VENCIMIENTO"));
					comprobanteCtaCte.setMontoOriginal(resultSet.getFloat("MONTO_ORIGINAL"));
					comprobanteCtaCte.setSaldo(resultSet.getFloat("SALDO"));
					comprobanteCtaCte.setCantidadCtas(resultSet.getInt("NRO_CTA"));
					comprobanteCtaCte.setAnulado(resultSet.getBoolean("ANULADO"));
					comprobantesCtaCte.add(comprobanteCtaCte);
				}
				preparedStatement.close();
				return comprobantesCtaCte;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}



	/************************ COBRANZAS - RECIBOS ********************************************/
	public ArrayList<Cobranza> selectAllCobranzasByCliente(Cliente cliente){
		try{
			Cobranza cobranza = null;
			ArrayList<Cobranza> cobranzas = new ArrayList<Cobranza>();
			String sql = "SELECT NRO_COMPROBANTE_COBRANZA_RECIBO, NRO_PTO_VTA_COBRANZA_RECIBO, " +
					"TIPO_COMPROBANTE_COBRANZA_RECIBO, LETRA_COMPROBANTE_COBRANZA_RECIBO, ID_CLIENTE, " +
					"FECHA_COBRANZA, MONTO_TOTAL_IMPUTADO, ID_VENDEDOR, ACTIVO, FECHA_HORA_UPDATE " +
					"FROM CTA_CTE_COBRANZAS WHERE ACTIVO = ? AND ID_CLIENTE  = ? ORDER BY FECHA_COBRANZA ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, cliente.getCodigo());
			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					cobranza = new Cobranza();
					cobranza.setNroComprobanteRecibo(resultSet.getString("NRO_COMPROBANTE_COBRANZA_RECIBO"));
					cobranza.setNroPtoVtaComprobanteRecibo(resultSet.getString("NRO_PTO_VTA_COBRANZA_RECIBO"));
					cobranza.setTipoComprobanteRecibo(resultSet.getString("TIPO_COMPROBANTE_COBRANZA_RECIBO"));
					cobranza.setLetraComprobanteRecibo(resultSet.getString("LETRA_COMPROBANTE_COBRANZA_RECIBO"));
					cobranza.setCliente(clienteLogica.getCliente(resultSet.getInt("ID_CLIENTE")));
					cobranza.setVendedor(vendedorLogica.getVendedor(resultSet.getInt("ID_VENDEDOR")));
					cobranza.setFechaCobranza(resultSet.getString("FECHA_COBRANZA"));
					cobranza.setMontoTotalImputado(resultSet.getFloat("MONTO_TOTAL_IMPUTADO"));
					cobranzas.add(cobranza);
				}
				preparedStatement.close();
				return cobranzas;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	public int crateDocumentoImputado(Cobranza cobranza, DocumentoImputado documentoImputado, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sqlCabecera = "INSERT INTO CTA_CTE_DOCUMENTOS_IMPUTADOS (NRO_COMPROBANTE_COBRANZA_RECIBO, " +
					"NRO_PTO_VTA_COBRANZA_RECIBO, LETRA_COMPROBANTE_COBRANZA_RECIBO, TIPO_COMPROBANTE_COBRANZA_RECIBO, " +
					"MONTO_IMPUTADO, NUMERO_CUOTA, ACTIVO, FECHA_HORA_UPDATE, NRO_COMPROBANTE_CTA_CTE, NRO_PTO_VTA_COMPROBANTE_CTA_CTE, " +
					"TIPO_COMPROBANTE_CTA_CTE, LETRA_COMPROBANTE_CTA_CTE) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlCabecera);

			preparedStatement.setString(1, cobranza.getNroComprobanteRecibo());
			preparedStatement.setString(2, cobranza.getNroPtoVtaComprobanteRecibo());
			preparedStatement.setString(3, cobranza.getLetraComprobanteRecibo());
			preparedStatement.setString(4, cobranza.getTipoComprobanteRecibo());
			preparedStatement.setFloat(5, documentoImputado.getMontoImputado());
			preparedStatement.setInt(6, documentoImputado.getNumeroCta());
			preparedStatement.setBoolean(7, true);
			preparedStatement.setString(8, fechaHoraUpdate);
			preparedStatement.setString(9, documentoImputado.getComprobanteCtaCte().getNroComprobanteCtaCte());
			preparedStatement.setString(10, documentoImputado.getComprobanteCtaCte().getNroPtoVtaComprobanteCtaCte());
			preparedStatement.setString(11, documentoImputado.getComprobanteCtaCte().getTipoComprobanteCtaCte());
			preparedStatement.setString(12, documentoImputado.getComprobanteCtaCte().getLetraComprobanteCtaCte());

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

	public int crateCobranza(Cobranza cobranza, String fechaHoraUpdate){
		try{
			int rowsAffected = 0;
			String sqlCabecera = "INSERT INTO CTA_CTE_COBRANZAS (NRO_COMPROBANTE_COBRANZA_RECIBO, " +
					"NRO_PTO_VTA_COBRANZA_RECIBO,  TIPO_COMPROBANTE_COBRANZA_RECIBO, LETRA_COMPROBANTE_COBRANZA_RECIBO, " +
					"ID_CLIENTE, FECHA_COBRANZA, MONTO_TOTAL_IMPUTADO, ID_VENDEDOR, ACTIVO, FECHA_HORA_UPDATE) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlCabecera);

			preparedStatement.setString(1, cobranza.getNroComprobanteRecibo());
			preparedStatement.setString(2, cobranza.getNroPtoVtaComprobanteRecibo());
			preparedStatement.setString(3, cobranza.getTipoComprobanteRecibo());
			preparedStatement.setString(4, cobranza.getLetraComprobanteRecibo());
			preparedStatement.setInt(5, cobranza.getCliente().getCodigo());
			preparedStatement.setString(6, cobranza.getFechaCobranza());
			preparedStatement.setFloat(7, cobranza.getMontoTotalImputado());
			if(cobranza.getVendedor() != null){
				preparedStatement.setInt(8, cobranza.getVendedor().getCodigo());
			}else{
				preparedStatement.setNull(8, Types.NULL);
			}
			preparedStatement.setBoolean(9, true);
			preparedStatement.setString(10, fechaHoraUpdate);

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

	public int deleteCobranza(Cobranza cobranza){
		try{
			int rowsAffected = 0;
			String sqlCabecera = "DELETE FROM CTA_CTE_DOCUMENTOS_IMPUTADOS WHERE NRO_COMPROBANTE_COBRANZA_RECIBO = ? " +
					"AND NRO_PTO_VTA_COBRANZA_RECIBO = ? AND TIPO_COMPROBANTE_COBRANZA_RECIBO = ? " +
					"AND LETRA_COMPROBANTE_COBRANZA_RECIBO = ?";
			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlCabecera);

			preparedStatement.setString(1, cobranza.getNroComprobanteRecibo());
			preparedStatement.setString(2, cobranza.getNroPtoVtaComprobanteRecibo());
			preparedStatement.setString(3, cobranza.getTipoComprobanteRecibo());
			preparedStatement.setString(4, cobranza.getLetraComprobanteRecibo());


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

	public ArrayList<DocumentoImputado> selectAllDocumentosImputadosByCobranza(Cobranza cobranza){
		try{
			ArrayList<DocumentoImputado> documentosImputados = new ArrayList<DocumentoImputado>();
			DocumentoImputado documentoImputado = null;
			String sqlCabecera = "SELECT NRO_COMPROBANTE_COBRANZA_RECIBO, NRO_PTO_VTA_COBRANZA_RECIBO, " +
					"LETRA_COMPROBANTE_COBRANZA_RECIBO, TIPO_COMPROBANTE_COBRANZA_RECIBO, MONTO_IMPUTADO, " +
					"NUMERO_CUOTA, ACTIVO, FECHA_HORA_UPDATE, NRO_COMPROBANTE_CTA_CTE, NRO_PTO_VTA_COMPROBANTE_CTA_CTE, " +
					"TIPO_COMPROBANTE_CTA_CTE, LETRA_COMPROBANTE_CTA_CTE FROM CTA_CTE_DOCUMENTOS_IMPUTADOS " +
					"WHERE NRO_COMPROBANTE_COBRANZA_RECIBO = ? AND NRO_PTO_VTA_COBRANZA_RECIBO = ? AND " +
					"LETRA_COMPROBANTE_COBRANZA_RECIBO = ? AND TIPO_COMPROBANTE_COBRANZA_RECIBO = ? AND ACTIVO = ? ORDER BY NRO_COMPROBANTE_CTA_CTE ASC";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sqlCabecera);

			preparedStatement.setString(1, cobranza.getNroComprobanteRecibo());
			preparedStatement.setString(2, cobranza.getNroPtoVtaComprobanteRecibo());
			preparedStatement.setString(3, cobranza.getLetraComprobanteRecibo());
			preparedStatement.setString(4, cobranza.getTipoComprobanteRecibo());
			preparedStatement.setBoolean(5, true);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					documentoImputado = new DocumentoImputado();
					documentoImputado.setComprobanteCtaCte(select(resultSet.getString("NRO_COMPROBANTE_CTA_CTE"), resultSet.getString("NRO_PTO_VTA_COMPROBANTE_CTA_CTE"), resultSet.getString("TIPO_COMPROBANTE_CTA_CTE"), resultSet.getString("LETRA_COMPROBANTE_CTA_CTE"), resultSet.getInt("NUMERO_CUOTA")));
					documentoImputado.setMontoImputado(resultSet.getFloat("MONTO_IMPUTADO"));
					documentoImputado.setNumeroCta(resultSet.getInt("NUMERO_CUOTA"));
					documentosImputados.add(documentoImputado);
				}
				preparedStatement.close();
				return documentosImputados;
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

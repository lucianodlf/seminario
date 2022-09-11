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
package reports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistencia.ManagerDao;

public class ReportVentasDao {
	private static ReportVentasDao reportVentasDao = null;
	public static ReportVentasDao getInstance(){
		if(reportVentasDao == null){
			reportVentasDao = new ReportVentasDao();
		}
		return reportVentasDao;
	}

	private ReportVentasDao(){

	}


	public ArrayList<ReportVentas> selectAllComprobantes(){
		try{
			ReportVentas item = null;
			ArrayList<ReportVentas> data = new ArrayList<ReportVentas>();
			String sql = "SELECT vcomp.NRO_COMPROBANTE as NRO_COMP, vcomp.NRO_PTO_VENTA as NRO_PTO_VTA, vcomp.ID_TIPO_COMPROBANTE as TIPO_COMP, " +
					"vcomp.LETRA_COMPROBANTE as LETRA_COMP, cli.id_cliente as ID_CLI, cli.razon_social as RAZ_SOC, vdet.id_articulo as ID_ART, " +
					"art.descripcion as DESC_ART, vdet.cantidad as CANT_ART, vdet.precio_unitario_venta as PRE_UNIT_ITEM, " +
					"vdet.precio_unitario_lista as PRE_TOTAL_ITEM, vcomp.cantidad_total_items as TOTAL_ITEM_COMP, " +
					"vcomp.monto_total_con_desc_imp as TOTAL_FINAL_COMP, DATE_FORMAT(vcomp.fecha_hora_comprobante, '%d/%m/%Y') as FECHA_COMP FROM " +
					"zelot_gestion_db.vtas_comprobantes_cabecera as vcomp LEFT JOIN " +
					"(zelot_gestion_db.vtas_clientes as cli, zelot_gestion_db.vtas_comprobantes_detalle as vdet, " +
					"zelot_gestion_db.invrio_articulos as art) " +
					"ON (cli.id_cliente = vcomp.id_cliente and vdet.cab_comp_nro_comprobante = vcomp.nro_comprobante and " +
					"vdet.cab_comp_nro_punto_venta = vcomp.nro_pto_venta and vdet.cab_comp_tipo = vcomp.id_tipo_comprobante and " +
					"vdet.cab_comp_letra = vcomp.letra_comprobante and vdet.id_articulo = art.id_articulo) WHERE vcomp.activo = '1' " +
					"ORDER BY vcomp.fecha_hora_comprobante;";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					item = new ReportVentas();
					item.setNroFactura(resultSet.getString("TIPO_COMP")+" "+resultSet.getString("LETRA_COMP")+ " "+
							resultSet.getString("NRO_PTO_VTA")+"-"+resultSet.getString("NRO_COMP"));
					item.setFechaVta(resultSet.getString("FECHA_COMP"));
					item.setCantItem(resultSet.getFloat("CANT_ART"));
					item.setCodCliente(resultSet.getInt("ID_CLI"));
					item.setCodItem(resultSet.getString("ID_ART"));
					item.setDescItem(resultSet.getString("DESC_ART"));
					item.setMontoTotalFac(resultSet.getFloat("TOTAL_FINAL_COMP"));
					item.setPreFinalUnit(resultSet.getFloat("PRE_UNIT_ITEM"));
					item.setPreTotalFinal(resultSet.getFloat("PRE_TOTAL_ITEM"));
					item.setRazonSocial(resultSet.getString("RAZ_SOC"));
					item.setTotalItemsFact(resultSet.getInt("TOTAL_ITEM_COMP"));
					data.add(item);
				}
				preparedStatement.close();
				return data;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ReportVentas> selectAllComprobantesByClient(int codigoCliente){
		try{
			ReportVentas item = null;
			ArrayList<ReportVentas> data = new ArrayList<ReportVentas>();
			String sql = "SELECT vcomp.NRO_COMPROBANTE as NRO_COMP, vcomp.NRO_PTO_VENTA as NRO_PTO_VTA, vcomp.ID_TIPO_COMPROBANTE as TIPO_COMP, " +
					"vcomp.LETRA_COMPROBANTE as LETRA_COMP, cli.id_cliente as ID_CLI, cli.razon_social as RAZ_SOC, vdet.id_articulo as ID_ART, " +
					"art.descripcion as DESC_ART, vdet.cantidad as CANT_ART, vdet.precio_unitario_venta as PRE_UNIT_ITEM, " +
					"vdet.precio_unitario_lista as PRE_TOTAL_ITEM, vcomp.cantidad_total_items as TOTAL_ITEM_COMP, " +
					"vcomp.monto_total_con_desc_imp as TOTAL_FINAL_COMP, DATE_FORMAT(vcomp.fecha_hora_comprobante, '%d/%m/%Y') as FECHA_COMP FROM " +
					"zelot_gestion_db.vtas_comprobantes_cabecera as vcomp LEFT JOIN " +
					"(zelot_gestion_db.vtas_clientes as cli, zelot_gestion_db.vtas_comprobantes_detalle as vdet, " +
					"zelot_gestion_db.invrio_articulos as art) " +
					"ON (cli.id_cliente = vcomp.id_cliente and vdet.cab_comp_nro_comprobante = vcomp.nro_comprobante and " +
					"vdet.cab_comp_nro_punto_venta = vcomp.nro_pto_venta and vdet.cab_comp_tipo = vcomp.id_tipo_comprobante and " +
					"vdet.cab_comp_letra = vcomp.letra_comprobante and vdet.id_articulo = art.id_articulo) WHERE vcomp.activo = '1' AND " +
					"cli.id_cliente = ? ORDER BY vcomp.fecha_hora_comprobante;";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, codigoCliente);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					item = new ReportVentas();
					item.setNroFactura(resultSet.getString("TIPO_COMP")+" "+resultSet.getString("LETRA_COMP")+ " "+
							resultSet.getString("NRO_PTO_VTA")+"-"+resultSet.getString("NRO_COMP"));
					item.setFechaVta(resultSet.getString("FECHA_COMP"));
					item.setCantItem(resultSet.getFloat("CANT_ART"));
					item.setCodCliente(resultSet.getInt("ID_CLI"));
					item.setCodItem(resultSet.getString("ID_ART"));
					item.setDescItem(resultSet.getString("DESC_ART"));
					item.setMontoTotalFac(resultSet.getFloat("TOTAL_FINAL_COMP"));
					item.setPreFinalUnit(resultSet.getFloat("PRE_UNIT_ITEM"));
					item.setPreTotalFinal(resultSet.getFloat("PRE_TOTAL_ITEM"));
					item.setRazonSocial(resultSet.getString("RAZ_SOC"));
					item.setTotalItemsFact(resultSet.getInt("TOTAL_ITEM_COMP"));
					data.add(item);
				}
				preparedStatement.close();
				return data;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}



	public ArrayList<ReportVentas> selectAllComprobantesBetweenFecha(String fechaDesde, String fechaHasta){
		try{
			ReportVentas item = null;
			ArrayList<ReportVentas> data = new ArrayList<ReportVentas>();
			String sql = "SELECT vcomp.NRO_COMPROBANTE as NRO_COMP, vcomp.NRO_PTO_VENTA as NRO_PTO_VTA, vcomp.ID_TIPO_COMPROBANTE as TIPO_COMP, " +
					"vcomp.LETRA_COMPROBANTE as LETRA_COMP, cli.id_cliente as ID_CLI, cli.razon_social as RAZ_SOC, vdet.id_articulo as ID_ART, " +
					"art.descripcion as DESC_ART, vdet.cantidad as CANT_ART, vdet.precio_unitario_venta as PRE_UNIT_ITEM, " +
					"vdet.precio_unitario_lista as PRE_TOTAL_ITEM, vcomp.cantidad_total_items as TOTAL_ITEM_COMP, " +
					"vcomp.monto_total_con_desc_imp as TOTAL_FINAL_COMP, DATE_FORMAT(vcomp.fecha_hora_comprobante, '%d/%m/%Y') as FECHA_COMP FROM " +
					"zelot_gestion_db.vtas_comprobantes_cabecera as vcomp LEFT JOIN " +
					"(zelot_gestion_db.vtas_clientes as cli, zelot_gestion_db.vtas_comprobantes_detalle as vdet, " +
					"zelot_gestion_db.invrio_articulos as art) " +
					"ON (cli.id_cliente = vcomp.id_cliente and vdet.cab_comp_nro_comprobante = vcomp.nro_comprobante and " +
					"vdet.cab_comp_nro_punto_venta = vcomp.nro_pto_venta and vdet.cab_comp_tipo = vcomp.id_tipo_comprobante and " +
					"vdet.cab_comp_letra = vcomp.letra_comprobante and vdet.id_articulo = art.id_articulo) WHERE vcomp.activo = '1' AND " +
					"vcomp.fecha_hora_comprobante BETWEEN ? and ? ORDER BY vcomp.fecha_hora_comprobante;";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setString(1, fechaDesde);
			preparedStatement.setString(2, fechaHasta);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					item = new ReportVentas();
					item.setNroFactura(resultSet.getString("TIPO_COMP")+" "+resultSet.getString("LETRA_COMP")+ " "+
							resultSet.getString("NRO_PTO_VTA")+"-"+resultSet.getString("NRO_COMP"));
					item.setFechaVta(resultSet.getString("FECHA_COMP"));
					item.setCantItem(resultSet.getFloat("CANT_ART"));
					item.setCodCliente(resultSet.getInt("ID_CLI"));
					item.setCodItem(resultSet.getString("ID_ART"));
					item.setDescItem(resultSet.getString("DESC_ART"));
					item.setMontoTotalFac(resultSet.getFloat("TOTAL_FINAL_COMP"));
					item.setPreFinalUnit(resultSet.getFloat("PRE_UNIT_ITEM"));
					item.setPreTotalFinal(resultSet.getFloat("PRE_TOTAL_ITEM"));
					item.setRazonSocial(resultSet.getString("RAZ_SOC"));
					item.setTotalItemsFact(resultSet.getInt("TOTAL_ITEM_COMP"));
					data.add(item);
				}
				preparedStatement.close();
				return data;
			}else{
				preparedStatement.close();
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<ReportVentas> selectAllComprobantesBetweenFechaAndCliente(int codigoCliente, String fechaDesde, String fechaHasta){
		try{
			ReportVentas item = null;
			ArrayList<ReportVentas> data = new ArrayList<ReportVentas>();
			String sql = "SELECT vcomp.NRO_COMPROBANTE as NRO_COMP, vcomp.NRO_PTO_VENTA as NRO_PTO_VTA, vcomp.ID_TIPO_COMPROBANTE as TIPO_COMP, " +
					"vcomp.LETRA_COMPROBANTE as LETRA_COMP, cli.id_cliente as ID_CLI, cli.razon_social as RAZ_SOC, vdet.id_articulo as ID_ART, " +
					"art.descripcion as DESC_ART, vdet.cantidad as CANT_ART, vdet.precio_unitario_venta as PRE_UNIT_ITEM, " +
					"vdet.precio_unitario_lista as PRE_TOTAL_ITEM, vcomp.cantidad_total_items as TOTAL_ITEM_COMP, " +
					"vcomp.monto_total_con_desc_imp as TOTAL_FINAL_COMP, DATE_FORMAT(vcomp.fecha_hora_comprobante, '%d/%m/%Y') as FECHA_COMP FROM " +
					"zelot_gestion_db.vtas_comprobantes_cabecera as vcomp LEFT JOIN " +
					"(zelot_gestion_db.vtas_clientes as cli, zelot_gestion_db.vtas_comprobantes_detalle as vdet, " +
					"zelot_gestion_db.invrio_articulos as art) " +
					"ON (cli.id_cliente = vcomp.id_cliente and vdet.cab_comp_nro_comprobante = vcomp.nro_comprobante and " +
					"vdet.cab_comp_nro_punto_venta = vcomp.nro_pto_venta and vdet.cab_comp_tipo = vcomp.id_tipo_comprobante and " +
					"vdet.cab_comp_letra = vcomp.letra_comprobante and vdet.id_articulo = art.id_articulo) WHERE vcomp.activo = '1' AND " +
					"cli.id_cliente = ? AND vcomp.fecha_hora_comprobante BETWEEN ? and ? ORDER BY vcomp.fecha_hora_comprobante;";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setInt(1, codigoCliente);
			preparedStatement.setString(2, fechaDesde);
			preparedStatement.setString(3, fechaHasta);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.isBeforeFirst()){
				while(resultSet.next()){
					item = new ReportVentas();
					item.setNroFactura(resultSet.getString("TIPO_COMP")+" "+resultSet.getString("LETRA_COMP")+ " "+
							resultSet.getString("NRO_PTO_VTA")+"-"+resultSet.getString("NRO_COMP"));
					item.setFechaVta(resultSet.getString("FECHA_COMP"));
					item.setCantItem(resultSet.getFloat("CANT_ART"));
					item.setCodCliente(resultSet.getInt("ID_CLI"));
					item.setCodItem(resultSet.getString("ID_ART"));
					item.setDescItem(resultSet.getString("DESC_ART"));
					item.setMontoTotalFac(resultSet.getFloat("TOTAL_FINAL_COMP"));
					item.setPreFinalUnit(resultSet.getFloat("PRE_UNIT_ITEM"));
					item.setPreTotalFinal(resultSet.getFloat("PRE_TOTAL_ITEM"));
					item.setRazonSocial(resultSet.getString("RAZ_SOC"));
					item.setTotalItemsFact(resultSet.getInt("TOTAL_ITEM_COMP"));
					data.add(item);
				}
				preparedStatement.close();
				return data;
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

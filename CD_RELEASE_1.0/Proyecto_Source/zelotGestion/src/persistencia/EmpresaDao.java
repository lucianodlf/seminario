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

import logica.CondicionIvaLogica;
import logica.DocumentoTipoPersonaLogica;
import logica.LocalidadLogica;
import logica.ProvinciaLogica;
import dominio.Empresa;


public class EmpresaDao {

	private static EmpresaDao empresaDao = null;
	private LocalidadLogica localidadLogica = LocalidadLogica.getInstance();
	private ProvinciaLogica provinciaLogica = ProvinciaLogica.getInstance();
	private CondicionIvaLogica condIvaLogica = CondicionIvaLogica.getInstance();
	private DocumentoTipoPersonaLogica documeTipoPersonaLogica = DocumentoTipoPersonaLogica.getInstance();
	public static EmpresaDao getInstance(){
		if(empresaDao == null){
			empresaDao = new EmpresaDao();
		}
		return empresaDao;
	}

	public EmpresaDao() {

	}

	/*
	 * METODOS
	 */


	/*
	 * SELECT POR CODIGO
	 */
	public Empresa select(int codigo){
		try{
			Empresa empresa = new Empresa();
			String sql = "SELECT RAZON_SOCIAL, NOMBRE_FANTASIA, NRO_PUNTO_VTA, FECHA_INICIO_ACTIVIDADES, DIRECCION, ID_LOCALIDAD, " +
					"ID_PROVINCIA, COD_POST, DOCUMENTO_TIPO, DOCUMENTO_NRO, ID_CONDICION_IVA, TELEFONO_1, " +
					"TELEFONO_2, EMAIL, WEB, ACTIVO, FECHA_HORA_UPDATE FROM GRAL_EMPRESAS WHERE ACTIVO = ? AND ID_EMPRESA = ?";

			PreparedStatement preparedStatement = ManagerDao.getConexion().prepareStatement(sql);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, codigo);

			System.out.println("QUERY: " + preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(resultSet.next()){
				empresa.setRazonSocial(resultSet.getString("RAZON_SOCIAL"));
				empresa.setNombreFantasia(resultSet.getString("NOMBRE_FANTASIA"));
				empresa.setNroPtoVta(resultSet.getString("NRO_PUNTO_VTA"));
				empresa.setFechaInicioActividades(resultSet.getString("FECHA_INICIO_ACTIVIDADES"));
				empresa.setDireccion(resultSet.getString("DIRECCION"));
				empresa.setLocalidad(localidadLogica.getLocalidad(resultSet.getInt("ID_LOCALIDAD")));
				empresa.setProvincia(provinciaLogica.getProvincia(resultSet.getInt("ID_PROVINCIA")));
				empresa.setCodPostal(resultSet.getString("COD_POST"));
				empresa.setDocumentoTipoPersona(documeTipoPersonaLogica.getDocumentoTipoPersona(resultSet.getInt("DOCUMENTO_TIPO")));
				empresa.setDocumentoNro(resultSet.getString("DOCUMENTO_NRO"));
				empresa.setCondicionIva(condIvaLogica.getCondicionIva(resultSet.getInt("ID_CONDICION_IVA")));
				empresa.setTelefono1(resultSet.getString("TELEFONO_1"));
				empresa.setTelefono2(resultSet.getString("TELEFONO_2"));
				empresa.setMail(resultSet.getString("EMAIL"));
				empresa.setWeb(resultSet.getString("WEB"));

				preparedStatement.close();
				return empresa;
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

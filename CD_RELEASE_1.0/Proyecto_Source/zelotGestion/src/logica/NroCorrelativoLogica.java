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
Este archivo forma parte de Zelot Gesti�n ERP.

     Zelot Gestion ERP es un software libre: usted puede redistribuirlo y / o modificar
     bajo los t�rminos de la GNU Lesser General Public License publicada por
     la Free Software Foundation, bien de la versi�n 3 de la Licencia, o
     (a su elecci�n) cualquier versi�n posterior.

     Zelot Gesti�n ERP se distribuye con la esperanza de que sea �til,
     pero SIN NINGUNA GARANT�A, incluso sin la garant�a impl�cita de
     COMERCIALIZACI�N o IDONEIDAD PARA UN PROP�SITO PARTICULAR. Consulte la
     GNU Lesser General Public License para m�s detalles.

     Deber�a haber recibido una copia de la GNU Lesser General Public License
     junto con Zelot Gesti�n ERP. Si no, vea <http://www.gnu.org/licenses/>.
******************************************************************************************/
package logica;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import persistencia.NroCorrelativoDao;
import dominio.NroCorrelativo;

public class NroCorrelativoLogica {

	private static NroCorrelativoLogica nroCorrelativoLogica = null;
	private NroCorrelativoDao nroCorrelativoDao = NroCorrelativoDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static NroCorrelativoLogica getInstance(){
		if(nroCorrelativoLogica == null){
			nroCorrelativoLogica = new NroCorrelativoLogica();
		}
		return nroCorrelativoLogica;
	}

	public NroCorrelativoLogica() {

	}

	/*
	 * METODOS
	 */
	/*public boolean addNroCorrelativo(NroCorrelativo iva){
		int rowAffected = nroCorrelativoDao.create(iva, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}*/

	public NroCorrelativo getNroCorrelativo(NroCorrelativo correlativo){
			NroCorrelativo nroCorrelativo = nroCorrelativoDao.select(correlativo);
			return nroCorrelativo;
	}

	/*public ArrayList<NroCorrelativo> getListNroCorrelativo(){
			ArrayList<NroCorrelativo> listaIva = nroCorrelativoDao.selectAll();
			return listaIva;
	}*/

	public boolean updateNroCorrelativo(NroCorrelativo nroCorrelativo){
		if(nroCorrelativo != null){
			int rowAffected = nroCorrelativoDao.update(nroCorrelativo, simpleDateFormat.format(new Date()));
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public boolean undoNroCorrelativo(NroCorrelativo nroCorrelativo){
		int rowAffected = nroCorrelativoDao.undoCorrelativo(nroCorrelativo, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public String getNextNroComprobante(NroCorrelativo correlativo){
		NroCorrelativo nroCorrelativo = getNroCorrelativo(correlativo);
		String newNroCorrelativo = null;
		if(nroCorrelativo != null){
			DecimalFormat decimalFormat = new DecimalFormat("00000000");
			Integer nroComp =  Integer.parseInt(nroCorrelativo.getNroComprobante());
			nroComp++;
			newNroCorrelativo = decimalFormat.format(nroComp);
		}
		System.out.println("nro Correlativo siguiente: "+newNroCorrelativo);
		return newNroCorrelativo;
	}

}

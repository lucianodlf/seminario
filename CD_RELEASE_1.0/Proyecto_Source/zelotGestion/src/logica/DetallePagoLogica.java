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
package logica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import persistencia.DetallePagoDao;
import dominio.DetallePago;

public class DetallePagoLogica {

	private static DetallePagoLogica detallePagoLogica = null;
	private DetallePagoDao detallePagoDao = DetallePagoDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static DetallePagoLogica getInstance(){
		if(detallePagoLogica == null){
			detallePagoLogica = new DetallePagoLogica();
		}
		return detallePagoLogica;
	}

	public DetallePagoLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addDetallesPagos(Vector<DetallePago> detallesPago){
		Iterator<DetallePago> itDetPago = detallesPago.iterator();
		boolean transaction = true;
		while(itDetPago.hasNext()){
			DetallePago dp = (DetallePago)itDetPago.next();
			int rowAffected = detallePagoDao.crateDetallePago(dp, simpleDateFormat.format(new Date()));
			if(rowAffected == 0){
				transaction = false;
				break;
			}
			if(!transaction){
				deleteDetallesPagos(detallesPago);
			}
		}
		return transaction;
	}


	public void deleteDetallesPagos(Vector<DetallePago> detallesPago){
		Iterator<DetallePago> itDetPago = detallesPago.iterator();
		while(itDetPago.hasNext()){
			DetallePago dp = (DetallePago)itDetPago.next();
			detallePagoDao.deleteDetallePago(dp);
		}
	}


}

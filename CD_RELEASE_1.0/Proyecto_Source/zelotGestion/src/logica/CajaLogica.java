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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import persistencia.CajaDao;
import dominio.Caja;
import dominio.MovimientoCaja;
import dominio.TipoMovimientoCaja;

public class CajaLogica {
	private static CajaLogica cajaLogica = null;
	private CajaDao cajaDao = CajaDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");


	public static CajaLogica getInstance(){
		if(cajaLogica == null){
			cajaLogica = new CajaLogica();
		}
		return cajaLogica;
	}

	public CajaLogica() {

	}

	/*
	 * METODOS
	 */
	public boolean addCaja(Caja caja){
		int rowAffected = cajaDao.create(caja, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Caja inicializaCaja(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Caja caja = null;
		caja = cajaDao.selectCajaByFecha(df.format(new Date()));
		if(caja == null){
			caja = new Caja();
			caja.setFechaInicio(simpleDateFormat.format(new Date()));
			caja.setFechaCierre(caja.getFechaInicio());
			caja.setMontoInicio(cajaDao.selectMontoCierreAnterio());
			caja.setMontoCierre(caja.getMontoInicio());
			if(addCaja(caja)){
				caja = cajaDao.selectCajaByFecha(df.format(new Date()));
			}else{
				caja = null;
			}
		}else{
			caja = null;
		}
		return caja;
	}

	public Caja getCajaByFecha(String fecha){
		return cajaDao.selectCajaByFecha(fecha);
	}

	public Caja getCaja(int codigo){
			Caja caja = cajaDao.selectCaja(codigo);
			return caja;
	}

	public TipoMovimientoCaja getTipoMovimientoCaja(int codigo){
		TipoMovimientoCaja tipoMovimientoCaja = cajaDao.selectTipoMovimiento(codigo);
		return tipoMovimientoCaja;
}



	public ArrayList<TipoMovimientoCaja> getListTipoMovimientoCaja(){
		ArrayList<TipoMovimientoCaja> tipoMovimientosCaja = cajaDao.selectAllTiposMovimientoCaja();
		return tipoMovimientosCaja;

	}

	public boolean updateCaja(Caja caja){
		if(caja.getCodigo() != 0){
			int rowAffected = cajaDao.update(caja, simpleDateFormat.format(new Date()));
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = cajaDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}

	public Float calculaSaldoCaja(Caja c){
		Float saldo = Float.valueOf(0);
		if(c != null){
			Float ingreso = Float.valueOf(0);
			Float egreso = Float.valueOf(0);
			if(c.getMontoInicio() != null)ingreso = c.getMontoInicio();
			if(c.getMovimientosCasa() != null){
				Iterator<MovimientoCaja> it = c.getMovimientosCasa().iterator();
				while(it.hasNext()){
					MovimientoCaja mc = (MovimientoCaja)it.next();
					if(mc.getTipoMovimiento().isSuma()) ingreso += mc.getImporteMovimiento();
					if(mc.getTipoMovimiento().isResta()) egreso += mc.getImporteMovimiento();
				}
				saldo = ingreso - egreso;
			}
		}
		return saldo;
	}


	public boolean cieereCaja(Caja c){
		boolean resultado = true;
		c.setFechaCierre(simpleDateFormat.format(new Date()));
		c.setMontoCierre(calculaSaldoCaja(c));
		c.setCerrada(true);
		resultado = updateCaja(c);
		return resultado;
	}
}

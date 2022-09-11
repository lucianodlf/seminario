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

import persistencia.PerfilDao;
import dominio.Perfil;
import dominio.Permiso;

public class PerfilLogica {

	private static PerfilLogica perfilLogica = null;
	private PerfilDao perfilDao = PerfilDao.getInstance();
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");

	public static PerfilLogica getInstance(){
		if(perfilLogica == null){
			perfilLogica = new PerfilLogica();
		}
		return perfilLogica;
	}

	private PerfilLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addPerfil(Perfil perfil){
		boolean resultado = true;
		int rowAffectedPerfil = perfilDao.create(perfil, simpleDateFormat.format(new Date()));
		if(rowAffectedPerfil != 0){
			perfil.setCodigo(perfilDao.selectLastCode());
			boolean completo = createPerfilPermisoByPerfil(perfil);
			if(!completo){
				resultado = false;
				perfilDao.rolbackPerfil(perfil);
				rolbackPermisosByPerfil(perfil);
			}
		}else{
			resultado = false;
		}
		return resultado;
	}

	public Perfil getPerfilByCodigo(int codigo){
		try{
			Perfil perfil = perfilDao.select(codigo);
			if(perfil != null)perfil.setPermisos(perfilDao.selectPermisosByPerfil(perfil));
			return perfil;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Perfil> getAllPerfil(){
		try{
			ArrayList<Perfil> listaPerfil = perfilDao.selectAll();
			if(listaPerfil != null){
				Iterator<Perfil> it = listaPerfil.iterator();
				while(it.hasNext()){
					Perfil perfil = (Perfil)it.next();
					perfil.setPermisos(perfilDao.selectPermisosByPerfil(perfil));
				}
			}
			return listaPerfil;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean updatePerfil(Perfil perfil){
		boolean resultado = true;
		if(perfil.getCodigo() != 0){
			int rowAffected = perfilDao.update(perfil, simpleDateFormat.format(new Date()));
			if(rowAffected != 0){
				boolean completo = updatePerfilesPermisos(perfil);
				if(!completo){
					resultado = false;
				}
			}else{
				resultado = false;
			}
		}else{
			resultado = false;
		}
		return resultado;
	}

	public boolean deletePerfil(int codigo){
		int rowAffected = perfilDao.delete(codigo, simpleDateFormat.format(new Date()));
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}
	}

	public Integer getNewCodigoItems(){
		Integer newCodigo = perfilDao.selectLastCode();
		if(newCodigo != null){
			newCodigo++;
		}
		return newCodigo;
	}

	public boolean createPerfilPermisoByPerfil(Perfil perfil){
		boolean resultado = true;
		int rowAffected = 0;
		if(perfil != null){
			if(perfil.getPermisos() != null){
				Iterator<Permiso> it = perfil.getPermisos().iterator();
				while(it.hasNext()){
					Permiso permiso = (Permiso)it.next();
					rowAffected = perfilDao.createPermiso(perfil, permiso, simpleDateFormat.format(new Date()));
					if(rowAffected == 0){
						resultado =  false;
						break;
					}
				}
			}
		}else{
			resultado = false;
		}
		return resultado;
	}

	public boolean updatePerfilesPermisos(Perfil perfil){
		boolean resultado = true;
		int rowAffected = 0;
		if(perfil != null){
			if(perfil.getPermisos() != null){
				Iterator<Permiso> it = perfil.getPermisos().iterator();
				while(it.hasNext()){
					Permiso permiso = (Permiso)it.next();
					rowAffected = perfilDao.updatePermisosByPerfiles(permiso, perfil, simpleDateFormat.format(new Date()));
					if(rowAffected == 0){
						rowAffected = perfilDao.createPermiso(perfil, permiso, simpleDateFormat.format(new Date()));
						if(rowAffected == 0){
							resultado = false;
						}
					}
				}

			}
		}else{
			resultado = false;
		}
		return resultado;
	}

	/*public boolean updatePerfilesPermisos(Perfil perfil, ArrayList<Permiso> permisosOld){
		boolean resultado = true;
		int rowAffected = 0;
		if(perfil != null){
			if(perfil.getPermisos() != null){
				Iterator<Permiso> it = perfil.getPermisos().iterator();
				while(it.hasNext()){
					Permiso permiso = (Permiso)it.next();
					boolean insert = true;
					if(permisosOld != null){
						Iterator<Permiso> itOld = permisosOld.iterator();
						while(itOld.hasNext()){
							Permiso perOld = (Permiso)itOld.next();
							if(permiso.getCodigo() == perOld.getCodigo()){
								insert = false;
								break;
							}
						}
					}
					if(insert){
						rowAffected = perfilDao.createPermiso(perfil, permiso, simpleDateFormat.format(new Date()));
						if(rowAffected == 0)resultado = false;
					}else{
						rowAffected = perfilDao.updatePermisosByPerfiles(permiso, perfil, simpleDateFormat.format(new Date()));
						if(rowAffected == 0)resultado = false;
					}
				}
			}
		}else{
			resultado = false;
		}
		return resultado;
	}*/

	public void rolbackPermisosByPerfil(Perfil perfil){
		if(perfil != null){
			if(perfil.getPermisos() != null){
				Iterator<Permiso> it = perfil.getPermisos().iterator();
				while(it.hasNext()){
					Permiso permiso = (Permiso)it.next();
					perfilDao.rolbackPermiso(perfil, permiso);
				}
			}
		}
	}


}

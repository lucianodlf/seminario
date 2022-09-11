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

import java.util.ArrayList;

import persistencia.DocumentoTipoPersonaDao;
import dominio.DocumentoTipoPersona;

public class DocumentoTipoPersonaLogica {

	private static DocumentoTipoPersonaLogica documentoTipoPersonaLogica = null;
	private DocumentoTipoPersonaDao documentoTipoPersonaDao = DocumentoTipoPersonaDao.getInstance();

	public static DocumentoTipoPersonaLogica getInstance(){
		if(documentoTipoPersonaLogica == null){
			documentoTipoPersonaLogica = new DocumentoTipoPersonaLogica();
		}
		return documentoTipoPersonaLogica;
	}

	public DocumentoTipoPersonaLogica() {

	}

	/*
	 * METODOS
	 */

	public boolean addDocumentoTipoPersona(DocumentoTipoPersona documentoTipoPersona){
		/*int rowAffected = documentoTipoPersonaDao.create(documentoTipoPersona);
		if (rowAffected != 0 ){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

	public DocumentoTipoPersona getDocumentoTipoPersona(int codigo){
			DocumentoTipoPersona documentoTipoPersona = documentoTipoPersonaDao.select(codigo);
			return documentoTipoPersona;
	}

	public ArrayList<DocumentoTipoPersona> getListDocumentoTipoPersona(){
			ArrayList<DocumentoTipoPersona> tiposDeDocumentos = documentoTipoPersonaDao.selectAll();
			return tiposDeDocumentos;
	}

	public boolean updateDocumentoTipoPersona(DocumentoTipoPersona documentoTipoPersona){
		/*if(documentoTipoPersona.getId() != 0){
			int rowAffected = documentoTipoPersonaDao.update(documentoTipoPersona);
			if(rowAffected != 0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}*/
		return true;
	}

	public boolean deleteDocumentoTipoPersona(int codigo){
		/*int rowAffected = documentoTipoPersonaDao.delete(codigo);
		if(rowAffected != 0){
			return true;
		}else{
			return false;
		}*/
		return true;
	}

}

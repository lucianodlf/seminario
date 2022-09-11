/*<Copyright� 2011 Luciano Delfino>
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
package complementos;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class MyPlainDocumentFilter extends PlainDocument{

	private JTextField editor;
	private int numeroMaximo;
	private String tipo;


	public MyPlainDocumentFilter(){

	}
	public MyPlainDocumentFilter(JComponent comp, int numeroMaximo, String tipo) {
		super();
		this.editor = (JTextField)comp;
		this.numeroMaximo = numeroMaximo;
		this.tipo = tipo;
	}

	@Override
	public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException {
		char c = arg1.charAt(0);
		boolean valido = false;
		if(tipo.equals("D")){
				if(!Character.isLetter(c)){
					valido = true;
				}else{
					 JOptionPane.showMessageDialog(null, "Solo se admiten letras ", "Error", JOptionPane.ERROR_MESSAGE);
				}
		}else if(tipo.equals("L")){
				if(Character.isLetter(c)){
					valido = true;
				}else{
					JOptionPane.showMessageDialog(null, "Solo se admiten n�meros ", "Error", JOptionPane.ERROR_MESSAGE);
				}
				arg1 = arg1.toUpperCase();
		}else if(tipo.equals(null)){
				valido = true;
				arg1 = arg1.toUpperCase();
		}
		if(valido){
			if(numeroMaximo != 0){
				if((editor.getText().length() + arg1.length()) > numeroMaximo){
					 JOptionPane.showMessageDialog(null, "Como m�ximo "+ numeroMaximo + " caracteres", "Fuera del rango", JOptionPane.ERROR_MESSAGE);
					 return;
				}else{
					//si no se supera el numero maximo se inserta el texto
					super.insertString(arg0, arg1, arg2);
				}
			}else{
				super.insertString(arg0, arg1, arg2);
			}

		}

	}




}


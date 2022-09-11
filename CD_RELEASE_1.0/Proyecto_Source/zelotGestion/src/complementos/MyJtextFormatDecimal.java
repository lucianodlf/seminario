/*<Copyright© 2011 Luciano Delfino>
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
package complementos;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

public class MyJtextFormatDecimal extends JFormattedTextField {

	private static final long serialVersionUID = 1L;
	private static MyJtextFormatDecimal myJtextFormatDecimal = null;

	public static MyJtextFormatDecimal getInstance(String tipo){

		myJtextFormatDecimal = new MyJtextFormatDecimal();
		NumberFormat visualFormat = null;
		NumberFormat editFormat = null;

		//formato visual
		if(tipo.equals("D")){

			visualFormat = NumberFormat.getInstance();
			//visualFormat = NumberFormat.getInstance();
			visualFormat.setMaximumFractionDigits(2);
		}else if(tipo.equals("P")){
			// visualFormat = NumberFormat.getPercentInstance();
			 //visualFormat.setMaximumFractionDigits(2);
		}

		//formato edicion
		 editFormat = NumberFormat.getInstance();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');

		//quita separadores de miles en la edicion
		editFormat.setGroupingUsed(false);
		//foormateadores
		NumberFormatter visualNumberFormat = new NumberFormatter(visualFormat);
		NumberFormatter editNumberFormat = new NumberFormatter(editFormat);
		//el formater de edicion permite caracteres incorrectos (por el punto)
		editNumberFormat.setAllowsInvalid(true);
		editNumberFormat.setOverwriteMode(true);
		visualNumberFormat.setAllowsInvalid(false);
		//factoria de formateadores especifia cual es de edicion y cual de visualizacion
		DefaultFormatterFactory numberFactory = new DefaultFormatterFactory(visualNumberFormat, visualNumberFormat, editNumberFormat);
		//se asigna la factory al campo
		myJtextFormatDecimal.setFormatterFactory(numberFactory);

		return myJtextFormatDecimal;

	}

	private MyJtextFormatDecimal() {
		super();
		// TODO Auto-generated constructor stub
	}












}

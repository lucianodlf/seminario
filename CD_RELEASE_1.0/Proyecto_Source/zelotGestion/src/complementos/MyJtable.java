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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MyJtable extends JTable{

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component component = super.prepareRenderer(renderer, row, column);
		if((row%2 == 0)){
			Color color = new Color(189,252,201);
			component.setBackground(color);
		}else{
			Color color = new Color(191,239,255);
			component.setBackground(color);
		}
		component.setForeground(Color.black);
		Font font = new Font(component.getFont().getName(), Font.BOLD, 12);
		component.setFont(font);
		return component;
	}


	public MyJtable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyJtable(int numRows, int numColumns) {
		super(numRows, numColumns);
		// TODO Auto-generated constructor stub
	}

	public MyJtable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}

	public MyJtable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		// TODO Auto-generated constructor stub
	}

	public MyJtable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		// TODO Auto-generated constructor stub
	}

	public MyJtable(TableModel dm) {
		super(dm);
		// TODO Auto-generated constructor stub
	}

	public MyJtable(Vector<?> rowData, Vector<?> columnNames) {
		super(rowData, columnNames);
		// TODO Auto-generated constructor stub
	}




}

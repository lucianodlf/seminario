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
package presentacion.InternalFrames;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logica.ListaPrecioLogica;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.ListaPrecio;

public class InternalFrameGestorListasPrecio extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanelLstaPrecios = null;
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableListaPrecio = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JLabel jLabelBusqueda = null;
	private JLabel jLabelCriterioBusqueda = null;
	private JTextField jTextFieldBusqueda = null;
	private JComboBox jComboBoxCriterioBusqueda = null;
	private JLabel jLabelCodigo = null;
	private JLabel jLabelDescripcion = null;
	private JLabel jLabelUtilidad = null;
	private JTextField jTextFieldCodigo = null;
	private JTextField jTextFieldDescripcion = null;
	private JCheckBox jCheckBoxAplicaAlicuota = null;

	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonActionAlta = null;
	private JButton jButtonActionModificacion = null;
	private JButton jButtonActionEliminar = null;
	private JButton jButtonActionOk = null;
	private JButton jButtonActionCancel = null;
	private JTextField jTextFieldPorcUtilidad = null;
	private JTextField jTextFieldPorcDescAdic = null;
	private JTextField jTextFieldPorcRecarAdic = null;
	private JLabel jLabelDescAdic = null;
	private JLabel jLabelRecarAdic = null;

	/*clases de logica*/
	private ListaPrecio listaPrecio = null;
	private ListaPrecioLogica listaPrecioLogica = ListaPrecioLogica.getInstance();  //  @jve:decl-index=0:
	private String activeActionType = null; //se utiliza para determina cual es la accion activa (agrega, modificar o eliminar)

	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:
	private DecimalFormat porcentFormat = new DecimalFormat("###.###");  //  @jve:decl-index=0:


	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameGestorListasPrecio() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(615, 355);
		this.setContentPane(getJPanelLstaPrecios());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setResizable(false);
		this.setTitle("Gestión de Listas de Precios");
		resetCampos();
		setEstatusAllComponent(false, true, true, true);
		setVisibleButtonsToolbar(true, true, true, false, false, true);
		setEnabledButtonsToolbar(true, false, false, false, false, true);
	}


	/**
	 * This method initializes jPanelLstaPrecios
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelLstaPrecios() {
		if (jPanelLstaPrecios == null) {
			jLabelRecarAdic = new JLabel();
			jLabelRecarAdic.setBounds(new Rectangle(350, 290, 70, 20));
			jLabelRecarAdic.setText("Rec. Adic: %");
			jLabelDescAdic = new JLabel();
			jLabelDescAdic.setBounds(new Rectangle(170, 290, 80, 20));
			jLabelDescAdic.setText("Desc. Adic: %");
			jPanelLstaPrecios = new JPanel();
			jPanelLstaPrecios.setLayout(null);
			jLabelUtilidad = new JLabel();
			jLabelUtilidad.setBounds(new Rectangle(5, 290, 65, 20));
			jLabelUtilidad.setText("Utilidad: %");
			jLabelDescripcion = new JLabel();
			jLabelDescripcion.setBounds(new Rectangle(170, 255, 75, 20));
			jLabelDescripcion.setText("Descripción:");
			jLabelCodigo = new JLabel();
			jLabelCodigo.setBounds(new Rectangle(05, 255, 50, 20));
			jLabelCodigo.setText("Código:");
			jLabelCriterioBusqueda = new JLabel();
			jLabelCriterioBusqueda.setBounds(new Rectangle(280, 40, 50, 20));
			jLabelCriterioBusqueda.setText("Criterio:");
			jLabelBusqueda = new JLabel();
			jLabelBusqueda.setBounds(new Rectangle(05, 40, 50, 20));
			jLabelBusqueda.setText("Buscar:");
			jPanelLstaPrecios.setLayout(null);
			jPanelLstaPrecios.add(getJScrollPaneaList(), null);
			jPanelLstaPrecios.add(jLabelBusqueda, null);
			jPanelLstaPrecios.add(jLabelCriterioBusqueda, null);
			jPanelLstaPrecios.add(getJTextFieldBusqueda(), null);
			jPanelLstaPrecios.add(getJComboBoxCriterioBusqueda(), null);
			jPanelLstaPrecios.add(jLabelCodigo, null);
			jPanelLstaPrecios.add(jLabelDescripcion, null);
			jPanelLstaPrecios.add(jLabelUtilidad, null);
			jPanelLstaPrecios.add(getJTextFieldCodigo(), null);
			jPanelLstaPrecios.add(getJTextFieldDescripcion(), null);
			jPanelLstaPrecios.add(getJCheckBoxAplicaAlicuota(), null);
			jPanelLstaPrecios.add(getJToolBar(), null);
			jPanelLstaPrecios.add(getJTextFieldPorcUtilidad(), null);
			jPanelLstaPrecios.add(getJTextFieldPorcDescAdic(), null);
			jPanelLstaPrecios.add(getJTextFieldPorcRecarAdic(), null);
			jPanelLstaPrecios.add(jLabelDescAdic, null);
			jPanelLstaPrecios.add(jLabelRecarAdic, null);
		}
		return jPanelLstaPrecios;
	}

	/**** ELEMENTOS JPANEL LISTA DE PRECIOS **********/

	/***************** ELEMENTOS DE LA TABLA ************************/
	/*
	 *Crea el ScrollPane para el listado de items
	 */
	private JScrollPane getJScrollPaneaList() {
		if (jScrollPaneaList == null) {
			jScrollPaneaList = new JScrollPane();
			jScrollPaneaList.setBounds(new Rectangle(5, 65, 590, 180));
			jScrollPaneaList.setViewportView(getJtableListaPrecio());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJtableListaPrecio() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableListaPrecio == null) {
			jTableListaPrecio = new MyJtable(getMyTableModel());
			jTableListaPrecio.setRowSorter(geTableRowSorter());
			jTableListaPrecio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableListaPrecio.setRowHeight(20);
			setItemsInJtable();
			jTableListaPrecio.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableListaPrecio.setAutoscrolls(true);
			jTableListaPrecio.setShowVerticalLines(true);
			jTableListaPrecio.setShowHorizontalLines(true);
			jTableListaPrecio.setVisible(true);
			jTableListaPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableListaPrecio.isEnabled()){
						listaPrecio = getSelectedElement();
						if(listaPrecio != null){
							setCamposSelectedElement(listaPrecio);
							setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
						}
					}
				}
			});
			jTableListaPrecio.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(jTableListaPrecio.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							listaPrecio = getSelectedElement();
							if(listaPrecio != null){
								setCamposSelectedElement(listaPrecio);
								setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
							}
						}
					}
				}
			});
		}
		return jTableListaPrecio;
	}

	/*
	 * devuelve el modelo de tabla para crear el jtable.
	 */
	private MyTableModel getMyTableModel(){
		if(myTableModel == null){
			myTableModel = new MyTableModel();
		}
		return myTableModel;
	}

	/* DEVUELVE EL RowSorter de la tabla. para organizar los elementos de la misma*/
	private TableRowSorter<TableModel> geTableRowSorter(){
		if(tableRowSorter == null){
			tableRowSorter = new TableRowSorter<TableModel>(getMyTableModel());
		}
		return tableRowSorter;
	}


	/**************ELEMENTOS DE SECCION DE BUSQUEDA EN LA TABLA *******************/

	private JTextField getJTextFieldBusqueda() {
		if (jTextFieldBusqueda == null) {
			jTextFieldBusqueda = new JTextField();
			jTextFieldBusqueda.setBounds(new Rectangle(60, 40, 180, 20));
			jTextFieldBusqueda.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					char c = e.getKeyChar();
							/*filtra la tabla por el jTextFieldBusqueda segun el criterio cuando se tipea cada tecla en el campo*/
							setFilterInTabla(jTextFieldBusqueda.getText() + String.valueOf(c), jComboBoxCriterioBusqueda.getSelectedIndex());
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == 127 || e.getKeyCode() == 8){
						/*filtra la tabla por el campo jTextFieldBusqueda y segun el criterio cuando se borra algun caracter o todo el campo*/
						setFilterInTabla(jTextFieldBusqueda.getText(), jComboBoxCriterioBusqueda.getSelectedIndex());
					}
				}
			});
		}
		return jTextFieldBusqueda;
	}

	private JComboBox getJComboBoxCriterioBusqueda() {
		if (jComboBoxCriterioBusqueda == null) {
			jComboBoxCriterioBusqueda = new JComboBox();
			jComboBoxCriterioBusqueda.setBounds(new Rectangle(340, 40, 180, 20));
			jComboBoxCriterioBusqueda
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if(!jTextFieldBusqueda.getText().isEmpty()){
								/*filtra la tabla por el campo de busqueda cuando se selecciona un criterio de busqueda*/
								setFilterInTabla(jTextFieldBusqueda.getText(), jComboBoxCriterioBusqueda.getSelectedIndex());
							}
						}
					});
			/*DEFINE LOS CRITERIOS DE BUSQUEDA*/
			jComboBoxCriterioBusqueda.addItem("Código");
			jComboBoxCriterioBusqueda.addItem("Descripción");
		}
		return jComboBoxCriterioBusqueda;
	}


	/************** ELEMENTOS PARA VISUALIZAR O EDITAR LOS ITEMS DE LA TABLA ***************/

	private JTextField getJTextFieldCodigo() {
		if (jTextFieldCodigo == null) {
			jTextFieldCodigo = new JTextField();
			jTextFieldCodigo.setBounds(new Rectangle(65, 255, 100, 20));

		}
		return jTextFieldCodigo;
	}

	private JTextField getJTextFieldDescripcion() {
		if (jTextFieldDescripcion == null) {
			jTextFieldDescripcion = new JTextField();
			jTextFieldDescripcion.setBounds(new Rectangle(255, 255, 200, 20));
			jTextFieldDescripcion.setDocument(new MyPlainDocument(jTextFieldDescripcion,50,null,true));
		}
		return jTextFieldDescripcion;
	}

	private JCheckBox getJCheckBoxAplicaAlicuota() {
		if (jCheckBoxAplicaAlicuota == null) {
			jCheckBoxAplicaAlicuota = new JCheckBox();
			jCheckBoxAplicaAlicuota.setBounds(new Rectangle(465, 255, 136, 20));
			jCheckBoxAplicaAlicuota.setText("Aplica Alicuota IVA");
		}
		return jCheckBoxAplicaAlicuota;
	}

	/**
	 * This method initializes jTextFieldPorcUtilidad
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPorcUtilidad() {
		if (jTextFieldPorcUtilidad == null) {
			jTextFieldPorcUtilidad = new JTextField();
			jTextFieldPorcUtilidad.setBounds(new Rectangle(75, 290, 80, 20));
			jTextFieldPorcUtilidad.setDocument(new MyPlainDocument(getJTextFieldPorcUtilidad(), 20, "D", true));
		}
		return jTextFieldPorcUtilidad;
	}

	/**
	 * This method initializes jTextFieldPorcDescAdic
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPorcDescAdic() {
		if (jTextFieldPorcDescAdic == null) {
			jTextFieldPorcDescAdic = new JTextField();
			jTextFieldPorcDescAdic.setBounds(new Rectangle(255, 290, 80, 20));
			jTextFieldPorcDescAdic.setDocument(new MyPlainDocument(getJTextFieldPorcDescAdic(), 20, "D", true));
		}
		return jTextFieldPorcDescAdic;
	}

	/**
	 * This method initializes jTextFieldPorcRecarAdic
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPorcRecarAdic() {
		if (jTextFieldPorcRecarAdic == null) {
			jTextFieldPorcRecarAdic = new JTextField();
			jTextFieldPorcRecarAdic.setBounds(new Rectangle(425, 290, 80, 20));
			jTextFieldPorcRecarAdic.setDocument(new MyPlainDocument(getJTextFieldPorcRecarAdic(), 20, "D", true));
		}
		return jTextFieldPorcRecarAdic;
	}

	/************ TRATAMIENTO SOBRE FORMATO DE DATOS ***********************/





	// RETORNA EL VALOR FLOAT DE LOS TEXTFIELD PARA PORCENTAJES
	private String getValuePorcentVisual(Float valor){
		decimalFormatSymbols.setDecimalSeparator('.');
		porcentFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		if(valor != null){
			System.out.println(porcentFormat.format(valor.doubleValue() * 100));
			return porcentFormat.format(valor.doubleValue() * 100);
		}else{
			return null;
		}
	}


	private Float getValuePorcentReal(String valor){
		try{
		decimalFormatSymbols.setDecimalSeparator('.');
		porcentFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		if(!valor.isEmpty()){
			System.out.println(porcentFormat.parse(valor).floatValue());
			return (porcentFormat.parse(valor).floatValue()) / 100;
		}else{
			return new Float(0);
		}
		}catch(ParseException pe){
			pe.printStackTrace();
			return new Float(0);
		}
	}

	/************** GESTION DE ITEMS DE LA TABLA *******************/

	public void setItemsInJtable(){
		ArrayList<ListaPrecio> listaPrecios = listaPrecioLogica.getListListaPrecio();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Descripción");
		titulos.add("Utilidad %");
		titulos.add("Aplica Alicuota");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(listaPrecios != null){
			Iterator<ListaPrecio> it = listaPrecios.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				listaPrecio = (ListaPrecio)it.next();
				renglon.add(String.valueOf(listaPrecio.getCodigoListaPrecios()));
				renglon.add(listaPrecio.getDescripcion());
				renglon.add(getValuePorcentVisual(listaPrecio.getUtilidad()));
				if(listaPrecio.isAplicaAlicuta()){
					renglon.add("SI");
				}else{
					renglon.add("NO");
				}
				registros.add(renglon);

			}
		}
		myTableModel.setDataVector(registros, titulos);
		//setea ancho de campos
		jTableListaPrecio.getColumnModel().getColumn(0).setPreferredWidth(8);
		jTableListaPrecio.getColumnModel().getColumn(1).setPreferredWidth(200);
		jTableListaPrecio.getColumnModel().getColumn(2).setPreferredWidth(20);
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	public ListaPrecio getSelectedElement(){
		try{
			int codigo = Integer.parseInt((String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableListaPrecio.getSelectedRow()), 0));
			listaPrecio = listaPrecioLogica.getListaPrecio(codigo);
			return listaPrecio;
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}

	/*
	 * Filtra la lista segun el texto buscado y el criterio de busqueda
	 */
	public void setFilterInTabla(String textBusqueda, int criterio){
		if(jTextFieldBusqueda.isEditable() && jTextFieldBusqueda.isEnabled() && jComboBoxCriterioBusqueda.isEnabled()){
			if(textBusqueda.isEmpty()){
				tableRowSorter.setRowFilter(null);
			}else{
				textBusqueda = textBusqueda.toUpperCase();
				if(criterio == 0){
					tableRowSorter.setRowFilter(RowFilter.regexFilter(textBusqueda, criterio));
				}else if(criterio == 1){
					tableRowSorter.setRowFilter(RowFilter.regexFilter(textBusqueda, criterio));
				}
			}
		}
	}

	/*setea los campos del detalle segun el elemento seleccionado en la tabla*/
	public void setCamposSelectedElement(ListaPrecio listaPrecio){
		jTextFieldCodigo.setText(String.valueOf(listaPrecio.getCodigoListaPrecios()));
		jTextFieldDescripcion.setText(listaPrecio.getDescripcion());
		jTextFieldPorcUtilidad.setText(getValuePorcentVisual(listaPrecio.getUtilidad()));
		jTextFieldPorcDescAdic.setText(getValuePorcentVisual(listaPrecio.getDescuentoAdicional()));
		jTextFieldPorcRecarAdic.setText(getValuePorcentVisual(listaPrecio.getRecargoAdicional()));
		jCheckBoxAplicaAlicuota.setSelected(listaPrecio.isAplicaAlicuta());

	}


	/************** GESTION DE ITEMS DE LA BASE DE DATOS Y DE LA TABLA ***********************/

	/*AGREGA UN NUEVO ITEM A LA BASE DE DATOS Y A LA TABLA*/
	public int addItem(){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldDescripcion.getText().isEmpty()){

					/*CREA EL OBJETO Y SETEA LOS VALORES*/
					listaPrecio = new ListaPrecio();
					listaPrecio.setDescripcion(jTextFieldDescripcion.getText());
					listaPrecio.setAplicaAlicuta(jCheckBoxAplicaAlicuota.isSelected());
					listaPrecio.setUtilidad(getValuePorcentReal(jTextFieldPorcUtilidad.getText()));
					listaPrecio.setRecargoAdicional(getValuePorcentReal(jTextFieldPorcRecarAdic.getText()));
					listaPrecio.setDescuentoAdicional(getValuePorcentReal(jTextFieldPorcDescAdic.getText()));


					/*EJECUTA EL ADD CON LA LOGICA Y EVALUA LA RESPUESTA*/
					if(listaPrecioLogica.addListaPrecio(listaPrecio)){
						/*SI FUE SATISFACTORIO EL ADD*/
						JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
						statusAction = 0;
					}else{
						/*SI OCURRIO UN ERROR EN EL ADD*/
						JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operacion!", "Error", JOptionPane.ERROR_MESSAGE);
						statusAction = 2;
					}
				}else{
					/*SI NO ESTAN COMPLETOS TODOS LOS CAMPOS OBLIGATORIOS PARA HACER EL ADD*/
					JOptionPane.showMessageDialog(null, "Debe completar los campos necesarios", "Error", JOptionPane.ERROR_MESSAGE);
					jTextFieldDescripcion.requestFocus(true);
					statusAction = 1;
				}
			return statusAction;
		}catch(Exception ex){
			ex.printStackTrace();
			return statusAction;
		}
	}


	/* HACE UPDATE DE UN ITEM SELECCIONADO EN LA TABLA*/
	public int updateItem(ListaPrecio listaPrecio){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldDescripcion.getText().isEmpty()){
				/*setea los nuevos valores al objeto seleccionado para modificar*/
				listaPrecio.setCodigoListaPrecios(Integer.parseInt(jTextFieldCodigo.getText()));
				listaPrecio.setDescripcion(jTextFieldDescripcion.getText());
				listaPrecio.setAplicaAlicuta(jCheckBoxAplicaAlicuota.isSelected());
				listaPrecio.setUtilidad(getValuePorcentReal(jTextFieldPorcUtilidad.getText()));
				listaPrecio.setRecargoAdicional(getValuePorcentReal(jTextFieldPorcRecarAdic.getText()));
				listaPrecio.setDescuentoAdicional(getValuePorcentReal(jTextFieldPorcDescAdic.getText()));

				/*EJECUTA EL UDPATE CON LA LOGICA*/
				if(listaPrecioLogica.updateListaPrecio(listaPrecio)){
					/*SI ES SATISFACTORIO EL UPDATE*/
					 JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
					 statusAction = 0;
				}else{
					/*SI SE TIENE UN ERROR EN EL UPDATE*/
					 JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación!", "Error", JOptionPane.ERROR_MESSAGE);
					 statusAction = 2;
				}
			}else{
				/*SI FALTAN LLENAR CAMPOS OBLIGATORIOS PARA HACER EL UPDATE*/
				JOptionPane.showMessageDialog(null, "Debe completar los campos necesarios", "Error", JOptionPane.ERROR_MESSAGE);
				statusAction = 1;
			}
			return statusAction;
		}catch(Exception ex){
			ex.printStackTrace();
			return statusAction;
		}
	}

	/*BORRA EL ELEMENTO SELECCIONADO DE LA TABLA*/
	public int deleteItem(ListaPrecio listaPrecio){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			JOptionPane.showMessageDialog(null, "La Lista de Precios Seleccionada Será Eliminada\n" +
					"Código: "+listaPrecio.getCodigoListaPrecios()+"\n" +
					"Descripción: "+listaPrecio.getDescripcion(),"Eliminar",JOptionPane.INFORMATION_MESSAGE);
			int selectedOption = JOptionPane.showConfirmDialog(null, "Desea Eliminar la Lista de Precios","Eliminar", JOptionPane.OK_CANCEL_OPTION);
			if(selectedOption == 0){
				/*EJECUTA EL DELETE CON LA LOGICA*/
				if(listaPrecioLogica.deleteListaPrecio(listaPrecio.getCodigoListaPrecios())){
					/*SI ES SATISFACTORIO EL UPDATE*/
					 JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
					 statusAction = 0;
				}else{
					/*SI SE TIENE UN ERROR EN EL UPDATE*/
					JOptionPane.showMessageDialog(null, "Ocurrio un Error en la Operación!", "Error", JOptionPane.ERROR_MESSAGE);
					statusAction = 2;
				}
			}
			return statusAction;
		}catch(Exception ex){
			ex.printStackTrace();
			return statusAction;
		}
	}


	/**************** BUTTONS DE TOLBAR  Y TOOLBAR ********************/
	/*DEVULEVE LA JTOOLBAR*/
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0,600, 30));
			jToolBar.setFloatable(false);
			jToolBar.addSeparator(new Dimension(10,10));
			jToolBar.add(getJButtonAlta());
			jToolBar.add(getJButtonModificacion());
			jToolBar.add(getJButtonEliminar());
			jToolBar.addSeparator(new Dimension(10,10));
			jToolBar.add(getJButtonActionOk());
			jToolBar.add(getJButtonActionCancel());
			jToolBar.add(getJButtonActionCloce());
			jToolBar.addSeparator(new Dimension(10,10));
		}
		/*Component component[] = jToolBar.getComponents();
		for(int i=0; i < component.length; i++){
			System.out.println(component[i].getClass());
			if(component[i].getClass() == JButton.class){
				JButton button = (JButton)component[i];
				System.out.println("Text: "+ button.getText());
			}
		}*/
		return jToolBar;
	}


	private JButton getJButtonActionOk() {
		if (jButtonActionOk == null) {
			jButtonActionOk = new JButton();
			jButtonActionOk.setText("OK");
			jButtonActionOk.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonActionOk.setBorderPainted(false);
			jButtonActionOk.setPreferredSize(new Dimension(100, 25));
			jButtonActionOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed() btnOK"); // TODO Auto-generated Event stub actionPerformed()
					if(activeActionType.equals("ADD")){
						//ejecuta el add de un item y evalua el resultado
						int statusAction = addItem();
						if(statusAction == 0){
							setItemsInJtable();
							resetCampos();
							setFilterInTabla("", 0);
							int selectedOption = JOptionPane.showConfirmDialog(null, "Desea agregar un nuevo elemento", "Operación Satisfactoria!", JOptionPane.OK_CANCEL_OPTION);
							System.out.println(selectedOption);
							if(selectedOption == 0){
								jTextFieldCodigo.setText(String.valueOf(listaPrecioLogica.getNewCodigoItems()));
								jTextFieldDescripcion.requestFocus(true);
							}else{
								activeActionType = null;
								setEnabledButtonsToolbar(true, false, false, false, false, true);
								setVisibleButtonsToolbar(true, true, true, false, false, true);
								setEstatusAllComponent(false, true, true, true);
								resetCampos();
							}
						}else if(statusAction == 2){
							setEstatusAllComponent(false, true, true, true);
							setEnabledButtonsToolbar(true, false, false, false, false, true);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							activeActionType = null;
							resetCampos();
						}
					}else if(activeActionType.equals("UPDATE")){
						int statusAction = updateItem(getSelectedElement());
						if(statusAction == 0){
							setItemsInJtable();
							activeActionType = null;
							setEnabledButtonsToolbar(true, false, false, false, false, true);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							setEstatusAllComponent(false, true, true, true);
							resetCampos();

						}else if(statusAction == 2){
							setEstatusAllComponent(false, true, true, true);
							setEnabledButtonsToolbar(true, false, false, false, false, true);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							activeActionType = null;
							resetCampos();
						}
					}else if(activeActionType.equals("DELETE")){

					}else if(activeActionType.equals(null)){

					}
				}
			});
		}
		return jButtonActionOk;
	}

	private JButton getJButtonActionCancel() {
		if (jButtonActionCancel == null) {
			jButtonActionCancel = new JButton();
			jButtonActionCancel.setText("Cancelar");
			jButtonActionCancel.setIcon(new ImageIcon(getClass().getResource("/Symbol-Stop_24x24-32.png")));
			jButtonActionCancel.setBorderPainted(false);
			jButtonActionCancel.setPreferredSize(new Dimension(100, 25));
			jButtonActionCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed() btbCancel"); // TODO Auto-generated Event stub actionPerformed()
						int selectedOption = JOptionPane.showConfirmDialog(null, "Desea Cancelar la Operación?", "Cancelar Operación", JOptionPane.OK_CANCEL_OPTION);
						System.out.println(selectedOption);
						if(selectedOption == 0){
							activeActionType = null;
							setEnabledButtonsToolbar(true, false, false, false, false, true);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							setEstatusAllComponent(false, true, true, true);
							resetCampos();
						}else{
							jTextFieldDescripcion.requestFocus(true);
						}
				}
			});
		}
		return jButtonActionCancel;
	}

	public JButton getJButtonActionCloce() {
		if (jButtonaActionCloce == null) {
			jButtonaActionCloce = new JButton();
			jButtonaActionCloce.setIcon(new ImageIcon(getClass().getResource("/Symbol-Delete_24x24-32.png")));
			jButtonaActionCloce.setText("Cerrar");
			jButtonaActionCloce.setName("Close");
			jButtonaActionCloce.setBorderPainted(false);
			jButtonaActionCloce.setPreferredSize(new Dimension(100, 30));
			jButtonaActionCloce.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					dispose();
				}
			});
		}

		return jButtonaActionCloce;
	}


	public JButton getJButtonAlta() {
		if (jButtonActionAlta == null) {
			jButtonActionAlta = new JButton();
			jButtonActionAlta.setText("Nuevo");
			jButtonActionAlta.setIcon(new ImageIcon(getClass().getResource("/Symbol-Add_24x24-32.png")));
			jButtonActionAlta.setBorderPainted(false);
			jButtonActionAlta.setPreferredSize(new Dimension(100, 25));
			jButtonActionAlta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Boton Alta"); // TODO Auto-generated Event stub actionPerformed()
					resetCampos();
					setEstatusAllComponent(true, false, false, false);
					setVisibleButtonsToolbar(true, true, true, true, true, true);
					setEnabledButtonsToolbar(false, false, false, true, true, false);
					jTextFieldCodigo.setText(String.valueOf(listaPrecioLogica.getNewCodigoItems()));
					jTextFieldCodigo.setEditable(false);
					activeActionType = "ADD";
				}
			});
		}
		return jButtonActionAlta;
	}


	public JButton getJButtonModificacion() {
		if (jButtonActionModificacion == null) {
			jButtonActionModificacion = new JButton();
			jButtonActionModificacion.setBorderPainted(false);
			jButtonActionModificacion.setIcon(new ImageIcon(getClass().getResource("/Edit_24x24-32.png")));
			jButtonActionModificacion.setText("Modificar");
			jButtonActionModificacion.setPreferredSize(new Dimension(100, 25));
			jButtonActionModificacion
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("Boton Modificar"); // TODO Auto-generated Event stub actionPerformed()
							//resetCampos();
							setEstatusAllComponent(true, false, false, false);
							setVisibleButtonsToolbar(true, true, true, true, true, true);
							setEnabledButtonsToolbar(false, false, false, true, true, false);
							//jTextFieldCodigo.setText(String.valueOf(listaPrecioLogica.getNewCodigoItems()));
							jTextFieldCodigo.setEditable(false);
							activeActionType = "UPDATE";
						}
					});
		}
		return jButtonActionModificacion;
	}

	public JButton getJButtonEliminar() {
		if (jButtonActionEliminar == null) {
			jButtonActionEliminar = new JButton();
			jButtonActionEliminar.setBorderPainted(false);
			jButtonActionEliminar.setIcon(new ImageIcon(getClass().getResource("/Cut_24x24-32.png")));
			jButtonActionEliminar.setText("Eliminar");
			jButtonActionEliminar.setPreferredSize(new Dimension(100, 25));
			jButtonActionEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Boton Eliminar"); // TODO Auto-generated Event stub actionPerformed()
					setEstatusAllComponent(false, false, false, false);
					setVisibleButtonsToolbar(true, true, true, false, false, true);
					setEnabledButtonsToolbar(false, false, false, false, false, false);
					int statusAction = deleteItem(getSelectedElement());
					if(statusAction == 0){
						setItemsInJtable();
						setEnabledButtonsToolbar(true, false, false, false, false, true);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						setEstatusAllComponent(false, true, true, true);
						resetCampos();
					}else{
						setEnabledButtonsToolbar(true, false, false, false, false, true);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						setEstatusAllComponent(false, true, true, true);
						resetCampos();
					}
				}
			});
		}
		return jButtonActionEliminar;
	}


	/********** ACTIONES CON BUTTONS DE TOOLBAR ****************/

	/*habilita o desabilita los botones de la toolbar*/
	public void setEnabledButtonsToolbar(boolean btnNuevo, boolean btnModificar, boolean btnEliminar, boolean btnOk, boolean btnCancel, boolean btnCloce){
		jButtonActionAlta.setEnabled(btnNuevo);
		jButtonActionEliminar.setEnabled(btnEliminar);
		jButtonActionModificacion.setEnabled(btnModificar);
		jButtonActionOk.setEnabled(btnOk);
		jButtonActionCancel.setEnabled(btnCancel);
		jButtonaActionCloce.setEnabled(btnCloce);
	}

	/*muestra o oculta los botonoes de la toolbar*/
	public void setVisibleButtonsToolbar(boolean btnNuevo, boolean btnModificar, boolean btnEliminar, boolean btnOk, boolean btnCancel, boolean btnCloce){
		jButtonActionAlta.setVisible(btnNuevo);
		jButtonActionEliminar.setVisible(btnEliminar);
		jButtonActionModificacion.setVisible(btnModificar);
		jButtonActionOk.setVisible(btnOk);
		jButtonActionCancel.setVisible(btnCancel);
		jButtonaActionCloce.setVisible(btnCloce);
	}

	/*setea el estado (enable o editable) de todos los componenetes*/
	public void setEstatusAllComponent(boolean estado, boolean estadoTextBusqueda, boolean estadoComboBoxBusqueda, boolean estadoTableList){
		Component component[] = jPanelLstaPrecios.getComponents();
		for(int i = 0; i < component.length;i++){
			if(component[i].getClass() == JTextField.class){
				((JTextField)component[i]).setEditable(estado);
			}else if(component[i].getClass() == JFormattedTextField.class){
				((JFormattedTextField)component[i]).setEditable(estado);
			}else if(component[i].getClass() == JButton.class){
				((JButton)component[i]).setEnabled(estado);
			}else if(component[i].getClass() == MyJtextFormatDecimal.class){
				((MyJtextFormatDecimal)component[i]).setEditable(estado);
			}else if(component[i].getClass() == JTextArea.class){
				((JTextArea)component[i]).setEditable(estado);
			}else if(component[i].getClass() == JCheckBox.class){
				((JCheckBox)component[i]).setEnabled(estado);
			}
		}
		jComboBoxCriterioBusqueda.setEnabled(estadoComboBoxBusqueda);
		jTextFieldBusqueda.setEditable(estadoTextBusqueda);
		jTableListaPrecio.setEnabled(estadoTableList);
	}

	/*resetea todos los campos*/
	public void resetCampos(){
		Component component[] = jPanelLstaPrecios.getComponents();
		int indice = 0;
		for(int i = 0; i < component.length;i++){
			if(component[i].getClass() == JTextField.class){
				if(indice == 0) indice = i;
				((JTextField)component[i]).setText(null);
			}else if(component[i].getClass() == JFormattedTextField.class){
				((JFormattedTextField)component[i]).setText(null);
			}else if(component[i].getClass() == MyJtextFormatDecimal.class){
				NumberFormat nf = NumberFormat.getPercentInstance();
				((MyJtextFormatDecimal)component[i]).setText(nf.format(0));
			}else if(component[i].getClass() == JTextArea.class){
				((JTextArea)component[i]).setText(null);
			}else if(component[i].getClass() == JCheckBox.class){
				((JCheckBox)component[i]).setSelected(false);
			}
		}
		setFilterInTabla("", 0);
	}



}  //  @jve:decl-index=0:visual-constraint="2,80"

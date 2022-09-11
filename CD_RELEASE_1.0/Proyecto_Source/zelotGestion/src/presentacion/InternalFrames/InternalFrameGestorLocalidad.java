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
import java.awt.Frame;
import java.awt.Rectangle;
import java.net.URL;
import java.text.NumberFormat;
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

import logica.LocalidadLogica;
import logica.ProvinciaLogica;
import presentacion.Dialog.DialogGestorProvincia;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Localidad;
import dominio.Provincia;

public class InternalFrameGestorLocalidad extends JInternalFrame {

	private JPanel jContentPane = null;
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableLocalidad = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JLabel jLabelBusqueda = null;
	private JLabel jLabelCriterioBusqueda = null;
	private JTextField jTextFieldBusqueda = null;
	private JComboBox jComboBoxCriterioBusqueda = null;
	private JLabel jLabelCodigo = null;
	private JLabel jLabelDescripcion = null;
	private JTextField jTextFieldCodigo = null;
	private JTextField jTextFieldDescripcion = null;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonActionAlta = null;
	private JButton jButtonActionModificacion = null;
	private JButton jButtonActionEliminar = null;
	private JButton jButtonActionOk = null;
	private JButton jButtonActionCancel = null;
	private JLabel jLabelProvincia = null;
	private JComboBox jComboBoxProvincia = null;
	private JLabel jLabelCodPost = null;
	private JTextField jTextFieldCodPost = null;

	/*clases de logica*/
	private Localidad localidad = null;
	private LocalidadLogica localidadLogica = LocalidadLogica.getInstance();  //  @jve:decl-index=0:
	private ProvinciaLogica provinciaLogica = ProvinciaLogica.getInstance();
	private String activeActionType = null; //se utiliza para determina cual es la accion activa (agrega, modificar o eliminar)
	private JButton jButtonAddProvincia = null;



	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameGestorLocalidad() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(610, 345);
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Gestión de Localidades");
		resetCampos();
		setEstatusAllComponent(false, true, true, true, false);
		setVisibleButtonsToolbar(true, true, true, false, false, true);
		setEnabledButtonsToolbar(true, false, false, false, false, true);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelCodPost = new JLabel();
			jLabelCodPost.setBounds(new Rectangle(402, 285, 85, 20));
			jLabelCodPost.setText("Código Postal:");
			jLabelProvincia = new JLabel();
			jLabelProvincia.setBounds(new Rectangle(5, 285, 60, 20));
			jLabelProvincia.setText("Provincia:");
			jContentPane = new JPanel();
			jLabelDescripcion = new JLabel();
			jLabelDescripcion.setBounds(new Rectangle(225, 255, 75, 20));
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
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPaneaList(), null);
			jContentPane.add(jLabelBusqueda, null);
			jContentPane.add(jLabelCriterioBusqueda, null);
			jContentPane.add(getJTextFieldBusqueda(), null);
			jContentPane.add(getJComboBoxCriterioBusqueda(), null);
			jContentPane.add(jLabelCodigo, null);
			jContentPane.add(jLabelDescripcion, null);
			jContentPane.add(getJTextFieldCodigo(), null);
			jContentPane.add(getJTextFieldDescripcion(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabelProvincia, null);
			jContentPane.add(getJComboBoxProvincia(), null);
			jContentPane.add(jLabelCodPost, null);
			jContentPane.add(getJTextFieldCodPost(), null);
			jContentPane.add(getJButtonAddProvincia(), null);
		}
		return jContentPane;
	}

	/***************** ELEMENTOS DE LA TABLA ************************/
	/*
	 *Crea el ScrollPane para el listado de items
	 */
	private JScrollPane getJScrollPaneaList() {
		if (jScrollPaneaList == null) {
			jScrollPaneaList = new JScrollPane();
			jScrollPaneaList.setBounds(new Rectangle(5, 65, 590, 180));
			jScrollPaneaList.setViewportView(getJtableLocalidad());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJtableLocalidad() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableLocalidad == null) {
			jTableLocalidad = new MyJtable(getMyTableModel());
			jTableLocalidad.setRowSorter(geTableRowSorter());
			jTableLocalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableLocalidad.setRowHeight(20);
			setItemsInJtable();
			jTableLocalidad.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableLocalidad.setAutoscrolls(true);
			jTableLocalidad.setShowVerticalLines(true);
			jTableLocalidad.setShowHorizontalLines(true);
			jTableLocalidad.setVisible(true);
			jTableLocalidad.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableLocalidad.isEnabled()){
						localidad = getSelectedElement();
						if(localidad != null){
							setCamposSelectedElement(localidad);
							setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
						}
					}
				}
			});
			jTableLocalidad.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(jTableLocalidad.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							localidad = getSelectedElement();
							if(localidad != null){
								setCamposSelectedElement(localidad);
								setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
							}
						}
					}
				}
			});
		}
		return jTableLocalidad;
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
			jComboBoxCriterioBusqueda.addItem("Nombre");
			jComboBoxCriterioBusqueda.addItem("Cód. Post.");
			jComboBoxCriterioBusqueda.addItem("Provincia");
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
			jTextFieldDescripcion.setBounds(new Rectangle(310, 255, 200, 20));
			jTextFieldDescripcion.setDocument(new MyPlainDocument(jTextFieldDescripcion,50,null,true));
		}
		return jTextFieldDescripcion;
	}

	/**
	 * This method initializes jComboBoxProvincia
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxProvincia() {
		if (jComboBoxProvincia == null) {
			jComboBoxProvincia = new JComboBox();
			jComboBoxProvincia.setBounds(new Rectangle(85, 285, 150, 20));
			jComboBoxProvincia.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			setItemjComboBoxProvincia();
		}
		return jComboBoxProvincia;
	}

	/**
	 * This method initializes jTextFieldCodPost
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCodPost() {
		if (jTextFieldCodPost == null) {
			jTextFieldCodPost = new JTextField();
			jTextFieldCodPost.setBounds(new Rectangle(497, 285, 50, 20));
			jTextFieldCodPost.setDocument(new MyPlainDocument(getJTextFieldCodPost(), 50, null, true));
		}
		return jTextFieldCodPost;
	}

	/************* GESTION DE COMBO BOX ****************/

	private void setItemjComboBoxProvincia(){
		jComboBoxProvincia.removeAllItems();
		ArrayList<Provincia> provincias = provinciaLogica.getListProvincias();
		Iterator<Provincia> it = provincias.iterator();
		while(it.hasNext()){
			jComboBoxProvincia.addItem((Provincia)it.next());
		}
	}

	private void selectItemjComboBoxProvincia(Provincia provincia){
		for(int i = 0; i < jComboBoxProvincia.getItemCount(); i++){
			Provincia provin = (Provincia)jComboBoxProvincia.getItemAt(i);
			if(provincia.getCodigo() == provin.getCodigo()){
				jComboBoxProvincia.setSelectedItem(provin);
				break;
			}
		}
	}


	/************** GESTION DE ITEMS DE LA TABLA *******************/

	public void setItemsInJtable(){
		ArrayList<Localidad> localidads = localidadLogica.getListLocalidad();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Nombre");
		titulos.add("Cód. Post.");
		titulos.add("Provincia");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(localidads != null){
			Iterator<Localidad> it = localidads.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				localidad = (Localidad)it.next();
				renglon.add(String.valueOf(localidad.getCodigo()));
				renglon.add(localidad.getNombre());
				renglon.add(localidad.getCodigoPostal());
				renglon.add(localidad.getProvincia().toString());
				registros.add(renglon);
			}
		}
		myTableModel.setDataVector(registros, titulos);
		jTableLocalidad.getColumnModel().getColumn(0).setPreferredWidth(8);
		jTableLocalidad.getColumnModel().getColumn(1).setPreferredWidth(300);
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	public Localidad getSelectedElement(){
		try{
			int codigo = Integer.parseInt((String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableLocalidad.getSelectedRow()), 0));
			localidad = localidadLogica.getLocalidad(codigo);
			return localidad;
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
				tableRowSorter.setRowFilter(RowFilter.regexFilter(textBusqueda, criterio));
			}
		}
	}

	/*setea los campos del detalle segun el elemento seleccionado en la tabla*/
	public void setCamposSelectedElement(Localidad localidad){
		jTextFieldCodigo.setText(String.valueOf(localidad.getCodigo()));
		jTextFieldDescripcion.setText(localidad.getNombre());
		jTextFieldCodPost.setText(localidad.getCodigoPostal());
		selectItemjComboBoxProvincia(localidad.getProvincia());
	}


	/************** GESTION DE ITEMS DE LA BASE DE DATOS Y DE LA TABLA ***********************/

	/*AGREGA UN NUEVO ITEM A LA BASE DE DATOS Y A LA TABLA*/
	public int addItem(){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldDescripcion.getText().isEmpty()){

					/*CREA EL OBJETO Y SETEA LOS VALORES*/
					Localidad localidad = new Localidad();
					localidad.setNombre(jTextFieldDescripcion.getText());
					localidad.setCodigoPostal(jTextFieldCodPost.getText());
					localidad.setProvincia((Provincia)jComboBoxProvincia.getSelectedItem());


					/*EJECUTA EL ADD CON LA LOGICA Y EVALUA LA RESPUESTA*/
					if(localidadLogica.addLocalidad(localidad)){
						/*SI FUE SATISFACTORIO EL ADD*/
						JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
						statusAction = 0;
					}else{
						/*SI OCURRIO UN ERROR EN EL ADD*/
						JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación!", "Error", JOptionPane.ERROR_MESSAGE);
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
	public int updateItem(Localidad localidad){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(jTextFieldDescripcion.getText().length() != 0){
				/*setea los nuevos valores al objeto seleccionado para modificar*/
				localidad.setCodigo(Integer.parseInt(jTextFieldCodigo.getText()));
				localidad.setNombre(jTextFieldDescripcion.getText());
				localidad.setCodigoPostal(jTextFieldCodPost.getText());
				localidad.setProvincia((Provincia)jComboBoxProvincia.getSelectedItem());

				/*EJECUTA EL UDPATE CON LA LOGICA*/
				if(localidadLogica.updateLocalidad(localidad)){
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
	public int deleteItem(Localidad localidad){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			JOptionPane.showMessageDialog(null, "La Localidad Seleccionada Será Eliminada\n" +
					"Código: "+localidad.getCodigo()+"\n" +
					"Descripción: "+localidad.getNombre(),"Eliminar",JOptionPane.INFORMATION_MESSAGE);
			int selectedOption = JOptionPane.showConfirmDialog(null, "Desea Eliminar la Localidad","Eliminar", JOptionPane.OK_CANCEL_OPTION);
			if(selectedOption == 0){
				/*EJECUTA EL DELETE CON LA LOGICA*/
				if(localidadLogica.deleteLocalidad(localidad.getCodigo())){
					/*SI ES SATISFACTORIO EL UPDATE*/
					 JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
					 statusAction = 0;
				}else{
					/*SI SE TIENE UN ERROR EN EL UPDATE*/
					JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación!", "Error", JOptionPane.ERROR_MESSAGE);
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
								jTextFieldCodigo.setText(String.valueOf(localidadLogica.getNewCodigoItems()));
								jTextFieldDescripcion.requestFocus(true);
							}else{
								activeActionType = null;
								setEnabledButtonsToolbar(true, false, false, false, false, true);
								setVisibleButtonsToolbar(true, true, true, false, false, true);
								setEstatusAllComponent(false, true, true, true, true);
								resetCampos();
							}
						}else if(statusAction == 2){
							setEstatusAllComponent(false, true, true, true, true);
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
							setEstatusAllComponent(false, true, true, true, true);
							resetCampos();

						}else if(statusAction == 2){
							setEstatusAllComponent(false, true, true, true, true);
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
							setEstatusAllComponent(false, true, true, true, true);
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
					System.out.println("Cloce()"); // TODO Auto-generated Event stub actionPerformed()
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
					setEstatusAllComponent(true, false, false, false, false);
					setVisibleButtonsToolbar(true, true, true, true, true, true);
					setEnabledButtonsToolbar(false, false, false, true, true, false);
					jTextFieldCodigo.setText(String.valueOf(localidadLogica.getNewCodigoItems()));
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
							setEstatusAllComponent(true, false, false, false, false);
							setVisibleButtonsToolbar(true, true, true, true, true, true);
							setEnabledButtonsToolbar(false, false, false, true, true, false);
							//jTextFieldCodigo.setText(String.valueOf(localidadLogica.getNewCodigoItems()));
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
					setEstatusAllComponent(false, false, false, false, false);
					setVisibleButtonsToolbar(true, true, true, false, false, true);
					setEnabledButtonsToolbar(false, false, false, false, false, false);
					int statusAction = deleteItem(getSelectedElement());
					if(statusAction == 0){
						setItemsInJtable();
						setEnabledButtonsToolbar(true, false, false, false, false, true);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						setEstatusAllComponent(false, true, true, true, true);
						resetCampos();
					}else{
						setEnabledButtonsToolbar(true, false, false, false, false, true);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						setEstatusAllComponent(false, true, true, true, true);
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
	public void setEstatusAllComponent(boolean estado, boolean estadoTextBusqueda, boolean estadoComboBoxBusqueda, boolean estadoTableList, boolean estadoBtnSeleccionar){
		Component component[] = getContentPane().getComponents();
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
		jTableLocalidad.setEnabled(estadoTableList);
	}

	/*resetea todos los campos*/
	public void resetCampos(){
		Component component[] = getContentPane().getComponents();
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
		//component[indice].requestFocus();
		setFilterInTabla("", 0);
	}

	/**
	 * This method initializes jButtonAddProvincia
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddProvincia() {
		if (jButtonAddProvincia == null) {
			jButtonAddProvincia = new JButton();
			jButtonAddProvincia.setBounds(new Rectangle(241, 285, 145, 20));
			jButtonAddProvincia.setText("Agregar Provincia");
			jButtonAddProvincia.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Provincia()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorProvincia dialogGestorProvincia = new DialogGestorProvincia(new Frame());
					dialogGestorProvincia.setModal(true);
					dialogGestorProvincia.setVisible(true);

					setItemjComboBoxProvincia();
				}
			});
		}
		return jButtonAddProvincia;
	}



}  //  @jve:decl-index=0:visual-constraint="10,10"

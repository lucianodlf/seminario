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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
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
import javax.swing.text.MaskFormatter;

import logica.CajaLogica;
import logica.ClienteLogica;
import logica.MovimientoCajaLogica;
import presentacion.Dialog.DialogGestorCliente;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Caja;
import dominio.Cliente;
import dominio.MovimientoCaja;
import dominio.TipoMovimientoCaja;

public class InternalFrameMovimientoCaja extends JInternalFrame {

	private JPanel jContentPane = null;
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableMovCaja = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JLabel jLabelBusqueda = null;
	private JLabel jLabelCriterioBusqueda = null;
	private JTextField jTextFieldBusqueda = null;
	private JComboBox jComboBoxCriterioBusqueda = null;
	private JLabel jLabelSujetoEntidad = null;
	private JLabel jLabelConcepto = null;
	private JTextField jTextFieldSujetoEntidad = null;
	private JTextField jTextFieldConcepto = null;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonActionAlta = null;
	private JButton jButtonActionModificacion = null;
	private JButton jButtonActionEliminar = null;
	private JButton jButtonActionOk = null;
	private JButton jButtonActionCancel = null;
	private JTextField jTextFieldImporte = null;
	private JLabel jLabelImporte = null;
	private JLabel jLabelFecha = null;
	private JFormattedTextField jTextFieldFechaCaja = null;
	private MaskFormatter maskFormatterFechaCaja = null;
	private JComboBox jComboBoxTipoMovimiento = null;
	private JLabel jLabelTipoMovimiento = null;
	private JLabel jLabelSaldo = null;
	private JLabel jLabelMontoInicial = null;
	/*clases de logica*/
	private Caja caja = null;  //  @jve:decl-index=0:
	private CajaLogica cajaLogica = CajaLogica.getInstance();  //  @jve:decl-index=0:
	private String activeActionType = null; //se utiliza para determina cual es la accion activa (agrega, modificar o eliminar)
	private MovimientoCaja movimientoCaja = null;
	private MovimientoCajaLogica movimientoCajaLogica = MovimientoCajaLogica.getInstance();  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();

	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private ClienteLogica clietClienteLogica = ClienteLogica.getInstance();  //  @jve:decl-index=0:
	private Cliente cliente = null;  //  @jve:decl-index=0:
	private JButton jButtonCliente = null;
	private JLabel jLabelSaldoFinal = null;
	private JLabel jLabelSaldoInicial = null;
	private JButton jButtonaActionRefresh = null;
	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameMovimientoCaja(Caja c) {
		super();
		initialize(c);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize(Caja c) {
		this.setSize(847, 524);
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Movimientos de Caja");
		resetCampos();
		setEstatusAllComponent(false, true, true, true, false);
		setVisibleButtonsToolbar(true, true, true, false, false, true);
		setEnabledButtonsToolbar(true, false, false, false, false, true);
		setDatosCaja(c);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelSaldoInicial = new JLabel();
			jLabelSaldoInicial.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelSaldoInicial.setBounds(new Rectangle(630, 455, 195, 28));
			jLabelSaldoInicial.setText("");
			jLabelSaldoFinal = new JLabel();
			jLabelSaldoFinal.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelSaldoFinal.setBounds(new Rectangle(630, 396, 195, 50));
			jLabelSaldoFinal.setText("");
			jLabelMontoInicial = new JLabel();
			jLabelMontoInicial.setBounds(new Rectangle(550, 457, 80, 20));
			jLabelMontoInicial.setText("Monto Inicial:");
			jLabelSaldo = new JLabel();
			jLabelSaldo.setBounds(new Rectangle(550, 403, 77, 32));
			jLabelSaldo.setText("<html><b><font size='6'>Saldo:</font></b></html>");
			jLabelTipoMovimiento = new JLabel();
			jLabelTipoMovimiento.setBounds(new Rectangle(5, 397, 100, 20));
			jLabelTipoMovimiento.setText("Tipo Movimiento:");
			jLabelFecha = new JLabel();
			jLabelFecha.setBounds(new Rectangle(539, 40, 90, 20));
			jLabelFecha.setText("Fecha de Caja:");
			jLabelImporte = new JLabel();
			jLabelImporte.setBounds(new Rectangle(350, 397, 50, 20));
			jLabelImporte.setText("Importe:");
			jContentPane = new JPanel();
			jLabelConcepto = new JLabel();
			jLabelConcepto.setBounds(new Rectangle(5, 432, 60, 20));
			jLabelConcepto.setText("Concepto:");
			jLabelSujetoEntidad = new JLabel();
			jLabelSujetoEntidad.setBounds(new Rectangle(5, 464, 93, 20));
			jLabelSujetoEntidad.setText("Sujeto / Entidad:");
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
			jContentPane.add(jLabelSujetoEntidad, null);
			jContentPane.add(jLabelConcepto, null);
			jContentPane.add(getJTextFieldSujetoEntidad(), null);
			jContentPane.add(getJTextFieldConcepto(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJTextFieldImporte(), null);
			jContentPane.add(jLabelImporte, null);
			jContentPane.add(jLabelFecha, null);
			jContentPane.add(getJTextFieldFechaCaja(), null);
			jContentPane.add(getJComboBoxTipoMovimiento(), null);
			jContentPane.add(jLabelTipoMovimiento, null);
			jContentPane.add(jLabelSaldo, null);
			jContentPane.add(jLabelMontoInicial, null);
			jContentPane.add(getJButtonCliente(), null);
			jContentPane.add(jLabelSaldoFinal, null);
			jContentPane.add(jLabelSaldoInicial, null);
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
			jScrollPaneaList.setBounds(new Rectangle(5, 71, 821, 318));
			jScrollPaneaList.setViewportView(getJTableMovCaja());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJTableMovCaja() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableMovCaja == null) {
			jTableMovCaja = new MyJtable(getMyTableModel());
			jTableMovCaja.setRowSorter(geTableRowSorter());
			jTableMovCaja.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableMovCaja.setRowHeight(20);
			jTableMovCaja.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableMovCaja.setAutoscrolls(true);
			jTableMovCaja.setShowVerticalLines(true);
			jTableMovCaja.setShowHorizontalLines(true);
			jTableMovCaja.setVisible(true);
			jTableMovCaja.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableMovCaja.isEnabled()){
						movimientoCaja = getSelectedElement();
						if(movimientoCaja != null){
							setCamposMovimientoCaja(movimientoCaja);
							setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
						}
					}
				}
			});
			jTableMovCaja.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(jTableMovCaja.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							movimientoCaja = getSelectedElement();
							if(movimientoCaja != null){
								setCamposMovimientoCaja(movimientoCaja);
								setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
							}
						}
					}
				}
			});
		}
		return jTableMovCaja;
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
			jComboBoxCriterioBusqueda.addItem("Código");
			jComboBoxCriterioBusqueda.addItem("Fecha");
			jComboBoxCriterioBusqueda.addItem("Tipo Movimiento");
			jComboBoxCriterioBusqueda.addItem("Concepto");
			jComboBoxCriterioBusqueda.setSelectedIndex(1);
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


		}
		return jComboBoxCriterioBusqueda;
	}


	/************** ELEMENTOS PARA VISUALIZAR O EDITAR LOS ITEMS DE LA TABLA ***************/

	private JTextField getJTextFieldSujetoEntidad() {
		if (jTextFieldSujetoEntidad == null) {
			jTextFieldSujetoEntidad = new JTextField();
			jTextFieldSujetoEntidad.setBounds(new Rectangle(110, 464, 300, 20));

		}
		return jTextFieldSujetoEntidad;
	}

	private JTextField getJTextFieldConcepto() {
		if (jTextFieldConcepto == null) {
			jTextFieldConcepto = new JTextField();
			jTextFieldConcepto.setBounds(new Rectangle(76, 432, 434, 20));
			jTextFieldConcepto.setDocument(new MyPlainDocument(jTextFieldConcepto,50,null,true));
		}
		return jTextFieldConcepto;
	}
	/**
	 * This method initializes jTextFieldImporte
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldImporte() {
		if (jTextFieldImporte == null) {
			jTextFieldImporte = new JTextField();
			jTextFieldImporte.setBounds(new Rectangle(410, 397, 100, 20));
			jTextFieldImporte.setDocument(new MyPlainDocument(getJTextFieldImporte(), 20, "D", true));

			jTextFieldImporte.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(!jTextFieldImporte.getText().isEmpty()) jTextFieldImporte.setText(getValueDecimalVisual(Float.valueOf(jTextFieldImporte.getText())));
				}
			});
		}
		return jTextFieldImporte;
	}

	/**
	 * This method initializes maskFormatterFechaCaja
	 *
	 * @return javax.swing.text.MaskFormatter
	 */
	private MaskFormatter getMaskFormatterFechaCaja() {
		if (maskFormatterFechaCaja == null) {
			try {
				maskFormatterFechaCaja = new MaskFormatter("##/##/####");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maskFormatterFechaCaja.setPlaceholderCharacter('_');
		}
		return maskFormatterFechaCaja;
	}

	/**
	 * This method initializes jTextFieldFechaCaja
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getJTextFieldFechaCaja() {
		if (jTextFieldFechaCaja == null) {
			jTextFieldFechaCaja = new JFormattedTextField(getMaskFormatterFechaCaja());
			jTextFieldFechaCaja.setBounds(new Rectangle(635, 40, 70, 20));
		}
		return jTextFieldFechaCaja;
	}

	/**
	 * This method initializes jComboBoxTipoMovimiento
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxTipoMovimiento() {
		if (jComboBoxTipoMovimiento == null) {
			jComboBoxTipoMovimiento = new JComboBox();
			jComboBoxTipoMovimiento.setBounds(new Rectangle(110, 397, 220, 20));
		}
		setItemsjComboBoxTipoMovimiento();
		return jComboBoxTipoMovimiento;
	}

	/************** GESTION DE ITEMS DE LA TABLA *******************/

	public void setItemsInJtable(ArrayList<MovimientoCaja> movimientosCaja){
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Fecha");
		titulos.add("Tipo");
		titulos.add("Concepto");
		titulos.add("Cta. Cta.");
		titulos.add("Ingreso");
		titulos.add("Egreso");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(movimientosCaja != null){
			Iterator<MovimientoCaja> it = movimientosCaja.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				movimientoCaja = (MovimientoCaja)it.next();

				renglon.add(String.valueOf(movimientoCaja.getCodigoMovimiento()));

				try {
					renglon.add(dateFormatArgen.format(dateFormatJapan.parse(movimientoCaja.getFechaMovimiento())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				renglon.add(movimientoCaja.getTipoMovimiento().getDescripcion());
				renglon.add(movimientoCaja.getConcepto());

				if(movimientoCaja.getTipoMovimiento().isSuma()){
					renglon.add(null);
					renglon.add("$ "+getValueDecimalVisual(movimientoCaja.getImporteMovimiento()));
					renglon.add(null);
				}else if(movimientoCaja.getTipoMovimiento().isResta()){
					renglon.add(null);
					renglon.add(null);
					renglon.add("$ "+getValueDecimalVisual(movimientoCaja.getImporteMovimiento()));
				}else{
					renglon.add("$ "+getValueDecimalVisual(movimientoCaja.getImporteMovimiento()));
					renglon.add(null);
					renglon.add(null);
				}
				registros.add(renglon);
			}
		}
		myTableModel.setDataVector(registros, titulos);
		getJTableMovCaja().getColumnModel().getColumn(0).setPreferredWidth(0);
		getJTableMovCaja().getColumnModel().getColumn(1).setPreferredWidth(8);
		getJTableMovCaja().getColumnModel().getColumn(2).setPreferredWidth(40);
		getJTableMovCaja().getColumnModel().getColumn(3).setPreferredWidth(250);
		getJTableMovCaja().getColumnModel().getColumn(4).setPreferredWidth(8);
		getJTableMovCaja().getColumnModel().getColumn(5).setPreferredWidth(8);
		getJTableMovCaja().getColumnModel().getColumn(6).setPreferredWidth(8);

		setOcultarColumnasJTable(jTableMovCaja, new int[]{0});
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	public MovimientoCaja getSelectedElement(){
		try{
			int codigo = Integer.parseInt((String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableMovCaja.getSelectedRow()), 0));
			movimientoCaja = movimientoCajaLogica.getMovimientoCaja(codigo);
			return movimientoCaja;
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


	/************** GESTION DE ITEMS DE LA BASE DE DATOS Y DE LA TABLA ***********************/

	/*AGREGA UN NUEVO ITEM A LA BASE DE DATOS Y A LA TABLA*/
	public int addItem(){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldImporte.getText().isEmpty() && !jTextFieldConcepto.getText().isEmpty()){

				setDatosMovimientoCaja();
				if(movimientoCajaLogica.addMovimientoCaja(movimientoCaja)){
						JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
						statusAction = 0;
					}else{
						JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación!", "Error", JOptionPane.ERROR_MESSAGE);
						statusAction = 2;
					}
				}else{
					JOptionPane.showMessageDialog(null, "Debe completar los campos necesarios", "Error", JOptionPane.ERROR_MESSAGE);
					jTextFieldConcepto.requestFocus(true);
					statusAction = 1;
				}
			return statusAction;
		}catch(Exception ex){
			ex.printStackTrace();
			return statusAction;
		}
	}


	/* HACE UPDATE DE UN ITEM SELECCIONADO EN LA TABLA*/
	public int updateItem(){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldImporte.getText().isEmpty() && !jTextFieldConcepto.getText().isEmpty()){
				setDatosMovimientoCaja();
				if(movimientoCajaLogica.updateMovimientoCaja(movimientoCaja)){
					 JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
					 statusAction = 0;
				}else{
					 JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación!", "Error", JOptionPane.ERROR_MESSAGE);
					 statusAction = 2;
				}
			}else{
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
	public int deleteItem(){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			JOptionPane.showMessageDialog(null, "El movimiento será elminado\n" +
					"Fecha: "+dateFormatArgen.format(dateFormatJapan.parse(movimientoCaja.getFechaMovimiento()))+"\n" +
					"Concepto: "+movimientoCaja.getConcepto(),"Eliminar",JOptionPane.INFORMATION_MESSAGE);
			int selectedOption = JOptionPane.showConfirmDialog(null, "Desea Eliminar el Rubro Seleccionados","Eliminar", JOptionPane.OK_CANCEL_OPTION);
			if(selectedOption == 0){
				if(movimientoCajaLogica.deleteMovimientoCaja(movimientoCaja.getCodigoMovimiento())){

					 JOptionPane.showMessageDialog(null, "Operación Satisfactoria!", "Operación Satisfactoria", JOptionPane.INFORMATION_MESSAGE);
					 statusAction = 0;
				}else{

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
			jToolBar.setBounds(new Rectangle(0, 0, 838, 30));
			jToolBar.setFloatable(false);
			jToolBar.addSeparator(new Dimension(10,10));
			jToolBar.add(getJButtonAlta());
			jToolBar.add(getJButtonModificacion());
			jToolBar.add(getJButtonEliminar());
			jToolBar.addSeparator(new Dimension(10,10));
			jToolBar.add(getJButtonActionOk());
			jToolBar.add(getJButtonActionCancel());
			jToolBar.add(getJButtonaActionRefresh());
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
						int statusAction = addItem();
						if(statusAction == 0){
							resetCampos();
							updateDatosCaja();
							setFilterInTabla("", 0);
							int selectedOption = JOptionPane.showConfirmDialog(null, "Desea agregar un nuevo elemento", "Operación Satisfactoria!", JOptionPane.OK_CANCEL_OPTION);
							System.out.println(selectedOption);
							if(selectedOption == 0){
								//jTextFieldSujetoEntidad.setText(String.valueOf(rubroLogica.getNewCodigoItems()));
								jComboBoxTipoMovimiento.requestFocus(true);
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
						int statusAction = updateItem();
						if(statusAction == 0){
							updateDatosCaja();
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
						Caja c = caja;
						c.setMovimientosCasa(movimientoCajaLogica.getListMovimientoCajas(c));
						recalculaSaldo(c);
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
							jTextFieldConcepto.requestFocus(true);
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
					jComboBoxTipoMovimiento.requestFocus();
					movimientoCaja = new MovimientoCaja();
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
							//jTextFieldCodigo.setText(String.valueOf(rubroLogica.getNewCodigoItems()));
							jComboBoxTipoMovimiento.requestFocus();
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
					int statusAction = deleteItem();
					if(statusAction == 0){
						updateDatosCaja();
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
		jTableMovCaja.setEnabled(estadoTableList);
		jTextFieldSujetoEntidad.setEditable(false);
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
		try {
			if(caja!=null)jTextFieldFechaCaja.setText(dateFormatArgen.format(dateFormatJapan.parse(caja.getFechaInicio())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//component[indice].requestFocus();
		setFilterInTabla("", 0);
	}


	/************ TRATAMIENTO SOBRE FORMATO DE DATOS ***********************/

	//RETORNA EL VALOR FLOAT DE LOS TEXT FIELD DECIMALES CON EL FORMATO DETERMINADO
	private String getValueDecimalVisual(Float valor){
		decimalFormatSymbols.setDecimalSeparator('.');
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		if(valor != null){
			System.out.println(decimalFormat.format(valor.doubleValue()));
			return decimalFormat.format(valor.doubleValue());
		}else{
			return null;
		}
	}



	private Float getValueDecimalReal(String valor){
		try{
		decimalFormatSymbols.setDecimalSeparator('.');
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		if(!valor.isEmpty()){
			if(valor.contains("$ ")){
				valor = valor.substring(2);
			}
			System.out.println(decimalFormat.parse(valor).floatValue());
			return decimalFormat.parse(valor).floatValue();
		}else{
			return new Float(0);
		}
		}catch(ParseException pe){
			pe.printStackTrace();
			return new Float(0);
		}
	}

	private void recalculaSaldo(Caja c){
		if(c != null){
			if(c.getMovimientosCasa() != null){
				if(cajaLogica.calculaSaldoCaja(c) > 0){
					jLabelSaldoFinal.setText("<html><b><font size='5' color='green'>$" + getValueDecimalVisual(cajaLogica.calculaSaldoCaja(c)) + "</font></b></html>");
				}else{
					jLabelSaldoFinal.setText("<html><b><font size='5' color='red'>$" + getValueDecimalVisual(cajaLogica.calculaSaldoCaja(c)) + "</font></b></html>");
				}
			}
		}
	}

	public void setDatosCaja(Caja c){
		if(c != null){
			c.setMovimientosCasa(movimientoCajaLogica.getListMovimientoCajas(c));
			caja = c;
			try {
				jTextFieldFechaCaja.setText(dateFormatArgen.format(dateFormatJapan.parse(caja.getFechaInicio())));
				jTextFieldFechaCaja.setEditable(true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jLabelSaldoInicial.setText("<html></b><font size='4'>$"+getValueDecimalVisual(caja.getMontoInicio())+ "</font></b></html>");
			setItemsInJtable(caja.getMovimientosCasa());
			recalculaSaldo(caja);
		}
	}

	public void updateDatosCaja(){
		caja.setMovimientosCasa(movimientoCajaLogica.getListMovimientoCajas(caja));
		recalculaSaldo(caja);
		setItemsInJtable(caja.getMovimientosCasa());
	}

	private void setCamposMovimientoCaja(MovimientoCaja movimientoCaja){
		jTextFieldConcepto.setText(movimientoCaja.getConcepto());
		jTextFieldImporte.setText(getValueDecimalVisual(movimientoCaja.getImporteMovimiento()));
		if(movimientoCaja.getCodigoSujetoEntidad() != null){
			cliente = clietClienteLogica.getCliente(movimientoCaja.getCodigoSujetoEntidad());
			if(cliente != null){
				if(!cliente.getRazonSocial().isEmpty()){
					jTextFieldSujetoEntidad.setText(cliente.getRazonSocial());
				}else{
					jTextFieldSujetoEntidad.setText(cliente.getNombre() + " " + cliente.getApellido());
				}
			}else{
				jTextFieldSujetoEntidad.setText(null);
			}
		}else{
			jTextFieldSujetoEntidad.setText(null);
		}
		selectItemjComboBoxTipoMovimiento(movimientoCaja.getTipoMovimiento());

	}

	private void setDatosMovimientoCaja(){
		if(movimientoCaja != null){
			movimientoCaja.setCaja(caja);
			if(cliente != null){
				movimientoCaja.setCodigoSujetoEntidad(cliente.getCodigo());
			}else{
				movimientoCaja.setCodigoSujetoEntidad(null);
			}
			movimientoCaja.setConcepto(jTextFieldConcepto.getText());
			movimientoCaja.setFechaMovimiento(dateFormatJapan.format(new Date()));
			movimientoCaja.setImporteMovimiento(getValueDecimalReal(jTextFieldImporte.getText()));
			if(jComboBoxTipoMovimiento != null)movimientoCaja.setTipoMovimiento((TipoMovimientoCaja)jComboBoxTipoMovimiento.getSelectedItem());

		}
	}

	private void setItemsjComboBoxTipoMovimiento (){
		jComboBoxTipoMovimiento.removeAllItems();
		ArrayList<TipoMovimientoCaja> tiposMovimientosCaja = cajaLogica.getListTipoMovimientoCaja();
		Iterator<TipoMovimientoCaja> it = tiposMovimientosCaja.iterator();
		while(it.hasNext()){
			jComboBoxTipoMovimiento.addItem((TipoMovimientoCaja)it.next());
		}
	}

	private void selectItemjComboBoxTipoMovimiento(TipoMovimientoCaja tmc){
		for(int i = 0; i < jComboBoxTipoMovimiento.getItemCount(); i++){
			TipoMovimientoCaja tipoMovimientoCaja = (TipoMovimientoCaja)jComboBoxTipoMovimiento.getItemAt(i);
			if(tipoMovimientoCaja.getCodigoTipoMovimiento() == tmc.getCodigoTipoMovimiento()){
				jComboBoxTipoMovimiento.setSelectedItem(tipoMovimientoCaja);
				break;
			}
		}
	}

	private void setOcultarColumnasJTable(JTable tbl, int columna[])
    {
        for(int i=0;i<columna.length;i++){
             tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }

	/**
	 * This method initializes jButtonCliente
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCliente() {
		if (jButtonCliente == null) {
			jButtonCliente = new JButton();
			jButtonCliente.setBounds(new Rectangle(415, 464, 20, 20));
			jButtonCliente.setText("...");
			jButtonCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorCliente dialogGestorCliente = new DialogGestorCliente(new Frame());
					dialogGestorCliente.setModal(true);
					dialogGestorCliente.setVisible(true);
					if(dialogGestorCliente.getSelectedElement() != null){
						cliente = dialogGestorCliente.getSelectedElement();
						if(!cliente.getRazonSocial().isEmpty()){
							jTextFieldSujetoEntidad.setText(cliente.getRazonSocial());
						}else{
							jTextFieldSujetoEntidad.setText(cliente.getNombre() + " " + cliente.getApellido());
						}
					}else{
						cliente = null;
						jTextFieldSujetoEntidad.setText(null);
					}
				}
			});
		}
		return jButtonCliente;
	}





	/**
	 * This method initializes jButtonaActionRefresh
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonaActionRefresh() {
		if (jButtonaActionRefresh == null) {
			jButtonaActionRefresh = new JButton();
			jButtonaActionRefresh.setName("Close");
			jButtonaActionRefresh.setBorderPainted(false);
			jButtonaActionRefresh.setIcon(new ImageIcon(getClass().getResource("/view-refresh-icon.png")));
			jButtonaActionRefresh.setText("Actualizar");
			jButtonaActionRefresh.setPreferredSize(new Dimension(100, 30));
			jButtonaActionRefresh.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					String fechaSelected;
					try {
						SimpleDateFormat spd = new SimpleDateFormat("yyyyMMdd");
						fechaSelected = spd.format(dateFormatArgen.parse(jTextFieldFechaCaja.getText()));

					Caja c = cajaLogica.getCajaByFecha(fechaSelected);
					if(c != null){
						setDatosCaja(c);
					}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return jButtonaActionRefresh;
	}






}  //  @jve:decl-index=0:visual-constraint="10,10"

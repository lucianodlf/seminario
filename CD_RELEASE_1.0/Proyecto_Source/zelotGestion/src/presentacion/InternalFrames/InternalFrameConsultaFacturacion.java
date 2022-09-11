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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.text.MaskFormatter;

import logica.ClienteLogica;
import logica.ComprobanteLogica;
import presentacion.Dialog.DialogGestorCliente;
import presentacion.Dialog.DialogVisorFacturacion;
import presentacion.Dialog.DialogVisorNotaCredito;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Cliente;
import dominio.Factura;
import dominio.ItemFactura;

public class InternalFrameConsultaFacturacion extends JInternalFrame {

	private JPanel jContentPane = null;
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableConsultaFact = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JLabel jLabelBusqueda = null;
	private JLabel jLabelCriterioBusqueda = null;
	private JTextField jTextFieldBusqueda = null;
	private JComboBox jComboBoxCriterioBusqueda = null;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonActionVisualizar = null;
	private JButton jButtonActionSeleccionar = null;
	private JLabel jLabelFechaDesde = null;
	private JFormattedTextField jTextFieldFechaDesde = null;
	private MaskFormatter maskFormatterFechaDesde = null;
	private JLabel jLabelCliente = null;
	private JTextField jTextFieldCodigoCli = null;
	private JLabel jLabelRazonSoc = null;
	private JTextField jTextFieldRazonSocial = null;
	private JLabel jLabelFechaHasta = null;
	private JFormattedTextField jTextFieldFechaHasta = null;
	private MaskFormatter maskFormatterFechaHasta = null;

	/*clases de logica*/
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:

	private ClienteLogica clieteLogica = ClienteLogica.getInstance();  //  @jve:decl-index=0:
	private Cliente cliente = null;  //  @jve:decl-index=0:
	private Factura factura = null;
	private ComprobanteLogica comprobanteLogica = ComprobanteLogica.getInstance();
	private ArrayList<Factura> comprobantes = null;

	private String fechaDesde = null;
	private String fechaHasta = null;  //  @jve:decl-index=0:
	private JButton jButtonCliente = null;
	private JCheckBox jCheckBoxFiltraCliente = null;
	private JButton jButtonActionActualizar = null;

	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameConsultaFacturacion() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(878, 502);
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Consulta Facturación");
		resetCampos();
		setVisibleButtonsToolbar(false, false, true);
		setEnabledButtonsToolbar(false, false, true);
		setRangoFechaDefault();
		try {
			selectComprobantes(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText())), dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText())), cliente);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelFechaHasta = new JLabel();
			jLabelFechaHasta.setBounds(new Rectangle(709, 67, 86, 20));
			jLabelFechaHasta.setText("Fecha Hasta:");
			jLabelRazonSoc = new JLabel();
			jLabelRazonSoc.setBounds(new Rectangle(198, 38, 90, 21));
			jLabelRazonSoc.setText("Razón Social:");
			jLabelCliente = new JLabel();
			jLabelCliente.setBounds(new Rectangle(4, 38, 56, 22));
			jLabelCliente.setText("Cliente:");
			jLabelFechaDesde = new JLabel();
			jLabelFechaDesde.setBounds(new Rectangle(538, 67, 84, 20));
			jLabelFechaDesde.setText("Fecha Desde:");
			jContentPane = new JPanel();
			jLabelCriterioBusqueda = new JLabel();
			jLabelCriterioBusqueda.setBounds(new Rectangle(279, 67, 50, 20));
			jLabelCriterioBusqueda.setText("Criterio:");
			jLabelBusqueda = new JLabel();
			jLabelBusqueda.setBounds(new Rectangle(4, 67, 50, 20));
			jLabelBusqueda.setText("Buscar:");
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPaneaList(), null);
			jContentPane.add(jLabelBusqueda, null);
			jContentPane.add(jLabelCriterioBusqueda, null);
			jContentPane.add(getJTextFieldBusqueda(), null);
			jContentPane.add(getJComboBoxCriterioBusqueda(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(jLabelFechaDesde, null);
			jContentPane.add(getJTextFieldFechaDesde(), null);
			jContentPane.add(jLabelCliente, null);
			jContentPane.add(getJTextFieldCodigoCli(), null);
			jContentPane.add(jLabelRazonSoc, null);
			jContentPane.add(getJTextFieldRazonSocial(), null);
			jContentPane.add(jLabelFechaHasta, null);
			jContentPane.add(getJTextFieldFechaHasta(), null);
			jContentPane.add(getJButtonCliente(), null);
			jContentPane.add(getJCheckBoxFiltraCliente(), null);
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
			jScrollPaneaList.setBounds(new Rectangle(5, 96, 855, 354));
			jScrollPaneaList.setViewportView(getJTableConsultaFact());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJTableConsultaFact() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableConsultaFact == null) {
			jTableConsultaFact = new MyJtable(getMyTableModel());
			jTableConsultaFact.setRowSorter(geTableRowSorter());
			jTableConsultaFact.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableConsultaFact.setRowHeight(20);
			jTableConsultaFact.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableConsultaFact.setAutoscrolls(true);
			jTableConsultaFact.setShowVerticalLines(true);
			jTableConsultaFact.setShowHorizontalLines(true);
			jTableConsultaFact.setVisible(true);
			jTableConsultaFact.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableConsultaFact.isEnabled()){
						factura = getSelectedElement();
						if(factura != null){
							setVisibleButtonsToolbar(true, true, true);
							setEnabledButtonsToolbar(true, true, true);
						}
					}
				}
			});
			jTableConsultaFact.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(jTableConsultaFact.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							factura = getSelectedElement();
							if(factura != null){
								setVisibleButtonsToolbar(true, true, true);
								setEnabledButtonsToolbar(true, true, true);
							}
						}
					}
				}
			});
		}
		return jTableConsultaFact;
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
			jTextFieldBusqueda.setBounds(new Rectangle(59, 67, 180, 20));
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
			jComboBoxCriterioBusqueda.setBounds(new Rectangle(339, 67, 180, 20));
			jComboBoxCriterioBusqueda.addItem("Fecha");
			jComboBoxCriterioBusqueda.addItem("Tipo Comp.");
			jComboBoxCriterioBusqueda.addItem("Letra Comp.");
			jComboBoxCriterioBusqueda.addItem("Nº Pto. Vta.");
			jComboBoxCriterioBusqueda.addItem("Nº Comp.");
			jComboBoxCriterioBusqueda.addItem("Cliente");
			jComboBoxCriterioBusqueda.setSelectedIndex(5);
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


	/**
	 * This method initializes maskFormatterFechaCaja
	 *
	 * @return javax.swing.text.MaskFormatter
	 */
	private MaskFormatter getMaskFormatterFechaCaja() {
		if (maskFormatterFechaDesde == null) {
			try {
				maskFormatterFechaDesde = new MaskFormatter("##/##/####");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maskFormatterFechaDesde.setPlaceholderCharacter('_');
		}
		return maskFormatterFechaDesde;
	}

	/**
	 * This method initializes jTextFieldFechaDesde
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getJTextFieldFechaDesde() {
		if (jTextFieldFechaDesde == null) {
			jTextFieldFechaDesde = new JFormattedTextField(getMaskFormatterFechaCaja());
			jTextFieldFechaDesde.setBounds(new Rectangle(634, 67, 70, 20));
			jTextFieldFechaDesde.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					if(e.getKeyCode() == 10){
							try {
								selectComprobantes(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText())), dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText())), cliente);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
			});
		}
		return jTextFieldFechaDesde;
	}

	/************** GESTION DE ITEMS DE LA TABLA *******************/

	private void setItemsInJtable(ArrayList<Factura> comprobantes){
		Vector<String> titulos = new Vector<String>();
		titulos.add("Fecha");
		titulos.add("Tipo Comp.");
		titulos.add("Letra Comp.");
		titulos.add("Nº Pto. Vta.");
		titulos.add("Nº Comp.");
		titulos.add("Cliente");
		titulos.add("Importe");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(comprobantes != null){
			Iterator<Factura> it = comprobantes.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				Factura fac = (Factura)it.next();
				try {
					renglon.add(dateFormatArgen.format(dateFormatJapan.parse(fac.getFechaFacturacion())));

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				renglon.add(fac.getTipoComprobante());
				renglon.add(fac.getLetraFactura());
				renglon.add(fac.getNroPtoVenta());
				renglon.add(fac.getNroFactura());
				if(!fac.getCliente().getRazonSocial().isEmpty() && !fac.getCliente().getRazonSocial().equals("")){
					renglon.add(fac.getCliente().getRazonSocial());
				}else{
					renglon.add(fac.getCliente().getNombre()+" "+fac.getCliente().getNombre());
				}
				renglon.add("$ "+getValueDecimalVisual(fac.getTotalFinal()));
				registros.add(renglon);
			}
		}
		myTableModel.setDataVector(registros, titulos);
		getJTableConsultaFact().getColumnModel().getColumn(0).setPreferredWidth(30);
		getJTableConsultaFact().getColumnModel().getColumn(1).setPreferredWidth(3);
		getJTableConsultaFact().getColumnModel().getColumn(2).setPreferredWidth(3);
		getJTableConsultaFact().getColumnModel().getColumn(3).setPreferredWidth(3);
		getJTableConsultaFact().getColumnModel().getColumn(4).setPreferredWidth(20);
		getJTableConsultaFact().getColumnModel().getColumn(5).setPreferredWidth(300);
		getJTableConsultaFact().getColumnModel().getColumn(6).setPreferredWidth(20);

		//setOcultarColumnasJTable(jTableConsultaFact, new int[]{0});
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	private Factura getSelectedElement(){
		try{
			String tipoComp = (String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableConsultaFact.getSelectedRow()), 1);
			String letraComp = (String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableConsultaFact.getSelectedRow()), 2);
			String nroPtoVta = (String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableConsultaFact.getSelectedRow()), 3);
			String nroComp = (String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableConsultaFact.getSelectedRow()), 4);

			factura = comprobanteLogica.getFactura(tipoComp, letraComp, nroPtoVta, nroComp);
			return factura;
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



	/**************** BUTTONS DE TOLBAR  Y TOOLBAR ********************/
	/*DEVULEVE LA JTOOLBAR*/
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 947, 30));
			jToolBar.setFloatable(false);
			jToolBar.addSeparator(new Dimension(10,10));
			jToolBar.add(getJButtonVisualizar());
			jToolBar.addSeparator(new Dimension(10,10));
			jToolBar.add(getJButtonActionCloce());
			jToolBar.addSeparator(new Dimension(10,10));
			jToolBar.add(getJButtonActionSeleccionar());
			jToolBar.add(getJButtonActionActualizar());
			jToolBar.addSeparator(new Dimension(10,10));
		}
		return jToolBar;
	}

	private JButton getJButtonActionSeleccionar() {
		if (jButtonActionSeleccionar == null) {
			jButtonActionSeleccionar = new JButton();
			jButtonActionSeleccionar.setText("Seleccionar");
			jButtonActionSeleccionar.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonActionSeleccionar.setBorderPainted(false);
			jButtonActionSeleccionar.setPreferredSize(new Dimension(100, 25));
			jButtonActionSeleccionar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed() btbCancel"); // TODO Auto-generated Event stub actionPerformed()
					setVisible(false);
					dispose();
				}
			});
		}
		return jButtonActionSeleccionar;
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


	public JButton getJButtonVisualizar() {
		if (jButtonActionVisualizar == null) {
			jButtonActionVisualizar = new JButton();
			jButtonActionVisualizar.setBorderPainted(false);
			jButtonActionVisualizar.setIcon(new ImageIcon(getClass().getResource("/lupa-icon.png")));
			jButtonActionVisualizar.setText("Ver Comprobante");
			jButtonActionVisualizar.setPreferredSize(new Dimension(100, 25));
			jButtonActionVisualizar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(factura != null){
						if(factura.getTipoComprobante().equals("NC")){
							DialogVisorNotaCredito dialogVisorNotaCredito = new DialogVisorNotaCredito(new Frame(), validateFactura(factura));
							dialogVisorNotaCredito.setModal(true);
							dialogVisorNotaCredito.setVisible(true);
						}else if(factura.getTipoComprobante().equals("FAC")){
							DialogVisorFacturacion dialogVisorFacturacion = new DialogVisorFacturacion(new Frame(), validateFactura(factura));
							dialogVisorFacturacion.setModal(true);
							dialogVisorFacturacion.setVisible(true);
						}
					}
				}

			});
		}
		return jButtonActionVisualizar;
	}

	/**
	 * This method initializes jTextFieldCodigoCli
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCodigoCli() {
		if (jTextFieldCodigoCli == null) {
			jTextFieldCodigoCli = new JTextField();
			jTextFieldCodigoCli.setBounds(new Rectangle(64, 37, 91, 23));
			jTextFieldCodigoCli.setDocument(new MyPlainDocument(getJTextFieldCodigoCli(), 24, "D", true));
			jTextFieldCodigoCli.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					if(e.getKeyCode() == 10){
						cliente = clieteLogica.getCliente(Integer.parseInt(jTextFieldCodigoCli.getText()));
						if(cliente != null){
							if(!cliente.getRazonSocial().isEmpty()){
								jTextFieldRazonSocial.setText(cliente.getRazonSocial());
							}else{
								jTextFieldRazonSocial.setText(cliente.getNombre()+" "+cliente.getApellido());
							}
							try {
								selectComprobantes(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText())), dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText())), cliente);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}else{
							JOptionPane.showMessageDialog(null, "El código no pertenece a un cliente existente", "Cliente inexistente", JOptionPane.INFORMATION_MESSAGE);
							jTextFieldCodigoCli.requestFocus();
						}
					}
				}
			});
		}
		jTextFieldCodigoCli.requestFocus();
		return jTextFieldCodigoCli;
	}


	/**
	 * This method initializes jTextFieldRazonSocial
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRazonSocial() {
		if (jTextFieldRazonSocial == null) {
			jTextFieldRazonSocial = new JTextField();
			jTextFieldRazonSocial.setBounds(new Rectangle(293, 39, 409, 22));
		}
		return jTextFieldRazonSocial;
	}

	/**
	 * This method initializes maskFormatterFechaHasta
	 *
	 * @return javax.swing.text.MaskFormatter
	 */
	private MaskFormatter getMaskFormatterFechaHasta() {
		if (maskFormatterFechaHasta == null) {
			try {
				maskFormatterFechaHasta = new MaskFormatter("##/##/####");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maskFormatterFechaHasta.setPlaceholderCharacter('_');
		}
		return maskFormatterFechaHasta;
	}

	/**
	 * This method initializes jTextFieldFechaHasta
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getJTextFieldFechaHasta() {
		if (jTextFieldFechaHasta == null) {
			jTextFieldFechaHasta = new JFormattedTextField(
					getMaskFormatterFechaHasta());
			jTextFieldFechaHasta.setBounds(new Rectangle(800, 66, 67, 20));
			jTextFieldFechaHasta.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					if(e.getKeyCode() == 10){
							try {
								selectComprobantes(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText())), dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText())), cliente);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
				}
			});
		}
		return jTextFieldFechaHasta;
	}


	/********** ACTIONES CON BUTTONS DE TOOLBAR ****************/

	/*habilita o desabilita los botones de la toolbar*/
	public void setEnabledButtonsToolbar(boolean btnVisualizar, boolean btnSeleccionar, boolean btnCloce){
		jButtonActionVisualizar.setEnabled(btnVisualizar);
		jButtonActionSeleccionar.setEnabled(btnSeleccionar);
		jButtonaActionCloce.setEnabled(btnCloce);
	}

	/*muestra o oculta los botonoes de la toolbar*/
	public void setVisibleButtonsToolbar(boolean btnVisualizar, boolean btnSeleccionar, boolean btnCloce){
		//jButtonActionAlta.setVisible(btnNuevo);
		jButtonActionVisualizar.setVisible(btnVisualizar);
		//jButtonActionModificacion.setVisible(btnModificar);
		//jButtonActionOk.setVisible(btnOk);
		jButtonActionSeleccionar.setVisible(btnSeleccionar);
		jButtonaActionCloce.setVisible(btnCloce);
	}

	/*setea el estado (enable o editable) de todos los componenetes*/
	/*public void setEstatusAllComponent(boolean txtBusqueda, boolean cboBusqueda, boolean tableList){
		jTextFieldCodigoCli.setEditable(true);
		jTextFieldRazonSocial.setEditable(false);
		jButtonCliente.setEnabled(true);
		jComboBoxCriterioBusqueda.setEnabled(true);
		jTextFieldBusqueda.setEditable(true);
		jTableConsultaFact.setEnabled(true);
		jTextFieldFechaDesde.setEditable(true);
		jTextFieldFechaHasta.setEditable(true);
	}*/

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

	private void setRangoFechaDefault(){
		Calendar calendar = Calendar.getInstance();
		fechaHasta = dateFormatArgen.format(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, -30);
		fechaDesde = dateFormatArgen.format(calendar.getTime());
		jTextFieldFechaDesde.setText(fechaDesde);
		jTextFieldFechaHasta.setText(fechaHasta);
	}

	private void selectComprobantes(String fechaHasta, String fechaDesde, Cliente cliente){
		if(jCheckBoxFiltraCliente.isSelected()){
			if(cliente != null){
				comprobantes = comprobanteLogica.getListFacturasByClienteAndFecha(cliente, fechaDesde, fechaHasta);
			}
		}else{
			comprobantes = comprobanteLogica.getListFacturasByFecha(fechaDesde, fechaHasta);
		}
		setItemsInJtable(comprobantes);
	}

	public void setFiltroByCliente(Cliente cli){
		if(cli != null){
			cliente = cli;
			jCheckBoxFiltraCliente.setSelected(true);
			selectComprobantes(fechaHasta, fechaDesde, cliente);
		}
	}

	public Factura getComprobanteSelected(){
		return validateFactura(factura);
	}

	/**
	 * This method initializes jButtonCliente
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCliente() {
		if (jButtonCliente == null) {
			jButtonCliente = new JButton();
			jButtonCliente.setBounds(new Rectangle(159, 37, 23, 21));
			jButtonCliente.setText("...");
			jButtonCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorCliente dialogGestorCliente = new DialogGestorCliente(new Frame());
					dialogGestorCliente.setModal(true);
					dialogGestorCliente.setVisible(true);
					if(dialogGestorCliente.getSelectedElement() != null)cliente = dialogGestorCliente.getSelectedElement();
					if(cliente != null){
						jTextFieldCodigoCli.setText(String.valueOf(cliente.getCodigo()));
						if(!cliente.getRazonSocial().isEmpty()){
							jTextFieldRazonSocial.setText(cliente.getRazonSocial());
						}else{
							jTextFieldRazonSocial.setText(cliente.getNombre()+" "+cliente.getApellido());
						}
						try {
							selectComprobantes(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText())), dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText())), cliente);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return jButtonCliente;
	}

	public void setFocusStart(){
		jTextFieldCodigoCli.requestFocus();
	}

	/**
	 * This method initializes jCheckBoxFiltraCliente
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxFiltraCliente() {
		if (jCheckBoxFiltraCliente == null) {
			jCheckBoxFiltraCliente = new JCheckBox();
			jCheckBoxFiltraCliente.setBounds(new Rectangle(708, 40, 127, 20));
			jCheckBoxFiltraCliente.setSelected(false);
			jCheckBoxFiltraCliente.setText("Filtrar por Cliente");
		}
		return jCheckBoxFiltraCliente;
	}

	/**
	 * This method initializes jButtonActionActualizar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionActualizar() {
		if (jButtonActionActualizar == null) {
			jButtonActionActualizar = new JButton();
			jButtonActionActualizar.setPreferredSize(new Dimension(100, 25));
			jButtonActionActualizar.setIcon(new ImageIcon(getClass().getResource("/view-refresh-icon.png")));
			jButtonActionActualizar.setText("Actualizar");
			jButtonActionActualizar.setBorderPainted(false);
			jButtonActionActualizar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					try {
						selectComprobantes(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText())), dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText())), cliente);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return jButtonActionActualizar;
	}

	private Factura validateFactura(Factura fac){
		if(fac != null){
			if(fac.getSubTotal() == null)fac.setSubTotal(Float.valueOf(0));
			if(fac.getTotalBonifClient() == null)fac.setTotalBonifClient(Float.valueOf(0));
			if(fac.getTotalRecarClient() == null)fac.setTotalRecarClient(Float.valueOf(0));
			if(fac.getTotalBonifGlobal() == null)fac.setTotalBonifGlobal(Float.valueOf(0));
			if(fac.getTotalRecarGlobal() == null)fac.setTotalRecarGlobal(Float.valueOf(0));
			if(fac.getTotalIva21() == null)fac.setTotalIva21(Float.valueOf(0));
			if(fac.getTotalIva27() == null)fac.setTotalIva27(Float.valueOf(0));
			if(fac.getTotalIva105() == null)fac.setTotalIva105(Float.valueOf(0));
			if(fac.getTotalFinal() == null)fac.setTotalFinal(Float.valueOf(0));

			Iterator<ItemFactura> it = fac.getItemsFactura().iterator();
			while(it.hasNext()){
				ItemFactura itemF = (ItemFactura)it.next();
				if(itemF.getCantidad() == null)itemF.setCantidad(Float.valueOf(0));
				if(itemF.getDescManual() == null)itemF.setDescManual(Float.valueOf(0));
				if(itemF.getIva() == null)itemF.setIva(Float.valueOf(0));
				if(itemF.getPrecioTotal() == null)itemF.setPrecioTotal(Float.valueOf(0));
				if(itemF.getPrecioUnitario() == null)itemF.setPrecioUnitario(Float.valueOf(0));
				if(itemF.getRecManual() == null)itemF.setRecManual(Float.valueOf(0));
				if(itemF.getValorIvaCalculado() == null)itemF.setValorIvaCalculado(Float.valueOf(0));
			}
		}
		return fac;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"

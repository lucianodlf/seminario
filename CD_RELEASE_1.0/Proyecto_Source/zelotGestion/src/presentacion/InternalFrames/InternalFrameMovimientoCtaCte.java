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

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logica.ClienteLogica;
import logica.CobranzaLogica;
import logica.ComprobanteLogica;
import presentacion.Dialog.DialogCobranzaAltaRecibo;
import presentacion.Dialog.DialogGestorCliente;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyTableModel;
import complementos.MyTableModelCobranzas;

import dominio.Cliente;
import dominio.Cobranza;
import dominio.ComprobanteCtaCte;
import dominio.DocumentoImputado;
import dominio.Factura;
import dominio.ItemFactura;

public class InternalFrameMovimientoCtaCte extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPaneCtaCte = null;
	private JPanel jPanelCtaCteAll = null;
	private JPanel jPanelCtaCtePendientes = null;
	private JScrollPane jScrollPaneLisMovCtaCte = null;
	private JTable jTableMovCtaCte = null;
	private JScrollPane jScrollPaneListCtaCtePendientes = null;
	private JTable jTableCtaCtePendientes = null;
	private JLabel jLabelSaldo = null;
	private JLabel jLabelImporteSaldo = null;
	private JButton jButtonImputar = null;
	private MyTableModel myTableModelMovCtaCte = null;
	private MyTableModelCobranzas myTableModelCtaCtePendientes = null;
	private TableRowSorter<TableModel> tableRowSorterMovCtaCte = null;  //  @jve:decl-index=0:
	private TableRowSorter<TableModel> tableRowSorterCtaCtePendientes = null;
	private JLabel jLabelCodigoCli = null;
	private JTextField jTextFieldCodigoCli = null;
	private JButton jButtonClient = null;
	private JLabel jLabelRazonSocial = null;
	private JTextField jTextFieldRazonSocial = null;
	private JLabel jLabelSaldo1 = null;
	private JLabel jLabelImporteSaldoPendiente = null;


	private ArrayList<Cobranza> cobranzas = null;
	private ComprobanteLogica comprobanteLogica = ComprobanteLogica.getInstance();  //  @jve:decl-index=0:
	private ArrayList<ItemFactura> itemsFacturaComprobanteSelected = null;  //  @jve:decl-index=0:
	private ArrayList<DocumentoImputado> documentosImputadosCobranzaSelected = null;
	private ArrayList<ComprobanteCtaCte> comprobantesCtaCte = null;
	private ArrayList<ComprobanteCtaCte> comprobantesCtaCtePendientes = null;
	private ArrayList<ComprobanteCtaCte> comprobantesCtaCtePendienteSelected = null;  //  @jve:decl-index=0:
	private CobranzaLogica cobranzaLogica = CobranzaLogica.getInstance();  //  @jve:decl-index=0:
	private Cliente cliente = null;
	private ClienteLogica clienteLogica = ClienteLogica.getInstance();
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private Float importeAimputar = Float.valueOf(0);  //  @jve:decl-index=0:
	private JLabel jLabelmporteImputar = null;
	private JLabel jLabelImporteCompSelected = null;
	private JTextField jTextFieldMontoAImputar = null;
	private JLabel jLabelTotalAImputar = null;
	private JScrollPane jScrollPaneLisDetalleMovCtaCte = null;
	private MyJtable jTableDetalleMovCtaCte = null;
	private MyTableModel myTableModelDetalleFac = null;
	private JLabel jLabelDetalleComp = null;



	/**
	 * @param owner
	 */
	public InternalFrameMovimientoCtaCte(Cliente cliente) {
		super();
		initialize(cliente);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize(Cliente cliente) {


		this.setSize(971, 493);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Gestión de Cuentas Corrientes");
		this.setResizable(false);
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));

		this.cliente = cliente;
		validaCliente();

		this.setContentPane(getJContentPane());
		jTabbedPaneCtaCte.setSelectedIndex(0);
		setDatosCliente();

	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelRazonSocial = new JLabel();
			jLabelRazonSocial.setBounds(new Rectangle(230, 10, 80, 20));
			jLabelRazonSocial.setText("Razón Social:");
			jLabelCodigoCli = new JLabel();
			jLabelCodigoCli.setBounds(new Rectangle(5, 10, 75, 20));
			jLabelCodigoCli.setText("Cód. Cliente:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTabbedPaneCtaCte(), null);
			jContentPane.add(jLabelCodigoCli, null);
			jContentPane.add(getJTextFieldCodigoCli(), null);
			jContentPane.add(getJButtonClient(), null);
			jContentPane.add(jLabelRazonSocial, null);
			jContentPane.add(getJTextFieldRazonSocial(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPaneCtaCte
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPaneCtaCte() {
		if (jTabbedPaneCtaCte == null) {
			jTabbedPaneCtaCte = new JTabbedPane();
			jTabbedPaneCtaCte.setBounds(new Rectangle(5, 40, 952, 416));
			jTabbedPaneCtaCte.addTab("Movimientos de Cuenta Corriente", null, getJPanelCtaCteAll(), null);
			jTabbedPaneCtaCte.addTab("Documentos Pendientes de Pago", null, getJPanelCtaCtePendientes(), null);
			//jTabbedPaneCtaCte.setIconAt(0, new ImageIcon(getClass().getResource("/users.png")));
			jTabbedPaneCtaCte.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					System.out.println("stateChanged()"); // TODO Auto-generated Event stub stateChanged()
					if(jTabbedPaneCtaCte.getSelectedIndex() == 0){
						jTableDetalleMovCtaCte.removeAll();
					}
				}
			});
			//jTabbedPaneCtaCte.setIconAt(1, new ImageIcon(getClass().getResource("/traffic_light.png")));
		}
		return jTabbedPaneCtaCte;
	}

	/**
	 * This method initializes jPanelCtaCteAll
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCtaCteAll() {
		if (jPanelCtaCteAll == null) {
			jLabelDetalleComp = new JLabel();
			jLabelDetalleComp.setBounds(new Rectangle(6, 157, 291, 20));
			jLabelDetalleComp.setText("<html><b><font size='4'>Detalle de Comprobante Seleccionado:</font></b></html>");
			jLabelImporteSaldo = new JLabel();
			jLabelImporteSaldo.setBounds(new Rectangle(726, 326, 200, 50));
			jLabelSaldo = new JLabel();
			jLabelSaldo.setBounds(new Rectangle(633, 337, 88, 27));
			jLabelSaldo.setText("<html><b><font size='6'>Saldo:</font></b></html>");
			jPanelCtaCteAll = new JPanel();
			jPanelCtaCteAll.setName("Movimientos Cuenta Corriente");
			jPanelCtaCteAll.setLayout(null);
			jPanelCtaCteAll.add(getJScrollPaneLisMovCtaCte(), null);
			jPanelCtaCteAll.add(getJScrollPaneLisDetalleMovCtaCte(), null);
			jPanelCtaCteAll.add(jLabelSaldo, null);
			jPanelCtaCteAll.add(jLabelImporteSaldo, null);
			jPanelCtaCteAll.add(jLabelDetalleComp, null);
		}
		return jPanelCtaCteAll;
	}

	/**
	 * This method initializes jPanelCtaCtePendientes
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCtaCtePendientes() {
		if (jPanelCtaCtePendientes == null) {
			jLabelTotalAImputar = new JLabel();
			jLabelTotalAImputar.setBounds(new Rectangle(220, 270, 100, 20));
			jLabelTotalAImputar.setText("Total a Imputar:");
			jLabelImporteCompSelected = new JLabel();
			jLabelImporteCompSelected.setBounds(new Rectangle(720, 323, 200, 50));
			jLabelmporteImputar = new JLabel();
			jLabelmporteImputar.setBounds(new Rectangle(379, 336, 337, 22));
			jLabelmporteImputar.setText("<html><b><font size='4'>Importe Total de Comprobantes Seleccionados:</font></b></html>");
			jLabelImporteSaldoPendiente = new JLabel();
			jLabelImporteSaldoPendiente.setBounds(new Rectangle(720, 270, 200, 50));
			jLabelSaldo1 = new JLabel();
			jLabelSaldo1.setBounds(new Rectangle(627, 281, 88, 27));
			jLabelSaldo1.setText("<html><b><font size='6'>Saldo:</font></b></html>");
			jPanelCtaCtePendientes = new JPanel();
			jPanelCtaCtePendientes.setLayout(null);
			jPanelCtaCtePendientes.add(getJScrollPaneListCtaCtePendientes(), null);
			jPanelCtaCtePendientes.add(getJButtonImputar(), null);
			jPanelCtaCtePendientes.add(jLabelSaldo1, null);
			jPanelCtaCtePendientes.add(jLabelImporteSaldoPendiente, null);
			jPanelCtaCtePendientes.add(jLabelmporteImputar, null);
			jPanelCtaCtePendientes.add(jLabelImporteCompSelected, null);
			jPanelCtaCtePendientes.add(getJTextFieldMontoAImputar(), null);
			jPanelCtaCtePendientes.add(jLabelTotalAImputar, null);
		}
		return jPanelCtaCtePendientes;
	}

	/**
	 * This method initializes jTextFieldCodigoCli
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCodigoCli() {
		if (jTextFieldCodigoCli == null) {
			jTextFieldCodigoCli = new JTextField();
			jTextFieldCodigoCli.setBounds(new Rectangle(90, 10, 100, 20));
			jTextFieldCodigoCli.setDocument(new MyPlainDocument(getJTextFieldCodigoCli(), 24, "D", true));
			jTextFieldCodigoCli.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					if(e.getKeyCode() == 10){
						cliente = clienteLogica.getCliente(Integer.parseInt(jTextFieldCodigoCli.getText()));
						if(cliente != null){
							setDatosCliente();
							setItemsInJtableMovCtaCte();
							setItemsInJtableCtaCtePendientes();
						}else{
							JOptionPane.showMessageDialog(null, "El código no pertenece a un cliente existente", "Cliente inexistente", JOptionPane.INFORMATION_MESSAGE);
							jTextFieldCodigoCli.requestFocus();
						}
					}
				}
			});
		}
		return jTextFieldCodigoCli;
	}

	/**
	 * This method initializes jButtonClient
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonClient() {
		if (jButtonClient == null) {
			jButtonClient = new JButton();
			jButtonClient.setBounds(new Rectangle(195, 10, 20, 20));
			jButtonClient.setText("...");
			jButtonClient.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorCliente dialogGestorCliente = new DialogGestorCliente(new Frame());
					dialogGestorCliente.setModal(true);
					dialogGestorCliente.setVisible(true);
					if(dialogGestorCliente.getSelectedElement() != null)cliente = dialogGestorCliente.getSelectedElement();
					if(cliente != null){
						setDatosCliente();
						setItemsInJtableMovCtaCte();
						setItemsInJtableCtaCtePendientes();
					}
				}
			});
		}
		return jButtonClient;
	}

	/**
	 * This method initializes jTextFieldRazonSocial
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRazonSocial() {
		if (jTextFieldRazonSocial == null) {
			jTextFieldRazonSocial = new JTextField();
			jTextFieldRazonSocial.setBounds(new Rectangle(315, 10, 500, 20));
			jTextFieldRazonSocial.setEditable(false);
		}
		return jTextFieldRazonSocial;
	}

	/**
	 * This method initializes jScrollPaneLisMovCtaCte
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneLisMovCtaCte() {
		if (jScrollPaneLisMovCtaCte == null) {
			jScrollPaneLisMovCtaCte = new JScrollPane();
			jScrollPaneLisMovCtaCte.setBounds(new Rectangle(5, 5, 927, 150));
			jScrollPaneLisMovCtaCte.setViewportView(getJTableMovCtaCte());
		}
		return jScrollPaneLisMovCtaCte;
	}

	/**
	 * This method initializes jTableMovCtaCte
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTableMovCtaCte() {
		if (jTableMovCtaCte == null) {
			jTableMovCtaCte = new MyJtable(getMyTableModelMovCtaCte());
			jTableMovCtaCte.setRowSorter(geTableRowSorterMovCtaCte());
			jTableMovCtaCte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableMovCtaCte.setRowHeight(20);
			setItemsInJtableMovCtaCte();
			jTableMovCtaCte.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableMovCtaCte.setAutoscrolls(true);
			jTableMovCtaCte.setShowVerticalLines(true);
			jTableMovCtaCte.setShowHorizontalLines(true);
			jTableMovCtaCte.setVisible(true);
			jTableMovCtaCte.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableMovCtaCte.isEnabled()){
						String t = getDetalleSelectedMovCtaCte(getSelectedElementJtableMovCtaCte(e.getPoint()));
						if(!t.isEmpty()){
							if(t.equals("FAC"))setItemsInJtableDetalleMovCtaCteFactura();
							if(t.equals("REC"))setItemsInJtableDetalleMovCtaCteRecibo();
							//if(t.equals("NC"))setItemsInJtableDetalleMovCtaCteFactura();
						}
					}
				}
			});
			jTableMovCtaCte.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
						if(jTableMovCtaCte.isEnabled()){
							/*String t = getDetalleSelectedMovCtaCte(getSelectedElementJtableMovCtaCte());
							if(!t.isEmpty()){
								if(t.equals("FAC"))setItemsInJtableDetalleMovCtaCteFactura();
								if(t.equals("REC"))setItemsInJtableDetalleMovCtaCteRecibo();
							}*/
						}
					}
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {

				}
			});
		}
		return jTableMovCtaCte;
	}

	/*
	 * devuelve el modelo de tabla para crear el jtable.
	 */
	private MyTableModel getMyTableModelMovCtaCte(){
		if(myTableModelMovCtaCte == null){
			myTableModelMovCtaCte = new MyTableModel();
		}
		return myTableModelMovCtaCte;
	}

	/* DEVUELVE EL RowSorter de la tabla. para organizar los elementos de la misma*/
	private TableRowSorter<TableModel> geTableRowSorterMovCtaCte(){
		if(tableRowSorterMovCtaCte == null){
			tableRowSorterMovCtaCte = new TableRowSorter<TableModel>(getMyTableModelMovCtaCte());
		}
		return tableRowSorterMovCtaCte;
	}

	/**
	 * This method initializes jScrollPaneListCtaCtePendientes
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneListCtaCtePendientes() {
		if (jScrollPaneListCtaCtePendientes == null) {
			jScrollPaneListCtaCtePendientes = new JScrollPane();
			jScrollPaneListCtaCtePendientes.setBounds(new Rectangle(5, 5, 927, 250));
			jScrollPaneListCtaCtePendientes.setViewportView(getJTableCtaCtePendientes());
		}
		return jScrollPaneListCtaCtePendientes;
	}

	/**
	 * This method initializes jTableCtaCtePendientes
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTableCtaCtePendientes() {
		if (jTableCtaCtePendientes == null) {
			jTableCtaCtePendientes = new MyJtable(getMyTableModelCtaCtePendientes());
			jTableCtaCtePendientes.setRowSorter(geTableRowSorterCtaCtePendientes());
			jTableCtaCtePendientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableCtaCtePendientes.setRowHeight(20);
			setItemsInJtableCtaCtePendientes();
			jTableCtaCtePendientes.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableCtaCtePendientes.setAutoscrolls(true);
			jTableCtaCtePendientes.setShowVerticalLines(true);
			jTableCtaCtePendientes.setShowHorizontalLines(true);
			jTableCtaCtePendientes.setVisible(true);
			jTableCtaCtePendientes.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableCtaCtePendientes.isEnabled()){
						if(jTableCtaCtePendientes.columnAtPoint(e.getPoint()) == 0){
							recalculaSaldoClientePendienteSeleccionados();
							if(!comprobantesCtaCtePendienteSelected.isEmpty() && !getValueDecimalVisual(jTextFieldMontoAImputar.getText()).equals(Float.valueOf(0))){
								jButtonImputar.setEnabled(true);
								jTextFieldMontoAImputar.setEditable(true);
							}else{
								jButtonImputar.setEnabled(false);
								jTextFieldMontoAImputar.setEditable(false);
							}
						}
					}
				}
			});
			jTableCtaCtePendientes.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {

				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {

				}
			});
		}
		return jTableCtaCtePendientes;
	}

	/*
	 * devuelve el modelo de tabla para crear el jtable.
	 */
	private MyTableModelCobranzas getMyTableModelCtaCtePendientes(){
		if(myTableModelCtaCtePendientes == null){
			myTableModelCtaCtePendientes = new MyTableModelCobranzas();
		}
		return myTableModelCtaCtePendientes;
	}

	/* DEVUELVE EL RowSorter de la tabla. para organizar los elementos de la misma*/
	private TableRowSorter<TableModel> geTableRowSorterCtaCtePendientes(){
		if(tableRowSorterCtaCtePendientes == null){
			tableRowSorterCtaCtePendientes = new TableRowSorter<TableModel>(getMyTableModelCtaCtePendientes());
		}
		return tableRowSorterCtaCtePendientes;
	}

	/**
	 * This method initializes jButtonImputar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonImputar() {
		if (jButtonImputar == null) {
			jButtonImputar = new JButton();
			jButtonImputar.setBounds(new Rectangle(5, 270, 195, 30));
			jButtonImputar.setText("Imputar Comprobantes");
			jButtonImputar.setEnabled(false);
			jButtonImputar.setIcon(new ImageIcon(getClass().getResource("/Symbol-Add_24x24-32.png")));
			jButtonImputar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(!jTextFieldMontoAImputar.getText().isEmpty()){
						try {
							if(decimalFormat.parse(jTextFieldMontoAImputar.getText()).floatValue() <= decimalFormat.parse(decimalFormat.format(importeAimputar)).floatValue()){
								if(cliente != null && comprobantesCtaCtePendientes != null && importeAimputar != null){
									importeAimputar = getValueDecimalReal(jTextFieldMontoAImputar.getText());
									DialogCobranzaAltaRecibo dialogCobranzaAltaRecibo = new DialogCobranzaAltaRecibo(new Frame(), cliente, comprobantesCtaCtePendienteSelected, importeAimputar);
									dialogCobranzaAltaRecibo.setModal(true);
									dialogCobranzaAltaRecibo.setVisible(true);
									setItemsInJtableCtaCtePendientes();
									setItemsInJtableMovCtaCte();
									jLabelImporteCompSelected.setText(null);
									jTextFieldMontoAImputar.setText(getValueDecimalVisual(Float.valueOf(0)));
									importeAimputar = Float.valueOf(0);
									jButtonImputar.setEnabled(false);
									jTextFieldMontoAImputar.setEditable(false);
								}
							}else{
								JOptionPane.showMessageDialog(null, "El importe a Imputar no puede Superar la suma de saldos de los comprobantes seleccionados", "Importe Excedido", JOptionPane.ERROR_MESSAGE);
								jTextFieldMontoAImputar.setText(getValueDecimalVisual(importeAimputar));
								jTextFieldMontoAImputar.requestFocus();
							}
						} catch (HeadlessException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			});
		}
		return jButtonImputar;
	}

	/************** GESTION DE ITEMS DE LA TABLA *******************/

	public void setItemsInJtableMovCtaCte(){
		try{
			cobranzas = cobranzaLogica.getAllCobranzasByCliente(cliente);
			comprobantesCtaCte = cobranzaLogica.getAllComprobanteCtaCteByCliente(cliente);
			Vector<String> titulos = new Vector<String>();
			titulos.add("Fecha");
			titulos.add("Tipo");
			titulos.add("Pto. Vta.");
			titulos.add("Nro. Comp.");
			titulos.add("Letra");
			titulos.add("Concepto");
			titulos.add("Cuota Nº");
			titulos.add("Debe");
			titulos.add("Haber");
			Vector<Vector<String>> registros = new Vector<Vector<String>>();
			Vector<String> renglon = null;
			if(comprobantesCtaCte != null){
				Iterator<ComprobanteCtaCte> it = comprobantesCtaCte.iterator();
				while(it.hasNext()){
					renglon = new Vector<String>();
					ComprobanteCtaCte comprobanteCtaCte = (ComprobanteCtaCte)it.next();
					renglon.add(dateFormatArgen.format(dateFormatJapan.parse(comprobanteCtaCte.getFechaEmision())));
					renglon.add(comprobanteCtaCte.getTipoComprobanteCtaCte());
					renglon.add(comprobanteCtaCte.getNroPtoVtaComprobanteCtaCte());
					renglon.add(comprobanteCtaCte.getNroComprobanteCtaCte());
					renglon.add(comprobanteCtaCte.getLetraComprobanteCtaCte());
					renglon.add(comprobanteCtaCte.getConcepto());
					renglon.add(String.valueOf(comprobanteCtaCte.getCantidadCtas()));
					renglon.add("$ "+getValueDecimalVisual(comprobanteCtaCte.getMontoOriginal()));
					renglon.add(null);
					registros.add(renglon);
				}
				if(cobranzas != null){
					Iterator<Cobranza> it2 = cobranzas.iterator();
					while(it2.hasNext()){
						//renglon = new Vector<String>();
						Cobranza cobranza = (Cobranza)it2.next();
						renglon = new Vector<String>();
						renglon.add(dateFormatArgen.format(dateFormatJapan.parse(cobranza.getFechaCobranza())));
						renglon.add(cobranza.getTipoComprobanteRecibo());
						renglon.add(cobranza.getNroPtoVtaComprobanteRecibo());
						renglon.add(cobranza.getNroComprobanteRecibo());
						renglon.add(cobranza.getLetraComprobanteRecibo());
						renglon.add("REC "+cobranza.getLetraComprobanteRecibo()+" NRº "+cobranza.getNroPtoVtaComprobanteRecibo()+"-"+cobranza.getNroComprobanteRecibo());
						renglon.add(null);
						renglon.add(null);
						renglon.add("$ "+getValueDecimalVisual(cobranza.getMontoTotalImputado()));
						registros.add(renglon);
					}
				}
			}
			myTableModelMovCtaCte.setDataVector(registros, titulos);
			setOcultarColumnasJTable(jTableMovCtaCte, new int[]{1, 2, 3, 4});
			jTableMovCtaCte.getColumnModel().getColumn(0).setPreferredWidth(10);
			jTableMovCtaCte.getColumnModel().getColumn(5).setPreferredWidth(400);
			jTableMovCtaCte.getColumnModel().getColumn(6).setPreferredWidth(10);
			jTableMovCtaCte.getColumnModel().getColumn(7).setPreferredWidth(10);
			jTableMovCtaCte.getColumnModel().getColumn(8).setPreferredWidth(10);
			recalculaSaldoCliente();


		}catch(ParseException pe){
			pe.printStackTrace();
		}
	}

	public void setItemsInJtableDetalleMovCtaCteFactura(){
				Vector<String> titulos = new Vector<String>();
				titulos.add("Cód. Artículo");
				titulos.add("Descripción");
				titulos.add("Cantidad");
				titulos.add("Precio Unitario");
				titulos.add("Precio Total");
				Vector<Vector<String>> registros = new Vector<Vector<String>>();
				Vector<String> renglon = null;
				if(itemsFacturaComprobanteSelected != null){
					Iterator<ItemFactura> it = itemsFacturaComprobanteSelected.iterator();
					while(it.hasNext()){
						renglon = new Vector<String>();
						ItemFactura itemFactura = (ItemFactura)it.next();
						renglon.add(String.valueOf(itemFactura.getArticulo().getCodigo()));
						//renglon.add(String.valueOf(0));
						renglon.add(itemFactura.getArticulo().getDescripcion());
						renglon.add(getValueDecimalVisual(itemFactura.getCantidad()));
						renglon.add("$ "+getValueDecimalVisual(itemFactura.getPrecioUnitario()));
						renglon.add("$ "+getValueDecimalVisual(itemFactura.getPrecioTotal()));
						registros.add(renglon);
					}
				}
				if(registros.isEmpty()){
					myTableModelDetalleFac.setDataVector(null, titulos);
				}else{
					myTableModelDetalleFac.setDataVector(registros, titulos);
				}
				//setOcultarColumnasJTable(jTableMovCtaCte, new int[]{1, 2, 3, 4});
				jTableDetalleMovCtaCte.getColumnModel().getColumn(0).setPreferredWidth(10);
				jTableDetalleMovCtaCte.getColumnModel().getColumn(1).setPreferredWidth(400);
				jTableDetalleMovCtaCte.getColumnModel().getColumn(2).setPreferredWidth(10);
				jTableDetalleMovCtaCte.getColumnModel().getColumn(3).setPreferredWidth(10);
				jTableDetalleMovCtaCte.getColumnModel().getColumn(4).setPreferredWidth(10);
				recalculaSaldoCliente();

	}

	public void setItemsInJtableDetalleMovCtaCteRecibo(){
		Vector<String> titulos = new Vector<String>();
		titulos.add("Comprobante Nº");
		titulos.add("Cuota Nº");
		titulos.add("Importe Imputado");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(documentosImputadosCobranzaSelected != null){
			Iterator<DocumentoImputado> it = documentosImputadosCobranzaSelected.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				DocumentoImputado documentoImputado = (DocumentoImputado)it.next();
				//renglon.add(documentoImputado.getComprobanteCtaCte().getTipoComprobanteCtaCte()+" "+documentoImputado.getComprobanteCtaCte().getLetraComprobanteCtaCte()+" "+documentoImputado.getComprobanteCtaCte().getNroPtoVtaComprobanteCtaCte()+"-"+documentoImputado.getComprobanteCtaCte().getNroComprobanteCtaCte());
				renglon.add(documentoImputado.getComprobanteCtaCte().getConcepto());
				renglon.add(String.valueOf(documentoImputado.getNumeroCta()));
				renglon.add("$ "+getValueDecimalVisual(documentoImputado.getMontoImputado()));
				registros.add(renglon);
			}
		}
		if(registros.isEmpty()){
			myTableModelDetalleFac.setDataVector(null, titulos);
		}else{
			myTableModelDetalleFac.setDataVector(registros, titulos);
		}
		//setOcultarColumnasJTable(jTableMovCtaCte, new int[]{1, 2, 3, 4});
		jTableDetalleMovCtaCte.getColumnModel().getColumn(0).setPreferredWidth(400);
		jTableDetalleMovCtaCte.getColumnModel().getColumn(1).setPreferredWidth(10);
		jTableDetalleMovCtaCte.getColumnModel().getColumn(2).setPreferredWidth(10);
		recalculaSaldoCliente();
}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	/*private Usuario getSelectedElementUsuarios(){
		try{
			int codigo = Integer.parseInt((String)myTableModelUsuarios.getValueAt(tableRowSorterUsuarios.convertRowIndexToModel(jTableMovCtaCte.getSelectedRow()), 0));
			usuario = usuarioLogica.getUsuario(codigo);
			return usuario;
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}*/

	private void setItemsInJtableCtaCtePendientes(){
		try{
			comprobantesCtaCtePendientes = cobranzaLogica.getAllComprobanteCtaCteByClientePendientes(cliente);
			Vector<String> titulos = new Vector<String>();
			titulos.add("Imputar");
			titulos.add("Fecha Emisión");
			titulos.add("Fecha Vencimiento");
			titulos.add("Tipo");
			titulos.add("Pto. Vta.");
			titulos.add("Nro. Comp.");
			titulos.add("Letra");
			titulos.add("Comprobante");
			titulos.add("Cuota Nº");
			titulos.add("Cliente");
			titulos.add("Importe");
			titulos.add("Saldo");
			Vector<Vector<Object>> registros = new Vector<Vector<Object>>();
			Vector<Object> renglon = null;
			if(comprobantesCtaCtePendientes != null){
				Iterator<ComprobanteCtaCte> it = comprobantesCtaCtePendientes.iterator();
				while(it.hasNext()){
					ComprobanteCtaCte comprobanteCtaCte = (ComprobanteCtaCte)it.next();
					if(comprobanteCtaCte.getSaldo()>0 && !comprobanteCtaCte.isAnulado()){
						renglon = new Vector<Object>();
						renglon.add(false);
						renglon.add(dateFormatArgen.format(dateFormatJapan.parse(comprobanteCtaCte.getFechaEmision())));
						renglon.add(dateFormatArgen.format(dateFormatJapan.parse(comprobanteCtaCte.getFechaVencimiento())));
						renglon.add(comprobanteCtaCte.getTipoComprobanteCtaCte());
						renglon.add(comprobanteCtaCte.getNroPtoVtaComprobanteCtaCte());
						renglon.add(comprobanteCtaCte.getNroComprobanteCtaCte());
						renglon.add(comprobanteCtaCte.getLetraComprobanteCtaCte());
						renglon.add(comprobanteCtaCte.getConcepto());
						renglon.add(comprobanteCtaCte.getCantidadCtas());
						if(!comprobanteCtaCte.getCliente().getRazonSocial().isEmpty()){
							renglon.add(comprobanteCtaCte.getCliente().getRazonSocial());
						}else{
							renglon.add(comprobanteCtaCte.getCliente().getNombre() + " " + comprobanteCtaCte.getCliente().getApellido());
						}
						renglon.add("$ "+getValueDecimalVisual(comprobanteCtaCte.getMontoOriginal()));
						renglon.add("$ "+getValueDecimalVisual(comprobanteCtaCte.getSaldo()));
						registros.add(renglon);
					}
				}
			}
			myTableModelCtaCtePendientes.setDataVector(registros, titulos);
			setOcultarColumnasJTable(jTableCtaCtePendientes, new int[]{3, 4, 5, 6});
			jTableCtaCtePendientes.getColumnModel().getColumn(0).setPreferredWidth(5);
			jTableCtaCtePendientes.getColumnModel().getColumn(1).setPreferredWidth(30);
			jTableCtaCtePendientes.getColumnModel().getColumn(2).setPreferredWidth(30);
			jTableCtaCtePendientes.getColumnModel().getColumn(7).setPreferredWidth(200);
			jTableCtaCtePendientes.getColumnModel().getColumn(8).setPreferredWidth(10);
			jTableCtaCtePendientes.getColumnModel().getColumn(9).setPreferredWidth(200);
			jTableCtaCtePendientes.getColumnModel().getColumn(10).setPreferredWidth(10);
			jTableCtaCtePendientes.getColumnModel().getColumn(11).setPreferredWidth(10);

			recalculaSaldoClientePendiente();
			setComprobantesCtaCtePendientesSelected();
		}catch(ParseException pe){
			pe.printStackTrace();
		}
	}


	private String getDetalleSelectedMovCtaCte(Vector<Object> rowSelected){
		//Vector<Object> vector = (Vector<Object>)myTableModelMovCtaCte.getDataVector();
		String tipoComprobante = null;
		if(rowSelected != null){
				if(((String)rowSelected.get(1)).equals("FAC")){
					if(itemsFacturaComprobanteSelected == null){
						itemsFacturaComprobanteSelected = new ArrayList<ItemFactura>();
					}else{
						itemsFacturaComprobanteSelected.clear();
					}
					Factura fac = new Factura();
					fac.setNroFactura((String)rowSelected.get(3));
					fac.setNroPtoVenta((String)rowSelected.get(2));
					fac.setLetraFactura((String)rowSelected.get(4));
					fac.setTipoComprobante((String)rowSelected.get(1));
					itemsFacturaComprobanteSelected = comprobanteLogica.getListAllItemsFacturaByFactura(fac);
					tipoComprobante = "FAC";
				}else if(((String)rowSelected.get(1)).equals("REC")){
					if(documentosImputadosCobranzaSelected == null){
						documentosImputadosCobranzaSelected = new ArrayList<DocumentoImputado>();
					}else{
						documentosImputadosCobranzaSelected.clear();
					}
					Cobranza cob = new Cobranza();
					cob.setNroComprobanteRecibo((String)rowSelected.get(3));
					cob.setNroPtoVtaComprobanteRecibo((String)rowSelected.get(2));
					cob.setTipoComprobanteRecibo((String)rowSelected.get(1));
					cob.setLetraComprobanteRecibo((String)rowSelected.get(4));
					documentosImputadosCobranzaSelected = cobranzaLogica.getAllDocumentosImputadosByCobranza(cob);
					tipoComprobante = "REC";
				}/*else if(((String)rowSelected.get(1)).equals("NC")){
					if(itemsFacturaComprobanteSelected == null){
						itemsFacturaComprobanteSelected = new ArrayList<ItemFactura>();
					}else{
						itemsFacturaComprobanteSelected.clear();
					}
					Factura fac = new Factura();
					fac.setNroFactura((String)rowSelected.get(3));
					fac.setNroPtoVenta((String)rowSelected.get(2));
					fac.setLetraFactura((String)rowSelected.get(4));
					fac.setTipoComprobante((String)rowSelected.get(1));
					itemsFacturaComprobanteSelected = comprobanteLogica.getListAllItemsFacturaByFactura(fac);
					tipoComprobante = "NC";
				}*/
		}
		return tipoComprobante;
	}

	@SuppressWarnings("unchecked")
	private Vector<Object> getSelectedElementJtableMovCtaCte(Point p){
		Vector<Object> vector = (Vector<Object>)myTableModelMovCtaCte.getDataVector();
		Vector<Object> vresult = null;
		for(int i=0; i<vector.size(); i++){
			if(i == jTableMovCtaCte.rowAtPoint(p)){
				vresult = (Vector<Object>)vector.get(i);
				i = vector.size();
			}
		}
		return vresult;
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

	private String getValueDecimalVisual(String valor){
		decimalFormatSymbols.setDecimalSeparator('.');
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		if(!valor.isEmpty()){
			System.out.println(decimalFormat.format(Double.parseDouble(valor)));
			return decimalFormat.format(Double.parseDouble(valor));
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

	private void validaCliente(){
		if(cliente == null){
			int selectOption = JOptionPane.showConfirmDialog(null, "Debe seleccionar un cliente, Desea hacerlo ?", "Seleccion de Cliente", JOptionPane.OK_CANCEL_OPTION);
			if(selectOption == 0 ){
				DialogGestorCliente dialogGestorCliente = new DialogGestorCliente(new Frame());
				dialogGestorCliente.setModal(true);
				dialogGestorCliente.setVisible(true);
				if(dialogGestorCliente.getSelectedElement() != null)cliente = dialogGestorCliente.getSelectedElement();
				if(cliente == null){
					validaCliente();
				}
			}else{
				dispose();
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


	private Float recalculaSaldoCliente(){
		if(cobranzas == null)cobranzas = cobranzaLogica.getAllCobranzasByCliente(cliente);
		if(comprobantesCtaCte == null)comprobantesCtaCte = cobranzaLogica.getAllComprobanteCtaCteByCliente(cliente);
		Float totalComprobantes = Float.valueOf(0);
		Float totalCobranzas = Float.valueOf(0);
		if(comprobantesCtaCte != null){
			Iterator<ComprobanteCtaCte> itCcc = comprobantesCtaCte.iterator();
			while(itCcc.hasNext()){
				ComprobanteCtaCte ccc = (ComprobanteCtaCte)itCcc.next();
				totalComprobantes += ccc.getMontoOriginal();
			}
		}
		if(cobranzas != null){
			Iterator<Cobranza> itCob = cobranzas.iterator();

			while(itCob.hasNext()){
				Cobranza c = (Cobranza)itCob.next();
				totalCobranzas += c.getMontoTotalImputado();
			}
		}

		Float saldo = totalComprobantes - totalCobranzas;
		if(saldo <= 0){
			jLabelImporteSaldo.setText("<html><b><font size='5' color='green'>$" + Float.valueOf(0) + "</font></b></html>");
		}else{
			jLabelImporteSaldo.setText("<html><b><font size='5' color='red'>$" + getValueDecimalVisual(saldo) + "</font></b></html>");
		}
		return saldo;
	}

	private Float recalculaSaldoClientePendiente(){
		if(comprobantesCtaCtePendientes == null)comprobantesCtaCtePendientes = cobranzaLogica.getAllComprobanteCtaCteByClientePendientes(cliente);
		Float totalComprobantes = Float.valueOf(0);
		if(comprobantesCtaCtePendientes != null){
			Iterator<ComprobanteCtaCte> itCcc = comprobantesCtaCtePendientes.iterator();
			while(itCcc.hasNext()){
				ComprobanteCtaCte ccc = (ComprobanteCtaCte)itCcc.next();
				totalComprobantes += ccc.getSaldo();
			}
		}
		if(totalComprobantes <= 0){
			jLabelImporteSaldoPendiente.setText("<html><b><font size='5' color='green'>$" + Float.valueOf(0) + "</font></b></html>");
		}else{
			jLabelImporteSaldoPendiente.setText("<html><b><font size='5' color='red'>$" + getValueDecimalVisual(totalComprobantes) + "</font></b></html>");
		}
		if(!totalComprobantes.equals(recalculaSaldoCliente()))totalComprobantes = recalculaSaldoCliente();
		return totalComprobantes;
	}

	private Float recalculaSaldoClientePendienteSeleccionados(){
		setComprobantesCtaCtePendientesSelected();
		Float totalComprobantes = Float.valueOf(0);
		if(comprobantesCtaCtePendienteSelected != null){
			Iterator<ComprobanteCtaCte> itCcc = comprobantesCtaCtePendienteSelected.iterator();
			while(itCcc.hasNext()){
				ComprobanteCtaCte ccc = (ComprobanteCtaCte)itCcc.next();
				totalComprobantes += ccc.getSaldo();
			}
		}
		if(totalComprobantes <= 0){
			jLabelImporteCompSelected.setText("<html><b><font size='5' color='green'>$" + Float.valueOf(0) + "</font></b></html>");
			jTextFieldMontoAImputar.setText(String.valueOf(Float.valueOf(0)));
		}else{
			jLabelImporteCompSelected.setText("<html><b><font size='5' color='red'>$" + getValueDecimalVisual(totalComprobantes) + "</font></b></html>");
		}
		jTextFieldMontoAImputar.setText(getValueDecimalVisual(totalComprobantes));
		if(!totalComprobantes.equals(recalculaSaldoCliente()))totalComprobantes = recalculaSaldoCliente();
		importeAimputar = totalComprobantes;
		return totalComprobantes;
	}

	private void setDatosCliente(){
		if(cliente != null){
			System.out.println("codigo: "+cliente.getCodigo());
			jTextFieldCodigoCli.setText(String.valueOf(cliente.getCodigo()));
			if(!cliente.getRazonSocial().isEmpty()){
				jTextFieldRazonSocial.setText(cliente.getRazonSocial());
			}else{
				jTextFieldRazonSocial.setText(cliente.getNombre()+" "+cliente.getApellido());
			}
		}
	}

	public void setClienteSelected(Cliente cli){
		if(cli != null){
			cliente = cli;
			setDatosCliente();
			setItemsInJtableMovCtaCte();
			setItemsInJtableCtaCtePendientes();
		}
	}

	@SuppressWarnings("unchecked")
	private void setComprobantesCtaCtePendientesSelected(){
		if(comprobantesCtaCtePendienteSelected == null){
			comprobantesCtaCtePendienteSelected = new ArrayList<ComprobanteCtaCte>();
		}else{
			comprobantesCtaCtePendienteSelected.clear();
		}
		Vector<Object> vector = (Vector<Object>)myTableModelCtaCtePendientes.getDataVector();
		if(vector != null){
			Iterator<Object> it = vector.iterator();
			while(it.hasNext()){
				Vector<Object> te = (Vector<Object>)it.next();
				if((Boolean)te.get(0) == true){
					ComprobanteCtaCte comp = cobranzaLogica.getComprobanteCtaCte("FAC", (String)te.get(6), "0001", (String)te.get(5), (Integer)te.get(8));
					if(comp != null)comprobantesCtaCtePendienteSelected.add(comp);
				}
			}
		}
	}

	/**
	 * This method initializes jTextFieldMontoAImputar
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldMontoAImputar() {
		if (jTextFieldMontoAImputar == null) {
			jTextFieldMontoAImputar = new JTextField();
			jTextFieldMontoAImputar.setBounds(new Rectangle(330, 270, 100, 20));
			jTextFieldMontoAImputar.setEditable(false);
			jTextFieldMontoAImputar.setDocument(new MyPlainDocument(getJTextFieldMontoAImputar(), 10, "D", true));
			jTextFieldMontoAImputar.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(importeAimputar != null && !importeAimputar.equals(Float.valueOf(0))){
							//importeAimputar = getValueDecimalReal(jTextFieldMontoAImputar.getText());
							jTextFieldMontoAImputar.setText(getValueDecimalVisual(jTextFieldMontoAImputar.getText()));
					}
				}
			});
		}
		return jTextFieldMontoAImputar;
	}

	/**
	 * This method initializes jScrollPaneLisDetalleMovCtaCte
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneLisDetalleMovCtaCte() {
		if (jScrollPaneLisDetalleMovCtaCte == null) {
			jScrollPaneLisDetalleMovCtaCte = new JScrollPane();
			jScrollPaneLisDetalleMovCtaCte.setBounds(new Rectangle(5, 181, 926, 139));
			jScrollPaneLisDetalleMovCtaCte.setViewportView(getJTableDetalleMovCtaCte());
		}
		return jScrollPaneLisDetalleMovCtaCte;
	}

	/**
	 * This method initializes myTableModelDetalleFac
	 *
	 * @return complementos.MyTableModel
	 */
	private MyTableModel getMyTableModelDetalleFac() {
		if (myTableModelDetalleFac == null) {
			myTableModelDetalleFac = new MyTableModel();
		}
		return myTableModelDetalleFac;
	}

	/**
	 * This method initializes jTableDetalleMovCtaCte
	 *
	 * @return complementos.MyJtable
	 */
	private MyJtable getJTableDetalleMovCtaCte() {
		if (jTableDetalleMovCtaCte == null) {
			jTableDetalleMovCtaCte = new MyJtable(getMyTableModelDetalleFac());
			jTableDetalleMovCtaCte.setAutoscrolls(true);
			jTableDetalleMovCtaCte.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableDetalleMovCtaCte.setRowHeight(20);
			jTableDetalleMovCtaCte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableDetalleMovCtaCte.setShowHorizontalLines(true);
			jTableDetalleMovCtaCte.setShowVerticalLines(true);
			jTableDetalleMovCtaCte.setVisible(true);
		}
		return jTableDetalleMovCtaCte;
	}




}  //  @jve:decl-index=0:visual-constraint="10,10"

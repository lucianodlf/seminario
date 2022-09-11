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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import logica.ArticuloLogica;
import logica.ClienteLogica;
import logica.ComprobanteLogica;
import logica.CondicionVentaLogica;
import logica.DepositoLogica;
import logica.ListaPrecioLogica;
import logica.NroCorrelativoLogica;
import persistencia.ManagerDao;
import presentacion.WinPrincipal;
import presentacion.Dialog.DialogConsultaFacturacion;
import presentacion.Dialog.DialogGestorArticulo;
import presentacion.Dialog.DialogGestorCliente;
import verificadores.MyPlainDocument;

import complementos.CalculationEngine;
import complementos.DialogLoad;
import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Articulo;
import dominio.Cliente;
import dominio.CondicionVenta;
import dominio.Deposito;
import dominio.Factura;
import dominio.Impuesto;
import dominio.ItemFactura;
import dominio.ListaPrecio;
import dominio.NroCorrelativo;

public class InternalFrameNotaCredito extends JInternalFrame {


	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="0,1380"
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableArticulo = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonActionEliminar = null;
	private JButton jButtonActionOk = null;
	private JButton jButtonActionCancel = null;
	private JTextField jTextFieldCodigoCli = null;
	private JLabel jLabelCodigoCli = null;
	private JLabel jLabelRazonSocial = null;
	private JTextField jTextFieldRazonSocial = null;
	private JLabel jLabelFecha = null;
	private JLabel jLabelDeposito = null;
	private JComboBox jComboBoxDeposito = null;
	private JLabel jLabelCondVta = null;
	private JLabel jLabelListaPre = null;
	private JComboBox jComboBoxListaPre = null;
	private JLabel jLabelNroFac = null;
	private JTextField jTextFieldNroFac = null;
	private JLabel jLabelFactura = null;
	private JLabel jLabelLetraFac = null;
	private JFormattedTextField jTextFieldFechaFac = null;
	private MaskFormatter maskFormatterFechaFac = null;
	private JTextField jTextFieldCodigoArt = null;
	private JButton jButtonArt = null;
	private JLabel jLabelCantArt = null;
	private JTextField jTextFieldCantArt = null;
	private JTextField jTextFieldDescripcionArt = null;
	private JTextField jTextFieldPreUnit = null;
	private JTextField jTextFieldPreTotal = null;
	private JLabel jLabelPreUnit = null;
	private JLabel jLabelPreTotal = null;
	private JLabel jLabelSubTotalTitle = null;
	private JLabel jLabelBonifClient = null;
	private JLabel jLabelRecarClient = null;
	private JLabel jLabelBinifManual = null;
	private JLabel jLabelRecMan = null;
	private JLabel jLabelIva21 = null;
	private JLabel jLabelIva27 = null;
	private JLabel jLabelIva105 = null;
	private JLabel jLabelTotal = null;
	private JTextField jTextFieldBonifCli = null;
	private JTextField jTextFieldRecCli = null;
	private JTextField jTextFieldBonif = null;
	private JTextField jTextFieldRecar = null;
	private JTextField jTextFieldIva21 = null;
	private JTextField jTextFieldIva27 = null;
	private JTextField jTextFieldIva105 = null;
	private JTextField jTextFieldBonifCliST = null;
	private JTextField jTextFieldRecCliST = null;
	private JTextField jTextFieldBonifST = null;
	private JTextField jTextFieldRecarST = null;
	private JTextField jTextFieldSubTotal = null;
	private JTextField jTextFieldEspacio = null;
	private JLabel jLabelDescItem = null;
	private JTextField jTextFieldDesItem = null;
	private JLabel jLabelRecItem = null;
	private JTextField jTextFieldRecItem = null;
	private JLabel jLabelDesGlobal = null;
	private JTextField jTextFieldDescGlobal = null;
	private JLabel jLabelRecGlobal = null;
	private JTextField jTextFieldRecGlobal = null;


	/*clases de logica*/
	private Articulo articulo = null;  //  @jve:decl-index=0:
	private ArticuloLogica articuloLogica = ArticuloLogica.getInstance();  //  @jve:decl-index=0:
	private CondicionVentaLogica condicionVentaLogica = CondicionVentaLogica.getInstance();
	private ListaPrecioLogica listaPrecioLogica = ListaPrecioLogica.getInstance();  //  @jve:decl-index=0:
	private DepositoLogica depositoLogica = DepositoLogica.getInstance();
	private Factura factura = null;  //  @jve:decl-index=0:
	private Factura facturaAcancelar = null;
	private ItemFactura itemFactura = null;  //  @jve:decl-index=0:
	private Cliente cliente = null;  //  @jve:decl-index=0:
	private ClienteLogica clienteLogica = ClienteLogica.getInstance();

	private JasperReport jasperReport = null;
	private JasperPrint jasperPrint = null;
	private JasperViewer jasperViewer = null;
	private Thread thread1 = null;
	private Thread thread2 = null;
	private Map<Object, Object> pars = new HashMap<Object, Object>();  //  @jve:decl-index=0:
	private DialogLoad dialogLoad = null;

	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyyMMddhhmmss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:
	private DecimalFormat porcentFormat = new DecimalFormat("###.###");  //  @jve:decl-index=0:
	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private Vector<ItemFactura> itemsFactura = null;  //  @jve:decl-index=0:
	private Vector<String> renglonItemFactura = null;  //  @jve:decl-index=0:
	private NroCorrelativo nroCorrelativo = null;  //  @jve:decl-index=0:
	private NroCorrelativoLogica nroCorrelativoLogica = NroCorrelativoLogica.getInstance();
	private ComprobanteLogica comprobanteLogica = ComprobanteLogica.getInstance();  //  @jve:decl-index=0:
	private JComboBox jComboBoxCondVta = null;
	private JButton jButtonClient = null;
	private JLabel jLabelCondicionIva = null;
	private JTextField jTextFieldCondicionIva = null;
	private int indiceRenglonFactura;
	private boolean aclp = true;
	//private Vector<DetallePago> detallesPago = null;  //  @jve:decl-index=0:

	private JLabel jLabelTotalFac = null;
	private JLabel jLabelCantCta = null;
	private JTextField jTextFieldCantCtas = null;
	private JTextField jTextFieldFacNum = null;
	private JLabel jLabelSobreFac = null;
	private JButton jButtonBuscarFactura = null;
	private JCheckBox jCheckBoxDetalle = null;
	private JCheckBox jCheckBoxArticulo = null;


	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameNotaCredito() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(1024, 626);
		this.setMaximumSize(new Dimension(1024, 696));
		this.setMinimumSize(new Dimension(0, 0));
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Notas de Crédito");
		this.setResizable(false);
		resetCampos();
		setEstatusAllComponent(false);
		setEnabledButtonsToolbar(false, false, false, true);
		//articulo = null;
		//cliente = null;
		itemsFactura = new Vector<ItemFactura>();
		indiceRenglonFactura = 0;
		//itemFactura = null;
		//factura = new Factura();

		jTextFieldCodigoCli.requestFocus();
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelSobreFac = new JLabel();
			jLabelSobreFac.setBounds(new Rectangle(725, 99, 110, 20));
			jLabelSobreFac.setText("Sobre Factura Nro:");
			jLabelCantCta = new JLabel();
			jLabelCantCta.setBounds(new Rectangle(278, 65, 34, 20));
			jLabelCantCta.setVisible(false);
			jLabelCantCta.setText("Ctas:");
			jLabelTotalFac = new JLabel();
			jLabelTotalFac.setBounds(new Rectangle(773, 529, 200, 50));
			jLabelTotalFac.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelTotalFac.setText("");
			jLabelTotalFac.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelCondicionIva = new JLabel();
			jLabelCondicionIva.setBounds(new Rectangle(692, 65, 110, 20));
			jLabelCondicionIva.setText("Condición ante IVA:");
			jLabelRecGlobal = new JLabel();
			jLabelRecGlobal.setBounds(new Rectangle(610, 465, 90, 20));
			jLabelRecGlobal.setText("Recargo Global:");
			jLabelDesGlobal = new JLabel();
			jLabelDesGlobal.setBounds(new Rectangle(415, 465, 105, 20));
			jLabelDesGlobal.setText("Descuento Global:");
			jLabelRecItem = new JLabel();
			jLabelRecItem.setBounds(new Rectangle(211, 465, 119, 20));
			jLabelRecItem.setText("Recargo de Artículo:");
			jLabelDescItem = new JLabel();
			jLabelDescItem.setBounds(new Rectangle(10, 465, 131, 20));
			jLabelDescItem.setText("Descuento de Artículo:");
			jLabelTotal = new JLabel();
			jLabelTotal.setHorizontalAlignment(JLabel.CENTER);
			jLabelTotal.setBounds(new Rectangle(773, 504, 200, 25));
			jLabelTotal.setText("TOTAL");
			jLabelTotal.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelIva105 = new JLabel();
			jLabelIva105.setHorizontalAlignment(JLabel.CENTER);
			jLabelIva105.setBounds(new Rectangle(693, 504, 80, 25));
			jLabelIva105.setText("% IVA 10,5");
			jLabelIva105.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelIva27 = new JLabel();
			jLabelIva27.setHorizontalAlignment(JLabel.CENTER);
			jLabelIva27.setBounds(new Rectangle(613, 504, 80, 25));
			jLabelIva27.setText("% IVA 27");
			jLabelIva27.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelIva21 = new JLabel();
			jLabelIva21.setHorizontalAlignment(JLabel.CENTER);
			jLabelIva21.setBounds(new Rectangle(533, 504, 80, 25));
			jLabelIva21.setText("% IVA 21");
			jLabelIva21.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelRecMan = new JLabel();
			jLabelRecMan.setHorizontalAlignment(JLabel.CENTER);
			jLabelRecMan.setBounds(new Rectangle(453, 504, 80, 25));
			jLabelRecMan.setText("Recargo");
			jLabelRecMan.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelBinifManual = new JLabel();
			jLabelBinifManual.setHorizontalAlignment(JLabel.CENTER);
			jLabelBinifManual.setBounds(new Rectangle(373, 504, 80, 25));
			jLabelBinifManual.setText("Bonific.");
			jLabelBinifManual.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelRecarClient = new JLabel();
			jLabelRecarClient.setHorizontalAlignment(JLabel.CENTER);
			jLabelRecarClient.setBounds(new Rectangle(293, 504, 80, 25));
			jLabelRecarClient.setText("Rec. Cli.");
			jLabelRecarClient.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelBonifClient = new JLabel();
			jLabelBonifClient.setHorizontalAlignment(JLabel.CENTER);
			jLabelBonifClient.setBounds(new Rectangle(213, 504, 80, 25));
			jLabelBonifClient.setText("Bonif. Cli.");
			jLabelBonifClient.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelSubTotalTitle = new JLabel();
			jLabelSubTotalTitle.setHorizontalAlignment(JLabel.CENTER);
			jLabelSubTotalTitle.setBounds(new Rectangle(13, 504, 200, 25));
			jLabelSubTotalTitle.setText("SUB TOTAL");
			jLabelSubTotalTitle.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelPreTotal = new JLabel();
			jLabelPreTotal.setBounds(new Rectangle(859, 465, 62, 20));
			jLabelPreTotal.setText("Pre. Total:");
			jLabelPreUnit = new JLabel();
			jLabelPreUnit.setBounds(new Rectangle(887, 435, 55, 20));
			jLabelPreUnit.setText("Pre. Unit:");
			jLabelCantArt = new JLabel();
			jLabelCantArt.setBounds(new Rectangle(158, 435, 54, 20));
			jLabelCantArt.setText("Cantidad:");
			jLabelLetraFac = new JLabel();
			jLabelLetraFac.setBounds(new Rectangle(451, 89, 50, 40));
			jLabelLetraFac.setText("");
			jLabelLetraFac.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelLetraFac.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelFactura = new JLabel();
			jLabelFactura.setBounds(new Rectangle(344, 99, 97, 20));
			jLabelFactura.setText("Nota de Crédito:");
			jLabelNroFac = new JLabel();
			jLabelNroFac.setBounds(new Rectangle(511, 99, 55, 20));
			jLabelNroFac.setText("Número:");
			jLabelListaPre = new JLabel();
			jLabelListaPre.setBounds(new Rectangle(397, 65, 80, 20));
			jLabelListaPre.setText("Lista Precios:");
			jLabelCondVta = new JLabel();
			jLabelCondVta.setBounds(new Rectangle(10, 65, 115, 20));
			jLabelCondVta.setText("Condición de Venta:");
			jLabelDeposito = new JLabel();
			jLabelDeposito.setBounds(new Rectangle(799, 36, 55, 20));
			jLabelDeposito.setText("Depósito:");
			jLabelFecha = new JLabel();
			jLabelFecha.setBounds(new Rectangle(679, 36, 40, 20));
			jLabelFecha.setText("Fecha:");
			jLabelRazonSocial = new JLabel();
			jLabelRazonSocial.setBounds(new Rectangle(205, 35, 80, 20));
			jLabelRazonSocial.setText("Razón Social:");
			jLabelCodigoCli = new JLabel();
			jLabelCodigoCli.setBounds(new Rectangle(10, 35, 75, 20));
			jLabelCodigoCli.setText("Cód. Cliente:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setSize(1024, 815);
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPaneaList(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJTextFieldCodigoCli(), null);
			jContentPane.add(jLabelCodigoCli, null);
			jContentPane.add(jLabelRazonSocial, null);
			jContentPane.add(getJTextFieldRazonSocial(), null);
			jContentPane.add(jLabelFecha, null);
			jContentPane.add(jLabelDeposito, null);
			jContentPane.add(getJComboBoxDeposito(), null);
			jContentPane.add(jLabelCondVta, null);
			jContentPane.add(jLabelListaPre, null);
			jContentPane.add(getJComboBoxListaPre(), null);
			jContentPane.add(jLabelNroFac, null);
			jContentPane.add(getJTextFieldNroFac(), null);
			jContentPane.add(jLabelFactura, null);
			jContentPane.add(jLabelLetraFac, null);
			jContentPane.add(getJTextFieldFechaFac(), null);
			jContentPane.add(getJTextFieldCodigoArt(), null);
			jContentPane.add(getJButtonArt(), null);
			jContentPane.add(jLabelCantArt, null);
			jContentPane.add(getJTextFieldCantArt(), null);
			jContentPane.add(getJTextFieldDescripcionArt(), null);
			jContentPane.add(getJTextFieldPreUnit(), null);
			jContentPane.add(getJTextFieldPreTotal(), null);
			jContentPane.add(jLabelPreUnit, null);
			jContentPane.add(jLabelPreTotal, null);
			jContentPane.add(jLabelSubTotalTitle, null);
			jContentPane.add(jLabelBonifClient, null);
			jContentPane.add(jLabelRecarClient, null);
			jContentPane.add(jLabelBinifManual, null);
			jContentPane.add(jLabelRecMan, null);
			jContentPane.add(jLabelIva21, null);
			jContentPane.add(jLabelIva27, null);
			jContentPane.add(jLabelIva105, null);
			jContentPane.add(jLabelTotal, null);
			jContentPane.add(getJTextFieldBonifCli(), null);
			jContentPane.add(getJTextFieldRecCli(), null);
			jContentPane.add(getJTextFieldBonif(), null);
			jContentPane.add(getJTextFieldRecar(), null);
			jContentPane.add(getJTextFieldIva21(), null);
			jContentPane.add(getJTextFieldIva27(), null);
			jContentPane.add(getJTextFieldIva105(), null);
			jContentPane.add(getJTextFieldBonifCliST(), null);
			jContentPane.add(getJTextFieldRecCliST(), null);
			jContentPane.add(getJTextFieldBonifST(), null);
			jContentPane.add(getJTextFieldRecarST(), null);
			jContentPane.add(getJTextFieldSubTotal(), null);
			jContentPane.add(getJTextFieldEspacio(), null);
			jContentPane.add(jLabelDescItem, null);
			jContentPane.add(getJTextFieldDesItem(), null);
			jContentPane.add(jLabelRecItem, null);
			jContentPane.add(getJTextFieldRecItem(), null);
			jContentPane.add(jLabelDesGlobal, null);
			jContentPane.add(getJTextFieldDescGlobal(), null);
			jContentPane.add(jLabelRecGlobal, null);
			jContentPane.add(getJTextFieldRecGlobal(), null);
			jContentPane.add(getJComboBoxCondVta(), null);
			jContentPane.add(getJButtonClient(), null);
			jContentPane.add(jLabelCondicionIva, null);
			jContentPane.add(getJTextFieldCondicionIva(), null);
			jContentPane.add(jLabelTotalFac, null);
			jContentPane.add(jLabelCantCta, null);
			jContentPane.add(getJTextFieldCantCtas(), null);
			jContentPane.add(getJTextFieldFacNum(), null);
			jContentPane.add(jLabelSobreFac, null);
			jContentPane.add(getJButtonBuscarFactura(), null);
			jContentPane.add(getJCheckBoxDetalle(), null);
			jContentPane.add(getJCheckBoxArticulo(), null);
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
			jScrollPaneaList.setBounds(new Rectangle(5, 135, 1005, 291));
			jScrollPaneaList.setViewportView(getJtableArticulo());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJtableArticulo() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableArticulo == null) {
			jTableArticulo = new MyJtable(getMyTableModel());
			jTableArticulo.setRowSorter(geTableRowSorter());
			jTableArticulo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableArticulo.setRowHeight(20);
			jTableArticulo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableArticulo.setAutoscrolls(true);
			jTableArticulo.setShowVerticalLines(true);
			jTableArticulo.setShowHorizontalLines(true);
			//setea ancho de campos
			jTableArticulo.getColumnModel().getColumn(1).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(2).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(3).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(4).setPreferredWidth(300);
			jTableArticulo.getColumnModel().getColumn(5).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(6).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(7).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(8).setPreferredWidth(8);
			setOcultarColumnasJTable(jTableArticulo, new int[]{0});
			jTableArticulo.setVisible(true);
			jTableArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableArticulo.isEnabled()){
						renglonItemFactura = getSelectedElement();
						if(renglonItemFactura != null){
							//setDatosItemFactura(itemFactura);
							setEnabledButtonsToolbar(true, true, true, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
						}
					}
				}
			});
			jTableArticulo.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(jTableArticulo.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							renglonItemFactura = getSelectedElement();
							if(renglonItemFactura != null){
								//setDatosItemFactura(itemFactura);
								setEnabledButtonsToolbar(true, true, true, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
							}
						}
					}
				}
			});
		}
		return jTableArticulo;
	}

	/*
	 * devuelve el modelo de tabla para crear el jtable.
	 */
	private MyTableModel getMyTableModel(){
		if(myTableModel == null){
			myTableModel = new MyTableModel();
			Vector<String> titulos = setTitlesTableModel();
			myTableModel.setDataVector(null, titulos);

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


	/********************** ELEMENTOS VISUALES *************************/


	/*************COMBOS BOX ***************/

	/**
	 * This method initializes jComboBoxCondVta
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxCondVta() {
		if (jComboBoxCondVta == null) {
			jComboBoxCondVta = new JComboBox();
			jComboBoxCondVta.setBounds(new Rectangle(125, 65, 254, 20));
			jComboBoxCondVta.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(factura != null){
						factura.setCondicionVenta((CondicionVenta)jComboBoxCondVta.getSelectedItem());
						if(factura.getCondicionVenta().getCantidadCuotasDefault() > 1){
							//jTextFieldCantCtas.setEditable(true);
							jTextFieldCantCtas.setText(String.valueOf(factura.getCondicionVenta().getCantidadCuotasDefault()));
						}else{
							//jTextFieldCantCtas.setEditable(false);
							jTextFieldCantCtas.setText(null);
						}
					}
				}
			});
		}
		setItemsjComboBoxCondVta();
		return jComboBoxCondVta;
	}

	/**
	 * This method initializes jComboBoxDeposito
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxDeposito() {
		if (jComboBoxDeposito == null) {
			jComboBoxDeposito = new JComboBox();
			jComboBoxDeposito.setBounds(new Rectangle(854, 36, 150, 20));
			jComboBoxDeposito.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(factura != null)factura.setDeposito((Deposito)jComboBoxDeposito.getSelectedItem());


				}
			});
		}
		setItemsJComboBoxDeposito();
		return jComboBoxDeposito;
	}

	/**
	 * This method initializes jComboBoxListaPre
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxListaPre() {
		if (jComboBoxListaPre == null) {
			jComboBoxListaPre = new JComboBox();
			jComboBoxListaPre.setBounds(new Rectangle(482, 65, 200, 20));
			jComboBoxListaPre.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					/*if(factura != null){
						//factura.setListaPrecio((ListaPrecio)jComboBoxListaPre.getSelectedItem());
						cliente.setListaPrecios((ListaPrecio)jComboBoxListaPre.getSelectedItem());
						recalculaFactura();
						addItemsFacturaTableModel(factura.getItemsFactura());
					}*/

				}
			});
			jComboBoxListaPre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(factura != null){
						if(aclp){
							//factura.setListaPrecio((ListaPrecio)jComboBoxListaPre.getSelectedItem());
						cliente.setListaPrecios((ListaPrecio)jComboBoxListaPre.getSelectedItem());
						recalculaFactura();
						addItemsFacturaTableModel(factura.getItemsFactura());
						aclp = true;
						}
					}
				}
			});
		}
		setItemsjComboBoxListaPre();
		return jComboBoxListaPre;
	}

	/****************** TEXT FIELD ***********************/

	/**
	 * This method initializes jTextFieldCodigoCli
	 *
	 * @return javax.swing.JTextField
	 */
	public JTextField getJTextFieldCodigoCli() {
		if (jTextFieldCodigoCli == null) {
			jTextFieldCodigoCli = new JTextField();
			jTextFieldCodigoCli.setBounds(new Rectangle(95, 35, 80, 20));
			jTextFieldCodigoCli.setDocument(new MyPlainDocument(jTextFieldCodigoCli,24,"D",true));
			jTextFieldCodigoCli.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(!jTextFieldCodigoCli.getText().isEmpty() || !jTextFieldCodigoCli.getText().equals("")){
						cliente = clienteLogica.getCliente(Integer.parseInt(jTextFieldCodigoCli.getText()));
						if(cliente != null){
								factura = new Factura();
								//itemsFactura = new Vector<ItemFactura>();
								itemFactura = null;
								articulo = null;


								setDatosCliente(cliente);
								jTextFieldFechaFac.setText(dateFormatArgen.format(new Date()));


								setEstatusAllComponent(true);
								setEnabledButtonsToolbar(false, true, true, true);

								jLabelLetraFac.setText(
										"<html>" +
											"<b>" +
												"<font color='black' size=12>" +
													getLetraFact(cliente) +
												"</font>" +
											"</b>" +
										"</html>");

								jTextFieldNroFac.setText("0001-" + getNroFacturaCorrelativo(cliente));
								//setNroFacturaCorrelativo(cliente);


								setFactura();

								if(!itemsFactura.isEmpty()){
									recalculaFactura();
									addItemsFacturaTableModel(factura.getItemsFactura());
								}
								jTextFieldCantArt.requestFocus();

						}else{
							JOptionPane.showMessageDialog(null, "El código no pertenece a un cliente existente", "Cliente inexistente", JOptionPane.INFORMATION_MESSAGE);
							resetFactura();
							jTextFieldCodigoCli.requestFocus();
						}
					}else{
						resetFactura();
					}
				}
			});
		}
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
			jTextFieldRazonSocial.setBounds(new Rectangle(290, 35, 350, 20));
		}
		return jTextFieldRazonSocial;
	}

	/**
	 * This method initializes jTextFieldNroFac
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNroFac() {
		if (jTextFieldNroFac == null) {
			jTextFieldNroFac = new JTextField();
			jTextFieldNroFac.setBounds(new Rectangle(581, 99, 100, 20));
			jTextFieldNroFac.setEditable(false);
		}
		return jTextFieldNroFac;
	}

	/**
	 * This method initializes maskFormatterFechaFac
	 *
	 * @return javax.swing.text.MaskFormatter
	 */
	private MaskFormatter getMaskFormatterFechaFac() {
		if (maskFormatterFechaFac == null) {
			try {
				maskFormatterFechaFac = new MaskFormatter("##/##/####");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maskFormatterFechaFac.setPlaceholderCharacter('_');
		}
		return maskFormatterFechaFac;
	}

	/**
	 * This method initializes jTextFieldFechaFac
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getJTextFieldFechaFac() {
		if (jTextFieldFechaFac == null) {
			jTextFieldFechaFac = new JFormattedTextField(getMaskFormatterFechaFac());
			jTextFieldFechaFac.setBounds(new Rectangle(724, 36, 70, 20));
		}
		return jTextFieldFechaFac;
	}

	/**
	 * This method initializes jTextFieldCodigoArt
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCodigoArt() {
		if (jTextFieldCodigoArt == null) {
			jTextFieldCodigoArt = new JTextField();
			jTextFieldCodigoArt.setBounds(new Rectangle(278, 435, 80, 20));
			jTextFieldCodigoArt.setDocument(new MyPlainDocument(jTextFieldCodigoArt,24,"D",true));
			jTextFieldCodigoArt.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(!jTextFieldCodigoArt.getText().isEmpty() && !jTextFieldCodigoArt.getText().equals("")){
						articulo = articuloLogica.getArticulo(Integer.valueOf((jTextFieldCodigoArt.getText())));
						if(articulo != null){
							if(cliente != null){
								calculateDataItemFactura(articulo, cliente);
							}else{
								JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente", "Seleccione un Cliente", JOptionPane.INFORMATION_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "El código no pertenece a un Artículo Existente", "Artículo inexistente", JOptionPane.INFORMATION_MESSAGE);
							jTextFieldCodigoArt.setText(null);
							jTextFieldCodigoArt.requestFocus();

						}
					}
				}
			});
			jTextFieldCodigoArt.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					System.out.println(e.getKeyCode());
					if(e.getKeyCode() == 10){
						addItemFactura(itemFactura);
						addItemsFacturaTableModel(itemsFactura);
						resetDatosArticulo();
						jTextFieldCantArt.requestFocus();
					}
				}
			});
		}
		return jTextFieldCodigoArt;
	}

	/**
	 * This method initializes jButtonArt
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonArt() {
		if (jButtonArt == null) {
			jButtonArt = new JButton();
			jButtonArt.setBounds(new Rectangle(363, 435, 20, 20));
			jButtonArt.setText("...");
			jButtonArt.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorArticulo dialogGestorArticulo = new DialogGestorArticulo(new Frame());
					dialogGestorArticulo.setModal(true);
					dialogGestorArticulo.setVisible(true);
					if(dialogGestorArticulo.getSelectedElement() != null)articulo = dialogGestorArticulo.getSelectedElement();
					if(articulo != null)jTextFieldCodigoArt.setText(String.valueOf(articulo.getCodigo()));
					if(!jTextFieldCodigoArt.getText().isEmpty() && !jTextFieldCodigoArt.getText().equals("")){
						articulo = articuloLogica.getArticulo(Integer.valueOf((jTextFieldCodigoArt.getText())));
						//if(articulo != null){
							if(cliente != null){
								calculateDataItemFactura(articulo, cliente);
							}else{
								JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente", "Seleccione un Cliente", JOptionPane.INFORMATION_MESSAGE);
								jTextFieldCodigoArt.setText(null);
								jTextFieldCodigoCli.requestFocus();
							}
						/*}else{
							JOptionPane.showMessageDialog(null, "El codigo no pertenece a un Articulo Existente", "Articulo inexistente", JOptionPane.INFORMATION_MESSAGE);
							jTextFieldCodigoArt.setText(null);
							jTextFieldCodigoArt.requestFocus();

						}*/
					}
				}
			});
		}
		return jButtonArt;
	}

	/**
	 * This method initializes jTextFieldCantArt
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCantArt() {
		if (jTextFieldCantArt == null) {
			jTextFieldCantArt = new JTextField();
			jTextFieldCantArt.setBounds(new Rectangle(218, 435, 50, 20));
			jTextFieldCantArt.setDocument(new MyPlainDocument(jTextFieldCantArt,10,"D",true));
			jTextFieldCantArt.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost() Cantidad Articulo"); // TODO Auto-generated Event stub focusLost()
					if(!jTextFieldCantArt.getText().isEmpty()) jTextFieldCantArt.setText(getValueDecimalVisual(Float.valueOf(jTextFieldCantArt.getText())));
					if(!jTextFieldCodigoArt.getText().isEmpty() && !jTextFieldCodigoArt.getText().equals("")){
						articulo = articuloLogica.getArticulo(Integer.valueOf((jTextFieldCodigoArt.getText())));
						if(articulo != null && cliente != null){
							calculateDataItemFactura(articulo, cliente);
						}
					}
				}
			});
		}
		return jTextFieldCantArt;
	}

	/**
	 * This method initializes jTextFieldDescripcionArt
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDescripcionArt() {
		if (jTextFieldDescripcionArt == null) {
			jTextFieldDescripcionArt = new JTextField();
			jTextFieldDescripcionArt.setBounds(new Rectangle(388, 435, 485, 20));
			jTextFieldDescripcionArt.setDocument(new MyPlainDocument(jTextFieldDescripcionArt,100,null,true));
			jTextFieldDescripcionArt.setEditable(false);

			jTextFieldDescripcionArt.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(jCheckBoxDetalle.isSelected())jTextFieldPreTotal.requestFocus();
				}
			});
		}
		return jTextFieldDescripcionArt;
	}

	/**
	 * This method initializes jTextFieldPreUnit
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPreUnit() {
		if (jTextFieldPreUnit == null) {
			jTextFieldPreUnit = new JTextField();
			jTextFieldPreUnit.setBounds(new Rectangle(947, 435, 60, 20));
			jTextFieldPreUnit.setEditable(false);
		}
		return jTextFieldPreUnit;
	}

	/**
	 * This method initializes jTextFieldPreTotal
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPreTotal() {
		if (jTextFieldPreTotal == null) {
			jTextFieldPreTotal = new JTextField();
			jTextFieldPreTotal.setBounds(new Rectangle(924, 465, 85, 20));
			jTextFieldPreTotal.setEditable(false);
			jTextFieldPreTotal.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(!jTextFieldPreTotal.getText().isEmpty())jTextFieldPreTotal.setText(getValueDecimalVisual(Float.parseFloat(jTextFieldPreTotal.getText())));
					insertItemFacturaInTabla();
				}
			});
			jTextFieldPreTotal.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					if(e.getKeyCode() == 10){
						insertItemFacturaInTabla();
					}
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
				}
			});
		}
		return jTextFieldPreTotal;
	}

	/**
	 * This method initializes jTextFieldBonifCli
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBonifCli() {
		if (jTextFieldBonifCli == null) {
			jTextFieldBonifCli = new JTextField();
			jTextFieldBonifCli.setBounds(new Rectangle(213, 529, 80, 25));
			jTextFieldBonifCli.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldBonifCli.setEditable(false);
		}
		return jTextFieldBonifCli;
	}

	/**
	 * This method initializes jTextFieldRecCli
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRecCli() {
		if (jTextFieldRecCli == null) {
			jTextFieldRecCli = new JTextField();
			jTextFieldRecCli.setBounds(new Rectangle(293, 529, 80, 25));
			jTextFieldRecCli.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldRecCli.setEditable(false);
		}
		return jTextFieldRecCli;
	}

	/**
	 * This method initializes jTextFieldBonif
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBonif() {
		if (jTextFieldBonif == null) {
			jTextFieldBonif = new JTextField();
			jTextFieldBonif.setBounds(new Rectangle(373, 529, 80, 25));
			jTextFieldBonif.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldBonif.setEditable(false);
		}
		return jTextFieldBonif;
	}

	/**
	 * This method initializes jTextFieldRecar
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRecar() {
		if (jTextFieldRecar == null) {
			jTextFieldRecar = new JTextField();
			jTextFieldRecar.setBounds(new Rectangle(453, 529, 80, 25));
			jTextFieldRecar.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldRecar.setEditable(false);
		}
		return jTextFieldRecar;
	}

	/**
	 * This method initializes jTextFieldIva21
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldIva21() {
		if (jTextFieldIva21 == null) {
			jTextFieldIva21 = new JTextField();
			jTextFieldIva21.setBounds(new Rectangle(533, 529, 80, 25));
			jTextFieldIva21.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldIva21.setEditable(false);
		}
		return jTextFieldIva21;
	}

	/**
	 * This method initializes jTextFieldIva27
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldIva27() {
		if (jTextFieldIva27 == null) {
			jTextFieldIva27 = new JTextField();
			jTextFieldIva27.setBounds(new Rectangle(613, 529, 80, 25));
			jTextFieldIva27.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldIva27.setEditable(false);
		}
		return jTextFieldIva27;
	}

	/**
	 * This method initializes jTextFieldIva105
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldIva105() {
		if (jTextFieldIva105 == null) {
			jTextFieldIva105 = new JTextField();
			jTextFieldIva105.setBounds(new Rectangle(693, 529, 80, 25));
			jTextFieldIva105.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldIva105.setEditable(false);
		}
		return jTextFieldIva105;
	}

	/**
	 * This method initializes jTextFieldBonifCliST
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBonifCliST() {
		if (jTextFieldBonifCliST == null) {
			jTextFieldBonifCliST = new JTextField();
			jTextFieldBonifCliST.setBounds(new Rectangle(213, 554, 80, 25));
			jTextFieldBonifCliST.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldBonifCliST.setEditable(false);
		}
		return jTextFieldBonifCliST;
	}

	/**
	 * This method initializes jTextFieldRecCliST
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRecCliST() {
		if (jTextFieldRecCliST == null) {
			jTextFieldRecCliST = new JTextField();
			jTextFieldRecCliST.setBounds(new Rectangle(293, 554, 80, 25));
			jTextFieldRecCliST.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldRecCliST.setEditable(false);
		}
		return jTextFieldRecCliST;
	}

	/**
	 * This method initializes jTextFieldBonifST
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBonifST() {
		if (jTextFieldBonifST == null) {
			jTextFieldBonifST = new JTextField();
			jTextFieldBonifST.setBounds(new Rectangle(373, 554, 80, 25));
			jTextFieldBonifST.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldBonifST.setEditable(false);
		}
		return jTextFieldBonifST;
	}

	/**
	 * This method initializes jTextFieldRecarST
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRecarST() {
		if (jTextFieldRecarST == null) {
			jTextFieldRecarST = new JTextField();
			jTextFieldRecarST.setBounds(new Rectangle(453, 554, 80, 25));
			jTextFieldRecarST.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldRecarST.setEditable(false);
		}
		return jTextFieldRecarST;
	}

	/**
	 * This method initializes jTextFieldSubTotal
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSubTotal() {
		if (jTextFieldSubTotal == null) {
			jTextFieldSubTotal = new JTextField();
			jTextFieldSubTotal.setHorizontalAlignment(JTextField.CENTER);
			jTextFieldSubTotal.setBounds(new Rectangle(13, 529, 200, 50));
			jTextFieldSubTotal.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldSubTotal.setEditable(false);
		}
		return jTextFieldSubTotal;
	}

	/**
	 * This method initializes jTextFieldEspacio
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldEspacio() {
		if (jTextFieldEspacio == null) {
			jTextFieldEspacio = new JTextField();
			jTextFieldEspacio.setBounds(new Rectangle(533, 554, 240, 25));
			jTextFieldEspacio.setBorder(BorderFactory.createLineBorder(Color.black));
			jTextFieldEspacio.setEditable(false);
		}
		return jTextFieldEspacio;
	}

	/**
	 * This method initializes jTextFieldDesItem
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDesItem() {
		if (jTextFieldDesItem == null) {
			jTextFieldDesItem = new JTextField();
			jTextFieldDesItem.setBounds(new Rectangle(145, 465, 60, 20));
			jTextFieldDesItem.setDocument(new MyPlainDocument(jTextFieldDesItem,10,"D",true));
			jTextFieldDesItem.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					//if(!jTextFieldDesItem.getText().isEmpty()) jTextFieldDesItem.setText(getValuePorcentVisual(Float.valueOf(jTextFieldDesItem.getText())));
					if(!jTextFieldCodigoArt.getText().isEmpty() && !jTextFieldCodigoArt.getText().equals("")){
						articulo = articuloLogica.getArticulo(Integer.valueOf((jTextFieldCodigoArt.getText())));
						if(articulo != null && cliente != null){
							calculateDataItemFactura(articulo, cliente);
						}
					}
				}
			});
		}
		return jTextFieldDesItem;
	}

	/**
	 * This method initializes jTextFieldRecItem
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRecItem() {
		if (jTextFieldRecItem == null) {
			jTextFieldRecItem = new JTextField();
			jTextFieldRecItem.setBounds(new Rectangle(340, 465, 65, 20));
			jTextFieldRecItem.setDocument(new MyPlainDocument(jTextFieldRecItem,10,"D",true));
			jTextFieldRecItem.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					//if(!jTextFieldRecItem.getText().isEmpty()) jTextFieldRecItem.setText(getValuePorcentVisual(Float.valueOf(jTextFieldRecItem.getText())));
					insertItemFacturaInTabla();
				}
			});
		}
		return jTextFieldRecItem;
	}

	/**
	 * This method initializes jTextFieldDescGlobal
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDescGlobal() {
		if (jTextFieldDescGlobal == null) {
			jTextFieldDescGlobal = new JTextField();
			jTextFieldDescGlobal.setBounds(new Rectangle(530, 465, 65, 20));
			jTextFieldDescGlobal.setDocument(new MyPlainDocument(jTextFieldDescGlobal,10,"D",true));
			jTextFieldDescGlobal.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(factura != null)recalculaFactura();
				}
			});
		}
		return jTextFieldDescGlobal;
	}

	/**
	 * This method initializes jTextFieldRecGlobal
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRecGlobal() {
		if (jTextFieldRecGlobal == null) {
			jTextFieldRecGlobal = new JTextField();
			jTextFieldRecGlobal.setBounds(new Rectangle(710, 465, 65, 20));
			jTextFieldRecGlobal.setDocument(new MyPlainDocument(jTextFieldRecGlobal,10,"D",true));
			jTextFieldRecGlobal.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(factura != null)recalculaFactura();
				}
			});
		}
		return jTextFieldRecGlobal;
	}

	/**
	 * This method initializes jButtonClient
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonClient() {
		if (jButtonClient == null) {
			jButtonClient = new JButton();
			jButtonClient.setBounds(new Rectangle(180, 35, 20, 20));
			jButtonClient.setText("...");
			jButtonClient.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorCliente dialogGestorCliente = new DialogGestorCliente(new Frame());
					dialogGestorCliente.setModal(true);
					dialogGestorCliente.setVisible(true);
					if(dialogGestorCliente.getSelectedElement() != null)cliente = dialogGestorCliente.getSelectedElement();
					if(cliente != null){
						jTextFieldCodigoCli.setText(String.valueOf(cliente.getCodigo()));
						if(!jTextFieldCodigoCli.getText().isEmpty() || !jTextFieldCodigoCli.getText().equals("")){
							//cliente = clienteLogica.getCliente(Integer.parseInt(jTextFieldCodigoCli.getText()));
							//if(cliente != null){
									factura = new Factura();
									//itemsFactura = new Vector<ItemFactura>();
									itemFactura = null;
									articulo = null;


									setDatosCliente(cliente);
									jTextFieldFechaFac.setText(dateFormatArgen.format(new Date()));


									setEstatusAllComponent(true);
									setEnabledButtonsToolbar(false, true, true, true);

									jLabelLetraFac.setText(
											"<html>" +
												"<b>" +
													"<font color='black' size=12>" +
														getLetraFact(cliente) +
													"</font>" +
												"</b>" +
											"</html>");

									jTextFieldNroFac.setText("0001-" + getNroFacturaCorrelativo(cliente));
									//setNroFacturaCorrelativo(cliente);


									setFactura();

									if(!itemsFactura.isEmpty()){
										recalculaFactura();
										addItemsFacturaTableModel(factura.getItemsFactura());
									}
									jTextFieldCantArt.requestFocus();

							/*}else{
								JOptionPane.showMessageDialog(null, "El codigo no pertenece a un cliente existente", "Cliente inexistente", JOptionPane.INFORMATION_MESSAGE);
								resetFactura();
								jTextFieldCodigoCli.requestFocus();
							}*/
						}else{
							resetFactura();
						}
					}
				}
			});
		}
		return jButtonClient;
	}

	/**
	 * This method initializes jTextFieldCondicionIva
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCondicionIva() {
		if (jTextFieldCondicionIva == null) {
			jTextFieldCondicionIva = new JTextField();
			jTextFieldCondicionIva.setBounds(new Rectangle(807, 65, 200, 20));
		}
		return jTextFieldCondicionIva;
	}



	/************* GESTION DE COMBO BOX ****************/

	private void setItemsJComboBoxDeposito(){
		ArrayList<Deposito> depositos = depositoLogica.getListDeposito();
		Iterator<Deposito> it = depositos.iterator();
		while(it.hasNext()){
			jComboBoxDeposito.addItem((Deposito)it.next());
		}
	}

	private void setItemsjComboBoxListaPre(){
		ArrayList<ListaPrecio> listasPrecios = listaPrecioLogica.getListListaPrecio();
		Iterator<ListaPrecio> it = listasPrecios.iterator();
		while(it.hasNext()){
			jComboBoxListaPre.addItem((ListaPrecio)it.next());
		}
	}

	private void setItemsjComboBoxCondVta(){
		ArrayList<CondicionVenta> condicionVenta = condicionVentaLogica.getListCondicionVenta();
		Iterator<CondicionVenta> it = condicionVenta.iterator();
		while(it.hasNext()){
			jComboBoxCondVta.addItem((CondicionVenta)it.next());
		}
	}

	private void selectDefaultItemInjComboBoxDeposito(){
		for(int i = 0; i < jComboBoxDeposito.getItemCount(); i++){
			Deposito deposito = (Deposito)jComboBoxDeposito.getItemAt(i);
			if(deposito.isPrincipal()){
				jComboBoxDeposito.setSelectedItem(deposito);
				break;

			}
		}
	}


	private void selectItemInjComboBoxListaPre(ListaPrecio lisPrecio){
		if(lisPrecio != null){
			for(int i = 0; i < jComboBoxListaPre.getItemCount(); i++){
				ListaPrecio listaPrecio = (ListaPrecio)jComboBoxListaPre.getItemAt(i);
				if(lisPrecio.getCodigoListaPrecios() == listaPrecio.getCodigoListaPrecios()){
					jComboBoxListaPre.setSelectedItem(listaPrecio);
					break;
				}
			}
		}
	}

	private void selectItemInjComboBoxCondVta(CondicionVenta condVenta){
		if(condVenta != null){
			for(int i = 0; i < jComboBoxCondVta.getItemCount(); i++){
				CondicionVenta condicionVenta = (CondicionVenta)jComboBoxCondVta.getItemAt(i);
				if(condVenta.getCodigoCondicionVenta() == condicionVenta.getCodigoCondicionVenta()){
					jComboBoxCondVta.setSelectedItem(condicionVenta);
					break;
				}
			}
		}
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

	/***********FUNCIONES FACTURACION ****************/

	//setea en los campos de cliente los datos del cliente seleccionado
	private void setDatosCliente(Cliente cliente){
		if(!cliente.getRazonSocial().isEmpty()){
			jTextFieldRazonSocial.setText(cliente.getRazonSocial());
		}else{
			jTextFieldRazonSocial.setText(cliente.getNombre() + " " + cliente.getApellido());
		}
		jTextFieldCondicionIva.setText(cliente.getCondicionIVA().getDescripcion());
		selectItemInjComboBoxCondVta(cliente.getCondicionVentaDefault());
		aclp=false;
		selectItemInjComboBoxListaPre(cliente.getListaPrecios());
		aclp=true;
	}

	private void calculateDataItemFactura(Articulo articulo, Cliente clie){
		if(articulo != null){
			if(itemFactura == null){
				itemFactura = new ItemFactura();
				itemFactura.setIndice(indiceRenglonFactura);
				indiceRenglonFactura++;
			}
			itemFactura.setArticulo(articulo);
			itemFactura.setCantidad(getValueDecimalReal(jTextFieldCantArt.getText()));
			itemFactura.setPrecioUnitario(getValueDecimalReal(jTextFieldPreUnit.getText()));
			itemFactura.setPrecioTotal(getValueDecimalReal(jTextFieldPreTotal.getText()));
			itemFactura.setDescManual(getValuePorcentReal(jTextFieldDesItem.getText()));
			itemFactura.setRecManual(getValuePorcentReal(jTextFieldRecItem.getText()));
			itemFactura.setIva(articulo.getImpuesto().getPorcentaje());

			ItemFactura itFac = CalculationEngine.calculaImportesItemFactura(itemFactura, clie);
			if(itFac != null){
				itemFactura = itFac;
				setDatosItemFactura(itemFactura);
			}
		}
	}

	private void setDatosItemFactura(ItemFactura itemFac){
		if(itemFac != null){
			jTextFieldDescripcionArt.setText(articulo.getDescripcion());
			jTextFieldPreUnit.setText(getValueDecimalVisual(itemFactura.getPrecioUnitario()));
			jTextFieldPreTotal.setText(getValueDecimalVisual(itemFactura.getPrecioTotal()));
		}
	}


	private Vector<Vector<String>> getVectorRenglonesFactura(Vector<ItemFactura> itemsFac){
		Vector<Vector<String>> renglonesFactura = new Vector<Vector<String>>();
		Vector<String> renglonItemFac = null;
		ItemFactura itemFacI = null;

		Iterator<ItemFactura> itItemFac = itemsFac.iterator();
		while(itItemFac.hasNext()){
			renglonItemFac = new Vector<String>();
			itemFacI = (ItemFactura)itItemFac.next();
			renglonItemFac.add(String.valueOf(itemFacI.getIndice()));
			renglonItemFac.add(getValueDecimalVisual(itemFacI.getCantidad()));
			renglonItemFac.add(itemFacI.getArticulo().getUnidadVta());
			renglonItemFac.add(String.valueOf(itemFacI.getArticulo().getCodigo()));
			renglonItemFac.add(itemFacI.getArticulo().getDescripcion());
			renglonItemFac.add(getValuePorcentVisual(itemFacI.getDescManual()) + "%");
			renglonItemFac.add(getValuePorcentVisual(itemFacI.getRecManual()) + "%");
			renglonItemFac.add(getValuePorcentVisual(itemFacI.getIva()) + "%");
			renglonItemFac.add("$(" + getValueDecimalVisual(itemFacI.getPrecioUnitario())+")");
			renglonItemFac.add("$(" + getValueDecimalVisual(itemFacI.getPrecioTotal())+")");
			renglonesFactura.add(renglonItemFac);
		}
		return renglonesFactura;
	}

	private void recalculaFactura(){
		//if(jCheckBoxArticulo.isSelected()){
			if(factura != null && factura.getItemsFactura() != null){
				setFactura();
				factura = CalculationEngine.calculaFactura(factura);
				setDatosFactura(factura, null);
			}
		/*}else if(jCheckBoxDetalle.isSelected()){
			setFactura();
			setDatosFactura(factura);
		}*/

	}

	private void setDatosFactura(Factura fac, ListaPrecio listaPre){
		if(fac.getSubTotal() != null && fac.getSubTotal() > 0){
			jTextFieldSubTotal.setText("$ -" + getValueDecimalVisual(fac.getSubTotal()));
		}else if(fac.getSubTotal() == 0){
			jTextFieldSubTotal.setText("$ 0.00");
		}else if(fac.getSubTotal() < 0){
			jTextFieldSubTotal.setText("$ " + getValueDecimalVisual(fac.getSubTotal()));
		}
		if(fac.getTotalBonifClient() != null && fac.getTotalBonifClient() > 0){
			jTextFieldBonifCli.setText("$ -" + getValueDecimalVisual(fac.getTotalBonifClient()));
		}else if(fac.getTotalBonifClient() == 0){
			jTextFieldBonifCli.setText("$ 0.00");
		}else if(fac.getTotalBonifClient() < 0){
			jTextFieldBonifCli.setText("$ " + getValueDecimalVisual(fac.getTotalBonifClient()));
		}
		if(fac.getTotalRecarClient() != null && fac.getTotalRecarClient() > 0){
			jTextFieldRecCli.setText("$ -" + getValueDecimalVisual(fac.getTotalRecarClient()));
		}else if(fac.getTotalRecarClient() == 0){
			jTextFieldRecCli.setText("$ 0.00");
		}else if(fac.getTotalRecarClient() < 0){
			jTextFieldRecCli.setText("$ " + getValueDecimalVisual(fac.getTotalRecarClient()));
		}
		if(fac.getTotalBonifGlobal() != null && fac.getTotalBonifGlobal() > 0){
			jTextFieldBonif.setText("$ -" + getValueDecimalVisual(fac.getTotalBonifGlobal()));
		}else if(fac.getTotalBonifGlobal() == 0){
			jTextFieldBonif.setText("$ 0.00");
		}else if(fac.getTotalBonifGlobal() < 0){
			jTextFieldBonif.setText("$ " + getValueDecimalVisual(fac.getTotalBonifGlobal()));
		}
		if(fac.getTotalRecarGlobal() != null && fac.getTotalRecarGlobal() > 0){
			jTextFieldRecar.setText("$ -" + getValueDecimalVisual(fac.getTotalRecarGlobal()));
		}else if(fac.getTotalRecarGlobal() == 0){
			jTextFieldRecar.setText("$ 0.00");
		}else if(fac.getTotalRecarGlobal() < 0){
			jTextFieldRecar.setText("$ " + getValueDecimalVisual(fac.getTotalRecarGlobal()));
		}
		if(fac.getTotalIva21() != null && fac.getTotalIva21() > 0){
			jTextFieldIva21.setText("$ -" + getValueDecimalVisual(fac.getTotalIva21()));
		}else if(fac.getTotalIva21() == 0){
			jTextFieldIva21.setText("$ 0.00");
		}else if(fac.getTotalIva21() < 0){
			jTextFieldIva21.setText("$ " + getValueDecimalVisual(fac.getTotalIva21()));
		}
		if(fac.getTotalIva27() != null && fac.getTotalIva27() > 0){
			jTextFieldIva27.setText("$ -" + getValueDecimalVisual(fac.getTotalIva27()));
		}else if(fac.getTotalIva27() == 0){
			jTextFieldIva27.setText("$ 0.00");
		}else if(fac.getTotalIva27() < 0){
			jTextFieldIva27.setText("$ -" + getValueDecimalVisual(fac.getTotalIva27()));
		}
		if(fac.getTotalIva105() != null && fac.getTotalIva105() > 0){
			jTextFieldIva105.setText("$ -" + getValueDecimalVisual(fac.getTotalIva105()));
		}else if(fac.getTotalIva105() == 0){
			jTextFieldIva105.setText("$ 0.00");
		}else if(fac.getTotalIva105() < 0){
			jTextFieldIva105.setText("$ -" + getValueDecimalVisual(fac.getTotalIva105()));
		}
		if(fac.getTotalFinal() != null && fac.getTotalFinal() > 0){
			jLabelTotalFac.setText("<html>" +
									"<b>" +
										"<font color='red' size = 4>" +
											"$ -" + getValueDecimalVisual(fac.getTotalFinal()) +
										"</font>" +
									"</b>" +
								"</html>");
		}else if(fac.getTotalFinal() == 0){
			jLabelTotalFac.setText("<html>" +
					"<b>" +
						"<font color='red' size = 4>" +
							"$ 0.00"+
						"</font>" +
					"</b>" +
				"</html>");
		}else if(fac.getTotalFinal() < 0){
			jLabelTotalFac.setText("<html>" +
					"<b>" +
						"<font color='red' size = 4>" +
							"$ " + getValueDecimalVisual(fac.getTotalFinal()) +
						"</font>" +
					"</b>" +
				"</html>");
		}
		aclp = false;
		if(listaPre != null)selectItemInjComboBoxListaPre(listaPre);
		aclp = true;

	}

	private void setFactura(){
		if(factura != null && itemsFactura != null && cliente != null){
			factura.setCliente(cliente);
			factura.setCondicionVenta((CondicionVenta)jComboBoxCondVta.getSelectedItem());
			factura.setDeposito((Deposito)jComboBoxDeposito.getSelectedItem());
			factura.setListaPrecio((ListaPrecio)jComboBoxListaPre.getSelectedItem());
			if(!jTextFieldFechaFac.getText().isEmpty() && !jTextFieldFechaFac.getText().equals("__/__/____")){
				try {
					factura.setFechaFacturacion(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaFac.getText())));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			factura.setItemsFactura(itemsFactura);
			factura.setLetraFactura(getLetraFact(factura.getCliente()));
			factura.setNroFactura(getNroFacturaCorrelativo(factura.getCliente()));
			factura.setNroPtoVenta("0001");
			factura.setTipoComprobante("NC");
			factura.setCantCtas(factura.getCondicionVenta().getCantidadCuotasDefault());
			factura.setTotalIva105(getValueDecimalReal(jTextFieldIva105.getText()));
			factura.setTotalIva21(getValueDecimalReal(jTextFieldIva21.getText()));
			factura.setTotalIva27(getValueDecimalReal(jTextFieldIva27.getText()));

			if(facturaAcancelar != null){
				factura.setNroComprobanteRelac(facturaAcancelar.getNroFactura());
				factura.setNroPtoVtaComprobanteRelac(facturaAcancelar.getNroPtoVenta());
				factura.setLetraComprobanteRelac(facturaAcancelar.getLetraFactura());
				factura.setTipoComprobanteRelac(facturaAcancelar.getTipoComprobante());
				factura.setAnulado(true);
			}

		}
	}

	/************** GESTION DE ITEMS DE LA TABLA *******************/

	public void addItemsFacturaTableModel(Vector<ItemFactura> itemsFac){
		if(itemsFac != null){
			//itemsFactura.add(itemFac);
			myTableModel.setDataVector(getVectorRenglonesFactura(itemsFac), setTitlesTableModel());
			jTableArticulo.getColumnModel().getColumn(1).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(2).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(3).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(4).setPreferredWidth(300);
			jTableArticulo.getColumnModel().getColumn(5).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(6).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(7).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(8).setPreferredWidth(8);

			setOcultarColumnasJTable(jTableArticulo, new int[]{0});
		}else{
			myTableModel.setDataVector(null, setTitlesTableModel());
			jTableArticulo.getColumnModel().getColumn(1).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(2).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(3).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(4).setPreferredWidth(300);
			jTableArticulo.getColumnModel().getColumn(5).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(6).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(7).setPreferredWidth(8);
			jTableArticulo.getColumnModel().getColumn(8).setPreferredWidth(8);

			setOcultarColumnasJTable(jTableArticulo, new int[]{0});
		}
	}

	private Vector<String> setTitlesTableModel(){
		Vector<String> titulos = new Vector<String>();
		titulos.add("Índice");
		titulos.add("Cantidad");
		titulos.add("Unidad Vta.");
		titulos.add("CÓdigo");
		titulos.add("Descripción");
		titulos.add("Desc.");
		titulos.add("Recar.");
		titulos.add("IVA");
		titulos.add("P. Unit.");
		titulos.add("P. Total");
		return titulos;
	}

	private void addItemFactura(ItemFactura itemFac){
		if(jCheckBoxArticulo.isSelected()){
			if(!jTextFieldCantArt.getText().isEmpty() && !jTextFieldCantArt.getText().equals("") &&
					!jTextFieldCodigoArt.getText().isEmpty() && !jTextFieldCodigoArt.getText().equals("") &&
					itemFac != null){
				itemsFactura.add(itemFac);
				//setEnabledButtonsToolbar(true, true, true, true);
			}
		}else if(jCheckBoxDetalle.isSelected()){
			if(!jTextFieldDescripcionArt.getText().isEmpty() && !jTextFieldDescripcionArt.getText().equals("")){
				itemsFactura.add(itemFac);
			}

		}

	}


	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	@SuppressWarnings("unchecked")
	public Vector<String> getSelectedElement(){
		try{
			//Vector<String> vectorItemsFac =  (Vector<String>)(myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableArticulo.getSelectedRow()), 0));
			Vector<String> vector =  (Vector<String>)myTableModel.getDataVector().elementAt(tableRowSorter.convertRowIndexToModel(jTableArticulo.getSelectedRow()));
			return  vector;
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}



	/**************** BUTTONS DE TOLBAR  Y TOOLBAR ********************/
	/*DEVULEVE LA JTOOLBAR*/
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 1013, 30));
			jToolBar.setFloatable(false);
			jToolBar.add(getJButtonEliminar());
			jToolBar.add(getJButtonActionOk());
			jToolBar.add(getJButtonActionCancel());
			jToolBar.add(getJButtonActionCloce());
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
			jButtonActionOk.setText("Grabar");
			jButtonActionOk.setPreferredSize(new Dimension(100, 25));
			jButtonActionOk.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonActionOk.setBorderPainted(false);
			jButtonActionOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(factura != null){
						if(factura.getCliente() != null){
							if(!itemsFactura.isEmpty()){
								int selectedOption = JOptionPane.showConfirmDialog(null, "Confirma que desea grabar la Note de Crédito Nro: " + factura.getNroFactura() + " Del Cliente Nro: " + factura.getCliente().getCodigo() + " ?", "Grabar Comprobante", JOptionPane.OK_CANCEL_OPTION);
								if(selectedOption == 0){
									boolean grabar = true;
									boolean resultado = false;
									setFactura();
									/*if(factura.getCondicionVenta().getCodigoCondicionVenta() == 1){
										DialogMedioPago dialogMedioPago = new DialogMedioPago(new Frame(), factura, null);
										dialogMedioPago.setModal(true);
										dialogMedioPago.setVisible(true);
										detallesPago = dialogMedioPago.getDetallesPagos();
										if(detallesPago != null) grabar = true;
									}else{
										grabar = true;
									}*/
									if(grabar){
										resultado = comprobanteLogica.addComprobanteNCCabecera(factura, WinPrincipal.getCaja());
										if(resultado){
											JOptionPane.showMessageDialog(null, "El Comprobante fue guardado con Éxito", "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
											resetCampos();
											setEstatusAllComponent(false);
											setEnabledButtonsToolbar(false, false, false, true);
											printComprobante(factura);
											resetCamposCliente();
											resetDatosArticulo();
											resetFactura();
											addItemsFacturaTableModel(itemsFactura);
										}else{
											JOptionPane.showMessageDialog(null, "Ha Ocurrido un Error al grabar el comprobante\n" +
													"Intente Nuevamente o Consulte con su Administrador del Sistema", "Error al Grabar Comprobante", JOptionPane.OK_OPTION);
											jTextFieldCantArt.requestFocus();
										}
									}else{
										JOptionPane.showMessageDialog(null, "Debe especificar los medios de pago","Especificar Medios de Pago", JOptionPane.ERROR_MESSAGE);
									}
								}else{
									jTextFieldCantArt.requestFocus();
								}
							}else{
								JOptionPane.showMessageDialog(null, "Debe tener al menos 1 detalle para grabar una Nota de Crédito", "Sin Detalle", JOptionPane.INFORMATION_MESSAGE);
								jTextFieldCantArt.requestFocus();
							}
						}else{
							JOptionPane.showMessageDialog(null, "Debe tener un cliente seleccionado para grabar la Note de Crédito", "Sin Cliente Seleccionado", JOptionPane.INFORMATION_MESSAGE);
							jTextFieldCodigoCli.requestFocus();
						}
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
						int selectedOption = JOptionPane.showConfirmDialog(null, "Si Cancela la Operación se Perderá La Nota de Crédito Cargada\n" +
								"Desea Cancelar la Operación?", "Cancelar Operación", JOptionPane.OK_CANCEL_OPTION);
						System.out.println(selectedOption);
						if(selectedOption == 0){
							resetCampos();
							setEstatusAllComponent(false);
							setEnabledButtonsToolbar(false, false, false, true);
							resetCamposCliente();
							resetDatosArticulo();
							resetFactura();
							addItemsFacturaTableModel(itemsFactura);
							JOptionPane.showMessageDialog(null, "La Operación fue Cancelada", "Operación Cancelada", JOptionPane.OK_OPTION);
						}else{
							jTextFieldCantArt.requestFocus();
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
			jButtonaActionCloce.setBorderPainted(false);
			jButtonaActionCloce.setPreferredSize(new Dimension(100, 30));
			jButtonaActionCloce.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int selectedOption = JOptionPane.showConfirmDialog(null, "Si cierra la ventana, se perderan\n" +
							"los datos cargados que no se hayan grabado\n" +
							"desea cerrar esta ventana de todos modos?", "Cerrar", JOptionPane.OK_CANCEL_OPTION);
					System.out.println(selectedOption);
					if(selectedOption == 0){
						dispose();
					}else{

					}


				}
			});
		}

		return jButtonaActionCloce;
	}






	public JButton getJButtonEliminar() {
		if (jButtonActionEliminar == null) {
			jButtonActionEliminar = new JButton();
			jButtonActionEliminar.setBorderPainted(false);
			jButtonActionEliminar.setIcon(new ImageIcon(getClass().getResource("/Cut_24x24-32.png")));
			jButtonActionEliminar.setText("Eliminar Artículo");
			jButtonActionEliminar.setPreferredSize(new Dimension(100, 25));
			jButtonActionEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(renglonItemFactura != null){
						Iterator<ItemFactura> itItemFac = itemsFactura.iterator();
						ItemFactura itemFac = null;
						while(itItemFac.hasNext()){
							itemFac = (ItemFactura)itItemFac.next();
							if(itemFac.getIndice() == Integer.parseInt(renglonItemFactura.get(0))){
								//itemsFactura.remove(itemFac);
								break;
							}
						}
						itemsFactura.remove(itemFac);
						addItemsFacturaTableModel(itemsFactura);
						recalculaFactura();
						setEnabledButtonsToolbar(false, true, true, true);
					}
				}
			});
		}
		return jButtonActionEliminar;
	}


	/********** ACTIONES CON BUTTONS DE TOOLBAR ****************/

	/*habilita o desabilita los botones de la toolbar*/
	public void setEnabledButtonsToolbar(boolean btnEliminar, boolean btnOk, boolean btnCancel, boolean btnCloce){
		jButtonActionEliminar.setEnabled(btnEliminar);
		jButtonActionOk.setEnabled(btnOk);
		jButtonActionCancel.setEnabled(btnCancel);
		jButtonaActionCloce.setEnabled(btnCloce);
	}

	/*setea el estado (enable o editable) de todos los componenetes*/
	public void setEstatusAllComponent(boolean estadoCamposArticulos){

		jTextFieldCodigoCli.setEditable(true);
		jTextFieldFechaFac.setEditable(true);

		//jComboBoxDeposito.setEnabled(false);
		//jComboBoxListaPre.setEditable(false);

		jTextFieldCodigoArt.setEditable(estadoCamposArticulos);
		jTextFieldDescripcionArt.setEditable(estadoCamposArticulos);
		jTextFieldCantArt.setEditable(estadoCamposArticulos);
		jTextFieldDesItem.setEditable(estadoCamposArticulos);
		jTextFieldRecItem.setEditable(estadoCamposArticulos);
		jTextFieldDescGlobal.setEditable(estadoCamposArticulos);
		jTextFieldRecGlobal.setEditable(estadoCamposArticulos);

		jTextFieldCondicionIva.setEditable(false);
		jTextFieldPreUnit.setEditable(false);
		jTextFieldPreTotal.setEditable(false);
		jTextFieldNroFac.setEditable(false);
		jTextFieldRazonSocial.setEditable(false);
		jTextFieldCondicionIva.setEditable(false);
		jTextFieldSubTotal.setEditable(false);
		jTextFieldBonifCli.setEditable(false);
		jTextFieldBonifCliST.setEditable(false);
		jTextFieldRecCli.setEditable(false);
		jTextFieldRecCliST.setEditable(false);
		jTextFieldBonif.setEditable(false);
		jTextFieldBonifST.setEditable(false);
		jTextFieldRecar.setEditable(false);
		jTextFieldRecarST.setEditable(false);
		jTextFieldIva21.setEditable(false);
		jTextFieldIva27.setEditable(false);
		jTextFieldIva105.setEditable(false);
		jTextFieldCantCtas.setEditable(false);
	}


	/*resetea todos los campos*/
	public void resetCampos(){
		Component component[] = getComponents();
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
	}

	private void resetCamposCliente(){
		jTextFieldCodigoCli.setText(null);
		jTextFieldRazonSocial.setText(null);
		jTextFieldCondicionIva.setText(null);
		selectDefaultItemInjComboBoxDeposito();
	}

	private String getLetraFact(Cliente cliente){
		String letraFactura = null;
		if(cliente != null){
			if(cliente.getCondicionIVA().getCodigo() == 1 || cliente.getCondicionIVA().getCodigo() == 5){
				//jLabelLetraFac.setText("A");
				letraFactura = "A";
			}else{
				//jLabelLetraFac.setText("B");
				letraFactura = "B";
			}
		}
		return letraFactura;
	}

	private String getNroFacturaCorrelativo(Cliente cliente){
		NroCorrelativo correlativo = new NroCorrelativo("NC", getLetraFact(cliente), "0001", null);
		nroCorrelativo = correlativo;
		String nroComprobanteCorrelativo = nroCorrelativoLogica.getNextNroComprobante(correlativo);
		nroCorrelativo.setNroComprobante(nroComprobanteCorrelativo);
		return nroComprobanteCorrelativo;

	}

	private void resetFactura(){
		setEstatusAllComponent(false);
		setEnabledButtonsToolbar(false, false, false, true);
		resetCamposCliente();
		nroCorrelativo = null;
		jLabelLetraFac.setText(null);
		jTextFieldNroFac.setText(null);
		jTextFieldFechaFac.setText(null);
		cliente = null;
		factura = null;
		itemsFactura = new Vector<ItemFactura>();
		itemFactura = null;
		articulo = null;
		jTextFieldCodigoCli.requestFocus();
		jTextFieldCantCtas.setText(null);
		jTextFieldCondicionIva.setText(null);

		jTextFieldSubTotal.setText(null);
		jTextFieldBonifCli.setText(null);
		jTextFieldBonifCliST.setText(null);
		jTextFieldRecCli.setText(null);
		jTextFieldRecCliST.setText(null);
		jTextFieldBonif.setText(null);
		jTextFieldBonifST.setText(null);
		jTextFieldRecar.setText(null);
		jTextFieldRecarST.setText(null);
		jTextFieldIva21.setText(null);
		jTextFieldIva27.setText(null);
		jTextFieldIva105.setText(null);
		jLabelTotalFac.setText(null);
	}

	private void resetDatosArticulo(){
		jTextFieldCantArt.setText(null);
		jTextFieldCodigoArt.setText(null);
		jTextFieldDescripcionArt.setText(null);
		jTextFieldPreUnit.setText(null);
		jTextFieldPreTotal.setText(null);
		jTextFieldRecItem.setText(null);
		jTextFieldDesItem.setText(null);
		itemFactura = null;
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
	 * This method initializes jTextFieldCantCtas
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCantCtas() {
		if (jTextFieldCantCtas == null) {
			jTextFieldCantCtas = new JTextField();
			jTextFieldCantCtas.setBounds(new Rectangle(315, 65, 50, 20));
			jTextFieldCantCtas.setVisible(false);
			jTextFieldCantCtas.setDocument(new MyPlainDocument(getJTextFieldCantCtas(), 24, "D", true));
		}
		return jTextFieldCantCtas;
	}

	/*public void setDetallesPagos(Vector<DetallePago> detPagos){
		detallesPago = detPagos;
	}*/

	/**
	 * This method initializes jTextFieldFacNum
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldFacNum() {
		if (jTextFieldFacNum == null) {
			jTextFieldFacNum = new JTextField();
			jTextFieldFacNum.setBounds(new Rectangle(840, 99, 140, 20));
		}
		return jTextFieldFacNum;
	}

	/**
	 * This method initializes jButtonBuscarFactura
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonBuscarFactura() {
		if (jButtonBuscarFactura == null) {
			jButtonBuscarFactura = new JButton();
			jButtonBuscarFactura.setBounds(new Rectangle(985, 99, 20, 20));
			jButtonBuscarFactura.setText("...");
			jButtonBuscarFactura.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(cliente != null){
						Calendar calendar = Calendar.getInstance();
						String fechaHasta = dateFormatArgen.format(calendar.getTime());
						calendar.add(Calendar.DAY_OF_MONTH, -30);
						String fechaDesde = dateFormatArgen.format(calendar.getTime());
						DialogConsultaFacturacion dialogConsultaFacturacion = new DialogConsultaFacturacion(new Frame(), fechaDesde, fechaHasta, cliente);
						dialogConsultaFacturacion.setModal(true);
						//dialogConsultaFacturacion.setFiltroByCliente(cliente);
						dialogConsultaFacturacion.setVisible(true);
						facturaAcancelar = dialogConsultaFacturacion.getComprobanteSelected();
						if(facturaAcancelar != null){
							setFacturaParaNotaCredito(facturaAcancelar);
							jTextFieldFacNum.setEditable(false);
							jTextFieldFacNum.setText(facturaAcancelar.getNroPtoVenta()+"-"+facturaAcancelar.getNroFactura());
						}

					}
				}
			});
		}
		return jButtonBuscarFactura;
	}


	public void setFacturaParaNotaCredito(Factura fac){
		if(fac != null){
			//factura = fac;
			factura.setCantCtas(fac.getCantCtas());
			factura.setDescGlobal(fac.getDescGlobal());
			factura.setDescGlobal(fac.getDescGlobal());
			factura.setRecGlobal(fac.getRecGlobal());
			factura.setSubTotal(fac.getSubTotal());
			factura.setTotalBonifClient(fac.getTotalBonifClient());
			factura.setTotalBonifGlobal(fac.getTotalBonifGlobal());
			factura.setTotalFinal(fac.getTotalFinal());
			factura.setTotalIva105(fac.getTotalIva105());
			factura.setTotalIva21(fac.getTotalIva21());
			factura.setTotalIva27(fac.getTotalIva27());
			factura.setTotalRecarClient(fac.getTotalRecarClient());
			factura.setTotalRecarGlobal(fac.getTotalRecarGlobal());
			factura.setItemsFactura(fac.getItemsFactura());
			itemsFactura = fac.getItemsFactura();
			//setDatosCliente(fac.getCliente());
			setDatosFactura(fac, fac.getListaPrecio());
			addItemsFacturaTableModel(fac.getItemsFactura());
			setEnabledButtonsToolbar(false, true, true, true);
			setEstatusAllComponent(true);
		}


	}

	/**
	 * This method initializes jCheckBoxDetalle
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxDetalle() {
		if (jCheckBoxDetalle == null) {
			jCheckBoxDetalle = new JCheckBox();
			jCheckBoxDetalle.setBounds(new Rectangle(5, 435, 71, 20));
			jCheckBoxDetalle.setText("Detalle");
			jCheckBoxDetalle.setSelected(false);
			jCheckBoxDetalle.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(jCheckBoxDetalle.isSelected()){
						jCheckBoxArticulo.setSelected(false);
						jTextFieldCantArt.setText(null);
						jTextFieldCantArt.setEditable(false);
						jTextFieldCodigoArt.setText(null);
						jTextFieldCodigoArt.setEditable(false);
						jButtonArt.setEnabled(false);
						//jTextFieldDescripcionArt.setText(null);
						jTextFieldDescripcionArt.setEditable(true);
						jTextFieldPreUnit.setText(null);
						jTextFieldPreUnit.setEditable(false);
						jTextFieldPreTotal.setEditable(true);
					}else{
						jCheckBoxArticulo.setSelected(true);
					}
				}
			});
		}
		return jCheckBoxDetalle;
	}

	/**
	 * This method initializes jCheckBoxArticulo
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxArticulo() {
		if (jCheckBoxArticulo == null) {
			jCheckBoxArticulo = new JCheckBox();
			jCheckBoxArticulo.setSelected(true);
			jCheckBoxArticulo.setBounds(new Rectangle(77, 435, 75, 20));
			jCheckBoxArticulo.setText("Articulo");
			jCheckBoxArticulo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(jCheckBoxArticulo.isSelected()){
						jCheckBoxDetalle.setSelected(false);
						jTextFieldCantArt.setEditable(true);
						jTextFieldCodigoArt.setEditable(true);
						jTextFieldDescripcionArt.setEditable(false);
						jButtonArt.setEnabled(true);
						jTextFieldPreUnit.setEditable(false);
						jTextFieldPreTotal.setEditable(false);
					}else{
						jCheckBoxDetalle.setSelected(true);
					}

				}
			});
		}
		return jCheckBoxArticulo;
	}

	private void insertItemFacturaInTabla(){
		if(jCheckBoxArticulo.isSelected()){
			if(!jTextFieldCodigoArt.getText().isEmpty() && !jTextFieldCodigoArt.getText().equals("")){
				articulo = articuloLogica.getArticulo(Integer.valueOf((jTextFieldCodigoArt.getText())));
				if(articulo != null && cliente != null){
					calculateDataItemFactura(articulo, cliente);
				}
			}
			if(itemFactura != null){
				addItemFactura(itemFactura);
				addItemsFacturaTableModel(itemsFactura);
				resetDatosArticulo();
				jTextFieldCantArt.requestFocus();
				recalculaFactura();
			}
		}else if(jCheckBoxDetalle.isSelected()){
			itemFactura = new ItemFactura();
			Articulo art = new Articulo();
			art.setCodigo(000000000);
			art.setActivo(true);
			art.setCostoConImp(Float.valueOf(0));
			art.setCostoSinImp(Float.valueOf(0));
			art.setDeposito(null);
			art.setDescuentoDefault(Float.valueOf(0));
			art.setDescuentoMax(Float.valueOf(0));
			art.setDivision(null);
			art.setFechaAlta(null);
			art.setImpInterno(Float.valueOf(0));
			art.setLinea(null);
			art.setImpuesto(new Impuesto(1, null, Float.valueOf(1), Float.valueOf(0), null, false));
			art.setMarca(null);
			art.setMarckupDefaul(Float.valueOf(0));
			art.setPesoEstimado(Float.valueOf(0));
			art.setPrecioListaBase(Float.valueOf(0));
			art.setProveedor(null);
			art.setRubro(null);
			art.setSinonimo(null);
			art.setStockDefault(Float.valueOf(0));
			art.setStockMax(Float.valueOf(0));
			art.setStockMin(Float.valueOf(0));
			art.setSubRubro(null);
			art.setSubUnidadVta(Float.valueOf(0));
			art.setUnidadMinVta(Float.valueOf(0));
			art.setUnidadVta(null);
			art.setDescripcion(jTextFieldDescripcionArt.getText());
			itemFactura.setArticulo(art);
			itemFactura.setCantidad(Float.valueOf(1));
			itemFactura.setPrecioUnitario(getValueDecimalReal(jTextFieldPreTotal.getText()));
			itemFactura.setPrecioTotal(getValueDecimalReal(jTextFieldPreTotal.getText()));
			itemFactura.setDescManual(Float.valueOf(0));
			itemFactura.setIndice(indiceRenglonFactura);
			indiceRenglonFactura++;
			itemFactura.setIva(Float.valueOf(0));
			itemFactura.setRecManual(Float.valueOf(0));
			itemFactura.setValorIvaCalculado(Float.valueOf(0));

			if(itemFactura != null){
				addItemFactura(itemFactura);
				addItemsFacturaTableModel(itemsFactura);
				resetDatosArticulo();
				recalculaFactura();
				jCheckBoxArticulo.setSelected(true);
				jTextFieldCantArt.requestFocus();
			}

		}
	}

	private void printComprobante(Factura fac){
		int selectedOption = JOptionPane.showConfirmDialog(null, "Desa ver el comprobante?","Vista Previa de Comprobante", JOptionPane.OK_CANCEL_OPTION);
		if(selectedOption == 0){
			try {
				jasperReport = null;
				jasperPrint = null;
				jasperViewer = null;

				pars.put("NRO_COMPROBANTE", fac.getNroFactura());
				pars.put("NRP_PTO_VTA_COMPROBANTE", fac.getNroPtoVenta());
				pars.put("LETRA-COMPROBANTE", fac.getLetraFactura());
				pars.put("TIPO_COMPROBANTE", fac.getTipoComprobante());

				thread1 = null;
				thread2 = null;
				if(dialogLoad != null){
					dialogLoad.setVisible(false);
					dialogLoad.dispose();
					dialogLoad = null;
				}
				dialogLoad = new DialogLoad(new Frame(), "Comprobante: "+fac.getTipoComprobante()+" "+fac.getLetraFactura()+" Nº "+fac.getNroPtoVenta()+"-"+fac.getNroFactura());
				//jLabelLoad.setVisible(false);

				thread1 = new Thread(){
					public void run(){
						try {
						jasperReport = (JasperReport)JRLoader.loadObject("reports/comprobante_NC.jasper");
						jasperPrint = JasperFillManager.fillReport(jasperReport,pars, ManagerDao.getConexion());
						jasperViewer =	new JasperViewer(jasperPrint);
						jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
						jasperViewer.setTitle("Vista Previa: Factura");
						jasperViewer.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
						jasperViewer.removeWindowListener(jasperViewer.getWindowListeners()[0]);
						jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
						thread1.interrupt();
						//jLabelLoad.setVisible(false);
						dialogLoad.setVisible(false);
						jasperViewer.setVisible(true);
						setVisible(true);
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};

				thread2 = new Thread(){
					public void run(){
						thread1.start();
						//if(jasperViewer != null)jLabelLoad.setVisible(false);
						if(jasperViewer != null)dialogLoad.setVisible(false);
						while(jasperViewer == null){
							try {
								sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(jasperViewer != null){
								//jLabelLoad.setVisible(false);
								dialogLoad.setVisible(false);
								dialogLoad.dispose();
								thread2.interrupt();
							}
						}

					}
				};

				//jLabelLoad.setVisible(true);
				this.setVisible(false);
				dialogLoad.setVisible(true);
				dialogLoad.requestFocus();
				thread2.start();

			}catch(Exception es){
				es.printStackTrace();
			}
		}
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"

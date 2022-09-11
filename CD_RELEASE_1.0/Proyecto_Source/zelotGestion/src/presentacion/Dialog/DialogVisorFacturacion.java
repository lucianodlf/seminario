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
package presentacion.Dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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

import logica.CondicionVentaLogica;
import logica.DepositoLogica;
import logica.ListaPrecioLogica;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Cliente;
import dominio.CondicionVenta;
import dominio.Deposito;
import dominio.Factura;
import dominio.ItemFactura;
import dominio.ListaPrecio;

public class DialogVisorFacturacion extends JDialog {


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
	//private Articulo articulo = null;  //  @jve:decl-index=0:
	//private ArticuloLogica articuloLogica = ArticuloLogica.getInstance();  //  @jve:decl-index=0:
	private CondicionVentaLogica condicionVentaLogica = CondicionVentaLogica.getInstance();
	private ListaPrecioLogica listaPrecioLogica = ListaPrecioLogica.getInstance();  //  @jve:decl-index=0:
	private DepositoLogica depositoLogica = DepositoLogica.getInstance();
	@SuppressWarnings("unused")
	private Factura factura = null;  //  @jve:decl-index=0:
	//private ItemFactura itemFactura = null;  //  @jve:decl-index=0:
	//private Cliente cliente = null;  //  @jve:decl-index=0:
	//private ClienteLogica clienteLogica = ClienteLogica.getInstance();

	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:
	private DecimalFormat porcentFormat = new DecimalFormat("###.###");  //  @jve:decl-index=0:
	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	@SuppressWarnings("unused")
	private Vector<ItemFactura> itemsFactura = null;  //  @jve:decl-index=0:
	//private Vector<String> renglonItemFactura = null;  //  @jve:decl-index=0:
	//private NroCorrelativo nroCorrelativo = null;  //  @jve:decl-index=0:
	//private NroCorrelativoLogica nroCorrelativoLogica = NroCorrelativoLogica.getInstance();
	//private ComprobanteLogica comprobanteLogica = ComprobanteLogica.getInstance();  //  @jve:decl-index=0:
	private JComboBox jComboBoxCondVta = null;
	private JButton jButtonClient = null;
	private JLabel jLabelCondicionIva = null;
	private JTextField jTextFieldCondicionIva = null;
	//private int indiceRenglonFactura = 0;
	//private Vector<DetallePago> detallesPago = null;  //  @jve:decl-index=0:

	private JLabel jLabelTotalFac = null;
	private JLabel jLabelCantCta = null;
	private JTextField jTextFieldCantCtas = null;
	private JLabel jLabelAnulada = null;


	/**
	 * This is the xxx default constructor
	 */
	public DialogVisorFacturacion(Frame owner, Factura fac) {
		super(owner);
		initialize(fac);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize(Factura fac) {
		this.setSize(1024, 626);
		this.setMaximumSize(new Dimension(1024, 696));
		this.setMinimumSize(new Dimension(0, 0));
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Visor de Facturas");
		this.setResizable(false);
		resetCampos();
		setEstatusAllComponent(false);
		setEnabledButtonsToolbar(false, false, false, true);
		if(fac != null){
			setFacturaVisualizar(fac);
		}
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelAnulada = new JLabel();
			jLabelAnulada.setBounds(new Rectangle(8, 92, 376, 37));
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
			jLabelPreTotal.setBounds(new Rectangle(860, 435, 62, 20));
			jLabelPreTotal.setText("Pre. Total:");
			jLabelPreUnit = new JLabel();
			jLabelPreUnit.setBounds(new Rectangle(735, 435, 55, 20));
			jLabelPreUnit.setText("Pre. Unit:");
			jLabelCantArt = new JLabel();
			jLabelCantArt.setBounds(new Rectangle(10, 435, 54, 20));
			jLabelCantArt.setText("Cantidad:");
			jLabelLetraFac = new JLabel();
			jLabelLetraFac.setBounds(new Rectangle(451, 89, 50, 40));
			jLabelLetraFac.setText("");
			jLabelLetraFac.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelLetraFac.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelFactura = new JLabel();
			jLabelFactura.setBounds(new Rectangle(391, 99, 50, 20));
			jLabelFactura.setText("Factura:");
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
			jContentPane.add(jLabelAnulada, null);
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
	private JTextField getJTextFieldCodigoCli() {
		if (jTextFieldCodigoCli == null) {
			jTextFieldCodigoCli = new JTextField();
			jTextFieldCodigoCli.setBounds(new Rectangle(95, 35, 80, 20));
			jTextFieldCodigoCli.setDocument(new MyPlainDocument(jTextFieldCodigoCli,24,"D",true));
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
			jTextFieldCodigoArt.setBounds(new Rectangle(130, 435, 80, 20));
			jTextFieldCodigoArt.setDocument(new MyPlainDocument(jTextFieldCodigoArt,24,"D",true));
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
			jButtonArt.setBounds(new Rectangle(215, 435, 20, 20));
			jButtonArt.setText("...");
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
			jTextFieldCantArt.setBounds(new Rectangle(70, 435, 50, 20));
			jTextFieldCantArt.setDocument(new MyPlainDocument(jTextFieldCantArt,10,"D",true));
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
			jTextFieldDescripcionArt.setBounds(new Rectangle(240, 435, 485, 20));
			jTextFieldDescripcionArt.setEditable(false);
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
			jTextFieldPreUnit.setBounds(new Rectangle(795, 435, 60, 20));
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
			jTextFieldPreTotal.setBounds(new Rectangle(925, 435, 85, 20));
			jTextFieldPreTotal.setEditable(false);
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


	/***********FUNCIONES FACTURACION ****************/

	//setea en los campos de cliente los datos del cliente seleccionado
	private void setDatosCliente(Cliente cliente){
		if(!cliente.getRazonSocial().isEmpty()){
			jTextFieldRazonSocial.setText(cliente.getRazonSocial());
		}else{
			jTextFieldRazonSocial.setText(cliente.getNombre() + " " + cliente.getApellido());
		}
		jTextFieldCodigoCli.setText(String.valueOf(cliente.getCodigo()));
		jTextFieldCondicionIva.setText(cliente.getCondicionIVA().getDescripcion());
		selectItemInjComboBoxCondVta(cliente.getCondicionVentaDefault());
		selectItemInjComboBoxListaPre(cliente.getListaPrecios());
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
			renglonItemFac.add("$" + getValueDecimalVisual(itemFacI.getPrecioUnitario()));
			renglonItemFac.add("$" + getValueDecimalVisual(itemFacI.getPrecioTotal()));
			renglonesFactura.add(renglonItemFac);
		}
		return renglonesFactura;
	}


	private void setDatosFactura(Factura fac){
		jTextFieldSubTotal.setText("$ " + getValueDecimalVisual(fac.getSubTotal()));
		jTextFieldBonifCli.setText("$ " + getValueDecimalVisual(fac.getTotalBonifClient()));
		jTextFieldRecCli.setText("$ " + getValueDecimalVisual(fac.getTotalRecarClient()));
		jTextFieldBonif.setText("$ " + getValueDecimalVisual(fac.getTotalBonifGlobal()));
		jTextFieldRecar.setText("$ " + getValueDecimalVisual(fac.getTotalRecarGlobal()));
		jTextFieldIva21.setText("$ " + getValueDecimalVisual(fac.getTotalIva21()));
		jTextFieldIva27.setText("$ " + getValueDecimalVisual(fac.getTotalIva27()));
		jTextFieldIva105.setText("$ " + getValueDecimalVisual(fac.getTotalIva105()));
		jLabelTotalFac.setText("<html>" +
									"<b>" +
										"<font color='red' size = 4>" +
											"$ " + getValueDecimalVisual(fac.getTotalFinal()) +
										"</font>" +
									"</b>" +
								"</html>");

		jTextFieldNroFac.setText(fac.getNroPtoVenta()+"-"+fac.getNroFactura());
		try {
			jTextFieldFechaFac.setText(dateFormatArgen.format(dateFormatJapan.parse(fac.getFechaFacturacion())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(fac.getListaPrecio() != null)selectItemInjComboBoxListaPre(fac.getListaPrecio());

		jLabelLetraFac.setText(
				"<html>" +
					"<b>" +
						"<font color='black' size=12>" +
							fac.getLetraFactura()+
						"</font>" +
					"</b>" +
				"</html>");

		if(fac.isAnulado()){
			jLabelAnulada.setText(
					"<html>" +
						"<b>" +
							"<font color='red' size=5>" +
								"ANULADA POR NOTA DE CREDITO"+
							"</font>" +
						"</b>" +
					"</html>");

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
		titulos.add("Código");
		titulos.add("Descripción");
		titulos.add("Desc.");
		titulos.add("Recar.");
		titulos.add("IVA");
		titulos.add("P. Unit.");
		titulos.add("P. Total");
		return titulos;
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
		return jToolBar;
	}


	private JButton getJButtonActionOk() {
		if (jButtonActionOk == null) {
			jButtonActionOk = new JButton();
			jButtonActionOk.setText("Grabar");
			jButtonActionOk.setPreferredSize(new Dimension(100, 25));
			jButtonActionOk.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonActionOk.setBorderPainted(false);
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
					setVisible(false);
					dispose();
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
			jButtonActionEliminar.setText("Eliminar Articulo");
			jButtonActionEliminar.setPreferredSize(new Dimension(100, 25));
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

		jTextFieldCodigoCli.setEditable(false);
		jTextFieldFechaFac.setEditable(false);
		jComboBoxCondVta.setEnabled(false);
		jComboBoxDeposito.setEnabled(false);
		jComboBoxListaPre.setEnabled(false);
		jButtonArt.setEnabled(false);
		jButtonClient.setEnabled(false);

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


	private void setFacturaVisualizar(Factura fac){
		if(fac != null){
			factura = fac;
			itemsFactura = fac.getItemsFactura();
			setDatosCliente(fac.getCliente());
			setDatosFactura(fac);
			addItemsFacturaTableModel(fac.getItemsFactura());

		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

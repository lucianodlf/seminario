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
import java.awt.Container;
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
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import logica.ArticuloLogica;
import logica.ImpuestoLogica;
import presentacion.Dialog.DialogGestorRubro;
import presentacion.Dialog.DialogGestorSubRubro;
import verificadores.MyPlainDocument;

import complementos.CalculationEngine;
import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Articulo;
import dominio.Cliente;
import dominio.CondicionIva;
import dominio.Impuesto;
import dominio.ItemFactura;
import dominio.ListaPrecio;
import dominio.Rubro;
import dominio.SubRubro;

public class InternalFrameGestorArticulo extends JInternalFrame {


	private JPanel jContentPane = null;
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableArticulo = null;
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
	private JLabel jLabelSinonimo = null;
	private JTextField jTextFieldSinonimo = null;
	private JLabel jLabelProveedor = null;
	private JTextField jTextFieldProveedor = null;
	private JLabel jLabelRubro = null;
	private JTextField jTextFieldRubro = null;
	private JLabel jLabelSubrubro = null;
	private JTextField jTextFieldSubrubro = null;
	private JLabel jLabelFechaAlta = null;
	private JFormattedTextField jTextFieldFechaAlta = null;
	private JLabel jLabelMarckup = null;
	private JTextField jTextFieldMarckupDefault = null;
	private JLabel jLabelStock = null;
	private JTextField jTextFieldStockBase = null;
	private JLabel jLabelPrecioCosto1 = null;
	private JTextField jTextFieldPrecioCostoSinImp = null;
	private JLabel jLabelImpInterno = null;
	private JTextField jTextFieldImpInterno = null;
	private JTextField jTextFieldUnidadVenta = null;
	private JLabel jLabelUnidadVenta = null;
	private JLabel jLabelSubunidadVenta = null;
	private JTextField jTextFieldSubunidadVenta = null;
	private JLabel jLabelUnidadMinVta = null;
	private JTextField jTextFieldMinimoVenta = null;
	private JLabel jLabelStockMin = null;
	private JTextField jTextFieldStockMin = null;
	private JLabel jLabelStockMax = null;
	private JTextField jTextFieldStockMax = null;
	private JLabel jLabelDescuentoMax = null;
	private JTextField jTextFieldDescuentoMax = null;
	private JLabel jLabelDescuentoBase = null;
	private JTextField jTextFieldDescuentoBase = null;
	private JLabel jLabelPesoEstimado = null;
	private JTextField jTextFieldPesoEstimado = null;
	private JPanel jPanelCategoriaArticulo = null;
	private JPanel jPanelInventario = null;
	private JCheckBox jCheckBoxActivo = null;
	private JComboBox jComboBoxAlicuota = null;
	private JCheckBox jCheckBoxPesable = null;
	private JCheckBox jCheckBoxControlaStock = null;
	private JLabel jLabelAlicuota = null;
	private JButton jButtonRubro = null;


	/*clases de logica*/
	private Articulo articulo = null;  //  @jve:decl-index=0:
	private ArticuloLogica articuloLogica = ArticuloLogica.getInstance();  //  @jve:decl-index=0:
	private ImpuestoLogica impuestoLogica = ImpuestoLogica.getInstance();
	private String activeActionType = null; //se utiliza para determina cual es la accion activa (agrega, modificar o eliminar)
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyyMMddhhmmss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");
	private Rubro rubro = null;  //  @jve:decl-index=0:
	private SubRubro subRubro = null;  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:
	private DecimalFormat porcentFormat = new DecimalFormat("###.###");  //  @jve:decl-index=0:
	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:

	private JButton jButtonSubRubro = null;
	private JLabel jLabelPrecioVentaSIva = null;
	private JLabel jLabelPrecioVtaFinal = null;
	private JLabel jLabelLblPrecioVentaSinIva = null;
	private JLabel jLabelLblPreVtaFinal = null;



	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameGestorArticulo() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(1024, 614);
		this.setMaximumSize(new Dimension(1024, 696));
		this.setMinimumSize(new Dimension(0, 0));
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Gestión de Artículos");
		this.setResizable(false);
		resetCampos();
		resetCampos(jPanelCategoriaArticulo);
		resetCampos(jPanelInventario);
		setEstatusAllComponent(false, true, true, true);
		setEstatusAllComponent(false, true, true, true, jPanelCategoriaArticulo);
		setEstatusAllComponent(false, true, true, true, jPanelInventario);
		setVisibleButtonsToolbar(true, true, true, false, false, true);
		setEnabledButtonsToolbar(true, false, false, false, false, true);
		articulo = null;
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelLblPreVtaFinal = new JLabel();
			jLabelLblPreVtaFinal.setBounds(new Rectangle(420, 535, 120, 25));
			jLabelLblPreVtaFinal.setText("<html><font size=5>Precio Final:</font></html>");
			jLabelLblPrecioVentaSinIva = new JLabel();
			jLabelLblPrecioVentaSinIva.setBounds(new Rectangle(5, 535, 210, 25));
			jLabelLblPrecioVentaSinIva.setText("<html><font size=5>Precio De Venta S/ IVA:</font></html>");
			jLabelPrecioVtaFinal = new JLabel();
			jLabelPrecioVtaFinal.setBounds(new Rectangle(560, 525, 150, 50));
			jLabelPrecioVtaFinal.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelPrecioVtaFinal.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelPrecioVentaSIva = new JLabel();
			jLabelPrecioVentaSIva.setBounds(new Rectangle(230, 525, 150, 50));
			jLabelPrecioVentaSIva.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelPrecioVentaSIva.setBorder(BorderFactory.createLineBorder(Color.black));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jLabelAlicuota = new JLabel();
			jLabelAlicuota.setBounds(new Rectangle(211, 405, 56, 20));
			jLabelAlicuota.setText("Alicuota:");
			jLabelPesoEstimado = new JLabel();
			jLabelPesoEstimado.setText("Peso Estimado:");
			jLabelPesoEstimado.setBounds(new Rectangle(5, 80, 90, 20));
			jLabelPesoEstimado.setVisible(false);
			jLabelDescuentoBase = new JLabel();
			jLabelDescuentoBase.setText("Descuento Base: %");
			jLabelDescuentoBase.setBounds(new Rectangle(6, 475, 110, 20));
			jLabelDescuentoMax = new JLabel();
			jLabelDescuentoMax.setText("Descuento Máximo: %");
			jLabelDescuentoMax.setBounds(new Rectangle(211, 475, 130, 20));
			jLabelStockMax = new JLabel();
			jLabelStockMax.setText("Máximo Stock:");
			jLabelStockMax.setBounds(new Rectangle(5, 53, 85, 20));
			jLabelStockMin = new JLabel();
			jLabelStockMin.setText("Mínimo Stock:");
			jLabelStockMin.setBounds(new Rectangle(5, 28, 85, 20));
			jLabelUnidadMinVta = new JLabel();
			jLabelUnidadMinVta.setText("Mínimo de Venta:");
			jLabelUnidadMinVta.setBounds(new Rectangle(5, 155, 100, 20));
			jLabelSubunidadVenta = new JLabel();
			jLabelSubunidadVenta.setText("Subunidad de Venta:");
			jLabelSubunidadVenta.setBounds(new Rectangle(5, 130, 120, 20));
			jLabelUnidadVenta = new JLabel();
			jLabelUnidadVenta.setText("Unidad de Venta:");
			jLabelUnidadVenta.setBounds(new Rectangle(5, 105, 100, 20));
			jLabelImpInterno = new JLabel();
			jLabelImpInterno.setBounds(new Rectangle(211, 440, 105, 20));
			jLabelImpInterno.setText("Impuesto Interno:");
			jLabelPrecioCosto1 = new JLabel();
			jLabelPrecioCosto1.setBounds(new Rectangle(6, 405, 98, 20));
			jLabelPrecioCosto1.setText("Precio De Costo:");
			jLabelStock = new JLabel();
			jLabelStock.setText("<html><b><font color='green'>Stock Actual:</font></b></html>");
			jLabelStock.setBounds(new Rectangle(5, 4, 85, 20));
			jLabelMarckup = new JLabel();
			jLabelMarckup.setBounds(new Rectangle(6, 440, 90, 20));
			jLabelMarckup.setText("Utilidad Base: %");
			jLabelFechaAlta = new JLabel();
			jLabelFechaAlta.setBounds(new Rectangle(6, 290, 70, 20));
			jLabelFechaAlta.setText("Fecha Alta:");
			jLabelSubrubro = new JLabel();
			jLabelSubrubro.setText("Subrubro:");
			jLabelSubrubro.setBounds(new Rectangle(5, 35, 65, 20));
			jLabelRubro = new JLabel();
			jLabelRubro.setText("Rubro:");
			jLabelRubro.setBounds(new Rectangle(5, 5, 50, 20));
			jLabelProveedor = new JLabel();
			jLabelProveedor.setBounds(new Rectangle(176, 290, 65, 20));
			jLabelProveedor.setText("Proveedor:");
			jLabelSinonimo = new JLabel();
			jLabelSinonimo.setBounds(new Rectangle(846, 260, 65, 20));
			jLabelSinonimo.setText("Sinónimo:");
			jLabelDescripcion = new JLabel();
			jLabelDescripcion.setBounds(new Rectangle(151, 260, 75, 20));
			jLabelDescripcion.setText("Descripción:");
			jLabelCodigo = new JLabel();
			jLabelCodigo.setBounds(new Rectangle(6, 260, 50, 20));
			jLabelCodigo.setText("Código:");
			jLabelCriterioBusqueda = new JLabel();
			jLabelCriterioBusqueda.setBounds(new Rectangle(280, 40, 50, 20));
			jLabelCriterioBusqueda.setText("Criterio:");
			jLabelBusqueda = new JLabel();
			jLabelBusqueda.setBounds(new Rectangle(05, 40, 50, 20));
			jLabelBusqueda.setText("Buscar:");
			jContentPane.setSize(1024, 768);
			jContentPane.setLayout(null);
			jContentPane.add(getJTextFieldDescuentoBase(), null);
			jContentPane.add(jLabelDescuentoBase, null);
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
			jContentPane.add(jLabelSinonimo, null);
			jContentPane.add(getJTextFieldSinonimo(), null);
			jContentPane.add(jLabelProveedor, null);
			jContentPane.add(getJTextFieldProveedor(), null);
			jContentPane.add(jLabelFechaAlta, null);
			jContentPane.add(getJTextFieldFechaAlta(), null);
			jContentPane.add(jLabelMarckup, null);
			jContentPane.add(getJTextFieldMarckupDefault(), null);
			jContentPane.add(jLabelPrecioCosto1, null);
			jContentPane.add(getJTextFieldPrecioCosto1(), null);
			jContentPane.add(jLabelImpInterno, null);
			jContentPane.add(getJTextFieldImpInterno(), null);
			jContentPane.add(getJPanelCategoriaArticulo(), null);
			jContentPane.add(getJPanelInventario(), null);
			jContentPane.add(getJCheckBoxActivo(), null);
			jContentPane.add(getJComboBoxAlicuota(), null);
			jContentPane.add(jLabelAlicuota, null);
			jContentPane.add(jLabelDescuentoMax, null);
			jContentPane.add(getJTextFieldDescuentoMax(), null);
			jContentPane.add(getJButtonRubro(), null);
			jContentPane.add(getJButtonSubRubro(), null);
			jContentPane.add(jLabelPrecioVentaSIva, null);
			jContentPane.add(jLabelPrecioVtaFinal, null);
			jContentPane.add(jLabelLblPrecioVentaSinIva, null);
			jContentPane.add(jLabelLblPreVtaFinal, null);
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
			jScrollPaneaList.setBounds(new Rectangle(5, 65, 1005, 190));
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
			setItemsInJtable();
			jTableArticulo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableArticulo.setAutoscrolls(true);
			jTableArticulo.setShowVerticalLines(true);
			jTableArticulo.setShowHorizontalLines(true);
			jTableArticulo.setVisible(true);
			jTableArticulo.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableArticulo.isEnabled()){
						articulo = getSelectedElement();
						if(articulo != null){
							setCamposSelectedElement(articulo);
							setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
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
							articulo = getSelectedElement();
							if(articulo != null){
								setCamposSelectedElement(articulo);
								setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
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
			jComboBoxCriterioBusqueda.addItem("Sinónimo");
			jComboBoxCriterioBusqueda.addItem("Descripción");
			jComboBoxCriterioBusqueda.addItem("Rubro");
			jComboBoxCriterioBusqueda.addItem("Subrubro");
		}
		return jComboBoxCriterioBusqueda;
	}


	/************** ELEMENTOS PARA VISUALIZAR O EDITAR LOS ITEMS DE LA TABLA ***************/

	private JTextField getJTextFieldCodigo() {
		if (jTextFieldCodigo == null) {
			jTextFieldCodigo = new JTextField();
			jTextFieldCodigo.setBounds(new Rectangle(61, 260, 80, 20));

		}
		return jTextFieldCodigo;
	}

	private JTextField getJTextFieldDescripcion() {
		if (jTextFieldDescripcion == null) {
			jTextFieldDescripcion = new JTextField();
			jTextFieldDescripcion.setBounds(new Rectangle(231, 260, 600, 20));
			jTextFieldDescripcion.setDocument(new MyPlainDocument(jTextFieldDescripcion,100,null,true));
		}
		return jTextFieldDescripcion;
	}

	/**
	 * This method initializes jTextFieldSinonimo
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSinonimo() {
		if (jTextFieldSinonimo == null) {
			jTextFieldSinonimo = new JTextField();
			jTextFieldSinonimo.setBounds(new Rectangle(916, 260, 93, 20));
			jTextFieldSinonimo.setText("Sinonimo");
			jTextFieldSinonimo.setDocument(new MyPlainDocument(jTextFieldSinonimo,50,null,true));
		}
		return jTextFieldSinonimo;
	}

	/**
	 * This method initializes jTextFieldProveedor
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldProveedor() {
		if (jTextFieldProveedor == null) {
			jTextFieldProveedor = new JTextField();
			jTextFieldProveedor.setBounds(new Rectangle(251, 290, 250, 20));
			jTextFieldProveedor.setText("Proveedor");
			jTextFieldProveedor.setDocument(new MyPlainDocument(jTextFieldProveedor,100,null,true));
		}
		return jTextFieldProveedor;
	}

	/**
	 * This method initializes jTextFieldMarca
	 *
	 * @return javax.swing.JTextField
	 */
	/*private JTextField getJTextFielMarca() {
		if (jTextFieldMarca == null) {
			jTextFieldMarca = new JTextField();
			jTextFieldMarca.setText("Marca");
			jTextFieldMarca.setBounds(new Rectangle(100, 9, 148, 19));
		}
		return jTextFieldMarca;
	}*/

	/**
	 * This method initializes jTextFieldRubro
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFielRubro() {
		if (jTextFieldRubro == null) {
			jTextFieldRubro = new JTextField();
			jTextFieldRubro.setText("Rubro");
			jTextFieldRubro.setBounds(new Rectangle(60, 5, 465, 20));
			jTextFieldRubro.setDocument(new MyPlainDocument(jTextFieldRubro,50,null,true));
		}
		return jTextFieldRubro;
	}

	/**
	 * This method initializes jTextFieldSubrubro
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSubrubro() {
		if (jTextFieldSubrubro == null) {
			jTextFieldSubrubro = new JTextField();
			jTextFieldSubrubro.setText("Subrubro");
			jTextFieldSubrubro.setBounds(new Rectangle(75, 35, 450, 20));
			jTextFieldSubrubro.setDocument(new MyPlainDocument(jTextFieldSubrubro,50,null,true));
		}
		return jTextFieldSubrubro;
	}

	/**
	 * This method initializes jTextFieldLinea
	 *
	 * @return javax.swing.JTextField
	 */
	/*private JTextField getJTextFieldLinea() {
		if (jTextFieldLinea == null) {
			jTextFieldLinea = new JTextField();
			jTextFieldLinea.setText("Linea");
			jTextFieldLinea.setBounds(new Rectangle(101, 99, 149, 20));
		}
		return jTextFieldLinea;
	}*/

	/**
	 * This method initializes jTextFieldDivision
	 *
	 * @return javax.swing.JTextField
	 */
	/*private JTextField getJTextFieldDivision() {
		if (jTextFieldDivision == null) {
			jTextFieldDivision = new JTextField();
			jTextFieldDivision.setText("Division");
			jTextFieldDivision.setBounds(new Rectangle(103, 131, 150, 19));
		}
		return jTextFieldDivision;
	}*/

	/**
	 * This method initializes jTextFieldFechaAlta
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldFechaAlta() {
		try{
			MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
			maskFormatter.setPlaceholderCharacter('_');
			if (jTextFieldFechaAlta == null) {
				jTextFieldFechaAlta = new JFormattedTextField(maskFormatter);
				jTextFieldFechaAlta.setBounds(new Rectangle(86, 290, 70, 20));
				jTextFieldFechaAlta.setText("Fecha Alta");
			}
		}catch(ParseException e){
			e.printStackTrace();
		}

		return jTextFieldFechaAlta;
	}

	/**
	 * This method initializes jTextFieldMarckupDefault
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldMarckupDefault() {
		if (jTextFieldMarckupDefault == null) {
			jTextFieldMarckupDefault = new JTextField();
			jTextFieldMarckupDefault.setBounds(new Rectangle(101, 440, 80, 20));
			jTextFieldMarckupDefault.setText("Marckup Base");
			jTextFieldMarckupDefault.setDocument(new MyPlainDocument(jTextFieldMarckupDefault,20,"D",true));
			jTextFieldMarckupDefault.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(articulo != null){
						articulo.setMarckupDefaul(getValuePorcentReal(jTextFieldMarckupDefault.getText()));
						recalculaPrecios(articulo);
						//jTextFieldMarckupDefault.setText(getValuePorcentVisual(jTextFieldMarckupDefault.getText()));
					}
				}
			});
		}
		return jTextFieldMarckupDefault;
	}


	/**
	 * This method initializes jTextFieldDeposito
	 *
	 * @return javax.swing.JTextField
	 */
	/*private JTextField getJTextFieldDeposito() {
		if (jTextFieldDeposito == null) {
			jTextFieldDeposito = new JTextField();
			jTextFieldDeposito.setText("Deposito");
			jTextFieldDeposito.setBounds(new Rectangle(138, 3, 87, 20));
		}
		return jTextFieldDeposito;
	}*/

	/**
	 * This method initializes jTextFieldStockBase
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldStockBase() {
		if (jTextFieldStockBase == null) {
			jTextFieldStockBase = new JTextField();
			jTextFieldStockBase.setText("Stock Base");
			jTextFieldStockBase.setBounds(new Rectangle(100, 4, 80, 20));
			jTextFieldStockBase.setDocument(new MyPlainDocument(jTextFieldStockBase,20,"D",true));
			jTextFieldStockBase.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					jTextFieldStockBase.setText(getValueDecimalVisual(jTextFieldStockBase.getText()));
					}
			});
		}
		return jTextFieldStockBase;
	}

	/**
	 * This method initializes jTextFieldPrecioCostoSinImp
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPrecioCosto1() {
		if (jTextFieldPrecioCostoSinImp == null) {
			jTextFieldPrecioCostoSinImp = new JTextField();
			jTextFieldPrecioCostoSinImp.setBounds(new Rectangle(111, 405, 80, 20));
			jTextFieldPrecioCostoSinImp.setText("Costo S/Imp");
			jTextFieldPrecioCostoSinImp.setDocument(new MyPlainDocument(jTextFieldPrecioCostoSinImp,20,"D",true));
			jTextFieldPrecioCostoSinImp.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(articulo != null){
						jTextFieldPrecioCostoSinImp.setText(getValueDecimalVisual(jTextFieldPrecioCostoSinImp.getText()));
						articulo.setCostoSinImp(getValueDecimalReal(jTextFieldPrecioCostoSinImp.getText()));
						recalculaPrecios(articulo);
					}
				}
			});
		}
		return jTextFieldPrecioCostoSinImp;
	}

	/**
	 * This method initializes jTextFieldImpInterno
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldImpInterno() {
		if (jTextFieldImpInterno == null) {
			jTextFieldImpInterno = new JTextField();
			jTextFieldImpInterno.setBounds(new Rectangle(326, 440, 80, 20));
			jTextFieldImpInterno.setText("Imp Interno");
			jTextFieldImpInterno.setDocument(new MyPlainDocument(jTextFieldImpInterno,20,"D",true));
			jTextFieldImpInterno.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(articulo != null){
						jTextFieldImpInterno.setText(getValueDecimalVisual(jTextFieldImpInterno.getText()));
						articulo.setImpInterno(getValueDecimalReal(jTextFieldImpInterno.getText()));
						recalculaPrecios(articulo);

					}
				}
			});
		}
		return jTextFieldImpInterno;
	}

	/**
	 * This method initializes jTextFieldUnidadVenta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldUnidadVenta() {
		if (jTextFieldUnidadVenta == null) {
			jTextFieldUnidadVenta = new JTextField();
			jTextFieldUnidadVenta.setText("Unidad Venta");
			jTextFieldUnidadVenta.setBounds(new Rectangle(115, 105, 100, 20));
			jTextFieldUnidadVenta.setDocument(new MyPlainDocument(jTextFieldUnidadVenta,50,null,true));
		}
		return jTextFieldUnidadVenta;
	}

	/**
	 * This method initializes jTextFieldSubunidadVenta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSubunidadVenta() {
		if (jTextFieldSubunidadVenta == null) {
			jTextFieldSubunidadVenta = new JTextField();
			jTextFieldSubunidadVenta.setText("SubunidadVenta");
			jTextFieldSubunidadVenta.setBounds(new Rectangle(135, 130, 100, 20));
			jTextFieldSubunidadVenta.setDocument(new MyPlainDocument(jTextFieldSubunidadVenta,20,"D",true));
			jTextFieldSubunidadVenta.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					jTextFieldSubunidadVenta.setText(getValueDecimalVisual(jTextFieldSubunidadVenta.getText()));
				}
			});
		}
		return jTextFieldSubunidadVenta;
	}

	/**
	 * This method initializes jTextFieldMinimoVenta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldMinimoVenta() {
		if (jTextFieldMinimoVenta == null) {
			jTextFieldMinimoVenta = new JTextField();
			jTextFieldMinimoVenta.setText("MinimoVenta");
			jTextFieldMinimoVenta.setBounds(new Rectangle(115, 155, 80, 20));
			jTextFieldMinimoVenta.setDocument(new MyPlainDocument(jTextFieldMinimoVenta,20,"D",true));
			jTextFieldMinimoVenta.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					jTextFieldMinimoVenta.setText(getValueDecimalVisual(jTextFieldMinimoVenta.getText()));
				}
			});
		}
		return jTextFieldMinimoVenta;
	}

	/**
	 * This method initializes jTextFieldStockMin
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldStockMin() {
		if (jTextFieldStockMin == null) {
			jTextFieldStockMin = new JTextField();
			jTextFieldStockMin.setText("Minimo Stock");
			jTextFieldStockMin.setBounds(new Rectangle(100, 28, 80, 20));
			jTextFieldStockMin.setDocument(new MyPlainDocument(jTextFieldStockMin,20,"D",true));
			jTextFieldStockMin.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					jTextFieldStockMin.setText(getValueDecimalVisual(jTextFieldStockMin.getText()));
				}
			});
		}
		return jTextFieldStockMin;
	}

	/**
	 * This method initializes jTextFieldStockMax
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldStockMax() {
		if (jTextFieldStockMax == null) {
			jTextFieldStockMax = new JTextField();
			jTextFieldStockMax.setText("Stock Maximo");
			jTextFieldStockMax.setBounds(new Rectangle(100, 53, 80, 20));
			jTextFieldStockMax.setDocument(new MyPlainDocument(jTextFieldStockMax,20,"D",true));
			jTextFieldStockMax.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					jTextFieldStockMax.setText(getValueDecimalVisual(jTextFieldStockMax.getText()));
				}
			});
		}
		return jTextFieldStockMax;
	}

	/**
	 * This method initializes jTextFieldDescuentoMax
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDescuentoMax() {
		if (jTextFieldDescuentoMax == null) {
			jTextFieldDescuentoMax = new JTextField();
			jTextFieldDescuentoMax.setText("Descuento Max");
			jTextFieldDescuentoMax.setBounds(new Rectangle(345, 475, 80, 20));
			jTextFieldDescuentoMax.setDocument(new MyPlainDocument(jTextFieldDescuentoMax,20,"D",true));
			jTextFieldDescuentoMax.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					//jTextFieldDescuentoMax.setText(getValuePorcentVisual(jTextFieldDescuentoMax.getText()));
				}
			});
		}
		return jTextFieldDescuentoMax;
	}

	/**
	 * This method initializes jTextFieldDescuentoBase
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDescuentoBase() {
		if (jTextFieldDescuentoBase == null) {
			jTextFieldDescuentoBase = new JTextField();
			jTextFieldDescuentoBase.setText("Descuento Base");
			jTextFieldDescuentoBase.setBounds(new Rectangle(121, 475, 80, 20));
			jTextFieldDescuentoBase.setDocument(new MyPlainDocument(jTextFieldDescuentoBase,20,"D",true));
			jTextFieldDescuentoBase.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(articulo != null){
						articulo.setDescuentoDefault(getValuePorcentReal(jTextFieldDescuentoBase.getText()));
						recalculaPrecios(articulo);
						//jTextFieldDescuentoBase.setText(getValuePorcentVisual(jTextFieldDescuentoBase.getText()));
					}
				}
			});
		}
		return jTextFieldDescuentoBase;
	}

	/**
	 * This method initializes jTextFieldPesoEstimado
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPesoEstimado() {
		if (jTextFieldPesoEstimado == null) {
			jTextFieldPesoEstimado = new JTextField();
			jTextFieldPesoEstimado.setText("Peso Estimado");
			jTextFieldPesoEstimado.setBounds(new Rectangle(100, 80, 80, 20));
			jTextFieldPesoEstimado.setDocument(new MyPlainDocument(jTextFieldPesoEstimado,20,"D",true));
			jTextFieldPesoEstimado.setVisible(false);
			jTextFieldPesoEstimado.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					jTextFieldPesoEstimado.setText(getValueDecimalVisual(jTextFieldPesoEstimado.getText()));
				}
			});
		}
		return jTextFieldPesoEstimado;
	}

	/**
	 * This method initializes jCheckBoxActivo
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxActivo() {
		if (jCheckBoxActivo == null) {
			jCheckBoxActivo = new JCheckBox();
			jCheckBoxActivo.setBounds(new Rectangle(511, 290, 65, 20));
			jCheckBoxActivo.setText("Activo");
		}
		return jCheckBoxActivo;
	}

	/**
	 * This method initializes jCheckBoxPesable
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxPesable() {
		if (jCheckBoxPesable == null) {
			jCheckBoxPesable = new JCheckBox();
			jCheckBoxPesable.setText("Pesable");
			jCheckBoxPesable.setBounds(new Rectangle(250, 78, 75, 20));
			jCheckBoxPesable.setVisible(false);
		}
		return jCheckBoxPesable;
	}

	/**
	 * This method initializes jCheckBoxControlaStock
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxControlaStock() {
		if (jCheckBoxControlaStock == null) {
			jCheckBoxControlaStock = new JCheckBox();
			jCheckBoxControlaStock.setText("Valida Stock");
			jCheckBoxControlaStock.setBounds(new Rectangle(225, 5, 100, 20));
		}
		return jCheckBoxControlaStock;
	}

	/**
	 * This method initializes jComboBoxAlicuota
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxAlicuota() {
		if (jComboBoxAlicuota == null) {
			jComboBoxAlicuota = new JComboBox();
			jComboBoxAlicuota.setBounds(new Rectangle(281, 405, 190, 20));
			jComboBoxAlicuota.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(articulo != null){
						articulo.setImpuesto((Impuesto)jComboBoxAlicuota.getSelectedItem());
						recalculaPrecios(articulo);
					}
				}
			});
			setItemJComboBoxAlicuota();
		}
		return jComboBoxAlicuota;
	}


	private JButton getJButtonRubro() {
		if (jButtonRubro == null) {
			jButtonRubro = new JButton();
			jButtonRubro.setBounds(new Rectangle(541, 331, 100, 20));
			jButtonRubro.setText("Rubro");
			jButtonRubro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Rubro()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorRubro dialogGestorRubro = new DialogGestorRubro(new Frame());
					dialogGestorRubro.setModal(true);
					dialogGestorRubro.setVisible(true);
					if(dialogGestorRubro.getSelectedElement() != null)rubro = dialogGestorRubro.getSelectedElement();
					if(rubro != null)jTextFieldRubro.setText(rubro.getDescripcion());
				}
			});

		}
		return jButtonRubro;
	}

	/**
	 * This method initializes jPanelCategoriaArticulo
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelCategoriaArticulo() {
		if (jPanelCategoriaArticulo == null) {
			jPanelCategoriaArticulo = new JPanel();
			jPanelCategoriaArticulo.setLayout(null);
			jPanelCategoriaArticulo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jPanelCategoriaArticulo.setBounds(new Rectangle(6, 325, 533, 60));
			jPanelCategoriaArticulo.add(getJTextFielRubro(), null);
			jPanelCategoriaArticulo.add(jLabelSubrubro, null);
			jPanelCategoriaArticulo.add(getJTextFieldSubrubro(), null);
			jPanelCategoriaArticulo.add(jLabelRubro, null);
		}
		return jPanelCategoriaArticulo;
	}

	/**
	 * This method initializes jPanelInventario
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelInventario() {
		if (jPanelInventario == null) {
			jPanelInventario = new JPanel();
			jPanelInventario.setLayout(null);
			jPanelInventario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jPanelInventario.setBounds(new Rectangle(669, 328, 328, 181));
			jPanelInventario.add(jLabelStockMin, null);
			jPanelInventario.add(jLabelStockMax, null);
			jPanelInventario.add(getJTextFieldStockMax(), null);
			jPanelInventario.add(getJTextFieldStockMin(), null);
			jPanelInventario.add(jLabelPesoEstimado, null);
			jPanelInventario.add(getJTextFieldPesoEstimado(), null);
			jPanelInventario.add(jLabelUnidadVenta, null);
			jPanelInventario.add(getJTextFieldUnidadVenta(), null);
			jPanelInventario.add(jLabelSubunidadVenta, null);
			jPanelInventario.add(getJTextFieldSubunidadVenta(), null);
			jPanelInventario.add(jLabelUnidadMinVta, null);
			jPanelInventario.add(getJTextFieldMinimoVenta(), null);
			jPanelInventario.add(getJCheckBoxPesable(), null);
			jPanelInventario.add(getJCheckBoxControlaStock(), null);
			jPanelInventario.add(jLabelStock, null);
			jPanelInventario.add(getJTextFieldStockBase(), null);
		}
		return jPanelInventario;
	}




	/************* GESTION DE COMBO BOX ****************/

	private void setItemJComboBoxAlicuota(){
		ArrayList<Impuesto> impuestos = impuestoLogica.getListImpuestos();
		Iterator<Impuesto> it = impuestos.iterator();
		while(it.hasNext()){
			jComboBoxAlicuota.addItem((Impuesto)it.next());
		}
	}

	private void setAlicuotaDefaultInJComboBoxAlicuota(){
		for(int i = 0; i < jComboBoxAlicuota.getItemCount(); i++){
			Impuesto impuesto = (Impuesto)jComboBoxAlicuota.getItemAt(i);
			if(impuesto.isDefault()){
				jComboBoxAlicuota.setSelectedItem(impuesto);
				break;
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
		ArrayList<Articulo> articulos = articuloLogica.getListArticulo();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Sinónimo");
		titulos.add("Descripción");
		titulos.add("Rubro");
		titulos.add("Subrubro");
		titulos.add("Stock");
		titulos.add("Precio Costo Neto");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(articulos != null){
			Iterator<Articulo> it = articulos.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				articulo = (Articulo)it.next();
				renglon.add(String.valueOf(articulo.getCodigo()));
				renglon.add(articulo.getSinonimo());
				renglon.add(articulo.getDescripcion());
				renglon.add(articulo.getRubro().toString());
				renglon.add(articulo.getSubRubro().toString());
				renglon.add(getValueDecimalVisual(articulo.getStockDefault()));
				renglon.add("$ " + getValueDecimalVisual(articulo.getCostoSinImp()));
				registros.add(renglon);
			}
		}
		myTableModel.setDataVector(registros, titulos);
		//setea ancho de campos
		jTableArticulo.getColumnModel().getColumn(0).setPreferredWidth(8);
		jTableArticulo.getColumnModel().getColumn(1).setPreferredWidth(8);
		jTableArticulo.getColumnModel().getColumn(2).setPreferredWidth(400);
		jTableArticulo.getColumnModel().getColumn(3).setPreferredWidth(10);
		jTableArticulo.getColumnModel().getColumn(4).setPreferredWidth(10);
		jTableArticulo.getColumnModel().getColumn(5).setPreferredWidth(4);
		jTableArticulo.getColumnModel().getColumn(6).setPreferredWidth(4);

	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	public Articulo getSelectedElement(){
		try{
			int codigo = Integer.parseInt((String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableArticulo.getSelectedRow()), 0));
			articulo = articuloLogica.getArticulo(codigo);
			return articulo;
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
	public void setCamposSelectedElement(Articulo articulo){
		try{
			jTextFieldCodigo.setText(String.valueOf(articulo.getCodigo()));
			jTextFieldSinonimo.setText(articulo.getSinonimo());
			this.rubro = articulo.getRubro();
			this.subRubro = articulo.getSubRubro();
			jTextFieldRubro.setText(articulo.getRubro().toString());
			jTextFieldSubrubro.setText(articulo.getSubRubro().toString());
			jTextFieldDescripcion.setText(articulo.getDescripcion());
			jTextFieldFechaAlta.setText(dateFormatArgen.format(dateFormatJapan.parse(articulo.getFechaAlta())));
			jTextFieldMarckupDefault.setText(getValuePorcentVisual(articulo.getMarckupDefaul()));
			jTextFieldStockBase.setText(getValueDecimalVisual(articulo.getStockDefault()));
			jTextFieldPrecioCostoSinImp.setText(getValueDecimalVisual(articulo.getCostoSinImp()));
			jTextFieldImpInterno.setText(getValueDecimalVisual((articulo.getImpInterno())));
			jTextFieldUnidadVenta.setText(articulo.getUnidadVta());
			jTextFieldSubunidadVenta.setText(getValueDecimalVisual((articulo.getSubUnidadVta())));
			jTextFieldMinimoVenta.setText(getValueDecimalVisual(articulo.getUnidadMinVta()));
			jTextFieldStockMin.setText(getValueDecimalVisual((articulo.getStockMin())));
			jTextFieldStockMax.setText(getValueDecimalVisual((articulo.getStockMax())));
			jTextFieldDescuentoMax.setText(getValuePorcentVisual(articulo.getDescuentoMax()));
			jTextFieldDescuentoBase.setText(getValuePorcentVisual(articulo.getDescuentoDefault()));
			jTextFieldPesoEstimado.setText(getValueDecimalVisual(articulo.getPesoEstimado()));
			jCheckBoxActivo.setSelected(articulo.isActivo());

			for(int i = 0; i < jComboBoxAlicuota.getItemCount(); i++){
				Impuesto impuesto = (Impuesto)jComboBoxAlicuota.getItemAt(i);
				if(articulo.getImpuesto().getCodigo() == impuesto.getCodigo()){
					jComboBoxAlicuota.setSelectedItem(impuesto);
					break;
				}
			}
			recalculaPrecios(articulo);

		}catch (ParseException e){
			e.printStackTrace();
		}
	}


	/************** GESTION DE ITEMS DE LA BASE DE DATOS Y DE LA TABLA ***********************/

	/*AGREGA UN NUEVO ITEM A LA BASE DE DATOS Y A LA TABLA*/
	public int addItem(Articulo articulo){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldDescripcion.getText().isEmpty()){

					/*SETEA LOS VALORES*/
					articulo.setSinonimo(jTextFieldSinonimo.getText());
					articulo.setProveedor(null);
					articulo.setMarca(null);
					articulo.setRubro(rubro);
					articulo.setSubRubro(subRubro);
					articulo.setLinea(null);
					articulo.setDivision(null);
					articulo.setDescripcion(jTextFieldDescripcion.getText());
					articulo.setMarckupDefaul(getValuePorcentReal(jTextFieldMarckupDefault.getText()));
					articulo.setDeposito(null);
					articulo.setStockDefault(getValueDecimalReal(jTextFieldStockBase.getText()));
					articulo.setCostoSinImp(getValueDecimalReal(jTextFieldPrecioCostoSinImp.getText()));
					articulo.setCostoConImp(new Float(0));
					articulo.setImpInterno(getValueDecimalReal(jTextFieldImpInterno.getText()));
					articulo.setUnidadVta(jTextFieldUnidadVenta.getText());
					articulo.setSubUnidadVta(getValueDecimalReal(jTextFieldSubunidadVenta.getText()));
					articulo.setUnidadMinVta(getValueDecimalReal(jTextFieldMinimoVenta.getText()));
					articulo.setStockMin(getValueDecimalReal(jTextFieldStockMin.getText()));
					articulo.setStockMax(getValueDecimalReal(jTextFieldStockMax.getText()));
					articulo.setDescuentoMax(getValuePorcentReal(jTextFieldDescuentoMax.getText()));
					articulo.setDescuentoDefault(getValuePorcentReal(jTextFieldDescuentoBase.getText()));
					articulo.setPesoEstimado(getValueDecimalReal(jTextFieldPesoEstimado.getText()));
					articulo.setActivo(jCheckBoxActivo.isSelected());
					articulo.setImpuesto((Impuesto)jComboBoxAlicuota.getSelectedItem());
					//articulo.setPrecioListaBase(getValueDecimalReal(jTextFieldPrecioListaBase.getText()));


					/*EJECUTA EL ADD CON LA LOGICA Y EVALUA LA RESPUESTA*/
					if(articuloLogica.addArticulo(articulo)){
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
	public int updateItem(Articulo articulo){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldDescripcion.getText().isEmpty()){
				/*setea los nuevos valores al objeto seleccionado para modificar*/
				articulo.setCodigo(Integer.parseInt(jTextFieldCodigo.getText()));
				articulo.setSinonimo(jTextFieldSinonimo.getText());
				articulo.setProveedor(null);
				articulo.setMarca(null);
				articulo.setRubro(rubro);
				articulo.setSubRubro(subRubro);
				articulo.setLinea(null);
				articulo.setDivision(null);
				articulo.setDescripcion(jTextFieldDescripcion.getText());
				//articulo.setFechaAlta(jTextFieldFechaAlta.getText());
				articulo.setMarckupDefaul(getValuePorcentReal(jTextFieldMarckupDefault.getText()));
				articulo.setDeposito(null);
				articulo.setStockDefault(getValueDecimalReal(jTextFieldStockBase.getText()));
				articulo.setCostoSinImp(getValueDecimalReal(jTextFieldPrecioCostoSinImp.getText()));
				articulo.setCostoConImp(new Float(0));
				articulo.setImpInterno(getValueDecimalReal(jTextFieldImpInterno.getText()));
				articulo.setUnidadVta(jTextFieldUnidadVenta.getText());
				articulo.setSubUnidadVta(getValueDecimalReal(jTextFieldSubunidadVenta.getText()));
				articulo.setUnidadMinVta(getValueDecimalReal(jTextFieldMinimoVenta.getText()));
				articulo.setStockMin(getValueDecimalReal(jTextFieldStockMin.getText()));
				articulo.setStockMax(getValueDecimalReal(jTextFieldStockMax.getText()));
				articulo.setDescuentoMax(getValuePorcentReal(jTextFieldDescuentoMax.getText()));
				articulo.setDescuentoDefault(getValuePorcentReal(jTextFieldDescuentoBase.getText()));
				articulo.setPesoEstimado(getValueDecimalReal(jTextFieldPesoEstimado.getText()));
				articulo.setActivo(jCheckBoxActivo.isSelected());
				articulo.setImpuesto((Impuesto)jComboBoxAlicuota.getSelectedItem());
				//articulo.setPrecioListaBase(getValueDecimalReal(jTextFieldPrecioListaBase.getText()));

				/*EJECUTA EL UDPATE CON LA LOGICA*/
				if(articuloLogica.updateArticulo(articulo)){
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
	public int deleteItem(Articulo articulo){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			JOptionPane.showMessageDialog(null, "El Artículo Seleccionado será Eliminado\n" +
					"Código: "+articulo.getCodigo()+"\n" +
					"Descripción: "+articulo.getDescripcion(),"Eliminar",JOptionPane.INFORMATION_MESSAGE);
			int selectedOption = JOptionPane.showConfirmDialog(null, "Desea Eliminar el Artículo","Eliminar", JOptionPane.OK_CANCEL_OPTION);
			if(selectedOption == 0){
				/*EJECUTA EL DELETE CON LA LOGICA*/
				if(articuloLogica.deleteArticulo(articulo.getCodigo())){
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
			jToolBar.setBounds(new Rectangle(0, 0,1024, 30));
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
						int statusAction = addItem(articulo);
						if(statusAction == 0){
							setItemsInJtable();
							resetCampos();
							resetCampos(jPanelCategoriaArticulo);
							resetCampos(jPanelInventario);
							int selectedOption = JOptionPane.showConfirmDialog(null, "Desea agregar un nuevo elemento", "Operación Satisfactoria!", JOptionPane.OK_CANCEL_OPTION);
							System.out.println(selectedOption);
							if(selectedOption == 0){
								jTextFieldCodigo.setText(String.valueOf(articuloLogica.getNewCodigoItems()));
								jTextFieldDescripcion.requestFocus(true);
							}else{
								activeActionType = null;
								setEnabledButtonsToolbar(true, false, false, false, false, true);
								setVisibleButtonsToolbar(true, true, true, false, false, true);

								setEstatusAllComponent(false, true, true, true);
								setEstatusAllComponent(false, true, true, true, jPanelCategoriaArticulo);
								setEstatusAllComponent(false, true, true, true, jPanelInventario);
								resetCampos();
								resetCampos(jPanelCategoriaArticulo);
								resetCampos(jPanelInventario);
							}
						}else if(statusAction == 2){

							setEstatusAllComponent(false, true, true, true);
							setEstatusAllComponent(false, true, true, true, jPanelCategoriaArticulo);
							setEstatusAllComponent(false, true, true, true, jPanelInventario);
							setEnabledButtonsToolbar(true, false, false, false, false, true);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							activeActionType = null;
							resetCampos();
							resetCampos(jPanelCategoriaArticulo);
							resetCampos(jPanelInventario);
						}
					}else if(activeActionType.equals("UPDATE")){
						int statusAction = updateItem(getSelectedElement());
						if(statusAction == 0){
							setItemsInJtable();
							activeActionType = null;
							setEnabledButtonsToolbar(true, false, false, false, false, true);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							setEstatusAllComponent(false, true, true, true);
							setEstatusAllComponent(false, true, true, true, jPanelCategoriaArticulo);
							setEstatusAllComponent(false, true, true, true, jPanelInventario);
							resetCampos();
							resetCampos(jPanelCategoriaArticulo);
							resetCampos(jPanelInventario);

						}else if(statusAction == 2){

							setEstatusAllComponent(false, true, true, true);
							setEstatusAllComponent(false, true, true, true, jPanelCategoriaArticulo);
							setEstatusAllComponent(false, true, true, true, jPanelInventario);
							setEnabledButtonsToolbar(true, false, false, false, false, true);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							activeActionType = null;
							resetCampos();
							resetCampos(jPanelCategoriaArticulo);
							resetCampos(jPanelInventario);
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
							setEstatusAllComponent(false, true, true, true, jPanelCategoriaArticulo);
							setEstatusAllComponent(false, true, true, true, jPanelInventario);
							resetCampos();
							resetCampos(jPanelCategoriaArticulo);
							resetCampos(jPanelInventario);
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
					articulo = new Articulo();
					resetCampos();
					resetCampos(jPanelCategoriaArticulo);
					resetCampos(jPanelInventario);
					setEstatusAllComponent(true, false, false, false);
					setEstatusAllComponent(true, false, false, false, jPanelCategoriaArticulo);
					setEstatusAllComponent(true, false, false, false, jPanelInventario);
					setVisibleButtonsToolbar(true, true, true, true, true, true);
					setEnabledButtonsToolbar(false, false, false, true, true, false);
					jTextFieldCodigo.setText(String.valueOf(articuloLogica.getNewCodigoItems()));
					jTextFieldCodigo.setEditable(false);
					jTextFieldFechaAlta.setText(dateFormatArgen.format(new Date()));
					jTextFieldFechaAlta.setEditable(false);
					jCheckBoxActivo.setSelected(true);
					rubro = null;
					subRubro = null;
					setAlicuotaDefaultInJComboBoxAlicuota();
					articulo.setImpuesto((Impuesto)jComboBoxAlicuota.getSelectedItem());
					activeActionType = "ADD";

					//jTextFieldMarckupDefault.setText(getValuePorcentVisual(jTextFieldMarckupDefault.getText()));
					//System.out.println(getValuePorcentVisual(jTextFieldMarckupDefault.getText()));
					//System.out.println(returnValueDecimal(jTextFieldMarckupDefault, null));
					//System.out.println(returnValueDecimal(null, jTextFieldMarckupDefault.getText()));
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
							setEstatusAllComponent(true, false, false, false, jPanelCategoriaArticulo);
							setEstatusAllComponent(true, false, false, false, jPanelInventario);
							setVisibleButtonsToolbar(true, true, true, true, true, true);
							setEnabledButtonsToolbar(false, false, false, true, true, false);
							//jTextFieldCodigo.setText(String.valueOf(articuloLogica.getNewCodigoItems()));
							jTextFieldCodigo.setEditable(false);
							jTextFieldFechaAlta.setEditable(false);
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
					//setEstatusAllComponent(false, false, false, false);
					setVisibleButtonsToolbar(true, true, true, false, false, true);
					setEnabledButtonsToolbar(false, false, false, false, false, false);
					int statusAction = deleteItem(getSelectedElement());
					if(statusAction == 0){
						setItemsInJtable();
						setEnabledButtonsToolbar(true, false, false, false, false, true);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						//setEstatusAllComponent(false, true, true, true);
						resetCampos();
						resetCampos(jPanelCategoriaArticulo);
						resetCampos(jPanelInventario);
					}else{
						setEnabledButtonsToolbar(true, false, false, false, false, true);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						//setEstatusAllComponent(false, true, true, true);
						resetCampos();
						resetCampos(jPanelCategoriaArticulo);
						resetCampos(jPanelInventario);
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
			}else if(component[i].getClass() == JComboBox.class){
				((JComboBox)component[i]).setEnabled(estado);
			}
		}
		jComboBoxCriterioBusqueda.setEnabled(estadoComboBoxBusqueda);
		jTextFieldBusqueda.setEditable(estadoTextBusqueda);
		jTableArticulo.setEnabled(estadoTableList);
	}

	public void setEstatusAllComponent(boolean estado, boolean estadoTextBusqueda, boolean estadoComboBoxBusqueda, boolean estadoTableList, Container container){
		Component component[] = container.getComponents();
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
			}else if(component[i].getClass() == JComboBox.class){
				((JComboBox)component[i]).setEnabled(estado);
			}
		}
		jComboBoxCriterioBusqueda.setEnabled(estadoComboBoxBusqueda);
		jTextFieldBusqueda.setEditable(estadoTextBusqueda);
		jTableArticulo.setEnabled(estadoTableList);
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
		setFilterInTabla("", 0);
		jLabelPrecioVentaSIva.setText(null);
		jLabelPrecioVtaFinal.setText(null);
	}

	public void resetCampos(Container container){
		Component component[] = container.getComponents();
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


	//recalcula los precios del articulo
	private ItemFactura recalculaPrecios(Articulo articulo){
		if(articulo != null && articulo.getImpuesto() != null){
			ItemFactura itemFactura = new ItemFactura();
			Cliente cliente = new Cliente();
			CondicionIva condicionIva = new CondicionIva();
			ListaPrecio listaPrecio = new ListaPrecio();

			listaPrecio.setCodigoListaPrecios(1);
			condicionIva.setDiscriminaIVA(true);
			cliente.setCondicionIVA(condicionIva);
			cliente.setListaPrecios(listaPrecio);
			itemFactura.setArticulo(articulo);
			itemFactura.setCantidad(Float.valueOf(1));
			itemFactura.setDescManual(Float.valueOf(0));
			itemFactura.setIva(articulo.getImpuesto().getPorcentaje());
			itemFactura.setRecManual(Float.valueOf(0));

			ItemFactura itFacResul = CalculationEngine.calculaImportesItemFactura(itemFactura, cliente);
			if(itFacResul != null){
				jLabelPrecioVentaSIva.setText(
						"<html>" +
						"	<b>" +
						"		<font color='black' size=5> " +
									"$ " + getValueDecimalVisual(itFacResul.getPrecioUnitario()) +
						"		</font>" +
						"	</b>" +
						"</html>");

				jLabelPrecioVtaFinal.setText(
						"<html>" +
						"	<b>" +
						"		<font color='red' size=5> " +
									"$ " + getValueDecimalVisual(itFacResul.getPrecioTotal()) +
						"		</font>" +
						"	</b>" +
						"</html>");
			}
			return itFacResul;
		}
		return null;
	}



	public void setRubro(Rubro rubro){
		this.rubro = rubro;
		jTextFieldRubro.setText(rubro.getDescripcion());
	}

	/**
	 * This method initializes jButtonSubRubro
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonSubRubro() {
		if (jButtonSubRubro == null) {
			jButtonSubRubro = new JButton();
			jButtonSubRubro.setBounds(new Rectangle(542, 358, 100, 20));
			jButtonSubRubro.setText("SubRubro");
			jButtonSubRubro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("SubRubro()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorSubRubro dialogGestorSubRubro = new DialogGestorSubRubro(new Frame());
					dialogGestorSubRubro.setModal(true);
					dialogGestorSubRubro.setVisible(true);
					if(dialogGestorSubRubro.getSelectedElement() != null)subRubro = dialogGestorSubRubro.getSelectedElement();
					if(subRubro != null)jTextFieldSubrubro.setText(subRubro.getDescripcion());
				}
			});
		}
		return jButtonSubRubro;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

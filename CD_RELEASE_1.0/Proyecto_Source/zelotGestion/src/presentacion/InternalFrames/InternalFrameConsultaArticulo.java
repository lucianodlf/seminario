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
import java.awt.Rectangle;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
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

import logica.ArticuloLogica;
import logica.ListaPrecioLogica;
import verificadores.MyPlainDocument;

import complementos.CalculationEngine;
import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Articulo;
import dominio.Cliente;
import dominio.CondicionIva;
import dominio.ItemFactura;
import dominio.ListaPrecio;

public class InternalFrameConsultaArticulo extends JInternalFrame {


	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="10,719"
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
	private JLabel jLabelSinonimo = null;
	private JTextField jTextFieldSinonimo = null;
	private JLabel jLabelStock = null;
	private JTextField jTextFieldStockBase = null;
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
	private JLabel jLabelPesoEstimado = null;
	private JTextField jTextFieldPesoEstimado = null;
	private JPanel jPanelInventario = null;
	private JLabel jLabelLblPrecioVentaSinIva = null;
	private JCheckBox jCheckBoxPesable = null;
	private JCheckBox jCheckBoxControlaStock = null;
	private JLabel jLabelPrecioVentaSIva = null;
	private JLabel jLabelPrecioVtaFinal = null;
	private JLabel jLabelLblPreVtaFinal = null;
	private JLabel jLabelLblLIstaPrecio = null;
	private JComboBox jComboBoxListaPrecios = null;

	/*clases de logica*/
	private Articulo articulo = null;  //  @jve:decl-index=0:
	private ArticuloLogica articuloLogica = ArticuloLogica.getInstance();  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:

	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private ListaPrecioLogica listaPrecioLogica = ListaPrecioLogica.getInstance();
	private JLabel jLabelLblDesManual = null;
	private JLabel jLabelLblRecManual = null;
	private JTextField jTextFieldDesManual = null;
	private JTextField jTextFieldRecManual = null;
	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameConsultaArticulo() {
		super();
		initialize();

	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(1024, 511);
		this.setMaximumSize(new Dimension(1024, 696));
		this.setMinimumSize(new Dimension(0, 0));
		this.setContentPane(getJContentPane());
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Consulta de Productos");
		this.setResizable(false);
		this.setClosable(true);
		resetCampos();
		resetCampos(jPanelInventario);
		setEstatusAllComponent();
		setEstatusAllComponent(jPanelInventario);
		articulo = null;
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelLblRecManual = new JLabel();
			jLabelLblRecManual.setBounds(new Rectangle(630, 290, 100, 20));
			jLabelLblRecManual.setText("Recargo Manual:");
			jLabelLblDesManual = new JLabel();
			jLabelLblDesManual.setBounds(new Rectangle(350, 290, 110, 20));
			jLabelLblDesManual.setText("Descuento Manual:");
			jLabelLblLIstaPrecio = new JLabel();
			jLabelLblLIstaPrecio.setBounds(new Rectangle(531, 41, 118, 18));
			jLabelLblLIstaPrecio.setText("Lista de Precios:");
			jLabelLblPreVtaFinal = new JLabel();
			jLabelLblPreVtaFinal.setBounds(new Rectangle(731, 365, 114, 20));
			jLabelLblPreVtaFinal.setText("<html><font size=5>Precio Final:</font></html>");
			jLabelPrecioVtaFinal = new JLabel();
			jLabelPrecioVtaFinal.setBounds(new Rectangle(858, 350, 150, 50));
			jLabelPrecioVtaFinal.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelPrecioVtaFinal.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelPrecioVentaSIva = new JLabel();
			jLabelPrecioVentaSIva.setBounds(new Rectangle(570, 350, 150, 50));
			jLabelPrecioVentaSIva.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelPrecioVentaSIva.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jLabelLblPrecioVentaSinIva = new JLabel();
			jLabelLblPrecioVentaSinIva.setBounds(new Rectangle(350, 365, 207, 20));
			jLabelLblPrecioVentaSinIva.setText("<html><font size=5>Precio De Venta S/ IVA:</font></html>");
			jLabelPesoEstimado = new JLabel();
			jLabelPesoEstimado.setText("Peso Estimado:");
			jLabelPesoEstimado.setVisible(false);
			jLabelPesoEstimado.setBounds(new Rectangle(5, 80, 90, 20));
			jLabelStockMax = new JLabel();
			jLabelStockMax.setText("Máximo Stock:");
			jLabelStockMax.setBounds(new Rectangle(5, 54, 85, 20));
			jLabelStockMin = new JLabel();
			jLabelStockMin.setText("Mínimo Stock:");
			jLabelStockMin.setBounds(new Rectangle(5, 29, 85, 20));
			jLabelUnidadMinVta = new JLabel();
			jLabelUnidadMinVta.setText("Mínimo de Venta:");
			jLabelUnidadMinVta.setBounds(new Rectangle(5, 155, 100, 20));
			jLabelSubunidadVenta = new JLabel();
			jLabelSubunidadVenta.setText("Subunidad de Venta:");
			jLabelSubunidadVenta.setBounds(new Rectangle(5, 130, 120, 20));
			jLabelUnidadVenta = new JLabel();
			jLabelUnidadVenta.setText("Unidad de Venta:");
			jLabelUnidadVenta.setBounds(new Rectangle(5, 105, 100, 20));
			jLabelStock = new JLabel();
			jLabelStock.setText("<html><b><font color='green'>Stock Actual:<font></b></html>");
			jLabelStock.setBounds(new Rectangle(5, 4, 85, 20));
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
			jContentPane.setSize(1024, 491);
			jContentPane.setLayout(null);
			jContentPane.add(jLabelLblDesManual, null);
			jContentPane.add(jLabelLblRecManual, null);
			jContentPane.add(getJTextFieldDesManual(), null);
			jContentPane.add(getJTextFieldRecManual(), null);
			jContentPane.add(getJComboBoxListaPrecios(), null);
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
			jContentPane.add(getJPanelInventario(), null);
			jContentPane.add(jLabelLblPrecioVentaSinIva, null);
			jContentPane.add(jLabelPrecioVentaSIva, null);
			jContentPane.add(jLabelPrecioVtaFinal, null);
			jContentPane.add(jLabelLblPreVtaFinal, null);
			jContentPane.add(jLabelLblLIstaPrecio, null);

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
							jTextFieldDesManual.setEditable(true);
							jTextFieldRecManual.setEditable(true);
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
								jTextFieldDesManual.setEditable(true);
								jTextFieldRecManual.setEditable(true);
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

	/**
	 * This method initializes jComboBoxListaPrecios
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxListaPrecios() {
		if (jComboBoxListaPrecios == null) {
			jComboBoxListaPrecios = new JComboBox();
			jComboBoxListaPrecios.setBounds(new Rectangle(657, 40, 258, 18));
			jComboBoxListaPrecios.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					//setItemsInJtable();
					if(articulo != null)recalculaPrecios(articulo);
				}
			});
			setItemsjComboBoxListaPre();
			selectDefaultItemInjComboBoxDeposito();
		}
		return jComboBoxListaPrecios;
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
			jTextFieldStockMin.setBounds(new Rectangle(100, 29, 80, 20));
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
			jTextFieldStockMax.setBounds(new Rectangle(100, 54, 80, 20));
			jTextFieldStockMax.setDocument(new MyPlainDocument(jTextFieldStockMax,20,"D",true));
		}
		return jTextFieldStockMax;
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
		}
		return jTextFieldPesoEstimado;
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
	 * This method initializes jPanelInventario
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelInventario() {
		if (jPanelInventario == null) {
			jPanelInventario = new JPanel();
			jPanelInventario.setLayout(null);
			jPanelInventario.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			jPanelInventario.setBounds(new Rectangle(5, 290, 328, 181));
			jPanelInventario.add(jLabelStockMin, null);
			jPanelInventario.add(jLabelStockMax, null);
			jPanelInventario.add(getJTextFieldStockBase(), null);
			jPanelInventario.add(getJTextFieldStockMax(), null);
			jPanelInventario.add(getJTextFieldStockMin(), null);
			jPanelInventario.add(jLabelStock, null);
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
		}
		return jPanelInventario;
	}




	/************* GESTION DE COMBO BOX ****************/

	private void setItemsjComboBoxListaPre(){
		ArrayList<ListaPrecio> listasPrecios = listaPrecioLogica.getListListaPrecio();
		Iterator<ListaPrecio> it = listasPrecios.iterator();
		while(it.hasNext()){
			jComboBoxListaPrecios.addItem((ListaPrecio)it.next());
		}
	}



	private void selectDefaultItemInjComboBoxDeposito(){
		for(int i = 0; i < jComboBoxListaPrecios.getItemCount(); i++){
			ListaPrecio listaPrecio = (ListaPrecio)jComboBoxListaPrecios.getItemAt(i);
			if(listaPrecio.isDefault()){
				jComboBoxListaPrecios.setSelectedItem(listaPrecio);
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
		//titulos.add("Precio S/IVA");
		//titulos.add("Precio Final");
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
				//renglon.add("$ " + getValueDecimalVisual(recalculaPrecios(articulo).getPrecioUnitario()));
				//renglon.add("$ " + getValueDecimalVisual(recalculaPrecios(articulo).getPrecioTotal()));
				registros.add(renglon);
			}
		}
		myTableModel.setDataVector(registros, titulos);
		//setea ancho de campos
		jTableArticulo.getColumnModel().getColumn(0).setPreferredWidth(8);
		jTableArticulo.getColumnModel().getColumn(1).setPreferredWidth(8);
		jTableArticulo.getColumnModel().getColumn(2).setPreferredWidth(400);
		jTableArticulo.getColumnModel().getColumn(3).setPreferredWidth(8);
		jTableArticulo.getColumnModel().getColumn(4).setPreferredWidth(8);
		jTableArticulo.getColumnModel().getColumn(5).setPreferredWidth(8);

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
		jTextFieldCodigo.setText(String.valueOf(articulo.getCodigo()));
		jTextFieldSinonimo.setText(articulo.getSinonimo());
		jTextFieldDescripcion.setText(articulo.getDescripcion());
		jTextFieldStockBase.setText(getValueDecimalVisual(articulo.getStockDefault()));
		jTextFieldUnidadVenta.setText(articulo.getUnidadVta());
		jTextFieldSubunidadVenta.setText(getValueDecimalVisual((articulo.getSubUnidadVta())));
		jTextFieldMinimoVenta.setText(getValueDecimalVisual(articulo.getUnidadMinVta()));
		jTextFieldStockMin.setText(getValueDecimalVisual((articulo.getStockMin())));
		jTextFieldStockMax.setText(getValueDecimalVisual((articulo.getStockMax())));
		jTextFieldPesoEstimado.setText(getValueDecimalVisual(articulo.getPesoEstimado()));
		recalculaPrecios(articulo);
	}


	/************** GESTION DE ITEMS DE LA BASE DE DATOS Y DE LA TABLA ***********************/


	/**************** BUTTONS DE TOLBAR  Y TOOLBAR ********************/
	/*DEVULEVE LA JTOOLBAR*/
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0,1024, 30));
			jToolBar.setFloatable(false);
			jToolBar.add(getJButtonActionCloce());

		}
		return jToolBar;
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


	/********** ACTIONES CON BUTTONS DE TOOLBAR ****************/

	/*habilita o desabilita los botones de la toolbar*/
	public void setEnabledButtonsToolbar(boolean btnCloce){
		jButtonaActionCloce.setEnabled(btnCloce);
	}

	/*muestra o oculta los botonoes de la toolbar*/
	public void setVisibleButtonsToolbar(boolean btnCloce){
		jButtonaActionCloce.setVisible(btnCloce);
	}

	/*setea el estado (enable o editable) de todos los componenetes*/
	public void setEstatusAllComponent(){
		Component component[] = getContentPane().getComponents();
		for(int i = 0; i < component.length;i++){
			if(component[i].getClass() == JTextField.class){
				((JTextField)component[i]).setEditable(false);
			}else if(component[i].getClass() == JFormattedTextField.class){
				((JFormattedTextField)component[i]).setEditable(false);
			}else if(component[i].getClass() == JButton.class){
				((JButton)component[i]).setEnabled(false);
			}else if(component[i].getClass() == MyJtextFormatDecimal.class){
				((MyJtextFormatDecimal)component[i]).setEditable(false);
			}else if(component[i].getClass() == JTextArea.class){
				((JTextArea)component[i]).setEditable(false);
			}else if(component[i].getClass() == JCheckBox.class){
				((JCheckBox)component[i]).setEnabled(false);
			}else if(component[i].getClass() == JComboBox.class){
				((JComboBox)component[i]).setEnabled(false);
			}
		}
		jComboBoxCriterioBusqueda.setEnabled(true);
		jTextFieldBusqueda.setEditable(true);
		jTableArticulo.setEnabled(true);
		jComboBoxListaPrecios.setEnabled(true);

	}

	public void setEstatusAllComponent(Container container){
		Component component[] = container.getComponents();
		for(int i = 0; i < component.length;i++){
			if(component[i].getClass() == JTextField.class){
				((JTextField)component[i]).setEditable(false);
			}else if(component[i].getClass() == JFormattedTextField.class){
				((JFormattedTextField)component[i]).setEditable(false);
			}else if(component[i].getClass() == JButton.class){
				((JButton)component[i]).setEnabled(false);
			}else if(component[i].getClass() == MyJtextFormatDecimal.class){
				((MyJtextFormatDecimal)component[i]).setEditable(false);
			}else if(component[i].getClass() == JTextArea.class){
				((JTextArea)component[i]).setEditable(false);
			}else if(component[i].getClass() == JCheckBox.class){
				((JCheckBox)component[i]).setEnabled(false);
			}else if(component[i].getClass() == JComboBox.class){
				((JComboBox)component[i]).setEnabled(false);
			}
		}
		jComboBoxCriterioBusqueda.setEnabled(true);
		jTextFieldBusqueda.setEditable(true);
		jTableArticulo.setEnabled(true);
		jComboBoxListaPrecios.setEnabled(true);
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
		ItemFactura itemFactura = new ItemFactura();
		Cliente cliente = new Cliente();
		CondicionIva condicionIva = new CondicionIva();

		condicionIva.setDiscriminaIVA(true);
		cliente.setCondicionIVA(condicionIva);
		cliente.setListaPrecios((ListaPrecio)jComboBoxListaPrecios.getSelectedItem());
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




	/**
	 * This method initializes jTextFieldDesManual
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDesManual() {
		if (jTextFieldDesManual == null) {
			jTextFieldDesManual = new JTextField();
			jTextFieldDesManual.setBounds(new Rectangle(470, 290, 80, 20));
			jTextFieldDesManual.setDocument(new MyPlainDocument(getJTextFieldDesManual(), 10, "D", true));
			jTextFieldDesManual.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					System.out.println("keyTyped()"); // TODO Auto-generated Event stub keyTyped()
					if(e.getKeyCode() == 10){
						if(articulo != null)recalculaPrecios(articulo);
					}
				}
			});
			jTextFieldDesManual.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(articulo != null)recalculaPrecios(articulo);
				}
			});
		}
		return jTextFieldDesManual;
	}

	/**
	 * This method initializes jTextFieldRecManual
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRecManual() {
		if (jTextFieldRecManual == null) {
			jTextFieldRecManual = new JTextField();
			jTextFieldRecManual.setBounds(new Rectangle(740, 290, 80, 20));
			jTextFieldRecManual.setDocument(new MyPlainDocument(getJTextFieldRecManual(), 10, "D", true));
			jTextFieldRecManual.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					System.out.println("keyTyped()"); // TODO Auto-generated Event stub keyTyped()
					if(e.getKeyCode() == 10){
						if(articulo != null)recalculaPrecios(articulo);
					}
				}
			});
			jTextFieldRecManual.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(articulo != null)recalculaPrecios(articulo);
				}
			});
		}
		return jTextFieldRecManual;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

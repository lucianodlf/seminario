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
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
import logica.CondicionIvaLogica;
import logica.CondicionVentaLogica;
import logica.DocumentoTipoPersonaLogica;
import logica.ListaPrecioLogica;
import logica.LocalidadLogica;
import logica.ProvinciaLogica;
import logica.VendedorLogica;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Cliente;
import dominio.ClienteCategoria;
import dominio.CondicionIva;
import dominio.CondicionVenta;
import dominio.DocumentoTipoPersona;
import dominio.ListaPrecio;
import dominio.Localidad;
import dominio.Provincia;
import dominio.Vendedor;

public class DialogGestorCliente extends JDialog {


	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="17,217"
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableCliente = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JLabel jLabelBusqueda = null;
	private JLabel jLabelCriterioBusqueda = null;
	private JTextField jTextFieldBusqueda = null;
	private JComboBox jComboBoxCriterioBusqueda = null;
	private JLabel jLabelCodigo = null;
	private JLabel jLabelNombre = null;
	private JTextField jTextFieldCodigo = null;
	private JTextField jTextFieldNombre = null;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonActionAlta = null;
	private JButton jButtonActionModificacion = null;
	private JButton jButtonActionEliminar = null;
	private JButton jButtonActionOk = null;
	private JButton jButtonActionCancel = null;
	//private JLabel jLabelNroCuit = null;
	//private JFormattedTextField jTextFieldNroCuit = null;
	private JLabel jLabelNroDoc = null;
	private JFormattedTextField jTextFieldNroDoc = null;
	private JLabel jLabelLimiteCredito = null;
	private JTextField jTextFieldLimiteCredito = null;
	private JLabel jLabelVendedor = null;
	private JLabel jLabelFechaAlta = null;
	private JFormattedTextField jTextFieldFechaAlta = null;
	private JLabel jLabelDescDefault = null;
	private JTextField jTextFieldDescDefault = null;
	private JLabel jLabelDirPiso = null;
	private JTextField jTextFieldDirPiso = null;
	private JLabel jLabelTelFijo = null;
	private JTextField jTextFieldTelefono = null;
	private JTextField jTextFieldEmail = null;
	private JLabel jLabelEmail = null;
	private JLabel jLabelRazonSocial = null;
	private JTextField jTextFieldRazonSocial = null;
	private JLabel jLabelNomFantasia = null;
	private JTextField jTextFieldNomFantasia = null;
	private JLabel jLabelDirCalle = null;
	private JTextField jTextFieldDirCalle = null;
	private JLabel jLabelDirNro = null;
	private JTextField jTextFieldDirNro = null;
	private JLabel jLabelDescMax = null;
	private JTextField jTextFieldDescMax = null;
	private JLabel jLabelDirDpto = null;
	private JTextField jTextFieldDirDpto = null;
	private JTextField jTextFieldMovil = null;
	private JLabel jLabelMovil = null;
	private JCheckBox jCheckBoxActivo = null;
	private JComboBox jComboBoxDocTipo = null;
	private JLabel jLabelDocTipo = null;
	private JButton jButtonAddProvincia = null;
	private JLabel jLabelCondIva = null;
	private JLabel jLabelCondVenta = null;
	private JComboBox jComboBoxCondIva = null;
	private JComboBox jComboBoxCondVenta = null;
	private JFormattedTextField jTextFieldFechaNac = null;

	private JLabel jLabelFechaNac = null;
	private JLabel jLabelListaPre = null;
	private JComboBox jComboBoxListaPre = null;
	private JCheckBox jCheckBoxCtaCteActiva = null;
	private JLabel jLabelProvincia = null;
	private JComboBox jComboBoxProvincia = null;
	private JLabel jLabelLocalidad = null;
	private JComboBox jComboBoxLocalidad = null;
	private JTextField jTextFieldApellido = null;
	private JLabel jLabelApellido = null;
	private JButton jButtonAddLocalidad = null;
	private JComboBox jComboBoxVendedores = null;
	private JButton jButtonAddCondIva = null;
	private JButton jButtonAddCondVta = null;
	private JButton jButtonAddListPre = null;
	private JButton jButtonAddVendedor = null;


	/*clases de logica*/
	private Cliente cliente = null;  //  @jve:decl-index=0:
	private ClienteLogica clienteLogica = ClienteLogica.getInstance();  //  @jve:decl-index=0:
	private CondicionIvaLogica condicionIvaLogica = CondicionIvaLogica.getInstance();  //  @jve:decl-index=0:
	private ListaPrecioLogica listaPrecioLogica  = ListaPrecioLogica.getInstance();
	private LocalidadLogica localidadLogica = LocalidadLogica.getInstance();
	private ProvinciaLogica provinciaLogica = ProvinciaLogica.getInstance();
	private VendedorLogica vendedorLogica = VendedorLogica.getInstance();
	private DocumentoTipoPersonaLogica documentoTipoPersonaLogica = DocumentoTipoPersonaLogica.getInstance();
	private CondicionVentaLogica condicionVentaLogica = CondicionVentaLogica.getInstance();



	private String activeActionType = null; //se utiliza para determina cual es la accion activa (agrega, modificar o eliminar)
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:
	private DecimalFormat porcentFormat = new DecimalFormat("###.###");  //  @jve:decl-index=0:
	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private MaskFormatter mfNroDoc = new MaskFormatter();  //  @jve:decl-index=0:

	private JButton jButtonaActionSeleccionar = null;





	/**
	 * This is the xxx default constructor
	 */
	public DialogGestorCliente(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(1024, 657);
		this.setMaximumSize(new Dimension(1024, 696));
		this.setMinimumSize(new Dimension(0, 0));
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Gestión de Clientes");
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		this.setResizable(false);
		resetCampos();
		setEstatusAllComponent(false, true, true, true);
		setVisibleButtonsToolbar(true, true, true, false, false, true);
		setEnabledButtonsToolbar(true, false, false, false, false, true, false);
		cliente = null;
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelApellido = new JLabel();
			jLabelApellido.setBounds(new Rectangle(380, 260, 50, 20));
			jLabelApellido.setText("Apellído:");
			jLabelLocalidad = new JLabel();
			jLabelLocalidad.setBounds(new Rectangle(570, 335, 60, 20));
			jLabelLocalidad.setText("Localidad:");
			jLabelProvincia = new JLabel();
			jLabelProvincia.setBounds(new Rectangle(570, 300, 60, 20));
			jLabelProvincia.setText("Provincia:");
			jLabelListaPre = new JLabel();
			jLabelListaPre.setBounds(new Rectangle(5, 495, 100, 20));
			jLabelListaPre.setText("Lista de Precios:");
			jLabelFechaNac = new JLabel();
			jLabelFechaNac.setBounds(new Rectangle(610, 260, 106, 20));
			jLabelFechaNac.setText("Fecha Nacimiento:");
			jLabelCondVenta = new JLabel();
			jLabelCondVenta.setBounds(new Rectangle(5, 460, 115, 20));
			jLabelCondVenta.setText("Condición de Venta:");
			jLabelCondIva = new JLabel();
			jLabelCondIva.setBounds(new Rectangle(5, 425, 100, 20));
			jLabelCondIva.setText("Condiciòn de IVA:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jLabelDocTipo = new JLabel();
			jLabelDocTipo.setBounds(new Rectangle(5, 306, 115, 20));
			jLabelDocTipo.setText("Tipo Identificación:");
			jLabelMovil = new JLabel();
			jLabelMovil.setBounds(new Rectangle(570, 570, 35, 20));
			jLabelMovil.setText("Móvil:");
			jLabelDirDpto = new JLabel();
			jLabelDirDpto.setText("Departamento:");
			jLabelDirDpto.setBounds(new Rectangle(570, 465, 85, 20));
			jLabelDescMax = new JLabel();
			jLabelDescMax.setText("Descuento Máximo: %");
			jLabelDescMax.setBounds(new Rectangle(5, 590, 125, 20));
			jLabelDirNro = new JLabel();
			jLabelDirNro.setText("Número:");
			jLabelDirNro.setBounds(new Rectangle(570, 400, 50, 20));
			jLabelDirCalle = new JLabel();
			jLabelDirCalle.setText("Calle:");
			jLabelDirCalle.setBounds(new Rectangle(570, 370, 35, 20));
			jLabelNomFantasia = new JLabel();
			jLabelNomFantasia.setText("Nombre de Fantasía:");
			jLabelNomFantasia.setBounds(new Rectangle(5, 390, 120, 20));
			jLabelRazonSocial = new JLabel();
			jLabelRazonSocial.setText("Razón Social:");
			jLabelRazonSocial.setBounds(new Rectangle(5, 360, 80, 20));
			jLabelEmail = new JLabel();
			jLabelEmail.setText("Email:");
			jLabelEmail.setBounds(new Rectangle(570, 500, 40, 20));
			jLabelTelFijo = new JLabel();
			jLabelTelFijo.setBounds(new Rectangle(570, 535, 60, 20));
			jLabelTelFijo.setText("Teléfono:");
			jLabelDirPiso = new JLabel();
			jLabelDirPiso.setText("Piso:");
			jLabelDirPiso.setBounds(new Rectangle(570, 430, 30, 20));
			jLabelDescDefault = new JLabel();
			jLabelDescDefault.setBounds(new Rectangle(5, 560, 120, 20));
			jLabelDescDefault.setText("Descuento Default: %");
			jLabelFechaAlta = new JLabel();
			jLabelFechaAlta.setBounds(new Rectangle(815, 260, 70, 20));
			jLabelFechaAlta.setText("Fecha Alta:");
			jLabelVendedor = new JLabel();
			jLabelVendedor.setText("Vendedor:");
			jLabelVendedor.setBounds(new Rectangle(5, 530, 60, 20));
			jLabelLimiteCredito = new JLabel();
			jLabelLimiteCredito.setText("Límite Crédito:");
			jLabelLimiteCredito.setBounds(new Rectangle(240, 560, 85, 20));
			jLabelNroDoc = new JLabel();
			jLabelNroDoc.setBounds(new Rectangle(216, 306, 112, 20));
			jLabelNroDoc.setText("Nro. Identificación:");
			//jLabelNroCuit = new JLabel();
			//jLabelNroCuit.setBounds(new Rectangle(225, 326, 60, 20));
			//jLabelNroCuit.setText("Nro. CUIT:");
			jLabelNombre = new JLabel();
			jLabelNombre.setBounds(new Rectangle(160, 260, 50, 20));
			jLabelNombre.setText("Nombre:");
			jLabelCodigo = new JLabel();
			jLabelCodigo.setBounds(new Rectangle(5, 260, 50, 20));
			jLabelCodigo.setText("Código:");
			jLabelCriterioBusqueda = new JLabel();
			jLabelCriterioBusqueda.setBounds(new Rectangle(280, 40, 50, 20));
			jLabelCriterioBusqueda.setText("Criterio:");
			jLabelBusqueda = new JLabel();
			jLabelBusqueda.setBounds(new Rectangle(05, 40, 50, 20));
			jLabelBusqueda.setText("Buscar:");
			jContentPane.setSize(1024, 768);
			jContentPane.setLayout(null);
			jContentPane.add(getJTextFieldDescMax(), null);
			jContentPane.add(jLabelDescMax, null);
			jContentPane.add(getJScrollPaneaList(), null);
			jContentPane.add(jLabelBusqueda, null);
			jContentPane.add(jLabelCriterioBusqueda, null);
			jContentPane.add(getJTextFieldBusqueda(), null);
			jContentPane.add(getJComboBoxCriterioBusqueda(), null);
			jContentPane.add(jLabelCodigo, null);
			jContentPane.add(jLabelNombre, null);
			jContentPane.add(getJTextFieldCodigo(), null);
			jContentPane.add(getJTextFieldNombre(), null);
			jContentPane.add(getJToolBar(), null);
			//jContentPane.add(jLabelNroCuit, null);
			//jContentPane.add(getJTextFieldNroCuit(), null);
			jContentPane.add(jLabelNroDoc, null);
			jContentPane.add(getJTextFieldNroDoc(), null);
			jContentPane.add(jLabelFechaAlta, null);
			jContentPane.add(getJTextFieldFechaAlta(), null);
			jContentPane.add(jLabelDescDefault, null);
			jContentPane.add(getJTextFieldDescDefault(), null);
			jContentPane.add(jLabelTelFijo, null);
			jContentPane.add(getJTextFieldPrecioCosto2(), null);
			jContentPane.add(getJTextFieldMovil(), null);
			jContentPane.add(jLabelMovil, null);
			jContentPane.add(getJComboBoxDocTipo(), null);
			jContentPane.add(jLabelDocTipo, null);
			jContentPane.add(jLabelDirCalle, null);
			jContentPane.add(getJTextFieldDirCalle(), null);
			jContentPane.add(jLabelDirNro, null);
			jContentPane.add(getJTextFieldDirNro(), null);
			jContentPane.add(jLabelDirPiso, null);
			jContentPane.add(getJTextFieldDirPiso(), null);
			jContentPane.add(jLabelDirDpto, null);
			jContentPane.add(getJTextFieldDirDpto(), null);
			jContentPane.add(jLabelEmail, null);
			jContentPane.add(getJTextFieldEmail(), null);
			jContentPane.add(jLabelRazonSocial, null);
			jContentPane.add(getJTextFieldRazonSocial(), null);
			jContentPane.add(jLabelNomFantasia, null);
			jContentPane.add(getJTextFieldNomFantasia(), null);
			jContentPane.add(jLabelLimiteCredito, null);
			jContentPane.add(getJTextFielRubro(), null);
			jContentPane.add(jLabelVendedor, null);
			jContentPane.add(jLabelCondIva, null);
			jContentPane.add(jLabelCondVenta, null);
			jContentPane.add(getJComboBoxCondIva(), null);
			jContentPane.add(getJComboBoxCondVenta(), null);
			jContentPane.add(getJTextFieldFechaNac(), null);
			jContentPane.add(jLabelFechaNac, null);
			jContentPane.add(jLabelListaPre, null);
			jContentPane.add(getJComboBoxListaPre(), null);
			jContentPane.add(jLabelProvincia, null);
			jContentPane.add(getJComboBoxProvincia(), null);
			jContentPane.add(jLabelLocalidad, null);
			jContentPane.add(getJComboBoxLocalidad(), null);
			jContentPane.add(getJTextFieldApellido(), null);
			jContentPane.add(jLabelApellido, null);
			jContentPane.add(getJButtonAddProvincia(), null);
			jContentPane.add(getJButtonAddLocalidad(), null);
			jContentPane.add(getJComboBoxVendedores(), null);
			jContentPane.add(getJCheckBoxActivo(), null);
			jContentPane.add(getJCheckBoxCtaCteActiva(), null);
			jContentPane.add(getJButtonAddCondIva(), null);
			jContentPane.add(getJButtonAddCondVta(), null);
			jContentPane.add(getJButtonAddListPre(), null);
			jContentPane.add(getJButtonAddVendedor(), null);
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
			jScrollPaneaList.setViewportView(getJtableCliente());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJtableCliente() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableCliente == null) {
			jTableCliente = new MyJtable(getMyTableModel());
			jTableCliente.setRowSorter(geTableRowSorter());
			jTableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableCliente.setRowHeight(20);
			setItemsInJtable();
			jTableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableCliente.setAutoscrolls(true);
			jTableCliente.setShowVerticalLines(true);
			jTableCliente.setShowHorizontalLines(true);
			jTableCliente.setVisible(true);
			jTableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableCliente.isEnabled()){
						cliente = getSelectedElement();
						if(cliente != null){
							setCamposSelectedElement(cliente);
							setEnabledButtonsToolbar(true, true, true, false, false, true, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
						}
					}
				}
			});
			jTableCliente.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(jTableCliente.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							cliente = getSelectedElement();
							if(cliente != null){
								setCamposSelectedElement(cliente);
								setEnabledButtonsToolbar(true, true, true, false, false, true, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
							}
						}
					}
				}
			});
		}
		return jTableCliente;
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
			jComboBoxCriterioBusqueda.addItem("Còdigo");
			jComboBoxCriterioBusqueda.addItem("Nombre");
			jComboBoxCriterioBusqueda.addItem("Apellído");
			jComboBoxCriterioBusqueda.addItem("Razón Social");
			jComboBoxCriterioBusqueda.addItem("Nombre Fantasía");
		}
		return jComboBoxCriterioBusqueda;
	}


	/************** ELEMENTOS PARA VISUALIZAR O EDITAR LOS ITEMS DE LA TABLA ***************/

	private JTextField getJTextFieldCodigo() {
		if (jTextFieldCodigo == null) {
			jTextFieldCodigo = new JTextField();
			jTextFieldCodigo.setBounds(new Rectangle(60, 260, 80, 20));

			jTextFieldCodigo.setText("CODIGO");
		}
		return jTextFieldCodigo;
	}

	private JTextField getJTextFieldNombre() {
		if (jTextFieldNombre == null) {
			jTextFieldNombre = new JTextField();
			jTextFieldNombre.setBounds(new Rectangle(215, 260, 150, 20));
			jTextFieldNombre.setText("Nombre");
			jTextFieldNombre.setDocument(new MyPlainDocument(jTextFieldNombre,50,null,true));
		}
		return jTextFieldNombre;
	}

	/**
	 * This method initializes jTextFieldNroCuit
	 *
	 * @return javax.swing.JTextField
	 */
	/*private JFormattedTextField getJTextFieldNroCuit() {
		try {
			mfNroCuit.setMask("##-##.###.###-#");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mfNroCuit.setPlaceholderCharacter('_');
		if (jTextFieldNroCuit == null) {
			jTextFieldNroCuit = new JFormattedTextField(mfNroCuit);
			jTextFieldNroCuit.setBounds(new Rectangle(295, 326, 143, 20));
		}
		return jTextFieldNroCuit;
	}*/

	/**
	 * This method initializes jTextFieldNroDoc
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldNroDoc() {
			if (jTextFieldNroDoc == null) {
				jTextFieldNroDoc = new JFormattedTextField();
				jTextFieldNroDoc.setBounds(new Rectangle(330, 306, 108, 20));

			}
		return jTextFieldNroDoc;
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
		if (jTextFieldLimiteCredito == null) {
			jTextFieldLimiteCredito = new JTextField();
			jTextFieldLimiteCredito.setBounds(new Rectangle(330, 560, 80, 20));
			jTextFieldLimiteCredito.setDocument(new MyPlainDocument(jTextFieldLimiteCredito,20,"D",true));
		}
		return jTextFieldLimiteCredito;
	}

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
				jTextFieldFechaAlta.setBounds(new Rectangle(895, 260, 70, 20));
			}
		}catch(ParseException e){
			e.printStackTrace();
		}

		return jTextFieldFechaAlta;
	}

	/**
	 * This method initializes jTextFieldDescDefault
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDescDefault() {
		if (jTextFieldDescDefault == null) {
			jTextFieldDescDefault = new JTextField();
			jTextFieldDescDefault.setBounds(new Rectangle(140, 560, 80, 20));
			jTextFieldDescDefault.setDocument(new MyPlainDocument(jTextFieldDescDefault,20,"D",true));
		}
		return jTextFieldDescDefault;
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
	 * This method initializes jTextFieldDirPiso
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDirPiso() {
		if (jTextFieldDirPiso == null) {
			jTextFieldDirPiso = new JTextField();
			jTextFieldDirPiso.setBounds(new Rectangle(630, 430, 112, 20));
			jTextFieldDirPiso.setDocument(new MyPlainDocument(jTextFieldDirPiso,3,"D",true));
		}
		return jTextFieldDirPiso;
	}

	/**
	 * This method initializes jTextFieldPrecioCostoConImp
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPrecioCosto2() {
		if (jTextFieldTelefono == null) {
			jTextFieldTelefono = new JTextField();
			jTextFieldTelefono.setBounds(new Rectangle(640, 535, 300, 20));
			jTextFieldTelefono.setDocument(new MyPlainDocument(jTextFieldTelefono,100,null,true));
		}
		return jTextFieldTelefono;
	}

	/**
	 * This method initializes jTextFieldEmail
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldEmail() {
		if (jTextFieldEmail == null) {
			jTextFieldEmail = new JTextField();
			jTextFieldEmail.setBounds(new Rectangle(640, 500, 300, 20));
			jTextFieldEmail.setDocument(new MyPlainDocument(jTextFieldEmail,100,null,false));
		}
		return jTextFieldEmail;
	}

	/**
	 * This method initializes jTextFieldRazonSocial
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldRazonSocial() {
		if (jTextFieldRazonSocial == null) {
			jTextFieldRazonSocial = new JTextField();
			jTextFieldRazonSocial.setBounds(new Rectangle(100, 360, 339, 20));
			jTextFieldRazonSocial.setDocument(new MyPlainDocument(jTextFieldRazonSocial,100,null,true));
		}
		return jTextFieldRazonSocial;
	}

	/**
	 * This method initializes jTextFieldNomFantasia
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNomFantasia() {
		if (jTextFieldNomFantasia == null) {
			jTextFieldNomFantasia = new JTextField();
			jTextFieldNomFantasia.setText("MinimoVenta");
			jTextFieldNomFantasia.setBounds(new Rectangle(140, 390, 300, 20));
			jTextFieldNomFantasia.setDocument(new MyPlainDocument(jTextFieldNomFantasia,100,null,true));
		}
		return jTextFieldNomFantasia;
	}

	/**
	 * This method initializes jTextFieldDirCalle
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDirCalle() {
		if (jTextFieldDirCalle == null) {
			jTextFieldDirCalle = new JTextField();
			jTextFieldDirCalle.setBounds(new Rectangle(619, 370, 202, 20));
			jTextFieldDirCalle.setDocument(new MyPlainDocument(jTextFieldDirCalle,100,null,true));
		}
		return jTextFieldDirCalle;
	}

	/**
	 * This method initializes jTextFieldDirNro
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDirNro() {
		if (jTextFieldDirNro == null) {
			jTextFieldDirNro = new JTextField();
			jTextFieldDirNro.setBounds(new Rectangle(630, 400, 112, 20));
			jTextFieldDirNro.setDocument(new MyPlainDocument(jTextFieldDirNro,10,"D",true));
			jTextFieldDirNro.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					//jTextFieldDirNro.setText(getValueDecimalVisual(jTextFieldDirNro.getText()));
				}
			});
		}
		return jTextFieldDirNro;
	}

	/**
	 * This method initializes jTextFieldDescMax
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDescMax() {
		if (jTextFieldDescMax == null) {
			jTextFieldDescMax = new JTextField();
			jTextFieldDescMax.setBounds(new Rectangle(140, 590, 80, 20));
			jTextFieldDescMax.setDocument(new MyPlainDocument(jTextFieldDescMax,20,"D",true));
		}
		return jTextFieldDescMax;
	}

	/**
	 * This method initializes jTextFieldDirDpto
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDirDpto() {
		if (jTextFieldDirDpto == null) {
			jTextFieldDirDpto = new JTextField();
			jTextFieldDirDpto.setBounds(new Rectangle(665, 465, 80, 20));
			jTextFieldDirDpto.setDocument(new MyPlainDocument(jTextFieldDirDpto,10,null,true));
		}
		return jTextFieldDirDpto;
	}

	/**
	 * This method initializes jTextFieldPrevioVenta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldMovil() {
		if (jTextFieldMovil == null) {
			jTextFieldMovil = new JTextField();
			jTextFieldMovil.setBounds(new Rectangle(640, 570, 300, 20));
			jTextFieldMovil.setDocument(new MyPlainDocument(jTextFieldMovil,100,null,true));
		}
		return jTextFieldMovil;
	}

	/**
	 * This method initializes jCheckBoxActivo
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxActivo() {
		if (jCheckBoxActivo == null) {
			jCheckBoxActivo = new JCheckBox();
			jCheckBoxActivo.setText("Activo");
			jCheckBoxActivo.setBounds(new Rectangle(249, 589, 60, 24));
		}
		return jCheckBoxActivo;
	}

	private JButton getJButtonAddProvincia() {
		if (jButtonAddProvincia == null) {
			jButtonAddProvincia = new JButton();
			jButtonAddProvincia.setText("Agregar Provincia");
			jButtonAddProvincia.setBounds(new Rectangle(830, 300, 140, 20));
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

	/**
	 * This method initializes jTextFieldFechaNac
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getJTextFieldFechaNac() {
		try{
		MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
		maskFormatter.setPlaceholderCharacter('_');
		if (jTextFieldFechaNac == null) {
			jTextFieldFechaNac = new JFormattedTextField(maskFormatter);
			jTextFieldFechaNac.setBounds(new Rectangle(725, 260, 65, 20));
		}
		}catch(ParseException e){
			e.printStackTrace();
		}
		return jTextFieldFechaNac;
	}

	/**
	 * This method initializes jCheckBoxCtaCteActiva
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxCtaCteActiva() {
		if (jCheckBoxCtaCteActiva == null) {
			jCheckBoxCtaCteActiva = new JCheckBox();
			jCheckBoxCtaCteActiva.setText("Cta. Cte. Activa");
			jCheckBoxCtaCteActiva.setBounds(new Rectangle(349, 590, 110, 24));
		}
		return jCheckBoxCtaCteActiva;
	}

	/**
	 * This method initializes jTextFieldApellido
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldApellido() {
		if (jTextFieldApellido == null) {
			jTextFieldApellido = new JTextField();
			jTextFieldApellido.setBounds(new Rectangle(435, 260, 150, 20));
			jTextFieldApellido.setDocument(new MyPlainDocument(getJTextFieldApellido(), 50, null, true));
			jTextFieldApellido.setText("Apellìdo");
		}
		return jTextFieldApellido;
	}

	/**
	 * This method initializes jButtonAddLocalidad
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddLocalidad() {
		if (jButtonAddLocalidad == null) {
			jButtonAddLocalidad = new JButton();
			jButtonAddLocalidad.setBounds(new Rectangle(830, 335, 140, 20));
			jButtonAddLocalidad.setText("Agregar Localidad");
			jButtonAddLocalidad.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Localidades()"); // TODO Auto-generated Event stub actionPerformed()

					DialogGestorLocalidad dialogGestorLocalidad = new DialogGestorLocalidad(new Frame());
					dialogGestorLocalidad.setModal(true);
					dialogGestorLocalidad.setVisible(true);

					setItemjComboBoxLocalidad();
				}
			});
		}
		return jButtonAddLocalidad;
	}

	/********VISUALES COMBOS BOX ***************/

	/**
	 * This method initializes jComboBoxCondIva
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxCondIva() {
		if (jComboBoxCondIva == null) {
			jComboBoxCondIva = new JComboBox();
			jComboBoxCondIva.setBounds(new Rectangle(125, 425, 200, 20));
			jComboBoxCondIva.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			setItemjComboBoxCondIva();
		}
		return jComboBoxCondIva;
	}

	/**
	 * This method initializes jComboBoxDocTipo
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxDocTipo() {
		if (jComboBoxDocTipo == null) {
			jComboBoxDocTipo = new JComboBox();
			jComboBoxDocTipo.setBounds(new Rectangle(125, 306, 80, 20));
			jComboBoxDocTipo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					//jTextFieldNroDoc.getFormatter().uninstall();
					DocumentoTipoPersona dtp = (DocumentoTipoPersona)jComboBoxDocTipo.getSelectedItem();
					try {
						mfNroDoc.setMask(dtp.getMascara());
						//mfNroDoc.setPlaceholderCharacter('_');
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					mfNroDoc.install(jTextFieldNroDoc);

				}
			});
			setItemjComboBoxDocTipo();
		}
		return jComboBoxDocTipo;
	}

	/**
	 * This method initializes jComboBoxCondVenta
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxCondVenta() {
		if (jComboBoxCondVenta == null) {
			jComboBoxCondVenta = new JComboBox();
			jComboBoxCondVenta.setBounds(new Rectangle(140, 460, 183, 20));
			jComboBoxCondVenta.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			setItemjComboBoxCondVenta();
		}
		return jComboBoxCondVenta;
	}


	/**
	 * This method initializes jComboBoxListaPre
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxListaPre() {
		if (jComboBoxListaPre == null) {
			jComboBoxListaPre = new JComboBox();
			jComboBoxListaPre.setBounds(new Rectangle(125, 495, 200, 20));
			jComboBoxListaPre.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			setItemjComboBoxListaPre();
		}
		return jComboBoxListaPre;
	}

	/**
	 * This method initializes jComboBoxProvincia
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxProvincia() {
		if (jComboBoxProvincia == null) {
			jComboBoxProvincia = new JComboBox();
			jComboBoxProvincia.setBounds(new Rectangle(640, 300, 180, 20));
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
	 * This method initializes jComboBoxLocalidad
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxLocalidad() {
		if (jComboBoxLocalidad == null) {
			jComboBoxLocalidad = new JComboBox();
			jComboBoxLocalidad.setBounds(new Rectangle(640, 335, 180, 20));
			jComboBoxLocalidad.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			setItemjComboBoxLocalidad();
			setItemjComboBoxProvincia();
		}
		return jComboBoxLocalidad;
	}



	/**
	 * This method initializes jComboBoxVendedores
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxVendedores() {
		if (jComboBoxVendedores == null) {
			jComboBoxVendedores = new JComboBox();
			jComboBoxVendedores.setBounds(new Rectangle(85, 530, 200, 20));
			jComboBoxVendedores.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
			setItemjComboBoxVendedores();
		}
		return jComboBoxVendedores;
	}

	/**
	 * This method initializes jButtonAddCondIva
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddCondIva() {
		if (jButtonAddCondIva == null) {
			jButtonAddCondIva = new JButton();
			jButtonAddCondIva.setBounds(new Rectangle(335, 425, 145, 20));
			jButtonAddCondIva.setText("Agregar Cond. IVA");
			jButtonAddCondIva.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorCondicionIva dialogGestorCondicionIva = new DialogGestorCondicionIva(new Frame());
					dialogGestorCondicionIva.setModal(true);
					dialogGestorCondicionIva.setVisible(true);

					setItemjComboBoxCondIva();
				}
			});
		}
		return jButtonAddCondIva;
	}

	/**
	 * This method initializes jButtonAddCondVta
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddCondVta() {
		if (jButtonAddCondVta == null) {
			jButtonAddCondVta = new JButton();
			jButtonAddCondVta.setBounds(new Rectangle(335, 460, 145, 20));
			jButtonAddCondVta.setText("Agregar Cond. Vta.");
			jButtonAddCondVta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorCondVenta dialogGestorCondVenta = new DialogGestorCondVenta(new Frame());
					dialogGestorCondVenta.setModal(true);
					dialogGestorCondVenta.setVisible(true);

					setItemjComboBoxCondVenta();
				}
			});
		}
		return jButtonAddCondVta;
	}

	/**
	 * This method initializes jButtonAddListPre
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddListPre() {
		if (jButtonAddListPre == null) {
			jButtonAddListPre = new JButton();
			jButtonAddListPre.setBounds(new Rectangle(335, 495, 145, 20));
			jButtonAddListPre.setText("Agregar List. Pre.");
			jButtonAddListPre.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorListasPrecio dialogGestorListasPrecio = new DialogGestorListasPrecio(new Frame());
					dialogGestorListasPrecio.setModal(true);
					dialogGestorListasPrecio.setVisible(true);
					setItemjComboBoxListaPre();
				}
			});
		}
		return jButtonAddListPre;
	}

	/**
	 * This method initializes jButtonAddVendedor
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddVendedor() {
		if (jButtonAddVendedor == null) {
			jButtonAddVendedor = new JButton();
			jButtonAddVendedor.setBounds(new Rectangle(335, 530, 145, 20));
			jButtonAddVendedor.setText("Agregar Vendedor");
			jButtonAddVendedor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Vendedores()"); // TODO Auto-generated Event stub actionPerformed()
					System.out.println("Rubro()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorVendedor dialogGestorVendedor = new DialogGestorVendedor(new Frame());
					dialogGestorVendedor.setModal(true);
					dialogGestorVendedor.setVisible(true);

					setItemjComboBoxVendedores();
				}
			});
		}
		return jButtonAddVendedor;
	}

	/************* GESTION DE COMBO BOX ****************/

	private void setItemjComboBoxCondIva(){
		jComboBoxCondIva.removeAllItems();
		ArrayList<CondicionIva> condIva = condicionIvaLogica.getListCondicionIva();
		Iterator<CondicionIva> it = condIva.iterator();
		while(it.hasNext()){
			jComboBoxCondIva.addItem((CondicionIva)it.next());
		}
	}

	private void setItemjComboBoxCondVenta(){
		jComboBoxCondVenta.removeAllItems();
		ArrayList<CondicionVenta> condVta = condicionVentaLogica.getListCondicionVenta();
		Iterator<CondicionVenta> it = condVta.iterator();
		while(it.hasNext()){
			jComboBoxCondVenta.addItem((CondicionVenta)it.next());
		}
	}

	private void setItemjComboBoxDocTipo(){
		jComboBoxDocTipo.removeAllItems();
		ArrayList<DocumentoTipoPersona> tipoDoc = documentoTipoPersonaLogica.getListDocumentoTipoPersona();
		Iterator<DocumentoTipoPersona> it = tipoDoc.iterator();
		while(it.hasNext()){
			jComboBoxDocTipo.addItem((DocumentoTipoPersona)it.next());
		}
	}

	private void setItemjComboBoxListaPre(){
		jComboBoxListaPre.removeAllItems();
		ArrayList<ListaPrecio> listaPrecios = listaPrecioLogica.getListListaPrecio();
		Iterator<ListaPrecio> it = listaPrecios.iterator();
		while(it.hasNext()){
			jComboBoxListaPre.addItem((ListaPrecio)it.next());
		}
	}

	private void setItemjComboBoxLocalidad(){
		jComboBoxLocalidad.removeAllItems();
		ArrayList<Localidad> localidad = localidadLogica.getListLocalidad();
		Iterator<Localidad> it = localidad.iterator();
		while(it.hasNext()){
			jComboBoxLocalidad.addItem((Localidad)it.next());
		}
	}

	private void setItemjComboBoxProvincia(){
		jComboBoxProvincia.removeAllItems();
		ArrayList<Provincia> provincia = provinciaLogica.getListProvincias();
		Iterator<Provincia> it = provincia.iterator();
		while(it.hasNext()){
			jComboBoxProvincia.addItem((Provincia)it.next());
		}
	}

	private void setItemjComboBoxVendedores(){
		jComboBoxVendedores.removeAllItems();
		ArrayList<Vendedor> vendedor = vendedorLogica.getListVendedor();
		Iterator<Vendedor> it = vendedor.iterator();
		while(it.hasNext()){
			jComboBoxVendedores.addItem((Vendedor)it.next());
		}
	}

	private void selectItemjComboBoxCondIva(CondicionIva condIva){
		for(int i = 0; i < jComboBoxCondIva.getItemCount(); i++){
			CondicionIva condicionIva = (CondicionIva)jComboBoxCondIva.getItemAt(i);
			if(condIva.getCodigo() == condicionIva.getCodigo()){
				jComboBoxCondIva.setSelectedItem(condicionIva);
				break;
			}
		}
	}

	private void selectItemjComboBoxCondVenta(CondicionVenta condVta){
		for(int i = 0; i < jComboBoxCondVenta.getItemCount(); i++){
			CondicionVenta condicionVenta = (CondicionVenta)jComboBoxCondVenta.getItemAt(i);
			if(condVta.getCodigoCondicionVenta() == condicionVenta.getCodigoCondicionVenta()){
				jComboBoxCondVenta.setSelectedItem(condicionVenta);
				break;
			}
		}
	}

	private void selectItemjComboBoxListaPre(ListaPrecio lisPrecio){
		for(int i = 0; i < jComboBoxListaPre.getItemCount(); i++){
			ListaPrecio listaPrecio = (ListaPrecio)jComboBoxListaPre.getItemAt(i);
			if(lisPrecio.getCodigoListaPrecios() == listaPrecio.getCodigoListaPrecios()){
				jComboBoxListaPre.setSelectedItem(listaPrecio);
				break;
			}
		}
	}

	private void selectItemjComboBoxLocalidad(Localidad locali){
		for(int i = 0; i < jComboBoxLocalidad.getItemCount(); i++){
			Localidad localidad = (Localidad)jComboBoxLocalidad.getItemAt(i);
			if(locali.getCodigo() == localidad.getCodigo()){
				jComboBoxLocalidad.setSelectedItem(localidad);
				break;
			}
		}
	}

	private void selectItemjComboBoxProvincia(Provincia prov){
		for(int i = 0; i < jComboBoxProvincia.getItemCount(); i++){
			Provincia provincia = (Provincia)jComboBoxProvincia.getItemAt(i);
			if(prov.getCodigo() == provincia.getCodigo()){
				jComboBoxProvincia.setSelectedItem(provincia);
				break;
			}
		}
	}

	private void selectItemjComboBoxVendedores(Vendedor vend){
		for(int i = 0; i < jComboBoxVendedores.getItemCount(); i++){
			Vendedor vendedor = (Vendedor)jComboBoxVendedores.getItemAt(i);
			if(vend.getCodigo() == vendedor.getCodigo()){
				jComboBoxVendedores.setSelectedItem(vendedor);
				break;
			}
		}
	}

	private void selectItemjComboBoxDocTipo(DocumentoTipoPersona docTipoPer){
		for(int i = 0; i < jComboBoxDocTipo.getItemCount(); i++){
			DocumentoTipoPersona documentoTipoPersona = (DocumentoTipoPersona)jComboBoxDocTipo.getItemAt(i);
			if(docTipoPer.getCodigo() == documentoTipoPersona.getCodigo()){
				jComboBoxDocTipo.setSelectedItem(documentoTipoPersona);
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
		ArrayList<Cliente> clientes = clienteLogica.getListClientes();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Nombre");
		titulos.add("Apellído");
		titulos.add("Razón Social");
		titulos.add("Nombre Fantasía");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(clientes != null){
			Iterator<Cliente> it = clientes.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				cliente = (Cliente)it.next();
				renglon.add(String.valueOf(cliente.getCodigo()));
				renglon.add(cliente.getNombre());
				renglon.add(cliente.getApellido());
				renglon.add(cliente.getRazonSocial());
				renglon.add(cliente.getNombreFantasia());
				registros.add(renglon);
			}
		}
		myTableModel.setDataVector(registros, titulos);
		//setea ancho de campos
		jTableCliente.getColumnModel().getColumn(0).setPreferredWidth(8);
		jTableCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
		jTableCliente.getColumnModel().getColumn(2).setPreferredWidth(150);
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	public Cliente getSelectedElement(){
		try{
			int codigo = Integer.parseInt((String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableCliente.getSelectedRow()), 0));
			cliente = clienteLogica.getCliente(codigo);
			return cliente;
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
	public void setCamposSelectedElement(Cliente cliente){
		try{
			jTextFieldCodigo.setText(String.valueOf(cliente.getCodigo()));
			selectItemjComboBoxCondIva(cliente.getCondicionIVA());
			selectItemjComboBoxCondVenta(cliente.getCondicionVentaDefault());
			jTextFieldNombre.setText(cliente.getNombre());
			jTextFieldApellido.setText(cliente.getApellido());
			selectItemjComboBoxDocTipo(cliente.getDocumentoTipoPersona());

			if(jTextFieldNroDoc.getFormatter() != null)jTextFieldNroDoc.getFormatter().uninstall();
			mfNroDoc.setMask(cliente.getDocumentoTipoPersona().getMascara());
			mfNroDoc.install(jTextFieldNroDoc);
			jTextFieldNroDoc.setText(cliente.getDocumentoNroPersona());


			//jTextFieldNroCuit.setText(cliente.getNumeroCuit());
			jTextFieldDirCalle.setText(cliente.getDireccionCalle());
			jTextFieldDirNro.setText(cliente.getDireccionNro());
			jTextFieldDirDpto.setText(cliente.getDireccionDpto());
			jTextFieldDirPiso.setText(String.valueOf(cliente.getDireccionPiso()));
			jTextFieldTelefono.setText(cliente.getTelefonoFijo());
			jTextFieldMovil.setText(cliente.getTelefonoMovil());
			jTextFieldEmail.setText(cliente.getEmail());
			jCheckBoxCtaCteActiva.setSelected(cliente.isCtaCteActiva());

			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			jTextFieldFechaNac.setText(dateFormatArgen.format(dateFormatJapan.parse(cliente.getFechaNacimiento())));
			jTextFieldFechaAlta.setText(dateFormatArgen.format(dateFormatJapan.parse(cliente.getFechaAlta())));
			jTextFieldRazonSocial.setText(cliente.getRazonSocial());
			jTextFieldNomFantasia.setText(cliente.getNombreFantasia());
			jTextFieldLimiteCredito.setText(getValueDecimalVisual(cliente.getLimiteCredito()));
			selectItemjComboBoxListaPre(cliente.getListaPrecios());
			selectItemjComboBoxLocalidad(cliente.getLocalidad());
			selectItemjComboBoxProvincia(cliente.getProvincia());
			selectItemjComboBoxVendedores(cliente.getVendedor());
			jTextFieldDescDefault.setText(getValuePorcentVisual(cliente.getPorcentajeDescuentoDefault()));
			jTextFieldDescMax.setText(getValuePorcentVisual(cliente.getPorcentajeDescuentoMaximo()));
			jCheckBoxActivo.setSelected(cliente.isActivo());

		}catch (ParseException e){
			e.printStackTrace();
		}
	}


	/************** GESTION DE ITEMS DE LA BASE DE DATOS Y DE LA TABLA ***********************/

	/*AGREGA UN NUEVO ITEM A LA BASE DE DATOS Y A LA TABLA*/
	public int addItem(Cliente cliente){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldNombre.getText().isEmpty() && !jTextFieldDirCalle.getText().isEmpty()){
					/*SETEA LOS VALORES*/
					cliente.setCondicionVentaDefault((CondicionVenta)jComboBoxCondVenta.getSelectedItem());
					cliente.setCondicionIVA((CondicionIva)jComboBoxCondIva.getSelectedItem());
					cliente.setNombre(jTextFieldNombre.getText());
					cliente.setApellido(jTextFieldApellido.getText());
					cliente.setDocumentoTipoPersona((DocumentoTipoPersona)jComboBoxDocTipo.getSelectedItem());

					if(jTextFieldNroDoc.getFormatter() != null)jTextFieldNroDoc.getFormatter().uninstall();
					cliente.setDocumentoNroPersona(jTextFieldNroDoc.getText());
					//cliente.setNumeroCuit(jTextFieldNroCuit.getText());
					cliente.setDireccionCalle(jTextFieldDirCalle.getText());
					cliente.setDireccionDpto(jTextFieldDirDpto.getText());
					cliente.setDireccionNro(jTextFieldDirNro.getText());
					if(!jTextFieldDirPiso.getText().isEmpty())cliente.setDireccionPiso(Integer.parseInt((jTextFieldDirPiso.getText())));
					cliente.setTelefonoFijo(jTextFieldTelefono.getText());
					cliente.setTelefonoMovil(jTextFieldMovil.getText());
					cliente.setEmail(jTextFieldEmail.getText());
					cliente.setCtaCteActiva(jCheckBoxCtaCteActiva.isSelected());
					cliente.setFechaAlta(dateFormatJapan.format(new Date()));
					cliente.setFechaNacimiento(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaNac.getText())));
					cliente.setRazonSocial(jTextFieldRazonSocial.getText());
					cliente.setNombreFantasia(jTextFieldNomFantasia.getText());
					cliente.setLimiteCredito(getValueDecimalReal(jTextFieldLimiteCredito.getText()));
					cliente.setClienteCategoria(new ClienteCategoria(1,"prueba"));
					cliente.setListaPrecios((ListaPrecio)jComboBoxListaPre.getSelectedItem());
					cliente.setVendedor((Vendedor)jComboBoxVendedores.getSelectedItem());
					cliente.setProvincia((Provincia)jComboBoxProvincia.getSelectedItem());
					cliente.setLocalidad((Localidad)jComboBoxLocalidad.getSelectedItem());
					cliente.setPorcentajeDescuentoDefault(getValuePorcentReal(jTextFieldDescDefault.getText()));
					cliente.setPorcentajeDescuentoMaximo(getValuePorcentReal(jTextFieldDescMax.getText()));
					cliente.setActivo(jCheckBoxActivo.isSelected());


					/*EJECUTA EL ADD CON LA LOGICA Y EVALUA LA RESPUESTA*/
					if(clienteLogica.addCliente(cliente)){
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
					jTextFieldNombre.requestFocus(true);
					statusAction = 1;
				}
			return statusAction;
		}catch(Exception ex){
			ex.printStackTrace();
			return statusAction;
		}
	}


	/* HACE UPDATE DE UN ITEM SELECCIONADO EN LA TABLA*/
	public int updateItem(Cliente cliente){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldNombre.getText().isEmpty() && !jTextFieldDirCalle.getText().isEmpty()){
				/*setea los nuevos valores al objeto seleccionado para modificar*/
				cliente.setCodigo(Integer.parseInt(jTextFieldCodigo.getText()));
				cliente.setCondicionVentaDefault((CondicionVenta)jComboBoxCondVenta.getSelectedItem());
				cliente.setCondicionIVA((CondicionIva)jComboBoxCondIva.getSelectedItem());
				cliente.setNombre(jTextFieldNombre.getText());
				cliente.setApellido(jTextFieldApellido.getText());
				cliente.setDocumentoTipoPersona((DocumentoTipoPersona)jComboBoxDocTipo.getSelectedItem());
				cliente.setDocumentoNroPersona(jTextFieldNroDoc.getText());
				//cliente.setNumeroCuit(jTextFieldNroCuit.getText());
				cliente.setDireccionCalle(jTextFieldDirCalle.getText());
				cliente.setDireccionDpto(jTextFieldDirDpto.getText());
				cliente.setDireccionNro(jTextFieldDirNro.getText());
				if(!jTextFieldDirPiso.getText().isEmpty())cliente.setDireccionPiso(Integer.parseInt((jTextFieldDirPiso.getText())));
				cliente.setTelefonoFijo(jTextFieldTelefono.getText());
				cliente.setTelefonoMovil(jTextFieldMovil.getText());
				cliente.setEmail(jTextFieldEmail.getText());
				cliente.setCtaCteActiva(jCheckBoxCtaCteActiva.isSelected());
				cliente.setFechaAlta(dateFormatJapan.format(new Date()));
				cliente.setFechaNacimiento(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaNac.getText())));
				cliente.setRazonSocial(jTextFieldRazonSocial.getText());
				cliente.setNombreFantasia(jTextFieldNomFantasia.getText());
				cliente.setLimiteCredito(getValueDecimalReal(jTextFieldLimiteCredito.getText()));
				cliente.setClienteCategoria(new ClienteCategoria(1,"prueba"));
				cliente.setListaPrecios((ListaPrecio)jComboBoxListaPre.getSelectedItem());
				cliente.setVendedor((Vendedor)jComboBoxVendedores.getSelectedItem());
				cliente.setProvincia((Provincia)jComboBoxProvincia.getSelectedItem());
				cliente.setLocalidad((Localidad)jComboBoxLocalidad.getSelectedItem());
				cliente.setPorcentajeDescuentoDefault(getValuePorcentReal(jTextFieldDescDefault.getText()));
				cliente.setPorcentajeDescuentoMaximo(getValuePorcentReal(jTextFieldDescMax.getText()));
				cliente.setActivo(jCheckBoxActivo.isSelected());

				/*EJECUTA EL UDPATE CON LA LOGICA*/
				if(clienteLogica.updateCliente(cliente)){
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
	public int deleteItem(Cliente cliente){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			JOptionPane.showMessageDialog(null, "El Cliente Seleccionado Será Eliminado\n" +
					"Código: "+cliente.getCodigo(),"Eliminar",JOptionPane.INFORMATION_MESSAGE);
			int selectedOption = JOptionPane.showConfirmDialog(null, "Desea Eliminar el Cliente","Eliminar", JOptionPane.OK_CANCEL_OPTION);
			if(selectedOption == 0){
				/*EJECUTA EL DELETE CON LA LOGICA*/
				if(clienteLogica.deleteCliente(cliente.getCodigo())){
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
			jToolBar.add(getJButtonaActionSeleccionar());
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
						int statusAction = addItem(cliente);
						if(statusAction == 0){
							setItemsInJtable();
							resetCampos();
							int selectedOption = JOptionPane.showConfirmDialog(null, "Desea agregar un nuevo elemento", "Operación Satisfactoria!", JOptionPane.OK_CANCEL_OPTION);
							System.out.println(selectedOption);
							if(selectedOption == 0){
								jTextFieldCodigo.setText(String.valueOf(clienteLogica.getNewCodigoItems()));
								jTextFieldNombre.requestFocus(true);
								jCheckBoxActivo.setSelected(true);
								jTextFieldFechaAlta.setText(dateFormatArgen.format(new Date()));
							}else{
								activeActionType = null;
								setEnabledButtonsToolbar(true, false, false, false, false, true, false);
								setVisibleButtonsToolbar(true, true, true, false, false, true);

								setEstatusAllComponent(false, true, true, true);
								resetCampos();
							}
						}else if(statusAction == 2){

							setEstatusAllComponent(false, true, true, true);
							setEnabledButtonsToolbar(true, false, false, false, false, true, false);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							activeActionType = null;
							resetCampos();
						}
					}else if(activeActionType.equals("UPDATE")){
						int statusAction = updateItem(getSelectedElement());
						if(statusAction == 0){
							setItemsInJtable();
							activeActionType = null;
							setEnabledButtonsToolbar(true, false, false, false, false, true, false);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							setEstatusAllComponent(false, true, true, true);
							resetCampos();
						}else if(statusAction == 2){

							setEstatusAllComponent(false, true, true, true);
							setEnabledButtonsToolbar(true, false, false, false, false, true, false);
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
							setEnabledButtonsToolbar(true, false, false, false, false, true, false);
							setVisibleButtonsToolbar(true, true, true, false, false, true);
							setEstatusAllComponent(false, true, true, true);
							resetCampos();
						}else{
							jTextFieldNombre.requestFocus(true);
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
					cliente = new Cliente();
					resetCampos();
					setEstatusAllComponent(true, false, false, false);
					setVisibleButtonsToolbar(true, true, true, true, true, true);
					setEnabledButtonsToolbar(false, false, false, true, true, false, false);
					jTextFieldCodigo.setText(String.valueOf(clienteLogica.getNewCodigoItems()));
					jTextFieldCodigo.setEditable(false);
					jTextFieldFechaAlta.setText(dateFormatArgen.format(new Date()));
					jTextFieldFechaAlta.setEditable(false);
					jCheckBoxActivo.setSelected(true);

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
							setEstatusAllComponent(true, false, false, false);
							setVisibleButtonsToolbar(true, true, true, true, true, true);
							setEnabledButtonsToolbar(false, false, false, true, true, false, false);
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
					setEnabledButtonsToolbar(false, false, false, false, false, false, false);
					int statusAction = deleteItem(getSelectedElement());
					if(statusAction == 0){
						setItemsInJtable();
						setEnabledButtonsToolbar(true, false, false, false, false, true, false);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						//setEstatusAllComponent(false, true, true, true);
						resetCampos();
					}else{
						setEnabledButtonsToolbar(true, false, false, false, false, true, false);
						setVisibleButtonsToolbar(true, true, true, false, false, true);
						//setEstatusAllComponent(false, true, true, true);
						resetCampos();

					}
				}
			});
		}
		return jButtonActionEliminar;
	}


	/********** ACTIONES CON BUTTONS DE TOOLBAR ****************/

	/*habilita o desabilita los botones de la toolbar*/
	public void setEnabledButtonsToolbar(boolean btnNuevo, boolean btnModificar, boolean btnEliminar, boolean btnOk, boolean btnCancel, boolean btnCloce, boolean btnSeleccionar){
		jButtonActionAlta.setEnabled(btnNuevo);
		jButtonActionEliminar.setEnabled(btnEliminar);
		jButtonActionModificacion.setEnabled(btnModificar);
		jButtonActionOk.setEnabled(btnOk);
		jButtonActionCancel.setEnabled(btnCancel);
		jButtonaActionCloce.setEnabled(btnCloce);
		jButtonaActionSeleccionar.setVisible(btnSeleccionar);
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
		jTableCliente.setEnabled(estadoTableList);
		//jTextFieldTelefono.setEditable(false);
		//jTextFieldMovil.setEditable(false);
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

	/**
	 * This method initializes jButtonaActionSeleccionar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonaActionSeleccionar() {
		if (jButtonaActionSeleccionar == null) {
			jButtonaActionSeleccionar = new JButton();
			jButtonaActionSeleccionar.setPreferredSize(new Dimension(100, 30));
			jButtonaActionSeleccionar.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonaActionSeleccionar.setText("Seleccionar");
			jButtonaActionSeleccionar.setBorderPainted(false);
			jButtonaActionSeleccionar
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
							dispose();
						}
					});
		}
		return jButtonaActionSeleccionar;
	}



}  //  @jve:decl-index=0:visual-constraint="10,10"

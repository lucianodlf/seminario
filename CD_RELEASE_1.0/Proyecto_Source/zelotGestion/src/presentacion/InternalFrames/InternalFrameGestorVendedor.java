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
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

import logica.DocumentoTipoPersonaLogica;
import logica.VendedorLogica;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;
import complementos.StringMD;

import dominio.DocumentoTipoPersona;
import dominio.Vendedor;

public class InternalFrameGestorVendedor extends JInternalFrame {


	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="17,217"
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableVendedor = null;
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
	private JLabel jLabelFechaAlta = null;
	private JFormattedTextField jTextFieldFechaAlta = null;
	private JLabel jLabelDirPiso = null;
	private JTextField jTextFieldDirPiso = null;
	private JLabel jLabelTelFijo = null;
	private JTextField jTextFieldTelefono = null;
	private JTextField jTextFieldEmail = null;
	private JLabel jLabelEmail = null;
	private JLabel jLabelPassword = null;
	private JLabel jLabelPassword2 = null;
	private JLabel jLabelDirCalle = null;
	private JTextField jTextFieldDirCalle = null;
	private JLabel jLabelDirNro = null;
	private JTextField jTextFieldDirNro = null;
	private JLabel jLabelDirDpto = null;
	private JTextField jTextFieldDirDpto = null;
	private JTextField jTextFieldMovil = null;
	private JLabel jLabelMovil = null;
	private JCheckBox jCheckBoxActivo = null;
	private JComboBox jComboBoxDocTipo = null;
	private JLabel jLabelDocTipo = null;
	private JButton jButtonAddDeposito = null;
	private JFormattedTextField jTextFieldFechaNac = null;
	private JLabel jLabelFechaNac = null;
	private JLabel jLabelDeposito = null;
	private JComboBox jComboBoxDeposito = null;
	private JLabel jLabelVehiculo = null;
	private JComboBox jComboBoxVehiculo = null;
	private JTextField jTextFieldApellido = null;
	private JLabel jLabelApellido = null;
	private JButton jButtonAddVehiculo = null;
	private JPasswordField jPasswordFieldPassword = null;
	private JPasswordField jPasswordFieldPassword1 = null;

	/*clases de logica*/
	private Vendedor vendedor = null;  //  @jve:decl-index=0:
	private VendedorLogica vendedorLogica = VendedorLogica.getInstance();  //  @jve:decl-index=0:
	private DocumentoTipoPersonaLogica documentoTipoPersonaLogica = DocumentoTipoPersonaLogica.getInstance();


	private String activeActionType = null; //se utiliza para determina cual es la accion activa (agrega, modificar o eliminar)
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private MaskFormatter mfNroDoc = new MaskFormatter();  //  @jve:decl-index=0:
	private JButton jButtonChangePassword = null;






	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameGestorVendedor() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(774, 559);
		this.setMaximumSize(new Dimension(1024, 670));
		this.setMinimumSize(new Dimension(0, 0));
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Gestión de Vendedors");
		this.setResizable(false);
		resetCampos();
		setEstatusAllComponent(false, true, true, true);
		setVisibleButtonsToolbar(true, true, true, false, false, true);
		setEnabledButtonsToolbar(true, false, false, false, false, true);
		vendedor = null;
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
			jLabelVehiculo = new JLabel();
			jLabelVehiculo.setBounds(new Rectangle(391, 402, 60, 20));
			jLabelVehiculo.setText("Vehículo:");
			jLabelDeposito = new JLabel();
			jLabelDeposito.setBounds(new Rectangle(391, 367, 60, 20));
			jLabelDeposito.setText("Depósito:");
			jLabelFechaNac = new JLabel();
			jLabelFechaNac.setBounds(new Rectangle(445, 300, 106, 20));
			jLabelFechaNac.setText("Fecha Nacimiento:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jLabelDocTipo = new JLabel();
			jLabelDocTipo.setBounds(new Rectangle(5, 300, 115, 20));
			jLabelDocTipo.setText("Tipo Identificación:");
			jLabelMovil = new JLabel();
			jLabelMovil.setBounds(new Rectangle(5, 437, 35, 20));
			jLabelMovil.setText("Móvil:");
			jLabelDirDpto = new JLabel();
			jLabelDirDpto.setText("Dpto:");
			jLabelDirDpto.setBounds(new Rectangle(410, 335, 32, 20));
			jLabelDirNro = new JLabel();
			jLabelDirNro.setText("Nro:");
			jLabelDirNro.setBounds(new Rectangle(250, 335, 29, 20));
			jLabelDirCalle = new JLabel();
			jLabelDirCalle.setText("Calle:");
			jLabelDirCalle.setBounds(new Rectangle(5, 335, 35, 20));
			jLabelPassword2 = new JLabel();
			jLabelPassword2.setText("Contraseña:");
			jLabelPassword2.setBounds(new Rectangle(5, 500, 70, 20));
			jLabelPassword = new JLabel();
			jLabelPassword.setText("Contraseña:");
			jLabelPassword.setBounds(new Rectangle(5, 470, 70, 20));
			jLabelEmail = new JLabel();
			jLabelEmail.setText("Email:");
			jLabelEmail.setBounds(new Rectangle(5, 367, 40, 20));
			jLabelTelFijo = new JLabel();
			jLabelTelFijo.setBounds(new Rectangle(5, 402, 60, 20));
			jLabelTelFijo.setText("Télefono:");
			jLabelDirPiso = new JLabel();
			jLabelDirPiso.setText("Piso:");
			jLabelDirPiso.setBounds(new Rectangle(331, 335, 30, 20));
			jLabelFechaAlta = new JLabel();
			jLabelFechaAlta.setBounds(new Rectangle(588, 260, 70, 20));
			jLabelFechaAlta.setText("Fecha Alta:");
			jLabelNroDoc = new JLabel();
			jLabelNroDoc.setBounds(new Rectangle(216, 300, 112, 20));
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
			jContentPane.add(jLabelPassword, null);
			jContentPane.add(jLabelPassword2, null);
			jContentPane.add(getJTextFieldFechaNac(), null);
			jContentPane.add(jLabelFechaNac, null);
			jContentPane.add(jLabelDeposito, null);
			jContentPane.add(getJComboBoxDeposito(), null);
			jContentPane.add(jLabelVehiculo, null);
			jContentPane.add(getJComboBoxVehiculo(), null);
			jContentPane.add(getJTextFieldApellido(), null);
			jContentPane.add(jLabelApellido, null);
			jContentPane.add(getJButtonAddDeposito(), null);
			jContentPane.add(getJButtonAddVehiculo(), null);
			jContentPane.add(getJCheckBoxActivo(), null);
			jContentPane.add(getJPasswordFieldPassword(), null);
			jContentPane.add(getJPasswordFieldPassword1(), null);
			jContentPane.add(getJButtonChangePassword(), null);
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
			jScrollPaneaList.setBounds(new Rectangle(5, 65, 750, 190));
			jScrollPaneaList.setViewportView(getJtableVendedor());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJtableVendedor() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableVendedor == null) {
			jTableVendedor = new MyJtable(getMyTableModel());
			jTableVendedor.setRowSorter(geTableRowSorter());
			jTableVendedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableVendedor.setRowHeight(20);
			setItemsInJtable();
			jTableVendedor.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableVendedor.setAutoscrolls(true);
			jTableVendedor.setShowVerticalLines(true);
			jTableVendedor.setShowHorizontalLines(true);
			jTableVendedor.setVisible(true);
			jTableVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableVendedor.isEnabled()){
						vendedor = getSelectedElement();
						if(vendedor != null){
							setCamposSelectedElement(vendedor);
							setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
						}
					}
				}
			});
			jTableVendedor.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {
					if(jTableVendedor.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							vendedor = getSelectedElement();
							if(vendedor != null){
								setCamposSelectedElement(vendedor);
								setEnabledButtonsToolbar(true, true, true, false, false, true); //SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
							}
						}
					}
				}
			});
		}
		return jTableVendedor;
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
			jComboBoxCriterioBusqueda.addItem("Apellído");
			jComboBoxCriterioBusqueda.addItem("Email");
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
	 * This method initializes jTextFieldNroDoc
	 *
	 * @return javax.swing.JTextField
	 */
	private JFormattedTextField getJTextFieldNroDoc() {
			if (jTextFieldNroDoc == null) {
				jTextFieldNroDoc = new JFormattedTextField();
				jTextFieldNroDoc.setBounds(new Rectangle(330, 300, 108, 20));

			}
		return jTextFieldNroDoc;
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
				jTextFieldFechaAlta.setBounds(new Rectangle(668, 260, 80, 20));
			}
		}catch(ParseException e){
			e.printStackTrace();
		}

		return jTextFieldFechaAlta;
	}

	/**
	 * This method initializes jTextFieldDirPiso
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDirPiso() {
		if (jTextFieldDirPiso == null) {
			jTextFieldDirPiso = new JTextField();
			jTextFieldDirPiso.setBounds(new Rectangle(365, 335, 40, 20));
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
			jTextFieldTelefono.setBounds(new Rectangle(75, 402, 300, 20));
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
			jTextFieldEmail.setBounds(new Rectangle(75, 367, 300, 20));
			jTextFieldEmail.setDocument(new MyPlainDocument(jTextFieldEmail,100,null,false));
		}
		return jTextFieldEmail;
	}

	/**
	 * This method initializes jTextFieldDirCalle
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDirCalle() {
		if (jTextFieldDirCalle == null) {
			jTextFieldDirCalle = new JTextField();
			jTextFieldDirCalle.setBounds(new Rectangle(45, 335, 202, 20));
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
			jTextFieldDirNro.setBounds(new Rectangle(280, 335, 50, 20));
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
	 * This method initializes jTextFieldDirDpto
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDirDpto() {
		if (jTextFieldDirDpto == null) {
			jTextFieldDirDpto = new JTextField();
			jTextFieldDirDpto.setBounds(new Rectangle(445, 335, 40, 20));
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
			jTextFieldMovil.setBounds(new Rectangle(75, 437, 300, 20));
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
			jCheckBoxActivo.setBounds(new Rectangle(677, 295, 60, 24));
		}
		return jCheckBoxActivo;
	}

	private JButton getJButtonAddDeposito() {
		if (jButtonAddDeposito == null) {
			jButtonAddDeposito = new JButton();
			jButtonAddDeposito.setText("Agregar Depósito");
			jButtonAddDeposito.setBounds(new Rectangle(585, 367, 140, 20));
			jButtonAddDeposito.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					/*System.out.println("Rubro()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorRubro dialogGestorRubro = new DialogGestorRubro(new Frame());
					dialogGestorRubro.setModal(true);
					dialogGestorRubro.setVisible(true);*/

				}
			});

		}
		return jButtonAddDeposito;
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
			jTextFieldFechaNac.setBounds(new Rectangle(560, 300, 80, 20));
		}
		}catch(ParseException e){
			e.printStackTrace();
		}
		return jTextFieldFechaNac;
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
			jTextFieldApellido.setText("Apellído");
		}
		return jTextFieldApellido;
	}

	/**
	 * This method initializes jButtonAddVehiculo
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddVehiculo() {
		if (jButtonAddVehiculo == null) {
			jButtonAddVehiculo = new JButton();
			jButtonAddVehiculo.setBounds(new Rectangle(585, 402, 140, 20));
			jButtonAddVehiculo.setText("Agregar Vehículo");
			jButtonAddVehiculo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButtonAddVehiculo;
	}

	/**
	 * This method initializes jPasswordFieldPassword
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordFieldPassword() {
		if (jPasswordFieldPassword == null) {
			jPasswordFieldPassword = new JPasswordField();
			jPasswordFieldPassword.setBounds(new Rectangle(80, 470, 130, 20));
		}
		return jPasswordFieldPassword;
	}

	/**
	 * This method initializes jPasswordFieldPassword1
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordFieldPassword1() {
		if (jPasswordFieldPassword1 == null) {
			jPasswordFieldPassword1 = new JPasswordField();
			jPasswordFieldPassword1.setBounds(new Rectangle(80, 500, 130, 20));
		}
		return jPasswordFieldPassword1;
	}

	/********VISUALES COMBOS BOX ***************/

	/**
	 * This method initializes jComboBoxDocTipo
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxDocTipo() {
		if (jComboBoxDocTipo == null) {
			jComboBoxDocTipo = new JComboBox();
			jComboBoxDocTipo.setBounds(new Rectangle(125, 300, 80, 20));
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
	 * This method initializes jComboBoxDeposito
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxDeposito() {
		if (jComboBoxDeposito == null) {
			jComboBoxDeposito = new JComboBox();
			jComboBoxDeposito.setBounds(new Rectangle(461, 367, 120, 20));
			jComboBoxDeposito.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
		}
		return jComboBoxDeposito;
	}

	/**
	 * This method initializes jComboBoxVehiculo
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxVehiculo() {
		if (jComboBoxVehiculo == null) {
			jComboBoxVehiculo = new JComboBox();
			jComboBoxVehiculo.setBounds(new Rectangle(461, 402, 120, 20));
			jComboBoxVehiculo.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
				}
			});
		}
		return jComboBoxVehiculo;
	}



	/************* GESTION DE COMBO BOX ****************/
	private void setItemjComboBoxDocTipo(){
		ArrayList<DocumentoTipoPersona> tipoDoc = documentoTipoPersonaLogica.getListDocumentoTipoPersona();
		Iterator<DocumentoTipoPersona> it = tipoDoc.iterator();
		while(it.hasNext()){
			jComboBoxDocTipo.addItem((DocumentoTipoPersona)it.next());
		}
	}
	/*private void selectItemjComboBoxLocalidad(Localidad locali){
		for(int i = 0; i < jComboBoxVehiculo.getItemCount(); i++){
			Localidad localidad = (Localidad)jComboBoxVehiculo.getItemAt(i);
			if(locali.getCodigo() == localidad.getCodigo()){
				jComboBoxVehiculo.setSelectedItem(localidad);
				break;
			}
		}
	}
	private void selectItemjComboBoxProvincia(Provincia prov){
		for(int i = 0; i < jComboBoxDeposito.getItemCount(); i++){
			Provincia provincia = (Provincia)jComboBoxDeposito.getItemAt(i);
			if(prov.getCodigo() == provincia.getCodigo()){
				jComboBoxDeposito.setSelectedItem(provincia);
				break;
			}
		}
	}*/
	private void selectItemjComboBoxDocTipo(DocumentoTipoPersona docTipoPer){
		for(int i = 0; i < jComboBoxDocTipo.getItemCount(); i++){
			DocumentoTipoPersona documentoTipoPersona = (DocumentoTipoPersona)jComboBoxDocTipo.getItemAt(i);
			if(docTipoPer.getCodigo() == documentoTipoPersona.getCodigo()){
				jComboBoxDocTipo.setSelectedItem(documentoTipoPersona);
				break;
			}
		}
	}

	/************** GESTION DE ITEMS DE LA TABLA *******************/
	public void setItemsInJtable(){
		ArrayList<Vendedor> vendedors = vendedorLogica.getListVendedor();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Nombre");
		titulos.add("Apellído");
		titulos.add("Email");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(vendedors != null){
			Iterator<Vendedor> it = vendedors.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				vendedor = (Vendedor)it.next();
				renglon.add(String.valueOf(vendedor.getCodigo()));
				renglon.add(vendedor.getNombre());
				renglon.add(vendedor.getApellido());
				renglon.add(String.valueOf(vendedor.getEmail()));
				registros.add(renglon);
			}
		}
		myTableModel.setDataVector(registros, titulos);
		//setea ancho de campos
		jTableVendedor.getColumnModel().getColumn(0).setPreferredWidth(8);
		jTableVendedor.getColumnModel().getColumn(1).setPreferredWidth(100);
		jTableVendedor.getColumnModel().getColumn(2).setPreferredWidth(100);
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	public Vendedor getSelectedElement(){
		try{
			int codigo = Integer.parseInt((String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableVendedor.getSelectedRow()), 0));
			vendedor = vendedorLogica.getVendedor(codigo);
			return vendedor;
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
	public void setCamposSelectedElement(Vendedor vendedor){
		try{
			jTextFieldCodigo.setText(String.valueOf(vendedor.getCodigo()));
			jTextFieldNombre.setText(vendedor.getNombre());
			jTextFieldApellido.setText(vendedor.getApellido());
			selectItemjComboBoxDocTipo(vendedor.getDocumentoTipoPersona());

			if(jTextFieldNroDoc.getFormatter() != null)jTextFieldNroDoc.getFormatter().uninstall();
			mfNroDoc.setMask(vendedor.getDocumentoTipoPersona().getMascara());
			mfNroDoc.install(jTextFieldNroDoc);
			jTextFieldNroDoc.setText(vendedor.getDocumentoNroPersona());

			jTextFieldDirCalle.setText(vendedor.getDireccionCalle());
			jTextFieldDirNro.setText(vendedor.getDireccionNro());
			jTextFieldDirDpto.setText(vendedor.getDireccionDpto());
			jTextFieldDirPiso.setText(String.valueOf(vendedor.getDireccionPiso()));
			jTextFieldTelefono.setText(vendedor.getTelefonoFijo());
			jTextFieldMovil.setText(vendedor.getTelefonoMovil());
			jTextFieldEmail.setText(vendedor.getEmail());
			jTextFieldFechaNac.setText(dateFormatArgen.format(dateFormatJapan.parse(vendedor.getFechaNacimiento())));
			jTextFieldFechaAlta.setText(dateFormatArgen.format(dateFormatJapan.parse(vendedor.getFechaAlta())));
			jCheckBoxActivo.setSelected(vendedor.isActivo());

		}catch (ParseException e){
			e.printStackTrace();
		}
	}


	/************** GESTION DE ITEMS DE LA BASE DE DATOS Y DE LA TABLA ***********************/

	/*AGREGA UN NUEVO ITEM A LA BASE DE DATOS Y A LA TABLA*/
	public int addItem(Vendedor vendedor){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldNombre.getText().isEmpty() && !jTextFieldApellido.getText().isEmpty() && !jTextFieldDirCalle.getText().isEmpty() && validatePassword(jPasswordFieldPassword.getPassword(), jPasswordFieldPassword1.getPassword())){
					/*SETEA LOS VALORES*/
					vendedor.setNombre(jTextFieldNombre.getText());
					vendedor.setApellido(jTextFieldApellido.getText());
					vendedor.setDocumentoTipoPersona((DocumentoTipoPersona)jComboBoxDocTipo.getSelectedItem());

					if(jTextFieldNroDoc.getFormatter() != null)jTextFieldNroDoc.getFormatter().uninstall();
					vendedor.setDocumentoNroPersona(jTextFieldNroDoc.getText());

					vendedor.setDireccionCalle(jTextFieldDirCalle.getText());
					vendedor.setDireccionDpto(jTextFieldDirDpto.getText());
					vendedor.setDireccionNro(jTextFieldDirNro.getText());
					if(!jTextFieldDirPiso.getText().isEmpty())vendedor.setDireccionPiso(Integer.parseInt((jTextFieldDirPiso.getText())));
					vendedor.setTelefonoFijo(jTextFieldTelefono.getText());
					vendedor.setTelefonoMovil(jTextFieldMovil.getText());
					vendedor.setEmail(jTextFieldEmail.getText());
					vendedor.setFechaAlta(dateFormatJapan.format(new Date()));
					vendedor.setFechaNacimiento(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaNac.getText())));
					//vendedor.setProvincia((Provincia)jComboBoxDeposito.getSelectedItem());
					//vendedor.setLocalidad((Localidad)jComboBoxVehiculo.getSelectedItem());

					System.out.println(jPasswordFieldPassword.getPassword());
					if(!(String.valueOf(jPasswordFieldPassword.getPassword()).isEmpty())){
						vendedor.setPassword(StringMD.getStringMessageDigest(String.valueOf(jPasswordFieldPassword.getPassword()), StringMD.MD5));
					}

					vendedor.setActivo(jCheckBoxActivo.isSelected());


					/*EJECUTA EL ADD CON LA LOGICA Y EVALUA LA RESPUESTA*/
					if(vendedorLogica.addVendedor(vendedor)){
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
	public int updateItem(Vendedor vendedor){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			if(!jTextFieldNombre.getText().isEmpty() && !jTextFieldApellido.getText().isEmpty() && !jTextFieldDirCalle.getText().isEmpty() && validatePassword(jPasswordFieldPassword.getPassword(), jPasswordFieldPassword1.getPassword())){
				/*setea los nuevos valores al objeto seleccionado para modificar*/
				vendedor.setCodigo(Integer.parseInt(jTextFieldCodigo.getText()));
				vendedor.setNombre(jTextFieldNombre.getText());
				vendedor.setApellido(jTextFieldApellido.getText());
				vendedor.setDocumentoTipoPersona((DocumentoTipoPersona)jComboBoxDocTipo.getSelectedItem());

				if(jTextFieldNroDoc.getFormatter() != null)jTextFieldNroDoc.getFormatter().uninstall();
				vendedor.setDocumentoNroPersona(jTextFieldNroDoc.getText());

				vendedor.setDireccionCalle(jTextFieldDirCalle.getText());
				vendedor.setDireccionDpto(jTextFieldDirDpto.getText());
				vendedor.setDireccionNro(jTextFieldDirNro.getText());
				if(!jTextFieldDirPiso.getText().isEmpty())vendedor.setDireccionPiso(Integer.parseInt((jTextFieldDirPiso.getText())));
				vendedor.setTelefonoFijo(jTextFieldTelefono.getText());
				vendedor.setTelefonoMovil(jTextFieldMovil.getText());
				vendedor.setEmail(jTextFieldEmail.getText());
				vendedor.setFechaAlta(dateFormatJapan.format(new Date()));
				vendedor.setFechaNacimiento(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaNac.getText())));
				//vendedor.setProvincia((Provincia)jComboBoxDeposito.getSelectedItem());
				//vendedor.setLocalidad((Localidad)jComboBoxVehiculo.getSelectedItem());

				if(!(String.valueOf(jPasswordFieldPassword.getPassword()).isEmpty())){
					vendedor.setPassword(StringMD.getStringMessageDigest(String.valueOf(jPasswordFieldPassword.getPassword()), StringMD.MD5));
				}
				vendedor.setActivo(jCheckBoxActivo.isSelected());

				/*EJECUTA EL UDPATE CON LA LOGICA*/
				if(vendedorLogica.updateVendedor(vendedor)){
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
	public int deleteItem(Vendedor vendedor){
		int statusAction = 2; // 0=Exito | 1=Falso | 2=Error
		try{
			JOptionPane.showMessageDialog(null, "El Vendedor Seleccionado Será Eliminado\n" +
					"Código: "+vendedor.getCodigo(),"Eliminar",JOptionPane.INFORMATION_MESSAGE);
			int selectedOption = JOptionPane.showConfirmDialog(null, "Desea Eliminar el Vendedor","Eliminar", JOptionPane.OK_CANCEL_OPTION);
			if(selectedOption == 0){
				/*EJECUTA EL DELETE CON LA LOGICA*/
				if(vendedorLogica.deleteVendedor(vendedor.getCodigo())){
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
						int statusAction = addItem(vendedor);
						if(statusAction == 0){
							setItemsInJtable();
							resetCampos();
							int selectedOption = JOptionPane.showConfirmDialog(null, "Desea agregar un nuevo elemento", "Operación Satisfactoria!", JOptionPane.OK_CANCEL_OPTION);
							System.out.println(selectedOption);
							if(selectedOption == 0){
								jTextFieldCodigo.setText(String.valueOf(vendedorLogica.getNewCodigoItems()));
								jTextFieldNombre.requestFocus(true);
								jCheckBoxActivo.setSelected(true);
								jTextFieldFechaAlta.setText(dateFormatArgen.format(new Date()));
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
							setItemsInJtable();
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
					vendedor = new Vendedor();
					resetCampos();
					setEstatusAllComponent(true, false, false, false);
					setVisibleButtonsToolbar(true, true, true, true, true, true);
					setEnabledButtonsToolbar(false, false, false, true, true, false);
					jTextFieldCodigo.setText(String.valueOf(vendedorLogica.getNewCodigoItems()));
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
							setEnabledButtonsToolbar(false, false, false, true, true, false);
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
					}else{
						setEnabledButtonsToolbar(true, false, false, false, false, true);
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
			}else if(component[i].getClass() == JPasswordField.class){
				((JPasswordField)component[i]).setEnabled(estado);
			}
		}
		jComboBoxCriterioBusqueda.setEnabled(estadoComboBoxBusqueda);
		jTextFieldBusqueda.setEditable(estadoTextBusqueda);
		jTableVendedor.setEnabled(estadoTableList);
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
			}else if(component[i].getClass() == JPasswordField.class){
				((JPasswordField)component[i]).setText(null);
			}
		}
		setFilterInTabla("", 0);
	}

	private boolean validatePassword(char[] password1, char[] password2){
		boolean result = false;
		String pass1 = String.valueOf(password1);
		String pass2 = String.valueOf(password2);
		System.out.println("pass1 "+pass1);
		System.out.println("pass2 "+pass2);
		if(pass1.equals(pass2)){
			result = true;
		}else{
			JOptionPane.showMessageDialog(null, "Los datos de la contraseña no son iguales", "Error", JOptionPane.INFORMATION_MESSAGE);
		}
		return result;
	}

	/**
	 * This method initializes jButtonChangePassword
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonChangePassword() {
		if (jButtonChangePassword == null) {
			jButtonChangePassword = new JButton();
			jButtonChangePassword.setBounds(new Rectangle(215, 470, 160, 20));
			jButtonChangePassword.setText("Cambiar Contraseña");
		}
		return jButtonChangePassword;
	}



}  //  @jve:decl-index=0:visual-constraint="10,10"

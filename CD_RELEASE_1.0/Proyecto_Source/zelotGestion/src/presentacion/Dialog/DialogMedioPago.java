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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import logica.BancoLogica;
import logica.MedioPagoLogica;
import logica.TarjetaLogica;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyJtextFormatDecimal;
import complementos.MyTableModel;

import dominio.Banco;
import dominio.Cobranza;
import dominio.DetallePago;
import dominio.Factura;
import dominio.MedioPago;
import dominio.Tarjeta;

public class DialogMedioPago extends JDialog {

	private JPanel jContentPane = null;
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableDetallePagos = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JToolBar jToolBar = null;
	private JButton jButtonActionAlta = null;
	private JButton jButtonActionModificacion = null;
	private JButton jButtonActionEliminar = null;
	private JButton jButtonActionOk = null;
	private JButton jButtonActionCancel = null;
	private JButton jButtonaActionCloce = null;
	private JLabel jLabelLblMedioPago = null;
	private JComboBox jComboBoxMedPago = null;
	private JComboBox jComboBoxBanco = null;
	private JLabel jLabelLblBanco = null;
	private JLabel jLabelLblTarjeta = null;
	private JComboBox jComboBoxTarjeta = null;
	private JLabel jLabelLblBancoSucur = null;
	private JTextField jTextFieldBancoSucur = null;
	private JLabel jLabelLblBancoNroCta = null;
	private JTextField jTextFieldBancoNroCta = null;
	private JLabel jLabelLblBancoNroCompTransf = null;
	private JTextField jTextFieldBancoNroCompTransf = null;
	private JLabel jLabelLblBancoNroSerieCheq = null;
	private JTextField jTextFieldBancoNroSerieCheq = null;
	private JLabel jLabelLblTitularTarjNombre = null;
	private JTextField jTextFieldNomTitularTarjeta = null;
	private JLabel jLabelLblNroDocTitularTarj = null;
	private JTextField jTextFieldNroDocTitularTarjeta = null;
	private JLabel jLabelLblBancoFechaEmision = null;
	private JFormattedTextField jTextFieldFechaEmision = null;
	private MaskFormatter maskFormatterFechaEmision = null;
	private JLabel jLabelLblBancoFechaVen = null;
	private JFormattedTextField jTextFieldFechaVen = null;
	private MaskFormatter maskFormatterFechaVen = null;
	private JLabel jLabelLblCotizacion = null;
	private JTextField jTextFieldCotizacion = null;
	private JLabel jLabelLblNroTarjeta = null;
	private JTextField jTextFieldNroTarjeta = null;


	/*clases de logica*/
	private MedioPagoLogica medioPagoLogica = MedioPagoLogica.getInstance();  //  @jve:decl-index=0:
	private BancoLogica bancoLogica = BancoLogica.getInstance();  //  @jve:decl-index=0:
	private TarjetaLogica tarjetaLogica = TarjetaLogica.getInstance();
	private Factura factura = null;  //  @jve:decl-index=0:
	private Cobranza cobranza = null;

	private DetallePago detallePago = null;
	private Vector<DetallePago> detallesPago = null;  //  @jve:decl-index=0:

	private String activeActionType = null; //se utiliza para determina cual es la accion activa (agrega, modificar o eliminar)
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyyMMddhhmmss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:

	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private JLabel jLabelLblImporteMedPago = null;
	private JTextField jTextFieldImporteMedPago = null;
	private JButton jButtonActionGrabarPago = null;
	private JButton jButtonActionCancelarPago = null;
	private int indiceRenglonMedioPago = 0;
	private Vector<String> renglonDetallePago = null;  //  @jve:decl-index=0:

	private Vector<DetallePago> detallesPagosFinal = null;
	/**
	 * This is the xxx default constructor
	 */
	public DialogMedioPago(Frame owner, Factura fac, Cobranza cob) {
		super(owner);
		initialize(fac, cob);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize(Factura fac, Cobranza cob) {
		this.setSize(694, 503);
		//this.setMaximumSize(new Dimension(1024, 696));
		//this.setMinimumSize(new Dimension(0, 0));
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Medios de Pago");
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		this.setResizable(false);
		detallesPago = new Vector<DetallePago>();
		resetCampos();
		setEnabledButtonsToolbar(true, false, false, false, false, true, true, true);
		setVisibleButtonsToolbar(true, false, false, false, false, true, true, true);
		setEnableCampos(false, false);
		factura = fac;
		cobranza = cob;
		renglonDetallePago = new Vector<String>();
		setMedioPagoDefault(fac, cob);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelLblImporteMedPago = new JLabel();
			jLabelLblImporteMedPago.setBounds(new Rectangle(300, 255, 50, 20));
			jLabelLblImporteMedPago.setText("Importe:");
			jLabelLblNroTarjeta = new JLabel();
			jLabelLblNroTarjeta.setBounds(new Rectangle(300, 360, 70, 20));
			jLabelLblNroTarjeta.setText("Nro Tarjeta:");
			jLabelLblCotizacion = new JLabel();
			jLabelLblCotizacion.setBounds(new Rectangle(480, 255, 65, 20));
			jLabelLblCotizacion.setText("Cotización:");
			jLabelLblCotizacion.setVisible(false);
			jLabelLblBancoFechaVen = new JLabel();
			jLabelLblBancoFechaVen.setBounds(new Rectangle(5, 435, 115, 20));
			jLabelLblBancoFechaVen.setText("Fecha Vencimiento:");
			jLabelLblBancoFechaEmision = new JLabel();
			jLabelLblBancoFechaEmision.setBounds(new Rectangle(5, 410, 88, 20));
			jLabelLblBancoFechaEmision.setText("Fecha Emisión:");
			jLabelLblNroDocTitularTarj = new JLabel();
			jLabelLblNroDocTitularTarj.setBounds(new Rectangle(300, 335, 200, 20));
			jLabelLblNroDocTitularTarj.setText("Nro. Documento Titular de Tarjeta:");
			jLabelLblTitularTarjNombre = new JLabel();
			jLabelLblTitularTarjNombre.setBounds(new Rectangle(300, 310, 150, 20));
			jLabelLblTitularTarjNombre.setText("Nombre Titular de Tarjeta:");
			jLabelLblBancoNroSerieCheq = new JLabel();
			jLabelLblBancoNroSerieCheq.setBounds(new Rectangle(5, 360, 100, 20));
			jLabelLblBancoNroSerieCheq.setText("Nro. Serie:");
			jLabelLblBancoNroCompTransf = new JLabel();
			jLabelLblBancoNroCompTransf.setBounds(new Rectangle(5, 385, 165, 20));
			jLabelLblBancoNroCompTransf.setText("Nro. Comp. Transf Bancaria:");
			jLabelLblBancoNroCta = new JLabel();
			jLabelLblBancoNroCta.setBounds(new Rectangle(5, 335, 100, 20));
			jLabelLblBancoNroCta.setText("Nro. de Cuenta:");
			jLabelLblBancoSucur = new JLabel();
			jLabelLblBancoSucur.setBounds(new Rectangle(5, 310, 100, 20));
			jLabelLblBancoSucur.setText("Sucursal Banco:");
			jLabelLblTarjeta = new JLabel();
			jLabelLblTarjeta.setBounds(new Rectangle(300, 285, 50, 20));
			jLabelLblTarjeta.setText("Tarjeta:");
			jLabelLblBanco = new JLabel();
			jLabelLblBanco.setBounds(new Rectangle(5, 285, 50, 20));
			jLabelLblBanco.setText("Banco:");
			jLabelLblMedioPago = new JLabel();
			jLabelLblMedioPago.setBounds(new Rectangle(5, 255, 70, 20));
			jLabelLblMedioPago.setText("Medio de Pago");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJScrollPaneaList(), null);

			jContentPane.add(jLabelLblMedioPago, null);
			jContentPane.add(jLabelLblBanco, null);
			jContentPane.add(jLabelLblTarjeta, null);
			jContentPane.add(jLabelLblBancoSucur, null);
			jContentPane.add(getJTextFieldBancoSucur(), null);
			jContentPane.add(jLabelLblBancoNroCta, null);
			jContentPane.add(getJTextFieldBancoNroCta(), null);
			jContentPane.add(jLabelLblBancoNroCompTransf, null);
			jContentPane.add(getJTextFieldBancoNroCompTransf(), null);
			jContentPane.add(jLabelLblBancoNroSerieCheq, null);
			jContentPane.add(getJTextFieldBancoNroSerieCheq(), null);
			jContentPane.add(jLabelLblTitularTarjNombre, null);
			jContentPane.add(getJTextFieldNomTitularTarjeta(), null);
			jContentPane.add(jLabelLblNroDocTitularTarj, null);
			jContentPane.add(getJTextFieldNroDocTitularTarjeta(), null);
			jContentPane.add(jLabelLblBancoFechaEmision, null);
			jContentPane.add(getJTextFieldFechaEmision(), null);
			jContentPane.add(jLabelLblBancoFechaVen, null);
			jContentPane.add(getJTextFieldFechaVen(), null);
			jContentPane.add(jLabelLblCotizacion, null);
			jContentPane.add(getJTextFieldCotizacion(), null);
			jContentPane.add(jLabelLblNroTarjeta, null);
			jContentPane.add(getJTextFieldNroTarjeta(), null);
			jContentPane.add(jLabelLblImporteMedPago, null);
			jContentPane.add(getJTextFieldImporteMedPago(), null);
			jContentPane.add(getJComboBoxTarjeta(), null);
			jContentPane.add(getJComboBoxBanco(), null);
			jContentPane.add(getJComboBoxMedPago(), null);
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
			jScrollPaneaList.setBounds(new Rectangle(5, 40, 680, 211));
			jScrollPaneaList.setViewportView(getJtableDetallePagos());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJtableDetallePagos() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableDetallePagos == null) {
			jTableDetallePagos = new MyJtable(getMyTableModel());
			jTableDetallePagos.setRowSorter(geTableRowSorter());
			jTableDetallePagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableDetallePagos.setRowHeight(20);
			jTableDetallePagos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableDetallePagos.setAutoscrolls(true);
			jTableDetallePagos.setShowVerticalLines(true);
			jTableDetallePagos.setShowHorizontalLines(true);
			jTableDetallePagos.setVisible(true);
			jTableDetallePagos.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableDetallePagos.isEnabled()){
						detallePago = getSelectedElement();
						if(detallePago != null){
							//setDatosDetallePago(detallePago);
							setEnabledButtonsToolbar(true, true, true, false, false, true, true, true);//SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
							setVisibleButtonsToolbar(true, true, true, false, false, true, true, true);
						}
					}
				}
			});
			jTableDetallePagos.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(jTableDetallePagos.isEnabled()){
						if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
							detallePago = getSelectedElement();
							if(detallePago != null){
								//setDatosDetallePago(detallePago);
								setEnabledButtonsToolbar(true, true, true, false, false, true, true, true);//SETEA EL ENABLE DE LOS BOTONES CUANDO SE SELECCIONA UN ITEM DE LA TABLA
								setVisibleButtonsToolbar(true, true, true, false, false, true, true, true);
							}
						}
					}
				}
				public void keyTyped(java.awt.event.KeyEvent e) {
				}
				public void keyReleased(java.awt.event.KeyEvent e) {

				}
			});
		}
		return jTableDetallePagos;
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

	/**
	 * This method initializes jToolBar
	 *
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 1011, 34));
			jToolBar.setFloatable(false);
			jToolBar.add(getJButtonActionAlta());
			jToolBar.add(getJButtonActionModificacion());
			jToolBar.add(getJButtonActionEliminar());
			jToolBar.add(getJButtonActionOk());
			jToolBar.add(getJButtonActionCancel());
			jToolBar.add(getJButtonaActionCloce());
			jToolBar.add(getJButtonActionGrabarPago());
			jToolBar.add(getJButtonActionCancelarPago());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jButtonActionAlta
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionAlta() {
		if (jButtonActionAlta == null) {
			jButtonActionAlta = new JButton();
			jButtonActionAlta.setPreferredSize(new Dimension(100, 25));
			jButtonActionAlta.setIcon(new ImageIcon(getClass().getResource("/Symbol-Add_24x24-32.png")));
			jButtonActionAlta.setText("Nuevo");
			jButtonActionAlta.setBorderPainted(false);
			jButtonActionAlta.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					detallePago = new DetallePago();
					detallePago.setIndice(indiceRenglonMedioPago);
					indiceRenglonMedioPago++;
					setEnabledButtonsToolbar(false, false, false, true, true, true, false, false);
					setVisibleButtonsToolbar(true, false, false, true, true, true, false, false);
					jTableDetallePagos.setEnabled(false);
					jComboBoxMedPago.requestFocus();
					activeActionType = "ADD";
				}
			});
		}
		return jButtonActionAlta;
	}

	/**
	 * This method initializes jButtonActionModificacion
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionModificacion() {
		if (jButtonActionModificacion == null) {
			jButtonActionModificacion = new JButton();
			jButtonActionModificacion.setPreferredSize(new Dimension(100, 25));
			jButtonActionModificacion.setIcon(new ImageIcon(getClass().getResource("/Edit_24x24-32.png")));
			jButtonActionModificacion.setText("Modificar");
			jButtonActionModificacion.setBorderPainted(false);
			jButtonActionModificacion
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
							if(detallePago != null){
								activeActionType = "UPDATE";
								setDatosDetallePago(detallePago);
								setEnabledButtonsToolbar(false, false, false, true, true, true, false, false);
								setVisibleButtonsToolbar(false, false, false, true, true, true, false, false);
								jTableDetallePagos.setEnabled(false);
							}
						}
					});
		}
		return jButtonActionModificacion;
	}

	/**
	 * This method initializes jButtonActionEliminar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionEliminar() {
		if (jButtonActionEliminar == null) {
			jButtonActionEliminar = new JButton();
			jButtonActionEliminar.setPreferredSize(new Dimension(100, 25));
			jButtonActionEliminar.setIcon(new ImageIcon(getClass().getResource("/Cut_24x24-32.png")));
			jButtonActionEliminar.setText("Eliminar");
			jButtonActionEliminar.setBorderPainted(false);
			jButtonActionEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(detallePago != null){
						detallesPago.remove(detallePago);
						addDetallePagoMyTableModel(detallesPago);
						setEnabledButtonsToolbar(true, false, false, false, false, true, true, true);
						setVisibleButtonsToolbar(true, false, false, false, false, true, true, true);
						resetCampos();
					}
				}
			});
		}
		return jButtonActionEliminar;
	}

	/**
	 * This method initializes jButtonActionOk
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionOk() {
		if (jButtonActionOk == null) {
			jButtonActionOk = new JButton();
			jButtonActionOk.setPreferredSize(new Dimension(100, 25));
			jButtonActionOk.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonActionOk.setText("OK");
			jButtonActionOk.setBorderPainted(false);
			jButtonActionOk.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(activeActionType.equals("ADD")){
						if(jComboBoxMedPago.getSelectedItem() != null && !(jTextFieldImporteMedPago.getText().isEmpty()) && !(jTextFieldImporteMedPago.getText().equals(""))){
						setDetallePago();
						addDetallePagoInDetallesPagos(detallePago);
						if(validaTotalMediosPagos(detallesPago)){
							addDetallePagoMyTableModel(detallesPago);
							setEnabledButtonsToolbar(true, false, false, false, false, true, true, true);
							setVisibleButtonsToolbar(true, false, false, false, false, true, true, true);
							jTableDetallePagos.setEnabled(true);
							resetCampos();
							setEnableCampos(false, false);
							jComboBoxMedPago.requestFocus();
						}else{
							JOptionPane.showMessageDialog(null, "El importe de los medios de pagos no puede superar\n " +
									"el importe a abonar de la Factura", "Superó el importe a pagar", JOptionPane.ERROR_MESSAGE);
							detallesPago.remove(detallePago);
							jTextFieldImporteMedPago.requestFocus();
						}

						}else{
							JOptionPane.showMessageDialog(null, "Debe completar los campos indispensables", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
							jTextFieldImporteMedPago.requestFocus();
						}
					}else if(activeActionType.equals("UPDATE")){
						if(jComboBoxMedPago.getSelectedItem() != null && !(jTextFieldImporteMedPago.getText().isEmpty()) && !(jTextFieldImporteMedPago.getText().equals(""))){
							setDetallePago();
							updateDetallePagoInDetallesPagos(detallePago);
							if(validaTotalMediosPagos(detallesPago)){
								addDetallePagoMyTableModel(detallesPago);
								setEnabledButtonsToolbar(true, false, false, false, false, true, true, true);
								setVisibleButtonsToolbar(true, false, false, false, false, true, true, true);
								jTableDetallePagos.setEnabled(true);
								resetCampos();
								setEnableCampos(false, false);
								jComboBoxMedPago.requestFocus();
							}else{
								JOptionPane.showMessageDialog(null, "El importe de los medios de pagos no puede superar\n " +
										"el importe a abonar de la Factura", "Superó el importe a pagar", JOptionPane.ERROR_MESSAGE);
								jTextFieldImporteMedPago.requestFocus();
							}
						}else{
							JOptionPane.showMessageDialog(null, "Debe completar los campos indispensables", "Campos Incompletos", JOptionPane.ERROR_MESSAGE);
							jTextFieldImporteMedPago.requestFocus();
						}
					}else if(activeActionType.equals("DELETE")){

					}else if(activeActionType.equals(null)){

					}
				}
			});
		}
		return jButtonActionOk;
	}

	/**
	 * This method initializes jButtonActionCancel
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionCancel() {
		if (jButtonActionCancel == null) {
			jButtonActionCancel = new JButton();
			jButtonActionCancel.setPreferredSize(new Dimension(100, 25));
			jButtonActionCancel.setIcon(new ImageIcon(getClass().getResource("/Symbol-Stop_24x24-32.png")));
			jButtonActionCancel.setText("Cancelar");
			jButtonActionCancel.setBorderPainted(false);
			jButtonActionCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					setEnabledButtonsToolbar(true, false, false, false, false, true, true, true);
					setVisibleButtonsToolbar(true, false, false, false, false, true, true, true);
					jTableDetallePagos.setEnabled(true);
					resetCampos();
				}
			});
		}
		return jButtonActionCancel;
	}

	/**
	 * This method initializes jButtonaActionCloce
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonaActionCloce() {
		if (jButtonaActionCloce == null) {
			jButtonaActionCloce = new JButton();
			jButtonaActionCloce.setPreferredSize(new Dimension(100, 30));
			jButtonaActionCloce.setIcon(new ImageIcon(getClass().getResource("/Symbol-Delete_24x24-32.png")));
			jButtonaActionCloce.setText("Cerrar");
			jButtonaActionCloce.setBorderPainted(false);
			jButtonaActionCloce.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					dispose();
				}
			});
		}
		return jButtonaActionCloce;
	}


	/************* GESTION DE COMBO BOX************/


	/**
	 * This method initializes jComboBoxMedPago
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxMedPago() {
		if (jComboBoxMedPago == null) {
			jComboBoxMedPago = new JComboBox();
			jComboBoxMedPago.setBounds(new Rectangle(85, 255, 180, 20));
			jComboBoxMedPago.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(jComboBoxMedPago.getComponentCount() >= 0){
						if(((MedioPago)jComboBoxMedPago.getSelectedItem()).getCodigo() == 2){
							setEnableCampos(true, false);
						}else if(((MedioPago)jComboBoxMedPago.getSelectedItem()).getCodigo() == 3){
							setEnableCampos(false, true);
						}else{
							setEnableCampos(false, false);
						}
					}
				}
			});
		}
		setItemjComboBoxMedioPago();
		return jComboBoxMedPago;
	}

	/**
	 * This method initializes jComboBoxBanco
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxBanco() {
		if (jComboBoxBanco == null) {
			jComboBoxBanco = new JComboBox();
			jComboBoxBanco.setBounds(new Rectangle(85, 285, 180, 20));
		}
		setItemjComboBoxBanco();
		return jComboBoxBanco;
	}

	/**
	 * This method initializes jComboBoxTarjeta
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxTarjeta() {
		if (jComboBoxTarjeta == null) {
			jComboBoxTarjeta = new JComboBox();
			jComboBoxTarjeta.setBounds(new Rectangle(360, 285, 180, 20));
		}
		setItemjComboBoxTarjeta();
		return jComboBoxTarjeta;
	}

	private void setItemjComboBoxMedioPago(){
		jComboBoxMedPago.removeAllItems();
		ArrayList<MedioPago> mediosPagos = medioPagoLogica.getListMedioPago();
		Iterator<MedioPago> it = mediosPagos.iterator();
		while(it.hasNext()){
			jComboBoxMedPago.addItem((MedioPago)it.next());
		}
		MedioPago medPago = new MedioPago();
		medPago.setCodigo(1);
		selectItemjComboBoxMedPago(medPago);
	}

	private void setItemjComboBoxBanco (){
		jComboBoxBanco.removeAllItems();
		ArrayList<Banco> bancos = bancoLogica.getListBancos();
		Iterator<Banco> it = bancos.iterator();
		while(it.hasNext()){
			jComboBoxBanco.addItem((Banco)it.next());
		}
	}

	private void setItemjComboBoxTarjeta (){
		jComboBoxTarjeta.removeAllItems();
		ArrayList<Tarjeta> tarjetas = tarjetaLogica.getListTarjeta();
		Iterator<Tarjeta> it = tarjetas.iterator();
		while(it.hasNext()){
			jComboBoxTarjeta.addItem((Tarjeta)it.next());
		}
	}

	private void selectItemjComboBoxMedPago(MedioPago medPago){
		if(medPago != null && jComboBoxMedPago.isVisible()){
			for(int i = 0; i < jComboBoxMedPago.getItemCount(); i++){
				MedioPago medioPago = (MedioPago)jComboBoxMedPago.getItemAt(i);
				if(medPago.getCodigo() == medioPago.getCodigo()){
					jComboBoxMedPago.setSelectedItem(medioPago);
					break;
				}
			}
		}else{
			jComboBoxMedPago.setSelectedItem(null);
		}

	}

	private void selectItemjComboBoxBanco(Banco b){
		if(b != null && jComboBoxBanco.isVisible()){
			for(int i = 0; i < jComboBoxBanco.getItemCount(); i++){
				Banco banco = (Banco)jComboBoxBanco.getItemAt(i);
				if(b.getCodigo() == banco.getCodigo()){
					jComboBoxBanco.setSelectedItem(banco);
					break;
				}
			}
		}else{
			jComboBoxBanco.setSelectedItem(null);
		}

	}

	private void selectItemjComboBoxTarjeta(Tarjeta t){
		if(t != null && jComboBoxTarjeta.isVisible()){
			for(int i = 0; i < jComboBoxTarjeta.getItemCount(); i++){
				Tarjeta tarjeta= (Tarjeta)jComboBoxTarjeta.getItemAt(i);
				if(t.getCodigo() == tarjeta.getCodigo()){
					jComboBoxTarjeta.setSelectedItem(tarjeta);
					break;
				}
			}
		}else{
			jComboBoxTarjeta.setSelectedItem(null);
		}

	}

	/**********************************************************/


	/**
	 * This method initializes jTextFieldBancoSucur
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBancoSucur() {
		if (jTextFieldBancoSucur == null) {
			jTextFieldBancoSucur = new JTextField();
			jTextFieldBancoSucur.setBounds(new Rectangle(110, 310, 170, 20));
			jTextFieldBancoSucur.setDocument(new MyPlainDocument(getJTextFieldBancoSucur(), 50, null, true));
			jTextFieldBancoSucur.setText("Nombre");
		}
		return jTextFieldBancoSucur;
	}

	/**
	 * This method initializes jTextFieldBancoNroCta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBancoNroCta() {
		if (jTextFieldBancoNroCta == null) {
			jTextFieldBancoNroCta = new JTextField();
			jTextFieldBancoNroCta.setBounds(new Rectangle(110, 335, 170, 20));
			jTextFieldBancoNroCta.setDocument(new MyPlainDocument(getJTextFieldBancoNroCta(), 50, null, true));
			jTextFieldBancoNroCta.setText("Nombre");
		}
		return jTextFieldBancoNroCta;
	}

	/**
	 * This method initializes jTextFieldBancoNroCompTransf
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBancoNroCompTransf() {
		if (jTextFieldBancoNroCompTransf == null) {
			jTextFieldBancoNroCompTransf = new JTextField();
			jTextFieldBancoNroCompTransf.setBounds(new Rectangle(180, 385, 170, 20));
			jTextFieldBancoNroCompTransf.setDocument(new MyPlainDocument(getJTextFieldBancoNroCompTransf(), 50, null, true));
			jTextFieldBancoNroCompTransf.setText("Nombre");
		}
		return jTextFieldBancoNroCompTransf;
	}

	/**
	 * This method initializes jTextFieldBancoNroSerieCheq
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldBancoNroSerieCheq() {
		if (jTextFieldBancoNroSerieCheq == null) {
			jTextFieldBancoNroSerieCheq = new JTextField();
			jTextFieldBancoNroSerieCheq.setBounds(new Rectangle(110, 360, 170, 20));
			jTextFieldBancoNroSerieCheq.setDocument(new MyPlainDocument(getJTextFieldBancoNroSerieCheq(), 50, null, true));
			jTextFieldBancoNroSerieCheq.setText("Nombre");
		}
		return jTextFieldBancoNroSerieCheq;
	}

	/**
	 * This method initializes jTextFieldNomTitularTarjeta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNomTitularTarjeta() {
		if (jTextFieldNomTitularTarjeta == null) {
			jTextFieldNomTitularTarjeta = new JTextField();
			jTextFieldNomTitularTarjeta.setBounds(new Rectangle(505, 310, 170, 20));
			jTextFieldNomTitularTarjeta.setDocument(new MyPlainDocument(getJTextFieldNomTitularTarjeta(), 50, null, true));
			jTextFieldNomTitularTarjeta.setText("Nombre");
		}
		return jTextFieldNomTitularTarjeta;
	}

	/**
	 * This method initializes jTextFieldNroDocTitularTarjeta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNroDocTitularTarjeta() {
		if (jTextFieldNroDocTitularTarjeta == null) {
			jTextFieldNroDocTitularTarjeta = new JTextField();
			jTextFieldNroDocTitularTarjeta.setBounds(new Rectangle(505, 335, 170, 20));
			jTextFieldNroDocTitularTarjeta.setDocument(new MyPlainDocument(getJTextFieldNroDocTitularTarjeta(), 50, null, true));
			jTextFieldNroDocTitularTarjeta.setText("Nombre");
		}
		return jTextFieldNroDocTitularTarjeta;
	}

	/**
	 * This method initializes maskFormatterFechaEmision
	 *
	 * @return javax.swing.text.MaskFormatter
	 */
	private MaskFormatter getMaskFormatterFechaEmision() {
		if (maskFormatterFechaEmision == null) {
			try {
				maskFormatterFechaEmision = new MaskFormatter("##/##/####");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maskFormatterFechaEmision.setPlaceholderCharacter('_');
		}
		return maskFormatterFechaEmision;
	}

	/**
	 * This method initializes jTextFieldFechaEmision
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getJTextFieldFechaEmision() {
		if (jTextFieldFechaEmision == null) {
			jTextFieldFechaEmision = new JFormattedTextField(
					getMaskFormatterFechaEmision());
			jTextFieldFechaEmision.setBounds(new Rectangle(125, 410, 67, 20));
		}
		return jTextFieldFechaEmision;
	}

	/**
	 * This method initializes maskFormatterFechaVen
	 *
	 * @return javax.swing.text.MaskFormatter
	 */
	private MaskFormatter getMaskFormatterFechaVen() {
		if (maskFormatterFechaVen == null) {
			try {
				maskFormatterFechaVen = new MaskFormatter("##/##/####");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			maskFormatterFechaVen.setPlaceholderCharacter('_');
		}
		return maskFormatterFechaVen;
	}

	/**
	 * This method initializes jTextFieldFechaVen
	 *
	 * @return javax.swing.JFormattedTextField
	 */
	private JFormattedTextField getJTextFieldFechaVen() {
		if (jTextFieldFechaVen == null) {
			jTextFieldFechaVen = new JFormattedTextField(getMaskFormatterFechaVen());
			jTextFieldFechaVen.setBounds(new Rectangle(125, 435, 67, 20));
		}
		return jTextFieldFechaVen;
	}

	/**
	 * This method initializes jTextFieldCotizacion
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCotizacion() {
		if (jTextFieldCotizacion == null) {
			jTextFieldCotizacion = new JTextField();
			jTextFieldCotizacion.setBounds(new Rectangle(552, 255, 122, 20));
			jTextFieldCotizacion.setDocument(new MyPlainDocument(getJTextFieldCotizacion(), 50, null, true));
			jTextFieldCotizacion.setText("Nombre");
		}
		return jTextFieldCotizacion;
	}

	/**
	 * This method initializes jTextFieldNroTarjeta
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNroTarjeta() {
		if (jTextFieldNroTarjeta == null) {
			jTextFieldNroTarjeta = new JTextField();
			jTextFieldNroTarjeta.setBounds(new Rectangle(380, 360, 170, 20));
			jTextFieldNroTarjeta.setDocument(new MyPlainDocument(getJTextFieldNroTarjeta(), 50, null, true));
			jTextFieldNroTarjeta.setText("Nombre");
		}
		return jTextFieldNroTarjeta;
	}

	/**
	 * This method initializes jTextFieldImporteMedPago
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldImporteMedPago() {
		if (jTextFieldImporteMedPago == null) {
			jTextFieldImporteMedPago = new JTextField();
			jTextFieldImporteMedPago.setBounds(new Rectangle(360, 255, 80, 20));
			jTextFieldImporteMedPago.setDocument(new MyPlainDocument(getJTextFieldImporteMedPago(), 50, null, true));
			jTextFieldImporteMedPago.setText("Nombre");
			jTextFieldImporteMedPago.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
					if(!jTextFieldImporteMedPago.getText().isEmpty()) jTextFieldImporteMedPago.setText(getValueDecimalVisual(Float.valueOf(jTextFieldImporteMedPago.getText())));
				}
			});
		}
		return jTextFieldImporteMedPago;
	}


	/**
	 * This method initializes jButtonActionGrabarPago
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionGrabarPago() {
		if (jButtonActionGrabarPago == null) {
			jButtonActionGrabarPago = new JButton();
			jButtonActionGrabarPago.setPreferredSize(new Dimension(100, 25));
			jButtonActionGrabarPago.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonActionGrabarPago.setText("Grabar Pago");
			jButtonActionGrabarPago.setBorderPainted(false);
			jButtonActionGrabarPago.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(detallesPago != null){
						if(!detallesPago.isEmpty()){
							if(validaTotalFinal(detallesPago)){
								detallesPagosFinal = detallesPago;
								dispose();
							}else{
								JOptionPane.showMessageDialog(null, "La suma de los medios de pago debe ser igual al monto a pagar de la factura", "Verificar los importes", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			});
		}
		return jButtonActionGrabarPago;
	}

	/**
	 * This method initializes jButtonActionCancelarPago
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionCancelarPago() {
		if (jButtonActionCancelarPago == null) {
			jButtonActionCancelarPago = new JButton();
			jButtonActionCancelarPago.setPreferredSize(new Dimension(100, 25));
			jButtonActionCancelarPago.setIcon(new ImageIcon(getClass().getResource("/Symbol-Stop_24x24-32.png")));
			jButtonActionCancelarPago.setText("Cancelar Pago");
			jButtonActionCancelarPago.setBorderPainted(false);
			jButtonActionCancelarPago
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
							dispose();
						}
					});
		}
		return jButtonActionCancelarPago;
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







	/************** GESTION DE ITEMS DE LA TABLA *******************/

	private Vector<String> setTitlesTableModel(){
		Vector<String> titulos = new Vector<String>();
		titulos.add("Índice");
		titulos.add("Código");
		titulos.add("Descripción");
		titulos.add("Importe");
		return titulos;
	}

	public void setItemsInJtable(){
		//ArrayList<Vendedor> vendedors = vendedorLogica.getListVendedor();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Índice");
		titulos.add("Código");
		titulos.add("Descripción");
		titulos.add("Importe");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		//Vector<String> renglon = null;
		/*if(vendedors != null){
			Iterator<Vendedor> it = vendedors.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				vendedor = (Vendedor)it.next();
				renglon.add(String.valueOf(vendedor.getCodigo()));
				renglon.add(vendedor.getNombre());
				renglon.add(vendedor.getApellido());
				renglon.add(vendedor.getEmail());
				registros.add(renglon);
			}
		}*/
		myTableModel.setDataVector(registros, titulos);
		//setea ancho de campos
		jTableDetallePagos.getColumnModel().getColumn(0).setPreferredWidth(0);
		jTableDetallePagos.getColumnModel().getColumn(1).setPreferredWidth(8);
		jTableDetallePagos.getColumnModel().getColumn(2).setPreferredWidth(300);
		jTableDetallePagos.getColumnModel().getColumn(3).setPreferredWidth(10);
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	@SuppressWarnings("unchecked")
	private DetallePago getSelectedElement(){
		try{
			renglonDetallePago = (Vector<String>)myTableModel.getDataVector().elementAt(tableRowSorter.convertRowIndexToModel(jTableDetallePagos.getSelectedRow()));
			//int indice = Integer.parseInt((String)myTableModel.getValueAt(tableRowSorter.convertRowIndexToModel(jTableDetallePagos.getSelectedRow()), 0));
			return getDetallePago(renglonDetallePago);
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}

	private DetallePago getDetallePago(Vector<String> selectRenglon){
		DetallePago detaPago = null;
		if(detallesPago != null){
			Iterator<DetallePago> itDetallesPago = detallesPago.iterator();
			while(itDetallesPago.hasNext()){
				detaPago = (DetallePago)itDetallesPago.next();
				if(detaPago.getIndice() == Integer.parseInt(selectRenglon.get(0))){
					break;
				}
			}
		}
		return detaPago;
	}


	private Vector<Vector<String>> getVectorRenglonesDetallePago(Vector<DetallePago> detallesPagos){
		Vector<Vector<String>> renglonesDetallesPago = new Vector<Vector<String>>();
		Vector<String> renglonDetallePago = null;
		DetallePago detaPago = null;

		Iterator<DetallePago> itDetallesPago = detallesPagos.iterator();
		while(itDetallesPago.hasNext()){
			renglonDetallePago = new Vector<String>();
			detaPago = (DetallePago)itDetallesPago.next();
			renglonDetallePago.add(String.valueOf(detaPago.getIndice()));
			renglonDetallePago.add(String.valueOf(detaPago.getMedioPago().getCodigo()));
			renglonDetallePago.add(detaPago.getMedioPago().getDescripcion());
			renglonDetallePago.add("$ "+getValueDecimalVisual(detaPago.getImporte()));
			renglonesDetallesPago.add(renglonDetallePago);
		}
		return renglonesDetallesPago;
	}


	/*private void recalculaFactura(){
		if(factura != null && factura.getItemsFactura() != null){
			setFactura();
			factura = CalculationEngine.calculaFactura(factura);
			setDatosFactura(factura);
		}
	}*/

	private void setDatosDetallePago(DetallePago detPago){
		if(detPago != null){
			selectItemjComboBoxMedPago(detPago.getMedioPago());
			selectItemjComboBoxBanco(detPago.getBanco());
			selectItemjComboBoxTarjeta(detPago.getTarjeta());

			jTextFieldBancoSucur.setText(detPago.getSucursalBanco());
			jTextFieldBancoNroCta.setText(detPago.getNroCuenta());
			jTextFieldBancoNroCompTransf.setText(detPago.getNroComprobante());
			jTextFieldBancoNroSerieCheq.setText(detPago.getNroSerie());
			try {
				if(!detPago.getFechaEmicion().isEmpty())jTextFieldFechaEmision.setText(dateFormatArgen.format(dateFormatJapan.parse(detPago.getFechaEmicion())));
				if(!detPago.getFechaVencimiento().isEmpty())jTextFieldFechaVen.setText(dateFormatArgen.format(dateFormatJapan.parse(detPago.getFechaVencimiento())));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jTextFieldCotizacion.setText(getValueDecimalVisual(detPago.getCotizacion()));
			jTextFieldNomTitularTarjeta.setText(detPago.getTitularNombre());
			jTextFieldNroDocTitularTarjeta.setText(detPago.getTitularNroDoc());
			jTextFieldNroTarjeta.setText(detPago.getNroTarjeta());
			jTextFieldImporteMedPago.setText(getValueDecimalVisual(detPago.getImporte()));
			if(detPago.getMedioPago().getCodigo() == 2){
				setEnableCampos(true, false);
			}else if(detPago.getMedioPago().getCodigo() == 3){
				setEnableCampos(false, true);
			}
		}
	}

	private void setDetallePago(){
		if(factura != null || cobranza != null){
			/*if(detallePago == null){
				detallePago = new DetallePago();
				detallePago.setIndice(indiceRenglonMedioPago);
				indiceRenglonMedioPago ++;
			}*/
			if(jComboBoxBanco.isEditable()){
				detallePago.setBanco((Banco)jComboBoxBanco.getSelectedItem());
			}else{
				detallePago.setBanco(null);
			}
			detallePago.setCodLocalidad(null);
			if(!(jTextFieldCotizacion.getText().isEmpty()) && !(jTextFieldCotizacion.getText().equals(""))){
				detallePago.setCotizacion(getValueDecimalReal(jTextFieldCotizacion.getText()));
			}else{
				detallePago.setCotizacion(Float.valueOf(0));
			}
			if(factura != null){
				detallePago.setFactura(factura);
				detallePago.setCobranza(null);
			}else if(cobranza != null){
				detallePago.setFactura(null);
				detallePago.setCobranza(cobranza);
			}
			try {
				if(!(jTextFieldFechaVen.getText().equals("__/__/____")) && !(jTextFieldFechaVen.getText().isEmpty())){
					detallePago.setFechaVencimiento(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaVen.getText())));
				}else{
					detallePago.setFechaVencimiento("");
				}
				if(!(jTextFieldFechaEmision.getText().equals("__/__/____")) && !(jTextFieldFechaEmision.getText().isEmpty())){
					detallePago.setFechaEmicion(dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaEmision.getText())));
				}else{
					detallePago.setFechaEmicion("");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			detallePago.setImporte(getValueDecimalReal(jTextFieldImporteMedPago.getText()));
			detallePago.setMedioPago((MedioPago)jComboBoxMedPago.getSelectedItem());
			detallePago.setNroComprobante(jTextFieldBancoNroCompTransf.getText());
			detallePago.setNroCuenta(jTextFieldBancoNroCta.getText());
			detallePago.setNroSerie(jTextFieldBancoNroSerieCheq.getText());
			detallePago.setNroTarjeta(jTextFieldNroTarjeta.getText());
			detallePago.setSucursalBanco(jTextFieldBancoSucur.getText());
			if(jComboBoxTarjeta.isEditable()){
				detallePago.setTarjeta((Tarjeta)jComboBoxTarjeta.getSelectedItem());
			}else{
				detallePago.setTarjeta(null);
			}
			detallePago.setTitularNombre(jTextFieldNomTitularTarjeta.getText());
			detallePago.setTitularNroDoc(jTextFieldNroDocTitularTarjeta.getText());
		}
	}

	public void setMedioPagoDefault(Factura fac, Cobranza cob){
		MedioPago medPago = medioPagoLogica.getMedioPago(1);
		DetallePago detPago = new DetallePago();
		detPago.setIndice(indiceRenglonMedioPago);
		indiceRenglonMedioPago ++;
		detPago.setFechaEmicion("");
		detPago.setFechaVencimiento("");
		detPago.setBanco(null);
		detPago.setTarjeta(null);
		detPago.setCotizacion(Float.valueOf(0));
		if(fac != null){
			detPago.setFactura(fac);
			detPago.setImporte(fac.getTotalFinal());
			detPago.setCobranza(null);
		}else if(cob != null){
			detPago.setFactura(null);
			detPago.setCobranza(cob);
			detPago.setImporte(cob.getMontoTotalImputado());
		}
		detPago.setMedioPago(medPago);
		addDetallePagoInDetallesPagos(detPago);
		addDetallePagoMyTableModel(detallesPago);
	}

	public void addDetallePagoMyTableModel(Vector<DetallePago> detPago){
		if(detPago != null){
			//itemsFactura.add(itemFac);
			myTableModel.setDataVector(getVectorRenglonesDetallePago(detPago), setTitlesTableModel());
			jTableDetallePagos.getColumnModel().getColumn(0).setPreferredWidth(0);
			jTableDetallePagos.getColumnModel().getColumn(1).setPreferredWidth(8);
			jTableDetallePagos.getColumnModel().getColumn(2).setPreferredWidth(300);
			jTableDetallePagos.getColumnModel().getColumn(3).setPreferredWidth(10);

			setOcultarColumnasJTable(jTableDetallePagos, new int[]{0});
		}else{
			myTableModel.setDataVector(null, setTitlesTableModel());
			jTableDetallePagos.getColumnModel().getColumn(0).setPreferredWidth(0);
			jTableDetallePagos.getColumnModel().getColumn(1).setPreferredWidth(8);
			jTableDetallePagos.getColumnModel().getColumn(2).setPreferredWidth(300);
			jTableDetallePagos.getColumnModel().getColumn(3).setPreferredWidth(10);

			setOcultarColumnasJTable(jTableDetallePagos, new int[]{0});
		}
	}

	private void addDetallePagoInDetallesPagos(DetallePago detallePago){
		if(detallePago.getMedioPago() != null && detallePago.getImporte() != 0 && detallePago.getImporte() != null){
			detallesPago.add(detallePago);
			//setEnabledButtonsToolbar(true, true, true, true);
		}
	}

	private void updateDetallePagoInDetallesPagos(DetallePago detallePago){
		if(detallePago.getMedioPago() != null && detallePago.getImporte() != 0 && detallePago.getImporte() != null){
			Iterator<DetallePago> itDetPagos = detallesPago.iterator();
			while(itDetPagos.hasNext()){
				DetallePago dp = (DetallePago)itDetPagos.next();
				if(dp.getIndice() == detallePago.getIndice()){
					itDetPagos.remove();
					break;
				}
			}
			detallesPago.add(detallePago);
		}
	}


	/********** ACTIONES CON BUTTONS DE TOOLBAR ****************/

	/*habilita o desabilita los botones de la toolbar*/
	public void setEnabledButtonsToolbar(boolean btnNuevo, boolean btnModificar, boolean btnEliminar, boolean btnOk, boolean btnCancel, boolean btnCloce, boolean btnGrabarPago, boolean btnCancelarPago){
		jButtonActionAlta.setEnabled(btnNuevo);
		jButtonActionEliminar.setEnabled(btnEliminar);
		jButtonActionModificacion.setEnabled(btnModificar);
		jButtonActionOk.setEnabled(btnOk);
		jButtonActionCancel.setEnabled(btnCancel);
		jButtonaActionCloce.setEnabled(btnCloce);
		jButtonActionGrabarPago.setEnabled(btnGrabarPago);
		jButtonActionCancelarPago.setEnabled(btnCancelarPago);
	}

	/*muestra o oculta los botonoes de la toolbar*/
	public void setVisibleButtonsToolbar(boolean btnNuevo, boolean btnModificar, boolean btnEliminar, boolean btnOk, boolean btnCancel, boolean btnCloce, boolean btnGrabarPago, boolean btnCancelarPago){
		jButtonActionAlta.setVisible(btnNuevo);
		jButtonActionEliminar.setVisible(btnEliminar);
		jButtonActionModificacion.setVisible(btnModificar);
		jButtonActionOk.setVisible(btnOk);
		jButtonActionCancel.setVisible(btnCancel);
		jButtonaActionCloce.setVisible(btnCloce);
		jButtonActionGrabarPago.setVisible(btnGrabarPago);
		jButtonActionCancelarPago.setVisible(btnCancelarPago);
	}


	public void setEnableCampos(boolean cposBcoCheque, boolean cposTarj){
		jComboBoxMedPago.setEditable(true);
		jTextFieldImporteMedPago.setEditable(true);
		jTextFieldCotizacion.setVisible(false);
		jTextFieldBancoNroCompTransf.setVisible(false);

		jTextFieldBancoSucur.setEditable(cposBcoCheque);
		jTextFieldBancoNroCta.setEditable(cposBcoCheque);
		jTextFieldBancoNroSerieCheq.setEditable(cposBcoCheque);
		jTextFieldFechaEmision.setEditable(cposBcoCheque);
		jTextFieldFechaVen.setEditable(cposBcoCheque);

		jTextFieldNomTitularTarjeta.setEditable(cposTarj);
		jTextFieldNroDocTitularTarjeta.setEditable(cposTarj);
		jTextFieldNroTarjeta.setEditable(cposTarj);
	}

	private boolean validaTotalMediosPagos(Vector<DetallePago> dPagos){
		Float importeMediosPagos = Float.valueOf(0);
		Iterator<DetallePago> itDetPa = dPagos.iterator();
		while(itDetPa.hasNext()){
			DetallePago dp = (DetallePago)itDetPa.next();
			importeMediosPagos +=  dp.getImporte();
		}
		Float c = Float.valueOf(0);
		Float a = Float.valueOf(decimalFormat.format(importeMediosPagos.doubleValue()));
		if(factura != null)c = Float.valueOf(decimalFormat.format(factura.getTotalFinal().doubleValue()));
		if(cobranza != null)c = Float.valueOf(decimalFormat.format(cobranza.getMontoTotalImputado().doubleValue()));
		if(a > c){
			return false;
		}else{
			return true;
		}
	}
	private boolean validaTotalFinal(Vector<DetallePago> dPagos){
		Float importeMediosPagos = Float.valueOf(0);
		Iterator<DetallePago> itDetPa = dPagos.iterator();
		while(itDetPa.hasNext()){
			DetallePago dp = (DetallePago)itDetPa.next();
			importeMediosPagos +=  dp.getImporte();
		}
		Float c = Float.valueOf(0);
		//Float a = factura.getTotalFinal();
		//Float b = getValueDecimalReal(String.valueOf(factura.getTotalFinal()));
		Float a = Float.valueOf(decimalFormat.format(importeMediosPagos.doubleValue()));
		if(factura != null)c = Float.valueOf(decimalFormat.format(factura.getTotalFinal().doubleValue()));
		if(cobranza != null)c = Float.valueOf(decimalFormat.format(cobranza.getMontoTotalImputado().doubleValue()));
		int i = a.compareTo(c);
		if(i == 0){
			return true;
		}else{
			return false;
		}
	}

	/*private boolean validaMedioPagoDuplicado(DetallePago detPago){
		Iterator<DetallePago> itDetPa = detallesPago.iterator();
		boolean r = true;
		while(itDetPa.hasNext()){
			DetallePago dp = (DetallePago)itDetPa.next();
			if(dp.getMedioPago().getCodigo() == detPago.getMedioPago().getCodigo()){
				r = false;
				break;
			}
		}
		return r;
	}*/


	/*resetea todos los campos*/
	private void resetCampos(){
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
		//setFilterInTabla("", 0);
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

	public Vector<DetallePago> getDetallesPagos(){
		return detallesPagosFinal;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"

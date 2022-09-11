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
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import logica.CobranzaLogica;
import logica.NroCorrelativoLogica;
import presentacion.WinPrincipal;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyTableModel;

import dominio.Cliente;
import dominio.Cobranza;
import dominio.ComprobanteCtaCte;
import dominio.DetallePago;
import dominio.DocumentoImputado;
import dominio.NroCorrelativo;

public class DialogCobranzaAltaRecibo extends JDialog {


	private JPanel jContentPane = null;  //  @jve:decl-index=0:visual-constraint="0,1380"
	private static final long serialVersionUID = 1L;
	/*clases visuales*/
	private JScrollPane jScrollPaneaList = null;
	private MyJtable jTableDocumentosAimputar = null;
	private MyTableModel myTableModel = null;
	private TableRowSorter<TableModel> tableRowSorter = null;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonActionOk = null;
	private JTextField jTextFieldCodigoCli = null;
	private JLabel jLabelCodigoCli = null;
	private JLabel jLabelRazonSocial = null;
	private JTextField jTextFieldRazonSocial = null;
	private JLabel jLabelFecha = null;
	private JLabel jLabelNroFac = null;
	private JTextField jTextFieldNroFac = null;
	private JLabel jLabelRecibo = null;
	private JLabel jLabelLetraFac = null;
	private JFormattedTextField jTextFieldFechaFac = null;
	private MaskFormatter maskFormatterFechaFac = null;
	private JLabel jLabelTotal = null;
	private JLabel jLabelTotalFac = null;

	/*clases de logica*/


	private Cliente cliente = null;  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //  @jve:decl-index=0:
	private DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();  //  @jve:decl-index=0:

	private DecimalFormat decimalFormat = new DecimalFormat("####.00");  //  @jve:decl-index=0:
	private NroCorrelativo nroCorrelativo = null;  //  @jve:decl-index=0:
	private NroCorrelativoLogica nroCorrelativoLogica = NroCorrelativoLogica.getInstance();
	private Vector<DetallePago> detallesPago = null;  //  @jve:decl-index=0:
	private ArrayList<ComprobanteCtaCte> comprobantesAimputar = null;
	private Float montoAimputar = Float.valueOf(0);
	private Cobranza cobranza = null;
	private ArrayList<DocumentoImputado> documentosAimputar = null;
	private CobranzaLogica cobranzaLogica = CobranzaLogica.getInstance();

	/**
	 * This is the xxx default constructor
	 */
	public DialogCobranzaAltaRecibo(Frame owner, Cliente cliente, ArrayList<ComprobanteCtaCte> comprobantesAimputar, Float montoAimputar) {
		super(owner);
		initialize(cliente, comprobantesAimputar, montoAimputar);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize(Cliente cliente, ArrayList<ComprobanteCtaCte> comprobantesAimputar, Float montoAimputar) {
		this.setSize(1024, 360);
		this.setMaximumSize(new Dimension(1024, 696));
		this.setMinimumSize(new Dimension(0, 0));
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Cobranza - Recibos de Pago");
		this.setResizable(false);

		if(cliente == null || comprobantesAimputar == null || montoAimputar == null){
			dispose();
			try {
				finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//setVisible(false);
		}else{
			this.cliente = cliente;
			this.comprobantesAimputar = comprobantesAimputar;
			this.montoAimputar = montoAimputar;
		}
		this.setContentPane(getJContentPane());
		setCobranza();
		setItemsInJtableDocumentosAimputar();
		setEstatusAllComponent();
		setEnabledButtonsToolbar(true, true);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelTotalFac = new JLabel();
			jLabelTotalFac.setBounds(new Rectangle(888, 269, 120, 50));
			jLabelTotalFac.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelTotalFac.setText("");
			jLabelTotalFac.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelTotal = new JLabel();
			jLabelTotal.setBounds(new Rectangle(888, 244, 120, 25));
			jLabelTotal.setText("TOTAL");
			jLabelTotal.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelLetraFac = new JLabel();
			jLabelLetraFac.setBounds(new Rectangle(829, 35, 50, 40));
			jLabelLetraFac.setText("");
			jLabelLetraFac.setBorder(BorderFactory.createLineBorder(Color.black));
			jLabelLetraFac.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelRecibo = new JLabel();
			jLabelRecibo.setBounds(new Rectangle(773, 35, 50, 20));
			jLabelRecibo.setText("Recibo");
			jLabelNroFac = new JLabel();
			jLabelNroFac.setBounds(new Rectangle(885, 35, 55, 20));
			jLabelNroFac.setText("Número:");
			jLabelFecha = new JLabel();
			jLabelFecha.setBounds(new Rectangle(640, 35, 40, 20));
			jLabelFecha.setText("Fecha:");
			jLabelRazonSocial = new JLabel();
			jLabelRazonSocial.setBounds(new Rectangle(185, 35, 80, 20));
			jLabelRazonSocial.setText("Razón Social:");
			jLabelCodigoCli = new JLabel();
			jLabelCodigoCli.setBounds(new Rectangle(5, 35, 75, 20));
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
			jContentPane.add(jLabelNroFac, null);
			jContentPane.add(getJTextFieldNroFac(), null);
			jContentPane.add(jLabelRecibo, null);
			jContentPane.add(jLabelLetraFac, null);
			jContentPane.add(getJTextFieldFechaFac(), null);
			jContentPane.add(jLabelTotal, null);
			jContentPane.add(jLabelTotalFac, null);
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
			jScrollPaneaList.setBounds(new Rectangle(5, 90, 1005, 150));
			jScrollPaneaList.setViewportView(getJTableDocumentosAimputar());
		}
		return jScrollPaneaList;
	}

	public MyJtable getJTableDocumentosAimputar() {
		/*
		 * Instanciamos el TableRowSorter y lo añadimos al JTable
		 */
		if (jTableDocumentosAimputar == null) {
			jTableDocumentosAimputar = new MyJtable(getMyTableModel());
			jTableDocumentosAimputar.setRowSorter(geTableRowSorter());
			jTableDocumentosAimputar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableDocumentosAimputar.setRowHeight(20);
			jTableDocumentosAimputar.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableDocumentosAimputar.setAutoscrolls(true);
			jTableDocumentosAimputar.setShowVerticalLines(true);
			jTableDocumentosAimputar.setShowHorizontalLines(true);
			jTableDocumentosAimputar.setVisible(true);
		}
		return jTableDocumentosAimputar;
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

	private void setItemsInJtableDocumentosAimputar(){
		try{
			if(documentosAimputar == null)calculateDocumentosAimputar();
			Vector<String> titulos = new Vector<String>();
			titulos.add("Fecha Emisión");
			titulos.add("Fecha Vencimiento");
			titulos.add("Comprobante");
			titulos.add("Cliente");
			titulos.add("Saldo Comprobante");
			titulos.add("Importe a Imputar");
			titulos.add("Saldo");
			Vector<Vector<Object>> registros = new Vector<Vector<Object>>();
			Vector<Object> renglon = null;
			if(documentosAimputar != null){
				Iterator<DocumentoImputado> it = documentosAimputar.iterator();
				while(it.hasNext()){
					DocumentoImputado documentoImputado = (DocumentoImputado)it.next();
					renglon = new Vector<Object>();
					renglon.add(dateFormatArgen.format(dateFormatJapan.parse(documentoImputado.getComprobanteCtaCte().getFechaEmision())));
					renglon.add(dateFormatArgen.format(dateFormatJapan.parse(documentoImputado.getComprobanteCtaCte().getFechaVencimiento())));
					renglon.add(documentoImputado.getComprobanteCtaCte().getConcepto());
					if(!documentoImputado.getComprobanteCtaCte().getCliente().getRazonSocial().isEmpty()){
						renglon.add(documentoImputado.getComprobanteCtaCte().getCliente().getRazonSocial());
					}else{
						renglon.add(documentoImputado.getComprobanteCtaCte().getCliente().getNombre() + " " + documentoImputado.getComprobanteCtaCte().getCliente().getApellido());
					}
					renglon.add("$ "+getValueDecimalVisual(documentoImputado.getComprobanteCtaCte().getSaldo()));
					renglon.add("$ "+getValueDecimalVisual(documentoImputado.getMontoImputado()));
					renglon.add("$ "+getValueDecimalVisual(documentoImputado.getComprobanteCtaCte().getSaldo() - documentoImputado.getMontoImputado()));
					registros.add(renglon);
				}
			}
			myTableModel.setDataVector(registros, titulos);
			//setOcultarColumnasJTable(jTableDocumentosAimputar, new int[]{3, 4, 5, 6});
			jTableDocumentosAimputar.getColumnModel().getColumn(0).setPreferredWidth(10);
			jTableDocumentosAimputar.getColumnModel().getColumn(1).setPreferredWidth(10);
			jTableDocumentosAimputar.getColumnModel().getColumn(2).setPreferredWidth(200);
			jTableDocumentosAimputar.getColumnModel().getColumn(3).setPreferredWidth(200);
			jTableDocumentosAimputar.getColumnModel().getColumn(4).setPreferredWidth(10);
			jTableDocumentosAimputar.getColumnModel().getColumn(5).setPreferredWidth(10);
			jTableDocumentosAimputar.getColumnModel().getColumn(6).setPreferredWidth(10);

		}catch(ParseException pe){
			pe.printStackTrace();
		}
	}


	/**
	 * This method initializes jTextFieldCodigoCli
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCodigoCli() {
		if (jTextFieldCodigoCli == null) {
			jTextFieldCodigoCli = new JTextField();
			jTextFieldCodigoCli.setBounds(new Rectangle(85, 35, 80, 20));
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
			jTextFieldRazonSocial.setBounds(new Rectangle(280, 35, 350, 20));
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
			jTextFieldNroFac.setBounds(new Rectangle(885, 56, 100, 20));
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
			jTextFieldFechaFac.setBounds(new Rectangle(695, 35, 70, 20));
		}
		return jTextFieldFechaFac;
	}





	/************ TRATAMIENTO SOBRE FORMATO DE DATOS ***********************/

	//RETORNA EL VALOR FLOAT DE LOS TEXT FIELD DECIMALES CON EL FORMATO DETERMINADO
	private String getValueDecimalVisual(Float valor){
		decimalFormatSymbols.setDecimalSeparator('.');
		decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
		if(valor != null){
			System.out.println(decimalFormat.format(valor.floatValue()));
			return decimalFormat.format(valor.doubleValue());
		}else{
			return null;
		}
	}




	private void setCobranza(){
		if(cliente != null){
			cobranza = new Cobranza();
			if(!cliente.getRazonSocial().isEmpty()){
				jTextFieldRazonSocial.setText(cliente.getRazonSocial());
			}else{
				jTextFieldRazonSocial.setText(cliente.getNombre() + " " + cliente.getApellido());
			}
			jTextFieldCodigoCli.setText(String.valueOf(cliente.getCodigo()));
			jTextFieldFechaFac.setText(dateFormatArgen.format(new Date()));
			jTextFieldNroFac.setText("0001-"+getNroFacturaCorrelativo(cliente));
			jLabelLetraFac.setText(
					"<html>" +
						"<b>" +
							"<font color='black' size=12>" +
								getLetraRecibo(cliente) +
							"</font>" +
						"</b>" +
					"</html>");
			jLabelTotalFac.setText("<html>" +
					"<b>" +
						"<font color='red' size = 4>" +
							"$ " + getValueDecimalVisual(montoAimputar) +
						"</font>" +
					"</b>" +
				"</html>");

			cobranza.setCliente(cliente);
			cobranza.setDocumentosImputados(calculateDocumentosAimputar());
			cobranza.setFechaCobranza(dateFormatJapan.format(new Date()));
			cobranza.setLetraComprobanteRecibo(getLetraRecibo(cliente));
			cobranza.setMontoTotalImputado(montoAimputar);
			cobranza.setNroComprobanteRecibo(getNroFacturaCorrelativo(cliente));
			cobranza.setNroPtoVtaComprobanteRecibo("0001");
			cobranza.setTipoComprobanteRecibo("REC");
			cobranza.setVendedor(null);
		}
	}


	/**************** BUTTONS DE TOLBAR  Y TOOLBAR ********************/
	/*DEVULEVE LA JTOOLBAR*/
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 1013, 30));
			jToolBar.setFloatable(false);
			jToolBar.add(getJButtonActionOk());
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
			jButtonActionOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(cobranza != null){
						int selectedOption = JOptionPane.showConfirmDialog(null, "Confirma que desea grabar el Recibo Nro: " + cobranza.getNroComprobanteRecibo() + " Del Cliente Nro: " + cobranza.getCliente().getCodigo() + " ?", "Grabar Comprobante", JOptionPane.OK_CANCEL_OPTION);
						if(selectedOption == 0){
							boolean grabar = false;
							boolean resultado = false;
							DialogMedioPago dialogMedioPago = new DialogMedioPago(new Frame(), null, cobranza);
							dialogMedioPago.setModal(true);
							dialogMedioPago.setVisible(true);
							detallesPago = dialogMedioPago.getDetallesPagos();
							if(detallesPago != null) grabar = true;

							if(grabar){
								resultado = cobranzaLogica.addCobranza(cobranza, detallesPago, WinPrincipal.getCaja());
								if(resultado){
									JOptionPane.showMessageDialog(null, "El Comprobante fue guardado con éxito", "Guardado Exitoso", JOptionPane.INFORMATION_MESSAGE);
									dispose();
									setVisible(false);
								}else{
									JOptionPane.showMessageDialog(null, "Ha Ocurrido un Error al grabar el comprobante\n" +
											"Intente Nuevamente o Consulte con su Administrador del Sistema", "Error al Grabar Comprobante", JOptionPane.OK_OPTION);
									dispose();
									setVisible(false);
								}
							}
						}else{
							JOptionPane.showMessageDialog(null, "Debe especificar los medios de pago","Especificar Medios de Pago", JOptionPane.ERROR_MESSAGE);


					}
				}
				}
			});
		}
		return jButtonActionOk;
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
	public void setEnabledButtonsToolbar(boolean btnOk, boolean btnCloce){
		jButtonActionOk.setEnabled(btnOk);
		jButtonaActionCloce.setEnabled(btnCloce);
	}

	/*setea el estado (enable o editable) de todos los componenetes*/
	public void setEstatusAllComponent(){
		jTextFieldCodigoCli.setEditable(false);
		jTextFieldFechaFac.setEditable(false);
		jTextFieldRazonSocial.setEditable(false);
	}


	private String getLetraRecibo(Cliente cliente){
		String letraRecibo = null;
		if(cliente != null){
			if(cliente.getCondicionIVA().getCodigo() == 1 || cliente.getCondicionIVA().getCodigo() == 5){
				//jLabelLetraFac.setText("A");
				letraRecibo = "A";
			}else{
				//jLabelLetraFac.setText("B");
				letraRecibo = "B";
			}
		}
		return letraRecibo;
	}

	private String getNroFacturaCorrelativo(Cliente cliente){
		NroCorrelativo correlativo = new NroCorrelativo("REC", getLetraRecibo(cliente), "0001", null);
		nroCorrelativo = correlativo;
		String nroComprobanteCorrelativo = nroCorrelativoLogica.getNextNroComprobante(correlativo);
		nroCorrelativo.setNroComprobante(nroComprobanteCorrelativo);
		return nroComprobanteCorrelativo;
	}




	public void setDetallesPagos(Vector<DetallePago> detPagos){
		detallesPago = detPagos;
	}

	private ArrayList<DocumentoImputado> calculateDocumentosAimputar(){
		ArrayList<DocumentoImputado> documentosImputados = new ArrayList<DocumentoImputado>();
		Float saldoAimputar = Float.valueOf(0);
		DocumentoImputado di = null;
		if(montoAimputar != null && comprobantesAimputar != null){
			saldoAimputar = montoAimputar;
			Iterator<ComprobanteCtaCte> it = comprobantesAimputar.iterator();
			while(it.hasNext()){
				if(saldoAimputar != 0){
					di = new DocumentoImputado();
					ComprobanteCtaCte ccc = (ComprobanteCtaCte)it.next();
					di.setComprobanteCtaCte(ccc);
					if(saldoAimputar > ccc.getSaldo()){
						di.setMontoImputado(ccc.getSaldo());
						//di.getComprobanteCtaCte().setSaldo(ccc.getSaldo());
						saldoAimputar -= ccc.getSaldo();
					}else if(saldoAimputar < ccc.getSaldo() && saldoAimputar > 0){
						di.setMontoImputado(saldoAimputar);
						//di.getComprobanteCtaCte().setSaldo(ccc.getSaldo() - saldoAimputar);
						saldoAimputar = Float.valueOf(0);
					}else if(saldoAimputar.equals(ccc.getSaldo())){
						di.setMontoImputado(saldoAimputar);
						//di.getComprobanteCtaCte().setSaldo(ccc.getSaldo() - saldoAimputar);
						saldoAimputar = Float.valueOf(0);
					}
					di.setNumeroCta(ccc.getCantidadCtas());
					documentosImputados.add(di);
				}else{
					break;
				}
			}
		}
		documentosAimputar = documentosImputados;
		return documentosImputados;
	}





}  //  @jve:decl-index=0:visual-constraint="10,10"

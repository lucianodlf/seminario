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
package presentacion.Reports;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Rectangle;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import presentacion.Dialog.DialogGestorCliente;
import reports.ReportVentasDao;
import verificadores.MyPlainDocument;
import dominio.Cliente;

public class InternalFrameReporteVentas extends JInternalFrame {

	private JPanel jContentPane = null;
	private static final long serialVersionUID = 1L;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonaActionVer = null;
	private JFormattedTextField jTextFieldFechaDesde = null;
	private MaskFormatter maskFormatterFechaDesde = null;
	private JFormattedTextField jTextFieldFechaHasta = null;
	private MaskFormatter maskFormatterFechaHasta = null;
	private JLabel jLabelFechaDesde = null;
	private JLabel jLabelFechaHasta = null;
	private JCheckBox jCheckBoxActFiltroFecha = null;
	private JLabel jLabelCodigo = null;
	private JTextField jTextFieldCodigoCli = null;
	private JCheckBox jCheckBoxActFiltroCliente = null;
	private JButton jButtonClientes = null;


	/*clases de logica*/
	private Cliente cliente = null;
	private String fechaDesde = null;
	private String fechaHasta = null;
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //
	private ReportVentasDao reportVentasDao = ReportVentasDao.getInstance();//  @jve:decl-index=0:
	private JRBeanCollectionDataSource dataSource;
	private JasperReport jasperReport = null;
	private JasperPrint jasperPrint = null;
	private JasperViewer jasperViewer = null;
	private JLabel jLabelLoad = null;
	private Thread thread1 = null;
	private Thread thread2 = null;
	private Integer codCliente = null;


	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameReporteVentas() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(335, 231);
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Reporte de Ventas");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelLoad = new JLabel();
			jLabelLoad.setBounds(new Rectangle(7, 149, 120, 45));
			jLabelLoad.setIcon(new ImageIcon(getClass().getResource("/ajax-loader.gif")));
			jLabelLoad.setText("   Cargando...");
			jLabelLoad.setVisible(false);
			jLabelCodigo = new JLabel();
			jLabelCodigo.setBounds(new Rectangle(5, 40, 91, 20));
			jLabelCodigo.setText("Código Cliente:");
			jLabelFechaHasta = new JLabel();
			jLabelFechaHasta.setBounds(new Rectangle(170, 99, 80, 20));
			jLabelFechaHasta.setText("Fecha Hasta:");
			jLabelFechaDesde = new JLabel();
			jLabelFechaDesde.setBounds(new Rectangle(5, 99, 80, 20));
			jLabelFechaDesde.setText("Fecha Desde:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJButtonaActionVer(), null);
			jContentPane.add(getJTextFieldFechaDesde(), null);
			jContentPane.add(getJTextFieldFechaHasta(), null);
			jContentPane.add(jLabelFechaDesde, null);
			jContentPane.add(jLabelFechaHasta, null);
			jContentPane.add(getJCheckBoxActFiltroFecha(), null);
			jContentPane.add(jLabelCodigo, null);
			jContentPane.add(getJTextFieldCodigoCli(), null);
			jContentPane.add(getJCheckBoxActFiltroCliente(), null);
			jContentPane.add(getJButtonClientes(), null);
			jContentPane.add(jLabelLoad, null);
		}
		return jContentPane;
	}


	private JButton getJButtonaActionVer() {
		if (jButtonaActionVer == null) {
			jButtonaActionVer = new JButton();
			jButtonaActionVer.setBounds(new Rectangle(175, 153, 144, 37));
			jButtonaActionVer.setPreferredSize(new Dimension(100, 30));
			jButtonaActionVer.setBorderPainted(true);
			jButtonaActionVer.setIcon(new ImageIcon(getClass().getResource("/print-preview_24x24.png")));
			jButtonaActionVer.setText("Vista Previa");
			jButtonaActionVer.setName("Close");
			jButtonaActionVer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					dataSource = null;

					thread1 = null;
					thread2 = null;
					jLabelLoad.setVisible(false);

					thread1 = new Thread(){
						public void run(){
							if(cliente != null){
								if(!jTextFieldCodigoCli.getText().isEmpty()){
									codCliente = Integer.valueOf(jTextFieldCodigoCli.getText());
								}else{
									codCliente = cliente.getCodigo();
								}
							}else{
								if(!jTextFieldCodigoCli.getText().isEmpty()){
									codCliente = Integer.valueOf(jTextFieldCodigoCli.getText());
								}else{
									codCliente = null;
								}
							}

							try{
								if(jCheckBoxActFiltroCliente.isSelected() && jCheckBoxActFiltroFecha.isSelected()){
									if(jTextFieldFechaDesde.getText().equals("__/__/____")){
										fechaDesde = "";
									}else{
										fechaDesde = dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText()));
									}
									if(jTextFieldFechaHasta.getText().equals("__/__/____")){
										fechaHasta = "";
									}else{
										fechaHasta = dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText()));
									}
									if(codCliente != null){
										dataSource = new JRBeanCollectionDataSource(reportVentasDao.selectAllComprobantesBetweenFechaAndCliente(codCliente, fechaDesde, fechaHasta));
									}else{
										dataSource = new  JRBeanCollectionDataSource(reportVentasDao.selectAllComprobantesBetweenFecha(fechaDesde, fechaHasta));
									}
								}else if(jCheckBoxActFiltroCliente.isSelected()){
									if(codCliente != null){
										dataSource = new JRBeanCollectionDataSource(reportVentasDao.selectAllComprobantesByClient(codCliente));
									}
								}else if(jCheckBoxActFiltroFecha.isSelected()){
									if(jTextFieldFechaDesde.getText().equals("__/__/____")){
										fechaDesde = "";
									}else{
										fechaDesde = dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText()));
									}
									if(jTextFieldFechaHasta.getText().equals("__/__/____")){
										fechaHasta = "";
									}else{
										fechaHasta = dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText()));
									}
									dataSource = new JRBeanCollectionDataSource(reportVentasDao.selectAllComprobantesBetweenFecha(fechaDesde, fechaHasta));
								}else{
									dataSource = new JRBeanCollectionDataSource(reportVentasDao.selectAllComprobantes());
								}

								boolean datos = true;
								if(dataSource != null){
									if(dataSource.getData() != null){
										try {
											jasperReport = (JasperReport)JRLoader.loadObject("reports/reporteVentas_1.jasper");
											jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

												jasperViewer = new JasperViewer(jasperPrint);
												jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
												jasperViewer.setTitle("Vista Previa: Reporte de Ventas");
												jasperViewer.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
												jasperViewer.removeWindowListener(jasperViewer.getWindowListeners()[0]);
												jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
												thread1.interrupt();
												jLabelLoad.setVisible(false);
												jasperViewer.setVisible(true);

											} catch (JRException ex) {
												// TODO Auto-generated catch block
												ex.printStackTrace();
											}
									}else{
										datos = false;
									}
								}else{
									datos = false;
								}
								if(!datos){
									thread1.interrupt();
									jLabelLoad.setVisible(false);
									JOptionPane.showMessageDialog(null, "No existen Datos para Visualizar", "Sin Datos", JOptionPane.INFORMATION_MESSAGE);
								}
							}catch(ParseException pe){
								pe.printStackTrace();
							}
						}
					};

					thread2 = new Thread(){
						public void run(){
							thread1.start();
							if(jasperViewer != null)jLabelLoad.setVisible(false);
							while(jasperViewer == null){
								if(jasperViewer != null){
									jLabelLoad.setVisible(false);
									thread2.interrupt();
								}
							}

						}
					};

					jLabelLoad.setVisible(true);
					thread2.start();


				}
			});
		}
		return jButtonaActionVer;
	}
	/**************** BUTTONS DE TOLBAR  Y TOOLBAR ********************/
	/*DEVULEVE LA JTOOLBAR*/
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setBounds(new Rectangle(0, 0, 323, 30));
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


	/**
	 * This method initializes maskFormatterFechaDesde
	 *
	 * @return javax.swing.text.MaskFormatter
	 */
	private MaskFormatter getMaskFormatterFechaDesde() {
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
			jTextFieldFechaDesde = new JFormattedTextField(
					getMaskFormatterFechaDesde());
			jTextFieldFechaDesde.setBounds(new Rectangle(90, 99, 68, 20));
			jTextFieldFechaDesde.setText(dateFormatArgen.format(new Date()));
		}
		return jTextFieldFechaDesde;
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
			jTextFieldFechaHasta.setBounds(new Rectangle(255, 99, 68, 20));
			jTextFieldFechaHasta.setText(dateFormatArgen.format(new Date()));
		}
		return jTextFieldFechaHasta;
	}

	/**
	 * This method initializes jCheckBoxActFiltroFecha
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxActFiltroFecha() {
		if (jCheckBoxActFiltroFecha == null) {
			jCheckBoxActFiltroFecha = new JCheckBox();
			jCheckBoxActFiltroFecha.setBounds(new Rectangle(5, 125, 153, 20));
			jCheckBoxActFiltroFecha.setSelected(false);
			jCheckBoxActFiltroFecha.setText("Activar Filtro de Fecha");
		}
		return jCheckBoxActFiltroFecha;
	}

	/**
	 * This method initializes jTextFieldCodigoCli
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldCodigoCli() {
		if (jTextFieldCodigoCli == null) {
			jTextFieldCodigoCli = new JTextField();
			jTextFieldCodigoCli.setBounds(new Rectangle(100, 40, 80, 20));
			jTextFieldCodigoCli.setDocument(new MyPlainDocument(jTextFieldCodigoCli,10,"D",true));
		}
		return jTextFieldCodigoCli;
	}

	/**
	 * This method initializes jCheckBoxActFiltroCliente
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBoxActFiltroCliente() {
		if (jCheckBoxActFiltroCliente == null) {
			jCheckBoxActFiltroCliente = new JCheckBox();
			jCheckBoxActFiltroCliente.setBounds(new Rectangle(5, 65, 160, 20));
			jCheckBoxActFiltroCliente.setSelected(false);
			jCheckBoxActFiltroCliente.setText("Activar Filtro de Cliente");
		}
		return jCheckBoxActFiltroCliente;
	}

	/**
	 * This method initializes jButtonClientes
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonClientes() {
		if (jButtonClientes == null) {
			jButtonClientes = new JButton();
			jButtonClientes.setBounds(new Rectangle(184, 40, 20, 20));
			jButtonClientes.setText("...");
			jButtonClientes.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					DialogGestorCliente dialogGestorCliente = new DialogGestorCliente(new Frame());
					dialogGestorCliente.setModal(true);
					dialogGestorCliente.setVisible(true);
					if(dialogGestorCliente.getSelectedElement() != null)cliente = dialogGestorCliente.getSelectedElement();
					if(cliente != null){
						jTextFieldCodigoCli.setText(String.valueOf(cliente.getCodigo()));
					}
				}
			});
		}
		return jButtonClientes;
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"

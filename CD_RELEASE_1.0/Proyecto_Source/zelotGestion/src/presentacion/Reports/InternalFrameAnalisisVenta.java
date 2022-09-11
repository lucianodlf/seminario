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
import java.awt.Rectangle;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.text.MaskFormatter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import persistencia.ManagerDao;

public class InternalFrameAnalisisVenta extends JInternalFrame {

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
	/*clases de logica*/
	private String fechaDesde = null;
	private String fechaHasta = null;
	private SimpleDateFormat dateFormatJapan = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  //  @jve:decl-index=0:
	private SimpleDateFormat dateFormatArgen = new SimpleDateFormat("dd/MM/yyyy");  //
	@SuppressWarnings("unused")
	private JRBeanCollectionDataSource dataSource;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	private JComboBox jComboBoxCantFechas = null;
	private JComboBox jComboBoxCantMeses = null;
	private JComboBox jComboBoxCantAños = null;
	private JLabel jLabelMaxFechas = null;
	private JLabel jLabelMaxMeses = null;
	private JLabel jLabelMaxAños = null;
	private JasperViewer jasperViewer = null;
	private JLabel jLabelLoad = null;
	private Thread thread1 = null;
	private Thread thread2 = null;
	private Map<Object, Object> pars = new HashMap<Object, Object>();  //  @jve:decl-index=0:

	/*private static InternalFrameAnalisisVenta internalFrameAnalisisVenta = null;

	public static InternalFrameAnalisisVenta getInstance(){
		if(internalFrameAnalisisVenta == null){
			internalFrameAnalisisVenta = new InternalFrameAnalisisVenta();
		}
		return internalFrameAnalisisVenta;
	}*/

	public InternalFrameAnalisisVenta() {
		super();
		initialize();
	}

	/**
	 * This is the xxx default constructor
	 */
	/*public InternalFrameAnalisisVenta() {
		super();
		initialize();
	}*/

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(344, 188);
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Estadística de Ventas");
		setValuesJComboBox();
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelLoad = new JLabel();
			jLabelLoad.setBounds(new Rectangle(194, 68, 120, 45));
			jLabelLoad.setText("   Cargando...");
			jLabelLoad.setIcon(new ImageIcon(getClass().getResource("/ajax-loader.gif")));
			jLabelLoad.setVisible(false);
			jLabelMaxAños = new JLabel();
			jLabelMaxAños.setBounds(new Rectangle(5, 130, 110, 20));
			jLabelMaxAños.setText("Máximo de Años:");
			jLabelMaxMeses = new JLabel();
			jLabelMaxMeses.setBounds(new Rectangle(5, 100, 110, 20));
			jLabelMaxMeses.setText("Máximo de Meses:");
			jLabelMaxFechas = new JLabel();
			jLabelMaxFechas.setBounds(new Rectangle(5, 70, 110, 20));
			jLabelMaxFechas.setText("Máximo de Fechas:");
			jLabelFechaHasta = new JLabel();
			jLabelFechaHasta.setBounds(new Rectangle(174, 42, 80, 20));
			jLabelFechaHasta.setText("Fecha Hasta:");
			jLabelFechaDesde = new JLabel();
			jLabelFechaDesde.setBounds(new Rectangle(5, 42, 80, 20));
			jLabelFechaDesde.setText("Fecha Desde:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJButtonaActionVer(), null);
			jContentPane.add(getJTextFieldFechaDesde(), null);
			jContentPane.add(getJTextFieldFechaHasta(), null);
			jContentPane.add(jLabelFechaDesde, null);
			jContentPane.add(jLabelFechaHasta, null);
			jContentPane.add(getJComboBoxCantFechas(), null);
			jContentPane.add(getJComboBoxCantMeses(), null);
			jContentPane.add(getJComboBoxCantAños(), null);
			jContentPane.add(jLabelMaxFechas, null);
			jContentPane.add(jLabelMaxMeses, null);
			jContentPane.add(jLabelMaxAños, null);
			jContentPane.add(jLabelLoad, null);
		}
		return jContentPane;
	}


	private JButton getJButtonaActionVer() {
		if (jButtonaActionVer == null) {
			jButtonaActionVer = new JButton();
			jButtonaActionVer.setBounds(new Rectangle(184, 115, 144, 37));
			jButtonaActionVer.setPreferredSize(new Dimension(100, 30));
			jButtonaActionVer.setBorderPainted(true);
			jButtonaActionVer.setIcon(new ImageIcon(getClass().getResource("/print-preview_24x24.png")));
			jButtonaActionVer.setText("Vista Previa");
			jButtonaActionVer.setName("Close");
			jButtonaActionVer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					dataSource = null;
					boolean ok = true;
					try {
						if(!(jTextFieldFechaDesde.getText().equals("__/__/____")) && !(jTextFieldFechaHasta.getText().equals("__/__/____"))){
							fechaHasta = dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaHasta.getText()));
							fechaDesde = dateFormatJapan.format(dateFormatArgen.parse(jTextFieldFechaDesde.getText()));
						}else{
							JOptionPane.showMessageDialog(null, "Debe Seleccionar un rango de Fechas", "Error", JOptionPane.ERROR_MESSAGE);
							ok = false;
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(ok){
						try {

							pars.put("MAXIMO_FECHAS", (Integer)jComboBoxCantFechas.getSelectedItem()-1);
							pars.put("MAXIMO_MESES", (Integer)jComboBoxCantMeses.getSelectedItem());
							pars.put("MAXIMO_ANOS", (Integer)jComboBoxCantAños.getSelectedItem());
							pars.put("FECHA_DESDE", fechaDesde);
							pars.put("FECHA_HASTA", fechaHasta);

							thread1 = null;
							thread2 = null;
							jLabelLoad.setVisible(false);

							thread1 = new Thread(){
								public void run(){
									try {
									jasperReport = (JasperReport)JRLoader.loadObject("reports/estadisticaVentas_1.jasper");
									jasperPrint = JasperFillManager.fillReport(jasperReport,pars, ManagerDao.getConexion());
									jasperViewer =	new JasperViewer(jasperPrint);
									jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
									jasperViewer.setTitle("Vista Previa: Estadística de Ventas");
									jasperViewer.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
									jasperViewer.removeWindowListener(jasperViewer.getWindowListeners()[0]);
									jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
									thread1.interrupt();
									jLabelLoad.setVisible(false);
									jasperViewer.setVisible(true);
									} catch (JRException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
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
							//jLabelLoad.setVisible(false);

						}catch(Exception es){
							es.printStackTrace();
						}

					}

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
			jToolBar.setBounds(new Rectangle(0, 0, 335, 30));
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
			jTextFieldFechaDesde.setBounds(new Rectangle(94, 42, 68, 20));
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
			jTextFieldFechaHasta.setBounds(new Rectangle(259, 42, 68, 20));
			jTextFieldFechaHasta.setText(dateFormatArgen.format(new Date()));
		}
		return jTextFieldFechaHasta;
	}

	/**
	 * This method initializes jComboBoxCantFechas
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxCantFechas() {
		if (jComboBoxCantFechas == null) {
			jComboBoxCantFechas = new JComboBox();
			jComboBoxCantFechas.setBounds(new Rectangle(120, 70, 60, 20));
		}
		return jComboBoxCantFechas;
	}

	/**
	 * This method initializes jComboBoxCantMeses
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxCantMeses() {
		if (jComboBoxCantMeses == null) {
			jComboBoxCantMeses = new JComboBox();
			jComboBoxCantMeses.setBounds(new Rectangle(120, 100, 60, 20));
		}
		return jComboBoxCantMeses;
	}

	/**
	 * This method initializes jComboBoxCantAños
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxCantAños() {
		if (jComboBoxCantAños == null) {
			jComboBoxCantAños = new JComboBox();
			jComboBoxCantAños.setBounds(new Rectangle(120, 130, 60, 20));
		}
		return jComboBoxCantAños;
	}

	private void setValuesJComboBox(){
		for(int i=10;i>=1;i--){
			jComboBoxCantAños.addItem(Integer.valueOf(i));
			jComboBoxCantFechas.addItem(Integer.valueOf(i));
			jComboBoxCantMeses.addItem(Integer.valueOf(i));
		}
	}



}  //  @jve:decl-index=0:visual-constraint="10,10"

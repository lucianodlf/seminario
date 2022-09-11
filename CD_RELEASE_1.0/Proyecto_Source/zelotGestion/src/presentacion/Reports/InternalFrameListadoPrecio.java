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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import logica.ListaPrecioLogica;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import reports.ReportListadoPrecioLogica;
import dominio.ListaPrecio;

public class InternalFrameListadoPrecio extends JInternalFrame {

	private JPanel jContentPane = null;
	private static final long serialVersionUID = 1L;
	private JLabel jLabelListasPrecios = null;
	private JComboBox jComboBoxListasPrecios = null;
	private JToolBar jToolBar = null;
	private JButton jButtonaActionCloce = null;
	private JButton jButtonaActionVer = null;


	/*clases de logica*/
	private ListaPrecio listaPrecio = null;
	private ListaPrecioLogica listaPrecioLogica = ListaPrecioLogica.getInstance();//  @jve:decl-index=0:
	private JRBeanCollectionDataSource dataSource;
	private JasperReport jasperReport;
	private JasperPrint jasperPrint;
	private ReportListadoPrecioLogica reportsLogica = ReportListadoPrecioLogica.getInstance();
	private JLabel jLabelLoad = null;
	private Thread thread1 = null;
	private Thread thread2 = null;
	private JasperViewer jasperViewer = null;
	private Map<String, String> pars = null;

	/**
	 * This is the xxx default constructor
	 */
	public InternalFrameListadoPrecio() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(334, 157);
		this.setContentPane(getJContentPane());
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Listado de Precios");
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelLoad = new JLabel();
			jLabelLoad.setBounds(new Rectangle(6, 67, 120, 45));
			jLabelLoad.setText("   Cargando...");
			jLabelLoad.setIcon(new ImageIcon(getClass().getResource("/ajax-loader.gif")));
			jLabelLoad.setVisible(false);
			jContentPane = new JPanel();
			jLabelListasPrecios = new JLabel();
			jLabelListasPrecios.setBounds(new Rectangle(6, 36, 123, 20));
			jLabelListasPrecios.setText("Listas de Precios:");
			jContentPane.setLayout(null);
			jContentPane.add(jLabelListasPrecios, null);
			jContentPane.add(getJComboBoxListasPrecios(), null);
			jContentPane.add(getJToolBar(), null);
			jContentPane.add(getJButtonaActionVer(), null);
			jContentPane.add(jLabelLoad, null);
		}
		return jContentPane;
	}


	private JComboBox getJComboBoxListasPrecios() {
		if (jComboBoxListasPrecios == null) {
			jComboBoxListasPrecios = new JComboBox();
			jComboBoxListasPrecios.setBounds(new Rectangle(139, 36, 180, 20));
			jComboBoxListasPrecios
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							listaPrecio = (ListaPrecio)jComboBoxListasPrecios.getSelectedItem();
						}
					});
			setItemInJComboBoxListadoPrecios();
		}
		return jComboBoxListasPrecios;
	}

	private JButton getJButtonaActionVer() {
		if (jButtonaActionVer == null) {
			jButtonaActionVer = new JButton();
			jButtonaActionVer.setBounds(new Rectangle(173, 71, 144, 37));
			jButtonaActionVer.setPreferredSize(new Dimension(100, 30));
			jButtonaActionVer.setBorderPainted(true);
			jButtonaActionVer.setIcon(new ImageIcon(getClass().getResource("/print-preview_24x24.png")));
			jButtonaActionVer.setText("Vista Previa");
			jButtonaActionVer.setName("Close");
			jButtonaActionVer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(listaPrecio != null){
						pars = new HashMap<String, String>();
						pars.put("ID_LISTA_PRECIOS", String.valueOf(listaPrecio.getCodigoListaPrecios()));
						pars.put("NOMBRE_LISTA_PRECIOS", listaPrecio.getDescripcion());

						thread1 = null;
						thread2 = null;
						jLabelLoad.setVisible(false);

						thread1 = new Thread(){
							public void run(){
								try {
									dataSource = new JRBeanCollectionDataSource(reportsLogica.getDataReport(listaPrecio));
										if(!dataSource.getData().isEmpty() || dataSource != null){
											jasperReport = (JasperReport)JRLoader.loadObject("reports/listadoPrecios_1.jasper");
											jasperPrint = JasperFillManager.fillReport(jasperReport,pars, dataSource);
											jasperViewer =	new JasperViewer(jasperPrint);
											jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
											jasperViewer.setTitle("Vista Previa: Listado de Precios");
											jasperViewer.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
											jasperViewer.removeWindowListener(jasperViewer.getWindowListeners()[0]);
											jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
											thread1.interrupt();
											jLabelLoad.setVisible(false);
											jasperViewer.setVisible(true);
										}else{
											thread1.interrupt();
											jLabelLoad.setVisible(false);
											JOptionPane.showMessageDialog(null, "No existen Datos para Visualizar", "Sin Datos", JOptionPane.INFORMATION_MESSAGE);
										}
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

	private void setItemInJComboBoxListadoPrecios(){
		jComboBoxListasPrecios.removeAllItems();
		ArrayList<ListaPrecio> listasPrecios = listaPrecioLogica.getListListaPrecio();
		Iterator<ListaPrecio> it = listasPrecios.iterator();
		while(it.hasNext()){
			ListaPrecio lp = (ListaPrecio)it.next();
			if(listaPrecio == null)listaPrecio = lp;
			jComboBoxListasPrecios.addItem(lp);
		}
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"

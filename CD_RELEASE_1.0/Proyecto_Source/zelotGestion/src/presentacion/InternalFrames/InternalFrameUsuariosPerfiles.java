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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import logica.PerfilLogica;
import logica.UsuarioLogica;
import verificadores.MyPlainDocument;

import complementos.MyJtable;
import complementos.MyTableModel;
import complementos.StringMD;

import dominio.Perfil;
import dominio.Permiso;
import dominio.Usuario;

public class InternalFrameUsuariosPerfiles extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTabbedPane jTabbedPaneUsuariosPerfiles = null;
	private JPanel jPanelUsuarios = null;
	private JPanel jPanelPerfiles = null;
	private JPanel jPanelPermisos = null;
	private JCheckBox jCheckBox1000 = null;
	private JCheckBox jCheckBox10001 = null;
	private JCheckBox jCheckBox10002 = null;
	private JCheckBox jCheckBox10003 = null;
	private JCheckBox jCheckBox10004 = null;
	private JCheckBox jCheckBox10005 = null;
	private JCheckBox jCheckBox10006 = null;
	private JCheckBox jCheckBox10007 = null;
	private JCheckBox jCheckBox2000 = null;
	private JCheckBox jCheckBox20001 = null;
	private JCheckBox jCheckBox20002 = null;
	private JCheckBox jCheckBox20003 = null;
	private JCheckBox jCheckBox3000 = null;
	private JCheckBox jCheckBox4000 = null;
	private JCheckBox jCheckBox5000 = null;
	private JCheckBox jCheckBox6000 = null;
	private JCheckBox jCheckBox6001 = null;
	private JCheckBox jCheckBox6002 = null;
	private JCheckBox jCheckBox6003 = null;
	private JCheckBox jCheckBox6004 = null;
	private JCheckBox jCheckBox7000 = null;
	private JToolBar jJToolBarPermisos = null;
	private JScrollPane jScrollPaneListUsuarios = null;
	private JTable jTableUsuarios = null;
	private JScrollPane jScrollPaneListPerfiles = null;
	private JTable jTablePerfiles = null;
	private JLabel jLabelNomUsuario = null;
	private JLabel jLabelApeUsuario = null;
	private JLabel jLabelApodoUsuario = null;
	private JLabel jLabelPasswordUsuario = null;
	private JLabel jLabelMailUsuario = null;
	private JTextField jTextFieldNomUsuario = null;
	private JTextField jTextFieldApeUsuario = null;
	private JTextField jTextFieldApodoUsuario = null;
	private JTextField jTextFieldMailUsuario = null;
	private JPasswordField jPasswordFieldPassUsuario = null;
	private JComboBox jComboBoxPerfilUsuario = null;
	private JLabel jLabelPerfilUsuario = null;
	private JButton jButtonAddUsuario = null;
	private JButton jButtonUpdateUsuario = null;
	private JButton jButtonDeleteUsuario = null;
	private JButton jButtonUsuarioGrabar = null;
	private JButton jButtonUsuarioCancelar = null;
	private JButton jButtonAddPerfil = null;
	private JButton jButtonUpdatePerfil = null;
	private JButton jButtonDeletePerfil = null;
	private JButton jButtonPerfilGrabar = null;
	private JButton jButtonPerfilCancelar = null;
	private JLabel jLabelNombrePerfil = null;
	private JTextField jTextFieldNombrePerfil = null;


	private Usuario usuario = null;
	private Perfil perfil = null;
	private UsuarioLogica usuarioLogica = UsuarioLogica.getInstance();
	private PerfilLogica perfilLogica = PerfilLogica.getInstance();
	private MyTableModel myTableModelUsuarios = null;
	private MyTableModel myTableModelPerfiles = null;
	private TableRowSorter<TableModel> tableRowSorterUsuarios = null;
	private TableRowSorter<TableModel> tableRowSorterPerfiles = null;
	private ArrayList<JCheckBox> arrayLisCheckBox = null;


	/**
	 * @param owner
	 */
	public InternalFrameUsuariosPerfiles() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(652, 518);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Usuarios y Perfiles");
		this.setResizable(false);
		this.setClosable(true);
		URL url = getClass().getResource("/ZelotGestionLogo2.png");
		this.setFrameIcon(new ImageIcon(url));
		this.setContentPane(getJContentPane());
		jTabbedPaneUsuariosPerfiles.setSelectedIndex(0);
		setArrayListJCheckBox();
		setEnableButtonsPerfiles(true, false, false, false, false);
		setEnableButtonsUsuarios(true, false, false, false, false);
		resetCamposPerfil();
		resetCamposUsuario();
		setEnableCamposPerfiles(false, true, false);
		setEnableCamposUsuarios(false, true);

	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJTabbedPaneUsuariosPerfiles(), null);
			jContentPane.add(getJPanelPermisos(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTabbedPaneUsuariosPerfiles
	 *
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getJTabbedPaneUsuariosPerfiles() {
		if (jTabbedPaneUsuariosPerfiles == null) {
			jTabbedPaneUsuariosPerfiles = new JTabbedPane();
			jTabbedPaneUsuariosPerfiles.setBounds(new Rectangle(5, 5, 400, 470));
			jTabbedPaneUsuariosPerfiles.addTab("Usuarios", null, getJPanelUsuarios(), null);
			jTabbedPaneUsuariosPerfiles.addTab("Perfiles", null, getJPanelPerfiles(), null);
			jTabbedPaneUsuariosPerfiles.setIconAt(0, new ImageIcon(getClass().getResource("/users.png")));
			jTabbedPaneUsuariosPerfiles.setIconAt(1, new ImageIcon(getClass().getResource("/traffic_light.png")));
		}
		return jTabbedPaneUsuariosPerfiles;
	}

	/**
	 * This method initializes jPanelUsuarios
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelUsuarios() {
		if (jPanelUsuarios == null) {
			jLabelPerfilUsuario = new JLabel();
			jLabelPerfilUsuario.setBounds(new Rectangle(282, 261, 38, 20));
			jLabelPerfilUsuario.setText("Perfil");
			jLabelMailUsuario = new JLabel();
			jLabelMailUsuario.setBounds(new Rectangle(5, 360, 30, 20));
			jLabelMailUsuario.setText("Email:");
			jLabelPasswordUsuario = new JLabel();
			jLabelPasswordUsuario.setBounds(new Rectangle(5, 335, 80, 20));
			jLabelPasswordUsuario.setText("Contraseña:");
			jLabelApodoUsuario = new JLabel();
			jLabelApodoUsuario.setBounds(new Rectangle(5, 310, 120, 20));
			jLabelApodoUsuario.setText("Nombre Usuario:");
			jLabelApeUsuario = new JLabel();
			jLabelApeUsuario.setBounds(new Rectangle(5, 285, 80, 20));
			jLabelApeUsuario.setText("Apellído:");
			jLabelNomUsuario = new JLabel();
			jLabelNomUsuario.setBounds(new Rectangle(5, 260, 80, 20));
			jLabelNomUsuario.setText("Nombre:");
			jPanelUsuarios = new JPanel();
			jPanelUsuarios.setName("Usuarios");
			jPanelUsuarios.setLayout(null);
			jPanelUsuarios.add(getJScrollPaneListUsuarios(), null);
			jPanelUsuarios.add(jLabelNomUsuario, null);
			jPanelUsuarios.add(jLabelApeUsuario, null);
			jPanelUsuarios.add(jLabelApodoUsuario, null);
			jPanelUsuarios.add(jLabelPasswordUsuario, null);
			jPanelUsuarios.add(jLabelMailUsuario, null);
			jPanelUsuarios.add(jLabelPerfilUsuario, null);
			jPanelUsuarios.add(getJTextFieldNomUsuario(), null);
			jPanelUsuarios.add(getJTextFieldApeUsuario(), null);
			jPanelUsuarios.add(getJTextFieldApodoUsuario(), null);
			jPanelUsuarios.add(getJTextFieldMailUsuario(), null);
			jPanelUsuarios.add(getJPasswordFieldPassUsuario(), null);
			jPanelUsuarios.add(getJComboBoxPerfilUsuario(), null);
			jPanelUsuarios.add(getJButtonAddUsuario(), null);
			jPanelUsuarios.add(getJButtonUpdateUsuario(), null);
			jPanelUsuarios.add(getJButtonDeleteUsuario(), null);
			jPanelUsuarios.add(getJButtonUsuarioGrabar(), null);
			jPanelUsuarios.add(getJButtonUsuarioCancelar(), null);
		}
		return jPanelUsuarios;
	}

	/**
	 * This method initializes jPanelPerfiles
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelPerfiles() {
		if (jPanelPerfiles == null) {
			jLabelNombrePerfil = new JLabel();
			jLabelNombrePerfil.setBounds(new Rectangle(10, 350, 80, 20));
			jLabelNombrePerfil.setText("Perfil:");
			jPanelPerfiles = new JPanel();
			jPanelPerfiles.setLayout(null);
			jPanelPerfiles.add(getJScrollPaneListPerfiles(), null);
			jPanelPerfiles.add(getJButtonAddPerfil(), null);
			jPanelPerfiles.add(getJButtonUpdatePerfil(), null);
			jPanelPerfiles.add(getJButtonDeletePerfil(), null);
			jPanelPerfiles.add(jLabelNombrePerfil, null);
			jPanelPerfiles.add(getJTextFieldNombrePerfil(), null);
		}
		return jPanelPerfiles;
	}

	/**
	 * This method initializes jPanelPermisos
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelPermisos() {
		if (jPanelPermisos == null) {
			jPanelPermisos = new JPanel();
			jPanelPermisos.setLayout(null);
			jPanelPermisos.setBounds(new Rectangle(408, 26, 230, 450));
			jPanelPermisos.add(getJCheckBox1000(), null);
			jPanelPermisos.add(getJCheckBox10001(), null);
			jPanelPermisos.add(getJCheckBox10002(), null);
			jPanelPermisos.add(getJCheckBox10003(), null);
			jPanelPermisos.add(getJCheckBox10004(), null);
			jPanelPermisos.add(getJCheckBox10005(), null);
			jPanelPermisos.add(getJCheckBox10006(), null);
			jPanelPermisos.add(getJCheckBox10007(), null);
			jPanelPermisos.add(getJCheckBox2000(), null);
			jPanelPermisos.add(getJCheckBox20001(), null);
			jPanelPermisos.add(getJCheckBox20002(), null);
			jPanelPermisos.add(getJCheckBox20003(), null);
			jPanelPermisos.add(getJCheckBox3000(), null);
			jPanelPermisos.add(getJCheckBox4000(), null);
			jPanelPermisos.add(getJCheckBox5000(), null);
			jPanelPermisos.add(getJCheckBox6000(), null);
			jPanelPermisos.add(getJCheckBox6001(), null);
			jPanelPermisos.add(getJCheckBox6002(), null);
			jPanelPermisos.add(getJCheckBox6003(), null);
			jPanelPermisos.add(getJCheckBox6004(), null);
			jPanelPermisos.add(getJCheckBox7000(), null);
			jPanelPermisos.add(getJJToolBarPermisos(), null);
		}
		return jPanelPermisos;
	}

	/**
	 * This method initializes jCheckBox1000
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox1000() {
		if (jCheckBox1000 == null) {
			jCheckBox1000 = new JCheckBox();
			jCheckBox1000.setBounds(new Rectangle(10, 5, 200, 15));
			jCheckBox1000.setText("<html><b><font color='blue'>Ajustes y Configuración</font></b></html>");
			jCheckBox1000.setName("1000");
			System.out.println("NOMBRE DE CHECKBOX: " + jCheckBox1000.getName());
		}
		return jCheckBox1000;
	}

	/**
	 * This method initializes jCheckBox10001
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox10001() {
		if (jCheckBox10001 == null) {
			jCheckBox10001 = new JCheckBox();
			jCheckBox10001.setBounds(new Rectangle(10, 20, 200, 15));
			jCheckBox10001.setName("1001");
			jCheckBox10001.setText("   Listas de Precios");
		}
		return jCheckBox10001;
	}

	/**
	 * This method initializes jCheckBox10002
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox10002() {
		if (jCheckBox10002 == null) {
			jCheckBox10002 = new JCheckBox();
			jCheckBox10002.setBounds(new Rectangle(10, 35, 200, 15));
			jCheckBox10002.setName("1002");
			jCheckBox10002.setText("   Condiciones de Venta");
		}
		return jCheckBox10002;
	}

	/**
	 * This method initializes jCheckBox10003
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox10003() {
		if (jCheckBox10003 == null) {
			jCheckBox10003 = new JCheckBox();
			jCheckBox10003.setBounds(new Rectangle(10, 50, 200, 15));
			jCheckBox10003.setName("1003");
			jCheckBox10003.setText("   Provincias");
		}
		return jCheckBox10003;
	}

	/**
	 * This method initializes jCheckBox10004
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox10004() {
		if (jCheckBox10004 == null) {
			jCheckBox10004 = new JCheckBox();
			jCheckBox10004.setBounds(new Rectangle(10, 65, 200, 15));
			jCheckBox10004.setName("1004");
			jCheckBox10004.setText("   Localidades");
		}
		return jCheckBox10004;
	}

	/**
	 * This method initializes jCheckBox10005
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox10005() {
		if (jCheckBox10005 == null) {
			jCheckBox10005 = new JCheckBox();
			jCheckBox10005.setBounds(new Rectangle(10, 80, 200, 15));
			jCheckBox10005.setName("1005");
			jCheckBox10005.setText("   Vendedores");
		}
		return jCheckBox10005;
	}

	/**
	 * This method initializes jCheckBox10006
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox10006() {
		if (jCheckBox10006 == null) {
			jCheckBox10006 = new JCheckBox();
			jCheckBox10006.setBounds(new Rectangle(10, 95, 200, 15));
			jCheckBox10006.setName("1006");
			jCheckBox10006.setText("   Condiciones IVA");
		}
		return jCheckBox10006;
	}

	/**
	 * This method initializes jCheckBox10007
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox10007() {
		if (jCheckBox10007 == null) {
			jCheckBox10007 = new JCheckBox();
			jCheckBox10007.setBounds(new Rectangle(10, 110, 200, 15));
			jCheckBox10007.setName("1007");
			jCheckBox10007.setText("   Usuarios y Mantenimiento");
		}
		return jCheckBox10007;
	}

	/**
	 * This method initializes jCheckBox2000
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox2000() {
		if (jCheckBox2000 == null) {
			jCheckBox2000 = new JCheckBox();
			jCheckBox2000.setBounds(new Rectangle(10, 125, 200, 15));
			jCheckBox2000.setName("2000");
			jCheckBox2000.setText("<html><b><font color='blue'>Ventas</font></b></html>");
		}
		return jCheckBox2000;
	}

	/**
	 * This method initializes jCheckBox20001
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox20001() {
		if (jCheckBox20001 == null) {
			jCheckBox20001 = new JCheckBox();
			jCheckBox20001.setBounds(new Rectangle(10, 140, 200, 15));
			jCheckBox20001.setName("2001");
			jCheckBox20001.setText("  Clientes");
		}
		return jCheckBox20001;
	}

	/**
	 * This method initializes jCheckBox20002
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox20002() {
		if (jCheckBox20002 == null) {
			jCheckBox20002 = new JCheckBox();
			jCheckBox20002.setBounds(new Rectangle(10, 155, 200, 15));
			jCheckBox20002.setName("2002");
			jCheckBox20002.setText("   Facturación");
		}
		return jCheckBox20002;
	}

	/**
	 * This method initializes jCheckBox20003
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox20003() {
		if (jCheckBox20003 == null) {
			jCheckBox20003 = new JCheckBox();
			jCheckBox20003.setBounds(new Rectangle(10, 170, 200, 15));
			jCheckBox20003.setName("2003");
			jCheckBox20003.setText("   Cuentas Corrientes");
		}
		return jCheckBox20003;
	}

	/**
	 * This method initializes jCheckBox3000
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox3000() {
		if (jCheckBox3000 == null) {
			jCheckBox3000 = new JCheckBox();
			jCheckBox3000.setBounds(new Rectangle(10, 185, 200, 15));
			jCheckBox3000.setName("3000");
			jCheckBox3000.setText("<html><b><font color='blue'>Proveedores</font></b></html>");
		}
		return jCheckBox3000;
	}

	/**
	 * This method initializes jCheckBox4000
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox4000() {
		if (jCheckBox4000 == null) {
			jCheckBox4000 = new JCheckBox();
			jCheckBox4000.setBounds(new Rectangle(10, 200, 200, 15));
			jCheckBox4000.setName("4000");
			jCheckBox4000.setText("<html><b><font color='blue'>Inventario</font></b></html>");
		}
		return jCheckBox4000;
	}

	/**
	 * This method initializes jCheckBox5000
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox5000() {
		if (jCheckBox5000 == null) {
			jCheckBox5000 = new JCheckBox();
			jCheckBox5000.setBounds(new Rectangle(10, 215, 200, 15));
			jCheckBox5000.setName("5000");
			jCheckBox5000.setText("<html><b><font color='blue'>Caja</font></b></html>");
		}
		return jCheckBox5000;
	}

	/**
	 * This method initializes jCheckBox6000
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox6000() {
		if (jCheckBox6000 == null) {
			jCheckBox6000 = new JCheckBox();
			jCheckBox6000.setBounds(new Rectangle(10, 230, 200, 15));
			jCheckBox6000.setName("6000");
			jCheckBox6000.setText("<html><b><font color='blue'>Listados y Reportes</font></b></html>");
		}
		return jCheckBox6000;
	}

	/**
	 * This method initializes jCheckBox6001
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox6001() {
		if (jCheckBox6001 == null) {
			jCheckBox6001 = new JCheckBox();
			jCheckBox6001.setBounds(new Rectangle(10, 245, 300, 15));
			jCheckBox6001.setName("6001");
			jCheckBox6001.setText("   Listado de Productos");
		}
		return jCheckBox6001;
	}

	/**
	 * This method initializes jCheckBox6002
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox6002() {
		if (jCheckBox6002 == null) {
			jCheckBox6002 = new JCheckBox();
			jCheckBox6002.setBounds(new Rectangle(10, 260, 200, 15));
			jCheckBox6002.setName("6002");
			jCheckBox6002.setText("   Listado de Clientes");
		}
		return jCheckBox6002;
	}

	/**
	 * This method initializes jCheckBox6003
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox6003() {
		if (jCheckBox6003 == null) {
			jCheckBox6003 = new JCheckBox();
			jCheckBox6003.setBounds(new Rectangle(10, 275, 200, 15));
			jCheckBox6003.setName("6003");
			jCheckBox6003.setText("   Listado de Proveedores");
		}
		return jCheckBox6003;
	}

	/**
	 * This method initializes jCheckBox6004
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox6004() {
		if (jCheckBox6004 == null) {
			jCheckBox6004 = new JCheckBox();
			jCheckBox6004.setBounds(new Rectangle(10, 290, 200, 15));
			jCheckBox6004.setName("6004");
			jCheckBox6004.setText("   Reporte de Ventas");
		}
		return jCheckBox6004;
	}

	/**
	 * This method initializes jCheckBox7000
	 *
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getJCheckBox7000() {
		if (jCheckBox7000 == null) {
			jCheckBox7000 = new JCheckBox();
			jCheckBox7000.setBounds(new Rectangle(10, 305, 200, 15));
			jCheckBox7000.setName("7000");
			jCheckBox7000.setText("<html><b><font color='blue'>Estadísticas</font></b></html>");
		}
		return jCheckBox7000;
	}

	/**
	 * This method initializes jJToolBarPermisos
	 *
	 * @return javax.swing.JToolBar
	 */
	private JToolBar getJJToolBarPermisos() {
		if (jJToolBarPermisos == null) {
			jJToolBarPermisos = new JToolBar();
			jJToolBarPermisos.setFloatable(false);
			jJToolBarPermisos.setBounds(new Rectangle(-1, 419, 230, 30));
			jJToolBarPermisos.add(getJButtonPerfilGrabar());
			jJToolBarPermisos.add(getJButtonPerfilCancelar());
		}
		return jJToolBarPermisos;
	}

	/**
	 * This method initializes jScrollPaneListUsuarios
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneListUsuarios() {
		if (jScrollPaneListUsuarios == null) {
			jScrollPaneListUsuarios = new JScrollPane();
			jScrollPaneListUsuarios.setBounds(new Rectangle(5, 5, 385, 250));
			jScrollPaneListUsuarios.setViewportView(getJTableUsuarios());
		}
		return jScrollPaneListUsuarios;
	}

	/**
	 * This method initializes jTableUsuarios
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTableUsuarios() {
		if (jTableUsuarios == null) {
			jTableUsuarios = new MyJtable(getMyTableModelUsuarios());
			jTableUsuarios.setRowSorter(geTableRowSorterUsuarios());
			jTableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableUsuarios.setRowHeight(20);
			setItemsInJtableUsuarios();
			jTableUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableUsuarios.setAutoscrolls(true);
			jTableUsuarios.setShowVerticalLines(true);
			jTableUsuarios.setShowHorizontalLines(true);
			jTableUsuarios.setVisible(true);
			jTableUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTableUsuarios.isEnabled()){
						usuario = getSelectedElementUsuarios();
						if(usuario != null){
							setCamposSelectedElementUsuario(usuario);
							setEnableButtonsUsuarios(true, true, true, false, false);

						}
					}
				}
			});
			jTableUsuarios.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
						if(jTableUsuarios.isEnabled()){
							usuario = getSelectedElementUsuarios();
							if(usuario != null){
								setCamposSelectedElementUsuario(usuario);
								setEnableButtonsUsuarios(true, true, true, false, false);

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
		return jTableUsuarios;
	}

	/*
	 * devuelve el modelo de tabla para crear el jtable.
	 */
	private MyTableModel getMyTableModelUsuarios(){
		if(myTableModelUsuarios == null){
			myTableModelUsuarios = new MyTableModel();
		}
		return myTableModelUsuarios;
	}

	/* DEVUELVE EL RowSorter de la tabla. para organizar los elementos de la misma*/
	private TableRowSorter<TableModel> geTableRowSorterUsuarios(){
		if(tableRowSorterUsuarios == null){
			tableRowSorterUsuarios = new TableRowSorter<TableModel>(getMyTableModelUsuarios());
		}
		return tableRowSorterUsuarios;
	}

	/**
	 * This method initializes jScrollPaneListPerfiles
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneListPerfiles() {
		if (jScrollPaneListPerfiles == null) {
			jScrollPaneListPerfiles = new JScrollPane();
			jScrollPaneListPerfiles.setBounds(new Rectangle(5, 5, 385, 340));
			jScrollPaneListPerfiles.setViewportView(getJTablePerfiles());
		}
		return jScrollPaneListPerfiles;
	}

	/**
	 * This method initializes jTablePerfiles
	 *
	 * @return javax.swing.JTable
	 */
	private JTable getJTablePerfiles() {
		if (jTablePerfiles == null) {
			jTablePerfiles = new MyJtable(getMyTableModelPerfiles());
			jTablePerfiles.setRowSorter(geTableRowSorterPerfiles());
			jTablePerfiles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTablePerfiles.setRowHeight(20);
			setItemsInJtablePerfiles();
			jTablePerfiles.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTablePerfiles.setAutoscrolls(true);
			jTablePerfiles.setShowVerticalLines(true);
			jTablePerfiles.setShowHorizontalLines(true);
			jTablePerfiles.setVisible(true);
			jTablePerfiles.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(jTablePerfiles.isEnabled()){
						perfil = getSelectedElementPerfiles();
						if(perfil != null){
							setCamposSelectedElementPerfil(perfil);
							setEnableButtonsPerfiles(true, true, true, false, false);

						}
					}
				}
			});
			jTablePerfiles.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if(e.getKeyCode() == 38 || e.getKeyCode() == 40 || e.getKeyCode() == 9 || e.getKeyCode() == 10){
						if(jTablePerfiles.isEnabled()){
							perfil = getSelectedElementPerfiles();
							if(perfil != null){
								setCamposSelectedElementPerfil(perfil);
								setEnableButtonsPerfiles(true, true, true, false, false);

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
		return jTablePerfiles;
	}

	/*
	 * devuelve el modelo de tabla para crear el jtable.
	 */
	private MyTableModel getMyTableModelPerfiles(){
		if(myTableModelPerfiles == null){
			myTableModelPerfiles = new MyTableModel();
		}
		return myTableModelPerfiles;
	}

	/* DEVUELVE EL RowSorter de la tabla. para organizar los elementos de la misma*/
	private TableRowSorter<TableModel> geTableRowSorterPerfiles(){
		if(tableRowSorterPerfiles == null){
			tableRowSorterPerfiles = new TableRowSorter<TableModel>(getMyTableModelPerfiles());
		}
		return tableRowSorterPerfiles;
	}

	/**
	 * This method initializes jTextFieldNomUsuario
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNomUsuario() {
		if (jTextFieldNomUsuario == null) {
			jTextFieldNomUsuario = new JTextField();
			jTextFieldNomUsuario.setBounds(new Rectangle(110, 260, 100, 20));
			jTextFieldNomUsuario.setDocument(new MyPlainDocument(jTextFieldNomUsuario,50,null,true));
		}
		return jTextFieldNomUsuario;
	}

	/**
	 * This method initializes jTextFieldApeUsuario
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldApeUsuario() {
		if (jTextFieldApeUsuario == null) {
			jTextFieldApeUsuario = new JTextField();
			jTextFieldApeUsuario.setBounds(new Rectangle(110, 285, 100, 20));
			jTextFieldApeUsuario.setDocument(new MyPlainDocument(jTextFieldApeUsuario,50,null,true));
		}
		return jTextFieldApeUsuario;
	}

	/**
	 * This method initializes jTextFieldApodoUsuario
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldApodoUsuario() {
		if (jTextFieldApodoUsuario == null) {
			jTextFieldApodoUsuario = new JTextField();
			jTextFieldApodoUsuario.setBounds(new Rectangle(110, 310, 100, 20));
			jTextFieldApodoUsuario.setDocument(new MyPlainDocument(jTextFieldApodoUsuario,50,null,true));
		}
		return jTextFieldApodoUsuario;
	}

	/**
	 * This method initializes jTextFieldMailUsuario
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldMailUsuario() {
		if (jTextFieldMailUsuario == null) {
			jTextFieldMailUsuario = new JTextField();
			jTextFieldMailUsuario.setBounds(new Rectangle(49, 360, 161, 20));
			jTextFieldMailUsuario.setDocument(new MyPlainDocument(jTextFieldMailUsuario,100,null,false));
		}
		return jTextFieldMailUsuario;
	}

	/**
	 * This method initializes jPasswordFieldPassUsuario
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordFieldPassUsuario() {
		if (jPasswordFieldPassUsuario == null) {
			jPasswordFieldPassUsuario = new JPasswordField();
			jPasswordFieldPassUsuario.setBounds(new Rectangle(80, 335, 130, 20));
			jPasswordFieldPassUsuario.setDocument(new MyPlainDocument(jPasswordFieldPassUsuario,100,null,false));
		}
		return jPasswordFieldPassUsuario;
	}

	/**
	 * This method initializes jComboBoxPerfilUsuario
	 *
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJComboBoxPerfilUsuario() {
		if (jComboBoxPerfilUsuario == null) {
			jComboBoxPerfilUsuario = new JComboBox();
			jComboBoxPerfilUsuario.setBounds(new Rectangle(215, 285, 174, 20));
			jComboBoxPerfilUsuario.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					System.out.println("itemStateChanged()"); // TODO Auto-generated Event stub itemStateChanged()
					if(jComboBoxPerfilUsuario.getSelectedItem() != null){
						setCheckedAllJcheckBoxByPerfil((Perfil)jComboBoxPerfilUsuario.getSelectedItem(), arrayLisCheckBox);
					}
				}
			});
			setItemsjComboBoxPerfilUsuario();
		}
		return jComboBoxPerfilUsuario;
	}

	/**
	 * This method initializes jButtonAddUsuario
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddUsuario() {
		if (jButtonAddUsuario == null) {
			jButtonAddUsuario = new JButton();
			jButtonAddUsuario.setBounds(new Rectangle(10, 399, 110, 30));
			jButtonAddUsuario.setText("Agregar");
			jButtonAddUsuario.setIcon(new ImageIcon(getClass().getResource("/Symbol-Add_24x24-32.png")));
			jButtonAddUsuario.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					setEnableCamposUsuarios(true, false);
					setEnableButtonsUsuarios(false, false, false, true, true);
					usuario = null;
					resetCamposUsuario();
					jTextFieldNomUsuario.requestFocus();
				}
			});
		}
		return jButtonAddUsuario;
	}

	/**
	 * This method initializes jButtonUpdateUsuario
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonUpdateUsuario() {
		if (jButtonUpdateUsuario == null) {
			jButtonUpdateUsuario = new JButton();
			jButtonUpdateUsuario.setBounds(new Rectangle(125, 399, 120, 30));
			jButtonUpdateUsuario.setText("Modificar");
			jButtonUpdateUsuario.setIcon(new ImageIcon(getClass().getResource("/Edit_24x24-32.png")));
			jButtonUpdateUsuario.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(usuario != null){
						setEnableCamposUsuarios(true, false);
						setEnableButtonsUsuarios(false, false, false, true, true);
					}
				}
			});
		}
		return jButtonUpdateUsuario;
	}

	/**
	 * This method initializes jButtonDeleteUsuario
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonDeleteUsuario() {
		if (jButtonDeleteUsuario == null) {
			jButtonDeleteUsuario = new JButton();
			jButtonDeleteUsuario.setBounds(new Rectangle(250, 399, 120, 30));
			jButtonDeleteUsuario.setText("Eliminar");
			jButtonDeleteUsuario.setIcon(new ImageIcon(getClass().getResource("/Cut_24x24-32.png")));
			jButtonDeleteUsuario.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int action = JOptionPane.showConfirmDialog(null, "Está Seguro que desea eliminar el usuario seleccionado?", "Eliminar Usuario", JOptionPane.OK_CANCEL_OPTION);
					if(action == 0){
						boolean estado = usuarioLogica.deleteUsuario(usuario.getCodigo());
						if(estado){
							JOptionPane.showMessageDialog(null, "Operación finalizada con Éxito", "Usuario", JOptionPane.INFORMATION_MESSAGE);
							setItemsInJtableUsuarios();
							setEnableButtonsUsuarios(true, false, false, false, false);
							setEnableCamposUsuarios(false, true);
							resetCamposUsuario();
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación", "Error", JOptionPane.ERROR_MESSAGE);
							setEnableButtonsUsuarios(true, false, false, false, false);
							setEnableCamposUsuarios(false, true);
							resetCamposUsuario();
						}
					}
				}
			});
		}
		return jButtonDeleteUsuario;
	}

	/**
	 * This method initializes jButtonUsuarioGrabar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonUsuarioGrabar() {
		if (jButtonUsuarioGrabar == null) {
			jButtonUsuarioGrabar = new JButton();
			jButtonUsuarioGrabar.setBounds(new Rectangle(250, 310, 120, 30));
			jButtonUsuarioGrabar.setText("Grabar");
			jButtonUsuarioGrabar.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonUsuarioGrabar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					boolean estado = false;
					if(usuario == null){
						usuario = new Usuario();
						setDatosUsuario();
						estado = usuarioLogica.addUsuario(usuario);
					}else{
						setDatosUsuario();
						estado = usuarioLogica.updateUsuario(usuario);
					}
						if(estado){
							JOptionPane.showMessageDialog(null, "Operación finalizada con Éxito", "Usuario", JOptionPane.INFORMATION_MESSAGE);
							setItemsInJtableUsuarios();
							setEnableButtonsUsuarios(true, false, false, false, false);
							setEnableCamposUsuarios(false, true);
							resetCamposUsuario();
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación", "Error", JOptionPane.ERROR_MESSAGE);
							setEnableButtonsUsuarios(true, false, false, false, false);
							setEnableCamposUsuarios(false, true);
							resetCamposUsuario();
						}
				}
			});
		}
		return jButtonUsuarioGrabar;
	}

	/**
	 * This method initializes jButtonUsuarioCancelar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonUsuarioCancelar() {
		if (jButtonUsuarioCancelar == null) {
			jButtonUsuarioCancelar = new JButton();
			jButtonUsuarioCancelar.setBounds(new Rectangle(250, 345, 120, 30));
			jButtonUsuarioCancelar.setText("Cancelar");
			jButtonUsuarioCancelar.setIcon(new ImageIcon(getClass().getResource("/Symbol-Stop_24x24-32.png")));
			jButtonUsuarioCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					setEnableButtonsUsuarios(true, false, false, false, false);
					setEnableCamposUsuarios(false, true);
					resetCamposUsuario();
				}
			});
		}
		return jButtonUsuarioCancelar;
	}

	/**
	 * This method initializes jButtonAddPerfil
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonAddPerfil() {
		if (jButtonAddPerfil == null) {
			jButtonAddPerfil = new JButton();
			jButtonAddPerfil.setBounds(new Rectangle(9, 393, 110, 30));
			jButtonAddPerfil.setText("Agregar");
			jButtonAddPerfil.setIcon(new ImageIcon(getClass().getResource("/Symbol-Add_24x24-32.png")));
			jButtonAddPerfil.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					setEnableCamposPerfiles(true, false, true);
					setEnableButtonsPerfiles(false, false, false, true, true);
					perfil = null;
					resetCamposPerfil();
					jTextFieldNombrePerfil.requestFocus();
				}
			});
		}
		return jButtonAddPerfil;
	}

	/**
	 * This method initializes jButtonUpdatePerfil
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonUpdatePerfil() {
		if (jButtonUpdatePerfil == null) {
			jButtonUpdatePerfil = new JButton();
			jButtonUpdatePerfil.setBounds(new Rectangle(129, 393, 120, 30));
			jButtonUpdatePerfil.setText("Modificar");
			jButtonUpdatePerfil.setIcon(new ImageIcon(getClass().getResource("/Edit_24x24-32.png")));
			jButtonUpdatePerfil.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					if(perfil != null){
						setEnableCamposPerfiles(true, false, true);
						setEnableButtonsPerfiles(false, false, false, true, true);
					}
				}
			});
		}
		return jButtonUpdatePerfil;
	}

	/**
	 * This method initializes jButtonDeletePerfil
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonDeletePerfil() {
		if (jButtonDeletePerfil == null) {
			jButtonDeletePerfil = new JButton();
			jButtonDeletePerfil.setBounds(new Rectangle(258, 393, 120, 30));
			jButtonDeletePerfil.setText("Eliminar");
			jButtonDeletePerfil.setIcon(new ImageIcon(getClass().getResource("/Cut_24x24-32.png")));
			jButtonDeletePerfil.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					int action = JOptionPane.showConfirmDialog(null, "Está Seguro que desea eliminar el perfil seleccionado?", "Eliminar Perfil", JOptionPane.OK_CANCEL_OPTION);
					if(action == 0){
						boolean estado = perfilLogica.deletePerfil(perfil.getCodigo());
						if(estado){
							JOptionPane.showMessageDialog(null, "Operación finalizada con Éxito", "Usuario", JOptionPane.INFORMATION_MESSAGE);
							setItemsInJtablePerfiles();
							setEnableButtonsPerfiles(true, false, false, false, false);
							setEnableCamposPerfiles(false, true, false);
							resetCamposPerfil();
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación", "Error", JOptionPane.ERROR_MESSAGE);
							setEnableButtonsPerfiles(true, false, false, false, false);
							setEnableCamposPerfiles(false, true, false);
							resetCamposPerfil();
						}
					}
				}
			});
		}
		return jButtonDeletePerfil;
	}

	/**
	 * This method initializes jButtonPerfilGrabar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPerfilGrabar() {
		if (jButtonPerfilGrabar == null) {
			jButtonPerfilGrabar = new JButton();
			jButtonPerfilGrabar.setText("Grabar");
			jButtonPerfilGrabar.setPreferredSize(new Dimension(50, 30));
			jButtonPerfilGrabar.setBorderPainted(false);
			jButtonPerfilGrabar.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonPerfilGrabar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					boolean estado = false;
					if(perfil == null){
						perfil = new Perfil();
						setDatosPerfil();
						estado = perfilLogica.addPerfil(perfil);
					}else{
						//ArrayList<Permiso> permisosOld = perfil.getPermisos();
						setDatosPerfil();
						estado = perfilLogica.updatePerfil(perfil);
					}
						if(estado){
							JOptionPane.showMessageDialog(null, "Operación finalizada con Éxito", "Usuario", JOptionPane.INFORMATION_MESSAGE);
							setItemsInJtablePerfiles();
							setItemsInJtableUsuarios();
							setEnableButtonsPerfiles(true, false, false, false, false);
							setEnableCamposPerfiles(false, true, false);
							setItemsjComboBoxPerfilUsuario();
							resetCamposPerfil();
						}else{
							JOptionPane.showMessageDialog(null, "Ocurrió un Error en la Operación", "Error", JOptionPane.ERROR_MESSAGE);
							setEnableButtonsPerfiles(true, false, false, false, false);
							setEnableCamposPerfiles(false, true, false);
							resetCamposPerfil();
						}
				}
			});
		}
		return jButtonPerfilGrabar;
	}

	/**
	 * This method initializes jButtonPerfilCancelar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonPerfilCancelar() {
		if (jButtonPerfilCancelar == null) {
			jButtonPerfilCancelar = new JButton();
			jButtonPerfilCancelar.setPreferredSize(new Dimension(50, 30));
			jButtonPerfilCancelar.setBorderPainted(false);
			jButtonPerfilCancelar.setIcon(new ImageIcon(getClass().getResource("/Symbol-Stop_24x24-32.png")));
			jButtonPerfilCancelar.setText("Cancelar");
			jButtonPerfilCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					setEnableButtonsPerfiles(true, false, false, false, false);
					setEnableCamposPerfiles(false, true, false);
					resetCamposPerfil();
				}
			});
		}
		return jButtonPerfilCancelar;
	}

	/**
	 * This method initializes jTextFieldNombrePerfil
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldNombrePerfil() {
		if (jTextFieldNombrePerfil == null) {
			jTextFieldNombrePerfil = new JTextField();
			jTextFieldNombrePerfil.setBounds(new Rectangle(70, 350, 200, 20));
			jTextFieldNombrePerfil.setDocument(new MyPlainDocument(jTextFieldNombrePerfil,50,null,true));
		}
		return jTextFieldNombrePerfil;
	}



	/************** GESTION DE ITEMS DE LA TABLA *******************/

	public void setItemsInJtableUsuarios(){
		ArrayList<Usuario> usuarios = usuarioLogica.getAllUsuario();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Usuario");
		titulos.add("Perfil");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(usuarios != null){
			Iterator<Usuario> it = usuarios.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				Usuario usuario = (Usuario)it.next();
				renglon.add(String.valueOf(usuario.getCodigo()));
				renglon.add(usuario.getApodo());
				renglon.add(usuario.getPerfil().getNombre());
				registros.add(renglon);
			}
		}
		myTableModelUsuarios.setDataVector(registros, titulos);
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	private Usuario getSelectedElementUsuarios(){
		try{
			int codigo = Integer.parseInt((String)myTableModelUsuarios.getValueAt(tableRowSorterUsuarios.convertRowIndexToModel(jTableUsuarios.getSelectedRow()), 0));
			usuario = usuarioLogica.getUsuario(codigo);
			return usuario;
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}

	private void setItemsInJtablePerfiles(){
		ArrayList<Perfil> perfiles = perfilLogica.getAllPerfil();
		Vector<String> titulos = new Vector<String>();
		titulos.add("Código");
		titulos.add("Nombre");
		Vector<Vector<String>> registros = new Vector<Vector<String>>();
		Vector<String> renglon = null;
		if(perfiles != null){
			Iterator<Perfil> it = perfiles.iterator();
			while(it.hasNext()){
				renglon = new Vector<String>();
				Perfil perfil = (Perfil)it.next();
				renglon.add(String.valueOf(perfil.getCodigo()));
				renglon.add(perfil.getNombre());
				registros.add(renglon);
			}
		}
		myTableModelPerfiles.setDataVector(registros, titulos);
	}

	/*
	 * Devuelve 1 item seleccionado de la lista.
	 */
	private Perfil getSelectedElementPerfiles(){
		try{
			int codigo = Integer.parseInt((String)myTableModelPerfiles.getValueAt(tableRowSorterPerfiles.convertRowIndexToModel(jTablePerfiles.getSelectedRow()), 0));
			perfil = perfilLogica.getPerfilByCodigo(codigo);
			return perfil;
		}catch(ArrayIndexOutOfBoundsException e){
			return null;
		}
	}


	private void setCamposSelectedElementUsuario(Usuario usuario){
		jTextFieldNomUsuario.setText(usuario.getNombre());
		jTextFieldApeUsuario.setText(usuario.getApellido());
		jTextFieldApodoUsuario.setText(usuario.getApodo());
		jTextFieldMailUsuario.setText(usuario.getEmail());
		selectItemInjComboBoxPerfilUsuario(usuario.getPerfil());
		setCheckedAllJcheckBoxByPerfil(usuario.getPerfil(), arrayLisCheckBox);
	}

	private void setCamposSelectedElementPerfil(Perfil perfil){
		jTextFieldNombrePerfil.setText(perfil.getNombre());
		setCheckedAllJcheckBoxByPerfil(perfil, arrayLisCheckBox);
	}

	private void setDatosUsuario(){
		usuario.setNombre(jTextFieldNomUsuario.getText());
		usuario.setApellido(jTextFieldApeUsuario.getText());
		usuario.setApodo(jTextFieldApodoUsuario.getText());
		usuario.setEmail(jTextFieldMailUsuario.getText());
		usuario.setActivo(true);
		if(jComboBoxPerfilUsuario != null)usuario.setPerfil((Perfil)jComboBoxPerfilUsuario.getSelectedItem());
		if(!jPasswordFieldPassUsuario.getPassword().equals(""))usuario.setPassword(StringMD.getStringMessageDigest(String.valueOf(jPasswordFieldPassUsuario.getPassword()), StringMD.MD5));
	}

	private void setDatosPerfil(){
		perfil.setNombre(jTextFieldNombrePerfil.getText());
		setArrayListJCheckBox();
		if(perfil != null && arrayLisCheckBox != null){
			DecimalFormat df = new DecimalFormat("####");
			ArrayList<Permiso> permisos = new ArrayList<Permiso>();
			Permiso permiso = null;
			Iterator<JCheckBox> itC = arrayLisCheckBox.iterator();
			try{
				while(itC.hasNext()){
					JCheckBox c = (JCheckBox)itC.next();
					//if(c.isSelected()){
						permiso = new Permiso();
						permiso.setActivo(c.isSelected());
						permiso.setCodigo(df.parse(c.getName()).intValue());
						permisos.add(permiso);
					//}
				}
			}catch(ParseException pe){
				pe.printStackTrace();
			}
			perfil.setPermisos(permisos);
		}
	}


	private void resetCamposUsuario(){
		jTextFieldNomUsuario.setText(null);
		jTextFieldApeUsuario.setText(null);
		jTextFieldApodoUsuario.setText(null);
		jTextFieldMailUsuario.setText(null);
		jPasswordFieldPassUsuario.setText(null);
	}

	private void resetCamposPerfil(){
		jTextFieldNombrePerfil.setText(null);
		setCheckedAllJcheckBox(false);
	}

	private void setCheckedAllJcheckBox(boolean chek){
		Component[] component = jPanelPermisos.getComponents();
		for(int i = 0; i < component.length;i++){
			if(component[i].getClass() == JCheckBox.class){
				((JCheckBox)component[i]).setSelected(chek);
			}
		}
	}

	private void setEnableAllJcheckBox(boolean estado){
		Component[] component = jPanelPermisos.getComponents();
		for(int i = 0; i < component.length;i++){
			if(component[i].getClass() == JCheckBox.class){
				((JCheckBox)component[i]).setEnabled(estado);
			}
		}
	}

	private void setCheckedAllJcheckBoxByPerfil(Perfil perfil, ArrayList<JCheckBox> allCheckBox){
		if(perfil != null && allCheckBox != null){
			setCheckedAllJcheckBox(false);
			if(perfil.getPermisos() != null){
				DecimalFormat df = new DecimalFormat("####");
				Iterator<Permiso> itP = perfil.getPermisos().iterator();
				Iterator<JCheckBox> itC = allCheckBox.iterator();
				while(itP.hasNext()){
					Permiso p = (Permiso)itP.next();
					String nomP = df.format(p.getCodigo());
					while(itC.hasNext()){
						JCheckBox c = (JCheckBox)itC.next();
						if(c.getName().equals(nomP)){
							c.setSelected(p.isActivo());
							break;
						}
					}
				}
			}
		}
	}

	private void setArrayListJCheckBox(){
		Component[] component = jPanelPermisos.getComponents();
		arrayLisCheckBox = new ArrayList<JCheckBox>();
		for(int i = 0; i < component.length;i++){
			if(component[i].getClass() == JCheckBox.class){
				arrayLisCheckBox.add((JCheckBox)component[i]);
			}
		}
	}



	/************* GESTION DE COMBO BOX ****************/

	private void setItemsjComboBoxPerfilUsuario(){
		jComboBoxPerfilUsuario.removeAllItems();
		ArrayList<Perfil> perfiles = perfilLogica.getAllPerfil();
		Iterator<Perfil> it = perfiles.iterator();
		while(it.hasNext()){
			jComboBoxPerfilUsuario.addItem((Perfil)it.next());
		}
	}

	private void selectItemInjComboBoxPerfilUsuario(Perfil perf){
		if(perf != null){
			for(int i = 0; i < jComboBoxPerfilUsuario.getItemCount(); i++){
				Perfil perfil = (Perfil)jComboBoxPerfilUsuario.getItemAt(i);
				if(perf.getCodigo() == perfil.getCodigo()){
					jComboBoxPerfilUsuario.setSelectedItem(perfil);
					break;
				}
			}
		}
	}

	private void setEnableButtonsUsuarios(boolean btnAgregar, boolean btnModificar, boolean btnEliminar, boolean btnGrabar, boolean btnCancelar){
		jButtonAddUsuario.setEnabled(btnAgregar);
		jButtonUpdateUsuario.setEnabled(btnModificar);
		jButtonDeleteUsuario.setEnabled(btnEliminar);
		jButtonUsuarioGrabar.setEnabled(btnGrabar);
		jButtonUsuarioCancelar.setEnabled(btnCancelar);
	}
	private void setEnableButtonsPerfiles(boolean btnAgregar, boolean btnModificar, boolean btnEliminar, boolean btnGrabar, boolean btnCancelar){
		jButtonAddPerfil.setEnabled(btnAgregar);
		jButtonUpdatePerfil.setEnabled(btnModificar);
		jButtonDeletePerfil.setEnabled(btnEliminar);
		jButtonPerfilGrabar.setEnabled(btnGrabar);
		jButtonPerfilCancelar.setEnabled(btnCancelar);
	}

	private void setEnableCamposUsuarios(boolean estadoCampos, boolean estadoTable){
		jTextFieldNomUsuario.setEditable(estadoCampos);
		jTextFieldApeUsuario.setEditable(estadoCampos);
		jTextFieldApodoUsuario.setEditable(estadoCampos);
		jTextFieldMailUsuario.setEditable(estadoCampos);
		jPasswordFieldPassUsuario.setEditable(estadoCampos);
		jTableUsuarios.setEnabled(estadoTable);
	}

	private void setEnableCamposPerfiles(boolean estadoCampos, boolean estadoTable, boolean estadoPermisos){
		jTextFieldNombrePerfil.setEditable(estadoCampos);
		jTablePerfiles.setEnabled(estadoTable);
		setEnableAllJcheckBox(estadoPermisos);
	}





}  //  @jve:decl-index=0:visual-constraint="10,10"

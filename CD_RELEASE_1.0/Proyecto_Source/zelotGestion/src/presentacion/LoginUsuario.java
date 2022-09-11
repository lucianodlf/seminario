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
package presentacion;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import logica.UsuarioLogica;
import verificadores.MyPlainDocument;

import complementos.StringMD;

import dominio.Usuario;

public class LoginUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField jTextFieldUsuario = null;
	private JPasswordField jPasswordFieldPassword = null;
	private JLabel jLabelUsuario = null;
	private JLabel jLabelPassword = null;
	private JLabel jLabelTitulo = null;
	private JButton jButtonActionOk = null;
	private JButton jButtonActionCancel = null;

	private Usuario usuario = null;
	private UsuarioLogica usuarioLogica = UsuarioLogica.getInstance();  //  @jve:decl-index=0:
	private JLabel jLabelImage = null;

	/**
	 * @param owner
	 */
	public LoginUsuario() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(426, 176);
		this.setTitle("Ingreso");
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelImage = new JLabel();
			jLabelImage.setBounds(new Rectangle(10, 7, 128, 128));
			jLabelImage.setIcon(new ImageIcon(getClass().getResource("/Login Manager_128x128.png")));
			jLabelTitulo = new JLabel();
			jLabelTitulo.setBounds(new Rectangle(171, 7, 207, 30));
			jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
			jLabelTitulo.setText("<html><b><font size=5>Acceso al Sistema</font></b></html>");
			jLabelPassword = new JLabel();
			jLabelPassword.setBounds(new Rectangle(147, 75, 71, 19));
			jLabelPassword.setText("Contraseña:");
			jLabelUsuario = new JLabel();
			jLabelUsuario.setBounds(new Rectangle(147, 45, 98, 16));
			jLabelUsuario.setText("Nombre Usuario:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			jContentPane.add(getJTextFieldUsuario(), null);
			jContentPane.add(getJPasswordFieldPassword(), null);
			jContentPane.add(jLabelUsuario, null);
			jContentPane.add(jLabelPassword, null);
			jContentPane.add(jLabelTitulo, null);
			jContentPane.add(getJButtonActionOk(), null);
			jContentPane.add(getJButtonActionCancel(), null);
			jContentPane.add(jLabelImage, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextFieldUsuario
	 *
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldUsuario() {
		if (jTextFieldUsuario == null) {
			jTextFieldUsuario = new JTextField();
			jTextFieldUsuario.setBounds(new Rectangle(257, 45, 150, 20));
			jTextFieldUsuario.setDocument(new MyPlainDocument(jTextFieldUsuario,50,null,true));
		}
		return jTextFieldUsuario;
	}

	/**
	 * This method initializes jPasswordFieldPassword
	 *
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJPasswordFieldPassword() {
		if (jPasswordFieldPassword == null) {
			jPasswordFieldPassword = new JPasswordField();
			jPasswordFieldPassword.setBounds(new Rectangle(257, 75, 150, 20));
			jPasswordFieldPassword.addKeyListener(new java.awt.event.KeyListener() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					System.out.println("keyPressed()"); // TODO Auto-generated Event stub keyPressed()
					if(e.getKeyCode() == 10){
						setDatosUsuario();
						if(usuario != null){
							usuario = usuarioLogica.getUserByNameAndPass(usuario);
							if(usuario == null){
								JOptionPane.showMessageDialog(null, "El Usuario o Password no corresponden a ningun usuario del sistema","Error de Acceso",JOptionPane.ERROR_MESSAGE);
								jTextFieldUsuario.requestFocus();
							}else{
								dispose();
								setVisible(false);
								runWinPrincipal();
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
		return jPasswordFieldPassword;
	}

	/**
	 * This method initializes jButtonActionOk
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonActionOk() {
		if (jButtonActionOk == null) {
			jButtonActionOk = new JButton();
			jButtonActionOk.setBounds(new Rectangle(147, 105, 115, 30));
			jButtonActionOk.setBorderPainted(true);
			jButtonActionOk.setIcon(new ImageIcon(getClass().getResource("/Symbol-Check_24x24-32.png")));
			jButtonActionOk.setText("Aceptar");
			jButtonActionOk.setPreferredSize(new Dimension(100, 25));
			jButtonActionOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					setDatosUsuario();
					if(usuario != null){
						usuario = usuarioLogica.getUserByNameAndPass(usuario);
						if(usuario == null){
							JOptionPane.showMessageDialog(null, "El Usuario o Password no corresponden a ningun usuario del sistema","Error de Acceso",JOptionPane.ERROR_MESSAGE);
							jTextFieldUsuario.requestFocus();
						}else{
							dispose();
							setVisible(false);
							runWinPrincipal();
						}
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
			jButtonActionCancel.setBounds(new Rectangle(293, 105, 115, 30));
			jButtonActionCancel.setBorderPainted(true);
			jButtonActionCancel.setIcon(new ImageIcon(getClass().getResource("/Symbol-Stop_24x24-32.png")));
			jButtonActionCancel.setText("Cancelar");
			jButtonActionCancel.setPreferredSize(new Dimension(100, 25));
			jButtonActionCancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					dispose();
				}
			});
		}
		return jButtonActionCancel;
	}

	private void setDatosUsuario(){
		usuario = new Usuario();
		usuario.setApodo(jTextFieldUsuario.getText());
		usuario.setPassword(StringMD.getStringMessageDigest(String.valueOf(jPasswordFieldPassword.getPassword()), StringMD.MD5));
	}

	public Usuario getUsuarioActivo(){
		return usuario;
	}

	private void runWinPrincipal(){
		WinPrincipal winPrincipal = WinPrincipal.getInstance();
		winPrincipal.loginSistema(usuario);
		winPrincipal.setVisible(true);
		System.out.println("Alto: "+winPrincipal.getJJToolBarBar().getSize().height+" / Ancho: "+winPrincipal.getJJToolBarBar().getSize().width);
		winPrincipal.getJJToolBarBar().setSize(new Dimension(winPrincipal.getDimensionWinPrincipal().width, 70));
		winPrincipal.getJJToolBarBar().setLocation(new Point(3,0));
		winPrincipal.getJJToolBarBar().setRollover(true);
		winPrincipal.getJJToolBarBar().setOpaque(false);
		winPrincipal.setPositionFecha(new Point(winPrincipal.getDimensionWinPrincipal().width - 210, 70));
		winPrincipal.getJInternalFrameClock().setLocation(winPrincipal.getDimensionWinPrincipal().width - 220, 110);
		//winPrincipal.getJJToolBarBar().getComponentAtIndex(10).setLocation(winPrincipal.getJJToolBarBar().getSize().width-64,0);
	}

}  //  @jve:decl-index=0:visual-constraint="191,8"

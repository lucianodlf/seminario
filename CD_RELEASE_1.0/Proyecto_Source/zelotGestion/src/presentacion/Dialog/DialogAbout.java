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

import java.awt.Frame;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DialogAbout extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabelText1 = null;
	private JLabel jLabelText2 = null;
	private JLabel jLabelText3 = null;
	private JLabel jLabelText4 = null;
	private JButton jButtonOk = null;
	private JLabel jLabelIcon = null;
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane = null;
	/**
	 * @param owner
	 */
	public DialogAbout(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 393);
		this.setTitle("Acerca De Zelot Gestión ERP");
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setContentPane(getJContentPane());
		setTextInTextArea();
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelIcon = new JLabel();
			jLabelIcon.setBounds(new Rectangle(292, 321, 91, 35));
			jLabelIcon.setIcon(new ImageIcon(getClass().getResource("/lgplv3-88x31.png")));
			jLabelText4 = new JLabel();
			jLabelText4.setBounds(new Rectangle(0, 50, 400, 20));
			jLabelText4.setText("<html><b><font size='2'>Versión: 1.0.34 (build 0000034526-526)</font></b></html>");
			jLabelText4.setHorizontalAlignment(JLabel.CENTER);
			jLabelText3 = new JLabel();
			jLabelText3.setBounds(new Rectangle(0, 90, 400, 20));
			jLabelText3.setText("Luciano J. Delfino <luciano.dlf@gmail.com>");
			jLabelText3.setHorizontalAlignment(JLabel.CENTER);
			jLabelText1 = new JLabel();
			jLabelText1.setText("<html><b><i><font size='7' face='arial' color='black'>Zelot Gestión</font> <font size='7' face='arial' color='red'>ERP</font></i></b></html>");
			jLabelText1.setBounds(new Rectangle(0, 0, 400, 50));
			jLabelText1.setHorizontalAlignment(JLabel.CENTER);
			jLabelText2 = new JLabel();
			jLabelText2.setText("<html><b>Autor:</b></html>");
			jLabelText2.setBounds(new Rectangle(1, 70, 400, 20));
			jLabelText2.setHorizontalAlignment(JLabel.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelText1, null);
			jContentPane.add(jLabelText2, null);
			jContentPane.add(jLabelText3, null);
			jContentPane.add(jLabelText4, null);
			jContentPane.add(getJButtonOk(), null);
			jContentPane.add(jLabelIcon, null);
			jContentPane.add(getJScrollPane(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButtonOk
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonOk() {
		if (jButtonOk == null) {
			jButtonOk = new JButton();
			jButtonOk.setBounds(new Rectangle(157, 323, 87, 28));
			jButtonOk.setText("Aceptar");
			jButtonOk.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					setVisible(false);
					dispose();
				}
			});
		}
		return jButtonOk;
	}

	private void setTextInTextArea(){
		try {
			BufferedReader bf = new BufferedReader(new FileReader("licence_es.txt"));
			String text = "";
			String line = "";
			while((line = bf.readLine())!=null){
				if(line != null)text += line+"\n";
			}
			jTextArea.setText(text);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes jTextArea
	 *
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setEditable(false);
		}
		return jTextArea;
	}

	/**
	 * This method initializes jScrollPane
	 *
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(10, 115, 376, 183));
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

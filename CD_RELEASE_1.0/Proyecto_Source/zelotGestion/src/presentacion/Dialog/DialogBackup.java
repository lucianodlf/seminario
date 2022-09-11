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
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import complementos.BackUp;

public class DialogBackup extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButtonStart = null;
	private JProgressBar jProgressBar = null;
	private JButton jButtonFinish = null;

	/**
	 * @param owner
	 */
	public DialogBackup(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 132);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Respaldo de Base de Datos");
		this.setIconImage(new ImageIcon(getClass().getResource("/ZelotGestionLogo2.png")).getImage());
		this.setLocationRelativeTo(null);
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
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJButtonStart(), null);
			jContentPane.add(getJProgressBar(), null);
			jContentPane.add(getJButtonFinish(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButtonStart
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStart() {
		if (jButtonStart == null) {
			jButtonStart = new JButton();
			jButtonStart.setBounds(new Rectangle(178, 60, 100, 30));
			jButtonStart.setText("Guardar");
			jButtonStart.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					fileChooser.setName("Respaldar Base de Datos");
					fileChooser.setSelectedFile(new File("backupPrueba.sql"));
					fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
					int returnValue = fileChooser.showSaveDialog(DialogBackup.this);
					if(returnValue == JFileChooser.APPROVE_OPTION){
						File fichero = fileChooser.getSelectedFile();
						System.out.println("fichero: " + fichero.getPath());
						new BackUp().CrearBackup("localhost", "3306", "root", "$sinte34$", "zelot_gestion_db", fichero.getPath());
						startProgress();
					}
				}
			});
		}
		return jButtonStart;
	}

	public void startProgress(){
		try {
		for(int i=0;i<10000;i++){
			Thread.sleep(150);
			System.out.println(i);
			if(i<3000){
				i +=500;
			}else if(i>3000 && i<5000){
				i +=200;
			}else if(i>5000){
				i +=1000;
			}
			jProgressBar.setValue(i);
			jProgressBar.update(jProgressBar.getGraphics());
		}
		//jButtonFinalizar.setVisible(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jButtonFinish.setVisible(true);
	}

	/**
	 * This method initializes jProgressBar
	 *
	 * @return javax.swing.JProgressBar
	 */
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar(0, 10000);
			jProgressBar.setValue(0);
			jProgressBar.setStringPainted(true);
			jProgressBar.setBounds(new Rectangle(10, 10, 270, 40));
			jProgressBar.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					//System.out.println("stateChanged()"); // TODO Auto-generated Event stub stateChanged()
					//jProgressBar.repaint();
				}
			});
		}
		return jProgressBar;
	}

	/**
	 * This method initializes jButtonFinish
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonFinish() {
		if (jButtonFinish == null) {
			jButtonFinish = new JButton();
			jButtonFinish.setBounds(new Rectangle(10, 60, 100, 30));
			jButtonFinish.setVisible(false);
			jButtonFinish.setText("Finalizar");
			jButtonFinish.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					dispose();
				}
			});
		}
		return jButtonFinish;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"

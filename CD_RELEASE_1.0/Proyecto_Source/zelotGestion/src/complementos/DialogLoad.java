package complementos;

import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DialogLoad extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabelLoad = null;
	private JLabel jLabelLeyenda = null;

	/**
	 * @param owner
	 */
	public DialogLoad(Frame owner, String leyenda) {
		super(owner);
		initialize(leyenda);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize(String leyenda) {
		this.setSize(415, 45);
		this.setIconImage(null);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setModal(false);
		this.setTitle(null);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setContentPane(getJContentPane());
		jLabelLeyenda.setText(leyenda);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabelLeyenda = new JLabel();
			jLabelLeyenda.setBounds(new Rectangle(161, 5, 241, 40));
			jLabelLeyenda.setText(null);
			jLabelLeyenda.setVisible(true);
			jLabelLoad = new JLabel();
			jLabelLoad.setBounds(new Rectangle(5, 5, 155, 40));
			jLabelLoad.setIcon(new ImageIcon(getClass().getResource("/ajax-loader.gif")));
			jLabelLoad.setText("   Generando...");
			jLabelLoad.setVisible(true);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabelLoad, null);
			jContentPane.add(jLabelLeyenda, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,23"

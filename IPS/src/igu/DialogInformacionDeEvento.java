package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;

public class DialogInformacionDeEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipo;
	private JLabel lblNewLabel_1;
	private JTextField txtDistancia;
	private JLabel lblFechaDeLa;
	private JTextField txtFechaCarrera;
	private JLabel lbNombreEvento;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTabbedPane tabbedPane;
	private JPanel pnInscripciones;
	private JPanel pnResultados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogInformacionDeEvento dialog = new DialogInformacionDeEvento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogInformacionDeEvento() {
		setBounds(100, 100, 910, 662);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(null);
			panel.setBounds(0, 0, 892, 154);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblTipo = new JLabel("Tipo: ");
				lblTipo.setBounds(92, 77, 56, 16);
				panel.add(lblTipo);
			}
			{
				txtTipo = new JTextField();
				txtTipo.setBounds(170, 74, 86, 22);
				panel.add(txtTipo);
				txtTipo.setColumns(10);
			}
			panel.add(getLblNewLabel_1());
			panel.add(getTxtDistancia());
			panel.add(getLblFechaDeLa());
			panel.add(getTxtFechaCarrera());
			panel.add(getLbNombreEvento());
			panel.add(getLblNewLabel());
			panel.add(getTextField());
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 155, 892, 412);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getTabbedPane());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Distancia: ");
			lblNewLabel_1.setBounds(92, 106, 66, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTxtDistancia() {
		if (txtDistancia == null) {
			txtDistancia = new JTextField();
			txtDistancia.setBounds(170, 103, 56, 22);
			txtDistancia.setColumns(10);
		}
		return txtDistancia;
	}
	private JLabel getLblFechaDeLa() {
		if (lblFechaDeLa == null) {
			lblFechaDeLa = new JLabel("Fecha de la carrera:");
			lblFechaDeLa.setBounds(369, 77, 142, 16);
		}
		return lblFechaDeLa;
	}
	private JTextField getTxtFechaCarrera() {
		if (txtFechaCarrera == null) {
			txtFechaCarrera = new JTextField();
			txtFechaCarrera.setBounds(507, 74, 86, 22);
			txtFechaCarrera.setColumns(10);
		}
		return txtFechaCarrera;
	}
	private JLabel getLbNombreEvento() {
		if (lbNombreEvento == null) {
			lbNombreEvento = new JLabel("Evento: ");
			lbNombreEvento.setFont(new Font("Verdana", Font.BOLD, 17));
			lbNombreEvento.setBounds(12, 13, 123, 22);
		}
		return lbNombreEvento;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Plazas disponibles: ");
			lblNewLabel.setBounds(369, 106, 116, 16);
		}
		return lblNewLabel;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(507, 103, 86, 22);
			textField.setColumns(10);
		}
		return textField;
	}
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Inscripciones", null, getPnInscripciones(), null);
			tabbedPane.addTab("Resultados", null, getPnResultados(), null);
		}
		return tabbedPane;
	}
	private JPanel getPnInscripciones() {
		if (pnInscripciones == null) {
			pnInscripciones = new JPanel();
		}
		return pnInscripciones;
	}
	private JPanel getPnResultados() {
		if (pnResultados == null) {
			pnResultados = new JPanel();
		}
		return pnResultados;
	}
	
	public void activarDescativarPaneles(boolean inscripciones, boolean resultados){
		
	}
}

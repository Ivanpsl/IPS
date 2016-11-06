package igu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class panelCuadroResultadosAtletaEvento extends JPanel {
	private JPanel pnNombre;
	private JPanel panel_1;
	private JLabel lbNombre;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Panel que se introducira dentro de la seccion donde el atleta podra consultar sus resultados en eventos
	 * Puesto que nunca se sabra cuantos eventos participara, este panel se creara tantas veces como eventos en los que
	 * aparezca, por eso me ha parecido mas facil separarlo de la ventanaPrincipal
	 * 
	 */
	public panelCuadroResultadosAtletaEvento() {
		setLayout(new BorderLayout(0, 0));
		add(getPnNombre(), BorderLayout.NORTH);
		add(getPanel_1(), BorderLayout.CENTER);

	}

	private JPanel getPnNombre() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			pnNombre.add(getLbNombre());
		}
		return pnNombre;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(2, 2, 0, 0));
			panel_1.add(getLblNewLabel_1());
			panel_1.add(getPanel());
			panel_1.add(getLblNewLabel());
			panel_1.add(getPanel_2());
		}
		return panel_1;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("nombre");
		}
		return lbNombre;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
		}
		return panel_2;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("New label");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("New label");
		}
		return lblNewLabel_1;
	}
}

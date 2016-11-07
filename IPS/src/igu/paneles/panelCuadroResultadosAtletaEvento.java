package igu.paneles;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class panelCuadroResultadosAtletaEvento extends JPanel {
	

	
	private JPanel pnNombre;
	private JPanel panel_1;
	private JLabel lbNombre;
	private JPanel panel;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblCategoria;
	private JPanel panel_4;
	private JLabel lblNewLabel_2;
	private JTextField txTiempoAbsoluto;
	private JTextField txPosicionAbsoluta;
	private JLabel lblNewLabel_3;
	private JLabel lblposicioncat;
	private JTextField txPosicionCat;
	private JPanel panel_6;
	private JButton btnVerMasInformacion;
	private JPanel panel_3;

	/**
	 * Panel que se introducira dentro de la seccion donde el atleta podra consultar sus resultados en eventos
	 * Puesto que nunca se sabra cuantos eventos participara, este panel se creara tantas veces como eventos en los que
	 * aparezca, por eso me ha parecido mas facil separarlo de la ventanaPrincipal
	 * 
	 */
	public panelCuadroResultadosAtletaEvento(Inscripcion ins,Evento ev, int posicionAbsoluta, int posicionCategoria) {
		setBorder(new TitledBorder(null, "Evento: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		add(getPnNombre(), BorderLayout.NORTH);
		lbNombre.setText(ev.getNombre() +" ("+ ev.getTipo()+  ")");
		txPosicionAbsoluta.setText("" +posicionAbsoluta);
		txPosicionCat.setText("" + posicionCategoria);
		txTiempoAbsoluto.setText("" + ins.getTiempoSegundos());
		add(getPanel_6_1(), BorderLayout.CENTER);
	}

	private JPanel getPnNombre() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			pnNombre.setLayout(new GridLayout(0, 3, 0, 0));
			pnNombre.add(getLbNombre());
			pnNombre.add(getLblCategoria());
			pnNombre.add(getPanel_3());
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
			lbNombre.setFont(new Font("Verdana", Font.BOLD, 15));
			lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbNombre;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(2, 1, 0, 0));
			panel.add(getLblNewLabel_3());
			panel.add(getTxPosicionAbsoluta());
		}
		return panel;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new GridLayout(2, 1, 0, 0));
			panel_2.add(getLblposicioncat());
			panel_2.add(getTxPosicionCat());
		}
		return panel_2;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Clasificacion categoria");
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Clasificacion absoluta");
		}
		return lblNewLabel_1;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel("categoria");
			lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCategoria;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.add(getLblNewLabel_2());
			panel_4.add(getTxTiempoAbsoluto());
		}
		return panel_4;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Tiempo");
		}
		return lblNewLabel_2;
	}
	private JTextField getTxTiempoAbsoluto() {
		if (txTiempoAbsoluto == null) {
			txTiempoAbsoluto = new JTextField();
			txTiempoAbsoluto.setEditable(false);
			txTiempoAbsoluto.setHorizontalAlignment(SwingConstants.CENTER);
			txTiempoAbsoluto.setColumns(10);
		}
		return txTiempoAbsoluto;
	}
	private JTextField getTxPosicionAbsoluta() {
		if (txPosicionAbsoluta == null) {
			txPosicionAbsoluta = new JTextField();
			txPosicionAbsoluta.setEditable(false);
			txPosicionAbsoluta.setHorizontalAlignment(SwingConstants.CENTER);
			txPosicionAbsoluta.setColumns(10);
		}
		return txPosicionAbsoluta;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Posicion:");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblposicioncat() {
		if (lblposicioncat == null) {
			lblposicioncat = new JLabel("Posicion:");
			lblposicioncat.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblposicioncat;
	}
	private JTextField getTxPosicionCat() {
		if (txPosicionCat == null) {
			txPosicionCat = new JTextField();
			txPosicionCat.setHorizontalAlignment(SwingConstants.CENTER);
			txPosicionCat.setEditable(false);
			txPosicionCat.setColumns(10);
		}
		return txPosicionCat;
	}
	private JPanel getPanel_6_1() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setLayout(new BorderLayout(0, 0));
			panel_6.add(getPanel_1(), BorderLayout.CENTER);
			panel_6.add(getPanel_4(), BorderLayout.NORTH);
		}
		return panel_6;
	}
	private JButton getBtnVerMasInformacion() {
		if (btnVerMasInformacion == null) {
			btnVerMasInformacion = new JButton("Ver mas informacion");
			btnVerMasInformacion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
		}
		return btnVerMasInformacion;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getBtnVerMasInformacion());
		}
		return panel_3;
	}
}

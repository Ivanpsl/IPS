package igu.paneles;

import igu.VentanaPrincipal;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

import logica.Gestor;

public class panelFiltros extends JPanel {
	
	
	private VentanaPrincipal vP;
	private Gestor g;
	private ArrayList<String> enComboBox;
	private JPanel panel;
	private JLabel lblDistancia;
	private JPanel pnFiltroDistancia;
	private JCheckBox chckbxNewCheckBox;
	private JSpinner spinner;
	private JLabel lblNewLabel;
	private JSpinner spinner_1;
	private JLabel lblNewLabel_1;
	private JPanel pnMostrar;
	private JCheckBox chckbxEventosTerminados;
	private JCheckBox chckbxPlazoCerrado;
	private JCheckBox chckbxLlenos;
	private JPanel pnTipo;
	private JComboBox<String> cmbBox;
	


	/**
	 * Create the panel.
	 */
	public panelFiltros(VentanaPrincipal vP, Gestor g) {
		this.vP=vP;
		this.g=g;
		enComboBox = new ArrayList<String>();
		setLayout(new BorderLayout(0, 0));
		add(getPanel());

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtros: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.add(getPnFiltroDistancia());
			panel.add(getPanel_1_1());
			panel.add(getPnMostrar());
		}
		return panel;
	}
	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("de");
		}
		return lblDistancia;
	}
	private JPanel getPnFiltroDistancia() {
		if (pnFiltroDistancia == null) {
			pnFiltroDistancia = new JPanel();
			pnFiltroDistancia.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Distancia: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnFiltroDistancia.add(getChckbxNewCheckBox());
			pnFiltroDistancia.add(getLblDistancia());
			pnFiltroDistancia.add(getSpinner());
			pnFiltroDistancia.add(getLblNewLabel());
			pnFiltroDistancia.add(getSpinner_1());
			pnFiltroDistancia.add(getLblNewLabel_1());
		}
		return pnFiltroDistancia;
	}
	private JCheckBox getChckbxNewCheckBox() {
		if (chckbxNewCheckBox == null) {
			chckbxNewCheckBox = new JCheckBox("");
		}
		return chckbxNewCheckBox;
	}
	private JSpinner getSpinner() {
		if (spinner == null) {
			spinner = new JSpinner();
		}
		return spinner;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("km a: ");
		}
		return lblNewLabel;
	}
	private JSpinner getSpinner_1() {
		if (spinner_1 == null) {
			spinner_1 = new JSpinner();
		}
		return spinner_1;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("km");
		}
		return lblNewLabel_1;
	}
	private JPanel getPnMostrar() {
		if (pnMostrar == null) {
			pnMostrar = new JPanel();
			pnMostrar.setBorder(new TitledBorder(null, "Mostrar: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnMostrar.add(getChckbxEventosTerminados());
			pnMostrar.add(getChckbxPlazoCerrado());
			pnMostrar.add(getChckbxLlenos());
		}
		return pnMostrar;
	}
	private JCheckBox getChckbxEventosTerminados() {
		if (chckbxEventosTerminados == null) {
			chckbxEventosTerminados = new JCheckBox("Eventos terminados");
			chckbxEventosTerminados.setSelected(true);
		}
		return chckbxEventosTerminados;
	}
	private JCheckBox getChckbxPlazoCerrado() {
		if (chckbxPlazoCerrado == null) {
			chckbxPlazoCerrado = new JCheckBox("Plazo cerrado");
			chckbxPlazoCerrado.setSelected(true);
		}
		return chckbxPlazoCerrado;
	}
	private JCheckBox getChckbxLlenos() {
		if (chckbxLlenos == null) {
			chckbxLlenos = new JCheckBox("Plazas llenas");
			chckbxLlenos.setSelected(true);
		}
		return chckbxLlenos;
	}
	private JPanel getPanel_1_1() {
		if (pnTipo == null) {
			pnTipo = new JPanel();
			pnTipo.setBorder(new TitledBorder(null, "Tipo: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnTipo.add(getCmbBox());
		}
		return pnTipo;
	}
	private JComboBox<String> getCmbBox() {
		if (cmbBox == null) {
			cmbBox = new JComboBox<String>();
			
		}
		return cmbBox;
	}
	public void rellenarComboBox(){
		
		for(String tipo: g.obtenerTipos())
			if(!enComboBox.contains(tipo.toUpperCase()))
				cmbBox.addItem(tipo.toUpperCase());
	}
}

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
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import logica.Gestor;

import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class panelFiltros extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int maxDistancia=-1;
	private int minDistancia=-1;
	private VentanaPrincipal vP;
	private Gestor g;
	private ArrayList<String> enComboBox;
	private JPanel panel;
	private JLabel lblDistancia;
	private JPanel pnFiltroDistancia;
	private JCheckBox chDistancia;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel pnMostrar;
	private JCheckBox chEvTerminados;
	private JCheckBox chPlCerrado;
	private JCheckBox chplLlenos;
	private JPanel pnTipo;
	private JComboBox<String> cmbBox;
	private JTextField txMinDistancia;
	private JTextField txMaxDistancia;
	


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
	public int getMaxDistancia(){
		String maxSt=txMaxDistancia.getText();
		if(maxSt.equals("") || maxSt.equals(" ")) return -1;
		else return Integer.valueOf(maxSt);
	}
	public int getMinDistancia(){
		String minSt=txMinDistancia.getText();
		if(minSt.equals("")|| minSt.equals(" ")) return -1;
		else return Integer.valueOf(minSt);
	}
	public boolean getFDistancia(){
		return chDistancia.isSelected();
	}
	public boolean getFPlazasLlenas(){
		return chplLlenos.isSelected();
	}
	public boolean getFPlazoCerrado(){
		return chPlCerrado.isSelected();
	}
	public boolean getFTerminados(){
		return chEvTerminados.isSelected();
	}
	public String getFTipo(){
		return (String) cmbBox.getSelectedItem();
	}
	public void reiniciarFiltros(){
		chDistancia.setSelected(false);
		chEvTerminados.setSelected(true);
		chPlCerrado.setSelected(true);
		chplLlenos.setSelected(true);
		actualizarEstadoCuadrosTexto(false);
		maxDistancia=-1;
		minDistancia=-1;
		
	}
	private boolean comprobacionDeCampos(){
		if(txMaxDistancia.getText()!=null && !txMaxDistancia.getText().equals("") && chDistancia.isSelected()){
			if(!txMinDistancia.equals("") && Integer.parseInt(txMaxDistancia.getText())<
			Integer.parseInt(txMinDistancia.getText())){
				JOptionPane.showMessageDialog(vP, "La distancia maxima no puede ser menor que la distancia minima.","Error de distancias",JOptionPane.ERROR_MESSAGE);
				txMaxDistancia.setText("");
				return false;
			}
		}if(txMinDistancia.getText()!=null && !txMinDistancia.equals("") && chDistancia.isSelected() ){
				if(!txMaxDistancia.getText().equals("")&& Integer.parseInt(txMinDistancia.getText())>Integer.parseInt(txMaxDistancia.getText())){
					JOptionPane.showMessageDialog(vP, "La distancia minima no puede ser mayor que la maxima.","Error de distancias",JOptionPane.ERROR_MESSAGE);
					txMinDistancia.setText("");
					return false;
				}
		}if(chDistancia.isSelected()) {
			minDistancia=getMinDistancia();
			maxDistancia=getMaxDistancia();
			vP.actualizarTablaFiltrada();
		}
		return true;
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
			pnFiltroDistancia.add(getChDistancia());
			pnFiltroDistancia.add(getLblDistancia());
			pnFiltroDistancia.add(getTxMinDistancia());
			pnFiltroDistancia.add(getLblNewLabel());
			pnFiltroDistancia.add(getTxMaxDistancia());
			pnFiltroDistancia.add(getLblNewLabel_1());
		}
		return pnFiltroDistancia;
	}
	private void actualizarEstadoCuadrosTexto(boolean estado){
		txMaxDistancia.setEnabled(estado);
		txMinDistancia.setEnabled(estado);
		if(!estado){
			txMaxDistancia.setText("");
			txMinDistancia.setText("");
		}
	}
	private JCheckBox getChDistancia() {
		if (chDistancia == null) {
			chDistancia = new JCheckBox("");
			chDistancia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(chDistancia.isSelected()){
						actualizarEstadoCuadrosTexto(true);
						vP.actualizarTablaFiltrada();
					}else{
						actualizarEstadoCuadrosTexto(false);
						vP.actualizarTablaFiltrada();
					}
				}
			});
		}
		return chDistancia;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("km a: ");
		}
		return lblNewLabel;
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
			pnMostrar.add(getChEvTerminados());
			pnMostrar.add(getChPlCerrado());
			pnMostrar.add(getChplLlenos());
		}
		return pnMostrar;
	}
	private JCheckBox getChEvTerminados() {
		if (chEvTerminados == null) {
			chEvTerminados = new JCheckBox("Eventos terminados");
			chEvTerminados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.actualizarTablaFiltrada();
				}
			});
			chEvTerminados.setSelected(true);
		}
		return chEvTerminados;
	}
	private JCheckBox getChPlCerrado() {
		if (chPlCerrado == null) {
			chPlCerrado = new JCheckBox("Plazo cerrado");
			chPlCerrado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.actualizarTablaFiltrada();
				}
			});
			chPlCerrado.setSelected(true);
		}
		return chPlCerrado;
	}
	private JCheckBox getChplLlenos() {
		if (chplLlenos == null) {
			chplLlenos = new JCheckBox("Plazas llenas");
			chplLlenos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.actualizarTablaFiltrada();
				}
			});
			chplLlenos.setSelected(true);
		}
		return chplLlenos;
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
			cmbBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.actualizarTablaFiltrada();
				}
			});
			
		}
		return cmbBox;
	}
	public void rellenarComboBox(){
		cmbBox.addItem("TODOS");
		for(String tipo: g.obtenerTipos())
			if(!enComboBox.contains(tipo.toUpperCase()))
				cmbBox.addItem(tipo.toUpperCase());
	}
	private JTextField getTxMinDistancia() {
		if (txMinDistancia == null) {
			txMinDistancia = new JTextField();
			txMinDistancia.setEnabled(false);
			txMinDistancia.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					comprobacionDeCampos();
				}
			});
			txMinDistancia.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if (!Character.isDigit(c)) {
						arg0.consume();
					}
					
					if(!txMinDistancia.getText().equals("") && maxDistancia>0 && Integer.valueOf(txMinDistancia.getText())<maxDistancia){
						
						vP.actualizarTablaFiltrada();
					}
				}
			});
			txMinDistancia.setColumns(3);
		}
		return txMinDistancia;
	}
	private JTextField getTxMaxDistancia() {
		if (txMaxDistancia == null) {
			txMaxDistancia = new JTextField();
			txMaxDistancia.setEnabled(false);
			txMaxDistancia.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					comprobacionDeCampos();
				}
			});
			txMaxDistancia.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if (!Character.isDigit(c)) {
						arg0.consume();
					}
					if(!txMaxDistancia.getText().equals("") && minDistancia>0 && Integer.valueOf(txMaxDistancia.getText())>minDistancia){
						vP.actualizarTablaFiltrada();
					}
				}
			});
			txMaxDistancia.setColumns(3);
		}
		return txMaxDistancia;
	}
}

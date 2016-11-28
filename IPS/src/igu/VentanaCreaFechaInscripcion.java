package igu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Vistas.PlazoInscripcion;
import utiles.ConversorFechas;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCreaFechaInscripcion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	//Para intentar no borrar la fecha si se edita y se cierrra la ventana
	PlazoInscripcion plazoAnterior;  //Actual y anterior se refiere a un plazo que esta siendo editado o creado
	PlazoInscripcion plazoActual;
	VentanaPrincipal vp;
	private JComboBox cbDiaIn;
	private JLabel lblDa;
	private JLabel lblMes;
	private JComboBox cbMesIn;
	private JLabel lblAo;
	private JComboBox cbAñoIn;
	private JLabel lbFechFin;
	private JComboBox cbDiaFin;
	private JComboBox cbMesFin;
	private JComboBox cbAñoFin;
	private JLabel lblPreciopersona;
	private JSpinner spPrecio;
	private JLabel label;
	private JButton btnCrearPlazo;
	private JButton btnCancelar;
	
	
	
	//Modelos combo
	DefaultComboBoxModel<Integer> modeloDiasA;
	DefaultComboBoxModel<Integer> modeloDiasB;
	DefaultComboBoxModel<Integer> modeloAñosA;
	DefaultComboBoxModel<Integer> modeloAñosB;
	DefaultComboBoxModel<String> modeloMesesA;
	DefaultComboBoxModel<String> modeloMesesB;
	SpinnerNumberModel modeloSpinnerDinero;
	String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	//Fechas
	Date fechaActual;
	Date fechaInicio;
	Date fechaFin;
	private JLabel lbFechaActual;
	
	

	/**
	 * Crea el dialogo para editar una fecha. 
	 * @wbp.parser.constructor
	 */
	public VentanaCreaFechaInscripcion(VentanaPrincipal v, PlazoInscripcion plazo) {
		
		this.vp = v;
		this.plazoActual = plazo;
		this.plazoAnterior = plazo;
		this.setLocationRelativeTo(null);
		
		fechaActual = ConversorFechas.getFechaActual();
		
		VentanaCreaFechaInscripcion.this.setLocationRelativeTo(null);
		VentanaCreaFechaInscripcion.this.setResizable(false);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFechaInicio = new JLabel("Fecha inicio:");
			lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaInicio.setBounds(20, 54, 75, 14);
			contentPanel.add(lblFechaInicio);
		}
		contentPanel.add(getCbDiaIn());
		contentPanel.add(getLblDa());
		contentPanel.add(getLblMes());
		contentPanel.add(getCbMesIn());
		contentPanel.add(getLblAo());
		contentPanel.add(getCbAñoIn());
		contentPanel.add(getLbFechFin());
		contentPanel.add(getCbDiaFin());
		contentPanel.add(getCbMesFin());
		contentPanel.add(getCbAñoFin());
		contentPanel.add(getLblPreciopersona());
		contentPanel.add(getSpPrecio());
		contentPanel.add(getLabel());
		contentPanel.add(getBtnCrearPlazo());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getLbFechaActual());
		
		crearModelosComboBox();
		asignarModelosCombo();
		
		//Asignar los valores del plazo a los elementos.
		asignarValores(plazo);
	}
	private void asignarValores(PlazoInscripcion p){
		int precio = (int) p.getPrecio();
		Date fechaIn = p.getFechaInicio();
		Date fechaFin = p.getFechaFin();
		
		int[] calIn = ConversorFechas.getFechaEnArray(fechaIn);
		int [] calFin = ConversorFechas.getFechaEnArray(fechaFin);
		int diaIn = calIn[0];
		int diaFin = calFin[0];
		int mesIn = calIn[1];
		int mesFin = calFin[1];
		int indexAñosIn = calIn[2] - 2016; 
		int indexAñosFin = calFin[2] - 2016;
		getCbDiaIn().setSelectedIndex(diaIn-1);
		getCbMesIn().setSelectedIndex(mesIn);
		getCbAñoIn().setSelectedIndex(indexAñosIn);
		getCbDiaFin().setSelectedIndex(diaFin-1);
		getCbMesFin().setSelectedIndex(mesFin);
		getCbAñoFin().setSelectedIndex(indexAñosFin);
		getSpPrecio().setValue(precio);
	}
	
	/**
	 * Crea el dialogo para añadir una nueva fecha de inscripcion
	 */
	public VentanaCreaFechaInscripcion(VentanaPrincipal v) {
		this.vp = v;
		this.plazoActual = null;
		this.plazoAnterior = null;
		fechaActual = ConversorFechas.getFechaActual();

		
		VentanaCreaFechaInscripcion.this.setLocationRelativeTo(null);
		VentanaCreaFechaInscripcion.this.setResizable(false);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFechaInicio = new JLabel("Fecha inicio:");
			lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaInicio.setBounds(20, 54, 75, 14);
			contentPanel.add(lblFechaInicio);
		}
		contentPanel.add(getCbDiaIn());
		contentPanel.add(getLblDa());
		contentPanel.add(getLblMes());
		contentPanel.add(getCbMesIn());
		contentPanel.add(getLblAo());
		contentPanel.add(getCbAñoIn());
		contentPanel.add(getLbFechFin());
		contentPanel.add(getCbDiaFin());
		contentPanel.add(getCbMesFin());
		contentPanel.add(getCbAñoFin());
		contentPanel.add(getLblPreciopersona());
		contentPanel.add(getSpPrecio());
		contentPanel.add(getLabel());
		contentPanel.add(getBtnCrearPlazo());
		contentPanel.add(getBtnCancelar());
		contentPanel.add(getLbFechaActual());
		
		crearModelosComboBox();
		asignarModelosCombo();
	}
	private JComboBox<Integer> getCbDiaIn() {
		if (cbDiaIn == null) {
			cbDiaIn = new JComboBox();
			cbDiaIn.setBounds(105, 51, 49, 20);
		}
		return cbDiaIn;
	}
	private JLabel getLblDa() {
		if (lblDa == null) {
			lblDa = new JLabel("d\u00EDa");
			lblDa.setBounds(119, 30, 22, 14);
		}
		return lblDa;
	}
	private JLabel getLblMes() {
		if (lblMes == null) {
			lblMes = new JLabel("mes");
			lblMes.setHorizontalAlignment(SwingConstants.CENTER);
			lblMes.setBounds(164, 30, 75, 14);
		}
		return lblMes;
	}
	private JComboBox<String> getCbMesIn() {
		if (cbMesIn == null) {
			cbMesIn = new JComboBox();
			cbMesIn.setBounds(164, 51, 75, 20);
		}
		return cbMesIn;
	}
	private JLabel getLblAo() {
		if (lblAo == null) {
			lblAo = new JLabel("a\u00F1o");
			lblAo.setHorizontalAlignment(SwingConstants.CENTER);
			lblAo.setBounds(249, 30, 80, 14);
		}
		return lblAo;
	}
	private JComboBox<Integer> getCbAñoIn() {
		if (cbAñoIn == null) {
			cbAñoIn = new JComboBox();
			cbAñoIn.setBounds(249, 51, 80, 20);
		}
		return cbAñoIn;
	}
	private JLabel getLbFechFin() {
		if (lbFechFin == null) {
			lbFechFin = new JLabel("Fecha fin:");
			lbFechFin.setHorizontalAlignment(SwingConstants.RIGHT);
			lbFechFin.setBounds(20, 112, 75, 14);
		}
		return lbFechFin;
	}
	private JComboBox<Integer> getCbDiaFin() {
		if (cbDiaFin == null) {
			cbDiaFin = new JComboBox();
			cbDiaFin.setBounds(105, 106, 49, 20);
		}
		return cbDiaFin;
	}
	private JComboBox<String> getCbMesFin() {
		if (cbMesFin == null) {
			cbMesFin = new JComboBox();
			cbMesFin.setBounds(164, 106, 75, 20);
		}
		return cbMesFin;
	}
	private JComboBox<Integer> getCbAñoFin() {
		if (cbAñoFin == null) {
			cbAñoFin = new JComboBox();
			cbAñoFin.setBounds(249, 106, 80, 20);
		}
		return cbAñoFin;
	}
	private JLabel getLblPreciopersona() {
		if (lblPreciopersona == null) {
			lblPreciopersona = new JLabel("Precio/persona:");
			lblPreciopersona.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPreciopersona.setBounds(10, 166, 97, 14);
		}
		return lblPreciopersona;
	}
	private JSpinner getSpPrecio() {
		if (spPrecio == null) {
			spPrecio = new JSpinner();
			
			spPrecio.setBounds(117, 163, 49, 20);
			modeloSpinnerDinero = new SpinnerNumberModel(1, 1, 200, 1);
		}
		return spPrecio;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("\u20AC");
			label.setBounds(176, 166, 46, 14);
		}
		return label;
	}
	private JButton getBtnCrearPlazo() {
		if (btnCrearPlazo == null) {
			btnCrearPlazo = new JButton("Crear plazo");
			btnCrearPlazo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//COMPROBAR AQUI
					fechaInicio = ConversorFechas.crearFecha(getCbDiaIn().getSelectedItem().toString(), getCbMesIn().getSelectedItem().toString(), getCbAñoIn().getSelectedItem().toString());
					fechaFin = ConversorFechas.crearFecha(getCbDiaFin().getSelectedItem().toString(), getCbMesFin().getSelectedItem().toString(), getCbAñoFin().getSelectedItem().toString());
					
					if(plazoAnterior != null){
						//vp.plazosInscripcionNuevoEvento.remove(plazoAnterior);
						vp.borrarDelModeloListFechaIns(plazoAnterior);
					}
					if(fechasCorrectas()){
						PlazoInscripcion plazo = new PlazoInscripcion(fechaInicio, fechaFin, (int) getSpPrecio().getValue());
						vp.añadirPlazo(plazo);
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Las fechas no estan correctas!");
					}
					
				}
			});
			btnCrearPlazo.setBounds(321, 237, 113, 23);
		}
		return btnCrearPlazo;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cancelar?","¿Seguro/a?", JOptionPane.YES_NO_OPTION);
					if(respuesta == JOptionPane.YES_OPTION){
						dispose();
					}
					
					
				}
			});
			btnCancelar.setBounds(10, 237, 89, 23);
		}
		return btnCancelar;
	}
	
	private void crearModelosComboBox(){
		Integer[] añosin = new Integer[10];
		Integer[] añosfin = new Integer[10];
		for(int i = 0; i < añosin.length; i++){
			añosin[i] = 2000+i+16; //Que la fecha empiece este año 2016
			añosfin[i] = 2000+i+17;
		}
		modeloAñosA = new DefaultComboBoxModel<Integer>(añosin);
		modeloAñosB = new DefaultComboBoxModel<Integer>(añosin);
		
		Integer[] dias = new Integer[31];
		for(int i = 0; i < dias.length; i++){
			dias[i] = i+1;
		}
		modeloDiasA = new DefaultComboBoxModel<Integer>(dias);
		modeloDiasB = new DefaultComboBoxModel<Integer>(dias);
		modeloMesesA = new DefaultComboBoxModel<String>(meses);
		modeloMesesB = new DefaultComboBoxModel<String>(meses);
		
	}
	
	private int getNumeroMes(String cad) throws Exception{
		int it = 0;
		for(String m : meses){
			if(m.equals(cad))
				return it;
			it++;
		}
		throw new Exception("No existe el mes que has metido");
	}
	
	private boolean fechasCorrectas(){
		if(fechaInicio == null || fechaFin == null)
			throw new IllegalStateException("Todavía no se puede comparar");
		if(fechaInicio.getTime() >= fechaFin.getTime())
			return false;
		return true;
	}
	
	private void asignarModelosCombo(){
		getCbDiaFin().setModel(modeloDiasA);
		getCbDiaIn().setModel(modeloDiasB);
		getCbAñoIn().setModel(modeloAñosB);
		getCbAñoFin().setModel(modeloAñosA);
		getCbMesFin().setModel(modeloMesesA);
		getCbMesIn().setModel(modeloMesesB);
	}
	private JLabel getLbFechaActual() {
		if (lbFechaActual == null) {
			lbFechaActual = new JLabel("Cadena");
			lbFechaActual.setHorizontalAlignment(SwingConstants.CENTER);
			lbFechaActual.setBounds(119, 241, 210, 14);
			lbFechaActual.setText("Hoy es: "+fechaActual.toString());
		}
		return lbFechaActual;
	}
}

package igu.dialog;

import igu.ModeloNoEditable;
import igu.VentanaPrincipal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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



import logica.Vistas.Categoria;
import logica.Vistas.Clasificacion;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;
import logica.Vistas.PlazoInscripcion;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class DialogInformacionDeEvento extends JDialog {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] cabecera= { "Posicion", "Dorsal", "Nombre", "Tiempo" };
	//private ModeloNoEditable modeloTabla;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtTipo;
	private JLabel lblNewLabel_1;
	private JTextField txtDistancia;
	private JLabel lblFechaDeLa;
	private JTextField txtFechaCarrera;
	private JLabel lbNombreEvento;
	private JLabel lblNewLabel;
	private JTextField txtEstado;
	private JPanel pnInscripciones;
	private JPanel pnResultados;
	private Evento ev;
	private VentanaPrincipal vP;
	private JTextArea areaPlazos;
	private JPanel pnGeneral;
	private JPanel pnInscritos;
	private JTextArea areaInscritos;
	private JPanel pnInscritos2;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTextArea areaCategorias;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JPanel panel_4;
	private JLabel lblSeleccionarCategoriaA;
	private JTable table;
	private JComboBox<String> cmBoxCategoria;
	private JPanel cardEventos;
	private JButton btnInscripciones;
	private JButton btnResultado;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			DialogInformacionDeEvento dialog = new DialogInformacionDeEvento();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public DialogInformacionDeEvento(VentanaPrincipal vp, Evento ev) {
		setResizable(false);
		setTitle("Gestor Eventos: Ficha de evento");
		setModal(true);
		setType(Type.UTILITY);
		setAlwaysOnTop(true);
		this.vP=vp;
		this.ev=ev;
		this.setLocationRelativeTo(vP);
		
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
				txtTipo.setEditable(false);
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
			panel.add(getTxtEstado());
			panel.add(getBtnInscripciones());
			panel.add(getBtnResultado());
		}
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 155, 892, 412);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getCardEventos(), BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		rellenarInformacion();
	}
	

	
	private void rellenarInformacion(){
		if(ev.getFinalizado()){
			txtEstado.setText("FINALIZADO");
			rellenarComboBox();
			rellenarTabla();
			btnResultado.setEnabled(true);
		}
		else
			{
			
			if(ev.getPlazos().size()==0) txtEstado.setText("CERRADO");
			else txtEstado.setText("INSCRIPCION");
			btnResultado.setEnabled(false);
		}
		lbNombreEvento.setText(ev.getNombre());
		txtDistancia.setText(""+ev.getDistancia()+ "km");
		txtFechaCarrera.setText(ev.getFechaCompeticion().toString());
		txtTipo.setText(ev.getTipo());
		rellenarInscritos();
		rellenarPlazos();
		rellenarCategorias();
		
	}
	
	private void rellenarTabla(){
	
		for(Clasificacion cl : ev.getClasificaciones()){
			ModeloNoEditable modeloTabla = new ModeloNoEditable(cabecera, 0);
				if(cmBoxCategoria.getSelectedItem().toString().toUpperCase().equals(cl.getCategoria().toUpperCase())){
					ArrayList<Inscripcion> inscritosQueFinalizaron=new ArrayList<Inscripcion>();
					ArrayList<Inscripcion> inscritosQueNoFinalizaron = new ArrayList<Inscripcion>();
//					for(Inscripcion ins: cl.getCorredores()){
//						if(ins.getResultado()>0 && ins.getDorsal()>-1) inscritosQueFinalizaron.add(ins);
//						else inscritosQueNoFinalizaron.add(ins);
//					}
					for(Inscripcion ins: ev.getInscritosEvento())
						if(ins.getCategoria().equals(cmBoxCategoria.getSelectedItem()) || 
								cmBoxCategoria.getSelectedItem().toString().toUpperCase().equals("ABSOLUTA")){
							if(ins.getResultado()>0) inscritosQueFinalizaron.add(ins);
							else inscritosQueNoFinalizaron.add(ins);
						}
				
				for (int i=1; i<inscritosQueFinalizaron.size();i++ ) {
					String[] fila = new String[4];
					fila[0] = ""+i;
					fila[1] = ""+inscritosQueFinalizaron.get(i).getDorsal();
					fila[2] = inscritosQueFinalizaron.get(i).getAtleta().getNombre();
					fila[3] = ""+inscritosQueFinalizaron.get(i).getTiempoSegundos() + "seg.";
				
					modeloTabla.addRow(fila);
				}
				for (int i=0; i<inscritosQueNoFinalizaron.size();i++ ) {
					String[] fila = new String[4];
					fila[0] = "--";
					if(inscritosQueNoFinalizaron.get(i).getDorsal()==-1){
						fila[1] = "SIN PAGAR";
						fila[2]= "SIN PAGAR";
						fila[3]= "SIN PAGAR";
					}else{
						fila[1] = ""+inscritosQueNoFinalizaron.get(i).getDorsal();
						fila[2] = inscritosQueNoFinalizaron.get(i).getAtleta().getNombre();
						if(inscritosQueNoFinalizaron.get(i).getTiempoSegundos()<0)fila[3] ="NO_PRESENTADO";
						else fila[3] = "ABANDONO";
						
					}
						modeloTabla.addRow(fila);
					
				}
				table.getTableHeader().setReorderingAllowed(false);
				table.setModel(modeloTabla);
			}
		}
	}
	
	private void rellenarComboBox(){
		cmBoxCategoria.addItem("Absoluta");
		for(Categoria c: ev.getCategorias()){
			cmBoxCategoria.addItem(c.getNombre());
		}rellenarTabla();
		
	}
	private void rellenarCategorias(){
		for(Categoria cat : ev.getCategorias())
			areaCategorias.append(cat.getNombre() +" Edades: "+ cat.getEdadMinima()+"-"+ cat.getEdadMaxima()+ "\n");
	}
	private void rellenarPlazos(){
		if(ev.getPlazos()!=null && ev.getPlazos().size()>0){
			for(PlazoInscripcion plazo : ev.getPlazos()){
				areaPlazos.append("De " + plazo.getFechaInicio().toString()+ " a " + plazo.getFechaFin().toString()+"  Precio: " +plazo.getPrecio() + "€\n");
			}
		}else areaPlazos.append("SIN PLAZOS");
	}
	private void rellenarInscritos(){
		ArrayList<Inscripcion> atletas = ev.getInscritosEvento();
 		for(Inscripcion inscripcion: atletas){
 			areaInscritos.append(inscripcion.getAtleta().toString()+ "\n");
 		}
	}
	
	
	public void cambiarPanel(int opcion){
		if (opcion == 0) {
			((CardLayout) cardEventos.getLayout()).show(cardEventos, "pn_inscripciones");
	
		}
		if (opcion == 2) {
			((CardLayout)cardEventos.getLayout()).show(cardEventos, "pn_resultados");
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
			txtDistancia.setEditable(false);
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
			txtFechaCarrera.setEditable(false);
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
			lblNewLabel = new JLabel("Estado: ");
			lblNewLabel.setBounds(449, 106, 116, 16);
		}
		return lblNewLabel;
	}
	private JTextField getTxtEstado() {
		if (txtEstado == null) {
			txtEstado = new JTextField();
			txtEstado.setEditable(false);
			txtEstado.setBounds(507, 103, 86, 22);
			txtEstado.setColumns(10);
		}
		return txtEstado;
	}
	private JPanel getPnInscripciones() {
		if (pnInscripciones == null) {
			pnInscripciones = new JPanel();
			pnInscripciones.setLayout(new GridLayout(0, 2, 0, 0));
			pnInscripciones.add(getPnGeneral());
			pnInscripciones.add(getPnInscritos());
		}
		return pnInscripciones;
	}
	private JPanel getPnResultados() {
		if (pnResultados == null) {
			pnResultados = new JPanel();
			pnResultados.setLayout(new BorderLayout(0, 0));
			pnResultados.add(getScrollPane(), BorderLayout.CENTER);
			pnResultados.add(getPanel_4(), BorderLayout.NORTH);
		}
		return pnResultados;
	}
	
	private JTextArea getAreaPlazos() {
		if (areaPlazos == null) {
			areaPlazos = new JTextArea();
			areaPlazos.setEditable(false);
		}
		return areaPlazos;
	}
	private JPanel getPnGeneral() {
		if (pnGeneral == null) {
			pnGeneral = new JPanel();
			pnGeneral.setLayout(null);
			pnGeneral.add(getPanel_1());
			pnGeneral.add(getPanel_2());
			pnGeneral.add(getPanel_3());
		}
		return pnGeneral;
	}
	private JPanel getPnInscritos() {
		if (pnInscritos == null) {
			pnInscritos = new JPanel();
			pnInscritos.setLayout(new BorderLayout(0, 0));
			pnInscritos.add(getPnInscritos2(), BorderLayout.CENTER);
		}
		return pnInscritos;
	}
	private JTextArea getAreaInscritos() {
		if (areaInscritos == null) {
			areaInscritos = new JTextArea();
			areaInscritos.setEditable(false);
		}
		return areaInscritos;
	}
	private JPanel getPnInscritos2() {
		if (pnInscritos2 == null) {
			pnInscritos2 = new JPanel();
			pnInscritos2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Atletas inscritos: ", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, Color.BLACK));
			pnInscritos2.setLayout(new BorderLayout(0, 0));
			pnInscritos2.add(getAreaInscritos());
		}
		return pnInscritos2;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Plazos de inscripcion: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(12, 13, 177, 356);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getAreaPlazos());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Categorias permitidas: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(254, 13, 165, 356);
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getAreaCategorias(), BorderLayout.CENTER);
		}
		return panel_2;
	}
	private JTextArea getAreaCategorias() {
		if (areaCategorias == null) {
			areaCategorias = new JTextArea();
			areaCategorias.setEditable(false);
		}
		return areaCategorias;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBounds(12, 197, 407, 172);
		}
		return panel_3;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.add(getLblSeleccionarCategoriaA());
			panel_4.add(getCmBoxCategoria());
		}
		return panel_4;
	}
	private JLabel getLblSeleccionarCategoriaA() {
		if (lblSeleccionarCategoriaA == null) {
			lblSeleccionarCategoriaA = new JLabel("Categoria a mostrar: ");
		}
		return lblSeleccionarCategoriaA;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}
	private JComboBox<String> getCmBoxCategoria() {
		if (cmBoxCategoria == null) {
			cmBoxCategoria = new JComboBox<String>();
			cmBoxCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenarTabla();
				}
			});
		}
		return cmBoxCategoria;
	}
	private JPanel getCardEventos() {
		if (cardEventos == null) {
			cardEventos = new JPanel();
			cardEventos.setLayout(new CardLayout(0, 0));
			cardEventos.add(getPnInscripciones(), "pn_inscripciones");
			cardEventos.add(getPnResultados(), "pn_resultados");
		}
		return cardEventos;
	}
	private JButton getBtnInscripciones() {
		if (btnInscripciones == null) {
			btnInscripciones = new JButton("Inscripciones");
			btnInscripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarPanel(0);
				}
			});
			btnInscripciones.setBounds(617, 116, 123, 25);
		}
		return btnInscripciones;
	}
	private JButton getBtnResultado() {
		if (btnResultado == null) {
			btnResultado = new JButton("Resultados");
			btnResultado.setEnabled(false);
			btnResultado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cambiarPanel(2);
				}
			});
			btnResultado.setBounds(757, 116, 109, 25);
		}
		return btnResultado;
	}



	public void mostrarClasificaciones() {
		cambiarPanel(2);
	}
}

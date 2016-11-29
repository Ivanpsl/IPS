package igu.dialog;

import igu.VentanaPrincipal;
import igu.paneles.panelCuadroResultadosAtletaEvento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import logica.Gestor;
import logica.GestorClasificaciones;
import logica.Vistas.Atleta;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JList;

import java.awt.GridLayout;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DialogResultadosAtleta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	Gestor g;
	ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	private ArrayList<Evento> eventos;
	Atleta atleta;
	panelCuadroResultadosAtletaEvento cResultados;
	GestorClasificaciones gC = new GestorClasificaciones();
	VentanaPrincipal vP;
	
	private JList<String> list;
	private ListModel<String> modeloLista;
	private JTextField txtNombreEvento;
	private JTextField txtTipoEvento;
	private JTextField txtTiempo;
	private JTextField txtCategoria;
	private JTextField txtPosAbsoluta;
	private JTextField txtPosicionCategoria;
	JTextArea txaParciales;
	/**
	 * Create the dialog.
	 * 
	 */
	public DialogResultadosAtleta(Atleta atleta, Gestor g, VentanaPrincipal vP) {
		setTitle("Gestor eventos: resultados de " + atleta.getNombre() + " (DNI: "+atleta.getDNI()  +")");
		setModal(true);
		setLocationRelativeTo(vP);
		this.atleta=atleta;
		this.g=g;
		this.vP=vP;
		setLocationRelativeTo(vP);
		setBounds(100, 100, 691, 491);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnLista = new JPanel();
			contentPanel.add(pnLista, BorderLayout.WEST);
			pnLista.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Eventos inscritos:", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
				pnLista.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JScrollPane scrollPane = new JScrollPane();
					panel.add(scrollPane, BorderLayout.CENTER);
					{
						list = new JList<String>();
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								int seleccionado= list.getSelectedIndex();
								if(seleccionado>=0){
									seleccionarEvento(seleccionado);
								}
							}
						});
						scrollPane.setViewportView(list);
					}
				}
			}
		}
		{
			JPanel pnInformacion = new JPanel();
			contentPanel.add(pnInformacion, BorderLayout.CENTER);
			pnInformacion.setLayout(new BorderLayout(0, 0));
			{
				JPanel pnInformacionEvento = new JPanel();
				pnInformacionEvento.setBorder(new TitledBorder(null, " Evento:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				pnInformacion.add(pnInformacionEvento, BorderLayout.NORTH);
				pnInformacionEvento.setLayout(new BorderLayout(0, 0));
				{
					JPanel panelInfoEvento = new JPanel();
					pnInformacionEvento.add(panelInfoEvento);
					{
						JLabel lblNombre = new JLabel("Nombre: ");
						panelInfoEvento.add(lblNombre);
					}
					{
						txtNombreEvento = new JTextField();
						txtNombreEvento.setFocusable(false);
						txtNombreEvento.setBorder(null);
						txtNombreEvento.setEditable(false);
						txtNombreEvento.setColumns(10);
						panelInfoEvento.add(txtNombreEvento);
					}
					{
						JLabel lblTipo = new JLabel("Tipo:");
						panelInfoEvento.add(lblTipo);
					}
					{
						txtTipoEvento = new JTextField();
						txtTipoEvento.setFocusable(false);
						txtTipoEvento.setBorder(null);
						txtTipoEvento.setEditable(false);
						panelInfoEvento.add(txtTipoEvento);
						txtTipoEvento.setColumns(10);
					}
				}
			}
			{
				JPanel pnInformacionResultados = new JPanel();
				pnInformacionResultados.setBorder(new TitledBorder(null, "Resultados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				pnInformacion.add(pnInformacionResultados);
				pnInformacionResultados.setLayout(new GridLayout(2, 0, 0, 0));
				{
					JPanel panel = new JPanel();
					pnInformacionResultados.add(panel);
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
					{
						JPanel panel_1_1 = new JPanel();
						panel.add(panel_1_1);
						panel_1_1.setLayout(new GridLayout(2, 1, 0, 0));
						{
							JPanel panel_1 = new JPanel();
							panel_1_1.add(panel_1);
							FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.CENTER, 5, 5);
							panel_1.setLayout(fl_panel_1);
							{
								JLabel lblTiempo = new JLabel("Tiempo: ");
								lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
								panel_1.add(lblTiempo);
							}
							{
								txtTiempo = new JTextField();
								txtTiempo.setFocusable(false);
								txtTiempo.setBorder(null);
								txtTiempo.setEditable(false);
								panel_1.add(txtTiempo);
								txtTiempo.setColumns(5);
							}
						}
						{
							JPanel panel_1_2 = new JPanel();
							panel_1_1.add(panel_1_2);
							{
								JLabel lblNewLabel = new JLabel("Posicion absoluta: ");
								lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
								panel_1_2.add(lblNewLabel);
							}
							{
								txtPosAbsoluta = new JTextField();
								txtPosAbsoluta.setFocusable(false);
								txtPosAbsoluta.setBorder(null);
								txtPosAbsoluta.setEditable(false);
								panel_1_2.add(txtPosAbsoluta);
								txtPosAbsoluta.setColumns(3);
							}
						}
					}
					{
						JPanel panel_1 = new JPanel();
						panel.add(panel_1);
						panel_1.setLayout(new GridLayout(0, 1, 0, 0));
						{
							JPanel panel_2 = new JPanel();
							panel_1.add(panel_2);
							{
								JLabel lblCategoria = new JLabel("Categoria:");
								lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
								panel_2.add(lblCategoria);
							}
							{
								txtCategoria = new JTextField();
								txtCategoria.setFocusable(false);
								txtCategoria.setBorder(null);
								txtCategoria.setEditable(false);
								panel_2.add(txtCategoria);
								txtCategoria.setColumns(10);
							}
						}
						{
							JPanel panel_2 = new JPanel();
							FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
							panel_1.add(panel_2);
							{
								JLabel lblNewLabel_1 = new JLabel("Posicion en la categoria: ");
								lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
								panel_2.add(lblNewLabel_1);
							}
							{
								txtPosicionCategoria = new JTextField();
								txtPosicionCategoria.setFocusable(false);
								txtPosicionCategoria.setBorder(null);
								txtPosicionCategoria.setEditable(false);
								panel_2.add(txtPosicionCategoria);
								txtPosicionCategoria.setColumns(3);
							}
						}
					}
				}
				{
					JPanel pnListaParciales = new JPanel();
					pnListaParciales.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tiempos por etapa: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					pnInformacionResultados.add(pnListaParciales);
					pnListaParciales.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane scrollPane = new JScrollPane();
						pnListaParciales.add(scrollPane, BorderLayout.CENTER);
						{
							txaParciales = new JTextArea();
							txaParciales.setColumns(5);
							txaParciales.setEditable(false);
							txaParciales.setLineWrap(true);
							txaParciales.setRows(3);
							txaParciales.setWrapStyleWord(true);
							scrollPane.setViewportView(txaParciales);
						}
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		rellenarFicha();
	}
	
	public void rellenarFicha(){
		cargarInscripciones();
		
	}
	

	private void cargarInscripciones(){
		//obtenemos eventos en los que participa e inscripciones
		eventos = new ArrayList<Evento>();
		for(Evento e: g.obtenerEventosParticipaPorDNI(atleta.getDNI())){
			if(e.getFinalizado()){
				eventos.add(e);
				//inscripciones.add(g.getInscripcion(atleta.getDNI(), e));
			}
			añadirEventosLista(eventos);
		};
	
			
	}
//	private Clasificacion obtenerCategoriaPerteneciente(Inscripcion ins){
//			for(Clasificacion c: 
//				g.obtenerEventoPorId(ins.getIdEvento()).getClasificaciones()){
//				if(c.getCategoria()!=null && ins.getCategoria()!=null){
//				if(ins.getCategoria().equals(c.getCategoria()))
//					return c;
//				}
//			}return null;
//	}
	
	private void añadirEventosLista(ArrayList<Evento> eventos){
		modeloLista = new DefaultListModel<String>();
		for(Evento ev: eventos){
			((DefaultListModel<String>) modeloLista).addElement(" " +ev.getNombre()+ " ");
		}
		list.setModel(modeloLista);
	}
	private void seleccionarEvento(int index){
		Evento eventoSeleccionado= eventos.get(index);
		Inscripcion ins= g.getInscripcion(atleta.getDNI(), eventoSeleccionado);
		txtNombreEvento.setText(eventoSeleccionado.getNombre());
		txtTipoEvento.setText(eventoSeleccionado.getTipo());
		txtCategoria.setText(ins.getCategoria());
		int posAb=eventoSeleccionado.obtenerPosicionAbsoluta(ins);
		if(posAb>0)
			txtPosAbsoluta.setText(""+posAb);
		
		int posCat= eventoSeleccionado.obtenerPosicionCategoria(ins);
		if(posCat>0)
			txtPosicionCategoria.setText("" +posCat);
		txtTiempo.setText("" + ins.getTiempoSegundos()+ "s");
		StringBuilder sB = new StringBuilder();
		for(int i=0; i< ins.getTiemposPorEtapas().size(); i++){
			if(ins.getTiemposPorEtapas().size()-1==i){
				sB.append("Total: " + ins.getTiemposPorEtapas().get(i) + "seg.\n");
			}else sB.append("Etapa " + i + ": "+ ins.getTiemposPorEtapas().get(i)+ "seg.\n");
		}
		txaParciales.setText(sB.toString());
	}

}

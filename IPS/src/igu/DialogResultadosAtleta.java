package igu;

import igu.paneles.panelCuadroResultadosAtletaEvento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Gestor;
import logica.GestorClasificaciones;
import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Clasificacion;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;

import javax.swing.JScrollPane;

import oracle.net.aso.e;
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

public class DialogResultadosAtleta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	Gestor g;
	ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	Atleta atleta;
	panelCuadroResultadosAtletaEvento cResultados;
	GestorClasificaciones gC = new GestorClasificaciones();
	VentanaPrincipal vP;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Create the dialog.
	 * 
	 */
	public DialogResultadosAtleta(Atleta atleta, Gestor g, VentanaPrincipal vP) {
		setTitle("Gestor eventos: resultados de atleta");
		setModal(true);
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
						JList list = new JList();
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
					JPanel panel = new JPanel();
					pnInformacionEvento.add(panel);
					{
						JLabel label = new JLabel("New label");
						panel.add(label);
					}
					{
						textField = new JTextField();
						panel.add(textField);
						textField.setColumns(10);
					}
				}
				{
					JPanel panel = new JPanel();
					pnInformacionEvento.add(panel);
					{
						JLabel label = new JLabel("New label");
						panel.add(label);
					}
					{
						textField_1 = new JTextField();
						textField_1.setColumns(10);
						panel.add(textField_1);
					}
				}
				{
					JPanel panel = new JPanel();
					pnInformacionEvento.add(panel);
					{
						JLabel label = new JLabel("New label");
						panel.add(label);
					}
					{
						textField_2 = new JTextField();
						textField_2.setColumns(10);
						panel.add(textField_2);
					}
				}
			}
			{
				JPanel pnInformacionResultados = new JPanel();
				pnInformacionResultados.setBorder(new TitledBorder(null, "Resultados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				pnInformacion.add(pnInformacionResultados);
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
		generarCuadros();
	}
	
	private void cargarInscripciones(){
		//obtenemos eventos en los que participa
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		for(Evento e: g.obtenerEventosParticipaPorDNI(atleta.getDNI())){
			if(e.getFinalizado()) eventos.add(e);
		};
		//obtenemos la inscripcion
		for(Evento ev: eventos)
			inscripciones.add(g.getInscripcion(atleta.getDNI(), ev));
	}
	private Clasificacion obtenerCategoriaPerteneciente(Inscripcion ins){
			for(Clasificacion c: 
				g.obtenerEventoPorId(ins.getIdEvento()).getClasificaciones()){
				if(c.getCategoria()!=null && ins.getCategoria()!=null){
				if(ins.getCategoria().equals(c.getCategoria()))
					return c;
				}
			}return null;
	}
	
	
	public void generarCuadros(){
		Clasificacion pertenece;
		for(Inscripcion ins: inscripciones){
			if(obtenerCategoriaPerteneciente(ins)!=null){
				pertenece=obtenerCategoriaPerteneciente(ins);
				//pnContenedor.add(new panelCuadroResultadosAtletaEvento(ins, g.obtenerEventoPorId(ins.getIdEvento()),gC.obtenerPosicionAbsoluta(ins, g.obtenerEventoPorId(ins.getIdEvento())) , gC.obtenerPosicion(ins,pertenece)));
			}
		}
	}
}

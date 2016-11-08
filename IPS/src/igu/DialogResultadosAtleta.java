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

public class DialogResultadosAtleta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JPanel pnContenedor;
	Gestor g;
	ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
	Atleta atleta;
	panelCuadroResultadosAtletaEvento cResultados;
	GestorClasificaciones gC = new GestorClasificaciones();
	VentanaPrincipal vP;
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
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				pnContenedor = new JPanel();
				scrollPane.setViewportView(pnContenedor);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
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
				pnContenedor.add(new panelCuadroResultadosAtletaEvento(ins, g.obtenerEventoPorId(ins.getIdEvento()),gC.obtenerPosicionAbsoluta(ins, g.obtenerEventoPorId(ins.getIdEvento())) , gC.obtenerPosicion(ins,pertenece)));
			}
		}
	}
}

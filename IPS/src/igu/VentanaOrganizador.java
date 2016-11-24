package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Gestor;
import logica.Vistas.Evento;
import usuarios.Organizador;
import usuarios.Usuario;

public class VentanaOrganizador extends JFrame {
	Organizador organizador;
	Gestor gestor;
	ArrayList<Evento> eventosDelOrganizador;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelCard;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaOrganizador frame = new VentanaOrganizador();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaOrganizador(Gestor g, Organizador user) {
		this.gestor = g;
		this.organizador = user;
		this.setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		panelPrincipal.add(getPanelCard(), BorderLayout.CENTER);
	}
	private void inicializar(){
		eventosDelOrganizador = organizador.getMisEventos();
	}

	private JPanel getPanelCard() {
		if (panelCard == null) {
			panelCard = new JPanel();
		}
		return panelCard;
	}
}

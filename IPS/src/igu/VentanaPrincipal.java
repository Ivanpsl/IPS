package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel pnPrincipal;
	private JPanel pnInicio;
	private JPanel pnOrganizador;
	private JPanel pnUsuario;
	private JPanel pnCabecera;
	private JPanel pnAtletaResumen;
	private JPanel pnIdentificate;
	private JPanel panel;
	private JButton btnIniciarUsuario;
	private JButton btnIniciarOrganizador;
	private JLabel lblSeleccioneElModo;
	private JPanel pnCentro;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblParaPoderVer;
	private JPanel panel_3;
	private JButton btnIdentificarse;
	private JPanel pnTabla;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel pnOpciones;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 501);
		pnPrincipal = new JPanel();
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnPrincipal);
		pnPrincipal.setLayout(new CardLayout(0, 0));
		pnPrincipal.add(getPnInicio(), "pn_inicio");
		pnPrincipal.add(getPnOrganizador(), "pn_Organizador");
		pnPrincipal.add(getPnUsuario(), "pn_usuario");
	}
	
	/**
	 * Metodo usado para navegar por las pestañas principales 
	 * @param opcion: 
	 * 				inicio: pantalla de seleccion de modo
	 * 				organizador: menus de organizadores
	 * 				usuario: menus de usuarios
	 */
	private void cambiarPanelesPrincipales(String opcion){
		if(opcion.equals("organizador")) ((CardLayout)pnPrincipal.getLayout()).show(pnPrincipal,"pn_Organizador");
		if(opcion.equals("usuario")) ((CardLayout)pnPrincipal.getLayout()).show(pnPrincipal,"pn_usuario");
		if(opcion.equals("inicio")) ((CardLayout)pnPrincipal.getLayout()).show(pnPrincipal,"pn_inicio");
	}
	/**
	 * Metodo usado para cambiar la cabecera de los menus de usuario
	 * @param opcion
	 * 				logueado: cabecera con sus datos y la opcion de mostrar clasificaciones
	 * 				sin_loguear: cabecera sin datos
	 */
	private void cambiarCabeceraUsuario(String opcion){
		if(opcion.equals("sin_loguear")) ((CardLayout)pnCabecera.getLayout()).show(pnCabecera,"usuario_sinIdentificar");
		if(opcion.equals("loguear"))  ((CardLayout)pnCabecera.getLayout()).show(pnCabecera,"usuario_Identificado");
	}
	

	private JPanel getPnInicio() {
		if (pnInicio == null) {
			pnInicio = new JPanel();
			pnInicio.setLayout(new GridLayout(3, 1, 0, 0));
			pnInicio.add(getLblSeleccioneElModo());
			pnInicio.add(getPanel());
		}
		return pnInicio;
	}
	private JPanel getPnOrganizador() {
		if (pnOrganizador == null) {
			pnOrganizador = new JPanel();
		}
		return pnOrganizador;
	}
	private JPanel getPnUsuario() {
		if (pnUsuario == null) {
			pnUsuario = new JPanel();
			pnUsuario.setLayout(new BorderLayout(0, 0));
			pnUsuario.add(getPnCabecera(), BorderLayout.NORTH);
			pnUsuario.add(getPnCentro(), BorderLayout.CENTER);
		}
		return pnUsuario;
	}
	private JPanel getPnCabecera() {
		if (pnCabecera == null) {
			pnCabecera = new JPanel();
			pnCabecera.setBorder(new TitledBorder(null, "Datos del atleta: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCabecera.setLayout(new CardLayout(0, 0));
			pnCabecera.add(getPnIdentificate(), "usuario_sinIdentificar");
			pnCabecera.add(getPnAtletaResumen(), "usuario_Identificado");
			
		}
		return pnCabecera;
	}
	private JPanel getPnAtletaResumen() {
		if (pnAtletaResumen == null) {
			pnAtletaResumen = new JPanel();
		}
		return pnAtletaResumen;
	}
	private JPanel getPnIdentificate() {
		if (pnIdentificate == null) {
			pnIdentificate = new JPanel();
			pnIdentificate.setLayout(new GridLayout(0, 1, 0, 0));
			pnIdentificate.add(getPanel_2());
		}
		return pnIdentificate;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(2, 1, 0, 0));
			panel.add(getBtnIniciarUsuario());
			panel.add(getBtnIniciarOrganizador());
		}
		return panel;
	}
	private JButton getBtnIniciarUsuario() {
		if (btnIniciarUsuario == null) {
			btnIniciarUsuario = new JButton("Usuario");
			btnIniciarUsuario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cambiarPanelesPrincipales("usuario");
				}
			});
		}
		return btnIniciarUsuario;
	}
	private JButton getBtnIniciarOrganizador() {
		if (btnIniciarOrganizador == null) {
			btnIniciarOrganizador = new JButton("Organizador");
			btnIniciarOrganizador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cambiarPanelesPrincipales("organizador");
				}
			});
		}
		return btnIniciarOrganizador;
	}
	private JLabel getLblSeleccioneElModo() {
		if (lblSeleccioneElModo == null) {
			lblSeleccioneElModo = new JLabel("Seleccione el modo de inicio:");
			lblSeleccioneElModo.setFont(new Font("Verdana", Font.BOLD, 23));
			lblSeleccioneElModo.setForeground(new Color(0, 0, 128));
			lblSeleccioneElModo.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSeleccioneElModo;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new BorderLayout(0, 0));
			pnCentro.add(getPnTabla(), BorderLayout.CENTER);
			pnCentro.add(getPanel_4_1(), BorderLayout.EAST);
		}
		return pnCentro;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.add(getLblParaPoderVer());
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBorder(null);
			FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
			panel_2.add(getPanel_1());
			panel_2.add(getPanel_3());
		}
		return panel_2;
	}
	private JLabel getLblParaPoderVer() {
		if (lblParaPoderVer == null) {
			lblParaPoderVer = new JLabel("Para poder ver sus datos y resultados ha de identificarse:");
		}
		return lblParaPoderVer;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getBtnIdentificarse());
		}
		return panel_3;
	}
	private JButton getBtnIdentificarse() {
		if (btnIdentificarse == null) {
			btnIdentificarse = new JButton("Identificarse");
		}
		return btnIdentificarse;
	}
	private JPanel getPnTabla() {
		if (pnTabla == null) {
			pnTabla = new JPanel();
			pnTabla.setBorder(new TitledBorder(null, "Tabla", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnTabla.setLayout(new BorderLayout(0, 0));
			pnTabla.add(getScrollPane());
			pnTabla.add(getPanel_6(), BorderLayout.SOUTH);
		}
		return pnTabla;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
		}
		return table;
	}
	private JPanel getPanel_4_1() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.setLayout(new GridLayout(0, 2, 0, 2));
			pnOpciones.add(getPanel_4_2());
		}
		return pnOpciones;
	}
	private JPanel getPanel_4_2() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.add(getPanel_5());
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBorder(new TitledBorder(null, "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return panel_6;
	}
}

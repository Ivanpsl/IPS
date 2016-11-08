package igu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logica.Gestor;
import logica.GestorCategorias;
import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.PlazoInscripcion;
import usuarios.Organizador;
import utiles.Comprobaciones;
import utiles.ConversorFechas;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JList;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	DefaultListModel<String> modeloCategoriasDefecto = new DefaultListModel<String>();
	private static final long serialVersionUID = 1L;
	private ModeloNoEditable modeloTabla;
	private ArrayList<Evento> contenidoEventos; // ArrayList que se mostrara en
												// la tabla
	private String[] cabeceraTablaSeleccionEventos = { "Nombre", "Tipo", "Distancia", "Estado Carrera", "Plazo",
			"Precio" };
	// private ArrayList<Evento> eventosTabla; //lo usaremos en un futuro para
	// realizad filtrados
	private Evento eventoPulsado;

	Organizador organizador;
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
	private JPanel pnSelecEventosUsuario;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblParaPoderVer;
	private JPanel panel_3;
	private JButton btnIdentificarse;
	private JPanel pnTabla;
	private JScrollPane scrollPane;
	private JTable tbEventosSeleccion;
	private JPanel pnOpciones;
	private JPanel pnInfoEvPulsadoPrincipal;
	private DefaultListModel<String> modeloLista;

	private VentanaPrincipal vP;
	private Gestor g;
	private JPanel pnNombre;
	private JLabel lblNombreAt;
	private JTextField txtNombreAtleta;
	private JPanel pnFechaNacimiento;
	private JLabel lblNewLabel;
	private JTextField txtFechaNacimiento;
	private JPanel panel_7;
	private JButton btnMostrarResultadosAtleta;
	private JPanel panel_8;
	private JPanel pnEventosOrganizador;
	private JPanel pnCabezeraOrganizador;
	private JPanel pnCentroOrganizador;
	private JPanel pnTablaOrganizador;
	private JScrollPane pnScrollOrganizador;
	private JTable tablaEventosDelOrganizador;
	private JPanel pnDescripcionEventoOrganizador;
	private JPanel pnInfoOrganizador;
	private JPanel pnAccionesOrg;
	private JButton btA�adirEventoOr;
	private JButton btEditarEventoOr;
	private JLabel lbNombreOr;
	private JTextPane textPane;
	private JPanel pnCrearEvento;
	private JPanel pnBotonesCrearEvento;
	private JPanel pnUnoVacio;
	private JPanel pnDosBotones;
	private JButton btCancelarEvento;
	private JButton btCrearEvento;
	private JPanel pnContenidoCreacionEvento;
	private JLabel lblNombre;
	private JTextField tfNombreEvento;
	private JLabel lblTipo;
	private JComboBox cbTipoEventos;
	private JLabel lblDistancia;
	private JTextField tfDistanciaEvento;
	private JLabel lblNmeroDePlazas;
	private JLabel lbKm;
	private JSpinner spinnerPlazas;
	List<Atleta> atletasAInscribir = new ArrayList<Atleta>();
	List<Atleta> atletasARegistrar = new ArrayList<Atleta>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setResizable(false);
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
		setTitle("Gestor de Eventos");
		vP = this;
		g = new Gestor();
		organizador = new Organizador("PACO", "XXX", "PACO ORGANIZER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 512);
		pnPrincipal = new JPanel();
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnPrincipal);
		pnPrincipal.setLayout(new CardLayout(0, 0));
		pnPrincipal.add(getPnInicio(), "pn_inicio");
		pnPrincipal.add(getPnOrganizador(), "pn_Organizador");
		pnPrincipal.add(getPnUsuario(), "pn_usuario");
	}

	private boolean comprobarDatosInscribirse() {
		if (txtDNIInscribirse.getText().equals("") || !g.comprobarFecha(txtFechaInscribirse.getText())
				|| txtSexoInscribirse.getText().equals("") || txtNombreInscribirse.getText().equals("")) {
			return false;
		}
		return true;
	}

	private void AnadirInscritoALista() {
		if (comprobarDatosInscribirse()) {
			int sexo = 0;
			boolean existe = false;
			if (txtSexoInscribirse.getText().toUpperCase().equals("MASCULINO")) {
				sexo = 0;
			}
			if (txtSexoInscribirse.getText().toUpperCase().equals("FEMENINO")) {
				sexo = 1;
			}

			String dni = txtDNIInscribirse.getText();
			dniInscrito.add(txtDNIInscribirse.getText());

			Atleta at = g.buscarAtletaPorDNI(dni);
			if (at == null) {
				at = new Atleta(dni, txtNombreInscribirse.getText(), txtFechaInscribirse.getText(), sexo);
				atletasARegistrar.add(at);
				JOptionPane.showMessageDialog(null, "Atleta no registrado. Sera registrado en la base de datos");
				precioTotal = precioTotal + precioEvento;
			}

			if (!modeloLista.contains(dni)) {
				if (!g.existeAtletaEnEvento(g.getEventoSeleccionado().getId(), dni)) {
					atletasAInscribir.add(at);
					modeloLista.addElement(dni);
					precioTotal = precioTotal + precioEvento;
				} else {
					JOptionPane.showMessageDialog(null, "Dni ya inscrito en el evento");
				}
			} else {
				JOptionPane.showMessageDialog(null, "El dni ya esta en la lista");
			}
			txtDNIInscribirse.setText("");
			txtNombreInscribirse.setText("");
			txtFechaInscribirse.setText("");
			txtSexoInscribirse.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "Rellene bien todos los campos");
			btnRealizarInscripcion.setEnabled(false);
		}

	}

	private void inscribir() {
		g.registrarLoteAtletas(atletasARegistrar);
		g.inscribirLote(atletasAInscribir);
		modeloLista.removeAllElements();
		listInscribirse.setModel(modeloLista);

		txtDNIInscribirse.setText("");
		txtNombreInscribirse.setText("");
		txtFechaInscribirse.setText("");
		txtSexoInscribirse.setText("");

		atletasARegistrar.clear();
		atletasAInscribir.clear();

	}

	private void borrarDatos() {
		txtDNIInscribirse.setText("");
		txtNombreInscribirse.setText("");
		txtFechaInscribirse.setText("");
		txtSexoInscribirse.setText("");
		modeloLista.removeAllElements();
		listInscribirse.setModel(modeloLista);
		atletasARegistrar.clear();
		atletasAInscribir.clear();
	}

	private void autorrellenar() {
		String dni = txtDNIAtleta.getText();
		if (!dni.equals("")) {
			txtDNIInscribirse.setText(dni);
			txtNombreInscribirse.setText(txtNombreAtleta.getText());
			txtFechaInscribirse.setText(txtFechaNacimiento.getText());
			txtSexoInscribirse.setText(g.buscarAtletaPorDNI(dni).getSexo() == 0 ? "masculino" : "femenino");
		}
	}

	/**
	 * Metodo usado para navegar por las pestaÃ±as principales
	 * 
	 * @param opcion
	 *            : inicio: pantalla de seleccion de modo organizador: menus de
	 *            organizadores usuario: menus de usuarios
	 */
	private void cambiarPanelesPrincipales(String opcion) {
		if (opcion.equals("organizador")) {
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, "pn_Organizador");
			mostrarTablaEventosOrganizador();
		}
		if (opcion.equals("usuario")) {
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, "pn_usuario");
			mostrarTablaEventosUsuario();
		}
		if (opcion.equals("inicio"))
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal, "pn_inicio");
	}

	/**
	 * Metodo usado para cambiar la cabecera de los menus de usuario
	 * 
	 * @param opcion
	 *            1=cabecera con datos 2=cabecera sin datos
	 */
	private void cambiarCabeceraUsuario(int opcion) {
		if (opcion == 0)
			((CardLayout) pnCabecera.getLayout()).show(pnCabecera, "usuario_sinIdentificar");
		if (opcion == 1) {
			((CardLayout) pnCabecera.getLayout()).show(pnCabecera, "usuario_Identificado");

		}
	}

	/**
	 * Metodo usado para navegar por las distintas ventanas que vera el usuario
	 * 
	 * @param opcion:
	 *            0: panel de eventos 1: panel de inscripciones
	 * 
	 *            3: panel resultados evento 4: panel resultados atleta
	 * 
	 */
	private void cambiarPanelesUsuario(int opcion) {
		if (opcion == 0) {
			reiniciarDatosPulsados();
			mostrarTablaEventosUsuario();
			((CardLayout) pnCardUsuario.getLayout()).show(pnCardUsuario, "pn_eventosUsuario");
		}
		if (opcion == 1) {
			((CardLayout) pnCardUsuario.getLayout()).show(pnCardUsuario, "pn_inscripcionesUsuario");

		}
		if (opcion == 2) {
			((CardLayout) pnCardUsuario.getLayout()).show(pnCardUsuario, "pn_pagosUsuario");
		}

	}

	// TABLA JAVI
	/**
	 * Para cargar la tabla de los eventos que ha creado el organizador.
	 */
	private void mostrarTablaEventosOrganizador() {
		ModeloNoEditable modeloTablaOrg = new ModeloNoEditable(cabeceraTablaSeleccionEventos, 0);
		//ArrayList<Evento> eventosOrganizador = g.getEventos(); // g.getEventosOrganizador(organizador);
		ArrayList<Evento> eventosOrganizador = organizador.getMisEventos();
		for (Evento ev : eventosOrganizador) {
			String[] fila = new String[6];
			fila[0] = ev.getNombre();
			fila[1] = ev.getTipo();
			fila[2] = String.valueOf(ev.getDistancia());
			if (!ev.getFinalizado()) {
				if (ev.getUltimoPlazo() != null) {
					fila[3] = "Inscripcion";
					fila[4] = "ABIERTO";
					fila[5] = String.valueOf(ev.getUltimoPlazo().getPrecio());
				} else {
					fila[3] = "Carrera";
					fila[4] = "CERRADO";
					fila[5] = "CERRADO";
				}

			} else {
				if (ev.getFinalizado()) {
					fila[3] = "Finalizada";
					fila[4] = "CERRADO";
					fila[5] = "CERRADO";
				}
			}
			modeloTablaOrg.addRow(fila);
		}
		tablaEventosDelOrganizador.getTableHeader().setReorderingAllowed(false);
		tablaEventosDelOrganizador.setModel(modeloTablaOrg);
		// reiniciarDatosPulsados(); //Hacer uno mio
	}

	/**
	 * Metodo usado para mostrar los datos de la tabla de seleccion de evento
	 */
	private void mostrarTablaEventosUsuario() {
		modeloTabla = new ModeloNoEditable(cabeceraTablaSeleccionEventos, 0);
		contenidoEventos = g.getEventos();
		for (Evento ev : contenidoEventos) {
			String[] fila = new String[6];
			fila[0] = ev.getNombre();
			fila[1] = ev.getTipo();
			fila[2] = String.valueOf(ev.getDistancia());
			if (!ev.getFinalizado()) {

				if (ev.getUltimoPlazo() != null) {
					fila[3] = "Inscripcion";
					fila[4] = "ABIERTO";
					fila[5] = String.valueOf(ev.getUltimoPlazo().getPrecio());
				} else {
					fila[3] = "Carrera";
					fila[4] = "CERRADO";
					fila[5] = "CERRADO";
				}

			} else {
				if (ev.getFinalizado()) {
					fila[3] = "Finalizada";
					fila[4] = "CERRADO";
					fila[5] = "CERRADO";
				}
			}
			modeloTabla.addRow(fila);
		}
		tbEventosSeleccion.getTableHeader().setReorderingAllowed(false);
		tbEventosSeleccion.setModel(modeloTabla);
		reiniciarDatosPulsados();
	}

	/**
	 * Metodo que reinicia los datos de la seleccion
	 */
	private void reiniciarDatosPulsados() {

		btnPasarAInscripcion.setEnabled(false);
		btnResultadosEvento.setEnabled(false);

		eventoPulsado = null;
		txEventoPulsadoNombre.setText("");
		txEventoPulsadoPlazas.setText("");
		textAreaCategoriasAdmitidas.setText("");
		textAreaPlazosInscripcionEventoUsuario.setText("");

	}

	/**
	 * 
	 * @param ev:
	 *            evento pulsado
	 * @param desc:
	 *            identifica que descripcion es, la de eventos de usuario (1) ,
	 *            o la de eventos de organizador (2)
	 */
	private void pulsarEvento(Evento ev, int desc) {
		eventoPulsado = ev;
		if (desc == 1) { // desc= 1 nos situa en la descripcion del panel de
							// usuarios

			if (eventoPulsado.getFinalizado()) {
				// btnMostrarResultadosAtleta.setEnabled(true);
				if (eventoPulsado.getClasificaciones() != null && eventoPulsado.getClasificaciones().size() > 0)
					btnResultadosEvento.setEnabled(true);
				btnPasarAInscripcion.setEnabled(false);
			}
			if (eventoPulsado.getPlazos() != null && !eventoPulsado.getFinalizado())
				btnPasarAInscripcion.setEnabled(true);
			else
				btnPasarAInscripcion.setEnabled(false);
			txEventoPulsadoNombre.setText(ev.getNombre());
			txEventoPulsadoPlazas.setText(String.valueOf(ev.getPlazasDisponibles()));
			ArrayList<Categoria> cat = ev.getCategorias();
			// ArrayList<Categoria> catEnListado;
			StringBuilder sB = new StringBuilder();
			for (Categoria c : cat) {
				String sexo;
				if (c.getSexo() == 0)
					sexo = "Masculino";
				else
					sexo = "Femenino";
				sB.append(c.getNombre() + " De " + c.getEdadMinima() + " a " + c.getEdadMaxima() + " Sexo: " + sexo
						+ "\n");
			}
			textAreaCategoriasAdmitidas.setText(sB.toString());
			ArrayList<PlazoInscripcion> plazos = ev.getPlazos();
			sB = new StringBuilder();
			if (plazos != null && plazos.size() > 0)
				for (PlazoInscripcion p : plazos) {
					sB.append("De: " + p.getFechaInicio() + " a: " + p.getFechaFin() + "\nPrecio: " + p.getPrecio()
							+ "� \n");
				}
			else
				sB.append("PLAZOS CERRADOS");
			textAreaPlazosInscripcionEventoUsuario.setText(sB.toString());
		}
	}

	private void cargarCabeceraAtleta() {
		Atleta at = g.getAtletaIdentificado();
		txtDNIAtleta.setText(at.getDNI());
		txtNombreAtleta.setText(at.getNombre());
		txtFechaNacimiento.setText(at.getFechaNacimiento());
		pnAtletaResumen
				.setBorder(new TitledBorder(null, at.getDNI(), TitledBorder.LEADING, TitledBorder.TOP, null, null));

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
			pnOrganizador.setLayout(new CardLayout(0, 0));
			pnOrganizador.add(getPnEventosOrganizador(), "pn_EventosOrganizador");
			pnOrganizador.add(getPnCrearEvento(), "pn_CrearEvento");
		}
		return pnOrganizador;
	}

	private void cambiarPanelesOrganizador(String opcion) {
		switch (opcion) {
		case "crear":
			((CardLayout) pnOrganizador.getLayout()).show(pnOrganizador, "pn_CrearEvento");
			break;
		case "misEventos":
			((CardLayout) pnOrganizador.getLayout()).show(pnOrganizador, "pn_EventosOrganizador");
			break;

		default:
			break;
		}
	}

	private JButton getBtAtrasOrganizador() {
		if (btAtrasOrganizador == null) {
			btAtrasOrganizador = new JButton("Atr\u00E1s");
			btAtrasOrganizador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cambiarPanelesPrincipales("inicio");
				}
			});
		}
		return btAtrasOrganizador;
	}

	private JPanel getPnSurOrganizador() {
		if (pnSurOrganizador == null) {
			pnSurOrganizador = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnSurOrganizador.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnSurOrganizador.add(getBtAtrasOrganizador());
		}
		return pnSurOrganizador;
	}

	private JPanel getPnUsuario() {
		if (pnUsuario == null) {
			pnUsuario = new JPanel();
			pnUsuario.setLayout(new BorderLayout(0, 0));
			pnUsuario.add(getPnCabecera(), BorderLayout.NORTH);
			pnUsuario.add(getPnCardUsuario(), BorderLayout.CENTER);
		}
		return pnUsuario;
	}

	private JPanel getPnCabecera() {
		if (pnCabecera == null) {
			pnCabecera = new JPanel();
			pnCabecera.setBorder(
					new TitledBorder(null, "Datos del atleta: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCabecera.setLayout(new CardLayout(0, 0));
			pnCabecera.add(getPnIdentificate(), "usuario_sinIdentificar");
			pnCabecera.add(getPnAtletaResumen(), "usuario_Identificado");

		}
		return pnCabecera;
	}

	private JPanel getPnAtletaResumen() {
		if (pnAtletaResumen == null) {
			pnAtletaResumen = new JPanel();

			pnAtletaResumen.setLayout(new BorderLayout(0, 0));
			pnAtletaResumen.add(getPanel_7(), BorderLayout.NORTH);
			pnAtletaResumen.add(getPanel_8(), BorderLayout.SOUTH);
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

	private JPanel getPnSelecEventosUsuario() {
		if (pnSelecEventosUsuario == null) {
			pnSelecEventosUsuario = new JPanel();
			pnSelecEventosUsuario.setLayout(new BorderLayout(0, 0));
			pnSelecEventosUsuario.add(getPnTabla(), BorderLayout.CENTER);
			pnSelecEventosUsuario.add(getPanel_4_1(), BorderLayout.SOUTH);
			pnSelecEventosUsuario.add(getPanel_4(), BorderLayout.EAST);
		}
		return pnSelecEventosUsuario;
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
			btnIdentificarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String dni = JOptionPane.showInputDialog(pnPrincipal, "Introduzca su DNI: ", "Identificacion",
							JOptionPane.QUESTION_MESSAGE);
					if (dni != null) {
						if (g.identificarAtletaPorDNI(dni)) {
							cargarCabeceraAtleta();
							cambiarCabeceraUsuario(1);
							ArrayList<Evento> evParticipa = g.obtenerEventosParticipaPorDNI(dni);
							if (evParticipa.size() > 0) {
								boolean datos = false;
								for (Evento ev : evParticipa)
									if (ev.getFinalizado())
										datos = true;
								if (datos) {
									btnMostrarResultadosAtleta.setText("Mostrar resultados");
									btnMostrarResultadosAtleta.setEnabled(true);
								} else {
									btnMostrarResultadosAtleta.setEnabled(false);
									btnMostrarResultadosAtleta.setText("Sin resultados almacenados");
								}
							} else {
								btnMostrarResultadosAtleta.setEnabled(false);
								btnMostrarResultadosAtleta.setText("Sin resultados almacenados");
							}
						} else
							JOptionPane.showInternalMessageDialog(pnPrincipal, "Atleta no identificado",
									"Error: Atleta no registrado", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnIdentificarse;
	}

	private JPanel getPnTabla() {
		if (pnTabla == null) {
			pnTabla = new JPanel();
			pnTabla.setBorder(new TitledBorder(null, "Tabla", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnTabla.setLayout(new BorderLayout(0, 0));
			pnTabla.add(getScrollPane());
		}
		return pnTabla;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTbEventosSeleccion());
		}
		return scrollPane;
	}

	private JTable getTbEventosSeleccion() {
		if (tbEventosSeleccion == null) {
			tbEventosSeleccion = new JTable();
			tbEventosSeleccion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tbEventosSeleccion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int fila = tbEventosSeleccion.getSelectedRow();
					pulsarEvento(contenidoEventos.get(fila), 1);
					eventoSeleccionado = contenidoEventos.get(fila);
					if(!eventoSeleccionado.getFinalizado())
						precioEvento = contenidoEventos.get(fila).getUltimoPlazo().getPrecio();
					precioTotal = 0;
				}
			});
		}
		return tbEventosSeleccion;
	}

	private JPanel getPanel_4_1() {
		if (pnOpciones == null) {
			pnOpciones = new JPanel();
			pnOpciones.setLayout(new BorderLayout(0, 0));
			pnOpciones.add(getBtnAtras(), BorderLayout.WEST);
			pnOpciones.add(getBtnPasarAInscripcion(), BorderLayout.EAST);
		}
		return pnOpciones;
	}

	private JPanel getPnInfoEvPulsadoPrincipal() {
		if (pnInfoEvPulsadoPrincipal == null) {
			pnInfoEvPulsadoPrincipal = new JPanel();
			pnInfoEvPulsadoPrincipal.setBorder(null);
			pnInfoEvPulsadoPrincipal.setLayout(new BorderLayout(0, 0));
			pnInfoEvPulsadoPrincipal.add(getPnEventoPulsadoCategoriasAdmitidas(), BorderLayout.CENTER);
			pnInfoEvPulsadoPrincipal.add(getPnLinkEventoInfoPulsado(), BorderLayout.NORTH);
		}
		return pnInfoEvPulsadoPrincipal;
	}

	private JPanel getPnNombre() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			pnNombre.add(getLblNombreAt());
			pnNombre.add(getTxtNombreAtleta());
		}
		return pnNombre;
	}

	private JLabel getLblNombreAt() {
		if (lblNombreAt == null) {
			lblNombreAt = new JLabel("Nombre: ");
		}
		return lblNombreAt;
	}

	private JTextField getTxtNombreAtleta() {
		if (txtNombreAtleta == null) {
			txtNombreAtleta = new JTextField();
			txtNombreAtleta.setEditable(false);
			txtNombreAtleta.setColumns(10);
		}
		return txtNombreAtleta;
	}

	private JPanel getPnFechaNacimiento() {
		if (pnFechaNacimiento == null) {
			pnFechaNacimiento = new JPanel();
			pnFechaNacimiento.add(getLblNewLabel());
			pnFechaNacimiento.add(getTxtFechaNacimiento());
		}
		return pnFechaNacimiento;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Fecha de nacimiento: ");
		}
		return lblNewLabel;
	}

	private JTextField getTxtFechaNacimiento() {
		if (txtFechaNacimiento == null) {
			txtFechaNacimiento = new JTextField();
			txtFechaNacimiento.setEditable(false);
			txtFechaNacimiento.setColumns(10);
		}
		return txtFechaNacimiento;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.add(getPanel_4_4());
			panel_7.add(getPnNombre());
			panel_7.add(getPnFechaNacimiento());
		}
		return panel_7;
	}

	private JButton getBtnMostrarResultadosAtleta() {
		if (btnMostrarResultadosAtleta == null) {
			btnMostrarResultadosAtleta = new JButton("Mostrar resultados");
			btnMostrarResultadosAtleta.setEnabled(false);
			btnMostrarResultadosAtleta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DialogResultadosAtleta dR = new DialogResultadosAtleta(g.getAtletaIdentificado(), g, vP);
					dR.setVisible(true);
				}
			});
		}
		return btnMostrarResultadosAtleta;
	}

	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_8.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel_8.add(getBtnMostrarResultadosAtleta());
		}
		return panel_8;
	}

	private JPanel getPnEventosOrganizador() {
		if (pnEventosOrganizador == null) {
			pnEventosOrganizador = new JPanel();
			pnEventosOrganizador.setLayout(new BorderLayout(0, 0));
			pnEventosOrganizador.add(getPnCabezeraOrganizador(), BorderLayout.NORTH);
			pnEventosOrganizador.add(getPnCentroOrganizador(), BorderLayout.CENTER);
			pnEventosOrganizador.add(getPnSurOrganizador(), BorderLayout.SOUTH);
		}
		return pnEventosOrganizador;
	}

	private JPanel getPnCabezeraOrganizador() {
		if (pnCabezeraOrganizador == null) {
			pnCabezeraOrganizador = new JPanel();
			pnCabezeraOrganizador.setLayout(new BorderLayout(0, 0));
			pnCabezeraOrganizador.add(getPnInfoOrganizador(), BorderLayout.CENTER);
			pnCabezeraOrganizador.add(getPnAccionesOrg(), BorderLayout.EAST);
		}
		return pnCabezeraOrganizador;
	}

	private JPanel getPnCentroOrganizador() {
		if (pnCentroOrganizador == null) {
			pnCentroOrganizador = new JPanel();
			pnCentroOrganizador.setLayout(new BorderLayout(0, 0));
			pnCentroOrganizador.add(getPnTablaOrganizador(), BorderLayout.CENTER);
		}
		return pnCentroOrganizador;
	}

	private JPanel getPnTablaOrganizador() {
		if (pnTablaOrganizador == null) {
			pnTablaOrganizador = new JPanel();
			pnTablaOrganizador.setLayout(new BorderLayout(0, 0));
			pnTablaOrganizador.add(getPnScrollOrganizador(), BorderLayout.CENTER);
			pnTablaOrganizador.add(getPnDescripcionEventoOrganizador(), BorderLayout.EAST);
			pnTablaOrganizador.setBorder(
					new TitledBorder(null, "Mis eventos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return pnTablaOrganizador;
	}

	private JScrollPane getPnScrollOrganizador() {
		if (pnScrollOrganizador == null) {
			pnScrollOrganizador = new JScrollPane();
			pnScrollOrganizador.setViewportView(getTablaEventosDelOrganizador());
		}
		return pnScrollOrganizador;
	}

	private JTable getTablaEventosDelOrganizador() {
		if (tablaEventosDelOrganizador == null) {
			tablaEventosDelOrganizador = new JTable();
		}
		return tablaEventosDelOrganizador;
	}

	private JPanel getPnDescripcionEventoOrganizador() {
		if (pnDescripcionEventoOrganizador == null) {
			pnDescripcionEventoOrganizador = new JPanel();
			pnDescripcionEventoOrganizador.setLayout(new GridLayout(0, 1, 0, 0));
			pnDescripcionEventoOrganizador.add(getTextPane());
		}
		return pnDescripcionEventoOrganizador;
	}

	private JPanel getPnInfoOrganizador() {
		if (pnInfoOrganizador == null) {
			pnInfoOrganizador = new JPanel();
			pnInfoOrganizador.setLayout(new GridLayout(3, 2, 0, 0));
			pnInfoOrganizador.add(getLbNombreOr());
		}
		return pnInfoOrganizador;
	}

	private JPanel getPnAccionesOrg() {
		if (pnAccionesOrg == null) {
			pnAccionesOrg = new JPanel();
			pnAccionesOrg.setLayout(new GridLayout(2, 1, 0, 0));
			pnAccionesOrg.add(getBtA�adirEventoOr());
			pnAccionesOrg.add(getBtEditarEventoOr());
		}
		return pnAccionesOrg;
	}

	private JButton getBtA�adirEventoOr() {
		if (btA�adirEventoOr == null) {
			btA�adirEventoOr = new JButton("A\u00F1adir evento");
			btA�adirEventoOr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cambiarPanelesOrganizador("crear");
				}
			});
			btA�adirEventoOr.setToolTipText("Crear un nuevo evento");
		}
		return btA�adirEventoOr;
	}

	private JButton getBtEditarEventoOr() {
		if (btEditarEventoOr == null) {
			btEditarEventoOr = new JButton("Editar evento");
			btEditarEventoOr.setToolTipText("Edita el evento seleccionado.");
			btEditarEventoOr.setMnemonic('E');
		}
		return btEditarEventoOr;
	}

	private JLabel getLbNombreOr() {
		if (lbNombreOr == null) {
			lbNombreOr = new JLabel("Nombre:");
			lbNombreOr.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbNombreOr;
	}

	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
		}
		return textPane;
	}

	private JPanel getPnCrearEvento() {
		if (pnCrearEvento == null) {
			pnCrearEvento = new JPanel();
			pnCrearEvento.setLayout(new BorderLayout(0, 0));
			pnCrearEvento.add(getPnBotonesCrearEvento(), BorderLayout.SOUTH);
			pnCrearEvento.add(getPnContenidoCreacionEvento(), BorderLayout.CENTER);
		}
		return pnCrearEvento;
	}

	private JPanel getPnBotonesCrearEvento() {
		if (pnBotonesCrearEvento == null) {
			pnBotonesCrearEvento = new JPanel();
			pnBotonesCrearEvento.setLayout(new GridLayout(1, 2, 0, 0));
			pnBotonesCrearEvento.add(getPnUnoVacio());
			pnBotonesCrearEvento.add(getPnDosBotones());
		}
		return pnBotonesCrearEvento;
	}

	private JPanel getPnUnoVacio() {
		if (pnUnoVacio == null) {
			pnUnoVacio = new JPanel();
		}
		return pnUnoVacio;
	}

	private JPanel getPnDosBotones() {
		if (pnDosBotones == null) {
			pnDosBotones = new JPanel();
			pnDosBotones.setLayout(new GridLayout(0, 2, 20, 10));
			pnDosBotones.add(getBtCancelarEvento());
			pnDosBotones.add(getBtCrearEvento());
		}
		return pnDosBotones;
	}

	private JButton getBtCancelarEvento() {
		if (btCancelarEvento == null) {
			btCancelarEvento = new JButton("Cancelar");
			btCancelarEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cambiarPanelesOrganizador("misEventos");
				}
			});
		}
		return btCancelarEvento;
	}

	private JButton getBtCrearEvento() {
		if (btCrearEvento == null) {
			btCrearEvento = new JButton("Crear evento");
			btCrearEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (faltaAlgoPorRellenar()) {
						JOptionPane.showMessageDialog(null,
								"Por favor, rellene todos los campos.");
						return;
					}
					Evento evento = crearEventoOrgnizador();
					if (evento == null) {
						cambiarPanelesOrganizador("pnEventosOrganizador");
						return;
					}
					// JAVIMENSAJE Mirar aqui a ver si el evento tiene todos los
					// atributos que necesita la bd, si no, que los genere el
					// gestor.
					organizador.crearEvento(evento);// Esto deberia actualizarse
													// a la base de datos.
					g.a�adirEvento(evento);
					actualizarModeloTablaOr();
					cambiarPanelesOrganizador("misEventos");

				}
			});
		}
		return btCrearEvento;
	}
	
	private void actualizarModeloTablaOr(){
		mostrarTablaEventosOrganizador();
	}

	private boolean faltaAlgoPorRellenar() {
		if (getTfNombreEvento().getText().isEmpty() || getTfDistanciaEvento().getText().isEmpty()
				|| getTfTipoEvetno().getText().isEmpty()) {
			return true;
		}
		return false;
	}

	ArrayList<Categoria> misCategoriasCreadas;

	private JPanel getPnContenidoCreacionEvento() {
		if (pnContenidoCreacionEvento == null) {
			misCategoriasCreadas = new ArrayList<Categoria>(); // Lista de
																// categorias
																// que vamos a
																// crear

			pnContenidoCreacionEvento = new JPanel();
			pnContenidoCreacionEvento.setLayout(null);
			pnContenidoCreacionEvento.add(getLblNombre());
			pnContenidoCreacionEvento.add(getTfNombreEvento());
			pnContenidoCreacionEvento.add(getLblTipo());
			// pnContenidoCreacionEvento.add(getCbTipoEventos());
			pnContenidoCreacionEvento.add(getLblDistancia());
			pnContenidoCreacionEvento.add(getTfDistanciaEvento());
			pnContenidoCreacionEvento.add(getLbKm());
			pnContenidoCreacionEvento.add(getSpinnerPlazas());
			pnContenidoCreacionEvento.add(getTfTipoEvetno());
			pnContenidoCreacionEvento.add(getLblNmeroDePlazas());
			pnContenidoCreacionEvento.add(getLblCategorasDelEvento());
			pnContenidoCreacionEvento.add(getCbCatDef());
			pnContenidoCreacionEvento.add(getScrollPaneCategorias());
			pnContenidoCreacionEvento.add(getBtA�adirCat());

			pnContenidoCreacionEvento.add(getBtEditarCategoria());
			pnContenidoCreacionEvento.add(getScrollPaneFechasIns());
			pnContenidoCreacionEvento.add(getBtnAadir());
			pnContenidoCreacionEvento.add(getBtnEditar());

			JLabel lblFechasInscripcin = new JLabel("Fechas inscripci\u00F3n");
			lblFechasInscripcin.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechasInscripcin.setBounds(51, 219, 144, 14);
			pnContenidoCreacionEvento.add(lblFechasInscripcin);
			pnContenidoCreacionEvento.add(getLblFechaComienzoEvento());
			pnContenidoCreacionEvento.add(getCbDia());
			pnContenidoCreacionEvento.add(getCbMes());
			pnContenidoCreacionEvento.add(getCbA�o());
			pnContenidoCreacionEvento.add(getLblDa());
			pnContenidoCreacionEvento.add(getLbMes());
			pnContenidoCreacionEvento.add(getLbA�o());
		}
		return pnContenidoCreacionEvento;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre del evento:");
			lblNombre.setBounds(10, 31, 142, 33);
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblNombre;
	}

	private JTextField getTfNombreEvento() {
		if (tfNombreEvento == null) {
			tfNombreEvento = new JTextField();
			tfNombreEvento.setHorizontalAlignment(SwingConstants.CENTER);
			tfNombreEvento.setBounds(162, 37, 106, 20);
			tfNombreEvento.setColumns(10);
		}
		return tfNombreEvento;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo: ");
			lblTipo.setBounds(104, 75, 48, 22);
			lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblTipo;
	}

	private JComboBox getCbTipoEventos() {
		if (cbTipoEventos == null) {
			ArrayList<String> tiposEventos = new ArrayList<String>();
			tiposEventos.add(" ");
			tiposEventos.addAll(Evento.getTiposEventosPorDefecto()); // Cargamos
																		// los
																		// eventos
																		// que
																		// tenemos
																		// por
																		// defecto
																		// despues
																		// del
																		// vacio,
																		// que
																		// es la
																		// opcion
																		// que
																		// se
																		// pone
																		// si se
																		// quiere
																		// aÃ±adir
																		// uno
																		// propio.
			cbTipoEventos = new JComboBox();
			cbTipoEventos.setEnabled(false);
			cbTipoEventos.setBounds(298, 76, 118, 20);
		}
		return cbTipoEventos;
	}

	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia:");
			lblDistancia.setBounds(76, 108, 76, 33);
			lblDistancia.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblDistancia;
	}

	private JTextField getTfDistanciaEvento() {
		if (tfDistanciaEvento == null) {
			tfDistanciaEvento = new JTextField();
			tfDistanciaEvento.setBounds(162, 114, 50, 20);
			tfDistanciaEvento.setColumns(10);
		}
		return tfDistanciaEvento;
	}

	private JLabel getLblNmeroDePlazas() {
		if (lblNmeroDePlazas == null) {
			lblNmeroDePlazas = new JLabel("N\u00FAmero de plazas:");
			lblNmeroDePlazas.setBounds(38, 152, 114, 33);
			lblNmeroDePlazas.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblNmeroDePlazas;
	}

	private JLabel getLbKm() {
		if (lbKm == null) {
			lbKm = new JLabel("Km");
			lbKm.setHorizontalAlignment(SwingConstants.CENTER);
			lbKm.setBounds(222, 117, 29, 14);
		}
		return lbKm;
	}

	int numeroPlazasEvento = 0;
	private JTextField tfTipoEvetno;
	private JPanel pnInfoEvPulsado;
	private JButton btnPasarAInscripcion;
	private JButton btnAtras;
	private JPanel pnInfoEvPulsadoPlazos;
	private JLabel lblNombre_1;
	private JTextField txEventoPulsadoNombre;
	private JPanel pnNombreEvPulsado;
	private JPanel pnPlazasDisponibles;
	private JLabel lblNewLabel_1;
	private JTextField txEventoPulsadoPlazas;
	private JPanel pnLinkEventoInfoPulsado;
	private JTextArea textAreaCategoriasAdmitidas;
	private JPanel pnEventoPulsadoCategoriasAdmitidas;
	private JTextArea textAreaPlazosInscripcionEventoUsuario;
	private JPanel pnCardUsuario;
	private JPanel pnResultadosAtleta;
	private JPanel pnResultadosEvento;
	private JLabel lblCategorasDelEvento;
	private JCheckBox cbCatDef;
	private JScrollPane scrollPaneCategorias;
	private JList list;
	private JPanel pn_DNI;
	private JLabel lblDni;
	private JTextField txtDNIAtleta;
	private JPanel pnInscribirse;
	private JPanel pnListaInscritos;
	private JScrollPane scrollPane_1;
	private JButton btA�adirAtleta;
	private JLabel label;
	private JTextField txtDNIInscribirse;
	private JTextField txtNombreInscribirse;
	private JTextField txtFechaInscribirse;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblFechaNacimientoddmmyyyy;
	private JLabel label_4;
	private JTextField txtSexoInscribirse;
	private JButton btnUsuarioActual;
	private JList<String> listInscribirse;
	private JButton btnRealizarInscripcion;
	private JButton btnSeleccionarOtroEvento;

	private JSpinner getSpinnerPlazas() {
		if (spinnerPlazas == null) {
			spinnerPlazas = new JSpinner();
			spinnerPlazas.setBounds(162, 158, 50, 20);
			SpinnerModel modelo = new SpinnerNumberModel(numeroPlazasEvento, 0, 9999, 1);
			spinnerPlazas.setModel(modelo);
		}
		return spinnerPlazas;
	}

	private JTextField getTfTipoEvetno() {
		if (tfTipoEvetno == null) {
			tfTipoEvetno = new JTextField();
			tfTipoEvetno.setToolTipText("Tipo de evento (marat\u00F3n, triatl\u00F3n, monta\u00F1a...)");
			tfTipoEvetno.setBounds(162, 76, 106, 20);
			tfTipoEvetno.setHorizontalAlignment(SwingConstants.CENTER);
			tfTipoEvetno.setColumns(10);
			// if (getCbTipoEventos().getSelectedItem() == " ") {
			// tfTipoEvetno.setEnabled(true);
			// } else {
			// tfTipoEvetno.setEnabled(false);
			// }
		}
		return tfTipoEvetno;
	}

	// ----------------------------------- MÃ©todos para crear un evento

	/**
	 * LO QUE DEBERIA SER
	 */
	private void crearEvetno() {
		String nombre = getTfNombreEvento().getText();
		String tipo = getCbTipoEventos().getSelectedItem().toString();
		String Stdistancia = getTfDistanciaEvento().getText();
		int distancia = 0;
		if (Comprobaciones.esString(Stdistancia)) {
			distancia = Integer.parseInt(Stdistancia);
		} else {
			throw new IllegalArgumentException("La distancia no es un nÃºmero");
		}
		Date fechaComienzo = new Date(Calendar.getInstance().getTimeInMillis());
		// Date fechaFinInscripciones = new
		// Date(Calendar.getInstance().getTimeInMillis());
		ArrayList<Categoria> categoriasParaEvento = new ArrayList<Categoria>();
		int plazas = (int) getSpinnerPlazas().getValue();
		// Obtener las categorias del elemento en cuestion
		// Plazos de inscripcion
		ArrayList<PlazoInscripcion> plazosInscripcion = new ArrayList<PlazoInscripcion>();

		// Si todo esta OK
		g.crearEvento(nombre, tipo, distancia, fechaComienzo, plazas, categoriasParaEvento, plazosInscripcion);
	}

	// ---------------------------------- FIN metodos crear un evento
	private JPanel getPnInfoEvPulsado() {
		if (pnInfoEvPulsado == null) {
			pnInfoEvPulsado = new JPanel();
			pnInfoEvPulsado.setBorder(null);
			pnInfoEvPulsado.setLayout(new GridLayout(2, 1, 0, 0));
			pnInfoEvPulsado.add(getPnInfoEvPulsadoPrincipal());
			pnInfoEvPulsado.add(getPanel_4_2());
		}
		return pnInfoEvPulsado;
	}

	private JButton getBtnPasarAInscripcion() {
		if (btnPasarAInscripcion == null) {
			btnPasarAInscripcion = new JButton("Inscribirse");
			btnPasarAInscripcion.setEnabled(false);
			btnPasarAInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					g.confirmarSeleccion(eventoPulsado);
					cambiarPanelesUsuario(1);
					// ((CardLayout)
					// pnCardUsuario.getLayout()).show(pnCardUsuario,
					// "pn_inscripcionesUsuario");
					//
				}
			});
		}
		return btnPasarAInscripcion;
	}

	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reiniciarDatosPulsados();
					cambiarPanelesPrincipales("inicio");
				}
			});
		}
		return btnAtras;
	}

	private JPanel getPanel_4_2() {
		if (pnInfoEvPulsadoPlazos == null) {
			pnInfoEvPulsadoPlazos = new JPanel();
			pnInfoEvPulsadoPlazos.setBorder(new TitledBorder(null, "Plazos de inscripcion: ", TitledBorder.LEADING,
					TitledBorder.BELOW_TOP, null, null));
			pnInfoEvPulsadoPlazos.setLayout(new BorderLayout(0, 0));
			pnInfoEvPulsadoPlazos.add(getTextAreaPlazosInscripcionEventoUsuario());
		}
		return pnInfoEvPulsadoPlazos;
	}

	private JLabel getLblNombre_1() {
		if (lblNombre_1 == null) {
			lblNombre_1 = new JLabel("Nombre: ");
		}
		return lblNombre_1;
	}

	private JTextField getTxEventoPulsadoNombre() {
		if (txEventoPulsadoNombre == null) {
			txEventoPulsadoNombre = new JTextField();
			txEventoPulsadoNombre.setHorizontalAlignment(SwingConstants.LEFT);
			txEventoPulsadoNombre.setBorder(null);
			txEventoPulsadoNombre.setEditable(false);
			txEventoPulsadoNombre.setColumns(10);
		}
		return txEventoPulsadoNombre;
	}

	private JPanel getPanel_4_3() {
		if (pnNombreEvPulsado == null) {
			pnNombreEvPulsado = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnNombreEvPulsado.getLayout();
			flowLayout.setAlignment(FlowLayout.LEADING);
			pnNombreEvPulsado.add(getLblNombre_1());
			pnNombreEvPulsado.add(getTxEventoPulsadoNombre());
		}
		return pnNombreEvPulsado;
	}

	private JPanel getPnPlazasDisponibles() {
		if (pnPlazasDisponibles == null) {
			pnPlazasDisponibles = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPlazasDisponibles.getLayout();
			flowLayout.setAlignment(FlowLayout.LEADING);
			pnPlazasDisponibles.add(getLblNewLabel_1());
			pnPlazasDisponibles.add(getTxEventoPulsadoPlazas());
		}
		return pnPlazasDisponibles;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Plazas diponibles");
		}
		return lblNewLabel_1;
	}

	private JTextField getTxEventoPulsadoPlazas() {
		if (txEventoPulsadoPlazas == null) {
			txEventoPulsadoPlazas = new JTextField();
			txEventoPulsadoPlazas.setHorizontalAlignment(SwingConstants.CENTER);
			txEventoPulsadoPlazas.setBorder(null);
			txEventoPulsadoPlazas.setEditable(false);
			txEventoPulsadoPlazas.setColumns(10);
		}
		return txEventoPulsadoPlazas;
	}

	private JPanel getPnLinkEventoInfoPulsado() {
		if (pnLinkEventoInfoPulsado == null) {
			pnLinkEventoInfoPulsado = new JPanel();
			pnLinkEventoInfoPulsado.setLayout(new GridLayout(0, 1, 0, 0));
			pnLinkEventoInfoPulsado.add(getPanel_4_3());
			pnLinkEventoInfoPulsado.add(getPnPlazasDisponibles());
		}
		return pnLinkEventoInfoPulsado;
	}

	private JTextArea getTextAreaCategoriasAdmitidas() {
		if (textAreaCategoriasAdmitidas == null) {
			textAreaCategoriasAdmitidas = new JTextArea();
			textAreaCategoriasAdmitidas.setWrapStyleWord(true);
			textAreaCategoriasAdmitidas.setLineWrap(true);
			textAreaCategoriasAdmitidas.setEditable(false);
			textAreaCategoriasAdmitidas.setColumns(2);
		}
		return textAreaCategoriasAdmitidas;
	}

	private JPanel getPnEventoPulsadoCategoriasAdmitidas() {
		if (pnEventoPulsadoCategoriasAdmitidas == null) {
			pnEventoPulsadoCategoriasAdmitidas = new JPanel();
			pnEventoPulsadoCategoriasAdmitidas.setBorder(new TitledBorder(null, "Categorias admitidas: ",
					TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
			pnEventoPulsadoCategoriasAdmitidas.setLayout(new BorderLayout(0, 0));
			pnEventoPulsadoCategoriasAdmitidas.add(getTextAreaCategoriasAdmitidas());
		}
		return pnEventoPulsadoCategoriasAdmitidas;
	}

	private JTextArea getTextAreaPlazosInscripcionEventoUsuario() {
		if (textAreaPlazosInscripcionEventoUsuario == null) {
			textAreaPlazosInscripcionEventoUsuario = new JTextArea();
			textAreaPlazosInscripcionEventoUsuario.setEditable(false);
			textAreaPlazosInscripcionEventoUsuario.setLineWrap(true);
			textAreaPlazosInscripcionEventoUsuario.setWrapStyleWord(true);
		}
		return textAreaPlazosInscripcionEventoUsuario;
	}

	private JPanel getPnCardUsuario() {
		if (pnCardUsuario == null) {
			pnCardUsuario = new JPanel();
			pnCardUsuario.setLayout(new CardLayout(0, 0));
			pnCardUsuario.add(getPnSelecEventosUsuario(), "pn_eventosUsuario");
			pnCardUsuario.add(getPnResultadosAtleta(), "pn_ResultadosUsuario");
			pnCardUsuario.add(getPnResultadosEvento(), "pn_resultadosEvento");
			pnCardUsuario.add(getPnInscribirse(), "pn_inscripcionesUsuario");
			pnCardUsuario.add(getPnPagar(), "pn_pagosUsuario");
		}
		return pnCardUsuario;
	}

	private JPanel getPnResultadosAtleta() {
		if (pnResultadosAtleta == null) {
			pnResultadosAtleta = new JPanel();
		}
		return pnResultadosAtleta;
	}

	private JPanel getPnResultadosEvento() {
		if (pnResultadosEvento == null) {
			pnResultadosEvento = new JPanel();
		}
		return pnResultadosEvento;
	}

	private JLabel getLblCategorasDelEvento() {
		if (lblCategorasDelEvento == null) {
			lblCategorasDelEvento = new JLabel("Categor\u00EDas del evento: ");
			lblCategorasDelEvento.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategorasDelEvento.setBounds(777, 40, 178, 14);
		}
		return lblCategorasDelEvento;

	}

	private JCheckBox getCbCatDef() {

		if (cbCatDef == null) {
			cbCatDef = new JCheckBox("Usar categor\u00EDas por defecto");
			cbCatDef.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if (getCbCatDef().isSelected()) {
						cargarCategoriasDefectoLista();
					} else {
						cargarMisCategoriasAlModelo();
					}
				}
			});
			cbCatDef.setHorizontalAlignment(SwingConstants.LEFT);
			cbCatDef.setBounds(766, 73, 189, 23);

		}
		return cbCatDef;
	}

	private JScrollPane getScrollPaneCategorias() {
		if (scrollPaneCategorias == null) {
			scrollPaneCategorias = new JScrollPane();
			scrollPaneCategorias.setBounds(777, 115, 178, 130);
			scrollPaneCategorias.setViewportView(getListCategoriaOr());
			// scrollPane.add(getList()); //Esto no se si estara del todo
			// bien...
		}
		return scrollPaneCategorias;

	}

	private DefaultListModel<String> modeloListaCategorias = null;
	ArrayList<Categoria> categoriasAlCrearEvento;
	private JButton btA�adirCat;
	private JButton btEditarCategoria;
	private JButton btnResultadosEvento;
	private JPanel panel_4;
	private JLabel lblPlazosDeInscripcin;
	private JScrollPane scrollPaneFechasIns;
	private JList<String> listFechasInscrip;

	public ArrayList<Categoria> getCategoriasCrearEvento() {
		return categoriasAlCrearEvento;
	}

	public void a�adirCategoriaAlCrearEvento(Categoria c) {
		categoriasAlCrearEvento.add(c);
		a�adirAlModeloListaCategoriasOr(c);
	}

	ArrayList<Categoria> catDef;

	private JList<String> getListCategoriaOr() {
		if (list == null) {
			modeloListaCategorias = new DefaultListModel<String>();
			categoriasAlCrearEvento = new ArrayList<Categoria>();
			list = new JList<String>();
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			catDef = new ArrayList<Categoria>();
		}
		return list;
	}

	private void cargarMisCategoriasAlModelo() {
		getListCategoriaOr().setModel(modeloListaCategorias);
	}



	private void cargarCategoriasDefectoLista() {
		if (catDef.isEmpty()) {
			modeloCategoriasDefecto.clear();
			getListCategoriaOr().setModel(modeloCategoriasDefecto);
			catDef = GestorCategorias.getCategoriasDefecto();
			for (Categoria c : catDef) {
				modeloCategoriasDefecto.addElement(c.toString());
			}
		}
		getListCategoriaOr().setModel(modeloCategoriasDefecto);
	}

	private JButton getBtA�adirCat() {

		if (btA�adirCat == null) {
			btA�adirCat = new JButton("A\u00F1adir ");
			btA�adirCat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaCreaCategoria vcc = new VentanaCreaCategoria(VentanaPrincipal.this);
					vcc.setVisible(true);
				}
			});
			btA�adirCat.setToolTipText("A\u00F1ade una categoria al evento");
			btA�adirCat.setBounds(777, 256, 89, 23);
		}
		return btA�adirCat;

	}

	private JButton getBtEditarCategoria() {
		if (btEditarCategoria == null) {
			btEditarCategoria = new JButton("Editar");
			btEditarCategoria.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					try {
						String catString = getListCategoriaOr().getSelectedValue().toString();
						Categoria cat = null;
						if (!getCbCatDef().isSelected()) {
							for (Categoria c : categoriasAlCrearEvento) {
								if (c.toString().equals(catString))
									cat = c;
							}
						}else{
							for (Categoria c : catDef) {
								if (c.toString().equals(catString))
									cat = c;
							}
						}
						if (cat == null) {
							JOptionPane.showMessageDialog(null, "No se encuantra la categorÃ­a");
						} else {
							categoriasAlCrearEvento.remove(cat);
							VentanaCreaCategoria vcc = new VentanaCreaCategoria(VentanaPrincipal.this, cat);
							vcc.setVisible(true);
						}
					} catch (Exception e) {
						System.err.println("Ha cascado al editar probablemente por la lista");
					}

				}
			});
			btEditarCategoria.setToolTipText("Editar categoria seleccionada");
			btEditarCategoria.setBounds(866, 256, 89, 23);
		}
		return btEditarCategoria;

	}

	private JPanel getPanel_4_4() {
		if (pn_DNI == null) {
			pn_DNI = new JPanel();
			pn_DNI.add(getLblDni());
			pn_DNI.add(getTxtDNIAtleta());
		}
		return pn_DNI;
	}

	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel("DNI:");
		}
		return lblDni;
	}

	private JTextField getTxtDNIAtleta() {
		if (txtDNIAtleta == null) {
			txtDNIAtleta = new JTextField();
			txtDNIAtleta.setEditable(false);
			txtDNIAtleta.setColumns(10);
		}
		return txtDNIAtleta;
	}

	private JPanel getPnInscribirse() {
		if (pnInscribirse == null) {
			pnInscribirse = new JPanel();
			pnInscribirse.setLayout(null);
			pnInscribirse.add(getPnListaInscritos());
			pnInscribirse.add(getLabel());
			pnInscribirse.add(getTxtDNIInscribirse());
			pnInscribirse.add(getTxtNombreInscribirse());
			pnInscribirse.add(getTxtFechaInscribirse());
			pnInscribirse.add(getLabel_1());
			pnInscribirse.add(getLabel_2());
			pnInscribirse.add(getLblFechaNacimientoddmmyyyy());
			pnInscribirse.add(getLabel_4());
			pnInscribirse.add(getTxtSexoInscribirse());
			pnInscribirse.add(getBtnUsuarioActual());
			pnInscribirse.add(getBtnRealizarInscripcion());
			pnInscribirse.add(getBtnSeleccionarOtroEvento());
		}
		return pnInscribirse;
	}

	private JPanel getPnListaInscritos() {
		if (pnListaInscritos == null) {
			pnListaInscritos = new JPanel();
			pnListaInscritos.setLayout(null);
			pnListaInscritos.setBounds(623, 11, 295, 297);
			pnListaInscritos.add(getScrollPane_1_1());
			pnListaInscritos.add(getBtA�adirAtleta());
		}
		return pnListaInscritos;
	}

	private JScrollPane getScrollPane_1_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(34, 25, 239, 211);
			scrollPane_1.setViewportView(getListInscribirse());
		}
		return scrollPane_1;
	}

	private JButton getBtA�adirAtleta() {
		if (btA�adirAtleta == null) {
			btA�adirAtleta = new JButton("A\u00F1adir");
			btA�adirAtleta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnRealizarInscripcion.setEnabled(true);
					dniInscrito = new ArrayList<String>();
					AnadirInscritoALista();
				}

			});
			btA�adirAtleta.setBounds(184, 263, 89, 23);
		}
		return btA�adirAtleta;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Introduzca sus datos");
			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label.setBounds(136, 45, 165, 33);
		}
		return label;
	}

	private JTextField getTxtDNIInscribirse() {
		if (txtDNIInscribirse == null) {
			txtDNIInscribirse = new JTextField();
			txtDNIInscribirse.setColumns(10);
			txtDNIInscribirse.setBounds(342, 92, 133, 20);
		}
		return txtDNIInscribirse;
	}

	private JTextField getTxtNombreInscribirse() {
		if (txtNombreInscribirse == null) {
			txtNombreInscribirse = new JTextField();
			txtNombreInscribirse.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if (!Character.isAlphabetic(c)) {
						arg0.consume();
					}

				}
			});
			txtNombreInscribirse.setColumns(10);
			txtNombreInscribirse.setBounds(342, 151, 133, 20);
		}
		return txtNombreInscribirse;
	}

	private JTextField getTxtFechaInscribirse() {
		if (txtFechaInscribirse == null) {
			txtFechaInscribirse = new JTextField();
			txtFechaInscribirse.setColumns(10);
			txtFechaInscribirse.setBounds(342, 254, 133, 20);
		}
		return txtFechaInscribirse;
	}

	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("DNI");
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_1.setBounds(136, 92, 46, 14);
		}
		return label_1;
	}

	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("Nombre");
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_2.setBounds(136, 154, 46, 14);
		}
		return label_2;
	}

	private JLabel getLblFechaNacimientoddmmyyyy() {
		if (lblFechaNacimientoddmmyyyy == null) {
			lblFechaNacimientoddmmyyyy = new JLabel("Fecha nacimiento (dd/mm/yyyy)");
			lblFechaNacimientoddmmyyyy.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblFechaNacimientoddmmyyyy.setBounds(136, 257, 196, 14);
		}
		return lblFechaNacimientoddmmyyyy;
	}

	private JLabel getLabel_4() {
		if (label_4 == null) {
			label_4 = new JLabel("Sexo");
			label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_4.setBounds(136, 209, 106, 14);
		}
		return label_4;
	}

	private JTextField getTxtSexoInscribirse() {
		if (txtSexoInscribirse == null) {
			txtSexoInscribirse = new JTextField();
			txtSexoInscribirse.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (!Character.isAlphabetic(c)) {
						e.consume();
					}
				}
			});
			txtSexoInscribirse.setColumns(10);
			txtSexoInscribirse.setBounds(342, 206, 133, 20);
		}
		return txtSexoInscribirse;
	}

	private JButton getBtnUsuarioActual() {
		if (btnUsuarioActual == null) {
			btnUsuarioActual = new JButton("Usuario actual");
			btnUsuarioActual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					autorrellenar();
				}
			});
			btnUsuarioActual.setBounds(124, 300, 118, 23);
		}
		return btnUsuarioActual;
	}

	private JList<String> getListInscribirse() {
		if (listInscribirse == null) {
			modeloLista = new DefaultListModel<String>();
			listInscribirse = new JList<String>(modeloLista);
		}
		return listInscribirse;
	}

	private JButton getBtnRealizarInscripcion() {
		if (btnRealizarInscripcion == null) {
			btnRealizarInscripcion = new JButton("Realizar Inscripcion");
			btnRealizarInscripcion.setEnabled(false);
			btnRealizarInscripcion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inscribir();
					cambiarPanelesUsuario(2);
					txPrecioTotal.setText(precioTotal + "�");
				}

			});
			btnRealizarInscripcion.setBounds(759, 319, 159, 23);
		}
		return btnRealizarInscripcion;
	}

	private JButton getBtnSeleccionarOtroEvento() {
		if (btnSeleccionarOtroEvento == null) {
			btnSeleccionarOtroEvento = new JButton("Seleccionar Otro Evento");
			btnSeleccionarOtroEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					borrarDatos();
					cambiarPanelesUsuario(0);
					btnRealizarInscripcion.setEnabled(false);
				}
			});
			btnSeleccionarOtroEvento.setBounds(560, 319, 189, 23);
		}
		return btnSeleccionarOtroEvento;
	}

	private JButton getBtnResultadosEvento() {
		if (btnResultadosEvento == null) {
			btnResultadosEvento = new JButton("Ver resultados");
			btnResultadosEvento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DialogInformacionDeEvento diE = new DialogInformacionDeEvento(vP, eventoPulsado);
					//diE.mostrarClasificaciones();
					diE.setVisible(true);
				}
			});
			btnResultadosEvento.setEnabled(false);
		}
		return btnResultadosEvento;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(null, "Evento seleccionado: ", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(getPnInfoEvPulsado());
			panel_4.add(getBtnResultadosEvento(), BorderLayout.SOUTH);
		}
		return panel_4;
	}

	private JLabel getLblPlazosDeInscripcin() {
		if (lblPlazosDeInscripcin == null) {
			lblPlazosDeInscripcin = new JLabel("Plazos de inscripci\u00F3n");
			lblPlazosDeInscripcin.setHorizontalAlignment(SwingConstants.CENTER);
			lblPlazosDeInscripcin.setBounds(51, 214, 145, 14);
		}
		return lblPlazosDeInscripcin;
	}

	private JScrollPane getScrollPaneFechasIns() {
		if (scrollPaneFechasIns == null) {
			scrollPaneFechasIns = new JScrollPane();
			scrollPaneFechasIns.setBounds(51, 244, 149, 130);
			scrollPaneFechasIns.setViewportView(getListFechasInscrip());
		}
		return scrollPaneFechasIns;
	}

	// Datos necesarios para las fechas de inscripcion:
	DefaultListModel<String> modeloListaFechasInscripcion = new DefaultListModel<String>();
	ArrayList<PlazoInscripcion> plazosInscripcionNuevoEvento;
	private JButton btnAadir;
	private JButton btnEditar;
	private JLabel lblFechaComienzoEvento;
	private JComboBox<Integer> cbDia;
	private JComboBox<String> cbMes;
	private JComboBox<Integer> cbA�o;
	private JLabel lblDa;
	private JLabel lbMes;
	private JLabel lbA�o;

	private JList<String> getListFechasInscrip() {
		if (listFechasInscrip == null) {
			plazosInscripcionNuevoEvento = new ArrayList<PlazoInscripcion>();

			listFechasInscrip = new JList();
			listFechasInscrip.setModel(modeloListaFechasInscripcion);

		}
		return listFechasInscrip;
	}

	private JButton getBtnAadir() {
		if (btnAadir == null) {
			btnAadir = new JButton("A\u00F1adir");
			btnAadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaCreaFechaInscripcion vi = new VentanaCreaFechaInscripcion(VentanaPrincipal.this);
					vi.setVisible(true);
				}
			});
			btnAadir.setToolTipText("A\u00F1adir un nuevo plazo de inscripci\u00F3n.");
			btnAadir.setBounds(51, 385, 72, 23);
		}
		return btnAadir;
	}

	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Editar");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (plazosInscripcionNuevoEvento.isEmpty() || plazosInscripcionNuevoEvento == null
							|| getListFechasInscrip().getSelectedValue() == null)
						return;

					PlazoInscripcion miplazo = null;
					String fechains = getListFechasInscrip().getSelectedValue().toString();
					for (PlazoInscripcion plazo : plazosInscripcionNuevoEvento) {
						if (plazo.toString().equals(fechains))
							miplazo = plazo;
					}
					if (miplazo != null) {
						VentanaCreaFechaInscripcion vi = new VentanaCreaFechaInscripcion(VentanaPrincipal.this,
								miplazo);
						vi.setVisible(true);
					}

				}
			});
			btnEditar.setBounds(124, 385, 76, 23);
		}
		return btnEditar;
	}

	private JLabel getLblFechaComienzoEvento() {
		if (lblFechaComienzoEvento == null) {
			lblFechaComienzoEvento = new JLabel("Fecha comienzo evento");
			lblFechaComienzoEvento.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaComienzoEvento.setBounds(222, 219, 163, 14);
		}
		return lblFechaComienzoEvento;
	}

	private JComboBox getCbDia() {
		if (cbDia == null) {
			cbDia = new JComboBox();

			Integer[] dias = new Integer[31];
			for (int i = 0; i < dias.length; i++) {
				dias[i] = i + 1;
			}
			DefaultComboBoxModel<Integer> modelo = new DefaultComboBoxModel<Integer>(dias);
			cbDia.setModel(modelo);
			cbDia.setBounds(222, 272, 46, 20);
		}
		return cbDia;
	}

	private JComboBox getCbMes() {
		if (cbMes == null) {
			cbMes = new JComboBox();
			String[] meses = ConversorFechas.getMesestoString();
			DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<String>(meses);
			cbMes.setModel(modelo);
			cbMes.setBounds(287, 272, 63, 20);
		}
		return cbMes;
	}

	private JComboBox getCbA�o() {
		if (cbA�o == null) {
			cbA�o = new JComboBox();
			Integer[] a�os = new Integer[10];
			for (int i = 0; i < a�os.length; i++) {
				a�os[i] = 2000 + i + 16; // Que la fecha empiece este aÃ±o 2016

			}
			DefaultComboBoxModel<Integer> modelo = new DefaultComboBoxModel<Integer>(a�os);
			cbA�o.setModel(modelo);
			cbA�o.setBounds(360, 272, 56, 20);
		}
		return cbA�o;
	}

	private JLabel getLblDa() {
		if (lblDa == null) {
			lblDa = new JLabel("d\u00EDa");
			lblDa.setHorizontalAlignment(SwingConstants.CENTER);
			lblDa.setBounds(222, 246, 46, 14);
		}
		return lblDa;
	}

	private JLabel getLbMes() {
		if (lbMes == null) {
			lbMes = new JLabel("mes");
			lbMes.setHorizontalAlignment(SwingConstants.CENTER);
			lbMes.setBounds(287, 246, 63, 14);
		}
		return lbMes;
	}

	private JLabel getLbA�o() {
		if (lbA�o == null) {
			lbA�o = new JLabel("a\u00F1o");
			lbA�o.setHorizontalAlignment(SwingConstants.CENTER);
			lbA�o.setBounds(360, 246, 63, 14);
		}
		return lbA�o;
	}

	// Crear el evento
	@SuppressWarnings("deprecation")
	private Evento crearEventoOrgnizador() {
		String nombre = getTfNombreEvento().getText();
		String tipo = getTfTipoEvetno().getText();
		String distancia = getTfDistanciaEvento().getText();
		String plazas = getSpinnerPlazas().getValue().toString();
		boolean puedoCrearEvento = false;

		if (!(Comprobaciones.esString(nombre) && Comprobaciones.esString(tipo))) {
			JOptionPane.showMessageDialog(null, "El nombre del evento y el tipo han de ser texto. Nada de numeros");
			return null;
		}
		if (!(Comprobaciones.esNumero(distancia) && Comprobaciones.esNumero(plazas))) {
			JOptionPane.showMessageDialog(null, "El numero de plazas y la distancia han de ser nÃºmeros");
			return null;
		}
		ArrayList<PlazoInscripcion> plazos = plazosInscripcionNuevoEvento;
		ArrayList<Categoria> categorias = categoriasAlCrearEvento;
		if (plazos == null || plazos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se han establecido plazos de inscripciÃ³n.");
			return null;
		}
		if(!getCbCatDef().isSelected()){
		if (categorias == null || categorias.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se han establecido las categorÃ­as del evento.");
			return null;
		}
		}
		// Fecha
		int dia = Integer.parseInt(getCbDia().getSelectedItem().toString());
		String mes = getCbMes().getSelectedItem().toString();
		int a�o = Integer.parseInt(getCbA�o().getSelectedItem().toString());
		Date miFechaComienzo = null;
		try {
			miFechaComienzo = new Date(a�o - 1900, ConversorFechas.getNumeroMes(mes), dia);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"La fecha no se ha creado correctamente (Puede ser fallo en el cÃ³digo lÃ­nea 1896)");
			return null;
		}

		if (miFechaComienzo != null) {
			if (miFechaComienzo.getTime() > buscarUltimaFechaInscripcion().getTime()) {
				puedoCrearEvento = true;
			}
		}
		Evento evento = null;
		if (puedoCrearEvento) {
			if (!getCbCatDef().isSelected()) {
				evento = new Evento(nombre, tipo, Integer.parseInt(distancia), Integer.parseInt(plazas),
						miFechaComienzo, categorias, plazos);
			} else {
				evento = new Evento(nombre, tipo, Integer.parseInt(distancia), Integer.parseInt(plazas),
						miFechaComienzo, catDef, plazos);
			}
		}
		return evento;
	}

	private Date buscarUltimaFechaInscripcion() {
		Date fecha = new Date(0);
		for (PlazoInscripcion plazo : plazosInscripcionNuevoEvento) {
			if (plazo.getFechaFin().getTime() >= fecha.getTime()) {
				fecha = plazo.getFechaFin();
			}
		}
		if (fecha.getTime() == 0)
			throw new IllegalStateException("No ha encontrado una fecha mayo que 0 milis");
		return fecha;
	}

	private JPanel pnPagar;
	private JLabel lbPagar;
	private JRadioButton rdbtnTarjeta;
	private JRadioButton rdbtnTransferencia;
	private JButton btnFinalizarPago;
	private JPanel pnTarjeta;
	private JLabel lbNumeroTarjeta;
	private JTextField txNumTarjeta;
	private JLabel lbTitularTarjeta;
	private JTextField txTituTarjeta;
	private JPanel pnTransferencia;
	private JLabel lbNumeroBanco;
	private JTextField txtEs;
	private JLabel lbInfor;
	private final ButtonGroup btGroupPagos = new ButtonGroup();

	ArrayList<String> dniInscrito;
	Evento eventoSeleccionado;
	double precioEvento;
	double precioTotal;
	
	private JButton btAtrasOrganizador;
	private JPanel pnSurOrganizador;
	private JLabel lbPrecioTotal;
	private JTextField txPrecioTotal;

	private JPanel getPnPagar() {
		if (pnPagar == null) {
			pnPagar = new JPanel();
			pnPagar.setLayout(null);
			pnPagar.add(getLbPagar());
			pnPagar.add(getRdbtnTarjeta());
			pnPagar.add(getRdbtnTransferencia());
			pnPagar.add(getBtnFinalizarPago());
			pnPagar.add(getPnTarjeta());
			pnPagar.add(getPnTransferencia());
			pnPagar.add(getLbPrecioTotal());
			pnPagar.add(getTxPrecioTotal());
		}
		return pnPagar;
	}

	private JLabel getLbPagar() {
		if (lbPagar == null) {
			lbPagar = new JLabel("Seleccione el m\u00E9todo de pago:");
			lbPagar.setFont(new Font("Tahoma", Font.BOLD, 16));
			lbPagar.setBounds(10, 22, 261, 29);
		}
		return lbPagar;
	}

	private JRadioButton getRdbtnTarjeta() {
		if (rdbtnTarjeta == null) {
			rdbtnTarjeta = new JRadioButton("Tarjeta Cr\u00E9dito");
			rdbtnTarjeta.setSelected(true);
			rdbtnTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rdbtnTarjeta.setBounds(6, 101, 128, 23);
			btGroupPagos.add(rdbtnTarjeta);
		}
		return rdbtnTarjeta;
	}

	private JRadioButton getRdbtnTransferencia() {
		if (rdbtnTransferencia == null) {
			rdbtnTransferencia = new JRadioButton("Transferencia");
			rdbtnTransferencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rdbtnTransferencia.setBounds(6, 228, 109, 23);
			btGroupPagos.add(rdbtnTransferencia);
		}
		return rdbtnTransferencia;
	}

	private JButton getBtnFinalizarPago() {
		if (btnFinalizarPago == null) {
			btnFinalizarPago = new JButton("Finalizar Pago");
			btnFinalizarPago.setBounds(800, 323, 128, 23);
			btnFinalizarPago.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (rdbtnTarjeta.isSelected()) {
						if (comprobarDatosTarjeta()) {
							g.realizarPagoTarjeta(eventoSeleccionado.getId(), dniInscrito);
							cambiarPanelesUsuario(0);
						} else
							JOptionPane.showMessageDialog(null, "Datos incorrectos", "Tarjeta Credito",
									JOptionPane.ERROR_MESSAGE);
					} else {
						g.comprobarPagadosBanco(eventoSeleccionado.getId(), precioEvento);
						cambiarPanelesUsuario(0);
					}

				}
			});
		}
		return btnFinalizarPago;
	}

	public boolean comprobarDatosTarjeta()
	{
		if (getTxNumTarjeta().getText().isEmpty() || getTxTituTarjeta().getText().isEmpty())
			return false;
		return true;
	}

	private JPanel getPnTarjeta() {
		if (pnTarjeta == null) {
			pnTarjeta = new JPanel();
			pnTarjeta.setBorder(
					new TitledBorder(null, "Datos Tarjeta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnTarjeta.setBounds(163, 62, 448, 145);
			pnTarjeta.setLayout(null);
			pnTarjeta.add(getLbNumeroTarjeta());
			pnTarjeta.add(getTxNumTarjeta());
			pnTarjeta.add(getLbTitularTarjeta());
			pnTarjeta.add(getTxTituTarjeta());
		}
		return pnTarjeta;
	}

	private JLabel getLbNumeroTarjeta() {
		if (lbNumeroTarjeta == null) {
			lbNumeroTarjeta = new JLabel("N\u00FAmero:");
			lbNumeroTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbNumeroTarjeta.setBounds(10, 44, 86, 23);
		}
		return lbNumeroTarjeta;
	}

	private JTextField getTxNumTarjeta() {
		if (txNumTarjeta == null) {
			txNumTarjeta = new JTextField();
			txNumTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txNumTarjeta.setBounds(76, 44, 288, 23);
			txNumTarjeta.setColumns(10);
		}
		return txNumTarjeta;
	}

	private JLabel getLbTitularTarjeta() {
		if (lbTitularTarjeta == null) {
			lbTitularTarjeta = new JLabel("Titular:");
			lbTitularTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbTitularTarjeta.setBounds(10, 96, 56, 23);
		}
		return lbTitularTarjeta;
	}

	private JTextField getTxTituTarjeta() {
		if (txTituTarjeta == null) {
			txTituTarjeta = new JTextField();
			txTituTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txTituTarjeta.setBounds(76, 96, 288, 23);
			txTituTarjeta.setColumns(10);
		}
		return txTituTarjeta;
	}

	private JPanel getPnTransferencia() {
		if (pnTransferencia == null) {
			pnTransferencia = new JPanel();
			pnTransferencia.setBorder(
					new TitledBorder(null, "Datos del Banco", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnTransferencia.setBounds(163, 219, 448, 103);
			pnTransferencia.setLayout(null);
			pnTransferencia.add(getLbNumeroBanco());
			pnTransferencia.add(getTxtEs());
			pnTransferencia.add(getLbInfor());
		}
		return pnTransferencia;
	}

	private JLabel getLbNumeroBanco() {
		if (lbNumeroBanco == null) {
			lbNumeroBanco = new JLabel("N\u00FAmero cuenta: ");
			lbNumeroBanco.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbNumeroBanco.setBounds(10, 32, 113, 23);
		}
		return lbNumeroBanco;
	}

	private JTextField getTxtEs() {
		if (txtEs == null) {
			txtEs = new JTextField();
			txtEs.setText("ES12 3456 7891 00 5555555555");
			txtEs.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtEs.setEditable(false);
			txtEs.setBounds(133, 32, 230, 23);
			txtEs.setColumns(10);
		}
		return txtEs;
	}

	private JLabel getLbInfor() {
		if (lbInfor == null) {
			lbInfor = new JLabel("Dispondr\u00E1 del plazo hasta antes del inicio de la competici\u00F3n.");
			lbInfor.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbInfor.setBounds(10, 66, 376, 26);
		}
		return lbInfor;
	}

	// ACTUALIZAR LISTAS DE LA VENTANA ORGANIZADOR
	public void a�adirAlModeloListFechaInscripcionOr(String pl) {
		// getListFechasInscrip().setModel(null);
		modeloListaFechasInscripcion.addElement(pl.toString());
		getListFechasInscrip().setModel(modeloListaFechasInscripcion);
	}

	public void actualizarModeloListaInscripciones() {
		// getListFechasInscrip().setModel(null);
		modeloListaFechasInscripcion.removeAllElements();
		for (PlazoInscripcion plazo : plazosInscripcionNuevoEvento) {
			modeloListaFechasInscripcion.addElement(plazo.toString());
		}
		getListFechasInscrip().setModel(modeloListaFechasInscripcion);
		getScrollPaneFechasIns().add(getListFechasInscrip()); // Igual esta
																// linea sobra
	}

	public void a�adirPlazo(PlazoInscripcion plazo) {
		this.plazosInscripcionNuevoEvento.add(plazo);
		a�adirAlModeloListFechaInscripcionOr(plazo.toString());
	}

	public void borrarPlazo(PlazoInscripcion plazo) {
		this.plazosInscripcionNuevoEvento.remove(plazo);
	}

	public void actualizarCategorias() {
		// getListCategoriasOr().setModel(null);
		modeloListaCategorias.removeAllElements();
		for (Categoria c : categoriasAlCrearEvento) {
			modeloListaCategorias.addElement(c.toString());
		}
		getListCategoriaOr().setModel(modeloListaCategorias);
		getScrollPaneCategorias().add(getListCategoriaOr());
	}

	public void a�adirAlModeloListaCategoriasOr(Categoria elem) {
		// getListCategoriasOr().setModel(null);
		modeloListaCategorias.addElement(elem.toString());
		getListCategoriaOr().setModel(modeloListaCategorias);
	}
	private JLabel getLbPrecioTotal() {
		if (lbPrecioTotal == null) {
			lbPrecioTotal = new JLabel("Precio Total:");
			lbPrecioTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lbPrecioTotal.setBounds(800, 62, 109, 29);
		}
		return lbPrecioTotal;
	}
	private JTextField getTxPrecioTotal() {
		if (txPrecioTotal == null) {
			txPrecioTotal = new JTextField();
			txPrecioTotal.setHorizontalAlignment(SwingConstants.CENTER);
			txPrecioTotal.setEditable(false);
			txPrecioTotal.setBounds(800, 104, 109, 37);
			txPrecioTotal.setColumns(10);
		}
		return txPrecioTotal;
	}
}

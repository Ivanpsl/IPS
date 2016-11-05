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
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.SystemColor;

import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import logica.Gestor;
import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.PlazoInscripcion;
import utiles.Comprobaciones;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class VentanaPrincipal extends JFrame {

	private ModeloNoEditable modeloTabla;
	private ArrayList<Evento> contenidoEventos; // ArrayList que se mostrara en
												// la tabla
	private String[] cabeceraTablaSeleccionEventos = { "Nombre", "Tipo",
			"Distancia", "Estado Carrera", "Plazo", "Precio" };
	// private ArrayList<Evento> eventosTabla; //lo usaremos en un futuro para
	// realizad filtrados
	private Evento eventoPulsado;

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
		vP = this;
		g = new Gestor();
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
	 * Metodo usado para navegar por las pesta�as principales
	 * 
	 * @param opcion
	 *            : inicio: pantalla de seleccion de modo organizador: menus de
	 *            organizadores usuario: menus de usuarios
	 */
	private void cambiarPanelesPrincipales(String opcion) {
		if (opcion.equals("organizador"))
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal,
					"pn_Organizador");
		if (opcion.equals("usuario")){
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal,
					"pn_usuario");
			mostrarTablaEventosUsuario();
		}
		if (opcion.equals("inicio"))
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal,
					"pn_inicio");
	}

	/**
	 * Metodo usado para cambiar la cabecera de los menus de usuario
	 * 
	 * @param opcion
	 *            1=cabecera con datos 2=cabecera sin datos
	 */
	private void cambiarCabeceraUsuario(int opcion) {
		if (opcion == 0)
			((CardLayout) pnCabecera.getLayout()).show(pnCabecera,
					"usuario_sinIdentificar");
		if (opcion == 1)
			((CardLayout) pnCabecera.getLayout()).show(pnCabecera,
					"usuario_Identificado");
	}
	/**
	 * Metodo usado para navegar por las distintas ventanas que vera el usuario
	 * @param opcion: 
	 * 					eventos: panel con la tabla de eventos y donde el usuario selecciona a cual inscribirse 
	 */
	private void cambiarPanelesUsuario(String opcion) {
		if (opcion.equals("eventos"))
			reiniciarDatosPulsados();
			mostrarTablaEventosUsuario();
			((CardLayout) pnPrincipal.getLayout()).show(pnPrincipal,
					"pn_eventosUsuario");
	
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
			fila[3] = String.valueOf(ev.getDistancia());
			if (!ev.getFinalizado()) {
				fila[4] = "Inscripcion";
				fila[5] = "ABIERTO";
				fila[6] = String.valueOf(ev.getUltimoPlazo().getPrecio());
			} else {
				if (ev.getClasificacion() != null) {
					fila[4] = "Finalizada";
				} else
					fila[4] = "Corriendo";
				fila[5] = "CERRADO";
				fila[6] = "CERRADO";
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
		eventoPulsado=null;
		txEventoPulsadoNombre.setText("");
		txEventoPulsadoPlazas.setText("");
		textAreaCategoriasAdmitidas.setText("");
		textAreaPlazosInscripcionEventoUsuario.setText("");
	}

	/**
	 * 
	 * @param ev: evento pulsado
	 * @param desc: identifica que descripcion es, la de eventos de usuario (1) , o la de eventos de organizador (2)
	 */
	private void pulsarEvento(Evento ev,int desc) {
		eventoPulsado=ev;
		if(desc==1){ //desc= 1 nos situa en la descripcion del panel de usuarios
			txEventoPulsadoNombre.setText(ev.getNombre());
			txEventoPulsadoPlazas.setText(String.valueOf(ev.getPlazasDisponibles()));
			ArrayList<Categoria>cat = ev.getCategorias();
			//ArrayList<Categoria> catEnListado;
			StringBuilder sB = new StringBuilder();
			for(Categoria c: cat){
				String sexo;
				if(c.getSexo()==0) sexo="Masculino";
				else sexo="Femenino";
				sB.append(c.getNombre() + " De "+c.getEdadMinima() + " a " + c.getEdadMaxima() + " Sexo: " + sexo + "\n");
			}
			textAreaCategoriasAdmitidas.setText(sB.toString());
			ArrayList<PlazoInscripcion> plazos = ev.getPlazos();
			sB = new StringBuilder();
			for(PlazoInscripcion p: plazos){
				sB.append("De: "+ p.getFechaInicio() +" a: " + p.getFechaFin() + " Precio: " + p.getPrecio() + "� \n");
			}textAreaPlazosInscripcionEventoUsuario.setText(sB.toString());
		}
	}

	private void cargarCabeceraAtleta() {
		Atleta at = g.getAtletaIdentificado();
		txtNombreAtleta.setText(at.getNombre());
		txtFechaNacimiento.setText(at.getFechaNacimiento());
		pnAtletaResumen.setBorder(new TitledBorder(null, at.getDNI(),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

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
			pnOrganizador.add(getPnEventosOrganizador(),
					"pn_EventosOrganizador");
			pnOrganizador.add(getPnCrearEvento(), "pn_CrearEvento");
		}
		return pnOrganizador;
	}

	private void cambiarPanelesOrganizador(String opcion) {
		switch (opcion) {
		case "crear":
			((CardLayout) pnOrganizador.getLayout()).show(pnOrganizador,
					"pn_CrearEvento");
			break;
		case "misEventos":
			((CardLayout) pnOrganizador.getLayout()).show(pnOrganizador,
					"pnEventosOrganizador");
			break;

		default:
			break;
		}
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
			pnCabecera.setBorder(new TitledBorder(null, "Datos del atleta: ",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			pnSelecEventosUsuario.add(getPnInfoEvPulsado(), BorderLayout.EAST);
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
			lblParaPoderVer = new JLabel(
					"Para poder ver sus datos y resultados ha de identificarse:");
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
					String dni = JOptionPane.showInputDialog(pnPrincipal,
							"Introduzca su DNI: ", "Identificacion",
							JOptionPane.QUESTION_MESSAGE);
					if (dni != null) {
						if (g.identificarAtletaPorDNI(dni)) {
							cargarCabeceraAtleta();
							cambiarCabeceraUsuario(1);
						} else
							JOptionPane.showInternalMessageDialog(pnPrincipal,
									"Atleta no identificado",
									"Error: Atleta no registrado",
									JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return btnIdentificarse;
	}

	private JPanel getPnTabla() {
		if (pnTabla == null) {
			pnTabla = new JPanel();
			pnTabla.setBorder(new TitledBorder(null, "Tabla",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			tbEventosSeleccion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int fila = tbEventosSeleccion.getSelectedRow();
					pulsarEvento(contenidoEventos.get(fila),1);
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
			pnOpciones.add(getBtnSiguiente(), BorderLayout.EAST);
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
			txtFechaNacimiento.setColumns(10);
		}
		return txtFechaNacimiento;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.add(getPnNombre());
			panel_7.add(getPnFechaNacimiento());
		}
		return panel_7;
	}

	private JButton getBtnMostrarResultadosAtleta() {
		if (btnMostrarResultadosAtleta == null) {
			btnMostrarResultadosAtleta = new JButton("Mostrar resultados");
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
			pnEventosOrganizador.add(getPnCabezeraOrganizador(),
					BorderLayout.NORTH);
			pnEventosOrganizador.add(getPnCentroOrganizador(),
					BorderLayout.CENTER);
		}
		return pnEventosOrganizador;
	}

	private JPanel getPnCabezeraOrganizador() {
		if (pnCabezeraOrganizador == null) {
			pnCabezeraOrganizador = new JPanel();
			pnCabezeraOrganizador.setLayout(new BorderLayout(0, 0));
			pnCabezeraOrganizador.add(getPnInfoOrganizador(),
					BorderLayout.CENTER);
			pnCabezeraOrganizador.add(getPnAccionesOrg(), BorderLayout.EAST);
		}
		return pnCabezeraOrganizador;
	}

	private JPanel getPnCentroOrganizador() {
		if (pnCentroOrganizador == null) {
			pnCentroOrganizador = new JPanel();
			pnCentroOrganizador.setLayout(new BorderLayout(0, 0));
			pnCentroOrganizador.add(getPnTablaOrganizador(),
					BorderLayout.CENTER);
		}
		return pnCentroOrganizador;
	}

	private JPanel getPnTablaOrganizador() {
		if (pnTablaOrganizador == null) {
			pnTablaOrganizador = new JPanel();
			pnTablaOrganizador.setLayout(new BorderLayout(0, 0));
			pnTablaOrganizador.add(getPnScrollOrganizador(),
					BorderLayout.CENTER);
			pnTablaOrganizador.add(getPnDescripcionEventoOrganizador(),
					BorderLayout.EAST);
			pnTablaOrganizador.setBorder(new TitledBorder(null, "Mis eventos",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
		}
		return pnTablaOrganizador;
	}

	private JScrollPane getPnScrollOrganizador() {
		if (pnScrollOrganizador == null) {
			pnScrollOrganizador = new JScrollPane();
			pnScrollOrganizador
					.setViewportView(getTablaEventosDelOrganizador());
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
			pnDescripcionEventoOrganizador
					.setLayout(new GridLayout(0, 1, 0, 0));
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
			pnCrearEvento.add(getPnContenidoCreacionEvento(),
					BorderLayout.CENTER);
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
		}
		return btCancelarEvento;
	}

	private JButton getBtCrearEvento() {
		if (btCrearEvento == null) {
			btCrearEvento = new JButton("Crear evento");
			btCrearEvento.setEnabled(false);
		}
		return btCrearEvento;
	}

	private JPanel getPnContenidoCreacionEvento() {
		if (pnContenidoCreacionEvento == null) {
			pnContenidoCreacionEvento = new JPanel();
			GroupLayout grupoLayout = new GroupLayout(pnContenidoCreacionEvento);
			grupoLayout
					.setHorizontalGroup(grupoLayout
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									grupoLayout
											.createSequentialGroup()
											.addGap(60)
											.addGroup(
													grupoLayout
															.createParallelGroup(
																	Alignment.LEADING)
															.addGroup(
																	grupoLayout
																			.createSequentialGroup()
																			.addComponent(
																					getLblNombre(),
																					GroupLayout.PREFERRED_SIZE,
																					76,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(
																					ComponentPlacement.RELATED)
																			.addComponent(
																					getTfNombreEvento(),
																					GroupLayout.PREFERRED_SIZE,
																					106,
																					GroupLayout.PREFERRED_SIZE))
															.addGroup(
																	grupoLayout
																			.createSequentialGroup()
																			.addGap(10)
																			.addComponent(
																					getLblTipo(),
																					GroupLayout.PREFERRED_SIZE,
																					48,
																					GroupLayout.PREFERRED_SIZE)
																			.addGap(18)
																			.addComponent(
																					getCbTipoEventos(),
																					0,
																					118,
																					Short.MAX_VALUE))
															.addGroup(
																	grupoLayout
																			.createSequentialGroup()
																			.addPreferredGap(
																					ComponentPlacement.RELATED)
																			.addComponent(
																					getLblDistancia(),
																					GroupLayout.PREFERRED_SIZE,
																					76,
																					GroupLayout.PREFERRED_SIZE)
																			.addPreferredGap(
																					ComponentPlacement.RELATED)
																			.addComponent(
																					getTfDistanciaEvento(),
																					GroupLayout.PREFERRED_SIZE,
																					50,
																					GroupLayout.PREFERRED_SIZE)
																			.addGap(18)
																			.addComponent(
																					getLbKm()))
															.addGroup(
																	grupoLayout
																			.createSequentialGroup()
																			.addGap(85)
																			.addComponent(
																					getSpinnerPlazas(),
																					GroupLayout.PREFERRED_SIZE,
																					38,
																					GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(
													ComponentPlacement.UNRELATED)
											.addComponent(getTfTipoEvetno(),
													GroupLayout.PREFERRED_SIZE,
													GroupLayout.DEFAULT_SIZE,
													GroupLayout.PREFERRED_SIZE)
											.addGap(644))
							.addGroup(
									grupoLayout
											.createSequentialGroup()
											.addGap(24)
											.addComponent(
													getLblNmeroDePlazas(),
													GroupLayout.PREFERRED_SIZE,
													101,
													GroupLayout.PREFERRED_SIZE)
											.addContainerGap(869,
													Short.MAX_VALUE)));
			grupoLayout
					.setVerticalGroup(grupoLayout
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									grupoLayout
											.createSequentialGroup()
											.addGap(38)
											.addGroup(
													grupoLayout
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(
																	getLblNombre(),
																	GroupLayout.PREFERRED_SIZE,
																	33,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	getTfNombreEvento(),
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(
													ComponentPlacement.UNRELATED)
											.addGroup(
													grupoLayout
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(
																	getCbTipoEventos(),
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	getLblTipo(),
																	GroupLayout.PREFERRED_SIZE,
																	22,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	getTfTipoEvetno(),
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(
													ComponentPlacement.UNRELATED)
											.addGroup(
													grupoLayout
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(
																	getLbKm())
															.addComponent(
																	getLblDistancia(),
																	GroupLayout.PREFERRED_SIZE,
																	33,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	getTfDistanciaEvento(),
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(
													ComponentPlacement.UNRELATED)
											.addGroup(
													grupoLayout
															.createParallelGroup(
																	Alignment.BASELINE)
															.addComponent(
																	getLblNmeroDePlazas(),
																	GroupLayout.PREFERRED_SIZE,
																	33,
																	GroupLayout.PREFERRED_SIZE)
															.addComponent(
																	getSpinnerPlazas(),
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
											.addContainerGap(237,
													Short.MAX_VALUE)));
			pnContenidoCreacionEvento.setLayout(grupoLayout);
		}
		return pnContenidoCreacionEvento;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNombre;
	}

	private JTextField getTfNombreEvento() {
		if (tfNombreEvento == null) {
			tfNombreEvento = new JTextField();
			tfNombreEvento.setColumns(10);
		}
		return tfNombreEvento;
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo: ");
			lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
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
																		// a�adir
																		// uno
																		// propio.
			cbTipoEventos = new JComboBox();

		}
		return cbTipoEventos;
	}

	private JLabel getLblDistancia() {
		if (lblDistancia == null) {
			lblDistancia = new JLabel("Distancia:");
			lblDistancia.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDistancia;
	}

	private JTextField getTfDistanciaEvento() {
		if (tfDistanciaEvento == null) {
			tfDistanciaEvento = new JTextField();
			tfDistanciaEvento.setColumns(10);
		}
		return tfDistanciaEvento;
	}

	private JLabel getLblNmeroDePlazas() {
		if (lblNmeroDePlazas == null) {
			lblNmeroDePlazas = new JLabel("N\u00FAmero de plazas:");
			lblNmeroDePlazas.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNmeroDePlazas;
	}

	private JLabel getLbKm() {
		if (lbKm == null) {
			lbKm = new JLabel("Km");
		}
		return lbKm;
	}

	int numeroPlazasEvento = 0;
	private JTextField tfTipoEvetno;
	private JPanel pnInfoEvPulsado;
	private JButton btnSiguiente;
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

	private JSpinner getSpinnerPlazas() {
		if (spinnerPlazas == null) {
			spinnerPlazas = new JSpinner();
			SpinnerModel modelo = new SpinnerNumberModel(numeroPlazasEvento, 0,
					9999, 1);
			spinnerPlazas.setModel(modelo);
		}
		return spinnerPlazas;
	}

	private JTextField getTfTipoEvetno() {
		if (tfTipoEvetno == null) {
			tfTipoEvetno = new JTextField();
			tfTipoEvetno.setHorizontalAlignment(SwingConstants.CENTER);
			tfTipoEvetno.setColumns(10);
			if (getCbTipoEventos().getSelectedItem() == " ") {
				tfTipoEvetno.setEnabled(true);
			} else {
				tfTipoEvetno.setEnabled(false);
			}
		}
		return tfTipoEvetno;
	}

	// ----------------------------------- M�todos para crear un evento

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
			throw new IllegalArgumentException("La distancia no es un n�mero");
		}
		Date fechaComienzo = new Date(Calendar.getInstance().getTimeInMillis());
		Date fechaFinInscripciones = new Date(Calendar.getInstance()
				.getTimeInMillis());
		ArrayList<Categoria> categoriasParaEvento = new ArrayList<Categoria>();
		int plazas = (int) getSpinnerPlazas().getValue();
		// Obtener las categorias del elemento en cuestion
		// Plazos de inscripcion
		ArrayList<PlazoInscripcion> plazosInscripcion = new ArrayList<PlazoInscripcion>();

		// Si todo esta OK
		g.crearEvento(nombre, tipo, distancia, fechaComienzo,
				fechaFinInscripciones, plazas, categoriasParaEvento,
				plazosInscripcion);
	}

	// ---------------------------------- FIN metodos crear un evento
	private JPanel getPnInfoEvPulsado() {
		if (pnInfoEvPulsado == null) {
			pnInfoEvPulsado = new JPanel();
			pnInfoEvPulsado.setBorder(new TitledBorder(null, "Evento seleccionado: ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnInfoEvPulsado.setLayout(new GridLayout(2, 1, 0, 0));
			pnInfoEvPulsado.add(getPnInfoEvPulsadoPrincipal());
			pnInfoEvPulsado.add(getPanel_4_2());
		}
		return pnInfoEvPulsado;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton("Inscribirse");
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					g.confirmarSeleccion(eventoPulsado);
					
				}
			});
		}
		return btnSiguiente;
	}
	private JButton getBtnAtras() {
		if (btnAtras == null) {
			btnAtras = new JButton("Atras");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reiniciarDatosPulsados();
				}
			});
		}
		return btnAtras;
	}
	private JPanel getPanel_4_2() {
		if (pnInfoEvPulsadoPlazos == null) {
			pnInfoEvPulsadoPlazos = new JPanel();
			pnInfoEvPulsadoPlazos.setBorder(new TitledBorder(null, "Plazos de inscripcion: ", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
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
			pnEventoPulsadoCategoriasAdmitidas.setBorder(new TitledBorder(null, "Categorias admitidas: ", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
			pnEventoPulsadoCategoriasAdmitidas.setLayout(new BorderLayout(0, 0));
			pnEventoPulsadoCategoriasAdmitidas.add(getTextAreaCategoriasAdmitidas());
		}
		return pnEventoPulsadoCategoriasAdmitidas;
	}
	private JTextArea getTextAreaPlazosInscripcionEventoUsuario() {
		if (textAreaPlazosInscripcionEventoUsuario == null) {
			textAreaPlazosInscripcionEventoUsuario = new JTextArea();
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
}

package igu;

import igu.dialog.DialogInformacionDeEvento;
import igu.dialog.DialogResultadosAtleta;
import igu.paneles.panelFiltros;

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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import logica.Gestor;
import logica.GestorCategorias;
import logica.GestorFechasInscripcion;
import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.PlazoInscripcion;
import persistencia.GestorFicheros;
import usuarios.Organizador;
import utiles.Comprobaciones;
import utiles.ConversorFechas;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

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
	
	private panelFiltros pF;
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
	private DefaultListModel<String> modeloListaInscribirse;

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
	private JButton btAñadirEventoOr;
	private JButton btEditarEventoOr;
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

	JFileChooser selector;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setLocationRelativeTo(null);
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
		organizador = new Organizador("PACO", "XXX", "PACO ORGANIZER", 0);
		g.asignarEventosAOrganizador(organizador);
		pF=new panelFiltros(vP,g);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1134, 581);
		setJMenuBar(getMenuBar_1());
		pnPrincipal = new JPanel();
		pnPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnPrincipal);
		pnPrincipal.setLayout(new CardLayout(0, 0));
		pnPrincipal.add(getPnInicio(), "pn_inicio");
		pnPrincipal.add(getPnOrganizador(), "pn_Organizador");
		pnPrincipal.add(getPnUsuario(), "pn_usuario");
	}
	private void recargarAplicacion(){
		reiniciarDatosPulsados();
		cambiarCabeceraUsuario(0);
		borrarDatos();
		cambiarPanelesPrincipales("inicio");
		g= new Gestor();
		g.asignarEventosAOrganizador(organizador);
		pF=new panelFiltros(vP,g);
		mostrarTablaEventosOrganizador();
		//pF.rellenarComboBox();
		
		
	}

	private boolean comprobarDatosInscribirse() {
		if (!rdbtnFemenino.isSelected() && !rdbtnMasculino.isSelected()) {
			return false;
		}
		if (txtDNIInscribirse.getText().equals("") || !g.comprobarFecha(txtFechaInscribirse.getText())
				|| txtNombreInscribirse.getText().equals("")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo publico usado desde el panel de filtros para reiniciar la tabla 
	 * y aplicar los cambios del filtrado.
	 */
	public void actualizarTablaFiltrada(){
		mostrarTablaEventosUsuario();
	}

	private void AnadirInscritoALista() {

		if (comprobarDatosInscribirse()) {

			int sexo = 0;

			if (rdbtnMasculino.isSelected()) {
				sexo = 0;
			}
			if (rdbtnFemenino.isSelected()) {
				sexo = 1;
			}

			String dni = txtDNIInscribirse.getText();

			if (!modeloListaInscribirse.contains(dni)) {
				Atleta at = g.buscarAtletaPorDNI(dni);
				if (at == null) {
					at = new Atleta(dni, txtNombreInscribirse.getText(), txtFechaInscribirse.getText(), sexo);
					atletasARegistrar.add(at);
				}
				if (!g.existeAtletaEnEvento(g.getEventoSeleccionado().getId(), dni)) {
					atletasAInscribir.add(at);
					modeloListaInscribirse.addElement(dni);
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

		} else {
			JOptionPane.showMessageDialog(null, "Rellene bien todos los campos");

		}
		if (modeloListaInscribirse.getSize() > 0) {
			btnRealizarInscripcion.setEnabled(true);
			btnEliminarInscribirse.setEnabled(true);
		}

	}

	private void inscribir() {
		System.out.println("REALIZANDO INSCRIPCION \n");
		g.registrarLoteAtletas(atletasARegistrar);
		g.inscribirLote(atletasAInscribir);
		modeloListaInscribirse.removeAllElements();
		listInscribirse.setModel(modeloListaInscribirse);
		
		dniInscrito = new ArrayList<String>();
		
		for (Atleta a : atletasARegistrar)
		{
			dniInscrito.add(a.getDNI());
		}

		borrarDatos();
		System.out.println("INSCRIPCION FINALIZADA \n");

	}

	private void borrarDatos() {
		txtDNIInscribirse.setText("");
		txtNombreInscribirse.setText("");
		txtFechaInscribirse.setText("");
		rdbtnFemenino.setSelected(false);
		rdbtnMasculino.setSelected(false);
		modeloListaInscribirse.clear();
		listInscribirse.setModel(modeloListaInscribirse);
		
		atletasARegistrar = new ArrayList<Atleta>();
		atletasAInscribir = new ArrayList<Atleta>();
		btnEliminarInscribirse.setEnabled(false);
	}

	private void autorrellenar() {
		String dni = txtDNIAtleta.getText();
		if (!dni.equals("")) {
			txtDNIInscribirse.setText(dni);
			txtNombreInscribirse.setText(txtNombreAtleta.getText());
			txtFechaInscribirse.setText(txtFechaNacimiento.getText());
			rdbtnMasculino.setSelected(g.buscarAtletaPorDNI(dni).getSexo() == 0 ? true : false);
			rdbtnFemenino.setSelected(g.buscarAtletaPorDNI(dni).getSexo() == 0 ? false : true);
		}
	}

	private void cargarFichero() {
		int respuesta = getSelector().showOpenDialog(null);
		if (respuesta == JFileChooser.APPROVE_OPTION) {

			List<Atleta> atletasFichero = g.leerFicheroInscribirAtletas(selector.getSelectedFile());

			String dni = "";
			for (int i = 0; i < atletasFichero.size(); i++) {
				dni = atletasFichero.get(i).getDNI();
				if (!modeloListaInscribirse.contains(dni)) {

					Atleta at = g.buscarAtletaPorDNI(dni);
					if (at == null) {
						atletasARegistrar.add(atletasFichero.get(i));
					}
					if (!g.existeAtletaEnEvento(g.getEventoSeleccionado().getId(), dni)) {
						atletasAInscribir.add(at);
						modeloListaInscribirse.addElement(dni);
						precioTotal = precioTotal + precioEvento;
					} else {
						JOptionPane.showMessageDialog(null, "Dni ya inscrito en el evento");
					}
				} else {
					JOptionPane.showMessageDialog(null, "El dni ya esta en la lista");
				}
			} // fin for

			if (modeloListaInscribirse.getSize() > 0) {
				btnRealizarInscripcion.setEnabled(true);
				btnEliminarInscribirse.setEnabled(true);
			}

		}

	}

	private void eliminarListaInscribirse() {
		if (modeloListaInscribirse.getSize() > 0) {
			int pos = listInscribirse.getSelectedIndex();
			if (pos != -1) {
				String dni = modeloListaInscribirse.getElementAt(pos);
				for (int i = 0; i < atletasAInscribir.size(); i++) {
					if (atletasAInscribir.get(i).DNI.equals(dni)) {
						atletasAInscribir.remove(i);
					}
				}
				for (int i = 0; i < atletasARegistrar.size(); i++) {
					if (atletasARegistrar.get(i).DNI.equals(dni)) {
						atletasARegistrar.remove(i);
					}
				}
				modeloListaInscribirse.remove(pos);
				listInscribirse.setModel(modeloListaInscribirse);

			}
		}
		if (modeloListaInscribirse.getSize() == 0) {
			btnRealizarInscripcion.setEnabled(false);
			btnEliminarInscribirse.setEnabled(false);
		}

	}

	public JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(false);
			// Para filtrar los archivos que queremos seleccionar
			selector.setFileFilter(new FileNameExtensionFilter("Archivos texto", "txt"));

			// String directorio= System.getProperty("user.dir");
			// fija en el directorio de ejecucion del programa
			// selector.setCurrentDirectory(new File(directorio));

			// Fijar donde quiero que busque los txt
			String directorio = System.getProperty("user.home");
			// String directorio=
			// System.getProperty("Users.home")+"/Desktop/Musica";
			selector.setCurrentDirectory(new File(directorio));

		}
		return selector;
	}

	public void setSelector(JFileChooser selector) {
		this.selector = selector;
	}
	
	private void editarCategoria() {
		if(!modeloCategoriasDefecto.isEmpty()){
			if (!getCbCatDef().isSelected()) {

				VentanaEditarCategorias vec = new VentanaEditarCategorias(this, categoriasAlCrearEvento);
				vec.setVisible(true);
			} else {

				VentanaEditarCategorias vec = new VentanaEditarCategorias(this, catDef);
				vec.setVisible(true);
			}

		}else{
			JOptionPane.showMessageDialog(null, "No hay categorias");
		}
		
	}
	
	public void editarCategoriasAlCrearEvento(ArrayList<Categoria> cat){
		this.categoriasAlCrearEvento=cat;
		cbCatDef.setSelected(false);
		cargarMisCategoriasAlModelo();
	}
	
	public String comprobarCategorias(ArrayList<Categoria> cat) {
		return GestorCategorias.comprobarCategorias(cat);
		
	}
	
	private void cargarMisCategoriasAlModelo() {
		modeloListaCategorias.clear();
		getListCategoriaOr().setModel(modeloListaCategorias);
		
		for (Categoria c : categoriasAlCrearEvento) {
			modeloListaCategorias.addElement(c.toString());
		}
		getListCategoriaOr().setModel(modeloListaCategorias);
	}

	/**
	 * Metodo usado para navegar por las pestaÃƒÂ±as principales
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
			pF.rellenarComboBox();
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
	ModeloNoEditable modeloTablaOrg;
	/**
	 * Para cargar la tabla de los eventos que ha creado el organizador.
	 */
	private void mostrarTablaEventosOrganizador() {
		 modeloTablaOrg = new ModeloNoEditable(cabeceraTablaSeleccionEventos, 0);
		// ArrayList<Evento> eventosOrganizador = g.getEventos(); //
		// g.getEventosOrganizador(organizador);
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
		contenidoEventos =g.filtrar(g.getEventos(),pF.getFDistancia()  , pF.getMaxDistancia(), pF.getMinDistancia(), 
				pF.getFTipo(), pF.getFTerminados(), pF.getFPlazoCerrado(), pF.getFPlazasLlenas());
		for (Evento ev : contenidoEventos) {
			String[] fila = new String[6];
			fila[0] = ev.getNombre();
			fila[1] = ev.getTipo();
			fila[2] = String.valueOf(ev.getDistancia());
			if (!ev.getFinalizado()) {

				if (ev.getUltimoPlazo() != null) {
					fila[3] = "Inscripcion";
					if(ev.getPlazasDisponibles()==0)
						fila[4] = "LLENO";
					else
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
							+ "€ \n");
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
			pnSelecEventosUsuario.add(getPnEventoSeleccionado(), BorderLayout.EAST);
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
									btnUsuarioActual.setEnabled(true);
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
			pnTabla.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Eventos disponibles: ", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
			pnTabla.setLayout(new BorderLayout(0, 0));
			pnTabla.add(getScrollPane());
			pnTabla.add(getPanel_4_5(), BorderLayout.NORTH);
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
					if (!eventoSeleccionado.getFinalizado() && eventoSeleccionado.getUltimoPlazo()!=null)
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
			eventoPulsado = null;
			pnScrollOrganizador = new JScrollPane();
//			pnScrollOrganizador.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent arg0) {
//					int fila = tablaEventosDelOrganizador.getSelectedRow();
//					if(organizador.getMisEventos().size()>0){
//						eventoPulsado = organizador.getMisEventos().get(fila);
//						if(eventoPulsado.comprobarFinalizado())
//							getBtEditarEventoOr().setEnabled(true);
//					}
//				}
//			});
			pnScrollOrganizador.setViewportView(getTablaEventosDelOrganizador());
		}
		return pnScrollOrganizador;
	}

	private JTable getTablaEventosDelOrganizador() {
		if (tablaEventosDelOrganizador == null) {
			tablaEventosDelOrganizador = new JTable();
			tablaEventosDelOrganizador.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					int fila = tablaEventosDelOrganizador.getSelectedRow();
					if(organizador.getMisEventos().size()>0){
						eventoPulsado = organizador.getMisEventos().get(fila);
						if(eventoPulsado.comprobarFinalizado())
							getBtEditarEventoOr().setEnabled(true);
					}
				}
			});
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
		}
		return pnInfoOrganizador;
	}

	private JPanel getPnAccionesOrg() {
		if (pnAccionesOrg == null) {
			pnAccionesOrg = new JPanel();
			pnAccionesOrg.setLayout(new GridLayout(2, 1, 0, 0));
			pnAccionesOrg.add(getBtAñadirEventoOr());
			pnAccionesOrg.add(getBtEditarEventoOr());
		}
		return pnAccionesOrg;
	}

	private JButton getBtAñadirEventoOr() {
		if (btAñadirEventoOr == null) {
			btAñadirEventoOr = new JButton("A\u00F1adir evento");
			btAñadirEventoOr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cambiarPanelesOrganizador("crear");
				}
			});
			btAñadirEventoOr.setToolTipText("Crear un nuevo evento");
		}
		return btAñadirEventoOr;
	}

	private JButton getBtEditarEventoOr() {
		if (btEditarEventoOr == null) {
			
			btEditarEventoOr = new JButton("A\u00F1adir resultados");
			btEditarEventoOr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
//					// Obtener el evento
//					int row = tablaEventosDelOrganizador.getSelectedRow();
//					int col = tablaEventosDelOrganizador.getSelectedColumn();
//					
//					String data;
//					try{
//						data = tablaEventosDelOrganizador.getValueAt(row, col).toString();
//					}catch(ArrayIndexOutOfBoundsException nullE){
//						JOptionPane.showMessageDialog(null, "No se ha seleccionado nada en la tabla", "Error",JOptionPane.ERROR_MESSAGE);
//						return;
//					}
//					for (Evento ev : organizador.getMisEventos()) {
//						if (ev.getNombre().equals(data)) {
//							e = ev;
//							break;
//						}
//					}
//					if(!e.comprobarFinalizado()){
//							JOptionPane.showMessageDialog(null, "El evento aún no ha finalizado", "Error", JOptionPane.ERROR_MESSAGE);
//					}
					//
					if(eventoPulsado == null || !organizador.getMisEventos().contains(eventoPulsado)){
						JOptionPane.showMessageDialog(null, "Vuelve a seleccionar el evento.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(eventoPulsado.comprobarFinalizado()){
						JOptionPane.showMessageDialog(null, "El evento aún no ha finalizado, no se pueden cargar resultados", "Evento no finalizado!", JOptionPane.ERROR_MESSAGE);
					}
					boolean salioBien = false;

					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat File",new String[]{"dat"}); //Aqui le podemos decir que mas tipos de archivos admite. 
					chooser.setFileFilter(filter);
					chooser.addChoosableFileFilter(filter);
					int returnVal = chooser.showOpenDialog(VentanaPrincipal.this);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
						FileReader f = null;
						String name;
						try {
							f = new FileReader(chooser.getSelectedFile().getAbsolutePath());
						} catch (FileNotFoundException e1) {

							boolean loMeteMal = true; // Por si el usuario es
														// tontito

							while (loMeteMal) {
								loMeteMal = false;
								System.err.println("Ha cascado al obtener la ruta absoluta del fichero");
								int op = JOptionPane.showConfirmDialog(null,
										"No se ha podido cargar la ruta abosoluta del fichero. \nSi lo desea, puede copiar el fichero en la carpeta ficheros del proyecto y escribir su nombre en la siguiente ventana (sin extensión",
										"Error de ruta, ¿solucionar?", JOptionPane.YES_NO_OPTION);
								// e1.printStackTrace();
								if (op == JOptionPane.YES_OPTION) {
									name = JOptionPane
											.showInputDialog("Introduzca el nombre del fichero sin extension");
									if (name.contains(".")) { // Por si el
																// usuario es
																// tonto y no ha
																// hecho caso
										int pos = name.indexOf(".");
										String nName = "";
										for (int i = 0; i < pos; i++) {
											nName += name.charAt(i);
										}
										name = nName;
										name += ".dat";
									}
									try {
										f = new FileReader(name);
									} catch (FileNotFoundException e2) {
										System.out.println("Meter el fichero a mano también ha cascado.");
										JOptionPane.showMessageDialog(null,
												"Meter el fichero a mano tambien ha cascado");
										loMeteMal = true;
									}
								}
							}
						}

						try {
							g.cargarTiemposDesdeFichero(eventoPulsado, f);
							salioBien = true;
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null,
									"Ha cascado algo en la lectura del fichero, revisalo y vuelve a cargarlo. Gracias.\n Debe cumplir el siguiente formato: \nidEvento;dorsal;tEtapa1@tEtapa2@tEtapa3.....");
							return;
						}
						if (salioBien)
							JOptionPane.showMessageDialog(null, "Las clasificaciones se han cargado con éxito");
						
						
					} else {
						JOptionPane.showMessageDialog(null,
								"No se ha podido cargar el fichero. (Igual está mal la condición)");
					}
				}
			});
			btEditarEventoOr.setToolTipText("Edita el evento seleccionado.");
			btEditarEventoOr.setMnemonic('E');
		}
		return btEditarEventoOr;
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
					if(!comprobarPlazosIncscripcion()) return;
					if (faltaAlgoPorRellenar()) {
						JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.");
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
					g.añadirEvento(evento);
					actualizarModeloTablaOr();
					cambiarPanelesOrganizador("misEventos");

				}
			});
		}
		return btCrearEvento;
	}

	private void actualizarModeloTablaOr() {
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
			pnContenidoCreacionEvento.add(getBtAñadirCat());

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
			pnContenidoCreacionEvento.add(getCbAño());
			pnContenidoCreacionEvento.add(getLblDa());
			pnContenidoCreacionEvento.add(getLbMes());
			pnContenidoCreacionEvento.add(getLbAño());
			pnContenidoCreacionEvento.add(getLblNmeroDeEtapas());
			pnContenidoCreacionEvento.add(getSpNumeroEtapas());
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
																		// aÃƒÂ±adir
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
			tfDistanciaEvento.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent arg0) {
					cambiarModeloSpinner();
				}
				@Override
				public void focusGained(FocusEvent e) {
					cambiarModeloSpinner();
				}
			});
			tfDistanciaEvento.setBounds(162, 114, 50, 20);
			tfDistanciaEvento.setColumns(10);
		}
		return tfDistanciaEvento;
	}
	private void cambiarModeloSpinner(){
		String t = getTfDistanciaEvento().getText();
		if(t.isEmpty())
			;
		else if(Comprobaciones.esNumero(t)){
			int n = Integer.parseInt(t);
			modeloSpinnerEtapas = new SpinnerNumberModel(1, 1, n, 1);
			getSpNumeroEtapas().setModel(modeloSpinnerEtapas);
		}
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
	private JButton btAñadirAtleta;
	private JLabel label;
	private JTextField txtDNIInscribirse;
	private JTextField txtNombreInscribirse;
	private JTextField txtFechaInscribirse;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblFechaNacimientoddmmyyyy;
	private JLabel label_4;
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

	// ----------------------------------- MÃƒÂ©todos para crear un evento

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
			throw new IllegalArgumentException("La distancia no es un nÃƒÂºmero");
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
		g.crearEvento(nombre, tipo, distancia, fechaComienzo, plazas, categoriasParaEvento, plazosInscripcion,
				organizador.getId());
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
			lblCategorasDelEvento.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
			cbCatDef.setBounds(777, 75, 189, 23);

		}
		return cbCatDef;
	}

	private JScrollPane getScrollPaneCategorias() {
		if (scrollPaneCategorias == null) {
			scrollPaneCategorias = new JScrollPane();
			scrollPaneCategorias.setBounds(777, 115, 258, 225);
			scrollPaneCategorias.setViewportView(getListCategoriaOr());
			// scrollPane.add(getList()); //Esto no se si estara del todo
			// bien...
		}
		return scrollPaneCategorias;

	}

	private DefaultListModel<String> modeloListaCategorias = null;
	ArrayList<Categoria> categoriasAlCrearEvento;
	private JButton btAñadirCat;
	private JButton btEditarCategoria;
	private JButton btnResultadosEvento;
	private JPanel pnEventoSeleccionado;
	private JLabel lblPlazosDeInscripcin;
	private JScrollPane scrollPaneFechasIns;
	private JList<String> listFechasInscrip;

	public ArrayList<Categoria> getCategoriasCrearEvento() {
		return categoriasAlCrearEvento;
	}

	public void añadirCategoriaAlCrearEvento(Categoria c) {
		categoriasAlCrearEvento.add(c);
		añadirAlModeloListaCategoriasOr(c);
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

	private JButton getBtAñadirCat() {

		if (btAñadirCat == null) {
			btAñadirCat = new JButton("A\u00F1adir ");
			btAñadirCat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaCreaCategoria vcc = new VentanaCreaCategoria(VentanaPrincipal.this);
					vcc.setVisible(true);
				}
			});
			btAñadirCat.setToolTipText("A\u00F1ade una categoria al evento");
			btAñadirCat.setBounds(857, 351, 89, 23);
		}
		return btAñadirCat;

	}

	private JButton getBtEditarCategoria() {
		if (btEditarCategoria == null) {
			btEditarCategoria = new JButton("Editar");
			btEditarCategoria.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					editarCategoria();

				}


			});
			btEditarCategoria.setToolTipText("Editar categoria seleccionada");
			btEditarCategoria.setBounds(946, 351, 89, 23);
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
			pnInscribirse.add(getBtnUsuarioActual());
			pnInscribirse.add(getBtnRealizarInscripcion());
			pnInscribirse.add(getBtnSeleccionarOtroEvento());
			pnInscribirse.add(getBtnCargarFichero());
			pnInscribirse.add(getRdbtnMasculino());
			pnInscribirse.add(getRdbtnFemenino());
		}
		return pnInscribirse;
	}

	private JPanel getPnListaInscritos() {
		if (pnListaInscritos == null) {
			pnListaInscritos = new JPanel();
			pnListaInscritos
					.setBorder(new TitledBorder(null, "Inscribir", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnListaInscritos.setLayout(null);
			pnListaInscritos.setBounds(623, 11, 295, 297);
			pnListaInscritos.add(getScrollPane_1_1());
			pnListaInscritos.add(getBtAñadirAtleta());
			pnListaInscritos.add(getBtnEliminarInscribirse());
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

	private JButton getBtAñadirAtleta() {
		if (btAñadirAtleta == null) {
			btAñadirAtleta = new JButton("A\u00F1adir");
			btAñadirAtleta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnRealizarInscripcion.setEnabled(true);
					AnadirInscritoALista();
				}

			});
			btAñadirAtleta.setBounds(34, 263, 89, 23);
		}
		return btAñadirAtleta;
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
			txtDNIInscribirse.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if (!Character.isDigit(c)) {
						arg0.consume();
					}

				}
			});
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
			txtFechaInscribirse.addKeyListener(new KeyAdapter() {

				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if (Character.isAlphabetic(c)) {
						e.consume();
					}
					if (c != '/') {
						if (!Character.isDigit(c)) {
							e.consume();
						}
					}
				}
			});
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

	private JButton getBtnUsuarioActual() {
		if (btnUsuarioActual == null) {
			btnUsuarioActual = new JButton("Usuario actual");
			btnUsuarioActual.setEnabled(false);
			btnUsuarioActual.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					autorrellenar();
				}
			});
			btnUsuarioActual.setBounds(124, 319, 118, 23);
		}
		return btnUsuarioActual;
	}

	private JList<String> getListInscribirse() {
		if (listInscribirse == null) {
			modeloListaInscribirse = new DefaultListModel<String>();
			listInscribirse = new JList<String>(modeloListaInscribirse);
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
					txPrecioTotal.setText(precioTotal + "€");
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
					// diE.mostrarClasificaciones();
					diE.setVisible(true);
				}
			});
			btnResultadosEvento.setEnabled(false);
		}
		return btnResultadosEvento;
	}

	private JPanel getPnEventoSeleccionado() {
		if (pnEventoSeleccionado == null) {
			pnEventoSeleccionado = new JPanel();
			pnEventoSeleccionado.setBorder(new TitledBorder(null, "Evento seleccionado: ", TitledBorder.LEADING, TitledBorder.TOP,
					null, null));
			pnEventoSeleccionado.setLayout(new BorderLayout(0, 0));
			pnEventoSeleccionado.add(getPnInfoEvPulsado());
			pnEventoSeleccionado.add(getBtnResultadosEvento(), BorderLayout.SOUTH);
		}
		return pnEventoSeleccionado;
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
	private JComboBox<Integer> cbAño;
	private JLabel lblDa;
	private JLabel lbMes;
	private JLabel lbAño;

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

	private JComboBox getCbAño() {
		if (cbAño == null) {
			cbAño = new JComboBox();
			Integer[] años = new Integer[10];
			for (int i = 0; i < años.length; i++) {
				años[i] = 2000 + i + 16; // Que la fecha empiece este aÃƒÂ±o
											// 2016

			}
			DefaultComboBoxModel<Integer> modelo = new DefaultComboBoxModel<Integer>(años);
			cbAño.setModel(modelo);
			cbAño.setBounds(360, 272, 56, 20);
		}
		return cbAño;
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

	private JLabel getLbAño() {
		if (lbAño == null) {
			lbAño = new JLabel("a\u00F1o");
			lbAño.setHorizontalAlignment(SwingConstants.CENTER);
			lbAño.setBounds(360, 246, 63, 14);
		}
		return lbAño;
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
			JOptionPane.showMessageDialog(null, "El numero de plazas y la distancia han de ser nÃƒÂºmeros");
			return null;
		}
		ArrayList<PlazoInscripcion> plazos = plazosInscripcionNuevoEvento;
		ArrayList<Categoria> categorias = categoriasAlCrearEvento;
		if (plazos == null || plazos.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No se han establecido plazos de inscripciÃƒÂ³n.");
			return null;
		}
		if (!getCbCatDef().isSelected()) {
			if (categorias == null || categorias.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se han establecido las categorÃƒÂ­as del evento.");
				return null;
			}
		}
		// Fecha
		int dia = Integer.parseInt(getCbDia().getSelectedItem().toString());
		String mes = getCbMes().getSelectedItem().toString();
		int año = Integer.parseInt(getCbAño().getSelectedItem().toString());
		Date miFechaComienzo = null;
		try {
			miFechaComienzo = new Date(año - 1900, ConversorFechas.getNumeroMes(mes), dia);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"La fecha no se ha creado correctamente (Puede ser fallo en el comienzo del año de la fecha - 1900)");
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
						miFechaComienzo, categorias, plazos, organizador.getId(), (Integer) getSpNumeroEtapas().getValue() );
			} else {
				evento = new Evento(nombre, tipo, Integer.parseInt(distancia), Integer.parseInt(plazas),
						miFechaComienzo, catDef, plazos, organizador.getId(), (Integer) getSpNumeroEtapas().getValue());
			}
		}
		return evento;
	}
	
	
	private boolean comprobarPlazosIncscripcion(){
		boolean res = GestorFechasInscripcion.comprobarFechasCorrectas(plazosInscripcionNuevoEvento);
		if(!res){
			if(GestorFechasInscripcion.plazoQueFalla1 != null && GestorFechasInscripcion.plazoQueFalla2 != null){
				PlazoInscripcion p1 = GestorFechasInscripcion.plazoQueFalla1;
				PlazoInscripcion p2 = GestorFechasInscripcion.plazoQueFalla2;
				JOptionPane.showMessageDialog(null, "Los plazos que se solapan son los siguientes:\n Plazo 1 = "+p1.toString()+"\nPlazo 2 = "+p2.toString(),"Solapamiento de plazos",JOptionPane.ERROR_MESSAGE);
				
			}
			return false;
		}
		return true;
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
	private JButton btnCargarFichero;
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFemenino;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnEliminarInscribirse;
	private JPanel pnDFiltros;
	private JLabel lblNmeroDeEtapas;
	private JSpinner spNumeroEtapas;

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
							getTxNumTarjeta().setText("");
							getTxTituTarjeta().setText("");
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

	public boolean comprobarDatosTarjeta() {
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
			txNumTarjeta.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if (!Character.isDigit(c)) {
						arg0.consume();
					}
				}
			});
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
			txTituTarjeta.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent arg0) {
					char c = arg0.getKeyChar();
					if (!Character.isLetter(c)) {
						if (!Character.isWhitespace(c))
							arg0.consume();
					}
				}
			});
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
	public void añadirAlModeloListFechaInscripcionOr(String pl) {
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

	public void añadirPlazo(PlazoInscripcion plazo) {
		this.plazosInscripcionNuevoEvento.add(plazo);
		añadirAlModeloListFechaInscripcionOr(plazo.toString());
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

	public void añadirAlModeloListaCategoriasOr(Categoria elem) {
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

	private JButton getBtnCargarFichero() {
		if (btnCargarFichero == null) {
			btnCargarFichero = new JButton("Cargar fichero");
			btnCargarFichero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cargarFichero();
				}

			});
			btnCargarFichero.setBounds(341, 319, 118, 23);
		}
		return btnCargarFichero;
	}

	private JRadioButton getRdbtnMasculino() {
		if (rdbtnMasculino == null) {
			rdbtnMasculino = new JRadioButton("Masculino");
			buttonGroup.add(rdbtnMasculino);
			rdbtnMasculino.setBounds(223, 206, 93, 23);
		}
		return rdbtnMasculino;
	}

	private JRadioButton getRdbtnFemenino() {
		if (rdbtnFemenino == null) {
			rdbtnFemenino = new JRadioButton("Femenino");
			buttonGroup.add(rdbtnFemenino);
			rdbtnFemenino.setBounds(342, 206, 88, 23);
		}
		return rdbtnFemenino;
	}

	private JButton getBtnEliminarInscribirse() {
		if (btnEliminarInscribirse == null) {
			btnEliminarInscribirse = new JButton("Eliminar");
			btnEliminarInscribirse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					eliminarListaInscribirse();
				}

			});
			btnEliminarInscribirse.setEnabled(false);
			btnEliminarInscribirse.setBounds(184, 263, 89, 23);
		}
		return btnEliminarInscribirse;
	}
	private JPanel getPanel_4_5() {
		if (pnDFiltros == null) {
			pnDFiltros = new JPanel();
			pnDFiltros.setLayout(new BorderLayout(0, 0));
			pnDFiltros.add(pF);
		}
		
		return pnDFiltros;
	}
	private JLabel getLblNmeroDeEtapas() {
		if (lblNmeroDeEtapas == null) {
			lblNmeroDeEtapas = new JLabel("N\u00FAmero de etapas:");
			lblNmeroDeEtapas.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNmeroDeEtapas.setBounds(222, 161, 106, 14);
		}
		return lblNmeroDeEtapas;
	}
	SpinnerNumberModel modeloSpinnerEtapas;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mtRecargar;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	private JSpinner getSpNumeroEtapas() {
		if (spNumeroEtapas == null) {
			spNumeroEtapas = new JSpinner();
			spNumeroEtapas.setToolTipText("Número de etapas del evento. Distacia etapa = distEvento / nEtapas");
			modeloSpinnerEtapas = new SpinnerNumberModel(1, 1, 15, 1);
			spNumeroEtapas.setModel(modeloSpinnerEtapas);
			spNumeroEtapas.setBounds(338, 158, 47, 20);
		}
		return spNumeroEtapas;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnArchivo());
		}
		return menuBar;
	}
	private JMenu getMnArchivo() {
		if (mnArchivo == null) {
			mnArchivo = new JMenu("Archivo");
			mnArchivo.add(getMtRecargar());
			mnArchivo.add(getSeparator());
			mnArchivo.add(getMntmSalir());
		}
		return mnArchivo;
	}
	private JMenuItem getMtRecargar() {
		if (mtRecargar == null) {
			mtRecargar = new JMenuItem("Recargar");
			mtRecargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					recargarAplicacion();
				}
			});
		}
		return mtRecargar;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmSalir;
	}
}

package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;
import logica.Vistas.PlazoInscripcion;
import logica.filtros.FiltrarPlazasLlenas;
import logica.filtros.FiltrarPlazoCerrado;
import logica.filtros.FiltrarRangosDistancias;
import logica.filtros.FiltrarTerminados;
import logica.filtros.FiltrarTipo;
import logica.filtros.Filtro;
import persistencia.ConexionBD;
import persistencia.GestorFicheros;
import usuarios.Organizador;

public class Gestor {

	ArrayList<Evento> eventos;
	ArrayList<Atleta> atletas;
	Organizador organizador;
	Evento eventoSeleccionado;
	Atleta atIdentificado;

	private ConexionBD bd = new ConexionBD();
	private GestorFicheros gF = new GestorFicheros();
	private BufferedReader reader;

	public Gestor() {
		this.eventos = new ArrayList<Evento>();
		this.atletas = new ArrayList<Atleta>();
		cargarDatos();
		comprobarFechasInscripcion();
		comprobarFechasEventoFinalizado();
	}

	/**
	 * Metodo que da por finalizados los eventos corridos
	 */
	private void comprobarFechasEventoFinalizado() {
		java.util.Date fechaActualJava = new java.util.Date();
		Date fecha = new java.sql.Date(fechaActualJava.getTime());
		for (Evento ev : eventos) {
			if (ev.getFechaCompeticion().before(fecha))
				finalizarEvento(ev.getId());
		}
	}

	/**
	 * Metodo que comprueba las fechas de las inscripciones a cada evento, si se
	 * produce un cambio se actualiza la base de datos
	 * 
	 */
	private void comprobarFechasInscripcion() {
		java.util.Date fechaActualJava = new java.util.Date();
		Date fecha = new java.sql.Date(fechaActualJava.getTime());
		for (Evento ev : eventos) {
			if (ev.comprobarFechasInscripcion(fecha))
				bd.actualizarPlazos(ev);
		}
	}

	/**
	 * Metodo que llama a la base de datos para cargar todos los datos
	 */
	private void cargarDatos() {
		if (!bd.cargarDatos(this)) {
			JOptionPane.showMessageDialog(null, "Imposible conectar con la base de datos", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		for (Evento e : eventos) {
			if (e.getFinalizado())
				e.generarClasificacion();
		}

	}

	/**
	 * Metodo que busca y marca como atleta identificado si es que existe
	 * 
	 * @param dni:
	 *            del atleta buscado
	 * @return
	 */
	public boolean identificarAtletaPorDNI(String dni) {
		atIdentificado = buscarAtletaPorDNI(dni);
		if (atIdentificado != null) {
			return true;
		}
		return false;
	}

	public Atleta getAtletaIdentificado() {
		return atIdentificado;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public ArrayList<Atleta> getAtletas() {
		return atletas;
	}

	public ArrayList<Evento> getEventosAbiertos() {
		Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());
		ArrayList<Evento> evDisp = new ArrayList<Evento>();
		for (Evento evento : eventos) {
			if (evento.getFechaFinInscripcion().getTime() >= fechaActual.getTime()) {
				evDisp.add(evento);
			}
		}
		return evDisp;
	}

	public ArrayList<Evento> getEventosDisponibles() {
		return this.eventos;
	}

	public ArrayList<Evento> obtenerEventosParticipaPorDNI(String dni) {
		ArrayList<Evento> evInscrito = new ArrayList<Evento>();
		for (Evento e : eventos) {
			for (Inscripcion a : e.getInscritosEvento()) {
				if (a.getAtleta().DNI.equals(dni)) {
					evInscrito.add(e);
				}
			}
		}
		return evInscrito;
	}

	public Inscripcion getInscripcion(String dni, Evento ev) {
		for (Inscripcion ins : ev.getInscritosEvento()) {
			if (dni.equals(ins.getAtleta().getDNI()))
				return ins;
		}
		return null;
	}

	public void mostrarInscritosDeEvento(int id) {
		Evento ev = null;
		for (Evento e : eventos) {
			if (e.getId() == id) {
				ev = e;
			}
		}
		if (ev == null) {
			System.out.println("No se ha encontrado el evento");
		} else {
			ArrayList<Inscripcion> inscritos = ev.getInscritosEvento();
			for (Inscripcion a : inscritos) {
				System.out.println(a.toString());
			}
		}
	}

	public boolean comprobarAtletaRegistrado(String dni) {
		for (int i = 0; i < atletas.size(); i++) {
			if (atletas.get(i).getDNI().equals(dni)) {
				return true;
			}
		}
		return false;

	}

	public Atleta buscarAtletaPorDNI(String dni) {
		for (int i = 0; i < atletas.size(); i++) {
			if (atletas.get(i).getDNI().equals(dni)) {
				return atletas.get(i);
			}
		}
		return null;
	}

	public void a�adirInscripcionEvento(Atleta atl, Evento evento) {
		Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());

		boolean repetido = false;
		for (int i = 0; i < evento.getInscritosEvento().size(); i++) {
			if (evento.getInscritosEvento().get(i).getIdEvento() == evento.getId()
					&& evento.getInscritosEvento().get(i).getAtleta() == atl) {
				repetido = true;
			}
		}
		if (!repetido && evento.getPlazasDisponibles() > 0) {
			Inscripcion ins = new Inscripcion(atl, fechaActual, evento);
			System.out.println("Se ha inscrito correctamente. Detalles:");
			System.out.println();
			evento.a�adirInscrito(ins);
			mostrarEstado(ins);
			System.out.println();
			bd.a�adirInscrito(atl, ins);
		}
	}

	/**
	 * Pasar el n�mero de cuenta al usuario para que pueda pagar, viene en un
	 * txt antes de los que han pagado.
	 * 
	 * @throws IOException
	 */
	public void mostrarEstado(Inscripcion ins) {
		System.out.println("Se ha actualizado su estado y ahora consta como Pendiente de Pago.");
		ins.setEstado(1);
		bd.actualizarEstadoPago(ins, 1);
	}

	/**
	 * Asignaci�n de dorsales a partir del 10. Antes de ello se ordenan los
	 * inscritos por fecha de inscripci�n y estado del pago.
	 */
	public void asignarDorsales(int idEvento) {
		Collections.sort(eventos.get(idEvento).getInscritosEvento());

		ArrayList<Inscripcion> inscritos = eventos.get(idEvento).getInscritosEvento();
		int cont = 10;

		for (Inscripcion i : inscritos) {
			if (i.getEstado() == 2) {
				i.setDorsal(cont);
				bd.asignarDorsal(i, cont);
				cont++;
			} else {
				i.setDorsal(-1);
				bd.asignarDorsal(i, -1);
			}
		}
	}

	public void realizarPagoTarjeta(int idEvento, ArrayList<String> dni) {

		ArrayList<Inscripcion> inscritos = obtenerEventoPorId(idEvento).getInscritosEvento();

		for (Inscripcion i : inscritos) {
			for (int j = 0; j < dni.size(); j++) {
				if (i.getAtleta().getDNI().toUpperCase().equals(dni.get(j).toUpperCase())) {
					i.setEstado(2);
					bd.actualizarEstadoPago(i, 2);
					System.out.println("Se ha actualizado su estado y ahora consta como Pagado.");
				}
			}
		}
	}

	/**
	 * M�todo que verifica los DNI del txt para saber que atletas han pagado.
	 * 
	 * @throws IOException
	 */
	public void comprobarPagadosBanco(int idEvento) {
		try {
			boolean actualizado = false;

			BufferedReader fichero = new BufferedReader(new FileReader("ficheros/banco.txt"));

			ArrayList<Inscripcion> inscritos = eventos.get(idEvento).getInscritosEvento();

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			while (fichero.ready()) {
				String linea = fichero.readLine();
				String[] trozos = linea.split(";");

				String dni = trozos[0];

				java.util.Date parsed = null;
				try {
					parsed = format.parse(trozos[1]);
				} catch (ParseException e) {
					e.printStackTrace();
				}

				java.sql.Date fecha = new java.sql.Date(parsed.getTime());

				double dinero = Double.parseDouble(trozos[2]);

				BufferedReader ficheroPagos = new BufferedReader(new FileReader("ficheros/pagos.txt"));

				while (ficheroPagos.ready()) {

					String linea2 = ficheroPagos.readLine();
					String[] trozos2 = linea2.split(";");

					String dniPendiente = trozos2[0];
					double dineroPendiente = Double.parseDouble(trozos2[1]);

					if (dni.equals(dniPendiente) && dinero >= dineroPendiente
							&& fecha.before(obtenerEventoPorId(idEvento).getFechaCompeticion()))
						for (Inscripcion i : inscritos)
							if (i.getAtleta().getDNI().toUpperCase().equals(dni)) {
								i.setEstado(2);
								bd.actualizarEstadoPago(i, 2);
								actualizado = true;
								System.out.println("Se ha actualizado su estado y ahora consta como Pagado.");
							}
				}
				ficheroPagos.close();
			}
			fichero.close();
			if (!actualizado)
				System.out.println("\nNing�n estado de pago ha sido actualizado.");
		} catch (FileNotFoundException fnfe) {
			new FileNotFoundException("Fichero no encontrado");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public void agregarPendientesBanco(ArrayList<String> dni, double precioEvento, boolean continuar) {
		try {
			PrintWriter fichero = new PrintWriter(new FileWriter("ficheros/pagos.txt", continuar));

			for (String s : dni)
				fichero.write("\n" + s + ";" + precioEvento);

			fichero.close();
		} catch (FileNotFoundException fnfe) {
			new FileNotFoundException("Fichero de pagos no encontrado");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public Evento obtenerEventoPorId(int id) {
		for (Evento e : eventos) {
			if (e.getId() == id)
				return e;
		}
		return null;
	}

	public void addAtleta(Atleta atl) {
		atletas.add(atl);
		bd.a�adirAtleta(atl);
	}

	public ArrayList<Evento> getEventosOrganizador(Organizador or) {
		return or.getMisEventos();
	}

	/***
	 * Metodo usado para crear eventos y a�adirlos tanto al array como a la base
	 * de datos
	 * 
	 * @param nombre
	 * @param tipo
	 * @param precio
	 * @param distancia
	 * @param fecha_comienzo
	 * @param fecha_fin_insc
	 * @param plazasTotales
	 */
	public void crearEvento(String nombre, String tipo, double distancia, Date fecha_comienzo, int plazasTotales,
			ArrayList<Categoria> categoriasDelEvento, ArrayList<PlazoInscripcion> plazos, int idOrganizador) {
		// Los fallos de argumento que los controle la interfaz para que no se
		// detenga la ejecuci�n.
		// if(!Comprobaciones.esString(nombre) || !Comprobaciones.esString(tipo)
		// || fecha_comienzo == null || categoriasDelEvento == null || plazos ==
		// null)
		// throw new IllegalArgumentException();
		//
		// Si todo esta bien se crea el evento y se a�ade
		int ultimaId = getEventos().get(getEventos().size() - 1).getId();
		Evento nuevoEvento = new Evento(ultimaId + 1, nombre, tipo, distancia, plazasTotales, false,
				categoriasDelEvento, plazos, fecha_comienzo, idOrganizador);
		eventos.add(nuevoEvento);
		bd.a�adirEventoABD(nuevoEvento);
	}

	/**
	 * Mirar este metodo y comprobar que se le pasa bien todo lo que necesita la
	 * bd (JAVIMENSAJE)
	 * 
	 * @param e
	 */
	public void a�adirEvento(Evento e) {
		if (!eventos.isEmpty()) {

			e.setId(eventos.get(eventos.size() - 1).getId() + 1);
		} else {
			e.setId(0);
		}
		eventos.add(e);
		bd.a�adirEventoABD(e);
	}

	/**
	 * Metodo que da por concluido el evento con el id que se le pasa por
	 * parametro y busca el fichero con los resultados
	 * 
	 * @param id:
	 *            id del evento que finaliza
	 */
	public void finalizarEvento(int id) {
		if (obtenerEventoPorId(id) != null && !obtenerEventoPorId(id).getFinalizado())
			comprobarPagadosBanco(id);
		asignarDorsales(id);
		//gF.obtenerResultadosEvento(obtenerEventoPorId(id), bd);
		eventos.get(id).setFinalizado();
		bd.marcarComoFinalizado(obtenerEventoPorId(id));
	}

	public void listarEventosAbiertos() {
		ArrayList<Evento> eventosAbiertos = getEventosAbiertos();
		for (int i = 0; i < eventosAbiertos.size(); i++) {
			System.out.println(i + ". " + eventos.get(i).toString());
		}

	}

	public void confirmarSeleccion(Evento ev) {
		eventoSeleccionado = ev;
	}

	public Evento getEventoSeleccionado() {
		return eventoSeleccionado;
	}

	public boolean existeAtletaEnEvento(int id, String dni) {
		Evento ev = null;
		for (Evento e : eventos) {
			if (e.getId() == id)
				ev = e;
		}
		if (ev != null) {
			for (Inscripcion i : ev.getInscritosEvento()) {
				if (i.getAtleta().getDNI().equals(dni)) {
					return true;
				}
			}
		}
		return false;
	}

	public void inscribirLote(List<Atleta> atletasAInscribir) {
		for (int i = 0; i < atletasAInscribir.size(); i++) {
			a�adirInscripcionEvento(atletasAInscribir.get(i), eventoSeleccionado);
		}

	}

	public void registrarAtleta(String DNI, String nombre, String fecha, int sexo, Evento evento) {

		if (!DNI.equals("") && !comprobarAtletaRegistrado(DNI)) {

			Atleta atl = new Atleta(DNI, nombre, fecha, sexo);
			addAtleta(atl);

		}

	}

	public boolean comprobarFecha(String fecha) {

		char[] array = fecha.toCharArray();
		if (array.length != 10) {
			return false;
		}
		if (Character.isDigit(array[0]) && Character.isDigit(array[1]) && Character.isDigit(array[3])
				&& Character.isDigit(array[4]) && Character.isDigit(array[6]) && Character.isDigit(array[7])
				&& Character.isDigit(array[8]) && Character.isDigit(array[9]) && array[2] == '/' && array[5] == '/') {

			String[] trozos = fecha.split("/");
			int a�o = Integer.parseInt(trozos[2]);
			int dia = Integer.parseInt(trozos[0]);

			int mes = Integer.parseInt(trozos[1]);
			if (a�o <= 2015 && a�o > 1896 && mes > 0 && mes < 13 && dia > 0) {
				if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 9 || mes == 11) {
					if (dia <= 31) {
						return true;
					}
				} else if (mes % 2 == 0 && mes != 2) {
					if (dia <= 30) {
						return true;
					}
				} else {
					if (a�o % 4 == 0 && a�o % 100 != 0 || a�o % 400 == 0) {
						// es bisiesto
						if (dia <= 29) {
							return true;
						}
					} else {
						if (dia <= 28) {
							return true;
						}
					}
				}

			} else {
				System.out.println("Rango de edad no permitido");
			}
		} else {
			System.out.println("Formato de fecha mal introducido");
		}
		return false;
	}

	public void registrarLoteAtletas(List<Atleta> atletasARegistrar) {
		for (int i = 0; i < atletasARegistrar.size(); i++) {
			registrarAtleta(atletasARegistrar.get(i).getDNI(), atletasARegistrar.get(i).getNombre(),
					atletasARegistrar.get(i).getFechaNacimiento(), atletasARegistrar.get(i).getSexo(),
					eventoSeleccionado);
		}

	}

	public List<Atleta> leerFicheroInscribirAtletas(File fichero) {
		List<Atleta> atletasFichero = new ArrayList<Atleta>();
		try {
			reader = new BufferedReader(new FileReader(fichero));

			while (reader.ready()) {
				String[] trozos = reader.readLine().split(";");
				if (!trozos[0].equals("") && !trozos[1].equals("") && comprobarFecha(trozos[2])
						&& (trozos[3].equals("masculino") || trozos[3].equals("femenino"))) {
					atletasFichero
							.add(new Atleta(trozos[0], trozos[1], trozos[2], trozos[3].equals("masculino") ? 0 : 1));
				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
		return atletasFichero;

	}

	public void asignarTiemposAEvento(int dorsal, ArrayList<Integer> tiempos, Evento evento) {
		evento.asignarTiemposDorsal(dorsal, tiempos, this.bd);
	}

	public void cargarTiemposDesdeFichero(Evento e, FileReader fich) throws NumberFormatException, IOException {
		GestorFicheros.cargarClasificacionDeEvento(e, fich, this);
	}

	public void asignarEventosAOrganizador(Organizador or) {
		for (Evento e : eventos) {
			if (e.getIdOrganizador() == or.getId()) {
				or.crearEvento(e);
			}
		}
	}

	/**
	 * Metodo que devuelve una lista de string con los tipos de eventos
	 * encontrados
	 * 
	 * @return ArrayList<String> tipos de eventos
	 */
	public ArrayList<String> obtenerTipos() {
		ArrayList<String> tipos = new ArrayList<String>();
		for (Evento e : eventos)
			tipos.add(e.getTipo());
		return tipos;
	}

	/**
	 * Metodo filtrar que usa el Gestor de filtros para aplicar filtros
	 * 
	 * @param lista:
	 *            lista a filtrar
	 * @param distancia:
	 *            si es true a�ade filtro de distancias
	 * @param maxDistancia:
	 *            int maxima distancia para el filtro de distancias
	 * @param minDistancia:
	 *            int minima distancia para el filtro de distancias
	 * @param tipo:
	 *            tipo de eventos que se filtran
	 * @param terminados:
	 *            si es true se a�ade filtro para eventos terminados
	 * @param plCerrada:
	 *            si es true se a�ade filtro para plazos cerrados
	 * @param plzLlenas:
	 *            si es true se a�ade el filtro para filtrar plazas llenas
	 * @return lista con los eventos ya filtrados.
	 */
	public ArrayList<Evento> filtrar(ArrayList<Evento> lista, boolean distancia, int maxDistancia, int minDistancia,
			String tipo, boolean terminados, boolean plCerrada, boolean plzLlenas) {
		ArrayList<Filtro> filtros = new ArrayList<Filtro>();
		filtros.add(new FiltrarTipo(tipo));
		if (distancia)
			filtros.add(new FiltrarRangosDistancias(minDistancia, maxDistancia));
		if (!terminados)
			filtros.add(new FiltrarTerminados());
		if (!plCerrada)
			filtros.add(new FiltrarPlazoCerrado());
		if (!plzLlenas)
			filtros.add(new FiltrarPlazasLlenas());
		Filtro[] f = new Filtro[filtros.size()];
		filtros.toArray(f);
		return new GestorFiltros().filtrar(lista, f);
	}
	
	//Test
	public void setEventoSelccionado(Evento e){
		this.eventoSeleccionado = e;
	}
}

package igu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

import logica.Gestor;
import logica.GestorClasificaciones;
import logica.Vistas.Atleta;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;

public class Main {
	static Gestor g;
	static boolean ejecucion = true;

//	public static void main(String[] args) throws IOException, ParseException {
//		g = new Gestor();
//
//		mostrarComandosDisponibles();
//	}

	private static void mostrarComandosDisponibles() {
		System.out
				.println("\n -------------------------------------------- \n");
		while (ejecucion) {

			System.out
					.println("\nEscribir los siguientes comandos para mostrar contenido:");
			System.out.println();
			System.out
					.println("mostrarEventos -> muestra los eventos disponibles para un atleta.");
			System.out
					.println("mostrarAtletas -> Muestra los atletas inscritos a un evento a seleccionar.");
			System.out
					.println("mostrarInformacionDNI - DNI -> Muestra los eventos a los que se ha inscrito alguien con ese DNI");
			System.out
					.println("mostrarClasificacion - idEvento(int) -> Muestra los resultados de un evento ");
			System.out.println("inscribirseAEvento  ");
			System.out.println("Fin -> termina con la ejecucion del programa.");

			System.out.print("\nEscriba: ");
			String lectura = consoleRead();
			comandoEscogido(lectura);
			System.out
					.println("\n -------------------------------------------- \n");
		}
	}

	private static void comandoEscogido(String comand) {
		try{
		if (!comand.equals("Fin")) {
			System.out.println("Resultado: \n");
		}
		String[] cachos = comand.split(" - ");
		switch (cachos[0]) {
		case "mostrarEventos":
			imprimirEventos(g.getEventosAbiertos());
			break;
		case "mostrarAtletas":
			// g.mostrarInscritosDeEvento(Integer.parseInt(cachos[1]));
			mostrarAtletasDeUnEvento();
			break;
		case "mostrarInformacionDNI":
			mostrarInformacionDNI(cachos[1]);
			break;
		case "mostrarClasificacion":
			try {
				g.comprobarPagados(Integer.parseInt(cachos[1]));
				g.asignarDorsales(Integer.parseInt(cachos[1]));
				g.finalizarEvento(Integer.parseInt(cachos[1]));
				mostrarClasificacion(Integer.parseInt(cachos[1]));
			} catch (NumberFormatException e) {
				System.err
						.println("ID invalido, ha de ser un numero mayor o igual a 0.");
			}

			break;
		case "Fin":
			ejecucion = false;
			break;
		case "inscribirseAEvento":
			System.out.println("Seleccione una opcion: ");
			g.listarEventosAbiertos();
			int respuesta = Integer.parseInt(consoleRead());
			ArrayList<Evento> eve = g.getEventosAbiertos();
			// Registramos al atleta y se añade a la inscripcion seleccionada
			registrarAtleta(eve.get(respuesta));

			break;
		default:
			System.out.println("Comando no reconocido.");
		}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("\n Falta meter algún parámetro. \n");
		}
	}

	/**
	 * Metodo que registra a un atleta si no estaba registrado y ademas lo
	 * apunta al envento que se le pasa como parametro
	 */

	private static void registrarAtleta(Evento evento) {
		String DNI = "";

		System.out.println("Introduzca su DNI");
		DNI = consoleRead();
		if (!DNI.equals("") && !g.comprobarAtletaRegistrado(DNI)) {
			String[] datos = pedirDatos();
			Atleta atl = new Atleta(DNI, datos[0], datos[1],
					Integer.parseInt(datos[2]));
			g.addAtleta(atl);
			g.añadirInscripcionEvento(atl, evento);
		} else {
			Atleta atl = g.buscarAtletaPorDNI(DNI);
			//String respuesta = "";
			System.out
					.println("Ya está registrado.");
			System.out.println("DNI: " + DNI + "\n Nombre :" + atl.getNombre()
					+ "\n Sexo: "
					+ (atl.getSexo() == 0 ? "Masculino" : "Femenino")
					+ "\n edad: " + atl.getEdad());
//			while (!respuesta.equals("s") || !respuesta.equals("n")) {
//
//				respuesta = consoleRead();
//			}
//
//			if (respuesta.equals("s")) {
//				String[] datos = pedirDatos();
//				atl.setNombre(datos[0]);
//				atl.setFechaNacimiento(datos[1]);
//				atl.setSexo(Integer.parseInt(datos[2]));
//			}
			g.añadirInscripcionEvento(atl, evento);
		}

	}

	private static String[] pedirDatos() {
		String nombre = "";
		int sexo = -1;
		String fechaNacimiento = "";
		String[] datos = new String[3];
		while (nombre.equals("") || nombre == null) {
			System.out.println("Introduzca su nombre");
			nombre = consoleRead();
		}
		datos[0] = nombre;
		while (!comprobarFecha(fechaNacimiento)) {
			System.out.println("Introduzca fecha nacimiento 'DD/MM/YYYY'");
			fechaNacimiento = consoleRead();
		}
		datos[1] = fechaNacimiento;
		while (sexo < 0 || sexo > 1) {
			System.out.println("Introduzca su sexo (0 -> Masc , 1 -> Fem)");
			sexo = Integer.parseInt(consoleRead());
		}
		datos[2] = String.valueOf(sexo);
		return datos;
	}

	private static boolean comprobarFecha(String fecha) {

		char[] array = fecha.toCharArray();
		if (array.length != 10) {
			return false;
		}
		if (Character.isDigit(array[0]) && Character.isDigit(array[1])
				&& Character.isDigit(array[3]) && Character.isDigit(array[4])
				&& Character.isDigit(array[6]) && Character.isDigit(array[7])
				&& Character.isDigit(array[8]) && Character.isDigit(array[9])
				&& array[2] == '/' && array[5] == '/') {

			String[] trozos = fecha.split("/");
			int año = Integer.parseInt(trozos[2]);
			int dia = Integer.parseInt(trozos[0]);

			int mes = Integer.parseInt(trozos[1]);
			if (año <= 2015 && año > 1896 && mes > 0 && mes < 13 && dia > 0) {
				if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 9
						|| mes == 11) {
					if (dia <= 31) {
						return true;
					}
				} else if (mes % 2 == 0 && mes != 2) {
					if (dia <= 30) {
						return true;
					}
				} else {
					if (año % 4 == 0 && año % 100 != 0 || año % 400 == 0) {
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

	private static void mostrarAtletasDeUnEvento() {
		System.out.println("\n Seleccione un id de los eventos existentes: \n");
		// imprimirEventos(g.getEventosDisponibles());
		for (Evento e : g.getEventosDisponibles()) {
			System.out.println("ID: " + e.getId() + "; Nombre: "
					+ e.getNombre());
		}
		System.out.print("\nID de evento seleccionado: ");
		int idEvento = Integer.parseInt(consoleRead());
		Evento evento = g.obtenerEventoPorId(idEvento);
		System.out.println("");
		if (evento.getInscritosEvento().size() == 0) {
			System.out.println("Actualmente no hay inscripciones al evento");
		} else {
			for (Inscripcion ins : evento.getInscritosEvento()) {
				// System.out.println(ins.toString());
				imprimirAtleta(ins);
			}
		}
	}

	private static void imprimirEventos(ArrayList<Evento> eventos) {
		if (eventos.size() == 0)
			System.out.println("No hay eventos disponibles.");
		else {
			for (Evento e : eventos) {
				imprimeEvento(e);
			}
		}

	}

	private static void imprimeEvento(Evento e) {
		// System.out.println(e.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		// sb.append("\nID: "+e.getId()+"\n");
		sb.append("Nombre: " + e.getNombre() + "\n");
		sb.append("Tipo: " + e.getTipo() + "\n");
		sb.append("Distancia: " + e.getDistancia() + "\n");
//	sb.append("Precio: " + e.getPrecio() + "\n");
		sb.append("Fecha de la competición: "
				+ e.getFechaCompeticion().toString() + "\n");
		sb.append("Fecha fin inscripción: "
				+ e.getFechaFinInscripcion().toString());
		System.out.println(sb.toString());
	}

	private static void imprimirAtleta(Inscripcion i) {
		Atleta a = i.getAtleta();

		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("DNI: " + a.getDNI() + "\n");
		sb.append("Nombre: " + a.getNombre() + "\n");
		// sb.append("Categoría: " + i.getCategoria() + "\n");
		// sb.append("Categoria: Actualmente no disponible.\n");
		sb.append("Categoría: " + a.getSexoString() + "\n");
		sb.append("Fecha de la inscripción: "
				+ i.getFechaInscripcion().toString() + "\n");

		System.out.println(sb.toString());
	}

	/**
	 * Evento que muestra por consola toda la informacion registrada acerca del
	 * atleta con el dni que se le pasa por parametro
	 * 
	 * @param dni
	 *            :
	 */
	private static void mostrarInformacionDNI(String dni) {
		ArrayList<Evento> evInscritos = g.obtenerEventosEInscripciones(dni);
		if(evInscritos.size()==0) System.err.println("No existen datos con ese DNI");
		else{
			System.out.println("EVENTOS INSCRITOS: ");
			for (Evento e : evInscritos) {
				Inscripcion inscripcion = g.getInscripcion(dni, e);
				System.out.println(" *** Evento: " + e.getId() + " Nombre del evento: " + e.getNombre());
	
				System.out.println("\tDatos de la inscripcion: ");
				System.out.println("\t "+inscripcion.toString());
				if(e.getFinalizado()){
					System.out.println("\tRESULTADOS DE LA CARRERA: ");
//					GestorClasificaciones cl = e.getClasificacion();
//					int posCategoria=-1;
//					int posAbsoluta=cl.obtenerPosicionAbsoluta(inscripcion);
//					posCategoria=cl.obtenerPosicionCategoria(inscripcion);
					String tiempo;
					if(inscripcion.getTiempoSegundos()==0) tiempo ="--";
					else tiempo =""+ inscripcion.getTiempoSegundos();
//					System.out.println("\t\tTiempo: " + tiempo + "\n\t\tPosicion absoluta: " + 
//					posAbsoluta + "\n\t\tPosicion en categoria: " + posCategoria);
				}
			}
		}
		
	}

	/**
	 * Metodo que muestra las tablas de resultados del evento con la id que se
	 * le pasa como parametro
	 * 
	 * @param id
	 *            : id del evento que se desea consultar
	 */
	private static void mostrarClasificacion(int id) {
//		if (id > g.getEventos().size()) {
//			System.err.println("El evento especificado no existe");
//		} else if (id >= 0) {
//			Evento evento = g.getEventos().get(id);
//			if (!evento.getFinalizado()) {
//				System.err
//						.println("El evento aun no ha finalizado y no existen resultados");
//			} else {
//
//				ArrayList<Inscripcion> clasificacionMasculina = evento
//						.getClasificacion().obtenerClasificacion("M");
//				ArrayList<Inscripcion> clasificacionFemenina = evento
//						.getClasificacion().obtenerClasificacion("F");
//				ArrayList<Inscripcion> clasificacionAbsoluta = evento
//						.getClasificacion().obtenerClasificacion("A");
//				System.out.println("\nClasificacion absoluta: \n");
//				System.out.println("Posicion\t  Sexo\t   Nombre\t     Tiempo");
//				for (int i = 0; i < clasificacionAbsoluta.size(); i++) {
//					Atleta corredor = clasificacionAbsoluta.get(i).getAtleta();
//					String sexo;
//					if (corredor.getSexo() == 0)
//						sexo = "Hombre";
//					else
//						sexo = "Mujer";
//					if(clasificacionAbsoluta.get(i).getDorsal() != -1){
//						System.out.println(i + 1 + "\t\t" + sexo + "\t\t"
//								+ corredor.getNombre() + "\t\t"
//								+ clasificacionAbsoluta.get(i).getTiempoSegundos());
//					}
//				}
//				System.out.println("\nClasificacion Femenina: ");
//				System.out.println("Posicion\t  Sexo\t   Nombre\t     Tiempo");
//				for (int i = 0; i < clasificacionFemenina.size(); i++) {
//					Atleta corredor = clasificacionFemenina.get(i).getAtleta();
//					String sexo;
//					if (corredor.getSexo() == 0)
//						sexo = "Hombre";
//					else
//						sexo = "Mujer";
//					System.out.println(i + 1 + "\t\t" + sexo + "\t\t"
//							+ corredor.getNombre() + "\t\t"
//							+ clasificacionFemenina.get(i).getTiempoSegundos());
//				}
//				System.out.println("\nClasificacion Masculina: \n");
//				System.out.println("Posicion\t  Sexo\t   Nombre\t     Tiempo");
//				for (int i = 0; i < clasificacionMasculina.size(); i++) {
//					Atleta corredor = clasificacionMasculina.get(i).getAtleta();
//					String sexo;
//					if (corredor.getSexo() == 0)
//						sexo = "Hombre";
//					else
//						sexo = "Mujer";
//					System.out
//							.println(i
//									+ 1
//									+ "\t\t"
//									+ sexo
//									+ "\t\t"
//									+ corredor.getNombre()
//									+ "\t\t"
//									+ clasificacionMasculina.get(i)
//											.getTiempoSegundos());
//				}
//			}
//		} else {
//			System.err
//					.println("ID invalido, ha de ser un numero mayor o igual a 0.");
//		}
	}

	private static String consoleRead() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// InputStreamReader isr = new InputStreamReader(System.in);
		// BufferedReader br = new BufferedReader (isr);
		String lectura = "";
		try {
			System.out.print(">");
			lectura = br.readLine();
		} catch (IOException e) {
			System.err.println("No se ha leído adecuadamente");
		}
		return lectura;
	}

}

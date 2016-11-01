package utiles;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import logica.Gestor;
import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;
import logica.Vistas.PlazoInscripcion;

public class Asignador {

	/**
	 * Metodo asignarAtleta que relaciona la inscripcion con el atleta
	 * 
	 * @param g
	 *            Gestor que maneja los datos
	 * @param ins
	 *            inscripcion
	 * @param dni
	 *            dni del atleta dueño de la inscripcion
	 * @return true si lo encuentra, false si no
	 */
	public static boolean asignarAtleta(Gestor g, Inscripcion ins, String dni) {
		for (Atleta at : g.getAtletas()) {
			if (dni.equals(at.getDNI())) {
				ins.setAtleta(at);
				return true;
			}
		}
		return false;
	}

	/***
	 * Metodo que asigna una inscripcion a su evento correspondiente
	 * 
	 * @param g
	 *            : gestor que gestiona todos los datos
	 * @param ins
	 *            : inscripcion a asignar
	 * @param id
	 *            : id del evento al que pertenece
	 * @return true si lo consigo, false si no
	 */
	public static boolean asignarAEvento(Gestor g, Inscripcion ins, int id) {
		for (Evento ev : g.getEventos()) {
			if (id == ev.getId()) {
				ev.añadirInscrito(ins);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que decodifica el string de las categorias, crea categorias para
	 * asignar a los eventos
	 * 
	 * @param stringCategoria
	 * @return Array con las categorias
	 */
	public static ArrayList<Categoria> decodificarCategorias(
			String stringCategoria) {
		ArrayList<Categoria> arrayCategorias = new ArrayList<Categoria>();
		String[] trozos = stringCategoria.split("@");
		for (String string : trozos) {
			String[] tr = string.split("%");
			arrayCategorias.add(new Categoria(tr[0], Integer.parseInt(tr[1]),
					Integer.parseInt(tr[2]), Integer.parseInt(tr[3])));
		}
		return arrayCategorias;
	}

	/**
	 * Metodo que decodifica el string de plazos y retorna una lista de plazos
	 * 
	 * @param stringEventos
	 * @return
	 */
	public static ArrayList<PlazoInscripcion> decodificaPlazos(
			String stringPlazos) {
		try {
			ArrayList<PlazoInscripcion> plazos = new ArrayList<PlazoInscripcion>();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String[] trozos = stringPlazos.split("@");
			for (String string : trozos) {
				String[] tr = string.split("%");

				plazos.add(new PlazoInscripcion(
						ConversorFechas
								.convertFechaJavaSQL(format.parse(tr[0])),
						ConversorFechas.convertFechaJavaSQL(format.parse(tr[1])),
						Double.parseDouble(tr[2])));
			}
			return plazos;
		} catch (NumberFormatException e) {
			System.out
					.println("Error de formato a la hora de decodificar plazos");
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static String codificarPlazos(ArrayList<PlazoInscripcion> plazos){
		StringBuilder sb = new StringBuilder();
		for(PlazoInscripcion plazo: plazos){
			sb.append("@"+plazo.getFechaInicio()+"%"+plazo.getFechaFin()+"%"+plazo.getPrecio());
		}return sb.toString();
		
	}
	public static String codificarCategorias(ArrayList<Categoria> categorias){
		StringBuilder sb = new StringBuilder();
		for(Categoria categoria: categorias){
			sb.append("@"+categoria.getNombre()+"%"+categoria.getEdadMinima()+"%"+categoria.getEdadMaxima()+"%"+categoria.getSexo());
		}return sb.toString();
	}
	
}

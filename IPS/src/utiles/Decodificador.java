package utiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import logica.Vistas.Categoria;
import logica.Vistas.PlazoInscripcion;

public class Decodificador {

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
			if(string!="" && string!=" "){
			String[] tr = string.split("%");
			arrayCategorias.add(new Categoria(tr[0], Integer.parseInt(tr[1]),
					Integer.parseInt(tr[2]), Integer.parseInt(tr[3])));
			}
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
				if(string!="" && string!=" "){
					String[] tr = string.split("%");
					plazos.add(new PlazoInscripcion(
							ConversorFechas
									.convertFechaJavaSQL(format.parse(tr[0])),
							ConversorFechas.convertFechaJavaSQL(format.parse(tr[1])),
							Double.parseDouble(tr[2])));
				}
			}
			return plazos;
		} catch (NumberFormatException e) {
			System.out
					.println("Error de formato a la hora de decodificar plazos");
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			System.err.println("[COD] Imposible decodificar fecha vacia");
			//e.printStackTrace();
			return null;
		}
	}
	public static String codificarPlazos(ArrayList<PlazoInscripcion> plazos){
		StringBuilder sb = new StringBuilder();
		for(PlazoInscripcion plazo: plazos){
			sb.append(plazo.getFechaInicio()+"%"+plazo.getFechaFin()+"%"+plazo.getPrecio()+"@");
		}return sb.toString();
		
	}
	public static String codificarCategorias(ArrayList<Categoria> categorias){
		StringBuilder sb = new StringBuilder();
		for(Categoria categoria: categorias){
			sb.append(categoria.getNombre()+"%"+categoria.getEdadMinima()+"%"+categoria.getEdadMaxima()+"%"+categoria.getSexo()+
					"@");
		}return sb.toString();
	}
	
	public static String codificarResultados(ArrayList<Integer> resultados){
		StringBuilder sB = new StringBuilder();
		for(Integer resultado : resultados)
			sB.append(resultado+"@");
		return null;
	}
	
	
	public static ArrayList<Integer> decodificarResultado(String resultados){
		ArrayList<Integer> tiempos = new ArrayList<Integer>();
		String[] res = resultados.split("@");
		for(String t : res){
			tiempos.add(Integer.parseInt(t));
		}
		return tiempos;
		
	}
}

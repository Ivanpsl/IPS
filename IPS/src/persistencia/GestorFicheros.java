package persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logica.Gestor;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;

public class GestorFicheros {

	// public void cargarEventos(ArrayList<Evento> ev) {
	// String nombreFichero = "ficheros/eventos.dat";
	// String linea=null;
	// try {
	// BufferedReader fichero = new BufferedReader(new
	// FileReader(nombreFichero));
	//
	// //Mientras quede información
	// while (fichero.ready()) {
	//
	// linea = fichero.readLine();
	// String[] trozos = linea.split("@");
	// SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	// Date cFecha = format.parse(trozos[5]);
	// Date fFecha = format.parse(trozos[6]);
	// java.sql.Date fin_insc = new java.sql.Date(fFecha.getTime());
	// java.sql.Date comienzo = new java.sql.Date(cFecha.getTime());
	// Evento evento= new
	// Evento(Integer.parseInt(trozos[0]),trozos[1],trozos[2],Double.parseDouble(trozos[3]),
	// Double.parseDouble(trozos[4]), comienzo,fin_insc);
	// ev.add(evento);
	// System.out.println("Añadido evento desde fichero .dat. evento --> " +
	// evento.toString());
	// }
	// fichero.close();
	// }catch (ParseException e){
	// System.out.println("Error con el formato de fechas.");
	// }
	// catch (FileNotFoundException fnfe){
	// System.out.println("El archivo no se ha encontrado.");
	// }
	// catch(IOException ioe){
	// new RuntimeException("Error de entrada/salida.");
	// }
	// }
	public void obtenerResultadosEvento(Evento ev, ConexionBD bd) {
		String nombreFichero = "ficheros/resultados/resultado" + ev.getId() + ".dat";
		String linea = null;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));

			while (fichero.ready()) {

				linea = fichero.readLine();
				String[] trozos = linea.split("-");
				ArrayList<Integer> resultados = new ArrayList<Integer>();
				resultados.add(Integer.parseInt(trozos[1]));
				ev.asignarTiemposDorsal(Integer.parseInt(trozos[0]),resultados, bd);
			}
			fichero.close();
			System.out.println(
					"[DAT] Fichero de resultados del evento con ID" + ev.getId() + " ha sido cargado correctamente.");
		} catch (FileNotFoundException fnfe) {
			System.err.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}
	/**
	 * 
	 * Se le tiene que pasar un FileReader ya creado con el path del fichero que se selecciona
	 * El fichero deberá tener el siguiente formato en .dat:
	 * 
	 * idEvento;dorsal;tEtapa1@tEtapa2@tEtapa3.....
	 * 
	 * Este metodo solo carga para un evento, en el fichero ha de estar el id para comprobar que 
	 * realmente pertenece el resultado al evento en cuestion
	 * @param nombreFichero
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	public static void cargarClasificacionDeEvento(Evento e,FileReader fich, Gestor g) throws NumberFormatException, IOException{
		
		
		//FileReader fich = null;
		BufferedReader br = null;
		String cadena;
		String[] cacho;
//		try {
//			fich = new FileReader(nombreFichero);
//		} catch (FileNotFoundException e2) {
//			System.err.println("No se ha encontrado el fichero que se quiere leer");
//			throw new FileNotFoundException(); //Para que la coja la interfaz
//		}
		br = new BufferedReader(fich);
		
		while((cadena = br.readLine()) != null){
			cacho = cadena.split(";");
			
			int idEvento = Integer.parseInt(cacho[0]);
			int dorsal = Integer.parseInt(cacho[1]);
			String tiempos = cacho[2];
			if(e.getId() == idEvento){ //Si pertenece a este evento, se añadira la clasificacion
//					if(i.getDorsal() == dorsal){
//						separarYAñadirTiempos(tiempos, i);
//						
//					}
					ArrayList<Integer> tiemposList = separarYAñadirTiempos(tiempos);
					g.asignarTiemposAEvento(dorsal, tiemposList, e);
				
			}
			
		}
		
		br.close();
	}
	
	private static ArrayList<Integer> separarYAñadirTiempos(String tiempos){
		String[] times = tiempos.split("@");
		ArrayList<Integer> tiemposList = new ArrayList<Integer>();
		for(String s : times){
			int tiempoEtapa = Integer.parseInt(s);
			tiemposList.add(tiempoEtapa);
		}
		return tiemposList;
	}
}

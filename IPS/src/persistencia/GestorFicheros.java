package persistencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import logica.Evento;


public class GestorFicheros {
	
//	public void cargarEventos(ArrayList<Evento> ev) {
//		    String nombreFichero = "ficheros/eventos.dat";
//		    String linea=null;
//		    try {
//		      BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));
//		
//		      //Mientras quede información
//		      while (fichero.ready()) {
//		    	  
//		        linea = fichero.readLine();
//		        String[] trozos = linea.split("@");
//		        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		        Date cFecha = format.parse(trozos[5]);
//		        Date fFecha = format.parse(trozos[6]);
//		        java.sql.Date fin_insc = new java.sql.Date(fFecha.getTime());
//		        java.sql.Date comienzo = new java.sql.Date(cFecha.getTime());
//		        Evento evento= new Evento(Integer.parseInt(trozos[0]),trozos[1],trozos[2],Double.parseDouble(trozos[3]),
//		        		Double.parseDouble(trozos[4]), comienzo,fin_insc);
//		        ev.add(evento);
//		        System.out.println("Añadido evento desde fichero .dat. evento --> " + evento.toString());
//		      }
//		      fichero.close();
//		     }catch (ParseException e){
//		    	System.out.println("Error con el formato de fechas.");
//		    }
//		    catch (FileNotFoundException fnfe){
//		        System.out.println("El archivo no se ha encontrado.");
//		    }
//		    catch(IOException ioe){
//		        new RuntimeException("Error de entrada/salida.");
//	    }
//	 }
	public void obtenerResultadosEvento(Evento ev, ConexionBD bd) {
	    String nombreFichero = "ficheros/resultados/resultado"+ev.getId()+".dat";
	    String linea=null;
	    try {
	      BufferedReader fichero = new BufferedReader(new FileReader(nombreFichero));
	
	      while (fichero.ready()) {
	    	  
	        linea = fichero.readLine();
	        String[] trozos = linea.split("-");
	        ev.asignarTiemposDorsal(Integer.parseInt(trozos[0]), Integer.parseInt(trozos[1]), bd);
	      }
	      fichero.close();
	      System.out.println("[DAT] Fichero de resultados del evento con ID" + ev.getId() + " ha sido cargado correctamente.");
	    }
	    catch (FileNotFoundException fnfe){
	        System.err.println("El archivo no se ha encontrado.");
	    }
	    catch(IOException ioe){
	        new RuntimeException("Error de entrada/salida.");
    }
 }
}

package logica;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;

import logica.Vistas.PlazoInscripcion;
/**
 * Clase que posee m�todos static para poder gestionar plazos de Inscripcion
 *
 *
 */
public class GestorFechasInscripcion {
	public static PlazoInscripcion plazoQueFalla1 = null;
	public static PlazoInscripcion plazoQueFalla2 = null;
	/**
	 * Lo que comprueba es si en la lista de los plazos de inscripcion que se le pasa como parametro al metodo
	 * existe algun plazo que tenga una de las dos fechas dentro del rango de otra. De ser asi, los plazos estar�an mal
	 * creados porque se solapar�an. 
	 * Si los plazos est�n mal creados el metodo devolveria false. De estar todo bien true. 
	 * @param lista
	 * @return
	 */
	public static boolean comprobarFechasCorrectas(ArrayList<PlazoInscripcion> lista){
		for(PlazoInscripcion pl1 : lista){
			for(PlazoInscripcion pl2 : lista){
				if(!pl1.equals(pl2)){
					if(pl1.getFechaInicio().getTime() <= pl2.getFechaInicio().getTime() && pl1.getFechaFin().getTime() >= pl2.getFechaInicio().getTime()){
						plazoQueFalla1 = pl1;
						plazoQueFalla2 = pl2;
						return false;
					}
						
					else if(pl1.getFechaInicio().getTime() <= pl2.getFechaFin().getTime() && pl1.getFechaFin().getTime() >= pl2.getFechaFin().getTime()){
						plazoQueFalla1 = pl1;
						plazoQueFalla2 = pl2;
						return false;
					}
						
				}
			}
		}
		return true;
		
	}
	/**
	 * �ste m�todo ordena la lista de plazos de inscripci�n, por lo que antes se debe comprobar
	 * si est�n bien los rangos o lo ordenar� mal. 
	 * 
	 * En caso de que la fecha de comienzo de la carrera sea m�s tarde que la ultima fecha de los plazos, devuelve
	 * true, en caso contrario, false;
	 * @param fechaActual
	 * @param lista
	 * @return
	 */
	public static boolean comprobarFechaInicioConLaListaDePlazos(Date fechaActual, ArrayList<PlazoInscripcion> lista){
		lista.sort(new Comparator<PlazoInscripcion>() {
			@Override
			public int compare(PlazoInscripcion cat1, PlazoInscripcion cat2) {
				return cat1.compareTo(cat2);
			}
		}); //La lista ya esta ordenada por fechas (Deben estar bien los rangos para que salgan bien creadas)
		Date fechaUltima = lista.get(lista.size()-1).getFechaFin();
		if(fechaActual.getTime() < fechaUltima.getTime())
			return false;
		return true;
	}
}

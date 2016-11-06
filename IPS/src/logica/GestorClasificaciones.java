package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import logica.Vistas.Categoria;
import logica.Vistas.Clasificacion;
import logica.Vistas.Evento;
import logica.Vistas.Inscripcion;

public class GestorClasificaciones {


	
//	public GestorClasificaciones(ArrayList<Inscripcion> inscripciones, ArrayList<Categoria> categorias){
//		calcularInscripciones(inscripciones,categorias);
//	}
	
	/**
	 * Calcula la clasificacion por cada categoria 
	 * 
	 * @param ins
	 * @param categorias
	 * @return
	 */
	public ArrayList<Clasificacion> calcularInscripciones(ArrayList<Inscripcion> ins, ArrayList<Categoria> categorias){
		ArrayList<Clasificacion> clasificaciones = new ArrayList<Clasificacion>();
		
		//Calculamos la clasificacion absoluta
		ArrayList<Inscripcion> lAbsoluta = ins;
		ordenar(lAbsoluta);
		Clasificacion absoluta = new Clasificacion("Absoluta", lAbsoluta);
		clasificaciones.add(absoluta);
		
		//por cada categoria generamos una clasificacion
		ArrayList<Inscripcion> aux = new ArrayList<Inscripcion>();
		for(Categoria cat : categorias){
			aux.clear();
			for(Inscripcion corredor: ins){
				if(cat.estaDentro(corredor.getAtleta())){
					aux.add(corredor);
				}
			}
			ordenar(aux);
			clasificaciones.add(new Clasificacion(cat.getNombre(), aux));
		}
		return clasificaciones;
	}
	
	
	/**
	 * Metodo usado para ordenar los resultados de las clasificaciones
	 * @param array
	 */
	private void ordenar(ArrayList<Inscripcion> array){
		Collections.sort(array, new Comparator<Inscripcion>() {
			@Override
			public int compare(Inscripcion ins1, Inscripcion ins2) {
				return new Integer(ins1.getResultado()).compareTo(ins2.getResultado());
			}
		});
	}
	
	
	 /**
	  * Metodo que devuelve la posicion en la clasificacion de un corredor
	  * @param inscrito
	  * @return: posicion
	  */
	public int obtenerPosicion(Inscripcion inscrito, Clasificacion clasificacion){
		
		ArrayList<Inscripcion> corredores = clasificacion.getCorredores();
		for(int i=0; i< corredores.size(); i++){
				if(corredores.get(i).getAtleta().equals(inscrito.getAtleta())){
					return i+1;
				}
			}
		return -1;
	}
}
	
	

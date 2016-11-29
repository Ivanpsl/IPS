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
			//aux.clear();
			aux = new ArrayList<Inscripcion>();
			for(Inscripcion corredor: ins){
				if(cat.estaDentro(corredor.getAtleta()) || cat.getNombre().equals(corredor.getCategoria())){
					aux.add(corredor);
					corredor.setCategoriaObj(cat);
				}
			}
			ordenar(aux);
			Clasificacion clas= new Clasificacion(cat.getNombre(), aux);
			
			clasificaciones.add(clas);
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
	  * Metodo que devuelve la posicion en la clasificacion de un corredor por medio de la clasificacion y de la inscripcion
	  * @param inscrito
	  * @param clasificacion
	  * @return: posicion
	  */
	public int obtenerPosicion(Inscripcion inscrito, Clasificacion clasificacion){
		
		ArrayList<Inscripcion> corredores = clasificacion.getCorredores();
		for(int i=0; i< corredores.size(); i++){
				if(corredores.get(i).getAtleta().equals(inscrito.getAtleta())){
					return i+1;
				}
			}
		return -2;
	}
	
	/**
	 * Metodo que devuelve la posicion en la clasificacion de un corredor por medio del evento y la inscripcion 
	 * @param inscrito
	 * @param evento
	 * @return posicion
	 */
	public int obtenerPosicion(Inscripcion inscrito,Evento evento){
		Categoria categoria= null;
		for(Categoria c: evento.getCategorias()){
			if(c.estaDentro(inscrito.getAtleta())&& categoria==null){
				categoria=c;
			}
		}if(categoria!=null){
			for(Clasificacion cl : evento.getClasificaciones()){
				if(cl.getCategoria().equals(categoria.getNombre())){
					return obtenerPosicion(inscrito, cl);
				}
			}
		}return -1;
		
	}
	/**
	 * MEtodo que devuelve la posicion absoluta en un evento de un corredor
	 */
	public int obtenerPosicionAbsoluta(Inscripcion inscrito, Evento evento){
		for(Clasificacion cl : evento.getClasificaciones()){
			if(cl.getCategoria().equals("Absoluta")) return obtenerPosicion(inscrito, cl);
		}
		return -2;
	}
	
}
	
	

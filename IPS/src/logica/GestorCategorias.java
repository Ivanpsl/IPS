package logica;

import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.Comparator;

import logica.Vistas.Categoria;
import logica.Vistas.Evento;

public class GestorCategorias {
	static ArrayList<Categoria> categorias;
	Evento evento;
	// boolean quieroCategoriasPorDefecto;

	// Categorias por defecto
	static Categoria[] categoriasDef = {
	new Categoria("Senior", 18, 35, Categoria.MASCULINO),
	new Categoria("Senior", 18, 35, Categoria.MASCULINO)
	};

	public GestorCategorias() {

	}
	
	/**
	 * Carga las categorias de un evento. 
	 * @param e
	 */
	public void cargarCategoriasDeEvento(Evento e){
		cargarCaegorias(e.getCategorias());
	}
	/**
	 * Si se le pasa como argumento null, quiere decir que el organizador quiere
	 * las categorias por defecto.
	 * 
	 * @param misCat
	 */
	public static void cargarCaegorias(ArrayList<Categoria> misCat) {
//		categorias = new ArrayList<Categoria>(); // Vaciar
//		if (misCat == null) {
//			categorias.add(senior);
//			categorias.add(seniorFem);
//		} else {
//			categorias.addAll(misCat);
//		}
		//ordenarCategorias();
	}

	/**
	 * Metodo que calcula la edad maxima de las categorias que tengo en la
	 * competicion y la minimoa
	 * 
	 * @param cats
	 * @return Array de eMinima,eMaxima
	 */
	private int[] getEdadMaxMinCategorias(ArrayList<Categoria> cats) {
		int edadMax = 0;
		int edadMin = 999999;
		for (Categoria cat : cats) {
			if (edadMax < cat.getEdadMaxima())
				edadMax = cat.getEdadMaxima();
			if (edadMin > cat.getEdadMinima()) {
				edadMin = cat.getEdadMinima();
			}
		}
		int[] edades = { edadMin, edadMax };
		return edades;
	}
	
//	private static void ordenarCategorias(){
//		if(categorias == null)
//			throw new IllegalSelectorException(); //no hay categorias asignadas
//		categorias.sort(new Comparator<Categoria>() {
//
//			@Override
//			public int compare(Categoria cat1, Categoria cat2) {
//				return cat1.compareTo(cat2);
//			}
//		});
//	}
	/**
	 * Carga las categorias por defecto en una lista que se le pasa como parámetro BUENO
	 * @param list
	 */
	public static void cargarCategoriasPorDefecto(ArrayList<Categoria> list){
		list = new ArrayList<Categoria>();
		for(Categoria c : categoriasDef)
			list.add(c);
	}
	/**
	 * Método para ordenar las categorías de una lista de categorias BUENO
	 * @param list
	 */
	public static void ordenarCategorias(ArrayList<Categoria> list){
		if(list.isEmpty())
			throw new IllegalStateException("No hay contenido en la lista de categorías a ordenar");
		list.sort(new Comparator<Categoria>() {
			@Override
			public int compare(Categoria cat1, Categoria cat2) {
				return cat1.compareTo(cat2);
			}
		});
	}
	private boolean dentroDeRango(Categoria cat, int edad){
		if(cat.getEdadMinima()<= edad && cat.getEdadMaxima()>= edad){
			return true;
		}
		return false;
	}
	
	/**
	 * Si este metodo falla igual es por el compare to de la categoria.
	 * @param categoriasPrueba
	 * @return
	 */
	public boolean comprobarCategoriasCorrectas(ArrayList<Categoria> categoriasPrueba){
		int edades[] = getEdadMaxMinCategorias(categoriasPrueba);
		int edadActual = edades[0];
		int edadMaxima = edades[1];
		int pointer = 0;
		for (edadActual = edades[0]; edadActual < edadMaxima; edadActual++){
			if(dentroDeRango(categoriasPrueba.get(pointer), edadActual)){
				//Todo va bien
			}else{ //Si no esta dentro del rango pasa a comprobar a la categoria siguiente
				if(pointer < categoriasPrueba.size()){
					pointer++;
					edadActual--;//Para que en la siguiente iteraccion vuelva a comrpobar la misma edad
				}else{//Si ya no quedan categorias en las que probar, es que hay algo mal
					return false;
				}
			}
		}
		return true;
		
	}
}

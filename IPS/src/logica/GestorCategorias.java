package logica;


import java.util.ArrayList;
import java.util.Comparator;

import logica.Vistas.Atleta;
import logica.Vistas.Categoria;
import logica.Vistas.Evento;

public class GestorCategorias {
	static ArrayList<Categoria> categorias;
	Evento evento;
	// boolean quieroCategoriasPorDefecto;

	// Categorias por defecto
	static Categoria[] categoriasDef = {
	
		new Categoria("Senior", 18, 35, Categoria.MASCULINO),
		new Categoria("VeteranoA", 36, 40, Categoria.MASCULINO),
		new Categoria("VeteranoB", 41, 45, Categoria.MASCULINO),
		new Categoria("VeteranoC", 46, 50, Categoria.MASCULINO),
		new Categoria("VeteranoD", 51, 55, Categoria.MASCULINO),
		new Categoria("VeteranoE", 56, 60, Categoria.MASCULINO),
		new Categoria("VeteranoF", 61, 65, Categoria.MASCULINO),
		new Categoria("VeteranoG", 66, 70, Categoria.MASCULINO),
		new Categoria("VeteranoH", 71, 75, Categoria.MASCULINO),
		new Categoria("VeteranoI", 76, 80, Categoria.MASCULINO),
		new Categoria("VeteranoJ", 81, 85, Categoria.MASCULINO),
		new Categoria("VeteranoK", 86, 90, Categoria.MASCULINO),
		
		new Categoria("Senior", 18, 35, Categoria.FEMENINO),
		new Categoria("VeteranoA", 36, 40, Categoria.FEMENINO),	
		new Categoria("VeteranoB", 41, 45, Categoria.FEMENINO),	
		new Categoria("VeteranoC", 46, 50, Categoria.FEMENINO),	
		new Categoria("VeteranoD", 51, 55, Categoria.FEMENINO),	
		new Categoria("VeteranoE", 56, 60, Categoria.FEMENINO),	
		new Categoria("VeteranoF", 61, 65, Categoria.FEMENINO),	
		new Categoria("VeteranoG", 66, 70, Categoria.FEMENINO),	
		new Categoria("VeteranoH", 71, 75, Categoria.FEMENINO),	
		new Categoria("VeteranoI", 76, 80, Categoria.FEMENINO),
		new Categoria("VeteranoJ", 81, 85, Categoria.FEMENINO),	
		new Categoria("VeteranoK", 86, 90, Categoria.FEMENINO)
	
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
	public static ArrayList<Categoria> getCategoriasDefecto(){
		ArrayList<Categoria> list = new ArrayList<Categoria>();
		for(Categoria c : categoriasDef)
			list.add(c);
		return list;
	}

	/**
	 * Metodo que calcula la edad maxima de las categorias que tengo en la
	 * competicion y la minimoa
	 * 
	 * @param cats
	 * @return Array de eMinima,eMaxima
	 */
	private static int[] getEdadMaxMinCategorias(ArrayList<Categoria> cats, int sexo) {
		int edadMax = 0;
		int edadMin = 999999;
		for (Categoria cat : cats) {
			if(cat.getSexo()==sexo){
				if (edadMax < cat.getEdadMaxima())
					edadMax = cat.getEdadMaxima();
				if (edadMin > cat.getEdadMinima()) {
					edadMin = cat.getEdadMinima();
				}
			}
		}
		int[] edades = { edadMin, edadMax };
		return edades;
	}
	
	public static int[] getPosEdadMaxMinCategorias(ArrayList<Categoria> cats, int sexo) {
		int edadMax = 0;
		int edadMin = 999999;
		int max =0;
		int min=0;
		for (int i=0;i<cats.size();i++) {
			if(cats.get(i).getSexo()==sexo){
				if (edadMax < cats.get(i).getEdadMaxima()){
					edadMax = cats.get(i).getEdadMaxima();
					max=i;
				}
					
				if (edadMin > cats.get(i).getEdadMinima()) {
					edadMin = cats.get(i).getEdadMinima();
					min=i;
				}
			}
		}
		int[] edades = { min, max };
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

	

	private static boolean comprobarRangoCategorias(ArrayList<Categoria> cat) {
		//COMPROBAMOS SOLO LAS FEMENINAS
		int edades[] = getEdadMaxMinCategorias(cat,Atleta.FEMENINO);
		int edadMin = edades[0];
		int edadMaxima = edades[1];
		Categoria categoria;
		for (int i = edadMin; i <= edadMaxima; i++) {
			categoria=null;
			for (int j = 0; j < cat.size(); j++) {
				if(cat.get(j).getEdadMinima()<=i && cat.get(j).getEdadMaxima()>=i && cat.get(j).getSexo()==Atleta.FEMENINO){
					categoria=cat.get(j);
				}
			}
			if(categoria==null){
				return false;
			}
		}
		// COMPROBAMOS SOLO LAS MASCULINAS
		edades = getEdadMaxMinCategorias(cat,Atleta.MASCULINO);
		edadMin = edades[0];
		edadMaxima = edades[1];
		
		for (int i = edadMin; i <= edadMaxima; i++) {
			categoria=null;
			for (int j = 0; j < cat.size(); j++) {
				if(cat.get(j).getEdadMinima()<=i && cat.get(j).getEdadMaxima()>=i && cat.get(j).getSexo()==Atleta.MASCULINO){
					categoria=cat.get(j);
				}
			}
			if(categoria==null){
				return false;
			}
		}
		return true;
	}

	private static  boolean comprobarSolapadosCategoria(ArrayList<Categoria> cat) {
		int maxima;
		int minima;
		//COMPROBAMOS LAS EDADES QUE NO ESTEN SOLAPADAS femeninas
		
		boolean repetido = false;
		for (int i = 0; i < cat.size(); i++) {
			if(cat.get(i).getSexo()==Atleta.FEMENINO){
				minima = cat.get(i).getEdadMinima();
				maxima=cat.get(i).getEdadMaxima();
				repetido = false;
				for (int j = 0; j < cat.size(); j++) {
					if(cat.get(j).getSexo()==Atleta.FEMENINO){
						if(i!=j){
							if(cat.get(j).getEdadMinima()>= minima && cat.get(j).getEdadMinima()<= maxima ){
								repetido = true;
							}
							if(cat.get(j).getEdadMaxima()>= minima && cat.get(j).getEdadMaxima()<= maxima ){
								repetido = true;
							}
						}
						
					}
				}
				if(repetido){
					return false;
				}
			}
		}
		
		
		//COMPROBAMOS LAS EDADES QUE NO ESTEN SOLAPADAS MASCULINAS
		
		
		for (int i = 0; i < cat.size(); i++) {
			if(cat.get(i).getSexo()==Atleta.MASCULINO){
				minima = cat.get(i).getEdadMinima();
				maxima=cat.get(i).getEdadMaxima();
				repetido = false;
				for (int j = 0; j < cat.size(); j++) {
					
					if(cat.get(j).getSexo()==Atleta.MASCULINO){
						if(i!=j){
							if(cat.get(j).getEdadMinima()>= minima && cat.get(j).getEdadMinima()<= maxima ){
								repetido = true;
							}
							if(cat.get(j).getEdadMaxima()>= minima && cat.get(j).getEdadMaxima()<= maxima ){
								repetido = true;
							}
						}
					}
				}
				if(repetido){
					return false;
				}
			}
		}
		return true;
	}
	
	private static  boolean comprobarNombreRepetidoCategoria(ArrayList<Categoria> cat) {
		String nombre;
		//COMPROBAMOS LAS EDADES QUE NO ESTEN SOLAPADAS femeninas
		
		boolean repetido = false;
		for (int i = 0; i < cat.size(); i++) {
			if(cat.get(i).getSexo()==Atleta.FEMENINO){
				nombre = cat.get(i).getNombre();
				repetido = false;
				for (int j = 0; j < cat.size(); j++) {
					if(cat.get(j).getSexo()==Atleta.FEMENINO){
						if(i!=j){
							if(cat.get(j).getNombre().equals(nombre) ){
								repetido = true;
							}
							
						}
						
					}
				}
				if(repetido){
					return false;
				}
			}
		}
		
		
		//COMPROBAMOS LAS EDADES QUE NO ESTEN SOLAPADAS MASCULINAS
		for (int i = 0; i < cat.size(); i++) {
			if(cat.get(i).getSexo()==Atleta.MASCULINO){
				nombre = cat.get(i).getNombre();
				repetido = false;
				for (int j = 0; j < cat.size(); j++) {
					if(cat.get(j).getSexo()==Atleta.MASCULINO){
						if(i!=j){
							if(cat.get(j).getNombre().equals(nombre) ){
								repetido = true;
							}
							
						}
						
					}
				}
				if(repetido){
					return false;
				}
			}
		}
		return true;
	}

	public static String comprobarCategorias(ArrayList<Categoria> cat) {
		if(!comprobarRangoCategorias(cat) && !comprobarSolapadosCategoria(cat)&&!comprobarNombreRepetidoCategoria(cat)){
			return "todos";
		}
		if(!comprobarRangoCategorias(cat)){
			return "rango";
		}
		if(!comprobarSolapadosCategoria(cat)){
			return "solapados";
		}
		if(!comprobarNombreRepetidoCategoria(cat)){
			return "repetido";
		}
		return "ok";
		
	}
}

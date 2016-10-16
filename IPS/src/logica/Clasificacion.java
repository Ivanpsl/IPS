package logica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Clasificacion {

	ArrayList<Inscripcion> clasificacionAbsoluta = new ArrayList<Inscripcion>();
	ArrayList<Inscripcion> clasificacionMasculina = new ArrayList<Inscripcion>();
	ArrayList<Inscripcion> clasificacionFemenina = new ArrayList<Inscripcion>();
	
	public Clasificacion(ArrayList<Inscripcion> inscripciones){
		calcularInscripciones(inscripciones);
	}
	
	private void calcularInscripciones(ArrayList<Inscripcion> ins){
		clasificacionAbsoluta=ins;
		ordenar(clasificacionAbsoluta);
		for(Inscripcion inscripcion: ins){
			if(inscripcion.getAtleta().getSexo()==0){
				clasificacionMasculina.add(inscripcion);
			}else clasificacionFemenina.add(inscripcion);
		}
		ordenar(clasificacionMasculina);
		ordenar(clasificacionFemenina);
	}
	
	private void ordenar(ArrayList<Inscripcion> array){
		Collections.sort(array, new Comparator<Inscripcion>() {
			@Override
			public int compare(Inscripcion ins1, Inscripcion ins2) {
				return new Integer(ins1.getResultado()).compareTo(ins2.getResultado());
			}
		});
	}
	
	/**
	 * Metodo que devuelve la clasificacion ordenada segun los resultados de la carrera
	 * @param tipo: A = Absoluta, M= Masculina, F=femenina
	 * @return ArrayList con los resultados ordenados
	 */
	public ArrayList<Inscripcion> obtenerClasificacion(String tipo){
		if(tipo.equals("A")) return clasificacionAbsoluta;
		if(tipo.equals("M")) return clasificacionMasculina;
		if(tipo.equals("F")) return clasificacionFemenina;
		else {
			System.err.println("No existe ninguna clasificacion de ese tipo");
			return null;
		}
	}
	
	//en el return devuelve la absoluta, mientras que en la variable posCategoria devuelve la de su categoria
	public int obtenerPosicion(Inscripcion inscrito, int posCategoria){
		if(inscrito.getCategoria().equals("Masculina")){
			for(int i=0; i<clasificacionMasculina.size(); i++){
				if(clasificacionMasculina.get(i).getAtleta().equals(inscrito.getAtleta())){
					posCategoria=i+1;
					break;
				}
			}
		}else{
			for(int i=0; i<clasificacionFemenina.size(); i++){
				if(clasificacionFemenina.get(i).getAtleta().equals(inscrito.getAtleta())){
					posCategoria=i+1;
					break;
				}
			}
		}	for(int i=0; i<clasificacionAbsoluta.size(); i++){
				if(clasificacionAbsoluta.get(i).getAtleta().equals(inscrito.getAtleta())){
					return i+1;
					
				}
			}
		return -1;
	}
}

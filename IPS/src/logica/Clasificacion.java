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
	public ArrayList<Inscripcion> obtenerClasificacion(String tipo){
		if(tipo.equals("absoluta")) return clasificacionAbsoluta;
		if(tipo.equals("masculina")) return clasificacionMasculina;
		if(tipo.equals("femenina")) return clasificacionFemenina;
		else {
			System.err.println("No existe ninguna clasificacion de ese tipo");
			return null;
		}
		
	}
}

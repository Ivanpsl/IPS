package logica.Vistas;

import java.util.ArrayList;

public class Clasificacion {
	
	String nombre;
	ArrayList<Inscripcion> corredores;
	
	public Clasificacion(String nombre, ArrayList<Inscripcion> corredores){
		this.nombre=nombre;
		this.corredores=corredores;
	}

	public String getCategoria() {
		return nombre;
	}

	public ArrayList<Inscripcion> getCorredores() {
		return corredores;
	}
	
}


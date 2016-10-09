package logica;

import java.util.ArrayList;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		cargarEventosDisponibles();
	}
	
	private void cargarEventosDisponibles(){
		//comando SQL para obtener todos los eventos
		//Crear objetos evento por cada uno de los obtenidos
		//cargar en el arraylist eventos
	}
	
	public void mostrarEventosDisponibles(){
		for(Evento evento : eventos){
			System.out.println(evento.toString());
		}
	}
	public ArrayList<Evento> getEventosDisponibles(){
		return this.eventos;
	}
}

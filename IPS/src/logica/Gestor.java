package logica;

import java.util.ArrayList;

import persistencia.ConexionBD;

public class Gestor {
	
	
	ArrayList<Evento> eventos;
	private ConexionBD bd = new ConexionBD();
	public Gestor(){
		this.eventos  = new ArrayList<Evento>();
		cargarEventosDisponibles();
	}
	
	private void cargarEventosDisponibles(){
		bd.cargarEventos(eventos);
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
